package com.sudabRecepcion.ms_recepcion.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sudabRecepcion.ms_recepcion.client.OrdenFeignClient;
import com.sudabRecepcion.ms_recepcion.dto.OrdenResponseDTO;
import com.sudabRecepcion.ms_recepcion.http.response.PedidoPendienteResponseDTO;
import com.sudabRecepcion.ms_recepcion.model.PedidoPendiente;
import com.sudabRecepcion.ms_recepcion.repository.PedidoPendienteRepository;

import feign.FeignException;

@Service
public class PedidoPendienteService {

    private final PedidoPendienteRepository pedidoPendienteRepository;
    private final OrdenFeignClient ordenFeignClient;

    public PedidoPendienteService(PedidoPendienteRepository pedidoPendienteRepository, OrdenFeignClient ordenFeignClient) {
        this.pedidoPendienteRepository = pedidoPendienteRepository;
        this.ordenFeignClient = ordenFeignClient;
    }

    public PedidoPendienteResponseDTO registrar(Long idOrden, String motivo) {
        OrdenResponseDTO orden;
        try {
            orden = ordenFeignClient.obtenerOrden(idOrden);
        } catch (FeignException.NotFound e) {
            throw new IllegalArgumentException("La orden con id " + idOrden + " no existe en ms-ordenes.");
        } catch (FeignException e) {
            throw new IllegalStateException("No se pudo verificar la orden en ms-ordenes. Intenta más tarde.");
        }

        if (orden == null) {
            throw new IllegalArgumentException("La orden con id " + idOrden + " no existe en ms-ordenes.");
        }

        PedidoPendiente pedido = new PedidoPendiente();
        pedido.setIdOrden(idOrden);
        pedido.setMotivo(motivo);
        pedido.setEstado("PENDIENTE");
        pedido.setFechaRegistro(LocalDateTime.now());
        PedidoPendiente guardado = pedidoPendienteRepository.save(pedido);
        return toResponse(guardado);
    }

    public List<PedidoPendienteResponseDTO> listar() {
        return pedidoPendienteRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public PedidoPendienteResponseDTO resolver(Long id) {
        PedidoPendiente pedido = pedidoPendienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido pendiente no encontrado"));

        if ("RESUELTO".equals(pedido.getEstado())) {
            throw new IllegalStateException("El pedido ya ha sido resuelto previamente.");
        }

        pedido.setEstado("RESUELTO");
        pedido.setFechaResolucion(LocalDateTime.now());
        PedidoPendiente guardado = pedidoPendienteRepository.save(pedido);
        return toResponse(guardado);
    }

    private PedidoPendienteResponseDTO toResponse(PedidoPendiente pedido) {
        return PedidoPendienteResponseDTO.builder()
                .idPedidoPendiente(pedido.getIdPedidoPendiente())
                .idOrden(pedido.getIdOrden())
                .motivo(pedido.getMotivo())
                .observacion(pedido.getObservacion())
                .estado(pedido.getEstado())
                .fechaRegistro(pedido.getFechaRegistro())
                .fechaResolucion(pedido.getFechaResolucion())
                .build();
    }
}
