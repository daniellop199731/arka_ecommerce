package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.domain.model.Role;
import com.bancolombia.arka_ecommerce.domain.port.out.RoleRepositoryPort;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.RoleEntity;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.mapper.RoleMapper;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.repository.RoleJpaRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RolePersistenceAdapter implements RoleRepositoryPort{

    private final RoleMapper roleMapper;
    private final RoleJpaRepository repository;

    @Override
    public Role save(Role role) {
        return roleMapper.toModel(repository.save(roleMapper.toEntity(role)));
    }

    @Override
    public List<Role> findAll() {
        List<RoleEntity> roleEntities = (List<RoleEntity>)repository.findAll();
        return roleEntities.stream()
            .map(roleMapper::toModel)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Role> findById(int id) {
        Optional<RoleEntity> roleOptional = repository.findById(id);
        return roleOptional.map(roleMapper::toModel);
    }

    @Override
    public Optional<Role> findByName(String name) {
        Optional<RoleEntity> roleOptional = repository.findByName(name);
        return roleOptional.map(roleMapper::toModel);
    }
    
}
