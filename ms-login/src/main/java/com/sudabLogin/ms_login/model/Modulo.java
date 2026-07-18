package com.sudabLogin.ms_login.model;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "modulo")
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

    public Modulo() {
    }

    public Modulo(Integer idMod, String descripcion, String url, String activo) {
        this.idMod = idMod;
        this.descripcion = descripcion;
        this.url = url;
        this.activo = activo;
    }

    public Integer getIdMod() {
        return idMod;
    }

    public void setIdMod(Integer idMod) {
        this.idMod = idMod;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Modulo{" +
                "idMod=" + idMod +
                ", descripcion='" + descripcion + '\'' +
                ", url='" + url + '\'' +
                ", activo='" + activo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Modulo modulo = (Modulo) o;
        return Objects.equals(idMod, modulo.idMod) &&
                Objects.equals(descripcion, modulo.descripcion) &&
                Objects.equals(url, modulo.url) &&
                Objects.equals(activo, modulo.activo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMod, descripcion, url, activo);
    }
}
