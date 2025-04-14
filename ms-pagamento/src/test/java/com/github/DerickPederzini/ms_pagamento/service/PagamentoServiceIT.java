package com.github.DerickPederzini.ms_pagamento.service;

import com.github.DerickPederzini.ms_pagamento.repositories.PagamentoRepository;
import com.github.DerickPederzini.ms_pagamento.services.PagamentoService;
import com.github.DerickPederzini.ms_pagamento.services.exceptions.ResourceNotFoundException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class PagamentoServiceIT {
    @Autowired
    private PagamentoService pagamentoService;
    @Autowired
    private PagamentoRepository repository;

    private Long existingId;
    private Long nonExistingId;
    private Long countTotalPagamentos;

    @BeforeEach
    void setup() throws Exception {
        existingId = 1L;
        nonExistingId = 100L;
        countTotalPagamentos = 6L;
    }

    @Test
    public void deletePagamentoShouldDeleteResourceWhenIdExists() {
        pagamentoService.deletePagamento(existingId);
        Assertions.assertEquals(countTotalPagamentos - 1, repository.count());
    }

    @Test
    public void deletePagamentoShouldThrowExceptionWhenIdDoesNotExist(){
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> {
            pagamentoService.deletePagamento(nonExistingId);
                });
    }

    @Test
    public void getAllShouldReturnListPagamentoDTO(){
        var result = pagamentoService.getAll();
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(countTotalPagamentos, result.size());
        Assertions.assertEquals(Double.valueOf(35.55), result.get(0).valor().doubleValue());
        Assertions.assertEquals("Amadeus Mozart", result.get(0).nome());
        Assertions.assertEquals("Chiquinha Gonzaga", result.get(1).nome());
        Assertions.assertNull(result.get(5).nome());


    }


}
