package com.github.DerickPederzini.ms_pagamento.data.dto;

import com.github.DerickPederzini.ms_pagamento.entities.Pagamento;
import com.github.DerickPederzini.ms_pagamento.entities.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record PagamentoDTO(Long id,
                           @NotNull(message = "Campo obrigatorio")
                           @Positive(message = "Deve ser positivo") BigDecimal valor,
                           @Size(min = 3, max = 100,
                                   message = "Nome deve ter entre 3 e 100 char") String nome,
                           @Size(min = 16, max = 19,
                                   message = "Nome deve ter entre 16 e 19 char") String numeroDoCartao,
                           @Size(min = 5, max = 5,
                                   message = "Nome deve ter 5 char") String validade,
                           @Size(min = 3, max = 3,
                                   message = "Nome deve ter 3 char") String codigoDeSeguranca,
                           @Enumerated(value = EnumType.STRING) @NotNull(message = "Valor nulo")
                           Status status,
                           @NotNull(message = "Pedido ID é obrigatório") Long pedidoId,
                           @NotNull(message = "FDI obrigatorio") Long formaDePagamentoId
) {
    public PagamentoDTO(Pagamento entity){
        this(entity.getId(),
                entity.getValor(),
                entity.getNome(),
                entity.getNumeroCartao(),
                entity.getValidade(),
                entity.getCodigoDeSeguranca(),
                entity.getStatus(),
                entity.getPedidoId(),
                entity.getFormaDePagamentoId()
        );
    }


}
