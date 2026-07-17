package com.sudab.requerimientos.service.impl;

import com.sudab.requerimientos.client.UsuarioClient;
import com.sudab.requerimientos.dto.request.ProveedorRequestDTO;
import com.sudab.requerimientos.dto.response.ProveedorResponseDTO;
import com.sudab.requerimientos.exception.BusinessException;
import com.sudab.requerimientos.exception.ResourceNotFoundException;
import com.sudab.requerimientos.mapper.ProveedorMapper;
import com.sudab.requerimientos.model.Proveedor;
import com.sudab.requerimientos.repository.ProveedorRepository;
import com.sudab.requerimientos.service.ProveedorService;
import feign.FeignException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository proveedorRepository;
    private final ProveedorMapper proveedorMapper;
    private final UsuarioClient usuarioClient;

    public ProveedorServiceImpl(ProveedorRepository proveedorRepository,
            ProveedorMapper proveedorMapper,
            UsuarioClient usuarioClient) {
        this.proveedorRepository = proveedorRepository;
        this.proveedorMapper = proveedorMapper;
        this.usuarioClient = usuarioClient;
    }

    @Override
    public ProveedorResponseDTO crear(ProveedorRequestDTO dto) {
        try {
            usuarioClient.obtenerUsuarioPorId(dto.idUsuario());
        } catch (FeignException e) {
            throw new ResourceNotFoundException("Usuario no encontrado con id: " + dto.idUsuario());
        }

        if (proveedorRepository.existsByRuc(dto.ruc())) {
            throw new BusinessException("Ya existe un proveedor con el RUC: " + dto.ruc());
        }
        if (proveedorRepository.existsByIdUsuario(dto.idUsuario())) {
            throw new BusinessException("El usuario indicado ya tiene un perfil de proveedor");
        }

        Proveedor guardado = proveedorRepository.save(proveedorMapper.toEntity(dto));
        return proveedorMapper.toResponseDTO(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProveedorResponseDTO> listarTodos() {
        return proveedorRepository.findAll().stream()
                .map(proveedorMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProveedorResponseDTO obtenerPorId(UUID id) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con id: " + id));
        return proveedorMapper.toResponseDTO(proveedor);
    }

    @Override
    @Transactional(readOnly = true)
    public ProveedorResponseDTO obtenerPorIdUsuario(Long idUsuario) {
        Proveedor proveedor = proveedorRepository.findByIdUsuario(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "El usuario " + idUsuario + " no tiene un perfil de proveedor"));
        return proveedorMapper.toResponseDTO(proveedor);
    }
}
