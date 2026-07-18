package com.sudabKardex.ms_kardex.DTO;

import java.util.Objects;

public class KardexMovimientoRequestDTO {

    private Long idProducto;
    private Integer cantidad;
    private String tipoMovimiento;
    private String documentoReferencia; 
    private String observaciones;

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getDocumentoReferencia() {
        return documentoReferencia;
    }

    public void setDocumentoReferencia(String documentoReferencia) {
        this.documentoReferencia = documentoReferencia;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KardexMovimientoRequestDTO that = (KardexMovimientoRequestDTO) o;
        return Objects.equals(idProducto, that.idProducto) && Objects.equals(cantidad, that.cantidad) && Objects.equals(tipoMovimiento, that.tipoMovimiento) && Objects.equals(documentoReferencia, that.documentoReferencia) && Objects.equals(observaciones, that.observaciones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto, cantidad, tipoMovimiento, documentoReferencia, observaciones);
    }

    @Override
    public String toString() {
        return "KardexMovimientoRequestDTO{idProducto=" + idProducto + ", cantidad=" + cantidad + ", tipoMovimiento='" + tipoMovimiento + "', documentoReferencia='" + documentoReferencia + "', observaciones='" + observaciones + "'}";
    }
}
