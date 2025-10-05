package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.domain.model.MetodoPago;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.MetodoPagoEntity;

@Component
public class MetodoPagoMapper {

    public MetodoPago toModel(MetodoPagoEntity metodoPagoEntity) {
        if (metodoPagoEntity == null) {
            return null;
        }
        MetodoPago metodoPago = new MetodoPago();
        metodoPago.setIdMetodoPago(metodoPagoEntity.getIdMetodoPago());
        metodoPago.setNombreMetodoPago(metodoPagoEntity.getNombreMetodoPago());
        return metodoPago;
    }

    public MetodoPagoEntity toEntity(MetodoPago metodoPago) {
        if (metodoPago == null) {
            return null;
        }
        MetodoPagoEntity metodoPagoEntity = new MetodoPagoEntity();
        metodoPagoEntity.setIdMetodoPago(metodoPago.getIdMetodoPago());
        metodoPagoEntity.setNombreMetodoPago(metodoPago.getNombreMetodoPago());
        return metodoPagoEntity;
    }

}


