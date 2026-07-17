package com.sudab.requerimientos.client;

import com.sudab.requerimientos.dto.external.DependenciaResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "ms-pedidodependencia")
public interface DependenciaClient {

    @GetMapping("/api/dependencias/{id}")
    DependenciaResponseDTO obtenerDependenciaPorId(@PathVariable("id") UUID id);
}
