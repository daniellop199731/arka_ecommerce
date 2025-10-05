package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.domain.model.CarritoCompra;
import com.bancolombia.arka_ecommerce.domain.model.Cliente;
import com.bancolombia.arka_ecommerce.domain.port.out.CarritoCompraRepositoryPort;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.CarritoCompraEntity;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.mapper.CarritoCompraMapper;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.mapper.ClienteMapper;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.repository.CarritoCompraJpaRepo;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CarritoCompraPersistenceAdapter implements CarritoCompraRepositoryPort {

    private final CarritoCompraJpaRepo repository;
    private final CarritoCompraMapper mapper;
    
    private final ClienteMapper clienteMapper;

    @Override
    public CarritoCompra save(CarritoCompra carritoCompra) {
        CarritoCompraEntity carritoCompraEntity = mapper.toEntity(carritoCompra);
        if(carritoCompra.getIdCarritoCompra() == 0){
            carritoCompraEntity.setIdCarritoCompra(null);
        }
        return mapper.toModel(repository.save(carritoCompraEntity));
    }

    @Override
    public List<CarritoCompra> findAll() {
        List<CarritoCompraEntity> carritosCompraEntity = (List<CarritoCompraEntity>) repository.findAll();
        return carritosCompraEntity.stream().map(mapper::toModel).toList();
    }

    @Override
    public Optional<CarritoCompra> findById(int idCarritoCompra) {
        return repository.findById(idCarritoCompra).map(mapper::toModel);
    }

    @Override
    public void deleteById(int idCarritoCompra) {
        repository.deleteById(idCarritoCompra);
    }

    @Override
    public boolean existsById(int idCarritoCompra) {
        boolean exist = false;
        if(repository.findById(idCarritoCompra).isPresent()){
            exist = true;
        }
        return exist;
    }

    @Override
    public List<CarritoCompra> findByClienteCarritoCompra(Cliente cliente) {
        return repository.findByClienteCarritoCompra(clienteMapper.toEntity(cliente)).stream().map(mapper::toModel).toList();
    }

    @Override
    public List<CarritoCompra> findCarritoActual(int idCliente) {
        return repository.findCarritoActual(idCliente).stream().map(mapper::toModel).toList();
    }

    @Override
    public List<CarritoCompra> findcarritosAbandonados() {        
        return repository.carritosAbandonados().stream().map(mapper::toModel).toList();
    }

    @Override
    public List<CarritoCompra> findByFechaCreacionCarritoCompraBetween(Date dateStar, Date dateEnd) {
        return repository.findByFechaCreacionCarritoCompraBetween(dateStar, dateEnd).stream().map(mapper::toModel).toList();
    }
    
}
