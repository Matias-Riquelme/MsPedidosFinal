package com.pedidos.principal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedidos.principal.Model.Pedido;
import com.pedidos.principal.Model.Entity.PedidoEntity;
import com.pedidos.principal.Repository.PedidoRepository;

@Service

public class PedidoService {
    @Autowired
    private PedidoRepository pedidorepository;

    public String crearPedido(Pedido user){
        
        try {
            Boolean estado = pedidorepository.existsByIdPedido(user.getIdPedido());
            if (!estado) {
                PedidoEntity pedidoNuevo = new PedidoEntity();
                pedidoNuevo.setIdPedido(user.getIdPedido());
                pedidoNuevo.setTipoEnvio(user.getTipoEnvio());
                pedidoNuevo.setId(user.getId());
                pedidoNuevo.setIdPerfume(user.getIdPerfume());
                pedidorepository.save(pedidoNuevo);
                return "Pedido creado correctamente";
            }
            return "El correo ya existe";

        } catch (Exception e) {
    e.printStackTrace();
    return "Error al crear el pedido: " + e.getMessage();
}


    }

    public Pedido obtenerPedido(int idPedido){
        try{
            PedidoEntity pedido = pedidorepository.findByIdPedido(idPedido);
            if(pedido != null){
                Pedido user = new Pedido(
                    pedido.getIdPedido(),
                    pedido.getTipoEnvio(),
                    pedido.getIdPerfume(),
                    pedido.getId()
                );
                return user;
            }
            return null;
        }catch (Exception e){
            return null;
        }
        
    }


    public List<PedidoEntity> listarPedido(){
        return pedidorepository.findAll();
    }


    public String eliminarPedido (int idPedido){
        try{
            pedidorepository.deleteByIdPedido(idPedido);
            return "Pedido eliminado correctamente";
        }catch(Exception e){
            return "Error al eliminar pedido";
        }
    }

    



}
