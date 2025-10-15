package com.bancolombia.arka_ecommerce.application.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ClienteApplicationServiceTest {

    @Mock
    private com.bancolombia.arka_ecommerce.domain.port.out.ClienteRepositoryPort clienteRepo;

    @InjectMocks
    private ClienteApplicationService service;

    @Test
    void getAllClientes_shouldDelegate() {
        when(clienteRepo.findAll()).thenReturn(Collections.emptyList());
        List<com.bancolombia.arka_ecommerce.domain.model.Cliente> res = service.getAllClientes();
        assertNotNull(res);
        verify(clienteRepo).findAll();
    }

    @Test
    void deleteCliente_whenExists_deletesAndReturnsTrue() {
        when(clienteRepo.existsById(10)).thenReturn(true);
        doNothing().when(clienteRepo).deleteById(10);
        boolean deleted = service.deleteCliente(10);
        assertTrue(deleted);
        verify(clienteRepo).deleteById(10);
    }

    @Test
    void getClienteByIdentificacion_whenNotFound_returnsNewCliente() {
        when(clienteRepo.findByIdentificacion("XYZ")).thenReturn(Optional.empty());
        com.bancolombia.arka_ecommerce.domain.model.Cliente c = service.getClienteByIdentificacion("XYZ");
        assertNotNull(c);
    }
}
