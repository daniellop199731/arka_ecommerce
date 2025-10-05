package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.domain.model.Producto;
import com.bancolombia.arka_ecommerce.domain.port.out.ProductoRepositoryPort;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.ProductoEntity;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.mapper.ProductoMapper;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.repository.ProductoJpaRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductoPersistenceAdapter implements ProductoRepositoryPort {

    private final ProductoJpaRepository repository;
    private final ProductoMapper mapper;

    @Override
    public List<Producto> findAll() {
        List<ProductoEntity> productosEntity = repository.findAll();
        return productosEntity.stream().map(mapper::toModel).toList();
    }

    @Override
    public Optional<Producto> findById(int idProducto) {
        return repository.findById(idProducto).map(mapper::toModel);
    }

    @Override
    public Producto save(Producto producto) {
        ProductoEntity productoEntity = mapper.toEntity(producto);
        if(producto.getIdProducto() == 0){
            productoEntity.setIdProducto(null);
        }
        return mapper.toModel(repository.save(productoEntity));
    }

    @Override
    public void deleteById(int idProducto) {
        repository.deleteById(idProducto);
    }

    @Override
    public boolean existById(int idProducto) {
        if(repository.findById(idProducto).isPresent()){
            return true;
        }
        return false;
    }

}
