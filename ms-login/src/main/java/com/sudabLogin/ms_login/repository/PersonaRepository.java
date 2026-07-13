package com.sudabLogin.ms_login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sudabLogin.ms_login.model.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}