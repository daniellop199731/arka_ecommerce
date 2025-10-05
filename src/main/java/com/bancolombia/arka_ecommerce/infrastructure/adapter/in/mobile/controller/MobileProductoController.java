package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.mobile.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_ecommerce.domain.model.Producto;
import com.bancolombia.arka_ecommerce.domain.port.in.ProductoUseCase;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto.ProductoDto;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.mapper.ProductoWebMapper;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.mobile.dto.MobileProductoDto;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.mobile.mapper.MobileProductoMapper;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/mobile/productos")
@RequiredArgsConstructor
public class MobileProductoController {

    private final ProductoUseCase productoUseCase;
    private final ProductoWebMapper webMapper;
    private final MobileProductoMapper mapper;

    @GetMapping("")
    public ResponseEntity<List<MobileProductoDto>> getAllProductos() {

        List<Producto> productos = productoUseCase.getAllProductos();

        List<ProductoDto> productosDto = productos.stream()
            .map(webMapper::toDto)
            .toList();

        List<MobileProductoDto> productosMobileDto = productosDto.stream()
            .limit(10)
            .map(mapper::toDto)
            .toList();

        return ResponseEntity.ok(productosMobileDto);
    }
    
    
}
