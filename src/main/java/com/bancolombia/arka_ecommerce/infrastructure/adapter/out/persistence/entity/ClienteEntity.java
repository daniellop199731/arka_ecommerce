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
@Table(name = "clientes")

@Data
@NoArgsConstructor 
@AllArgsConstructor
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;

    private String identificacionCliente;
    private String correoElectronicoCliente;
    private String nombresCliente;
    private String apellidosCliente;
    private String direccionDespachoCliente;
    private String contrasennaCliente;
    private String nicknameCliente;

    @ManyToOne
    @JoinColumn(name = "idPerfilCliente")
    private PerfilEntity perfil;    

    @JsonIgnore
    @OneToMany(mappedBy = "clienteMetodoPago")
    private List<MetodoPagoClienteEntity> metodosPagoCliente;

    @JsonIgnore
    @OneToMany(mappedBy = "clienteCarritoCompra")
    private List<CarritoCompraEntity> carritosCompra;
}

