package com.bancolombia.arka_ecommerce.domain.port.in;

import java.util.List;

import com.bancolombia.arka_ecommerce.domain.model.CarritoCompraProducto;

public interface CarritoCompraProductoUseCase {

    List<CarritoCompraProducto> obtenerProductosCarrito(int idCarrito);
    CarritoCompraProducto agregarRelacionCarritoProducto(int idCarrito, int idProducto, int unidades);
    void eliminarProductosCarrito(int idCarrito);
    
}
