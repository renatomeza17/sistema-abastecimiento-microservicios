package com.sudab.requerimientos.service;

import java.util.UUID;

import com.sudab.requerimientos.dto.request.ElegirMejorProformaRequestDTO;
import com.sudab.requerimientos.dto.request.ProformaRequestDTO;
import com.sudab.requerimientos.dto.response.ProformaResponseDTO;
import com.sudab.requerimientos.model.enums.EstadoProforma;

import java.util.List;

public interface ProformaService {

    ProformaResponseDTO cotizar(UUID idRequerimiento, ProformaRequestDTO dto);

    List<ProformaResponseDTO> listarPorRequerimiento(UUID idRequerimiento);

    List<ProformaResponseDTO> listarPorUsuario(Long idUsuario, EstadoProforma estado);

    ProformaResponseDTO obtenerPorId(UUID id);

    ProformaResponseDTO elegirMejorProforma(UUID idRequerimiento, ElegirMejorProformaRequestDTO dto);
}
