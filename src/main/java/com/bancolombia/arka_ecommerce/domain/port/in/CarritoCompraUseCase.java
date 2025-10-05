package com.bancolombia.arka_ecommerce.domain.port.in;

import java.util.Date;
import java.util.List;

import com.bancolombia.arka_ecommerce.domain.model.CarritoCompra;
import com.bancolombia.arka_ecommerce.domain.model.CarritoCompraProducto;

public interface CarritoCompraUseCase {
    
    CarritoCompra crearNuevo(int idCliente);
    CarritoCompra obtenerCarritoPorId(int idCarritoCompra);
    List<CarritoCompra> carritosPorUsuario(int idCliente);
    CarritoCompra carritoActual(int idCliente);
    List<CarritoCompra> carritosAbandonados();
    List<CarritoCompra> carritoComprasPorFechas(Date startDate, Date finishDate);

    List<CarritoCompraProducto> agregarProductos(int idCliente, List<CarritoCompraProducto> carritoCompraProductos);

}
