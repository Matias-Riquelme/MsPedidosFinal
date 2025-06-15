package com.ejemplo_semestral.principal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ms_gestor_de_pedido.principal.models.pedido;
import com.ms_gestor_de_pedido.principal.models.entity.pedidoEntity;
import com.ms_gestor_de_pedido.principal.repository.pedidoRepository;
import com.ms_gestor_de_pedido.principal.service.UserService;
import com.pedidos.principal.Model.Pedido;
import com.pedidos.principal.Model.Entity.PedidoEntity;
import com.pedidos.principal.Repository.PedidoRepository;

public class UserTest {
    
    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService userService;

    private Pedido pedido;
    private PedidoEntity pedidoEntity;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        pedido = new Pedido(1, "Delivery");
        pedidoEntity = new PedidoEntity();
        pedidoEntity.setId(1);
        pedidoEntity.setTipoEnvio("Delivery");

    }

    @Test
    public void testAgregarpedido_nuevo(){
        when(pedidoRepository.existsById(pedido.getIdPedido())).thenReturn(false);
        when(pedidoRepository.save(any(pedidoEntity.class))).thenReturn(pedidoEntity);

        String result = userService.agregapedido(pedido);
        assertEquals("pedido agregado correctamente", result);
    }

    @Test
    public void testAgregarpedido_existe(){
        when(pedidoRepository.existsByIdPedido(pedido.getIdPedido())).thenReturn(true);

        String result = userService.agregapedido(pedido);
        assertEquals("El pedido ya existe", result);
    }

    @Test
    public void testTraerpedidoPorCorreo(){
        when(pedidoRepository.findByIdPedido(1)).thenReturn(pedidoEntity);
        Pedido result = userService.traerpedido(1);
        assertNotNull(result);
        assertEquals(1, result.getIdPedido());
    }

    @Test
    public void testTraerpedidoNoExiste(){
        when(pedidoRepository.findByIdPedido(0)).thenReturn(null);
        pedido result = userService.traerpedido(0);
        assertNull(result);
    }

    @Test
    public void actualizarpedido_existe(){
        when(pedidoRepository.findByIdPedido(1)).thenReturn(Optional.of(pedidoEntity));
        when(pedidoRepository.save(any(pedidoEntity.class))).thenReturn(pedidoEntity);

        pedido nuevo =  new pedido(1, "Delivery");;
        String result = userService.actualizarpedido(1, nuevo);

        assertEquals("pedido actualizado correctamente", result);
    }

    @Test
    public void borrarpedido(){
        when(pedidoRepository.existsByIdPedido(1)).thenReturn(true);
        doNothing().when(pedidoRepository).deleteById(1);
        String result = userService.borrarpedido(1);

        assertEquals("pedido borrado correctamente", result);
    }
}
