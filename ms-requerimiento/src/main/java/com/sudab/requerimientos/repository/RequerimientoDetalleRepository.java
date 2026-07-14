package com.sudab.requerimientos.repository;

import java.util.UUID;

import com.sudab.requerimientos.model.RequerimientoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequerimientoDetalleRepository extends JpaRepository<RequerimientoDetalle, UUID> {
    List<RequerimientoDetalle> findByRequerimientoId(UUID idRequerimiento);
}
