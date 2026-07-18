package com.sudabKardex.ms_kardex.Model;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="kardex")
public class Kardex {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idKardex;

    @OneToOne
    @JoinColumn(name = "id_producto", unique = true, nullable = false)
    private Producto producto;

    @Column
    private Integer stockActual;

    @Column
    private Integer stockMinimo;

    @Column(length = 20)
    private String ubicacionAlmacen;

    @Column
    private LocalDate fechaApertura;

    @Column(length = 100)
    private String caracteristicas;

    public Long getIdKardex() {
        return idKardex;
    }

    public void setIdKardex(Long idKardex) {
        this.idKardex = idKardex;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getStockActual() {
        return stockActual;
    }

    public void setStockActual(Integer stockActual) {
        this.stockActual = stockActual;
    }

    public Integer getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Integer stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public String getUbicacionAlmacen() {
        return ubicacionAlmacen;
    }

    public void setUbicacionAlmacen(String ubicacionAlmacen) {
        this.ubicacionAlmacen = ubicacionAlmacen;
    }

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kardex kardex = (Kardex) o;
        return Objects.equals(idKardex, kardex.idKardex) && Objects.equals(producto, kardex.producto) && Objects.equals(stockActual, kardex.stockActual) && Objects.equals(stockMinimo, kardex.stockMinimo) && Objects.equals(ubicacionAlmacen, kardex.ubicacionAlmacen) && Objects.equals(fechaApertura, kardex.fechaApertura) && Objects.equals(caracteristicas, kardex.caracteristicas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idKardex, producto, stockActual, stockMinimo, ubicacionAlmacen, fechaApertura, caracteristicas);
    }

    @Override
    public String toString() {
        return "Kardex{idKardex=" + idKardex + ", producto=" + producto + ", stockActual=" + stockActual + ", stockMinimo=" + stockMinimo + ", ubicacionAlmacen='" + ubicacionAlmacen + "', fechaApertura=" + fechaApertura + ", caracteristicas='" + caracteristicas + "'}";
    }
}
