package com.sudab.requerimientos.dto.request;


import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record DetalleProformaRequestDTO(
        @NotNull(message = "La cantidad es obligatoria")
        @Min(value = 1, message = "La cantidad debe ser mayor a 0")
        Integer cantidad,

        String observacionEspecifica,

        @NotNull(message = "El precio unitario es obligatorio")
        @DecimalMin(value = "0.0", inclusive = false, message = "El precio unitario debe ser mayor a 0")
        BigDecimal precioUnitario,

        @NotNull(message = "El id del producto es obligatorio")
        Long idProducto
) {}
