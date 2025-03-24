package com.github.DerickPederzini.ms_pagamento.controllers;

import com.github.DerickPederzini.ms_pagamento.data.dto.PagamentoDTO;
import com.github.DerickPederzini.ms_pagamento.services.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping
    public ResponseEntity<List<PagamentoDTO>> getAll(){
        List<PagamentoDTO> pagamentoDTOS = pagamentoService.getAll();
        return ResponseEntity.ok(pagamentoDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> getById(@PathVariable Long id){
        PagamentoDTO pagamentoDTO = pagamentoService.getById(id);
        return ResponseEntity.ok(pagamentoDTO);
    }

    @PostMapping
    public ResponseEntity<PagamentoDTO> createPagamento(@RequestBody @Valid PagamentoDTO pagamentoDTO){
        pagamentoDTO = pagamentoService.createPagamento(pagamentoDTO);

        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(pagamentoDTO.id())
                .toUri();
        return ResponseEntity.created(uri).body(pagamentoDTO);
    }

}
