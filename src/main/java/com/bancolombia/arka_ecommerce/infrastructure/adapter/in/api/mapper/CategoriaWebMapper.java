package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.mapper;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.domain.model.Categoria;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto.CategoriaDto;

@Component
public class CategoriaWebMapper {

    public CategoriaDto toDto(Categoria categoria){
        if(categoria == null){
            return null;
        }

        CategoriaDto categoriaDto = new CategoriaDto();
        categoriaDto.setIdCategoria(categoria.getIdCategoria());
        categoriaDto.setNombreCategoria(categoria.getNombreCategoria());
        return categoriaDto;
    }

    public Categoria toModel(CategoriaDto categoriaDto){
        if(categoriaDto == null){
            return null;
        }

        Categoria categoria = new Categoria();
        categoria.setIdCategoria(categoriaDto.getIdCategoria());
        categoria.setNombreCategoria(categoriaDto.getNombreCategoria());
        return categoria;
    }    

}
