package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDto {

    private int idProducto;
    private String referenciaProducto;

    @NotBlank(message = "Debe proporcionar un nombre al producto")
    @NotNull(message = "Debe proporcionar un nombre al producto")
    private String nombreProducto;
    
    @NotBlank(message = "Debe proporcionar una descripcion")
    @NotNull(message = "Debe proporcionar una descripcion")
    private String descripcionProducto;
    
    @NotNull(message = "Debe proporcionar un precio al producto")
    @Positive(message = "El precio del producto no puede ser cero")
    private double precioProducto;
    
    @NotNull(message = "Debe proporcionar un stock. Este puede ser cero")
    private int stockProducto;

    @NotNull(message = "Debe proporcionar un stock minimo. Este puede ser cero")
    private int stockMinimoProducto;
    
    @NotNull(message = "Debe proporcionar un proveedor valido")
    private ProveedorDto proveedorProducto;    

    @NotNull(message = "Debe ingresas una cantidad de unidades a solicitar. Este puede ser cero")
    private int unidadesSolicitarProducto; 
    
    @NotNull(message = "Debe proporcionar una categoria valida")
    private CategoriaDto categoriaProducto;

    private List<CarritoCompraProductoDto> carritoCompraProductos;

}
