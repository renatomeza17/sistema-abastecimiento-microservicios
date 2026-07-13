package com.sudabKardex.ms_kardex.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sudabKardex.ms_kardex.Model.KardexMovimiento;


@Repository
public interface KardexMovimientoRepository extends JpaRepository<KardexMovimiento, Long> {
    List<KardexMovimiento> findByKardexIdKardexOrderByFechaMovimientoDesc(Long IdKardex);
}
