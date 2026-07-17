package com.sudab.requerimientos.model;

import java.util.UUID;

import com.sudab.requerimientos.model.enums.EstadoProforma;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "proforma", uniqueConstraints = @UniqueConstraint(columnNames = "codigo"))
public class Proforma {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, length = 20)
    private String codigo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoProforma estado;

    @Column(name = "fecha_recepcion", nullable = false, updatable = false)
    private LocalDateTime fechaRecepcion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @Version
    private Long version;

    @Column(name = "precio_total", nullable = false, precision = 12, scale = 2)
    private BigDecimal precioTotal;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_requerimiento", nullable = false)
    private Requerimiento requerimiento;

    @OneToMany(mappedBy = "proforma", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleProforma> detalles = new ArrayList<>();

    public Proforma() {
    }

    public Proforma(UUID id, String codigo, EstadoProforma estado, LocalDateTime fechaRecepcion,
            LocalDateTime fechaActualizacion, Long version, BigDecimal precioTotal,
            Long idUsuario, Requerimiento requerimiento, List<DetalleProforma> detalles) {
        this.id = id;
        this.codigo = codigo;
        this.estado = estado;
        this.fechaRecepcion = fechaRecepcion;
        this.fechaActualizacion = fechaActualizacion;
        this.version = version;
        this.precioTotal = precioTotal;
        this.idUsuario = idUsuario;
        this.requerimiento = requerimiento;
        this.detalles = detalles != null ? detalles : new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public EstadoProforma getEstado() {
        return estado;
    }

    public void setEstado(EstadoProforma estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(LocalDateTime fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public BigDecimal getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(BigDecimal precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Requerimiento getRequerimiento() {
        return requerimiento;
    }

    public void setRequerimiento(Requerimiento requerimiento) {
        this.requerimiento = requerimiento;
    }

    public List<DetalleProforma> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleProforma> detalles) {
        this.detalles = detalles;
    }

    @PrePersist
    protected void onCreate() {
        this.fechaRecepcion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
        if (this.estado == null) this.estado = EstadoProforma.PENDIENTE;
    }

    @PreUpdate
    protected void onUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }

    public void recalcularPrecioTotal() {
        this.precioTotal = this.detalles.stream()
                .map(d -> d.getPrecioUnitario().multiply(BigDecimal.valueOf(d.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proforma proforma = (Proforma) o;
        return Objects.equals(id, proforma.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Proforma{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", estado=" + estado +
                ", fechaRecepcion=" + fechaRecepcion +
                ", fechaActualizacion=" + fechaActualizacion +
                ", version=" + version +
                ", precioTotal=" + precioTotal +
                ", idUsuario=" + idUsuario +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private String codigo;
        private EstadoProforma estado;
        private LocalDateTime fechaRecepcion;
        private LocalDateTime fechaActualizacion;
        private Long version;
        private BigDecimal precioTotal;
        private Long idUsuario;
        private Requerimiento requerimiento;
        private List<DetalleProforma> detalles = new ArrayList<>();

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder codigo(String codigo) {
            this.codigo = codigo;
            return this;
        }

        public Builder estado(EstadoProforma estado) {
            this.estado = estado;
            return this;
        }

        public Builder fechaRecepcion(LocalDateTime fechaRecepcion) {
            this.fechaRecepcion = fechaRecepcion;
            return this;
        }

        public Builder fechaActualizacion(LocalDateTime fechaActualizacion) {
            this.fechaActualizacion = fechaActualizacion;
            return this;
        }

        public Builder version(Long version) {
            this.version = version;
            return this;
        }

        public Builder precioTotal(BigDecimal precioTotal) {
            this.precioTotal = precioTotal;
            return this;
        }

        public Builder idUsuario(Long idUsuario) {
            this.idUsuario = idUsuario;
            return this;
        }

        public Builder requerimiento(Requerimiento requerimiento) {
            this.requerimiento = requerimiento;
            return this;
        }

        public Builder detalles(List<DetalleProforma> detalles) {
            this.detalles = detalles != null ? detalles : new ArrayList<>();
            return this;
        }

        public Proforma build() {
            return new Proforma(id, codigo, estado, fechaRecepcion, fechaActualizacion,
                    version, precioTotal, idUsuario, requerimiento, detalles);
        }
    }
}
