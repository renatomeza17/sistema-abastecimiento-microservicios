package com.sudabKardex.ms_kardex.Client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sudabKardex.ms_kardex.Config.FeignClientConfig;
import com.sudabKardex.ms_kardex.DTO.Producto.ProductoResponseDTO;

@FeignClient(name = "ms-productos", configuration = FeignClientConfig.class)
public interface ProductoClient {

    
    @GetMapping("/api/productos/{id}")
    ProductoResponseDTO obtenerProductoPorId(@PathVariable("id") Long id);

    
    @GetMapping("/api/productos/productos-disponibles")
    List<ProductoResponseDTO> obtenerProductosSinKardex();

}
