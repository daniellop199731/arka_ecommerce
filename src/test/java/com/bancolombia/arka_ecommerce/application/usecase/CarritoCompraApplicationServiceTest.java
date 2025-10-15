package com.bancolombia.arka_ecommerce.application.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bancolombia.arka_ecommerce.domain.model.*;
import com.bancolombia.arka_ecommerce.domain.port.in.ClienteUserCase;
import com.bancolombia.arka_ecommerce.domain.port.in.ProductoUseCase;
import com.bancolombia.arka_ecommerce.domain.port.out.CarritoCompraProductoRepositoryPort;
import com.bancolombia.arka_ecommerce.domain.port.out.CarritoCompraRepositoryPort;

@ExtendWith(MockitoExtension.class)
class CarritoCompraApplicationServiceTest {

    @Mock
    private CarritoCompraRepositoryPort repositoryPort;

    @Mock
    private CarritoCompraProductoRepositoryPort carritoCompraProductoRepositoryPort;

    @Mock
    private ClienteUserCase clienteUserCase;

    @Mock
    private ProductoUseCase productoUseCase;

    @InjectMocks
    private CarritoCompraApplicationService service;

    private Cliente cliente;
    private CarritoCompra carritoExistente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setIdCliente(1);

        carritoExistente = new CarritoCompra();
        carritoExistente.setIdCarritoCompra(10);
        carritoExistente.setClienteCarritoCompra(cliente);
    }

    // ---- crearNuevo ----
    /*@Test
    void crearNuevo_clienteSinCarrito_creaNuevoCarrito() {
        when(repositoryPort.findCarritoActual(1)).thenReturn(new ArrayList<>());
        when(clienteUserCase.getClienteById(1)).thenReturn(cliente);
        when(service.carritoActual(1)).thenReturn(new CarritoCompra());
        when(repositoryPort.save(any(CarritoCompra.class))).thenAnswer(inv -> {
            CarritoCompra c = inv.getArgument(0);
            c.setIdCarritoCompra(99);
            return c;
        });

        CarritoCompra result = service.crearNuevo(1);

        assertNotNull(result);
        assertEquals(99, result.getIdCarritoCompra());
        verify(repositoryPort).save(any(CarritoCompra.class));
    }

    @Test
    void crearNuevo_clienteConCarritoExistente_noCreaNuevo() {
        when(clienteUserCase.getClienteById(1)).thenReturn(cliente);
        when(service.carritoActual(1)).thenReturn(carritoExistente);

        CarritoCompra result = service.crearNuevo(1);

        assertEquals(carritoExistente, result);
        verify(repositoryPort, never()).save(any());
    }*/

    // ---- obtenerCarritoPorId ----
    @Test
    void obtenerCarritoPorId_existente_devuelveCarrito() {
        when(repositoryPort.findById(10)).thenReturn(Optional.of(carritoExistente));

        CarritoCompra result = service.obtenerCarritoPorId(10);

        assertEquals(10, result.getIdCarritoCompra());
        verify(repositoryPort).findById(10);
    }

    @Test
    void obtenerCarritoPorId_noExistente_devuelveNuevo() {
        when(repositoryPort.findById(5)).thenReturn(Optional.empty());

        CarritoCompra result = service.obtenerCarritoPorId(5);

        assertEquals(0, result.getIdCarritoCompra());
    }

    // ---- carritosPorUsuario ----
    @Test
    void carritosPorUsuario_clienteValido_devuelveLista() {
        when(clienteUserCase.getClienteById(1)).thenReturn(cliente);
        when(repositoryPort.findByClienteCarritoCompra(cliente))
            .thenReturn(List.of(carritoExistente));

        List<CarritoCompra> result = service.carritosPorUsuario(1);

        assertEquals(1, result.size());
        verify(repositoryPort).findByClienteCarritoCompra(cliente);
    }

    @Test
    void carritosPorUsuario_clienteInvalido_devuelveListaVacia() {
        Cliente clienteInvalido = new Cliente();
        clienteInvalido.setIdCliente(0);
        when(clienteUserCase.getClienteById(2)).thenReturn(clienteInvalido);

        List<CarritoCompra> result = service.carritosPorUsuario(2);

        assertTrue(result.isEmpty());
        verify(repositoryPort, never()).findByClienteCarritoCompra(any());
    }

    // ---- carritoActual ----
    @Test
    void carritoActual_listaConElemento_devuelvePrimero() {
        when(repositoryPort.findCarritoActual(1))
            .thenReturn(List.of(carritoExistente));

        CarritoCompra result = service.carritoActual(1);

        assertEquals(10, result.getIdCarritoCompra());
    }

    @Test
    void carritoActual_listaVacia_devuelveNuevo() {
        when(repositoryPort.findCarritoActual(1)).thenReturn(Collections.emptyList());

        CarritoCompra result = service.carritoActual(1);

        assertEquals(0, result.getIdCarritoCompra());
    }

    // ---- carritosAbandonados ----
    @Test
    void carritosAbandonados_delegaEnRepositorio() {
        when(repositoryPort.findcarritosAbandonados()).thenReturn(List.of(carritoExistente));

        List<CarritoCompra> result = service.carritosAbandonados();

        assertEquals(1, result.size());
        verify(repositoryPort).findcarritosAbandonados();
    }

    // ---- carritoComprasPorFechas ----
    @Test
    void carritoComprasPorFechas_delegaEnRepositorio() {
        Date start = new Date();
        Date end = new Date();
        when(repositoryPort.findByFechaCreacionCarritoCompraBetween(start, end))
            .thenReturn(List.of(carritoExistente));

        List<CarritoCompra> result = service.carritoComprasPorFechas(start, end);

        assertEquals(1, result.size());
        verify(repositoryPort).findByFechaCreacionCarritoCompraBetween(start, end);
    }

    // ---- agregarProductos ----
    @Test
    void agregarProductos_conProductosValidos_guardaYReduceStock() {
        Producto producto = new Producto();
        producto.setIdProducto(10);

        CarritoCompraProducto ccp = new CarritoCompraProducto();
        ccp.setProductoCarritoCompra(producto);
        ccp.setUnidadesProducto(2);

        List<CarritoCompraProducto> input = List.of(ccp);

        CarritoCompra carrito = new CarritoCompra();
        carrito.setIdCarritoCompra(5);

        when(clienteUserCase.getClienteById(1)).thenReturn(cliente);
        when(productoUseCase.existeProducto(10)).thenReturn(true);
        when(carritoCompraProductoRepositoryPort.saveAllOriginal(any()))
            .thenAnswer(inv -> inv.getArgument(0));
        when(service.crearNuevo(1)).thenReturn(carrito);


        List<CarritoCompraProducto> result = service.agregarProductos(1, input);

        assertEquals(1, result.size());
        verify(productoUseCase).decreaseStock(10, 2);
        verify(carritoCompraProductoRepositoryPort).saveAllOriginal(any());
    }

    @Test
    void agregarProductos_carritoNoCreado_retornaVacio() {
        when(clienteUserCase.getClienteById(1)).thenReturn(cliente);
        when(service.crearNuevo(1)).thenReturn(new CarritoCompra());

        List<CarritoCompraProducto> result = service.agregarProductos(1, List.of(new CarritoCompraProducto()));

        assertTrue(result.isEmpty());
    }

    @Test
    void agregarProductos_listaVacia_retornaVacio() {
        CarritoCompra carrito = new CarritoCompra();
        carrito.setIdCarritoCompra(5);
        when(clienteUserCase.getClienteById(1)).thenReturn(cliente);
        when(service.crearNuevo(1)).thenReturn(carrito);

        List<CarritoCompraProducto> result = service.agregarProductos(1, new ArrayList<>());

        assertTrue(result.isEmpty());
    }
}
