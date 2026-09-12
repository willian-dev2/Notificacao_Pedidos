package com.dev.java.notificacaoPedidos.business.dto;

public record NotificacaoCompra(

        CompraDTO compra,
        ProdutoDTO produto
) {
}
