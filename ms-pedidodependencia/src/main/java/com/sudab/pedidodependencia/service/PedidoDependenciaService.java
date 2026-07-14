package com.sudab.pedidodependencia.service;

import com.sudab.pedidodependencia.dto.request.PedidoDependenciaRequestDTO;
import com.sudab.pedidodependencia.dto.response.PedidoDependenciaResponseDTO;
import com.sudab.pedidodependencia.model.enums.EstadoPedidoDependencia;

import java.util.List;
import java.util.UUID;

public interface PedidoDependenciaService {

    PedidoDependenciaResponseDTO crear(PedidoDependenciaRequestDTO dto);

    List<PedidoDependenciaResponseDTO> listarPorUsuario(Long idUsuario, EstadoPedidoDependencia estado);

    PedidoDependenciaResponseDTO obtenerPorId(UUID id);

    PedidoDependenciaResponseDTO cancelar(UUID id);
}
