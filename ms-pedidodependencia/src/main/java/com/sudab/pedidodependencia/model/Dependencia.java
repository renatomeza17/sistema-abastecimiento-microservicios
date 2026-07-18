package com.sudab.pedidodependencia.model;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "dependencia", uniqueConstraints = @UniqueConstraint(columnNames = "nombre"))
public class Dependencia {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, length = 150)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    public Dependencia() {
    }

    public Dependencia(UUID id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public static DependenciaBuilder builder() {
        return new DependenciaBuilder();
    }

    public static class DependenciaBuilder {
        private UUID id;
        private String nombre;
        private String descripcion;

        DependenciaBuilder() {
        }

        public DependenciaBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public DependenciaBuilder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public DependenciaBuilder descripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        public Dependencia build() {
            return new Dependencia(id, nombre, descripcion);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dependencia that = (Dependencia) o;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre)
                && Objects.equals(descripcion, that.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, descripcion);
    }

    @Override
    public String toString() {
        return "Dependencia{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
