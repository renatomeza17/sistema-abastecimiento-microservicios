package com.sudab.pedidodependencia.repository;

import com.sudab.pedidodependencia.model.Dependencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DependenciaRepository extends JpaRepository<Dependencia, UUID> {
    Optional<Dependencia> findByNombre(String nombre);
    List<Dependencia> findAllByOrderByNombreAsc();
}
