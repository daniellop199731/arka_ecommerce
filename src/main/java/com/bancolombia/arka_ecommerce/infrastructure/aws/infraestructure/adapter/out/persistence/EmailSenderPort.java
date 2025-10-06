package com.bancolombia.arka_ecommerce.infrastructure.aws.infraestructure.adapter.out.persistence;

/**
 * Puerto de salida que define la interfaz para el envío de correos electrónicos.
 * El dominio interactúa con esta interfaz sin conocer la tecnología subyacente (ej. AWS SES).
 */
public interface EmailSenderPort {
    /**
     * Envía un correo electrónico.
     *
     * @param to El destinatario del correo.
     * @param subject El asunto del correo.
     * @param body El cuerpo del correo (puede ser HTML).
     */
    void sendEmail(String to, String subject, String body);
}
