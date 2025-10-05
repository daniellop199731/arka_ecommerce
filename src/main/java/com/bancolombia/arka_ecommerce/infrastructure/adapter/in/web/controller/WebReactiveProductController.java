package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_ecommerce.domain.model.Comment;
import com.bancolombia.arka_ecommerce.domain.model.Product;
import com.bancolombia.arka_ecommerce.domain.port.in.ProductUseCase;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/web/reactive/product")
@RequiredArgsConstructor
public class WebReactiveProductController {

    private final ProductUseCase service;

    @GetMapping("")
    public ResponseEntity<Flux<Product>> getAllProducts() {
        return ResponseEntity.ok(service.getAllProducts()); 
    }
    
    @PostMapping("/new")
    public ResponseEntity<Mono<Product>> createNew(@RequestBody Product product) {
        return ResponseEntity.ok(service.save(product));
    }

    @GetMapping("/bestComment")
    public ResponseEntity<Mono<String>> getBestComment(){
        return ResponseEntity.ok(service.findInOrderBestComment());
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Flux<Comment>> getCommentsById(@PathVariable("id") String id) {
        return ResponseEntity.ok(service.findCommentsByIdProduct(id));
    }
    
    
    
}
