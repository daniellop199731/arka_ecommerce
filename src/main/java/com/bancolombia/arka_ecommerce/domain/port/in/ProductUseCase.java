package com.bancolombia.arka_ecommerce.domain.port.in;

import com.bancolombia.arka_ecommerce.domain.model.Comment;
import com.bancolombia.arka_ecommerce.domain.model.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductUseCase {

    Mono<Product> save(Product product);
    Flux<Product> getAllProducts();
    Mono<String> findInOrderBestComment();
    Flux<Comment> findCommentsByIdProduct(String id);
    
}
