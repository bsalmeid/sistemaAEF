/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Beans.CadColaboradorBeans;
import Beans.CEIBeans;
import Beans.CadColaboradorClassBeans;
import Beans.CargosBeans;
import Beans.ListSetorTrabalhoBeans;
import Beans.PropriedadesBeans;
import DAO.CadColaboradorDAO;
import DAO.Diversas;
import DAO.DiversasHibernate;
import static GUI.Principal.ListaPropriedades;
import static GUI.Principal.listCEI;
import static GUI.Principal.listCargosFuncoes;
import static GUI.Principal.listSetorTrabalho;
import static GUI.Principal.tela;
import GerarRelatorios.RelatoriosFuncionario;
import TableModel.TableModelConsultaColaborador;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import Utilitarios.ValidarPermissoes;
import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class frmResumoCadColaborador extends javax.swing.JInternalFrame {

    TableModelConsultaColaborador TbFuncionario;
    CadColaboradorClassBeans ClassB;
    CentralizarTabela Centralizar;
    CadColaboradorDAO FuncD;
    CadColaboradorBeans FuncB;
    Diversas DiversasD;
    MaskFormatter CPFMask;
    CellRenderer cellRenderer;

    public frmResumoCadColaborador() {
        initComponents();
        FuncD = new CadColaboradorDAO();
        FuncB = new CadColaboradorBeans();
        ClassB = new CadColaboradorClassBeans();
        DiversasD = new Diversas();
        cellRenderer = new CellRenderer();
        carregarTabela();
        carregarListCEI();
        carregarCargosFuncoes();
        carregarFazendas();
        carregarSetores();
        maskFormaterCPF();

        System.out.println("Fim Construtor");

    }

    private void carregarListCEI() {
        if (listCEI == null) {
            listCEI = DiversasHibernate.getListaCEI();
        }
        CEIBeans b = new CEIBeans(0, "-");
        cb_cei.addItem(b);
        for (CEIBeans list : listCEI) {
            cb_cei.addItem(list);
        }

    }

    private void carregarCargosFuncoes() {
        if (listCargosFuncoes == null) {
            listCargosFuncoes = DiversasHibernate.getListaCargos();
        }
        cb_funcao.addItem(new CargosBeans(0, "-", 0.00));
        for (CargosBeans list : listCargosFuncoes) {
            cb_funcao.addItem(list);
        }
    }

    private void carregarSetores() {
        if (listSetorTrabalho == null) {
            listSetorTrabalho = DiversasHibernate.getListaSetoresTrabalho();
        }
        cb_setor.addItem(new ListSetorTrabalhoBeans(0, "-"));
        for (ListSetorTrabalhoBeans list : listSetorTrabalho) {
            cb_setor.addItem(list);
        }
    }

    private void carregarFazendas() {
        if (ListaPropriedades == null) {
            ListaPropriedades = DiversasHibernate.getListaFazendas();
        }
        PropriedadesBeans P = new PropriedadesBeans();
        P.setCodigo(0);
        P.setNome("-");
        cb_fazenda.addItem(P);
        for (PropriedadesBeans l : ListaPropriedades) {
            cb_fazenda.addItem(l);
        }

    }

    private void maskFormaterCPF() {
        try {
            CPFMask = new MaskFormatter("###.###.###-##");
            txt_cpf.setFormatterFactory(new DefaultFormatterFactory(CPFMask));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    private JTable carregarTabela() {
        tb_funcionarios.setModel(getTableModel());
        Centralizar = new CentralizarTabela();
        Centralizar.centralizarTabela(tb_funcionarios);
        ((DefaultTableCellRenderer) tb_funcionarios.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        tb_funcionarios.getColumnModel().getColumn(TbFuncionario.STATUS).setPreferredWidth(70);// Status
        tb_funcionarios.getColumnModel().getColumn(TbFuncionario.NOME).setPreferredWidth(180);// Nome
        tb_funcionarios.getColumnModel().getColumn(TbFuncionario.CEI).setPreferredWidth(120);// Cei/3
        tb_funcionarios.getColumnModel().getColumn(TbFuncionario.FAZENDA).setPreferredWidth(120);// Fazenda
        tb_funcionarios.getColumnModel().getColumn(TbFuncionario.SETOR).setPreferredWidth(100);// Setor
        tb_funcionarios.getColumnModel().getColumn(TbFuncionario.FUNCAO).setPreferredWidth(120);// Função
        tb_funcionarios.getColumnModel().getColumn(TbFuncionario.SALARIO).setPreferredWidth(100);// Salario/8
        tb_funcionarios.getColumnModel().getColumn(TbFuncionario.DT_ADMISSAO).setPreferredWidth(100);// Dt Admissão
        tb_funcionarios.getColumnModel().getColumn(TbFuncionario.SALARIO).setCellRenderer(cellRenderer);
        tb_funcionarios.getColumnModel().getColumn(TbFuncionario.CEI).setCellRenderer(cellRenderer);

        return tb_funcionarios;
    }

    private TableModelConsultaColaborador getTableModel() {
        if (TbFuncionario == null) {
            TbFuncionario = new TableModelConsultaColaborador();
        }
        return TbFuncionario;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopup = new javax.swing.JPopupMenu();
        jmenu_editar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmenu_imprimir = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenu_addclassificacao = new javax.swing.JMenuItem();
        jXTaskPane1 = new org.jdesktop.swingx.JXTaskPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_dtInicial = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        txt_dtFinal = new com.toedter.calendar.JDateChooser();
        ch_dtAdmissao = new javax.swing.JCheckBox();
        ch_dtDemissao = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        cb_cei = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cb_fazenda = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cb_setor = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cb_status = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txt_nome = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        cb_funcao = new javax.swing.JComboBox<>();
        txt_cpf = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_funcionarios = new javax.swing.JTable();

        jmenu_editar.setText("Editar Cadastro");
        jmenu_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmenu_editarActionPerformed(evt);
            }
        });
        jPopup.add(jmenu_editar);
        jPopup.add(jSeparator1);

        jmenu_imprimir.setText("Imprimir Ficha");
        jmenu_imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmenu_imprimirActionPerformed(evt);
            }
        });
        jPopup.add(jmenu_imprimir);
        jPopup.add(jSeparator2);

        jMenu_addclassificacao.setText("Adicionar Classificação");
        jMenu_addclassificacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_addclassificacaoActionPerformed(evt);
            }
        });
        jPopup.add(jMenu_addclassificacao);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro de Funcionários");

        jXTaskPane1.setTitle("Opções de Pesquisa");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("De");

        txt_dtInicial.setMaximumSize(new java.awt.Dimension(130, 2147483647));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("a");

        txt_dtFinal.setMaximumSize(new java.awt.Dimension(130, 2147483647));

        buttonGroup1.add(ch_dtAdmissao);
        ch_dtAdmissao.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_dtAdmissao.setSelected(true);
        ch_dtAdmissao.setText("Admissão");

        buttonGroup1.add(ch_dtDemissao);
        ch_dtDemissao.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_dtDemissao.setText("Demissão");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("CEI");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Fazenda");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Setor");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Função");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Status");

        cb_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Aguardando Registro", "Ativo", "Inativo", "INSS", "Licença Maternidade", "Licença Paternidade", "Outras Licenças" }));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Nome");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("CPF");

        jButton1.setMnemonic('e');
        jButton1.setText("Pesquisar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cb_funcao.setEditable(true);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Código");

        txt_codigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_funcao, 0, 226, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_status, 0, 231, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nome, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_cpf, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_dtInicial, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dtFinal, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(ch_dtAdmissao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ch_dtDemissao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_cei, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_fazenda, 0, 106, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_setor, 0, 108, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(txt_dtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txt_dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ch_dtAdmissao)
                    .addComponent(ch_dtDemissao)
                    .addComponent(jLabel3)
                    .addComponent(cb_cei, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cb_setor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel8)
                    .addComponent(txt_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(cb_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(cb_funcao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jXTaskPane1.getContentPane().add(jPanel1);

        tb_funcionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tb_funcionarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_funcionariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_funcionarios);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        TbFuncionario.limpar();
        TbFuncionario.addLista(FuncD.listarColaborador(getWhere()));

    }//GEN-LAST:event_jButton1ActionPerformed

    private void tb_funcionariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_funcionariosMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            if (jPopup.isVisible() == true) {
                jPopup.setVisible(false);
            } else {
                jPopup.setVisible(true);
                jPopup.show(this, 0, 0);
                jPopup.setLocation(evt.getLocationOnScreen());
            }
        }
    }//GEN-LAST:event_tb_funcionariosMouseClicked

    private void jmenu_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmenu_editarActionPerformed
        int index = tb_funcionarios.getSelectedRow();
        if (index >= 0) {
            FuncB = FuncD.buscarColaborador((Integer) TbFuncionario.getValueAt(index, TbFuncionario.ID_COLABORADOR));
            if (FuncB != null) {
                frmCadColaborador frmCadastro = new frmCadColaborador();
                frmCadastro.FuncB = FuncB;
                this.getParent().add(frmCadastro);
                frmCadastro.tp_filiacao.setExpanded(false);
                frmCadastro.tp_clas.setExpanded(false);
                frmCadastro.setSize(new Dimension(frmCadastro.getSize().width, tela.getSize().height));
                frmCadastro.setVisible(true);
                frmCadastro.habilitarCampos();
                frmCadastro.preencherFormulario(FuncB);
                frmCadastro.btn_salvarPedido.setEnabled(false);
                frmCadastro.btn_editarPedido.setEnabled(true);
                frmCadastro.btn_excluirPedido.setEnabled(true);
            }
        }
    }//GEN-LAST:event_jmenu_editarActionPerformed

    private void jmenu_imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmenu_imprimirActionPerformed
        int index = tb_funcionarios.getSelectedRow();
        int confirma = JOptionPane.showConfirmDialog(null, "Deseja visualizar o relatório do Funcionário?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            Integer IDFuncionario = (Integer) TbFuncionario.getValueAt(index, TbFuncionario.ID_COLABORADOR);
            RelatoriosFuncionario.imprimirRelatorioFuncionario(IDFuncionario);
        }
    }//GEN-LAST:event_jmenu_imprimirActionPerformed

    private void jMenu_addclassificacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_addclassificacaoActionPerformed
        int[] indexs = tb_funcionarios.getSelectedRows();
        if (indexs.length > 0) {
            int confirma = JOptionPane.showConfirmDialog(null, "<html> <B>Atencão, Está Ação Irá Editar todos os Colaboradores Selecionados, </B> <br> Deseja Continuar? </html>", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION && ValidarPermissoes.validarPermissaoUpdate(frmCadColaborador.class.getSimpleName())) {
                frmCadColaboradorClassificacao frmClassificacao = new frmCadColaboradorClassificacao(null, true, getListaIDColaborador(indexs));
                frmClassificacao.setLocationRelativeTo(null);
                frmClassificacao.setVisible(true);
            }
        }
    }//GEN-LAST:event_jMenu_addclassificacaoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<CEIBeans> cb_cei;
    private javax.swing.JComboBox<PropriedadesBeans> cb_fazenda;
    private javax.swing.JComboBox<Beans.CargosBeans> cb_funcao;
    private javax.swing.JComboBox<ListSetorTrabalhoBeans> cb_setor;
    private javax.swing.JComboBox<String> cb_status;
    private javax.swing.JCheckBox ch_dtAdmissao;
    private javax.swing.JCheckBox ch_dtDemissao;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenu_addclassificacao;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopup;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane1;
    private javax.swing.JMenuItem jmenu_editar;
    private javax.swing.JMenuItem jmenu_imprimir;
    private javax.swing.JTable tb_funcionarios;
    private javax.swing.JTextField txt_codigo;
    private javax.swing.JFormattedTextField txt_cpf;
    private com.toedter.calendar.JDateChooser txt_dtFinal;
    private com.toedter.calendar.JDateChooser txt_dtInicial;
    private javax.swing.JTextField txt_nome;
    // End of variables declaration//GEN-END:variables

    private CEIBeans getCEI(JComboBox<CEIBeans> combo) {
        return (CEIBeans) combo.getSelectedItem();
    }

    private PropriedadesBeans getFazenda(JComboBox<PropriedadesBeans> comboBox) {
        return (PropriedadesBeans) comboBox.getSelectedItem();
    }

    private CargosBeans getFuncao(JComboBox<CargosBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return (CargosBeans) comboBox.getSelectedItem();
        }
        return null;
    }

    private ListSetorTrabalhoBeans getSetor(JComboBox<ListSetorTrabalhoBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return (ListSetorTrabalhoBeans) comboBox.getSelectedItem();
        }
        return null;
    }

    private String getWhere() {
        String s = "";
        if (txt_dtInicial.getDate() != null && txt_dtFinal.getDate() != null) {
            if (ch_dtAdmissao.isSelected()) {
                s = " C.dtAdmissao Between '" + Corretores.ConverterParaSQL(getStringDate(txt_dtInicial.getDate()))
                        + "' AND '" + Corretores.ConverterParaSQL(getStringDate(txt_dtFinal.getDate())) + "'";
            } else {
                s = " C.dtAdmissao Between '" + Corretores.ConverterParaSQL(getStringDate(txt_dtInicial.getDate()))
                        + "' AND '" + Corretores.ConverterParaSQL(getStringDate(txt_dtFinal.getDate())) + "'";
            }
        }

        if (cb_cei.getSelectedIndex() > 0) {
            s += " and C.IdCei = " + getCEI(cb_cei).getIdCEI();
        }

        if (cb_fazenda.getSelectedIndex() > 0) {
            s += " and C.idFazenda = " + getFazenda(cb_fazenda).getCodigo();
        }
        if (cb_setor.getSelectedIndex() > 0) {
            s += " and C.idSetor = " + getSetor(cb_setor).getID();
        }
        if (cb_funcao.getSelectedIndex() > 0) {
            s += " and C.idFuncao = " + getFuncao(cb_funcao).getId();
        }
        if (cb_status.getSelectedIndex() > 0) {
            s += " and C.Ativo = '" + cb_status.getSelectedItem().toString() + "'";
        }
        if (!txt_nome.getText().equals("")) {
            s += " and C.NomeFunc like '%" + txt_nome.getText() + "%'";
        }
        if (!txt_cpf.getText().equals("   .   .   -  ")) {
            s += " and C.CPFFunc = '" + txt_cpf.getText() + "'";
        }
        if (!txt_codigo.getText().equals("")) {
            s += " and C.CodigoMaster = '" + txt_codigo.getText() + "'";
        }
        if (!s.equals("")) {
            s = " WHERE" + s.replaceFirst("and", "");
        }
        System.out.println(s);
        return s;
    }

    private String getStringDate(Date dt) {
        String s;
        s = new SimpleDateFormat("dd/MM/yyyy").format(dt);
        return s;
    }

    private void limparCampos(Container container) {
        for (int i = 0; i < container.getComponentCount(); i++) {
            Component c = container.getComponent(i);
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            } else if (c instanceof JComboBox) {
                ((JComboBox) c).setSelectedIndex(0);
            } else if (c instanceof JDateChooser) {
                ((JDateChooser) c).setDate(null);
            }
        }
    }

    private List<Integer> getListaIDColaborador(int[] indexs) {
        List<Integer> listaID = new ArrayList<>();
        for (int i = 0; i < indexs.length; i++) {
            listaID.add((Integer) TbFuncionario.getValueAt(indexs[i], TbFuncionario.ID_COLABORADOR));
        }
        return listaID;
    }

}

class CellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setHorizontalAlignment(SwingConstants.CENTER);
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Object val = table.getValueAt(row, column);

        if (column == 3) {
            String cei = String.valueOf(val);
            //String ceiF = String.format("%s.%s.%s/%s", cei.substring(0, 2), cei.substring(2, 5), cei.substring(5, 10), cei.substring(10, 12));
            //setText(ceiF);
        }
        if (column == 8) {
            Double valorD = (Double) val;
            setText(new DecimalFormat("R$ ###,##0.00").format(valorD));
        }

        return this;
    }

}
