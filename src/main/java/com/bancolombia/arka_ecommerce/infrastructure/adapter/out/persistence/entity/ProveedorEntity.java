package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proveedores")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProveedor;
    
    private String identificacionProveedor;
    private String nombreProveedor;
    private String telefonoProveedor;
    private String correoElectronicoProveedor;

    @JsonBackReference
    @OneToMany(mappedBy = "proveedorProducto")
    private List<ProductoEntity> productos;

}
