package com.sudab.pedidodependencia.dto.external;

public record UsuarioResponseDTO(
        Long idUsuario,
        String username,
        String nombres,
        String apellidoPaterno,
        String apellidoMaterno,
        String email,
        Boolean activo
) {}
