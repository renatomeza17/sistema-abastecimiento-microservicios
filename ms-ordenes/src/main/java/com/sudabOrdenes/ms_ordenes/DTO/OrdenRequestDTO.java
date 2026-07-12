package com.sudabOrdenes.ms_ordenes.DTO;

import lombok.Data;

@Data
public class OrdenRequestDTO {
    private Long idProforma;

    // private Long idProveedor;

    //DATOS QUE DIGITA EL USUARIO
    private String fechaEntrega;
    private String lugarEntrega;
    private String observaciones; 
    private String formaPago;
    private String plazoEntrega;
    private String garantia;

    // Lista de detalles que vienen desde Angular
    // private List<OrdenDetalleRequestDTO> detalles;
}
