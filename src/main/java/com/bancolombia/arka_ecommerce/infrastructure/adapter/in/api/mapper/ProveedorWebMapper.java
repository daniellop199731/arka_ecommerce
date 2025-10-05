package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.mapper;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.domain.model.Proveedor;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto.ProveedorDto;

@Component
public class ProveedorWebMapper {

    public ProveedorDto toDto(Proveedor proveedor){
        if(proveedor == null){
            return null;
        }

        ProveedorDto proveedorDto = new ProveedorDto();
        proveedorDto.setIdProveedor(proveedor.getIdProveedor());
        proveedorDto.setIdentificacionProveedor(proveedor.getIdentificacionProveedor());
        proveedorDto.setNombreProveedor(proveedor.getNombreProveedor());
        proveedorDto.setTelefonoProveedor(proveedor.getTelefonoProveedor());
        proveedorDto.setCorreoElectronicoProveedor(proveedor.getCorreoElectronicoProveedor());
        return proveedorDto;
    }

    public Proveedor toModel(ProveedorDto proveedorDto){
        if(proveedorDto == null){
            return null;
        }

        Proveedor proveedor = new Proveedor();
        proveedor.setIdProveedor(proveedorDto.getIdProveedor());
        proveedor.setIdentificacionProveedor(proveedorDto.getIdentificacionProveedor());
        proveedor.setNombreProveedor(proveedorDto.getNombreProveedor());
        proveedor.setTelefonoProveedor(proveedorDto.getTelefonoProveedor());
        proveedor.setCorreoElectronicoProveedor(proveedorDto.getCorreoElectronicoProveedor());
        return proveedor;
    }


}
