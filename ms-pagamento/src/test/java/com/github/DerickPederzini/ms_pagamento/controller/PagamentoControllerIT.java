package com.github.DerickPederzini.ms_pagamento.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.DerickPederzini.ms_pagamento.data.dto.PagamentoDTO;
import com.github.DerickPederzini.ms_pagamento.test.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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

    @Test
    public void getAllShouldReturnListAllPagamentos() throws Exception{
        mockMvc.perform(get("/pagamentos").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("[0].id").value(1))
                .andExpect(jsonPath("[0].nome").isString())
                .andExpect(jsonPath("[0].nome").value("Amadeus Mozart"))
                .andExpect(jsonPath("[5].status").value("CONFIRMADO"));
    }

    @Test
    public void getByIdShouldReturnPagamentoWhenIdExistis() throws Exception {
        mockMvc.perform(get("/pagamentos/{id}", existingId).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("nome").isString())
                .andExpect(jsonPath("nome").value("Amadeus Mozart"))
                .andExpect(jsonPath("status").value("CRIADO"));
    }


    @Test
    public void getByIdShouldThrowExceptionWhenIdDoesntExists() throws Exception {
        mockMvc.perform(get("/pagamentos/{id}", nonExistingId).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void createShouldReturnPagamentoDTO() throws Exception{
        pagamentoDTO = Factory.createNewPagamentoDTO();
        String json = _mapper.writeValueAsString(pagamentoDTO);
        
        mockMvc.perform(post("/pagamentos")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.valor").exists())
                .andExpect(jsonPath("$.status").value("CRIADO"));
    }
    @Test
    public void createShouldPersistPagamentoWithRequiredFields() throws Exception{
        pagamentoDTO = Factory.createWithRequiredFields();
        String json = _mapper.writeValueAsString(pagamentoDTO);

        mockMvc.perform(post("/pagamentos")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.valor").exists())
                .andExpect(jsonPath("$.status").value("CRIADO"));
    }


}
