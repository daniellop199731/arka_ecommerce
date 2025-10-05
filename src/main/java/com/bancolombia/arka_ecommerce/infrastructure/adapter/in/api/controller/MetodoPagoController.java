package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_ecommerce.domain.model.MetodoPago;
import com.bancolombia.arka_ecommerce.domain.port.in.MetodoPagoUseCase;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto.MetodoPagoDto;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.mapper.MetodoPagoWebMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/metodosPago")
@RequiredArgsConstructor
public class MetodoPagoController {

    private final MetodoPagoUseCase metodoPagoUseCase;
    private final MetodoPagoWebMapper mapper;

    @GetMapping("")
    public ResponseEntity<List<MetodoPagoDto>> getMetodosPago() {  
        List<MetodoPago> metodosPago = metodoPagoUseCase.getMetodosPago();
        List<MetodoPagoDto> metodosPagoDto = metodosPago.stream()
            .map(mapper::toDto)
            .toList();
        return ResponseEntity.ok(metodosPagoDto);
    }

    @GetMapping("/{idMetodoPago}")
    public ResponseEntity<MetodoPagoDto> getMetodoPagoById(@PathVariable("idMetodoPago") int idMetodoPago) {
        MetodoPago metodoPago = metodoPagoUseCase.getMetodoPagoById(idMetodoPago);
        MetodoPagoDto metodoPagoDto = mapper.toDto(metodoPago);
        return ResponseEntity.ok(metodoPagoDto);
    }
    
    @PostMapping("/createMetodoPago")
    public ResponseEntity<MetodoPagoDto> cretaeMetodoPago(@Valid @RequestBody MetodoPagoDto metodoPagoDto) {
        MetodoPago metodoPagoSaved = metodoPagoUseCase.createMetodoPago(mapper.toModel(metodoPagoDto));
        MetodoPagoDto metodoPagoSavedDto = mapper.toDto(metodoPagoSaved);
        return ResponseEntity.status(HttpStatus.CREATED).body(metodoPagoSavedDto);
    }

    @PutMapping("/updateMetodoPago/{idMetodoPago}")
    public ResponseEntity<MetodoPagoDto> updateMetodoPago(@PathVariable("idMetodoPago") int idMetodoPago, @RequestBody MetodoPagoDto metodoPagoDto) {
        MetodoPago metodoPagoUpdated = metodoPagoUseCase.updateMetodoPago(idMetodoPago, mapper.toModel(metodoPagoDto));
        MetodoPagoDto metodoPagoUpdatedDto = mapper.toDto(metodoPagoUpdated);
        return ResponseEntity.status(HttpStatus.CREATED).body(metodoPagoUpdatedDto);
    }

    @DeleteMapping("/deleteMetodoPago/{idMetodoPago}")
    public ResponseEntity<Void> deleteMetodoPago(@PathVariable("idMetodoPago") int idMetodoPago) {
        if(metodoPagoUseCase.deleteMetodoPagoById(idMetodoPago)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

