package com.sudab.requerimientos.dto.external;

public record ProductoResponseDTO(
        Long idProducto,
        String codigo,
        String nombre,
        String descripcion,
        String unidadMedida,
        Boolean activo
) {}
