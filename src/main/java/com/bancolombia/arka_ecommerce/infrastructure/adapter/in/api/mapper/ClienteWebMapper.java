package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.mapper;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.domain.model.Cliente;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto.ClienteDto;

@Component
public class ClienteWebMapper {

    public ClienteDto toDto(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        ClienteDto ClienteDto = new ClienteDto();
        ClienteDto.setIdCliente(cliente.getIdCliente());
        ClienteDto.setIdentificacionCliente(cliente.getIdentificacionCliente());
        ClienteDto.setCorreoElectronicoCliente(cliente.getCorreoElectronicoCliente());
        ClienteDto.setNombresCliente(cliente.getNombresCliente());
        ClienteDto.setApellidosCliente(cliente.getApellidosCliente());
        ClienteDto.setDireccionDespachoCliente(cliente.getDireccionDespachoCliente());
        ClienteDto.setNicknameCliente(cliente.getNicknameCliente());
        ClienteDto.setPerfil(new PerfilWebMapper().toDto(cliente.getPerfil()));
        return ClienteDto;
    }

    public Cliente toModel(ClienteDto clienteDto) {
        if (clienteDto == null) {
            return null;
        }
        Cliente Cliente = new Cliente();
        Cliente.setIdCliente(clienteDto.getIdCliente());
        Cliente.setIdentificacionCliente(clienteDto.getIdentificacionCliente());
        Cliente.setCorreoElectronicoCliente(clienteDto.getCorreoElectronicoCliente());
        Cliente.setNombresCliente(clienteDto.getNombresCliente());
        Cliente.setApellidosCliente(clienteDto.getApellidosCliente());
        Cliente.setDireccionDespachoCliente(clienteDto.getDireccionDespachoCliente());
        Cliente.setNicknameCliente(clienteDto.getNicknameCliente());
        Cliente.setPerfil(new PerfilWebMapper().toModel(clienteDto.getPerfil()));
        return Cliente;
    }

}

