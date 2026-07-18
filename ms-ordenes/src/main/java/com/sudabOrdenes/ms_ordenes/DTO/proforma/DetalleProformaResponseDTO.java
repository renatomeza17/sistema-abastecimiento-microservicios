package com.sudabOrdenes.ms_ordenes.DTO.proforma;

import java.util.Objects;

public class DetalleProformaResponseDTO {
    private Long idProformaDetalle;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal; // cantidad * precioUnitario

    private Long idProducto;

    public DetalleProformaResponseDTO() {
    }

    public Long getIdProformaDetalle() {
        return idProformaDetalle;
    }

    public void setIdProformaDetalle(Long idProformaDetalle) {
        this.idProformaDetalle = idProformaDetalle;
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

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public String toString() {
        return "DetalleProformaResponseDTO{" +
                "idProformaDetalle=" + idProformaDetalle +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", subtotal=" + subtotal +
                ", idProducto=" + idProducto +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleProformaResponseDTO that = (DetalleProformaResponseDTO) o;
        return Objects.equals(idProformaDetalle, that.idProformaDetalle) &&
                Objects.equals(cantidad, that.cantidad) &&
                Objects.equals(precioUnitario, that.precioUnitario) &&
                Objects.equals(subtotal, that.subtotal) &&
                Objects.equals(idProducto, that.idProducto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProformaDetalle, cantidad, precioUnitario, subtotal, idProducto);
    }

}
