package com.sudabLogin.ms_login.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sudabLogin.ms_login.dto.LoginRequestDTO;
import com.sudabLogin.ms_login.dto.LoginResponseDTO;
import com.sudabLogin.ms_login.service.AuthService;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // HU01 - Inicio de sesion
    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        try {
            LoginResponseDTO response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
