package com.sudab.pedidodependencia.dto.response;

import com.sudab.pedidodependencia.model.enums.EstadoPedidoDependencia;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record PedidoDependenciaResponseDTO(
        UUID id,
        String codigo,
        String descripcion,
        EstadoPedidoDependencia estado,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaActualizacion,
        Long idUsuario,
        UUID idDependencia,
        List<DetallePedidoDependenciaResponseDTO> detalles
) {}
