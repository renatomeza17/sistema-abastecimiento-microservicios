package com.sudabOrdenes.ms_ordenes.DTO.kardex;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class KardexMovimientoResponseDTO {

    private Long idMovimiento;
    private Long idKardex;
    private String tipoMovimiento; // ENTRADA o SALIDA
    private Integer cantidad;
    private Integer saldoStock;
    private LocalDateTime fechaMovimiento;
    private String documentoReferencia;
    private String observaciones;

}
