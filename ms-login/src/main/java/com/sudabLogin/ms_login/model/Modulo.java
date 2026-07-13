package com.sudabLogin.ms_login.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "modulo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Modulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mod")
    private Integer idMod;

    @Column(name = "descripcion", nullable = false, unique = true)
    private String descripcion; // ej: "ORDENES", "REQUERIMIENTO", "RECEPCION"

    @Column(name = "url", nullable = false, unique = true)
    private String url; // ej: "/orden", "/requerimiento"

    @Column(name = "activo", nullable = false)
    private String activo = "true";
}