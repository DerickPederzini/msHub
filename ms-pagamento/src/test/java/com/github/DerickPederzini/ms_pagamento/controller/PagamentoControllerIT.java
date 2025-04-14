package com.github.DerickPederzini.ms_pagamento.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.DerickPederzini.ms_pagamento.data.dto.PagamentoDTO;
import com.github.DerickPederzini.ms_pagamento.test.Factory;
import jakarta.transaction.Transactional;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PagamentoControllerIT {

    @Autowired
    private MockMvc mockMvc;

    private Long existingId;
    private Long nonExistingId;
    private PagamentoDTO pagamentoDTO;

    @Autowired
    private ObjectMapper _mapper;

    @BeforeEach
    public void setup() throws Exception{
        existingId = 1L;
        nonExistingId = 100L;
        pagamentoDTO = Factory.createPagamentoDTO();
    }


}
