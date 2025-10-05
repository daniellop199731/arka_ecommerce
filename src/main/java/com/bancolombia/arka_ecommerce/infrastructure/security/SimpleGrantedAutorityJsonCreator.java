package com.bancolombia.arka_ecommerce.infrastructure.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SimpleGrantedAutorityJsonCreator {

    @JsonCreator
    public SimpleGrantedAutorityJsonCreator (@JsonProperty("authority") String role){

    }
    
}
