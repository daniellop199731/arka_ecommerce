package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorDto {

    private int idProveedor;

    @NotBlank(message = "La identificacion del proveedor no puede estar vacia")
    @NotNull(message = "La identificacion del proveedor es requerida")
    private String identificacionProveedor;

    @NotBlank(message = "El nombre del proveedor no puede estar vacio")
    @NotNull(message = "El nombre del proveedor es requerida")
    private String nombreProveedor;

    @NotBlank(message = "El telefono del proveedor no puede estar vacio")
    @NotNull(message = "El telefono del proveedor es requerido ")
    private String telefonoProveedor;

    @NotBlank(message = "El correo electronico del proveedor no puede estar vacio")
    @NotNull(message = "El correo electronico del proveedor es requerido")
    @Email(message = "El correo electronico debe tener un estructura valida")
    private String correoElectronicoProveedor;    

}
