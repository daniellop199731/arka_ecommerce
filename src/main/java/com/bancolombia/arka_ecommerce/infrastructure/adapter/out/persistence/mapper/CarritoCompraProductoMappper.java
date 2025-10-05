package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.domain.model.CarritoCompraProducto;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.CarritoCompraProductoEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CarritoCompraProductoMappper {

    private final CarritoCompraMapper carritoCompraMapper;
    private final ProductoMapper productoMapper;

    public CarritoCompraProducto toModel(CarritoCompraProductoEntity carritoCompraProductoEntity){
        if(carritoCompraProductoEntity == null){
            return null;
        }
        CarritoCompraProducto carritoCompraProducto = new CarritoCompraProducto();
        carritoCompraProducto.setId(carritoCompraProductoEntity.getId());
        carritoCompraProducto.setCarritoCompra(carritoCompraMapper.toModel(carritoCompraProductoEntity.getCarritoCompra()));
        carritoCompraProducto.setProductoCarritoCompra(productoMapper.toModel(carritoCompraProductoEntity.getProductoCarritoCompra()));
        carritoCompraProducto.setUnidadesProducto(carritoCompraProductoEntity.getUnidadesProducto());

        return carritoCompraProducto;

    }

    public CarritoCompraProductoEntity toEntity(CarritoCompraProducto cariCarritoCompraProductotoCompra){
        if(cariCarritoCompraProductotoCompra == null){
            return null;
        }
        CarritoCompraProductoEntity carritoCompraProductoEntity = new CarritoCompraProductoEntity();
        carritoCompraProductoEntity.setId(cariCarritoCompraProductotoCompra.getId());
        carritoCompraProductoEntity.setCarritoCompra(carritoCompraMapper.toEntity(cariCarritoCompraProductotoCompra.getCarritoCompra()));
        carritoCompraProductoEntity.setProductoCarritoCompra(productoMapper.toEntity(cariCarritoCompraProductotoCompra.getProductoCarritoCompra()));
        carritoCompraProductoEntity.setUnidadesProducto(cariCarritoCompraProductotoCompra.getUnidadesProducto());

        return carritoCompraProductoEntity;

    }    
    
}
