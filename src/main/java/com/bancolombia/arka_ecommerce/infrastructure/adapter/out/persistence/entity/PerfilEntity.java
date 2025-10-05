package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //Indica a Spring que esta clase es un bean y que refleja informacion de la base de datos
@Table(name = "perfiles") //Se especifica el nombre de la base de datos
@Data //Lombok, geters y seters envevidos
@NoArgsConstructor //Lombok: Constructor sin atributos envevido
@AllArgsConstructor //Lombok: Contructor con todos los atributos envevido
public class PerfilEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPerfil")
    private Integer idPerfil = null;

    private String nombrePerfil;

}

