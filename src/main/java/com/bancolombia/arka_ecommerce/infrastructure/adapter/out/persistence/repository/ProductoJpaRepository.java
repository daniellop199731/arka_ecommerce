package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.ProductoEntity;

public interface ProductoJpaRepository extends JpaRepository<ProductoEntity, Integer> {

}
