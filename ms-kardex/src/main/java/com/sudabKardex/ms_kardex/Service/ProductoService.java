package com.sudabKardex.ms_kardex.Service;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sudabKardex.ms_kardex.DTO.ProductoResponseDTO;
import com.sudabKardex.ms_kardex.Model.Producto;
import com.sudabKardex.ms_kardex.Repository.ProductoRepository;


import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> listarProductosActivos() {
        List<Producto> productos = productoRepository.findByActivoTrue();
    
        // Mapeas en caliente a ProductoResponseDTO usando el stream local
        return productos.stream().map(p -> {
            ProductoResponseDTO dto = new ProductoResponseDTO();
            dto.setIdProducto(p.getIdProducto());
            dto.setCodigo(p.getCodigo());
            dto.setNombre(p.getNombre());
            dto.setUnidadMedida(p.getUnidadMedida());
            return dto;
        }).collect(Collectors.toList());
    }
}