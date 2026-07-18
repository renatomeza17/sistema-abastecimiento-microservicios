package com.sudabLogin.ms_login.dto;

import java.util.List;
import java.util.Objects;

public class LoginResponseDTO {
    private String token;
    private String username;
    private String nombreCompleto;
    private List<String> roles;
    private List<ModuloDTO> modulos;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(String token, String username, String nombreCompleto, List<String> roles, List<ModuloDTO> modulos) {
        this.token = token;
        this.username = username;
        this.nombreCompleto = nombreCompleto;
        this.roles = roles;
        this.modulos = modulos;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<ModuloDTO> getModulos() {
        return modulos;
    }

    public void setModulos(List<ModuloDTO> modulos) {
        this.modulos = modulos;
    }

    public record ModuloDTO(String descripcion, String url) {}

    @Override
    public String toString() {
        return "LoginResponseDTO{" +
                "token='" + token + '\'' +
                ", username='" + username + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", roles=" + roles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginResponseDTO that = (LoginResponseDTO) o;
        return Objects.equals(token, that.token) &&
                Objects.equals(username, that.username) &&
                Objects.equals(nombreCompleto, that.nombreCompleto) &&
                Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, username, nombreCompleto, roles);
    }
}
