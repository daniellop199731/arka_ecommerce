package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.domain.model.Categoria;
import com.bancolombia.arka_ecommerce.domain.port.out.CategoriaRepositoryPort;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.CategoriaEntity;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.mapper.CategoriaMapper;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.repository.CategoriaJpaRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CategoriaPersistenceAdapter implements CategoriaRepositoryPort {

    private final CategoriaJpaRepository repository;
    private final CategoriaMapper mapper;

    @Override
    public List<Categoria> findAll() {
        List<CategoriaEntity> categoriasEntity = repository.findAll();
        List<Categoria> categorias = categoriasEntity.stream()
                                        .map(mapper::toModel)
                                        .toList();
        return categorias;
    }

    @Override
    public Optional<Categoria> findById(int idCategoria) {
        return repository.findById(idCategoria).map(mapper::toModel);
    }

    @Override
    public Categoria save(Categoria categoria) {
        CategoriaEntity categoriaEntity = mapper.toEntity(categoria);
        if(categoria.getIdCategoria() == 0){
            categoriaEntity.setIdCategoria(null);
        }
        return mapper.toModel(repository.save(categoriaEntity));
    }

    @Override
    public void deleteById(int idCategoria) {
        repository.deleteById(idCategoria);
    }

    @Override
    public boolean existsById(int idCategoria) {
        if(repository.findById(idCategoria).isPresent()){
            return true;
        }
        return false;
    }

}
