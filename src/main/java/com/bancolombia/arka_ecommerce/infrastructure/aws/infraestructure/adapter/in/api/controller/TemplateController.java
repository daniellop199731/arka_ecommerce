package com.bancolombia.arka_ecommerce.infrastructure.aws.infraestructure.adapter.in.api.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_ecommerce.infrastructure.aws.domain.port.in.ManageTemplatesUseCase;

import java.util.Optional;

/**
 * Controlador REST que expone los endpoints para la gestión de plantillas.
 * Actúa como un adaptador de entrada, traduciendo las peticiones HTTP
 * a llamadas al caso de uso del dominio.
 */
@RestController
public class TemplateController {

    private final ManageTemplatesUseCase manageTemplatesUseCase;

    /**
     * Constructor que inyecta el caso de uso de gestión de plantillas.
     * @param manageTemplatesUseCase El puerto de entrada del dominio.
     */
    public TemplateController(ManageTemplatesUseCase manageTemplatesUseCase) {
        this.manageTemplatesUseCase = manageTemplatesUseCase;
    }

    /**
     * Endpoint para obtener el contenido de una plantilla por su nombre.
     * @param templateName El nombre de la plantilla.
     * @return Una ResponseEntity con el contenido de la plantilla o un mensaje de error.
     */
    @GetMapping("/templates/{templateName}")
    public ResponseEntity<String> getTemplate(@PathVariable String templateName) {
        Optional<String> content = manageTemplatesUseCase.getTemplate(templateName);
        if (content.isEmpty()) {
            // Retorna 404 Not Found si la plantilla no se encuentra
            return new ResponseEntity<>("Error: Plantilla no encontrada o no se pudo cargar.", HttpStatus.NOT_FOUND);
        }
        // Retorna 200 OK con el contenido de la plantilla
        return new ResponseEntity<>(content.get(), HttpStatus.OK);
    }

 /**
     * Nuevo endpoint para enviar un correo electrónico utilizando una plantilla de S3.
     * @param toEmail La dirección de correo electrónico del destinatario.
     * @param templateName El nombre de la plantilla a utilizar.
     * @param subject El asunto del correo electrónico (opcional, con valor por defecto).
     * @return Una ResponseEntity indicando el éxito o fracaso del envío.
     */
    @PostMapping("/send-email-from-template")
    public ResponseEntity<String> sendEmailFromTemplate(
            @RequestParam String toEmail,
            @RequestParam String templateName,
            @RequestParam(required = false, defaultValue = "Notificación de tu tienda en línea") String subject
    ) {
        try {
            manageTemplatesUseCase.sendTemplatedEmail(toEmail, templateName, subject);
            return new ResponseEntity<>("Correo enviado a " + toEmail + " usando la plantilla " + templateName + ".", HttpStatus.OK);
        } catch (RuntimeException e) {
            // Captura la excepción lanzada por el dominio/infraestructura
            System.err.println("Error en el controlador al enviar correo: " + e.getMessage());
            return new ResponseEntity<>("Error al enviar correo: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }    
}
