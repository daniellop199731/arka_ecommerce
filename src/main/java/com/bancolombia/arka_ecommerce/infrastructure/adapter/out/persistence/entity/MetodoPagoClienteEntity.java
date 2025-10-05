package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "metodosPagoCliente")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetodoPagoClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idClienteMetodoPago")
    private ClienteEntity clienteMetodoPago;

    @ManyToOne
    @JoinColumn(name = "idMetodoPago")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private MetodoPagoEntity metodoPago;

    private double valorCuentaMetodoPago;

}
