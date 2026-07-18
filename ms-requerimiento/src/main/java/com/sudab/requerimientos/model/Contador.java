package com.sudab.requerimientos.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Tabla auxiliar para generar codigos correlativos (REQ-2026-00001,
 * PROF-2026-00001) de forma segura ante solicitudes concurrentes.
 * Usa clave compuesta natural (prefijo + anio); no necesita un UUID propio
 * porque ya es unica por definicion.
 */
@Entity
@Table(name = "contador")
@IdClass(Contador.ContadorId.class)
public class Contador {

    @Id
    @Column(nullable = false, length = 10)
    private String prefijo;

    @Id
    @Column(nullable = false, columnDefinition = "int4")
    private Integer anio;

    @Column(name = "ultimo_numero", nullable = false, columnDefinition = "int4")
    private Integer ultimoNumero;

    public Contador() {
    }

    public Contador(String prefijo, Integer anio, Integer ultimoNumero) {
        this.prefijo = prefijo;
        this.anio = anio;
        this.ultimoNumero = ultimoNumero;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getUltimoNumero() {
        return ultimoNumero;
    }

    public void setUltimoNumero(Integer ultimoNumero) {
        this.ultimoNumero = ultimoNumero;
    }

    @Override
    public String toString() {
        return "Contador{prefijo='" + prefijo + "', anio=" + anio + ", ultimoNumero=" + ultimoNumero + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contador contador = (Contador) o;
        return Objects.equals(prefijo, contador.prefijo) && Objects.equals(anio, contador.anio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prefijo, anio);
    }

    public static class ContadorId implements Serializable {
        private String prefijo;
        private Integer anio;

        public ContadorId() {
        }

        public ContadorId(String prefijo, Integer anio) {
            this.prefijo = prefijo;
            this.anio = anio;
        }

        public String getPrefijo() {
            return prefijo;
        }

        public void setPrefijo(String prefijo) {
            this.prefijo = prefijo;
        }

        public Integer getAnio() {
            return anio;
        }

        public void setAnio(Integer anio) {
            this.anio = anio;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ContadorId that = (ContadorId) o;
            return Objects.equals(prefijo, that.prefijo) && Objects.equals(anio, that.anio);
        }

        @Override
        public int hashCode() {
            return Objects.hash(prefijo, anio);
        }
    }
}
