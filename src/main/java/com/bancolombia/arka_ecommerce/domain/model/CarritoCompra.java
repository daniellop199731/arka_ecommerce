package com.bancolombia.arka_ecommerce.domain.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoCompra {

    private int idCarritoCompra;

    private Cliente clienteCarritoCompra;

    private EstadoDespacho estadoDespacho;

    private MetodoPago metodoPagoCarritoCompra;

    //0: para carrito no pagado, 1: para carrito pagado
    private int carritoPagado;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaCreacionCarritoCompra;

    private int cantidadNotificacionesPorCarritoAbandonado;    
    
}
