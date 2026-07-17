package com.sudab.pedidodependencia.service.impl;

import com.sudab.pedidodependencia.client.ProductoClient;
import com.sudab.pedidodependencia.client.UsuarioClient;
import com.sudab.pedidodependencia.dto.request.PedidoDependenciaRequestDTO;
import com.sudab.pedidodependencia.dto.response.PedidoDependenciaResponseDTO;
import com.sudab.pedidodependencia.exception.BusinessException;
import com.sudab.pedidodependencia.exception.ResourceNotFoundException;
import com.sudab.pedidodependencia.mapper.PedidoDependenciaMapper;
import com.sudab.pedidodependencia.model.Dependencia;
import com.sudab.pedidodependencia.model.PedidoDependencia;
import com.sudab.pedidodependencia.model.enums.EstadoPedidoDependencia;
import com.sudab.pedidodependencia.repository.DependenciaRepository;
import com.sudab.pedidodependencia.repository.PedidoDependenciaRepository;
import com.sudab.pedidodependencia.service.CodigoGeneratorService;
import com.sudab.pedidodependencia.service.PedidoDependenciaService;
import feign.FeignException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PedidoDependenciaServiceImpl implements PedidoDependenciaService {

    private final PedidoDependenciaRepository pedidoDependenciaRepository;
    private final DependenciaRepository dependenciaRepository;
    private final PedidoDependenciaMapper pedidoDependenciaMapper;
    private final CodigoGeneratorService codigoGeneratorService;
    private final UsuarioClient usuarioClient;
    private final ProductoClient productoClient;

    public PedidoDependenciaServiceImpl(PedidoDependenciaRepository pedidoDependenciaRepository,
            DependenciaRepository dependenciaRepository,
            PedidoDependenciaMapper pedidoDependenciaMapper,
            CodigoGeneratorService codigoGeneratorService,
            UsuarioClient usuarioClient,
            ProductoClient productoClient) {
        this.pedidoDependenciaRepository = pedidoDependenciaRepository;
        this.dependenciaRepository = dependenciaRepository;
        this.pedidoDependenciaMapper = pedidoDependenciaMapper;
        this.codigoGeneratorService = codigoGeneratorService;
        this.usuarioClient = usuarioClient;
        this.productoClient = productoClient;
    }

    @Override
    public PedidoDependenciaResponseDTO crear(PedidoDependenciaRequestDTO dto) {
        Dependencia dependencia = dependenciaRepository.findById(dto.idDependencia())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Dependencia no encontrada con id: " + dto.idDependencia()));

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

        PedidoDependencia pedido = pedidoDependenciaMapper.toEntity(dto, dependencia);
        pedido.setCodigo(codigoGeneratorService.generarCodigo("PED"));
        pedido.setEstado(EstadoPedidoDependencia.PENDIENTE);

        PedidoDependencia guardado = pedidoDependenciaRepository.save(pedido);
        return pedidoDependenciaMapper.toResponseDTO(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoDependenciaResponseDTO> listarPorUsuario(Long idUsuario, EstadoPedidoDependencia estado) {
        List<PedidoDependencia> pedidos = (estado != null)
                ? pedidoDependenciaRepository.findByIdUsuarioAndEstado(idUsuario, estado)
                : pedidoDependenciaRepository.findByIdUsuario(idUsuario);

        return pedidos.stream()
                .map(pedidoDependenciaMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PedidoDependenciaResponseDTO obtenerPorId(UUID id) {
        return pedidoDependenciaMapper.toResponseDTO(buscarEntidadPorId(id));
    }

    @Override
    public PedidoDependenciaResponseDTO cancelar(UUID id) {
        PedidoDependencia pedido = buscarEntidadPorId(id);

        if (pedido.getEstado() != EstadoPedidoDependencia.PENDIENTE) {
            throw new BusinessException(
                    "Solo se puede cancelar un pedido en estado PENDIENTE. Estado actual: "
                            + pedido.getEstado()
            );
        }

        pedido.setEstado(EstadoPedidoDependencia.CANCELADO);
        PedidoDependencia actualizado = pedidoDependenciaRepository.save(pedido);
        return pedidoDependenciaMapper.toResponseDTO(actualizado);
    }

    private PedidoDependencia buscarEntidadPorId(UUID id) {
        return pedidoDependenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido de dependencia no encontrado con id: " + id));
    }
}
