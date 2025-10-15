package com.bancolombia.arka_ecommerce.application.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bancolombia.arka_ecommerce.domain.model.Role;
import com.bancolombia.arka_ecommerce.domain.model.User;
import com.bancolombia.arka_ecommerce.domain.port.in.RoleUseCase;
import com.bancolombia.arka_ecommerce.domain.port.out.UserRepositoryPort;

@ExtendWith(MockitoExtension.class)
class UserApplicationServiceTest {

    @Mock
    private UserRepositoryPort userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleUseCase roleUseCase;

    @InjectMocks
    private UserApplicationService service;

    private User user;

    @BeforeEach
    void setup() {
        user = new User();
        user.setUsername("testuser");
        user.setPassword("1234");
    }

    @Test
    void createUser_shouldAddUserRoleAndEncodePassword() {
        Role userRole = new Role();
        userRole.setName("ROLE_USER");

        when(roleUseCase.getRoleByName("ROLE_USER")).thenReturn(Optional.of(userRole));
        when(passwordEncoder.encode("1234")).thenReturn("encodedPass");
        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        User result = service.createUser(user);

        assertTrue(result.isEnabled());
        assertEquals("encodedPass", result.getPassword());
        assertEquals(1, result.getRoles().size());
        assertEquals("ROLE_USER", result.getRoles().get(0).getName());
        verify(userRepository).save(result);
    }

    @Test
    void createUser_shouldAddUserAndAdminRolesWhenIsAdmin() {
        user.setAdmin(true);

        Role userRole = new Role();
        userRole.setName("ROLE_USER");

        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");

        when(roleUseCase.getRoleByName("ROLE_USER")).thenReturn(Optional.of(userRole));
        when(roleUseCase.getRoleByName("ROLE_ADMIN")).thenReturn(Optional.of(adminRole));
        when(passwordEncoder.encode(anyString())).thenReturn("encoded");
        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        User result = service.createUser(user);

        assertEquals(2, result.getRoles().size());
        assertTrue(result.getRoles().stream().anyMatch(r -> r.getName().equals("ROLE_ADMIN")));
        verify(roleUseCase, times(1)).getRoleByName("ROLE_USER");
        verify(roleUseCase, times(1)).getRoleByName("ROLE_ADMIN");
    }

    @Test
    void createUser_shouldHandleMissingRolesGracefully() {
        user.setAdmin(true);

        when(roleUseCase.getRoleByName("ROLE_USER")).thenReturn(Optional.empty());
        when(roleUseCase.getRoleByName("ROLE_ADMIN")).thenReturn(Optional.empty());
        when(passwordEncoder.encode(anyString())).thenReturn("encoded");
        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        User result = service.createUser(user);

        assertNotNull(result);
        assertTrue(result.getRoles().isEmpty());
        assertEquals("encoded", result.getPassword());
    }

    @Test
    void getAllUsers_shouldReturnListFromRepository() {
        when(userRepository.findAll()).thenReturn(List.of(new User(), new User()));

        List<User> result = service.getAllUsers();

        assertEquals(2, result.size());
        verify(userRepository).findAll();
    }

    @Test
    void existsByUserName_shouldReturnTrueIfExists() {
        when(userRepository.existByUserName("testuser")).thenReturn(true);

        boolean result = service.existsByUserName("testuser");

        assertTrue(result);
        verify(userRepository).existByUserName("testuser");
    }

    @Test
    void existsByUserName_shouldReturnFalseIfNotExists() {
        when(userRepository.existByUserName("unknown")).thenReturn(false);

        boolean result = service.existsByUserName("unknown");

        assertFalse(result);
        verify(userRepository).existByUserName("unknown");
    }
}