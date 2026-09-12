package com.dev.java.notificacaoPedidos.business.dto;

import com.dev.java.notificacaoPedidos.business.enums.Categoria;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoDTO {

    private Categoria categoria;

}
