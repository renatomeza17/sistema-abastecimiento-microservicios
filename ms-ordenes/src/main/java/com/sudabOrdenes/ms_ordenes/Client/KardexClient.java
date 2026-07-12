package com.sudabOrdenes.ms_ordenes.Client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.sudabOrdenes.ms_ordenes.DTO.kardex.KardexMovimientoRequestDTO;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@FeignClient(name = "ms-kardex")
public interface KardexClient {
    @PostMapping("/api/kardex/movimiento")
    void registrarMovimiento(@RequestBody KardexMovimientoRequestDTO movimiento);

    // Enviamos TODOS los productos encapsulados en un solo viaje HTTP
    @PostMapping("/apu/movimientos/masivo")
    void registrarMovimientosMasivos(@RequestBody List<KardexMovimientoRequestDTO> movimientos);

}
