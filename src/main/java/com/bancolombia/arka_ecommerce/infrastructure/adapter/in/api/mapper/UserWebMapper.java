package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.mapper;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.domain.model.User;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto.UserDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserWebMapper {

    private final RoleWebMapper roleWebMapper;


    public User toModel(UserDto userDto){
        if(userDto == null){
            return null;
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setAdmin(userDto.isAdmin());
        return user;
    }

    public UserDto toDto(User user){
        if(user == null){
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setPassword("************");
        userDto.setUsername(user.getUsername());
        userDto.setEnabled(user.isEnabled());
        userDto.setRoles(user.getRoles().stream().map(roleWebMapper::toDto).toList());
        return userDto;
    }

}
