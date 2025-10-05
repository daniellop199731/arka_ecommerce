package com.bancolombia.arka_ecommerce.application.usecase;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bancolombia.arka_ecommerce.domain.model.Producto;
import com.bancolombia.arka_ecommerce.domain.port.in.ProductoUseCase;
import com.bancolombia.arka_ecommerce.domain.port.out.ProductoRepositoryPort;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.dto.ProductoDto;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.mapper.ProductoWebMapper;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductoApplicationService implements ProductoUseCase {

    private final ProductoRepositoryPort productoRepositoryPort;
    private final ProductoWebMapper productoWebMapper;

    @Override
    public List<Producto> getAllProductos() {
        return productoRepositoryPort.findAll();
    }

    @Override
    public Producto getProductoById(int idProducto) {
        if(productoRepositoryPort.existById(idProducto)){    
            return productoRepositoryPort.findById(idProducto).get();
        }
        return new Producto();
    }

    @Override
    public Producto createProducto(Producto producto) {
        return productoRepositoryPort.save(producto);
    }

    @Override
    public Producto updateProducto(int idProducto, Producto producto) {
        if(productoRepositoryPort.existById(idProducto)){
            producto.setIdProducto(idProducto);
            return productoRepositoryPort.save(producto);
        }
        return new Producto();
    }

    @Override
    public boolean deleteProducto(int idProducto) {
        if(productoRepositoryPort.existById(idProducto)){
            productoRepositoryPort.deleteById(idProducto);
            return true;
        }
        return false;
    }

    @Override
    public Producto increaseStock(int idProducto, int stock) {
        Optional<Producto> producto = productoRepositoryPort.findById(idProducto);
        if(producto.isPresent()){
            Producto productoFinded = producto.get();
            productoFinded.setStockProducto(productoFinded.getStockProducto() + stock);
            return productoRepositoryPort.save(productoFinded);
        }
        return new Producto();
    }

    @Override
    public Producto decreaseStock(int idProducto, int stock) {
        Optional<Producto> producto = productoRepositoryPort.findById(idProducto);
        if(producto.isPresent()){
            Producto productoFinded = producto.get();
            if(productoFinded.getStockProducto() - stock <= productoFinded.getStockMinimoProducto()){
                //Reactive: Realizar solicitud de unidades del producto 
                //Unidades a solicitar en productoFinded.getUnidadesSolicitudProducto()
            }

            if(productoFinded.getStockProducto() - stock > 0){
                productoFinded.setStockProducto(productoFinded.getStockProducto() - stock);
            } else{
                productoFinded.setStockProducto(0);
            }
            
            return productoRepositoryPort.save(productoFinded);
        }
        return new Producto();
    }

    @Override
    public Flux<Producto> reactiveGetAllProductos() {
        return 
            Flux.fromIterable(productoRepositoryPort.findAll())
            .doOnError(error -> System.out.println("Error en la operacion: " + error.getMessage()));
    }

    @Override
    public Mono<ProductoDto> getProductoByIdReactive(int idProducto) {
        return Mono.fromCallable(()->{
            Optional<Producto> productoFinded = productoRepositoryPort.findById(idProducto);
            if(productoFinded.isPresent()){
                return productoWebMapper.toDto(productoFinded.get());
            }
            return productoWebMapper.toDto(new Producto());
            
        })
        .doOnSuccess(p -> System.out.println("Producto encontrado " + p.getIdProducto()))
        .doOnError(erro -> System.out.println("Error en la operacion: " + erro.getMessage()));
    }

    @Override
    public Mono<Double> getPrice(int idProducto) {
        return Mono.fromCallable(()->{
            try{
                Thread.sleep(100);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
            Optional<Producto> productoFinded = productoRepositoryPort.findById(idProducto);
            if(productoFinded.isPresent()){
                return productoFinded.get().getPrecioProducto();
            }
            return 0.0;            
        })
        .doOnSuccess(response -> System.out.println("Consulta ejecutada con exito"))
        .doOnError(erro -> System.out.println("Error en la operacion: " + erro.getMessage()));
    }

    @Override
    public boolean existeProducto(int idProducto) {
        return productoRepositoryPort.existById(idProducto);
    }    

}
