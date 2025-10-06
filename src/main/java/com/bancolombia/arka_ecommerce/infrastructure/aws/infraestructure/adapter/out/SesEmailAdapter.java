package com.bancolombia.arka_ecommerce.infrastructure.aws.infraestructure.adapter.out;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bancolombia.arka_ecommerce.infrastructure.aws.infraestructure.adapter.out.persistence.EmailSenderPort;

import software.amazon.awssdk.regions.Region;
/**
 * Adaptador de infraestructura que implementa el puerto EmailSenderPort
 * utilizando el SDK de AWS SES para enviar correos electrónicos.
 */
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.Body;
import software.amazon.awssdk.services.ses.model.Content;
import software.amazon.awssdk.services.ses.model.Destination;
import software.amazon.awssdk.services.ses.model.Message;
import software.amazon.awssdk.services.ses.model.SendEmailRequest;
import software.amazon.awssdk.services.ses.model.SesException;
@Component
public class SesEmailAdapter implements EmailSenderPort {

    private final SesClient sesClient;

    // El correo electrónico del remitente se inyecta desde las propiedades de la aplicación
    @Value("${aws.ses.sender-email}")
    private String senderEmail;

    /**
     * Constructor que inicializa el cliente SES.
     * La región de AWS se inyecta desde las propiedades de la aplicación.
     * @param awsRegion La región de AWS.
     */
    public SesEmailAdapter(@Value("${aws.region}") String awsRegion) {
        this.sesClient = SesClient.builder()
                .region(Region.of(awsRegion)) // Configura la región del cliente SES
                .build();
    }


    /**
     * Implementación del método para enviar un correo electrónico usando AWS SES.
     * @param to El destinatario del correo.
     * @param subject El asunto del correo.
     * @param body El cuerpo del correo (contenido HTML).
     */
    @Override
    public void sendEmail(String to, String subject, String body) {
        Destination destination = Destination.builder()
                .toAddresses(to)
                .build();

        Content subjectContent = Content.builder()
                .charset("UTF-8")
                .data(subject)
                .build();

        Content bodyContent = Content.builder()
                .charset("UTF-8")
                .data(body)
                .build();

        Message message = Message.builder()
                .subject(subjectContent)
                .body(Body.builder().html(bodyContent).build()) // Asume que el cuerpo es HTML
                .build();

        SendEmailRequest request = SendEmailRequest.builder()
                .destination(destination)
                .message(message)
                .source(senderEmail) // El correo remitente verificado en SES
                .build();

        try {
            sesClient.sendEmail(request);
            System.out.println("Correo enviado a: " + to + " con asunto: " + subject);
        } catch (SesException e) {
            System.err.println("Error de SES al enviar correo a '" + to + "': " + e.awsErrorDetails().errorMessage());
            // En un entorno de producción, considera lanzar una excepción de negocio
            // o registrar el error de forma más robusta.
            throw new RuntimeException("Fallo al enviar correo vía SES", e);
        } catch (Exception e) {
            System.err.println("Error inesperado al enviar correo a '" + to + "': " + e.getMessage());
            throw new RuntimeException("Error inesperado al enviar correo", e);
        }
    }
}
