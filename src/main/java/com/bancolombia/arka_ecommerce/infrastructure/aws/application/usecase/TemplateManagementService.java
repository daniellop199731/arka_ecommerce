package com.bancolombia.arka_ecommerce.infrastructure.aws.application.usecase;

import java.util.Optional;

import com.bancolombia.arka_ecommerce.infrastructure.aws.infraestructure.adapter.out.persistence.EmailSenderPort;
import com.bancolombia.arka_ecommerce.infrastructure.aws.infraestructure.adapter.out.persistence.TemplateRepository;
import com.bancolombia.arka_ecommerce.infrastructure.aws.domain.port.in.ManageTemplatesUseCase;

/**
 * Implementación del caso de uso ManageTemplatesUseCase.
 * Contiene la lógica de negocio para la gestión de plantillas,
 * utilizando el puerto de salida TemplateRepository.
 */
public class TemplateManagementService implements ManageTemplatesUseCase {

    private final TemplateRepository templateRepository;
    private final EmailSenderPort emailSenderPort; // Nuevo: Puerto para enviar correos

    /**
     * Constructor que inyecta el repositorio de plantillas.
     * @param templateRepository El puerto de salida para acceder a las plantillas.
     */
    public TemplateManagementService(TemplateRepository templateRepository, EmailSenderPort emailSenderPort) {
        this.templateRepository = templateRepository;
        this.emailSenderPort = emailSenderPort;
    }

    /**
     * Obtiene el contenido de una plantilla a través del repositorio.
     * @param templateName El nombre de la plantilla.
     * @return Un Optional con el contenido de la plantilla.
     */
    @Override
    public Optional<String> getTemplate(String templateName) {
        return templateRepository.getTemplateContent(templateName);
    }

    /**
     * Envía un correo electrónico utilizando una plantilla.
     * Orquesta la obtención de la plantilla y el envío del correo.
     * @param toEmail La dirección de correo electrónico del destinatario.
     * @param templateName El nombre de la plantilla a utilizar.
     * @param subject El asunto del correo electrónico.
     */
    @Override
    public void sendTemplatedEmail(String toEmail, String templateName, String subject) {
        Optional<String> templateContent = templateRepository.getTemplateContent(templateName);

        if (templateContent.isEmpty()) {
            // Lanza una excepción si la plantilla no se encuentra.
            // En una aplicación real, esto podría ser una excepción de negocio más específica.
            throw new RuntimeException("Plantilla no encontrada: " + templateName);
        }

        // Aquí podrías añadir lógica para reemplazar placeholders en la plantilla
        // Por ejemplo: templateContent.get().replace("{{userName}}", "Nombre del Usuario");
        // Para este ejemplo, enviamos el contenido tal cual.

        emailSenderPort.sendEmail(toEmail, subject, templateContent.get());
    }
    
}
