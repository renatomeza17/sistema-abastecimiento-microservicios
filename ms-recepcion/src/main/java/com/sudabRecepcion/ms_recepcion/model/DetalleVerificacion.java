package com.sudabRecepcion.ms_recepcion.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detalle_verificacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVerificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_verificacion")
    private Long idDetalleVerificacion;

    @ManyToOne
    @JoinColumn(name = "id_verificacion", nullable = false)
    private VerificacionRecepcion verificacionRecepcion;

    // Referencia logica al item de la OC en ms-ordenes (orden_compra_detalles),
    // no es FK real porque vive en otra base de datos.
    @Column(name = "id_orden_detalle", nullable = false)
    private Long idOrdenDetalle;

    @Column(name = "nombre_producto")
    private String nombreProducto;

    @Column(name = "cantidad_esperada")
    private Integer cantidadEsperada;

    // El checkbox de validacion por cada producto (HU08)
    @Column(nullable = false)
    private Boolean verificado = false;

    private String observacion;
}