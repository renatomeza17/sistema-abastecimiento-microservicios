package com.sudab.requerimientos.mapper;

import com.sudab.requerimientos.dto.request.ProveedorRequestDTO;
import com.sudab.requerimientos.dto.response.ProveedorResponseDTO;
import com.sudab.requerimientos.model.Proveedor;
import org.springframework.stereotype.Component;

@Component
public class ProveedorMapper {

    public Proveedor toEntity(ProveedorRequestDTO dto) {
        return Proveedor.builder()
                .ruc(dto.ruc())
                .razonSocial(dto.razonSocial())
                .direccion(dto.direccion())
                .idUsuario(dto.idUsuario())
                .build();
    }

    public ProveedorResponseDTO toResponseDTO(Proveedor entity) {
        return new ProveedorResponseDTO(
                entity.getId(),
                entity.getRuc(),
                entity.getRazonSocial(),
                entity.getDireccion(),
                entity.getIdUsuario()
        );
    }
}
