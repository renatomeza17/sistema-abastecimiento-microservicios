package com.sudab.pedidodependencia.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

/**
 * Master data de dependencias (unidades/areas que pueden generar pedidos).
 * Vive en este microservicio porque el modulo de Pedido de Dependencia es su
 * dueno natural.
 */
@Entity
@Table(name = "dependencia", uniqueConstraints = @UniqueConstraint(columnNames = "nombre"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dependencia {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, length = 150)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;
}
