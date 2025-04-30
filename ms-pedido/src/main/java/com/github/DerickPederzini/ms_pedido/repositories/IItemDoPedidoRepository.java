package com.github.DerickPederzini.ms_pedido.repositories;

import com.github.DerickPederzini.ms_pedido.entities.ItemDoPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemDoPedidoRepository extends JpaRepository<ItemDoPedido, Long> {
}
