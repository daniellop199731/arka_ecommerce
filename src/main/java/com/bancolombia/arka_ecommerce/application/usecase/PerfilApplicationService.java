package com.bancolombia.arka_ecommerce.application.usecase;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.bancolombia.arka_ecommerce.domain.model.Perfil;
import com.bancolombia.arka_ecommerce.domain.port.in.PerfilUseCase;
import com.bancolombia.arka_ecommerce.domain.port.out.PerfilRepositoryPort;

import lombok.RequiredArgsConstructor;

//Definicion del contrato de casos de uso para el Perfil
// Implementa el puerto de entrada definido en PerfilUseCase
@Service
@RequiredArgsConstructor
public class PerfilApplicationService implements PerfilUseCase {

    private final PerfilRepositoryPort perfilRepository;

    @Override
    public List<Perfil> getAllPerfiles() {
        return perfilRepository.findAll();
    }

    @Override
    public Perfil getPerfilById(int idPerfil) {
        Optional<Perfil> perfilOptional = perfilRepository.findById(idPerfil);
        if(perfilOptional.isPresent()){
            return perfilOptional.get();
        }
        return new Perfil();
    }

    @Override
    public Perfil createPerfil(Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    @Override
    public Perfil updatePerfil(int idPerfil, Perfil perfil) {
        if(perfilRepository.existsById(idPerfil)){
            perfil.setIdPerfil(idPerfil); // Aseguramos que el ID sea el correcto
            return perfilRepository.save(perfil);
        }
        return new Perfil(); // Retornamos un objeto vacío si no existe el perfil   
    }

    @Override
    public boolean deletePerfil(int idPerfil) {
        if(perfilRepository.existsById(idPerfil)){
            perfilRepository.deleteById(idPerfil);
            return true;
        }
        return false;
    }

}

