package com.sudabOrdenes.ms_ordenes.DTO.kardex;

import lombok.Data;

@Data
public class KardexMovimientoRequestDTO {
    private Long idProducto;
    private Integer cantidad;
    private String tipoMovimiento; // ENTRADA o SALIDA
    private String documentoReferencia; // Ej: "OC-2026-004" o "PECOSA-023"
    private String observaciones;

}
