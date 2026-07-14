package com.sudab.pedidodependencia.service;

import com.sudab.pedidodependencia.dto.request.DependenciaRequestDTO;
import com.sudab.pedidodependencia.dto.response.DependenciaResponseDTO;

import java.util.List;
import java.util.UUID;

public interface DependenciaService {
    DependenciaResponseDTO crear(DependenciaRequestDTO dto);
    List<DependenciaResponseDTO> listarTodas();
    DependenciaResponseDTO obtenerPorId(UUID id);
}
