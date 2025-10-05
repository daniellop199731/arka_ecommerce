package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.domain.model.Producto;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.ProductoEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductoMapper {

    private final ProveedorMapper proveedorMapper;
    private final CategoriaMapper categoriaMapper;

    public Producto toModel(ProductoEntity productoEntity){
        if(productoEntity == null){
            return null;
        }

        Producto producto = new Producto();
        producto.setIdProducto(productoEntity.getIdProducto());
        producto.setReferenciaProducto(productoEntity.getReferenciaProducto());
        producto.setNombreProducto(productoEntity.getNombreProducto());
        producto.setDescripcionProducto(productoEntity.getDescripcionProducto());
        producto.setPrecioProducto(productoEntity.getPrecioProducto());
        producto.setStockProducto(productoEntity.getStockProducto());
        producto.setStockMinimoProducto(productoEntity.getStockMinimoProducto());
        producto.setProveedorProducto(proveedorMapper.toModel(productoEntity.getProveedorProducto()));
        producto.setUnidadesSolicitarProducto(productoEntity.getUnidadesSolicitarProducto());
        producto.setCategoriaProducto(categoriaMapper.toModel(productoEntity.getCategoriaProducto()));        
        return producto;
    }

    public ProductoEntity toEntity(Producto producto){
        if(producto == null){
            return null;
        }

        ProductoEntity productoEntity = new ProductoEntity();
        productoEntity.setIdProducto(producto.getIdProducto());
        productoEntity.setReferenciaProducto(producto.getReferenciaProducto());
        productoEntity.setNombreProducto(producto.getNombreProducto());
        productoEntity.setDescripcionProducto(producto.getDescripcionProducto());
        productoEntity.setPrecioProducto(producto.getPrecioProducto());
        productoEntity.setStockProducto(producto.getStockProducto());
        productoEntity.setStockMinimoProducto(producto.getStockMinimoProducto());
        productoEntity.setProveedorProducto(proveedorMapper.toEntity(producto.getProveedorProducto()));
        productoEntity.setUnidadesSolicitarProducto(producto.getUnidadesSolicitarProducto());
        productoEntity.setCategoriaProducto(categoriaMapper.toEntity(producto.getCategoriaProducto()));        
        return productoEntity;
    }

}
