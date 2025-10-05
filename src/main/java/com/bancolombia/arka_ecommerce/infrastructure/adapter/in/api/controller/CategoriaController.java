package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_ecommerce.domain.model.Categoria;
import com.bancolombia.arka_ecommerce.domain.port.in.CategoriaUseCase;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto.CategoriaDto;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.mapper.CategoriaWebMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaUseCase categoriaUseCase;
    private final CategoriaWebMapper mapper;

    @GetMapping("")
    public ResponseEntity<List<CategoriaDto>> getAllCategorias() {
        List<Categoria> categorias = categoriaUseCase.getCategorias();
        List<CategoriaDto> categoriasDto = categorias.stream()
                                            .map(mapper::toDto)
                                            .toList();
        return ResponseEntity.ok(categoriasDto);
    }

    @GetMapping("/{idCategoria}")
    public ResponseEntity<CategoriaDto> getCategoriaById(@PathVariable("idCategoria") int idCategoria) {
        return ResponseEntity.ok(mapper.toDto(categoriaUseCase.getCategoriaById(idCategoria)));
    }
    
    @PostMapping("/createCategoria")
    public ResponseEntity<CategoriaDto> createCategoria(@Valid @RequestBody CategoriaDto categoriaDto) {
        Categoria categoria = mapper.toModel(categoriaDto);
        return ResponseEntity.ok(mapper.toDto(categoriaUseCase.createCategoria(categoria)));
    }

    @PutMapping("/updateCategoria/{idCategoria}")
    public ResponseEntity<CategoriaDto> updateCategoria(@PathVariable("idCategoria") int idCategoria, @Valid @RequestBody CategoriaDto categoriaDto) {
        Categoria categoria = mapper.toModel(categoriaDto);
        return ResponseEntity.ok(mapper.toDto(categoriaUseCase.updateCategoria(idCategoria, categoria)));        
    }
    
    @DeleteMapping("/deleteCategoria/{idCategoria}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable("idCategoria") int idCategoria){
        if(categoriaUseCase.deleteCategoriaById(idCategoria)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
