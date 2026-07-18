package com.sudab.requerimientos.model;

import java.util.UUID;

import com.sudab.requerimientos.model.enums.EstadoRequerimiento;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "requerimiento", uniqueConstraints = @UniqueConstraint(columnNames = "codigo"))
public class Requerimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, length = 20)
    private String codigo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoRequerimiento estado;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @Version
    private Long version;

    @Column(name = "id_dependencia", nullable = false)
    private UUID idDependencia;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @OneToMany(mappedBy = "requerimiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RequerimientoDetalle> detalles = new ArrayList<>();

    public Requerimiento() {
    }

    public Requerimiento(UUID id, String codigo, String descripcion, EstadoRequerimiento estado,
            LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion, Long version,
            UUID idDependencia, Long idUsuario, List<RequerimientoDetalle> detalles) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.version = version;
        this.idDependencia = idDependencia;
        this.idUsuario = idUsuario;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoRequerimiento getEstado() {
        return estado;
    }

    public void setEstado(EstadoRequerimiento estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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

    public UUID getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(UUID idDependencia) {
        this.idDependencia = idDependencia;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<RequerimientoDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<RequerimientoDetalle> detalles) {
        this.detalles = detalles;
    }

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
        if (this.estado == null) this.estado = EstadoRequerimiento.PENDIENTE;
    }

    @PreUpdate
    protected void onUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Requerimiento that = (Requerimiento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Requerimiento{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado=" + estado +
                ", fechaCreacion=" + fechaCreacion +
                ", fechaActualizacion=" + fechaActualizacion +
                ", version=" + version +
                ", idDependencia=" + idDependencia +
                ", idUsuario=" + idUsuario +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private String codigo;
        private String descripcion;
        private EstadoRequerimiento estado;
        private LocalDateTime fechaCreacion;
        private LocalDateTime fechaActualizacion;
        private Long version;
        private UUID idDependencia;
        private Long idUsuario;
        private List<RequerimientoDetalle> detalles = new ArrayList<>();

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder codigo(String codigo) {
            this.codigo = codigo;
            return this;
        }

        public Builder descripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        public Builder estado(EstadoRequerimiento estado) {
            this.estado = estado;
            return this;
        }

        public Builder fechaCreacion(LocalDateTime fechaCreacion) {
            this.fechaCreacion = fechaCreacion;
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

        public Builder idDependencia(UUID idDependencia) {
            this.idDependencia = idDependencia;
            return this;
        }

        public Builder idUsuario(Long idUsuario) {
            this.idUsuario = idUsuario;
            return this;
        }

        public Builder detalles(List<RequerimientoDetalle> detalles) {
            this.detalles = detalles != null ? detalles : new ArrayList<>();
            return this;
        }

        public Requerimiento build() {
            return new Requerimiento(id, codigo, descripcion, estado, fechaCreacion,
                    fechaActualizacion, version, idDependencia, idUsuario, detalles);
        }
    }
}
