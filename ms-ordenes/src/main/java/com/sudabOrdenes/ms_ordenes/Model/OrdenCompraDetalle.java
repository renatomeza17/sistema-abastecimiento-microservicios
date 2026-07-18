package com.sudabOrdenes.ms_ordenes.Model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity
@Table(name = "orden_compra_detalles")
public class OrdenCompraDetalle {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrdenDetalle;

    @ManyToOne
    @JoinColumn(name = "orden_compra_id")
    private OrdenCompra ordenCompra;

    // @ManyToOne
    // @JoinColumn(name = "producto_id")
    // private Producto producto;

    // Relación externa (SE CAMBIA A ID, porque el Producto vive en ms-kardex)
    @Column(name = "id_producto")
    private Long idProducto;


    // Campo de apoyo opcional para no tener que consultar a Kardex a cada rato
    private String nombreProducto;

    
    @Column(name="cantidad",nullable = false)
    private Integer cantidad;

    @Column(name="precio_unitario",nullable = false)
    private Double precioUnitario;

    public OrdenCompraDetalle() {
    }

    public OrdenCompraDetalle(Long idOrdenDetalle, OrdenCompra ordenCompra, Long idProducto, String nombreProducto, Integer cantidad, Double precioUnitario) {
        this.idOrdenDetalle = idOrdenDetalle;
        this.ordenCompra = ordenCompra;
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public Long getIdOrdenDetalle() {
        return idOrdenDetalle;
    }

    public void setIdOrdenDetalle(Long idOrdenDetalle) {
        this.idOrdenDetalle = idOrdenDetalle;
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
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

    // Método útil para lógica de negocio o reportes
    public Double getSubtotal() {
        return this.cantidad * this.precioUnitario;
    }

    @Override
    public String toString() {
        return "OrdenCompraDetalle{" +
                "idOrdenDetalle=" + idOrdenDetalle +
                ", ordenCompra=" + ordenCompra +
                ", idProducto=" + idProducto +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdenCompraDetalle that = (OrdenCompraDetalle) o;
        return Objects.equals(idOrdenDetalle, that.idOrdenDetalle) &&
                Objects.equals(ordenCompra, that.ordenCompra) &&
                Objects.equals(idProducto, that.idProducto) &&
                Objects.equals(nombreProducto, that.nombreProducto) &&
                Objects.equals(cantidad, that.cantidad) &&
                Objects.equals(precioUnitario, that.precioUnitario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrdenDetalle, ordenCompra, idProducto, nombreProducto, cantidad, precioUnitario);
    }

}
