package com.sudab.requerimientos.repository;

import java.util.UUID;

import com.sudab.requerimientos.model.Requerimiento;
import com.sudab.requerimientos.model.enums.EstadoRequerimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RequerimientoRepository extends JpaRepository<Requerimiento, UUID> {

    Optional<Requerimiento> findByCodigo(String codigo);

    List<Requerimiento> findByEstado(EstadoRequerimiento estado);

    List<Requerimiento> findByIdUsuario(Long idUsuario);

    List<Requerimiento> findByIdDependencia(UUID idDependencia);

    boolean existsByCodigo(String codigo);
}
