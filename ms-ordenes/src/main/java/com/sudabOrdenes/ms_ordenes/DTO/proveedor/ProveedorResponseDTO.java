package com.sudabOrdenes.ms_ordenes.DTO.proveedor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorResponseDTO {
     private Long idProveedor;
    private String ruc;
    private String razonSocial;
    private String direccion;
    private String telefono;
    private String email;
    private String contacto;

}
