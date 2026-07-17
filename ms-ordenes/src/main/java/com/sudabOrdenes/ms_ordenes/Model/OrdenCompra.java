package com.sudabOrdenes.ms_ordenes.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;

import jakarta.persistence.Table;

@Entity
@Table(name = "ordenes_compra")
public class OrdenCompra {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrden;

    @Column(unique = true, nullable = false)
    private String codigo; // Ej: OC-2026-0001

    @Column(nullable = false, length = 100) 
    private String descripcion;

     @Column(nullable = false)
    private LocalDate fechaCreacion;

    @Column(nullable = false)
    private LocalDate fechaEntrega;

    @Column(nullable = false)
    private String estado;

    
    @Column(nullable = false)
    private Double montoTotal;  

    


    // @OneToOne
    // @JoinColumn(name = "id_proforma",nullable = false)
    // private Proforma proforma;

    //  @ManyToOne
    //  @JoinColumn(name = "id_proveedor",nullable = false)
    //  private Proveedor proveedor;

    @Column(name = "id_proforma", nullable = false)
    private Long idProforma;

    @Column(name = "id_proveedor", nullable = false)
    private Long idProveedor;

    @OneToMany(mappedBy = "ordenCompra", cascade = CascadeType.ALL)
    private List<OrdenCompraDetalle> detalles;



    
    //ATRIBUTOS DEL REQUEST 
    
    @Column(name = "lugar_entrega", length = 255)
    private String lugarEntrega;

    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;

    @Column(name = "forma_pago", length = 100)
    private String formaPago;

    @Column(name = "plazo_entrega", length = 100)
    private String plazoEntrega;

    @Column(name = "garantia", length = 100)
    private String garantia;


    //APROBACIÓN Y FIRMA

    @Column(name = "fecha_autorizacion")
    private LocalDateTime fechaAutorizacion;

    @Column(name = "firma_digital_hash", length = 500)
    private String firmaDigitalHash; // Hash de auditoría de la firma

    @Column(name = "autorizado_por")
    private String autorizadoPor; // Username del Director


    public OrdenCompra() {
    }

    public OrdenCompra(Long idOrden, String codigo, String descripcion, LocalDate fechaCreacion, LocalDate fechaEntrega, String estado, Double montoTotal, Long idProforma, Long idProveedor, List<OrdenCompraDetalle> detalles, String lugarEntrega, String observaciones, String formaPago, String plazoEntrega, String garantia, LocalDateTime fechaAutorizacion, String firmaDigitalHash, String autorizadoPor) {
        this.idOrden = idOrden;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.montoTotal = montoTotal;
        this.idProforma = idProforma;
        this.idProveedor = idProveedor;
        this.detalles = detalles;
        this.lugarEntrega = lugarEntrega;
        this.observaciones = observaciones;
        this.formaPago = formaPago;
        this.plazoEntrega = plazoEntrega;
        this.garantia = garantia;
        this.fechaAutorizacion = fechaAutorizacion;
        this.firmaDigitalHash = firmaDigitalHash;
        this.autorizadoPor = autorizadoPor;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Long getIdProforma() {
        return idProforma;
    }

    public void setIdProforma(Long idProforma) {
        this.idProforma = idProforma;
    }

    public Long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Long idProveedor) {
        this.idProveedor = idProveedor;
    }

    public List<OrdenCompraDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<OrdenCompraDetalle> detalles) {
        this.detalles = detalles;
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

    public LocalDateTime getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(LocalDateTime fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }

    public String getFirmaDigitalHash() {
        return firmaDigitalHash;
    }

    public void setFirmaDigitalHash(String firmaDigitalHash) {
        this.firmaDigitalHash = firmaDigitalHash;
    }

    public String getAutorizadoPor() {
        return autorizadoPor;
    }

    public void setAutorizadoPor(String autorizadoPor) {
        this.autorizadoPor = autorizadoPor;
    }

    @Override
    public String toString() {
        return "OrdenCompra{" +
                "idOrden=" + idOrden +
                ", codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", fechaEntrega=" + fechaEntrega +
                ", estado='" + estado + '\'' +
                ", montoTotal=" + montoTotal +
                ", idProforma=" + idProforma +
                ", idProveedor=" + idProveedor +
                ", detalles=" + detalles +
                ", lugarEntrega='" + lugarEntrega + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", formaPago='" + formaPago + '\'' +
                ", plazoEntrega='" + plazoEntrega + '\'' +
                ", garantia='" + garantia + '\'' +
                ", fechaAutorizacion=" + fechaAutorizacion +
                ", firmaDigitalHash='" + firmaDigitalHash + '\'' +
                ", autorizadoPor='" + autorizadoPor + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdenCompra that = (OrdenCompra) o;
        return Objects.equals(idOrden, that.idOrden) &&
                Objects.equals(codigo, that.codigo) &&
                Objects.equals(descripcion, that.descripcion) &&
                Objects.equals(fechaCreacion, that.fechaCreacion) &&
                Objects.equals(fechaEntrega, that.fechaEntrega) &&
                Objects.equals(estado, that.estado) &&
                Objects.equals(montoTotal, that.montoTotal) &&
                Objects.equals(idProforma, that.idProforma) &&
                Objects.equals(idProveedor, that.idProveedor) &&
                Objects.equals(detalles, that.detalles) &&
                Objects.equals(lugarEntrega, that.lugarEntrega) &&
                Objects.equals(observaciones, that.observaciones) &&
                Objects.equals(formaPago, that.formaPago) &&
                Objects.equals(plazoEntrega, that.plazoEntrega) &&
                Objects.equals(garantia, that.garantia) &&
                Objects.equals(fechaAutorizacion, that.fechaAutorizacion) &&
                Objects.equals(firmaDigitalHash, that.firmaDigitalHash) &&
                Objects.equals(autorizadoPor, that.autorizadoPor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrden, codigo, descripcion, fechaCreacion, fechaEntrega, estado, montoTotal, idProforma, idProveedor, detalles, lugarEntrega, observaciones, formaPago, plazoEntrega, garantia, fechaAutorizacion, firmaDigitalHash, autorizadoPor);
    }

}
