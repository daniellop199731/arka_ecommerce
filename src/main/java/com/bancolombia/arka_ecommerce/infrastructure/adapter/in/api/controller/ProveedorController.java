package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_ecommerce.domain.model.Proveedor;
import com.bancolombia.arka_ecommerce.domain.port.in.ProveedorUseCase;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto.ProveedorDto;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.mapper.ProveedorWebMapper;

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
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/proveedores")
@RequiredArgsConstructor
public class ProveedorController {

    private final ProveedorUseCase proveedorUseCase;
    private final ProveedorWebMapper proveedorWebMapper;

    @GetMapping("")
    public ResponseEntity<List<ProveedorDto>> getAllProveedores() {
        List<Proveedor> proveedores = proveedorUseCase.getAllProveedores();
        List<ProveedorDto> proveedoresDto = proveedores.stream()
                                                .map(proveedorWebMapper::toDto)
                                                .toList();
        return ResponseEntity.ok(proveedoresDto);
    }

    @GetMapping("/{idProveedor}")
    public ResponseEntity<ProveedorDto> getProveedorById(@PathVariable("idProveedor") int idProveedor) {
        return ResponseEntity.ok(proveedorWebMapper.toDto(proveedorUseCase.getProveedorById(idProveedor)));
    }   
    
    @PostMapping("/createProveedor")
    public ResponseEntity<ProveedorDto> createProveedor(@Valid @RequestBody ProveedorDto proveedorDto) {
        Proveedor proveedor = proveedorWebMapper.toModel(proveedorDto);
        return ResponseEntity.ok(proveedorWebMapper.toDto(proveedorUseCase.createProveedor(proveedor)));
    }  
    
    @PutMapping("/updateProveedor/{idProveedor}")
    public ResponseEntity<ProveedorDto> createProveedor(@PathVariable("idProveedor") int idProveedor, @Valid @RequestBody ProveedorDto proveedorDto) {
        Proveedor proveedor = proveedorWebMapper.toModel(proveedorDto);
        return ResponseEntity.ok(proveedorWebMapper.toDto(proveedorUseCase.updateProveedor(idProveedor, proveedor)));
    }    

    @DeleteMapping("/deleteProveedor/{idProveedor}")
    public ResponseEntity<Void> deleteProveedor(@PathVariable("idProveedor") int idProveedor){
        if(proveedorUseCase.deleteProveedorById(idProveedor)){            
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    

}
