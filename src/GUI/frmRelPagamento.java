/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Beans.BancosBeans;
import Beans.ContaBancariaBeans;
import Beans.ListFazendasBeans;
import Beans.InventarioBeans;
import Beans.RelatoriosFinanceirosBeans;
import DAO.Diversas;
import DAO.DiversasHibernate;
import DAO.RelatoriosFinanceirosDAO;
import static GUI.Principal.ListFazendas;
import static GUI.Principal.listBancos;
import static GUI.Principal.listContaOrigem;
import static GUI.Principal.listInventario;
import static GUI.Principal.listPlanoContas;
import static GUI.frmLogin.NomeUsuario;
import static GUI.frmLogin.dataAtual;
import Icones.FormatarICO;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import Utilitarios.FrmProgressBar;
import Utilitarios.ThreadLoadProgressBar;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import jxl.WorkbookSettings;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFCellStyle.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;

/**
 *
 * @author Bruno
 */
public class frmRelPagamento extends javax.swing.JInternalFrame {

    RelatoriosFinanceirosDAO RelatoriosD;
    RelatoriosFinanceirosBeans RelatoriosB;
    DefaultTableModel TbPlanoContas;
    DefaultTableModel TbContaOrigem;
    DefaultTableModel TbFazendas;
    DefaultTableModel TbPagamentos;
    CentralizarTabela Centralizar;
    Diversas DiversasD;
    frmPrevisaoPagto PrevisaoPagto;
    frmGerarExtrato_CC GerarExtrato;
    frmPagamentos frmPagtos;
    frmDespesasInventario frmDespesasInvent;
    ActionListener OrigemIDPAgto;
    private MaskFormatter CNPJMask;
    private MaskFormatter CPFMask;
    Date dtInicial;
    Date dtFinal;
    ThreadLoadProgressBar thread;

    public frmRelPagamento() {
        initComponents();

        RelatoriosD = new RelatoriosFinanceirosDAO();
        RelatoriosB = new RelatoriosFinanceirosBeans();
        Centralizar = new CentralizarTabela();
        DiversasD = new Diversas();
        dtInicial = new Date();
        dtFinal = new Date();

        maskFormaterCPF();

        formatarTabelas();
        carregarFazendas();
        carregarContasOrigem();
    }

    private void maskFormaterCPF() {
        try {
            CNPJMask = new MaskFormatter("##.###.###/####-##");
            CPFMask = new MaskFormatter("###.###.###-##");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    private void carregarBancos() {
        if (listBancos == null) {
            listBancos = new ArrayList<>();
            listBancos = DiversasHibernate.getListaBancos();
        }
        //cb_banco.addItem("-");
        for (BancosBeans list : listBancos) {
            //cb_banco.addItem(list);
        }
    }

    private void carregarFazendas() {
        if (ListFazendas == null) {
            ListFazendas = new ArrayList<>();
            ListFazendas = DiversasD.listaDeFazendas();
        }
        cb_fazendaPC.addItem("-");
        for (ListFazendasBeans list : ListFazendas) {
            cb_fazendaPC.addItem(list);
        }
    }

    private void carregarContasOrigem() {
        if (listContaOrigem == null) {
            listContaOrigem = new ArrayList<>();
            listContaOrigem = DiversasD.listContaOrigem();
        }
        ContaBancariaBeans l = new ContaBancariaBeans();
        l.setIdConta(0);
        l.setDescricao("-");
        cb_contaOrigem.addItem(l);
        cb_bancoOrigem.addItem(l);
        for (ContaBancariaBeans list : listContaOrigem) {
            cb_contaOrigem.addItem(list);
            cb_bancoOrigem.addItem(list);
        }
    }

    private void listPlanoContas() {
        if (listPlanoContas == null) {
            listPlanoContas = new ArrayList<>();
            listPlanoContas = DiversasD.ListPlanoContas();
        }
    }

    private void formatarTabelas() {
        TbPlanoContas = (DefaultTableModel) tb_planoContas.getModel();
        TbContaOrigem = (DefaultTableModel) tb_contaOrigem.getModel();
        TbFazendas = (DefaultTableModel) tb_despesasFazendas.getModel();
        TbPagamentos = (DefaultTableModel) tb_nfPagtos.getModel();
        Centralizar.centralizarTabela(tb_planoContas);
        Centralizar.centralizarTabela(tb_contaOrigem);
        Centralizar.centralizarTabela(tb_despesasFazendas);
        Centralizar.centralizarTabela(tb_nfPagtos);
        ((DefaultTableCellRenderer) tb_contaOrigem.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        ((DefaultTableCellRenderer) tb_planoContas.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        ((DefaultTableCellRenderer) tb_despesasFazendas.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        ((DefaultTableCellRenderer) tb_nfPagtos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }

    private List<InventarioBeans> listInventario() {
        if (listInventario == null) {
            listInventario = DiversasD.ListInventario();
        }
        return listInventario;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.ButtonGroup buttonGroup1 = new javax.swing.ButtonGroup();
        jPopMenu = new javax.swing.JPopupMenu();
        jMenuEditarPagto = new javax.swing.JMenuItem();
        frmPagamentos = new javax.swing.JInternalFrame();
        javax.swing.JPanel jPanel4 = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane3 = new javax.swing.JScrollPane();
        tb_nfPagtos = new javax.swing.JTable();
        org.jdesktop.swingx.JXTaskPane jXTaskPane1 = new org.jdesktop.swingx.JXTaskPane();
        javax.swing.JPanel jPanel7 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        txt_dtInicialPagto =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        txt_dtFinalPagto =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        btn_pesqPagto = new javax.swing.JButton();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        cb_tipoDocPagto = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        txt_nDocPagto = new javax.swing.JTextField();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        txt_favorecidoPagto = new javax.swing.JTextField();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        txt_valorPagto = new javax.swing.JTextField();
        javax.swing.JLabel jLabel16 = new javax.swing.JLabel();
        cb_tipoDespesa = new javax.swing.JComboBox<>();
        cb_doc = new javax.swing.JComboBox<>();
        txt_cpf = new javax.swing.JFormattedTextField();
        btn_pesqFornecedor = new javax.swing.JButton();
        javax.swing.JLabel jLabel17 = new javax.swing.JLabel();
        cb_bancoOrigem = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel18 = new javax.swing.JLabel();
        cb_formaPagto = new javax.swing.JComboBox<>();
        frmPlanoContas = new javax.swing.JInternalFrame();
        javax.swing.JPanel jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLayer1 = new javax.swing.JLayeredPane();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        txt_dtInicialPC =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        txt_dtFinalPC =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        btn_pesqPlanoConta = new javax.swing.JButton();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        ch_PlanoCompleto = new javax.swing.JCheckBox();
        javax.swing.JCheckBox jCheckBox2 = new javax.swing.JCheckBox();
        cb_fazendaPC = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        cb_contaOrigem = new javax.swing.JComboBox<>();
        txt_contaInicial = new javax.swing.JTextField();
        javax.swing.JLabel jLabel13 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel14 = new javax.swing.JLabel();
        txt_contaFinal = new javax.swing.JTextField();
        btn_pesqPlanoConta1 = new javax.swing.JButton();
        javax.swing.JLabel jLabel15 = new javax.swing.JLabel();
        txt_nFrota = new javax.swing.JTextField();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        tb_contaOrigem = new javax.swing.JTable();
        javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        tb_despesasFazendas = new javax.swing.JTable();
        javax.swing.JScrollPane jScrollPane4 = new javax.swing.JScrollPane();
        tb_planoContas = new javax.swing.JTable();
        javax.swing.JMenuBar jMenuBar1 = new javax.swing.JMenuBar();
        javax.swing.JMenu jMenu1 = new javax.swing.JMenu();
        menuDespFazenda = new javax.swing.JCheckBoxMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator3 = new javax.swing.JPopupMenu.Separator();
        menuDespPlano = new javax.swing.JCheckBoxMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator2 = new javax.swing.JPopupMenu.Separator();
        menuDespPagto = new javax.swing.JCheckBoxMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator4 = new javax.swing.JPopupMenu.Separator();
        menuDesoDiario = new javax.swing.JCheckBoxMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator1 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenu jMenu2 = new javax.swing.JMenu();
        jm_previsaoPagto = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jm_extrato_cc = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jm_despesa_inventario = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jm_exportarContas = new javax.swing.JMenuItem();

        jMenuEditarPagto.setText("Editar Pagamento");
        jMenuEditarPagto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEditarPagtoActionPerformed(evt);
            }
        });
        jPopMenu.add(jMenuEditarPagto);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Relatório de Pagamentos");
        getContentPane().setLayout(null);

        frmPagamentos.setClosable(true);
        frmPagamentos.setIconifiable(true);
        frmPagamentos.setMaximizable(true);
        frmPagamentos.setResizable(true);
        frmPagamentos.setTitle("Consulta de Pagamentos");
        frmPagamentos.setVisible(false);

        tb_nfPagtos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Pagto", "Conta Origem", "Emissão", "Pagamento", "Emissor", "Nome Fantasia", "CNPJ / CPF", "Tipo Despesa", "Plano Conta", "Tipo Doc", "Nº Doc.", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_nfPagtos.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        tb_nfPagtos.getTableHeader().setReorderingAllowed(false);
        tb_nfPagtos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_nfPagtosMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tb_nfPagtosMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tb_nfPagtos);
        if (tb_nfPagtos.getColumnModel().getColumnCount() > 0) {
            tb_nfPagtos.getColumnModel().getColumn(0).setMinWidth(40);
            tb_nfPagtos.getColumnModel().getColumn(0).setPreferredWidth(40);
            tb_nfPagtos.getColumnModel().getColumn(0).setMaxWidth(40);
            tb_nfPagtos.getColumnModel().getColumn(1).setPreferredWidth(100);
            tb_nfPagtos.getColumnModel().getColumn(2).setMinWidth(90);
            tb_nfPagtos.getColumnModel().getColumn(2).setPreferredWidth(90);
            tb_nfPagtos.getColumnModel().getColumn(2).setMaxWidth(90);
            tb_nfPagtos.getColumnModel().getColumn(3).setMinWidth(90);
            tb_nfPagtos.getColumnModel().getColumn(3).setPreferredWidth(90);
            tb_nfPagtos.getColumnModel().getColumn(3).setMaxWidth(90);
            tb_nfPagtos.getColumnModel().getColumn(5).setPreferredWidth(0);
            tb_nfPagtos.getColumnModel().getColumn(6).setMinWidth(140);
            tb_nfPagtos.getColumnModel().getColumn(6).setPreferredWidth(160);
            tb_nfPagtos.getColumnModel().getColumn(6).setMaxWidth(160);
            tb_nfPagtos.getColumnModel().getColumn(7).setMinWidth(110);
            tb_nfPagtos.getColumnModel().getColumn(7).setPreferredWidth(110);
            tb_nfPagtos.getColumnModel().getColumn(7).setMaxWidth(110);
            tb_nfPagtos.getColumnModel().getColumn(8).setMinWidth(100);
            tb_nfPagtos.getColumnModel().getColumn(8).setPreferredWidth(100);
            tb_nfPagtos.getColumnModel().getColumn(8).setMaxWidth(100);
            tb_nfPagtos.getColumnModel().getColumn(9).setMinWidth(90);
            tb_nfPagtos.getColumnModel().getColumn(9).setPreferredWidth(90);
            tb_nfPagtos.getColumnModel().getColumn(9).setMaxWidth(90);
            tb_nfPagtos.getColumnModel().getColumn(10).setMinWidth(80);
            tb_nfPagtos.getColumnModel().getColumn(10).setPreferredWidth(80);
            tb_nfPagtos.getColumnModel().getColumn(10).setMaxWidth(80);
            tb_nfPagtos.getColumnModel().getColumn(11).setMinWidth(100);
            tb_nfPagtos.getColumnModel().getColumn(11).setMaxWidth(120);
        }
        tb_nfPagtos.getAccessibleContext().setAccessibleParent(frmPagamentos);

        jXTaskPane1.setTitle("Opções de Pesquisa");

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("De");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("à");

        btn_pesqPagto.setText("Pesquisar");
        btn_pesqPagto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesqPagtoActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Tipo Doc");

        cb_tipoDocPagto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "DANFE", "Recibo", "CTR", "S/ Doc. Fiscal", "N.F Faltando", "Comprovante", "FGTS", "Recibo Fiscal", "C.F", "Rescisão", "N.F", "Cédula Rural", "DARE", "DARF", "INSS", "Transf. Bancária", "Resgate", "Tárifa Bancária", "Título Capital.", "Poupança", "CDB", "LCA", "Ajuste", "Salário", "NDF", "SM" }));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Nº Doc");

        txt_nDocPagto.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Favorecido");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Valor");

        txt_valorPagto.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Tipo Despesa");

        cb_tipoDespesa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Custeio", "Investimento", "Compra de Gado" }));

        cb_doc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "CPF", "CNPJ" }));
        cb_doc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_docItemStateChanged(evt);
            }
        });

        txt_cpf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_cpfFocusLost(evt);
            }
        });

        btn_pesqFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar pequeno.png"))); // NOI18N
        btn_pesqFornecedor.setPreferredSize(new java.awt.Dimension(30, 25));
        btn_pesqFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesqFornecedorActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Conta de Origem");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Forma Pagamento");

        cb_formaPagto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Cheque em Mãos", "Cheque Deposito", "Em Espécie", "Boleto", "Formulário TED", "Transferência", "Internet Banking" }));
        cb_formaPagto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_formaPagtoItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dtInicialPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dtFinalPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_bancoOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_tipoDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_tipoDocPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nDocPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_formaPagto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_favorecidoPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_pesqFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_doc, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_valorPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_pesqPagto, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel5)
                            .addComponent(txt_dtInicialPagto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txt_dtFinalPagto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(cb_tipoDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(cb_tipoDocPagto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(txt_nDocPagto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cb_bancoOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel9)
                            .addComponent(txt_favorecidoPagto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(txt_valorPagto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cb_doc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_pesqFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cb_formaPagto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 3, Short.MAX_VALUE))
                    .addComponent(btn_pesqPagto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jXTaskPane1.getContentPane().add(jPanel7);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(jXTaskPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXTaskPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout frmPagamentosLayout = new javax.swing.GroupLayout(frmPagamentos.getContentPane());
        frmPagamentos.getContentPane().setLayout(frmPagamentosLayout);
        frmPagamentosLayout.setHorizontalGroup(
            frmPagamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        frmPagamentosLayout.setVerticalGroup(
            frmPagamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(frmPagamentos);
        frmPagamentos.setBounds(140, 10, 1298, 600);

        frmPlanoContas.setClosable(true);
        frmPlanoContas.setIconifiable(true);
        frmPlanoContas.setResizable(true);
        frmPlanoContas.setTitle("Relatório de Plano de Contas");
        frmPlanoContas.setVisible(false);

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setDoubleBuffered(false);
        jPanel6.setFocusCycleRoot(true);
        jPanel6.setFocusTraversalPolicyProvider(true);
        jPanel6.setInheritsPopupMenu(true);
        jPanel6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPanel6FocusLost(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("De");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("à");

        btn_pesqPlanoConta.setText("Pesquisar");
        btn_pesqPlanoConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesqPlanoContaActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Fazenda");

        buttonGroup1.add(ch_PlanoCompleto);
        ch_PlanoCompleto.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_PlanoCompleto.setText("Completo");

        buttonGroup1.add(jCheckBox2);
        jCheckBox2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jCheckBox2.setSelected(true);
        jCheckBox2.setText("Sintético");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Conta de Origem");

        txt_contaInicial.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_contaInicial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_contaInicialFocusLost(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Plano de Contas");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("à");

        txt_contaFinal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_contaFinal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_contaFinalFocusLost(evt);
            }
        });

        btn_pesqPlanoConta1.setText("Imprimir");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Nº de Frota");

        txt_nFrota.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nFrota.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_nFrotaFocusLost(evt);
            }
        });

        jLayer1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayer1.setLayer(txt_dtInicialPC, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayer1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayer1.setLayer(txt_dtFinalPC, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayer1.setLayer(btn_pesqPlanoConta, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayer1.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayer1.setLayer(ch_PlanoCompleto, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayer1.setLayer(jCheckBox2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayer1.setLayer(cb_fazendaPC, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayer1.setLayer(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayer1.setLayer(cb_contaOrigem, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayer1.setLayer(txt_contaInicial, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayer1.setLayer(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayer1.setLayer(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayer1.setLayer(txt_contaFinal, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayer1.setLayer(btn_pesqPlanoConta1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayer1.setLayer(jLabel15, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayer1.setLayer(txt_nFrota, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayer1Layout = new javax.swing.GroupLayout(jLayer1);
        jLayer1.setLayout(jLayer1Layout);
        jLayer1Layout.setHorizontalGroup(
            jLayer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayer1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayer1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dtInicialPC, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_dtFinalPC, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_fazendaPC, 0, 116, Short.MAX_VALUE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(ch_PlanoCompleto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCheckBox2))
                    .addGroup(jLayer1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_contaInicial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_contaFinal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nFrota)
                        .addGap(14, 14, 14)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_contaOrigem, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jLayer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_pesqPlanoConta, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addComponent(btn_pesqPlanoConta1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jLayer1Layout.setVerticalGroup(
            jLayer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayer1Layout.createSequentialGroup()
                .addGroup(jLayer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_pesqPlanoConta)
                    .addComponent(jCheckBox2)
                    .addComponent(ch_PlanoCompleto)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_dtFinalPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_dtInicialPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_fazendaPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jLayer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_contaOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_contaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_contaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesqPlanoConta1)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nFrota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLayer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLayer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tb_contaOrigem.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tb_contaOrigem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Conta", "Valor", "%"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_contaOrigem.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tb_contaOrigem.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tb_contaOrigem);
        if (tb_contaOrigem.getColumnModel().getColumnCount() > 0) {
            tb_contaOrigem.getColumnModel().getColumn(0).setMinWidth(30);
            tb_contaOrigem.getColumnModel().getColumn(0).setMaxWidth(30);
            tb_contaOrigem.getColumnModel().getColumn(1).setMinWidth(70);
            tb_contaOrigem.getColumnModel().getColumn(1).setPreferredWidth(90);
            tb_contaOrigem.getColumnModel().getColumn(1).setMaxWidth(120);
            tb_contaOrigem.getColumnModel().getColumn(2).setMinWidth(120);
            tb_contaOrigem.getColumnModel().getColumn(2).setPreferredWidth(120);
            tb_contaOrigem.getColumnModel().getColumn(2).setMaxWidth(150);
        }

        tb_despesasFazendas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tb_despesasFazendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fazenda", "Valor", "%"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tb_despesasFazendas);
        if (tb_despesasFazendas.getColumnModel().getColumnCount() > 0) {
            tb_despesasFazendas.getColumnModel().getColumn(0).setResizable(false);
            tb_despesasFazendas.getColumnModel().getColumn(0).setPreferredWidth(30);
            tb_despesasFazendas.getColumnModel().getColumn(1).setMinWidth(70);
            tb_despesasFazendas.getColumnModel().getColumn(1).setPreferredWidth(90);
            tb_despesasFazendas.getColumnModel().getColumn(1).setMaxWidth(120);
            tb_despesasFazendas.getColumnModel().getColumn(2).setMinWidth(120);
            tb_despesasFazendas.getColumnModel().getColumn(2).setPreferredWidth(120);
            tb_despesasFazendas.getColumnModel().getColumn(2).setMaxWidth(150);
        }

        jScrollPane4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane4.setFocusable(false);
        jScrollPane4.setRequestFocusEnabled(false);
        jScrollPane4.setVerifyInputWhenFocusTarget(false);

        tb_planoContas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tb_planoContas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Conta", "Plano de Contas", "ID Pagto", "Descrição", "Fazenda", "Valor Total", "%"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_planoContas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_planoContasMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tb_planoContasMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(tb_planoContas);
        if (tb_planoContas.getColumnModel().getColumnCount() > 0) {
            tb_planoContas.getColumnModel().getColumn(0).setPreferredWidth(70);
            tb_planoContas.getColumnModel().getColumn(1).setMinWidth(100);
            tb_planoContas.getColumnModel().getColumn(1).setPreferredWidth(150);
            tb_planoContas.getColumnModel().getColumn(1).setMaxWidth(170);
            tb_planoContas.getColumnModel().getColumn(5).setPreferredWidth(130);
            tb_planoContas.getColumnModel().getColumn(6).setMinWidth(80);
            tb_planoContas.getColumnModel().getColumn(6).setMaxWidth(80);
        }
        tb_planoContas.getAccessibleContext().setAccessibleParent(frmPlanoContas);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE))
                    .addComponent(jScrollPane4)))
        );

        javax.swing.GroupLayout frmPlanoContasLayout = new javax.swing.GroupLayout(frmPlanoContas.getContentPane());
        frmPlanoContas.getContentPane().setLayout(frmPlanoContasLayout);
        frmPlanoContasLayout.setHorizontalGroup(
            frmPlanoContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        frmPlanoContasLayout.setVerticalGroup(
            frmPlanoContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frmPlanoContasLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(frmPlanoContas);
        frmPlanoContas.setBounds(10, 10, 790, 540);

        jMenu1.setText("Exibir");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        menuDespFazenda.setText("Despesas por Fazenda");
        menuDespFazenda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                menuDespFazendaItemStateChanged(evt);
            }
        });
        jMenu1.add(menuDespFazenda);
        jMenu1.add(jSeparator3);

        menuDespPlano.setText("Despesas por Centro de Custo");
        menuDespPlano.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                menuDespPlanoItemStateChanged(evt);
            }
        });
        menuDespPlano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDespPlanoActionPerformed(evt);
            }
        });
        jMenu1.add(menuDespPlano);
        jMenu1.add(jSeparator2);

        menuDespPagto.setText("Relatório de Documentos Fiscais");
        menuDespPagto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDespPagtoActionPerformed(evt);
            }
        });
        jMenu1.add(menuDespPagto);
        jMenu1.add(jSeparator4);

        menuDesoDiario.setText("Despesas Diárias");
        menuDesoDiario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDesoDiarioActionPerformed(evt);
            }
        });
        jMenu1.add(menuDesoDiario);
        jMenu1.add(jSeparator1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Relatórios");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jm_previsaoPagto.setText("Previsão de Pagamentos");
        jm_previsaoPagto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jm_previsaoPagtoActionPerformed(evt);
            }
        });
        jMenu2.add(jm_previsaoPagto);
        jMenu2.add(jSeparator5);

        jm_extrato_cc.setText("Extrato Bancário");
        jm_extrato_cc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jm_extrato_ccActionPerformed(evt);
            }
        });
        jMenu2.add(jm_extrato_cc);
        jMenu2.add(jSeparator6);

        jm_despesa_inventario.setText("Despesas por Inventário");
        jm_despesa_inventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jm_despesa_inventarioActionPerformed(evt);
            }
        });
        jMenu2.add(jm_despesa_inventario);
        jMenu2.add(jSeparator7);

        jm_exportarContas.setText("Exportar Plano de Contas");
        jm_exportarContas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jm_exportarContasActionPerformed(evt);
            }
        });
        jMenu2.add(jm_exportarContas);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        setBounds(0, 0, 1366, 669);
    }// </editor-fold>//GEN-END:initComponents

    private void menuDespFazendaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_menuDespFazendaItemStateChanged
        getMenuSelected();
    }//GEN-LAST:event_menuDespFazendaItemStateChanged

    private void menuDespPlanoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_menuDespPlanoItemStateChanged
        getMenuSelected();
    }//GEN-LAST:event_menuDespPlanoItemStateChanged

    private void btn_pesqPlanoContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesqPlanoContaActionPerformed
        popularBeans();
        SQLPlanoContasCompleto();
        RelatoriosD.relatorioPlanoContas(TbPlanoContas, RelatoriosB, SQLPlanoContas());
        RelatoriosD.relatorioContasDeOrigem(TbContaOrigem, RelatoriosB);
        RelatoriosD.relatorioDespesasFazendas(TbFazendas, RelatoriosB);

    }//GEN-LAST:event_btn_pesqPlanoContaActionPerformed

    private void btn_pesqPagtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesqPagtoActionPerformed
        try {
            RelatoriosB.setDtInicial(new SimpleDateFormat("dd/MM/yyyy").format(txt_dtInicialPagto.getDate()));
            RelatoriosB.setDtFinal(new SimpleDateFormat("dd/MM/yyyy").format(txt_dtFinalPagto.getDate()));
            RelatoriosB.setSQLPagto(getSQL());
            RelatoriosD.relatorioPagamentos(TbPagamentos, RelatoriosB);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Selecione um intervalo data válida!", "Erro", 0, FormatarICO.ICObtnSair());
        }


    }//GEN-LAST:event_btn_pesqPagtoActionPerformed

    private void menuDespPagtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDespPagtoActionPerformed
        if (menuDespPagto.isSelected()) {
            if (frmPagamentos == null) {
                frmPagamentos = new JInternalFrame();
            }
            this.add(frmPagamentos);
            try {
                frmPagamentos.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(frmRelPagamento.class.getName()).log(Level.SEVERE, null, ex);
            }
            frmPagamentos.setVisible(true);
        } else {
            frmPagamentos.setVisible(false);
        }
    }//GEN-LAST:event_menuDespPagtoActionPerformed

    private void menuDespPlanoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDespPlanoActionPerformed
        if (menuDespPlano.isSelected()) {
            if (frmPlanoContas == null) {
                frmPlanoContas = new JInternalFrame();
            }
            this.add(frmPlanoContas);
            frmPlanoContas.setVisible(true);
        } else {
            frmPlanoContas.setVisible(false);
        }
    }//GEN-LAST:event_menuDespPlanoActionPerformed

    private void jPanel6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel6FocusLost
        /* lst_fazenda.setSize(116, 20);
        sc_lstFazenda.setSize(116,20); 
        jPanel6.setSize(jPanel6.getWidth(), 49); */
    }//GEN-LAST:event_jPanel6FocusLost

    private void jm_previsaoPagtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jm_previsaoPagtoActionPerformed
      /*  if (PrevisaoPagto == null) {
            PrevisaoPagto = new frmPrevisaoPagto(null, true);
        }
        PrevisaoPagto.setLocationRelativeTo(null);
        PrevisaoPagto.setVisible(true);*/
        gerarRelatorioPrevisaoPagtos();
    }//GEN-LAST:event_jm_previsaoPagtoActionPerformed

    private void tb_planoContasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_planoContasMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            if (jPopMenu.isVisible() == true) {
                jPopMenu.setVisible(false);
            } else {
                jPopMenu.setVisible(true);
                jPopMenu.show(this, 0, 0);
                jPopMenu.setLocation(evt.getLocationOnScreen());
            }
        }
    }//GEN-LAST:event_tb_planoContasMouseClicked

    private void tb_nfPagtosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_nfPagtosMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            if (jPopMenu.isVisible() == true) {
                jPopMenu.setVisible(false);
            } else {
                jPopMenu.setVisible(true);
                jPopMenu.show(this, 0, 0);
                jPopMenu.setLocation(evt.getLocationOnScreen());

            }
        }
    }//GEN-LAST:event_tb_nfPagtosMouseClicked

    private void jMenuEditarPagtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuEditarPagtoActionPerformed
        retornarFrmPagtos(IDPagto());
    }//GEN-LAST:event_jMenuEditarPagtoActionPerformed

    private void tb_nfPagtosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_nfPagtosMousePressed
        TipTextNotasFiscais();
    }//GEN-LAST:event_tb_nfPagtosMousePressed

    private void jm_extrato_ccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jm_extrato_ccActionPerformed

        if (GerarExtrato == null) {
            GerarExtrato = new frmGerarExtrato_CC(null, true);
        }
        GerarExtrato.setLocationRelativeTo(null);
        GerarExtrato.setVisible(true);
    }//GEN-LAST:event_jm_extrato_ccActionPerformed

    private void txt_contaInicialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_contaInicialFocusLost
        try {
            int conta = Integer.parseInt(txt_contaInicial.getText());
            if (verificarPlanoConta(conta)) {
            } else {
                txt_contaInicial.setText("");
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Conta não localizada!", "Erro", 0, FormatarICO.ICObtnSair());
        }

    }//GEN-LAST:event_txt_contaInicialFocusLost

    private void txt_contaFinalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_contaFinalFocusLost
        try {
            int conta = Integer.parseInt(txt_contaFinal.getText());
            if (verificarPlanoConta(conta)) {
            } else {
                txt_contaFinal.setText("");
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Conta não localizada!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }//GEN-LAST:event_txt_contaFinalFocusLost

    private void txt_nFrotaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_nFrotaFocusLost
        String nFrota = txt_nFrota.getText();
        if (!nFrota.equals("")) {
            if (getIdFrota(nFrota) == 0) {
                txt_nFrota.setText("");
            }
        }
    }//GEN-LAST:event_txt_nFrotaFocusLost

    private void jm_despesa_inventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jm_despesa_inventarioActionPerformed
        if (frmDespesasInvent == null) {
            frmDespesasInvent = new frmDespesasInventario(null, true);
        }
        frmDespesasInvent.setLocationRelativeTo(null);
        frmDespesasInvent.setVisible(true);
    }//GEN-LAST:event_jm_despesa_inventarioActionPerformed

    private void tb_planoContasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_planoContasMousePressed
        TipTextTabelaPlanoContas(tb_planoContas);
    }//GEN-LAST:event_tb_planoContasMousePressed

    private void cb_docItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_docItemStateChanged
        if (cb_doc.getSelectedItem().toString().equals("CPF")) {
            txt_cpf.setValue(null);
            txt_cpf.setFormatterFactory(new DefaultFormatterFactory(CPFMask));
        } else if (cb_doc.getSelectedItem().toString().equals("CNPJ")) {
            txt_cpf.setValue(null);
            txt_cpf.setFormatterFactory(new DefaultFormatterFactory(CNPJMask));
        } else if (cb_doc.getSelectedItem().toString().equals("-")) {
            txt_cpf.setFormatterFactory(null);
            txt_cpf.setValue("");
        }
    }//GEN-LAST:event_cb_docItemStateChanged

    private void txt_cpfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cpfFocusLost

    }//GEN-LAST:event_txt_cpfFocusLost

    private void btn_pesqFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesqFornecedorActionPerformed
        frmCadFornecedores fornecedor = new frmCadFornecedores();
        this.getParent().add(fornecedor);
        fornecedor.setVisible(true);
        fornecedor.tb_fornecedores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    int index = fornecedor.tb_fornecedores.getSelectedRow();

                    String CNPJ = fornecedor.TbForn.getValueAt(index, 7).toString();
                    if (CNPJ.length() > 14) {
                        cb_doc.setSelectedIndex(2);
                    } else {
                        cb_doc.setSelectedIndex(1);
                    }
                    txt_cpf.setText(CNPJ);
                    fornecedor.dispose();
                }
            }
        });
    }//GEN-LAST:event_btn_pesqFornecedorActionPerformed

    private void cb_formaPagtoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_formaPagtoItemStateChanged


    }//GEN-LAST:event_cb_formaPagtoItemStateChanged

    private void menuDesoDiarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDesoDiarioActionPerformed
        gerarRelatorioDepesasDiarias();
    }//GEN-LAST:event_menuDesoDiarioActionPerformed

    private void jm_exportarContasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jm_exportarContasActionPerformed
        gerarPlanilhaPlanoContas();
    }//GEN-LAST:event_jm_exportarContasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btn_pesqFornecedor;
    javax.swing.JButton btn_pesqPagto;
    javax.swing.JButton btn_pesqPlanoConta;
    javax.swing.JButton btn_pesqPlanoConta1;
    javax.swing.JComboBox<Beans.ContaBancariaBeans> cb_bancoOrigem;
    javax.swing.JComboBox<Beans.ContaBancariaBeans> cb_contaOrigem;
    javax.swing.JComboBox<String> cb_doc;
    javax.swing.JComboBox<Object> cb_fazendaPC;
    javax.swing.JComboBox<String> cb_formaPagto;
    javax.swing.JComboBox<String> cb_tipoDespesa;
    javax.swing.JComboBox<String> cb_tipoDocPagto;
    javax.swing.JCheckBox ch_PlanoCompleto;
    javax.swing.JInternalFrame frmPagamentos;
    javax.swing.JInternalFrame frmPlanoContas;
    javax.swing.JLayeredPane jLayer1;
    javax.swing.JMenuItem jMenuEditarPagto;
    javax.swing.JPanel jPanel6;
    javax.swing.JPopupMenu jPopMenu;
    javax.swing.JMenuItem jm_despesa_inventario;
    javax.swing.JMenuItem jm_exportarContas;
    javax.swing.JMenuItem jm_extrato_cc;
    javax.swing.JMenuItem jm_previsaoPagto;
    javax.swing.JCheckBoxMenuItem menuDesoDiario;
    javax.swing.JCheckBoxMenuItem menuDespFazenda;
    javax.swing.JCheckBoxMenuItem menuDespPagto;
    javax.swing.JCheckBoxMenuItem menuDespPlano;
    javax.swing.JTable tb_contaOrigem;
    javax.swing.JTable tb_despesasFazendas;
    javax.swing.JTable tb_nfPagtos;
    javax.swing.JTable tb_planoContas;
    javax.swing.JTextField txt_contaFinal;
    javax.swing.JTextField txt_contaInicial;
    javax.swing.JFormattedTextField txt_cpf;
    com.toedter.calendar.JDateChooser txt_dtFinalPC;
    com.toedter.calendar.JDateChooser txt_dtFinalPagto;
    com.toedter.calendar.JDateChooser txt_dtInicialPC;
    com.toedter.calendar.JDateChooser txt_dtInicialPagto;
    javax.swing.JTextField txt_favorecidoPagto;
    javax.swing.JTextField txt_nDocPagto;
    javax.swing.JTextField txt_nFrota;
    javax.swing.JTextField txt_valorPagto;
    // End of variables declaration//GEN-END:variables

    private frmPagamentos getFrmPagamentos() {
        if (frmPagtos == null) {
            frmPagtos = new frmPagamentos();
        } else if (frmPagtos.isVisible()) {
            frmPagtos.dispose();
            frmPagtos = new frmPagamentos();
        }
        return frmPagtos;
    }

    private String IDPagto() {
        String IDPagto = "";
        if (tb_planoContas.isShowing()) {
            if (ch_PlanoCompleto.isSelected()) {
                IDPagto = TbPlanoContas.getValueAt(tb_planoContas.getSelectedRow(), 2).toString();
            } else {
                JOptionPane.showMessageDialog(null, "Selecione a opção de plano de contas completo!", "Erro", 0, FormatarICO.ICObtnSair());
            }
        }
        if (tb_nfPagtos.isShowing()) {
            IDPagto = TbPagamentos.getValueAt(tb_nfPagtos.getSelectedRow(), 0).toString();
        }

        return IDPagto;
    }

    private void retornarFrmPagtos(String IdPagto) {
        if (!IdPagto.equals("0")) {
            this.getParent().add(getFrmPagamentos());
            frmPagtos.setVisible(true);
            frmPagtos.txt_idPagto.setText(IdPagto);
            frmPagtos.ch_buscarPagto.doClick();
            frmPagtos.btn_pesquisarPagto.doClick();
            //    frmPagtos.getLinhaSelecionada(); Retirado na nova versão comhibernate não corrigido
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um ID de Pagamento válido!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }

    private void getMenuSelected() {

    }

    private String getSQL() {
        String SQL = "";
        if (!txt_favorecidoPagto.getText().equals("")) {
            SQL += " and ef.fiscal_emissor like '%" + txt_favorecidoPagto.getText() + "%'";
        }
        if (!txt_nDocPagto.getText().equals("")) {
            SQL += " and ef.fiscal_ndoc = " + txt_nDocPagto.getText();
        }
        if (!cb_tipoDocPagto.getSelectedItem().equals("-")) {
            SQL += " and ef.fiscal_tipodoc = '" + cb_tipoDocPagto.getSelectedItem().toString() + "'";
        }
        if (!txt_valorPagto.getText().equals("")) {
            SQL += " and ef.fiscal_valordoc like '%" + txt_valorPagto.getText().replace(",", ".") + "%'";
        }
        if (cb_bancoOrigem.getSelectedIndex() != 0) {
            SQL += " and pg.pagto_idContaOrigem = " + getIDContaOrigem(cb_bancoOrigem);
        }
        if (cb_formaPagto.getSelectedIndex() != 0) {
            SQL += " and pg.pagto_forma = '" + cb_formaPagto.getSelectedItem().toString() + "'";
        }
        if (txt_cpf.getText().length() >= 9) {
            SQL += " and pg.pagto_cpf = '" + txt_cpf.getText() + "'";
        }

        if (cb_tipoDespesa.getSelectedIndex() > 0) {
            SQL += " Having TipoDespesa = '" + cb_tipoDespesa.getSelectedItem().toString() + "'";
        }
        return SQL;
    }

    private void SQLPlanoContasCompleto() {
        String SQL = "";
        String GroupBySQL = "";
        if (ch_PlanoCompleto.isSelected()) {
            SQL = "ec.clas_descricao as DescDespesa, "
                    + "ec.clas_idpagto as IDPagto, "
                    + "ec.clas_fazenda as Fazenda, ";

            GroupBySQL = ", ec.clas_descricao, ec.clas_fazenda, ec.clas_idpagto ";
            tb_planoContas.getColumnModel().getColumn(2).setMaxWidth(70);
            tb_planoContas.getColumnModel().getColumn(2).setMinWidth(50);
            tb_planoContas.getColumnModel().getColumn(3).setMaxWidth(200);
            tb_planoContas.getColumnModel().getColumn(3).setMinWidth(140);
            tb_planoContas.getColumnModel().getColumn(4).setMaxWidth(90);
            tb_planoContas.getColumnModel().getColumn(4).setMinWidth(70);
        } else {
            tb_planoContas.getColumnModel().getColumn(2).setMaxWidth(0);
            tb_planoContas.getColumnModel().getColumn(2).setMinWidth(0);
            tb_planoContas.getColumnModel().getColumn(2).setPreferredWidth(0);
            tb_planoContas.getColumnModel().getColumn(3).setMaxWidth(0);
            tb_planoContas.getColumnModel().getColumn(3).setMinWidth(0);
            tb_planoContas.getColumnModel().getColumn(3).setPreferredWidth(0);
            tb_planoContas.getColumnModel().getColumn(4).setMaxWidth(0);
            tb_planoContas.getColumnModel().getColumn(4).setMinWidth(0);
            tb_planoContas.getColumnModel().getColumn(4).setPreferredWidth(0);
        }
        RelatoriosB.setSQL_Plano_Conta_Completo(SQL);
        RelatoriosB.setGROUPBY_SQL_PlanoConta(GroupBySQL);
    }

    private String SQLPlanoContas() {
        String s = "";
        if (!cb_fazendaPC.getSelectedItem().equals("-")) {
            s += " and ec.clas_fazenda = '" + cb_fazendaPC.getSelectedItem().toString() + "'";
        }
        if (cb_contaOrigem.getSelectedIndex() > 0) {
            s += " and (select pg.pagto_idContaOrigem from pagamentos pg where pg.pagto_id = ec.clas_idpagto) = " + getIDContaOrigem(cb_contaOrigem);
        }
        if (!txt_contaInicial.getText().equals("") && !txt_contaInicial.getText().equals("")) {
            int contaInicial = Integer.parseInt(txt_contaInicial.getText());
            int contaFinal = Integer.parseInt(txt_contaFinal.getText());
            s += " and ec.clas_planoconta between " + contaInicial + " and " + contaFinal;
        }
        if (!txt_nFrota.getText().equals("")) {
            s += " and clas_idinvent = " + getIdFrota(txt_nFrota.getText());
        }
        return s;
    }

    public void popularBeans() {
        RelatoriosB.setDtInicial(getStringDate(txt_dtInicialPC.getDate()));
        RelatoriosB.setDtFinal(getStringDate(txt_dtFinalPC.getDate()));
    }

    private String getStringDate(Date dt) {
        String s = "";
        try {
            s = new SimpleDateFormat("dd/MM/yyyy").format(dt);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Selecione um intervalo de data válido!", "Erro", 0, FormatarICO.ICObtnSair());
        }
        return s;
    }

    private void TipTextNotasFiscais() {
        tb_nfPagtos.setToolTipText(null);
        int[] linha = tb_nfPagtos.getSelectedRows();
        if (linha.length > 1) {
            String texto = "";
            double SomaValorDocumento = 0.0;
            for (int i = 0; i < linha.length; i++) {
                SomaValorDocumento += Corretores.ConverterReaisDouble(TbPagamentos.getValueAt(linha[i], 11).toString());
            }
            texto = "<html><FONT FACE=\"Tahoma\" SIZE=3>"
                    + "Valor Total: <B>" + Corretores.ConverterDoubleReais(SomaValorDocumento) + "</B><br>"
                    + "</FONT></html>";
            ToolTipManager.sharedInstance().setInitialDelay(150);
            ToolTipManager.sharedInstance().setDismissDelay(30000);
            tb_nfPagtos.setToolTipText(texto);
        }
    }

    private void TipTextTabelaPlanoContas(JTable tabela) {
        tabela.setToolTipText(null);
        int[] linha = tabela.getSelectedRows();
        if (linha.length > 1) {
            String texto = "";
            double SomaValor = 0.0;

            for (int i = 0; i < linha.length; i++) {
                SomaValor += Corretores.ConverterReaisDouble((TbPlanoContas.getValueAt(linha[i], 5).toString()));
            }
            texto = "<html><FONT FACE=\"Tahoma\" SIZE=3>"
                    + "Soma dos Valores: <B>" + Corretores.ConverterDoubleReais(SomaValor) + "</B><br>"
                    + "</FONT></html>";
            ToolTipManager.sharedInstance().setInitialDelay(150);
            ToolTipManager.sharedInstance().setDismissDelay(30000);
            tabela.setToolTipText(texto);
        }
    }

    private Integer getIDContaOrigem(JComboBox<ContaBancariaBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return comboBox.getItemAt(comboBox.getSelectedIndex()).getIdConta();
        }
        return 0;
    }

    private Boolean verificarPlanoConta(Integer Conta) {
        for (int i = 0; i < listPlanoContas.size(); i++) {
            if (listPlanoContas.get(i).getConta() == Conta) {
                return true;
            }
        }
        JOptionPane.showMessageDialog(null, "Conta não localizada!", "Erro", 0, FormatarICO.ICObtnSair());
        return false;
    }

    private Integer getIdFrota(String nFrota) {
        for (int i = 0; i < listInventario().size(); i++) {
            if (listInventario.get(i).getnFrota().equals(nFrota)) {
                return listInventario.get(i).getID();
            }
        }
        JOptionPane.showMessageDialog(null, "Nº de Frota não encontrado!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        return 0;
    }

    private void gerarRelatorioPrevisaoPagtos() {
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel File", "xlsx", "XLSX");
        fc.setDialogTitle("Selecione o Local do Arquivo");
        fc.setFileFilter(filter);
        fc.setSelectedFile(new File("Previsão Pagtos Dia " + dataAtual.replace("/", "-")));
        int option = fc.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            String fileName = fc.getSelectedFile().getAbsolutePath();
            frmSeletorDatas seletor = new frmSeletorDatas(null, true);
            seletor.setTitle("Selecione o Intervalo Desejado");
            seletor.getRootPane().setDefaultButton(seletor.btn_gerar);
            seletor.txt_dtFinal.setDate(seletor.txt_dtInicial.getDate());
            seletor.btn_gerar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    dtInicial = seletor.txt_dtInicial.getDate();
                    dtFinal = seletor.txt_dtFinal.getDate();
                    seletor.dispose();
                }
            });
            seletor.setLocationRelativeTo(null);
            seletor.setVisible(true);

            thread = new ThreadLoadProgressBar() {
                @Override
                public void run() {
                    exportarPlanilhaPrevisaoPagtos(fileName, dtInicial, dtFinal, thread.dialog);
                }
            };
        }
    }

    public void exportarPlanilhaPrevisaoPagtos(String fileName, Date dtInicial, Date dtFinal, FrmProgressBar frm) {
        int cData = 0;
        int cFavorecido = 1;
        int cValor = 2;
        int cDescricao = 3;
        int cFazenda = 4;
        int cContaOrigem = 5;
        int cForma = 6;

        try {
            WorkbookSettings conf = new WorkbookSettings();
            conf.setEncoding("ISO-8859-1");
            XSSFWorkbook workbook;

            File caminhoCanonical = Corretores.ValidarArquivoModelo("/src/Arquivos/Planilhas_Modelo/model_previsao_pagamentos.xlsx");
            workbook = new XSSFWorkbook(new FileInputStream(caminhoCanonical));
            XSSFSheet planilha = workbook.getSheetAt(0);
            frm.setString("Realizando Consulta");
            ResultSet rs = RelatoriosD.relatorioPrevisaoPagtos(Corretores.ConverterParaSQL(new SimpleDateFormat("dd/MM/yyyy").format(dtInicial)), Corretores.ConverterParaSQL(new SimpleDateFormat("dd/MM/yyyy").format(dtFinal)));
            int row = 2;
            frm.setString("Transpondo Dados");
            String ContaOrigem = "";
            while (rs.next()) {
                if (!ContaOrigem.equals("") && !ContaOrigem.equals(rs.getString("ContaOrigem"))) {
                    XSSFRow rowDados = planilha.getRow(row);
                    XSSFCell cellFavorecido = rowDados.getCell(cFavorecido);
                    cellFavorecido.setCellValue("Total Conta " + ContaOrigem + ":");

                    row += 1;
                    rowDados = planilha.getRow(row);
                    cellFavorecido = rowDados.getCell(cFavorecido);
                    cellFavorecido.setCellValue("Saldo Bancário D-1:");
                    row += 2;
                }
                XSSFRow rowDados = planilha.getRow(row);
                XSSFCell cellData = rowDados.getCell(cData);
                XSSFCell cellFavorecido = rowDados.getCell(cFavorecido);
                XSSFCell cellValor = rowDados.getCell(cValor);
                XSSFCell cellDescricao = rowDados.getCell(cDescricao);
                XSSFCell cellFazenda = rowDados.getCell(cFazenda);
                XSSFCell cellContaOrigem = rowDados.getCell(cContaOrigem);
                XSSFCell cellForma = rowDados.getCell(cForma);

                cellData.setCellValue(rs.getDate("Data"));
                cellFavorecido.setCellValue(rs.getString("Favorecido"));
                cellValor.setCellValue(rs.getDouble("Valor"));
                cellDescricao.setCellValue(rs.getString("Descricao"));
                cellFazenda.setCellValue(rs.getString("Fazenda"));
                ContaOrigem = rs.getString("ContaOrigem");
                cellContaOrigem.setCellValue(ContaOrigem);
                cellForma.setCellValue(rs.getString("Forma"));
                row += 1;
                if (rs.isLast()) {
                    rowDados = planilha.getRow(row);
                    cellFavorecido = rowDados.getCell(cFavorecido);
                    cellFavorecido.setCellValue("Total Conta " + ContaOrigem + ":");
                    row += 1;
                    rowDados = planilha.getRow(row);
                    cellFavorecido = rowDados.getCell(cFavorecido);
                    cellFavorecido.setCellValue("Saldo Bancário D-1:");
                }
            }
            rs.close();
            workbook.write(new FileOutputStream(fileName + ".xlsx"));
        } catch (IOException | SQLException ex) {
            Logger.getLogger(frmRelPagamento.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex + " Erro ao Criar Planilha!", "Erro", 1, FormatarICO.ICObtnSair());
        } finally {
            frm.dispose();
        }
        JOptionPane.showMessageDialog(null, "Planilha Criada com Sucesso!", "Executado", 1, FormatarICO.ICObtnOk());
    }

    private void gerarRelatorioDepesasDiarias() {
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel File", "xlsx", "XLSX");
        fc.setDialogTitle("Selecione o Local do Arquivo");
        fc.setFileFilter(filter);
        fc.setSelectedFile(new File("Pagamentos Dia " + dataAtual.replace("/", "-")));
        int option = fc.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            String fileName = fc.getSelectedFile().getAbsolutePath();
            frmSeletorDatas seletor = new frmSeletorDatas(null, true);
            seletor.setTitle("Selecione o Intervalo Desejado");
            seletor.getRootPane().setDefaultButton(seletor.btn_gerar);
            seletor.txt_dtFinal.setDate(seletor.txt_dtInicial.getDate());
            seletor.btn_gerar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    dtInicial = seletor.txt_dtInicial.getDate();
                    dtFinal = seletor.txt_dtFinal.getDate();
                    seletor.dispose();
                }
            });
            seletor.setLocationRelativeTo(null);
            seletor.setVisible(true);

            thread = new ThreadLoadProgressBar() {
                @Override
                public void run() {
                    exportarPlanilhaDespesas(fileName, dtInicial, dtFinal, thread.dialog);
                }
            };
        }
    }

    public void exportarPlanilhaDespesas(String fileName, Date dtInicial, Date dtFinal, FrmProgressBar frm) {
        int cData = 0;
        int cFavorecido = 1;
        int cValor = 2;
        int cDescricao = 3;
        int cFazenda = 4;
        int cContaOrigem = 5;
        int cForma = 6;

        try {
            WorkbookSettings conf = new WorkbookSettings();
            conf.setEncoding("ISO-8859-1");
            XSSFWorkbook workbook;

            File caminhoCanonical = Corretores.ValidarArquivoModelo("/src/Arquivos/Planilhas_Modelo/model_pagamentos_diarios.xlsx");

            workbook = new XSSFWorkbook(new FileInputStream(caminhoCanonical));
            XSSFSheet planilha = workbook.getSheetAt(0);
            frm.setString("Realizando Consulta");
            ResultSet rs = RelatoriosD.relatorioDepesasDiarias(Corretores.ConverterParaSQL(new SimpleDateFormat("dd/MM/yyyy").format(dtInicial)), Corretores.ConverterParaSQL(new SimpleDateFormat("dd/MM/yyyy").format(dtFinal)));
            int row = 2;
            frm.setString("Transpondo Dados");
            while (rs.next()) {
                XSSFRow rowDados = planilha.getRow(row);
                XSSFCell cellData = rowDados.getCell(cData);
                XSSFCell cellFavorecido = rowDados.getCell(cFavorecido);
                XSSFCell cellValor = rowDados.getCell(cValor);
                XSSFCell cellDescricao = rowDados.getCell(cDescricao);
                XSSFCell cellFazenda = rowDados.getCell(cFazenda);
                XSSFCell cellContaOrigem = rowDados.getCell(cContaOrigem);
                XSSFCell cellForma = rowDados.getCell(cForma);

                cellData.setCellValue(rs.getDate("Data"));
                cellFavorecido.setCellValue(rs.getString("Favorecido"));
                cellValor.setCellValue(rs.getDouble("Valor"));
                cellDescricao.setCellValue(rs.getString("Descricao"));
                cellFazenda.setCellValue(rs.getString("Fazenda"));
                cellContaOrigem.setCellValue(rs.getString("ContaOrigem"));
                cellForma.setCellValue(rs.getString("Forma"));
                row += 1;
            }
            rs.close();
            workbook.write(new FileOutputStream(fileName + ".xlsx"));
        } catch (IOException | SQLException ex) {
            Logger.getLogger(frmRelPagamento.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex + " Erro ao Criar Planilha!", "Erro", 1, FormatarICO.ICObtnSair());
        } finally {
            frm.dispose();
        }
        JOptionPane.showMessageDialog(null, "Planilha Criada com Sucesso!", "Executado", 1, FormatarICO.ICObtnOk());
    }

    public void gerarPlanilhaPlanoContas() {
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel File", "xlsx", "XLSX");
        fc.setDialogTitle("Selecione o Local do Arquivo");
        fc.setFileFilter(filter);
        fc.setSelectedFile(new File("Plano de Contas - " + dataAtual.replace("/", "-")));
        int option = fc.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            String fileName = fc.getSelectedFile().getAbsolutePath();
            frmSeletorDatas seletor = new frmSeletorDatas(null, true);
            seletor.setTitle("Selecione o Intervalo Desejado");
            seletor.getRootPane().setDefaultButton(seletor.btn_gerar);
            seletor.txt_dtInicial.setDate(Corretores.PrimeiroDiaMes());
            seletor.txt_dtFinal.setDate(Corretores.UltimoDiaMes());
            seletor.btn_gerar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    dtInicial = seletor.txt_dtInicial.getDate();
                    dtFinal = seletor.txt_dtFinal.getDate();
                    seletor.dispose();
                }
            });
            seletor.setLocationRelativeTo(null);
            seletor.setVisible(true);

            thread = new ThreadLoadProgressBar() {
                @Override
                public void run() {
                    exportarPlanilhaPlanoContas(fileName, dtInicial, dtFinal, thread.dialog);
                }
            };
        }
    }

    public void exportarPlanilhaPlanoContas(String fileName, Date dtInicial, Date dtFinal, FrmProgressBar frm) {
        int cData = 0;
        int cIdPagto = 1;
        int cCategoria = 2;
        int cAtividade = 3;
        int cDescricaoConta = 4;
        int cPlanoConta = 5;
        int cFazenda = 6;
        int cDescricao = 7;
        int cValor = 8;
        int cFavorecido = 9;
        XSSFRow rowDados;
        try {

            File caminhoCanonical = Corretores.ValidarArquivoModelo("/src/Arquivos/Planilhas_Modelo/model_plano_contas.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(caminhoCanonical));
            XSSFSheet planilha = workbook.getSheetAt(1);
            XSSFCreationHelper createHelper = workbook.getCreationHelper();

            XSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));
            cellStyle.setAlignment(HorizontalAlignment.CENTER);

            XSSFCellStyle styleHorizontal = workbook.createCellStyle();
            styleHorizontal.setAlignment(HorizontalAlignment.CENTER);

            frm.setString("Realizando Consulta");
            ResultSet rs = RelatoriosD.exportarPlanoContas(Corretores.ConverterParaSQL(new SimpleDateFormat("dd/MM/yyyy").format(dtInicial)), Corretores.ConverterParaSQL(new SimpleDateFormat("dd/MM/yyyy").format(dtFinal)));

            rs.last();
            int row = 1;
            int nRows = rs.getRow();
            rs.beforeFirst();
            frm.setProgressBar(false, 0, nRows, "Exportando");
            while (rs.next()) {
                frm.setString("Exportando " + row + " de " + nRows);
                frm.setValue(row);
                rowDados = planilha.createRow(row);

                XSSFCell cellData = rowDados.createCell(cData);
                XSSFCell cellIdPagto = rowDados.createCell(cIdPagto);
                XSSFCell cellCategoria = rowDados.createCell(cCategoria);
                XSSFCell cellAtividade = rowDados.createCell(cAtividade);
                XSSFCell cellDescricaoConta = rowDados.createCell(cDescricaoConta);
                XSSFCell cellPlanoConta = rowDados.createCell(cPlanoConta);
                XSSFCell cellFazenda = rowDados.createCell(cFazenda);
                XSSFCell cellDescricao = rowDados.createCell(cDescricao);
                XSSFCell cellValor = rowDados.createCell(cValor);
                XSSFCell cellFavorecido = rowDados.createCell(cFavorecido);

                cellData.setCellStyle(cellStyle);
                cellIdPagto.setCellStyle(styleHorizontal);
                cellCategoria.setCellStyle(styleHorizontal);
                cellAtividade.setCellStyle(styleHorizontal);
                cellDescricaoConta.setCellStyle(styleHorizontal);
                cellPlanoConta.setCellStyle(styleHorizontal);
                cellFazenda.setCellStyle(styleHorizontal);
                cellDescricao.setCellStyle(styleHorizontal);

                cellData.setCellValue(rs.getDate("DataPagto"));
                cellIdPagto.setCellValue(rs.getInt("idPagto"));
                cellCategoria.setCellValue(rs.getString("Categoria"));
                cellAtividade.setCellValue(rs.getString("Atividade"));
                cellDescricaoConta.setCellValue(rs.getString("DescricaoConta"));
                cellPlanoConta.setCellValue(rs.getInt("PlanoCOnta"));
                cellFazenda.setCellValue(rs.getString("Fazenda"));
                cellDescricao.setCellValue(rs.getString("Descricao"));
                cellValor.setCellValue(rs.getDouble("Valor"));
                cellFavorecido.setCellValue(rs.getString("Favorecido"));

                row += 1;
            }

            XSSFTable table = workbook.getTable("tabela1");
            AreaReference intervaloTabela = new AreaReference(new CellReference(0, 0), new CellReference(row - 1, 9));
            table.setCellReferences(intervaloTabela);
            rs.close();
            workbook.write(new FileOutputStream(fileName + ".xlsx"));
        } catch (IOException ex) {
            Logger.getLogger(frmRelPagamento.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex + " Erro ao Criar Planilha!", "Erro", 1, FormatarICO.ICObtnSair());
        } catch (Exception ex) {
            Logger.getLogger(frmRelPagamento.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex + " Erro ao Criar Planilha!", "Erro", 1, FormatarICO.ICObtnSair());
        } finally {
            frm.dispose();
        }
        JOptionPane.showMessageDialog(null, "Planilha Criada com Sucesso!", "Executado", 1, FormatarICO.ICObtnOk());
    }

}
