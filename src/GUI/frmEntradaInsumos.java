/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Beans.EntradaInsumosBeans;
import Beans.ListFazendasBeans;
import DAO.Diversas;
import DAO.EntradaInsumosDAO;
import static GUI.Principal.ListFazPermitidas;
import static GUI.Principal.ListFazendas;
import Icones.FormatarICO;
import TableModel.TableModelEntInsumos;
import TableModel.TableModelRelInsumos;
import TableModel.TbEntradaInsumosBeans;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import Utilitarios.ReaisCellRenderer;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Bruno
 */
public class frmEntradaInsumos extends javax.swing.JInternalFrame {

    Diversas DiversasD;
    MaskFormatter PlacaMask;
    EntradaInsumosDAO EntradaD;
    EntradaInsumosBeans EntradaB;
    frmPesquisarPedInsumo pesquisaPedido;
    frmPesquisarPedInsumo pesquisaInsumo;
    frmPesquisarInsumo frmPesqInsumos;
    CentralizarTabela Centralizar;
    TableModelEntInsumos TbInsumos;
    TableModelRelInsumos TbRelInsumos;
    DefaultComboBoxModel cbModelFornecedor;
    
    public frmEntradaInsumos() {
        initComponents();
        DiversasD = new Diversas();
        EntradaD = new EntradaInsumosDAO();
        EntradaB = new EntradaInsumosBeans();
        TbInsumos = new TableModelEntInsumos();
        carregarTabelaPedInsumos();
        carregarTabelEntradas();
        carregarFazendas();
        carregarFazPermitidas();
        maskFormaterCPF();
        txt_placa.setFormatterFactory(new DefaultFormatterFactory(PlacaMask));
        txt_dtInicial.setDate(Corretores.PrimeiroDiaMes());
        txt_dtFinal.setDate(Corretores.UltimoDiaMes());

    }

    private JTable carregarTabelaPedInsumos() {
        tb_pedInsumos.setModel(getTableModelInsumos());
        Centralizar = new CentralizarTabela();
        Centralizar.centralizarTabela(tb_pedInsumos);
        ((DefaultTableCellRenderer) tb_pedInsumos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        //tb_pedInsumos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tb_pedInsumos.getColumnModel().getColumn(0).setPreferredWidth(0);// ID
        tb_pedInsumos.getColumnModel().getColumn(0).setMinWidth(0);
        tb_pedInsumos.getColumnModel().getColumn(0).setMaxWidth(0);
        tb_pedInsumos.getColumnModel().getColumn(3).setPreferredWidth(0);// ID Pedido
        tb_pedInsumos.getColumnModel().getColumn(3).setMinWidth(0);
        tb_pedInsumos.getColumnModel().getColumn(3).setMaxWidth(0);
        tb_pedInsumos.getColumnModel().getColumn(5).setPreferredWidth(0);// ID Fazenda
        tb_pedInsumos.getColumnModel().getColumn(5).setMinWidth(0);
        tb_pedInsumos.getColumnModel().getColumn(5).setMaxWidth(0);
        tb_pedInsumos.getColumnModel().getColumn(7).setPreferredWidth(0);// ID Categoria
        tb_pedInsumos.getColumnModel().getColumn(7).setMinWidth(0);
        tb_pedInsumos.getColumnModel().getColumn(7).setMaxWidth(0);
        tb_pedInsumos.getColumnModel().getColumn(9).setPreferredWidth(0);// ID INsumo
        tb_pedInsumos.getColumnModel().getColumn(9).setMinWidth(0);
        tb_pedInsumos.getColumnModel().getColumn(9).setMaxWidth(0);
        tb_pedInsumos.getColumnModel().getColumn(1).setPreferredWidth(40);// Status
        tb_pedInsumos.getColumnModel().getColumn(4).setPreferredWidth(70); // Nº Pedido
        tb_pedInsumos.getColumnModel().getColumn(6).setPreferredWidth(90);// Fazenda
        tb_pedInsumos.getColumnModel().getColumn(8).setPreferredWidth(110);// Categoria
        tb_pedInsumos.getColumnModel().getColumn(10).setPreferredWidth(100);// Insumo
        tb_pedInsumos.getColumnModel().getColumn(11).setPreferredWidth(40);// Unidade
        tb_pedInsumos.getColumnModel().getColumn(12).setPreferredWidth(80);// Quantidade
        tb_pedInsumos.getColumnModel().getColumn(13).setPreferredWidth(40);// Sifra
        tb_pedInsumos.getColumnModel().getColumn(14).setPreferredWidth(80);// Valor Unitário
        tb_pedInsumos.getColumnModel().getColumn(15).setPreferredWidth(120);// Valor Total
        tb_pedInsumos.getColumnModel().getColumn(16).setPreferredWidth(100);// Quantidade Entregue
        tb_pedInsumos.getColumnModel().getColumn(17).setPreferredWidth(100);// Saldo

        tb_pedInsumos.getColumnModel().getColumn(14).setCellRenderer(new ReaisCellRenderer());
        tb_pedInsumos.getColumnModel().getColumn(15).setCellRenderer(new ReaisCellRenderer());

        return tb_pedInsumos;
    }

    private JTable carregarTabelEntradas() {
        tb_entradas.setModel(getTableModelRelInsumos());
        Centralizar = new CentralizarTabela();
        Centralizar.centralizarTabela(tb_entradas);
        ((DefaultTableCellRenderer) tb_entradas.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        //tb_entradas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tb_entradas.getColumnModel().getColumn(0).setPreferredWidth(40);// ID
        tb_entradas.getColumnModel().getColumn(0).setMinWidth(40);
        tb_entradas.getColumnModel().getColumn(0).setMaxWidth(40);
        tb_entradas.getColumnModel().getColumn(2).setPreferredWidth(40);// ID NF
        tb_entradas.getColumnModel().getColumn(2).setMinWidth(40);
        tb_entradas.getColumnModel().getColumn(2).setMaxWidth(40);
        tb_entradas.getColumnModel().getColumn(3).setPreferredWidth(0);// ID ITEM PEDIDO
        tb_entradas.getColumnModel().getColumn(3).setMinWidth(0);
        tb_entradas.getColumnModel().getColumn(3).setMaxWidth(0);
        tb_entradas.getColumnModel().getColumn(5).setPreferredWidth(80);// ID Fazenda
        tb_entradas.getColumnModel().getColumn(5).setMinWidth(80);
        tb_entradas.getColumnModel().getColumn(5).setMaxWidth(120);
        tb_entradas.getColumnModel().getColumn(7).setPreferredWidth(0);// ID Categoria
        tb_entradas.getColumnModel().getColumn(7).setMinWidth(0);
        tb_entradas.getColumnModel().getColumn(7).setMaxWidth(0);
        tb_entradas.getColumnModel().getColumn(9).setPreferredWidth(70);// ID INsumo
        tb_entradas.getColumnModel().getColumn(9).setMinWidth(70);
        tb_entradas.getColumnModel().getColumn(9).setMaxWidth(90);
        tb_entradas.getColumnModel().getColumn(11).setPreferredWidth(0);// ID Cetegoria
        tb_entradas.getColumnModel().getColumn(11).setMinWidth(0);
        tb_entradas.getColumnModel().getColumn(11).setMaxWidth(0);
        tb_entradas.getColumnModel().getColumn(13).setPreferredWidth(0);// ID INsumo
        tb_entradas.getColumnModel().getColumn(13).setMinWidth(0);
        tb_entradas.getColumnModel().getColumn(13).setMaxWidth(0);
        tb_entradas.getColumnModel().getColumn(14).setPreferredWidth(100);// Insumo

        tb_entradas.getColumnModel().getColumn(15).setPreferredWidth(80);// Quantidade
        tb_entradas.getColumnModel().getColumn(16).setPreferredWidth(80);// Valor Unitário
        tb_entradas.getColumnModel().getColumn(17).setPreferredWidth(120);// Valor Total

        tb_entradas.getColumnModel().getColumn(16).setCellRenderer(new ReaisCellRenderer());
        tb_entradas.getColumnModel().getColumn(17).setCellRenderer(new ReaisCellRenderer());
        return tb_entradas;
    }

    public TableModelEntInsumos getTableModelInsumos() {
        if (TbInsumos == null) {
            TbInsumos = new TableModelEntInsumos();
        }
        return TbInsumos;
    }

    public TableModelRelInsumos getTableModelRelInsumos() {
        if (TbRelInsumos == null) {
            TbRelInsumos = new TableModelRelInsumos();
        }
        return TbRelInsumos;
    }

    private void carregarFazendas() {

        if (ListFazendas == null) {
            ListFazendas = new ArrayList<>();
            ListFazendas = DiversasD.listaDeFazendas();
        }
        cb_fazenda.addItem("-");
        for (ListFazendasBeans list : ListFazendas) {
            cb_fazenda.addItem(list);
        }
    }

    private void carregarFazPermitidas() {
        ListFazPermitidas = new ArrayList<>();
        if (ListFazPermitidas.isEmpty()) {
            ListFazPermitidas = DiversasD.ListpmFazenda();
        }
        cb_fazendaInsumo.addItem("-");
        cb_pesqFazenda.addItem("-");
        for (ListFazendasBeans list : ListFazPermitidas) {
            cb_fazendaInsumo.addItem(list);
            cb_pesqFazenda.addItem(list);
        }
    }
    
    private DefaultComboBoxModel getCBoxModelFornecedor(){
        if (cbModelFornecedor == null) {
            cbModelFornecedor = new DefaultComboBoxModel();
        }
        cbModelFornecedor.removeAllElements();
        cbModelFornecedor.addElement("-");
        return cbModelFornecedor;
    } 

    private void maskFormaterCPF() {
        try {
            PlacaMask = new MaskFormatter("AAA-####");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupEditar = new javax.swing.JPopupMenu();
        jMenuEditar = new javax.swing.JMenuItem();
        jTp_ficheiro = new javax.swing.JTabbedPane();
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel13 = new javax.swing.JLabel();
        txt_idNF = new javax.swing.JTextField();
        txt_dtEntrada = new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        cb_operacao = new javax.swing.JComboBox<>();
        txt_nPedido = new javax.swing.JTextField();
        btn_buscarPedido = new javax.swing.JButton();
        txt_idFornecedor = new javax.swing.JTextField();
        cb_tioDoc = new javax.swing.JComboBox<>();
        txt_dtEmissao = new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        txt_nDoc = new javax.swing.JTextField();
        txt_vlrDoc = new javax.swing.JTextField();
        cb_fazenda = new javax.swing.JComboBox<>();
        txt_pesoBruto = new javax.swing.JTextField();
        txt_tara = new javax.swing.JTextField();
        txt_pesoLiq = new javax.swing.JTextField();
        javax.swing.JLabel jLabel14 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel15 = new javax.swing.JLabel();
        txt_pesoNF = new javax.swing.JTextField();
        javax.swing.JLabel jLabel20 = new javax.swing.JLabel();
        cb_tipoFrete = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel21 = new javax.swing.JLabel();
        txt_nCTE = new javax.swing.JTextField();
        txt_placa = new javax.swing.JFormattedTextField();
        txt_fornecedor = new javax.swing.JComboBox<>();
        javax.swing.JPanel jPanel4 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel16 = new javax.swing.JLabel();
        txt_insumo = new javax.swing.JTextField();
        javax.swing.JLabel jLabel24 = new javax.swing.JLabel();
        txt_idCategoria = new javax.swing.JTextField();
        txt_categoria = new javax.swing.JTextField();
        javax.swing.JLabel jLabel17 = new javax.swing.JLabel();
        txt_quantidade = new javax.swing.JTextField();
        javax.swing.JLabel jLabel18 = new javax.swing.JLabel();
        txt_unid = new javax.swing.JTextField();
        javax.swing.JLabel jLabel19 = new javax.swing.JLabel();
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
        btn_novo = new javax.swing.JButton();
        btn_salvarPedido = new javax.swing.JButton();
        btn_editarPedido = new javax.swing.JButton();
        btn_excluirPedido = new javax.swing.JButton();
        javax.swing.JPanel jPanel3 = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        tb_pedInsumos = new javax.swing.JTable();
        javax.swing.JPanel jPanel5 = new javax.swing.JPanel();
        javax.swing.JPanel jPanel6 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel26 = new javax.swing.JLabel();
        txt_dtInicial = new com.toedter.calendar.JDateChooser();
        javax.swing.JLabel jLabel27 = new javax.swing.JLabel();
        txt_dtFinal = new com.toedter.calendar.JDateChooser();
        javax.swing.JLabel jLabel28 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel29 = new javax.swing.JLabel();
        txt_pesqFornecedor = new javax.swing.JTextField();
        txt_pesqProduto = new javax.swing.JTextField();
        btn_pesquisar = new javax.swing.JButton();
        javax.swing.JLabel jLabel31 = new javax.swing.JLabel();
        cb_pesqFazenda = new javax.swing.JComboBox<>();
        javax.swing.JPanel jPanel7 = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        tb_entradas = new javax.swing.JTable();
        javax.swing.JPanel jPanel8 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel30 = new javax.swing.JLabel();
        txt_qtTotal = new javax.swing.JTextField();

        jMenuEditar.setText("Editar Entrada");
        jMenuEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEditarActionPerformed(evt);
            }
        });
        jPopupEditar.add(jMenuEditar);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Entrada de Insumos");
        setToolTipText("");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("ID ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Fornecedor");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Fazenda");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Operação");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Nº Pedido");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Tipo Doc.");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Data Emissão");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Nº Doc");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Valor Total");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Peso Bruto");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Tara");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Peso Liquido");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Data Entrada");

        txt_idNF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_idNF.setEnabled(false);

        txt_dtEntrada.setEnabled(false);

        cb_operacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Compra", "Transferencia", "Devolução Interna", "Outras Entradas" }));
        cb_operacao.setEnabled(false);
        cb_operacao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_operacaoItemStateChanged(evt);
            }
        });

        txt_nPedido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nPedido.setEnabled(false);

        btn_buscarPedido.setText("jButton1");
        btn_buscarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarPedidoActionPerformed(evt);
            }
        });

        txt_idFornecedor.setEditable(false);
        txt_idFornecedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_idFornecedor.setEnabled(false);

        cb_tioDoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "NF", "DANFE", "SM", " " }));
        cb_tioDoc.setEnabled(false);

        txt_dtEmissao.setEnabled(false);

        txt_nDoc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nDoc.setEnabled(false);

        txt_vlrDoc.setEnabled(false);
        txt_vlrDoc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_vlrDocFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_vlrDocFocusLost(evt);
            }
        });

        cb_fazenda.setEnabled(false);

        txt_pesoBruto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_pesoBruto.setText("0 kg");
        txt_pesoBruto.setEnabled(false);
        txt_pesoBruto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_pesoBrutoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_pesoBrutoFocusLost(evt);
            }
        });
        txt_pesoBruto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_pesoBrutoKeyReleased(evt);
            }
        });

        txt_tara.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_tara.setText("0 kg");
        txt_tara.setEnabled(false);
        txt_tara.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_taraFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_taraFocusLost(evt);
            }
        });
        txt_tara.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_taraKeyReleased(evt);
            }
        });

        txt_pesoLiq.setEditable(false);
        txt_pesoLiq.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_pesoLiq.setText("0 kg");
        txt_pesoLiq.setEnabled(false);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Placa");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Peso NF");

        txt_pesoNF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_pesoNF.setText("0 kg");
        txt_pesoNF.setEnabled(false);
        txt_pesoNF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_pesoNFFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_pesoNFFocusLost(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Tipo de Frete");

        cb_tipoFrete.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "FOB", "CIF" }));
        cb_tipoFrete.setEnabled(false);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Nº CTE");

        txt_nCTE.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nCTE.setEnabled(false);

        txt_placa.setEnabled(false);

        txt_fornecedor.setEditable(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dtEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel9)
                        .addGap(3, 3, 3)
                        .addComponent(txt_vlrDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_tipoFrete, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nCTE, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(7, 7, 7)
                                .addComponent(cb_operacao, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_nPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_buscarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_idFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(txt_fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_tioDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_nDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_idNF, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_dtEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_placa, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_pesoNF, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_pesoBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_tara, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_pesoLiq, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txt_idNF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13))
                    .addComponent(txt_dtEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cb_operacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txt_nPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_buscarPedido)
                    .addComponent(jLabel2)
                    .addComponent(txt_idFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cb_tioDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txt_nDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_dtEmissao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txt_vlrDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20)
                        .addComponent(cb_tipoFrete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21)
                        .addComponent(txt_nCTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txt_pesoBruto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(txt_tara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(txt_pesoLiq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(txt_pesoNF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(txt_placa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Insumo");

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

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Quantidade");

        txt_quantidade.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_quantidade.setEnabled(false);
        txt_quantidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_quantidadeFocusGained(evt);
            }
        });
        txt_quantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_quantidadeKeyReleased(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Unid");

        txt_unid.setEditable(false);
        txt_unid.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_unid.setEnabled(false);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Valor Unitário");

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
                        .addComponent(jLabel16)
                        .addGap(15, 15, 15)
                        .addComponent(txt_idInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txt_insumo, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(btn_pesqInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_idCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txt_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_fazendaInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel22)
                        .addGap(28, 28, 28)
                        .addComponent(cb_status, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_unid, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_vlrUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_vlrTotalInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_incluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_editar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_excluir)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
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
                    .addComponent(jLabel17)
                    .addComponent(txt_quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(txt_unid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(txt_vlrUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_incluir)
                    .addComponent(btn_editar)
                    .addComponent(btn_excluir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_salvarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_editarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_excluirPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_salvarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_editarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_excluirPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTp_ficheiro.addTab("Entrada", jPanel1);

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("Data Entrada");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText("a");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setText("Fornecedor");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setText("Produto");

        btn_pesquisar.setText("Pesquisar");
        btn_pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisarActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel31.setText("Fazenda");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_dtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_pesqFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_pesqProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_pesqFazenda, 0, 97, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_pesquisar)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel28)
                        .addComponent(jLabel29)
                        .addComponent(txt_pesqFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_pesqProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_pesquisar)
                        .addComponent(jLabel31)
                        .addComponent(cb_pesqFazenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_dtInicial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tb_entradas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tb_entradas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb_entradas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_entradasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_entradas);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel30.setText("Total");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_qtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txt_qtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTp_ficheiro.addTab("Relatório de Entradas", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTp_ficheiro)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTp_ficheiro)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_quantidadeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_quantidadeKeyReleased
        calcularVlrTotal();
    }//GEN-LAST:event_txt_quantidadeKeyReleased

    private void txt_vlrUnitFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_vlrUnitFocusLost
        try {
            double vf = Double.parseDouble(txt_vlrUnit.getText().replace("R$", "").replace(",", "."));
            txt_vlrUnit.setText(new DecimalFormat("R$ #,###,##0.00").format(vf));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txt_vlrUnitFocusLost

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
            JOptionPane.showMessageDialog(null, " Erro ao inserir insumo, verifique os campos preenhidos!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }//GEN-LAST:event_btn_incluirActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        int index = tb_pedInsumos.getSelectedRow();
        editarInsumo(index);
        btn_editar.setEnabled(false);
        btn_excluir.setEnabled(false);
        btn_incluir.setEnabled(false);
    }//GEN-LAST:event_btn_editarActionPerformed

    private void btn_pesqInsumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesqInsumoActionPerformed
       if (cb_operacao.getSelectedItem().equals("Compra")){
            pesquisarInsumoCompra();
       } else {
           pesquisarInsumoOutrasOrigens();
       }
    }//GEN-LAST:event_btn_pesqInsumoActionPerformed

    private void tb_pedInsumosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_pedInsumosMouseClicked
        if (evt.getClickCount() == 2) {
            preencherCamposInsumos();
            btn_editar.setEnabled(true);
            btn_excluir.setEnabled(true);
            txt_idNF.setEnabled(false);
            btn_incluir.setEnabled(false);
        }
    }//GEN-LAST:event_tb_pedInsumosMouseClicked

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed
        btn_salvarPedido.setEnabled(true);
        btn_editarPedido.setEnabled(false);
        txt_idNF.setEnabled(false);
        habilitarCampos();
        limparCampos();
    }//GEN-LAST:event_btn_novoActionPerformed

    private void btn_salvarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarPedidoActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(null, "Deseja Salvar este Pedido?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION) {
            if (verificarBeans()) {
                popularBeans();
                if (EntradaD.InserirEntrada(EntradaB, TbInsumos)) {
                    limparCampos();
                    desabilitarCampos();
                }
            }
        }
    }//GEN-LAST:event_btn_salvarPedidoActionPerformed

    private void btn_editarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarPedidoActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja editar esta entrada?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION) {
            if (!txt_idNF.getText().equals("")) {
                popularBeans();
                EntradaB.setID(Integer.parseInt(txt_idNF.getText()));
                EntradaD.editarEntrada(EntradaB, TbInsumos);
                limparCampos();
                desabilitarCampos();
                btn_editarPedido.setEnabled(false);
                btn_excluirPedido.setEnabled(false);
                txt_idNF.setText("");

            } else {
                JOptionPane.showMessageDialog(null, "Selecione a entrada que deseja editar!", "Erro", 0, FormatarICO.ICObtnSair());
            }
        }
    }//GEN-LAST:event_btn_editarPedidoActionPerformed

    private void btn_excluirPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirPedidoActionPerformed
        int excluir = JOptionPane.showConfirmDialog(null, "Deseja excluir esta Escala de Gado?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (excluir == JOptionPane.YES_OPTION) {

            limparCampos();
            desabilitarCampos();
            btn_editarPedido.setEnabled(false);
            btn_excluirPedido.setEnabled(false);
        }
    }//GEN-LAST:event_btn_excluirPedidoActionPerformed

    private void txt_vlrDocFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_vlrDocFocusLost
        if (!txt_vlrDoc.getText().equals("")) {
            double vf = Double.parseDouble(txt_vlrDoc.getText().replaceAll("[^0-9&&[^,]]", "").replace(",", "."));
            txt_vlrDoc.setText(new DecimalFormat("R$ #,###,##0.00").format(vf));
        }
    }//GEN-LAST:event_txt_vlrDocFocusLost

    private void txt_pesoNFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_pesoNFFocusLost
        txt_pesoNF.setText(formatarValorKG(txt_pesoNF.getText()));
    }//GEN-LAST:event_txt_pesoNFFocusLost

    private void txt_pesoBrutoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_pesoBrutoFocusLost
        txt_pesoBruto.setText(formatarValorKG(txt_pesoBruto.getText()));
    }//GEN-LAST:event_txt_pesoBrutoFocusLost

    private void txt_taraFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_taraFocusLost
        txt_tara.setText(formatarValorKG(txt_tara.getText()));
    }//GEN-LAST:event_txt_taraFocusLost

    private void txt_pesoBrutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_pesoBrutoKeyReleased
        calcularPesoLiq();
    }//GEN-LAST:event_txt_pesoBrutoKeyReleased

    private void txt_taraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_taraKeyReleased
        calcularPesoLiq();
    }//GEN-LAST:event_txt_taraKeyReleased

    private void txt_pesoNFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_pesoNFFocusGained
        txt_pesoNF.selectAll();
    }//GEN-LAST:event_txt_pesoNFFocusGained

    private void txt_pesoBrutoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_pesoBrutoFocusGained
        txt_pesoBruto.selectAll();
    }//GEN-LAST:event_txt_pesoBrutoFocusGained

    private void txt_taraFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_taraFocusGained
        txt_tara.selectAll();
    }//GEN-LAST:event_txt_taraFocusGained

    private void btn_buscarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarPedidoActionPerformed
        getFrmPesquisar();

    }//GEN-LAST:event_btn_buscarPedidoActionPerformed

    private void cb_operacaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_operacaoItemStateChanged
        String op = cb_operacao.getSelectedItem().toString();
        switch (op) {
            case "Compra":
                caseOperacaoCompra();
                break;
            case "Transferencia":
                caseOperacaoTransferencia();
                break;
            case "Devolução Interna":
                caseOperacaoDevolucaoInterna();
                break;
            default:
                caseOperacaoEntradaDiversas();
                break;
        }
    }//GEN-LAST:event_cb_operacaoItemStateChanged

    private void btn_pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisarActionPerformed
        if (getSQLPesquisa()) {;
            EntradaD.consultarEntradas(TbRelInsumos, EntradaB);
            totalizarEntrada();
        }
    }//GEN-LAST:event_btn_pesquisarActionPerformed

    private void txt_vlrUnitFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_vlrUnitFocusGained
        txt_vlrUnit.selectAll();
    }//GEN-LAST:event_txt_vlrUnitFocusGained

    private void txt_vlrDocFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_vlrDocFocusGained
        txt_vlrDoc.selectAll();
    }//GEN-LAST:event_txt_vlrDocFocusGained

    private void jMenuEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuEditarActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja editar esta entrada?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION) {
            EntradaB.setID(Integer.parseInt(tb_entradas.getValueAt(tb_entradas.getSelectedRow(), 2).toString()));
            EntradaD.buscarEntrada(TbInsumos, EntradaB);
            txt_idNF.setText(EntradaB.getID().toString());
            habilitarCampos();                  
            preencherCamposNF();
            jTp_ficheiro.setSelectedIndex(0);
            btn_salvarPedido.setEnabled(false);
            btn_editarPedido.setEnabled(true);
            btn_excluirPedido.setEnabled(true);
        }
    }//GEN-LAST:event_jMenuEditarActionPerformed

    private void tb_entradasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_entradasMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            if (jPopupEditar.isVisible() == true) {
                jPopupEditar.setVisible(false);
            } else {
                jPopupEditar.setVisible(true);
                jPopupEditar.show(this, 0, 0);
                jPopupEditar.setLocation(evt.getLocationOnScreen());
            }
        }
    }//GEN-LAST:event_tb_entradasMouseClicked

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        int excluir = JOptionPane.showConfirmDialog(null, "Deseja excluir este insumo?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (excluir == JOptionPane.YES_OPTION) {
            int index = tb_pedInsumos.getSelectedRow();
            int IdEntradaInsumo = Integer.parseInt(TbInsumos.getValueAt(index, 0).toString());
            if (IdEntradaInsumo > 0) {
                if (EntradaD.excluirInsumo(IdEntradaInsumo)) {
                    TbInsumos.excluirLinha(index);
                }
            } else {
                TbInsumos.excluirLinha(index);
            }
        }
    }//GEN-LAST:event_btn_excluirActionPerformed

    private void txt_quantidadeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_quantidadeFocusGained
        txt_quantidade.selectAll();
    }//GEN-LAST:event_txt_quantidadeFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btn_buscarPedido;
    javax.swing.JButton btn_editar;
    javax.swing.JButton btn_editarPedido;
    javax.swing.JButton btn_excluir;
    javax.swing.JButton btn_excluirPedido;
    javax.swing.JButton btn_incluir;
    javax.swing.JButton btn_novo;
    javax.swing.JButton btn_pesqInsumo;
    javax.swing.JButton btn_pesquisar;
    javax.swing.JButton btn_salvarPedido;
    javax.swing.JComboBox<Object> cb_fazenda;
    javax.swing.JComboBox<Object> cb_fazendaInsumo;
    javax.swing.JComboBox<String> cb_operacao;
    javax.swing.JComboBox<Object> cb_pesqFazenda;
    javax.swing.JComboBox<String> cb_status;
    javax.swing.JComboBox<String> cb_tioDoc;
    javax.swing.JComboBox<String> cb_tipoFrete;
    javax.swing.JMenuItem jMenuEditar;
    javax.swing.JPopupMenu jPopupEditar;
    javax.swing.JTabbedPane jTp_ficheiro;
    javax.swing.JTable tb_entradas;
    javax.swing.JTable tb_pedInsumos;
    public javax.swing.JTextField txt_categoria;
    com.toedter.calendar.JDateChooser txt_dtEmissao;
    com.toedter.calendar.JDateChooser txt_dtEntrada;
    com.toedter.calendar.JDateChooser txt_dtFinal;
    com.toedter.calendar.JDateChooser txt_dtInicial;
    javax.swing.JComboBox<String> txt_fornecedor;
    public javax.swing.JTextField txt_idCategoria;
    javax.swing.JTextField txt_idFornecedor;
    public final javax.swing.JTextField txt_idInsumo = new javax.swing.JTextField();
    javax.swing.JTextField txt_idNF;
    public javax.swing.JTextField txt_insumo;
    javax.swing.JTextField txt_nCTE;
    javax.swing.JTextField txt_nDoc;
    public javax.swing.JTextField txt_nPedido;
    javax.swing.JTextField txt_pesoBruto;
    javax.swing.JTextField txt_pesoLiq;
    javax.swing.JTextField txt_pesoNF;
    javax.swing.JTextField txt_pesqFornecedor;
    javax.swing.JTextField txt_pesqProduto;
    javax.swing.JFormattedTextField txt_placa;
    javax.swing.JTextField txt_qtTotal;
    javax.swing.JTextField txt_quantidade;
    javax.swing.JTextField txt_tara;
    javax.swing.JTextField txt_unid;
    javax.swing.JTextField txt_vlrDoc;
    javax.swing.JTextField txt_vlrTotalInsumo;
    javax.swing.JTextField txt_vlrUnit;
    // End of variables declaration//GEN-END:variables

    private void caseOperacaoCompra(){
            txt_nPedido.setEnabled(true);
            btn_buscarPedido.setEnabled(true);
            txt_idFornecedor.setText("0");
            txt_fornecedor.setEditable(false);
    }
    
    private void caseOperacaoTransferencia(){
           EntradaB.setIdPedido(0);
           EntradaB.setIdItemPedInsumo(0);
           txt_nPedido.setText("-");
           txt_nPedido.setEnabled(false);
           btn_buscarPedido.setEnabled(false);
           txt_idFornecedor.setText("0");
           txt_fornecedor.setSelectedItem("-");
           txt_fornecedor.setEditable(false);
           getCBoxModelFornecedor();
           for (ListFazendasBeans list : ListFazendas) {
            cbModelFornecedor.addElement(list);
           }
           txt_fornecedor.setModel(cbModelFornecedor);
    }
    
    private void caseOperacaoDevolucaoInterna(){
           EntradaB.setIdPedido(0);
           EntradaB.setIdItemPedInsumo(0);
           txt_nPedido.setText("-");
           txt_nPedido.setEnabled(false);
           btn_buscarPedido.setEnabled(false);
           txt_idFornecedor.setText("0");
           txt_fornecedor.setSelectedItem("-");
           txt_fornecedor.setEditable(false);
           getCBoxModelFornecedor();
           for (ListFazendasBeans list : ListFazPermitidas) {
            cbModelFornecedor.addElement(list);
           }
           txt_fornecedor.setModel(cbModelFornecedor);
    }
    
    private void caseOperacaoEntradaDiversas(){
        EntradaB.setIdPedido(0);
        EntradaB.setIdItemPedInsumo(0);
        txt_nPedido.setText("-");
        txt_nPedido.setEnabled(false);
        btn_buscarPedido.setEnabled(false);
        txt_idFornecedor.setText("0");
        txt_fornecedor.setSelectedItem("-");
        txt_fornecedor.setEditable(true);
        getCBoxModelFornecedor();
    }
    
    private void pesquisarInsumoCompra() {
        if (pesquisaInsumo == null) {
            pesquisaInsumo = new frmPesquisarPedInsumo(null, true);
            pesquisaInsumo.setLocationRelativeTo(null);
            pesquisaInsumo.tb_insumos.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (evt.getClickCount() == 2) {
                        int i = pesquisaInsumo.tb_insumos.getSelectedRow();
                        EntradaB.setIdItemPedInsumo(Integer.parseInt(pesquisaInsumo.tb_insumos.getValueAt(i, 0).toString()));
                        EntradaB.setQtEntrega(Double.parseDouble(pesquisaInsumo.tb_insumos.getValueAt(i, 12).toString()));
                        EntradaB.setQtSaldo(Double.parseDouble(pesquisaInsumo.tb_insumos.getValueAt(i, 13).toString()));
                        txt_idInsumo.setText(pesquisaInsumo.tb_insumos.getValueAt(i, 8).toString());
                        txt_insumo.setText(pesquisaInsumo.tb_insumos.getValueAt(i, 9).toString());
                        txt_idCategoria.setText(pesquisaInsumo.tb_insumos.getValueAt(i, 6).toString());
                        txt_categoria.setText(pesquisaInsumo.tb_insumos.getValueAt(i, 7).toString());
                        txt_unid.setText(pesquisaInsumo.tb_insumos.getValueAt(i, 11).toString());
                        pesquisaInsumo.dispose();
                    }
                }
            });
        }
        try {
            for (int y = 0; y < ListFazPermitidas.size(); y++) {
                if (ListFazPermitidas.get(y).getNomeFazenda().equals(cb_fazenda.getSelectedItem().toString())) {
                    pesquisaInsumo.cb_fazenda.setSelectedIndex(y + 1);
                    break;
                }
            }
        } catch (Exception e) { }     
        pesquisaInsumo.setVisible(true);
    }

    private frmPesquisarInsumo pesquisarInsumoOutrasOrigens(){
        if (frmPesqInsumos == null) {
            frmPesqInsumos = new frmPesquisarInsumo(null, true);
            frmPesqInsumos.setLocationRelativeTo(null);
        }
        frmPesqInsumos.tb_insumos.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (evt.getClickCount() == 2) {
                        int i = frmPesqInsumos.tb_insumos.getSelectedRow();
                        EntradaB.setIdItemPedInsumo(0);
                        EntradaB.setQtEntrega(0.0);
                        EntradaB.setQtSaldo(0.0);
                        txt_idInsumo.setText(frmPesqInsumos.tb_insumos.getValueAt(i, 0).toString());
                        txt_insumo.setText(frmPesqInsumos.tb_insumos.getValueAt(i, 3).toString());
                        txt_idCategoria.setText(frmPesqInsumos.tb_insumos.getValueAt(i, 1).toString());
                        txt_categoria.setText(frmPesqInsumos.tb_insumos.getValueAt(i, 2).toString());
                        txt_unid.setText(frmPesqInsumos.tb_insumos.getValueAt(i, 4).toString());
                        frmPesqInsumos.dispose();
                    }
                }
            });
       
        frmPesqInsumos.setVisible(true);
        return frmPesqInsumos;
    }
    
    private boolean getStatus() {
        if (cb_status.getSelectedItem().equals("Aberto")) {
            return false;
        } else if (cb_status.getSelectedItem().equals("Encerrado")) {
            return true;
        } else {
            return false;
        }
    }

    private int getStatusTabela(Integer indexTabela) {
        if (Boolean.parseBoolean(TbInsumos.getValueAt(indexTabela, 1).toString())) {
            return 2;
        } else {
            return 1;
        }
    }

    private Boolean getSQLPesquisa() {
        EntradaB.setSQLPesqFornecedor("");
        EntradaB.setSQLPesqProduto("");
        EntradaB.setDtInicial("");
        EntradaB.setDtFinal("");

        if (!txt_pesqFornecedor.getText().equals("")) {
            EntradaB.setSQLPesqFornecedor(" and (select cad_fornecedor_nome from cad_fornecedor f where f.cad_fornecedor_id = nf.ent_nf_idEmissor) like '%" + txt_pesqFornecedor.getText() + "%'");
        }
        if (!txt_pesqProduto.getText().equals("")) {
            EntradaB.setSQLPesqProduto(" and ent_ins_insumo like '%" + txt_pesqProduto.getText() + "%'");
        }
        if (!cb_pesqFazenda.getSelectedItem().equals("-")) {
            EntradaB.setIdFazendaPesq(ListFazPermitidas.get(cb_pesqFazenda.getSelectedIndex() - 1).getID());
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma fazenda de Destino!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (txt_dtInicial.getDate() != null) {
            EntradaB.setDtInicial(Corretores.ConverterParaSQL(new SimpleDateFormat("dd/MM/yyyy").format(txt_dtInicial.getDate())));
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um intervalo de data!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (txt_dtFinal.getDate() != null) {
            EntradaB.setDtFinal(Corretores.ConverterParaSQL(new SimpleDateFormat("dd/MM/yyyy").format(txt_dtFinal.getDate())));
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um intervalo de data!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }

        return true;
    }

    private frmPesquisarPedInsumo getFrmPesquisar() {
        if (pesquisaPedido == null) {
            pesquisaPedido = new frmPesquisarPedInsumo(null, true);
            pesquisaPedido.setLocationRelativeTo(null);
        }

        pesquisaPedido.tb_insumos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int i = pesquisaPedido.tb_insumos.getSelectedRow();
                    EntradaB.setIdItemPedInsumo(Integer.parseInt(pesquisaPedido.tb_insumos.getValueAt(i, 0).toString()));
                    EntradaB.setIdPedido(Integer.parseInt(pesquisaPedido.tb_insumos.getValueAt(i, 1).toString()));
                    txt_nPedido.setText(pesquisaPedido.tb_insumos.getValueAt(i, 2).toString());
                    txt_idFornecedor.setText(pesquisaPedido.tb_insumos.getValueAt(i, 3).toString());
                    DefaultComboBoxModel cbModel = new DefaultComboBoxModel(); 
                    cbModel.addElement(pesquisaPedido.tb_insumos.getValueAt(i, 4).toString());
                    txt_fornecedor.setModel(cbModel);
                    pesquisaPedido.dispose();
                    
                }
            }
        });
        
        pesquisaPedido.setVisible(true);
        return pesquisaPedido;
    }

    private void calcularPesoLiq() {
        try {
            double pesoB;
            double tara;
            if (txt_pesoBruto.getText().equals("")) {
                pesoB = 0.0;
            } else {
                pesoB = Double.parseDouble(txt_pesoBruto.getText().replaceAll("[^0-9&&[^,]]", "").replace(",", "."));
            }
            if (txt_tara.getText().equals("")) {
                tara = 0.0;
            } else {
                tara = Double.parseDouble(txt_tara.getText().replaceAll("[^0-9&&[^,]]", "").replace(",", "."));
            }
            double pesoLiq = pesoB - tara;
            txt_pesoLiq.setText(new DecimalFormat("#,###,##0.00 kg").format(pesoLiq));
        } catch (Exception e) {

        }
    }

    private String formatarValorKG(String valor) {
        double valorD;
        if (valor.equals("")) {
            valorD = 0.0;
        } else {
            valorD = Double.parseDouble(valor.replaceAll("[^0-9&&[^,]]", "").replace(",", "."));
        }
        String vf = new DecimalFormat("#,###,##0.00 kg").format(valorD);

        return vf;
    }

    private void preencherCamposInsumos() {
        int index = tb_pedInsumos.getSelectedRow();
        EntradaB.setIdItemPedInsumo(Integer.parseInt(TbInsumos.getValueAt(index, 2).toString()));
        EntradaB.setIdPedido(Integer.parseInt(TbInsumos.getValueAt(index, 3).toString()));
        txt_idInsumo.setText(TbInsumos.getValueAt(index, 9).toString());
        txt_insumo.setText(TbInsumos.getValueAt(index, 10).toString());
        txt_idCategoria.setText(TbInsumos.getValueAt(index, 7).toString());
        txt_categoria.setText(TbInsumos.getValueAt(index, 8).toString());
        cb_fazendaInsumo.setSelectedIndex(getIndexFazendaPermitida(Integer.parseInt(TbInsumos.getValueAt(index, 5).toString())));
        cb_status.setSelectedIndex(getStatusTabela(index));
        txt_quantidade.setText(TbInsumos.getValueAt(index, 12).toString());
        txt_unid.setText(TbInsumos.getValueAt(index, 11).toString());
        txt_vlrUnit.setText(new DecimalFormat("R$ #,###,##0.00").format(Double.parseDouble(TbInsumos.getValueAt(index, 14).toString())));
        txt_vlrTotalInsumo.setText(new DecimalFormat("R$ #,###,##0.00").format(Double.parseDouble(TbInsumos.getValueAt(index, 15).toString())));
    }

    private Integer getIndexFazendaPermitida(Integer IdFazenda){
        int index = 0;
        for (int i = 0; i < ListFazPermitidas.size(); i++){
           if (ListFazPermitidas.get(i).getID() == IdFazenda){
                index = i+1;
                return index;
           }
        }
        return index;
    }
    
    private void preencherCamposNF() {

        cb_operacao.setSelectedItem(EntradaB.getOperacao());
        txt_nPedido.setText(EntradaB.getnPedido());
        txt_idFornecedor.setText(EntradaB.getIdFornecedor().toString());
        txt_fornecedor.setEditable(true);
        txt_fornecedor.setSelectedItem(EntradaB.getFornecedor());
        txt_fornecedor.setEditable(false);
        cb_tioDoc.setSelectedItem(EntradaB.getTipoDoc());
        txt_nDoc.setText(EntradaB.getnDoc().toString());
        try {
            txt_dtEmissao.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(EntradaB.getDtEmissao()));
            txt_dtEntrada.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(EntradaB.getDtEntrada()));
        } catch (ParseException ex) {
            Logger.getLogger(frmEntradaInsumos.class.getName()).log(Level.SEVERE, null, ex);
        }
        txt_vlrDoc.setText(new DecimalFormat("R$ #,###,##0.00").format(EntradaB.getVlrTotal()));
        cb_fazenda.setSelectedIndex(getNomeFazenda(EntradaB.getIdFazenda()) + 1);
        cb_tipoFrete.setSelectedItem(EntradaB.getTipoFrete());
        txt_nCTE.setText(EntradaB.getnCTE().toString());
        txt_placa.setText(EntradaB.getPlaca());
        txt_pesoNF.setText(Corretores.ConverterDoubleQuilos(EntradaB.getPesoNF().toString().replace(".", ",")));
        txt_pesoBruto.setText(Corretores.ConverterDoubleQuilos(EntradaB.getPesoBruto().toString().replace(".", ",")));
        txt_tara.setText(Corretores.ConverterDoubleQuilos(EntradaB.getTara().toString().replace(".", ",")));
        txt_pesoLiq.setText(Corretores.ConverterDoubleQuilos(EntradaB.getPesoLiq().toString().replace(".", ",")));

    }

    private void popularBeans() {
        EntradaB.setDtEntrada(new SimpleDateFormat("dd/MM/yyyy").format(txt_dtEntrada.getDate()));
        EntradaB.setOperacao(cb_operacao.getSelectedItem().toString());
        EntradaB.setnPedido(txt_nPedido.getText());
        EntradaB.setIdFornecedor(Integer.parseInt(txt_idFornecedor.getText()));
        EntradaB.setFornecedor(txt_fornecedor.getSelectedItem().toString());
        EntradaB.setTipoDoc(cb_tioDoc.getSelectedItem().toString());
        EntradaB.setnDoc(Integer.parseInt(txt_nDoc.getText()));
        EntradaB.setDtEmissao(new SimpleDateFormat("dd/MM/yyyy").format(txt_dtEntrada.getDate()));
        EntradaB.setVlrTotal(getBigDecimal(txt_vlrDoc.getText()));
        EntradaB.setIdFazenda(ListFazendas.get(cb_fazenda.getSelectedIndex() - 1).getID());
        EntradaB.setTipoFrete(cb_tipoFrete.getSelectedItem().toString());
        EntradaB.setnCTE(Integer.parseInt(txt_nCTE.getText()));
        EntradaB.setPlaca(txt_placa.getText());
        EntradaB.setPesoNF(Corretores.ConverQuilosDouble(txt_pesoNF.getText()));
        EntradaB.setPesoBruto(Corretores.ConverQuilosDouble(txt_pesoBruto.getText()));
        EntradaB.setTara(Corretores.ConverQuilosDouble(txt_tara.getText()));
        EntradaB.setPesoLiq(Corretores.ConverQuilosDouble(txt_pesoLiq.getText()));
    }

    private boolean verificarBeans() {
        if (TbInsumos.getRowCount() == 0) {
            return false;
        }
        if (txt_placa.getText().equals("   -    ")){
            JOptionPane.showMessageDialog(null,"O campo 'Placa' deve ser preenchido!", "Atenção", 0, FormatarICO.ICObtnSair()); 
            return false;
        }
        if (txt_nCTE.getText().equals("")){
            JOptionPane.showMessageDialog(null,"O campo 'Nº CTE' deve ser preenchido!", "Atenção", 0, FormatarICO.ICObtnSair()); 
            return false;
        }
        return true;
    }

    private void inserirInsumo() {
        if (!txt_nPedido.getText().equals("")) {
            TbEntradaInsumosBeans beans = new TbEntradaInsumosBeans();
            beans.setId(0);
            beans.setStatus(getStatus());
            beans.setItemListaPedidos(EntradaB.getIdItemPedInsumo());
            beans.setIdPedido(EntradaB.getIdPedido());
            beans.setnPedido(txt_nPedido.getText());
            beans.setIdFazenda(ListFazPermitidas.get(cb_fazendaInsumo.getSelectedIndex() - 1).getID());
            beans.setFazenda(cb_fazendaInsumo.getSelectedItem().toString());
            beans.setIdCategoria(Integer.parseInt(txt_idCategoria.getText()));
            beans.setCategoria(txt_categoria.getText());
            beans.setIdInsumo(Integer.parseInt(txt_idInsumo.getText()));
            beans.setInsumo(txt_insumo.getText());
            beans.setUnidade(txt_unid.getText());
            beans.setQuantidade(Double.parseDouble(txt_quantidade.getText().replace(",", ".")));
            beans.setSifra("R$");
            beans.setValorUnit(getBigDecimal(txt_vlrUnit.getText()));
            beans.setValorTotal(getBigDecimal(txt_vlrTotalInsumo.getText()));
            beans.setQtEntregue(EntradaB.getQtEntrega());
            beans.setSaldoEntregar(EntradaB.getQtSaldo() - Double.parseDouble(txt_quantidade.getText().replace(",", ".")));
            TbInsumos.addBeans(beans);
        }
    }

    private void editarInsumo(Integer index) {
        // as variaveis abaixo são ajustar a quantidade entregue e o saldo a enregar mediante a alteração 
        // na quantidade de produto que está sendo dada entrada;
        double qtEntregaInicial = Double.parseDouble(TbInsumos.getValueAt(index, 16).toString());
        double qtSaldoInicial = Double.parseDouble(TbInsumos.getValueAt(index, 17).toString());
        double qtInicial = Double.parseDouble(TbInsumos.getValueAt(index, 12).toString());
        double qtFinal = Double.parseDouble(txt_quantidade.getText().replace(",", "."));
        double qtEntregaFinal = qtEntregaInicial - qtInicial + qtFinal;
        double qtSaldoFinal = qtSaldoInicial - qtInicial + qtFinal;
        TbInsumos.setValueAt(getStatus(), index, 1);
        TbInsumos.setValueAt(EntradaB.getIdItemPedInsumo(), index, 2);
        TbInsumos.setValueAt(EntradaB.getIdPedido(), index, 3);
        TbInsumos.setValueAt(txt_nPedido.getText(), index, 4);
        TbInsumos.setValueAt(ListFazPermitidas.get(cb_fazendaInsumo.getSelectedIndex() - 1).getID(), index, 5);
        TbInsumos.setValueAt(cb_fazendaInsumo.getSelectedItem().toString(), index, 6);
        TbInsumos.setValueAt(Integer.parseInt(txt_idCategoria.getText()), index, 7);
        TbInsumos.setValueAt(txt_categoria.getText(), index, 8);
        TbInsumos.setValueAt(Integer.parseInt(txt_idInsumo.getText()), index, 9);
        TbInsumos.setValueAt(txt_insumo.getText(), index, 10);
        TbInsumos.setValueAt(txt_unid.getText(), index, 11);
        TbInsumos.setValueAt(qtFinal, index, 12);
        TbInsumos.setValueAt("R$", index, 13);
        TbInsumos.setValueAt(getBigDecimal(txt_vlrUnit.getText()), index, 14);
        TbInsumos.setValueAt(getBigDecimal(txt_vlrTotalInsumo.getText()), index, 15);
        TbInsumos.setValueAt(qtEntregaFinal, index, 16);
        TbInsumos.setValueAt(qtSaldoFinal, index, 17);

    }

    private BigDecimal getBigDecimal(String valor) {
        String vf = valor.replaceAll("[^0-9&&[^,]]", "").replace(",", ".");
        return new BigDecimal(vf);
    }

    private void limparCampos() {
        txt_dtEntrada.setDate(null);
        cb_operacao.setSelectedItem("-");
        txt_nPedido.setText("");
        EntradaB.setIdPedido(0);
        txt_idFornecedor.setText("");
        txt_fornecedor.setSelectedItem("-");
        cb_tioDoc.setSelectedItem("-");
        txt_nDoc.setText("");
        txt_dtEmissao.setDate(null);
        txt_vlrDoc.setText("");
        cb_fazenda.setSelectedItem("-");
        cb_tipoFrete.setSelectedItem("-");
        txt_nCTE.setText("");
        txt_placa.setText("");
        txt_pesoNF.setText("0 kg");
        txt_pesoBruto.setText("0 kg");
        txt_tara.setText("0 kg");
        txt_pesoLiq.setText("0 kg");

        txt_idInsumo.setText("");
        txt_idCategoria.setText("");
        txt_insumo.setText("");
        txt_categoria.setText("");
        cb_fazendaInsumo.setSelectedItem("-");
        cb_status.setSelectedItem("-");
        txt_quantidade.setText("");
        txt_unid.setText("");
        txt_vlrUnit.setText("");
        txt_vlrTotalInsumo.setText("");
        TbInsumos.limpar();
    }

    private void desabilitarCampos() {
        txt_dtEntrada.setEnabled(false);
        cb_operacao.setEnabled(false);
        txt_nPedido.setEnabled(false);
        txt_idFornecedor.setEnabled(false);
        txt_fornecedor.setEnabled(false);
        cb_tioDoc.setEnabled(false);
        txt_nDoc.setEnabled(false);
        txt_dtEmissao.setEnabled(false);
        txt_vlrDoc.setEnabled(false);
        cb_fazenda.setEnabled(false);
        cb_tipoFrete.setEnabled(false);
        txt_nCTE.setEnabled(false);
        txt_placa.setEnabled(false);
        txt_pesoNF.setEnabled(false);
        txt_pesoBruto.setEnabled(false);
        txt_tara.setEnabled(false);
        txt_pesoLiq.setEnabled(false);

        txt_idInsumo.setEnabled(false);
        txt_insumo.setEnabled(false);
        txt_idCategoria.setEnabled(false);
        txt_categoria.setEnabled(false);
        cb_fazendaInsumo.setEnabled(false);
        cb_status.setEnabled(false);
        txt_quantidade.setEnabled(false);
        txt_unid.setEnabled(false);
        txt_vlrUnit.setEnabled(false);
        txt_vlrTotalInsumo.setEnabled(false);
        btn_incluir.setEnabled(false);
    }

    private void habilitarCampos() {
        txt_dtEntrada.setEnabled(true);
        cb_operacao.setEnabled(true);
        txt_nPedido.setEnabled(true);
        txt_idFornecedor.setEnabled(true);
        txt_fornecedor.setEnabled(true);
        cb_tioDoc.setEnabled(true);
        txt_nDoc.setEnabled(true);
        txt_dtEmissao.setEnabled(true);
        txt_vlrDoc.setEnabled(true);
        cb_fazenda.setEnabled(true);
        cb_tipoFrete.setEnabled(true);
        txt_nCTE.setEnabled(true);
        txt_placa.setEnabled(true);
        txt_pesoNF.setEnabled(true);
        txt_pesoBruto.setEnabled(true);
        txt_tara.setEnabled(true);
        txt_pesoLiq.setEnabled(true);

        txt_idInsumo.setEnabled(true);
        txt_insumo.setEnabled(true);
        txt_idCategoria.setEnabled(true);
        txt_categoria.setEnabled(true);
        cb_fazendaInsumo.setEnabled(true);
        cb_status.setEnabled(true);
        txt_quantidade.setEnabled(true);
        txt_unid.setEnabled(true);
        txt_vlrUnit.setEnabled(true);
        txt_vlrTotalInsumo.setEnabled(true);
        btn_incluir.setEnabled(true);

    }

    final void calcularVlrTotal() {

        try {
            double qt = Double.parseDouble(txt_quantidade.getText().replace(",", "."));
            double vlrUnit = Double.parseDouble(txt_vlrUnit.getText().replace("R$", "").replace(",", "."));
            double vlrTotal = qt * vlrUnit;
            txt_vlrTotalInsumo.setText(new DecimalFormat("R$" + " #,###,##0.00").format(vlrTotal));
        } catch (Exception e) {

        }

    }

    private Integer getNomeFazenda(Integer idFazenda) {
        int idLista = 0;
        for (int i = 0; i < ListFazendas.size(); i++) {
            if (ListFazendas.get(i).getID() == idFazenda) {
                idLista = i;
                return i;
            }
        }
        return idLista;
    }

    private void totalizarEntrada(){
        double qt = 0;
        double total = 0;
        if (!txt_pesqProduto.getText().equals("")){
            for (int i = 0; i < TbRelInsumos.getRowCount(); i++){
                qt = Double.parseDouble(TbRelInsumos.getValueAt(i, 15).toString());
                total += qt;
                System.out.println(i);
            }
            txt_qtTotal.setText(new DecimalFormat("###,###,##0.00").format(total));
        } else {
            txt_qtTotal.setText(new DecimalFormat("###,###,##0.00").format(total));
        }
    }
    
    
    
    
    
}
