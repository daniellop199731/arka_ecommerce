package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.domain.model.CarritoCompra;
import com.bancolombia.arka_ecommerce.domain.model.CarritoCompraProducto;
import com.bancolombia.arka_ecommerce.domain.port.out.CarritoCompraProductoRepositoryPort;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.CarritoCompraProductoEntity;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.mapper.CarritoCompraMapper;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.mapper.CarritoCompraProductoMappper;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.repository.CarritoCompraProductoJR;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CarritoCompraProductoPAdapter implements CarritoCompraProductoRepositoryPort {

    private final CarritoCompraProductoJR repository;
    private final CarritoCompraProductoMappper mapper;

    private final CarritoCompraMapper carritoCompraMapper;

    @Override
    public List<CarritoCompraProducto> findByCarritoCompra(CarritoCompra carritoCompra) {
        return repository.findByCarritoCompra(
            carritoCompraMapper.toEntity(carritoCompra)
        ).stream().map(mapper::toModel).toList();        
    }

    @Override
    public CarritoCompraProducto save(CarritoCompraProducto carritoCompraProducto) {
        CarritoCompraProductoEntity carritoCompraProductoEntity = mapper.toEntity(carritoCompraProducto);
        if(carritoCompraProducto.getId() == 0){
            carritoCompraProductoEntity.setId(null);
        }
        return mapper.toModel(repository.save(carritoCompraProductoEntity));        
    }

    @Override
    public List<CarritoCompraProducto> saveAll(List<CarritoCompraProducto> carritoCompraProductos) {
        List<CarritoCompraProducto> carritoCompraProductosResult = new ArrayList<>();
        List<CarritoCompraProductoEntity> carritoCompraProductosEntity = 
            carritoCompraProductos.stream().map(mapper::toEntity).toList();

        CarritoCompraProductoEntity carritoCompraProductoEntityAux;
        for (CarritoCompraProductoEntity carritoCompraProductoEntity : carritoCompraProductosEntity) {
            if(carritoCompraProductoEntity.getId() == 0){
                carritoCompraProductoEntity.setId(null);
            }          
            carritoCompraProductoEntityAux = repository.save(carritoCompraProductoEntity);  
            carritoCompraProductosResult.add(mapper.toModel(carritoCompraProductoEntityAux));
        }
        return carritoCompraProductosResult;
    }

    @Override
    public List<CarritoCompraProducto> saveAllOriginal(List<CarritoCompraProducto> carritoCompraProductos) {
        List<CarritoCompraProductoEntity> carritoCompraProductoEntities = 
            carritoCompraProductos.stream().map(mapper::toEntity).toList();
        
        carritoCompraProductoEntities = (List<CarritoCompraProductoEntity>) repository.saveAll(carritoCompraProductoEntities);
        return carritoCompraProductoEntities.stream().map(mapper::toModel).toList();
    }    

    @Override
    public void deleteById(int idCarritoCompraProducto) {
        repository.deleteById(idCarritoCompraProducto);
    }

    @Override
    public void deleteAll(List<CarritoCompraProducto> carritoCompraProductos) {
        List<CarritoCompraProductoEntity> carritoCompraProductosEntities = 
            carritoCompraProductos.stream().map(mapper::toEntity).toList();

        repository.deleteAll(carritoCompraProductosEntities);
    }
    
}
