package com.sudabOrdenes.ms_ordenes.DTO;

import java.util.Objects;

public class OrdenRequestDTO {
    private Long idProforma;

    // private Long idProveedor;

    //DATOS QUE DIGITA EL USUARIO
    private String fechaEntrega;
    private String lugarEntrega;
    private String observaciones; 
    private String formaPago;
    private String plazoEntrega;
    private String garantia;

    // Lista de detalles que vienen desde Angular
    // private List<OrdenDetalleRequestDTO> detalles;

    public OrdenRequestDTO() {
    }

    public Long getIdProforma() {
        return idProforma;
    }

    public void setIdProforma(Long idProforma) {
        this.idProforma = idProforma;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getLugarEntrega() {
        return lugarEntrega;
    }

    public void setLugarEntrega(String lugarEntrega) {
        this.lugarEntrega = lugarEntrega;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getPlazoEntrega() {
        return plazoEntrega;
    }

    public void setPlazoEntrega(String plazoEntrega) {
        this.plazoEntrega = plazoEntrega;
    }

    public String getGarantia() {
        return garantia;
    }

    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }

    @Override
    public String toString() {
        return "OrdenRequestDTO{" +
                "idProforma=" + idProforma +
                ", fechaEntrega='" + fechaEntrega + '\'' +
                ", lugarEntrega='" + lugarEntrega + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", formaPago='" + formaPago + '\'' +
                ", plazoEntrega='" + plazoEntrega + '\'' +
                ", garantia='" + garantia + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdenRequestDTO that = (OrdenRequestDTO) o;
        return Objects.equals(idProforma, that.idProforma) &&
                Objects.equals(fechaEntrega, that.fechaEntrega) &&
                Objects.equals(lugarEntrega, that.lugarEntrega) &&
                Objects.equals(observaciones, that.observaciones) &&
                Objects.equals(formaPago, that.formaPago) &&
                Objects.equals(plazoEntrega, that.plazoEntrega) &&
                Objects.equals(garantia, that.garantia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProforma, fechaEntrega, lugarEntrega, observaciones, formaPago, plazoEntrega, garantia);
    }

}
