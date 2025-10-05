package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.bancolombia.arka_ecommerce.domain.model.Cliente;
import com.bancolombia.arka_ecommerce.domain.port.out.ClienteRepositoryPort;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.ClienteEntity;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.mapper.ClienteMapper;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.repository.ClienteJpaRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ClientePersistenceAdapter implements ClienteRepositoryPort {

    private final ClienteJpaRepository repository;
    private final ClienteMapper mapper;

    @Override
    public List<Cliente> findAll() {
        List<ClienteEntity> ClientesEntities = (List<ClienteEntity>) repository.findAll();
        return ClientesEntities.stream()
            .map(mapper::toModel)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Cliente> findById(int idCliente) {
        Optional<ClienteEntity> ClienteEntity = repository.findById(idCliente);
        return ClienteEntity.map(mapper::toModel);
    }

    @Override
    public Cliente save(Cliente Cliente) {
        ClienteEntity ClienteEntity = mapper.toEntity(Cliente);
        if(ClienteEntity.getIdCliente() == 0){
            ClienteEntity.setIdCliente(null);
        }
        ClienteEntity ClienteEntitySaved = repository.save(ClienteEntity);
        return mapper.toModel(ClienteEntitySaved);
    }

    @Override
    public void deleteById(int idCliente) {
        repository.deleteById(idCliente);
        
    }

    @Override
    public boolean existsById(int idCliente) {
        Optional<ClienteEntity> ClienteFinded = repository.findById(idCliente);
        if(ClienteFinded.isPresent()){
            return true;
        }
        return false;
    }

    @Override
    public Optional<Cliente> findByIdentificacion(String identificacionCliente) {
        Optional<ClienteEntity> ClienteEntity = repository.findByIdentificacionCliente(identificacionCliente);
        return ClienteEntity.map(mapper::toModel);
    }

}

