package com.github.DerickPederzini.ms_pagamento.service;

import com.github.DerickPederzini.ms_pagamento.data.dto.PagamentoDTO;
import com.github.DerickPederzini.ms_pagamento.entities.Pagamento;
import com.github.DerickPederzini.ms_pagamento.repositories.PagamentoRepository;
import com.github.DerickPederzini.ms_pagamento.services.PagamentoService;
import com.github.DerickPederzini.ms_pagamento.services.exceptions.ResourceNotFoundException;
import com.github.DerickPederzini.ms_pagamento.test.Factory;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.matches;


@ExtendWith(SpringExtension.class)
public class PagamentoServiceTest {

    @InjectMocks
    private PagamentoService service;

    @Mock
    private PagamentoRepository repository;

    private Long existingId;
    private Long nonExistingId;

    private Pagamento pagamento;
    private PagamentoDTO pagamentoDTO;

    @BeforeEach
    void setup() throws Exception {
        existingId = 1L;
        nonExistingId = 100L;

        Mockito.when(repository.existsById(existingId)).thenReturn(true);
        Mockito.when(repository.existsById(nonExistingId)).thenReturn(false);
        Mockito.doNothing().when(repository).deleteById(existingId);

        pagamento = Factory.createPagamento();

        pagamentoDTO = new PagamentoDTO(pagamento);

        Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(pagamento));
        Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

        Mockito.when(repository.save(any())).thenReturn(pagamento);
        Mockito.when(repository.getReferenceById(existingId)).thenReturn(pagamento);
        Mockito.when(repository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists() {
        Assertions.assertDoesNotThrow(
                () -> service.deletePagamento(existingId)
        );
    }

    @Test
    public void deleteShouldThrowResouceNotFoundExceptionWhenIdNotExist() {
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> service.deletePagamento(nonExistingId)
        );
    }

    @Test
    public void findByIdShouldReturnPagamento() {
        PagamentoDTO pgDto = service.getById(existingId);
        System.out.println(pagamentoDTO.id());
        Assertions.assertNotNull(pgDto);
        Assertions.assertEquals(existingId, pgDto.id());
    }

    @Test
    public void findByIdShouldReturnEmptyOptional() {
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> service.getById(nonExistingId)
        );
    }

    @Test
    public void savePagamentoShouldReturnPagamento() {
        PagamentoDTO dto = service.createPagamento(pagamentoDTO);
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(dto.id(), existingId);
        Assertions.assertEquals(dto.valor(), pagamento.getValor());
    }

    @Test
    public void updateShouldReturnPagamentoWhenIdExists() {
        PagamentoDTO pag = service.updatePagamento(pagamentoDTO, pagamento.getId());
        Assertions.assertNotNull(pag);
        Assertions.assertEquals(pag.id(), existingId);
        Assertions.assertEquals(pag.valor(), pagamento.getValor());
    }

    @Test
    public void updateShouldThrowResourceNotFoundExceptionWhenIdNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> {
                    service.updatePagamento(pagamentoDTO, nonExistingId);
                });
    }

}
