package com.sudab.pedidodependencia.repository;

import com.sudab.pedidodependencia.model.Contador;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ContadorRepository extends JpaRepository<Contador, Contador.ContadorId> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM Contador c WHERE c.prefijo = :prefijo AND c.anio = :anio")
    Optional<Contador> buscarParaActualizar(@Param("prefijo") String prefijo, @Param("anio") Integer anio);
}
