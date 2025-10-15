package com.bancolombia.arka_ecommerce.application.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bancolombia.arka_ecommerce.domain.model.CarritoCompra;
import com.bancolombia.arka_ecommerce.domain.model.CarritoCompraProducto;
import com.bancolombia.arka_ecommerce.domain.port.out.CarritoCompraProductoRepositoryPort;

@ExtendWith(MockitoExtension.class)
class CarritoCompraProductoApplicationServiceTest {

    @Mock
    private CarritoCompraProductoRepositoryPort repository;

    @InjectMocks
    private CarritoCompraProductoApplicationService service;

    private CarritoCompra carrito;
    private CarritoCompraProducto relacion;

    @BeforeEach
    void setUp() {
        carrito = new CarritoCompra();
        carrito.setIdCarritoCompra(5);

        relacion = new CarritoCompraProducto();
        relacion.setCarritoCompra(carrito);
        relacion.setUnidadesProducto(2);
    }

    // ---- obtenerProductosCarrito ----
    @Test
    void obtenerProductosCarrito_deberiaRetornarListaDeProductos() {
        when(repository.findByCarritoCompra(any(CarritoCompra.class)))
            .thenReturn(List.of(relacion));

        List<CarritoCompraProducto> result = service.obtenerProductosCarrito(5);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(5, result.get(0).getCarritoCompra().getIdCarritoCompra());
        verify(repository).findByCarritoCompra(any(CarritoCompra.class));
    }

    @Test
    void obtenerProductosCarrito_sinProductos_retornaListaVacia() {
        when(repository.findByCarritoCompra(any(CarritoCompra.class)))
            .thenReturn(List.of());

        List<CarritoCompraProducto> result = service.obtenerProductosCarrito(99);

        assertTrue(result.isEmpty());
        verify(repository).findByCarritoCompra(any(CarritoCompra.class));
    }

    // ---- agregarRelacionCarritoProducto ----
    @Test
    void agregarRelacionCarritoProducto_deberiaGuardarYRetornarRelacion() {
        CarritoCompraProducto esperado = new CarritoCompraProducto(5, 10, 3);
        when(repository.save(any(CarritoCompraProducto.class))).thenReturn(esperado);

        CarritoCompraProducto result = service.agregarRelacionCarritoProducto(5, 10, 3);

        assertNotNull(result);
        assertEquals(5, result.getCarritoCompra().getIdCarritoCompra());
        assertEquals(10, result.getProductoCarritoCompra().getIdProducto());
        assertEquals(3, result.getUnidadesProducto());
        verify(repository).save(any(CarritoCompraProducto.class));
    }

    // ---- eliminarProductosCarrito ----
    @Test
    void eliminarProductosCarrito_noImplementado_lanzaExcepcion() {
        UnsupportedOperationException ex = assertThrows(
            UnsupportedOperationException.class,
            () -> service.eliminarProductosCarrito(5)
        );

        assertEquals("Unimplemented method 'eliminarProductosCarrito'", ex.getMessage());
    }
}
