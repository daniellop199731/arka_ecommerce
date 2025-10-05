package com.bancolombia.arka_ecommerce.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bancolombia.arka_ecommerce.domain.model.CarritoCompra;
import com.bancolombia.arka_ecommerce.domain.model.CarritoCompraProducto;
import com.bancolombia.arka_ecommerce.domain.port.in.CarritoCompraProductoUseCase;
import com.bancolombia.arka_ecommerce.domain.port.out.CarritoCompraProductoRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarritoCompraProductoApplicationService implements CarritoCompraProductoUseCase {

    private final CarritoCompraProductoRepositoryPort repository;

    @Override
    public List<CarritoCompraProducto> obtenerProductosCarrito(int idCarrito) {
        CarritoCompra carritoCompra = new CarritoCompra();
        carritoCompra.setIdCarritoCompra(idCarrito);
        return repository.findByCarritoCompra(carritoCompra);
    }

    @Override
    public CarritoCompraProducto agregarRelacionCarritoProducto(int idCarrito, int idProducto, int unidades) {
        CarritoCompraProducto carritoCompraProducto = new CarritoCompraProducto(idCarrito, idProducto, unidades);
        return repository.save(carritoCompraProducto);
    }

    @Override
    public void eliminarProductosCarrito(int idCarrito) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarProductosCarrito'");
    }
    
}
