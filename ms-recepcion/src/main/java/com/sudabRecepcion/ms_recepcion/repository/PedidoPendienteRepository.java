package com.sudabRecepcion.ms_recepcion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sudabRecepcion.ms_recepcion.model.PedidoPendiente;

public interface PedidoPendienteRepository extends JpaRepository<PedidoPendiente, Long> {

    List<PedidoPendiente> findByEstado(String estado);
}
