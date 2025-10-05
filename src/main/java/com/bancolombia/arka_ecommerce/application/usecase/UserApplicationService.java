package com.bancolombia.arka_ecommerce.application.usecase;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bancolombia.arka_ecommerce.domain.model.Role;
import com.bancolombia.arka_ecommerce.domain.model.User;
import com.bancolombia.arka_ecommerce.domain.port.in.RoleUseCase;
import com.bancolombia.arka_ecommerce.domain.port.in.UserUseCase;
import com.bancolombia.arka_ecommerce.domain.port.out.UserRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserApplicationService implements UserUseCase {

    private final UserRepositoryPort userRepository;
    private final PasswordEncoder passwordEncoder;

    private final RoleUseCase roleUseCase;

    @Override
    @Transactional
    public User createUser(User user) {
        //Siempre se otorga el rol USER
        user.setRoles(new ArrayList<>());
        Optional<Role> roleOptionalUser = roleUseCase.getRoleByName("ROLE_USER");
        //Si existe el rol USER en base de datos se agrega al usuario que se va a crear
        roleOptionalUser.ifPresent(user.getRoles()::add);

        //Si desde el request se indica que el usuario es admin
        if(user.isAdmin()){
            //Se busca el rol ADMIN en la base de datos
            Optional<Role> roleOptionalAdmin = roleUseCase.getRoleByName("ROLE_ADMIN");
            //Si existe el rol ADMIN se agrega al usuario que se va a crear
            roleOptionalAdmin.ifPresent(user.getRoles()::add);
        }
        //Se encripta la contraseña antes de guardarla en base de datos
        String passwordEncoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoded);
        user.setEnabled(true);
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean existsByUserName(String userName) {
        return userRepository.existByUserName(userName);
    }
    
}
