package com.sudab.requerimientos.model;

import java.util.UUID;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "detalle_proforma")
public class DetalleProforma {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "observacion_especifica", columnDefinition = "TEXT")
    private String observacionEspecifica;

    @Column(name = "precio_unitario", nullable = false, precision = 12, scale = 2)
    private BigDecimal precioUnitario;

    @Column(name = "id_producto", nullable = false)
    private Long idProducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proforma", nullable = false)
    private Proforma proforma;

    public DetalleProforma() {
    }

    public DetalleProforma(UUID id, Integer cantidad, String observacionEspecifica,
            BigDecimal precioUnitario, Long idProducto, Proforma proforma) {
        this.id = id;
        this.cantidad = cantidad;
        this.observacionEspecifica = observacionEspecifica;
        this.precioUnitario = precioUnitario;
        this.idProducto = idProducto;
        this.proforma = proforma;
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

    public String getObservacionEspecifica() {
        return observacionEspecifica;
    }

    public void setObservacionEspecifica(String observacionEspecifica) {
        this.observacionEspecifica = observacionEspecifica;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Proforma getProforma() {
        return proforma;
    }

    public void setProforma(Proforma proforma) {
        this.proforma = proforma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleProforma that = (DetalleProforma) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DetalleProforma{" +
                "id=" + id +
                ", cantidad=" + cantidad +
                ", observacionEspecifica='" + observacionEspecifica + '\'' +
                ", precioUnitario=" + precioUnitario +
                ", idProducto=" + idProducto +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private Integer cantidad;
        private String observacionEspecifica;
        private BigDecimal precioUnitario;
        private Long idProducto;
        private Proforma proforma;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder cantidad(Integer cantidad) {
            this.cantidad = cantidad;
            return this;
        }

        public Builder observacionEspecifica(String observacionEspecifica) {
            this.observacionEspecifica = observacionEspecifica;
            return this;
        }

        public Builder precioUnitario(BigDecimal precioUnitario) {
            this.precioUnitario = precioUnitario;
            return this;
        }

        public Builder idProducto(Long idProducto) {
            this.idProducto = idProducto;
            return this;
        }

        public Builder proforma(Proforma proforma) {
            this.proforma = proforma;
            return this;
        }

        public DetalleProforma build() {
            return new DetalleProforma(id, cantidad, observacionEspecifica,
                    precioUnitario, idProducto, proforma);
        }
    }
}
