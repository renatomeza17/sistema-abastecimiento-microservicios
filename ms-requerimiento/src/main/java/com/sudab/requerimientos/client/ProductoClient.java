package com.sudab.requerimientos.client;

import com.sudab.requerimientos.dto.external.ProductoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-kardex")
public interface ProductoClient {

    @GetMapping("/api/productos/{id}")
    ProductoResponseDTO obtenerProductoPorId(@PathVariable("id") Long id);
}
