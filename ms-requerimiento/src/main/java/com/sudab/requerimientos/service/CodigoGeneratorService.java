package com.sudab.requerimientos.service;

import com.sudab.requerimientos.model.Contador;
import com.sudab.requerimientos.repository.ContadorRepository;
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
                .orElseGet(() -> new Contador(prefijo, anioActual, 0));

        int siguienteNumero = contador.getUltimoNumero() + 1;
        contador.setUltimoNumero(siguienteNumero);
        contadorRepository.save(contador);

        return String.format("%s-%d-%05d", prefijo, anioActual, siguienteNumero);
    }
}
