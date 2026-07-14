package com.sudab.requerimientos.service;

import com.sudab.requerimientos.dto.request.ProveedorRequestDTO;
import com.sudab.requerimientos.dto.response.ProveedorResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ProveedorService {
    ProveedorResponseDTO crear(ProveedorRequestDTO dto);
    List<ProveedorResponseDTO> listarTodos();
    ProveedorResponseDTO obtenerPorId(UUID id);
    ProveedorResponseDTO obtenerPorIdUsuario(Long idUsuario);
}
