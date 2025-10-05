package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.mapper;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.domain.model.CarritoCompra;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto.CarritoCompraDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CarritoCompraWebMapper {

    private final ClienteWebMapper clienteWebMapper;
    private final EstadoDespachoWebMapper estadoDespachoWebMapper;
    private final MetodoPagoWebMapper metodoPagoWebMapper;

    public CarritoCompraDto toDto(CarritoCompra carritoCompra){

        if(carritoCompra == null){
            return null;
        }

        CarritoCompraDto carritoCompraDto = new CarritoCompraDto();
        
        carritoCompraDto.setIdCarritoCompra(carritoCompra.getIdCarritoCompra());
        carritoCompraDto.setClienteCarritoCompra(clienteWebMapper.toDto(carritoCompra.getClienteCarritoCompra()));
        carritoCompraDto.setEstadoDespacho(estadoDespachoWebMapper.toDto(carritoCompra.getEstadoDespacho()));
        carritoCompraDto.setMetodoPagoCarritoCompra(metodoPagoWebMapper.toDto(carritoCompra.getMetodoPagoCarritoCompra()));
        carritoCompraDto.setCarritoPagado(carritoCompra.getCarritoPagado());
        carritoCompraDto.setFechaCreacionCarritoCompra(carritoCompra.getFechaCreacionCarritoCompra());
        carritoCompraDto.setCantidadNotificacionesPorCarritoAbandonado(carritoCompra.getCantidadNotificacionesPorCarritoAbandonado());

        return carritoCompraDto;

    }

    public CarritoCompra toModel(CarritoCompraDto carritoCompraDto){

        if(carritoCompraDto == null){
            return null;
        }

        CarritoCompra carritoCompra = new CarritoCompra();
        carritoCompra.setIdCarritoCompra(carritoCompraDto.getIdCarritoCompra());
        carritoCompra.setClienteCarritoCompra(clienteWebMapper.toModel(carritoCompraDto.getClienteCarritoCompra()));
        carritoCompra.setEstadoDespacho(estadoDespachoWebMapper.toModel(carritoCompraDto.getEstadoDespacho()));
        carritoCompra.setMetodoPagoCarritoCompra(metodoPagoWebMapper.toModel(carritoCompraDto.getMetodoPagoCarritoCompra()));
        carritoCompra.setCarritoPagado(carritoCompraDto.getCarritoPagado());
        carritoCompra.setFechaCreacionCarritoCompra(carritoCompraDto.getFechaCreacionCarritoCompra());
        carritoCompra.setCantidadNotificacionesPorCarritoAbandonado(carritoCompraDto.getCantidadNotificacionesPorCarritoAbandonado());        

        return carritoCompra;

    }
    
}
