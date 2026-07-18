package com.sudab.pedidodependencia.controller;

import com.sudab.pedidodependencia.dto.request.PedidoDependenciaRequestDTO;
import com.sudab.pedidodependencia.dto.response.PedidoDependenciaResponseDTO;
import com.sudab.pedidodependencia.model.enums.EstadoPedidoDependencia;
import com.sudab.pedidodependencia.service.PedidoDependenciaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PedidoDependenciaController {

    private final PedidoDependenciaService pedidoDependenciaService;

    public PedidoDependenciaController(PedidoDependenciaService pedidoDependenciaService) {
        this.pedidoDependenciaService = pedidoDependenciaService;
    }

    @PostMapping("/api/pedidos-dependencia")
    public ResponseEntity<PedidoDependenciaResponseDTO> crear(
            @Valid @RequestBody PedidoDependenciaRequestDTO dto) {
        PedidoDependenciaResponseDTO creado = pedidoDependenciaService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping("/api/usuarios/{idUsuario}/pedidos-dependencia")
    public ResponseEntity<List<PedidoDependenciaResponseDTO>> listarPorUsuario(
            @PathVariable Long idUsuario,
            @RequestParam(required = false) EstadoPedidoDependencia estado) {
        return ResponseEntity.ok(pedidoDependenciaService.listarPorUsuario(idUsuario, estado));
    }

    @GetMapping("/api/pedidos-dependencia/{id}")
    public ResponseEntity<PedidoDependenciaResponseDTO> obtenerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(pedidoDependenciaService.obtenerPorId(id));
    }

    @PatchMapping("/api/pedidos-dependencia/{id}/cancelar")
    public ResponseEntity<PedidoDependenciaResponseDTO> cancelar(@PathVariable UUID id) {
        return ResponseEntity.ok(pedidoDependenciaService.cancelar(id));
    }
}
