/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.frmPagamentos;
import TableModel.TableModelResPagtoNovo;
import Beans.PagamentosBeans;
import DAO.PagamentosDAO;
import GUI.*;
import Beans.BancosBeans;
import Beans.ContaBancariaBeans;
import Beans.FornecedoresBeans;
import Beans.PagtoDiversosBeans;
import DAO.Diversas;
import DAO.DiversasHibernate;
import static GUI.Principal.listBancos;
import static GUI.Principal.listContaOrigem;
import static GUI.frmLogin.dataAtual;
import TableModel.CellRenderTbResPagto;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import Utilitarios.FixedColumnTable;
import Utilitarios.ReaisCellRenderer;
import com.sun.glass.events.KeyEvent;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.table.DefaultTableCellRenderer;

public class frmResPagamentos extends javax.swing.JInternalFrame {

    Diversas DiversasD;
    CentralizarTabela Centralizar;
    FornecedoresBeans fornecedor;
    TableModelResPagtoNovo TbPagtos;
    PagamentosDAO PagamentosD;
    PagtoDiversosBeans PagamentosB;
    FixedColumnTable fct;
    JPopupMenu pop;
    frmPagamentos frmPagtos;

    public frmResPagamentos() {
        initComponents();
        Centralizar = new CentralizarTabela();
        DiversasD = new Diversas();
        PagamentosD = new PagamentosDAO();
        pop = new JPopupMenu();
        carregarBancos();
        carregarContasOrigem();
        carregarTabelaNF();

        try {
            Date hoje = new SimpleDateFormat("dd/MM/yyyy").parse(dataAtual);
            txt_dtInicial.setDate(hoje);
            txt_dtFinal.setDate(Corretores.UltimoDiaMes());
            getRootPane().setDefaultButton(btn_pesquisar);
        } catch (ParseException ex) {
            Logger.getLogger(frmResPagamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void carregarBancos() {
        if (listBancos == null) {
            listBancos = DiversasHibernate.getListaBancos();
        }
        cb_bancoDestino.addItem(new BancosBeans(0, "-"));
        for (BancosBeans list : listBancos) {
            cb_bancoDestino.addItem(list);
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

    private JTable carregarTabelaNF() {
        tb_pagtos.setModel(getTableModelPagto());
        //tb_pagtos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        Centralizar = new CentralizarTabela();
        Centralizar.centralizarTabela(tb_pagtos);
        ((DefaultTableCellRenderer) tb_pagtos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        for (int c = 0; c < TbPagtos.getColumnCount(); c++) {
            if (c == TbPagtos.VALOREMMOEDA || c == TbPagtos.TAXA || c == TbPagtos.MOEDA) {
                tb_pagtos.getColumnModel().getColumn(c).setPreferredWidth(0);// ID pagamento
                tb_pagtos.getColumnModel().getColumn(c).setMinWidth(0);
                tb_pagtos.getColumnModel().getColumn(c).setMaxWidth(0);
            }
        }
        tb_pagtos.getColumnModel().getColumn(0).setPreferredWidth(60);// ID pagamento
        tb_pagtos.getColumnModel().getColumn(1).setPreferredWidth(120);// conta de origem
        tb_pagtos.getColumnModel().getColumn(2).setPreferredWidth(110);// data prevista
        tb_pagtos.getColumnModel().getColumn(3).setPreferredWidth(110);// data pagamento
        tb_pagtos.getColumnModel().getColumn(4).setPreferredWidth(210);// favorecido
        tb_pagtos.getColumnModel().getColumn(5).setPreferredWidth(100);// forma pagamento
        tb_pagtos.getColumnModel().getColumn(6).setPreferredWidth(80);// Número Documento

        tb_pagtos.getColumnModel().getColumn(TbPagtos.VALORPREVISTO).setPreferredWidth(110);// valor previsto
        tb_pagtos.getColumnModel().getColumn(TbPagtos.VALORPAGO).setPreferredWidth(110);// valor pagamento
        tb_pagtos.getColumnModel().getColumn(TbPagtos.VALORNF).setPreferredWidth(110);// valor de NF
        tb_pagtos.getColumnModel().getColumn(TbPagtos.VALORCLASSIFICADO).setPreferredWidth(110);// valor classificado
        tb_pagtos.getColumnModel().getColumn(TbPagtos.STATUS).setPreferredWidth(40);// Status

        tb_pagtos.getColumnModel().getColumn(TbPagtos.TAXA).setCellRenderer(new ReaisCellRenderer());
        tb_pagtos.getColumnModel().getColumn(TbPagtos.VALORPREVISTO).setCellRenderer(new ReaisCellRenderer());
        tb_pagtos.getColumnModel().getColumn(TbPagtos.VALORPAGO).setCellRenderer(new ReaisCellRenderer());
        tb_pagtos.getColumnModel().getColumn(TbPagtos.VALORNF).setCellRenderer(new CellRenderTbResPagto());
        tb_pagtos.getColumnModel().getColumn(TbPagtos.VALORCLASSIFICADO).setCellRenderer(new CellRenderTbResPagto());

        return tb_pagtos;
    }

    private TableModelResPagtoNovo getTableModelPagto() {
        if (TbPagtos == null) {
            TbPagtos = new TableModelResPagtoNovo();
        }
        return TbPagtos;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.ButtonGroup buttonGroup1 = new javax.swing.ButtonGroup();
        popPagto = new javax.swing.JPopupMenu();
        menuPopEditar = new javax.swing.JMenuItem();
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        ch_dtPrevista = new javax.swing.JCheckBox();
        ch_dtRealizada = new javax.swing.JCheckBox();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        cb_contaOrigem = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        cb_bancoDestino = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        cb_formaPagto = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        txt_nDoc = new javax.swing.JTextField();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        txt_favorecido = new javax.swing.JTextField();
        txt_dtInicial =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        txt_dtFinal =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        btn_pesquisar = new javax.swing.JButton();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        cb_statusPagto = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        cb_ordenacao = new javax.swing.JComboBox<>();
        btn_pesqEmissor1 = new javax.swing.JButton();
        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        tb_pagtos = new javax.swing.JTable();
        javax.swing.JPanel jPanel3 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        txt_somaVlrPrevisto = new javax.swing.JTextField();
        txt_somaVlrPago = new javax.swing.JTextField();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        txt_somaVlrClass = new javax.swing.JTextField();
        javax.swing.JLabel jLabel13 = new javax.swing.JLabel();
        txt_somaVlrNF = new javax.swing.JTextField();
        javax.swing.JLabel jLabel14 = new javax.swing.JLabel();

        menuPopEditar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        menuPopEditar.setText("Editar Pagamento");
        menuPopEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPopEditarActionPerformed(evt);
            }
        });
        popPagto.add(menuPopEditar);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Resumo dos Pagamentos");
        setAutoscrolls(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Data Inicial");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Data Final");

        buttonGroup1.add(ch_dtPrevista);
        ch_dtPrevista.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_dtPrevista.setSelected(true);
        ch_dtPrevista.setText("Data Prevista");

        buttonGroup1.add(ch_dtRealizada);
        ch_dtRealizada.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_dtRealizada.setText("Data Realizada");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Conta de Origem");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Banco de Destino");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Forma Pagto");

        cb_formaPagto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Cheque em Mãos", "Cheque Deposito", "Em Espécie", "Boleto", "Formulário TED", "Transferência" }));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Nº Documento");

        txt_nDoc.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Favorecido");

        txt_favorecido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_favorecidoFocusLost(evt);
            }
        });

        btn_pesquisar.setText("Pesquisar");
        btn_pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisarActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Status Pagto");

        cb_statusPagto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Previsto", "Pago" }));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Ordenar por:");

        cb_ordenacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Banco de Destino", "Conta de Origem", "Data Prevista", "Data Pagto", "Forma Pagamento", "Favorecido", "Nº Documento", " ", " ", " " }));

        btn_pesqEmissor1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar pequeno_1.png"))); // NOI18N
        btn_pesqEmissor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesqEmissor1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_dtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ch_dtPrevista)
                    .addComponent(ch_dtRealizada))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cb_bancoDestino, 0, 155, Short.MAX_VALUE)
                    .addComponent(cb_contaOrigem, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nDoc))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_formaPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cb_statusPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_favorecido, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn_pesqEmissor1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_pesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_ordenacao, 0, 120, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_pesquisar)
                    .addComponent(txt_favorecido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cb_formaPagto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cb_contaOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(ch_dtPrevista)
                    .addComponent(txt_dtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btn_pesqEmissor1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txt_nDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cb_statusPagto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(cb_ordenacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cb_bancoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(ch_dtRealizada)
                    .addComponent(txt_dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tb_pagtos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb_pagtos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_pagtosMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tb_pagtosMousePressed(evt);
            }
        });
        tb_pagtos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tb_pagtosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tb_pagtos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1248, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Previsto");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Pago");

        txt_somaVlrPrevisto.setEditable(false);
        txt_somaVlrPrevisto.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txt_somaVlrPago.setEditable(false);
        txt_somaVlrPago.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Classificado");

        txt_somaVlrClass.setEditable(false);
        txt_somaVlrClass.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Nota Fiscal");

        txt_somaVlrNF.setEditable(false);
        txt_somaVlrNF.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Valores:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_somaVlrPrevisto, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_somaVlrPago, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_somaVlrNF, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_somaVlrClass, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(txt_somaVlrNF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(txt_somaVlrClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jLabel11)
                        .addComponent(txt_somaVlrPrevisto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_somaVlrPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setBounds(0, 0, 1278, 570);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisarActionPerformed
        TbPagtos.limpar();
        //  tb_pagtos.setRowSorter(null);
        // tb_pagtos.setAutoCreateRowSorter(true);
        TbPagtos.addLista(PagamentosD.resumoPagamentos(SQLWhere()));
        somarValorTabela();


    }//GEN-LAST:event_btn_pesquisarActionPerformed

    private void tb_pagtosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_pagtosMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            if (popPagto.isVisible() == true) {
                popPagto.setVisible(false);
            } else {
                popPagto.setVisible(true);
                popPagto.show(this, 0, 0);
                popPagto.setLocation(evt.getLocationOnScreen());
            }
        }
    }//GEN-LAST:event_tb_pagtosMouseClicked

    private void menuPopEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPopEditarActionPerformed
        int rowIndex = tb_pagtos.getSelectedRow();
        if (rowIndex >= 0) {
            Long ID = (Long) TbPagtos.getValueAt(rowIndex, TbPagtos.IDPAGTO);
            PagamentosDAO pagamentoD = new PagamentosDAO();
            getFrmPagamentos(pagamentoD.getPagamento(ID));
        }
    }//GEN-LAST:event_menuPopEditarActionPerformed

    private void tb_pagtosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_pagtosMousePressed
        TipTextSomaValorPagto(tb_pagtos);

    }//GEN-LAST:event_tb_pagtosMousePressed

    private void btn_pesqEmissor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesqEmissor1ActionPerformed
        frmCadFornecedores frmFornecedor = new frmCadFornecedores();
        this.getParent().add(frmFornecedor);
        frmFornecedor.setVisible(true);
        frmFornecedor.btn_novo1.setEnabled(false);
        frmFornecedor.tb_fornecedores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    fornecedor = new FornecedoresBeans();
                    int index = frmFornecedor.tb_fornecedores.getSelectedRow();
                    fornecedor.setID((Integer) frmFornecedor.TbForn.getValueAt(index, 0));
                    txt_favorecido.setText(frmFornecedor.TbForn.getValueAt(index, 2).toString());
                    frmFornecedor.dispose();
                }
                frmFornecedor.btn_novo1.setEnabled(false);
                frmFornecedor.btn_Salvar.setEnabled(false);
                frmFornecedor.btn_editar.setEnabled(false);
                frmFornecedor.btn_excluir.setEnabled(false);
            }
        });
    }//GEN-LAST:event_btn_pesqEmissor1ActionPerformed

    private void txt_favorecidoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_favorecidoFocusLost
        if (txt_favorecido.getText().equals("")) {
            fornecedor = null;
        }
    }//GEN-LAST:event_txt_favorecidoFocusLost

    private void tb_pagtosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_pagtosKeyPressed
    switch(evt.getKeyCode()){
        case KeyEvent.VK_F2:
             menuPopEditar.doClick();
            break;
        default:    
    }
    }//GEN-LAST:event_tb_pagtosKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btn_pesqEmissor1;
    javax.swing.JButton btn_pesquisar;
    javax.swing.JComboBox<Object> cb_bancoDestino;
    javax.swing.JComboBox<Object> cb_contaOrigem;
    javax.swing.JComboBox<String> cb_formaPagto;
    javax.swing.JComboBox<String> cb_ordenacao;
    javax.swing.JComboBox<String> cb_statusPagto;
    javax.swing.JCheckBox ch_dtPrevista;
    javax.swing.JCheckBox ch_dtRealizada;
    javax.swing.JMenuItem menuPopEditar;
    javax.swing.JPopupMenu popPagto;
    javax.swing.JTable tb_pagtos;
    com.toedter.calendar.JDateChooser txt_dtFinal;
    com.toedter.calendar.JDateChooser txt_dtInicial;
    javax.swing.JTextField txt_favorecido;
    javax.swing.JTextField txt_nDoc;
    javax.swing.JTextField txt_somaVlrClass;
    javax.swing.JTextField txt_somaVlrNF;
    javax.swing.JTextField txt_somaVlrPago;
    javax.swing.JTextField txt_somaVlrPrevisto;
    // End of variables declaration//GEN-END:variables

    private void getFrmPagamentos(PagamentosBeans pg) {
        frmPagtos = new frmPagamentos();
        this.getParent().add(frmPagtos);
        frmPagtos.setVisible(true);
        frmPagtos.ch_buscarPagto.doClick();
        frmPagtos.PagamentosB = pg;
        frmPagtos.preencherFormulario(pg);
        frmPagtos.btn_SalvarPagto.setEnabled(false);
        frmPagtos.btn_editarPagto.setEnabled(true);
        frmPagtos.btn_excluirPagto.setEnabled(true);
    }

    private String getTipoOrdenacao() {
        String tipo = "";
        String st = cb_ordenacao.getSelectedItem().toString();
        switch (st) {
            case "ID":
                return "ID";
            case "Data Prevista":
                return "DtPrevista";
            case "Data Pagto":
                return "DtRealizado";
            case "Forma Pagamento":
                return "FormaPagto";
            case "Nº Documento":
                return "nDocumento";
            case "Conta de Origem":
                return "IDContaOrigem";
            case "Banco de Destino":
                return "Banco";
            case "Favorecido":
                return "titular";
            default:
                return "";
        }
    }

    private void somarValorTabela() {
        Double valorPrevisto = 0.00;
        Double valorPago = 0.00;
        Double valorClas = 0.00;
        Double valorNf = 0.00;
        for (int i = 0; i < TbPagtos.getRowCount(); i++) {
            PagamentosBeans pagto = TbPagtos.getBeans(i);
            valorPrevisto += pagto.getValorCompra();
            valorPago += pagto.getValorPagamento();
            valorClas += pagto.getValorClas();
            valorNf += pagto.getValorNF();
        }
        txt_somaVlrPrevisto.setText(new DecimalFormat("R$ #,###,##0.00").format(valorPrevisto));
        txt_somaVlrPago.setText(new DecimalFormat("R$ #,###,##0.00").format(valorPago));
        txt_somaVlrClass.setText(new DecimalFormat("R$ #,###,##0.00").format(valorClas));
        txt_somaVlrNF.setText(new DecimalFormat("R$ #,###,##0.00").format(valorNf));
    }

    private void TipTextSomaValorPagto(JTable tabela) {
        tabela.setToolTipText(null);
        int[] linha = tabela.getSelectedRows();
        if (linha.length > 1) {
            String texto;
            double SomaValorPrevisto = 0.0;
            double SomaValorPago = 0.0;
            double SomaValorNF = 0.0;
            double SomaValorClassificado = 0.0;
            for (int i = 0; i < linha.length; i++) {
                SomaValorPrevisto += ((Double) TbPagtos.getValueAt(linha[i], TbPagtos.VALORPREVISTO));
                SomaValorPago += ((Double) TbPagtos.getValueAt(linha[i], TbPagtos.VALORPAGO));
                SomaValorNF += ((Double) TbPagtos.getValueAt(linha[i], TbPagtos.VALORNF));
                SomaValorClassificado += ((Double) TbPagtos.getValueAt(linha[i], TbPagtos.VALORCLASSIFICADO));
            }
            texto = "<html><FONT FACE=\"Tahoma\" SIZE=3>"
                    + "Valor Previsto: <B>" + Corretores.ConverterDoubleReais(SomaValorPrevisto) + "</B><br>"
                    + "Valor Pago: <B>" + Corretores.ConverterDoubleReais(SomaValorPago) + "</B><br>"
                    + "Valor Notas Fiscais: <B>" + Corretores.ConverterDoubleReais(SomaValorNF) + "</B><br>"
                    + "Valor Classificado: <B>" + Corretores.ConverterDoubleReais(SomaValorClassificado) + "</B><br>"
                    + "</FONT></html>";
            ToolTipManager.sharedInstance().setInitialDelay(150);
            ToolTipManager.sharedInstance().setDismissDelay(30000);
            tabela.setToolTipText(texto);
        }
    }

    private String getTipoData() {
        String s = "";
        if (ch_dtPrevista.isSelected()) {
            return " DtPrevista ";
        } else {
            return " DtRealizado ";
        }
    }

    private String SQLWhere() {
        String s = "";
        s = " WHERE " + getTipoData() + " BETWEEN '" + Corretores.ConverterDateAAAA_MM_DD(txt_dtInicial.getDate()) + "' and '" + Corretores.ConverterDateAAAA_MM_DD(txt_dtFinal.getDate()) + "'";
        if (cb_contaOrigem.getSelectedIndex() > 0) {
            s += " and IDContaOrigem.IdConta = " + ((ContaBancariaBeans) cb_contaOrigem.getSelectedItem()).getIdConta();
        }
        if (cb_bancoDestino.getSelectedIndex() > 0) {
            s += " and Banco = '" + cb_bancoDestino.getSelectedItem().toString() + "'";
        }
        if (cb_formaPagto.getSelectedIndex() > 0) {
            s += " and FormaPagto = '" + cb_formaPagto.getSelectedItem().toString() + "'";
        }
        if (!txt_nDoc.getText().equals("")) {
            s += " and nDocumento like '%" + txt_nDoc.getText() + "%'";
        }

        if (fornecedor == null) {
            if (!txt_favorecido.getText().equals("")) {
                s += " and titular like '%" + txt_favorecido.getText() + "%'";
            }
        } else {
            s += " and FornecedorB.id = " + fornecedor.getID();
        }

        s += " Order By " + getTipoOrdenacao();
        return s;
    }

}
