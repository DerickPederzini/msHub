package com.github.DerickPederzini.ms_pedido.service;

import com.github.DerickPederzini.ms_pedido.repositories.IPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    IPedidoRepository pedidoRepository;
}
