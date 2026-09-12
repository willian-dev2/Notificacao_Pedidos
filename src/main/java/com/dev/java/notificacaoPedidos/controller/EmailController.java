package com.dev.java.notificacaoPedidos.controller;

import com.dev.java.notificacaoPedidos.business.EmailService;
import com.dev.java.notificacaoPedidos.business.dto.CompraDTO;
import com.dev.java.notificacaoPedidos.business.dto.NotificacaoCompra;
import com.dev.java.notificacaoPedidos.business.dto.ProdutoDTO;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<Void> enviarEmail(@RequestBody NotificacaoCompra notificacaoCompra) {
        emailService.enviaEmail(notificacaoCompra);
        return ResponseEntity.ok().build();
    }

}
