package com.sudab.requerimientos.service.impl;

import java.util.UUID;

import com.sudab.requerimientos.client.ProductoClient;
import com.sudab.requerimientos.client.UsuarioClient;
import com.sudab.requerimientos.dto.request.ElegirMejorProformaRequestDTO;
import com.sudab.requerimientos.dto.request.ProformaRequestDTO;
import com.sudab.requerimientos.dto.response.ProformaResponseDTO;
import com.sudab.requerimientos.exception.BusinessException;
import com.sudab.requerimientos.exception.ResourceNotFoundException;
import com.sudab.requerimientos.mapper.ProformaMapper;
import com.sudab.requerimientos.model.Proforma;
import com.sudab.requerimientos.model.Requerimiento;
import com.sudab.requerimientos.model.enums.EstadoProforma;
import com.sudab.requerimientos.model.enums.EstadoRequerimiento;
import com.sudab.requerimientos.repository.ProformaRepository;
import com.sudab.requerimientos.repository.RequerimientoRepository;
import com.sudab.requerimientos.service.CodigoGeneratorService;
import com.sudab.requerimientos.service.ProformaService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProformaServiceImpl implements ProformaService {

    private final ProformaRepository proformaRepository;
    private final RequerimientoRepository requerimientoRepository;
    private final ProformaMapper proformaMapper;
    private final CodigoGeneratorService codigoGeneratorService;
    private final UsuarioClient usuarioClient;
    private final ProductoClient productoClient;

    @Override
    public ProformaResponseDTO cotizar(UUID idRequerimiento, ProformaRequestDTO dto) {
        Requerimiento requerimiento = requerimientoRepository.findById(idRequerimiento)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Requerimiento no encontrado con id: " + idRequerimiento));

        if (requerimiento.getEstado() != EstadoRequerimiento.PENDIENTE) {
            throw new BusinessException(
                    "Solo se pueden agregar proformas a un requerimiento PENDIENTE. Estado actual: "
                            + requerimiento.getEstado()
            );
        }

        try {
            usuarioClient.obtenerUsuarioPorId(dto.idUsuario());
        } catch (FeignException e) {
            throw new ResourceNotFoundException("Usuario no encontrado con id: " + dto.idUsuario());
        }

        for (var detalle : dto.detalles()) {
            try {
                productoClient.obtenerProductoPorId(detalle.idProducto());
            } catch (FeignException e) {
                throw new ResourceNotFoundException("Producto no encontrado con id: " + detalle.idProducto());
            }
        }

        Proforma proforma = proformaMapper.toEntity(dto, requerimiento);
        proforma.setCodigo(codigoGeneratorService.generarCodigo("PROF"));
        proforma.setEstado(EstadoProforma.PENDIENTE);

        Proforma guardada = proformaRepository.save(proforma);
        return proformaMapper.toResponseDTO(guardada);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProformaResponseDTO> listarPorRequerimiento(UUID idRequerimiento) {
        return proformaRepository.findByRequerimientoId(idRequerimiento).stream()
                .map(proformaMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProformaResponseDTO> listarPorUsuario(Long idUsuario, EstadoProforma estado) {
        List<Proforma> proformas = (estado != null)
                ? proformaRepository.findByIdUsuarioAndEstado(idUsuario, estado)
                : proformaRepository.findByIdUsuario(idUsuario);

        return proformas.stream()
                .map(proformaMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProformaResponseDTO obtenerPorId(UUID id) {
        return proformaMapper.toResponseDTO(buscarEntidadPorId(id));
    }

    @Override
    public ProformaResponseDTO elegirMejorProforma(UUID idRequerimiento, ElegirMejorProformaRequestDTO dto) {
        Requerimiento requerimiento = requerimientoRepository.findById(idRequerimiento)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Requerimiento no encontrado con id: " + idRequerimiento));

        List<Proforma> proformas = proformaRepository.findByRequerimientoId(idRequerimiento);

        Proforma ganadora = proformas.stream()
                .filter(p -> p.getId().equals(dto.idProformaGanadora()))
                .findFirst()
                .orElseThrow(() -> new BusinessException(
                        "La proforma indicada no pertenece a este requerimiento"));

        for (Proforma proforma : proformas) {
            if (proforma.getId().equals(ganadora.getId())) {
                proforma.setEstado(EstadoProforma.APROBADO);
            } else {
                proforma.setEstado(EstadoProforma.RECHAZADO);
            }
        }
        proformaRepository.saveAll(proformas);

        requerimiento.setEstado(EstadoRequerimiento.FINALIZADO);
        requerimientoRepository.save(requerimiento);

        return proformaMapper.toResponseDTO(ganadora);
    }

    private Proforma buscarEntidadPorId(UUID id) {
        return proformaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proforma no encontrada con id: " + id));
    }
}
