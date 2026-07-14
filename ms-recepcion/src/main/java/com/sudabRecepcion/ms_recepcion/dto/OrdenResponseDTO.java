package com.sudabRecepcion.ms_recepcion.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdenResponseDTO {

    private Long idOrden;
    private String codigo;
    private LocalDate fechaCreacion;
    private Double montoTotal;
    private String estado;
    private String nombreProveedor;
    private String rucProveedor;
    private String codigoRequerimiento;
    // No incluyo "detalles" porque ms-recepcion solo necesita
    // confirmar que la orden existe, no la lista de productos.
    // Jackson ignora los campos del JSON que no estén aquí, así que no da error.
}