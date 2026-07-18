package com.sudabOrdenes.ms_ordenes.DTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class OrdenResponseDTO {
    private Long idOrden;
    private String codigo;
    private LocalDate fechaCreacion;
    private Double montoTotal;
    private String estado;
    
    // Datos aplanados del proveedor
    private String nombreProveedor;
    private String rucProveedor;
    
    // El "salto" de trazabilidad que va desde la OC -> Proforma -> Requerimiento
    private String codigoRequerimiento; 
    
    // La lista de sus productos usando su propio DTO detalle
    private List<OrdenDetalleDTO> detalles;

    public OrdenResponseDTO() {
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

    public List<OrdenDetalleDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<OrdenDetalleDTO> detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "OrdenResponseDTO{" +
                "idOrden=" + idOrden +
                ", codigo='" + codigo + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", montoTotal=" + montoTotal +
                ", estado='" + estado + '\'' +
                ", nombreProveedor='" + nombreProveedor + '\'' +
                ", rucProveedor='" + rucProveedor + '\'' +
                ", codigoRequerimiento='" + codigoRequerimiento + '\'' +
                ", detalles=" + detalles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdenResponseDTO that = (OrdenResponseDTO) o;
        return Objects.equals(idOrden, that.idOrden) &&
                Objects.equals(codigo, that.codigo) &&
                Objects.equals(fechaCreacion, that.fechaCreacion) &&
                Objects.equals(montoTotal, that.montoTotal) &&
                Objects.equals(estado, that.estado) &&
                Objects.equals(nombreProveedor, that.nombreProveedor) &&
                Objects.equals(rucProveedor, that.rucProveedor) &&
                Objects.equals(codigoRequerimiento, that.codigoRequerimiento) &&
                Objects.equals(detalles, that.detalles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrden, codigo, fechaCreacion, montoTotal, estado, nombreProveedor, rucProveedor, codigoRequerimiento, detalles);
    }

}
