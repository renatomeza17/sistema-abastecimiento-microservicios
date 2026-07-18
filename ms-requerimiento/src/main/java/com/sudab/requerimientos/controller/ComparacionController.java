package com.sudab.requerimientos.controller;

import java.util.UUID;

import com.sudab.requerimientos.dto.request.ElegirMejorProformaRequestDTO;
import com.sudab.requerimientos.dto.response.ProformaResponseDTO;
import com.sudab.requerimientos.dto.response.RequerimientoResponseDTO;
import com.sudab.requerimientos.model.enums.EstadoRequerimiento;
import com.sudab.requerimientos.service.ProformaService;
import com.sudab.requerimientos.service.RequerimientoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comparacion")
public class ComparacionController {

    private final RequerimientoService requerimientoService;
    private final ProformaService proformaService;

    public ComparacionController(RequerimientoService requerimientoService,
            ProformaService proformaService) {
        this.requerimientoService = requerimientoService;
        this.proformaService = proformaService;
    }

    @GetMapping("/requerimientos")
    public ResponseEntity<List<RequerimientoResponseDTO>> listarRequerimientos(
            @RequestParam(required = false) EstadoRequerimiento estado) {
        return ResponseEntity.ok(requerimientoService.listarTodos(estado));
    }

    @GetMapping("/requerimientos/{id}")
    public ResponseEntity<RequerimientoResponseDTO> obtenerDetalle(@PathVariable UUID id) {
        return ResponseEntity.ok(requerimientoService.obtenerPorId(id));
    }

    @GetMapping("/requerimientos/{id}/proformas")
    public ResponseEntity<List<ProformaResponseDTO>> obtenerProformas(@PathVariable UUID id) {
        return ResponseEntity.ok(proformaService.listarPorRequerimiento(id));
    }

    @PostMapping("/requerimientos/{id}/elegir")
    public ResponseEntity<ProformaResponseDTO> elegirMejorProforma(
            @PathVariable UUID id,
            @Valid @RequestBody ElegirMejorProformaRequestDTO dto) {
        return ResponseEntity.ok(proformaService.elegirMejorProforma(id, dto));
    }
}
