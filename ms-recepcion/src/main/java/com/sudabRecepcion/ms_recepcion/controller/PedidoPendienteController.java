package com.sudabRecepcion.ms_recepcion.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sudabRecepcion.ms_recepcion.http.response.PedidoPendienteResponseDTO;
import com.sudabRecepcion.ms_recepcion.service.PedidoPendienteService;

@RestController
@RequestMapping("/api/pedidos-pendientes")
@CrossOrigin(origins = "*")
public class PedidoPendienteController {

    private final PedidoPendienteService pedidoPendienteService;

    public PedidoPendienteController(PedidoPendienteService pedidoPendienteService) {
        this.pedidoPendienteService = pedidoPendienteService;
    }

    @PostMapping
    public ResponseEntity<PedidoPendienteResponseDTO> registrar(
            @RequestParam Long idOrden,
            @RequestParam String motivo
    ) {
        PedidoPendienteResponseDTO pedido = pedidoPendienteService.registrar(idOrden, motivo);
        return ResponseEntity.ok(pedido);
    }

    @GetMapping
    public ResponseEntity<List<PedidoPendienteResponseDTO>> listar() {
        List<PedidoPendienteResponseDTO> pedidos = pedidoPendienteService.listar();
        return ResponseEntity.ok(pedidos);
    }

    @PutMapping("/{id}/resolver")
    public ResponseEntity<PedidoPendienteResponseDTO> resolver(@PathVariable Long id) {
        PedidoPendienteResponseDTO pedido = pedidoPendienteService.resolver(id);
        return ResponseEntity.ok(pedido);
    }
}
