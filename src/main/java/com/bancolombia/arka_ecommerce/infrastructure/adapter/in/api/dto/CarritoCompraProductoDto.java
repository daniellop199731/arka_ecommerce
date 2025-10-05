package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoCompraProductoDto {

    private int id;
    
    private CarritoCompraDto carritoCompra;

    private ProductoDto productoCarritoCompra;

    @Positive(message = "Las unidades del producto deben ser mayores a cero")
    private int unidadesProducto;

    
}
