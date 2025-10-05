package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.mapper;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.domain.model.MetodoPago;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto.MetodoPagoDto;

@Component
public class MetodoPagoWebMapper {

    public MetodoPagoDto toDto(MetodoPago metodoPago) {
        if (metodoPago == null) {
            return null;
        }
        MetodoPagoDto metodoPagoDto = new MetodoPagoDto();
        metodoPagoDto.setIdMetodoPago(metodoPago.getIdMetodoPago());
        metodoPagoDto.setNombreMetodoPago(metodoPago.getNombreMetodoPago());
        return metodoPagoDto;
    }

    public MetodoPago toModel(MetodoPagoDto metodoPagoDto) {
        if (metodoPagoDto == null) {
            return null;
        }
        MetodoPago metodoPago = new MetodoPago();
        metodoPago.setIdMetodoPago(metodoPagoDto.getIdMetodoPago());
        metodoPago.setNombreMetodoPago(metodoPagoDto.getNombreMetodoPago());
        return metodoPago;
    }

}

