package com.sudabRecepcion.ms_recepcion.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "pedido_pendiente")
public class PedidoPendiente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido_pendiente")
    private Long idPedidoPendiente;

    @Column(name = "id_orden", nullable = false)
    private Long idOrden;

    @Column(nullable = false)
    private String motivo;

    @Column(columnDefinition = "TEXT")
    private String observacion;

    @Column(nullable = false)
    private String estado = "PENDIENTE";

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    @Column(name = "fecha_resolucion")
    private LocalDateTime fechaResolucion;

    public PedidoPendiente() {
    }

    public PedidoPendiente(Long idPedidoPendiente, Long idOrden, String motivo, String observacion, String estado, LocalDateTime fechaRegistro, LocalDateTime fechaResolucion) {
        this.idPedidoPendiente = idPedidoPendiente;
        this.idOrden = idOrden;
        this.motivo = motivo;
        this.observacion = observacion;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.fechaResolucion = fechaResolucion;
    }

    public Long getIdPedidoPendiente() {
        return idPedidoPendiente;
    }

    public void setIdPedidoPendiente(Long idPedidoPendiente) {
        this.idPedidoPendiente = idPedidoPendiente;
    }

    public Long getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Long idOrden) {
        this.idOrden = idOrden;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDateTime getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(LocalDateTime fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoPendiente that = (PedidoPendiente) o;
        return Objects.equals(idPedidoPendiente, that.idPedidoPendiente) && Objects.equals(idOrden, that.idOrden) && Objects.equals(motivo, that.motivo) && Objects.equals(observacion, that.observacion) && Objects.equals(estado, that.estado) && Objects.equals(fechaRegistro, that.fechaRegistro) && Objects.equals(fechaResolucion, that.fechaResolucion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPedidoPendiente, idOrden, motivo, observacion, estado, fechaRegistro, fechaResolucion);
    }

    @Override
    public String toString() {
        return "PedidoPendiente{idPedidoPendiente=" + idPedidoPendiente + ", idOrden=" + idOrden + ", motivo='" + motivo + "', observacion='" + observacion + "', estado='" + estado + "', fechaRegistro=" + fechaRegistro + ", fechaResolucion=" + fechaResolucion + "}";
    }
}
