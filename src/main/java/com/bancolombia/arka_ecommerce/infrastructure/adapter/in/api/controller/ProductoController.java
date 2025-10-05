package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_ecommerce.domain.model.Producto;
import com.bancolombia.arka_ecommerce.domain.port.in.ProductoUseCase;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto.ProductoDto;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.mapper.ProductoWebMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoUseCase productoUseCase;
    private final ProductoWebMapper mapper;

    @GetMapping("")
    public ResponseEntity<List<ProductoDto>> getAllProductos() {
        List<Producto> productos = productoUseCase.getAllProductos();
        List<ProductoDto> productosDto = productos.stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(productosDto);
    }

    @GetMapping("/{idProducto}")
    public ResponseEntity<ProductoDto> getProducto(@PathVariable("idProducto") int idProducto) {
        return ResponseEntity.ok( mapper.toDto(productoUseCase.getProductoById(idProducto)));
    }    

    @PostMapping("/createProducto")
    public ResponseEntity<ProductoDto> createProducto(@Valid @RequestBody ProductoDto productoDto) {
        Producto producto = mapper.toModel(productoDto);
        return ResponseEntity.ok(mapper.toDto(productoUseCase.createProducto(producto)));
    }

    @PutMapping("/updateProducto/{idProducto}")
    public ResponseEntity<ProductoDto> updateProducto(@PathVariable("idProducto") int idProducto, @Valid @RequestBody ProductoDto productoDto) {
        Producto producto = mapper.toModel(productoDto);
        return ResponseEntity.ok(mapper.toDto(productoUseCase.updateProducto(idProducto, producto)));
    }    

    @DeleteMapping("/deleteProducto/{idProducto}")
    public ResponseEntity<Void> deleteProducto(@PathVariable("idProducto") int idProducto){
        if(productoUseCase.deleteProducto(idProducto)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    //Se retorna un ResponseEntity que contiene un Flux
    //Flux, se entiende que se espera que se devolveran multiples elementos
    @GetMapping(path = "/reactive", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Flux<ProductoDto>> reactiveGetAllProductos() {
        Flux<ProductoDto> productosDto = productoUseCase.reactiveGetAllProductos()
            .map(producto -> mapper.toDto(producto))
            .delayElements(Duration.ofSeconds(1));
        return ResponseEntity.ok(productosDto);
    }    

    //Mono, se entiende que se espera que se devolvera un solo elemento
    @GetMapping(path = "/reactivev2")
    public Mono<ResponseEntity<List<ProductoDto>>> reactiveGetAllProductosv2() {
        List<Producto> productos = productoUseCase.getAllProductos();
        List<ProductoDto> productosDto = productos.stream().map(mapper::toDto).toList();
        return Mono.just(ResponseEntity.ok(productosDto));
    }    

    @GetMapping("/reactive/{idProducto}")
    public ResponseEntity<Mono<ProductoDto>> getProductoReactive(@PathVariable("idProducto") int idProducto) {
        Mono<ProductoDto> productoDto = productoUseCase.getProductoByIdReactive(idProducto);
        return ResponseEntity.ok(productoDto);
    }    
    
    @GetMapping("/price/reactive/{idProducto}")
    public Mono<Double> getPriceReactive(@PathVariable("idProducto") int idProducto) {
        return productoUseCase.getPrice(idProducto).doOnError(error -> System.out.println(error.getMessage()));
    }     

}
