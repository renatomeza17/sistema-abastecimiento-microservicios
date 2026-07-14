package com.sudab.pedidodependencia.dto.request;

import jakarta.validation.constraints.NotBlank;

public record DependenciaRequestDTO(
        @NotBlank(message = "El nombre de la dependencia es obligatorio")
        String nombre,

        String descripcion
) {}
