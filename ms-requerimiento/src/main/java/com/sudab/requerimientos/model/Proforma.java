package com.sudab.requerimientos.model;

import java.util.UUID;

import com.sudab.requerimientos.model.enums.EstadoProforma;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "proforma", uniqueConstraints = @UniqueConstraint(columnNames = "codigo"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    // Usuario (proveedor que cotiza) vive en otro microservicio
    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_requerimiento", nullable = false)
    private Requerimiento requerimiento;

    @OneToMany(mappedBy = "proforma", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<DetalleProforma> detalles = new ArrayList<>();

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
}
