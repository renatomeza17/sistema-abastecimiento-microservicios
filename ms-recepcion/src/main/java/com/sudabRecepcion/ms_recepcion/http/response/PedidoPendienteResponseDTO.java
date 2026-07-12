package com.sudabRecepcion.ms_recepcion.http.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PedidoPendienteResponseDTO {

    private Long idPedidoPendiente;
    private Long idOrden;
    private String motivo;
    private String observacion;
    private String estado;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaResolucion;
}
