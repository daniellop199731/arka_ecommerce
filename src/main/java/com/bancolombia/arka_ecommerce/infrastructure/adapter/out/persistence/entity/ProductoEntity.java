package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;
    
    private String referenciaProducto;
    private String nombreProducto;
    private String descripcionProducto;
    private double precioProducto;
    private int stockProducto;
    private int stockMinimoProducto;

    @ManyToOne
    @JoinColumn(name =  "idProveedorProducto")
    private ProveedorEntity proveedorProducto;

    private int unidadesSolicitarProducto;

    @ManyToOne
    @JoinColumn(name = "idCategoriaProducto")
    private CategoriaEntity categoriaProducto;    

    @JsonIgnore
    @OneToMany(mappedBy = "productoCarritoCompra")
    private List<CarritoCompraProductoEntity> carritoCompraProductos;

}
