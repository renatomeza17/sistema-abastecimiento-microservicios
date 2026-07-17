package com.sudabOrdenes.ms_ordenes.DTO.kardex;

import java.time.LocalDateTime;
import java.util.Objects;

public class KardexMovimientoResponseDTO {

    private Long idMovimiento;
    private Long idKardex;
    private String tipoMovimiento; // ENTRADA o SALIDA
    private Integer cantidad;
    private Integer saldoStock;
    private LocalDateTime fechaMovimiento;
    private String documentoReferencia;
    private String observaciones;

    public KardexMovimientoResponseDTO() {
    }

    public Long getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Long idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Long getIdKardex() {
        return idKardex;
    }

    public void setIdKardex(Long idKardex) {
        this.idKardex = idKardex;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getSaldoStock() {
        return saldoStock;
    }

    public void setSaldoStock(Integer saldoStock) {
        this.saldoStock = saldoStock;
    }

    public LocalDateTime getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(LocalDateTime fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
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
    public String toString() {
        return "KardexMovimientoResponseDTO{" +
                "idMovimiento=" + idMovimiento +
                ", idKardex=" + idKardex +
                ", tipoMovimiento='" + tipoMovimiento + '\'' +
                ", cantidad=" + cantidad +
                ", saldoStock=" + saldoStock +
                ", fechaMovimiento=" + fechaMovimiento +
                ", documentoReferencia='" + documentoReferencia + '\'' +
                ", observaciones='" + observaciones + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KardexMovimientoResponseDTO that = (KardexMovimientoResponseDTO) o;
        return Objects.equals(idMovimiento, that.idMovimiento) &&
                Objects.equals(idKardex, that.idKardex) &&
                Objects.equals(tipoMovimiento, that.tipoMovimiento) &&
                Objects.equals(cantidad, that.cantidad) &&
                Objects.equals(saldoStock, that.saldoStock) &&
                Objects.equals(fechaMovimiento, that.fechaMovimiento) &&
                Objects.equals(documentoReferencia, that.documentoReferencia) &&
                Objects.equals(observaciones, that.observaciones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMovimiento, idKardex, tipoMovimiento, cantidad, saldoStock, fechaMovimiento, documentoReferencia, observaciones);
    }

}
