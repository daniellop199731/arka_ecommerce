package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto;

import com.bancolombia.arka_ecommerce.domain.model.MetodoPago;
import com.bancolombia.arka_ecommerce.domain.model.Cliente;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetodoPagoClienteDto {

    private int id;

    @NotNull(message = "El Cliente del metodo de pago no puede ser nulo")
    private Cliente ClienteMetodoPago;

    @NotNull(message = "El metodo de pago no puede ser nulo")
    private MetodoPago metodoPago;

    @NotNull(message = "El valor de la cuenta del metodo de pago no puede ser nulo")
    @Positive(message = "El valor de la cuenta del metodo de pago debe ser positivo")
    private double valorCuentaMetodoPago;

}
