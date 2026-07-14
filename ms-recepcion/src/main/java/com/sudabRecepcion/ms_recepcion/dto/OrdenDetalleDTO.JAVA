package com.sudabRecepcion.ms_recepcion.dto;

import lombok.Data;

@Data
public class OrdenDetalleDTO {
    private Long idOrdenDetalle;
    private Long productoId;
    private String nombreProducto;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;
}