package com.sudab.pedidodependencia.mapper;

import com.sudab.pedidodependencia.dto.request.DetallePedidoDependenciaRequestDTO;
import com.sudab.pedidodependencia.dto.request.PedidoDependenciaRequestDTO;
import com.sudab.pedidodependencia.dto.response.DetallePedidoDependenciaResponseDTO;
import com.sudab.pedidodependencia.dto.response.PedidoDependenciaResponseDTO;
import com.sudab.pedidodependencia.model.Dependencia;
import com.sudab.pedidodependencia.model.DetallePedidoDependencia;
import com.sudab.pedidodependencia.model.PedidoDependencia;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoDependenciaMapper {

    public PedidoDependencia toEntity(PedidoDependenciaRequestDTO dto, Dependencia dependencia) {
        PedidoDependencia pedido = PedidoDependencia.builder()
                .descripcion(dto.descripcion())
                .idUsuario(dto.idUsuario())
                .dependencia(dependencia)
                .build();

        List<DetallePedidoDependencia> detalles = dto.detalles().stream()
                .map(d -> toDetalleEntity(d, pedido))
                .toList();

        pedido.setDetalles(detalles);
        return pedido;
    }

    public DetallePedidoDependencia toDetalleEntity(DetallePedidoDependenciaRequestDTO dto, PedidoDependencia pedido) {
        return DetallePedidoDependencia.builder()
                .cantidad(dto.cantidad())
                .unidadMedida(dto.unidadMedida())
                .observacionEspecifica(dto.observacionEspecifica())
                .idProducto(dto.idProducto())
                .pedido(pedido)
                .build();
    }

    public PedidoDependenciaResponseDTO toResponseDTO(PedidoDependencia entity) {
        List<DetallePedidoDependenciaResponseDTO> detalles = entity.getDetalles().stream()
                .map(this::toDetalleResponseDTO)
                .toList();

        return new PedidoDependenciaResponseDTO(
                entity.getId(),
                entity.getCodigo(),
                entity.getDescripcion(),
                entity.getEstado(),
                entity.getFechaCreacion(),
                entity.getFechaActualizacion(),
                entity.getIdUsuario(),
                entity.getDependencia().getId(),
                detalles
        );
    }

    public DetallePedidoDependenciaResponseDTO toDetalleResponseDTO(DetallePedidoDependencia entity) {
        return new DetallePedidoDependenciaResponseDTO(
                entity.getId(),
                entity.getCantidad(),
                entity.getUnidadMedida(),
                entity.getObservacionEspecifica(),
                entity.getIdProducto()
        );
    }
}
