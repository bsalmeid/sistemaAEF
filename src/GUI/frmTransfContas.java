/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Beans.ContaBancariaBeans;
import Beans.PlanoContaBeans;
import Beans.TransferenciasBancosBeans;
import DAO.Diversas;
import DAO.DiversasHibernate;
import DAO.TransferenciasBancosDAO;
import static GUI.Principal.listContaOrigem;
import static GUI.Principal.listPlanoContas;
import static GUI.frmLogin.dataAtual;
import Icones.FormatarICO;
import TableModel.TableModelTransfBancos;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import Utilitarios.ReaisCellRenderer;
import Utilitarios.ValidarPermissoes;
import com.sun.glass.events.KeyEvent;
import com.toedter.calendar.JDateChooser;
import componentes.UJComboBox;
import java.awt.AWTKeyStroke;
import java.awt.Component;
import java.awt.Container;
import java.awt.KeyboardFocusManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Objects;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class frmTransfContas extends javax.swing.JInternalFrame {

    Diversas DiversasD;
    TransferenciasBancosBeans TransfB;
    TransferenciasBancosDAO TransfD;
    TableModelTransfBancos TbTransf;
    CentralizarTabela Centralizar;

    public frmTransfContas() {
        initComponents();
        DiversasD = new Diversas();
        TransfB = new TransferenciasBancosBeans();
        TransfD = new TransferenciasBancosDAO();

        listPlanoContas();
        carregarContasOrigem();
        getTabela();
        preencherDatas();
        Keypress(jPanel2);
        Keypress(jPanel1);
    }

    private void Keypress(Container container) {
        for (Component c : container.getComponents()) {
            HashSet<AWTKeyStroke> conjForward = new HashSet<>(c.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
            conjForward.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
            c.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conjForward);
        }
    }

    private JTable getTabela() {
        tb_transf.setModel(getTableModel());
        Centralizar = new CentralizarTabela();
        Centralizar.centralizarTabela(tb_transf);
        ((DefaultTableCellRenderer) tb_transf.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        tb_transf.getColumnModel().getColumn(0).setPreferredWidth(40);// ID Status
        tb_transf.getColumnModel().getColumn(0).setMinWidth(40);
        tb_transf.getColumnModel().getColumn(0).setMaxWidth(40);

        tb_transf.getColumnModel().getColumn(2).setPreferredWidth(0);// ID Conta Origem
        tb_transf.getColumnModel().getColumn(2).setMinWidth(0);
        tb_transf.getColumnModel().getColumn(2).setMaxWidth(0);
        tb_transf.getColumnModel().getColumn(4).setPreferredWidth(0);// ID Conta Destino
        tb_transf.getColumnModel().getColumn(4).setMinWidth(0);
        tb_transf.getColumnModel().getColumn(4).setMaxWidth(0);
        tb_transf.getColumnModel().getColumn(8).setPreferredWidth(0);// Descricao Plano Contas
        tb_transf.getColumnModel().getColumn(8).setMinWidth(0);
        tb_transf.getColumnModel().getColumn(8).setMaxWidth(0);
        tb_transf.getColumnModel().getColumn(9).setCellRenderer(new ReaisCellRenderer());
        return tb_transf;
    }

    private void listPlanoContas() {
        if (listPlanoContas == null) {
            listPlanoContas = DiversasHibernate.getPlanoConta();
        }
        cb_planoConta.addItem(new PlanoContaBeans(0, 0, "-"));
        for (PlanoContaBeans p : listPlanoContas) {
            if (p.getGrupoConta() != null && p.getGrupoConta().getTransferencia() == true) {
                cb_planoConta.addItem(p);
            }
        }
    }

    private void carregarContasOrigem() {
        if (listContaOrigem == null) {
            listContaOrigem = DiversasHibernate.getListaContasBancarias();
        }
        ContaBancariaBeans l = new ContaBancariaBeans(0, "-");
        cb_contaOrigem.addItem(l);
        cb_contaDestino.addItem(l);
        cb_origemPesq.addItem(l);
        cb_destinoPesq.addItem(l);
        for (ContaBancariaBeans list : listContaOrigem) {
            cb_contaOrigem.addItem(list);
            cb_contaDestino.addItem(list);
            cb_origemPesq.addItem(list);
            cb_destinoPesq.addItem(list);
        }
    }

    private TableModelTransfBancos getTableModel() {
        if (TbTransf == null) {
            TbTransf = new TableModelTransfBancos();
        }
        return TbTransf;
    }

    private void preencherDatas() {
        try {
            txt_data.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(dataAtual));
            txt_dtInicial.setDate(Corretores.PrimeiroDiaMes());
            txt_dtFinal.setDate(Corretores.UltimoDiaMes());
        } catch (ParseException ex) {

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        txt_data =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        cb_contaOrigem = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        cb_contaDestino = new javax.swing.JComboBox<>();
        txt_descricao = new javax.swing.JTextField();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        txt_valor = new javax.swing.JTextField();
        txt_id = new javax.swing.JTextField();
        btn_Salvar = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();
        txt_descConta = new javax.swing.JTextField();
        btn_novo = new javax.swing.JButton();
        cb_planoConta = new componentes.UJComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_transf = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        txt_dtInicial =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        txt_dtFinal =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        cb_origemPesq = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        cb_destinoPesq = new javax.swing.JComboBox<>();
        btn_pesquisar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Transferências Bancárias");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("ID");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Data do Movimento");

        cb_contaOrigem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cb_contaOrigemFocusGained(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Conta de Origem");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Conta de Destino");

        cb_contaDestino.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cb_contaDestinoFocusGained(evt);
            }
        });

        txt_descricao.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_descricao.setText("TRANSFERENCIA");
        txt_descricao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_descricaoFocusGained(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Descricão");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Valor Transferido");

        txt_valor.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_valor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_valorFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_valorFocusLost(evt);
            }
        });

        txt_id.setEditable(false);
        txt_id.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_id.setEnabled(false);

        btn_Salvar.setBackground(new java.awt.Color(255, 255, 255));
        btn_Salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/salvar_32_32.png"))); // NOI18N
        btn_Salvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalvarActionPerformed(evt);
            }
        });

        btn_editar.setBackground(new java.awt.Color(255, 255, 255));
        btn_editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/editar_32_32.png"))); // NOI18N
        btn_editar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_editar.setEnabled(false);
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });

        btn_excluir.setBackground(new java.awt.Color(255, 255, 255));
        btn_excluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/deletar_32_32.png"))); // NOI18N
        btn_excluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_excluir.setEnabled(false);
        btn_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirActionPerformed(evt);
            }
        });

        txt_descConta.setEditable(false);
        txt_descConta.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_descConta.setEnabled(false);

        btn_novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/inserir-32.png"))); // NOI18N
        btn_novo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novoActionPerformed(evt);
            }
        });

        cb_planoConta.setAutocompletar(true);
        cb_planoConta.setMaximumSize(new java.awt.Dimension(140, 32767));
        cb_planoConta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_planoContaItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_descConta, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_contaOrigem, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_contaDestino, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_descricao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(txt_valor, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_data, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(cb_planoConta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(txt_data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3)
                    .addComponent(cb_contaOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(cb_contaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5)
                    .addComponent(txt_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_planoConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_descConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel7)
                    .addComponent(txt_valor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(86, Short.MAX_VALUE))
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tb_transf.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb_transf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_transfMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_transf);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("à");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Origem");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Destino");

        btn_pesquisar.setText("Pesquisar");
        btn_pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_dtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_origemPesq, 0, 140, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_destinoPesq, 0, 140, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_pesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel8)
                    .addComponent(txt_dtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(cb_origemPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(cb_destinoPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesquisar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_SalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalvarActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(null, "Deseja Salvar esta Transferência Entre Contas?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION && ValidarPermissoes.validarPermissaoInsert(frmTransfContas.class.getSimpleName())) {
            popularBeans(TransfB);
            if (verificarBeans(TransfB)) {
                if (TransfD.inserirMovimento(TransfB)) {
                    limparCampos();
                }
            }
        }
    }//GEN-LAST:event_btn_SalvarActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja editar Esta Transferência Bancária?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION && ValidarPermissoes.validarPermissaoUpdate(frmTransfContas.class.getSimpleName())) {
            popularBeans(TransfB);
            if (verificarBeans(TransfB)) {
                if (TransfD.editarTransferencia(TransfB)) {
                    limparCampos();
                    btn_editar.setEnabled(false);
                    btn_excluir.setEnabled(false);
                    btn_Salvar.setEnabled(true);
                }
            }
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        int excluir = JOptionPane.showConfirmDialog(null, "Deseja excluir esta Transferência", "Atenção", JOptionPane.YES_NO_OPTION);
        if (excluir == JOptionPane.YES_OPTION && ValidarPermissoes.validarPermissaoDelete(frmTransfContas.class.getSimpleName())) {
            if (TransfD.excluirTransferencia(TransfB)) {
                limparCampos();
                btn_editar.setEnabled(false);
                btn_excluir.setEnabled(false);
                btn_Salvar.setEnabled(true);
            }
        }
    }//GEN-LAST:event_btn_excluirActionPerformed

    private void txt_valorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorFocusLost
        txt_valor.setText(Corretores.ConverterDecimalReais(txt_valor.getText()));
    }//GEN-LAST:event_txt_valorFocusLost

    private void btn_pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisarActionPerformed
        if (ValidarPermissoes.validarPermissaoSelect(frmTransfContas.class.getSimpleName())) {
            if (txt_dtInicial.getDate() != null && txt_dtFinal.getDate() != null) {
                TbTransf.limpar();
                TbTransf.addLista(TransfD.resumoTransferencias(SQLWhere()));
            } else {
                JOptionPane.showMessageDialog(null, "O Intervalo de Data é Obrigatório", "Erro", 0, FormatarICO.ICObtnSair());
            }
        }
    }//GEN-LAST:event_btn_pesquisarActionPerformed

    private void tb_transfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_transfMouseClicked
        if (evt.getClickCount() == 2) {
            int linha = tb_transf.getSelectedRow();
            TransfB = TransfD.buscarTransferencias((Integer) TbTransf.getValueAt(linha, 0));
            preencherFormulario(TransfB);
            btn_editar.setEnabled(true);
            btn_excluir.setEnabled(true);
            btn_Salvar.setEnabled(false);
        }
    }//GEN-LAST:event_tb_transfMouseClicked

    private void txt_descricaoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_descricaoFocusGained
        txt_descricao.selectAll();
    }//GEN-LAST:event_txt_descricaoFocusGained

    private void txt_valorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorFocusGained
        txt_valor.selectAll();
    }//GEN-LAST:event_txt_valorFocusGained

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed
        btn_Salvar.setEnabled(true);
        btn_editar.setEnabled(false);
        txt_data.requestFocus();
        limparCampos();
    }//GEN-LAST:event_btn_novoActionPerformed

    private void cb_contaOrigemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cb_contaOrigemFocusGained
        setExpandedComboBox(evt.getComponent());
    }//GEN-LAST:event_cb_contaOrigemFocusGained

    private void cb_contaDestinoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cb_contaDestinoFocusGained
        setExpandedComboBox(evt.getComponent());
    }//GEN-LAST:event_cb_contaDestinoFocusGained

    private void cb_planoContaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_planoContaItemStateChanged
        txt_descConta.setText(getPlanoConta(cb_planoConta).getDescricao());
    }//GEN-LAST:event_cb_planoContaItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btn_Salvar;
    javax.swing.JButton btn_editar;
    javax.swing.JButton btn_excluir;
    javax.swing.JButton btn_novo;
    private javax.swing.JButton btn_pesquisar;
    private javax.swing.JComboBox<Beans.ContaBancariaBeans> cb_contaDestino;
    private javax.swing.JComboBox<Beans.ContaBancariaBeans> cb_contaOrigem;
    private javax.swing.JComboBox<Beans.ContaBancariaBeans> cb_destinoPesq;
    private javax.swing.JComboBox<Beans.ContaBancariaBeans> cb_origemPesq;
    private componentes.UJComboBox cb_planoConta;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_transf;
    private com.toedter.calendar.JDateChooser txt_data;
    private javax.swing.JTextField txt_descConta;
    private javax.swing.JTextField txt_descricao;
    private com.toedter.calendar.JDateChooser txt_dtFinal;
    private com.toedter.calendar.JDateChooser txt_dtInicial;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_valor;
    // End of variables declaration//GEN-END:variables

    private void popularBeans(TransferenciasBancosBeans TransfB) {
        TransfB.setDescricao(txt_descricao.getText());
        TransfB.setDtMovimentos(txt_data.getDate());
        TransfB.setIdContaDestino(getIDContaOrigem(cb_contaDestino));
        TransfB.setIdContaOrigem(getIDContaOrigem(cb_contaOrigem));
        TransfB.setPlanoContas(getPlanoConta(cb_planoConta));
        TransfB.setDescPlanoContas(getPlanoConta(cb_planoConta).getDescricao());
        TransfB.setValor(Corretores.ConverterReaisDouble(txt_valor.getText()));
    }

    private void preencherFormulario(TransferenciasBancosBeans TransfB) {
        txt_id.setText(TransfB.getID().toString());
        txt_data.setDate(TransfB.getDtMovimentos());
        setComboBoxContaOrigem(cb_contaOrigem, TransfB.getIdContaOrigem().getIdConta());
        setComboBoxContaOrigem(cb_contaDestino, TransfB.getIdContaDestino().getIdConta());
        txt_descricao.setText(TransfB.getDescricao());
        setComboBoxPlanoConta(cb_planoConta, TransfB.getPlanoContas().getID());
        txt_descConta.setText(TransfB.getDescPlanoContas());
        txt_valor.setText(Corretores.ConverterDoubleReais(TransfB.getValor()));
    }

    private Boolean verificarBeans(TransferenciasBancosBeans T) {
        if (T.getDtMovimentos() == null) {
            JOptionPane.showMessageDialog(null, "Verifique a Data do Movimento!", "Erro", 0, FormatarICO.ICObtnSair());
        }
        if (Corretores.verificarData(T.getDtMovimentos()) == false) {
            return false;
        }
        if (T.getValor() == 0.00) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Valor!", "Erro", 0, FormatarICO.ICObtnSair());
        }
        if (T.getPlanoContas().getID() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Plano de Contas!", "Erro", 0, FormatarICO.ICObtnSair());
        }
        if (T.getDescricao().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Descrição!", "Erro", 0, FormatarICO.ICObtnSair());
        }
        return true;
    }

    private void limparCampos() {
        txt_id.setText(null);
        txt_data.setDate(null);
        cb_contaOrigem.setSelectedIndex(0);
        cb_contaDestino.setSelectedIndex(0);
        txt_descricao.setText("TRANSFERENCIA");
        cb_planoConta.setSelectedIndex(0);
        txt_descConta.setText(null);
        txt_valor.setText("R$ 0,00");
    }

    private PlanoContaBeans getPlanoConta(UJComboBox combo) {
        return (PlanoContaBeans) combo.getSelectedItem();
    }

    private ContaBancariaBeans getIDContaOrigem(JComboBox<ContaBancariaBeans> comboBox) {
        return (ContaBancariaBeans) comboBox.getSelectedItem();
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

    private void setComboBoxPlanoConta(UJComboBox combo, Integer ID) {
        for (int i = 0; i < combo.getModel().getSize(); i++) {
            PlanoContaBeans p = (PlanoContaBeans) combo.getItemAt(i);
            if (Objects.equals(p.getID(), ID)) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }

    private void setExpandedComboBox(Component c) {
        if (c instanceof JComboBox) {
            ((JComboBox) c).setPopupVisible(true);
        } else if (c instanceof UJComboBox) {
            ((UJComboBox) c).setPopupVisible(true);
        }
    }

    private String SQLWhere() {
        String s = "";
        s = " WHERE T.dtMovimentos BETWEEN '" + Corretores.ConverterDateAAAA_MM_DD(txt_dtInicial.getDate()) + "' and '" + Corretores.ConverterDateAAAA_MM_DD(txt_dtFinal.getDate()) + "' ";
        if (cb_origemPesq.getSelectedIndex() > 0) {
            s += " and T.idContaOrigem = " + getIDContaOrigem(cb_origemPesq).getIdConta();
        }
        if (cb_destinoPesq.getSelectedIndex() > 0) {
            s += " and T.idContaDestino = " + getIDContaOrigem(cb_destinoPesq).getIdConta();
        }
        return s;
    }

}
