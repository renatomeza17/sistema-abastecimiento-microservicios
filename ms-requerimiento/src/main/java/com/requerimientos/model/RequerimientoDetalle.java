package com.sudab.requerimientos.model;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "requerimiento_detalle")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequerimientoDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Integer cantidad;

    // Columna adicional sugerida: sin unidad, la cantidad es ambigua.
    @Column(name = "unidad_medida", nullable = false, length = 20)
    private String unidadMedida; // ej: UNIDAD, KG, LITRO, CAJA

    @Column(name = "observacion_especifica", columnDefinition = "TEXT")
    private String observacionEspecifica;

    // Producto vive en otro microservicio (producto-service)
    @Column(name = "id_producto", nullable = false)
    private Long idProducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_requerimiento", nullable = false)
    private Requerimiento requerimiento;
}
