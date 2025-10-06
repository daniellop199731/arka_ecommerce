package com.arka.cotizador.domain.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cotizacion {

    private List<ProductoCotizacion> productos;
    private List<Integer> idsProductos;

}
