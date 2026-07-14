package com.sudab.requerimientos.dto.request;

import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;

public record RequerimientoRequestDTO(
        @NotBlank(message = "La descripcion es obligatoria")
        String descripcion,

        @NotNull(message = "La dependencia es obligatoria")
        UUID idDependencia,

        @NotNull(message = "El usuario es obligatorio")
        Long idUsuario,

        @NotEmpty(message = "Debe incluir al menos un detalle")
        @Valid
        List<RequerimientoDetalleRequestDTO> detalles
) {}
