package com.pedidos.principal.Model.Entity;


import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
@Data
public class PedidoEntity {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPedido;

    @Column(name = "tipo de envio")
    private String tipoEnvio;

    @Column(name = "idPerfume")
    private int idPerfume;

    @Column(name = "id")
    private int id;
}
