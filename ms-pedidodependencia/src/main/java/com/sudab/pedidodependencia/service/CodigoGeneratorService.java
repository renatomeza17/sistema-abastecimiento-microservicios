package com.sudab.pedidodependencia.service;

import com.sudab.pedidodependencia.model.Contador;
import com.sudab.pedidodependencia.repository.ContadorRepository;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;

@Service
public class CodigoGeneratorService {

    private final ContadorRepository contadorRepository;

    public CodigoGeneratorService(ContadorRepository contadorRepository) {
        this.contadorRepository = contadorRepository;
    }

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
