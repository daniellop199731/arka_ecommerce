package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.domain.model.Proveedor;
import com.bancolombia.arka_ecommerce.domain.port.out.ProveedorRepositoryPort;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.ProveedorEntity;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.mapper.ProveedorMapper;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.repository.ProveedorJpaRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProveedorPersistenceAdapter implements ProveedorRepositoryPort {

    private final ProveedorJpaRepository repository;
    private final ProveedorMapper mapper;

    @Override
    public List<Proveedor> findAll() {
        List<ProveedorEntity> proveedoresEntity = repository.findAll();
        List<Proveedor> proveedores = proveedoresEntity.stream()
                                        .map(mapper::toModel)
                                        .toList();
        return proveedores;
    }

    @Override
    public Optional<Proveedor> findById(int idProveedor) {
        return repository.findById(idProveedor).map(mapper::toModel);
    }

    @Override
    public Proveedor save(Proveedor proveedor) {
        ProveedorEntity proveedorEntity = mapper.toEntity(proveedor);
        if(proveedor.getIdProveedor() == 0){
            proveedorEntity.setIdProveedor(null);
        }
        return mapper.toModel(repository.save(proveedorEntity));
    }

    @Override
    public void deleteById(int idProveedor) {
        repository.deleteById(idProveedor);
    }

    @Override
    public boolean existsById(int idProveedor) {
        if(repository.findById(idProveedor).isPresent()){
            return true;
        }
        return false;
    }

}
