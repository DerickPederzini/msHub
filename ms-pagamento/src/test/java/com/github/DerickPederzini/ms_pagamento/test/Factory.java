package com.github.DerickPederzini.ms_pagamento.test;

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

    public static List<Pagamento> createPagamentos(){
        Pagamento pagamento1 = new Pagamento(1L, BigDecimal.valueOf(32.25), "Jon Snow", "2365412478964521",
                "07/32", "585", Status.CRIADO, 1L, 2L);
        Pagamento pagamento2 = new Pagamento(1L, BigDecimal.valueOf(32.25), "Jon Snow", "2365412478964521",
                "07/32", "585", Status.CRIADO, 1L, 2L);
        Pagamento pagamento3 = new Pagamento(1L, BigDecimal.valueOf(32.25), "Jon Snow", "2365412478964521",
                "07/32", "585", Status.CRIADO, 1L, 2L);

        List<Pagamento> pagamentos = new ArrayList<>();
        pagamentos.add(pagamento1);
        pagamentos.add(pagamento2);
        pagamentos.add(pagamento3);

        return pagamentos;

    }

}
