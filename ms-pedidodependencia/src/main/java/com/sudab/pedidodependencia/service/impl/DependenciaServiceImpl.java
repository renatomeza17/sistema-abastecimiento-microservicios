package com.sudab.pedidodependencia.service.impl;

import com.sudab.pedidodependencia.dto.request.DependenciaRequestDTO;
import com.sudab.pedidodependencia.dto.response.DependenciaResponseDTO;
import com.sudab.pedidodependencia.exception.BusinessException;
import com.sudab.pedidodependencia.exception.ResourceNotFoundException;
import com.sudab.pedidodependencia.mapper.DependenciaMapper;
import com.sudab.pedidodependencia.model.Dependencia;
import com.sudab.pedidodependencia.repository.DependenciaRepository;
import com.sudab.pedidodependencia.service.DependenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class DependenciaServiceImpl implements DependenciaService {

    private final DependenciaRepository dependenciaRepository;
    private final DependenciaMapper dependenciaMapper;

    @Override
    public DependenciaResponseDTO crear(DependenciaRequestDTO dto) {
        if (dependenciaRepository.findByNombre(dto.nombre()).isPresent()) {
            throw new BusinessException("Ya existe una dependencia con el nombre: " + dto.nombre());
        }
        Dependencia guardada = dependenciaRepository.save(dependenciaMapper.toEntity(dto));
        return dependenciaMapper.toResponseDTO(guardada);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DependenciaResponseDTO> listarTodas() {
        return dependenciaRepository.findAllByOrderByNombreAsc().stream()
                .map(dependenciaMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DependenciaResponseDTO obtenerPorId(UUID id) {
        Dependencia dependencia = dependenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dependencia no encontrada con id: " + id));
        return dependenciaMapper.toResponseDTO(dependencia);
    }
}
