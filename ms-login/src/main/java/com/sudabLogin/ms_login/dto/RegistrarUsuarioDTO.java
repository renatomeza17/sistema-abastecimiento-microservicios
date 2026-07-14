package com.sudabLogin.ms_login.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class RegistrarUsuarioDTO {
    // Datos de acceso
    private String username;
    private String password;
    private String email;

    // Datos de persona
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String ndocumento;
    private String tipoDocumento;
    private String direccion;
    private LocalDate fechaNacimiento;
    private String sexo;
    private String telefono;
}