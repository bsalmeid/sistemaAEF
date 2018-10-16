/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Beans.SaidaGadoBeans;
import DAO.SaidaGadoDAO;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bruno
 */
public class SaidaGadoController {
    
    SaidaGadoDAO SaidaD;
    SaidaGadoBeans SaidaB;
    DefaultTableModel TbSaidaResumo;
    
    public SaidaGadoController(){
        SaidaD = new SaidaGadoDAO();
        SaidaB = new SaidaGadoBeans();
}
    
    public void inserirRegistro(SaidaGadoBeans Saida){
        
    } 
    
    public void excluirRegistroController (Integer ID){
        SaidaD.excluirRegistro(ID);
        
    }
    
   public void preencherTabelaResumo(SaidaGadoBeans SaidaB, DefaultTableModel TbSaidaResumo){
       SaidaD.preencherTabelaResumo(SaidaB, TbSaidaResumo);
   } 
    
    
}
