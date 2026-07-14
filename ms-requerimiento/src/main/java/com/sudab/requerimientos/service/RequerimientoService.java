package com.sudab.requerimientos.service;

import java.util.UUID;

import com.sudab.requerimientos.dto.request.RequerimientoRequestDTO;
import com.sudab.requerimientos.dto.response.RequerimientoResponseDTO;
import com.sudab.requerimientos.model.enums.EstadoRequerimiento;

import java.util.List;

public interface RequerimientoService {

    RequerimientoResponseDTO crear(RequerimientoRequestDTO dto);

    List<RequerimientoResponseDTO> listarTodos(EstadoRequerimiento estado);

    RequerimientoResponseDTO obtenerPorId(UUID id);

    RequerimientoResponseDTO cancelar(UUID id);
}
