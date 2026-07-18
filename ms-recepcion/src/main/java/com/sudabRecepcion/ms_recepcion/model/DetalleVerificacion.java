package com.sudabRecepcion.ms_recepcion.model;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_verificacion")
public class DetalleVerificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_verificacion")
    private Long idDetalleVerificacion;

    @ManyToOne
    @JoinColumn(name = "id_verificacion", nullable = false)
    private VerificacionRecepcion verificacionRecepcion;

    @Column(name = "id_orden_detalle", nullable = false)
    private Long idOrdenDetalle;

    @Column(name = "nombre_producto")
    private String nombreProducto;

    @Column(name = "cantidad_esperada")
    private Integer cantidadEsperada;

    @Column(nullable = false)
    private Boolean verificado = false;

    private String observacion;

    public DetalleVerificacion() {
    }

    public DetalleVerificacion(Long idDetalleVerificacion, VerificacionRecepcion verificacionRecepcion, Long idOrdenDetalle, String nombreProducto, Integer cantidadEsperada, Boolean verificado, String observacion) {
        this.idDetalleVerificacion = idDetalleVerificacion;
        this.verificacionRecepcion = verificacionRecepcion;
        this.idOrdenDetalle = idOrdenDetalle;
        this.nombreProducto = nombreProducto;
        this.cantidadEsperada = cantidadEsperada;
        this.verificado = verificado;
        this.observacion = observacion;
    }

    public Long getIdDetalleVerificacion() {
        return idDetalleVerificacion;
    }

    public void setIdDetalleVerificacion(Long idDetalleVerificacion) {
        this.idDetalleVerificacion = idDetalleVerificacion;
    }

    public VerificacionRecepcion getVerificacionRecepcion() {
        return verificacionRecepcion;
    }

    public void setVerificacionRecepcion(VerificacionRecepcion verificacionRecepcion) {
        this.verificacionRecepcion = verificacionRecepcion;
    }

    public Long getIdOrdenDetalle() {
        return idOrdenDetalle;
    }

    public void setIdOrdenDetalle(Long idOrdenDetalle) {
        this.idOrdenDetalle = idOrdenDetalle;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Integer getCantidadEsperada() {
        return cantidadEsperada;
    }

    public void setCantidadEsperada(Integer cantidadEsperada) {
        this.cantidadEsperada = cantidadEsperada;
    }

    public Boolean getVerificado() {
        return verificado;
    }

    public void setVerificado(Boolean verificado) {
        this.verificado = verificado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleVerificacion that = (DetalleVerificacion) o;
        return Objects.equals(idDetalleVerificacion, that.idDetalleVerificacion) && Objects.equals(verificacionRecepcion, that.verificacionRecepcion) && Objects.equals(idOrdenDetalle, that.idOrdenDetalle) && Objects.equals(nombreProducto, that.nombreProducto) && Objects.equals(cantidadEsperada, that.cantidadEsperada) && Objects.equals(verificado, that.verificado) && Objects.equals(observacion, that.observacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDetalleVerificacion, verificacionRecepcion, idOrdenDetalle, nombreProducto, cantidadEsperada, verificado, observacion);
    }

    @Override
    public String toString() {
        return "DetalleVerificacion{idDetalleVerificacion=" + idDetalleVerificacion + ", verificacionRecepcion=" + verificacionRecepcion + ", idOrdenDetalle=" + idOrdenDetalle + ", nombreProducto='" + nombreProducto + "', cantidadEsperada=" + cantidadEsperada + ", verificado=" + verificado + ", observacion='" + observacion + "'}";
    }
}
