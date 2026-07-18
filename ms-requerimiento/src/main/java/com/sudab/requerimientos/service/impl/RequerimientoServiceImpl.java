package com.sudab.requerimientos.service.impl;

import java.util.UUID;

import com.sudab.requerimientos.client.DependenciaClient;
import com.sudab.requerimientos.client.ProductoClient;
import com.sudab.requerimientos.client.UsuarioClient;
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
import feign.FeignException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RequerimientoServiceImpl implements RequerimientoService {

    private final RequerimientoRepository requerimientoRepository;
    private final RequerimientoMapper requerimientoMapper;
    private final CodigoGeneratorService codigoGeneratorService;
    private final UsuarioClient usuarioClient;
    private final DependenciaClient dependenciaClient;
    private final ProductoClient productoClient;

    public RequerimientoServiceImpl(RequerimientoRepository requerimientoRepository,
            RequerimientoMapper requerimientoMapper,
            CodigoGeneratorService codigoGeneratorService,
            UsuarioClient usuarioClient,
            DependenciaClient dependenciaClient,
            ProductoClient productoClient) {
        this.requerimientoRepository = requerimientoRepository;
        this.requerimientoMapper = requerimientoMapper;
        this.codigoGeneratorService = codigoGeneratorService;
        this.usuarioClient = usuarioClient;
        this.dependenciaClient = dependenciaClient;
        this.productoClient = productoClient;
    }

    @Override
    public RequerimientoResponseDTO crear(RequerimientoRequestDTO dto) {
        try {
            usuarioClient.obtenerUsuarioPorId(dto.idUsuario());
        } catch (FeignException e) {
            throw new ResourceNotFoundException("Usuario no encontrado con id: " + dto.idUsuario());
        }

        try {
            dependenciaClient.obtenerDependenciaPorId(dto.idDependencia());
        } catch (FeignException e) {
            throw new ResourceNotFoundException("Dependencia no encontrada con id: " + dto.idDependencia());
        }

        for (var detalle : dto.detalles()) {
            try {
                productoClient.obtenerProductoPorId(detalle.idProducto());
            } catch (FeignException e) {
                throw new ResourceNotFoundException("Producto no encontrado con id: " + detalle.idProducto());
            }
        }

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
