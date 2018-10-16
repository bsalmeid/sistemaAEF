
package Controller;

import Beans.PropriedadesBeans;
import DAO.PropriedadesDAO;
import GUI.FrmCadFazendas;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PropriedadesController {

 PropriedadesDAO PropriedadesD;
 
    
    public PropriedadesController(){
        PropriedadesD = new PropriedadesDAO();
        
    }

    public boolean controleCadastrarFazendas(PropriedadesBeans Fazendas){
        
            if (Fazendas.getNome().equals("")) {
                JOptionPane.showMessageDialog(null, "Preencha o campo Nome!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
            }
            if (Fazendas.getEndereco().equals("")) {
                JOptionPane.showMessageDialog(null, "Preencha o campo Nome!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
            }
            if (Fazendas.getCidade().equals("")) {
                JOptionPane.showMessageDialog(null, "Preencha o campo Nome!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
            }
            if (Fazendas.getEstado().equals("-")) {
                JOptionPane.showMessageDialog(null, "Preencha o campo Nome!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
            }
            if (Fazendas.getArea().equals("")) {
                JOptionPane.showMessageDialog(null, "Preencha o campo Nome!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
            }
            if (Fazendas.getInscricao().equals("")) {
                JOptionPane.showMessageDialog(null, "Preencha o campo Nome!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
            }
            
        PropriedadesD.cadastrarFazendas(Fazendas);
        return true;
    }
    
    public PropriedadesBeans controleBuscarFazendas(int Codigo){
        return PropriedadesD.buscarFazendas(Codigo);
    }
    
    public void controleAtualizarFazendas(PropriedadesBeans Fazendas){
        PropriedadesD.editarFazendas(Fazendas);
    }

    public void controleBuscarFazendas(DefaultTableModel Fazendas){
   
    
    }
    
    public Integer controleBuscarUltimo(Integer Ultimo){
       return PropriedadesD.buscarUltimo(Ultimo);
        
    }
    
}
