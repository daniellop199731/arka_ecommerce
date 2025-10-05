package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_ecommerce.domain.model.Cliente;
import com.bancolombia.arka_ecommerce.domain.port.in.ClienteUserCase;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto.ClienteDto;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.mapper.ClienteWebMapper;

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
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteUserCase clienteUserCase;
    private final ClienteWebMapper mapper;

    @GetMapping("")
    public ResponseEntity<List<ClienteDto>> getAllClientes() {
        List<Cliente> clientes = clienteUserCase.getAllClientes();
        List<ClienteDto> clienteDtos = clientes.stream()
            .map(mapper::toDto)
            .toList();
        return ResponseEntity.ok(clienteDtos);
    }
    
    @GetMapping("/{idCliente}")
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable("idCliente") int idCliente) {
        Cliente cliente = clienteUserCase.getClienteById(idCliente);
        return ResponseEntity.ok(mapper.toDto(cliente));
    }

    @PostMapping("/createCliente")
    public ResponseEntity<ClienteDto> createCliente(@Valid @RequestBody ClienteDto clienteDto) {
        Cliente clienteCreated = clienteUserCase.createCliente(mapper.toModel(clienteDto));
        return ResponseEntity.ok(mapper.toDto(clienteCreated));
    }

    @PutMapping("/updateCliente/{idCliente}")
    public ResponseEntity<ClienteDto> putMethodName(@PathVariable("idCliente") int idCliente, @RequestBody ClienteDto clienteDto) {
        Cliente cliente = mapper.toModel(clienteDto);
        Cliente clienteUpdated = clienteUserCase.updateCliente(idCliente, cliente);
        return ResponseEntity.ok(mapper.toDto(clienteUpdated));
    }

    @DeleteMapping("/deleteCliente/{idCliente}")
    public ResponseEntity<Void> deleteCliente(@PathVariable("idCliente") int idCliente){
        if(clienteUserCase.deleteCliente(idCliente)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    

}

