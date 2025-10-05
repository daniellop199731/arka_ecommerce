package com.bancolombia.arka_ecommerce.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bancolombia.arka_ecommerce.domain.model.Proveedor;
import com.bancolombia.arka_ecommerce.domain.port.in.ProveedorUseCase;
import com.bancolombia.arka_ecommerce.domain.port.out.ProveedorRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProveedorApplicationService implements ProveedorUseCase {

    private final ProveedorRepositoryPort proveedorRepositoryPort;

    @Override
    public List<Proveedor> getAllProveedores() {
        return proveedorRepositoryPort.findAll();
    }

    @Override
    public Proveedor getProveedorById(int idProveedor) {
        return proveedorRepositoryPort.findById(idProveedor).get();
    }

    @Override
    public Proveedor createProveedor(Proveedor proveedor) {
        return proveedorRepositoryPort.save(proveedor);
    }

    @Override
    public Proveedor updateProveedor(int idProveedor, Proveedor proveedor) {
        if(proveedorRepositoryPort.existsById(idProveedor)){
            proveedor.setIdProveedor(idProveedor);
            return proveedorRepositoryPort.save(proveedor);
        }
        return new Proveedor();
    }

    @Override
    public boolean deleteProveedorById(int idProveedor) {
        if(proveedorRepositoryPort.existsById(idProveedor)){
            proveedorRepositoryPort.deleteById(idProveedor);
            return true;
        }
        return false;
    }

}
