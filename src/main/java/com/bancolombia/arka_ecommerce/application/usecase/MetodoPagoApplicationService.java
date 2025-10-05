package com.bancolombia.arka_ecommerce.application.usecase;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.bancolombia.arka_ecommerce.domain.model.MetodoPago;
import com.bancolombia.arka_ecommerce.domain.port.in.MetodoPagoUseCase;
import com.bancolombia.arka_ecommerce.domain.port.out.MetodoPagoRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MetodoPagoApplicationService implements MetodoPagoUseCase{
    
    private final MetodoPagoRepositoryPort metodoPagoRepository;    
    
    @Override
    public List<MetodoPago> getMetodosPago() {
        return metodoPagoRepository.findAll();
    }

    @Override
    public MetodoPago getMetodoPagoById(int idMetodoPago) {
        Optional<MetodoPago> metodoPagoFinded = metodoPagoRepository.findById(idMetodoPago);
        if(metodoPagoFinded.isPresent()){
            return metodoPagoFinded.get();
        }
        return new MetodoPago();
    }

    @Override
    public MetodoPago createMetodoPago(MetodoPago metodoPago) {
        return metodoPagoRepository.save(metodoPago);
    }

    @Override
    public MetodoPago updateMetodoPago(int idMetodoPago, MetodoPago metodoPago) {
        if(metodoPagoRepository.existsById(idMetodoPago)){
            metodoPago.setIdMetodoPago(idMetodoPago);
            return metodoPagoRepository.save(metodoPago);
        } else {
            return new MetodoPago();
        }
    }

    @Override
    public boolean deleteMetodoPagoById(int idMetodoPago) {
        if(metodoPagoRepository.existsById(idMetodoPago)){
            metodoPagoRepository.deleteById(idMetodoPago);
            return true;
        }
        return false;
    }

}

