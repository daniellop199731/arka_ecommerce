package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.EstadoDespachoEntity;

@Repository
public interface EstadoDespachoJR extends JpaRepository<EstadoDespachoEntity, Integer> {

}
