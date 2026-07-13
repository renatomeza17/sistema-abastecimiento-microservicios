package com.sudabLogin.ms_login.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sudabLogin.ms_login.dto.RegistrarUsuarioDTO;
import com.sudabLogin.ms_login.model.Usuario;
import com.sudabLogin.ms_login.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    // ADVERTENCIA: este endpoint es solo para uso del Administrador
    // (dar de alta personal), NO es un registro publico/auto-servicio.
    // Todavia no tiene proteccion real por rol via JWT - ver seccion
    // "Filtro de seguridad" pendiente antes de llevar esto a produccion.
    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody RegistrarUsuarioDTO request) {
        try {
            Usuario creado = usuarioService.registrar(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}