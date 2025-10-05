package com.bancolombia.arka_ecommerce.domain.port.out;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.bancolombia.arka_ecommerce.domain.model.CarritoCompra;
import com.bancolombia.arka_ecommerce.domain.model.Cliente;

public interface CarritoCompraRepositoryPort {

    CarritoCompra save(CarritoCompra carritoCompra);
    List<CarritoCompra> findAll();
    Optional<CarritoCompra>findById(int idCarritoCompra);
    void deleteById(int idCarritoCompra);
    boolean existsById(int idCarritoCompra);

    List<CarritoCompra> findByClienteCarritoCompra(Cliente cliente);
    List<CarritoCompra> findCarritoActual(int idCliente);
    List<CarritoCompra> findcarritosAbandonados();
    List<CarritoCompra> findByFechaCreacionCarritoCompraBetween(Date dateStar, Date dateEnd);
    
}
