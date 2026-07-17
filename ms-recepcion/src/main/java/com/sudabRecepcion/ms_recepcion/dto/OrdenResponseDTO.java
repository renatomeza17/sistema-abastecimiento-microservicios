package com.sudabRecepcion.ms_recepcion.dto;

import java.time.LocalDate;
import java.util.Objects;

public class OrdenResponseDTO {

    private Long idOrden;
    private String codigo;
    private LocalDate fechaCreacion;
    private Double montoTotal;
    private String estado;
    private String nombreProveedor;
    private String rucProveedor;
    private String codigoRequerimiento;

    public OrdenResponseDTO() {
    }

    public OrdenResponseDTO(Long idOrden, String codigo, LocalDate fechaCreacion, Double montoTotal, String estado, String nombreProveedor, String rucProveedor, String codigoRequerimiento) {
        this.idOrden = idOrden;
        this.codigo = codigo;
        this.fechaCreacion = fechaCreacion;
        this.montoTotal = montoTotal;
        this.estado = estado;
        this.nombreProveedor = nombreProveedor;
        this.rucProveedor = rucProveedor;
        this.codigoRequerimiento = codigoRequerimiento;
    }

    public Long getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Long idOrden) {
        this.idOrden = idOrden;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getRucProveedor() {
        return rucProveedor;
    }

    public void setRucProveedor(String rucProveedor) {
        this.rucProveedor = rucProveedor;
    }

    public String getCodigoRequerimiento() {
        return codigoRequerimiento;
    }

    public void setCodigoRequerimiento(String codigoRequerimiento) {
        this.codigoRequerimiento = codigoRequerimiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdenResponseDTO that = (OrdenResponseDTO) o;
        return Objects.equals(idOrden, that.idOrden) && Objects.equals(codigo, that.codigo) && Objects.equals(fechaCreacion, that.fechaCreacion) && Objects.equals(montoTotal, that.montoTotal) && Objects.equals(estado, that.estado) && Objects.equals(nombreProveedor, that.nombreProveedor) && Objects.equals(rucProveedor, that.rucProveedor) && Objects.equals(codigoRequerimiento, that.codigoRequerimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrden, codigo, fechaCreacion, montoTotal, estado, nombreProveedor, rucProveedor, codigoRequerimiento);
    }

    @Override
    public String toString() {
        return "OrdenResponseDTO{idOrden=" + idOrden + ", codigo='" + codigo + "', fechaCreacion=" + fechaCreacion + ", montoTotal=" + montoTotal + ", estado='" + estado + "', nombreProveedor='" + nombreProveedor + "', rucProveedor='" + rucProveedor + "', codigoRequerimiento='" + codigoRequerimiento + "'}";
    }
}
