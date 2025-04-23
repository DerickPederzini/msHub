package com.github.DerickPederzini.ms_pedido.controller;

import com.github.DerickPederzini.ms_pedido.data.dtos.PedidoDTO;
import com.github.DerickPederzini.ms_pedido.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> getAllPedidos(){
        List<PedidoDTO> pedidoDTO = null;
        return ResponseEntity.ok(pedidoDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> getPedidoById(@PathVariable Long id){
        PedidoDTO pedidoDTO = null;
        return ResponseEntity.ok(pedidoDTO);
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> createPedido(@PathVariable Long id){
        PedidoDTO pedidoDTO = null;
        URI uri = null;

        return ResponseEntity.created(uri).body(pedidoDTO);
    }

}
