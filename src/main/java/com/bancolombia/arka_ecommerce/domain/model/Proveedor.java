package com.bancolombia.arka_ecommerce.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor {

    private int idProveedor;
    private String identificacionProveedor;
    private String nombreProveedor;
    private String telefonoProveedor;
    private String correoElectronicoProveedor;

}
