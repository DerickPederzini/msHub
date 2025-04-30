package com.github.DerickPederzini.ms_pedido.repositories;

import com.github.DerickPederzini.ms_pedido.entities.ItemDoPedido;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface IItemDoPedidoRepository extends JpaRepository<ItemDoPedido, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM TB_ITEM_DO_PEDIDO i where i.pedido_id = :pedidoId")
    void deleteByPedidoId(Long pedidoId);

}
