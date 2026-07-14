package com.sudab.pedidodependencia.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "detalle_pedido_dependencia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetallePedidoDependencia {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "unidad_medida", nullable = false, length = 20)
    private String unidadMedida; // ej: UNIDAD, KG, LITRO, CAJA

    @Column(name = "observacion_especifica", columnDefinition = "TEXT")
    private String observacionEspecifica;

    // Producto vive en otro microservicio (producto-service)
    @Column(name = "id_producto", nullable = false)
    private Long idProducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido", nullable = false)
    private PedidoDependencia pedido;
}
