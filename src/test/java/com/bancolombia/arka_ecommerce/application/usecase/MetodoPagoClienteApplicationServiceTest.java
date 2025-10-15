package com.bancolombia.arka_ecommerce.application.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bancolombia.arka_ecommerce.application.utils.ResponseObject;
import com.bancolombia.arka_ecommerce.domain.model.*;
import com.bancolombia.arka_ecommerce.domain.port.out.*;

@ExtendWith(MockitoExtension.class)
class MetodoPagoClienteApplicationServiceTest {

    @Mock
    private MetodoPagoClienteRepositoryPort metodoPagoClienteRepositoryPort;

    @Mock
    private MetodoPagoRepositoryPort metodoPagoRepositoryPort;

    @Mock
    private ClienteRepositoryPort clienteRepositoryPort;

    @InjectMocks
    private MetodoPagoClienteApplicationService service;

    private Cliente cliente;
    private MetodoPago metodoPago;
    private MetodoPagoCliente metodoPagoCliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setIdCliente(1);

        metodoPago = new MetodoPago();
        metodoPago.setIdMetodoPago(2);

        metodoPagoCliente = new MetodoPagoCliente();
        metodoPagoCliente.setId(10);
        metodoPagoCliente.setClienteMetodoPago(cliente);
        metodoPagoCliente.setMetodoPago(metodoPago);
        metodoPagoCliente.setValorCuentaMetodoPago(100.0);
    }

    // ---- getMetodosPagoByIdCliente ----
    @Test
    void getMetodosPagoByIdCliente_deberiaRetornarLista() {
        when(metodoPagoClienteRepositoryPort.findByClienteMetodoPago(any(Cliente.class)))
            .thenReturn(List.of(metodoPagoCliente));

        List<MetodoPagoCliente> result = service.getMetodosPagoByIdCliente(1);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getClienteMetodoPago().getIdCliente());
        verify(metodoPagoClienteRepositoryPort).findByClienteMetodoPago(any(Cliente.class));
    }

    // ---- createMetodoPagoCliente ----
    @Test
    void createMetodoPagoCliente_clienteNoExiste() {
        when(clienteRepositoryPort.existsById(1)).thenReturn(false);

        ResponseObject<MetodoPagoCliente> result = service.createMetodoPagoCliente(1, 2);

        assertFalse(result.isSuccessful());
        assertEquals(result.getMessageAsString(), "No existe el Cliente con id " + 1);
    }

    @Test
    void createMetodoPagoCliente_metodoPagoNoExiste() {
        when(clienteRepositoryPort.existsById(1)).thenReturn(true);
        when(metodoPagoRepositoryPort.existsById(2)).thenReturn(false);

        ResponseObject<MetodoPagoCliente> result = service.createMetodoPagoCliente(1, 2);

        assertFalse(result.isSuccessful());
        assertEquals("No existe el Metodo de pago con id " + 2, result.getMessageAsString());
    }

    @Test
    void createMetodoPagoCliente_asignacionYaExiste() {
        when(clienteRepositoryPort.existsById(1)).thenReturn(true);
        when(metodoPagoRepositoryPort.existsById(2)).thenReturn(true);
        when(metodoPagoClienteRepositoryPort.existByIdClienteAndMetodoPago(1, 2)).thenReturn(true);

        ResponseObject<MetodoPagoCliente> result = service.createMetodoPagoCliente(1, 2);

        assertFalse(result.isSuccessful());
        assertEquals("Ya existe la asignacion", result.getMessageAsString());
    }

    @Test
    void createMetodoPagoCliente_exito() {
        when(clienteRepositoryPort.existsById(1)).thenReturn(true);
        when(metodoPagoRepositoryPort.existsById(2)).thenReturn(true);
        when(metodoPagoClienteRepositoryPort.existByIdClienteAndMetodoPago(1, 2)).thenReturn(false);
        when(metodoPagoClienteRepositoryPort.save(any(MetodoPagoCliente.class))).thenReturn(metodoPagoCliente);

        ResponseObject<MetodoPagoCliente> result = service.createMetodoPagoCliente(1, 2);

        assertTrue(result.isSuccessful());
        assertEquals("Se agrego el metodo de pago al Cliente con exito", result.getMessage());
        assertNotNull(result.getObj());
        verify(metodoPagoClienteRepositoryPort).save(any(MetodoPagoCliente.class));
    }

    // ---- manageMetodoPagoCliente ----
    @Test
    void manageMetodoPagoCliente_metodoPagoNoExiste() {
        when(metodoPagoRepositoryPort.existsById(2)).thenReturn(false);

        ResponseObject<MetodoPagoCliente> result = service.manageMetodoPagoCliente(1, 2, 100);

        assertFalse(result.isSuccessful());
        assertEquals("No existe el Metodo de pago con id " + 2, result.getMessageAsString());
    }

    @Test
    void manageMetodoPagoCliente_clienteNoExiste() {
        when(metodoPagoRepositoryPort.existsById(2)).thenReturn(true);
        when(clienteRepositoryPort.existsById(1)).thenReturn(false);

        ResponseObject<MetodoPagoCliente> result = service.manageMetodoPagoCliente(1, 2, 100);

        assertFalse(result.isSuccessful());
        assertEquals("No existe el Cliente de pago con id " + 1, result.getMessageAsString());
    }

    @Test
    void manageMetodoPagoCliente_valorInvalido() {
        when(metodoPagoRepositoryPort.existsById(2)).thenReturn(true);
        when(clienteRepositoryPort.existsById(1)).thenReturn(true);

        ResponseObject<MetodoPagoCliente> result = service.manageMetodoPagoCliente(1, 2, 0);

        assertFalse(result.isSuccessful());
        assertEquals("El valor a cargar debe ser mayor a cero", result.getMessageAsString());
    }

    @Test
    void manageMetodoPagoCliente_creaNuevo_exito() {
        when(metodoPagoRepositoryPort.existsById(2)).thenReturn(true);
        when(clienteRepositoryPort.existsById(1)).thenReturn(true);
        when(metodoPagoClienteRepositoryPort.findByIdClienteAndIdMetodoPago(1, 2)).thenReturn(Optional.empty());
        when(metodoPagoClienteRepositoryPort.save(any(MetodoPagoCliente.class)))
            .thenReturn(metodoPagoCliente);

        ResponseObject<MetodoPagoCliente> result = service.manageMetodoPagoCliente(1, 2, 50);

        assertTrue(result.isSuccessful());
        assertEquals("Se agrego el metodo de pago al Cliente con exito", result.getMessage());
        verify(metodoPagoClienteRepositoryPort).save(any(MetodoPagoCliente.class));
    }

    @Test
    void manageMetodoPagoCliente_actualizaExistente_exito() {
        MetodoPagoCliente existente = new MetodoPagoCliente();
        existente.setId(10);
        existente.setValorCuentaMetodoPago(100.0);

        when(metodoPagoRepositoryPort.existsById(2)).thenReturn(true);
        when(clienteRepositoryPort.existsById(1)).thenReturn(true);
        when(metodoPagoClienteRepositoryPort.findByIdClienteAndIdMetodoPago(1, 2))
            .thenReturn(Optional.of(existente));
        when(metodoPagoClienteRepositoryPort.save(any(MetodoPagoCliente.class)))
            .thenReturn(metodoPagoCliente);

        ResponseObject<MetodoPagoCliente> result = service.manageMetodoPagoCliente(1, 2, 50);

        assertTrue(result.isSuccessful());
        assertEquals("Se realizó la actualizacíon con exíto", result.getMessage());
        verify(metodoPagoClienteRepositoryPort).save(any(MetodoPagoCliente.class));
    }

    // ---- deleteByIdClienteAndMetodoPago ----
    @Test
    void deleteByIdClienteAndMetodoPago_existente_eliminaYRetornaTrue() {
        when(metodoPagoClienteRepositoryPort.findByIdClienteAndIdMetodoPago(1, 2))
            .thenReturn(Optional.of(metodoPagoCliente));

        boolean result = service.deleteByIdClienteAndMetodoPago(1, 2);

        assertTrue(result);
        verify(metodoPagoClienteRepositoryPort).deleteById(10);
    }

    @Test
    void deleteByIdClienteAndMetodoPago_noExiste_retornaFalse() {
        when(metodoPagoClienteRepositoryPort.findByIdClienteAndIdMetodoPago(1, 2))
            .thenReturn(Optional.empty());

        boolean result = service.deleteByIdClienteAndMetodoPago(1, 2);

        assertFalse(result);
        verify(metodoPagoClienteRepositoryPort, never()).deleteById(anyInt());
    }
}