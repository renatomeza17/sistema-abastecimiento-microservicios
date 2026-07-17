package com.sudab.pedidodependencia.repository;

import com.sudab.pedidodependencia.model.PedidoDependencia;
import com.sudab.pedidodependencia.model.enums.EstadoPedidoDependencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PedidoDependenciaRepository extends JpaRepository<PedidoDependencia, UUID> {

    Optional<PedidoDependencia> findByCodigo(String codigo);

    List<PedidoDependencia> findByIdUsuario(Long idUsuario);

    List<PedidoDependencia> findByIdUsuarioAndEstado(Long idUsuario, EstadoPedidoDependencia estado);

    List<PedidoDependencia> findByDependenciaId(UUID idDependencia);

    boolean existsByCodigo(String codigo);
}
