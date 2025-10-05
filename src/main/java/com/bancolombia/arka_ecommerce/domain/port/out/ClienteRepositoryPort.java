package com.bancolombia.arka_ecommerce.domain.port.out;

import java.util.List;
import java.util.Optional;
import com.bancolombia.arka_ecommerce.domain.model.Cliente;

public interface ClienteRepositoryPort {

    List<Cliente> findAll();
    Optional<Cliente> findById(int idCliente);
    Cliente save(Cliente Cliente);
    void deleteById(int idCliente);
    boolean existsById(int idCliente);
    Optional<Cliente> findByIdentificacion(String identificacionCliente);
}

