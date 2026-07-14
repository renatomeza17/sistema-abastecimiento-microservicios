package com.sudab.requerimientos.controller;

import java.util.UUID;

import com.sudab.requerimientos.dto.request.RequerimientoRequestDTO;
import com.sudab.requerimientos.dto.response.RequerimientoResponseDTO;
import com.sudab.requerimientos.model.enums.EstadoRequerimiento;
import com.sudab.requerimientos.service.RequerimientoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints para la subpagina "Requerimientos": crear, listar, ver detalle
 * y cancelar.
 */
@RestController
@RequestMapping("/api/requerimientos")
@RequiredArgsConstructor
public class RequerimientoController {

    private final RequerimientoService requerimientoService;

    @PostMapping
    public ResponseEntity<RequerimientoResponseDTO> crear(@Valid @RequestBody RequerimientoRequestDTO dto) {
        RequerimientoResponseDTO creado = requerimientoService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<RequerimientoResponseDTO>> listar(
            @RequestParam(required = false) EstadoRequerimiento estado) {
        return ResponseEntity.ok(requerimientoService.listarTodos(estado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequerimientoResponseDTO> obtenerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(requerimientoService.obtenerPorId(id));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<RequerimientoResponseDTO> cancelar(@PathVariable UUID id) {
        return ResponseEntity.ok(requerimientoService.cancelar(id));
    }
}
