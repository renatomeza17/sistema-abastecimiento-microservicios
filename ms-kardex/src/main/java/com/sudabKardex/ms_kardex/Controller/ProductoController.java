package com.sudabKardex.ms_kardex.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudabKardex.ms_kardex.DTO.Producto.ProductoResponseDTO;
import com.sudabKardex.ms_kardex.Service.ProductoService;


import lombok.RequiredArgsConstructor;

@RestController

@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    // Endpoint para nutrir el combobox/select de Angular
    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> obtenerCatalogo() {
        List<ProductoResponseDTO> catalogo = productoService.listarProductosActivos();
        return ResponseEntity.ok(catalogo);
    }

    
}
