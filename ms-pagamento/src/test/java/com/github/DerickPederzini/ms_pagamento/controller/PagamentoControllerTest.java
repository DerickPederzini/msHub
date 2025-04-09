package com.github.DerickPederzini.ms_pagamento.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.DerickPederzini.ms_pagamento.data.dto.PagamentoDTO;
import com.github.DerickPederzini.ms_pagamento.services.PagamentoService;
import com.github.DerickPederzini.ms_pagamento.services.exceptions.ResourceNotFoundException;
import com.github.DerickPederzini.ms_pagamento.test.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class PagamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PagamentoService service;

    private Long existingId;
    private Long nonExistingId;
    private PagamentoDTO dto;

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
        Mockito.when(service.getById(nonExistingId)).thenThrow(ResourceNotFoundException.class);

        //create
        Mockito.when(service.createPagamento(any())).thenReturn(dto);

        Mockito.when(service.updatePagamento(any(), eq(existingId)))
                .thenReturn(dto);

        Mockito.when(service.updatePagamento(any(), eq(nonExistingId)))
                .thenThrow(ResourceNotFoundException.class);

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

    @Test
    public void updatePagaemntoShouldReturnPagamentoDTOWhenIdExists()throws Exception{
        String jsonRequestBody = _mapper.writeValueAsString(dto);

        mockMvc.perform(put("/pagamentos/{id}", existingId)
                .content(jsonRequestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(existingId))
                .andExpect(jsonPath("$.valor").exists())
                .andExpect(jsonPath("$.valor").value(32.25))
        ;

    }

    @Test
    public void updatePagaemntoShouldThrowResourceNotFoundExceptionWhenIdNotExist()throws Exception{
        String jsonRequestBody = _mapper.writeValueAsString(dto);

        mockMvc.perform(put("/pagamentos/{id}", nonExistingId)
                        .content(jsonRequestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
        ;

    }


}
