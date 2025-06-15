package com.pedidos.principal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pedidos.principal.Model.Pedido;
import com.pedidos.principal.Model.Entity.PedidoEntity;
import com.pedidos.principal.Service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;
    @Operation(summary = "Agregar un pedido")
    @PostMapping("/crearPedido")
    // ResponseEntity <-- responder segun accion o resultado

    public ResponseEntity<String> obtenerPedido(@RequestBody Pedido pedido){
        return ResponseEntity.ok(pedidoService.crearPedido(pedido));
    }

    @Operation(summary = "Obtener pedidos por ID")
    @GetMapping("/obtenerPedido/{idPedido}")
    public ResponseEntity<Pedido>obtenerPedido(@PathVariable int idPedido){ 
        Pedido pedido = pedidoService.obtenerPedido(idPedido);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Listar todos los pedidos")
    @GetMapping("/listarPedidos")
    public ResponseEntity<List<PedidoEntity>> listarPedido(){
        return ResponseEntity.ok(pedidoService.listarPedido());
    }
    

    @Operation(summary = "Eliminar Pedido por ID")
    @DeleteMapping("/eliminarPedido/{idPedido}")
    public ResponseEntity<String> eliminarPedido(@PathVariable int idPedido){
        return ResponseEntity.ok(pedidoService.eliminarPedido(idPedido));
    }

    @Operation(summary = "Actualizar pedido por ID")
    @DeleteMapping("/actualizarPedido/{idPedido}")
    public ResponseEntity<String> actualizarPedido(@RequestBody Pedido pedido){
        return ResponseEntity.ok(pedidoService.actualizarPedido(pedido));
    }

}
