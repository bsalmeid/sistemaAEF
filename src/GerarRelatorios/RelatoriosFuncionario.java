package GerarRelatorios;

import Icones.FormatarICO;
import Utilitarios.Conexao;
import Utilitarios.Corretores;
import java.io.*;
import java.util.*;
import javax.swing.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

public class RelatoriosFuncionario {

    private static final Map parametros = new HashMap();
    private static JasperPrint impressao;
    private static JasperReport relatorio;
    private static JasperViewer jrviewer;

    public static void main(String[] args) {
         imprimirRelatorioFuncionario(199);
    }

    public static void imprimirRelatorioFuncionario(Integer nFuncionario) {
        try {
            parametros.put("Codigo_Funcionario", nFuncionario);
            parametros.put("caminhoLogo", FormatarICO.logoTipo().getImage());
            File file  = Corretores.ValidarArquivoModelo("/src/Relatorios/DadosdoFuncionario.jrxml");
            //System.out.println("Caminho File: " + Corretores.ValidarArquivoModelo("/src/Relatorios/DadosdoFuncionario.jrxml"));
            parametros.put("SUBREPORT_DIR", file.getParent());
            //System.out.println(parametros.get("SUBREPORT_DIR"));   
            relatorio = JasperCompileManager.compileReport(new File("").getClass().getResourceAsStream("/Relatorios/DadosdoFuncionario.jrxml"));
            impressao = JasperFillManager.fillReport(relatorio, parametros, Conexao.getConnection());
            jrviewer = new JasperViewer(impressao, false);
            jrviewer.setVisible(true);
            jrviewer.toFront();
            //  JOptionPane.showMessageDialog(null, "Verifique o número do Funcionário!!");
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
