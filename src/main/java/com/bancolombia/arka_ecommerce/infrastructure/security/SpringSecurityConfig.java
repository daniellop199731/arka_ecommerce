package com.bancolombia.arka_ecommerce.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.bancolombia.arka_ecommerce.infrastructure.security.filter.JwtAuthenticationFilter;
import com.bancolombia.arka_ecommerce.infrastructure.security.filter.JwtValidationFilter;

@Configuration
public class SpringSecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception  {
        return http.authorizeHttpRequests( authz -> authz
            //Rutas que no requieren autenticacion
            .requestMatchers(HttpMethod.GET,"/users").permitAll()
            .requestMatchers(HttpMethod.POST,"/users/register").permitAll()
            .requestMatchers(HttpMethod.POST,"/users").permitAll()
            .requestMatchers(HttpMethod.GET,"/test").permitAll()
            .requestMatchers(HttpMethod.GET,"/productos/price/**").permitAll()
            //... espacio para mas ruras sin autenticacion si se requiere
            //Cualquier otra ruta requerira autenticacion
            .anyRequest().authenticated())

            .addFilter(new JwtAuthenticationFilter(authenticationManager()))
            .addFilter(new JwtValidationFilter(authenticationManager()))

            //Se deshabilita para vistas del lado del servidor
            .csrf(config -> config.disable())
            //Se hace configuracion para que no se guarde la session
            .sessionManagement(managment -> managment
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .build();
    }
    
}
