package com.github.DerickPederzini.ms_pagamento.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "tb_pagamento")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private BigDecimal valor;
    private String nome;
    private String numeroCartao;
    private String validade; // MM/AA
    private String codigoDeSeguranca;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @Column(name = "pedido_id", nullable = false)
    private Long pedidoId;
    @Column(nullable = false)
    private Long formaDePagamentoId;

    public Pagamento() {
    }

    public Pagamento(Long id, BigDecimal valor, String nome, String numeroCartao, String validade, String codigoDeSeguranca, Status status, Long pedidoId, Long formaDePagamentoId) {
        this.id = id;
        this.valor = valor;
        this.nome = nome;
        this.numeroCartao = numeroCartao;
        this.validade = validade;
        this.codigoDeSeguranca = codigoDeSeguranca;
        this.status = status;
        this.pedidoId = pedidoId;
        this.formaDePagamentoId = formaDePagamentoId;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getNome() {
        return nome;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public String getValidade() {
        return validade;
    }

    public String getCodigoDeSeguranca() {
        return codigoDeSeguranca;
    }

    public Status getStatus() {
        return status;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public Long getFormaDePagamentoId() {
        return formaDePagamentoId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public void setCodigoDeSeguranca(String codigoDeSeguranca) {
        this.codigoDeSeguranca = codigoDeSeguranca;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public void setFormaDePagamentoId(Long formaDePagamentoId) {
        this.formaDePagamentoId = formaDePagamentoId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(id, pagamento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
