package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.MetodoPagoClienteEntity;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.ClienteEntity;

//MetodoPagoClienteJpaRepository: se guarda el archivo como MetodoPagoClienteJR
//porque fork se puso caprichoso con los nombres largos de los archivos
public interface MetodoPagoClienteJR extends JpaRepository<MetodoPagoClienteEntity, Integer> {

    List<MetodoPagoClienteEntity> findByClienteMetodoPago(ClienteEntity Cliente);

    @Query(nativeQuery = true, value = "select * from metodosPagoCliente M where idClienteMetodoPago = ?1 and idMetodoPago = ?2")
    Optional<MetodoPagoClienteEntity> findByClienteAndMetodoPago(int idClienteMetodoPago, int idMetodoPago);
}
