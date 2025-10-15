package com.bancolombia.arka_ecommerce.application.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

@ExtendWith(MockitoExtension.class)
class ProveedorApplicationServiceTest {

    @Mock
    private com.bancolombia.arka_ecommerce.domain.port.out.ProveedorRepositoryPort repo;

    @InjectMocks
    private ProveedorApplicationService service;

    @Test
    void getAllProveedores_delegates() {
        when(repo.findAll()).thenReturn(Collections.emptyList());
        assertNotNull(service.getAllProveedores());
        verify(repo).findAll();
    }

    @Test
    void deleteProveedorById_whenExists_deletes() {
        when(repo.existsById(3)).thenReturn(true);
        doNothing().when(repo).deleteById(3);
        assertTrue(service.deleteProveedorById(3));
        verify(repo).deleteById(3);
    }
}
