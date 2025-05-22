package com.pedidos.principal.Service;

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
                    pedido.getTipoEnvio()
                );
                return user;
            }
            return null;
        }catch (Exception e){
            return null;
        }
        
    }


}
