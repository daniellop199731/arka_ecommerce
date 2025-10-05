package com.bancolombia.arka_ecommerce.application.usecase;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bancolombia.arka_ecommerce.domain.model.Role;
import com.bancolombia.arka_ecommerce.domain.port.in.RoleUseCase;
import com.bancolombia.arka_ecommerce.domain.port.out.RoleRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleApplicationService implements RoleUseCase {

    private final RoleRepositoryPort roleRepository;

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> getRoleByName(String name) {
        return roleRepository.findByName(name);
    }
    
}
