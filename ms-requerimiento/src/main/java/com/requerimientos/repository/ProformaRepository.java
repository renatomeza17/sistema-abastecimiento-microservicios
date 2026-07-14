package com.sudab.requerimientos.repository;

import java.util.UUID;

import com.sudab.requerimientos.model.Proforma;
import com.sudab.requerimientos.model.enums.EstadoProforma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProformaRepository extends JpaRepository<Proforma, UUID> {

    Optional<Proforma> findByCodigo(String codigo);

    List<Proforma> findByRequerimientoId(UUID idRequerimiento);

    List<Proforma> findByIdUsuario(Long idUsuario);

    List<Proforma> findByIdUsuarioAndEstado(Long idUsuario, EstadoProforma estado);
}
