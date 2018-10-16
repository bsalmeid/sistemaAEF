
package Controller;

import Beans.UsuarioBeans;
import DAO.UsuarioDAO;
import GUI.telausuario;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class UsuarioController {
    
    UsuarioDAO UsuarioD;
 
    
    public UsuarioController(){
        UsuarioD = new UsuarioDAO();
        
    }
    
    public boolean verificarDados(UsuarioBeans Usuario, DefaultTableModel TbFazendas){
            if (Usuario.getNome().equals("")) {
                JOptionPane.showMessageDialog(null, "Preencha o campo Nome!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
            }
            if (Usuario.getEmail().equals("")) {
                JOptionPane.showMessageDialog(null, "Preencha o campo E-mail!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
            }
            
            if (Usuario.getLogin().equals("")) {
                JOptionPane.showMessageDialog(null, "Preencha o campo Login!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
            }
            
            if (Usuario.getSenha().equals("")) {
                JOptionPane.showMessageDialog(null, "Preencha o campo Senha!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
            }
              
        UsuarioD.CadastrarUsuario(Usuario, TbFazendas);
        return true;
    }
    
      public String controledeCodigo(){
        return UsuarioD.proximoUsuario();
    }  
    
    public void controleBuscarFazendas(DefaultTableModel TbFazendas){
        UsuarioD.buscarFazendas(TbFazendas);
    }
    
    public void controleBuscarUsuario(UsuarioBeans Usuario, DefaultTableModel TbFazendas){
        UsuarioD.buscarUsuario(Usuario, TbFazendas);
    }
    
    public void controleEditarUsuario(UsuarioBeans Usuario, DefaultTableModel TbFazendas){
        UsuarioD.editarUsuario(Usuario, TbFazendas);
    }
    
    public void controleExcluirUsuario(UsuarioBeans Usuario){
        UsuarioD.excluirUsuario(Usuario);
    }        
}
