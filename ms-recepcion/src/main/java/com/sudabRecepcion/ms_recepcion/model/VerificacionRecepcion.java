package com.sudabRecepcion.ms_recepcion.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "verificacion_recepcion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerificacionRecepcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_verificacion")
    private Long idVerificacion;

    @Column(name = "id_orden", nullable = false)
    private Long idOrden;

    @Column(name = "id_usuario_verifica")
    private Long idUsuarioVerifica;

    // EN_PROCESO | ACEPTADO_TOTAL | RECHAZADO
    @Column(nullable = false)
    private String estado = "EN_PROCESO";

    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio = LocalDateTime.now();

    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;

    @OneToMany(mappedBy = "verificacionRecepcion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVerificacion> detalles = new ArrayList<>();
}