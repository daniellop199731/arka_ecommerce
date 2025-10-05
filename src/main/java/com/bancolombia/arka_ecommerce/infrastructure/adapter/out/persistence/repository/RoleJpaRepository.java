package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.RoleEntity;

public interface RoleJpaRepository extends CrudRepository<RoleEntity, Integer>{
    
    Optional<RoleEntity> findByName(String name);

}
