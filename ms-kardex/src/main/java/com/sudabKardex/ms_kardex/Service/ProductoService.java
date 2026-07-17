package com.sudabKardex.ms_kardex.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sudabKardex.ms_kardex.DTO.Producto.ProductoResponseDTO;
import com.sudabKardex.ms_kardex.Model.Producto;
import com.sudabKardex.ms_kardex.Repository.ProductoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> listarProductosActivos() {
        List<Producto> productos = productoRepository.findByActivoTrue();
        return productos.stream().map(p -> {
            ProductoResponseDTO dto = new ProductoResponseDTO();
            dto.setIdProducto(p.getIdProducto());
            dto.setCodigo(p.getCodigo());
            dto.setNombre(p.getNombre());
            dto.setUnidadMedida(p.getUnidadMedida());
            return dto;
        }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductoResponseDTO obtenerPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con id: " + id));
        ProductoResponseDTO dto = new ProductoResponseDTO();
        dto.setIdProducto(producto.getIdProducto());
        dto.setCodigo(producto.getCodigo());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setUnidadMedida(producto.getUnidadMedida());
        dto.setActivo(producto.getActivo());
        return dto;
    }
}
