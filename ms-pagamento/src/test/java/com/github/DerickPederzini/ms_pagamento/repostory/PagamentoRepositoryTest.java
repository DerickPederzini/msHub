package com.github.DerickPederzini.ms_pagamento.repostory;

import com.github.DerickPederzini.ms_pagamento.entities.Pagamento;
import com.github.DerickPederzini.ms_pagamento.repositories.PagamentoRepository;
import com.github.DerickPederzini.ms_pagamento.test.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class PagamentoRepositoryTest {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    private Long existingId;
    private Long nonExistingId;
    private Long countTotalPagamento;

    @BeforeEach
    void setup() throws Exception{
        existingId = 1L;
        nonExistingId = 100L;
        countTotalPagamento = 3L;
    }

    @Test
    public void deleteObjectWhenIdExists(){
        pagamentoRepository.deleteById(existingId);

        Optional<Pagamento> pagamento = pagamentoRepository.findById(existingId);
        Assertions.assertTrue(pagamento.isEmpty(), "No existe pagamento, correto");
    }

    @Test
    public void paramAndIdInstanciamPagamento(){
        Pagamento pagamento = Factory.createPagamento();
        pagamento.setId(null);
        pagamento = pagamentoRepository.save(pagamento);
        Assertions.assertNotNull(pagamento.getId());
        Assertions.assertEquals(countTotalPagamento + 1, pagamento.getId());
    }

    @Test
    public void encontraPagamentoPorId(){
        Optional<Pagamento> pagamentoId = pagamentoRepository.findById(existingId);
        Assertions.assertTrue(pagamentoId.isPresent());
    }

    @Test
    public void naoEncontraPagamentoPorId(){
        Optional<Pagamento> pagamentoId = pagamentoRepository.findById(nonExistingId);
        Assertions.assertNotNull(pagamentoId);
        Assertions.assertFalse(pagamentoId.isPresent());
    }



}
