package com.sudabOrdenes.ms_ordenes.DTO.proforma;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class ProformaResponseDTO {
    private Long idProforma;
    private String codigo;
    private LocalDate fechaRecepcion;
    private Double precioTotal;
    private String estado;

    // Datos del requerimiento al que responde
    private Long idRequerimiento;
    private String codigoRequerimiento;

     private Long idProveedor;
    private String nombreProveedor; // NUEVO
    private String rucProveedor;

    private List<DetalleProformaResponseDTO> productos;

    public ProformaResponseDTO() {
    }

    public Long getIdProforma() {
        return idProforma;
    }

    public void setIdProforma(Long idProforma) {
        this.idProforma = idProforma;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(LocalDate fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getIdRequerimiento() {
        return idRequerimiento;
    }

    public void setIdRequerimiento(Long idRequerimiento) {
        this.idRequerimiento = idRequerimiento;
    }

    public String getCodigoRequerimiento() {
        return codigoRequerimiento;
    }

    public void setCodigoRequerimiento(String codigoRequerimiento) {
        this.codigoRequerimiento = codigoRequerimiento;
    }

    public Long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Long idProveedor) {
        this.idProveedor = idProveedor;
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

    public List<DetalleProformaResponseDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<DetalleProformaResponseDTO> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "ProformaResponseDTO{" +
                "idProforma=" + idProforma +
                ", codigo='" + codigo + '\'' +
                ", fechaRecepcion=" + fechaRecepcion +
                ", precioTotal=" + precioTotal +
                ", estado='" + estado + '\'' +
                ", idRequerimiento=" + idRequerimiento +
                ", codigoRequerimiento='" + codigoRequerimiento + '\'' +
                ", idProveedor=" + idProveedor +
                ", nombreProveedor='" + nombreProveedor + '\'' +
                ", rucProveedor='" + rucProveedor + '\'' +
                ", productos=" + productos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProformaResponseDTO that = (ProformaResponseDTO) o;
        return Objects.equals(idProforma, that.idProforma) &&
                Objects.equals(codigo, that.codigo) &&
                Objects.equals(fechaRecepcion, that.fechaRecepcion) &&
                Objects.equals(precioTotal, that.precioTotal) &&
                Objects.equals(estado, that.estado) &&
                Objects.equals(idRequerimiento, that.idRequerimiento) &&
                Objects.equals(codigoRequerimiento, that.codigoRequerimiento) &&
                Objects.equals(idProveedor, that.idProveedor) &&
                Objects.equals(nombreProveedor, that.nombreProveedor) &&
                Objects.equals(rucProveedor, that.rucProveedor) &&
                Objects.equals(productos, that.productos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProforma, codigo, fechaRecepcion, precioTotal, estado, idRequerimiento, codigoRequerimiento, idProveedor, nombreProveedor, rucProveedor, productos);
    }

}
