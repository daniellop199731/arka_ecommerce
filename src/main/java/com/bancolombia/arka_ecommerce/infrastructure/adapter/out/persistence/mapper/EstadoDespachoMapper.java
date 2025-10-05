package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.domain.model.EstadoDespacho;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.EstadoDespachoEntity;

@Component
public class EstadoDespachoMapper {

    public EstadoDespacho toModel(EstadoDespachoEntity estadoDespachoEntity){
        if(estadoDespachoEntity == null){
            return null;
        }
        EstadoDespacho estadoDespacho = new EstadoDespacho();
        estadoDespacho.setIdEstadoDespacho(estadoDespachoEntity.getIdEstadoDespacho());
        estadoDespacho.setNombreEstadoDespacho(estadoDespachoEntity.getNombreEstadoDespacho());
        return estadoDespacho;
    }

    public EstadoDespachoEntity toEntity(EstadoDespacho estadoDespacho){
        if(estadoDespacho == null){
            return null;
        }
        EstadoDespachoEntity estadoDespachoEntity = new EstadoDespachoEntity();
        estadoDespachoEntity.setIdEstadoDespacho(estadoDespacho.getIdEstadoDespacho());
        estadoDespachoEntity.setNombreEstadoDespacho(estadoDespacho.getNombreEstadoDespacho());
        return estadoDespachoEntity;
    }    

}
