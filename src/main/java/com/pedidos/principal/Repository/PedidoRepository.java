package com.pedidos.principal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedidos.principal.Model.Entity.PedidoEntity;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Integer> {

    PedidoEntity findByIdPedido(int idPedido);
    Boolean existsByIdPedido(int idPedido);
    void deleteByIdPedido(int idPedido);
}
