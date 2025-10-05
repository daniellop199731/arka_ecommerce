package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.mobile.mapper;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.domain.model.Producto;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto.ProductoDto;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.mobile.dto.MobileProductoDto;

@Component
public class MobileProductoMapper {

    public MobileProductoDto toDto(Producto producto){
        if(producto == null){
            return null;
        }

        MobileProductoDto mobileProductoDto = new MobileProductoDto();
        mobileProductoDto.setNombreProducto(producto.getNombreProducto());
        mobileProductoDto.setPrecioProducto(producto.getPrecioProducto());
        mobileProductoDto.setDescripcionProducto(producto.getDescripcionProducto());

        return mobileProductoDto;
    }
    
    public Producto toModel(MobileProductoDto mobileProductoDto){
        if(mobileProductoDto == null){
            return null;
        }

        Producto producto = new Producto();
        producto.setNombreProducto(mobileProductoDto.getNombreProducto());
        producto.setPrecioProducto(mobileProductoDto.getPrecioProducto());
        producto.setDescripcionProducto(mobileProductoDto.getDescripcionProducto());

        return producto;
    }

    public MobileProductoDto toDto(ProductoDto productoDto){
        if(productoDto == null){
            return null;
        }

        MobileProductoDto mobileProductoDto = new MobileProductoDto();
        mobileProductoDto.setNombreProducto(productoDto.getNombreProducto());
        mobileProductoDto.setPrecioProducto(productoDto.getPrecioProducto());
        mobileProductoDto.setDescripcionProducto(productoDto.getDescripcionProducto());
        
        return mobileProductoDto;
    }    
    
}
