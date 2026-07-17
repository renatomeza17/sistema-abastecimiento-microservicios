package com.sudabKardex.ms_kardex.DTO.Producto;

import java.util.Objects;

public class ProductoResponseDTO {
    private Long idProducto;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String unidadMedida;
    private boolean activo;

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductoResponseDTO that = (ProductoResponseDTO) o;
        return activo == that.activo && Objects.equals(idProducto, that.idProducto) && Objects.equals(codigo, that.codigo) && Objects.equals(nombre, that.nombre) && Objects.equals(descripcion, that.descripcion) && Objects.equals(unidadMedida, that.unidadMedida);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto, codigo, nombre, descripcion, unidadMedida, activo);
    }

    @Override
    public String toString() {
        return "ProductoResponseDTO{idProducto=" + idProducto + ", codigo='" + codigo + "', nombre='" + nombre + "', descripcion='" + descripcion + "', unidadMedida='" + unidadMedida + "', activo=" + activo + "}";
    }
}
