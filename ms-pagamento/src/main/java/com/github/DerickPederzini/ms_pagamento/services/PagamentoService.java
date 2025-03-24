package com.github.DerickPederzini.ms_pagamento.services;


import com.github.DerickPederzini.ms_pagamento.data.dto.PagamentoDTO;
import com.github.DerickPederzini.ms_pagamento.entities.Pagamento;
import com.github.DerickPederzini.ms_pagamento.repositories.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
                () -> new ResourcesNotFoundException("Recurso n encontrado"));
        return new PagamentoDTO(pagamento);
    }


}
