package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.CarritoCompraEntity;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.CarritoCompraProductoEntity;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.ProductoEntity;

public interface CarritoCompraProductoJR extends CrudRepository<CarritoCompraProductoEntity, Integer> {
    
    List<CarritoCompraProductoEntity> findByCarritoCompra(CarritoCompraEntity carritoCompra);
    
    List<CarritoCompraProductoEntity> findByProductoCarritoCompra(ProductoEntity productoCarritoCompra);

}
