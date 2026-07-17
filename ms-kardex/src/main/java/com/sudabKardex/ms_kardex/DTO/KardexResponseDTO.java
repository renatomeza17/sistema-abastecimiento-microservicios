package com.sudabKardex.ms_kardex.DTO;

import java.time.LocalDate;
import java.util.Objects;

public class KardexResponseDTO {

    private Long idKardex;
    private Long idProducto;
    private Integer stockActual;
    private Integer stockMinimo;
    private String ubicacionAlmacen;
    private LocalDate fechaApertura;
    private String caracteristicas;
    private String codigoProducto;
    private String nombreProducto;
    private String unidadMedida;

    public Long getIdKardex() {
        return idKardex;
    }

    public void setIdKardex(Long idKardex) {
        this.idKardex = idKardex;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
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

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KardexResponseDTO that = (KardexResponseDTO) o;
        return Objects.equals(idKardex, that.idKardex) && Objects.equals(idProducto, that.idProducto) && Objects.equals(stockActual, that.stockActual) && Objects.equals(stockMinimo, that.stockMinimo) && Objects.equals(ubicacionAlmacen, that.ubicacionAlmacen) && Objects.equals(fechaApertura, that.fechaApertura) && Objects.equals(caracteristicas, that.caracteristicas) && Objects.equals(codigoProducto, that.codigoProducto) && Objects.equals(nombreProducto, that.nombreProducto) && Objects.equals(unidadMedida, that.unidadMedida);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idKardex, idProducto, stockActual, stockMinimo, ubicacionAlmacen, fechaApertura, caracteristicas, codigoProducto, nombreProducto, unidadMedida);
    }

    @Override
    public String toString() {
        return "KardexResponseDTO{idKardex=" + idKardex + ", idProducto=" + idProducto + ", stockActual=" + stockActual + ", stockMinimo=" + stockMinimo + ", ubicacionAlmacen='" + ubicacionAlmacen + "', fechaApertura=" + fechaApertura + ", caracteristicas='" + caracteristicas + "', codigoProducto='" + codigoProducto + "', nombreProducto='" + nombreProducto + "', unidadMedida='" + unidadMedida + "'}";
    }
}
