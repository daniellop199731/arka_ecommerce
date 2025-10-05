package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_ecommerce.domain.model.User;
import com.bancolombia.arka_ecommerce.domain.port.in.UserUseCase;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto.UserDto;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.mapper.UserWebMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserUseCase userUseCase;
    private final UserWebMapper userWebMapper;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userUseCase.getAllUsers();
        List<UserDto> usersDto = users.stream().map(userWebMapper::toDto).toList();
        return ResponseEntity.ok().body(usersDto);
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto) {
        User userCreated = userUseCase.createUser(userWebMapper.toModel(userDto));  
        UserDto userCreatedDto = userWebMapper.toDto(userCreated);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreatedDto); 
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserDto userDto) {
        userDto.setAdmin(false);
        return create(userDto);
    }    
    
    

}
