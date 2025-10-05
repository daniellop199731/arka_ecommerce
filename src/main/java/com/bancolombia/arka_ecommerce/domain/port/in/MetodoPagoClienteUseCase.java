package com.bancolombia.arka_ecommerce.domain.port.in;

import java.util.List;

import com.bancolombia.arka_ecommerce.application.utils.ResponseObject;
import com.bancolombia.arka_ecommerce.domain.model.MetodoPagoCliente;

public interface MetodoPagoClienteUseCase {

    List<MetodoPagoCliente> getMetodosPagoByIdCliente(int idCliente);
    ResponseObject<MetodoPagoCliente> createMetodoPagoCliente(int idMetodoPago, int idCliente);

    /**
     * Gestiona creacion y actualizacion
     * Si existe la combinacion de Cliente y metodo de pago, solo actualiza el valor de la cuenta
     * Si no existe la combinacion de Cliente y metodo de pago, se crea un nuevo registro
     * @param idCliente         id del Cliente al que se le asignara el metodo de pago
     * @param idMetodoCuenta    id del metodo de pago a asignar
     * @param valorCuenta       valor monetario a recargar en el metodo de pago
     * @return                  objeto modelo de metodo pago con los datos persistidos
     */
    ResponseObject<MetodoPagoCliente> manageMetodoPagoCliente(int idCliente, int idMetodoCuenta, double valorCuenta);
    boolean deleteByIdClienteAndMetodoPago(int idCliente,int idMetodoPago);

}
