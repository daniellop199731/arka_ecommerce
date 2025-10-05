package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity;

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
@Table(name = "carritosCompraProductos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoCompraProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "idCarritoCompra")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private CarritoCompraEntity carritoCompra;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ProductoEntity productoCarritoCompra;
    
    private int unidadesProducto;    
    
}
