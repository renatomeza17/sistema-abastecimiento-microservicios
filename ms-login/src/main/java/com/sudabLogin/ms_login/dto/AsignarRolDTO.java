package com.sudabLogin.ms_login.dto;

import java.util.Objects;

public class AsignarRolDTO {
    private Long idUsuario;
    private String nombreRol; // JEFE, DIRECTOR, APOYO

    public AsignarRolDTO() {
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    @Override
    public String toString() {
        return "AsignarRolDTO{" +
                "idUsuario=" + idUsuario +
                ", nombreRol='" + nombreRol + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsignarRolDTO that = (AsignarRolDTO) o;
        return Objects.equals(idUsuario, that.idUsuario) &&
                Objects.equals(nombreRol, that.nombreRol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, nombreRol);
    }
}
