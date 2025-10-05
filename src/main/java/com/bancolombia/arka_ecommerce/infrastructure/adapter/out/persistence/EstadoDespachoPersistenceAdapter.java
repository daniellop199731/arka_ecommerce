package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.domain.model.EstadoDespacho;
import com.bancolombia.arka_ecommerce.domain.port.out.EstadoDespachoRepositoryPort;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.EstadoDespachoEntity;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.mapper.EstadoDespachoMapper;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.repository.EstadoDespachoJR;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EstadoDespachoPersistenceAdapter implements EstadoDespachoRepositoryPort {

    private final EstadoDespachoJR repository;
    private final EstadoDespachoMapper mapper;

    @Override
    public List<EstadoDespacho> findAll() {
        List<EstadoDespachoEntity> estadosDespachoEntity = repository.findAll();
        return estadosDespachoEntity.stream().map(mapper::toModel).toList();
    }

    @Override
    public Optional<EstadoDespacho> findById(int idEstadoDespacho) {
        return repository.findById(idEstadoDespacho).map(mapper::toModel);
        
    }

    @Override
    public EstadoDespacho save(EstadoDespacho estadoDespacho) {
        EstadoDespachoEntity estadoDespachoEntity = mapper.toEntity(estadoDespacho);
        if(estadoDespacho.getIdEstadoDespacho() == 0){
            estadoDespachoEntity.setIdEstadoDespacho(null);
        }
        return mapper.toModel(repository.save(estadoDespachoEntity));
    }

    @Override
    public void deleteById(int idEstadoDespacho) {
        repository.deleteById(idEstadoDespacho);
    }

    @Override
    public boolean existById(int idEstadoDespacho) {
        if(repository.findById(idEstadoDespacho).isPresent()){
            return true;
        }
        return false;
    }

}
