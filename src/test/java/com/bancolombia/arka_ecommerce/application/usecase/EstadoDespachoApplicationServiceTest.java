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
class EstadoDespachoApplicationServiceTest {

    @Mock
    private com.bancolombia.arka_ecommerce.domain.port.out.EstadoDespachoRepositoryPort repo;

    @InjectMocks
    private EstadoDespachoApplicationService service;

    @Test
    void getAllEstadosDespacho_delegates() {
        when(repo.findAll()).thenReturn(Collections.emptyList());
        assertNotNull(service.getAllEstadosDespacho());
        verify(repo).findAll();
    }
}
