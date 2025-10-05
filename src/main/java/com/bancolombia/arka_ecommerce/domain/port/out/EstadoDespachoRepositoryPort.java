package com.bancolombia.arka_ecommerce.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.bancolombia.arka_ecommerce.domain.model.EstadoDespacho;

public interface EstadoDespachoRepositoryPort {

    List<EstadoDespacho> findAll();
    Optional<EstadoDespacho> findById(int idEstadoDespacho);
    EstadoDespacho save(EstadoDespacho estadoDespacho);
    void deleteById(int idEstadoDespacho);
    boolean existById(int idEstadoDespacho);
}
