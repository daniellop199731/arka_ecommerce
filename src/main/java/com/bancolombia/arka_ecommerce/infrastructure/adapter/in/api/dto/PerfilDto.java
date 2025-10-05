package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilDto {

    private int idPerfil;

    @NotBlank(message = "El nombre del perfil no puede estar vacío")
    @NotNull(message = "El nombre del perfil no puede ser nulo")
    private String nombrePerfil;

}

