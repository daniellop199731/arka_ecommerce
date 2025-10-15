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
class MetodoPagoApplicationServiceTest {

    @Mock
    private com.bancolombia.arka_ecommerce.domain.port.out.MetodoPagoRepositoryPort metodoRepo;

    @InjectMocks
    private MetodoPagoApplicationService service;

    @Test
    void getMetodosPago_shouldDelegate() {
        when(metodoRepo.findAll()).thenReturn(Collections.emptyList());
        assertNotNull(service.getMetodosPago());
        verify(metodoRepo).findAll();
    }

    @Test
    void deleteMetodoPagoById_whenExists_shouldDelete() {
        when(metodoRepo.existsById(5)).thenReturn(true);
        doNothing().when(metodoRepo).deleteById(5);
        assertTrue(service.deleteMetodoPagoById(5));
        verify(metodoRepo).deleteById(5);
    }
}
