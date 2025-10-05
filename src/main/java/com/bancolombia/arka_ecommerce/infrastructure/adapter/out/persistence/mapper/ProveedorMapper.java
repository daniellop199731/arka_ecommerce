package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.domain.model.Proveedor;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.ProveedorEntity;

@Component
public class ProveedorMapper {

    public Proveedor toModel(ProveedorEntity proveedorEntity){
        if(proveedorEntity == null){
            return null;
        }

        Proveedor proveedor = new Proveedor();
        proveedor.setIdProveedor(proveedorEntity.getIdProveedor());
        proveedor.setIdentificacionProveedor(proveedorEntity.getIdentificacionProveedor());
        proveedor.setNombreProveedor(proveedorEntity.getNombreProveedor());
        proveedor.setTelefonoProveedor(proveedorEntity.getTelefonoProveedor());
        proveedor.setCorreoElectronicoProveedor(proveedorEntity.getCorreoElectronicoProveedor());
        return proveedor;
    }

    public ProveedorEntity toEntity(Proveedor proveedor){
        if(proveedor == null){
            return null;
        }

        ProveedorEntity proveedorEntity = new ProveedorEntity();
        proveedorEntity.setIdProveedor(proveedor.getIdProveedor());
        proveedorEntity.setIdentificacionProveedor(proveedor.getIdentificacionProveedor());
        proveedorEntity.setNombreProveedor(proveedor.getNombreProveedor());
        proveedorEntity.setTelefonoProveedor(proveedor.getTelefonoProveedor());
        proveedorEntity.setCorreoElectronicoProveedor(proveedor.getCorreoElectronicoProveedor());
        return proveedorEntity;
    }    

}
