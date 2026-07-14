package com.sudab.requerimientos.model;

import java.util.UUID;

import com.sudab.requerimientos.model.enums.EstadoRequerimiento;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "requerimiento", uniqueConstraints = @UniqueConstraint(columnNames = "codigo"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    // Dependencia y usuario viven en otros microservicios: solo guardamos el ID,
    // sin relacion JPA, para no acoplar este servicio a sus tablas.
    @Column(name = "id_dependencia", nullable = false)
    private UUID idDependencia;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @OneToMany(mappedBy = "requerimiento", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<RequerimientoDetalle> detalles = new ArrayList<>();

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
}
