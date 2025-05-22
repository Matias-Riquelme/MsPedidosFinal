package com.pedidos.principal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pedidos.principal.Model.Pedido;
import com.pedidos.principal.Service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;
    @Operation(summary = "Este endpoint permite agregar pedidos")
    @PostMapping("/crearPedido")
    // ResponseEntity <-- responder segun accion o resultado

    public ResponseEntity<String> obtenerPedido(@RequestBody Pedido pedido){
        return ResponseEntity.ok(pedidoService.crearPedido(pedido));
    }

    @GetMapping("/obtenerPedido/{idPedido}")
    public ResponseEntity<Pedido>obtenerPedido(@PathVariable int idPedido){ 
        Pedido pedido = pedidoService.obtenerPedido(idPedido);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        }
        return ResponseEntity.notFound().build();
    }
    

}
