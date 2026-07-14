package com.sudab.requerimientos.controller;

import java.util.UUID;

import com.sudab.requerimientos.dto.request.ProformaRequestDTO;
import com.sudab.requerimientos.dto.response.ProformaResponseDTO;
import com.sudab.requerimientos.model.enums.EstadoProforma;
import com.sudab.requerimientos.service.ProformaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints para la subpagina "Proformas": cotizar, ver proformas de un
 * requerimiento y ver proformas de un usuario (proveedor).
 */
@RestController
@RequiredArgsConstructor
public class ProformaController {

    private final ProformaService proformaService;

    // Cotizar: agregar una proforma asociada a un requerimiento
    @PostMapping("/api/requerimientos/{idRequerimiento}/proformas")
    public ResponseEntity<ProformaResponseDTO> cotizar(
            @PathVariable UUID idRequerimiento,
            @Valid @RequestBody ProformaRequestDTO dto) {
        ProformaResponseDTO creada = proformaService.cotizar(idRequerimiento, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    // Listar todas las proformas asociadas a un requerimiento
    @GetMapping("/api/requerimientos/{idRequerimiento}/proformas")
    public ResponseEntity<List<ProformaResponseDTO>> listarPorRequerimiento(
            @PathVariable UUID idRequerimiento) {
        return ResponseEntity.ok(proformaService.listarPorRequerimiento(idRequerimiento));
    }

    // Listar todas las proformas de un usuario (proveedor), filtro opcional por estado
    @GetMapping("/api/usuarios/{idUsuario}/proformas")
    public ResponseEntity<List<ProformaResponseDTO>> listarPorUsuario(
            @PathVariable Long idUsuario,
            @RequestParam(required = false) EstadoProforma estado) {
        return ResponseEntity.ok(proformaService.listarPorUsuario(idUsuario, estado));
    }

    @GetMapping("/api/proformas/{id}")
    public ResponseEntity<ProformaResponseDTO> obtenerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(proformaService.obtenerPorId(id));
    }
}
