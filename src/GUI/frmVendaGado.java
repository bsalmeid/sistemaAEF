package GUI;

import Beans.VendasGadoBeans;
import Controller.VendasGadoController;
import DAO.Diversas;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class frmVendaGado extends javax.swing.JInternalFrame {

    DefaultTableModel TbVendas;
    VendasGadoBeans VendasGadoB;
    VendasGadoController VendasGadoC;
    private Integer IdSelecionado;
    JPopupMenu pop;
    frmEscalasAbate telaAbate;
    Diversas DiversasD;
    CentralizarTabela Centralizar;
    CellRenderer CellRenderer;

    public frmVendaGado() {
        initComponents();
        Centralizar = new CentralizarTabela();

        TbVendas = (DefaultTableModel) tb_vendas.getModel();
        VendasGadoB = new VendasGadoBeans();
        VendasGadoC = new VendasGadoController();
        DiversasD = new Diversas();
        VendasGadoC.controleBuscarVendas(TbVendas);
        pop = new JPopupMenu();
        telaAbate = new frmEscalasAbate();
        cb_cliente.setModel(new DefaultComboBoxModel(new Vector(DiversasD.buscarFrigorifico())));
        tbVendaGado();

    }

    private JTable tbVendaGado() {
        CellRenderer = new CellRenderer();
        Centralizar.centralizarTabela(tb_vendas);
        ((DefaultTableCellRenderer) tb_vendas.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        tb_vendas.getColumnModel().getColumn(6).setCellRenderer(CellRenderer);
        tb_vendas.getColumnModel().getColumn(7).setCellRenderer(CellRenderer);
        tb_vendas.getColumnModel().getColumn(12).setCellRenderer(CellRenderer);
        tb_vendas.getColumnModel().getColumn(13).setCellRenderer(CellRenderer);
        
        return tb_vendas;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopup = new javax.swing.JPopupMenu();
        jMenuEscala = new javax.swing.JMenuItem();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        txt_nVenda = new javax.swing.JTextField();
        txt_dataVenda = new com.toedter.calendar.JDateChooser();
        cb_cliente = new javax.swing.JComboBox<>();
        cb_categoria = new javax.swing.JComboBox<>();
        txt_qCab = new javax.swing.JTextField();
        txt_qAr = new javax.swing.JTextField();
        txt_valorAr = new javax.swing.JTextField();
        txt_cepea = new javax.swing.JTextField();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        tb_vendas = new javax.swing.JTable();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        cb_mercado = new javax.swing.JComboBox<>();
        javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        txt_observ = new javax.swing.JTextArea();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        btn_Salvar = new javax.swing.JButton();
        btn_pesquisar = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        cb_situacao = new javax.swing.JComboBox<>();
        btn_novo = new javax.swing.JButton();

        jMenuEscala.setText("Adicionar Escala");
        jMenuEscala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEscalaActionPerformed(evt);
            }
        });
        jPopup.add(jMenuEscala);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Contratos de Venda de Gado");
        setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Nº Venda");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Data Venda");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Comprador");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Categoria");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Qt Cabeças");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Qt Arrobas");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("R$ / @");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Índice CEPEA");

        txt_nVenda.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txt_dataVenda.setEnabled(false);

        cb_cliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cb_cliente.setEnabled(false);
        cb_cliente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_clienteItemStateChanged(evt);
            }
        });

        cb_categoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Touros", "Vaca Parida", "Vaca Descarte", "Vaca Solteira", "Vacas", "Novilhas", "Bezerras", "Bezerros", "Garrotes", "Bois Magros", "Bois", " " }));
        cb_categoria.setEnabled(false);

        txt_qCab.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_qCab.setEnabled(false);

        txt_qAr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_qAr.setEnabled(false);

        txt_valorAr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_valorAr.setEnabled(false);
        txt_valorAr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_valorArFocusLost(evt);
            }
        });

        txt_cepea.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_cepea.setEnabled(false);
        txt_cepea.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_cepeaFocusLost(evt);
            }
        });

        tb_vendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Data Venda", "IDCLI", "Cliente", "Categoria", "Mercado", "Q Cab", "Q Arrobas", "Reais / @", "Cepea", "Observação", "Situação", "Qt Escalado", "Qt Embarcado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_vendas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_vendasMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tb_vendasMousePressed(evt);
            }
        });
        tb_vendas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tb_vendasKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tb_vendas);
        if (tb_vendas.getColumnModel().getColumnCount() > 0) {
            tb_vendas.getColumnModel().getColumn(0).setMinWidth(50);
            tb_vendas.getColumnModel().getColumn(0).setPreferredWidth(50);
            tb_vendas.getColumnModel().getColumn(0).setMaxWidth(70);
            tb_vendas.getColumnModel().getColumn(2).setMinWidth(0);
            tb_vendas.getColumnModel().getColumn(2).setPreferredWidth(0);
            tb_vendas.getColumnModel().getColumn(2).setMaxWidth(0);
            tb_vendas.getColumnModel().getColumn(10).setMinWidth(0);
            tb_vendas.getColumnModel().getColumn(10).setPreferredWidth(0);
            tb_vendas.getColumnModel().getColumn(10).setMaxWidth(0);
            tb_vendas.getColumnModel().getColumn(12).setCellRenderer(null);
        }

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Mercado");

        cb_mercado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "à Termo", "Balcão" }));
        cb_mercado.setEnabled(false);

        txt_observ.setColumns(20);
        txt_observ.setRows(5);
        txt_observ.setEnabled(false);
        jScrollPane2.setViewportView(txt_observ);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Observação");

        btn_Salvar.setBackground(new java.awt.Color(255, 255, 255));
        btn_Salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/salvar.png"))); // NOI18N
        btn_Salvar.setEnabled(false);
        btn_Salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalvarActionPerformed(evt);
            }
        });

        btn_pesquisar.setBackground(new java.awt.Color(255, 255, 255));
        btn_pesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar.png"))); // NOI18N
        btn_pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisarActionPerformed(evt);
            }
        });

        btn_editar.setBackground(new java.awt.Color(255, 255, 255));
        btn_editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/editar.png"))); // NOI18N
        btn_editar.setEnabled(false);
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });

        btn_excluir.setBackground(new java.awt.Color(255, 255, 255));
        btn_excluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/deletar.png"))); // NOI18N
        btn_excluir.setEnabled(false);
        btn_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Situação");

        cb_situacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Aberta", "Encerrada" }));
        cb_situacao.setEnabled(false);

        btn_novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/inserir.png"))); // NOI18N
        btn_novo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_qCab, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_qAr, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_valorAr, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_nVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_dataVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_cepea, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cb_situacao, 0, 107, Short.MAX_VALUE)
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cb_mercado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jScrollPane2)
                .addGap(18, 18, 18)
                .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_Salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_nVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(cb_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9)
                                .addComponent(cb_mercado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)
                                .addComponent(cb_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_dataVenda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_qCab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txt_qAr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(txt_valorAr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(txt_cepea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cb_situacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_SalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalvarActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(null, "Deseja Salvar esta Venda de Gado?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION) {
            popularBeans();
            VendasGadoC.controlerSalvarVenda(VendasGadoB, TbVendas);
            limparCampos();
        }
    }//GEN-LAST:event_btn_SalvarActionPerformed

    private void btn_pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisarActionPerformed
        VendasGadoC.controleBuscarVendas(TbVendas);
    }//GEN-LAST:event_btn_pesquisarActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja Editar esta Venda de Gado?", "Atenção", JOptionPane.YES_NO_OPTION);

        if (editar == JOptionPane.YES_OPTION) {
            popularBeans();
            VendasGadoB.setIdVenda(IdSelecionado);
            VendasGadoC.controleEditarVendas(VendasGadoB);
            VendasGadoC.controleBuscarVendas(TbVendas);
            limparCampos();
            desabilitarCampos();

        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja excluir esta Venda de Gado?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION) {
            VendasGadoB.setIdVenda(IdSelecionado);
            VendasGadoC.controleExcluir(VendasGadoB);
            VendasGadoC.controleBuscarVendas(TbVendas);
            limparCampos();
            desabilitarCampos();

        }
    }//GEN-LAST:event_btn_excluirActionPerformed

    private void txt_valorArFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorArFocusLost
        txt_valorAr.setText(Corretores.ConverterDecimalReais(txt_valorAr.getText()));
    }//GEN-LAST:event_txt_valorArFocusLost

    private void txt_cepeaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cepeaFocusLost
        txt_cepea.setText(Corretores.ConverterDecimalReais(txt_cepea.getText()));
    }//GEN-LAST:event_txt_cepeaFocusLost

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed
        btn_Salvar.setEnabled(true);
        btn_editar.setEnabled(false);
        btn_excluir.setEnabled(false);
        habilitarCampos();
        limparCampos();

    }//GEN-LAST:event_btn_novoActionPerformed

    private void tb_vendasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_vendasKeyPressed

    }//GEN-LAST:event_tb_vendasKeyPressed

    private void tb_vendasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_vendasMousePressed
         int rows [] = tb_vendas.getSelectedRows();
         tb_vendas.setToolTipText(null);
         if (rows.length == 1){
         try {
                int linha = tb_vendas.getSelectedRow();
                txt_nVenda.setText(TbVendas.getValueAt(linha, 0).toString());
                txt_dataVenda.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(TbVendas.getValueAt(linha, 1).toString()));
                cb_cliente.setSelectedItem((TbVendas.getValueAt(linha, 3)));
                cb_categoria.setSelectedItem((TbVendas.getValueAt(linha, 4)));
                cb_mercado.setSelectedItem((TbVendas.getValueAt(linha, 5)));
                txt_qCab.setText(TbVendas.getValueAt(linha, 6).toString());
                txt_qAr.setText(TbVendas.getValueAt(linha, 7).toString());
                txt_valorAr.setText(TbVendas.getValueAt(linha, 8).toString());
                txt_cepea.setText(TbVendas.getValueAt(linha, 9).toString());
                cb_situacao.setSelectedItem((TbVendas.getValueAt(linha, 11).toString()));
            } catch (Exception e) {
            }
         } else {
             TipTextQMinuta(tb_vendas, 6, 12, 13);
         }
        
        
    }//GEN-LAST:event_tb_vendasMousePressed

    private void tb_vendasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_vendasMouseClicked
        if (jPopup.isVisible() == true) {
            jPopup.setVisible(false);
        }
        if (evt.getButton() == MouseEvent.BUTTON3) {
            MouseEVENT(evt);
            if (TbVendas.getValueAt(tb_vendas.getSelectedRow(), 11).equals("Encerrada")) {
                jMenuEscala.setEnabled(false);
            } else {
                jMenuEscala.setEnabled(true);
            }
        }

        if (evt.getClickCount() == 2) {
            int editar = JOptionPane.showConfirmDialog(null, "Deseja habilitar está venda para edição?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (editar == JOptionPane.YES_OPTION) {
                IdSelecionado = Integer.parseInt(TbVendas.getValueAt(tb_vendas.getSelectedRow(), 0).toString());
                habilitarCampos();
                btn_Salvar.setEnabled(false);
                btn_editar.setEnabled(true);
                btn_excluir.setEnabled(true);
            }
        } else {
            desabilitarCampos();
            btn_Salvar.setEnabled(false);
            btn_editar.setEnabled(false);
            btn_excluir.setEnabled(false);
        }

    }//GEN-LAST:event_tb_vendasMouseClicked

    private void jMenuEscalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuEscalaActionPerformed
        this.getParent().add(telaAbate);
        telaAbate.setVisible(true);
    }//GEN-LAST:event_jMenuEscalaActionPerformed

    private void cb_clienteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_clienteItemStateChanged
        VendasGadoB.setIdCli(DiversasD.listIDFrigorifico.get(cb_cliente.getSelectedIndex()));
    }//GEN-LAST:event_cb_clienteItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btn_Salvar;
    javax.swing.JButton btn_editar;
    javax.swing.JButton btn_excluir;
    javax.swing.JButton btn_novo;
    javax.swing.JButton btn_pesquisar;
    javax.swing.JComboBox<String> cb_categoria;
    javax.swing.JComboBox<String> cb_cliente;
    javax.swing.JComboBox<String> cb_mercado;
    javax.swing.JComboBox<String> cb_situacao;
    javax.swing.JMenuItem jMenuEscala;
    javax.swing.JPopupMenu jPopup;
    javax.swing.JTable tb_vendas;
    javax.swing.JTextField txt_cepea;
    com.toedter.calendar.JDateChooser txt_dataVenda;
    javax.swing.JTextField txt_nVenda;
    javax.swing.JTextArea txt_observ;
    javax.swing.JTextField txt_qAr;
    javax.swing.JTextField txt_qCab;
    javax.swing.JTextField txt_valorAr;
    // End of variables declaration//GEN-END:variables

    final void popularBeans() {

        VendasGadoB.setDataVenda(new SimpleDateFormat("dd/MM/yyyy").format(txt_dataVenda.getDate()));
//  VendasGadoB.setIdCli(Integer.parseInt(txt_idCli.getText()));
        VendasGadoB.setCliente(cb_cliente.getSelectedItem().toString());
        VendasGadoB.setCategoria(cb_categoria.getSelectedItem().toString());
        VendasGadoB.setMercado(cb_mercado.getSelectedItem().toString());
        VendasGadoB.setqCab(Integer.parseInt(txt_qCab.getText()));
        VendasGadoB.setqArrobas(Double.parseDouble(txt_qAr.getText()));
        VendasGadoB.setReaisArr(Corretores.ConverterReaisDouble(txt_valorAr.getText()));
        VendasGadoB.setCepea(Corretores.ConverterReaisDouble(txt_cepea.getText()));
        VendasGadoB.setObservacao(txt_observ.getText());
        VendasGadoB.setSituacao(cb_situacao.getSelectedItem().toString());

    }

    final void limparCampos() {
        txt_dataVenda.setDate(null);
        // txt_idCli.setText("");
        cb_cliente.setSelectedItem("-");
        cb_mercado.setSelectedItem("-");
        cb_categoria.setSelectedItem("-");
        txt_qCab.setText("0");
        txt_qAr.setText("");
        txt_valorAr.setText("0");
        txt_cepea.setText("0");
        txt_observ.setText("");
    }

    final void habilitarCampos() {
        txt_dataVenda.setEnabled(true);
        cb_cliente.setEnabled(true);
        cb_categoria.setEnabled(true);
        cb_mercado.setEnabled(true);
        txt_qCab.setEnabled(true);
        txt_qAr.setEnabled(true);
        txt_valorAr.setEnabled(true);
        txt_cepea.setEnabled(true);
        cb_situacao.setEnabled(true);
        txt_observ.setEnabled(true);
    }

    final void desabilitarCampos() {
        txt_dataVenda.setEnabled(false);
        cb_cliente.setEnabled(false);
        cb_categoria.setEnabled(false);
        cb_mercado.setEnabled(false);
        txt_qCab.setEnabled(false);
        txt_qAr.setEnabled(false);
        txt_valorAr.setEnabled(false);
        txt_cepea.setEnabled(false);
        cb_situacao.setEnabled(false);
    }

    public void MouseEVENT(MouseEvent evt) {
        pop = jPopup;
        pop.show(this, 0, 0);
        pop.setLocation(evt.getLocationOnScreen());
    }

    private void TipTextQMinuta(JTable tabela, int column ,int column1, int column2) {
        tabela.setToolTipText(null);
        int[] linha = tabela.getSelectedRows();
        if (linha.length > 1) {
            String texto = "";
            int SomaQVendido = 0;
            int SomaQEscalado = 0;
            int SomaQEmbarcado = 0;
            for (int i = 0; i < linha.length; i++) {
                SomaQVendido += (Integer)(tabela.getValueAt(linha[i], column));
                SomaQEscalado += (Integer)(tabela.getValueAt(linha[i], column1));
                SomaQEmbarcado += (Integer)(tabela.getValueAt(linha[i], column2));
            }

            texto = "<html><FONT FACE=\"Tahoma\" SIZE=3>"
                    + "Quantidade de Cabeças Vendido é: <B>" + new DecimalFormat("#,###,##0").format(SomaQVendido) + "</B><br>"
                    + "Quantidade de Cabeças Escalada é: <B>" + new DecimalFormat("#,###,##0").format(SomaQEscalado) + "</B><br>"
                    + "Quantidade de Cabeças Embarcada é: <B>" + new DecimalFormat("#,###,##0").format(SomaQEmbarcado) + "</B><br>"
                    + "</FONT></html>";
            ToolTipManager.sharedInstance().setInitialDelay(150);
            ToolTipManager.sharedInstance().setDismissDelay(30000);
            tabela.setToolTipText(texto);
        }
    }
    
    
        class CellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setHorizontalAlignment(SwingConstants.CENTER);
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            Object val = table.getValueAt(row, column);
                if (val instanceof Integer){
                    Integer valorD = (Integer) val;
                    setText(new DecimalFormat("#,###,##0").format(valorD));
                }
            return this;
        }
    }




}
