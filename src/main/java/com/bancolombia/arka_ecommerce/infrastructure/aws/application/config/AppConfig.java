package com.bancolombia.arka_ecommerce.infrastructure.aws.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bancolombia.arka_ecommerce.infrastructure.aws.application.usecase.TemplateManagementService;
import com.bancolombia.arka_ecommerce.infrastructure.aws.domain.port.in.ManageTemplatesUseCase;
import com.bancolombia.arka_ecommerce.infrastructure.aws.infraestructure.adapter.out.persistence.EmailSenderPort;
import com.bancolombia.arka_ecommerce.infrastructure.aws.infraestructure.adapter.out.persistence.TemplateRepository;

/**
 * Clase de configuración de Spring para la aplicación.
 * Define cómo se instancian y conectan los componentes de la arquitectura hexagonal.
 */
@Configuration
public class AppConfig {

    /**
     * Define el bean para el caso de uso ManageTemplatesUseCase.
     * Spring inyectará automáticamente las implementaciones de TemplateRepository (S3TemplateAdapter)
     * y EmailSenderPort (SesEmailAdapter) ya que están marcadas con @Component.
     * @param templateRepository El adaptador de infraestructura para el repositorio de plantillas.
     * @param emailSenderPort El adaptador de infraestructura para el envío de correos.
     * @return Una instancia de TemplateManagementService que implementa el caso de uso.
     */
    @Bean
    public ManageTemplatesUseCase manageTemplatesUseCase(TemplateRepository templateRepository, EmailSenderPort emailSenderPort) {
        return new TemplateManagementService(templateRepository, emailSenderPort);
    }
}
