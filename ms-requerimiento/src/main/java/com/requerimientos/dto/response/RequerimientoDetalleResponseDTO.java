package com.sudab.requerimientos.dto.response;

import java.util.UUID;

public record RequerimientoDetalleResponseDTO(
        UUID id,
        Integer cantidad,
        String unidadMedida,
        String observacionEspecifica,
        Long idProducto
) {}
