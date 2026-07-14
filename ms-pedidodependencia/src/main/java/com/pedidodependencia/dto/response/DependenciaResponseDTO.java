package com.sudab.pedidodependencia.dto.response;

import java.util.UUID;

public record DependenciaResponseDTO(
        UUID id,
        String nombre,
        String descripcion
) {}
