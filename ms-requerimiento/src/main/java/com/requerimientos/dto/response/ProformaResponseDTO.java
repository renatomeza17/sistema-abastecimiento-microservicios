package com.sudab.requerimientos.dto.response;

import java.util.UUID;

import com.sudab.requerimientos.model.enums.EstadoProforma;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ProformaResponseDTO(
        UUID id,
        String codigo,
        EstadoProforma estado,
        LocalDateTime fechaRecepcion,
        LocalDateTime fechaActualizacion,
        BigDecimal precioTotal,
        Long idUsuario,
        UUID idRequerimiento,
        List<DetalleProformaResponseDTO> detalles
) {}
