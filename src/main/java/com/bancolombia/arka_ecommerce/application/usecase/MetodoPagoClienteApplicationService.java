package com.bancolombia.arka_ecommerce.application.usecase;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bancolombia.arka_ecommerce.application.utils.ResponseObject;
import com.bancolombia.arka_ecommerce.domain.model.MetodoPago;
import com.bancolombia.arka_ecommerce.domain.model.MetodoPagoCliente;
import com.bancolombia.arka_ecommerce.domain.model.Cliente;
import com.bancolombia.arka_ecommerce.domain.port.in.MetodoPagoClienteUseCase;
import com.bancolombia.arka_ecommerce.domain.port.out.MetodoPagoRepositoryPort;
import com.bancolombia.arka_ecommerce.domain.port.out.MetodoPagoClienteRepositoryPort;
import com.bancolombia.arka_ecommerce.domain.port.out.ClienteRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MetodoPagoClienteApplicationService implements MetodoPagoClienteUseCase {

    private final MetodoPagoClienteRepositoryPort metodoPagoClienteRepositoryPort;
    private final MetodoPagoRepositoryPort metodoPagoRepositoryPort;
    private final ClienteRepositoryPort ClienteRepositoryPort;

    private static ResponseObject<MetodoPagoCliente> rObj;

    @Override
    public List<MetodoPagoCliente> getMetodosPagoByIdCliente(int idCliente) {
        Cliente Cliente = new Cliente();
        Cliente.setIdCliente(idCliente);
        return metodoPagoClienteRepositoryPort.findByClienteMetodoPago(Cliente);
    }

    @Override
    public ResponseObject<MetodoPagoCliente> createMetodoPagoCliente(int idCliente, int idMetodoPago) {
        rObj = new ResponseObject<>();

        if(!ClienteRepositoryPort.existsById(idCliente)){
            rObj.setAsNotSuccessful("No existe el Cliente con id " + idCliente);
            return rObj;
        }

        if(!metodoPagoRepositoryPort.existsById(idMetodoPago)){
            rObj.setAsNotSuccessful("No existe el Metodo de pago con id " + idMetodoPago);
            return rObj;
        }

        if(metodoPagoClienteRepositoryPort.existByIdClienteAndMetodoPago(idCliente, idMetodoPago)){
            rObj.setAsNotSuccessful("Ya existe la asignacion");
            return rObj;
        }

        MetodoPagoCliente metodoPagoCliente = new MetodoPagoCliente();

        Cliente ClienteMetodoPago = new Cliente();
        ClienteMetodoPago.setIdCliente(idCliente);
        MetodoPago metodoPago = new MetodoPago();
        metodoPago.setIdMetodoPago(idMetodoPago);

        metodoPagoCliente.setClienteMetodoPago(ClienteMetodoPago);
        metodoPagoCliente.setMetodoPago(metodoPago);

        rObj.setAsSuccessful("Se agrego el metodo de pago al Cliente con exito"
                            , metodoPagoClienteRepositoryPort.save(metodoPagoCliente));

        return rObj;
    }

    @Override
    public ResponseObject<MetodoPagoCliente> manageMetodoPagoCliente(int idCliente, int idMetodoPago,
            double valorCuenta) {

        rObj = new ResponseObject<>();

        if(!metodoPagoRepositoryPort.existsById(idMetodoPago)){
            rObj.setAsNotSuccessful("No existe el Metodo de pago con id " + idMetodoPago);
            return rObj;
        }

        if(!ClienteRepositoryPort.existsById(idCliente)){
            rObj.setAsNotSuccessful("No existe el Metodo de pago con id " + idMetodoPago);
            return rObj;
        }

        if(valorCuenta <= 0){
            rObj.setAsNotSuccessful("El valor a cargar debe ser mayor a cero");
            return rObj;
        }

        MetodoPagoCliente metodoPagoCliente = new MetodoPagoCliente();
        Cliente ClienteMetodoPago = new Cliente();
        ClienteMetodoPago.setIdCliente(idCliente);
        MetodoPago metodoPago = new MetodoPago();
        metodoPago.setIdMetodoPago(idMetodoPago);

        metodoPagoCliente.setClienteMetodoPago(ClienteMetodoPago);
        metodoPagoCliente.setMetodoPago(metodoPago);
        metodoPagoCliente.setValorCuentaMetodoPago(valorCuenta);

        Optional<MetodoPagoCliente> metodoPagoClienteFinded = metodoPagoClienteRepositoryPort.findByIdClienteAndIdMetodoPago(idCliente, idMetodoPago);
        String msg = "Se agrego el metodo de pago al Cliente con exito";

        if(metodoPagoClienteFinded.isPresent()){
            metodoPagoCliente.setId(metodoPagoClienteFinded.get().getId());
            metodoPagoCliente.setValorCuentaMetodoPago(metodoPagoClienteFinded.get().getValorCuentaMetodoPago() + valorCuenta);
            msg = "Se realizó la actualizacíon con exíto";
        }

        rObj.setAsSuccessful(msg ,metodoPagoClienteRepositoryPort.save(metodoPagoCliente));
        return rObj;
    }

    @Override
    public boolean deleteByIdClienteAndMetodoPago(int idCliente,
            int idMetodoPago) {
        Optional<MetodoPagoCliente> metodoPagoCliente = metodoPagoClienteRepositoryPort.findByIdClienteAndIdMetodoPago(idCliente, idMetodoPago);
        if(metodoPagoCliente.isPresent()){
            metodoPagoClienteRepositoryPort.deleteById(metodoPagoCliente.get().getId());
            return true;
        }
        return false;
    }

}
