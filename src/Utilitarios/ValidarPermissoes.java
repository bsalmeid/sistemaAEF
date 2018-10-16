/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitarios;

import Beans.CadUsuarioPermissoes;
import Icones.FormatarICO;
import java.util.List;
import javax.swing.JOptionPane;
import static GUI.frmLogin.UsuarioLogadoBeans;

public class ValidarPermissoes {

    public static Boolean validarPermissaoView(String classe) {
        List<CadUsuarioPermissoes> permissoes = UsuarioLogadoBeans.getListPermissao();
        for (CadUsuarioPermissoes p : permissoes) {
            if (p.getNomeClasse().equals(classe)) {
                if (p.getView()) {
                    return p.getView();
                } else {
                    //JOptionPane.showMessageDialog(null, "Você Não Possuí Acesso a Está Rotina!", "Erro", 0, FormatarICO.ICObtnSair());
                    return false;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Permissão da Classe: " + classe + "  Não Localizada!", "Erro", 0, FormatarICO.ICObtnSair());
        return false;
    }

    public static Boolean validarPermissaoInsert(String classe) {
        List<CadUsuarioPermissoes> permissoes = UsuarioLogadoBeans.getListPermissao();
        for (CadUsuarioPermissoes p : permissoes) {
            if (p.getNomeClasse().equals(classe)) {
                if (p.getInsert()) {
                    return p.getInsert();
                } else {
                    JOptionPane.showMessageDialog(null, "Você Não Possuí Acesso a Está Rotina!", "Erro", 0, FormatarICO.ICObtnSair());
                    return false;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Rotina Não Localizada!", "Erro", 0, FormatarICO.ICObtnSair());
        return false;
    }

    public static Boolean validarPermissaoUpdate(String classe) {
        List<CadUsuarioPermissoes> permissoes = UsuarioLogadoBeans.getListPermissao();
        for (CadUsuarioPermissoes p : permissoes) {
            if (p.getNomeClasse().equals(classe)) {
                if (p.getUpdate()) {
                    return p.getUpdate();
                } else {
                    JOptionPane.showMessageDialog(null, "Você Não Possui Acesso a Esta Rotina!", "Erro", 0, FormatarICO.ICObtnSair());
                    return false;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Rotina Não Localizada!", "Erro", 0, FormatarICO.ICObtnSair());
        return false;
    }

    public static Boolean validarPermissaoDelete(String classe) {
        List<CadUsuarioPermissoes> permissoes = UsuarioLogadoBeans.getListPermissao();
        for (CadUsuarioPermissoes p : permissoes) {
            if (p.getNomeClasse().equals(classe)) {
                if (p.getDelete()) {
                    return p.getDelete();
                } else {
                    JOptionPane.showMessageDialog(null, "Você Não Possuí Acesso a Está Rotina!", "Erro", 0, FormatarICO.ICObtnSair());
                    return false;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Rotina Não Localizada!", "Erro", 0, FormatarICO.ICObtnSair());
        return false;
    }

    public static Boolean validarPermissaoSelect(String classe) {
        List<CadUsuarioPermissoes> permissoes = UsuarioLogadoBeans.getListPermissao();
        for (CadUsuarioPermissoes p : permissoes) {
            if (p.getNomeClasse().equals(classe)) {
                if (p.getSelect()) {
                    return p.getSelect();
                } else {
                    JOptionPane.showMessageDialog(null, "Você Não Possui Acesso a Está Rotina!", "Erro", 0, FormatarICO.ICObtnSair());
                    return false;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Rotina Não Localizada!", "Erro", 0, FormatarICO.ICObtnSair());
        return false;
    }

    public static void main(String[] args) {

    }

}
