/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.PedidosAlmoxarifado;
import Utilitarios.Corretores;
import org.junit.Ignore;
import org.junit.Test;



/**
 *
 * @author agroa
 */
public class PedidoAlmoxarifadoTest {

    @Test
    @Ignore
    public void SalvarPedido() {
        PedidosAlmoxarifadoDAO dao = new PedidosAlmoxarifadoDAO();
        PedidosAlmoxarifado pedido = new PedidosAlmoxarifado();
        pedido.setIdUsuario(1);
        pedido.setData(Corretores.ConverterStringDateDDMMAAAA("25/03/2018"));
        pedido.setStatusEmissao(false);
        dao.salvar(pedido);
    }
    
    @Test
    public void buscarPeqdido(){
        PedidosAlmoxarifadoDAO dao = new PedidosAlmoxarifadoDAO();
    
    }
    


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}













