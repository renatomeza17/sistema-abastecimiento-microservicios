package com.sudab.requerimientos.model;

import java.util.UUID;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "requerimiento_detalle")
public class RequerimientoDetalle {

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
    @JoinColumn(name = "id_requerimiento", nullable = false)
    private Requerimiento requerimiento;

    public RequerimientoDetalle() {
    }

    public RequerimientoDetalle(UUID id, Integer cantidad, String unidadMedida,
            String observacionEspecifica, Long idProducto, Requerimiento requerimiento) {
        this.id = id;
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
        this.observacionEspecifica = observacionEspecifica;
        this.idProducto = idProducto;
        this.requerimiento = requerimiento;
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

    public Requerimiento getRequerimiento() {
        return requerimiento;
    }

    public void setRequerimiento(Requerimiento requerimiento) {
        this.requerimiento = requerimiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequerimientoDetalle that = (RequerimientoDetalle) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "RequerimientoDetalle{" +
                "id=" + id +
                ", cantidad=" + cantidad +
                ", unidadMedida='" + unidadMedida + '\'' +
                ", observacionEspecifica='" + observacionEspecifica + '\'' +
                ", idProducto=" + idProducto +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private Integer cantidad;
        private String unidadMedida;
        private String observacionEspecifica;
        private Long idProducto;
        private Requerimiento requerimiento;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder cantidad(Integer cantidad) {
            this.cantidad = cantidad;
            return this;
        }

        public Builder unidadMedida(String unidadMedida) {
            this.unidadMedida = unidadMedida;
            return this;
        }

        public Builder observacionEspecifica(String observacionEspecifica) {
            this.observacionEspecifica = observacionEspecifica;
            return this;
        }

        public Builder idProducto(Long idProducto) {
            this.idProducto = idProducto;
            return this;
        }

        public Builder requerimiento(Requerimiento requerimiento) {
            this.requerimiento = requerimiento;
            return this;
        }

        public RequerimientoDetalle build() {
            return new RequerimientoDetalle(id, cantidad, unidadMedida,
                    observacionEspecifica, idProducto, requerimiento);
        }
    }
}
