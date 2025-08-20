package com.github.DerickPederzini.ms_pagamento.controllers;

import com.github.DerickPederzini.ms_pagamento.data.dto.PagamentoDTO;
import com.github.DerickPederzini.ms_pagamento.kafka.PagamentoPendenteProducer;
import com.github.DerickPederzini.ms_pagamento.services.PagamentoService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoPendenteProducer pagamentoPendenteProducer;
    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping("/port")
    public String getPort(@Value("${local.server.port}") String porta){
        return String.format("Request na instância recebida na porta "+ porta);
    }

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

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDTO> updatePagamento(@PathVariable Long id, @Valid @RequestBody PagamentoDTO pagamentoDTO){
        pagamentoDTO = pagamentoService.updatePagamento(pagamentoDTO, id);
        return ResponseEntity.ok(pagamentoDTO);
    }

    @PatchMapping("/{id}/confirmar")
    @CircuitBreaker(name = "atualizarPedido", fallbackMethod = "confirmacaoPagamentoPendente")
    public void confirmarPagamentoDoPedido(@PathVariable @NotNull Long id){
        pagamentoService.confirmaPagamentoDoPedido(id);
    }

    public void confirmacaoPagamentoPendente(Long id, Exception e){
        pagamentoService.alterarStatusDoPagamento(id);
        pagamentoPendenteProducer.enviarPagamentoPendente(id.toString());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePagamento(@PathVariable Long id){
            pagamentoService.deletePagamento(id);
            return ResponseEntity.noContent().build();
    }



}
