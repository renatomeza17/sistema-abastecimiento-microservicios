package com.sudab.requerimientos.repository;

import com.sudab.requerimientos.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProveedorRepository extends JpaRepository<Proveedor, UUID> {
    Optional<Proveedor> findByIdUsuario(Long idUsuario);
    Optional<Proveedor> findByRuc(String ruc);
    boolean existsByRuc(String ruc);
    boolean existsByIdUsuario(Long idUsuario);
}
