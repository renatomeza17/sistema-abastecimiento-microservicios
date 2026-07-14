package com.sudab.requerimientos.dto.request;


import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;

public record ProformaRequestDTO(
        @NotNull(message = "El usuario (proveedor) es obligatorio")
        Long idUsuario,

        @NotEmpty(message = "Debe incluir al menos un detalle")
        @Valid
        List<DetalleProformaRequestDTO> detalles
) {}
