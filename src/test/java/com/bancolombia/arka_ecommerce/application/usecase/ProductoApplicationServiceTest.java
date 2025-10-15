package com.bancolombia.arka_ecommerce.application.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bancolombia.arka_ecommerce.domain.model.Producto;
import com.bancolombia.arka_ecommerce.domain.port.out.ProductoRepositoryPort;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto.ProductoDto;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.mapper.ProductoWebMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class ProductoApplicationServiceTest {

    @Mock
    private ProductoRepositoryPort productoRepositoryPort;

    @Mock
    private ProductoWebMapper productoWebMapper;

    @InjectMocks
    private ProductoApplicationService service;

    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = new Producto();
        producto.setIdProducto(1);
        producto.setNombreProducto("Laptop");
        producto.setPrecioProducto(1500.0);
        producto.setStockProducto(10);
        producto.setStockMinimoProducto(2);
    }

    @Test
    void shouldReturnAllProductos() {
        when(productoRepositoryPort.findAll()).thenReturn(Arrays.asList(producto));

        List<Producto> result = service.getAllProductos();

        assertEquals(1, result.size());
        assertEquals("Laptop", result.get(0).getNombreProducto());
        verify(productoRepositoryPort).findAll();
    }

    @Test
    void shouldReturnProductoByIdWhenExists() {
        when(productoRepositoryPort.existById(1)).thenReturn(true);
        when(productoRepositoryPort.findById(1)).thenReturn(Optional.of(producto));

        Producto result = service.getProductoById(1);

        assertEquals("Laptop", result.getNombreProducto());
        verify(productoRepositoryPort).existById(1);
        verify(productoRepositoryPort).findById(1);
    }

    @Test
    void shouldReturnEmptyProductoWhenNotExists() {
        when(productoRepositoryPort.existById(1)).thenReturn(false);

        Producto result = service.getProductoById(1);

        assertEquals(0, result.getIdProducto());
        verify(productoRepositoryPort).existById(1);
        verify(productoRepositoryPort, never()).findById(anyInt());
    }

    @Test
    void shouldCreateProductoSuccessfully() {
        when(productoRepositoryPort.save(producto)).thenReturn(producto);

        Producto result = service.createProducto(producto);

        assertEquals("Laptop", result.getNombreProducto());
        verify(productoRepositoryPort).save(producto);
    }

    @Test
    void shouldUpdateProductoWhenExists() {
        when(productoRepositoryPort.existById(1)).thenReturn(true);
        when(productoRepositoryPort.save(producto)).thenReturn(producto);

        Producto result = service.updateProducto(1, producto);

        assertEquals(1, result.getIdProducto());
        verify(productoRepositoryPort).existById(1);
        verify(productoRepositoryPort).save(producto);
    }

    @Test
    void shouldReturnEmptyProductoWhenUpdatingNonExistent() {
        when(productoRepositoryPort.existById(1)).thenReturn(false);

        Producto result = service.updateProducto(1, producto);

        assertEquals(0, result.getIdProducto());
        verify(productoRepositoryPort).existById(1);
        verify(productoRepositoryPort, never()).save(any());
    }

    @Test
    void shouldDeleteProductoWhenExists() {
        when(productoRepositoryPort.existById(1)).thenReturn(true);

        boolean result = service.deleteProducto(1);

        assertTrue(result);
        verify(productoRepositoryPort).deleteById(1);
    }

    @Test
    void shouldNotDeleteProductoWhenNotExists() {
        when(productoRepositoryPort.existById(1)).thenReturn(false);

        boolean result = service.deleteProducto(1);

        assertFalse(result);
        verify(productoRepositoryPort, never()).deleteById(anyInt());
    }

    @Test
    void shouldIncreaseStockWhenProductoExists() {
        when(productoRepositoryPort.findById(1)).thenReturn(Optional.of(producto));
        when(productoRepositoryPort.save(any())).thenReturn(producto);

        Producto result = service.increaseStock(1, 5);

        assertEquals(15, result.getStockProducto());
        verify(productoRepositoryPort).save(any());
    }

    @Test
    void shouldReturnEmptyProductoWhenIncreaseStockNotFound() {
        when(productoRepositoryPort.findById(1)).thenReturn(Optional.empty());

        Producto result = service.increaseStock(1, 5);

        assertEquals(0, result.getIdProducto());
        verify(productoRepositoryPort, never()).save(any());
    }

    @Test
    void shouldDecreaseStockWhenProductoExistsAndEnoughStock() {
        when(productoRepositoryPort.findById(1)).thenReturn(Optional.of(producto));
        when(productoRepositoryPort.save(any())).thenReturn(producto);

        Producto result = service.decreaseStock(1, 3);

        assertEquals(7, result.getStockProducto());
        verify(productoRepositoryPort).save(any());
    }

    @Test
    void shouldSetStockToZeroWhenStockInsufficient() {
        producto.setStockProducto(1);
        when(productoRepositoryPort.findById(1)).thenReturn(Optional.of(producto));
        when(productoRepositoryPort.save(any())).thenReturn(producto);

        Producto result = service.decreaseStock(1, 5);

        assertEquals(0, result.getStockProducto());
        verify(productoRepositoryPort).save(any());
    }

    @Test
    void shouldReturnEmptyProductoWhenDecreaseStockNotFound() {
        when(productoRepositoryPort.findById(1)).thenReturn(Optional.empty());

        Producto result = service.decreaseStock(1, 5);

        assertEquals(0, result.getIdProducto());
        verify(productoRepositoryPort, never()).save(any());
    }

    @Test
    void shouldReturnFluxWithReactiveGetAllProductos() {
        when(productoRepositoryPort.findAll()).thenReturn(Arrays.asList(producto));

        Flux<Producto> flux = service.reactiveGetAllProductos();

        StepVerifier.create(flux)
            .expectNextMatches(p -> p.getNombreProducto().equals("Laptop"))
            .verifyComplete();
    }

    @Test
    void shouldReturnProductoDtoWhenProductoFoundReactive() {
        ProductoDto dto = new ProductoDto();
        dto.setIdProducto(1);
        when(productoRepositoryPort.findById(1)).thenReturn(Optional.of(producto));
        when(productoWebMapper.toDto(producto)).thenReturn(dto);

        Mono<ProductoDto> result = service.getProductoByIdReactive(1);

        StepVerifier.create(result)
            .expectNextMatches(p -> p.getIdProducto() == 1)
            .verifyComplete();
    }

    @Test
    void shouldReturnEmptyProductoDtoWhenNotFoundReactive() {
        ProductoDto dto = new ProductoDto();
        dto.setIdProducto(0);
        when(productoRepositoryPort.findById(1)).thenReturn(Optional.empty());
        when(productoWebMapper.toDto(any())).thenReturn(dto);

        Mono<ProductoDto> result = service.getProductoByIdReactive(1);

        StepVerifier.create(result)
            .expectNextMatches(p -> p.getIdProducto() == 0)
            .verifyComplete();
    }

    @Test
    void shouldReturnPriceWhenProductoFound() {
        when(productoRepositoryPort.findById(1)).thenReturn(Optional.of(producto));

        Mono<Double> result = service.getPrice(1);

        StepVerifier.create(result)
            .expectNext(1500.0)
            .verifyComplete();
    }

    @Test
    void shouldReturnZeroPriceWhenProductoNotFound() {
        when(productoRepositoryPort.findById(1)).thenReturn(Optional.empty());

        Mono<Double> result = service.getPrice(1);

        StepVerifier.create(result)
            .expectNext(0.0)
            .verifyComplete();
    }

    @Test
    void shouldReturnTrueWhenProductoExists() {
        when(productoRepositoryPort.existById(1)).thenReturn(true);

        boolean result = service.existeProducto(1);

        assertTrue(result);
        verify(productoRepositoryPort).existById(1);
    }

    @Test
    void shouldReturnFalseWhenProductoDoesNotExist() {
        when(productoRepositoryPort.existById(1)).thenReturn(false);

        boolean result = service.existeProducto(1);

        assertFalse(result);
        verify(productoRepositoryPort).existById(1);
    }
}