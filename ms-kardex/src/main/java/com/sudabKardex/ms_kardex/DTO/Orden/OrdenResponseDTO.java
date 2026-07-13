package com.sudabKardex.ms_kardex.DTO.Orden;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class OrdenResponseDTO {
    private Long idOrden;
    private String codigo;
    private LocalDate fechaCreacion;
    private Double montoTotal;
    private String estado;
    private String nombreProveedor;
    private String rucProveedor;
    private String codigoRequerimiento; 
    
    // Enlaza con la lista interna de detalles usando la nomenclatura exacta
    private List<OrdenDetalleDTO> detalles;

}
