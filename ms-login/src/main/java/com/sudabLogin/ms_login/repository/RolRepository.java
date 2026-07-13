package com.sudabLogin.ms_login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sudabLogin.ms_login.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByNombre(String nombre);
}