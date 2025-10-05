package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_ecommerce.application.utils.ResponseObject;
import com.bancolombia.arka_ecommerce.domain.model.MetodoPagoCliente;
import com.bancolombia.arka_ecommerce.domain.port.in.MetodoPagoClienteUseCase;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto.MetodoPagoClienteDto;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.mapper.MetodoPagoClienteWebMapper;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/metodosPagoCliente")
@RequiredArgsConstructor
public class MetodoPagoClienteController {

    private final MetodoPagoClienteUseCase metodoPagoClienteUseCase;
    private final MetodoPagoClienteWebMapper mapper;

    private static ResponseObject<MetodoPagoClienteDto> rObjDto;

    @GetMapping("/{idCliente}")
    public ResponseEntity<List<MetodoPagoClienteDto>> getMetodosPagoByIdCliente(
        @PathVariable("idCliente") int idCliente
    ) {
        List<MetodoPagoCliente> metodosPagoCliente = metodoPagoClienteUseCase.getMetodosPagoByIdCliente(idCliente);
        List<MetodoPagoClienteDto> metodosPagoClienteDto = metodosPagoCliente.stream()
                                                            .map(mapper::toDto)
                                                            .toList();

        return ResponseEntity.ok(metodosPagoClienteDto);
    }

    @PostMapping("/createMetodoPagoCliente/{idCliente}/{idMetodoPago}")
    public ResponseEntity<ResponseObject<MetodoPagoClienteDto>> createMetodoPagoCliente(
        @PathVariable("idCliente") int idCliente, @PathVariable("idMetodoPago") int idMetodoPago
    ) {
        rObjDto = new ResponseObject<>();

        ResponseObject<MetodoPagoCliente> metodoPagoClienteCreated = metodoPagoClienteUseCase.createMetodoPagoCliente(idCliente, idMetodoPago);

        rObjDto.setSuccessful(metodoPagoClienteCreated.isSuccessful());
        rObjDto.setMessage(metodoPagoClienteCreated.getMessage());
        rObjDto.setObj(mapper.toDto(metodoPagoClienteCreated.getObj()));

        if(rObjDto.isSuccessful()){
            return ResponseEntity.ok(rObjDto);
        }

        return ResponseEntity.badRequest().body(rObjDto);
    }

    @PutMapping("/updateMetodoPagoCliente/{idCliente}/{idMetodoPago}/{valorCuenta}")
    public ResponseEntity<ResponseObject<MetodoPagoClienteDto>> updateMetodoPagoCliente(
        @PathVariable("idCliente") int idCliente, @PathVariable("idMetodoPago") int idMetodoPago
        , @PathVariable("valorCuenta") double valorCuenta
    ) {
        rObjDto = new ResponseObject<>();

        ResponseObject<MetodoPagoCliente> metodoPagoClienteUpdated = metodoPagoClienteUseCase.manageMetodoPagoCliente(idCliente, idMetodoPago, valorCuenta);
        rObjDto.setSuccessful(metodoPagoClienteUpdated.isSuccessful());
        rObjDto.setMessage(metodoPagoClienteUpdated.getMessage());
        rObjDto.setObj(mapper.toDto(metodoPagoClienteUpdated.getObj()));     
        
        if(rObjDto.isSuccessful()){
            return ResponseEntity.ok(rObjDto);
        }        
        
        return ResponseEntity.badRequest().body(rObjDto);
    }

    @DeleteMapping("/deleteMetodoPagoCliente/{idCliente}/{idMetodoPago}")
    public ResponseEntity<Void> deleteMetodoPagoCliente (
        @PathVariable("idCliente") int idCliente, @PathVariable("idMetodoPago") int idMetodoPago
    ){
        if(metodoPagoClienteUseCase.deleteByIdClienteAndMetodoPago(idCliente, idMetodoPago)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
