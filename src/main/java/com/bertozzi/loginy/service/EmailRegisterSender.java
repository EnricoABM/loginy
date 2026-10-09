package com.bertozzi.loginy.service;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service 
@Slf4j
public class EmailRegisterSender {
    @Value("${spring.mail.username}")
    private String origin;

    private final JavaMailSender javaMailSender;

    public EmailRegisterSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void send(String email, String code) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(
                    message,
                    true,
                    StandardCharsets.UTF_8.name());

            String html = """
                    <html>
                        <body>
                            <h1>Loginy</h1>
                            <p>Convite para finalização de cadastro na plataforma.</p>
                            <h2><a href="http://localhost:8080/users/register/confirm?email=%s&code=%s">Finalizar Cadastro</a></h2>
                            <p>
                                <strong>Lembre-se, o código é válido por 15 minutos</strong>
                            </p>
                        </body>
                    </html>
                    """.formatted(email, code);

            helper.setFrom(origin);
            helper.setTo(email);
            helper.setSubject("Loginy - Convite de Verificação");
            helper.setText(html, true);

            javaMailSender.send(message);
        } catch (Exception e) {
            log.error("Erro ao enviar e-mail de convite: {}", e.getMessage());
        }
    }
}
