
package GUI;

import Beans.AtividadeBeans;
import Beans.CentroDeResultado;
import Beans.ContaBancariaBeans;
import Beans.FornecedoresBeans;
import Beans.MoedaBeans;
import Beans.PagamentosBeans;
import Beans.PagtoClassBeans;
import Beans.PagtoNFBeans;
import Beans.PlanoContaBeans;
import Beans.PropriedadesBeans;
import DAO.DiversasHibernate;
import DAO.FornecedoresDAO;
import DAO.PagamentosDAO;
import static GUI.Principal.ListaPropriedades;
import static GUI.Principal.listBancos;
import static GUI.Principal.listContaOrigem;
import static GUI.Principal.listMoeda;
import static GUI.Principal.listPlanoContas;
import static GUI.Principal.listaAtividades;
import static GUI.Principal.listaCentroResultado;
import static GUI.frmLogin.UsuarioLogadoBeans;
import Icones.FormatarICO;
import TableModel.TableModelImportarFolhaPagto;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import Utilitarios.ThreadLoadProgressBar;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import jxl.*;
import jxl.read.biff.BiffException;

/**
 *
 * @author agroa
 */
public class frmImportarFolhaPagto extends javax.swing.JInternalFrame {
    
    TableModelImportarFolhaPagto TbFolha2;
    CentralizarTabela Centralizar;
    FornecedoresDAO FornecedoresD;
    ThreadLoadProgressBar thread;
    PagamentosDAO PagtoD;
    
    public frmImportarFolhaPagto() {
        initComponents();
        carregarCentroResultado();
        tabelaPagtos();
        carregarPlanoContas();
        carregarMoedas();
        carregarBancos();
        carregarContasOrigem();
        carregarAtividades();
        carregarFazendas();
        FornecedoresD = new FornecedoresDAO();
        PagtoD = new PagamentosDAO();
    }
    
    private JTable tabelaPagtos() {
        tb_folha.setModel(getTableModel());
        Centralizar = new CentralizarTabela();
        Centralizar.centralizarTabela(tb_folha);
        ((DefaultTableCellRenderer) tb_folha.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
       
        for (int c = 0; c < tb_folha.getColumnCount(); c++){
            if (c == TbFolha2.ID_PAGTO){
                tb_folha.getColumnModel().getColumn(c).setMaxWidth(0);
                tb_folha.getColumnModel().getColumn(c).setMinWidth(0);
                tb_folha.getColumnModel().getColumn(c).setPreferredWidth(0);
            }
        }
        
        return tb_folha;
    }
    
    private void carregarCentroResultado() {
        if (listaCentroResultado == null) {
            listaCentroResultado = DiversasHibernate.getListaCentroResultado();
        }
    }
    
    private void carregarPlanoContas() {
        if (listPlanoContas == null) {
            listPlanoContas = DiversasHibernate.getPlanoConta();
        }
        
    }
    
    private void carregarMoedas() {
        if (listMoeda == null) {
            listMoeda = DiversasHibernate.getListaMoeda();
        }
    }
    
    private void carregarBancos() {
        if (listBancos == null) {
            listBancos = DiversasHibernate.getListaBancos();
        }
    }
    
    private void carregarContasOrigem() {
        if (listContaOrigem == null) {
            listContaOrigem = DiversasHibernate.getListaContasBancarias();
        }
    }
    
    private void carregarAtividades() {
        if (listaAtividades == null) {
            listaAtividades = DiversasHibernate.getListaAtividade();
        }
    }
    
    private void carregarFazendas() {
        if (ListaPropriedades == null) {
            ListaPropriedades = DiversasHibernate.getListaFazendas();
        }
    }
    
    private TableModelImportarFolhaPagto getTableModel() {
        if (TbFolha2 == null) {
            TbFolha2 = new TableModelImportarFolhaPagto();
        }
        return TbFolha2;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tb_folha = new javax.swing.JTable();
        jXTaskPane1 = new org.jdesktop.swingx.JXTaskPane();
        jPanel1 = new javax.swing.JPanel();
        btn_importar = new javax.swing.JButton();
        btn_salvarDados = new javax.swing.JButton();
        btn_verificar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Importar Dados Folha Pagto");

        tb_folha.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tb_folha);

        jXTaskPane1.setTitle("");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_importar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_importar.setText("Ler Dados Excel");
        btn_importar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_importarActionPerformed(evt);
            }
        });

        btn_salvarDados.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_salvarDados.setText("Salvar Dados DB");
        btn_salvarDados.setEnabled(false);
        btn_salvarDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salvarDadosActionPerformed(evt);
            }
        });

        btn_verificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_verificar.setText("Verificar Pagamentos");
        btn_verificar.setEnabled(false);
        btn_verificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_verificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(820, Short.MAX_VALUE)
                .addComponent(btn_importar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_verificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_salvarDados)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_importar)
                    .addComponent(btn_salvarDados)
                    .addComponent(btn_verificar))
                .addContainerGap())
        );

        jXTaskPane1.getContentPane().add(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jXTaskPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXTaskPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_importarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_importarActionPerformed
        TbFolha2.limpar();
        try {
            JFileChooser fc = new JFileChooser();
            int option = fc.showOpenDialog(tb_folha);
            if (option == JFileChooser.APPROVE_OPTION) {
                File fileName = new File(fc.getSelectedFile().getAbsolutePath());
                String path = fc.getSelectedFile().getParentFile().getPath();
                WorkbookSettings conf = new WorkbookSettings();
                conf.setEncoding("ISO-8859-1");
                Workbook workbook = Workbook.getWorkbook(fileName, conf);
                Sheet planilha = workbook.getSheet(0);
                List<PagamentosBeans> list = new ArrayList<>();
                criarListaPagamentos(list, planilha);
                btn_verificar.setEnabled(true);
                btn_importar.setEnabled(false);
            }
        } catch (IOException | BiffException ex) {
            Logger.getLogger(frmImportarFolhaPagto.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btn_importarActionPerformed

    private void btn_salvarDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarDadosActionPerformed
        if (TbFolha2.getRowCount() > 0) {
            int cadastrar = JOptionPane.showConfirmDialog(null, "Atenção está ação irá importar os pagamentos abaixo, deseja continuar?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (cadastrar == JOptionPane.YES_OPTION) {
                SalvarPagtos(TbFolha2.getLista());
            }
        }
    }//GEN-LAST:event_btn_salvarDadosActionPerformed

    private void btn_verificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_verificarActionPerformed
        for (int i = 0; i < TbFolha2.getLista().size(); i++) {
            if (verificarBeans(TbFolha2.getLista().get(i))) {
                btn_salvarDados.setEnabled(true);
            }
        }
    }//GEN-LAST:event_btn_verificarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_importar;
    private javax.swing.JButton btn_salvarDados;
    private javax.swing.JButton btn_verificar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane1;
    private javax.swing.JTable tb_folha;
    // End of variables declaration//GEN-END:variables

    private List<PagamentosBeans> criarListaPagamentos(List<PagamentosBeans> list, Sheet planilha) {
        PagamentosBeans pg;
        List<PagtoClassBeans> listClass;
        List<PagtoNFBeans> listNF;
        PagtoClassBeans clas = new PagtoClassBeans();
        PagtoNFBeans nf = new PagtoNFBeans();
        for (int i = 1; i < 200; i++) { //System.out.println("Cell Linha: " + i);
            if (!planilha.getCell(14, i).getContents().equals("")) { // System.out.println("Cell Linha Dentro do IF: " + i);
                pg = new PagamentosBeans();
                listClass = new ArrayList<>();
                listNF = new ArrayList<>();
                for (int columnIndex = 0; columnIndex < 20; columnIndex++) { // percorrer as colunas até a Coluna nº 19
                    if (columnIndex == 0) {
                        clas = new PagtoClassBeans();
                        nf = new PagtoNFBeans();
                    }
                    popularBeansPagamento(pg, nf, clas, planilha, i, columnIndex);
                }
                clas.setPagto(pg);
                nf.setPagto(pg);
                nf.setIdTipoDoc(24);
                nf.setTipoDoc("Salário");
                nf.setNDOC(0);
                
                pg.setFornecedorB(null);
                pg.setMoeda("Real");
                pg.setMoedaBeans(new MoedaBeans(1, "Real"));
                pg.setStatus(true);
                pg.setUsuario(UsuarioLogadoBeans.getLogin());
                pg.setValorEmMoeda(0.00);
                pg.setTaxaConverte(0.00);
                pg.setnCompra(null);
                pg.setnPedido(0);
                
                listClass.add(clas);
                listNF.add(nf);
                pg.setListaNotaFiscal(listNF);
                pg.setListaClassificacao(listClass);
                list.add(pg);
            }
        }
        TbFolha2.addLista(list);
        getIDFornecedor(list);
        
        return list;
    }
    
    private void popularBeansPagamento(PagamentosBeans pg, PagtoNFBeans nf, PagtoClassBeans clas, Sheet planilha, int rowIndex, int columnIndex) {
        //System.out.println("Cell Linha: " + rowIndex + " Coluna :" + columnIndex);
        String conteudo = planilha.getCell(columnIndex, rowIndex).getContents();
        switch (columnIndex) {
            case 0:
                Date data = Corretores.ConverterStringDateDDMMAA(planilha.getCell(columnIndex, rowIndex).getContents());
                pg.setDtPrevista(data);
                pg.setDtRealizado(data);
                pg.setDataLancamento(new Date(System.currentTimeMillis()));
                nf.setDtEmissao(data);
                break;
            case 2: // conta Origem
                pg.setIDContaOrigem(getContaOrigem(new Integer(conteudo)));
                break;
            case 3:
                clas.setAtividadeBeans(getAtividade(conteudo));
                clas.setAtividade(clas.getAtividadeBeans().getDescricao()); // manter esta mesma ordem para evitar novo GET
                break;
            case 18: // agencia
                pg.setAgencia(conteudo);
                break;
            case 11: // Banco Destino    
                pg.setBanco(conteudo);
                break;
            case 19:
                pg.setConta(retirarZeros(conteudo.replaceAll(" ", "")));
                break;
            case 13:
                String cpf = Corretores.ConverterParaCPF(conteudo);
                pg.setCPF_CNPJ("CPF");
                nf.setTipoCad("CPF");
                pg.setCpf(cpf);
                nf.setnCad(cpf);
                break;
            case 9:
                pg.setFormaPagto(conteudo);
                break;
            case 14:
                pg.setTitular(conteudo);
                break;
            case 15:
                Double valor = Corretores.ConverterStringDouble(conteudo);
                pg.setValorCompra(valor);
                pg.setValorPagamento(valor);
                clas.setValorClas(valor);
                nf.setValor(valor);
                break;
            case 10:
                pg.setnDocumento(conteudo);
                break;
            case 4:
                clas.setFazendaB(getFazendas(conteudo)); // Manter sequencia para evitar GET
                clas.setFazenda(clas.getFazendaB().getNome());
                nf.setFazenda(clas.getFazenda());
                nf.setFazendaB(clas.getFazendaB());
                break;
            case 6:
                clas.setCentroResultado(getCentroResultado(conteudo));
                break;
            case 7:
                clas.setPlanoConta(getPlanoConta(new Integer(conteudo))); // manre está mesma Ordemm para evitar o GET
                clas.setIdPlanoContas(clas.getPlanoConta().getID());
                clas.setDesConta(clas.getPlanoConta().getDescricao());
                clas.setTipoDespesa(clas.getPlanoConta().getGrupoConta().getDescricao());
                break;
            case 12:
                clas.setDescricao(conteudo);
                break;
            default:
        }
        
    }
    
    private String retirarZeros(String str) {
        String temp = "";
        for (int i = 0; i < str.length(); i++) {
            if (!str.substring(i, i + 1).equals("0")) {
                temp += str.substring(i, str.length());
                break;
            }
        }
        return temp;
    }
    
    private ContaBancariaBeans getContaOrigem(Integer idConta) {
        for (ContaBancariaBeans c : listContaOrigem) {
            if (Objects.equals(c.getIdConta(), idConta)) {
                return c;
            }
        }
        return null;
    }
    
    private AtividadeBeans getAtividade(String Atividade) {
        for (AtividadeBeans a : listaAtividades) {
            if (a.getDescricao().equals(Atividade)) {
                return a;
            }
        }
        return null;
    }
    
    private CentroDeResultado getCentroResultado(String conteudo) {
        for (CentroDeResultado a : listaCentroResultado) {
            if (a.getDescricao().equals(conteudo)) {
                return a;
            }
        }
        return null;
    }
    
    private PlanoContaBeans getPlanoConta(Integer idPlanoConta) {
        for (PlanoContaBeans a : listPlanoContas) {
            if (Objects.equals(a.getConta(), idPlanoConta)) {
                return a;
            }
        }
        return null;
    }
    
    private PropriedadesBeans getFazendas(String conteudo) {
        for (PropriedadesBeans p : ListaPropriedades) {
            if (Objects.equals(p.getNome(), conteudo)) {
                return p;
            }
        }
        return null;
    }
    
    private Boolean SalvarPagtos(List<PagamentosBeans> listaPg) {
        thread = new ThreadLoadProgressBar() {
            @Override
            public void run() {
                int maximo = listaPg.size();
                thread.dialog.setProgressBar(false, 0, maximo, "Salvando Ordem de Pagamento");
                for (int i = 0; i < listaPg.size(); i++) {
                    thread.dialog.setString("Salvando Ordem " + i + " de " + maximo);
                    PagamentosBeans pg = listaPg.get(i);
                    if (verificarBeans(pg)) {
                        PagtoD.salvarPagtoEmLote(pg);
                    }
                }
                excluirPagamentosLancados();
                btn_importar.setEnabled(true);
                btn_verificar.setEnabled(false);
                btn_salvarDados.setEnabled(false);
                thread.dialog.dispose();
            }
        };
        return true;
    }
    
    private boolean verificarBeans(PagamentosBeans pg) {
        if (pg.getStatus() == false) {
            // Teste para Status Previsto
           /* if (pg.getDtPrevista() == null) {
                JOptionPane.showMessageDialog(null, "Verifique o Campo Data Prevista!", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
            if (pg.getDtPrevista() != null && Corretores.verificarData(pg.getDtPrevista())) {
                
            }*/
            if (pg.getValorCompra() == 0.0000) {
                JOptionPane.showMessageDialog(null, "Verifique o Campo Valor Previsto!", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
            if (pg.getMoedaBeans().getIdMoeda() == 0) {
                JOptionPane.showMessageDialog(null, "Verifique o Campo Moeda!", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
            
            if (pg.getIDContaOrigem().getIdConta() == 0) {
                JOptionPane.showMessageDialog(null, "Verifique o Campo Conta de Origem!", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
            if (pg.getFormaPagto().equals("-")) {
                JOptionPane.showMessageDialog(null, "Verifique o Campo Forma de Pagamento!", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
            if (pg.getCPF_CNPJ().equals("-")) {
                JOptionPane.showMessageDialog(null, "Verifique a Caixa de Seleção CPF/CNPJ", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
            if (pg.getFornecedorB() == null) {
                JOptionPane.showMessageDialog(null, "Realize a Consulta do CPF/CNPJ do Fornecedor!", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
            if (pg.getBanco().equals("-")) {
                JOptionPane.showMessageDialog(null, "Verifique o Banco de Destino", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
            if (pg.getAgencia().equals("")) {
                JOptionPane.showMessageDialog(null, "Verifique o Campo Agência de Destino", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
            if (pg.getConta().equals("")) {
                JOptionPane.showMessageDialog(null, "Verifique o Campo Conta de Destino", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
            if (pg.getTitular().equals("")) {
                JOptionPane.showMessageDialog(null, "Verifique o Campo Titular da Conta de Destino!", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
            
        }
        // Realizar Teste Adicional para Case pagamento seja realizado.
        if (pg.getStatus() == true) {
           /* if (pg.getDtRealizado() == null) {
                JOptionPane.showMessageDialog(null, "Verifique o Campo Data Realizada!", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
            if (pg.getDtRealizado() != null && Corretores.verificarData(pg.getDtRealizado()) == false) {
                return false;
            }*/
            if (pg.getValorPagamento() == 0.0000) {
                StringBuilder str = new StringBuilder();
                str.append(" <html> Verifique o Campo Valor para Pagamento! <br> <B> Funcionário: ");
                str.append(pg.getTitular());
                str.append("</B> </html>");
                JOptionPane.showMessageDialog(null, str.toString(), "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
            if (!pg.getMoedaBeans().getMoeda().equals("Real")) {
                if (pg.getValorEmMoeda() == 0.0000 || pg.getTaxaConverte() == 0.0000) {
                    JOptionPane.showMessageDialog(null, "Verifique os Campos Valor em Moedae Taxa de Conversão!", "Erro", 0, FormatarICO.ICObtnSair());
                    return false;
                }
            }
        }
        
        for (PagtoClassBeans c : pg.getListaClassificacao()) {
            if (verificaBeans(c) == false) {
                return false;
            }
        }
        for (PagtoNFBeans nf : pg.getListaNotaFiscal()) {
            if (verificaBeans(nf) == false) {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean verificaBeans(PagtoClassBeans clas) {
        if (clas.getAtividadeBeans().getID() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Atividade!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (clas.getCentroResultado().getID() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Centro de Resultado!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (clas.getFazendaB().getCodigo() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Fazenda!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (clas.getPlanoConta().getID() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Plano de Conta!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (clas.getTipoDespesa().equals("-")) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Plano Tipo de Despesa!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        //System.out.println(clas.getValorClas());
        if (clas.getValorClas() == 0.00) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Valor Classificado!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (clas.getDescricao().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Descrição!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }
    
    private boolean verificaBeans(PagtoNFBeans nf) {
        if (nf.getIdTipoDoc() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Tipo de Documento!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (nf.getTipoCad().equals("-")) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo CPF/CNPJ!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (nf.getFornecedorB() == null) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Fornecedor!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (nf.getDtEmissao() == null) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Data de Emissão!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (nf.getFazendaB().getCodigo() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Fazenda!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (nf.getValor() == 0.00) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Valor do Documento Fiscal!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }
    
    private boolean getIDFornecedor(List<PagamentosBeans> listaPg) {
        String cpfs = "";
        for (int i = 0; i < listaPg.size(); i++) {
            cpfs += ", '" + listaPg.get(i).getCpf() + "'";
        }
        cpfs = "(" + cpfs.replaceFirst(",", "") + ")";
        List<FornecedoresBeans> listaF = FornecedoresD.consultaFornecedoresLote(cpfs);

        // Popular Lista de Pagamntos
        for (int p = 0; p < listaPg.size(); p++) {
            for (int f = 0; f < listaF.size(); f++) {
                if (listaPg.get(p).getCpf().equals(listaF.get(f).getCnpj())) {
                    FornecedoresBeans fornB = listaF.get(f);
                    listaPg.get(p).setFornecedorB(fornB);
                    listaPg.get(p).getListaNotaFiscal().get(0).setFornecedorB(fornB);
                    listaPg.get(p).getListaNotaFiscal().get(0).setFornecedor(fornB.getNomeFantasia());
                    listaPg.get(p).getListaNotaFiscal().get(0).setIdFornecedor(fornB.getID());
                    break;
                }
            }
        }
        // Testar se há fornecedores Nulos
        int contador = 0;
        for (int p = 0; p < listaPg.size(); p++) {
            if (listaPg.get(p).getFornecedorB() == null) {
                contador += 1;
            }
        }
        // cadastrar funcionário caso fornecedor seja nulo....
        if (contador > 0) {
            int cadastrar = JOptionPane.showConfirmDialog(null, "Existem " + contador + " Fornecedore(s) não Cadastrado(s), Desaja Cadastra-los?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (cadastrar == JOptionPane.YES_OPTION) {
                CadastrarFornecedores(listaPg);
                return false;
            }
        }
        return true;
    }
    
    private void CadastrarFornecedores(List<PagamentosBeans> listaPg) {
        thread = new ThreadLoadProgressBar() {
            @Override
            public void run() {
                FornecedoresBeans fornB;
                int contador = 0;
                thread.dialog.setProgressBar(false, 0, listaPg.size(), "Cadastrando Colaborador");
                for (int i = 0; i < listaPg.size(); i++) {
                    PagamentosBeans pg = listaPg.get(i);
                    fornB = new FornecedoresBeans();
                    if (pg.getFornecedorB() == null) {
                        thread.dialog.setValue(i);
                        fornB.setAgencia(pg.getAgencia());
                        fornB.setBanco(pg.getBanco());
                        fornB.setConta(pg.getConta());
                        fornB.setCnpj(pg.getCpf());
                        fornB.setNomeFantasia(pg.getTitular());
                        fornB.setRazaoSocial(pg.getTitular());
                        fornB.setTipoPessoa("CPF");
                        fornB.setIdSegmento(6);
                        if (FornecedoresD.cadastrarFornecedorHIbernate(fornB)) {
                            contador += 1;
                            pg.setFornecedorB(fornB);
                        }
                    }
                }
                thread.dialog.dispose();
                JOptionPane.showMessageDialog(null, contador + " Colaboradores(s) Cadastrado(s) com Sucesso!", "Registro Salvo com Sucesso", 0, FormatarICO.ICObtnOk());
                
            }
        };
    }
    
    private void excluirPagamentosLancados() {
        List<PagamentosBeans> listaNaoImportada = new ArrayList();
        for (int i = 0; i < TbFolha2.getLista().size(); i++) {
            if (TbFolha2.getValueAt(i, TbFolha2.ID_PAGTO) == null) {
                listaNaoImportada.add(TbFolha2.getBeans(i));
            }
        }
        TbFolha2.limpar();
        TbFolha2.addLista(listaNaoImportada);
    }
    
}
