/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Beans.AnoSafra;
import Beans.ListCategoriaInsumos;
import Beans.ListFazendasBeans;
import Beans.MoedaBeans;
import Icones.FormatarICO;
import javax.swing.JOptionPane;
import DAO.PedidosDAO;
import Beans.PedidosBeans;
import DAO.Diversas;
import static GUI.Principal.ListFazendas;
import static GUI.Principal.listAnoSafra;
import static GUI.Principal.listMoeda;
import static GUI.Principal.listCategoriaInsumos;
import TableModel.TableModelRelPedidos;
import TableModel.TableModelTbInsumos;
import TableModel.TbPedidosInsumosBeans;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import Utilitarios.FixedColumnTable;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
      

/**
 *
 * @author Bruno
 */
public class frmPedidosInsumos extends javax.swing.JInternalFrame {

  Diversas DiversasD;
  CentralizarTabela Centralizar;
  TableModelTbInsumos TbPedInsumos;
  TableModelTbInsumos TbRelInsumos;
  TableModelRelPedidos TbRelPedidos;
  public frmPesquisarInsumo frmPesqInsumos;
  PedidosDAO PedidosD;
  PedidosBeans PedidosB;
  frmCadFornecedores frmFornecedores;
  frmPagamentos pagamentos;
  frmResPagamentos ResumoPagtos;
  frmEntradaInsumos EntradaInsumos;
  TesteCellRenderer testeCellRenderer;
  RendererRelPedidos RendererRelPedidos;
  
    
    public frmPedidosInsumos() {
        initComponents();
        DiversasD = new Diversas();
        PedidosD = new PedidosDAO();
        PedidosB = new PedidosBeans();
        testeCellRenderer = new TesteCellRenderer();
        RendererRelPedidos = new RendererRelPedidos();
        carregarFazendas();
        carregarAnoSafra();
        carregarMoeda();
        carregarTabelaPedInsumos();
        carregarTabelaRelInsumos();
        carregarCategoriaInsumos();
        carregarTabelaRelPedidos();
    }

    private JTable carregarTabelaPedInsumos(){
        tb_pedInsumos.setModel(getTableModelInsumos());
        Centralizar = new CentralizarTabela();
        Centralizar.centralizarTabela(tb_pedInsumos);
        ((DefaultTableCellRenderer)tb_pedInsumos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        //tb_pedInsumos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tb_pedInsumos.getColumnModel().getColumn(0).setPreferredWidth(0);// ID
        tb_pedInsumos.getColumnModel().getColumn(0).setMinWidth(0);
        tb_pedInsumos.getColumnModel().getColumn(0).setMaxWidth(0);
        tb_pedInsumos.getColumnModel().getColumn(2).setPreferredWidth(0);// ID Pedido
        tb_pedInsumos.getColumnModel().getColumn(2).setMinWidth(0);
        tb_pedInsumos.getColumnModel().getColumn(2).setMaxWidth(0);
        tb_pedInsumos.getColumnModel().getColumn(4).setPreferredWidth(0);// ID Fazenda
        tb_pedInsumos.getColumnModel().getColumn(4).setMinWidth(0);
        tb_pedInsumos.getColumnModel().getColumn(4).setMaxWidth(0);
        tb_pedInsumos.getColumnModel().getColumn(6).setPreferredWidth(0);// ID Categoria
        tb_pedInsumos.getColumnModel().getColumn(6).setMinWidth(0);
        tb_pedInsumos.getColumnModel().getColumn(6).setMaxWidth(0);
        tb_pedInsumos.getColumnModel().getColumn(8).setPreferredWidth(0);// ID INsumo
        tb_pedInsumos.getColumnModel().getColumn(8).setMinWidth(0);
        tb_pedInsumos.getColumnModel().getColumn(8).setMaxWidth(0);
        tb_pedInsumos.getColumnModel().getColumn(1).setPreferredWidth(40);// Status
        tb_pedInsumos.getColumnModel().getColumn(3).setPreferredWidth(70); // Nº Pedido
        tb_pedInsumos.getColumnModel().getColumn(5).setPreferredWidth(90);// Fazenda
        tb_pedInsumos.getColumnModel().getColumn(7).setPreferredWidth(110);// Categoria
        tb_pedInsumos.getColumnModel().getColumn(9).setPreferredWidth(100);// Insumo
        tb_pedInsumos.getColumnModel().getColumn(10).setPreferredWidth(40);// Unidade
        tb_pedInsumos.getColumnModel().getColumn(11).setPreferredWidth(80);// Quantidade
        tb_pedInsumos.getColumnModel().getColumn(12).setPreferredWidth(40);// Sifra
        tb_pedInsumos.getColumnModel().getColumn(13).setPreferredWidth(80);// Valor Unitário
        tb_pedInsumos.getColumnModel().getColumn(14).setPreferredWidth(120);// Valor Total
        tb_pedInsumos.getColumnModel().getColumn(15).setPreferredWidth(100);// Quantidade Entregue
        tb_pedInsumos.getColumnModel().getColumn(16).setPreferredWidth(100);// Saldo
        
        tb_pedInsumos.getColumnModel().getColumn(11).setCellRenderer(testeCellRenderer);
        tb_pedInsumos.getColumnModel().getColumn(13).setCellRenderer(testeCellRenderer);
        tb_pedInsumos.getColumnModel().getColumn(14).setCellRenderer(testeCellRenderer);
        tb_pedInsumos.getColumnModel().getColumn(15).setCellRenderer(testeCellRenderer);
        tb_pedInsumos.getColumnModel().getColumn(16).setCellRenderer(testeCellRenderer);
        
        return tb_pedInsumos;
    } 
    
    private JTable carregarTabelaRelInsumos(){
        tb_relInsumos.setModel(getTableModelRelInsumos());
        Centralizar = new CentralizarTabela();
        Centralizar.centralizarTabela(tb_relInsumos);
        ((DefaultTableCellRenderer)tb_relInsumos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        //tb_relInsumos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tb_relInsumos.getColumnModel().getColumn(0).setPreferredWidth(0);// ID
        tb_relInsumos.getColumnModel().getColumn(0).setMinWidth(0);
        tb_relInsumos.getColumnModel().getColumn(0).setMaxWidth(0);
        tb_relInsumos.getColumnModel().getColumn(2).setPreferredWidth(0);// ID Pedido
        tb_relInsumos.getColumnModel().getColumn(2).setMinWidth(0);
        tb_relInsumos.getColumnModel().getColumn(2).setMaxWidth(0);
        tb_relInsumos.getColumnModel().getColumn(4).setPreferredWidth(0);// ID Fazenda
        tb_relInsumos.getColumnModel().getColumn(4).setMinWidth(0);
        tb_relInsumos.getColumnModel().getColumn(4).setMaxWidth(0);
        tb_relInsumos.getColumnModel().getColumn(6).setPreferredWidth(0);// ID Categoria
        tb_relInsumos.getColumnModel().getColumn(6).setMinWidth(0);
        tb_relInsumos.getColumnModel().getColumn(6).setMaxWidth(0);
        tb_relInsumos.getColumnModel().getColumn(8).setPreferredWidth(0);// ID INsumo
        tb_relInsumos.getColumnModel().getColumn(8).setMinWidth(0);
        tb_relInsumos.getColumnModel().getColumn(8).setMaxWidth(0);
        tb_relInsumos.getColumnModel().getColumn(1).setPreferredWidth(40);// Status
        tb_relInsumos.getColumnModel().getColumn(3).setPreferredWidth(90); // Nº Pedido
        tb_relInsumos.getColumnModel().getColumn(5).setPreferredWidth(110);// Fazenda
        tb_relInsumos.getColumnModel().getColumn(7).setPreferredWidth(110);// Categoria
        tb_relInsumos.getColumnModel().getColumn(9).setPreferredWidth(120);// Insumo
        tb_relInsumos.getColumnModel().getColumn(10).setPreferredWidth(40);// Unidade
        tb_relInsumos.getColumnModel().getColumn(11).setPreferredWidth(100);// Quantidade
        tb_relInsumos.getColumnModel().getColumn(12).setPreferredWidth(40);// Sifra
        tb_relInsumos.getColumnModel().getColumn(13).setPreferredWidth(90);// Valor Unitário
        tb_relInsumos.getColumnModel().getColumn(14).setPreferredWidth(130);// Valor Total
        tb_relInsumos.getColumnModel().getColumn(15).setPreferredWidth(110);// Quantidade Entregue
        tb_relInsumos.getColumnModel().getColumn(16).setPreferredWidth(110);// Saldo
        
        tb_relInsumos.getColumnModel().getColumn(11).setCellRenderer(testeCellRenderer);
        tb_relInsumos.getColumnModel().getColumn(13).setCellRenderer(testeCellRenderer);
        tb_relInsumos.getColumnModel().getColumn(14).setCellRenderer(testeCellRenderer);
        tb_relInsumos.getColumnModel().getColumn(15).setCellRenderer(testeCellRenderer);
        tb_relInsumos.getColumnModel().getColumn(16).setCellRenderer(testeCellRenderer);
        
        return tb_relInsumos;
    }
    
    private JTable carregarTabelaRelPedidos(){
        tb_relPedidos.setModel(getTableModelRelPedidos());
        Centralizar = new CentralizarTabela();
        Centralizar.centralizarTabela(tb_relPedidos);
        ((DefaultTableCellRenderer)tb_relPedidos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        //tb_relPedidos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tb_relPedidos.getColumnModel().getColumn(0).setPreferredWidth(55);// id
        tb_relPedidos.getColumnModel().getColumn(2).setPreferredWidth(90);// Data Emissao
        tb_relPedidos.getColumnModel().getColumn(3).setPreferredWidth(90);// Data Vencimento
        tb_relPedidos.getColumnModel().getColumn(4).setPreferredWidth(105);// nPedido
        tb_relPedidos.getColumnModel().getColumn(5).setPreferredWidth(80);// Safra
        tb_relPedidos.getColumnModel().getColumn(6).setPreferredWidth(0);//  id Fornecedor
        tb_relPedidos.getColumnModel().getColumn(6).setMinWidth(0);
        tb_relPedidos.getColumnModel().getColumn(6).setMaxWidth(0);
        tb_relPedidos.getColumnModel().getColumn(7).setPreferredWidth(130);// Fornecedor
        tb_relPedidos.getColumnModel().getColumn(8).setPreferredWidth(0);// id fazenda
        tb_relPedidos.getColumnModel().getColumn(8).setMinWidth(0);
        tb_relPedidos.getColumnModel().getColumn(8).setMaxWidth(0);
        tb_relPedidos.getColumnModel().getColumn(9).setPreferredWidth(110);// fazenda
        tb_relPedidos.getColumnModel().getColumn(10).setPreferredWidth(70);// Moeda
        tb_relPedidos.getColumnModel().getColumn(11).setPreferredWidth(50);// Sifra
        tb_relPedidos.getColumnModel().getColumn(12).setPreferredWidth(130);// Valor Pedido
        tb_relPedidos.getColumnModel().getColumn(13).setPreferredWidth(130);// Valor Insumos
        tb_relPedidos.getColumnModel().getColumn(14).setPreferredWidth(130);// Valor Agendado
        tb_relPedidos.getColumnModel().getColumn(15).setPreferredWidth(130);// Valor Pago em Moeda
        tb_relPedidos.getColumnModel().getColumn(16).setPreferredWidth(130);// Valor Pago em Reais
        tb_relPedidos.getColumnModel().getColumn(17).setPreferredWidth(130);// Valor de NF
        tb_relPedidos.getColumnModel().getColumn(18).setPreferredWidth(90);// Status Pagto

        tb_relPedidos.getColumnModel().getColumn(11).setCellRenderer(RendererRelPedidos);
        tb_relPedidos.getColumnModel().getColumn(12).setCellRenderer(RendererRelPedidos);
        tb_relPedidos.getColumnModel().getColumn(13).setCellRenderer(RendererRelPedidos);
        tb_relPedidos.getColumnModel().getColumn(14).setCellRenderer(RendererRelPedidos);
        tb_relPedidos.getColumnModel().getColumn(15).setCellRenderer(RendererRelPedidos);
        tb_relPedidos.getColumnModel().getColumn(16).setCellRenderer(RendererRelPedidos);
        tb_relPedidos.getColumnModel().getColumn(17).setCellRenderer(RendererRelPedidos);
        
        FixedColumnTable fixedTable = new FixedColumnTable(7, sc_tbPedidos);
        return tb_relPedidos;
    }
    
    public TableModelTbInsumos getTableModelInsumos(){
        if(TbPedInsumos == null ){
            TbPedInsumos = new TableModelTbInsumos() ;
        }
        return TbPedInsumos;
    }
    
    public TableModelTbInsumos getTableModelRelInsumos(){
        if(TbRelInsumos == null ){
            TbRelInsumos = new TableModelTbInsumos() ;
        }
        return TbRelInsumos;
    }
    
    public TableModelRelPedidos getTableModelRelPedidos(){
        if (TbRelPedidos == null) {
            TbRelPedidos = new TableModelRelPedidos();
        }
        return TbRelPedidos;
    }
    
    private void carregarFazendas(){
        ListFazendas = new ArrayList<>();
        if (ListFazendas.isEmpty()) {
            ListFazendas = DiversasD.listaDeFazendas();
        }
        cb_fazenda.addItem("-");
        cb_fazendaInsumo.addItem("-");
        cb_fazendaPesq.addItem("-");
        cb_fazendaPedido.addItem("-");
        for (ListFazendasBeans list : ListFazendas) {
            cb_fazenda.addItem(list);
            cb_fazendaInsumo.addItem(list);
            cb_fazendaPesq.addItem(list);
            cb_fazendaPedido.addItem(list);
        }
    }
     
    private void carregarAnoSafra(){
        listAnoSafra = new ArrayList<>();
        if (listAnoSafra.isEmpty()) {
            listAnoSafra = DiversasD.listAnoSafra();
        }
        cb_anoSafra.addItem("-");
        for (AnoSafra list : listAnoSafra) {
            cb_anoSafra.addItem(list);
        }
               
    }
    
    private void carregarMoeda(){
        listMoeda = new ArrayList<>();
        if (listMoeda.isEmpty()) {
            listMoeda = DiversasD.listMoeda();
        }
        cb_moeda.addItem("-");
        cb_moedaPesq.addItem("-");
        cb_moedaPedido.addItem("-");
        for (MoedaBeans list : listMoeda) {
            cb_moeda.addItem(list);
            cb_moedaPesq.addItem(list);
            cb_moedaPedido.addItem(list);
        }
    }
    
    private void carregarCategoriaInsumos(){
        listCategoriaInsumos = new ArrayList<>();
        if (listCategoriaInsumos.isEmpty()) {
            listCategoriaInsumos = DiversasD.listCategoriaInsumos();
        }
        cb_categoriaPesq.addItem("-");
        for (ListCategoriaInsumos list : listCategoriaInsumos) {
            cb_categoriaPesq.addItem(list);
        }
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupEditar = new javax.swing.JPopupMenu();
        jMenuEditar = new javax.swing.JMenuItem();
        jPopupPedidos = new javax.swing.JPopupMenu();
        jMenuPagamentos = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuPagtosPedido = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuEntradas = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuEditarPedido = new javax.swing.JMenuItem();
        jTp_ficheiro = new javax.swing.JTabbedPane();
        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        btn_novo = new javax.swing.JButton();
        btn_salvarPedido = new javax.swing.JButton();
        btn_editarPedido = new javax.swing.JButton();
        btn_excluirPedido = new javax.swing.JButton();
        javax.swing.JPanel jPanel3 = new javax.swing.JPanel();
        txt_idPedido = new javax.swing.JTextField();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        txt_npedido = new javax.swing.JTextField();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        cb_anoSafra = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        txt_dtEmissao = new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        txt_codFornecedor = new javax.swing.JTextField();
        txt_fornecedor = new javax.swing.JTextField();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        cb_fazenda = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        cb_moeda = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        txt_valorTotal = new javax.swing.JTextField();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        txt_dtVencto =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        txt_observacao = new javax.swing.JTextArea();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        txt_valorPago = new javax.swing.JTextField();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        txt_valorNF = new javax.swing.JTextField();
        btn_pesqFornecedor = new javax.swing.JButton();
        javax.swing.JPanel jPanel4 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel13 = new javax.swing.JLabel();
        txt_insumo = new javax.swing.JTextField();
        javax.swing.JLabel jLabel24 = new javax.swing.JLabel();
        txt_idCategoria = new javax.swing.JTextField();
        txt_categoria = new javax.swing.JTextField();
        javax.swing.JLabel jLabel15 = new javax.swing.JLabel();
        txt_quantidade = new javax.swing.JTextField();
        javax.swing.JLabel jLabel16 = new javax.swing.JLabel();
        txt_unid = new javax.swing.JTextField();
        javax.swing.JLabel jLabel18 = new javax.swing.JLabel();
        txt_vlrUnit = new javax.swing.JTextField();
        javax.swing.JLabel jLabel25 = new javax.swing.JLabel();
        cb_fazendaInsumo = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel22 = new javax.swing.JLabel();
        cb_status = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel23 = new javax.swing.JLabel();
        txt_vlrTotalInsumo = new javax.swing.JTextField();
        btn_incluir = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();
        btn_pesqInsumo = new javax.swing.JButton();
        javax.swing.JPanel jPanel5 = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        tb_pedInsumos = new javax.swing.JTable();
        btn_gerarPagto = new javax.swing.JButton();
        javax.swing.JPanel jPanel8 = new javax.swing.JPanel();
        javax.swing.JPanel jPanel9 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel30 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel31 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel32 = new javax.swing.JLabel();
        txt_dtInicialPedidos = new com.toedter.calendar.JDateChooser();
        txt_dtFinalPedidos = new com.toedter.calendar.JDateChooser();
        javax.swing.JLabel jLabel33 = new javax.swing.JLabel();
        txt_fornecedorPedidos = new javax.swing.JTextField();
        txt_nPedidoPedidos = new javax.swing.JTextField();
        javax.swing.JLabel jLabel36 = new javax.swing.JLabel();
        cb_fazendaPedido = new javax.swing.JComboBox<>();
        btn_pesquisar1 = new javax.swing.JButton();
        javax.swing.JLabel jLabel37 = new javax.swing.JLabel();
        cb_moedaPedido = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel38 = new javax.swing.JLabel();
        cb_statusPedidos = new javax.swing.JComboBox<>();
        sc_tbPedidos = new javax.swing.JScrollPane();
        tb_relPedidos = new javax.swing.JTable();
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        javax.swing.JPanel jPanel6 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel14 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel17 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel19 = new javax.swing.JLabel();
        txt_dtEmiInicial = new com.toedter.calendar.JDateChooser();
        txt_dtEmiFinal = new com.toedter.calendar.JDateChooser();
        javax.swing.JLabel jLabel20 = new javax.swing.JLabel();
        txt_forncedorPesq = new javax.swing.JTextField();
        javax.swing.JLabel jLabel21 = new javax.swing.JLabel();
        txt_produtoPesq = new javax.swing.JTextField();
        txt_npedidoPesquisa = new javax.swing.JTextField();
        javax.swing.JLabel jLabel26 = new javax.swing.JLabel();
        cb_categoriaPesq = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel27 = new javax.swing.JLabel();
        cb_fazendaPesq = new javax.swing.JComboBox<>();
        btn_pesquisar = new javax.swing.JButton();
        javax.swing.JLabel jLabel28 = new javax.swing.JLabel();
        cb_moedaPesq = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel29 = new javax.swing.JLabel();
        javax.swing.JComboBox<String> jComboBox1 = new javax.swing.JComboBox<>();
        javax.swing.JPanel jPanel7 = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane3 = new javax.swing.JScrollPane();
        tb_relInsumos = new javax.swing.JTable();

        jMenuEditar.setText("Editar Pedido");
        jMenuEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEditarActionPerformed(evt);
            }
        });
        jPopupEditar.add(jMenuEditar);

        jMenuPagamentos.setText("Exibir Pagtos do Fornecedor");
        jMenuPagamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuPagamentosActionPerformed(evt);
            }
        });
        jPopupPedidos.add(jMenuPagamentos);
        jPopupPedidos.add(jSeparator1);

        jMenuPagtosPedido.setText("Exibir Pagtos do Pedido");
        jMenuPagtosPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuPagtosPedidoActionPerformed(evt);
            }
        });
        jPopupPedidos.add(jMenuPagtosPedido);
        jPopupPedidos.add(jSeparator2);

        jMenuEntradas.setText("Exibir Entradas do Pedido");
        jMenuEntradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEntradasActionPerformed(evt);
            }
        });
        jPopupPedidos.add(jMenuEntradas);
        jPopupPedidos.add(jSeparator3);

        jMenuEditarPedido.setText("Editar Pedido");
        jMenuEditarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEditarPedidoActionPerformed(evt);
            }
        });
        jPopupPedidos.add(jMenuEditarPedido);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Controle de Pedidos");

        jTp_ficheiro.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        btn_novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/inserir_40x40.png"))); // NOI18N
        btn_novo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_novo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novoActionPerformed(evt);
            }
        });

        btn_salvarPedido.setBackground(new java.awt.Color(255, 255, 255));
        btn_salvarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/salvar.png"))); // NOI18N
        btn_salvarPedido.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_salvarPedido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_salvarPedido.setDisabledIcon(null);
        btn_salvarPedido.setEnabled(false);
        btn_salvarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salvarPedidoActionPerformed(evt);
            }
        });

        btn_editarPedido.setBackground(new java.awt.Color(255, 255, 255));
        btn_editarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/editar_pequeno.png"))); // NOI18N
        btn_editarPedido.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_editarPedido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_editarPedido.setEnabled(false);
        btn_editarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarPedidoActionPerformed(evt);
            }
        });

        btn_excluirPedido.setBackground(new java.awt.Color(255, 255, 255));
        btn_excluirPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/deletar_40x40.png"))); // NOI18N
        btn_excluirPedido.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_excluirPedido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_excluirPedido.setEnabled(false);
        btn_excluirPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirPedidoActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_idPedido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_idPedido.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Nº Pedido");

        txt_npedido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_npedido.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Ano Safra");

        cb_anoSafra.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Emissão");

        txt_dtEmissao.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Fornecedor");

        txt_codFornecedor.setEditable(false);
        txt_codFornecedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_codFornecedor.setEnabled(false);

        txt_fornecedor.setEditable(false);
        txt_fornecedor.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Fazenda");

        cb_fazenda.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Moeda");

        cb_moeda.setEnabled(false);
        cb_moeda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_moedaItemStateChanged(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Valor Total");

        txt_valorTotal.setEnabled(false);
        txt_valorTotal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_valorTotalFocusLost(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Vencimento");

        txt_dtVencto.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Observação");

        txt_observacao.setColumns(20);
        txt_observacao.setRows(5);
        txt_observacao.setEnabled(false);
        jScrollPane1.setViewportView(txt_observacao);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("ID");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Valor Pago");

        txt_valorPago.setEditable(false);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Valor NF");

        txt_valorNF.setEditable(false);

        btn_pesqFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar pequeno_1.png"))); // NOI18N
        btn_pesqFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesqFornecedorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_anoSafra, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_dtEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_codFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txt_fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_pesqFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txt_idPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_npedido, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_moeda, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 25, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_valorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 25, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dtVencto, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_valorPago, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 25, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_valorNF, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_idPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_npedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_pesqFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb_anoSafra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_dtEmissao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_codFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_valorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_dtVencto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_valorNF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb_moeda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_valorPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Insumo");

        txt_idInsumo.setEditable(false);
        txt_idInsumo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_idInsumo.setEnabled(false);

        txt_insumo.setEditable(false);
        txt_insumo.setEnabled(false);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Categoria");

        txt_idCategoria.setEditable(false);
        txt_idCategoria.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_idCategoria.setEnabled(false);

        txt_categoria.setEditable(false);
        txt_categoria.setEnabled(false);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Quantidade");

        txt_quantidade.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_quantidade.setEnabled(false);
        txt_quantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_quantidadeKeyReleased(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Unid");

        txt_unid.setEditable(false);
        txt_unid.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_unid.setEnabled(false);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Valor Unitário");

        txt_vlrUnit.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_vlrUnit.setEnabled(false);
        txt_vlrUnit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_vlrUnitFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_vlrUnitFocusLost(evt);
            }
        });
        txt_vlrUnit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_vlrUnitKeyReleased(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("Fazenda");

        cb_fazendaInsumo.setEnabled(false);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Status");

        cb_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Aberto", "Encerrado" }));
        cb_status.setEnabled(false);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("Valor Total");

        txt_vlrTotalInsumo.setEditable(false);
        txt_vlrTotalInsumo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        btn_incluir.setText("Incluir");
        btn_incluir.setEnabled(false);
        btn_incluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_incluirActionPerformed(evt);
            }
        });

        btn_editar.setText("Editar");
        btn_editar.setEnabled(false);
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });

        btn_excluir.setText("Excluir");
        btn_excluir.setEnabled(false);
        btn_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirActionPerformed(evt);
            }
        });

        btn_pesqInsumo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar pequeno_1.png"))); // NOI18N
        btn_pesqInsumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesqInsumoActionPerformed(evt);
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
                        .addComponent(jLabel13)
                        .addGap(15, 15, 15)
                        .addComponent(txt_idInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txt_insumo, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(btn_pesqInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_idCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txt_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 22, Short.MAX_VALUE)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_fazendaInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 30, Short.MAX_VALUE)
                        .addComponent(jLabel22)
                        .addGap(28, 28, 28)
                        .addComponent(cb_status, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_unid, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_vlrUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_vlrTotalInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_incluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_editar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_excluir)))
                .addGap(39, 39, 39))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txt_idInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_insumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_idCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(cb_fazendaInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)
                            .addComponent(cb_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btn_pesqInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txt_vlrTotalInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txt_quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txt_unid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(txt_vlrUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_incluir)
                    .addComponent(btn_editar)
                    .addComponent(btn_excluir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tb_pedInsumos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb_pedInsumos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_pedInsumosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_pedInsumos);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
        );

        btn_gerarPagto.setBackground(new java.awt.Color(255, 255, 255));
        btn_gerarPagto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/ico_caixa.png"))); // NOI18N
        btn_gerarPagto.setToolTipText("");
        btn_gerarPagto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_gerarPagto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_gerarPagtoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_salvarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_gerarPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_editarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_excluirPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_salvarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_editarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_excluirPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_gerarPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTp_ficheiro.addTab("Inclusão", jPanel2);

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel30.setText("Emissão");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel31.setText("a");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel32.setText("Nº Pedido");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel33.setText("Fornecedor");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel36.setText("Fazenda");

        btn_pesquisar1.setText("Pesquisar");
        btn_pesquisar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisar1ActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel37.setText("Moeda");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel38.setText("Status");

        cb_statusPedidos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Aberto", "Fechado" }));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dtInicialPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_dtFinalPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_fazendaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_statusPedidos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_fornecedorPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nPedidoPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_moedaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_pesquisar1)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txt_dtFinalPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_dtInicialPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(cb_moedaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(txt_nPedidoPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_pesquisar1))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(txt_fornecedorPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(cb_fazendaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38)
                    .addComponent(cb_statusPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        sc_tbPedidos.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tb_relPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb_relPedidos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tb_relPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_relPedidosMouseClicked(evt);
            }
        });
        sc_tbPedidos.setViewportView(tb_relPedidos);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addComponent(sc_tbPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, 992, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(100, 100, 100)
                    .addComponent(sc_tbPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                    .addGap(2, 2, 2)))
        );

        jTp_ficheiro.addTab("Pedidos", jPanel8);

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Emissão");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("a");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Nº Pedido");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Fornecedor");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Produto");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("Categoria");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText("Fazenda");

        btn_pesquisar.setText("Pesquisar");
        btn_pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisarActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setText("Moeda");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setText("Status");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Aberto", "Fechado" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dtEmiInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_dtEmiFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_forncedorPesq, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_produtoPesq, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_pesquisar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_npedidoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_moedaPesq, 0, 116, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_categoriaPesq, 0, 144, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_fazendaPesq, 0, 144, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, 0, 144, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_dtEmiFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_dtEmiInicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel20)
                                .addComponent(txt_forncedorPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21)
                                .addComponent(txt_produtoPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_pesquisar))
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_npedidoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)))
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jPanel6Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(cb_fazendaPesq, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(cb_categoriaPesq, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(cb_moedaPesq, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.CENTER))))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tb_relInsumos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb_relInsumos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_relInsumosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tb_relInsumos);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTp_ficheiro.addTab("Insumos", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTp_ficheiro, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTp_ficheiro)
                .addContainerGap())
        );

        jTp_ficheiro.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed
        btn_salvarPedido.setEnabled(true);
        btn_editarPedido.setEnabled(false);
        txt_idPedido.setEnabled(false);
        habilitarCampos();
        limparCampos();
    }//GEN-LAST:event_btn_novoActionPerformed

    private void btn_salvarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarPedidoActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(null, "Deseja Salvar este Pedido?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION) {
            popularBeans();
            int idPedido = PedidosD.inserirPedido(PedidosB, TbPedInsumos);
            txt_idPedido.setText(String.valueOf(idPedido));
            if (idPedido > 0){
                btn_gerarPagto.doClick();
                desabilitarCampos();
                limparCampos();
            }
        }
    }//GEN-LAST:event_btn_salvarPedidoActionPerformed

    private void btn_editarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarPedidoActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja editar esta Escala de Gado?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION) {
            if (!txt_idPedido.getText().equals("")){
                popularBeans();
                PedidosD.editarPedido(PedidosB, TbPedInsumos);
                limparCampos();
                desabilitarCampos();
                btn_editarPedido.setEnabled(false);
                btn_excluirPedido.setEnabled(false);
                TbPedInsumos.limpar();
            } else {
                JOptionPane.showMessageDialog(null, "Selecione a escala que deseja editar!", "Erro", 0, FormatarICO.ICObtnSair());
            }
        }
    }//GEN-LAST:event_btn_editarPedidoActionPerformed

    private void btn_excluirPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirPedidoActionPerformed
        int excluir = JOptionPane.showConfirmDialog(null, "Atenção, esta ação excluíra permanentemente este pedido, deseja continuar?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (excluir == JOptionPane.YES_OPTION) {
            PedidosB.setID(Integer.parseInt(txt_idPedido.getText()));
            PedidosD.excluirPedido(PedidosB);
            limparCampos();
            desabilitarCampos();
            btn_editarPedido.setEnabled(false);
            btn_excluirPedido.setEnabled(false);
        }
    }//GEN-LAST:event_btn_excluirPedidoActionPerformed

    private void txt_quantidadeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_quantidadeKeyReleased
        calcularVlrTotal();
    }//GEN-LAST:event_txt_quantidadeKeyReleased

    private void txt_vlrUnitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_vlrUnitKeyReleased
        calcularVlrTotal();
    }//GEN-LAST:event_txt_vlrUnitKeyReleased

    private void btn_incluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_incluirActionPerformed
        try {
            inserirInsumo();
            txt_quantidade.setText("");
            txt_unid.setText("");
            txt_vlrTotalInsumo.setText("0.00");
            txt_vlrUnit.setText("0.00");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e + " Erro ao inserir insumo, verifique os campos digitados", "Erro", 0, FormatarICO.ICObtnSair()); 
        }
    }//GEN-LAST:event_btn_incluirActionPerformed

    private void btn_pesqInsumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesqInsumoActionPerformed
            getFrmPesquisar();
    }//GEN-LAST:event_btn_pesqInsumoActionPerformed

    private void txt_valorTotalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorTotalFocusLost
        double vf = Double.parseDouble(txt_valorTotal.getText().replaceAll("[^0-9&&[^,]]", "").replace(",", "."));
        txt_valorTotal.setText(new DecimalFormat(getSifra() + " #,###,##0.00").format(vf));
    }//GEN-LAST:event_txt_valorTotalFocusLost

    private void cb_moedaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_moedaItemStateChanged
        txt_valorTotal.setText("0");
    }//GEN-LAST:event_cb_moedaItemStateChanged

    private void txt_vlrUnitFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_vlrUnitFocusLost
        try {
            double vf = Double.parseDouble(txt_vlrUnit.getText().replace(getSifra(), "").replace(",", "."));
            txt_vlrUnit.setText(new DecimalFormat(getSifra() + " #,###,##0.00").format(vf));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txt_vlrUnitFocusLost

    private void btn_pesqFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesqFornecedorActionPerformed
    this.getParent().add(getFrmFornecedores());
    frmFornecedores.tb_fornecedores.addMouseListener(new java.awt.event.MouseAdapter(){
         @Override
            public void mouseClicked (java.awt.event.MouseEvent evt){
              if (evt.getClickCount() == 2){
                  int i = frmFornecedores.tb_fornecedores.getSelectedRow();
                  txt_codFornecedor.setText(frmFornecedores.tb_fornecedores.getValueAt(i, 0).toString());
                  txt_fornecedor.setText(frmFornecedores.tb_fornecedores.getValueAt(i, 2).toString());
                  frmFornecedores.dispose();
              }  
            }
    });
    frmFornecedores.setVisible(true);
     
    }//GEN-LAST:event_btn_pesqFornecedorActionPerformed

    private void tb_pedInsumosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_pedInsumosMouseClicked
        if (evt.getClickCount() == 2 ){
            preencherCamposInsumos();
            btn_editar.setEnabled(true);
            btn_excluir.setEnabled(true);
            txt_idPedido.setEnabled(false);
            txt_npedido.setEnabled(false); 
            btn_incluir.setEnabled(false);
       }
    }//GEN-LAST:event_tb_pedInsumosMouseClicked

    private void btn_pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisarActionPerformed
        SQLFornecedor();
        PedidosD.buscarInsumos(PedidosB, TbRelInsumos);
    }//GEN-LAST:event_btn_pesquisarActionPerformed

    private void tb_relInsumosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_relInsumosMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3){
            if (jPopupEditar.isVisible() == true ){
                jPopupEditar.setVisible(false);
            } else {
                jPopupEditar.setVisible(true);
                jPopupEditar.show(this, 0, 0);
                jPopupEditar.setLocation(evt.getLocationOnScreen()); 
            }
        }
    }//GEN-LAST:event_tb_relInsumosMouseClicked

    private void jMenuEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuEditarActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja editar este Pedido?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION) {
            PedidosB.setID(Integer.parseInt(tb_relInsumos.getValueAt(tb_relInsumos.getSelectedRow(),2).toString()));
            PedidosD.buscarPedido(PedidosB, TbPedInsumos);
            preencherCamposNF();
            habilitarCampos();
            jTp_ficheiro.setSelectedIndex(0);
            btn_salvarPedido.setEnabled(false);
            btn_editarPedido.setEnabled(true);
            btn_excluirPedido.setEnabled(true);
            btn_incluir.setEnabled(true);
        }
    }//GEN-LAST:event_jMenuEditarActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        int index = tb_pedInsumos.getSelectedRow();
        TbPedInsumos.setValueAt(getCbStatus(), index, 1); // Status
        TbPedInsumos.setValueAt(ListFazendas.get(cb_fazendaInsumo.getSelectedIndex()- 1).getID(), index, 4); // id fazenda
        TbPedInsumos.setValueAt(cb_fazendaInsumo.getSelectedItem().toString(), index, 5); // fazenda
        TbPedInsumos.setValueAt(Integer.parseInt(txt_idCategoria.getText()), index, 6); // id categoria
        TbPedInsumos.setValueAt(txt_categoria.getText(), index, 7); // categoria
        TbPedInsumos.setValueAt(Integer.parseInt(txt_idInsumo.getText()), index, 8); // id insumo
        TbPedInsumos.setValueAt(txt_insumo.getText(), index, 9); // insumo
        TbPedInsumos.setValueAt(txt_unid.getText(), index, 10); // unidade
        TbPedInsumos.setValueAt(Double.parseDouble(txt_quantidade.getText().replace(",", ".")), index, 11); // quantidade
        TbPedInsumos.setValueAt(getSifra(), index, 12); // sifra
        TbPedInsumos.setValueAt(getBigDecimal(txt_vlrUnit.getText()), index, 13); // valor unit
        TbPedInsumos.setValueAt(getBigDecimal(txt_vlrTotalInsumo.getText()), index, 14); // valor total
        limparCamposInsumo();
        btn_editar.setEnabled(false);
        btn_excluir.setEnabled(false);
        btn_incluir.setEnabled(true);
    }//GEN-LAST:event_btn_editarActionPerformed

    private void btn_gerarPagtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_gerarPagtoActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(null, "Deseja gerar um contas a pagar deste pedido?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION) {
           if (!txt_idPedido.getText().equals("")){
                pagamentos = new frmPagamentos();
                this.getParent().add(pagamentos);
                pagamentos.setVisible(true);
                pagamentos.ch_novoPagto.setSelected(true);
                pagamentos.jTextNPedido();
                pagamentos.txt_nPedido.setText(txt_idPedido.getText());
           } else {
                 JOptionPane.showMessageDialog(null, "Selecione um pedido para agendar o pagamento!", "Erro", 0, FormatarICO.ICObtnSair());
           }
        }
    }//GEN-LAST:event_btn_gerarPagtoActionPerformed

    private void txt_vlrUnitFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_vlrUnitFocusGained
        txt_vlrUnit.selectAll();
    }//GEN-LAST:event_txt_vlrUnitFocusGained

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        int index = tb_pedInsumos.getSelectedRow();
        int idInsumo = Integer.parseInt(TbPedInsumos.getValueAt(index, 0).toString());
        if (idInsumo > 0) {
            int excluir = JOptionPane.showConfirmDialog(null, "Atenção, esta ação excluíra permanentemente este insumo, deseja continuar?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (excluir == JOptionPane.YES_OPTION) {
                PedidosD.excluirInsumo(idInsumo);
            }
        } else {
            TbPedInsumos.excluirLinha(index);
        }
        limparCamposInsumo();
        btn_editar.setEnabled(false);
        btn_excluir.setEnabled(false);
        btn_incluir.setEnabled(true);
    }//GEN-LAST:event_btn_excluirActionPerformed

    private void btn_pesquisar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisar1ActionPerformed
      PedidosD.ResumoPedidos(TbRelPedidos, getSQLWherePedidos());
    }//GEN-LAST:event_btn_pesquisar1ActionPerformed

    private void tb_relPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_relPedidosMouseClicked
         if (evt.getButton() == MouseEvent.BUTTON3){
            if (jPopupPedidos.isVisible() == true ){
                jPopupPedidos.setVisible(false);
            } else {
                jPopupPedidos.setVisible(true);
                jPopupPedidos.show(this, 0, 0);
                jPopupPedidos.setLocation(evt.getLocationOnScreen()); 
            }
        }
    }//GEN-LAST:event_tb_relPedidosMouseClicked

    private void jMenuEditarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuEditarPedidoActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja editar este Pedido?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION) {
            int rowIndex = tb_relPedidos.getSelectedRow();
            PedidosB.setID((Integer)(TbRelPedidos.getValueAt(rowIndex,  TbRelPedidos.ID)));
            PedidosD.buscarPedido(PedidosB, TbPedInsumos);
            preencherCamposNF();
            habilitarCampos();
            jTp_ficheiro.setSelectedIndex(0);
            btn_salvarPedido.setEnabled(false);
            btn_editarPedido.setEnabled(true);
            btn_excluirPedido.setEnabled(true);
            btn_incluir.setEnabled(true);
        }
    }//GEN-LAST:event_jMenuEditarPedidoActionPerformed

    private void jMenuPagamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPagamentosActionPerformed
        if (ResumoPagtos == null){
            ResumoPagtos = new frmResPagamentos();
        }
        this.getParent().add(ResumoPagtos);
        ResumoPagtos.setVisible(true);
        //PedidosD.resumoPagtosFornecedor(ResumoPagtos.TbPagtos, TbRelPedidos.getValueAt(tb_relPedidos.getSelectedRow(),6).toString());
    }//GEN-LAST:event_jMenuPagamentosActionPerformed

    private void jMenuPagtosPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPagtosPedidoActionPerformed
         if (ResumoPagtos == null){
            ResumoPagtos = new frmResPagamentos();
        }
        this.getParent().add(ResumoPagtos);
        ResumoPagtos.setVisible(true);
        //PedidosD.resumoPagtosDoPedido(ResumoPagtos.TbPagtos, TbRelPedidos.getValueAt(tb_relPedidos.getSelectedRow(),1).toString());
    }//GEN-LAST:event_jMenuPagtosPedidoActionPerformed

    private void jMenuEntradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuEntradasActionPerformed
        if (EntradaInsumos == null){
           EntradaInsumos = new frmEntradaInsumos();
        }
        this.getParent().add(EntradaInsumos);
        EntradaInsumos.setVisible(true);
        EntradaInsumos.jTp_ficheiro.setSelectedIndex(1);
        PedidosD.consultarEntradas(EntradaInsumos.TbRelInsumos, TbRelPedidos.getValueAt(tb_relPedidos.getSelectedRow(),1).toString());
        
    }//GEN-LAST:event_jMenuEntradasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btn_editar;
    javax.swing.JButton btn_editarPedido;
    javax.swing.JButton btn_excluir;
    javax.swing.JButton btn_excluirPedido;
    javax.swing.JButton btn_gerarPagto;
    javax.swing.JButton btn_incluir;
    javax.swing.JButton btn_novo;
    javax.swing.JButton btn_pesqFornecedor;
    javax.swing.JButton btn_pesqInsumo;
    javax.swing.JButton btn_pesquisar;
    javax.swing.JButton btn_pesquisar1;
    javax.swing.JButton btn_salvarPedido;
    javax.swing.JComboBox<Object> cb_anoSafra;
    javax.swing.JComboBox<Object> cb_categoriaPesq;
    javax.swing.JComboBox<Object> cb_fazenda;
    javax.swing.JComboBox<Object> cb_fazendaInsumo;
    javax.swing.JComboBox<Object> cb_fazendaPedido;
    javax.swing.JComboBox<Object> cb_fazendaPesq;
    javax.swing.JComboBox<Object> cb_moeda;
    javax.swing.JComboBox<Object> cb_moedaPedido;
    javax.swing.JComboBox<Object> cb_moedaPesq;
    javax.swing.JComboBox<String> cb_status;
    javax.swing.JComboBox<String> cb_statusPedidos;
    javax.swing.JMenuItem jMenuEditar;
    javax.swing.JMenuItem jMenuEditarPedido;
    javax.swing.JMenuItem jMenuEntradas;
    javax.swing.JMenuItem jMenuPagamentos;
    javax.swing.JMenuItem jMenuPagtosPedido;
    javax.swing.JPopupMenu jPopupEditar;
    javax.swing.JPopupMenu jPopupPedidos;
    javax.swing.JTabbedPane jTp_ficheiro;
    javax.swing.JScrollPane sc_tbPedidos;
    javax.swing.JTable tb_pedInsumos;
    javax.swing.JTable tb_relInsumos;
    javax.swing.JTable tb_relPedidos;
    public javax.swing.JTextField txt_categoria;
    javax.swing.JTextField txt_codFornecedor;
    com.toedter.calendar.JDateChooser txt_dtEmiFinal;
    com.toedter.calendar.JDateChooser txt_dtEmiInicial;
    com.toedter.calendar.JDateChooser txt_dtEmissao;
    com.toedter.calendar.JDateChooser txt_dtFinalPedidos;
    com.toedter.calendar.JDateChooser txt_dtInicialPedidos;
    com.toedter.calendar.JDateChooser txt_dtVencto;
    javax.swing.JTextField txt_forncedorPesq;
    javax.swing.JTextField txt_fornecedor;
    javax.swing.JTextField txt_fornecedorPedidos;
    public javax.swing.JTextField txt_idCategoria;
    public final javax.swing.JTextField txt_idInsumo = new javax.swing.JTextField();
    javax.swing.JTextField txt_idPedido;
    public javax.swing.JTextField txt_insumo;
    javax.swing.JTextField txt_nPedidoPedidos;
    javax.swing.JTextField txt_npedido;
    javax.swing.JTextField txt_npedidoPesquisa;
    javax.swing.JTextArea txt_observacao;
    javax.swing.JTextField txt_produtoPesq;
    javax.swing.JTextField txt_quantidade;
    javax.swing.JTextField txt_unid;
    javax.swing.JTextField txt_valorNF;
    javax.swing.JTextField txt_valorPago;
    javax.swing.JTextField txt_valorTotal;
    javax.swing.JTextField txt_vlrTotalInsumo;
    javax.swing.JTextField txt_vlrUnit;
    // End of variables declaration//GEN-END:variables

    private void SQLFornecedor(){
        limparSQLBeans();
        if (!txt_forncedorPesq.getText().equals("")){
            PedidosB.setSQLFornecedor(" and p.pedidos_fornecedor like '" + txt_forncedorPesq.getText() + "%' ");
        }
        if (!txt_produtoPesq.getText().equals("")){
            PedidosB.setSQLInsumo(" and pi.insumos_Insumo like '" + txt_produtoPesq.getText() + "%' ");
        }
         if (!txt_npedidoPesquisa.getText().equals("")){
            PedidosB.setSQLnPedido(" and pi.insumos_npedido like '" + txt_npedidoPesquisa.getText() + "%' ");
        }       
        if (cb_moedaPesq.getSelectedIndex() > 0){
            String sifra = listMoeda.get(cb_moedaPesq.getSelectedIndex() -1).getSifra();
            PedidosB.setSQLSifra(" and pi.insumos_sifra = '" + sifra + "'" );
        }    
        if (cb_categoriaPesq.getSelectedIndex() > 0){
            String idCategoria = String.valueOf(listCategoriaInsumos.get(cb_categoriaPesq.getSelectedIndex() -1).getID());
            PedidosB.setSQLIdCategoria(" and pi.insumos_idCategoria = " + idCategoria );
        }  
        if (cb_fazendaPesq.getSelectedIndex() > 0){
            String idFazenda = String.valueOf(ListFazendas.get(cb_fazendaPesq.getSelectedIndex() -1 ).getID());
            PedidosB.setSQLIdFazenda(" and pi.insumos_idFazenda = " + idFazenda );
        }  
    }
    
    private void limparSQLBeans(){
        PedidosB.setSQLFornecedor("");
        PedidosB.setSQLInsumo("");
        PedidosB.setSQLnPedido("");
        PedidosB.setSQLSifra("");
        PedidosB.setSQLIdCategoria("");
        PedidosB.setSQLIdFazenda("");
        
    }            
    
    private void preencherCamposInsumos(){
        int index = tb_pedInsumos.getSelectedRow();
        txt_idInsumo.setText(TbPedInsumos.getValueAt(index,8).toString());
        txt_insumo.setText(TbPedInsumos.getValueAt(index,9).toString());
        txt_idCategoria.setText(TbPedInsumos.getValueAt(index,6).toString());
        txt_categoria.setText(TbPedInsumos.getValueAt(index,7).toString());
        cb_fazendaInsumo.setSelectedIndex(Integer.parseInt(TbPedInsumos.getValueAt(index,4).toString()));
        if (Boolean.parseBoolean(TbPedInsumos.getValueAt(index,1).toString()) == false ) {
            cb_status.setSelectedIndex(1);
        } else {
            cb_status.setSelectedIndex(2);
        }
        
        txt_quantidade.setText(TbPedInsumos.getValueAt(index,11).toString().replace(".", ","));
        txt_unid.setText(TbPedInsumos.getValueAt(index,10).toString());
        String Sifra = TbPedInsumos.getValueAt(index,12).toString();
        String vlrUnit = new DecimalFormat(Sifra + " #,###,##0.00").format(Double.parseDouble(TbPedInsumos.getValueAt(index,13).toString()));
        String vlrTotal = new DecimalFormat(Sifra + " #,###,##0.00").format(Double.parseDouble(TbPedInsumos.getValueAt(index,14).toString()));
        txt_vlrUnit.setText(vlrUnit);
        txt_vlrTotalInsumo.setText(vlrTotal);
     
    }
    
    private void preencherCamposNF(){
        txt_idPedido.setText(PedidosB.getID().toString());
        txt_npedido.setText(PedidosB.getnPedido());
        cb_anoSafra.setSelectedIndex(getIDSafra(PedidosB.getSafra())+1);
      try {
          txt_dtEmissao.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(PedidosB.getDtEmissao()));
          txt_dtVencto.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(PedidosB.getDtVencimento()));
      } catch (ParseException ex) {
          Logger.getLogger(frmPedidosInsumos.class.getName()).log(Level.SEVERE, null, ex);
      }
        txt_codFornecedor.setText(PedidosB.getIdFornecedor().toString());
        txt_fornecedor.setText(PedidosB.getFornecedor());
        cb_fazenda.setSelectedIndex(PedidosB.getIdFazenda());
        cb_moeda.setSelectedIndex(getIDMoeda(PedidosB.getMoeda())+1);
        txt_valorTotal.setText(new DecimalFormat(getSifra() + " #,###,##0.00").format(PedidosB.getValorTotal()));
        txt_valorPago.setText(new DecimalFormat(getSifra() + " #,###,##0.00").format(PedidosB.getValorPago()));
        txt_valorNF.setText(new DecimalFormat("R$ #,###,##0.00").format(PedidosB.getValorNF()));;
        txt_observacao.setText(PedidosB.getObservacao());
    }
    
    private Integer getIDFazenda(String fazenda){
        int i = 0;
        for (i = 0; i < ListFazendas.size(); i++){
            if (ListFazendas.get(i).getNomeFazenda().equals(fazenda)){
                return i;
            }
        }
        return 0;
    }
    
    private Integer getIDMoeda(String moeda){
        int i = 0;  
        for (i = 0; i < listMoeda.size(); i++){
            if (listMoeda.get(i).getMoeda().equals(moeda)){
                return i;
            }
        }   
        return 0;
    }
    
    private Integer getIDSafra(String safra){
        int i = 0;
        for (i = 0; i < listAnoSafra.size(); i++){
            if (listAnoSafra.get(i).getAnoSafra().equals(safra)){
                return i;
            }
        }
        return 0;
    }
    
    private Boolean getCbStatus(){
        if (cb_status.getSelectedItem().equals("Aberto")){
            return false;
        } else {
            return true;
        }
    }
    
    private void inserirInsumo(){
        if (!txt_npedido.getText().equals("")){
            TbPedidosInsumosBeans beans = new TbPedidosInsumosBeans();
            beans.setId(0);
            beans.setStatus(false);
            beans.setIdPedido(0);
            beans.setnPedido(txt_npedido.getText());
            beans.setIdFazenda(ListFazendas.get(cb_fazendaInsumo.getSelectedIndex()- 1).getID());
            beans.setFazenda(cb_fazendaInsumo.getSelectedItem().toString());
            beans.setIdCategoria(Integer.parseInt(txt_idCategoria.getText()));
            beans.setCategoria(txt_categoria.getText());
            beans.setIdInsumo(Integer.parseInt(txt_idInsumo.getText()));
            beans.setInsumo(txt_insumo.getText());
            beans.setUnidade(txt_unid.getText());
            beans.setQuantidade(Double.parseDouble(txt_quantidade.getText().replace(",", ".")));
            beans.setSifra(getSifra());      
            beans.setValorUnit(getBigDecimal(txt_vlrUnit.getText()));
            beans.setValorTotal(getBigDecimal(txt_vlrTotalInsumo.getText()));
            beans.setQtEntregue(0.0);
            beans.setSaldoEntregar(Double.parseDouble(txt_quantidade.getText().replace(",", ".")));
            TbPedInsumos.addBeans(beans);
        }
    }
    
    private frmCadFornecedores getFrmFornecedores(){
         if (frmFornecedores == null){
             frmFornecedores = new frmCadFornecedores();
         }
        return frmFornecedores; 
    }
    
    private frmPesquisarInsumo getFrmPesquisar(){
        if (frmPesqInsumos == null){
            frmPesqInsumos = new frmPesquisarInsumo(null, true);
            frmPesqInsumos.setLocationRelativeTo(null);
        }
        frmPesqInsumos.setVisible(true);
        return frmPesqInsumos;
    }
    
    private boolean verificarBeans(){
        try {
            BigDecimal valorT = getBigDecimal(txt_valorTotal.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Verifique o campo valor total.", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }
    
    final void popularBeans(){
        if (verificarBeans()){
            PedidosB.setnPedido(txt_npedido.getText());
            PedidosB.setSafra(cb_anoSafra.getSelectedItem().toString());
            PedidosB.setDtEmissao(new SimpleDateFormat("dd/MM/yyyy").format(txt_dtEmissao.getDate()));
            PedidosB.setIdFornecedor(Integer.parseInt(txt_codFornecedor.getText()));
            PedidosB.setFornecedor(txt_fornecedor.getText());
            PedidosB.setIdFazenda(getIdFazenda());
            PedidosB.setFazenda(cb_fazenda.getSelectedItem().toString());
            PedidosB.setMoeda(cb_moeda.getSelectedItem().toString());   
            PedidosB.setValorTotal(getBigDecimal(txt_valorTotal.getText()));
            PedidosB.setDtVencimento(new SimpleDateFormat("dd/MM/yyyy").format(txt_dtVencto.getDate()));
            PedidosB.setObservacao(txt_observacao.getText());
        }
    }
    
    private Integer getIdFazenda(){
        return ListFazendas.get(cb_fazenda.getSelectedIndex() -1).getID();
    }
    
    final void limparCampos(){
        txt_idPedido.setText("");
        txt_npedido.setText("");
        cb_anoSafra.setSelectedItem("-");
        txt_dtEmissao.setDate(null);
        txt_codFornecedor.setText("");
        txt_fornecedor.setText("");
        cb_fazenda.setSelectedItem("-");
        cb_moeda.setSelectedItem("-");
        txt_valorTotal.setText("");
        txt_dtVencto.setDate(null);
        txt_observacao.setText("");
        TbPedInsumos.limpar();
    }
    
    final void limparCamposInsumo(){
        txt_quantidade.setText("");
        txt_unid.setText("");
        txt_vlrTotalInsumo.setText("");
        txt_vlrUnit.setText("");
        txt_idInsumo.setText("");
        txt_insumo.setText("");
        txt_idCategoria.setText("");
        txt_categoria.setText("");
        cb_fazendaInsumo.setSelectedItem("-");
        cb_status.setSelectedItem("-");
        
    }
    
    final void habilitarCampos(){
        txt_idPedido.setEnabled(false);
        txt_npedido.setEnabled(true);
        cb_anoSafra.setEnabled(true);
        txt_dtEmissao.setEnabled(true);
        txt_codFornecedor.setEnabled(true);
        txt_fornecedor.setEnabled(true);
        cb_fazenda.setEnabled(true);
        cb_moeda.setEnabled(true);
        txt_valorTotal.setEnabled(true);
        txt_dtVencto.setEnabled(true);
        txt_observacao.setEnabled(true);
        
        //campos insumos
        txt_quantidade.setEnabled(true);
        txt_unid.setEnabled(true);
        txt_vlrTotalInsumo.setEnabled(true);
        txt_vlrUnit.setEnabled(true);
        txt_idInsumo.setEnabled(true);
        txt_insumo.setEnabled(true);
        txt_idCategoria.setEnabled(true);
        txt_categoria.setEnabled(true);
        cb_fazendaInsumo.setEnabled(true);
        cb_status.setEnabled(true);
        btn_incluir.setEnabled(true);
                
    }
    
    final void desabilitarCampos(){
        txt_npedido.setEnabled(false);
        cb_anoSafra.setEnabled(false);
        txt_dtEmissao.setEnabled(false);
        txt_codFornecedor.setEnabled(false);
        txt_fornecedor.setEnabled(false);
        cb_fazenda.setEnabled(false);
        cb_moeda.setEnabled(false);
        txt_valorTotal.setEnabled(false);
        txt_dtVencto.setEnabled(false);
        txt_observacao.setEnabled(false);
        //INSUMOS
        txt_quantidade.setEnabled(false);
        txt_unid.setEnabled(false);
        txt_vlrTotalInsumo.setEnabled(false);
        txt_vlrUnit.setEnabled(false);
        txt_idInsumo.setEnabled(false);
        txt_insumo.setEnabled(false);
        txt_idCategoria.setEnabled(false);
        txt_categoria.setEnabled(false);
        cb_fazendaInsumo.setEnabled(false);
        cb_status.setEnabled(false);
        btn_incluir.setEnabled(false);
        
    }

    final String getSifra(){
        if (cb_moeda.getSelectedIndex() != 0){
            return listMoeda.get(cb_moeda.getSelectedIndex()-1).getSifra();
        }
        return "";
    }
    
    final void calcularVlrTotal(){
     if (cb_moeda.getSelectedIndex() != 0){
        try {
            double qt = Double.parseDouble(txt_quantidade.getText().replace(",", "."));
            double vlrUnit = Double.parseDouble(txt_vlrUnit.getText().replace(getSifra(), "").replace(",", "."));
            double vlrTotal = qt * vlrUnit;
            txt_vlrTotalInsumo.setText(new DecimalFormat(getSifra() + " #,###,##0.00").format(vlrTotal));
        } catch (Exception e) {
          
        }
     }  else {
        // JOptionPane.showMessageDialog(null, "O campo Moeda deve ser selecionado.", "Erro", 0, FormatarICO.ICObtnSair());
     }
    }
    
    private BigDecimal getBigDecimal(String valor){
        String vf = valor.replaceAll("[^0-9&&[^,]]", "").replace(",", ".");
        return new BigDecimal(vf);
    }

    private String getSQLWherePedidos(){
        String s = "%'";
        if (!txt_fornecedorPedidos.getText().equals("")){
            s = txt_fornecedorPedidos.getText() + "%'";
        }
        if (txt_dtInicialPedidos.getDate() != null || txt_dtFinalPedidos.getDate() != null){
            String dtInicial = new SimpleDateFormat("dd/MM/yyyy").format(txt_dtInicialPedidos.getDate());
            String dtFinal =   new SimpleDateFormat("dd/MM/yyyy").format(txt_dtInicialPedidos.getDate());
            s += " and between '" + Corretores.ConverterParaSQL(dtInicial) + "' and '" + Corretores.ConverterParaSQL(dtFinal) + "'";
        }
        if (!cb_fazendaPedido.getSelectedItem().equals("-")){
            s += " and pedidos_fazenda = '" + cb_fazendaPedido.getSelectedItem().toString() + "'";
        }
        if (!cb_moedaPedido.getSelectedItem().equals("-")){
            s += " and pedidos_moeda = '" + cb_moedaPedido.getSelectedItem().toString() + "'";
        }
        
        return s;
        
    }
    
    class TesteCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setHorizontalAlignment(SwingConstants.CENTER);
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            Object val = table.getValueAt(row, column);

            if (val instanceof BigDecimal) {
                BigDecimal valor = (BigDecimal) val;
                String Sifra = table.getValueAt(row, 12).toString();
                if (valor != null) {
                    setText(new DecimalFormat(Sifra + " #,###,##0.00").format(valor));
                    setHorizontalAlignment(SwingConstants.RIGHT);
                } else {
                    setText("");
                }
            } else if (val instanceof Double) {
                Double valorD = (Double) val;
                setText(new DecimalFormat("#,###,##0.00").format(valorD));
            }

            double Saldo = Double.parseDouble(table.getValueAt(row, 16).toString());
            if (isSelected) {
                return this;
            }
            if (column == 16) {
                if (Saldo == 0) {
                    setBackground(Color.GREEN);
                } else if (Saldo != 0) {
                    setBackground(Color.RED);
                }
            } else {
                setBackground(table.getBackground());
                setForeground(table.getForeground());
            }
            return this;
        }
    }

    class RendererRelPedidos extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setHorizontalAlignment(SwingConstants.CENTER);
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            Object val = table.getValueAt(row, column);

            if (val instanceof BigDecimal) {
                BigDecimal valor = (BigDecimal) val;
                String Sifra;
                if (column == 16 || column == 17) {
                    Sifra = "R$";
                } else {
                    Sifra = table.getValueAt(row, 11).toString();
                }
                if (valor != null) {
                    setText(new DecimalFormat(Sifra + " #,###,##0.00").format(valor));
                    setHorizontalAlignment(SwingConstants.RIGHT);
                } else {
                    setText("");
                }
            }
            return this;
        }
    }
    
    
}
