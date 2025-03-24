package com.github.DerickPederzini.ms_pagamento.controllers;

import com.github.DerickPederzini.ms_pagamento.data.dto.PagamentoDTO;
import com.github.DerickPederzini.ms_pagamento.services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    public ResponseEntity<List<PagamentoDTO>> getAll(){
        List<PagamentoDTO> pagamentoDTOS = pagamentoService.getAll();
        return ResponseEntity.ok(pagamentoDTOS);
    }



}
