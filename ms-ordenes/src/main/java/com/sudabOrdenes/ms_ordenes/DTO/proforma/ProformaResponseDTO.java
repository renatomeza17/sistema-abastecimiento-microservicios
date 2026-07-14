package com.sudabOrdenes.ms_ordenes.DTO.proforma;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class ProformaResponseDTO {
    private Long idProforma;
    private String codigo;
    private LocalDate fechaRecepcion;
    private Double precioTotal;
    private String estado;

    // Datos del requerimiento al que responde
    private Long idRequerimiento;
    private String codigoRequerimiento;

     private Long idProveedor;
    private String nombreProveedor; // NUEVO
    private String rucProveedor;

    private List<DetalleProformaResponseDTO> productos;


}
