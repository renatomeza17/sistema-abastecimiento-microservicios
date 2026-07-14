package com.sudab.requerimientos.service.impl;

import java.util.UUID;

import com.sudab.requerimientos.dto.request.RequerimientoRequestDTO;
import com.sudab.requerimientos.dto.response.RequerimientoResponseDTO;
import com.sudab.requerimientos.exception.BusinessException;
import com.sudab.requerimientos.exception.ResourceNotFoundException;
import com.sudab.requerimientos.mapper.RequerimientoMapper;
import com.sudab.requerimientos.model.Requerimiento;
import com.sudab.requerimientos.model.enums.EstadoRequerimiento;
import com.sudab.requerimientos.repository.RequerimientoRepository;
import com.sudab.requerimientos.service.CodigoGeneratorService;
import com.sudab.requerimientos.service.RequerimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RequerimientoServiceImpl implements RequerimientoService {

    private final RequerimientoRepository requerimientoRepository;
    private final RequerimientoMapper requerimientoMapper;
    private final CodigoGeneratorService codigoGeneratorService;

    @Override
    public RequerimientoResponseDTO crear(RequerimientoRequestDTO dto) {
        Requerimiento requerimiento = requerimientoMapper.toEntity(dto);
        requerimiento.setCodigo(codigoGeneratorService.generarCodigo("REQ"));
        requerimiento.setEstado(EstadoRequerimiento.PENDIENTE);

        Requerimiento guardado = requerimientoRepository.save(requerimiento);
        return requerimientoMapper.toResponseDTO(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RequerimientoResponseDTO> listarTodos(EstadoRequerimiento estado) {
        List<Requerimiento> requerimientos = (estado != null)
                ? requerimientoRepository.findByEstado(estado)
                : requerimientoRepository.findAll();

        return requerimientos.stream()
                .map(requerimientoMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RequerimientoResponseDTO obtenerPorId(UUID id) {
        Requerimiento requerimiento = buscarEntidadPorId(id);
        return requerimientoMapper.toResponseDTO(requerimiento);
    }

    @Override
    public RequerimientoResponseDTO cancelar(UUID id) {
        Requerimiento requerimiento = buscarEntidadPorId(id);

        if (requerimiento.getEstado() != EstadoRequerimiento.PENDIENTE) {
            throw new BusinessException(
                    "Solo se puede cancelar un requerimiento en estado PENDIENTE. Estado actual: "
                            + requerimiento.getEstado()
            );
        }

        requerimiento.setEstado(EstadoRequerimiento.CANCELADO);
        Requerimiento actualizado = requerimientoRepository.save(requerimiento);
        return requerimientoMapper.toResponseDTO(actualizado);
    }

    private Requerimiento buscarEntidadPorId(UUID id) {
        return requerimientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Requerimiento no encontrado con id: " + id));
    }
}
