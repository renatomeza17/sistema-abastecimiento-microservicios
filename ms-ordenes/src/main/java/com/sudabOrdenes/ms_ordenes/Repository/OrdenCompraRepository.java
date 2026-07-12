package com.sudabOrdenes.ms_ordenes.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sudabOrdenes.ms_ordenes.Model.OrdenCompra;

public interface OrdenCompraRepository extends JpaRepository<OrdenCompra,Long> {
    Optional<OrdenCompra> findByCodigo(String codigo);

    // Optional<OrdenCompra> findByProveedorIdProveedor(Long idProveedor);

    Optional<OrdenCompra> findByIdProveedor(Long idProveedor);

     List<OrdenCompra> findByEstado(String estado);

     List<OrdenCompra> findByEstadoIn(List<String> estados);

}
