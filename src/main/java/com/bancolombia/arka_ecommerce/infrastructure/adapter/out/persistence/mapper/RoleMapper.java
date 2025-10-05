package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.domain.model.Role;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.RoleEntity;

@Component
public class RoleMapper {
    
    public Role toModel(RoleEntity roleEntity){
        if(roleEntity == null){
            return null;
        }
        Role role = new Role();
        role.setId(roleEntity.getId());
        role.setName(roleEntity.getName());
        return role;
    }

    public RoleEntity toEntity(Role role){
        if(role == null){
            return null;
        }
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(role.getId());
        roleEntity.setName(role.getName());
        return roleEntity;
    }
}
