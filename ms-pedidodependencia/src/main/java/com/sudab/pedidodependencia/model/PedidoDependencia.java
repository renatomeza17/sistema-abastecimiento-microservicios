package com.sudab.pedidodependencia.model;

import com.sudab.pedidodependencia.model.enums.EstadoPedidoDependencia;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "pedido_dependencia", uniqueConstraints = @UniqueConstraint(columnNames = "codigo"))
public class PedidoDependencia {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, length = 20)
    private String codigo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoPedidoDependencia estado;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @Version
    private Long version;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dependencia", nullable = false)
    private Dependencia dependencia;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedidoDependencia> detalles = new ArrayList<>();

    public PedidoDependencia() {
    }

    public PedidoDependencia(UUID id, String codigo, String descripcion, EstadoPedidoDependencia estado,
            LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion, Long version, Long idUsuario,
            Dependencia dependencia, List<DetallePedidoDependencia> detalles) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.version = version;
        this.idUsuario = idUsuario;
        this.dependencia = dependencia;
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

    public EstadoPedidoDependencia getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedidoDependencia estado) {
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

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Dependencia getDependencia() {
        return dependencia;
    }

    public void setDependencia(Dependencia dependencia) {
        this.dependencia = dependencia;
    }

    public List<DetallePedidoDependencia> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedidoDependencia> detalles) {
        this.detalles = detalles;
    }

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
        if (this.estado == null) this.estado = EstadoPedidoDependencia.PENDIENTE;
    }

    @PreUpdate
    protected void onUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }

    public static PedidoDependenciaBuilder builder() {
        return new PedidoDependenciaBuilder();
    }

    public static class PedidoDependenciaBuilder {
        private UUID id;
        private String codigo;
        private String descripcion;
        private EstadoPedidoDependencia estado;
        private LocalDateTime fechaCreacion;
        private LocalDateTime fechaActualizacion;
        private Long version;
        private Long idUsuario;
        private Dependencia dependencia;
        private List<DetallePedidoDependencia> detalles = new ArrayList<>();

        PedidoDependenciaBuilder() {
        }

        public PedidoDependenciaBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public PedidoDependenciaBuilder codigo(String codigo) {
            this.codigo = codigo;
            return this;
        }

        public PedidoDependenciaBuilder descripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        public PedidoDependenciaBuilder estado(EstadoPedidoDependencia estado) {
            this.estado = estado;
            return this;
        }

        public PedidoDependenciaBuilder fechaCreacion(LocalDateTime fechaCreacion) {
            this.fechaCreacion = fechaCreacion;
            return this;
        }

        public PedidoDependenciaBuilder fechaActualizacion(LocalDateTime fechaActualizacion) {
            this.fechaActualizacion = fechaActualizacion;
            return this;
        }

        public PedidoDependenciaBuilder version(Long version) {
            this.version = version;
            return this;
        }

        public PedidoDependenciaBuilder idUsuario(Long idUsuario) {
            this.idUsuario = idUsuario;
            return this;
        }

        public PedidoDependenciaBuilder dependencia(Dependencia dependencia) {
            this.dependencia = dependencia;
            return this;
        }

        public PedidoDependenciaBuilder detalles(List<DetallePedidoDependencia> detalles) {
            this.detalles = detalles;
            return this;
        }

        public PedidoDependencia build() {
            return new PedidoDependencia(id, codigo, descripcion, estado, fechaCreacion,
                    fechaActualizacion, version, idUsuario, dependencia, detalles);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoDependencia that = (PedidoDependencia) o;
        return Objects.equals(id, that.id) && Objects.equals(codigo, that.codigo)
                && Objects.equals(descripcion, that.descripcion) && estado == that.estado
                && Objects.equals(fechaCreacion, that.fechaCreacion)
                && Objects.equals(fechaActualizacion, that.fechaActualizacion)
                && Objects.equals(version, that.version) && Objects.equals(idUsuario, that.idUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo, descripcion, estado, fechaCreacion, fechaActualizacion, version, idUsuario);
    }

    @Override
    public String toString() {
        return "PedidoDependencia{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado=" + estado +
                ", fechaCreacion=" + fechaCreacion +
                ", fechaActualizacion=" + fechaActualizacion +
                ", version=" + version +
                ", idUsuario=" + idUsuario +
                ", dependencia=" + dependencia +
                ", detalles=" + detalles +
                '}';
    }
}
