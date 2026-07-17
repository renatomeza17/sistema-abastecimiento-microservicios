package com.sudabLogin.ms_login.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sudabLogin.ms_login.config.JwtUtil;
import com.sudabLogin.ms_login.dto.LoginRequestDTO;
import com.sudabLogin.ms_login.dto.LoginResponseDTO;
import com.sudabLogin.ms_login.model.Rol;
import com.sudabLogin.ms_login.model.Usuario;
import com.sudabLogin.ms_login.repository.UsuarioRepository;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponseDTO login(LoginRequestDTO request) {
        Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Usuario o contraseña incorrectos"));

        // Compara el password en texto plano que mandaron contra el hash guardado
        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new IllegalArgumentException("Usuario o contraseña incorrectos");
        }

        List<String> nombresRoles = usuario.getRoles().stream()
                .map(Rol::getNombre)
                .toList();

        String token = jwtUtil.generarToken(usuario.getUsername(), nombresRoles);

        // Los datos de persona ahora viven directamente en Usuario (ya no hay entidad Persona separada)
        String nombreCompleto = usuario.getNombres() + " " + usuario.getApellidoPaterno();

        return new LoginResponseDTO(token, usuario.getUsername(), nombreCompleto, nombresRoles);
    }
}
