package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "carritosCompra")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoCompraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCarritoCompra;

    @NotNull(message = "Debe proporcionar un id de usuario valido")
    @ManyToOne
    @JoinColumn(name = "idClienteCarritoCompra")
    private ClienteEntity clienteCarritoCompra;
    
    @ManyToOne
    @JoinColumn(name = "idEstadoDespachoCarritoCompra")
    private EstadoDespachoEntity estadoDespacho;

    @ManyToOne
    @JoinColumn(name = "idMetodoPagoCarritoCompra")
    private MetodoPagoEntity metodoPagoCarritoCompra;

    //0: para carrito no pagado, 1: para carrito pagado
    private int carritoPagado;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaCreacionCarritoCompra;

    private int cantidadNotificacionesPorCarritoAbandonado;

    @JsonIgnore
    @OneToMany(mappedBy = "carritoCompra")
    private List<CarritoCompraProductoEntity> carritoCompraProductos;

    @PrePersist
    public void toCreate(){
        setFechaCreacionCarritoCompra(new Date());
        setCarritoPagado(0);
        setCantidadNotificacionesPorCarritoAbandonado(0);
    }    
    
}
