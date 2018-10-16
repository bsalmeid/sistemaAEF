/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import TableModel.TableModelPagtoNF;
import TableModel.TableModelPagtoClass;
import Beans.PagtoNFBeans;
import Beans.PagtoClassBeans;
import Beans.PagamentosBeans;
import DAO.PagamentosDAO;
import Beans.AtividadeBeans;
import Beans.FreteTableModel;
import Beans.BancosBeans;
import Beans.CentroDeResultado;
import Beans.ContaBancariaBeans;
import Beans.FornecedoresBeans;
import Beans.InventarioBeans;
import Beans.MoedaBeans;
import Beans.TipoDocPagto;
import Utilitarios.CentralizarTabela;
import Beans.PlanoContaBeans;
import Beans.PropriedadesBeans;
import Beans.CompraGadoBeans;
import DAO.Diversas;
import static GUI.Principal.listBancos;
import static GUI.Principal.listContaOrigem;
import static GUI.Principal.listPlanoContas;
import static GUI.Principal.listTipoDocPagto;
import DAO.DiversasHibernate;
import static GUI.Principal.FreteGado;
import static GUI.Principal.ListaPropriedades;
import static GUI.Principal.OrigemContasAPagar;
import static GUI.Principal.listInventario;
import static GUI.Principal.listMoeda;
import static GUI.Principal.listaAtividades;
import static GUI.Principal.listaCentroResultado;
import static GUI.frmLogin.UsuarioLogadoBeans;
import Icones.FormatarICO;
import Utilitarios.Corretores;
import Utilitarios.ReaisCellRenderer;
import Utilitarios.ValidarPermissoes;
import com.sun.glass.events.KeyEvent;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import componentes.UJComboBox;
import java.awt.AWTKeyStroke;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class frmPagamentos extends javax.swing.JInternalFrame {

    public PagamentosBeans PagamentosB;
    PagamentosDAO PagamentosD;
    Diversas DiversasD;
    FornecedoresBeans fornecedorClass;
    FornecedoresBeans fornecedorNF;
    FornecedoresBeans fornecedorPg;
    CentralizarTabela Centralizar;
    TableModelPagtoClass TbClass;
    TableModelPagtoNF TbNF;
    FreteTableModel FreteTable;
    MaskFormatter CNPJMask;
    MaskFormatter CPFMask;
    MaskFormatter DataMask;
    public JTextField txt_nCompra;
    public JTextField txt_nPedido;
    JLabel lbl_nCompra;
    JLabel lbl_nPedido;
    frmCadFornecedores frmCadFornecedores;
    Integer idPagtoResumo;
    Integer contador;
    List<Component> listaComponentes;
    public CompraGadoBeans nCompraGado;
    public Integer nPedidoInsumos;

    public frmPagamentos() {
        initComponents();
        PagamentosB = new PagamentosBeans();
        PagamentosD = new PagamentosDAO();
        Centralizar = new CentralizarTabela();
        DiversasD = new Diversas();
        nCompraGado = null;
        nPedidoInsumos = 0;
        OrigemContasAPagar = "";

        maskFormaterCPF();
        carregarTabelaClass();
        carregarTabelaNF();
        carregarBancos();
        carregarContasOrigem();
        carregarDocumentos();
        carregarFazendas();
        carregarAtividades();
        carregarMoedas();
        carregarPlanoContas();
        carregarInventario();
        carregarCentroResultado();
        Keypress(this.getContentPane());
        habilitarCampos(jPanel1, false);
        habilitarCampos(jPanel3, false);
        habilitarCampos(jPanel4, false);

        ch_novoPagto.requestFocus();
        idPagtoResumo = 0;
    }

    private void Keypress(Container container) {
        for (Component c : container.getComponents()) {
            HashSet<AWTKeyStroke> conjForward = new HashSet<>(c.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
            conjForward.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
            //  conjForward.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_DOWN, 0));
            c.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conjForward);
        }
    }

    public JTextField jTextNCompra() {
        if (txt_nCompra == null) {
            txt_nCompra = new JTextField();
            lbl_nCompra = new JLabel();
            lbl_nCompra.setText("Nº Compra:");
            lbl_nCompra.setFont(new Font("Tahoma", 1, 11));
            lbl_nCompra.setSize(70, 30);
            lbl_nCompra.setHorizontalAlignment(SwingConstants.RIGHT);
            txt_nCompra.setSize(70, 30);
            getContentPane().add(txt_nCompra, BorderLayout.NORTH + BorderLayout.EAST);
            getContentPane().add(lbl_nCompra);
            txt_nCompra.setLayout(txt_idPagto.getLayout());
            txt_nCompra.setHorizontalAlignment(SwingConstants.CENTER);
            lbl_nCompra.setLocation(650, txt_idPagto.getLocation().y);
            txt_nCompra.setLocation(730, txt_idPagto.getLocation().y);
            txt_nCompra.setEditable(false);
        }
        lbl_nCompra.setVisible(txt_nCompra.isVisible());
        return txt_nCompra;
    }

    public JTextField jTextNPedido() {
        if (txt_nPedido == null) {
            txt_nPedido = new JTextField();
            lbl_nPedido = new JLabel();
            lbl_nPedido.setText("Nº Pedido:");
            lbl_nPedido.setFont(new java.awt.Font("Tahoma", 1, 11));
            lbl_nPedido.setSize(70, 30);
            lbl_nPedido.setHorizontalAlignment(SwingConstants.RIGHT);
            txt_nPedido.setSize(70, 30);
            getContentPane().add(txt_nPedido, BorderLayout.NORTH + BorderLayout.EAST);
            getContentPane().add(lbl_nPedido);

            txt_nPedido.setLayout(txt_idPagto.getLayout());
            txt_nPedido.setHorizontalAlignment(SwingConstants.CENTER);
            lbl_nPedido.setLocation(650, txt_idPagto.getLocation().y);
            txt_nPedido.setLocation(730, 15);
        }
        //txt_nCompra.setVisible(true);
        txt_nPedido.setEditable(false);

        return txt_nPedido;
    }

    private void carregarFazendas() {
        if (ListaPropriedades == null) {
            ListaPropriedades = DiversasHibernate.getListaFazendas();
        }
        cb_fazendaClas.addItem(new PropriedadesBeans(0, "-"));
        cb_fazendaNF.addItem(new PropriedadesBeans(0, "-"));
        for (PropriedadesBeans p : ListaPropriedades) {
            cb_fazendaClas.addItem(p);
            cb_fazendaNF.addItem(p);
        }
    }

    private void carregarDocumentos() {
        if (listTipoDocPagto == null) {
            listTipoDocPagto = DiversasHibernate.getListaDocumentosPagto();
        }
        cb_tipoDoc.addItem(new TipoDocPagto(0, "-"));
        for (TipoDocPagto list : listTipoDocPagto) {
            cb_tipoDoc.addItem(list);
        }
    }

    private void carregarInventario() {
        if (listInventario == null) {
            listInventario = DiversasHibernate.getListaInventario();
        }
        cb_nFrota.removeAllItems();
        InventarioBeans l = new InventarioBeans(0, "-", "-");
        cb_nFrota.addItem(l);
        for (InventarioBeans frota : listInventario) {
            cb_nFrota.addItem(frota);
        }
    }

    private void carregarCentroResultado() {
        if (listaCentroResultado == null) {
            listaCentroResultado = DiversasHibernate.getListaCentroResultado();
        }
        cb_centroResultado.removeAllItems();
        cb_centroResultado.addItem(new CentroDeResultado(0L, "-"));
        for (CentroDeResultado c : listaCentroResultado) {
            cb_centroResultado.addItem(c);
        }
    }

    private void carregarCentroResultado(Integer idAtividade) {
        if (listaCentroResultado == null) {
            listaCentroResultado = DiversasHibernate.getListaCentroResultado();
        }
        cb_centroResultado.removeAllItems();
        cb_centroResultado.addItem(new CentroDeResultado(0L, "-"));
        for (CentroDeResultado c : listaCentroResultado) {
            if (c.getAtividade().getID() == idAtividade) {
                cb_centroResultado.addItem(c);
            }
        }
    }

    private JTable carregarTabelaNF() {
        tb_nf.setModel(getTableModelNF());
        Centralizar = new CentralizarTabela();
        Centralizar.centralizarTabela(tb_nf);
        ((DefaultTableCellRenderer) tb_nf.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        for (int c = 0; c < TbNF.getColumnCount(); c++) {
            if (c == TbNF.ID || c == TbNF.IDPAGTO || c == TbNF.TIPOCAD || c == TbNF.IDTIPODOC) {
                tb_nf.getColumnModel().getColumn(c).setPreferredWidth(0);
                tb_nf.getColumnModel().getColumn(c).setMinWidth(0);
                tb_nf.getColumnModel().getColumn(c).setMaxWidth(0);
            }
        }
        tb_nf.getColumnModel().getColumn(TbNF.DTEMISSAO).setPreferredWidth(110);
        tb_nf.getColumnModel().getColumn(TbNF.NCAD).setPreferredWidth(120);
        tb_nf.getColumnModel().getColumn(TbNF.TIPODOC).setPreferredWidth(150);// Tipo Documento
        tb_nf.getColumnModel().getColumn(TbNF.NDOC).setPreferredWidth(90);// Número Documento
        tb_nf.getColumnModel().getColumn(TbNF.FAZENDA).setPreferredWidth(90);// Fazenda
        tb_nf.getColumnModel().getColumn(TbNF.IDEMISSOR).setPreferredWidth(80);// ID EMISSOR
        tb_nf.getColumnModel().getColumn(TbNF.EMISSOR).setPreferredWidth(220);// EMISSOR
        tb_nf.getColumnModel().getColumn(TbNF.VALOR).setPreferredWidth(120);// valor
        tb_nf.getColumnModel().getColumn(TbNF.VALOR).setCellRenderer(new ReaisCellRenderer());
        return tb_nf;
    }

    private JTable carregarTabelaClass() {
        Centralizar = new CentralizarTabela();
        tb_class.setModel(getTableModel());
        Centralizar.centralizarTabela(tb_class);
        ((DefaultTableCellRenderer) tb_class.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        for (int c = 0; c < TbClass.getColumnCount(); c++) {
            if (c == TbClass.ID || c == TbClass.IDPAGTO || c == TbClass.IDPLANO || c == TbClass.IDFAZENDA || c == TbClass.IDAPLICACAO || c == TbClass.TIPODESPESA) {
                tb_class.getColumnModel().getColumn(c).setPreferredWidth(0);
                tb_class.getColumnModel().getColumn(c).setMinWidth(0);
                tb_class.getColumnModel().getColumn(c).setMaxWidth(0);
            }
        }
        tb_class.getColumnModel().getColumn(TbClass.ATIVIDADE).setPreferredWidth(120); // Atividade
        tb_class.getColumnModel().getColumn(TbClass.PLANOCONTA).setPreferredWidth(120); // Plano COnta
        tb_class.getColumnModel().getColumn(TbClass.DESCCONTA).setPreferredWidth(130); // Plano Descrição
        tb_class.getColumnModel().getColumn(TbClass.FAZ).setPreferredWidth(120); // Fazenda
        tb_class.getColumnModel().getColumn(TbClass.APLICACAO).setPreferredWidth(80); // Aplicação
        tb_class.getColumnModel().getColumn(TbClass.APLICACAO).setMaxWidth(70); // Aplicação
        tb_class.getColumnModel().getColumn(TbClass.DESCRICAO).setPreferredWidth(160); // Descriçao
        tb_class.getColumnModel().getColumn(TbClass.VALOR).setPreferredWidth(120); // valor
        tb_class.getColumnModel().getColumn(TbClass.VALOR).setCellRenderer(new ReaisCellRenderer());

        return tb_class;
    }

    private TableModelPagtoClass getTableModel() {
        if (TbClass == null) {
            TbClass = new TableModelPagtoClass();
        }
        return TbClass;
    }

    private TableModelPagtoNF getTableModelNF() {
        if (TbNF == null) {
            TbNF = new TableModelPagtoNF();
        }
        return TbNF;
    }

    private void carregarPlanoContas() {
        if (listPlanoContas == null) {
            listPlanoContas = DiversasHibernate.getPlanoConta();
        }
        cb_planoConta.addItem(new PlanoContaBeans(0, 0, "-"));
        for (PlanoContaBeans p : listPlanoContas) {
            cb_planoConta.addItem(p);
        }
    }

    private void carregarMoedas() {
        if (listMoeda == null) {
            listMoeda = DiversasHibernate.getListaMoeda();
        }
        cb_moeda.addItem(new MoedaBeans(0, "-"));
        for (MoedaBeans m : listMoeda) {
            cb_moeda.addItem(m);
        }
        cb_moeda.setSelectedIndex(1);
        txt_vlrMoeda.setEnabled(false);
        txt_taxaC.setEnabled(false);
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

    private void carregarContasOrigem() {
        if (listContaOrigem == null) {
            listContaOrigem = DiversasHibernate.getListaContasBancarias();
        }
        cb_contaOrigem.addItem(new ContaBancariaBeans(0, "-"));
        for (ContaBancariaBeans list : listContaOrigem) {
            cb_contaOrigem.addItem(list);
        }
    }

    private void carregarAtividades() {
        if (listaAtividades == null) {
            listaAtividades = DiversasHibernate.getListaAtividade();
        }
        cb_atividade.addItem(new AtividadeBeans(0, "-", true));
        for (AtividadeBeans a : listaAtividades) {
            cb_atividade.addItem(a);
        }
    }

    private void maskFormaterCPF() {
        try {
            CNPJMask = new MaskFormatter("##.###.###/####-##");
            CPFMask = new MaskFormatter("###.###.###-##");
            DataMask = new MaskFormatter("##/##/####");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        btn_SalvarPagto = new javax.swing.JButton();
        btn_pesquisarPagto = new javax.swing.JButton();
        btn_editarPagto = new javax.swing.JButton();
        btn_excluirPagto = new javax.swing.JButton();
        btn_anterior = new javax.swing.JButton();
        javax.swing.JLabel jLabel21 = new javax.swing.JLabel();
        txt_idPagto = new javax.swing.JTextField();
        ch_novoPagto = new javax.swing.JCheckBox();
        ch_buscarPagto = new javax.swing.JCheckBox();
        btn_avancar = new javax.swing.JButton();
        javax.swing.JScrollPane jScrollPane3 = new javax.swing.JScrollPane();
        javax.swing.JPanel jPanel5 = new javax.swing.JPanel();
        tp_pagto = new org.jdesktop.swingx.JXTaskPane();
        jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        txt_dataPagto =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        txt_valorPagto = new javax.swing.JTextField();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        cb_formaPagto = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        txt_numDoc = new javax.swing.JTextField();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        cb_banco = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        txt_agencia = new javax.swing.JTextField();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        txt_conta = new javax.swing.JTextField();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        txt_titular = new javax.swing.JTextField();
        cb_doc = new javax.swing.JComboBox<>();
        txt_cpf = new javax.swing.JFormattedTextField();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        txt_valorPagtoPrevisto = new javax.swing.JTextField();
        javax.swing.JLabel jLabel13 = new javax.swing.JLabel();
        cb_contaOrigem = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel18 = new javax.swing.JLabel();
        cb_moeda = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel19 = new javax.swing.JLabel();
        txt_vlrMoeda = new javax.swing.JTextField();
        javax.swing.JLabel jLabel20 = new javax.swing.JLabel();
        txt_taxaC = new javax.swing.JTextField();
        ch_previsto = new javax.swing.JCheckBox();
        ch_realizado = new javax.swing.JCheckBox();
        btn_repetirValor = new javax.swing.JButton();
        btn_cadastrar = new javax.swing.JButton();
        txt_dataPagtoPrevista =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        btn_repetirValor2 = new javax.swing.JButton();
        tp_nf = new org.jdesktop.swingx.JXTaskPane();
        jPanel3 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel15 = new javax.swing.JLabel();
        cb_tipoDoc = new javax.swing.JComboBox<>();
        txt_dtEmissao =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        cb_docNF = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        txt_cpfNF = new javax.swing.JFormattedTextField();
        txt_DocNF = new javax.swing.JTextField();
        javax.swing.JLabel jLabel17 = new javax.swing.JLabel();
        cb_fazendaNF = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel16 = new javax.swing.JLabel();
        txt_vlrNF = new javax.swing.JTextField();
        btn_pesqEmissor = new javax.swing.JButton();
        btn_incluirNF = new javax.swing.JButton();
        btn_editarNF = new javax.swing.JButton();
        btn_excluirNF = new javax.swing.JButton();
        javax.swing.JLabel jLabel14 = new javax.swing.JLabel();
        txt_emissor = new javax.swing.JTextField();
        txt_idEmissor = new javax.swing.JTextField();
        btn_repetirValor3 = new javax.swing.JButton();
        javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        tb_nf = new javax.swing.JTable();
        tp_clas = new org.jdesktop.swingx.JXTaskPane();
        jPanel4 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel22 = new javax.swing.JLabel();
        cb_atividade = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel23 = new javax.swing.JLabel();
        cb_fazendaClas = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel24 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel25 = new javax.swing.JLabel();
        cb_planoConta = new componentes.UJComboBox();
        btn_pesquisarPlano = new javax.swing.JButton();
        javax.swing.JLabel jLabel26 = new javax.swing.JLabel();
        cb_nFrota = new componentes.UJComboBox();
        javax.swing.JLabel jLabel27 = new javax.swing.JLabel();
        txt_descricaoClass = new javax.swing.JTextField();
        javax.swing.JLabel jLabel28 = new javax.swing.JLabel();
        txt_valorClass = new javax.swing.JTextField();
        btn_addClass = new javax.swing.JButton();
        btn_editarClass = new javax.swing.JButton();
        btn_delClass = new javax.swing.JButton();
        cb_centroResultado = new componentes.UJComboBox();
        btn_repetirValor1 = new javax.swing.JButton();
        btn_pesqEmissor2 = new javax.swing.JButton();
        scPClassifocacao = new javax.swing.JScrollPane();
        tb_class = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Pagamentos");
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        btn_SalvarPagto.setBackground(new java.awt.Color(255, 255, 255));
        btn_SalvarPagto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/salvar_32_32.png"))); // NOI18N
        btn_SalvarPagto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_SalvarPagto.setEnabled(false);
        btn_SalvarPagto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalvarPagtoActionPerformed(evt);
            }
        });

        btn_pesquisarPagto.setBackground(new java.awt.Color(255, 255, 255));
        btn_pesquisarPagto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar.png"))); // NOI18N
        btn_pesquisarPagto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_pesquisarPagto.setEnabled(false);
        btn_pesquisarPagto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisarPagtoActionPerformed(evt);
            }
        });

        btn_editarPagto.setBackground(new java.awt.Color(255, 255, 255));
        btn_editarPagto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/editar_32_32.png"))); // NOI18N
        btn_editarPagto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_editarPagto.setEnabled(false);
        btn_editarPagto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarPagtoActionPerformed(evt);
            }
        });

        btn_excluirPagto.setBackground(new java.awt.Color(255, 255, 255));
        btn_excluirPagto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/deletar_32_32.png"))); // NOI18N
        btn_excluirPagto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_excluirPagto.setEnabled(false);
        btn_excluirPagto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirPagtoActionPerformed(evt);
            }
        });

        btn_anterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/seta_esquerda_32_32.png"))); // NOI18N
        btn_anterior.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_anterior.setEnabled(false);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("ID Pagto");

        txt_idPagto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_idPagto.setEnabled(false);

        buttonGroup2.add(ch_novoPagto);
        ch_novoPagto.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_novoPagto.setText("Novo Pagto");
        ch_novoPagto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ch_novoPagtoActionPerformed(evt);
            }
        });

        buttonGroup2.add(ch_buscarPagto);
        ch_buscarPagto.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_buscarPagto.setText("Buscar Pagto");
        ch_buscarPagto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ch_buscarPagtoActionPerformed(evt);
            }
        });

        btn_avancar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/seta_direta_32_32.png"))); // NOI18N
        btn_avancar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_avancar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_avancar.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        btn_avancar.setEnabled(false);

        jScrollPane3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        tp_pagto.setTitle("Dados p/ Pagamento");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Data Pagto:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Valor Pagamento");

        txt_valorPagto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_valorPagto.setText("R$ 0,00");
        txt_valorPagto.setSelectionStart(0);
        txt_valorPagto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_valorPagtoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_valorPagtoFocusLost(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Forma Pagamento");

        cb_formaPagto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Cheque em Mãos", "Cheque Deposito", "Em Espécie", "Boleto", "Formulário TED", "Transferência", "Internet Banking" }));
        cb_formaPagto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cb_formaPagtoFocusGained(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Nº Doc");

        txt_numDoc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_numDoc.setText("0");
        txt_numDoc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_numDocFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_numDocFocusLost(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Banco");

        cb_banco.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cb_bancoFocusGained(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Agência");

        txt_agencia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_agencia.setSelectionEnd(7);
        txt_agencia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_agenciaFocusGained(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("C/C");

        txt_conta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_conta.setSelectionEnd(7);
        txt_conta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_contaFocusGained(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Títular da Conta");

        txt_titular.setSelectionEnd(7);
        txt_titular.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_titularFocusGained(evt);
            }
        });

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

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Data Prevista:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Valor Previsto");

        txt_valorPagtoPrevisto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_valorPagtoPrevisto.setText("R$ 0,00");
        txt_valorPagtoPrevisto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_valorPagtoPrevistoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_valorPagtoPrevistoFocusLost(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Conta de Origem");

        cb_contaOrigem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cb_contaOrigemFocusGained(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Moeda");

        cb_moeda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_moedaItemStateChanged(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Valor ");

        txt_vlrMoeda.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_vlrMoeda.setText("0,00");
        txt_vlrMoeda.setEnabled(false);
        txt_vlrMoeda.setSelectionEnd(7);
        txt_vlrMoeda.setSelectionStart(0);
        txt_vlrMoeda.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_vlrMoedaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_vlrMoedaFocusLost(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Taxa");

        txt_taxaC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_taxaC.setText("R$ 0,00");
        txt_taxaC.setToolTipText("");
        txt_taxaC.setEnabled(false);
        txt_taxaC.setSelectionStart(0);
        txt_taxaC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_taxaCFocusGained(evt);
            }
        });

        buttonGroup1.add(ch_previsto);
        ch_previsto.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_previsto.setText("Pagamento Previsto");
        ch_previsto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ch_previstoActionPerformed(evt);
            }
        });

        buttonGroup1.add(ch_realizado);
        ch_realizado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_realizado.setText("Pagamento Realizado");
        ch_realizado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ch_realizadoActionPerformed(evt);
            }
        });

        btn_repetirValor.setBackground(new java.awt.Color(255, 255, 255));
        btn_repetirValor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/refresh.png"))); // NOI18N
        btn_repetirValor.setBorder(null);
        btn_repetirValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_repetirValorActionPerformed(evt);
            }
        });

        btn_cadastrar.setBackground(new java.awt.Color(255, 255, 255));
        btn_cadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar pequeno.png"))); // NOI18N
        btn_cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cadastrarActionPerformed(evt);
            }
        });

        btn_repetirValor2.setBackground(new java.awt.Color(255, 255, 255));
        btn_repetirValor2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/refresh.png"))); // NOI18N
        btn_repetirValor2.setBorder(null);
        btn_repetirValor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_repetirValor2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_dataPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_dataPagtoPrevista, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_repetirValor2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_moeda, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_vlrMoeda, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_taxaC, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_valorPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btn_repetirValor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel12)
                                .addGap(19, 19, 19)
                                .addComponent(txt_valorPagtoPrevisto, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ch_previsto)
                                .addGap(18, 18, 18)
                                .addComponent(ch_realizado))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_banco, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_agencia, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_conta, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_titular))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_contaOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_formaPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_numDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb_doc, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3)
                    .addComponent(jLabel12)
                    .addComponent(txt_valorPagtoPrevisto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ch_previsto)
                    .addComponent(ch_realizado)
                    .addComponent(txt_dataPagtoPrevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_repetirValor, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txt_valorPagto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txt_vlrMoeda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(txt_taxaC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(cb_moeda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(txt_dataPagto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btn_repetirValor2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel7)
                    .addComponent(txt_numDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_doc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(cb_formaPagto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cb_contaOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(cb_banco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(txt_conta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(txt_agencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(txt_titular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
        );

        tp_pagto.getContentPane().add(jPanel1);

        tp_nf.setTitle("Dados Fiscais");
        tp_nf.setAnimated(false);
        tp_nf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tp_nfKeyPressed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Tipo Documento");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Data Emissão");

        cb_tipoDoc.setMaximumSize(new java.awt.Dimension(140, 2147483647));
        cb_tipoDoc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cb_tipoDocFocusGained(evt);
            }
        });

        txt_dtEmissao.setMaximumSize(new java.awt.Dimension(140, 2147483647));

        cb_docNF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "CPF", "CNPJ" }));
        cb_docNF.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_docNFItemStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Nº Documento");

        txt_cpfNF.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txt_DocNF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_DocNF.setText("0");
        txt_DocNF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_DocNFFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_DocNFFocusLost(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Fazenda");

        cb_fazendaNF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cb_fazendaNFFocusGained(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Valor Total");

        txt_vlrNF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_vlrNF.setSelectionEnd(txt_vlrNF.getText().length());
        txt_vlrNF.setSelectionStart(0);
        txt_vlrNF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_vlrNFFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_vlrNFFocusLost(evt);
            }
        });

        btn_pesqEmissor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar pequeno_1.png"))); // NOI18N
        btn_pesqEmissor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesqEmissorActionPerformed(evt);
            }
        });

        btn_incluirNF.setText("Add");
        btn_incluirNF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_incluirNFActionPerformed(evt);
            }
        });

        btn_editarNF.setText("Editar");
        btn_editarNF.setEnabled(false);
        btn_editarNF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarNFActionPerformed(evt);
            }
        });

        btn_excluirNF.setText("Del");
        btn_excluirNF.setEnabled(false);
        btn_excluirNF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirNFActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Emissor");

        txt_idEmissor.setEditable(false);
        txt_idEmissor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_idEmissor.setText("0");

        btn_repetirValor3.setBackground(new java.awt.Color(255, 255, 255));
        btn_repetirValor3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/refresh.png"))); // NOI18N
        btn_repetirValor3.setBorder(null);
        btn_repetirValor3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_repetirValor3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dtEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_tipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cb_docNF, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_cpfNF, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn_pesqEmissor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_idEmissor, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txt_emissor))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_DocNF, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_fazendaNF, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_vlrNF, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn_repetirValor3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_incluirNF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_editarNF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_excluirNF)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(cb_tipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_docNF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cpfNF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesqEmissor, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel14)
                    .addComponent(txt_emissor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_idEmissor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_excluirNF)
                    .addComponent(btn_editarNF)
                    .addComponent(btn_incluirNF)
                    .addComponent(txt_vlrNF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(cb_fazendaNF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(txt_DocNF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txt_dtEmissao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(btn_repetirValor3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );

        tp_nf.getContentPane().add(jPanel3);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(452, 140));

        tb_nf.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tb_nf.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb_nf.setFocusable(false);
        tb_nf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_nfMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_nf);

        tp_nf.getContentPane().add(jScrollPane2);

        tp_clas.setTitle("Classificação");

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Atividade");

        cb_atividade.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_atividadeItemStateChanged(evt);
            }
        });
        cb_atividade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cb_atividadeFocusGained(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("C. de Resultado");

        cb_fazendaClas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cb_fazendaClasFocusGained(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Fazenda");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("Conta");

        cb_planoConta.setAutocompletar(true);
        cb_planoConta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cb_planoContaFocusGained(evt);
            }
        });

        btn_pesquisarPlano.setText("jButton1");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("Nº Frota");

        cb_nFrota.setAutocompletar(true);
        cb_nFrota.setRequestFocusEnabled(true);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText("Descrição");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setText("Valor");

        txt_valorClass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_valorClassFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_valorClassFocusLost(evt);
            }
        });

        btn_addClass.setText("Add");
        btn_addClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addClassActionPerformed(evt);
            }
        });

        btn_editarClass.setText("Editar");
        btn_editarClass.setEnabled(false);
        btn_editarClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarClassActionPerformed(evt);
            }
        });

        btn_delClass.setText("Del");
        btn_delClass.setEnabled(false);
        btn_delClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delClassActionPerformed(evt);
            }
        });

        cb_centroResultado.setAutocompletar(true);
        cb_centroResultado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cb_centroResultadoFocusGained(evt);
            }
        });

        btn_repetirValor1.setBackground(new java.awt.Color(255, 255, 255));
        btn_repetirValor1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/refresh.png"))); // NOI18N
        btn_repetirValor1.setBorder(null);
        btn_repetirValor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_repetirValor1ActionPerformed(evt);
            }
        });

        btn_pesqEmissor2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar pequeno_1.png"))); // NOI18N
        btn_pesqEmissor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesqEmissor2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_atividade, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_centroResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_planoConta, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn_pesquisarPlano, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_fazendaClas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_nFrota, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn_pesqEmissor2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_descricaoClass, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_valorClass, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn_repetirValor1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(btn_addClass)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_editarClass)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_delClass)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel23)
                    .addComponent(cb_atividade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel25)
                    .addComponent(cb_planoConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesquisarPlano)
                    .addComponent(cb_fazendaClas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(cb_centroResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel26)
                    .addComponent(cb_nFrota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(txt_descricaoClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(txt_valorClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_addClass)
                    .addComponent(btn_editarClass)
                    .addComponent(btn_delClass)
                    .addComponent(btn_repetirValor1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btn_pesqEmissor2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        tp_clas.getContentPane().add(jPanel4);

        scPClassifocacao.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        scPClassifocacao.setPreferredSize(new java.awt.Dimension(454, 220));

        tb_class.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb_class.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tb_class.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_classMouseClicked(evt);
            }
        });
        scPClassifocacao.setViewportView(tb_class);

        tp_clas.getContentPane().add(scPClassifocacao);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tp_pagto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tp_nf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tp_clas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tp_pagto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tp_nf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tp_clas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_idPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94)
                        .addComponent(ch_novoPagto)
                        .addGap(18, 18, 18)
                        .addComponent(ch_buscarPagto)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_SalvarPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_editarPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_pesquisarPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_excluirPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_anterior, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_avancar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1082, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel21)
                    .addComponent(txt_idPagto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ch_novoPagto)
                    .addComponent(ch_buscarPagto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_SalvarPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_editarPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesquisarPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_excluirPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_anterior, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_avancar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_SalvarPagtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalvarPagtoActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(null, "Deseja Salvar este Pagamento?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION) {
            popularBeans(PagamentosB);
            if (verificarBeans(PagamentosB) && ValidarPermissoes.validarPermissaoInsert(frmPagamentos.class.getSimpleName())) {
                if (PagamentosD.salvarPagto(PagamentosB)) {
                    //System.out.println("OrigemContasAPagar :" + OrigemContasAPagar);
                    pagamentoMinutasFrete(PagamentosB);
                    ch_novoPagto.requestFocus();
                    ch_novoPagto.doClick();
                }
            }
        }
    }//GEN-LAST:event_btn_SalvarPagtoActionPerformed

    private void btn_pesqEmissorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesqEmissorActionPerformed
        fornecedorNF = PagamentosD.buscarFornecedor(txt_cpfNF.getText());
        if (fornecedorNF != null) {
            txt_idEmissor.setText(String.valueOf(fornecedorNF.getID()));
            txt_emissor.setText(fornecedorNF.getRazaoSocial());
        }
    }//GEN-LAST:event_btn_pesqEmissorActionPerformed

    private void cb_docNFItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_docNFItemStateChanged
        txt_cpfNF.setText(null);
        txt_cpfNF.setValue(null);
        txt_cpfNF.setFormatterFactory(null);
        switch (cb_docNF.getSelectedIndex()) {
            case 0:
                txt_cpfNF.setFormatterFactory(null);
                break;
            case 1:
                txt_cpfNF.setFormatterFactory(new DefaultFormatterFactory(CPFMask));
                break;
            case 2:
                txt_cpfNF.setFormatterFactory(new DefaultFormatterFactory(CNPJMask));
                break;
            default:
                break;
        }
    }//GEN-LAST:event_cb_docNFItemStateChanged

    private void btn_incluirNFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_incluirNFActionPerformed
        PagtoNFBeans nf = new PagtoNFBeans();
        addNF(nf);
    }//GEN-LAST:event_btn_incluirNFActionPerformed

    private void btn_excluirNFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirNFActionPerformed
        int rowIndex = tb_nf.getSelectedRow();
        if (TbNF.getBeans(rowIndex).getID() != null) {
            int excluir = JOptionPane.showConfirmDialog(null, "<Html> Está Ação irá <B>EXCLUIR Permanentemente </B>, <br> este Registro, deseja Prosseguir? </html>", "Atenção", JOptionPane.YES_NO_OPTION);
            if (excluir == JOptionPane.YES_OPTION) {
                if (PagamentosD.DeletarNF(TbNF.getBeans(rowIndex))) {
                    TbNF.excluirLinha(rowIndex);
                }
            }
        } else {
            TbNF.excluirLinha(rowIndex);
        }
        tp_nf.setTitle("Documentos Fiscais - " + Corretores.ConverterDoubleReais(TbNF.somarValorTabela(TbNF.VALOR)));
        btn_incluirNF.setEnabled(true);
        btn_editarNF.setEnabled(false);
        btn_excluirNF.setEnabled(false);
        limparCampos(jPanel3);
    }//GEN-LAST:event_btn_excluirNFActionPerformed

    private void txt_vlrNFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_vlrNFFocusLost
        txt_vlrNF.setText(Corretores.ConverterDecimalReais(txt_vlrNF.getText()));
    }//GEN-LAST:event_txt_vlrNFFocusLost

    private void ch_buscarPagtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ch_buscarPagtoActionPerformed
        if (ch_buscarPagto.isSelected()) {
            habilitarCampos(jPanel1, true);
            habilitarCampos(jPanel3, true);
            habilitarCampos(jPanel4, true);
            btn_SalvarPagto.setEnabled(false);
            btn_editarPagto.setEnabled(false);
            btn_excluirPagto.setEnabled(false);
            btn_pesquisarPagto.setEnabled(true);
            txt_idPagto.setEnabled(true);
            txt_idPagto.requestFocus();
        }
    }//GEN-LAST:event_ch_buscarPagtoActionPerformed

    private void btn_pesquisarPagtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisarPagtoActionPerformed
        PagamentosB = PagamentosD.getPagamento(new Long(txt_idPagto.getText()));
        if (PagamentosB != null) {
            limparFormulario();
            preencherFormulario(PagamentosB);
            btn_editarPagto.setEnabled(true);
            btn_excluirPagto.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "Pagamento Não Localizado!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }//GEN-LAST:event_btn_pesquisarPagtoActionPerformed

    private void btn_editarPagtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarPagtoActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja Editar este Pagamento?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION) {
            popularBeans(PagamentosB);
            if (verificarBeans(PagamentosB) && ValidarPermissoes.validarPermissaoUpdate(frmPagamentos.class.getSimpleName())) {
                if (PagamentosD.editarPagto(PagamentosB)) {
                    ch_novoPagto.requestFocus();
                    ch_novoPagto.doClick();
                }
            }
        }
    }//GEN-LAST:event_btn_editarPagtoActionPerformed

    private void tb_nfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_nfMouseClicked
        if (evt.getClickCount() == 2) {
            preencherCamposNF(TbNF.getBeans(tb_nf.getSelectedRow()));
            cb_tipoDoc.requestFocus();
            btn_incluirNF.setEnabled(false);
            btn_editarNF.setEnabled(true);
            btn_excluirNF.setEnabled(true);
        } else {
            limparCampos(jPanel3);
            fornecedorNF = null;
            btn_incluirNF.setEnabled(true);
            btn_editarNF.setEnabled(false);
            btn_excluirNF.setEnabled(false);
        }
    }//GEN-LAST:event_tb_nfMouseClicked

    private void btn_editarNFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarNFActionPerformed
        int rowIndex = tb_nf.getSelectedRow();
        if (editarNF(TbNF.getBeans(rowIndex), rowIndex)) {
            btn_incluirNF.setEnabled(true);
            btn_editarNF.setEnabled(false);
            btn_excluirNF.setEnabled(false);
            limparCampos(jPanel3);
            fornecedorNF = null;
        }
    }//GEN-LAST:event_btn_editarNFActionPerformed

    private void ch_novoPagtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ch_novoPagtoActionPerformed
        if (ch_novoPagto.isSelected()) {
            limparFormulario();
            PagamentosB = new PagamentosBeans();
            habilitarCampos(jPanel1, true);
            habilitarCampos(jPanel3, true);
            habilitarCampos(jPanel4, true);
            btn_SalvarPagto.setEnabled(true);
            btn_editarPagto.setEnabled(false);
            btn_excluirPagto.setEnabled(false);
            btn_pesquisarPagto.setEnabled(false);
            btn_incluirNF.setEnabled(true);
            btn_editarNF.setEnabled(false);
            btn_excluirNF.setEnabled(false);
            btn_addClass.setEnabled(true);
            btn_editarClass.setEnabled(false);
            btn_delClass.setEnabled(false);
            txt_vlrMoeda.setEnabled(false);
            txt_taxaC.setEnabled(false);
            txt_dataPagtoPrevista.requestFocus();
            txt_dataPagtoPrevista.setDate(new Date(System.currentTimeMillis()));
            JTextFieldDateEditor dateEditor = (JTextFieldDateEditor) txt_dataPagtoPrevista.getDateEditor();
            dateEditor.select(0, 2);
            ch_previsto.setSelected(true);
            if (OrigemContasAPagar.equals("frmFreteGado")) {
                int cadastrar = JOptionPane.showConfirmDialog(null, "Está Ação Irá Perder, o Link com O Controle de Minutas de Frete, Deseja Continuar", "Atenção", JOptionPane.YES_NO_OPTION);
                if (cadastrar == JOptionPane.YES_OPTION) {
                    OrigemContasAPagar = ""; // Manter para integração com o Formulário de Frete
                }
            }
        }
    }//GEN-LAST:event_ch_novoPagtoActionPerformed

    private void tb_classMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_classMouseClicked
        if (evt.getClickCount() == 2) {
            preecherCamposClass(TbClass.getBeans(tb_class.getSelectedRow()));
            cb_atividade.requestFocus();
            btn_addClass.setEnabled(false);
            btn_editarClass.setEnabled(true);
            btn_delClass.setEnabled(true);
        } else {
            limparCamposClass();
            btn_addClass.setEnabled(true);
            btn_editarClass.setEnabled(false);
            btn_delClass.setEnabled(false);
        }
    }//GEN-LAST:event_tb_classMouseClicked

    private void btn_cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cadastrarActionPerformed
        FrmConsultaFornecedor consulta = new FrmConsultaFornecedor(null, true);
        consulta.tb_forn.addMouseListener(getConsultaFornecedorAdapter(consulta));
        consulta.setLocationRelativeTo(null);
        consulta.setVisible(true);

    }//GEN-LAST:event_btn_cadastrarActionPerformed

    private void btn_repetirValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_repetirValorActionPerformed
        txt_valorPagto.setText(txt_valorPagtoPrevisto.getText());
    }//GEN-LAST:event_btn_repetirValorActionPerformed

    private void cb_moedaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_moedaItemStateChanged
        if (cb_moeda.getSelectedItem().toString().equals("-")) {
            txt_taxaC.setEnabled(true);
            txt_vlrMoeda.setEnabled(true);
        } else if (!cb_moeda.getSelectedItem().toString().equals("Real")) {
            txt_taxaC.setEnabled(false);
            txt_vlrMoeda.setEnabled(false);
        } else {
            txt_taxaC.setEnabled(false);
            txt_vlrMoeda.setEnabled(false);
        }
    }//GEN-LAST:event_cb_moedaItemStateChanged

    private void txt_valorPagtoPrevistoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorPagtoPrevistoFocusLost
        txt_valorPagtoPrevisto.setText(Corretores.ConverterDecimalReais(txt_valorPagtoPrevisto.getText()));
    }//GEN-LAST:event_txt_valorPagtoPrevistoFocusLost

    private void cb_docItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_docItemStateChanged
        txt_cpf.setText(null);
        txt_cpf.setValue(null);
        txt_cpf.setFormatterFactory(null);
        switch (cb_doc.getSelectedIndex()) {
            case 0:
                txt_cpf.setFormatterFactory(null);
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
    }//GEN-LAST:event_cb_docItemStateChanged

    private void txt_valorPagtoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorPagtoFocusLost

    }//GEN-LAST:event_txt_valorPagtoFocusLost

    private void btn_excluirPagtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirPagtoActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(null, "<Html> Está Ação irá <B>EXCLUIR Permanentemente </B>, <br> este Pagamento, deseja Prosseguir? </html>", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION) {
            if (PagamentosD.DeletarPagto(PagamentosB)) {
                limparFormulario();
                ch_novoPagto.requestFocus();
                ch_novoPagto.doClick();
            }
        }
    }//GEN-LAST:event_btn_excluirPagtoActionPerformed

    private void txt_numDocFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_numDocFocusLost
        if (testeInteger(txt_numDoc.getText()) == false) {
            JOptionPane.showMessageDialog(null, "O Número do Documento Deve Conter Apenas Números!", "Erro", 0, FormatarICO.ICObtnSair());
            txt_numDoc.requestFocus();
        }
    }//GEN-LAST:event_txt_numDocFocusLost

    private void txt_agenciaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_agenciaFocusGained
        txt_agencia.selectAll();
    }//GEN-LAST:event_txt_agenciaFocusGained

    private void txt_contaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_contaFocusGained
        txt_conta.selectAll();
    }//GEN-LAST:event_txt_contaFocusGained

    private void txt_vlrMoedaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_vlrMoedaFocusLost

    }//GEN-LAST:event_txt_vlrMoedaFocusLost

    private void txt_vlrNFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_vlrNFFocusGained
        txt_vlrNF.selectAll();
    }//GEN-LAST:event_txt_vlrNFFocusGained

    private void ch_previstoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ch_previstoActionPerformed

    }//GEN-LAST:event_ch_previstoActionPerformed

    private void ch_realizadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ch_realizadoActionPerformed

    }//GEN-LAST:event_ch_realizadoActionPerformed

    private void txt_valorPagtoPrevistoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorPagtoPrevistoFocusGained
        txt_valorPagtoPrevisto.selectAll();
    }//GEN-LAST:event_txt_valorPagtoPrevistoFocusGained

    private void txt_valorClassFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorClassFocusLost
        txt_valorClass.setText(Corretores.ConverterDecimalReais(txt_valorClass.getText()));
    }//GEN-LAST:event_txt_valorClassFocusLost

    private void txt_vlrMoedaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_vlrMoedaFocusGained
        txt_vlrMoeda.selectAll();
    }//GEN-LAST:event_txt_vlrMoedaFocusGained

    private void txt_taxaCFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_taxaCFocusGained
        txt_taxaC.selectAll();
    }//GEN-LAST:event_txt_taxaCFocusGained

    private void txt_valorPagtoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorPagtoFocusGained
        txt_valorPagto.selectAll();
    }//GEN-LAST:event_txt_valorPagtoFocusGained

    private void txt_numDocFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_numDocFocusGained
        txt_numDoc.selectAll();
    }//GEN-LAST:event_txt_numDocFocusGained

    private void txt_titularFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_titularFocusGained
        txt_titular.selectAll();
    }//GEN-LAST:event_txt_titularFocusGained

    private void btn_addClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addClassActionPerformed
        PagtoClassBeans clas = new PagtoClassBeans();
        addClassificacao(clas);
    }//GEN-LAST:event_btn_addClassActionPerformed

    private void txt_valorClassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorClassFocusGained
        txt_valorClass.selectAll();
    }//GEN-LAST:event_txt_valorClassFocusGained

    private void btn_editarClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarClassActionPerformed
        int rowIndex = tb_class.getSelectedRow();
        if (editarClassificacao(TbClass.getBeans(rowIndex), rowIndex)) {
            btn_addClass.setEnabled(true);
            btn_editarClass.setEnabled(false);
            btn_delClass.setEnabled(false);
            limparCamposClass();
        }
    }//GEN-LAST:event_btn_editarClassActionPerformed

    private void txt_DocNFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_DocNFFocusLost
        if (testeInteger(txt_DocNF.getText()) == false) {
            JOptionPane.showMessageDialog(null, "O Número do Documento Deve Conter Apenas Números!", "Erro", 0, FormatarICO.ICObtnSair());
            txt_DocNF.requestFocus();
        }
    }//GEN-LAST:event_txt_DocNFFocusLost

    private void tp_nfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tp_nfKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tp_nf.setAnimated(true);
            tp_nf.setExpanded(tp_nf.isExpanded());
            cb_tipoDoc.requestFocus();
        }
    }//GEN-LAST:event_tp_nfKeyPressed

    private void cb_contaOrigemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cb_contaOrigemFocusGained
        setExpandedComboBox(evt.getComponent());
    }//GEN-LAST:event_cb_contaOrigemFocusGained

    private void cb_formaPagtoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cb_formaPagtoFocusGained
        setExpandedComboBox(evt.getComponent());
    }//GEN-LAST:event_cb_formaPagtoFocusGained

    private void btn_delClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delClassActionPerformed
        int rowIndex = tb_class.getSelectedRow();
        if (TbClass.getBeans(rowIndex).getID() != null) {
            int excluir = JOptionPane.showConfirmDialog(null, "<Html> Está Ação irá <B>EXCLUIR Permanentemente </B>, <br> este Registro, deseja Prosseguir? </html>", "Atenção", JOptionPane.YES_NO_OPTION);
            if (excluir == JOptionPane.YES_OPTION) {
                if (PagamentosD.DeletarClassicacao(TbClass.getBeans(rowIndex))) {
                    TbClass.excluirLinha(rowIndex);
                    atualizarValorClas();
                }
            }
        } else {
            TbClass.excluirLinha(rowIndex);
            atualizarValorClas();
        }
        limparCampos(jPanel4);
        btn_addClass.setEnabled(true);
        btn_editarClass.setEnabled(false);
        btn_delClass.setEnabled(false);

    }//GEN-LAST:event_btn_delClassActionPerformed

    private void btn_repetirValor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_repetirValor1ActionPerformed
        txt_valorClass.setText(txt_valorPagto.getText());
    }//GEN-LAST:event_btn_repetirValor1ActionPerformed

    private void cb_tipoDocFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cb_tipoDocFocusGained
        setExpandedComboBox(evt.getComponent());
    }//GEN-LAST:event_cb_tipoDocFocusGained

    private void cb_fazendaNFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cb_fazendaNFFocusGained
        setExpandedComboBox(evt.getComponent());
    }//GEN-LAST:event_cb_fazendaNFFocusGained

    private void cb_atividadeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cb_atividadeFocusGained
        setExpandedComboBox(evt.getComponent());
    }//GEN-LAST:event_cb_atividadeFocusGained

    private void cb_centroResultadoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cb_centroResultadoFocusGained
        setExpandedComboBox(evt.getComponent());
    }//GEN-LAST:event_cb_centroResultadoFocusGained

    private void cb_bancoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cb_bancoFocusGained
        setExpandedComboBox(evt.getComponent());
    }//GEN-LAST:event_cb_bancoFocusGained

    private void cb_planoContaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cb_planoContaFocusGained
        setExpandedComboBox(evt.getComponent());
    }//GEN-LAST:event_cb_planoContaFocusGained

    private void cb_fazendaClasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cb_fazendaClasFocusGained
        setExpandedComboBox(evt.getComponent());
    }//GEN-LAST:event_cb_fazendaClasFocusGained

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            //System.out.println("Teste F2");
            tp_pagto.setExpanded(tp_nf.isExpanded());
            cb_tipoDoc.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_F3) {
            //System.out.println("Teste F3");
            tp_nf.setExpanded(tp_nf.isExpanded());
            cb_tipoDoc.requestFocus();
        }
    }//GEN-LAST:event_formKeyPressed

    private void cb_atividadeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_atividadeItemStateChanged

        if (cb_atividade.getSelectedIndex() > 0) {
            carregarCentroResultado(getAtividade(cb_atividade).getID());
        }

    }//GEN-LAST:event_cb_atividadeItemStateChanged

    private void btn_repetirValor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_repetirValor2ActionPerformed
        if (txt_dataPagtoPrevista.getDate() != null) {
            txt_dataPagto.setDate(txt_dataPagtoPrevista.getDate());
        }
    }//GEN-LAST:event_btn_repetirValor2ActionPerformed

    private void btn_repetirValor3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_repetirValor3ActionPerformed
        txt_vlrNF.setText(txt_valorPagto.getText());
    }//GEN-LAST:event_btn_repetirValor3ActionPerformed

    private void txt_DocNFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_DocNFFocusGained
        txt_DocNF.selectAll();
    }//GEN-LAST:event_txt_DocNFFocusGained

    private void btn_pesqEmissor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesqEmissor2ActionPerformed
        FrmConsInventario frmConsulta = new FrmConsInventario(null, false);
        frmConsulta.tb_conInventario.addMouseListener(getConsultaInventarioListener(frmConsulta));
        frmConsulta.setLocationRelativeTo(null);
        frmConsulta.setVisible(true);

    }//GEN-LAST:event_btn_pesqEmissor2ActionPerformed

    private void txt_cpfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cpfFocusLost
        String t = txt_cpf.getText().replaceAll("[^0-9]", "");
        if (!t.equals("")) {
            fornecedorPg = PagamentosD.buscarFornecedor(txt_cpf.getText());
            if (fornecedorPg != null) {
                preencherCamposFornecedor(fornecedorPg);
            } else if (fornecedorPg == null) {
                int editar = JOptionPane.showConfirmDialog(null, "<html> <B>Fornecedor Não Encontrado,</B> <br> Deseja Cadastrar Novo Fornecedor? </html>", "Atenção", JOptionPane.YES_NO_OPTION);
                if (editar == JOptionPane.YES_OPTION) {
                    frmCadFornecedores frmFornecedor = new frmCadFornecedores();
                    this.getParent().add(frmFornecedor);
                    frmFornecedor.btn_novo1.doClick();
                    frmFornecedor.cb_tipoPessoa.setSelectedItem(cb_doc.getSelectedItem());
                    frmFornecedor.txt_cnpj.setText(txt_cpf.getText());
                    frmFornecedor.setVisible(true);
                    frmFornecedor.txt_nomeFantasia.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_txt_cpfFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btn_SalvarPagto;
    javax.swing.JButton btn_addClass;
    javax.swing.JButton btn_anterior;
    javax.swing.JButton btn_avancar;
    javax.swing.JButton btn_cadastrar;
    javax.swing.JButton btn_delClass;
    javax.swing.JButton btn_editarClass;
    javax.swing.JButton btn_editarNF;
    public javax.swing.JButton btn_editarPagto;
    javax.swing.JButton btn_excluirNF;
    public javax.swing.JButton btn_excluirPagto;
    javax.swing.JButton btn_incluirNF;
    javax.swing.JButton btn_pesqEmissor;
    public javax.swing.JButton btn_pesqEmissor2;
    javax.swing.JButton btn_pesquisarPagto;
    javax.swing.JButton btn_pesquisarPlano;
    javax.swing.JButton btn_repetirValor;
    javax.swing.JButton btn_repetirValor1;
    javax.swing.JButton btn_repetirValor2;
    javax.swing.JButton btn_repetirValor3;
    javax.swing.ButtonGroup buttonGroup1;
    javax.swing.ButtonGroup buttonGroup2;
    javax.swing.JComboBox<AtividadeBeans> cb_atividade;
    javax.swing.JComboBox<BancosBeans> cb_banco;
    componentes.UJComboBox cb_centroResultado;
    javax.swing.JComboBox<ContaBancariaBeans> cb_contaOrigem;
    public javax.swing.JComboBox<String> cb_doc;
    javax.swing.JComboBox<String> cb_docNF;
    public javax.swing.JComboBox<PropriedadesBeans> cb_fazendaClas;
    javax.swing.JComboBox<PropriedadesBeans> cb_fazendaNF;
    javax.swing.JComboBox<String> cb_formaPagto;
    javax.swing.JComboBox<MoedaBeans> cb_moeda;
    componentes.UJComboBox cb_nFrota;
    componentes.UJComboBox cb_planoConta;
    javax.swing.JComboBox<TipoDocPagto> cb_tipoDoc;
    public javax.swing.JCheckBox ch_buscarPagto;
    public javax.swing.JCheckBox ch_novoPagto;
    public javax.swing.JCheckBox ch_previsto;
    javax.swing.JCheckBox ch_realizado;
    javax.swing.JPanel jPanel1;
    javax.swing.JPanel jPanel3;
    javax.swing.JPanel jPanel4;
    javax.swing.JScrollPane scPClassifocacao;
    javax.swing.JTable tb_class;
    javax.swing.JTable tb_nf;
    public org.jdesktop.swingx.JXTaskPane tp_clas;
    public org.jdesktop.swingx.JXTaskPane tp_nf;
    org.jdesktop.swingx.JXTaskPane tp_pagto;
    javax.swing.JTextField txt_DocNF;
    javax.swing.JTextField txt_agencia;
    javax.swing.JTextField txt_conta;
    public javax.swing.JFormattedTextField txt_cpf;
    javax.swing.JFormattedTextField txt_cpfNF;
    com.toedter.calendar.JDateChooser txt_dataPagto;
    com.toedter.calendar.JDateChooser txt_dataPagtoPrevista;
    public javax.swing.JTextField txt_descricaoClass;
    com.toedter.calendar.JDateChooser txt_dtEmissao;
    javax.swing.JTextField txt_emissor;
    javax.swing.JTextField txt_idEmissor;
    javax.swing.JTextField txt_idPagto;
    javax.swing.JTextField txt_numDoc;
    javax.swing.JTextField txt_taxaC;
    javax.swing.JTextField txt_titular;
    public javax.swing.JTextField txt_valorClass;
    public javax.swing.JTextField txt_valorPagto;
    public javax.swing.JTextField txt_valorPagtoPrevisto;
    javax.swing.JTextField txt_vlrMoeda;
    javax.swing.JTextField txt_vlrNF;
    // End of variables declaration//GEN-END:variables

    private MouseAdapter getConsultaInventarioListener(FrmConsInventario frmConsulta) {
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linha = frmConsulta.tb_conInventario.getSelectedRow();
                if (e.getClickCount() == 2) {
                    if (linha >= 0) {
                        setComboBoxInventario(cb_nFrota, (Integer) frmConsulta.TbConInventario.getValueAt(linha, frmConsulta.TbConInventario.ID_INVENTARIO));
                        frmConsulta.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Selecione um Número da Frota", "Atenção!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        };
        return adapter;
    }

    private MouseAdapter getConsultaFornecedorAdapter(FrmConsultaFornecedor frmConsulta) {
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linha = frmConsulta.tb_forn.getSelectedRow();
                if (e.getClickCount() == 2) {
                    if (linha >= 0) {
                        fornecedorPg = DiversasHibernate.getFornecedor((Integer) frmConsulta.TbForn.getValueAt(linha, frmConsulta.TbForn.ID));
                        if (fornecedorPg != null) {
                            if (fornecedorPg.getCnpj().length() > 14) {
                                cb_doc.setSelectedItem("CNPJ");
                                txt_cpf.setText(fornecedorPg.getCnpj());
                            } else {
                                cb_doc.setSelectedItem("CPF");
                                txt_cpf.setText(fornecedorPg.getCnpj());
                            }
                            preencherCamposFornecedor(fornecedorPg);
                        }
                        frmConsulta.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Selecione um Número da Frota", "Atenção!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        };
        return adapter;
    }

    private void preencherCamposFornecedor(FornecedoresBeans fornecedorPg) {
        txt_agencia.setText(fornecedorPg.getAgencia());
        txt_conta.setText(fornecedorPg.getConta());
        txt_titular.setText(fornecedorPg.getRazaoSocial());
        setComboBoxBanco(cb_banco, fornecedorPg.getBanco());
        txt_idEmissor.setText(String.valueOf(fornecedorPg.getID()));
        txt_emissor.setText(fornecedorPg.getRazaoSocial());
        cb_docNF.setSelectedIndex(cb_doc.getSelectedIndex());
        txt_cpfNF.setText(fornecedorPg.getCnpj());
        fornecedorNF = fornecedorPg;
        fornecedorClass = fornecedorPg;
    }

    private void setExpandedComboBox(Component c) {
        if (c instanceof JComboBox) {
            ((JComboBox) c).setPopupVisible(true);
        } else if (c instanceof UJComboBox) {
            ((UJComboBox) c).setPopupVisible(true);
        }
    }

    private Boolean testeInteger(String str) {
        try {
            Integer i = new Integer(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private boolean verificarBeans(PagamentosBeans pg) {
        if (pg.getStatus() == false) {
            // Teste para Status Previsto
            if (pg.getDtPrevista() == null) {
                JOptionPane.showMessageDialog(null, "Verifique o Campo Data Prevista!", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
            if (pg.getDtPrevista() != null && Corretores.verificarData(pg.getDtPrevista())) {

            }
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
            if (pg.getDtRealizado() == null) {
                JOptionPane.showMessageDialog(null, "Verifique o Campo Data Realizada!", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
            if (pg.getDtRealizado() != null && Corretores.verificarData(pg.getDtRealizado()) == false) {
                return false;
            }
            if (pg.getValorPagamento() == 0.0000) {
                JOptionPane.showMessageDialog(null, "Verifique o Campo Valor para Pagamento!", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
            if (!pg.getMoedaBeans().getMoeda().equals("Real")) {
                if (pg.getValorEmMoeda() == 0.0000 || pg.getTaxaConverte() == 0.0000) {
                    JOptionPane.showMessageDialog(null, "Verifique os Campos Valor em Moedae Taxa de Conversão!", "Erro", 0, FormatarICO.ICObtnSair());
                    return false;
                }
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
        if (clas.getValorClas() == 0.00) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Valor!", "Erro", 0, FormatarICO.ICObtnSair());
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
            JOptionPane.showMessageDialog(null, "Verifique o Campo Valor!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }

    private void popularBeans(PagamentosBeans pg) {
        pg.setAgencia(txt_agencia.getText());
        pg.setBanco(cb_banco.getSelectedItem().toString());
        pg.setCPF_CNPJ(cb_doc.getSelectedItem().toString());
        pg.setConta(txt_conta.getText());
        pg.setCpf(txt_cpf.getText());
        pg.setDtPrevista(txt_dataPagtoPrevista.getDate());
        pg.setDtRealizado(txt_dataPagto.getDate());
        pg.setDataLancamento(new Date(System.currentTimeMillis()));
        pg.setFormaPagto(cb_formaPagto.getSelectedItem().toString());
        pg.setFornecedorB(fornecedorPg);
        pg.setIDContaOrigem((ContaBancariaBeans) cb_contaOrigem.getSelectedItem());
        pg.setMoeda(cb_moeda.getSelectedItem().toString());
        pg.setMoedaBeans(getMoeda(cb_moeda));
        pg.setStatus(ch_realizado.isSelected());
        pg.setTitular(txt_titular.getText());
        pg.setUsuario(UsuarioLogadoBeans.getLogin());
        pg.setValorCompra(Corretores.ConverterReaisDouble(txt_valorPagtoPrevisto.getText()));
        pg.setValorEmMoeda(Corretores.ConverterReaisDouble(txt_vlrMoeda.getText()));
        pg.setTaxaConverte(Corretores.ConverterReaisDouble(txt_taxaC.getText()));
        pg.setValorPagamento(Corretores.ConverterReaisDouble(txt_valorPagto.getText()));
        pg.setnDocumento(txt_numDoc.getText());
        pg.setnCompra(nCompraGado);
        pg.setnPedido(nPedidoInsumos);

        pg.setListaNotaFiscal(TbNF.getLista());
        pg.setListaClassificacao(TbClass.getLista());
    }

    private void limparCamposClass() {
        cb_atividade.setSelectedIndex(0);
        cb_centroResultado.setSelectedIndex(0);
        cb_planoConta.setSelectedIndex(0);
        cb_fazendaClas.setSelectedIndex(0);
        cb_nFrota.setSelectedIndex(0);
        txt_descricaoClass.setText("");
        txt_valorClass.setText("R$ 0,00");
    }

    private void limparCamposNF() {
        txt_vlrNF.setText("R$ 0,00");
        cb_fazendaNF.setSelectedIndex(0);
        txt_DocNF.setText("0");
        txt_dtEmissao.setDate(null);
    }

    private void limparCampos(Container container) {

        for (int i = 0; i < container.getComponentCount(); i++) {
            Component c = container.getComponent(i);
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");

            } else if (c instanceof JComboBox) {
                ((JComboBox) c).setSelectedIndex(0);
            } else if (c instanceof JFormattedTextField) {
                ((JFormattedTextField) c).setValue(null);
            } else if (c instanceof JDateChooser) {
                ((JDateChooser) c).setDate(null);
            }
        }

    }

    private void limparFormulario() {
        nCompraGado = null;
        nPedidoInsumos = 0;
        limparCampos(jPanel1);
        limparCampos(jPanel3);
        limparCampos(jPanel4);
        TbClass.limpar();
        TbNF.limpar();
        txt_idPagto.setText("");
        ch_novoPagto.setSelected(false);
        ch_buscarPagto.setSelected(false);
        ch_novoPagto.requestFocus();
        txt_valorPagtoPrevisto.setText("R$ 0,00");
        txt_vlrMoeda.setText("R$ 0,00");
        txt_taxaC.setText("R$ 0,00");
        txt_valorPagto.setText("R$ 0,00");
        txt_numDoc.setText("0");
        txt_vlrNF.setText("R$ 0,00");
        txt_DocNF.setText("0");
        txt_valorClass.setText("R$ 0,00");
        if (txt_nCompra != null) {
            jTextNCompra().setVisible(false);
            lbl_nCompra.setVisible(false);
        }
    }

    private void habilitarCampos(Container container, Boolean b) {
        for (int i = 0; i < container.getComponentCount(); i++) {
            Component c = container.getComponent(i);
            c.setEnabled(b);
        }
    }

    private AtividadeBeans getAtividade(JComboBox<AtividadeBeans> combo) {
        return (AtividadeBeans) combo.getSelectedItem();
    }

    private InventarioBeans getInventario(UJComboBox combo) {
        if (combo.getSelectedIndex() > 0) {
            return (InventarioBeans) combo.getSelectedItem();
        } else {
            return null;
        }
    }

    private PropriedadesBeans getFazenda(JComboBox combo) {
        return (PropriedadesBeans) combo.getSelectedItem();
    }

    private PlanoContaBeans getPlanoConta(UJComboBox combo) {
        return (PlanoContaBeans) combo.getSelectedItem();
    }

    private CentroDeResultado getCentroResultado(UJComboBox combo) {
        return (CentroDeResultado) combo.getSelectedItem();
    }

    private MoedaBeans getMoeda(JComboBox<MoedaBeans> combo) {
        return (MoedaBeans) combo.getSelectedItem();
    }

    private TipoDocPagto getTipoDocumentoFiscal(JComboBox<TipoDocPagto> combo) {
        return (TipoDocPagto) combo.getSelectedItem();
    }

    private void setAtividade(JComboBox<AtividadeBeans> combo, Integer id) {
        combo.setSelectedIndex(0);
        for (int i = 0; i < combo.getModel().getSize(); i++) {
            if (Objects.equals(combo.getItemAt(i).getID(), id)) {
                combo.setSelectedIndex(i);
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

    private void setComboBoxContaOrigem(JComboBox<ContaBancariaBeans> combobox, Integer id) {
        combobox.setSelectedIndex(0);
        for (int i = 0; i < combobox.getModel().getSize(); i++) {
            if (combobox.getItemAt(i).getIdConta() == id) {
                combobox.setSelectedIndex(i);
                break;
            }
        }
    }

    private void setComboBoxMoeda(JComboBox<MoedaBeans> combobox, Integer id) {
        combobox.setSelectedIndex(0);
        for (int i = 0; i < combobox.getModel().getSize(); i++) {
            if (Objects.equals(combobox.getItemAt(i).getIdMoeda(), id)) {
                combobox.setSelectedIndex(i);
                break;
            }
        }
    }

    public void setComboFazenda(JComboBox<PropriedadesBeans> combobox, Integer idFazenda) {
        combobox.setSelectedIndex(0);
        for (int i = 0; i < combobox.getModel().getSize(); i++) {
            if (combobox.getItemAt(i).getCodigo() == idFazenda) {
                combobox.setSelectedIndex(i);
                break;
            }
        }
    }

    private void setComboBoxInventario(UJComboBox combo, Integer ID) {
        for (int i = 0; i < combo.getModel().getSize(); i++) {
            InventarioBeans frota = (InventarioBeans) combo.getItemAt(i);
            if (Objects.equals(frota.getID(), ID)) {
                combo.setSelectedIndex(i);
            }
        }
    }

    private void setComboBoxCentroResultado(UJComboBox combo, Long ID) {
        for (int i = 0; i < combo.getModel().getSize(); i++) {
            CentroDeResultado c = (CentroDeResultado) combo.getItemAt(i);
            if (Objects.equals(c.getID(), ID)) {
                combo.setSelectedIndex(i);
            }
        }
    }

    private void setComboBoxTipoDocumento(JComboBox<TipoDocPagto> combo, Integer idTipo) {
        for (int i = 0; i < combo.getModel().getSize(); i++) {
            if (Objects.equals(combo.getItemAt(i).getIdDoc(), idTipo)) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }

    private void setComboBoxPlanoConta(UJComboBox combo, Integer ID) {
        for (int i = 0; i < combo.getModel().getSize(); i++) {
            PlanoContaBeans p = (PlanoContaBeans) combo.getItemAt(i);
            if (Objects.equals(p.getID(), ID)) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }

    private void preecherCamposClass(PagtoClassBeans clas) {
        setAtividade(cb_atividade, clas.getAtividadeBeans().getID());
        if (clas.getCentroResultado() != null) {
            setComboBoxCentroResultado(cb_centroResultado, clas.getCentroResultado().getID());
        }
        cb_planoConta.setSelectedItem(clas.getPlanoConta());
        setComboFazenda(cb_fazendaClas, clas.getFazendaB().getCodigo());
        if (clas.getIdAplicacao() != null) {
            setComboBoxInventario(cb_nFrota, clas.getIdAplicacao().getID());
        }
        txt_descricaoClass.setText(clas.getDescricao());
        txt_valorClass.setText(Corretores.ConverterDoubleReais(clas.getValorClas()));
    }

    private Boolean addClassificacao(PagtoClassBeans clas) {
        try {
            clas.setPagto(PagamentosB);
            clas.setAtividadeBeans(getAtividade(cb_atividade));
            clas.setAtividade(getAtividade(cb_atividade).getDescricao());
            clas.setCentroResultado(getCentroResultado(cb_centroResultado));
            clas.setTipoDespesa(getPlanoConta(cb_planoConta).getGrupoConta().getDescricao());
            clas.setIdAplicacao(getInventario(cb_nFrota));
            clas.setnFrota((getInventario(cb_nFrota) == null ? "-" : (getInventario(cb_nFrota).getnFrota())));
            clas.setFazenda(getFazenda(cb_fazendaClas).getNome());
            clas.setFazendaB(getFazenda(cb_fazendaClas));
            clas.setIdPlanoContas(getPlanoConta(cb_planoConta).getConta());
            clas.setDesConta(getPlanoConta(cb_planoConta).getDescricao());
            clas.setPlanoConta(getPlanoConta(cb_planoConta));
            clas.setDescricao(txt_descricaoClass.getText());
            clas.setValorClas(Corretores.ConverterReaisDouble(txt_valorClass.getText()));
            if (verificaBeans(clas)) {
                TbClass.addBeans(clas);
                limparCamposClass();
                cb_atividade.requestFocus();
                atualizarValorClas();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Adicionar Classificação!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }

    private Boolean addNF(PagtoNFBeans nf) {
        try {
            nf.setPagto(PagamentosB);
            nf.setDtEmissao(txt_dtEmissao.getDate());
            nf.setFazenda(cb_fazendaNF.getSelectedItem().toString());
            nf.setFazendaB(getFazenda(cb_fazendaNF));
            nf.setFornecedor(fornecedorNF.getRazaoSocial());
            nf.setFornecedorB(fornecedorNF);
            nf.setIdFornecedor(new Integer(txt_idEmissor.getText()));
            nf.setIdTipoDoc(getTipoDocumentoFiscal(cb_tipoDoc).getIdDoc());
            nf.setNDOC(new Integer(txt_DocNF.getText()));
            nf.setTipoCad(cb_docNF.getSelectedItem().toString());
            nf.setnCad(txt_cpfNF.getText());
            nf.setTipoDoc(cb_tipoDoc.getSelectedItem().toString());
            nf.setValor(Corretores.ConverterReaisDouble(txt_vlrNF.getText()));
            if (verificaBeans(nf)) {
                TbNF.addBeans(nf);
                limparCamposNF();
                atualizarValorNF();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Adicionar Documento Fiscal!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }

    private Boolean editarClassificacao(PagtoClassBeans clas, int rowIndex) {
        try {
            clas.setPagto(PagamentosB);
            clas.setAtividade(getAtividade(cb_atividade).getDescricao());
            clas.setAtividadeBeans(getAtividade(cb_atividade));
            clas.setCentroResultado(getCentroResultado(cb_centroResultado));
            clas.setTipoDespesa((getPlanoConta(cb_planoConta).getGrupoConta().getDescricao()));
            clas.setIdAplicacao(getInventario(cb_nFrota));
            clas.setnFrota((getInventario(cb_nFrota) == null ? "-" : (getInventario(cb_nFrota).getnFrota())));
            clas.setFazenda(getFazenda(cb_fazendaClas).getNome());
            clas.setFazendaB(getFazenda(cb_fazendaClas));
            clas.setIdPlanoContas(getPlanoConta(cb_planoConta).getConta());
            clas.setDesConta(getPlanoConta(cb_planoConta).getDescricao());
            clas.setPlanoConta(getPlanoConta(cb_planoConta));
            clas.setDescricao(txt_descricaoClass.getText());
            clas.setValorClas(Corretores.ConverterReaisDouble(txt_valorClass.getText()));
            if (verificaBeans(clas)) {
                TbClass.setBeans(clas, rowIndex);
                atualizarValorClas();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Editar Classificação!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }

    private Boolean editarNF(PagtoNFBeans nf, int rowIndex) {
        try {
            nf.setPagto(PagamentosB);
            nf.setDtEmissao(txt_dtEmissao.getDate());
            nf.setFazenda(cb_fazendaNF.getSelectedItem().toString());
            nf.setFazendaB(getFazenda(cb_fazendaNF));
            nf.setFornecedor(fornecedorNF.getRazaoSocial());
            nf.setFornecedorB(fornecedorNF);
            nf.setIdFornecedor(fornecedorNF.getID());
            nf.setIdTipoDoc(getTipoDocumentoFiscal(cb_tipoDoc).getIdDoc());
            nf.setNDOC(new Integer(txt_DocNF.getText()));
            nf.setTipoCad(cb_docNF.getSelectedItem().toString());
            nf.setnCad(txt_cpfNF.getText());
            nf.setTipoDoc(cb_tipoDoc.getSelectedItem().toString());
            nf.setValor(Corretores.ConverterReaisDouble(txt_vlrNF.getText()));
            if (verificaBeans(nf)) {
                TbNF.editarBeans(nf, rowIndex);
                limparCamposNF();
                atualizarValorNF();
            } else {
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Editar Documento Fiscal!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }

    private void preencherCamposNF(PagtoNFBeans nf) {
        setComboBoxTipoDocumento(cb_tipoDoc, nf.getIdTipoDoc());
        fornecedorNF = nf.getFornecedorB();
        cb_docNF.setSelectedItem(nf.getTipoCad());
        txt_cpfNF.setText(nf.getnCad());
        txt_idEmissor.setText((nf.getFornecedorB() == null ? "" : String.valueOf(nf.getFornecedorB().getID())));
        txt_emissor.setText((nf.getFornecedorB() == null ? "" : String.valueOf(nf.getFornecedorB().getRazaoSocial())));
        txt_dtEmissao.setDate(nf.getDtEmissao());
        txt_DocNF.setText(String.valueOf(nf.getNDOC()));
        setComboFazenda(cb_fazendaNF, nf.getFazendaB().getCodigo());
        txt_vlrNF.setText(Corretores.ConverterDoubleReais(nf.getValor()));
    }

    public final void preencherFormulario(PagamentosBeans pg) {
        fornecedorPg = pg.getFornecedorB();
        txt_idPagto.setText(pg.getID().toString());
        txt_agencia.setText(pg.getAgencia());
        setComboBoxBanco(cb_banco, pg.getBanco());
        cb_doc.setSelectedItem(pg.getCPF_CNPJ());
        txt_conta.setText(pg.getConta());
        txt_cpf.setText(pg.getCpf());
        txt_dataPagtoPrevista.setDate(pg.getDtPrevista());
        txt_dataPagto.setDate(pg.getDtRealizado());
        cb_formaPagto.setSelectedItem(pg.getFormaPagto());
        setComboBoxContaOrigem(cb_contaOrigem, pg.getIDContaOrigem().getIdConta());
        setComboBoxMoeda(cb_moeda, pg.getMoedaBeans().getIdMoeda());
        txt_titular.setText(pg.getTitular());
        txt_valorPagtoPrevisto.setText(Corretores.ConverterDoubleReais(pg.getValorCompra()));
        txt_vlrMoeda.setText(Corretores.ConverterDoubleReais(pg.getValorEmMoeda()));
        txt_taxaC.setText(Corretores.ConverterDoubleReais(pg.getTaxaConverte()));
        txt_valorPagto.setText(Corretores.ConverterDoubleReais(pg.getValorPagamento()));
        txt_numDoc.setText(pg.getnDocumento());
        if (pg.getnCompra() != null) {
            jTextNCompra();
            nCompraGado = pg.getnCompra();
            txt_nCompra.setVisible(true);
            txt_nCompra.setText(pg.getnCompra().getID().toString());
        }
        nPedidoInsumos = pg.getnPedido();
        TbNF.addLista(pg.getListaNotaFiscal());
        TbClass.addLista(pg.getListaClassificacao());
        if (pg.getStatus() == true) {
            ch_realizado.setSelected(true);
        } else {
            ch_previsto.setSelected(true);
        }
        atualizarValorNF();
        atualizarValorClas();
    }

    private void atualizarValorNF() {
        tp_nf.setTitle("Documentos Fiscais - " + Corretores.ConverterDoubleReais(TbNF.somarValorTabela(TbNF.VALOR)));
    }

    private void atualizarValorClas() {
        tp_clas.setTitle("Classificação - " + Corretores.ConverterDoubleReais(TbClass.somarValorTabela(TbClass.VALOR)));
    }

    private void pagamentoMinutasFrete(PagamentosBeans pagto) {
        if (OrigemContasAPagar.equals("frmFreteGado")) {
            //System.out.println("Entrou Pagamentos");
            FreteGado.atualizarIDPagamento(pagto);
        }
    }

}
