package com.github.DerickPederzini.ms_pagamento.kafka;

import com.github.DerickPederzini.ms_pagamento.services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PagamentoConfirmadoConsumer {

    @Autowired
    private PagamentoService pagamentoService;

    @KafkaListener(topics = "pagamento-confirmado", groupId = "ms-pagamento")
    public void consumirConfirmacao(String pagamentoId) {
        Long id = Long.parseLong(pagamentoId);
        pagamentoService.confirmaPagamentoKafka(id);
    }
}
