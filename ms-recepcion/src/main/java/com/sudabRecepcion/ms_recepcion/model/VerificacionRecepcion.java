package com.sudabRecepcion.ms_recepcion.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "verificacion_recepcion")
public class VerificacionRecepcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_verificacion")
    private Long idVerificacion;

    @Column(name = "id_orden", nullable = false)
    private Long idOrden;

    @Column(name = "id_usuario_verifica")
    private Long idUsuarioVerifica;

    @Column(nullable = false)
    private String estado = "EN_PROCESO";

    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio = LocalDateTime.now();

    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;

    @OneToMany(mappedBy = "verificacionRecepcion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVerificacion> detalles = new ArrayList<>();

    public VerificacionRecepcion() {
    }

    public VerificacionRecepcion(Long idVerificacion, Long idOrden, Long idUsuarioVerifica, String estado, LocalDateTime fechaInicio, LocalDateTime fechaFin, List<DetalleVerificacion> detalles) {
        this.idVerificacion = idVerificacion;
        this.idOrden = idOrden;
        this.idUsuarioVerifica = idUsuarioVerifica;
        this.estado = estado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.detalles = detalles;
    }

    public Long getIdVerificacion() {
        return idVerificacion;
    }

    public void setIdVerificacion(Long idVerificacion) {
        this.idVerificacion = idVerificacion;
    }

    public Long getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Long idOrden) {
        this.idOrden = idOrden;
    }

    public Long getIdUsuarioVerifica() {
        return idUsuarioVerifica;
    }

    public void setIdUsuarioVerifica(Long idUsuarioVerifica) {
        this.idUsuarioVerifica = idUsuarioVerifica;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<DetalleVerificacion> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVerificacion> detalles) {
        this.detalles = detalles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VerificacionRecepcion that = (VerificacionRecepcion) o;
        return Objects.equals(idVerificacion, that.idVerificacion) && Objects.equals(idOrden, that.idOrden) && Objects.equals(idUsuarioVerifica, that.idUsuarioVerifica) && Objects.equals(estado, that.estado) && Objects.equals(fechaInicio, that.fechaInicio) && Objects.equals(fechaFin, that.fechaFin) && Objects.equals(detalles, that.detalles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVerificacion, idOrden, idUsuarioVerifica, estado, fechaInicio, fechaFin, detalles);
    }

    @Override
    public String toString() {
        return "VerificacionRecepcion{idVerificacion=" + idVerificacion + ", idOrden=" + idOrden + ", idUsuarioVerifica=" + idUsuarioVerifica + ", estado='" + estado + "', fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", detalles=" + detalles + "}";
    }
}
