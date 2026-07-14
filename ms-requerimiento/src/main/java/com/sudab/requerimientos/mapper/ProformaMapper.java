package com.sudab.requerimientos.mapper;

import com.sudab.requerimientos.dto.request.DetalleProformaRequestDTO;
import com.sudab.requerimientos.dto.request.ProformaRequestDTO;
import com.sudab.requerimientos.dto.response.DetalleProformaResponseDTO;
import com.sudab.requerimientos.dto.response.ProformaResponseDTO;
import com.sudab.requerimientos.model.DetalleProforma;
import com.sudab.requerimientos.model.Proforma;
import com.sudab.requerimientos.model.Requerimiento;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProformaMapper {

    public Proforma toEntity(ProformaRequestDTO dto, Requerimiento requerimiento) {
        Proforma proforma = Proforma.builder()
                .idUsuario(dto.idUsuario())
                .requerimiento(requerimiento)
                .build();

        List<DetalleProforma> detalles = dto.detalles().stream()
                .map(d -> toDetalleEntity(d, proforma))
                .toList();

        proforma.setDetalles(detalles);
        proforma.recalcularPrecioTotal();
        return proforma;
    }

    public DetalleProforma toDetalleEntity(DetalleProformaRequestDTO dto, Proforma proforma) {
        return DetalleProforma.builder()
                .cantidad(dto.cantidad())
                .observacionEspecifica(dto.observacionEspecifica())
                .precioUnitario(dto.precioUnitario())
                .idProducto(dto.idProducto())
                .proforma(proforma)
                .build();
    }

    public ProformaResponseDTO toResponseDTO(Proforma entity) {
        List<DetalleProformaResponseDTO> detalles = entity.getDetalles().stream()
                .map(this::toDetalleResponseDTO)
                .toList();

        return new ProformaResponseDTO(
                entity.getId(),
                entity.getCodigo(),
                entity.getEstado(),
                entity.getFechaRecepcion(),
                entity.getFechaActualizacion(),
                entity.getPrecioTotal(),
                entity.getIdUsuario(),
                entity.getRequerimiento().getId(),
                detalles
        );
    }

    public DetalleProformaResponseDTO toDetalleResponseDTO(DetalleProforma entity) {
        return new DetalleProformaResponseDTO(
                entity.getId(),
                entity.getCantidad(),
                entity.getObservacionEspecifica(),
                entity.getPrecioUnitario(),
                entity.getIdProducto()
        );
    }
}
