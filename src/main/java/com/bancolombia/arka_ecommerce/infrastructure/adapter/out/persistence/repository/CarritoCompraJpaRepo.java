package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.CarritoCompraEntity;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.ClienteEntity;

public interface CarritoCompraJpaRepo extends CrudRepository<CarritoCompraEntity, Integer> {
    
    List<CarritoCompraEntity> findByClienteCarritoCompra(ClienteEntity clienteCarritoCompra);

    @Query(value = "select * from carritosCompra C where idClienteCarritoCompra = ?1 and idEstadoDespachoCarritoCompra = 1 order by fechaCreacionCarritoCompra desc", nativeQuery = true)
    List<CarritoCompraEntity> findCarritoActual(int idCliente);

    @Query(value = "select * from carritosCompra C where idEstadoDespachoCarritoCompra = 9", nativeQuery = true)
    List<CarritoCompraEntity> carritosAbandonados();    

    List<CarritoCompraEntity> findByFechaCreacionCarritoCompraBetween(Date dateStar, Date dateEnd);

}
