package com.sudabKardex.ms_kardex.DTO.Producto;

import lombok.Data;

@Data
public class ProductoResponseDTO {
    private Long idProducto;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String unidadMedida;
    private boolean activo;

}
