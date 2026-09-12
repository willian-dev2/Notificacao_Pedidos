package com.dev.java.notificacaoPedidos.business;

import com.dev.java.notificacaoPedidos.business.dto.CompraDTO;
import com.dev.java.notificacaoPedidos.business.dto.NotificacaoCompra;
import com.dev.java.notificacaoPedidos.business.dto.ProdutoDTO;
import com.dev.java.notificacaoPedidos.infrastructure.exceptions.EmailException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Value("${envio.email.remetente}")
    public String remetente;

    @Value("${envio.email.nomeRemetente}")
    public String nomeRemetente;

    public void enviaEmail(NotificacaoCompra notificacaoCompra){
        try {
            MimeMessage mensagem = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mensagem, true, StandardCharsets.UTF_8.name());

            mimeMessageHelper.setFrom(new InternetAddress(remetente, nomeRemetente)); // remetente
            mimeMessageHelper.setTo(InternetAddress.parse(notificacaoCompra.compra().emailComprador())); // destinatario
            mimeMessageHelper.setSubject("Notificação de Compra"); // assunto do email

            // setando variaveis para usar no thymelief
            Context context = new Context();
            context.setVariable("compraId", notificacaoCompra.compra().id());
            context.setVariable("nomeProduto", notificacaoCompra.compra().nomeProduto());
            context.setVariable("categoria", notificacaoCompra.produto().getCategoria());
            context.setVariable("preco", notificacaoCompra.compra().valor());
            context.setVariable("dataCompra", notificacaoCompra.compra().dataCompra());
            context.setVariable("emailComprador", notificacaoCompra.compra().emailComprador());
            context.setVariable("linkCompra", "https://start.spring.io/");
            String template = templateEngine.process("notificacao-compra", context);
            mimeMessageHelper.setText(template, true);
            javaMailSender.send(mensagem);

        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new EmailException("Erro ao enviar o email " + e.getCause());
        }

    }



}
