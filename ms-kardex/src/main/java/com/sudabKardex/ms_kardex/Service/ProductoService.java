package com.sudabKardex.ms_kardex.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
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
        
        // Usamos tu DTO existente mapeando todos sus atributos reales
        return productos.stream()
                .map(prod -> ProductoResponseDTO.builder()
                        .idProducto(prod.getIdProducto())
                        .codigo(prod.getCodigo())
                        .nombre(prod.getNombre())
                        .descripcion(prod.getDescripcion())
                        .unidadMedida(prod.getUnidadMedida())
                        .activo(prod.getActivo()) // Mapea el boolean nativo de tu modelo
                        .build())
                .collect(Collectors.toList());
    }
}