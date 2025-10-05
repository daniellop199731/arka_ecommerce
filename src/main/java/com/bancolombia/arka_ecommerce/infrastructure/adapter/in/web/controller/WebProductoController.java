package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_ecommerce.domain.model.Producto;
import com.bancolombia.arka_ecommerce.domain.port.in.ProductoUseCase;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto.ProductoDto;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.mapper.ProductoWebMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/web/productos")
@RequiredArgsConstructor
public class WebProductoController {

    private final ProductoUseCase productoUseCase;

    private final ProductoWebMapper webMapper;

    @GetMapping("")
    public ResponseEntity<List<ProductoDto>> getAllProductos() {

        List<Producto> productos = productoUseCase.getAllProductos();

        List<ProductoDto> productosDto = productos.stream()
            .limit(25)
            .map(webMapper::toDto)
            .toList();

        return ResponseEntity.ok(productosDto);
    }

}
