package com.sudab.pedidodependencia.model;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "detalle_pedido_dependencia")
public class DetallePedidoDependencia {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "unidad_medida", nullable = false, length = 20)
    private String unidadMedida;

    @Column(name = "observacion_especifica", columnDefinition = "TEXT")
    private String observacionEspecifica;

    @Column(name = "id_producto", nullable = false)
    private Long idProducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido", nullable = false)
    private PedidoDependencia pedido;

    public DetallePedidoDependencia() {
    }

    public DetallePedidoDependencia(UUID id, Integer cantidad, String unidadMedida,
            String observacionEspecifica, Long idProducto, PedidoDependencia pedido) {
        this.id = id;
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
        this.observacionEspecifica = observacionEspecifica;
        this.idProducto = idProducto;
        this.pedido = pedido;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getObservacionEspecifica() {
        return observacionEspecifica;
    }

    public void setObservacionEspecifica(String observacionEspecifica) {
        this.observacionEspecifica = observacionEspecifica;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public PedidoDependencia getPedido() {
        return pedido;
    }

    public void setPedido(PedidoDependencia pedido) {
        this.pedido = pedido;
    }

    public static DetallePedidoDependenciaBuilder builder() {
        return new DetallePedidoDependenciaBuilder();
    }

    public static class DetallePedidoDependenciaBuilder {
        private UUID id;
        private Integer cantidad;
        private String unidadMedida;
        private String observacionEspecifica;
        private Long idProducto;
        private PedidoDependencia pedido;

        DetallePedidoDependenciaBuilder() {
        }

        public DetallePedidoDependenciaBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public DetallePedidoDependenciaBuilder cantidad(Integer cantidad) {
            this.cantidad = cantidad;
            return this;
        }

        public DetallePedidoDependenciaBuilder unidadMedida(String unidadMedida) {
            this.unidadMedida = unidadMedida;
            return this;
        }

        public DetallePedidoDependenciaBuilder observacionEspecifica(String observacionEspecifica) {
            this.observacionEspecifica = observacionEspecifica;
            return this;
        }

        public DetallePedidoDependenciaBuilder idProducto(Long idProducto) {
            this.idProducto = idProducto;
            return this;
        }

        public DetallePedidoDependenciaBuilder pedido(PedidoDependencia pedido) {
            this.pedido = pedido;
            return this;
        }

        public DetallePedidoDependencia build() {
            return new DetallePedidoDependencia(id, cantidad, unidadMedida,
                    observacionEspecifica, idProducto, pedido);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetallePedidoDependencia that = (DetallePedidoDependencia) o;
        return Objects.equals(id, that.id) && Objects.equals(cantidad, that.cantidad)
                && Objects.equals(unidadMedida, that.unidadMedida)
                && Objects.equals(observacionEspecifica, that.observacionEspecifica)
                && Objects.equals(idProducto, that.idProducto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cantidad, unidadMedida, observacionEspecifica, idProducto);
    }

    @Override
    public String toString() {
        return "DetallePedidoDependencia{" +
                "id=" + id +
                ", cantidad=" + cantidad +
                ", unidadMedida='" + unidadMedida + '\'' +
                ", observacionEspecifica='" + observacionEspecifica + '\'' +
                ", idProducto=" + idProducto +
                '}';
    }
}
