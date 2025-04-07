package com.github.DerickPederzini.ms_pagamento.test;

import com.github.DerickPederzini.ms_pagamento.data.dto.PagamentoDTO;
import com.github.DerickPederzini.ms_pagamento.entities.Pagamento;
import com.github.DerickPederzini.ms_pagamento.entities.Status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Factory {
    public static Pagamento createPagamento(){
        return new Pagamento(1L, BigDecimal.valueOf(32.25), "Jon Snow", "2365412478964521",
        "07/32", "585", Status.CRIADO, 1L, 2L);
    }

    public static PagamentoDTO createPagamentoDTO(){
        Pagamento pagamento = new Pagamento();
        return new PagamentoDTO(pagamento);
    }




}
