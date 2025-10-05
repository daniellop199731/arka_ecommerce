package com.bancolombia.arka_ecommerce.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetodoPagoCliente {

    private int id;
    private Cliente clienteMetodoPago;
    private MetodoPago metodoPago;
    private double valorCuentaMetodoPago;

}
