package com.sudabOrdenes.ms_ordenes.DTO.proforma;

import lombok.Data;

@Data
public class DetalleProformaResponseDTO {
    private Long idProformaDetalle;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal; // cantidad * precioUnitario

    private Long idProducto;

}
