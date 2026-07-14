package com.sudab.pedidodependencia.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

/**
 * Tabla auxiliar para generar codigos correlativos (PED-2026-00001) de forma
 * segura ante solicitudes concurrentes. Clave compuesta natural (prefijo +
 * anio); no necesita un UUID propio porque ya es unica por definicion.
 */
@Entity
@Table(name = "contador")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(Contador.ContadorId.class)
public class Contador {

    @Id
    @Column(nullable = false, length = 10)
    private String prefijo; // "PED"

    @Id
    @Column(nullable = false)
    private Integer anio;

    @Column(name = "ultimo_numero", nullable = false)
    private Integer ultimoNumero;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class ContadorId implements Serializable {
        private String prefijo;
        private Integer anio;
    }
}
