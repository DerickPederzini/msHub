package com.github.DerickPederzini.ms_pedido.data.dtos;

import com.github.DerickPederzini.ms_pedido.entities.ItemDoPedido;
import com.github.DerickPederzini.ms_pedido.entities.Pedido;
import com.github.DerickPederzini.ms_pedido.entities.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record PedidoDTO(Long id,
                        @NotEmpty(message = "Nome reqerido")
                        @Size(min = 3, max = 100)
                        String nome,
                        @NotBlank(message = "CPF REQUERIDO")
                        @Size(max = 11, min = 11)
                        String cpf,
                        LocalDate data,
                        @Enumerated(EnumType.STRING)
                        StatusDTO status,
                        @NotEmpty(message = "Deve ter pelo menos um item do pedido")
                        List<@Valid ItemDoPedidoDTO> itens,
                        BigDecimal valorTotal

) {
    public PedidoDTO(Pedido pedido) {
        this(pedido.getId(),
                pedido.getNome(),
                pedido.getCpf(),
                pedido.getData(),
                new StatusDTO(pedido.getStatus()),
                new ArrayList<>(),
                pedido.getValorTotal()
        );
        handlePedidos(pedido.getItens());
    }

    public void handlePedidos(List<ItemDoPedido> pedidoItens){
        for(ItemDoPedido item : pedidoItens){
            ItemDoPedidoDTO dtoItem = new ItemDoPedidoDTO(item);
            this.itens.add(dtoItem);
        }
    }

}
