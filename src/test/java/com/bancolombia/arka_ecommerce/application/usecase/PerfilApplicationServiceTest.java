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

import com.bancolombia.arka_ecommerce.domain.model.Perfil;
import com.bancolombia.arka_ecommerce.domain.port.out.PerfilRepositoryPort;

@ExtendWith(MockitoExtension.class)
class PerfilApplicationServiceTest {

    @Mock
    private PerfilRepositoryPort perfilRepository;

    @InjectMocks
    private PerfilApplicationService perfilService;

    private Perfil perfil;

    @BeforeEach
    void setUp() {
        perfil = new Perfil();
        perfil.setIdPerfil(1);
        perfil.setNombrePerfil("Admin");
    }

    @Test
    void shouldReturnAllPerfiles() {
        List<Perfil> perfiles = Arrays.asList(perfil);
        when(perfilRepository.findAll()).thenReturn(perfiles);

        List<Perfil> result = perfilService.getAllPerfiles();

        assertEquals(1, result.size());
        assertEquals("Admin", result.get(0).getNombrePerfil());
        verify(perfilRepository).findAll();
    }

    @Test
    void shouldReturnPerfilByIdWhenExists() {
        when(perfilRepository.findById(1)).thenReturn(Optional.of(perfil));

        Perfil result = perfilService.getPerfilById(1);

        assertNotNull(result);
        assertEquals("Admin", result.getNombrePerfil());
        verify(perfilRepository).findById(1);
    }

    @Test
    void shouldReturnEmptyPerfilWhenNotFound() {
        when(perfilRepository.findById(1)).thenReturn(Optional.empty());

        Perfil result = perfilService.getPerfilById(1);

        assertNotNull(result);
        assertNull(result.getNombrePerfil());
        verify(perfilRepository).findById(1);
    }

    @Test
    void shouldCreatePerfilSuccessfully() {
        when(perfilRepository.save(perfil)).thenReturn(perfil);

        Perfil result = perfilService.createPerfil(perfil);

        assertEquals("Admin", result.getNombrePerfil());
        verify(perfilRepository).save(perfil);
    }

    @Test
    void shouldUpdatePerfilWhenExists() {
        when(perfilRepository.existsById(1)).thenReturn(true);
        when(perfilRepository.save(perfil)).thenReturn(perfil);

        Perfil result = perfilService.updatePerfil(1, perfil);

        assertEquals(1, result.getIdPerfil());
        verify(perfilRepository).existsById(1);
        verify(perfilRepository).save(perfil);
    }

    @Test
    void shouldReturnEmptyPerfilWhenUpdatingNonExistent() {
        when(perfilRepository.existsById(1)).thenReturn(false);

        Perfil result = perfilService.updatePerfil(1, perfil);

        assertNotNull(result);
        assertEquals(0, result.getIdPerfil());
        verify(perfilRepository).existsById(1);
        verify(perfilRepository, never()).save(any());
    }

    @Test
    void shouldDeletePerfilWhenExists() {
        when(perfilRepository.existsById(1)).thenReturn(true);

        boolean result = perfilService.deletePerfil(1);

        assertTrue(result);
        verify(perfilRepository).existsById(1);
        verify(perfilRepository).deleteById(1);
    }

    @Test
    void shouldNotDeletePerfilWhenNotExists() {
        when(perfilRepository.existsById(1)).thenReturn(false);

        boolean result = perfilService.deletePerfil(1);

        assertFalse(result);
        verify(perfilRepository).existsById(1);
        verify(perfilRepository, never()).deleteById(anyInt());
    }
}
