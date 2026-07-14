package com.sudabLogin.ms_login.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sudabLogin.ms_login.dto.RegistrarUsuarioDTO;
import com.sudabLogin.ms_login.model.Usuario;
import com.sudabLogin.ms_login.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario registrar(RegistrarUsuarioDTO request) {
        if (usuarioRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Ya existe un usuario con el username: " + request.getUsername());
        }

        Usuario usuario = new Usuario();
        // Datos de acceso
        usuario.setUsername(request.getUsername());
        usuario.setEmail(request.getEmail());
        // NUNCA se guarda el password en texto plano, siempre encriptado
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));

        // Datos de persona (ahora viven en el mismo registro de usuario)
        usuario.setNombres(request.getNombres());
        usuario.setApellidoPaterno(request.getApellidoPaterno());
        usuario.setApellidoMaterno(request.getApellidoMaterno());
        usuario.setNdocumento(request.getNdocumento());
        usuario.setTipoDocumento(request.getTipoDocumento());
        usuario.setDireccion(request.getDireccion());
        usuario.setFechaNacimiento(request.getFechaNacimiento());
        usuario.setSexo(request.getSexo());
        usuario.setTelefono(request.getTelefono());

        return usuarioRepository.save(usuario);
    }
}