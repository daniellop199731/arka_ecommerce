package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.domain.model.Product;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.ProductEntity;

@Component
public class ProductMapper {
    
    public Product toModel(ProductEntity productEntity){
        if(productEntity == null){
            return null;
        }

        Product product = new Product();
        product.setId(productEntity.getId());
        product.setName(productEntity.getName());
        product.setPrice(productEntity.getPrice());
        product.setComments(productEntity.getComments());

        return product;
    }

    public ProductEntity toEntity(Product product){
        if(product == null){
            return null;
        }

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(product.getId());
        productEntity.setName(product.getName());
        productEntity.setPrice(product.getPrice());
        productEntity.setComments(product.getComments());

        return productEntity;
    }
}
