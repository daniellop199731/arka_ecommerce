package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetodoPagoDto {

    private int idMetodoPago;

    @NotBlank(message = "El nombre del método de pago no puede estar vacío")
    @NotNull(message = "El nombre del método de pago no puede ser nulo")
    private String nombreMetodoPago;

}

