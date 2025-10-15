package com.bancolombia.arka_ecommerce.application.usecase;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bancolombia.arka_ecommerce.domain.model.Comment;
import com.bancolombia.arka_ecommerce.domain.model.Product;
import com.bancolombia.arka_ecommerce.domain.port.out.ProductRepositoryPort;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class ProductApplicationServiceTest {

    @Mock
    private ProductRepositoryPort repositoryPort;

    @InjectMocks
    private ProductApplicationService service;

    private Product product;
    private Comment comment;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId("1");
        product.setName("Laptop");
        comment = new Comment();
        comment.setText("Excelente producto");
    }

    @Test
    void shouldSaveProductSuccessfully() {
        when(repositoryPort.save(product)).thenReturn(Mono.just(product));

        Mono<Product> result = service.save(product);

        StepVerifier.create(result)
            .expectNext(product)
            .verifyComplete();

        verify(repositoryPort).save(product);
    }

    @Test
    void shouldReturnAllProducts() {
        when(repositoryPort.findAll()).thenReturn(Flux.just(product));

        Flux<Product> result = service.getAllProducts();

        StepVerifier.create(result)
            .expectNextMatches(p -> p.getName().equals("Laptop"))
            .verifyComplete();

        verify(repositoryPort).findAll();
    }

    @Test
    void shouldReturnBestCommentInOrder() {
        when(repositoryPort.findInOrderBestComment()).thenReturn(Mono.just("Excelente"));

        Mono<String> result = service.findInOrderBestComment();

        StepVerifier.create(result)
            .expectNext("Excelente")
            .verifyComplete();

        verify(repositoryPort).findInOrderBestComment();
    }

    @Test
    void shouldReturnCommentsByProductId() {
        when(repositoryPort.findCommentsByIdProduct("1")).thenReturn(Flux.just(comment));

        Flux<Comment> result = service.findCommentsByIdProduct("1");

        StepVerifier.create(result)
            .expectNextMatches(c -> c.getText().contains("Excelente"))
            .verifyComplete();

        verify(repositoryPort).findCommentsByIdProduct("1");
    }
}
