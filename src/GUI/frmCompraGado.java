package GUI;

import Beans.BancosBeans;
import Beans.CategoriaAnimalBeans;
import Beans.CompradorGadoBeans;
import Beans.FornecedoresBeans;
import Beans.PropriedadesBeans;
import Beans.CompraGadoBeans;
import Controller.CompraGadoController;
import DAO.CompraGadoDAO;
import DAO.DiversasHibernate;
import static GUI.Principal.ListaPropriedades;
import static GUI.Principal.listBancos;
import static GUI.Principal.listCategoriaAnimal;
import static GUI.Principal.listCompradores;
import static GUI.frmLogin.UsuarioLogadoBeans;
import GerarRelatorios.RelatoriosGado;
import Icones.FormatarICO;
import Utilitarios.CentralizarTabela;
import Utilitarios.Conexao;
import Utilitarios.Corretores;
import Utilitarios.ValidarPermissoes;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class frmCompraGado extends javax.swing.JInternalFrame {

    CompraGadoDAO CompraGadoD;
    CompraGadoBeans CompraGadoB;
    CompraGadoController CompraGadoC;
    MaskFormatter FormatoCPF;
    DefaultTableModel TbCompra;
    frmPagamentos frmPagtos;
    CentralizarTabela Centralizar;
    FornecedoresBeans fornecedor;
    MaskFormatter CNPJMask;
    MaskFormatter CPFMask;

    public frmCompraGado() {
        initComponents();
        CompraGadoD = new CompraGadoDAO();
        CompraGadoB = new CompraGadoBeans();
        CompraGadoC = new CompraGadoController();
        TbCompra = (DefaultTableModel) tb_compras.getModel();

        txt_dataCompra.setDate(new Date(System.currentTimeMillis()));
        txt_dataInicial.setDate(Corretores.PrimeiroDiaMes());
        txt_dataFinal.setDate(Corretores.UltimoDiaMes());
        carregarTabela();
        carregarCompradores();
        carregarCategorias();
        carregarFazendas();
        carregarBancos();
        maskFormaterCPF();
    }

    private JTable carregarTabela() {
        Centralizar = new CentralizarTabela();
        Centralizar.centralizarTabela(tb_compras);
        ((DefaultTableCellRenderer) tb_compras.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        return tb_compras;
    }

    private void carregarCompradores() {
        if (listCompradores == null) {
            listCompradores = DiversasHibernate.getListaCompradorgado();
        }
        CompradorGadoBeans b = new CompradorGadoBeans(0, "-");
        cb_comprador.addItem(b);
        cb_compradorPesq.addItem(b);
        for (CompradorGadoBeans list : listCompradores) {
            cb_comprador.addItem(list);
            cb_compradorPesq.addItem(list);
        }
    }

    private void carregarCategorias() {
        if (listCategoriaAnimal == null) {
            listCategoriaAnimal = DiversasHibernate.getListaCategoriaAnimal();
        }
        CategoriaAnimalBeans c = new CategoriaAnimalBeans(0, "-");
        cb_descricao.addItem(c);
        for (CategoriaAnimalBeans cat : listCategoriaAnimal) {
            cb_descricao.addItem(cat);
        }
    }

    private void carregarFazendas() {
        if (ListaPropriedades == null) {
            ListaPropriedades = DiversasHibernate.getListaFazendas();
        }
        cb_destinoCompra.addItem(new PropriedadesBeans(0, "-"));
        cb_destinoPesq.addItem(new PropriedadesBeans(0, "-"));
        for (PropriedadesBeans p : ListaPropriedades) {
            cb_destinoCompra.addItem(p);
            cb_destinoPesq.addItem(p);
        }
    }

    private void carregarBancos() {
        if (listBancos == null) {
            listBancos = DiversasHibernate.getListaBancos();
        }
        cb_banco.addItem(new BancosBeans(0, "-"));
        for (BancosBeans list : listBancos) {
            cb_banco.addItem(list);
        }
    }

    private void maskFormaterCPF() {
        try {
            CNPJMask = new MaskFormatter("##.###.###/####-##");
            CPFMask = new MaskFormatter("###.###.###-##");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popPagamento = new javax.swing.JPopupMenu();
        menuInfoPag = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuRelCompra = new javax.swing.JMenuItem();
        javax.swing.ButtonGroup buttonGroup1 = new javax.swing.ButtonGroup();
        javax.swing.JPanel jPanel3 = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        tb_compras = new javax.swing.JTable();
        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        txt_somaQcab = new javax.swing.JTextField();
        javax.swing.JLabel jLabel17 = new javax.swing.JLabel();
        txt_somaValorT = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txt_valorMedio = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txt_pesoMdestino = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txt_reaisArDest = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txt_SNF = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txt_SGTA = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txt_SMIN = new javax.swing.JTextField();
        org.jdesktop.swingx.JXTaskPane jXTaskPane1 = new org.jdesktop.swingx.JXTaskPane();
        jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        txt_qNF = new javax.swing.JTextField();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        txt_qGTA = new javax.swing.JTextField();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        txt_qMin = new javax.swing.JTextField();
        txt_qCompra = new javax.swing.JTextField();
        javax.swing.JLabel jLabel13 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        txt_dataInicial =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        txt_dataFinal =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel14 = new javax.swing.JLabel();
        txt_fornecedorPesq = new javax.swing.JTextField();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        cb_compradorPesq = new javax.swing.JComboBox<>();
        btn_pesquisar1 = new javax.swing.JButton();
        cb_destinoPesq = new javax.swing.JComboBox<>();
        cb_statusPesq = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel15 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel16 = new javax.swing.JLabel();
        btn_exportar = new javax.swing.JButton();
        ch_dtPagto = new javax.swing.JCheckBox();
        ch_dtCompra = new javax.swing.JCheckBox();
        btn_relatorioCompra = new javax.swing.JButton();
        javax.swing.JLabel jLabel23 = new javax.swing.JLabel();
        cb_formaNegPesq = new javax.swing.JComboBox<>();
        javax.swing.JLabel label14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        txt_ID = new javax.swing.JTextField();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        tp_dados_compra = new org.jdesktop.swingx.JXTaskPane();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        txt_dataCompra =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        cb_destinoCompra = new javax.swing.JComboBox<>();
        javax.swing.JLabel label1 = new javax.swing.JLabel();
        cb_comprador = new javax.swing.JComboBox<>();
        javax.swing.JLabel label = new javax.swing.JLabel();
        cb_descricao = new javax.swing.JComboBox<>();
        javax.swing.JLabel label2 = new javax.swing.JLabel();
        cb_formaNeg = new javax.swing.JComboBox<>();
        tp_dados_animais = new org.jdesktop.swingx.JXTaskPane();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        txt_quant = new javax.swing.JTextField();
        javax.swing.JPanel jPanel6 = new javax.swing.JPanel();
        javax.swing.JLabel label8 = new javax.swing.JLabel();
        txt_pesoUnit = new javax.swing.JTextField();
        txt_pesoOrig = new javax.swing.JTextField();
        javax.swing.JLabel label6 = new javax.swing.JLabel();
        javax.swing.JPanel jPanel7 = new javax.swing.JPanel();
        javax.swing.JLabel label4 = new javax.swing.JLabel();
        txt_precoUnit = new javax.swing.JTextField();
        javax.swing.JLabel label15 = new javax.swing.JLabel();
        txt_valorAr = new javax.swing.JTextField();
        tp_dados_fornecedor = new org.jdesktop.swingx.JXTaskPane();
        javax.swing.JPanel jPanel5 = new javax.swing.JPanel();
        btn_pesqEmissor1 = new javax.swing.JButton();
        cb_tipoCadastro = new javax.swing.JComboBox<>();
        txt_cpf = new javax.swing.JFormattedTextField();
        javax.swing.JLabel label3 = new javax.swing.JLabel();
        txt_fornecedor = new javax.swing.JTextField();
        javax.swing.JLabel label11 = new javax.swing.JLabel();
        cb_banco = new javax.swing.JComboBox<>();
        javax.swing.JLabel label12 = new javax.swing.JLabel();
        txt_agencia = new javax.swing.JTextField();
        javax.swing.JLabel label13 = new javax.swing.JLabel();
        txt_contaCorrente = new javax.swing.JTextField();
        javax.swing.JPanel jPanel8 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        cb_status = new javax.swing.JComboBox<>();
        javax.swing.JLabel label7 = new javax.swing.JLabel();
        txt_valorTotal = new javax.swing.JTextField();
        javax.swing.JLabel label9 = new javax.swing.JLabel();
        txt_valorPago = new javax.swing.JTextField();
        javax.swing.JPanel jPanel9 = new javax.swing.JPanel();
        btn_novo = new javax.swing.JButton();
        btn_Salvar = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();

        menuInfoPag.setText("Informar Pagamento");
        menuInfoPag.setToolTipText("");
        menuInfoPag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuInfoPagActionPerformed(evt);
            }
        });
        popPagamento.add(menuInfoPag);
        popPagamento.add(jSeparator1);

        menuRelCompra.setText("Gerar Relatório da Compra");
        menuRelCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRelCompraActionPerformed(evt);
            }
        });
        popPagamento.add(menuRelCompra);

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Compra de Gado");
        setToolTipText("");
        setPreferredSize(new java.awt.Dimension(1366, 768));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tb_compras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº Ordem", "Data", "Destino", "Comprador", "Descrição", "Forma Neg.", "Quant.", "Preço Unit", "Peso Origem", "R$/@", "Fornecedor", "CPF", "Peso Unit", "Valor Total", "Valor Pago", "Status", "Qt. NF", "Qt. GTA", "Qt. Min", "Banco", "Agência", "Conta", "R$/@ Destino", "Frete (R$/@)", "kg/unit - Destino", "% Quebra", "Km Est", "Km Médio", "Data Pagto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_compras.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tb_compras.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tb_compras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_comprasMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tb_comprasMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tb_compras);
        if (tb_compras.getColumnModel().getColumnCount() > 0) {
            tb_compras.getColumnModel().getColumn(0).setMinWidth(50);
            tb_compras.getColumnModel().getColumn(0).setPreferredWidth(50);
            tb_compras.getColumnModel().getColumn(0).setMaxWidth(50);
            tb_compras.getColumnModel().getColumn(1).setMinWidth(80);
            tb_compras.getColumnModel().getColumn(1).setPreferredWidth(80);
            tb_compras.getColumnModel().getColumn(1).setMaxWidth(90);
            tb_compras.getColumnModel().getColumn(3).setMinWidth(80);
            tb_compras.getColumnModel().getColumn(3).setPreferredWidth(80);
            tb_compras.getColumnModel().getColumn(3).setMaxWidth(100);
            tb_compras.getColumnModel().getColumn(4).setMinWidth(0);
            tb_compras.getColumnModel().getColumn(4).setPreferredWidth(0);
            tb_compras.getColumnModel().getColumn(4).setMaxWidth(0);
            tb_compras.getColumnModel().getColumn(5).setMinWidth(70);
            tb_compras.getColumnModel().getColumn(5).setPreferredWidth(70);
            tb_compras.getColumnModel().getColumn(5).setMaxWidth(90);
            tb_compras.getColumnModel().getColumn(6).setMinWidth(60);
            tb_compras.getColumnModel().getColumn(6).setPreferredWidth(60);
            tb_compras.getColumnModel().getColumn(6).setMaxWidth(80);
            tb_compras.getColumnModel().getColumn(7).setMinWidth(90);
            tb_compras.getColumnModel().getColumn(7).setPreferredWidth(90);
            tb_compras.getColumnModel().getColumn(7).setMaxWidth(110);
            tb_compras.getColumnModel().getColumn(8).setMinWidth(100);
            tb_compras.getColumnModel().getColumn(8).setPreferredWidth(100);
            tb_compras.getColumnModel().getColumn(8).setMaxWidth(120);
            tb_compras.getColumnModel().getColumn(9).setMinWidth(70);
            tb_compras.getColumnModel().getColumn(9).setPreferredWidth(70);
            tb_compras.getColumnModel().getColumn(9).setMaxWidth(90);
            tb_compras.getColumnModel().getColumn(10).setMinWidth(150);
            tb_compras.getColumnModel().getColumn(10).setPreferredWidth(150);
            tb_compras.getColumnModel().getColumn(10).setMaxWidth(180);
            tb_compras.getColumnModel().getColumn(11).setMinWidth(0);
            tb_compras.getColumnModel().getColumn(11).setPreferredWidth(0);
            tb_compras.getColumnModel().getColumn(11).setMaxWidth(0);
            tb_compras.getColumnModel().getColumn(12).setMinWidth(70);
            tb_compras.getColumnModel().getColumn(12).setPreferredWidth(70);
            tb_compras.getColumnModel().getColumn(12).setMaxWidth(90);
            tb_compras.getColumnModel().getColumn(13).setMinWidth(100);
            tb_compras.getColumnModel().getColumn(13).setPreferredWidth(100);
            tb_compras.getColumnModel().getColumn(13).setMaxWidth(120);
            tb_compras.getColumnModel().getColumn(14).setMinWidth(100);
            tb_compras.getColumnModel().getColumn(14).setPreferredWidth(100);
            tb_compras.getColumnModel().getColumn(14).setMaxWidth(120);
            tb_compras.getColumnModel().getColumn(15).setMinWidth(90);
            tb_compras.getColumnModel().getColumn(15).setPreferredWidth(90);
            tb_compras.getColumnModel().getColumn(15).setMaxWidth(110);
            tb_compras.getColumnModel().getColumn(16).setMinWidth(60);
            tb_compras.getColumnModel().getColumn(16).setPreferredWidth(60);
            tb_compras.getColumnModel().getColumn(16).setMaxWidth(60);
            tb_compras.getColumnModel().getColumn(17).setMinWidth(60);
            tb_compras.getColumnModel().getColumn(17).setPreferredWidth(60);
            tb_compras.getColumnModel().getColumn(17).setMaxWidth(60);
            tb_compras.getColumnModel().getColumn(18).setMinWidth(60);
            tb_compras.getColumnModel().getColumn(18).setPreferredWidth(60);
            tb_compras.getColumnModel().getColumn(18).setMaxWidth(60);
            tb_compras.getColumnModel().getColumn(19).setMinWidth(0);
            tb_compras.getColumnModel().getColumn(19).setPreferredWidth(0);
            tb_compras.getColumnModel().getColumn(19).setMaxWidth(0);
            tb_compras.getColumnModel().getColumn(20).setMinWidth(0);
            tb_compras.getColumnModel().getColumn(20).setPreferredWidth(0);
            tb_compras.getColumnModel().getColumn(20).setMaxWidth(0);
            tb_compras.getColumnModel().getColumn(21).setMinWidth(0);
            tb_compras.getColumnModel().getColumn(21).setPreferredWidth(0);
            tb_compras.getColumnModel().getColumn(21).setMaxWidth(0);
            tb_compras.getColumnModel().getColumn(22).setMinWidth(90);
            tb_compras.getColumnModel().getColumn(22).setPreferredWidth(90);
            tb_compras.getColumnModel().getColumn(22).setMaxWidth(110);
            tb_compras.getColumnModel().getColumn(24).setMinWidth(100);
            tb_compras.getColumnModel().getColumn(24).setPreferredWidth(100);
            tb_compras.getColumnModel().getColumn(24).setMaxWidth(120);
            tb_compras.getColumnModel().getColumn(28).setMinWidth(80);
            tb_compras.getColumnModel().getColumn(28).setPreferredWidth(80);
            tb_compras.getColumnModel().getColumn(28).setMaxWidth(90);
        }

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_somaQcab.setEditable(false);
        txt_somaQcab.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Cabeças");

        txt_somaValorT.setEditable(false);
        txt_somaValorT.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Valor Pago");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("R$/ cab");

        txt_valorMedio.setEditable(false);
        txt_valorMedio.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Peso Chegada");

        txt_pesoMdestino.setEditable(false);
        txt_pesoMdestino.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("R$/@ Dest.");

        txt_reaisArDest.setEditable(false);
        txt_reaisArDest.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Q. NF");

        txt_SNF.setEditable(false);
        txt_SNF.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Q. GTA");

        txt_SGTA.setEditable(false);
        txt_SGTA.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("Q. MIN");

        txt_SMIN.setEditable(false);
        txt_SMIN.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_somaQcab, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_somaValorT, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_valorMedio, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_pesoMdestino, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_reaisArDest, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_SNF, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_SGTA, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, Short.MAX_VALUE)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_SMIN, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(txt_SMIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24)
                        .addComponent(txt_SGTA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22)
                        .addComponent(txt_SNF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21)
                        .addComponent(txt_reaisArDest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(txt_pesoMdestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_somaQcab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17)
                        .addComponent(txt_somaValorT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18)
                        .addComponent(jLabel19)
                        .addComponent(txt_valorMedio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jXTaskPane1.setTitle("Opções de Pesquisa");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("NF");

        txt_qNF.setEditable(false);
        txt_qNF.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_qNF.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("GTA");

        txt_qGTA.setEditable(false);
        txt_qGTA.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_qGTA.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Minuta");

        txt_qMin.setEditable(false);
        txt_qMin.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_qMin.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txt_qCompra.setEditable(false);
        txt_qCompra.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_qCompra.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Compra");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Data Inicial");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Produtor");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Comprador");

        btn_pesquisar1.setBackground(new java.awt.Color(255, 255, 255));
        btn_pesquisar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar.png"))); // NOI18N
        btn_pesquisar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisar1ActionPerformed(evt);
            }
        });

        cb_statusPesq.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Em Aberto", "Encerrada" }));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Destino");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Status");

        btn_exportar.setText("Exportar");
        btn_exportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exportarActionPerformed(evt);
            }
        });

        buttonGroup1.add(ch_dtPagto);
        ch_dtPagto.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_dtPagto.setText("Data Pagto");

        buttonGroup1.add(ch_dtCompra);
        ch_dtCompra.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_dtCompra.setSelected(true);
        ch_dtCompra.setText("Data Compra");

        btn_relatorioCompra.setText("Gerar Relatório");
        btn_relatorioCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_relatorioCompraActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("a");

        cb_formaNegPesq.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Peso", "Cabeça" }));
        cb_formaNegPesq.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_formaNegPesqItemStateChanged(evt);
            }
        });

        label14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label14.setText("Negociação");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_qCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_qNF, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_qGTA, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_qMin, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_relatorioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_exportar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(label14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_formaNegPesq, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel12)
                                .addGap(5, 5, 5)
                                .addComponent(cb_compradorPesq, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_destinoPesq, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_statusPesq, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_dataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_dataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ch_dtCompra)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ch_dtPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_fornecedorPesq)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_pesquisar1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txt_fornecedorPesq, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(ch_dtPagto)
                            .addComponent(ch_dtCompra)
                            .addComponent(txt_dataFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23)
                            .addComponent(txt_dataInicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(cb_statusPesq, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(cb_destinoPesq, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(cb_compradorPesq, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(cb_formaNegPesq, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label14)))
                    .addComponent(btn_pesquisar1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_exportar)
                    .addComponent(btn_relatorioCompra)
                    .addComponent(txt_qMin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txt_qGTA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txt_qNF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txt_qCompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap())
        );

        jXTaskPane1.getContentPane().add(jPanel1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jXTaskPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jXTaskPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jScrollPane2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setAutoscrolls(true);

        txt_ID.setEditable(false);
        txt_ID.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Nº Ordem");

        tp_dados_compra.setTitle("Dados da Compra");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Data da Compra");
        tp_dados_compra.getContentPane().add(jLabel2);

        txt_dataCompra.setEnabled(false);
        tp_dados_compra.getContentPane().add(txt_dataCompra);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Destino");
        tp_dados_compra.getContentPane().add(jLabel3);

        cb_destinoCompra.setEnabled(false);
        tp_dados_compra.getContentPane().add(cb_destinoCompra);

        label1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label1.setText("Comprador");
        tp_dados_compra.getContentPane().add(label1);

        cb_comprador.setEnabled(false);
        tp_dados_compra.getContentPane().add(cb_comprador);

        label.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label.setText("Descrição");
        tp_dados_compra.getContentPane().add(label);

        cb_descricao.setEnabled(false);
        tp_dados_compra.getContentPane().add(cb_descricao);

        label2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label2.setText("Negociação");
        tp_dados_compra.getContentPane().add(label2);

        cb_formaNeg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Peso", "Cabeça" }));
        cb_formaNeg.setEnabled(false);
        cb_formaNeg.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_formaNegItemStateChanged(evt);
            }
        });
        tp_dados_compra.getContentPane().add(cb_formaNeg);

        tp_dados_animais.setExpanded(false);
        tp_dados_animais.setTitle("Dados dos Animais");
        tp_dados_animais.setAnimated(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Quant.");
        tp_dados_animais.getContentPane().add(jLabel4);

        txt_quant.setEnabled(false);
        txt_quant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_quantKeyReleased(evt);
            }
        });
        tp_dados_animais.getContentPane().add(txt_quant);

        label8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label8.setText("Peso Unit.");

        txt_pesoUnit.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_pesoUnit.setText("0 kg");
        txt_pesoUnit.setEnabled(false);
        txt_pesoUnit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_pesoUnitFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_pesoUnitFocusLost(evt);
            }
        });
        txt_pesoUnit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_pesoUnitKeyReleased(evt);
            }
        });

        txt_pesoOrig.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_pesoOrig.setText("0 kg");
        txt_pesoOrig.setEnabled(false);
        txt_pesoOrig.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_pesoOrigFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_pesoOrigFocusLost(evt);
            }
        });
        txt_pesoOrig.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_pesoOrigKeyReleased(evt);
            }
        });

        label6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label6.setText("Peso de Origem");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_pesoOrig)
                    .addComponent(label6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_pesoUnit)
                    .addComponent(label8, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(label6)
                        .addGap(0, 0, 0)
                        .addComponent(txt_pesoOrig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(label8)
                        .addGap(0, 0, 0)
                        .addComponent(txt_pesoUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );

        tp_dados_animais.getContentPane().add(jPanel6);

        label4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label4.setText("Preço Unit.");

        txt_precoUnit.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_precoUnit.setText("R$ 0,00");
        txt_precoUnit.setEnabled(false);
        txt_precoUnit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_precoUnitFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_precoUnitFocusLost(evt);
            }
        });
        txt_precoUnit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_precoUnitKeyReleased(evt);
            }
        });

        label15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label15.setText("Valor R$/@");

        txt_valorAr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_valorAr.setText("R$ 0,00");
        txt_valorAr.setEnabled(false);
        txt_valorAr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_valorArFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_valorArFocusLost(evt);
            }
        });
        txt_valorAr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_valorArKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_precoUnit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label15, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addComponent(txt_valorAr)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label4)
                    .addComponent(label15))
                .addGap(0, 0, 0)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_precoUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_valorAr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        tp_dados_animais.getContentPane().add(jPanel7);

        tp_dados_fornecedor.setExpanded(false);
        tp_dados_fornecedor.setTitle("Dados do Fornecedor");
        tp_dados_fornecedor.setAnimated(false);

        btn_pesqEmissor1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar pequeno_1.png"))); // NOI18N
        btn_pesqEmissor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesqEmissor1ActionPerformed(evt);
            }
        });

        cb_tipoCadastro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "CPF", "CNPJ" }));
        cb_tipoCadastro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_tipoCadastroItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(cb_tipoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txt_cpf)
                .addGap(0, 0, 0)
                .addComponent(btn_pesqEmissor1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cb_tipoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesqEmissor1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txt_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tp_dados_fornecedor.getContentPane().add(jPanel5);

        label3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label3.setText("Fornecedor");
        tp_dados_fornecedor.getContentPane().add(label3);

        txt_fornecedor.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_fornecedor.setEnabled(false);
        tp_dados_fornecedor.getContentPane().add(txt_fornecedor);

        label11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label11.setText("Banco");
        tp_dados_fornecedor.getContentPane().add(label11);

        cb_banco.setEnabled(false);
        tp_dados_fornecedor.getContentPane().add(cb_banco);

        label12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label12.setText("Agência");
        tp_dados_fornecedor.getContentPane().add(label12);

        txt_agencia.setEnabled(false);
        tp_dados_fornecedor.getContentPane().add(txt_agencia);

        label13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label13.setText("Conta Corrente");
        tp_dados_fornecedor.getContentPane().add(label13);

        txt_contaCorrente.setEnabled(false);
        tp_dados_fornecedor.getContentPane().add(txt_contaCorrente);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Status da Compra");

        cb_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Em Aberto", "Encerrada" }));
        cb_status.setEnabled(false);

        label7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label7.setText("Valor Total");

        txt_valorTotal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_valorTotal.setForeground(new java.awt.Color(255, 0, 0));
        txt_valorTotal.setText("R$ 0,00");
        txt_valorTotal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_valorTotalFocusLost(evt);
            }
        });

        label9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label9.setText("Valor Pago");

        txt_valorPago.setEditable(false);
        txt_valorPago.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_valorPago.setForeground(new java.awt.Color(0, 0, 204));
        txt_valorPago.setText("R$ 0,00");

        btn_novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/inserir-32.png"))); // NOI18N
        btn_novo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_novo.setMaximumSize(new java.awt.Dimension(65, 65));
        btn_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novoActionPerformed(evt);
            }
        });

        btn_Salvar.setBackground(new java.awt.Color(255, 255, 255));
        btn_Salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/salvar_32_32.png"))); // NOI18N
        btn_Salvar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_Salvar.setEnabled(false);
        btn_Salvar.setMaximumSize(new java.awt.Dimension(65, 65));
        btn_Salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalvarActionPerformed(evt);
            }
        });

        btn_editar.setBackground(new java.awt.Color(255, 255, 255));
        btn_editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/editar_32_32.png"))); // NOI18N
        btn_editar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_editar.setEnabled(false);
        btn_editar.setMaximumSize(new java.awt.Dimension(65, 65));
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });

        btn_excluir.setBackground(new java.awt.Color(255, 255, 255));
        btn_excluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/deletar_32_32.png"))); // NOI18N
        btn_excluir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_excluir.setEnabled(false);
        btn_excluir.setMaximumSize(new java.awt.Dimension(65, 65));
        btn_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(btn_Salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(7, 7, 7)
                .addComponent(cb_status, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(label7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_valorTotal))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(label9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_valorPago))
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5)
                    .addComponent(cb_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_valorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_valorPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tp_dados_compra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tp_dados_fornecedor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tp_dados_animais, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(15, 15, 15))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tp_dados_compra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tp_dados_animais, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tp_dados_fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        setBounds(0, 0, 1324, 576);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_SalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalvarActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(null, "Deseja Salvar esta Compra de Gado?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION && ValidarPermissoes.validarPermissaoInsert(frmCompraGado.class.getSimpleName())) {
            popularBeans(CompraGadoB);
            if (CompraGadoC.inserirCompra(CompraGadoB)) {
                limparDados();
                DesabilitarCampos();
            }
        }
    }//GEN-LAST:event_btn_SalvarActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja Salvar esta Compra de Gado?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION && ValidarPermissoes.validarPermissaoUpdate(frmCompraGado.class.getSimpleName())) {
            popularBeans(CompraGadoB);
            if (CompraGadoC.editarCompra(CompraGadoB)) {
                limparDados();
                DesabilitarCampos();
            }
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        int excluir = JOptionPane.showConfirmDialog(null, "Deseja EXCLUIR esta Compra de Gado, os dados serão apagados permanentemente?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (excluir == JOptionPane.YES_OPTION && ValidarPermissoes.validarPermissaoDelete(frmCompraGado.class.getSimpleName())) {

        }
    }//GEN-LAST:event_btn_excluirActionPerformed

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed
        habilitarCampos();
        limparDados();
        CompraGadoB = new CompraGadoBeans();
        btn_Salvar.setEnabled(true);
        btn_excluir.setEnabled(false);
        btn_editar.setEnabled(false);
        tp_dados_animais.setAnimated(true);
        tp_dados_fornecedor.setAnimated(true);
    }//GEN-LAST:event_btn_novoActionPerformed

    private void cb_formaNegItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_formaNegItemStateChanged
        if (cb_formaNeg.getSelectedItem().equals("Cabeça")) {
            txt_pesoOrig.setEnabled(false);
            txt_pesoUnit.setEnabled(false);
            txt_valorAr.setEnabled(false);
            txt_precoUnit.setEnabled(true);
            txt_pesoOrig.setText("0");
            txt_pesoUnit.setText("0");
            txt_valorAr.setText("0");
        } else if (cb_formaNeg.getSelectedItem().equals("Peso")) {
            txt_pesoOrig.setEnabled(true);
            txt_pesoUnit.setEnabled(true);
            txt_valorAr.setEnabled(true);
            txt_precoUnit.setEnabled(false);
            txt_precoUnit.setText("0");
        } else {
            txt_precoUnit.setText("0");
            txt_pesoOrig.setText("0");
            txt_pesoUnit.setText("0");
            txt_valorAr.setText("0");
            txt_pesoOrig.setEnabled(false);
            txt_pesoUnit.setEnabled(false);
            txt_valorAr.setEnabled(false);
            txt_precoUnit.setEnabled(false);
        }
    }//GEN-LAST:event_cb_formaNegItemStateChanged

    private void txt_precoUnitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_precoUnitKeyReleased
        atualizarValorTotal();
    }//GEN-LAST:event_txt_precoUnitKeyReleased

    private void tb_comprasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_comprasMouseClicked
        int rowIndex = tb_compras.getSelectedRow();
        if (evt.getButton() == MouseEvent.BUTTON3) {
            if (popPagamento.isVisible() == true) {
                popPagamento.setVisible(false);
            } else {
                popPagamento.setVisible(true);
                popPagamento.show(this, 0, 0);
                popPagamento.setLocation(evt.getLocationOnScreen());
                VerificarStatusCompra(rowIndex);
            }
        } else if (evt.getClickCount() == 2) {
            editarCompra();
        }
        preencherQuantidades(rowIndex);
    }//GEN-LAST:event_tb_comprasMouseClicked

    private void tb_comprasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_comprasMousePressed
        // preencherDadosCompra();
        btn_editar.setEnabled(false);
        btn_excluir.setEnabled(false);
        btn_Salvar.setEnabled(false);
        DesabilitarCampos();
        TipTextTbCompras();
    }//GEN-LAST:event_tb_comprasMousePressed

    private void menuInfoPagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuInfoPagActionPerformed
        int rowIndex = tb_compras.getSelectedRow();
        if (rowIndex >= 0) {
            CompraGadoBeans compra = CompraGadoC.buscarCompra((Integer) TbCompra.getValueAt(rowIndex, 0));
            preencherFrmPagtos(compra);
        }
    }//GEN-LAST:event_menuInfoPagActionPerformed

    private void menuRelCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRelCompraActionPerformed
        int confirma = JOptionPane.showConfirmDialog(null, "Deseja visualizar o relatório de compra de gado?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            Integer IDCompra = (Integer) TbCompra.getValueAt(tb_compras.getSelectedRow(), 0);
            RelatoriosGado.imprimirOrdemCompraGado(IDCompra);
        }
    }//GEN-LAST:event_menuRelCompraActionPerformed

    private void btn_exportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exportarActionPerformed

    }//GEN-LAST:event_btn_exportarActionPerformed

    private void btn_pesquisar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisar1ActionPerformed
        if (txt_dataInicial.getDate() != null && txt_dataFinal.getDate() != null && ValidarPermissoes.validarPermissaoSelect(frmCompraGado.class.getSimpleName())) {
            TbCompra.setRowCount(0);
            CompraGadoD.pesquisarCompras(TbCompra, SQLWhere());
            totalizarTabela();
        } else {
            JOptionPane.showMessageDialog(null, "O Intervalo de Data é Obrigatório!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }//GEN-LAST:event_btn_pesquisar1ActionPerformed

    private void btn_relatorioCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_relatorioCompraActionPerformed
        String Comprador = "";
        String Destino = "";
        if (cb_destinoPesq.getSelectedIndex() > 0) {
            Destino = cb_destinoPesq.getSelectedItem().toString();
        }
        if (cb_compradorPesq.getSelectedIndex() > 0) {
            Comprador = cb_compradorPesq.getSelectedItem().toString();
        }
        relatorioGeralCompra(Comprador, Destino);
    }//GEN-LAST:event_btn_relatorioCompraActionPerformed

    private void cb_formaNegPesqItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_formaNegPesqItemStateChanged

    }//GEN-LAST:event_cb_formaNegPesqItemStateChanged

    private void btn_pesqEmissor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesqEmissor1ActionPerformed
        fornecedor = DiversasHibernate.buscarFornecedor(txt_cpf.getText());
        if (fornecedor != null) {
            txt_agencia.setText(fornecedor.getAgencia());
            txt_contaCorrente.setText(fornecedor.getConta());
            txt_fornecedor.setText(fornecedor.getNomeFantasia());
            setComboBoxBanco(cb_banco, fornecedor.getBanco());
        } else {
            int editar = JOptionPane.showConfirmDialog(null, "<html> <B>Fornecedor Não Encontrado,</B> <br> Deseja Cadastrar Novo Fornecedor? </html>", "Atenção", JOptionPane.YES_NO_OPTION);
            if (editar == JOptionPane.YES_OPTION) {
                frmCadFornecedores frmFornecedor = new frmCadFornecedores();
                this.getParent().add(frmFornecedor);
                frmFornecedor.btn_novo1.doClick();
                frmFornecedor.cb_tipoPessoa.setSelectedItem(cb_tipoCadastro.getSelectedItem());
                frmFornecedor.txt_cnpj.setText(txt_cpf.getText());
                frmFornecedor.setVisible(true);
                frmFornecedor.txt_nomeFantasia.requestFocus();
            }
        }
    }//GEN-LAST:event_btn_pesqEmissor1ActionPerformed

    private void txt_pesoOrigFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_pesoOrigFocusGained
        txt_pesoOrig.selectAll();
    }//GEN-LAST:event_txt_pesoOrigFocusGained

    private void txt_pesoUnitFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_pesoUnitFocusGained
        txt_pesoUnit.selectAll();
    }//GEN-LAST:event_txt_pesoUnitFocusGained

    private void txt_precoUnitFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_precoUnitFocusGained
        txt_precoUnit.selectAll();
    }//GEN-LAST:event_txt_precoUnitFocusGained

    private void txt_valorArFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorArFocusGained
        txt_valorAr.selectAll();
    }//GEN-LAST:event_txt_valorArFocusGained

    private void txt_quantKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_quantKeyReleased
        atualizarValorTotal();
    }//GEN-LAST:event_txt_quantKeyReleased

    private void txt_valorArKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_valorArKeyReleased
        atualizarValorTotal();
    }//GEN-LAST:event_txt_valorArKeyReleased

    private void txt_pesoOrigKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_pesoOrigKeyReleased
        atualizarValorTotal();
        double pesoOrig = Corretores.ConverterStringDouble(txt_pesoOrig.getText());
        txt_pesoUnit.setText(Corretores.ConverterParaDecimal((pesoOrig / getInteger(txt_quant.getText())), "Kg"));
    }//GEN-LAST:event_txt_pesoOrigKeyReleased

    private void txt_pesoUnitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_pesoUnitKeyReleased
        atualizarValorTotal();
        double pesoUnit = Corretores.ConverterStringDouble(txt_pesoUnit.getText());
        txt_pesoOrig.setText(Corretores.ConverterParaDecimal((pesoUnit * getInteger(txt_quant.getText())), "Kg"));
    }//GEN-LAST:event_txt_pesoUnitKeyReleased

    private void txt_pesoOrigFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_pesoOrigFocusLost
        txt_pesoOrig.setText(Corretores.ConverterParaDecimal(txt_pesoOrig.getText(), "kg"));
    }//GEN-LAST:event_txt_pesoOrigFocusLost

    private void txt_valorArFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorArFocusLost
        txt_valorAr.setText(Corretores.ConverterDecimalReais(txt_valorAr.getText()));
    }//GEN-LAST:event_txt_valorArFocusLost

    private void txt_precoUnitFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_precoUnitFocusLost
        txt_precoUnit.setText(Corretores.ConverterDecimalReais(txt_precoUnit.getText()));
    }//GEN-LAST:event_txt_precoUnitFocusLost

    private void txt_pesoUnitFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_pesoUnitFocusLost
        txt_pesoUnit.setText(Corretores.ConverterParaDecimal(txt_pesoUnit.getText(), "kg"));
    }//GEN-LAST:event_txt_pesoUnitFocusLost

    private void cb_tipoCadastroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_tipoCadastroItemStateChanged
        txt_cpf.setText(null);
        txt_cpf.setValue(null);
        txt_cpf.setFormatterFactory(null);
        switch (cb_tipoCadastro.getSelectedIndex()) {
            case 0:
                break;
            case 1:
                txt_cpf.setFormatterFactory(new DefaultFormatterFactory(CPFMask));
                break;
            case 2:
                txt_cpf.setFormatterFactory(new DefaultFormatterFactory(CNPJMask));
                break;
            default:
                break;
        }
    }//GEN-LAST:event_cb_tipoCadastroItemStateChanged

    private void txt_valorTotalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorTotalFocusLost
        txt_valorTotal.setText(Corretores.ConverterDecimalReais(txt_valorTotal.getText()));
    }//GEN-LAST:event_txt_valorTotalFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btn_Salvar;
    javax.swing.JButton btn_editar;
    javax.swing.JButton btn_excluir;
    javax.swing.JButton btn_exportar;
    javax.swing.JButton btn_novo;
    javax.swing.JButton btn_pesqEmissor1;
    javax.swing.JButton btn_pesquisar1;
    javax.swing.JButton btn_relatorioCompra;
    javax.swing.JComboBox<BancosBeans> cb_banco;
    javax.swing.JComboBox<CompradorGadoBeans> cb_comprador;
    javax.swing.JComboBox<Beans.CompradorGadoBeans> cb_compradorPesq;
    javax.swing.JComboBox<CategoriaAnimalBeans> cb_descricao;
    public static javax.swing.JComboBox<PropriedadesBeans> cb_destinoCompra;
    public static javax.swing.JComboBox<PropriedadesBeans> cb_destinoPesq;
    javax.swing.JComboBox<String> cb_formaNeg;
    javax.swing.JComboBox<String> cb_formaNegPesq;
    javax.swing.JComboBox<String> cb_status;
    javax.swing.JComboBox<String> cb_statusPesq;
    javax.swing.JComboBox<String> cb_tipoCadastro;
    javax.swing.JCheckBox ch_dtCompra;
    javax.swing.JCheckBox ch_dtPagto;
    javax.swing.JLabel jLabel18;
    javax.swing.JLabel jLabel19;
    javax.swing.JLabel jLabel20;
    javax.swing.JLabel jLabel21;
    javax.swing.JLabel jLabel22;
    javax.swing.JLabel jLabel24;
    javax.swing.JLabel jLabel25;
    javax.swing.JPanel jPanel1;
    javax.swing.JPanel jPanel4;
    javax.swing.JScrollPane jScrollPane2;
    javax.swing.JMenuItem menuInfoPag;
    javax.swing.JMenuItem menuRelCompra;
    javax.swing.JPopupMenu popPagamento;
    static javax.swing.JTable tb_compras;
    org.jdesktop.swingx.JXTaskPane tp_dados_animais;
    org.jdesktop.swingx.JXTaskPane tp_dados_compra;
    org.jdesktop.swingx.JXTaskPane tp_dados_fornecedor;
    javax.swing.JTextField txt_ID;
    javax.swing.JTextField txt_SGTA;
    javax.swing.JTextField txt_SMIN;
    javax.swing.JTextField txt_SNF;
    javax.swing.JTextField txt_agencia;
    javax.swing.JTextField txt_contaCorrente;
    javax.swing.JFormattedTextField txt_cpf;
    com.toedter.calendar.JDateChooser txt_dataCompra;
    com.toedter.calendar.JDateChooser txt_dataFinal;
    com.toedter.calendar.JDateChooser txt_dataInicial;
    javax.swing.JTextField txt_fornecedor;
    javax.swing.JTextField txt_fornecedorPesq;
    javax.swing.JTextField txt_pesoMdestino;
    javax.swing.JTextField txt_pesoOrig;
    javax.swing.JTextField txt_pesoUnit;
    javax.swing.JTextField txt_precoUnit;
    javax.swing.JTextField txt_qCompra;
    javax.swing.JTextField txt_qGTA;
    javax.swing.JTextField txt_qMin;
    javax.swing.JTextField txt_qNF;
    javax.swing.JTextField txt_quant;
    javax.swing.JTextField txt_reaisArDest;
    javax.swing.JTextField txt_somaQcab;
    javax.swing.JTextField txt_somaValorT;
    javax.swing.JTextField txt_valorAr;
    javax.swing.JTextField txt_valorMedio;
    javax.swing.JTextField txt_valorPago;
    javax.swing.JTextField txt_valorTotal;
    // End of variables declaration//GEN-END:variables

    private void preencherFrmPagtos(CompraGadoBeans compra) {
        frmPagamentos frmPagto = new frmPagamentos();
        this.getParent().add(frmPagto);
        frmPagto.tp_nf.setExpanded(false);
        frmPagto.tp_clas.setExpanded(true);
        frmPagto.setVisible(true);
        frmPagto.ch_novoPagto.doClick();
        frmPagto.jTextNCompra();
        frmPagto.txt_nCompra.setText(compra.getID().toString());
        frmPagto.txt_nCompra.setVisible(true);
        frmPagto.ch_previsto.setSelected(true);
        frmPagto.txt_valorPagtoPrevisto.setText(Corretores.ConverterDoubleReais(compra.getValorT()));
        frmPagto.nCompraGado = compra;
        frmPagto.cb_doc.setSelectedItem((compra.getCpf().length() > 14 ? "CNPJ" : "CPF"));
        frmPagto.txt_cpf.setText(compra.getCpf());
        frmPagto.setComboFazenda(frmPagto.cb_fazendaClas, compra.getFazendaDestino().getCodigo());
        frmPagto.txt_valorClass.setText(Corretores.ConverterDoubleReais(compra.getValorT()));
        frmPagto.txt_descricaoClass.setText("Compra de " + compra.getQuant().toString() + " " + compra.getDescricao() + " - " + compra.getComprador());

    }

    private Integer getInteger(String str) {
        try {
            return new Integer(str);
        } catch (Exception e) {
            return null;
        }
    }

    private Boolean getStatus() {
        if (cb_status.getSelectedIndex() == 0) {
            return false;
        } else {
            return true;
        }
    }

    private void setSatus(Boolean b) {
        if (b == false) {
            cb_status.setSelectedIndex(0);
        } else {
            cb_status.setSelectedIndex(1);
        }
    }

    private void popularBeans(CompraGadoBeans compra) {
        compra.setAgencia(txt_agencia.getText());
        compra.setBanco(cb_banco.getSelectedItem().toString());
        compra.setComprador(cb_comprador.getSelectedItem().toString());
        compra.setCompradorB(getComprador(cb_comprador));
        compra.setConta(txt_contaCorrente.getText());
        compra.setCpf(txt_cpf.getText());
        compra.setData(txt_dataCompra.getDate());
        compra.setDescricao(cb_descricao.getSelectedItem().toString());
        compra.setCategoriaAniaml(getCategoriaAnimal(cb_descricao));
        compra.setDtLan(new Date(System.currentTimeMillis()));
        compra.setFazendaDestino(getFazenda(cb_destinoCompra));
        compra.setDestino(getFazenda(cb_destinoCompra).getNome());
        compra.setFornecedor(txt_fornecedor.getText());
        compra.setNegociacao(cb_formaNeg.getSelectedItem().toString());
        compra.setQuant(getInteger(txt_quant.getText()));
        compra.setPesoOrigem(Corretores.ConverterStringDouble(txt_pesoOrig.getText()));
        compra.setPesoUnit(Corretores.ConverterStringDouble(txt_pesoUnit.getText()));
        compra.setReaisArr(Corretores.ConverterStringDouble(txt_valorAr.getText()));
        compra.setValorUnit(Corretores.ConverterStringDouble(txt_precoUnit.getText()));
        compra.setStatus(cb_status.getSelectedItem().toString());
        compra.setStatusB(getStatus());
        compra.setUser(UsuarioLogadoBeans.getLogin());
        compra.setValorT(Corretores.ConverterStringDouble(txt_valorTotal.getText()));

    }

    private void habilitarCampos() {
        txt_ID.setEnabled(false);
        txt_dataCompra.setEnabled(true);
        cb_destinoCompra.setEnabled(true);
        cb_comprador.setEnabled(true);
        cb_descricao.setEnabled(true);
        cb_formaNeg.setEnabled(true);
        txt_quant.setEnabled(true);
        txt_fornecedor.setEnabled(true);
        txt_cpf.setEnabled(true);
        cb_banco.setEnabled(true);
        txt_agencia.setEnabled(true);
        txt_contaCorrente.setEnabled(true);
        cb_status.setEnabled(true);

    }

    private void DesabilitarCampos() {
        txt_ID.setEnabled(false);
        txt_dataCompra.setEnabled(false);
        cb_destinoCompra.setEnabled(false);
        cb_comprador.setEnabled(false);
        cb_descricao.setEnabled(false);
        cb_formaNeg.setEnabled(false);
        txt_quant.setEnabled(false);
        txt_fornecedor.setEnabled(false);
        txt_cpf.setEnabled(false);
        cb_banco.setEnabled(false);
        txt_agencia.setEnabled(false);
        txt_contaCorrente.setEnabled(false);
        cb_status.setEnabled(false);
        btn_Salvar.setEnabled(false);
    }

    private void preencherQuantidades(int rowIndex) {
        txt_qCompra.setText(TbCompra.getValueAt(rowIndex, 6).toString());
        txt_qNF.setText(TbCompra.getValueAt(rowIndex, 16).toString());
        txt_qGTA.setText(TbCompra.getValueAt(rowIndex, 17).toString());
        txt_qMin.setText(TbCompra.getValueAt(rowIndex, 18).toString());
        atualizarCoresQuantidades();
    }

    private void preencherDadosCompra(CompraGadoBeans compra) {
        txt_ID.setText(compra.getID().toString());
        setComboFazenda(cb_destinoCompra, compra.getFazendaDestino().getCodigo());
        setComboBoxComprador(cb_comprador, compra.getComprador());
        setComboBoxCategoria(cb_descricao, compra.getCategoriaAniaml().getID());
        cb_formaNeg.setSelectedItem(compra.getNegociacao());
        txt_quant.setText(compra.getQuant().toString());
        txt_precoUnit.setText(Corretores.ConverterDoubleReais(compra.getValorUnit()));
        txt_pesoOrig.setText(Corretores.ConverterParaDecimal(compra.getPesoOrigem(), "Kg"));
        txt_valorAr.setText(Corretores.ConverterDoubleReais(compra.getReaisArr()));
        txt_pesoUnit.setText(Corretores.ConverterParaDecimal(compra.getPesoUnit(), "Kg"));
        txt_fornecedor.setText(compra.getFornecedor());
        txt_cpf.setText(compra.getCpf());
        cb_tipoCadastro.setSelectedItem((compra.getCpf().length() > 14 ? "CNPJ" : "CPF"));
        cb_status.setSelectedItem(compra.getStatus());
        txt_valorTotal.setText(Corretores.ConverterDoubleReais(compra.getValorT()));
        txt_valorPago.setText(Corretores.ConverterDoubleReais(compra.getValorPago()));
        setComboBoxBanco(cb_banco, compra.getBanco());
        txt_agencia.setText(compra.getAgencia());
        txt_contaCorrente.setText(compra.getConta());
        txt_dataCompra.setDate(compra.getData());
    }

    private void atualizarCoresQuantidades() {
        if (txt_qCompra.getText().equals(txt_qNF.getText())) {
            txt_qNF.setBackground(new java.awt.Color(0, 153, 0));
        } else {
            txt_qNF.setBackground(new java.awt.Color(255, 0, 0));
        }
        if (txt_qCompra.getText().equals(txt_qGTA.getText())) {
            txt_qGTA.setBackground(new java.awt.Color(0, 153, 0));
        } else {
            txt_qGTA.setBackground(new java.awt.Color(255, 0, 0));
        }
        if (txt_qCompra.getText().equals(txt_qMin.getText())) {
            txt_qMin.setBackground(new java.awt.Color(0, 153, 0));
        } else {
            txt_qMin.setBackground(new java.awt.Color(255, 0, 0));
        }
    }

    private void setComboBoxComprador(JComboBox<CompradorGadoBeans> comboBox, String Comprador) {
        for (int i = 0; i < comboBox.getModel().getSize(); i++) {
            if (comboBox.getModel().getElementAt(i).getComprador().equals(Comprador)) {
                comboBox.setSelectedIndex(i);
            }
        }
    }

    private void setComboBoxComprador(JComboBox<CompradorGadoBeans> comboBox, Integer Comprador) {
        for (int i = 0; i < comboBox.getModel().getSize(); i++) {
            if (comboBox.getModel().getElementAt(i).getIdComprador() == Comprador) {
                comboBox.setSelectedIndex(i);
                break;
            }
        }
    }

    private void setComboBoxBanco(JComboBox<BancosBeans> combobox, String nomeBanco) {
        combobox.setSelectedIndex(0);
        for (int i = 0; i < combobox.getModel().getSize(); i++) {
            if (combobox.getItemAt(i).getNomeBanco().equals(nomeBanco)) {
                combobox.setSelectedIndex(i);
                break;
            }
        }
    }

    private void setComboBoxBanco(JComboBox<BancosBeans> combobox, Integer id) {
        combobox.setSelectedIndex(0);
        for (int i = 0; i < combobox.getModel().getSize(); i++) {
            if (Objects.equals(combobox.getItemAt(i).getIdBanco(), id)) {
                combobox.setSelectedIndex(i);
                break;
            }
        }
    }

    private void setComboBoxCategoria(JComboBox<CategoriaAnimalBeans> combobox, Integer id) {
        combobox.setSelectedIndex(0);
        for (int i = 0; i < combobox.getModel().getSize(); i++) {
            if (Objects.equals(combobox.getItemAt(i).getID(), id)) {
                combobox.setSelectedIndex(i);
                break;
            }
        }
    }

    private void setComboFazenda(JComboBox<PropriedadesBeans> combobox, Integer idFazenda) {
        combobox.setSelectedIndex(0);
        for (int i = 0; i < combobox.getModel().getSize(); i++) {
            if (combobox.getItemAt(i).getCodigo() == idFazenda) {
                combobox.setSelectedIndex(i);
                break;
            }
        }
    }

    private CategoriaAnimalBeans getCategoriaAnimal(JComboBox<CategoriaAnimalBeans> comboBox) {
        return (CategoriaAnimalBeans) comboBox.getSelectedItem();
    }

    private CompradorGadoBeans getComprador(JComboBox<CompradorGadoBeans> comboBox) {
        return (CompradorGadoBeans) comboBox.getSelectedItem();
    }

    private PropriedadesBeans getFazenda(JComboBox<PropriedadesBeans> comboBox) {
        return (PropriedadesBeans) comboBox.getSelectedItem();
    }

    public void VerificarStatusCompra(int rowIndex) {
        if (TbCompra.getValueAt(rowIndex, 15).equals("Encerrada")) {
            menuInfoPag.setEnabled(false);
        } else {
            menuInfoPag.setEnabled(true);
        }
    }

    private void limparDados() {
        txt_ID.setText("");
        txt_dataCompra.setDate(null);
        cb_destinoCompra.setSelectedIndex(0);
        cb_comprador.setSelectedIndex(0);
        cb_descricao.setSelectedIndex(0);
        cb_formaNeg.setSelectedIndex(0);
        txt_quant.setText("");
        txt_fornecedor.setText("");
        cb_tipoCadastro.setSelectedIndex(0);
        cb_banco.setSelectedIndex(0);
        txt_agencia.setText("");
        txt_contaCorrente.setText("-");
        cb_status.setSelectedIndex(0);
    }

    private void totalizarTabela() {
        int SomaCabeca = 0;
        int SomaQNF = 0;
        int SomaQGTA = 0;
        int SomaQMIN = 0;
        double QCabecaPesada = 0;
        double ValorTotalPesado = 0;
        double SomaValorT = 0;
        double PesoTotalGado = 0;
        for (int i = 0; i <= tb_compras.getRowCount() - 1; i++) {
            SomaCabeca += Integer.parseInt(tb_compras.getValueAt(i, 6).toString());
            SomaValorT += Double.parseDouble(tb_compras.getValueAt(i, 14).toString().replace("R$", "").replace(".", "").replace(",", "."));
            SomaQNF += Integer.parseInt(tb_compras.getValueAt(i, 16).toString());
            SomaQGTA += Integer.parseInt(tb_compras.getValueAt(i, 17).toString());
            SomaQMIN += Integer.parseInt(tb_compras.getValueAt(i, 18).toString());
            if (!tb_compras.getValueAt(i, 24).equals("0,00 kg")) {
                QCabecaPesada += Double.parseDouble(tb_compras.getValueAt(i, 18).toString());
                PesoTotalGado += Double.parseDouble(tb_compras.getValueAt(i, 24).toString().replace("kg", "").replace(",", ".")) * Double.parseDouble(tb_compras.getValueAt(i, 18).toString());
                ValorTotalPesado += Double.parseDouble(tb_compras.getValueAt(i, 13).toString().replace("R$", "").replace(".", "").replace(",", "."));
            }
        }
        txt_somaQcab.setText(String.valueOf(SomaCabeca));
        txt_somaValorT.setText(new DecimalFormat("R$ #,##0.00").format(SomaValorT));
        txt_valorMedio.setText(new DecimalFormat("R$ #,##0.00").format(SomaValorT / SomaCabeca));
        txt_pesoMdestino.setText(new DecimalFormat("##0.0 kg").format(PesoTotalGado / QCabecaPesada));
        txt_reaisArDest.setText(new DecimalFormat("R$ ##0.00").format((ValorTotalPesado / PesoTotalGado) * 30));
        txt_SNF.setText(String.valueOf(SomaQNF));
        txt_SGTA.setText(String.valueOf(SomaQGTA));
        txt_SMIN.setText(String.valueOf(SomaQMIN));

    }

    private void editarCompra() {
        int editar = JOptionPane.showConfirmDialog(null, "Deseja editar esta Compra de Gado?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION) {
            CompraGadoB = CompraGadoC.buscarCompra((Integer) TbCompra.getValueAt(tb_compras.getSelectedRow(), 0));
            if (CompraGadoB != null) {
                preencherDadosCompra(CompraGadoB);
                btn_editar.setEnabled(true);
                btn_excluir.setEnabled(true);
                btn_Salvar.setEnabled(false);
                habilitarCampos();
            }
        }
    }

    private void relatorioGeralCompra(String Comprador, String Destino) {
        int confirma = JOptionPane.showConfirmDialog(null, "Deseja visualizar o relatório de compra de gado?", "Atenção", JOptionPane.YES_NO_OPTION);
        Conexao.ReiniciarCon();
        if (confirma == JOptionPane.YES_OPTION) {
            Map p = new HashMap();
            JasperReport relatorio;
            JasperPrint impressao;
            try {
                if (txt_dataInicial.getDate() != null) {
                    p.put("Comprador", Comprador);
                    p.put("Destino", Destino);
                    p.put("dtInicial", txt_dataInicial.getDate());
                    p.put("dtFinal", txt_dataFinal.getDate());
                    p.put("caminhoLogo", FormatarICO.logoTipo().getImage());
                    relatorio = JasperCompileManager.compileReport(new File("").getClass().getResourceAsStream("/Relatorios/CompraGadoGeral.jrxml"));
                    impressao = JasperFillManager.fillReport(relatorio, p, Conexao.getConnection());
                    JasperViewer view = new JasperViewer(impressao, false);
                    view.setTitle("Relatório de Compra de Gado");
                    view.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Verifique o número da Ordem de Compra!!");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    private void TipTextTbCompras() {
        tb_compras.setToolTipText(null);
        int[] linha = tb_compras.getSelectedRows();

        if (linha.length > 1) {
            String texto = "";
            int SomaQtCompra = 0;
            int SomaQtEntregue = 0;
            double SomaPesoTotal = 0.0;
            double SomaPesoDestino = 0.0;
            for (int i = 0; i < linha.length; i++) {
                SomaQtCompra += Integer.parseInt(TbCompra.getValueAt(linha[i], 6).toString());
                SomaQtEntregue += Integer.parseInt(TbCompra.getValueAt(linha[i], 18).toString());
                SomaPesoTotal += Corretores.ConverQuilosDouble(TbCompra.getValueAt(linha[i], 8).toString());
                SomaPesoDestino += (Corretores.ConverQuilosDouble(TbCompra.getValueAt(linha[i], 24).toString()) * Integer.parseInt(TbCompra.getValueAt(linha[i], 18).toString()));
            }
            double pesoMedioCompra = (SomaPesoTotal / SomaQtCompra);
            double pesoMedioDestino = (SomaPesoDestino / SomaQtEntregue);
            double QuebraPeso = (pesoMedioDestino / pesoMedioCompra) - 1;
            texto = "<html><FONT FACE=\"Tahoma\" SIZE=3>"
                    + "Quantidade Comprada: <B>" + String.valueOf(SomaQtCompra) + "</B><br>"
                    + "Quantidade Entregue: <B>" + String.valueOf(SomaQtEntregue) + "</B><br>"
                    + "Peso Total Compra: <B>" + new DecimalFormat("#,###,##0.00").format(SomaPesoTotal) + " - " + new DecimalFormat("##0.00 kg").format(pesoMedioCompra) + "</B><br>"
                    + "Peso Total Destino: <B>" + new DecimalFormat("#,###,##0.00").format(SomaPesoDestino) + " - " + new DecimalFormat("##0.00 kg").format(pesoMedioDestino) + "</B><br>"
                    + "% de Quebra: <B>" + new DecimalFormat("0.00 %").format(QuebraPeso) + "</B><br>"
                    + "</FONT></html>";
            ToolTipManager.sharedInstance().setInitialDelay(150);
            ToolTipManager.sharedInstance().setDismissDelay(30000);
            tb_compras.setToolTipText(texto);
        }
    }

    private void atualizarValorTotal() {
        try {
            Integer quant = getInteger(txt_quant.getText());
            if (cb_formaNeg.getSelectedIndex() == 1) {
                Double valorAr = Corretores.ConverterStringDouble(txt_valorAr.getText());
                Double pesoOrig = Corretores.ConverterStringDouble(txt_pesoOrig.getText());
                Double valorTotal = (pesoOrig / 30) * valorAr;
                txt_valorTotal.setText(Corretores.ConverterDoubleReais(valorTotal));
            } else if (cb_formaNeg.getSelectedIndex() == 2) {
                Double valorUnit = Corretores.ConverterStringDouble(txt_precoUnit.getText());
                Double valorTotal = valorUnit * quant;
                txt_valorTotal.setText(Corretores.ConverterDoubleReais(valorTotal));
            }
        } catch (Exception e) {
            txt_valorTotal.setText("R$ 0,00");
        }
    }

    private String SQLWhere() {
        String s = "";

        s = " WHERE " + getTipoData() + " BETWEEN '" + Corretores.ConverterDateAAAA_MM_DD(txt_dataInicial.getDate()) + "' AND '" + Corretores.ConverterDateAAAA_MM_DD(txt_dataFinal.getDate()) + "'";
        if (cb_compradorPesq.getSelectedIndex() > 0) {
            s += " and compra_gado_comprador = '" + getComprador(cb_compradorPesq).getComprador() + "'";
        }
        if (!txt_fornecedorPesq.getText().equals("")) {
            s += " and compra_gado_fornecedor Like '%" + txt_fornecedorPesq.getText() + "%' ";
        }
        if (cb_formaNegPesq.getSelectedIndex() > 0) {
            s += "and compra_gado_negociacao = '" + cb_formaNegPesq.getSelectedItem().toString()+ "'";
        }
        if (cb_destinoPesq.getSelectedIndex() > 0) {
            s += "and compra_gado_destino = '" + getFazenda(cb_destinoPesq).getNome() + "'";
        }
        if (cb_statusPesq.getSelectedIndex() == 1) {
            s += "and compra_gado_stat = 'Em Aberto' ";
        } else if (cb_statusPesq.getSelectedIndex() == 2) {
            s += "and compra_gado_stat = 'Encerrada' ";
        }
        return s;
    }

    private String getTipoData() {
        String s = "";
        if (ch_dtCompra.isSelected()) {
            s = ("compra_gado.compra_gado_data");
        } else if (ch_dtPagto.isSelected()) {
            s = "(select Min(pg.pagto_DataPagto) From pagamentos pg WHERE pg.pagto_nCompra = compra_gado.compra_gado_id)";
        }
        return s;
    }

}
