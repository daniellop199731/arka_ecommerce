package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.mapper;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.domain.model.Producto;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto.ProductoDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductoWebMapper {

    private final ProveedorWebMapper proveedorWebMapper;
    private final CategoriaWebMapper categoriaWebMapper;

    public ProductoDto toDto(Producto producto){
        if(producto == null){
            return null;
        }

        ProductoDto productoDto = new ProductoDto();
        productoDto.setIdProducto(producto.getIdProducto());
        productoDto.setReferenciaProducto(producto.getReferenciaProducto());
        productoDto.setNombreProducto(producto.getNombreProducto());
        productoDto.setDescripcionProducto(producto.getDescripcionProducto());
        productoDto.setPrecioProducto(producto.getPrecioProducto());
        productoDto.setStockProducto(producto.getStockProducto());
        productoDto.setStockMinimoProducto(producto.getStockMinimoProducto());
        productoDto.setProveedorProducto(proveedorWebMapper.toDto(producto.getProveedorProducto()));
        productoDto.setUnidadesSolicitarProducto(producto.getUnidadesSolicitarProducto());
        productoDto.setCategoriaProducto(categoriaWebMapper.toDto(producto.getCategoriaProducto()));
        return productoDto;
    }

    public Producto toModel(ProductoDto productoDto){
        if(productoDto == null){
            return null;
        }

        Producto producto = new Producto();
        producto.setIdProducto(productoDto.getIdProducto());
        producto.setReferenciaProducto(productoDto.getReferenciaProducto());
        producto.setNombreProducto(productoDto.getNombreProducto());
        producto.setDescripcionProducto(productoDto.getDescripcionProducto());
        producto.setPrecioProducto(productoDto.getPrecioProducto());
        producto.setStockProducto(productoDto.getStockProducto());
        producto.setStockMinimoProducto(productoDto.getStockMinimoProducto());
        producto.setProveedorProducto(proveedorWebMapper.toModel(productoDto.getProveedorProducto()));
        producto.setUnidadesSolicitarProducto(productoDto.getUnidadesSolicitarProducto());
        producto.setCategoriaProducto(categoriaWebMapper.toModel(productoDto.getCategoriaProducto()));
        return producto;
    }

}
