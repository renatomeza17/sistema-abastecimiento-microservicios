package com.sudabOrdenes.ms_ordenes.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orden_compra_detalles")
public class OrdenCompraDetalle {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrdenDetalle;

    @ManyToOne
    @JoinColumn(name = "orden_compra_id")
    private OrdenCompra ordenCompra;

    // @ManyToOne
    // @JoinColumn(name = "producto_id")
    // private Producto producto;

    // Relación externa (SE CAMBIA A ID, porque el Producto vive en ms-kardex)
    @Column(name = "id_producto")
    private Long idProducto;


    // Campo de apoyo opcional para no tener que consultar a Kardex a cada rato
    private String nombreProducto;

    
    @Column(name="cantidad",nullable = false)
    private Integer cantidad;

    @Column(name="precio_unitario",nullable = false)
    private Double precioUnitario;



    
    // Método útil para lógica de negocio o reportes
    public Double getSubtotal() {
        return this.cantidad * this.precioUnitario;
    }
    

}
