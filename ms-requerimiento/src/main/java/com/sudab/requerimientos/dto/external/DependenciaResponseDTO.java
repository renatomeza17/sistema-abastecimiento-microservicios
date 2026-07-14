package com.sudab.requerimientos.dto.external;

import java.util.UUID;

public record DependenciaResponseDTO(
        UUID id,
        String nombre,
        String descripcion
) {}
