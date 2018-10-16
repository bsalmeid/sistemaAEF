/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GerarRelatorios;

import Utilitarios.Conexao;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Usuario
 */
public class RelatoriosGado {

    public static void imprimirOrdemCompraGado(Integer nOrdem) {
        try {
            if (nOrdem > 0) {
                Map p = new HashMap();
                p.put("codCompra", nOrdem);
                p.put("SUBREPORT_DIR", new File("").getClass().getResource("/Relatorios/").toString());
                JasperReport relatorio = JasperCompileManager.compileReport(new File("").getClass().getResourceAsStream("/Relatorios/RelatorioCompraGado.jrxml"));
                JasperPrint impressao = JasperFillManager.fillReport(relatorio, p, Conexao.getConnection());
                JasperViewer jrviewer = new JasperViewer(impressao, false);
                jrviewer.setVisible(true);
                jrviewer.toFront();
            } else {
                JOptionPane.showMessageDialog(null, "Verifique o número da Ordem de Compra!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static void main(String[] args) {
        Conexao.ReiniciarCon();
    }

}
