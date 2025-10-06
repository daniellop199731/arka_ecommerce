package com.bancolombia.arka_ecommerce.infrastructure.aws.domain.port.in;

import org.springframework.web.multipart.MultipartFile; // Se usa MultipartFile aquí para simplificar la interfaz del caso de uso

/**
 * Puerto de entrada que define la operación de negocio para subir una imagen de producto.
 * La capa de aplicación (ej. un controlador REST) interactúa con esta interfaz.
 */
public interface UploadProductImageUseCase {
    /**
     * Sube la imagen de un producto.
     *
     * @param image El archivo MultipartFile que contiene la imagen a subir.
     * @return La URL pública de la imagen subida.
     */
    String uploadProductImage(MultipartFile image);
}
