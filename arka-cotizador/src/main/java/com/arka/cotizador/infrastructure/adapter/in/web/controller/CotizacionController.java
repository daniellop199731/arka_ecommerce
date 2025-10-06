package com.arka.cotizador.infrastructure.adapter.in.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.arka.cotizador.domain.model.CotizacionResponse;
import com.arka.cotizador.domain.port.in.CotizacionUseCase;


@RestController
@RequestMapping("/cotizacion")
@RequiredArgsConstructor
public class CotizacionController {

    private final CotizacionUseCase service;

    @GetMapping("/testCotizacion")
    public String testCotizacion() {
        return "Controlador para gestion de cotizaciones";
    }

    @GetMapping("/totalPriceProducts")
    public Mono<Double> totalPriceProducts(@RequestBody List<Integer> idsProductos) {
        return service.totalPriceProducts(idsProductos);
    }

    @GetMapping("/nuevaCotizacion")
    public Mono<CotizacionResponse> nuevaCotizacion(@RequestBody List<Integer> idsProductos) {
        return service.crearCotizacion(idsProductos);
    }    
    
    
}
