package com.sudab.requerimientos.model;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detalle_proforma")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    // Producto vive en otro microservicio (producto-service)
    @Column(name = "id_producto", nullable = false)
    private Long idProducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proforma", nullable = false)
    private Proforma proforma;
}
