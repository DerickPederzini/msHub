package com.github.DerickPederzini.ms_pedido.controller;

import com.github.DerickPederzini.ms_pedido.data.dtos.PedidoDTO;
import com.github.DerickPederzini.ms_pedido.data.dtos.StatusDTO;
import com.github.DerickPederzini.ms_pedido.kafka.PedidoProducer;
import com.github.DerickPederzini.ms_pedido.service.PedidoService;
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
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoProducer producer;


    @GetMapping("/port")
    public String getPort(@Value("${local.server.port}") String porta){
        return String.format("Request na instância recebida na porta "+ porta);
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> getAllPedidos(){
        List<PedidoDTO> pedidosDTO = pedidoService.getAll();
        return ResponseEntity.ok(pedidosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> getPedidoById(@PathVariable Long id){
        PedidoDTO pedidoDTO = pedidoService.getPedidoById(id);
        return ResponseEntity.ok(pedidoDTO);
    }

    @PostMapping("/enviar")
    public ResponseEntity<String> enviarMensagem(@RequestParam String mensagem) {
        producer.enviarMensagem(mensagem);
        return ResponseEntity.ok("Mensagem enviada para o Kafka: "+ mensagem);
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> createPedido(@RequestBody PedidoDTO pedidoDTO){
        pedidoDTO = pedidoService.createPedido(pedidoDTO);
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(pedidoDTO.id())
                .toUri();
        return ResponseEntity.created(uri).body(pedidoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> updatePedido(@PathVariable Long id,
                                                  @Valid @RequestBody PedidoDTO dto){
        dto = pedidoService.updatePedido(id, dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/pago")
    public ResponseEntity<String> atualizarPagamentoDoPedido(@PathVariable @NotNull Long id){
        pedidoService.aprovarPagamentoDoPedido(id);
        String message = "Pedido atualizado para pago";
        return ResponseEntity.ok().body(message);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PedidoDTO> updatePedidoStatus(@PathVariable Long id,
                                                  @Valid @RequestBody StatusDTO dto){
        PedidoDTO pedidoDTO = pedidoService.updatePedidoStatus(id, dto);
        return ResponseEntity.ok(pedidoDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedidoById(@PathVariable Long id){
        pedidoService.deletePedido(id);
        return ResponseEntity.noContent().build();
    }

}
