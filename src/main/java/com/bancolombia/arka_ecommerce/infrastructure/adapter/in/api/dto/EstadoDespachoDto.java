package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoDespachoDto {

    private int idEstadoDespacho;

    @NotBlank(message = "El nombre de estado de despacho no puede estar vacio")
    @NotNull(message = "El nombre de estado de despacho es requerido")
    private String nombreEstadoDespacho;    

}
