package com.sudabKardex.ms_kardex.DTO.Orden;

import java.util.Objects;

public class OrdenDetalleDTO {
    private Long idOrdenDetalle;
    private Long productoId;
    private String nombreProducto;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;

    public Long getIdOrdenDetalle() {
        return idOrdenDetalle;
    }

    public void setIdOrdenDetalle(Long idOrdenDetalle) {
        this.idOrdenDetalle = idOrdenDetalle;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdenDetalleDTO that = (OrdenDetalleDTO) o;
        return Objects.equals(idOrdenDetalle, that.idOrdenDetalle) && Objects.equals(productoId, that.productoId) && Objects.equals(nombreProducto, that.nombreProducto) && Objects.equals(cantidad, that.cantidad) && Objects.equals(precioUnitario, that.precioUnitario) && Objects.equals(subtotal, that.subtotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrdenDetalle, productoId, nombreProducto, cantidad, precioUnitario, subtotal);
    }

    @Override
    public String toString() {
        return "OrdenDetalleDTO{idOrdenDetalle=" + idOrdenDetalle + ", productoId=" + productoId + ", nombreProducto='" + nombreProducto + "', cantidad=" + cantidad + ", precioUnitario=" + precioUnitario + ", subtotal=" + subtotal + "}";
    }
}
