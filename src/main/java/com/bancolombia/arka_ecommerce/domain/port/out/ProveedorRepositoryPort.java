package com.bancolombia.arka_ecommerce.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.bancolombia.arka_ecommerce.domain.model.Proveedor;

public interface ProveedorRepositoryPort {

    List<Proveedor> findAll();
    Optional<Proveedor> findById(int idProveedor);
    Proveedor save(Proveedor proveedor);
    void deleteById(int idProveedor);
    boolean existsById(int idProveedor);

}
