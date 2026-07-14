package com.sudab.requerimientos.repository;

import java.util.UUID;

import com.sudab.requerimientos.model.DetalleProforma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleProformaRepository extends JpaRepository<DetalleProforma, UUID> {
    List<DetalleProforma> findByProformaId(UUID idProforma);
}
