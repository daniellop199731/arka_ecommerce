package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoCompraDto {

    private int idCarritoCompra;

    private ClienteDto clienteCarritoCompra;

    private EstadoDespachoDto estadoDespacho;

    private MetodoPagoDto metodoPagoCarritoCompra;

    //0: para carrito no pagado, 1: para carrito pagado
    private int carritoPagado;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaCreacionCarritoCompra;

    private int cantidadNotificacionesPorCarritoAbandonado;     
    
}
