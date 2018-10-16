package GerarRelatorios;

import Beans.PedidoAlmoxarifadoItens;
import Beans.PedidosAlmoxarifado;
import Icones.FormatarICO;
import Utilitarios.Conexao;
import Utilitarios.ConfigXML;
import Utilitarios.Corretores;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.commons.mail.MultiPartEmail;

public class RelatoriosPedidosMercadoria {

    public static void gerarPedido(PedidosAlmoxarifado nPedido, Boolean habilitarEnvio) {
        Map p = new HashMap();
        JasperReport relatorio;
        JasperPrint impressao;
        try {
            p.put("nPedido", nPedido.getId());
            p.put("caminhoLogo", FormatarICO.logoTipo().getImage());
            relatorio = JasperCompileManager.compileReport(new File("").getClass().getResourceAsStream("/Relatorios/pedidoFazenda.jrxml"));
            impressao = JasperFillManager.fillReport(relatorio, p, Conexao.getConnection());
            String caminho = (System.getProperty("user.home") + ("\\PedidoN" + nPedido.getId().toString() + ".pdf"));
            JasperViewer view = new JasperViewer(impressao, false);
            view.setTitle("Pedido Nº " + nPedido.getId());
            view.setVisible(true);
            if (habilitarEnvio) {
                enviarEmail(impressao, caminho, nPedido);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static void enviarEmail(JasperPrint impressao, String caminhaAnexo, PedidosAlmoxarifado nPedido) {
        int confirma = JOptionPane.showConfirmDialog(null, "Deseja Enviar Este Pedido, por e-mail?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            try {
                JasperExportManager.exportReportToPdfFile(impressao, caminhaAnexo);
                //File anexo = new File(caminhaAnexo); // Opçãp para enviar anexo
                //email.attach(anexo);
                //anexo.deleteOnExit();
                MultiPartEmail email = new MultiPartEmail();
                email.setCharset("utf-8");
                email.setDebug(false);
                email.setSmtpPort(587);
                email.setHostName("smtp.aefvilhena.com");
                email.setAuthentication("brunoalmeida=aefvilhena.com", "niv89dy");
                List<String> lista = listaEmails();
                for (int i = 0; i < lista.size(); i++) { email.addTo(lista.get(i)); }
                email.setFrom("brunoalmeida@aefvilhena.com");
                email.setSubject("Pedido Nº " + nPedido.getId());
                email.setContent(gerarHtmlEmail(nPedido), "text/html");
                email.send();
                
                JOptionPane.showMessageDialog(null, "E-mail enviado com sucesso!", "Executado", 1, FormatarICO.ICObtnOk());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao Enviar E-mail! " + e, "Erro", 0, FormatarICO.ICObtnSair());
            }
        }
    }

    private static String gerarHtmlEmail(PedidosAlmoxarifado nPedido) {
        String html = "";
        html += "<html><body>";
        html += "<h1>Pedido Nº " + nPedido.getId() + "</h1>";
        html += "<h2><B> Fazenda - " + nPedido.getIdFazenda().getNome() + "</B></h2>";
        html += "<br>";
        html += "<table><tr>";
        html += "<th>Item</th>";
        html += "<th>Codigo</th>";
        html += "<th>Descricao</th>";
        html += "<th>Quantidade</th>";
        html += "<th>Unidade</th>";
        html += "<th>Inventario</th>";
        html += "<th>Urgencia</th>";
        html += "<th>Setor</th>";
        html += "<th>Solicitante</th>";
        html += "</tr>";

        // linhas
        for (int i = 0; i < nPedido.getListaItens().size(); i++) {
            PedidoAlmoxarifadoItens item = nPedido.getListaItens().get(i);
            html += "<tr>";
            html += "<td>" + item.getnItem() + "</td> <td> " + item.getCodigo() + "</td> <td>" + item.getDescricao() + "</td> <td>" + item.getQuantidade() + "</td> <td>" + item.getUnidade() + "</td> <td>" + item.getInventario() + "</td>";
            html += "<td>" + item.getUrgencia() + "</td> <td> " + item.getSetor() + "</td> <td>" + item.getSolicitante() + "</td>";
            html += "</tr>";
        }
        html += "</table>";
        html += "<h3> Enviado em: " + Corretores.ConverterDateStringDDMMAAA(new Date()) + "<h3>";
        html += "</body></hmtl>";
        return html;
    }

    public static void gerarRemessa(Long idRemessa, String fazenda) {
        Map p = new HashMap();
        JasperReport relatorio;
        JasperPrint impressao;
        try {
            p.put("idRemessa", idRemessa);
            p.put("caminhoLogo", FormatarICO.logoTipo().getImage());
            p.put("fazendaDestino", fazenda);

            relatorio = JasperCompileManager.compileReport(new File("").getClass().getResourceAsStream("/Relatorios/imprimirRemessa.jrxml"));
            impressao = JasperFillManager.fillReport(relatorio, p, Conexao.getConnection());

            String caminho = (System.getProperty("user.home") + ("\\RemessaN" + idRemessa.toString() + ".pdf"));
            JasperViewer view = new JasperViewer(impressao, false);
            view.setTitle("Remessa Nº " + idRemessa);
            view.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private static List<String> listaEmails() {
        List<String> lista = new ArrayList<>();
        String[] split = ConfigXML.LerDadosTag("EmailsParaEnviarPedidos").split(",");
        lista.addAll(Arrays.asList(split));
        return lista;
    }

    public static void main(String[] args) {

    }

}
