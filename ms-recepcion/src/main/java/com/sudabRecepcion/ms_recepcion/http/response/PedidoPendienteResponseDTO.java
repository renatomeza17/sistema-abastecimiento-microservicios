package com.sudabRecepcion.ms_recepcion.http.response;

import java.time.LocalDateTime;
import java.util.Objects;

public class PedidoPendienteResponseDTO {

    private Long idPedidoPendiente;
    private Long idOrden;
    private String motivo;
    private String observacion;
    private String estado;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaResolucion;

    public PedidoPendienteResponseDTO() {
    }

    public PedidoPendienteResponseDTO(Long idPedidoPendiente, Long idOrden, String motivo, String observacion, String estado, LocalDateTime fechaRegistro, LocalDateTime fechaResolucion) {
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
        PedidoPendienteResponseDTO that = (PedidoPendienteResponseDTO) o;
        return Objects.equals(idPedidoPendiente, that.idPedidoPendiente) && Objects.equals(idOrden, that.idOrden) && Objects.equals(motivo, that.motivo) && Objects.equals(observacion, that.observacion) && Objects.equals(estado, that.estado) && Objects.equals(fechaRegistro, that.fechaRegistro) && Objects.equals(fechaResolucion, that.fechaResolucion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPedidoPendiente, idOrden, motivo, observacion, estado, fechaRegistro, fechaResolucion);
    }

    @Override
    public String toString() {
        return "PedidoPendienteResponseDTO{idPedidoPendiente=" + idPedidoPendiente + ", idOrden=" + idOrden + ", motivo='" + motivo + "', observacion='" + observacion + "', estado='" + estado + "', fechaRegistro=" + fechaRegistro + ", fechaResolucion=" + fechaResolucion + "}";
    }

    public static PedidoPendienteResponseDTOBuilder builder() {
        return new PedidoPendienteResponseDTOBuilder();
    }

    public static class PedidoPendienteResponseDTOBuilder {
        private Long idPedidoPendiente;
        private Long idOrden;
        private String motivo;
        private String observacion;
        private String estado;
        private LocalDateTime fechaRegistro;
        private LocalDateTime fechaResolucion;

        public PedidoPendienteResponseDTOBuilder idPedidoPendiente(Long idPedidoPendiente) {
            this.idPedidoPendiente = idPedidoPendiente;
            return this;
        }

        public PedidoPendienteResponseDTOBuilder idOrden(Long idOrden) {
            this.idOrden = idOrden;
            return this;
        }

        public PedidoPendienteResponseDTOBuilder motivo(String motivo) {
            this.motivo = motivo;
            return this;
        }

        public PedidoPendienteResponseDTOBuilder observacion(String observacion) {
            this.observacion = observacion;
            return this;
        }

        public PedidoPendienteResponseDTOBuilder estado(String estado) {
            this.estado = estado;
            return this;
        }

        public PedidoPendienteResponseDTOBuilder fechaRegistro(LocalDateTime fechaRegistro) {
            this.fechaRegistro = fechaRegistro;
            return this;
        }

        public PedidoPendienteResponseDTOBuilder fechaResolucion(LocalDateTime fechaResolucion) {
            this.fechaResolucion = fechaResolucion;
            return this;
        }

        public PedidoPendienteResponseDTO build() {
            return new PedidoPendienteResponseDTO(idPedidoPendiente, idOrden, motivo, observacion, estado, fechaRegistro, fechaResolucion);
        }
    }
}
