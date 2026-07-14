package com.sudab.requerimientos.dto.response;

import java.util.UUID;

import com.sudab.requerimientos.model.enums.EstadoRequerimiento;
import java.time.LocalDateTime;
import java.util.List;

public record RequerimientoResponseDTO(
        UUID id,
        String codigo,
        String descripcion,
        EstadoRequerimiento estado,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaActualizacion,
        UUID idDependencia,
        Long idUsuario,
        List<RequerimientoDetalleResponseDTO> detalles
) {}
