package com.sudabKardex.ms_kardex.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class KardexResponseDTO {

    private Long idKardex;
    private Long idProducto;
    private Integer stockActual;
    private Integer stockMinimo;
    private String ubicacionAlmacen;
    private LocalDate fechaApertura;
    private String caracteristicas;

    private String codigoProducto;
    private String nombreProducto;
    private String unidadMedida;

}
