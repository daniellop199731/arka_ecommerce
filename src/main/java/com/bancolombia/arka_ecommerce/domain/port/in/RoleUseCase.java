package com.bancolombia.arka_ecommerce.domain.port.in;

import java.util.List;
import java.util.Optional;

import com.bancolombia.arka_ecommerce.domain.model.Role;

public interface RoleUseCase {
    
    Role createRole(Role role);
    List<Role> getAllRoles();
    Optional<Role> getRoleByName(String name);
}
