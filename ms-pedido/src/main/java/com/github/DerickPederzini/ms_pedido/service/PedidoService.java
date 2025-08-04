package com.github.DerickPederzini.ms_pedido.service;

import com.github.DerickPederzini.ms_pedido.data.dtos.ItemDoPedidoDTO;
import com.github.DerickPederzini.ms_pedido.data.dtos.PedidoDTO;
import com.github.DerickPederzini.ms_pedido.entities.ItemDoPedido;
import com.github.DerickPederzini.ms_pedido.entities.Pedido;
import com.github.DerickPederzini.ms_pedido.entities.Status;
import com.github.DerickPederzini.ms_pedido.repositories.IItemDoPedidoRepository;
import com.github.DerickPederzini.ms_pedido.repositories.IPedidoRepository;
import com.github.DerickPederzini.ms_pedido.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private IPedidoRepository pedidoRepository;

    @Autowired
    private IItemDoPedidoRepository itemDoPedidoRepository;

    @Transactional(readOnly = true)
    public List<PedidoDTO> getAll(){
        List<Pedido> pedido = pedidoRepository.findAll();
        return pedido.stream().map(PedidoDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public PedidoDTO getPedidoById(Long id){
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não encontrado"));
        return new PedidoDTO(pedido);
    }

    @Transactional
    public PedidoDTO createPedido(PedidoDTO pedidoDTO){
        Pedido pedido = new Pedido();
        pedido.setData(LocalDate.now());
        pedido.setStatus(Status.REALIZADO);
        toEntity(pedidoDTO, pedido);
        pedido.calcularTotalPedido();
        pedido = pedidoRepository.save(pedido);
        itemDoPedidoRepository.saveAll(pedido.getItens());
        return new PedidoDTO(pedido);
    }

    @Transactional
    public PedidoDTO updatePedido(Long id, PedidoDTO pedidoDTO){
        try {
            Pedido pedido = pedidoRepository.getReferenceById(id);
            pedido.setStatus(Status.REALIZADO);
            pedido.setData(LocalDate.now());
            itemDoPedidoRepository.deleteByPedidoId(id);
            toEntity(pedidoDTO, pedido);
            pedido = pedidoRepository.save(pedido);
            itemDoPedidoRepository.saveAll(pedido.getItens());
            return new PedidoDTO(pedido);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado com id "+ id);
        }
    }

    public void deletePedido(Long id){
        if (!pedidoRepository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado "+ id);
        }
        pedidoRepository.deleteById(id);
    }


    public void toEntity(PedidoDTO pedidoDTO, Pedido pedido){
        pedido.setNome(pedidoDTO.nome());
        pedido.setCpf(pedidoDTO.cpf());

        List<ItemDoPedido> itens = new ArrayList<>();

        for(ItemDoPedidoDTO itemDoPedidoDTO : pedidoDTO.itens()){
            ItemDoPedido item = new ItemDoPedido();
            item.setQuantidade(itemDoPedidoDTO.quantidade());
            item.setDescricao(itemDoPedidoDTO.descricao());
            item.setValorUnitario(itemDoPedidoDTO.valorUnitario());
            item.setPedido(pedido);
            itens.add(item);
        }
        pedido.setItens(itens);
    }

}
