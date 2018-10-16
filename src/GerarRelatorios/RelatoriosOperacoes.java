
package GerarRelatorios;

import Icones.FormatarICO;
import Utilitarios.Conexao;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;


public class RelatoriosOperacoes {

    public RelatoriosOperacoes() {
        Conexao.ReiniciarCon();
    }

    public void gerarImprimirOrdemServico(Integer IdOS) {

        Map p = new HashMap();
        JasperReport relatorio;
        JasperPrint impressao;
        JasperViewer jrviewer = null;
        try {
            p.put("idOS", IdOS);
            relatorio = JasperCompileManager.compileReport(new File("").getClass().getResourceAsStream("/Relatorios/OrdemServicos.jrxml"));
            impressao = JasperFillManager.fillReport(relatorio, p, Conexao.getConnection());
            jrviewer = new JasperViewer(impressao, false);
            jrviewer.setVisible(true);
            jrviewer.toFront();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        jrviewer.toFront();

    }

    public void enviarEmail(String Assunto, String Corpo) {
        try {
            // Cria a mensagem de e-mail
            MultiPartEmail email = new MultiPartEmail();
            email.setDebug(false);
            email.setHostName("smtp.aefvilhena.com");
            email.setSmtpPort(587);
            email.setAuthentication("brunoalmeida=aefvilhena.com", "niv89dy");
            email.addTo("brunoalmeida@aefvilhena.com");
            email.addTo("tatiana@aefvilhena.com");
            email.setFrom("brunoalmeida@aefvilhena.com");
            email.setSubject(Assunto);
            email.setMsg(Corpo);
            email.send();
            JOptionPane.showMessageDialog(null, "E-mail enviado com sucesso!", "Executado", 1, new ImageIcon(getClass().getResource("/Icones/ok.png")));
        } catch (EmailException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Enviar E-mail! " + e, "Erro", 0, FormatarICO.ICObtnSair());
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Conexao.ReiniciarCon();
    }

}
