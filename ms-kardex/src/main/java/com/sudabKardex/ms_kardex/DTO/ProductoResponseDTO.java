package com.sudabKardex.ms_kardex.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoResponseDTO {
    private Long idProducto;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String unidadMedida;
    private boolean activo;
}
