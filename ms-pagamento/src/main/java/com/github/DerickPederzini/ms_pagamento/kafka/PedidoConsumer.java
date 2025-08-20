package com.github.DerickPederzini.ms_pagamento.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PedidoConsumer {

    @KafkaListener(topics = "Topico-pedidos", groupId = "grupo-ms")
    public void consumerMensagem(String mensagem) {
        System.out.println("Mensagem recebida: "+ mensagem);

    }

}
