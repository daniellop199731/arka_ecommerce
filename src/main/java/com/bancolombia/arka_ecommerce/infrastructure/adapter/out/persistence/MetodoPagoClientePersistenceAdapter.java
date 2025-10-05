package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.domain.model.MetodoPagoCliente;
import com.bancolombia.arka_ecommerce.domain.model.Cliente;
import com.bancolombia.arka_ecommerce.domain.port.out.MetodoPagoClienteRepositoryPort;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.MetodoPagoClienteEntity;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.mapper.MetodoPagoClienteMapper;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.mapper.ClienteMapper;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.repository.MetodoPagoClienteJR;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MetodoPagoClientePersistenceAdapter implements MetodoPagoClienteRepositoryPort {

    private final MetodoPagoClienteJR repository;
    private final MetodoPagoClienteMapper metodoPagoClienteMapper;
    private final ClienteMapper ClienteMapper;

    @Override
    public List<MetodoPagoCliente> findByClienteMetodoPago(Cliente Cliente) {
        List<MetodoPagoClienteEntity> metodoPagoClienteEntities = repository.findByClienteMetodoPago(ClienteMapper.toEntity(Cliente));
        return metodoPagoClienteEntities.stream()
                .map(metodoPagoClienteMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public MetodoPagoCliente save(MetodoPagoCliente metodoPagoCliente) {
        MetodoPagoClienteEntity metodoPagoClienteEntity = metodoPagoClienteMapper.toEntity(metodoPagoCliente);
        if(metodoPagoCliente.getId() == 0){
            metodoPagoClienteEntity.setId(null);
        }
        repository.save(metodoPagoClienteEntity);
        return metodoPagoClienteMapper.toModel(metodoPagoClienteEntity);
    }

    @Override
    public Optional<MetodoPagoCliente> findByIdClienteAndIdMetodoPago(int idClienteMetodoPago, int idMetodoPago){
        return repository.findByClienteAndMetodoPago(idClienteMetodoPago, idMetodoPago).map(metodoPagoClienteMapper::toModel);
    }

    @Override
    public boolean existByIdClienteAndMetodoPago(int idClienteMetodoPago, int idMetodoPago) {
        return repository.findByClienteAndMetodoPago(idClienteMetodoPago, idMetodoPago).isPresent();
    }

    @Override
    public void deleteById(int idMetodoPagoCliente) {
        repository.deleteById(idMetodoPagoCliente);
    }

}
