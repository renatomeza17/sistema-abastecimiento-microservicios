package com.sudabLogin.ms_login.model;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "rol_modulo")
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

    public RolModulo() {
    }

    public RolModulo(Integer idRolMod, Rol rol, Modulo modulo, Boolean puedeCrear, Boolean puedeLeer,
                     Boolean puedeActualizar, Boolean puedeEliminar) {
        this.idRolMod = idRolMod;
        this.rol = rol;
        this.modulo = modulo;
        this.puedeCrear = puedeCrear;
        this.puedeLeer = puedeLeer;
        this.puedeActualizar = puedeActualizar;
        this.puedeEliminar = puedeEliminar;
    }

    public Integer getIdRolMod() {
        return idRolMod;
    }

    public void setIdRolMod(Integer idRolMod) {
        this.idRolMod = idRolMod;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public Boolean getPuedeCrear() {
        return puedeCrear;
    }

    public void setPuedeCrear(Boolean puedeCrear) {
        this.puedeCrear = puedeCrear;
    }

    public Boolean getPuedeLeer() {
        return puedeLeer;
    }

    public void setPuedeLeer(Boolean puedeLeer) {
        this.puedeLeer = puedeLeer;
    }

    public Boolean getPuedeActualizar() {
        return puedeActualizar;
    }

    public void setPuedeActualizar(Boolean puedeActualizar) {
        this.puedeActualizar = puedeActualizar;
    }

    public Boolean getPuedeEliminar() {
        return puedeEliminar;
    }

    public void setPuedeEliminar(Boolean puedeEliminar) {
        this.puedeEliminar = puedeEliminar;
    }

    @Override
    public String toString() {
        return "RolModulo{" +
                "idRolMod=" + idRolMod +
                ", rol=" + rol +
                ", modulo=" + modulo +
                ", puedeCrear=" + puedeCrear +
                ", puedeLeer=" + puedeLeer +
                ", puedeActualizar=" + puedeActualizar +
                ", puedeEliminar=" + puedeEliminar +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolModulo rolModulo = (RolModulo) o;
        return Objects.equals(idRolMod, rolModulo.idRolMod) &&
                Objects.equals(rol, rolModulo.rol) &&
                Objects.equals(modulo, rolModulo.modulo) &&
                Objects.equals(puedeCrear, rolModulo.puedeCrear) &&
                Objects.equals(puedeLeer, rolModulo.puedeLeer) &&
                Objects.equals(puedeActualizar, rolModulo.puedeActualizar) &&
                Objects.equals(puedeEliminar, rolModulo.puedeEliminar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRolMod, rol, modulo, puedeCrear, puedeLeer, puedeActualizar, puedeEliminar);
    }
}
