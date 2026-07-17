package com.sudab.pedidodependencia.controller;

import com.sudab.pedidodependencia.dto.request.DependenciaRequestDTO;
import com.sudab.pedidodependencia.dto.response.DependenciaResponseDTO;
import com.sudab.pedidodependencia.service.DependenciaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * CRUD basico de la master data de dependencias. Este microservicio es el
 * dueno de esta tabla; otros servicios (ej. ms-requerimiento) solo guardan
 * el UUID de la dependencia como referencia externa.
 */
@RestController
@RequestMapping("/api/dependencias")
public class DependenciaController {

    private final DependenciaService dependenciaService;

    public DependenciaController(DependenciaService dependenciaService) {
        this.dependenciaService = dependenciaService;
    }

    @PostMapping
    public ResponseEntity<DependenciaResponseDTO> crear(@Valid @RequestBody DependenciaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dependenciaService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<DependenciaResponseDTO>> listar() {
        return ResponseEntity.ok(dependenciaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DependenciaResponseDTO> obtenerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(dependenciaService.obtenerPorId(id));
    }
}
