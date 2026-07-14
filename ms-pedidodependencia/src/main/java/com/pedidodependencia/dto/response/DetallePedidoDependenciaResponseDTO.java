package com.sudab.pedidodependencia.dto.response;

import java.util.UUID;

public record DetallePedidoDependenciaResponseDTO(
        UUID id,
        Integer cantidad,
        String unidadMedida,
        String observacionEspecifica,
        Long idProducto
) {}
