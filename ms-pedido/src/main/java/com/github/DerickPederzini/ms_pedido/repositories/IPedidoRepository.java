package com.github.DerickPederzini.ms_pedido.repositories;

import com.github.DerickPederzini.ms_pedido.entities.Pedido;
import com.github.DerickPederzini.ms_pedido.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface IPedidoRepository extends JpaRepository<Pedido, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Pedido p SET p.status =:status WHERE p = :pedido")
    void updatePedido(Status status, Pedido pedido);

    @Query("SELECT p FROM Pedido p LEFT JOIN FETCH p.itens WHERE p.id = :id")
    Pedido getPedidoByIdWithItens(Long id);


}
