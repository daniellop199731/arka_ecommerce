package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.domain.model.Perfil;
import com.bancolombia.arka_ecommerce.domain.port.out.PerfilRepositoryPort;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.PerfilEntity;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.mapper.PerfilMapper;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.repository.PerfilJpaRepository;

import lombok.RequiredArgsConstructor;

//Definicion del contrato de persistencia para el Perfil
// Implementa el puerto de salida definido en PerfilRepositoryPort

@Component
@RequiredArgsConstructor
public class PerfilPersistenceAdapter implements PerfilRepositoryPort {

    private final PerfilJpaRepository repository;
    private final PerfilMapper mapper;


    @Override
    public List<Perfil> findAll() {
        List<PerfilEntity> perfilEntities = (List<PerfilEntity>) repository.findAll();
        return perfilEntities.stream()
            .map(mapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Perfil> findById(int idPerfil) {
        return repository.findById(idPerfil).map(mapper::toDomain);
    }

    @Override
    public Perfil save(Perfil perfil) {
        PerfilEntity perfilEntity = mapper.toEntity(perfil);
        if(perfil.getIdPerfil() == 0){
            perfilEntity.setIdPerfil(null); // Aseguramos que el ID sea null para que se genere uno nuevo
        }
        PerfilEntity perfilEntitySaved = repository.save(perfilEntity);
        return mapper.toDomain(perfilEntitySaved);
    }

    @Override
    public boolean existsById(int idPerfil) {
        return this.findById(idPerfil).isPresent();
    }    

    @Override
    public void deleteById(int idPerfil) {
        repository.deleteById(idPerfil);

    }
    
}
