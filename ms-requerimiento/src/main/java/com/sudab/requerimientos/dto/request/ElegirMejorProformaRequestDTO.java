package com.sudab.requerimientos.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record ElegirMejorProformaRequestDTO(
        @NotNull(message = "El id de la proforma ganadora es obligatorio")
        UUID idProformaGanadora
) {}
