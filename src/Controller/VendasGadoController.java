
package Controller;

import Beans.VendasGadoBeans;
import DAO.VendasGadoDAO;
import javax.swing.table.DefaultTableModel;



public class VendasGadoController {
     VendasGadoDAO VendasGadoD;
     VendasGadoBeans VendasGadoB;
     
     public VendasGadoController() {
         VendasGadoD = new VendasGadoDAO(); 
         VendasGadoB = new VendasGadoBeans();
     }
     
     public void controleBuscarVendas(DefaultTableModel TbVendas){
         VendasGadoD.buscarVendas(TbVendas);
     }
     
     public boolean controleEditarVendas (VendasGadoBeans Vendas){
         VendasGadoD.editarVenda(Vendas);
         return true;
     }
     
     public boolean controlerSalvarVenda(VendasGadoBeans Vendas, DefaultTableModel TbVendas){
         
         VendasGadoD.inserirVenda(Vendas);
         VendasGadoD.buscarVendas(TbVendas);
         return true;
     }
     
     public void controleExcluir(VendasGadoBeans Vendas){
         VendasGadoD.deletarVenda(Vendas);
     }
     
     
}
