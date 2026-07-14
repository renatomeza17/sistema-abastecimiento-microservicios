package com.sudab.pedidodependencia.service;

import com.sudab.pedidodependencia.model.Contador;
import com.sudab.pedidodependencia.repository.ContadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;

@Service
@RequiredArgsConstructor
public class CodigoGeneratorService {

    private final ContadorRepository contadorRepository;

    /**
     * Genera un codigo correlativo por anio y prefijo, ej: PED-2026-00001.
     * Usa bloqueo pesimista sobre la fila del contador para evitar codigos
     * duplicados ante solicitudes concurrentes. Transaccion propia y corta
     * para no extender el bloqueo mas de lo necesario.
     *
     * CockroachDB usa SERIALIZABLE por defecto: bajo contencion puede
     * abortar con un error retryable (40001), por eso @Retryable.
     */
    @Retryable(
            retryFor = CannotAcquireLockException.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 100, multiplier = 2)
    )
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String generarCodigo(String prefijo) {
        int anioActual = Year.now().getValue();

        Contador contador = contadorRepository.buscarParaActualizar(prefijo, anioActual)
                .orElseGet(() -> Contador.builder()
                        .prefijo(prefijo)
                        .anio(anioActual)
                        .ultimoNumero(0)
                        .build());

        int siguienteNumero = contador.getUltimoNumero() + 1;
        contador.setUltimoNumero(siguienteNumero);
        contadorRepository.save(contador);

        return String.format("%s-%d-%05d", prefijo, anioActual, siguienteNumero);
    }
}
