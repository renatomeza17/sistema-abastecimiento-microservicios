package com.sudabKardex.ms_kardex.DTO.Orden;

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
