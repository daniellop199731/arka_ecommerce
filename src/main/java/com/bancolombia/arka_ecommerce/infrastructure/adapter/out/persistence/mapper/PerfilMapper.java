package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.domain.model.Perfil;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.PerfilEntity;

@Component
public class PerfilMapper {

    public Perfil toDomain(PerfilEntity perfilEntity) {
        if (perfilEntity == null) {
            return null;
        }
        Perfil perfil = new Perfil();
        perfil.setIdPerfil(perfilEntity.getIdPerfil());
        perfil.setNombrePerfil(perfilEntity.getNombrePerfil());
        return perfil;
        
    }

    public PerfilEntity toEntity(Perfil perfil) {
        if (perfil == null) {
            return null;
        }
        PerfilEntity perfilEntity = new PerfilEntity();
        perfilEntity.setIdPerfil(perfil.getIdPerfil());
        perfilEntity.setNombrePerfil(perfil.getNombrePerfil());
        return perfilEntity;
    }

}

