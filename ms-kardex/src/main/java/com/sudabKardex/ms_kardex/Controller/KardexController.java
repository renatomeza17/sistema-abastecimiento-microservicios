package com.sudabKardex.ms_kardex.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudabKardex.ms_kardex.DTO.KardexMovimientoRequestDTO;
import com.sudabKardex.ms_kardex.DTO.KardexMovimientoResponseDTO;
import com.sudabKardex.ms_kardex.DTO.KardexRequestDTO;
import com.sudabKardex.ms_kardex.DTO.KardexResponseDTO;
import com.sudabKardex.ms_kardex.DTO.Producto.ProductoResponseDTO;
import com.sudabKardex.ms_kardex.Model.Kardex;
import com.sudabKardex.ms_kardex.Service.KardexService;

@RestController
@RequestMapping("/api/kardex")
@CrossOrigin(origins = "*")
public class KardexController {

    private final KardexService kardexService;

    public KardexController(KardexService kardexService) {
        this.kardexService = kardexService;
    }

    @PostMapping("/nuevo-asiento")  
    public ResponseEntity<Kardex> crearAsiento(@RequestBody KardexRequestDTO dto) {
        return new ResponseEntity<>(kardexService.registrarNuevoAsiento(dto), HttpStatus.CREATED);
    }

    @GetMapping("/productos-disponibles")
    public ResponseEntity<List<ProductoResponseDTO>> obtenerProductosSinKardex() {
        return ResponseEntity.ok(kardexService.obtenerProductosDisponibles());
    }

    @GetMapping
    public ResponseEntity<List<KardexResponseDTO>> obtenerInventarioGeneral() {
        return ResponseEntity.ok(kardexService.listarTodoElKardex());
    }

    @GetMapping("/{idKardex}/movimientos")
    public ResponseEntity<List<KardexMovimientoResponseDTO>> obtenerHistorialMovimientos(@PathVariable Long idKardex) {
        return ResponseEntity.ok(kardexService.obtenerMovimientosPorKardex(idKardex));
    }

    @PostMapping("/movimiento")
    public ResponseEntity<String> crearMovimientoManual(@RequestBody KardexMovimientoRequestDTO request) {
        kardexService.registrarMovimiento(
            request.getIdProducto(),
            request.getCantidad(),
            request.getTipoMovimiento(),
            request.getDocumentoReferencia(),
            request.getObservaciones()
        );
        return ResponseEntity.ok("Movimiento registrado y stock actualizado con exito en Neon DB.");
    }

    @GetMapping("/verificar-faltantes/{idOrden}")
    public ResponseEntity<List<ProductoResponseDTO>> obtenerProductosFaltantesPorOrden(@PathVariable Long idOrden) {
        List<ProductoResponseDTO> faltantes = kardexService.verificarProductosFaltantesDeOrden(idOrden);
        return ResponseEntity.ok(faltantes);
    }
}
