package com.sudabLogin.ms_login.dto;

import lombok.Data;

@Data
public class AsignarRolDTO {
    private Long idUsuario;
    private String nombreRol; // JEFE, DIRECTOR, APOYO
}