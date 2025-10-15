package com.bancolombia.arka_ecommerce.application.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.bancolombia.arka_ecommerce.domain.model.Role;
import com.bancolombia.arka_ecommerce.domain.port.out.RoleRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RoleApplicationServiceTest {

    @Mock
    private RoleRepositoryPort roleRepository;

    @InjectMocks
    private RoleApplicationService roleService;

    private Role roleAdmin;
    private Role roleUser;

    @BeforeEach
    void setUp() {
        roleAdmin = new Role();
        roleAdmin.setId(1);
        roleAdmin.setName("ADMIN");

        roleUser = new Role();
        roleUser.setId(2);
        roleUser.setName("USER");
    }

    @Test
    void shouldCreateRoleSuccessfully() {
        when(roleRepository.save(roleAdmin)).thenReturn(roleAdmin);

        Role result = roleService.createRole(roleAdmin);

        assertNotNull(result);
        assertEquals("ADMIN", result.getName());
        verify(roleRepository).save(roleAdmin);
    }

    @Test
    void shouldReturnAllRoles() {
        List<Role> roles = Arrays.asList(roleAdmin, roleUser);
        when(roleRepository.findAll()).thenReturn(roles);

        List<Role> result = roleService.getAllRoles();

        assertEquals(2, result.size());
        assertEquals("USER", result.get(1).getName());
        verify(roleRepository).findAll();
    }

    @Test
    void shouldReturnRoleByNameWhenExists() {
        when(roleRepository.findByName("ADMIN")).thenReturn(Optional.of(roleAdmin));

        Optional<Role> result = roleService.getRoleByName("ADMIN");

        assertTrue(result.isPresent());
        assertEquals("ADMIN", result.get().getName());
        verify(roleRepository).findByName("ADMIN");
    }

    @Test
    void shouldReturnEmptyOptionalWhenRoleNotFound() {
        when(roleRepository.findByName("UNKNOWN")).thenReturn(Optional.empty());

        Optional<Role> result = roleService.getRoleByName("UNKNOWN");

        assertTrue(result.isEmpty());
        verify(roleRepository).findByName("UNKNOWN");
    }
}