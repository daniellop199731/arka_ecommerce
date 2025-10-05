package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.domain.model.User;
import com.bancolombia.arka_ecommerce.domain.port.out.UserRepositoryPort;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.UserEntity;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.mapper.UserMapper;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.repository.UserJpaRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserRepositoryPort{

    private final UserMapper userMapper;
    private final UserJpaRepository repository;

    @Override
    public User save(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        if(userEntity.getId() == 0){
            userEntity.setId(null);
        }
        return userMapper.toModel(repository.save(userEntity));
    }

    @Override
    public List<User> findAll() {
        List<UserEntity> userEntities = (List<UserEntity>)repository.findAll();
        return userEntities.stream()
            .map(userMapper::toModel)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(int id) {
        Optional<UserEntity> userOptional = repository.findById(id);
        return userOptional.map(userMapper::toModel);
    }

    @Override
    public boolean existByUserName(String userNamer) {
        return repository.existsByUsername(userNamer);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Optional<UserEntity> userOptional = repository.findByUsername(username);
        return userOptional.map(userMapper::toModel);
    }
    
}
