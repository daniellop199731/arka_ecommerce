package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.domain.model.User;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.UserEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final RoleMapper roleMapper;

    public User toModel(UserEntity userEntity){
        if(userEntity == null){
            return null;
        }
        User user = new User();
        user.setId(userEntity.getId());
        user.setUsername(userEntity.getUsername());
        user.setPassword(userEntity.getPassword());
        user.setEnabled(userEntity.isEnabled());
        user.setRoles(userEntity.getRoles().stream().map(roleMapper::toModel).toList());
        return user;
    }

    public UserEntity toEntity(User user){
        if(user == null){
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.setEnabled(user.isEnabled());
        userEntity.setRoles(user.getRoles().stream().map(roleMapper::toEntity).toList());
        return userEntity;
    }
    
}
