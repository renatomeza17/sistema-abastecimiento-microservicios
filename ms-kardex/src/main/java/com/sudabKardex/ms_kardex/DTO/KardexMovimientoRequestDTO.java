package com.sudabKardex.ms_kardex.DTO;

import lombok.Data;

@Data
public class KardexMovimientoRequestDTO {

    private Long idProducto; // Buscaremos el Kardex local usando este ID de catálogo externo
    private Integer cantidad;
    private String tipoMovimiento; // ENTRADA o SALIDA
    private String documentoReferencia; 
    private String observaciones;
    

}
