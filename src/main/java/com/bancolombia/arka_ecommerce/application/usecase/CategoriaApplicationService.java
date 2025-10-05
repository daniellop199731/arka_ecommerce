package com.bancolombia.arka_ecommerce.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bancolombia.arka_ecommerce.domain.model.Categoria;
import com.bancolombia.arka_ecommerce.domain.port.in.CategoriaUseCase;
import com.bancolombia.arka_ecommerce.domain.port.out.CategoriaRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaApplicationService implements CategoriaUseCase {

    private final CategoriaRepositoryPort categoriaRepositoryPort;

    @Override
    public List<Categoria> getCategorias() {
        return categoriaRepositoryPort.findAll();
    }

    @Override
    public Categoria getCategoriaById(int idCategoria) {
        return categoriaRepositoryPort.findById(idCategoria).get();
    }

    @Override
    public Categoria createCategoria(Categoria categoria) {
        return categoriaRepositoryPort.save(categoria);
    }

    @Override
    public Categoria updateCategoria(int idCategoria, Categoria categoria) {
        if(categoriaRepositoryPort.existsById(idCategoria)){
            categoria.setIdCategoria(idCategoria);
            return categoriaRepositoryPort.save(categoria);
        }
        return new Categoria();
    }

    @Override
    public boolean deleteCategoriaById(int idCategoria) {
        if(categoriaRepositoryPort.existsById(idCategoria)){
            categoriaRepositoryPort.deleteById(idCategoria);
            return true;
        }
        return false;
    }

}
