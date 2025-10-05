package com.bancolombia.arka_ecommerce.application.usecase;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.bancolombia.arka_ecommerce.domain.model.Cliente;
import com.bancolombia.arka_ecommerce.domain.port.in.ClienteUserCase;
import com.bancolombia.arka_ecommerce.domain.port.out.ClienteRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteApplicationService implements ClienteUserCase {

    private final ClienteRepositoryPort ClienteRepositoryPort;

    @Override
    public List<Cliente> getAllClientes() {
        return ClienteRepositoryPort.findAll();
    }

    @Override
    public Cliente getClienteById(int idCliente) {
        Optional<Cliente> ClienteFinded = ClienteRepositoryPort.findById(idCliente);
        if(ClienteFinded.isPresent()){
            return ClienteFinded.get();
        }
        return new Cliente();
    }

    @Override
    public Cliente createCliente(Cliente Cliente) {
        Cliente ClienteFinded = this.getClienteByIdentificacion(Cliente.getIdentificacionCliente());
        if(ClienteFinded.getIdCliente() == 0 ){            
            return ClienteRepositoryPort.save(Cliente);
        }
        return ClienteFinded;
    }

    @Override
    public Cliente updateCliente(int idCliente, Cliente Cliente) {   
        Optional<Cliente> ClienteFindedById = ClienteRepositoryPort.findById(idCliente);
        
        if(!ClienteFindedById.isPresent()){
            return new Cliente();
        }

        if(this.canUpdateCliente(Cliente.getIdentificacionCliente(), idCliente)){
            Cliente.setIdCliente(idCliente);
            return ClienteRepositoryPort.save(Cliente);
        }
        
        return ClienteFindedById.get();
    }

    @Override
    public boolean deleteCliente(int idCliente) {
        if(ClienteRepositoryPort.existsById(idCliente)){
            ClienteRepositoryPort.deleteById(idCliente);
            return true;
        }
        return false;
    }

    @Override
    public Cliente getClienteByIdentificacion(String identificacionCliente) {
        Optional<Cliente> ClienteFinded = ClienteRepositoryPort.findByIdentificacion(identificacionCliente);
        if(ClienteFinded.isPresent()){
            return ClienteFinded.get();
        }
        return new Cliente();
    }

    @Override
    public boolean canUpdateCliente(String identificacionCliente, int idCliente) {
        /**
         * El Cliente se puede actualizar si:
         * 1. La identificacion nueva no existe en la base de datos
         * 2. Si la identificacion nueva existe, debe ser del mismo Cliente
         *    (es decir, el idCliente debe coincidir con el id del Cliente encontrado
         */

        Optional<Cliente> ClienteFinded = ClienteRepositoryPort.findByIdentificacion(identificacionCliente);
        if(ClienteFinded.isPresent()){
            //Indica que la identificacion ya existe
            //Se debe validar si existe para el mismo id de Cliente
            if(ClienteFinded.get().getIdCliente() == idCliente){
                return true;
            }
            return false;
        }
        return true;
    }
    
}

