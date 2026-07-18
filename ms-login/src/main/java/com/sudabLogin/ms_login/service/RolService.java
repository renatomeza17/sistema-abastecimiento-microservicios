package com.sudabLogin.ms_login.service;

import org.springframework.stereotype.Service;

import com.sudabLogin.ms_login.dto.AsignarRolDTO;
import com.sudabLogin.ms_login.model.Rol;
import com.sudabLogin.ms_login.model.Usuario;
import com.sudabLogin.ms_login.repository.RolRepository;
import com.sudabLogin.ms_login.repository.UsuarioRepository;

@Service
public class RolService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    public RolService(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    public Usuario asignarRol(AsignarRolDTO request) {
        Usuario usuario = usuarioRepository.findById(request.getIdUsuario())
                .orElseThrow(() -> new IllegalArgumentException(
                        "No existe el usuario con id " + request.getIdUsuario()));

        Rol rol = rolRepository.findByNombre(request.getNombreRol().toUpperCase())
                .orElseThrow(() -> new IllegalArgumentException(
                        "El rol '" + request.getNombreRol() + "' no existe. Usa: JEFE, DIRECTOR o APOYO"));

        usuario.getRoles().add(rol);
        return usuarioRepository.save(usuario);
    }

    public java.util.List<Rol> listarRoles() {
        return rolRepository.findAll();
    }
}
