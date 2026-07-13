package com.sudabLogin.ms_login.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sudabLogin.ms_login.dto.AsignarRolDTO;
import com.sudabLogin.ms_login.model.Rol;
import com.sudabLogin.ms_login.model.Usuario;
import com.sudabLogin.ms_login.service.RolService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RolController {

    private final RolService rolService;

    // HU02 - Listar los roles disponibles (JEFE, DIRECTOR, APOYO)
    @GetMapping
    public ResponseEntity<List<Rol>> listar() {
        return ResponseEntity.ok(rolService.listarRoles());
    }

    // HU02 - Asignar un rol a un usuario
    @PostMapping("/asignar")
    public ResponseEntity<?> asignarRol(@RequestBody AsignarRolDTO request) {
        try {
            Usuario actualizado = rolService.asignarRol(request);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}