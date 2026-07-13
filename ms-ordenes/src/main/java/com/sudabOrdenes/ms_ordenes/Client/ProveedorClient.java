package com.sudabOrdenes.ms_ordenes.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sudabOrdenes.ms_ordenes.Config.FeignClientConfig;
import com.sudabOrdenes.ms_ordenes.DTO.proveedor.ProveedorResponseDTO;

@FeignClient(name = "ms-proveedores",configuration = FeignClientConfig.class)
public interface ProveedorClient {
    @GetMapping("/{id}")
    ProveedorResponseDTO obtenerPorId(@PathVariable("id") Long id);

}
