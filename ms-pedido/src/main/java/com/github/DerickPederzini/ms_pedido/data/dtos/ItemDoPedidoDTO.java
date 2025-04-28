package com.github.DerickPederzini.ms_pedido.data.dtos;

import com.github.DerickPederzini.ms_pedido.entities.ItemDoPedido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ItemDoPedidoDTO(Long id,
                              @NotNull(message = "Quantidade requerida")
                              Integer quantidade,
                              @NotBlank(message = "Desc requerida")
                              String descricao,
                              @NotNull(message = "valor nulo")
                              @Positive(message = "Valor positivo")
                              BigDecimal valorUnitario
) {
    public ItemDoPedidoDTO(ItemDoPedido itemDoPedido) {
        this(itemDoPedido.getId(),
                itemDoPedido.getQuantidade(),
                itemDoPedido.getDescricao(),
                itemDoPedido.getValorUnitario());
    }
}
