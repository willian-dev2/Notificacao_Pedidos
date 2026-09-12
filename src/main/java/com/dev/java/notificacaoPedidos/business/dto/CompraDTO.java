package com.dev.java.notificacaoPedidos.business.dto;

import java.time.LocalDateTime;

public record CompraDTO(

        Long id,
        Long produtoId,
        Double valor,
        String nomeProduto,
        String emailComprador,
        LocalDateTime dataCompra

) {
}
