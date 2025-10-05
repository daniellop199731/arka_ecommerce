package com.bancolombia.arka_ecommerce.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.bancolombia.arka_ecommerce.domain.model.MetodoPagoCliente;
import com.bancolombia.arka_ecommerce.domain.model.Cliente;

public interface MetodoPagoClienteRepositoryPort {

    List<MetodoPagoCliente> findByClienteMetodoPago(Cliente Cliente);
    Optional<MetodoPagoCliente> findByIdClienteAndIdMetodoPago(int idClienteMetodoPago, int idMetodoPago);
    MetodoPagoCliente save(MetodoPagoCliente metodoPagoCliente);
    boolean existByIdClienteAndMetodoPago(int idClienteMetodoPago, int idMetodoPago);
    void deleteById(int idMetodoPagoCliente);

}
