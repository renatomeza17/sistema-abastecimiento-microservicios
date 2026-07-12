package com.sudabRecepcion.ms_recepcion.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedido_pendiente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoPendiente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido_pendiente")
    private Long idPedidoPendiente;

    @Column(name = "id_orden", nullable = false)
    private Long idOrden;

    @Column(nullable = false)
    private String motivo;

    @Column(columnDefinition = "TEXT")
    private String observacion;

    @Column(nullable = false)
    private String estado = "PENDIENTE";

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    @Column(name = "fecha_resolucion")
    private LocalDateTime fechaResolucion;
}
