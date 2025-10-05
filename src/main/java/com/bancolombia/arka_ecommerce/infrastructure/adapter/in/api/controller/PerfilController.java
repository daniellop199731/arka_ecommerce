package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_ecommerce.domain.model.Perfil;
import com.bancolombia.arka_ecommerce.domain.port.in.PerfilUseCase;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto.PerfilDto;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.mapper.PerfilWebMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


// Controlador REST para manejar las solicitudes relacionadas con el Perfil
// Utiliza el caso de uso PerfilUseCase para interactuar con la lógica de negocio
@RestController
@RequestMapping("/perfiles")
@RequiredArgsConstructor
public class PerfilController {

    private final PerfilUseCase perfilUseCase;
    private final PerfilWebMapper mapper;

    @GetMapping("")
    public ResponseEntity<List<PerfilDto>> getAllPerfiles() {
        List<Perfil> perfiles = perfilUseCase.getAllPerfiles();
        List<PerfilDto> perfilesDto = perfiles.stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());

        return ResponseEntity.ok(perfilesDto);
    }

    @GetMapping("/{idPerfil}")
    public ResponseEntity<PerfilDto> getPerfilById(@PathVariable("idPerfil") int idPerfil) {
        Perfil perfil = perfilUseCase.getPerfilById(idPerfil);
        PerfilDto perfilDto = mapper.toDto(perfil);
        return ResponseEntity.ok(perfilDto);
    }

    @PostMapping("/createPerfil")
    public ResponseEntity<PerfilDto> createPerfil(@Valid @RequestBody PerfilDto perfilDto) {
        Perfil perfil = mapper.toModel(perfilDto);
        Perfil perfilSaved = perfilUseCase.createPerfil(perfil);
        return ResponseEntity.ok(mapper.toDto(perfilSaved));
    }

    @PutMapping("updatePerfil/{idPerfil}")
    public ResponseEntity<PerfilDto> updatePerfil(@PathVariable("idPerfil") int idPerfil, @RequestBody PerfilDto perfilDto) {
        Perfil perfil = mapper.toModel(perfilDto);
        Perfil perfilUpdated = perfilUseCase.updatePerfil(idPerfil, perfil);
        return ResponseEntity.ok(mapper.toDto(perfilUpdated));
    }

    @DeleteMapping("/deletePerfil/{idPerfil}")
    public ResponseEntity<Void> deletePerfil(@PathVariable("idPerfil") int idPerfil) {
        if(perfilUseCase.deletePerfil(idPerfil)){
            return ResponseEntity.ok().build();
        };
        return ResponseEntity.notFound().build();
    }
    
    
    
	
}

