package com.github.DerickPederzini.ms_pagamento.repositories;

import com.github.DerickPederzini.ms_pagamento.entities.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
