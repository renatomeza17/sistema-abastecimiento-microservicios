package com.sudab.requerimientos.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

/**
 * Datos adicionales de un usuario que actua como proveedor (cotiza
 * proformas). El usuario en si vive en otro microservicio (ms-login) con
 * id autoincremental; aqui solo se guarda esa referencia (idUsuario) mas
 * los datos propios de proveedor.
 */
@Entity
@Table(name = "proveedor", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ruc"),
        @UniqueConstraint(columnNames = "id_usuario")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, length = 11)
    private String ruc;

    @Column(name = "razon_social", nullable = false, length = 200)
    private String razonSocial;

    @Column(length = 255)
    private String direccion;

    // Referencia externa a ms-login (usuario), id autoincremental
    @Column(name = "id_usuario", nullable = false, unique = true)
    private Long idUsuario;
}
