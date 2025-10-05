package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.mapper;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.domain.model.MetodoPagoCliente;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto.MetodoPagoClienteDto;

@Component
public class MetodoPagoClienteWebMapper {

    public MetodoPagoClienteDto toDto(MetodoPagoCliente metodoPagoCliente) {
        if (metodoPagoCliente == null) {
            return null;
        }
        MetodoPagoClienteDto metodoPagoClienteDto = new MetodoPagoClienteDto();
        metodoPagoClienteDto.setId(metodoPagoCliente.getId());
        metodoPagoClienteDto.setClienteMetodoPago(metodoPagoCliente.getClienteMetodoPago());
        metodoPagoClienteDto.setMetodoPago(metodoPagoCliente.getMetodoPago());
        metodoPagoClienteDto.setValorCuentaMetodoPago(metodoPagoCliente.getValorCuentaMetodoPago());
        return metodoPagoClienteDto;
    }

    public MetodoPagoCliente toModel(MetodoPagoClienteDto metodoPagoClienteDto){
        if (metodoPagoClienteDto == null) {
            return null;
        }
        MetodoPagoCliente metodoPagoCliente = new MetodoPagoCliente();
        metodoPagoCliente.setId(metodoPagoClienteDto.getId());
        metodoPagoCliente.setClienteMetodoPago(metodoPagoClienteDto.getClienteMetodoPago());
        metodoPagoCliente.setMetodoPago(metodoPagoClienteDto.getMetodoPago());
        metodoPagoCliente.setValorCuentaMetodoPago(metodoPagoClienteDto.getValorCuentaMetodoPago());
        return metodoPagoCliente;        
    }

}
