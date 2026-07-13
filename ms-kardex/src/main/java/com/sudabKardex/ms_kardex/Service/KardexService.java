package com.sudabKardex.ms_kardex.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sudabKardex.ms_kardex.Client.OrdenClient;
import com.sudabKardex.ms_kardex.Client.ProductoClient;
import com.sudabKardex.ms_kardex.DTO.KardexMovimientoResponseDTO;
import com.sudabKardex.ms_kardex.DTO.KardexRequestDTO;
import com.sudabKardex.ms_kardex.DTO.KardexResponseDTO;
import com.sudabKardex.ms_kardex.DTO.Orden.OrdenResponseDTO;
import com.sudabKardex.ms_kardex.DTO.Producto.ProductoResponseDTO;
import com.sudabKardex.ms_kardex.Model.Kardex;
import com.sudabKardex.ms_kardex.Model.KardexMovimiento;
import com.sudabKardex.ms_kardex.Repository.KardexMovimientoRepository;
import com.sudabKardex.ms_kardex.Repository.KardexRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KardexService {

    private final KardexRepository kardexRepository;
    private final KardexMovimientoRepository movimientoRepository;
    // private final ProductoRepository productoRepository; 
    // private final OrdenCompraRepository ordenCompraRepository;

    private final ProductoClient productoClient;
    private final OrdenClient ordenClient;

    // --- LOGICA PARA HU11: CREAR NUEVO ASIENTO (PRODUCTO NUEVO) ---
    @Transactional
    public Kardex registrarNuevoAsiento(KardexRequestDTO dto) {
        // 1. Verificar si el producto existe en el catálogo maestro
        ProductoResponseDTO prod = productoClient.obtenerProductoPorId(dto.getIdProducto());
        if (prod == null) {
            throw new EntityNotFoundException("Producto no registrado en el catálogo maestro institucional.");
        }

        // 2. Validar que no tenga ya un Kárdex abierto
        if(kardexRepository.findByIdProducto(dto.getIdProducto()).isPresent()){
            throw new IllegalStateException("El producto ya cuenta con una ficha de Kárdex activa.");
        }

        // 3. Crear cabecera de Kárdex
        Kardex nuevoKardex = new Kardex();
        nuevoKardex.setIdProducto(dto.getIdProducto());
        nuevoKardex.setStockActual(0); // Nace en cero hasta que entre una OC
        nuevoKardex.setStockMinimo(dto.getStockMinimo());
        nuevoKardex.setUbicacionAlmacen(dto.getUbicacionAlmacen());
        nuevoKardex.setFechaApertura(LocalDate.now());
        nuevoKardex.setCaracteristicas(dto.getCaracteristicas());

        return kardexRepository.save(nuevoKardex);
    }





    // --- LOGICA PARA HU10: ACTUALIZACIÓN AUTOMÁTICA (ENTRADAS / SALIDAS) ---
    @Transactional
    public void registrarMovimiento(Long idProducto, Integer cantidad, String tipo, String docReferencia, String obs) {
        // 1. Buscar el Kárdex asociado al producto
        Kardex kardex = kardexRepository.findByIdProducto(idProducto)
            .orElseThrow(() -> new EntityNotFoundException("No existe un asiento de Kárdex para este producto. Debe crearlo primero (HU11)."));

        // 2. Calcular el nuevo stock real según la HU10
        int nuevoStock = kardex.getStockActual();
        if ("ENTRADA".equalsIgnoreCase(tipo)) {
            nuevoStock += cantidad;
        } else if ("SALIDA".equalsIgnoreCase(tipo)) {
            if (kardex.getStockActual() < cantidad) {
                throw new IllegalArgumentException("Stock insuficiente en almacén para realizar la salida.");
            }
            nuevoStock -= cantidad;
        }

        // 3. Actualizar cabecera
        kardex.setStockActual(nuevoStock);
        kardexRepository.save(kardex);

        // 4. Guardar en el histórico de movimientos (Auditoría)
        KardexMovimiento movimiento = new KardexMovimiento();
        movimiento.setKardex(kardex);
        movimiento.setTipoMovimiento(tipo.toUpperCase());
        movimiento.setCantidad(cantidad);
        movimiento.setSaldoStock(nuevoStock);
        movimiento.setFechaMovimiento(LocalDateTime.now());
        movimiento.setDocumentoReferencia(docReferencia);
        movimiento.setObservaciones(obs);

        movimientoRepository.save(movimiento);
    }


    @Transactional //(readOnly = true)
    public List<KardexResponseDTO> listarTodoElKardex() {
        return kardexRepository.findAll().stream().map(k -> {
            KardexResponseDTO dto = new KardexResponseDTO();
            dto.setIdKardex(k.getIdKardex());
            dto.setIdProducto(k.getIdProducto());
            // dto.setCodigoProducto(k.getIdCodigo()); // O el campo de código de tu entidad
            // dto.setNombreProducto(k.getProducto().getNombre());
            // dto.setUnidadMedida(k.getProducto().getUnidadMedida());
            dto.setStockActual(k.getStockActual());
            dto.setStockMinimo(k.getStockMinimo());
            dto.setUbicacionAlmacen(k.getUbicacionAlmacen());
            dto.setFechaApertura(k.getFechaApertura());
            dto.setCaracteristicas(k.getCaracteristicas());
            return dto;
        }).collect(Collectors.toList());
    }


    @Transactional //(readOnly = true)
    public List<KardexMovimientoResponseDTO> obtenerMovimientosPorKardex(Long idKardex) {
        return movimientoRepository.findByKardexIdKardexOrderByFechaMovimientoDesc(idKardex).stream().map(m -> {
            KardexMovimientoResponseDTO dto = new KardexMovimientoResponseDTO();
            dto.setIdMovimiento(m.getIdMovimiento());
            dto.setIdKardex(m.getKardex().getIdKardex());
            dto.setTipoMovimiento(m.getTipoMovimiento().toString());
            dto.setCantidad(m.getCantidad());
            dto.setSaldoStock(m.getSaldoStock());
            dto.setFechaMovimiento(m.getFechaMovimiento());
            dto.setDocumentoReferencia(m.getDocumentoReferencia());
            dto.setObservaciones(m.getObservaciones());
            return dto;
        }).collect(Collectors.toList());
    }


    public List<ProductoResponseDTO> obtenerProductosDisponibles() {
        return productoClient.obtenerProductosSinKardex();
    }


    public List<ProductoResponseDTO> verificarProductosFaltantesDeOrden(Long idOrden) {
        // 1. Buscamos de forma segura la Orden de Compra asociada al Pedido Pendiente
        OrdenResponseDTO orden = ordenClient.obtenerOrdenPorId(idOrden);
        if (orden == null) {
            throw new EntityNotFoundException("Orden de compra no registrada con el ID: " + idOrden);
        }

        // 2. Extraemos todos los productos mapeados en los detalles de esa Orden
        return orden.getDetalles().stream()
                .filter(detalle -> !kardexRepository.existsByIdProducto(detalle.getProductoId()))
                .map(detalle -> productoClient.obtenerProductoPorId(detalle.getProductoId()))
                .distinct() // Evitamos duplicados si el producto se repitió en las líneas de la orden
                .collect(Collectors.toList());
    }

    


    // public List<Producto> obtenerProductos() {
    //     return productoRepository.findAll();
    // }



    






}

