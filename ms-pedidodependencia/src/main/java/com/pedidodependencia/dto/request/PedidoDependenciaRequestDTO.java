package com.sudab.pedidodependencia.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.UUID;

public record PedidoDependenciaRequestDTO(
        @NotBlank(message = "La descripcion es obligatoria")
        String descripcion,

        @NotNull(message = "El usuario es obligatorio")
        Long idUsuario,

        @NotNull(message = "La dependencia es obligatoria")
        UUID idDependencia,

        @NotEmpty(message = "Debe incluir al menos un producto en el pedido")
        @Valid
        List<DetallePedidoDependenciaRequestDTO> detalles
) {}
