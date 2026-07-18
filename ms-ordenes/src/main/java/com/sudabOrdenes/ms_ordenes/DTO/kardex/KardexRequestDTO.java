package com.sudabOrdenes.ms_ordenes.DTO.kardex;

import java.util.Objects;

public class KardexRequestDTO {

    
    // El ID del producto seleccionado del catálogo maestro que aún no tiene Kárdex
    private Long idProducto; 
    
    // Parámetro clave exigido por la HU11 (Definición de stock mínimo)
    private Integer stockMinimo;
    
    // Ubicación física en el almacén de la UNI (Ej: "Estante C-A2", "Piso 1 - Zona Líquidos")
    private String ubicacionAlmacen;
    
    // Características iniciales del bien o ficha técnica inicial (HU11)
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
    public String toString() {
        return "KardexRequestDTO{" +
                "idProducto=" + idProducto +
                ", stockMinimo=" + stockMinimo +
                ", ubicacionAlmacen='" + ubicacionAlmacen + '\'' +
                ", caracteristicas='" + caracteristicas + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KardexRequestDTO that = (KardexRequestDTO) o;
        return Objects.equals(idProducto, that.idProducto) &&
                Objects.equals(stockMinimo, that.stockMinimo) &&
                Objects.equals(ubicacionAlmacen, that.ubicacionAlmacen) &&
                Objects.equals(caracteristicas, that.caracteristicas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto, stockMinimo, ubicacionAlmacen, caracteristicas);
    }

}
