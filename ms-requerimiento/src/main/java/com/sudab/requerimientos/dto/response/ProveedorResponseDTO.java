package com.sudab.requerimientos.dto.response;

import java.util.UUID;

public record ProveedorResponseDTO(
        UUID id,
        String ruc,
        String razonSocial,
        String direccion,
        Long idUsuario
) {}
