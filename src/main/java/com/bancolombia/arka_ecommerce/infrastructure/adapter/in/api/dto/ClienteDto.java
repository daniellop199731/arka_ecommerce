package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {

    private int idCliente;

    @NotNull(message = "Debe proporcionar una ideantificacion")
    @NotBlank(message = "Debe proporcionar una ideantificacion")
    @Size(min = 9, max = 15, message = "Debe proporcionar una ideantificacion valida")
    private String identificacionCliente;

    @NotNull(message = "Debe proporcionar un correo electronico")
    @NotBlank(message = "Debe proporcionar un correo electronico")
    @Email(message = "Debe proporcionar una direccion de correo con una estructura valida")    
    private String correoElectronicoCliente;

    @NotNull(message = "Debe proporcionar los nombres")
    @NotBlank(message = "Debe proporcionar los nombres")
    @Size(min = 3, max = 45)
    private String nombresCliente;

    @NotNull(message = "Debe proporcionar los apellidos")   
    @NotBlank(message = "Debe proporcionar los apellidos")   
    @Size(min = 5, max = 45)
    private String apellidosCliente;

    private String direccionDespachoCliente;
    private String nicknameCliente;
    private PerfilDto perfil;    

}

