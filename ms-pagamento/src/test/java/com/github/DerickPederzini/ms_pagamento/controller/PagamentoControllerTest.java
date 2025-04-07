package com.github.DerickPederzini.ms_pagamento.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.DerickPederzini.ms_pagamento.data.dto.PagamentoDTO;
import com.github.DerickPederzini.ms_pagamento.services.PagamentoService;
import com.github.DerickPederzini.ms_pagamento.test.Factory;
import jakarta.persistence.EntityNotFoundException;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class PagamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PagamentoService service;

    private PagamentoDTO dto;
    private Long existingId;
    private Long nonExistingId;

    @Autowired
    private ObjectMapper _mapper;


    @BeforeEach
    void setup() throws Exception{
        existingId = 1L;
        nonExistingId = 100L;
        dto = Factory.createPagamentoDTO();
        List<PagamentoDTO> list = List.of(dto);

        Mockito.when(service.getAll()).thenReturn(list);
        //fim simulacao getAll

        //simulando getById
        Mockito.when(service.getById(existingId)).thenReturn(dto);
        Mockito.when(service.getById(nonExistingId)).thenThrow(EntityNotFoundException.class);

        //create
        Mockito.when(service.createPagamento(any())).thenReturn(dto);


    }

    @Test
    public void getAllShouldReturnListPagamentoDTO() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/pagamentos").accept(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void getByIdShouldReturnDtoWhenIdExists() throws Exception{
        ResultActions actions = mockMvc.perform(get("/pagamentos/{id}", existingId).accept(MediaType.APPLICATION_JSON));


        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.id").exists());
        actions.andExpect(jsonPath("$.valor").exists());
        actions.andExpect(jsonPath("$.status").exists());

    }

    @Test
    public void getByIdShouldThrowExceptionWhenNonExistngId() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/pagamentos/{id}", nonExistingId).accept(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isNotFound());
    }

    @Test
    public void createPagamentoShouldReturnReturnDtoCreated() throws Exception{
        PagamentoDTO pagamentoDTO = Factory.createNewPagamentoDTO();
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
                .andExpect(jsonPath("$.status").exists())
                .andExpect(jsonPath("$.pedidoId").exists())
                .andExpect(jsonPath("$.formaDePagamentoId").exists());

    }


}
