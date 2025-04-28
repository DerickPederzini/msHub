package com.github.DerickPederzini.ms_pedido.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Entity
@Table(name = "tb_pedido")
@EqualsAndHashCode(of = "id")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private LocalDate data;
    @Enumerated
    private Status status;

    public Pedido() {
    }

    public Pedido(Long id, String nome, String cpf, LocalDate data, Status status) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.data = data;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
