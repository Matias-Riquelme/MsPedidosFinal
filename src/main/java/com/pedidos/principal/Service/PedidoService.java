package com.pedidos.principal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.pedidos.principal.Model.Pedido;
import com.pedidos.principal.Model.Entity.PedidoEntity;
import com.pedidos.principal.Repository.PedidoRepository;

@Service

public class PedidoService {
    @Autowired
    private PedidoRepository pedidorepository;
    
    @Autowired
    private RestTemplate restTemplate;


    public String infoPedido(String correo){
        String url = "http://localhost:8082/usuarios/" + correo;
        String data = restTemplate.getForObject(url, String.class);

        return "{\"PEDIDO\":" + data + "}";
    }

    public String crearPedido(Pedido pedido){
        
        try {
            Boolean estado = pedidorepository.existsByIdPedido(pedido.getIdPedido());
            if (!estado) {
                PedidoEntity pedidoNuevo = new PedidoEntity();
                pedidoNuevo.setTipoEnvio(pedido.getTipoEnvio());
                pedidorepository.save(pedidoNuevo);
                return "Pedido creado correctamente";
            }
            return "El pedido ya existe";

        } catch (Exception e) {
    e.printStackTrace();
    return "Error al crear el pedido: " + e.getMessage();
}
    }

    public Pedido obtenerPedido(int idPedido){
        try {
            PedidoEntity pedido = pedidorepository.findByIdPedido(idPedido);
            if (pedido != null) {
                Pedido ped = new Pedido(
                    pedido.getIdPedido(),
                    pedido.getTipoEnvio(),
                    pedido.getId()
                );
                return ped;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }


    public List<PedidoEntity> listarPedido(){
        return pedidorepository.findAll();
    }


    public String eliminarPedido(int idPedido) {
        try {
            if (pedidorepository.existsById(idPedido)) {
                pedidorepository.deleteById(idPedido);
                return "Pedido borrado correctamente";
            }
            return "Pedido no encontrado";
        } catch (Exception e) {
            return "Error al borrar pedido: " + e.getMessage();
        }
    }

    public String actualizarPedido(Pedido pedido){
        try {
            PedidoEntity pedidoActualizado = pedidorepository.findByIdPedido(pedido.getIdPedido());
            if (pedidoActualizado != null) {
                pedidoActualizado.setTipoEnvio(pedido.getTipoEnvio());
                pedidorepository.save(pedidoActualizado);
                return "Pedido actualizado correctamente";
            } else {
                return "Pedido no encontrado";
            }
        } catch (Exception e) {
            return "Error al actualizar el pedido";
        }
    }


}
