package com.sudabOrdenes.ms_ordenes.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sudabOrdenes.ms_ordenes.Client.KardexClient;
import com.sudabOrdenes.ms_ordenes.Client.ProformaClient;

import com.sudabOrdenes.ms_ordenes.DTO.OrdenDetalleDTO;
import com.sudabOrdenes.ms_ordenes.DTO.OrdenRequestDTO;
import com.sudabOrdenes.ms_ordenes.DTO.OrdenResponseDTO;
import com.sudabOrdenes.ms_ordenes.DTO.kardex.KardexMovimientoRequestDTO;
import com.sudabOrdenes.ms_ordenes.DTO.proforma.DetalleProformaResponseDTO;
import com.sudabOrdenes.ms_ordenes.DTO.proforma.ProformaResponseDTO;

import com.sudabOrdenes.ms_ordenes.Model.OrdenCompra;
import com.sudabOrdenes.ms_ordenes.Model.OrdenCompraDetalle;
import com.sudabOrdenes.ms_ordenes.Repository.OrdenCompraRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.transaction.annotation.Transactional;


import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor

public class OrdenService {

    
    // private final ProformaRepository proformaRepository;
    // private final KardexService kardexService;
    private final OrdenCompraRepository ordenCompraRepository;
    private final ProformaClient proformaClient;
   
    private final KardexClient kardexClient;
    


    @Transactional
    public OrdenResponseDTO generarOrden(OrdenRequestDTO ordenrequest){

        // 1. Buscar la proforma de forma remota a través de OpenFeign
        ProformaResponseDTO proforma = proformaClient.obtenerPorId(ordenrequest.getIdProforma());
        if (proforma == null) {
            throw new EntityNotFoundException("Proforma no encontrada en el sistema.");
        }


        // Validación de seguridad para evitar duplicaciones accidentales
        if("SELECCIONADA".equals(proforma.getEstado())){
            throw new IllegalStateException("La proforma ya ha sido seleccionada para una orden.");
        }



        OrdenCompra nuevaOC= new OrdenCompra();
        nuevaOC.setCodigo("OC-"+LocalDate.now().getYear()+"-"+ (System.currentTimeMillis() % 100000));
        nuevaOC.setFechaCreacion(LocalDate.now());
        nuevaOC.setDescripcion("Orden de compra");
        nuevaOC.setEstado("PENDIENTE");
        nuevaOC.setIdProforma(proforma.getIdProforma());
        nuevaOC.setIdProveedor(proforma.getIdProveedor());
        
        
        List<OrdenCompraDetalle> productos= new ArrayList<>();
        Double monto=0.0;

        for(DetalleProformaResponseDTO item: proforma.getProductos()){
            OrdenCompraDetalle detallesOC= new OrdenCompraDetalle();

            detallesOC.setIdProducto(item.getIdProducto());
            detallesOC.setCantidad(item.getCantidad());
            detallesOC.setPrecioUnitario(item.getPrecioUnitario());

            detallesOC.setOrdenCompra(nuevaOC);

            
            monto+=item.getPrecioUnitario()*item.getCantidad(); 
            productos.add(detallesOC);
        }

        nuevaOC.setMontoTotal(monto);
        nuevaOC.setDetalles(productos);
        // nuevaOC.setEstado ("SELECCIONADO");

        nuevaOC.setFechaEntrega(LocalDate.parse(ordenrequest.getFechaEntrega())); // Si el DTO manda String, lo pasas a LocalDate
        nuevaOC.setLugarEntrega(ordenrequest.getLugarEntrega());
        nuevaOC.setObservaciones(ordenrequest.getObservaciones());
        nuevaOC.setFormaPago(ordenrequest.getFormaPago());
        nuevaOC.setPlazoEntrega(ordenrequest.getPlazoEntrega());
        nuevaOC.setGarantia(ordenrequest.getGarantia());


        // 6. SOLUCIÓN AL ERROR: Invocar al Microservicio Origen para mutar el estado en su BD
        try {
            proformaClient.actualizarEstado(proforma.getIdProforma(), "SELECCIONADA");
        } catch (Exception e) {
            // Si la red falla y no podemos marcar la proforma, lanzamos excepción 
            // para gatillar el ROLLBACK automático de nuestra OrdenCompra local
            throw new RuntimeException("No se pudo bloquear la proforma. Operación abortada por consistencia.");
        }

        
        // 7. Persistir la Orden de Compra en nuestra Base de Datos local
        OrdenCompra ocGuardad=ordenCompraRepository.save(nuevaOC);


        return convertirAConvertirDTO(ocGuardad);
        
      }


    public List<OrdenResponseDTO> listarOrdenService() {
        List<OrdenCompra> ordenes= ordenCompraRepository.findAll();
        return ordenes.stream().map(this::convertirAConvertirDTO).toList();
    }


    @Transactional(readOnly = true)
    public OrdenResponseDTO consultarOrdenService(Long id) {
        OrdenCompra orden = ordenCompraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden de compra no encontrada."));
        return convertirAConvertirDTO(orden);
    }


    public OrdenResponseDTO enviarOrdenService(Long id) {
        // 1. Buscas la orden que aprobó el director administrativo
        OrdenCompra orden = ordenCompraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada."));

        // 2. Validación: Que esté aprobada para poder mandarla
        if (!"APROBADA".equals(orden.getEstado())) {
            throw new IllegalStateException("La orden debe estar APROBADA para enviarse.");
        }

        // 3. EL CAMBIO CLAVE: Cambias el estado a ENVIADA. 
        // Al guardarse en la BD, automáticamente el proveedor ya la puede ver en su usuario.
        orden.setEstado("ENVIADA");
        
        OrdenCompra ordenEnviada = ordenCompraRepository.save(orden);
        return convertirAConvertirDTO(ordenEnviada);
    }   




    @Transactional
    public OrdenResponseDTO autorizarYFirmarOrden(Long idOrden, String usernameDirector) {
        // 1. Buscar la orden
        OrdenCompra orden = ordenCompraRepository.findById(idOrden)
                .orElseThrow(() -> new RuntimeException("Orden de compra no encontrada: " + idOrden));

        // 2. Validar estado válido para firma
        if (!"PENDIENTE".equals(orden.getEstado())) {
            throw new IllegalStateException("La orden no se encuentra en estado PENDIENTE_AUTORIZACION");
        }

        // 3. Modificar datos de control de flujo
        orden.setEstado("APROBADA");
        orden.setAutorizadoPor(usernameDirector);
        orden.setFechaAutorizacion(LocalDateTime.now());

        // 4. Simulación Avanzada de Sello / Firma Digital Criptográfica
        try {
            String datosAFirmar = orden.getCodigo() + "|" + orden.getFechaAutorizacion() + "|" + usernameDirector;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(datosAFirmar.getBytes(StandardCharsets.UTF_8));
            String firmaCalculada = Base64.getEncoder().encodeToString(hashBytes);
            
            orden.setFirmaDigitalHash("SUDAB-SIG-" + firmaCalculada);
        } catch (Exception e) {
            orden.setFirmaDigitalHash("SUDAB-SIG-FALLBACK-HASH-" + orden.getIdOrden());
        }

        // 5. Guardar cambios en Neon DB
        OrdenCompra ordenGuardada = ordenCompraRepository.save(orden);

        // 6. Mapear y retornar tu DTO de respuesta habitual
        return convertirAConvertirDTO(ordenGuardada);
    }


    public String archivarOrdenService(Long id){
        OrdenCompra orden=ordenCompraRepository.findById(id).orElseThrow(() -> new RuntimeException("Orden no encontrada."));

        if(!"APROBADA".equals(orden.getEstado())){
            throw new IllegalStateException("Solo se pueden archivar órdenes en estado APROBADA.");
        }


        orden.setEstado("ARCHIVADA");
        ordenCompraRepository.save(orden);

        return "Orden archivada exitosamente.";


    }


    
    @Transactional
    public String cancelarOrdenService(Long id) {
        OrdenCompra orden = ordenCompraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden de compra no encontrada."));

        // Solo se puede cancelar si no ha sido enviada al proveedor
        if ("ENVIADA".equals(orden.getEstado()) || "ARCHIVADA".equals(orden.getEstado())) {
            throw new IllegalStateException("No se puede cancelar una orden que ya fue despachada o archivada.");
        }

        orden.setEstado("CANCELADA");
        
        // Liberamos la proforma asociada para que pueda volver a cotizarse si es necesario
        // if (orden.getIdProforma() != null) {
        //     ProformaDTO proforma = orden.getProforma();
        //     proforma.setEstado("PENDIENTE"); // O el estado inicial de tu flujo de proformas
        //     proformaRepository.save(proforma);
        // }

        // Llamada al otro microservicio como si fuera un método local
        proformaClient.liberarProforma(orden.getIdProforma());

        ordenCompraRepository.save(orden);
        return "Orden cancelada exitosamente y proforma liberada.";
    }


    public List<OrdenResponseDTO> listarOrdenesPorProveedorService(Long idProveedor) {
    // Suponiendo que tu entidad Orden tiene una relación con Proveedor o guarda su ID:
    return ordenCompraRepository.findById(idProveedor).stream()
            .map(this::convertirAConvertirDTO) // Usa tu método existente de conversión a DTO
            .collect(Collectors.toList());
    }   
    
    @Transactional(readOnly = true)
    public List<OrdenResponseDTO> listarOrdenesParaVerificacion() {
    return ordenCompraRepository.findByEstadoIn(List.of("APROBADA", "ENVIADA"))
            .stream()
            .map(this::convertirAConvertirDTO)
            .toList();
}

    @Transactional
        public OrdenResponseDTO recepcionarOrden(Long id) {
        OrdenCompra orden = ordenCompraRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Orden de compra no encontrada."));

    String estado = orden.getEstado();
    if (!"ENVIADA".equals(estado) && !"APROBADA".equals(estado)) {
        throw new IllegalStateException("Solo se pueden recepcionar órdenes en estado APROBADA o ENVIADA.");
    }

    orden.setEstado("RECIBIDA");
    orden.setObservaciones(
            (orden.getObservaciones() == null ? "" : orden.getObservaciones() + "\n")
            + "Recepción conforme registrada."
    );

    OrdenCompra guardada = ordenCompraRepository.save(orden);
    return convertirAConvertirDTO(guardada);
}

@Transactional
public OrdenResponseDTO registrarPedidoPendiente(Long id, String motivo) {
    OrdenCompra orden = ordenCompraRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Orden de compra no encontrada."));

    String estado = orden.getEstado();
    if (!"ENVIADA".equals(estado) && !"APROBADA".equals(estado) && !"RECIBIDA".equals(estado)) {
        throw new IllegalStateException("Solo se puede registrar pedido pendiente para órdenes ENVIADAS, APROBADAS o RECIBIDAS.");
    }

    orden.setEstado("PEDIDO_PENDIENTE");
    orden.setObservaciones(
            (orden.getObservaciones() == null ? "" : orden.getObservaciones() + "\n")
            + "Pedido pendiente: " + motivo
    );

    OrdenCompra guardada = ordenCompraRepository.save(orden);
    return convertirAConvertirDTO(guardada);
}

@Transactional(readOnly = true)
public List<OrdenResponseDTO> listarPedidosPendientes() {
    return ordenCompraRepository.findByEstado("PEDIDO_PENDIENTE")
            .stream()
            .map(this::convertirAConvertirDTO)
            .toList();
}   



    private OrdenResponseDTO convertirAConvertirDTO(OrdenCompra oc) {
        OrdenResponseDTO dto = new OrdenResponseDTO();
    dto.setIdOrden(oc.getIdOrden());
    dto.setCodigo(oc.getCodigo());
    dto.setFechaCreacion(oc.getFechaCreacion());
    dto.setMontoTotal(oc.getMontoTotal());
    dto.setEstado(oc.getEstado());

    // Valores por defecto (por si no hay proforma o falla el llamado)
    dto.setNombreProveedor("Proveedor no asignado");
    dto.setRucProveedor("0000000");

    // OBTENER REQUERIMIENTO Y PROVEEDOR DESDE ms-proformas VIA FEIGN
    // (ya no se usa ProveedorClient: el proveedor vive dentro de ms-proformas
    // y viene incrustado/plano en la respuesta de la proforma)
    if (oc.getIdProforma() != null) {
        try {
            ProformaResponseDTO prof = proformaClient.obtenerPorId(oc.getIdProforma());
            dto.setCodigoRequerimiento(prof.getCodigoRequerimiento());

            if (prof.getNombreProveedor() != null) {
                dto.setNombreProveedor(prof.getNombreProveedor());
                dto.setRucProveedor(prof.getRucProveedor());
            }
        } catch (Exception e) {
            dto.setCodigoRequerimiento("No disponible");
            dto.setNombreProveedor("Error al cargar proveedor");
            dto.setRucProveedor("0000000");
        }
    }

    // 3. DETALLES DE LA ORDEN (sin cambios)
    if (oc.getDetalles() != null) {
        List<OrdenDetalleDTO> detallesDTO = oc.getDetalles().stream().map(detalle -> {
            OrdenDetalleDTO dDto = new OrdenDetalleDTO();
            dDto.setIdOrdenDetalle(detalle.getIdOrdenDetalle());
            if (detalle.getIdProducto() != null) {
                dDto.setProductoId(detalle.getIdProducto());
                dDto.setNombreProducto(detalle.getNombreProducto());
            }
            dDto.setCantidad(detalle.getCantidad());
            dDto.setPrecioUnitario(detalle.getPrecioUnitario());
            dDto.setSubtotal(detalle.getCantidad() * detalle.getPrecioUnitario());
            return dDto;
        }).toList();
        dto.setDetalles(detallesDTO);
    }

    return dto;
    }
    




    @Transactional
    public String procesarRecepcionConforme(Long idOrden) {
        // 1. El almacenero da el clic al constatar que las cajas llegaron completas.
        OrdenCompra orden = ordenCompraRepository.findById(idOrden).orElseThrow();
        
        // 2. Cambiamos el estado de la OC porque ya entró físicamente a las instalaciones
        orden.setEstado("PROCESADA_ALMACEN"); 
        ordenCompraRepository.save(orden);
        
        // 3. Preparamos el payload ligero (DTO) para enviarlo al ms-kardex
        List<KardexMovimientoRequestDTO> movimientos = orden.getDetalles().stream().map(detalle -> {
            KardexMovimientoRequestDTO dto = new KardexMovimientoRequestDTO();
            // Recuerda que en microservicios pasamos IDs planos (Long), no entidades mapeadas
            dto.setIdProducto(detalle.getIdProducto()); 
            dto.setCantidad(detalle.getCantidad());
            dto.setTipoMovimiento("ENTRADA");
            dto.setDocumentoReferencia(orden.getCodigo());
            dto.setObservaciones("Ingreso físico verificado por Almacén");
            return dto;
        }).collect(Collectors.toList());


        // 4. Rompemos el acoplamiento: Enviamos la lista vía HTTP síncrono al ms-kardex
        try {
            kardexClient.registrarMovimientosMasivos(movimientos);
        } catch (Exception e) {
            // Si el ms-kardex falla, la anotación @Transactional local hará un Rollback 
            // y la orden NO cambiará a "PROCESADA_ALMACEN" por seguridad de datos.
            throw new RuntimeException("No se pudo actualizar el stock en el inventario. Operación abortada.");
        }

        return "Bienes ingresados al almacén con éxito.";
    }


}
