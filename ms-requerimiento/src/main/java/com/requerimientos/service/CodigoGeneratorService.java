package com.sudab.requerimientos.service;

import com.sudab.requerimientos.model.Contador;
import com.sudab.requerimientos.repository.ContadorRepository;
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
     * Genera un codigo correlativo por anio y prefijo, ej: REQ-2026-00001.
     * Usa un bloqueo pesimista sobre la fila del contador para evitar
     * codigos duplicados si llegan dos solicitudes al mismo tiempo.
     * Se ejecuta en una transaccion propia y corta para no bloquear
     * el resto de la operacion de guardado por mucho tiempo.
     *
     * CockroachDB corre en aislamiento SERIALIZABLE: bajo contencion real
     * puede abortar la transaccion con un error retryable (SQLSTATE 40001)
     * aunque la logica sea correcta. @Retryable reintenta automaticamente
     * en vez de propagar el error al usuario en el primer intento.
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
