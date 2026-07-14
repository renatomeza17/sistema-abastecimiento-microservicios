package com.sudabKardex.ms_kardex.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sudabKardex.ms_kardex.Client.OrdenClient;

import com.sudabKardex.ms_kardex.DTO.KardexMovimientoResponseDTO;
import com.sudabKardex.ms_kardex.DTO.KardexRequestDTO;
import com.sudabKardex.ms_kardex.DTO.KardexResponseDTO;
import com.sudabKardex.ms_kardex.DTO.Orden.OrdenResponseDTO;
import com.sudabKardex.ms_kardex.DTO.Producto.ProductoResponseDTO;
import com.sudabKardex.ms_kardex.Model.Kardex;
import com.sudabKardex.ms_kardex.Model.KardexMovimiento;
import com.sudabKardex.ms_kardex.Model.Producto;
import com.sudabKardex.ms_kardex.Repository.KardexMovimientoRepository;
import com.sudabKardex.ms_kardex.Repository.KardexRepository;
import com.sudabKardex.ms_kardex.Repository.ProductoRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KardexService {

    private final KardexRepository kardexRepository;
    private final KardexMovimientoRepository movimientoRepository;
    private final ProductoRepository productoRepository;
    // private final ProductoRepository productoRepository; 
    // private final OrdenCompraRepository ordenCompraRepository;

    // private final ProductoClient productoClient;
    private final OrdenClient ordenClient;

    // --- LOGICA PARA HU11: CREAR NUEVO ASIENTO (PRODUCTO NUEVO) ---
    @Transactional
    public Kardex registrarNuevoAsiento(KardexRequestDTO dto) {
        Producto prod = productoRepository.findById(dto.getIdProducto())
                    .orElseThrow(() -> new EntityNotFoundException("Producto no registrado en el catálogo maestro institucional."));

            // Validar que no tenga ya un Kárdex abierto
            if (kardexRepository.findByIdProducto(dto.getIdProducto()).isPresent()) {
                throw new IllegalStateException("El producto ya cuenta con una ficha de Kárdex activa.");
            }

            // Crear cabecera de Kárdex
            Kardex nuevoKardex = new Kardex();
            nuevoKardex.setProducto(prod);
            nuevoKardex.setStockActual(0); // Nace en cero hasta que entre una OC conforme
            nuevoKardex.setStockMinimo(dto.getStockMinimo());
            nuevoKardex.setUbicacionAlmacen(dto.getUbicacionAlmacen());
            nuevoKardex.setFechaApertura(LocalDate.now());
            nuevoKardex.setCaracteristicas(dto.getCaracteristicas());

            return kardexRepository.save(nuevoKardex);
    }





    // --- LOGICA PARA HU10: ACTUALIZACIÓN AUTOMÁTICA (ENTRADAS / SALIDAS) ---
    @Transactional
    public void registrarMovimiento(Long idProducto, Integer cantidad, String tipo, String docReferencia, String obs) {
        Kardex kardex = kardexRepository.findByIdProducto(idProducto)
            .orElseThrow(() -> new EntityNotFoundException("No existe un asiento de Kárdex para este producto. Debe crearlo primero (HU11)."));

        int nuevoStock = kardex.getStockActual();
        if ("ENTRADA".equalsIgnoreCase(tipo)) {
            nuevoStock += cantidad;
        } else if ("SALIDA".equalsIgnoreCase(tipo)) {
            if (kardex.getStockActual() < cantidad) {
                throw new IllegalArgumentException("Stock insuficiente en almacén para realizar la salida.");
            }
            nuevoStock -= cantidad;
        }

        kardex.setStockActual(nuevoStock);
        kardexRepository.save(kardex);

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
            dto.setIdProducto(k.getProducto().getIdProducto());
            
            // 3. SOLUCIÓN AL CONVERTIR A DTO: Jalar datos descriptivos del Producto local
            productoRepository.findById(k.getProducto().getIdProducto()).ifPresent(p -> {
                dto.setCodigoProducto(p.getCodigo()); 
                dto.setNombreProducto(p.getNombre());
                dto.setUnidadMedida(p.getUnidadMedida());
            });

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
        List<Long> idsConKardex = kardexRepository.findAll().stream()
                .map(kardex -> kardex.getProducto().getIdProducto())
                .toList();
        
        List<Producto> productosLocales;
        if (idsConKardex.isEmpty()) {
            productosLocales = productoRepository.findAll();
        } else {
            productosLocales = productoRepository.findByIdProductoNotIn(idsConKardex);
        }

        // Transformación limpia usando la referencia al método privado de abajo
        return productosLocales.stream()
                .map(this::convertirAProductoDTO)
                .collect(Collectors.toList());
    }


    public List<ProductoResponseDTO> verificarProductosFaltantesDeOrden(Long idOrden) {
        // Buscamos de forma segura la Orden de Compra remota vía OpenFeign
        OrdenResponseDTO orden = ordenClient.obtenerOrdenPorId(idOrden);
        if (orden == null) {
            throw new EntityNotFoundException("Orden de compra no registrada con el ID: " + idOrden);
        }

        // Filtramos los productos de la orden que no existen en tu Kárdex local
        List<Producto> productosFaltantes = orden.getDetalles().stream()
                .filter(detalle -> !kardexRepository.existsByIdProducto(detalle.getProductoId()))
                .map(detalle -> productoRepository.findById(detalle.getProductoId()).orElse(null))
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        // Reutilizamos el mismo método helper de mapeo
        return productosFaltantes.stream()
                .map(this::convertirAProductoDTO)
                .collect(Collectors.toList());
    }


    private ProductoResponseDTO convertirAProductoDTO(Producto p) {
        ProductoResponseDTO dto = new ProductoResponseDTO();
        dto.setIdProducto(p.getIdProducto());
        dto.setCodigo(p.getCodigo());
        dto.setNombre(p.getNombre());
        dto.setUnidadMedida(p.getUnidadMedida());
        return dto;
    }


    // public List<Producto> obtenerProductos() {
    //     return productoRepository.findAll();
    // }



    






}

