package com.arka.cotizador.infrastructure.config;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.Data;

@Component
@Data
public class WebClientConfig {

    private String domain;
    private String port;
    private WebClient webClienteProductos;
    
    public WebClientConfig (WebClient.Builder webClientBuilder){
        this.domain = "localhost";
        this.port = "8081";
        this.webClienteProductos = webClientBuilder.baseUrl("http://"+domain+":"+port+"/productos").build();
    }

}
