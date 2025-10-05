package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_ecommerce.domain.model.EstadoDespacho;
import com.bancolombia.arka_ecommerce.domain.port.in.EstadoDespachoUseCase;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto.EstadoDespachoDto;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.mapper.EstadoDespachoWebMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/estadosDespacho")
@RequiredArgsConstructor
public class EstadoDespachoController {

    private final EstadoDespachoUseCase useCase;
    private final EstadoDespachoWebMapper mapper;

    @GetMapping("")
    public ResponseEntity<List<EstadoDespachoDto>> getEstadosDespacho() {
        List<EstadoDespacho> estadosDespacho = useCase.getAllEstadosDespacho();
        List<EstadoDespachoDto> estadosDespachoDto = estadosDespacho.stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(estadosDespachoDto);
    }

    @GetMapping("/{idEstadoDespacho}")
    public ResponseEntity<EstadoDespachoDto> getEstadoDespacho(@PathVariable("idEstadoDespacho") int idEstadoDespacho) {
        return ResponseEntity.ok(mapper.toDto(useCase.getEstadoDespacho(idEstadoDespacho)));
    }    

    @PostMapping("/createEstadoDespacho")
    public ResponseEntity<EstadoDespachoDto> createEstadoDespacho(@Valid @RequestBody EstadoDespachoDto estadoDespachoDto) {
        return ResponseEntity.ok(mapper.toDto(useCase.createEstadoDespacho(mapper.toModel(estadoDespachoDto))));
    }

    @PutMapping("/updateEstadoDespacho/{idEstadoDespacho}")
    public ResponseEntity<EstadoDespachoDto> updateEstadoDespacho(@PathVariable("idEstadoDespacho") int idEstadoDespacho, @Valid @RequestBody EstadoDespachoDto estadoDespachoDto) {
        return ResponseEntity.ok(mapper.toDto(useCase.updateEstadoDespacho(idEstadoDespacho, mapper.toModel(estadoDespachoDto))));
    }    
    
    @DeleteMapping("/deleteEstadoDespacho/{idEstadoDespacho}")
    public ResponseEntity<Void> deleteEstadoDespacho(@PathVariable("idEstadoDespacho") int idEstadoDespacho) {
        if(useCase.deleteEstadoDespacho(idEstadoDespacho)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }       
    

}
