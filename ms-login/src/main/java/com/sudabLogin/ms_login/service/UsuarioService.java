package com.sudabLogin.ms_login.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sudabLogin.ms_login.dto.RegistrarUsuarioDTO;
import com.sudabLogin.ms_login.model.Persona;
import com.sudabLogin.ms_login.model.Usuario;
import com.sudabLogin.ms_login.repository.PersonaRepository;
import com.sudabLogin.ms_login.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PersonaRepository personaRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario registrar(RegistrarUsuarioDTO request) {
        if (usuarioRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Ya existe un usuario con el username: " + request.getUsername());
        }

        Persona persona = new Persona();
        persona.setNombres(request.getNombres());
        persona.setApellidoPaterno(request.getApellidoPaterno());
        persona.setApellidoMaterno(request.getApellidoMaterno());
        persona.setNdocumento(request.getNdocumento());
        persona.setTipoDocumento(request.getTipoDocumento());
        persona.setDireccion(request.getDireccion());
        persona.setFechaNacimiento(request.getFechaNacimiento());
        persona.setSexo(request.getSexo());
        persona.setTelefono(request.getTelefono());
        persona = personaRepository.save(persona);

        Usuario usuario = new Usuario();
        usuario.setUsername(request.getUsername());
        usuario.setEmail(request.getEmail());
        // NUNCA se guarda el password en texto plano, siempre encriptado
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setPersona(persona);

        return usuarioRepository.save(usuario);
    }
}