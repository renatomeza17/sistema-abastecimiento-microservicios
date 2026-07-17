package com.sudabKardex.ms_kardex.Model;

import java.time.LocalDateTime;
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
@Table(name="kardex_movimiento")
public class KardexMovimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdMovimiento;

    @ManyToOne
    @JoinColumn(name = "id_kardex", nullable = false)
    private Kardex kardex;

    @Column(length = 30)
    private String tipoMovimiento;

    @Column
    private Integer cantidad;

    @Column
    private Integer saldoStock;

    @Column
    private LocalDateTime fechaMovimiento;

    @Column(length = 40)
    private String documentoReferencia;

    @Column(length = 100)
    private String observaciones;

    public Long getIdMovimiento() {
        return IdMovimiento;
    }

    public void setIdMovimiento(Long idMovimiento) {
        IdMovimiento = idMovimiento;
    }

    public Kardex getKardex() {
        return kardex;
    }

    public void setKardex(Kardex kardex) {
        this.kardex = kardex;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KardexMovimiento that = (KardexMovimiento) o;
        return Objects.equals(IdMovimiento, that.IdMovimiento) && Objects.equals(kardex, that.kardex) && Objects.equals(tipoMovimiento, that.tipoMovimiento) && Objects.equals(cantidad, that.cantidad) && Objects.equals(saldoStock, that.saldoStock) && Objects.equals(fechaMovimiento, that.fechaMovimiento) && Objects.equals(documentoReferencia, that.documentoReferencia) && Objects.equals(observaciones, that.observaciones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(IdMovimiento, kardex, tipoMovimiento, cantidad, saldoStock, fechaMovimiento, documentoReferencia, observaciones);
    }

    @Override
    public String toString() {
        return "KardexMovimiento{IdMovimiento=" + IdMovimiento + ", kardex=" + kardex + ", tipoMovimiento='" + tipoMovimiento + "', cantidad=" + cantidad + ", saldoStock=" + saldoStock + ", fechaMovimiento=" + fechaMovimiento + ", documentoReferencia='" + documentoReferencia + "', observaciones='" + observaciones + "'}";
    }
}
