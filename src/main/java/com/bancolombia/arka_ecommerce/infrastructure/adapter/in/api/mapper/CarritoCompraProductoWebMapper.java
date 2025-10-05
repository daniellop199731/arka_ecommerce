package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.mapper;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.domain.model.CarritoCompraProducto;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto.CarritoCompraProductoDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CarritoCompraProductoWebMapper {

    private final CarritoCompraWebMapper carritoCompraWebMapper;
    private final ProductoWebMapper productoWebMapper;

    public CarritoCompraProductoDto toDto(CarritoCompraProducto carritoCompraProducto){
        if(carritoCompraProducto == null){
            return null;
        }
        CarritoCompraProductoDto carritoCompraProductoDto = new CarritoCompraProductoDto();
        carritoCompraProductoDto.setId(carritoCompraProducto.getId());
        carritoCompraProductoDto.setCarritoCompra(carritoCompraWebMapper.toDto(carritoCompraProducto.getCarritoCompra()));
        carritoCompraProductoDto.setProductoCarritoCompra(productoWebMapper.toDto(carritoCompraProducto.getProductoCarritoCompra()));
        carritoCompraProductoDto.setUnidadesProducto(carritoCompraProducto.getUnidadesProducto());

        return carritoCompraProductoDto;

    }

    public CarritoCompraProducto toModel(CarritoCompraProductoDto carritoCompraProductoDto){
        if(carritoCompraProductoDto == null){
            return null;
        }
        CarritoCompraProducto carritoCompraProducto = new CarritoCompraProducto();
        carritoCompraProducto.setId(carritoCompraProductoDto.getId());
        carritoCompraProducto.setCarritoCompra(carritoCompraWebMapper.toModel(carritoCompraProductoDto.getCarritoCompra()));
        carritoCompraProducto.setProductoCarritoCompra(productoWebMapper.toModel(carritoCompraProductoDto.getProductoCarritoCompra()));
        carritoCompraProducto.setUnidadesProducto(carritoCompraProductoDto.getUnidadesProducto());

        return carritoCompraProducto;

    }    
    
}
