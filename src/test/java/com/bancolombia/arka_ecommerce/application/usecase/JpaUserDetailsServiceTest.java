package com.bancolombia.arka_ecommerce.application.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class JpaUserDetailsServiceTest {

    @Mock
    private com.bancolombia.arka_ecommerce.domain.port.out.UserRepositoryPort userRepository;

    @InjectMocks
    private JpaUserDetailsService service;

    @Test
    void loadUserByUsername_whenUserExists_returnsUserDetails() {
        com.bancolombia.arka_ecommerce.domain.model.User u = new com.bancolombia.arka_ecommerce.domain.model.User();
        u.setUsername("marta");
        u.setPassword("pwd");
        u.setEnabled(true);
        com.bancolombia.arka_ecommerce.domain.model.Role r = new com.bancolombia.arka_ecommerce.domain.model.Role();
        r.setName("ROLE_USER");
        u.setRoles(Arrays.asList(r));

        when(userRepository.findByUsername("marta")).thenReturn(Optional.of(u));

        org.springframework.security.core.userdetails.UserDetails ud = service.loadUserByUsername("marta");

        assertNotNull(ud);
        assertEquals("marta", ud.getUsername());
        verify(userRepository).findByUsername("marta");
    }
}
