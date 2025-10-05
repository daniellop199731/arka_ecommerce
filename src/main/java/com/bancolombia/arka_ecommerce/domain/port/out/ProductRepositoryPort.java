package com.bancolombia.arka_ecommerce.domain.port.out;

import com.bancolombia.arka_ecommerce.domain.model.Comment;
import com.bancolombia.arka_ecommerce.domain.model.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepositoryPort {
    
    Flux<Product> findAll();
    Mono<String> findInOrderBestComment();
    Mono<Product> findById(String id);
    Mono<Product> save(Product product);
    void deleteById(String id);
    boolean existById(String id);
    Flux<Comment> findCommentsByIdProduct(String id);

}
