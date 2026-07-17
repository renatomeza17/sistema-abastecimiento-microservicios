package com.sudab.pedidodependencia.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "contador")
@IdClass(Contador.ContadorId.class)
public class Contador {

    @Id
    @Column(nullable = false, length = 10)
    private String prefijo;

    @Id
    @Column(nullable = false)
    private Integer anio;

    @Column(name = "ultimo_numero", nullable = false)
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

    public static ContadorBuilder builder() {
        return new ContadorBuilder();
    }

    public static class ContadorBuilder {
        private String prefijo;
        private Integer anio;
        private Integer ultimoNumero;

        ContadorBuilder() {
        }

        public ContadorBuilder prefijo(String prefijo) {
            this.prefijo = prefijo;
            return this;
        }

        public ContadorBuilder anio(Integer anio) {
            this.anio = anio;
            return this;
        }

        public ContadorBuilder ultimoNumero(Integer ultimoNumero) {
            this.ultimoNumero = ultimoNumero;
            return this;
        }

        public Contador build() {
            return new Contador(prefijo, anio, ultimoNumero);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contador contador = (Contador) o;
        return Objects.equals(prefijo, contador.prefijo) && Objects.equals(anio, contador.anio)
                && Objects.equals(ultimoNumero, contador.ultimoNumero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prefijo, anio, ultimoNumero);
    }

    @Override
    public String toString() {
        return "Contador{" +
                "prefijo='" + prefijo + '\'' +
                ", anio=" + anio +
                ", ultimoNumero=" + ultimoNumero +
                '}';
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

        @Override
        public String toString() {
            return "ContadorId{" +
                    "prefijo='" + prefijo + '\'' +
                    ", anio=" + anio +
                    '}';
        }
    }
}
