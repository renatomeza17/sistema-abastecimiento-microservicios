package com.sudabRecepcion.ms_recepcion.dto;

import java.util.List;
import java.util.Objects;

public class PedidoRequestDTO {

    private String descripcion;
    private List<DetallePedidoRequestDTO> detalles;

    public PedidoRequestDTO() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<DetallePedidoRequestDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedidoRequestDTO> detalles) {
        this.detalles = detalles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoRequestDTO that = (PedidoRequestDTO) o;
        return Objects.equals(descripcion, that.descripcion) && Objects.equals(detalles, that.detalles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descripcion, detalles);
    }

    @Override
    public String toString() {
        return "PedidoRequestDTO{descripcion='" + descripcion + "', detalles=" + detalles + "}";
    }

    public static class DetallePedidoRequestDTO {
        private Long idProducto;
        private Integer cantidad;
        private String observacionEspecifica;

        public DetallePedidoRequestDTO() {
        }

        public Long getIdProducto() {
            return idProducto;
        }

        public void setIdProducto(Long idProducto) {
            this.idProducto = idProducto;
        }

        public Integer getCantidad() {
            return cantidad;
        }

        public void setCantidad(Integer cantidad) {
            this.cantidad = cantidad;
        }

        public String getObservacionEspecifica() {
            return observacionEspecifica;
        }

        public void setObservacionEspecifica(String observacionEspecifica) {
            this.observacionEspecifica = observacionEspecifica;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DetallePedidoRequestDTO that = (DetallePedidoRequestDTO) o;
            return Objects.equals(idProducto, that.idProducto) && Objects.equals(cantidad, that.cantidad) && Objects.equals(observacionEspecifica, that.observacionEspecifica);
        }

        @Override
        public int hashCode() {
            return Objects.hash(idProducto, cantidad, observacionEspecifica);
        }

        @Override
        public String toString() {
            return "DetallePedidoRequestDTO{idProducto=" + idProducto + ", cantidad=" + cantidad + ", observacionEspecifica='" + observacionEspecifica + "'}";
        }
    }
}
