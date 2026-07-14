package com.sudab.requerimientos.dto.request;

import jakarta.validation.constraints.*;

public record ProveedorRequestDTO(
        @NotBlank(message = "El RUC es obligatorio")
        @Pattern(regexp = "\\d{11}", message = "El RUC debe tener 11 digitos")
        String ruc,

        @NotBlank(message = "La razon social es obligatoria")
        String razonSocial,

        String direccion,

        @NotNull(message = "El id del usuario es obligatorio")
        Long idUsuario
) {}
