package com.bancolombia.arka_ecommerce.infrastructure.aws.infraestructure.adapter.in.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bancolombia.arka_ecommerce.infrastructure.aws.domain.port.in.UploadProductImageUseCase;

/**
 * Controlador REST para la gestión de productos.
 * Actúa como un adaptador de entrada, traduciendo las peticiones HTTP
 * a llamadas a los casos de uso del dominio, incluyendo la carga de imágenes.
 */
@RestController
public class ProductController {

    private final UploadProductImageUseCase uploadProductImageUseCase;
    // Asume que también tienes un caso de uso para la gestión de productos (CRUD)
    // private final ManageProductUseCase manageProductUseCase;

    /**
     * Constructor que inyecta el caso de uso para la carga de imágenes.
     * @param uploadProductImageUseCase El puerto de entrada para subir imágenes de producto.
     * // @param manageProductUseCase El puerto de entrada para la gestión CRUD de productos.
     */
    public ProductController(UploadProductImageUseCase uploadProductImageUseCase /*, ManageProductUseCase manageProductUseCase */) {
        this.uploadProductImageUseCase = uploadProductImageUseCase;
        // this.manageProductUseCase = manageProductUseCase;
    }

    /**
     * Endpoint para subir una imagen de producto.
     * Este endpoint podría ser parte de la creación o actualización de un producto.
     * Para simplificar, aquí solo se muestra la lógica de subida de imagen.
     *
     * @param imagen El archivo MultipartFile de la imagen.
     * @return Una ResponseEntity con la URL de la imagen subida o un mensaje de error.
     */
    @PostMapping("/products/image-upload") // Nuevo endpoint específico para subir imágenes
    public ResponseEntity<String> uploadProductImage(@RequestParam("image") MultipartFile imagen) {
        if (imagen == null || imagen.isEmpty()) {
            return new ResponseEntity<>("Se requiere una imagen para subir.", HttpStatus.BAD_REQUEST);
        }

        try {
            // Llama al caso de uso para subir la imagen
            String imageUrl = uploadProductImageUseCase.uploadProductImage(imagen);

            // En un escenario real, aquí integrarías esta URL con tu lógica de producto,
            // por ejemplo, guardándola en la base de datos junto con los detalles del producto.
            // Ejemplo: manageProductUseCase.updateProductImageUrl(productId, imageUrl);

            return new ResponseEntity<>("Imagen subida exitosamente. URL: " + imageUrl, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            // Captura errores de validación (ej. imagen vacía)
            return new ResponseEntity<>("Error de validación: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            // Captura errores durante el proceso de subida (ej. S3, I/O)
            System.err.println("Error al subir imagen: " + e.getMessage());
            return new ResponseEntity<>("Error al subir la imagen: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Aquí iría el resto de tu CRUD de productos (ej. crearProducto, obtenerProducto, etc.)
    // que ahora podría recibir la URL de la imagen si se subió previamente, o
    // invocar uploadProductImageUseCase internamente si la imagen se envía junto con los datos del producto.
}
