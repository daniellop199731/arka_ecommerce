package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.domain.model.MetodoPagoCliente;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.MetodoPagoClienteEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MetodoPagoClienteMapper {

    private final ClienteMapper ClienteMapper;
    private final MetodoPagoMapper metodoPagoMapper;

    public MetodoPagoCliente toModel(MetodoPagoClienteEntity metodoPagoClienteEntity){
        if(metodoPagoClienteEntity == null){
            return null;
        }

        MetodoPagoCliente metodoPagoCliente = new MetodoPagoCliente();
        metodoPagoCliente.setId(metodoPagoClienteEntity.getId());
        metodoPagoCliente.setClienteMetodoPago(ClienteMapper.toModel(metodoPagoClienteEntity.getClienteMetodoPago()));
        metodoPagoCliente.setMetodoPago(metodoPagoMapper.toModel(metodoPagoClienteEntity.getMetodoPago()));
        metodoPagoCliente.setValorCuentaMetodoPago(metodoPagoClienteEntity.getValorCuentaMetodoPago());
        return metodoPagoCliente;
    }

    public MetodoPagoClienteEntity toEntity(MetodoPagoCliente metodoPagoCliente){
        if(metodoPagoCliente == null){
            return null;
        }

        MetodoPagoClienteEntity metodoPagoClienteEntity = new MetodoPagoClienteEntity();
        metodoPagoClienteEntity.setId(metodoPagoCliente.getId());
        metodoPagoClienteEntity.setClienteMetodoPago(ClienteMapper.toEntity(metodoPagoCliente.getClienteMetodoPago()));
        metodoPagoClienteEntity.setMetodoPago(metodoPagoMapper.toEntity(metodoPagoCliente.getMetodoPago()));
        metodoPagoClienteEntity.setValorCuentaMetodoPago(metodoPagoCliente.getValorCuentaMetodoPago());
        return metodoPagoClienteEntity;
    }
}
