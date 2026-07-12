package com.sudabOrdenes.ms_ordenes.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sudabOrdenes.ms_ordenes.DTO.proforma.ProformaResponseDTO;



@FeignClient(name = "ms-proformas")
public interface ProformaClient {
    // @GetMapping("/{id}")
    // ProformaResponseDTO obtenerProforma(@PathVariable Long id);

    @GetMapping("/api/proformas/{id}")
    ProformaResponseDTO obtenerPorId(@PathVariable("id") Long id);

    // @PatchMapping("/{id}/estado")
    // void actualizarEstado(@PathVariable Long id, @RequestBody EstadoUpdateDTO estado);

    @PutMapping("/api/proformas/{id}/actualizar-estado")
    void actualizarEstado(@PathVariable("id") Long id, @RequestParam("estado") String estado);
    

    @PutMapping("/api/proformas/{id}/liberar")
    void liberarProforma(@PathVariable("id") Long id);

}
