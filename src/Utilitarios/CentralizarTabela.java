/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitarios;


import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Bruno
 */
public class CentralizarTabela extends DefaultTableCellRenderer  {
       DefaultTableCellRenderer cellRender;
       public void centralizarTabela(JTable tabela){
       cellRender = new DefaultTableCellRenderer();
        cellRender.setHorizontalAlignment(SwingConstants.CENTER);
        
        for (int i = 0; i < tabela.getColumnCount(); i++){
            if(!tabela.getModel().getColumnClass(i).equals(Boolean.class)){
            tabela.getColumnModel().getColumn(i).setCellRenderer(cellRender);
           
            }
        }    
    }
    
    
    
}
