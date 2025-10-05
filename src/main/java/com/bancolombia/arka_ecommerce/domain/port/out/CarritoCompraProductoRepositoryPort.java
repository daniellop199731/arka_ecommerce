package com.bancolombia.arka_ecommerce.domain.port.out;

import java.util.List;

import com.bancolombia.arka_ecommerce.domain.model.CarritoCompra;
import com.bancolombia.arka_ecommerce.domain.model.CarritoCompraProducto;

public interface CarritoCompraProductoRepositoryPort {

    /**
     * Obtiene productos por carrito de compra
     * @param carritoCompra Carrito de compra a consultar
     * @return Lista CarritoCompraProducto
     */
    List<CarritoCompraProducto> findByCarritoCompra(CarritoCompra carritoCompra);

    CarritoCompraProducto save(CarritoCompraProducto carritoCompraProducto);
    List<CarritoCompraProducto> saveAll(List<CarritoCompraProducto> carritoCompraProductos);
    List<CarritoCompraProducto> saveAllOriginal(List<CarritoCompraProducto> carritoCompraProductos);
    void deleteById(int idCarritoCompraProducto);
    void deleteAll(List<CarritoCompraProducto> carritoCompraProductos);
    
}
