package com.sudab.requerimientos.dto.request;


import jakarta.validation.constraints.*;

public record RequerimientoDetalleRequestDTO(
        @NotNull(message = "La cantidad es obligatoria")
        @Min(value = 1, message = "La cantidad debe ser mayor a 0")
        Integer cantidad,

        @NotBlank(message = "La unidad de medida es obligatoria")
        String unidadMedida,

        String observacionEspecifica,

        @NotNull(message = "El id del producto es obligatorio")
        Long idProducto
) {}
