package com.github.DerickPederzini.ms_pedido.repositories;

import com.github.DerickPederzini.ms_pedido.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPedidoRepository extends JpaRepository<Pedido, Long> {
}
