package com.github.DerickPederzini.ms_pedido.service;

import com.github.DerickPederzini.ms_pedido.data.dtos.PedidoDTO;
import com.github.DerickPederzini.ms_pedido.entities.Pedido;
import com.github.DerickPederzini.ms_pedido.repositories.IPedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private IPedidoRepository pedidoRepository;

    @Transactional(readOnly = true)
    public List<PedidoDTO> getAll(){
        List<Pedido> pedido = pedidoRepository.findAll();
        return pedido.stream().map(PedidoDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public PedidoDTO getPedidoById(Long id){
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não encontrado"));
        return new PedidoDTO(pedido);
    }

    @Transactional
    public PedidoDTO createPedido(PedidoDTO pedidoDTO){
        Pedido pedido = new Pedido();
        toEntity(pedidoDTO, pedido);
        pedido = pedidoRepository.save(pedido);
        return new PedidoDTO(pedido);
    }

    public void toEntity(PedidoDTO pedidoDTO, Pedido pedido){
        pedido.setNome(pedidoDTO.nome());
        pedido.setCpf(pedidoDTO.cpf());
        pedido.setData(pedidoDTO.data());
        pedido.setStatus(pedidoDTO.status());
    }

}
