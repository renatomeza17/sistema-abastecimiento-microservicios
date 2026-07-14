package com.sudab.pedidodependencia.repository;

import com.sudab.pedidodependencia.model.DetallePedidoDependencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DetallePedidoDependenciaRepository extends JpaRepository<DetallePedidoDependencia, UUID> {
    List<DetallePedidoDependencia> findByPedidoId(UUID idPedido);
}
