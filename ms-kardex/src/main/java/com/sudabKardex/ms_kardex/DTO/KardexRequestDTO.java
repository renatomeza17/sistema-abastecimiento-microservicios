package com.sudabKardex.ms_kardex.DTO;

import java.util.Objects;

public class KardexRequestDTO {

    private Long idProducto; 
    private Integer stockMinimo;
    private String ubicacionAlmacen;
    private String caracteristicas;

    public KardexRequestDTO() {
    }

    public KardexRequestDTO(Long idProducto, Integer stockMinimo, String ubicacionAlmacen, String caracteristicas) {
        this.idProducto = idProducto;
        this.stockMinimo = stockMinimo;
        this.ubicacionAlmacen = ubicacionAlmacen;
        this.caracteristicas = caracteristicas;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
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
        KardexRequestDTO that = (KardexRequestDTO) o;
        return Objects.equals(idProducto, that.idProducto) && Objects.equals(stockMinimo, that.stockMinimo) && Objects.equals(ubicacionAlmacen, that.ubicacionAlmacen) && Objects.equals(caracteristicas, that.caracteristicas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto, stockMinimo, ubicacionAlmacen, caracteristicas);
    }

    @Override
    public String toString() {
        return "KardexRequestDTO{idProducto=" + idProducto + ", stockMinimo=" + stockMinimo + ", ubicacionAlmacen='" + ubicacionAlmacen + "', caracteristicas='" + caracteristicas + "'}";
    }
}
