package com.pedidos.principal.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Pedido {
    private int idPedido;
    private String tipoEnvio;
    private int id;
}
