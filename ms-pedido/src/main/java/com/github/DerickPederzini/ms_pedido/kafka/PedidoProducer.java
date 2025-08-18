package com.github.DerickPederzini.ms_pedido.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PedidoProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void enviarMensagem(String message) {
        //method send
        kafkaTemplate.send("Topico-pedidos", message);
    }

}
