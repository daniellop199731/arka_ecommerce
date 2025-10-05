package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence;


import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.domain.model.Comment;
import com.bancolombia.arka_ecommerce.domain.model.Product;
import com.bancolombia.arka_ecommerce.domain.port.out.ProductRepositoryPort;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.ProductEntity;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.mapper.ProductMapper;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductRepositoryPort{
    
    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Override
    public Flux<Product> findAll() {
        Flux<ProductEntity> products = repository.findAll();
        return products.map(mapper::toModel);
    }

    @Override
    public Mono<String> findInOrderBestComment() {        
        return repository.findInOrderBestComment();
    }    

    @Override
    public Mono<Product> findById(String id) {
        Mono<ProductEntity> productEntity = repository.findById(id);
        return productEntity.map(mapper::toModel);
    }

    @Override
    public Mono<Product> save(Product product) {
        Mono<ProductEntity> productEntity = repository.save(mapper.toEntity(product));
        return productEntity.map(mapper::toModel);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existById(String id) {
        Mono<ProductEntity> productEntity = repository.findById(id);
        if(productEntity.blockOptional().isPresent()){
            return true;
        }
        return false;
    }

    @Override
    public Flux<Comment> findCommentsByIdProduct(String id) {
        System.out.println(id);
        return repository.findCommentsByProductId(id);
    }
    
}
