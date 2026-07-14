package com.sudab.pedidodependencia.mapper;

import com.sudab.pedidodependencia.dto.request.DependenciaRequestDTO;
import com.sudab.pedidodependencia.dto.response.DependenciaResponseDTO;
import com.sudab.pedidodependencia.model.Dependencia;
import org.springframework.stereotype.Component;

@Component
public class DependenciaMapper {

    public Dependencia toEntity(DependenciaRequestDTO dto) {
        return Dependencia.builder()
                .nombre(dto.nombre())
                .descripcion(dto.descripcion())
                .build();
    }

    public DependenciaResponseDTO toResponseDTO(Dependencia entity) {
        return new DependenciaResponseDTO(
                entity.getId(),
                entity.getNombre(),
                entity.getDescripcion()
        );
    }
}
