package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_ecommerce.domain.model.CarritoCompraProducto;
import com.bancolombia.arka_ecommerce.domain.port.in.CarritoCompraUseCase;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto.CarritoCompraDto;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto.CarritoCompraProductoDto;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.mapper.CarritoCompraProductoWebMapper;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.mapper.CarritoCompraWebMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/carritosCompra")
@RequiredArgsConstructor
public class CarritoCompraController {

    private final CarritoCompraUseCase carritoCompraUseCase;

    private final CarritoCompraWebMapper carritoCompraWebMapper;
    private final CarritoCompraProductoWebMapper carritoCompraProductoWebMapper;

    @GetMapping("/{idCarritoCompra}")
    public ResponseEntity<CarritoCompraDto> obtenerCarritoPorId(@PathVariable int idCarritoCompra) {        
        return ResponseEntity.ok().body(
            carritoCompraWebMapper.toDto(
                carritoCompraUseCase.obtenerCarritoPorId(idCarritoCompra)
            )
        );
    }

    @GetMapping("/carritoActual/{idCliente}")
    public ResponseEntity<CarritoCompraDto> carritoActual(@PathVariable(value = "idCliente") int idCliente) {
        return ResponseEntity.ok().body(
            carritoCompraWebMapper.toDto(
                carritoCompraUseCase.carritoActual(idCliente)
            )
        );
    }

    @GetMapping("/abandonados")
    public ResponseEntity<List<CarritoCompraDto>> carritosAbandonados() {                
        return ResponseEntity.ok().body(
            carritoCompraUseCase.carritosAbandonados().stream()
            .map(carritoCompraWebMapper::toDto)
            .toList()
        );
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<CarritoCompraDto>> carritosUsuario(@PathVariable(value = "idCliente") int idCliente) {
        return ResponseEntity.ok().body(
            carritoCompraUseCase.carritosPorUsuario(idCliente).stream()
            .map(carritoCompraWebMapper::toDto)
            .toList()
        );
    }

    @PostMapping("/crearNuevo/{idCliente}")
    public ResponseEntity<CarritoCompraDto> crearNuevo(@PathVariable(value = "idCliente") int idCliente) {                
        return ResponseEntity.ok().body(
            carritoCompraWebMapper.toDto(
                carritoCompraUseCase.crearNuevo(idCliente)
            )
        );
    }
    
    @PostMapping("/agregarProductos/{idCliente}")
    public ResponseEntity<List<CarritoCompraProductoDto>> postMethodName(@Valid
            @PathVariable(value = "idCliente") int idCliente
            , @RequestBody List<CarritoCompraProductoDto> carritoCompraProductosDto) {

        List<CarritoCompraProducto> carritoCompraProductos = 
            carritoCompraUseCase.agregarProductos(
                idCliente
                , carritoCompraProductosDto.stream().map(carritoCompraProductoWebMapper::toModel).toList());
        
        return ResponseEntity.ok().body(
            carritoCompraProductos.stream().map(carritoCompraProductoWebMapper::toDto).toList()
        );
    }
    
    
}
