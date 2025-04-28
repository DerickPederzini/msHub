package com.github.DerickPederzini.ms_pedido.service;

import com.github.DerickPederzini.ms_pedido.data.dtos.PedidoDTO;
import com.github.DerickPederzini.ms_pedido.entities.Pedido;
import com.github.DerickPederzini.ms_pedido.repositories.IPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private IPedidoRepository pedidoRepository;

    @Transactional(readOnly = true)
    public PedidoDTO getAll(){
        List<Pedido> pedido = pedidoRepository.findAll();
        return pedido.stream().map(PedidoDTO::new).toList();
    }


}
