package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto;

import java.util.List;

import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.validations.ExistsByUserName;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    
    private int id;

    @ExistsByUserName
    @NotBlank(message =  "Debe proporcionar un nombre de usuario")
    @NotNull(message =  "Debe proporcionar un nombre de usuario (username)")
    private String username;

    @NotBlank(message =  "Debe proporcionar una contraseña")
    @NotNull(message =  "Debe proporcionar una contraseña (password)")

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean enabled;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<RoleDto> roles;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean admin;
}
