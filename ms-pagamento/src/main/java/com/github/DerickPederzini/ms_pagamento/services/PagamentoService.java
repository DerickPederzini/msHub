package com.github.DerickPederzini.ms_pagamento.services;


import com.github.DerickPederzini.ms_pagamento.data.dto.PagamentoDTO;
import com.github.DerickPederzini.ms_pagamento.entities.Pagamento;
import com.github.DerickPederzini.ms_pagamento.repositories.PagamentoRepository;
import com.github.DerickPederzini.ms_pagamento.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Transactional(readOnly = true)
    public List<PagamentoDTO> getAll(){
        List<Pagamento> pagamentos = pagamentoRepository.findAll();
        return pagamentos.stream().map(PagamentoDTO::new).toList();
    }
    @Transactional(readOnly = true)
    public PagamentoDTO getById(Long id){
        Pagamento pagamento = pagamentoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso n encontrado"));
        return new PagamentoDTO(pagamento);
    }

    @Transactional(readOnly = true)
    public PagamentoDTO createPagamento(PagamentoDTO dto){
        Pagamento pagamento = copyDtoToEntity(dto, new Pagamento());
        pagamento = pagamentoRepository.save(pagamento);
        return new PagamentoDTO(pagamento);
    }

    private Pagamento copyDtoToEntity(PagamentoDTO dto, Pagamento pagamento){
        pagamento.setCodigoDeSeguranca(dto.codigoDeSeguranca());
        pagamento.setFormaDePagamentoId(dto.formaDePagamentoId());
        pagamento.setNome(dto.nome());
        pagamento.setNumeroCartao(dto.codigoDeSeguranca());
        pagamento.setValidade(dto.validade());
        pagamento.setValor(dto.valor());
        pagamento.setStatus(dto.status());
        return pagamento;
    }

}
