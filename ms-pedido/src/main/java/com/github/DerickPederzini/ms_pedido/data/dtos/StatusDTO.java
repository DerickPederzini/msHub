package com.github.DerickPederzini.ms_pedido.data.dtos;

import com.github.DerickPederzini.ms_pedido.entities.Status;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class StatusDTO {
    private Status status;
}
