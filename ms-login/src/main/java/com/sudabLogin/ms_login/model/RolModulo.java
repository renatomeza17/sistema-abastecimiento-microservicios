package com.sudabLogin.ms_login.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rol_modulo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolModulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol_mod")
    private Integer idRolMod;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "id_mod", nullable = false)
    private Modulo modulo;

    @Column(name = "puede_crear", nullable = false)
    private Boolean puedeCrear = false;

    @Column(name = "puede_leer", nullable = false)
    private Boolean puedeLeer = false;

    @Column(name = "puede_actualizar", nullable = false)
    private Boolean puedeActualizar = false;

    @Column(name = "puede_eliminar", nullable = false)
    private Boolean puedeEliminar = false;
}