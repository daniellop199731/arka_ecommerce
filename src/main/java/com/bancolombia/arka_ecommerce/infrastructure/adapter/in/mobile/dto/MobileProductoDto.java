package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.mobile.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MobileProductoDto {

    private String nombreProducto;

    private Double precioProducto;

    private String descripcionProducto;
    
}
