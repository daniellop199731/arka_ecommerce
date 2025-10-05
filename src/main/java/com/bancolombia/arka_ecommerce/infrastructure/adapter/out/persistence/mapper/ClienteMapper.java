package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.domain.model.Cliente;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.ClienteEntity;

@Component
public class ClienteMapper {

    public Cliente toModel(ClienteEntity clienteEntity) {
        if (clienteEntity == null) {
            return null;
        }
        Cliente Cliente = new Cliente();
        Cliente.setIdCliente(clienteEntity.getIdCliente());
        Cliente.setIdentificacionCliente(clienteEntity.getIdentificacionCliente());
        Cliente.setCorreoElectronicoCliente(clienteEntity.getCorreoElectronicoCliente());
        Cliente.setNombresCliente(clienteEntity.getNombresCliente());
        Cliente.setApellidosCliente(clienteEntity.getApellidosCliente());
        Cliente.setDireccionDespachoCliente(clienteEntity.getDireccionDespachoCliente());
        Cliente.setNicknameCliente(clienteEntity.getNicknameCliente());
        Cliente.setPerfil(new PerfilMapper().toDomain(clienteEntity.getPerfil()));
        return Cliente;
    }

    public ClienteEntity toEntity(Cliente Cliente) {
        if (Cliente == null) {
            return null;
        }
        ClienteEntity ClienteEntity = new ClienteEntity();
        ClienteEntity.setIdCliente(Cliente.getIdCliente());
        ClienteEntity.setIdentificacionCliente(Cliente.getIdentificacionCliente());
        ClienteEntity.setCorreoElectronicoCliente(Cliente.getCorreoElectronicoCliente());
        ClienteEntity.setNombresCliente(Cliente.getNombresCliente());
        ClienteEntity.setApellidosCliente(Cliente.getApellidosCliente());
        ClienteEntity.setDireccionDespachoCliente(Cliente.getDireccionDespachoCliente());
        ClienteEntity.setNicknameCliente(Cliente.getNicknameCliente());
        ClienteEntity.setPerfil(new PerfilMapper().toEntity(Cliente.getPerfil()));
        return ClienteEntity;
    }

}

