package com.sudabRecepcion.ms_recepcion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sudabRecepcion.ms_recepcion.model.VerificacionRecepcion;

public interface VerificacionRecepcionRepository extends JpaRepository<VerificacionRecepcion, Long> {
    List<VerificacionRecepcion> findByIdOrden(Long idOrden);
}