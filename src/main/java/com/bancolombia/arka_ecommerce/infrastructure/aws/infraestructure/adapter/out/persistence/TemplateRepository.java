package com.bancolombia.arka_ecommerce.infrastructure.aws.infraestructure.adapter.out.persistence;

import java.util.Optional;

public interface TemplateRepository {
    /**
     * Obtiene el contenido de una plantilla por su nombre.
     *
     * @param templateName El nombre de la plantilla (ej. "bienvenida.html").
     * @return Un Optional que contiene el contenido de la plantilla si se encuentra, o vacío si no.
     */
    Optional<String> getTemplateContent(String templateName);    
}
