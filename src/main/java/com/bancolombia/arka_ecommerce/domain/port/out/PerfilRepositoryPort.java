package com.bancolombia.arka_ecommerce.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.bancolombia.arka_ecommerce.domain.model.Perfil;

//Contrato de persistencia para el Perfil
// Define las operaciones que se pueden realizar sobre el Perfil
// Este es el puerto de salida que será implementado por la infraestructura
public interface PerfilRepositoryPort {

    List<Perfil> findAll();
    Optional<Perfil> findById(int idPerfil);
    Perfil save(Perfil perfil);
    void deleteById(int idPerfil);
    boolean existsById(int idPerfil);
}

