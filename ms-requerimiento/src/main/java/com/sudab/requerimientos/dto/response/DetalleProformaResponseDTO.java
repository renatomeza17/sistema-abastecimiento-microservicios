package com.sudab.requerimientos.dto.response;

import java.util.UUID;

import java.math.BigDecimal;

public record DetalleProformaResponseDTO(
        UUID id,
        Integer cantidad,
        String observacionEspecifica,
        BigDecimal precioUnitario,
        Long idProducto
) {}
