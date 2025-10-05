package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import com.bancolombia.arka_ecommerce.domain.model.MetodoPago;
import com.bancolombia.arka_ecommerce.domain.port.out.MetodoPagoRepositoryPort;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.MetodoPagoEntity;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.mapper.MetodoPagoMapper;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.repository.MetodoPagoJpaRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MetodoPagoPercistenceAdapter implements MetodoPagoRepositoryPort{

    private final MetodoPagoJpaRepository repository;
    private final MetodoPagoMapper mapper;

    @Override
    public List<MetodoPago> findAll() {
        List<MetodoPagoEntity> metodoPagoEntities = repository.findAll();
        List<MetodoPago> metodosPagos = metodoPagoEntities.stream()
            .map(mapper::toModel)
            .toList();
            //.collect(Collectors.toList());
        return metodosPagos;
    }

    @Override
    public Optional<MetodoPago> findById(int idMetodoPago) {
        return repository.findById(idMetodoPago).map(mapper::toModel);
    }

    @Override
    public MetodoPago save(MetodoPago metodoPago) {
        MetodoPagoEntity metodoPagoEntity = mapper.toEntity(metodoPago);
        if(metodoPagoEntity.getIdMetodoPago() == 0){
            metodoPagoEntity.setIdMetodoPago(null);
        }
        return mapper.toModel(repository.save(metodoPagoEntity));
    }

    @Override
    public void deleteById(int idMetodoPago) {
        repository.deleteById(idMetodoPago);
    }

    @Override
    public boolean existsById(int idMetodoPago) {
        return repository.findById(idMetodoPago).isPresent();
    }

}

