package com.arka.cotizador.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoCotizacion {

    private String referenciaProducto;
    private double precio;
    private int cantidadProducto;
    
}
