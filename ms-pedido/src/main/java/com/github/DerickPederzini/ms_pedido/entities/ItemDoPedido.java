package com.github.DerickPederzini.ms_pedido.entities;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_item_do_pedido")
public class ItemDoPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantidade;
    private String descricao;
    private BigDecimal valorUnitario;

    public ItemDoPedido() {
    }

    public ItemDoPedido(Long id, Integer quantidade, String descricao, BigDecimal valorUnitario) {
        this.id = id;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}
