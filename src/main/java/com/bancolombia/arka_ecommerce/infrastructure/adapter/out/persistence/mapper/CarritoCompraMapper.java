package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.domain.model.CarritoCompra;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.CarritoCompraEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CarritoCompraMapper {

    private final ClienteMapper clienteMapper;
    private final MetodoPagoMapper metodoPagoMapper;
    private final EstadoDespachoMapper estadoDespachoMapper;

    public CarritoCompra toModel(CarritoCompraEntity carritoCompraEntity){

        if (carritoCompraEntity == null){
            return null;
        }

        CarritoCompra carritoCompra = new CarritoCompra();
        carritoCompra.setIdCarritoCompra(carritoCompraEntity.getIdCarritoCompra());
        carritoCompra.setClienteCarritoCompra(clienteMapper.toModel(carritoCompraEntity.getClienteCarritoCompra()));
        carritoCompra.setEstadoDespacho(estadoDespachoMapper.toModel(carritoCompraEntity.getEstadoDespacho()));
        carritoCompra.setMetodoPagoCarritoCompra(metodoPagoMapper.toModel(carritoCompraEntity.getMetodoPagoCarritoCompra()));
        carritoCompra.setCarritoPagado(carritoCompraEntity.getCarritoPagado());
        carritoCompra.setFechaCreacionCarritoCompra(carritoCompraEntity.getFechaCreacionCarritoCompra());
        carritoCompra.setCantidadNotificacionesPorCarritoAbandonado(carritoCompraEntity.getCantidadNotificacionesPorCarritoAbandonado());
        //FALTA !!!Lista de carrito de campras por producto

        return carritoCompra;

    }

    public CarritoCompraEntity toEntity(CarritoCompra carritoCompra){

        CarritoCompraEntity carritoCompraEntity = new CarritoCompraEntity();
        carritoCompraEntity.setIdCarritoCompra(carritoCompra.getIdCarritoCompra());
        carritoCompraEntity.setClienteCarritoCompra(clienteMapper.toEntity(carritoCompra.getClienteCarritoCompra()));
        carritoCompraEntity.setEstadoDespacho(estadoDespachoMapper.toEntity(carritoCompra.getEstadoDespacho()));
        carritoCompraEntity.setMetodoPagoCarritoCompra(metodoPagoMapper.toEntity(carritoCompra.getMetodoPagoCarritoCompra()));
        carritoCompraEntity.setCarritoPagado(carritoCompra.getCarritoPagado());
        carritoCompraEntity.setFechaCreacionCarritoCompra(carritoCompra.getFechaCreacionCarritoCompra());
        carritoCompraEntity.setCantidadNotificacionesPorCarritoAbandonado(carritoCompra.getCantidadNotificacionesPorCarritoAbandonado());
        //FALTA !!!Lista de carrito de campras por producto

        return carritoCompraEntity;

    }
    
}
