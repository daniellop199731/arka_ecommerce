package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * Anotacion personalizada para validar que un nombre de usuario no exista en la base de datos
 */

//Clase que define la logica de la validacion
@Constraint(validatedBy = ExistsByUserNameValidator.class)

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsByUserName {
    
    //Mensaje por defecto si la validacion falla
	String message() default "Ya existe en la base de datos , escoja otro nombre de usuario";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };    

}
