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

import com.bancolombia.arka_ecommerce.domain.model.Categoria;
import com.bancolombia.arka_ecommerce.domain.port.out.CategoriaRepositoryPort;

@ExtendWith(MockitoExtension.class)
class CategoriaApplicationServiceTest {

    @Mock
    private CategoriaRepositoryPort categoriaRepositoryPort;

    @InjectMocks
    private CategoriaApplicationService service;

    private Categoria categoria;

    @BeforeEach
    void setUp() {
        categoria = new Categoria();
        categoria.setIdCategoria(1);
        categoria.setNombreCategoria("Electrónica");
    }

    // ---- getCategorias ----
    @Test
    void getCategorias_deberiaRetornarListaCategorias() {
        when(categoriaRepositoryPort.findAll()).thenReturn(List.of(categoria));

        List<Categoria> result = service.getCategorias();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Electrónica", result.get(0).getNombreCategoria());
        verify(categoriaRepositoryPort).findAll();
    }

    // ---- getCategoriaById ----
    @Test
    void getCategoriaById_existente_devuelveCategoria() {
        when(categoriaRepositoryPort.findById(1)).thenReturn(Optional.of(categoria));

        Categoria result = service.getCategoriaById(1);

        assertNotNull(result);
        assertEquals(1, result.getIdCategoria());
        verify(categoriaRepositoryPort).findById(1);
    }

    @Test
    void getCategoriaById_noExistente_lanzaException() {
        when(categoriaRepositoryPort.findById(99)).thenReturn(Optional.empty());

        assertThrows(java.util.NoSuchElementException.class, () -> service.getCategoriaById(99));
    }

    // ---- createCategoria ----
    @Test
    void createCategoria_deberiaGuardarYCategoria() {
        when(categoriaRepositoryPort.save(any(Categoria.class))).thenReturn(categoria);

        Categoria result = service.createCategoria(categoria);

        assertNotNull(result);
        assertEquals("Electrónica", result.getNombreCategoria());
        verify(categoriaRepositoryPort).save(categoria);
    }

    // ---- updateCategoria ----
    @Test
    void updateCategoria_existente_actualizaYCategoria() {
        when(categoriaRepositoryPort.existsById(1)).thenReturn(true);
        when(categoriaRepositoryPort.save(any(Categoria.class))).thenReturn(categoria);

        Categoria result = service.updateCategoria(1, categoria);

        assertNotNull(result);
        assertEquals(1, result.getIdCategoria());
        verify(categoriaRepositoryPort).save(categoria);
    }

    @Test
    void updateCategoria_noExistente_retornaNuevaCategoria() {
        when(categoriaRepositoryPort.existsById(1)).thenReturn(false);

        Categoria result = service.updateCategoria(1, categoria);

        assertEquals(0, result.getIdCategoria());
        verify(categoriaRepositoryPort, never()).save(any());
    }

    // ---- deleteCategoriaById ----
    @Test
    void deleteCategoriaById_existente_eliminaYRetornaTrue() {
        when(categoriaRepositoryPort.existsById(1)).thenReturn(true);

        boolean result = service.deleteCategoriaById(1);

        assertTrue(result);
        verify(categoriaRepositoryPort).deleteById(1);
    }

    @Test
    void deleteCategoriaById_noExistente_retornaFalse() {
        when(categoriaRepositoryPort.existsById(1)).thenReturn(false);

        boolean result = service.deleteCategoriaById(1);

        assertFalse(result);
        verify(categoriaRepositoryPort, never()).deleteById(anyInt());
    }
}
