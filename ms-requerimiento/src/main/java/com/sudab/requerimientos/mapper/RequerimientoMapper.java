package com.sudab.requerimientos.mapper;

import com.sudab.requerimientos.dto.request.RequerimientoDetalleRequestDTO;
import com.sudab.requerimientos.dto.request.RequerimientoRequestDTO;
import com.sudab.requerimientos.dto.response.RequerimientoDetalleResponseDTO;
import com.sudab.requerimientos.dto.response.RequerimientoResponseDTO;
import com.sudab.requerimientos.model.Requerimiento;
import com.sudab.requerimientos.model.RequerimientoDetalle;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RequerimientoMapper {

    public Requerimiento toEntity(RequerimientoRequestDTO dto) {
        Requerimiento requerimiento = Requerimiento.builder()
                .descripcion(dto.descripcion())
                .idDependencia(dto.idDependencia())
                .idUsuario(dto.idUsuario())
                .build();

        List<RequerimientoDetalle> detalles = dto.detalles().stream()
                .map(d -> toDetalleEntity(d, requerimiento))
                .toList();

        requerimiento.setDetalles(detalles);
        return requerimiento;
    }

    public RequerimientoDetalle toDetalleEntity(RequerimientoDetalleRequestDTO dto, Requerimiento requerimiento) {
        return RequerimientoDetalle.builder()
                .cantidad(dto.cantidad())
                .unidadMedida(dto.unidadMedida())
                .observacionEspecifica(dto.observacionEspecifica())
                .idProducto(dto.idProducto())
                .requerimiento(requerimiento)
                .build();
    }

    public RequerimientoResponseDTO toResponseDTO(Requerimiento entity) {
        List<RequerimientoDetalleResponseDTO> detalles = entity.getDetalles().stream()
                .map(this::toDetalleResponseDTO)
                .toList();

        return new RequerimientoResponseDTO(
                entity.getId(),
                entity.getCodigo(),
                entity.getDescripcion(),
                entity.getEstado(),
                entity.getFechaCreacion(),
                entity.getFechaActualizacion(),
                entity.getIdDependencia(),
                entity.getIdUsuario(),
                detalles
        );
    }

    public RequerimientoDetalleResponseDTO toDetalleResponseDTO(RequerimientoDetalle entity) {
        return new RequerimientoDetalleResponseDTO(
                entity.getId(),
                entity.getCantidad(),
                entity.getUnidadMedida(),
                entity.getObservacionEspecifica(),
                entity.getIdProducto()
        );
    }
}
