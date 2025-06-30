package com.pedidos.principal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<String> crearPedido(@RequestBody Pedido pedido){
        String resultado = pedidoService.crearPedido(pedido);
        if (resultado.equals("Pedido creado correctamente")) {
            return ResponseEntity.status(201).body(resultado); // 201
        } else if (resultado.equals("El pedido ya existe")){
            return ResponseEntity.status(409).body(resultado); // 409 
        }
        return ResponseEntity.badRequest().body(resultado); // 400 
    }
    

    @Operation(summary = "Obtener pedidos por ID")
    @GetMapping("/obtenerPedido/{idPedido}")
    public ResponseEntity<Pedido>obtenerPedido(@PathVariable int idPedido){ 
        Pedido pedido = pedidoService.obtenerPedido(idPedido);
        if (pedido != null) {
            return ResponseEntity.ok(pedido); // 200 
        }
        return ResponseEntity.notFound().build(); // 404
    }


    @Operation(summary = "Listar todos los pedidos")
    @GetMapping("/listarPedidos")
    public ResponseEntity<List<PedidoEntity>> listarPedido(){
        return ResponseEntity.ok(pedidoService.listarPedido()); // 200
    }
    

    @Operation(summary = "Eliminar Pedido por ID")
    @DeleteMapping("/eliminarPedido/{idPedido}")
    public ResponseEntity<String> eliminarPedido(@PathVariable int idPedido){
        String resultado = pedidoService.eliminarPedido(idPedido);
        if (resultado.equals("Pedido eliminado correctamente")) {
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.status(404).body(resultado); // 404
    }

    @Operation(summary = "Actualizar pedido por ID")
    @PutMapping("/actualizarPedido/{idPedido}")
    public ResponseEntity<String> actualizarPedido(@RequestBody Pedido pedido){
        String resultado = pedidoService.actualizarPedido(pedido);
        if (resultado.equals("Pedido actualizado correctamente")) {
            return ResponseEntity.ok(resultado); // 200
        } else if (resultado.equals("Pedido no encontrado")){
            return ResponseEntity.status(404).body(resultado); // 404
        }
        return ResponseEntity.badRequest().body(resultado); // 400
    }

    @Operation(summary = "Obtener información del pedido desde usuarios (conexión entre microservicios)")
    @GetMapping("/infoPedido/{correo}")
    public ResponseEntity<String> infoPedido(@PathVariable String correo) {
        String pedido = pedidoService.infoPedido(correo);
        if (pedido != null) {
            return ResponseEntity.ok(pedido); // 200 OK
        }
        return ResponseEntity.notFound().build(); // 404 Not Found
    }
    

}
