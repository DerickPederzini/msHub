package com.github.DerickPederzini.ms_pagamento.services;

public class ResourcesNotFoundException extends RuntimeException{

    public ResourcesNotFoundException(String message) {
        super(message);
    }
    
}
