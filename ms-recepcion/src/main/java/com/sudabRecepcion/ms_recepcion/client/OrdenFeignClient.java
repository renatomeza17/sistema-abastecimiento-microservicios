package com.sudabRecepcion.ms_recepcion.client;
 
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
 
import com.sudabRecepcion.ms_recepcion.dto.OrdenResponseDTO;
 
@FeignClient(name = "MS-ORDENES", url = "${ms-ordenes.url:http://localhost:8082}")
public interface OrdenFeignClient {
 
    @GetMapping("/api/ordenes/{id}")
    OrdenResponseDTO obtenerOrden(@PathVariable("id") Long id);
}
 