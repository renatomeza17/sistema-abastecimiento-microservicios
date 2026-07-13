package com.sudabKardex.ms_kardex.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sudabKardex.ms_kardex.Config.FeignClientConfig;
import com.sudabKardex.ms_kardex.DTO.Orden.OrdenResponseDTO;

@FeignClient(name = "ms-ordenes", configuration = FeignClientConfig.class)
public interface OrdenClient {
    
    @GetMapping("/api/ordenes/{id}")
    OrdenResponseDTO obtenerOrdenPorId(@PathVariable("id") Long id);
}
