package com.github.DerickPederzini.ms_pagamento.repostory;

import com.github.DerickPederzini.ms_pagamento.entities.Pagamento;
import com.github.DerickPederzini.ms_pagamento.repositories.PagamentoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;

import java.util.Optional;

@DataJpaTest
public class PagamentoRepositoryTest {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Test
    public void deleteObjectWhenIdExists(){
        Long existId = 1L;

        pagamentoRepository.deleteById(existId);

        Optional<Pagamento> pagamento = pagamentoRepository.findById(existId);
        Assertions.assertTrue(pagamento.isEmpty(), "No existe pagamento, correto");
    }
}
