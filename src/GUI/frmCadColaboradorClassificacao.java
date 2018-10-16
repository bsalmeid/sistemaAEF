package GUI;

import Beans.AtividadeBeans;
import Beans.CadColaboradorBeans;
import Beans.CadColaboradorClassBeans;
import Beans.CadFuncionarios;
import Beans.CentroDeResultado;
import Beans.PlanoContaBeans;
import Beans.PropriedadesBeans;
import DAO.CadColaboradorDAO;
import DAO.DiversasHibernate;
import static GUI.Principal.ListaPropriedades;
import static GUI.Principal.listPlanoContas;
import static GUI.Principal.listaAtividades;
import static GUI.Principal.listaCentroResultado;
import Icones.FormatarICO;
import TableModel.TableModelColaboradorClass;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import Utilitarios.ValidarPermissoes;
import componentes.UJComboBox;
import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class frmCadColaboradorClassificacao extends javax.swing.JDialog {

    public TableModelColaboradorClass TbClass;
    CentralizarTabela Centralizar;
    CadColaboradorClassBeans ClassB;
    CadColaboradorDAO FuncD;
    CadColaboradorBeans FuncB;
    List<Integer> listaIDFuncionarios;

    public frmCadColaboradorClassificacao(Frame parent, boolean modal, List<Integer> listID) {
        super(parent, modal);
        initComponents();
        ClassB = new CadColaboradorClassBeans();
        Centralizar = new CentralizarTabela();
        FuncD = new CadColaboradorDAO();
        FuncB = new CadColaboradorBeans();
        listaIDFuncionarios = listID;
        carregarFazendas();
        carregarAtividades();
        carregarCentroResultado();
        carregarPlanoContas();
        carregarTabelaClass();

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

    private void carregarFazendas() {
        if (ListaPropriedades == null) {
            ListaPropriedades = DiversasHibernate.getListaFazendas();
        } else if (ListaPropriedades != null) {
            ListaPropriedades = DiversasHibernate.getListaFazendas();
        }
        cb_fazendaClas.addItem(new PropriedadesBeans(0, "-"));
        for (PropriedadesBeans L : ListaPropriedades) {
            cb_fazendaClas.addItem(L);
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

    private void carregarCentroResultado(Integer idAtividade, UJComboBox combo) {
        if (listaCentroResultado == null) {
            listaCentroResultado = DiversasHibernate.getListaCentroResultado();
        }
        combo.removeAllItems();
        combo.addItem(new CentroDeResultado(0L, "-"));
        for (CentroDeResultado c : listaCentroResultado) {
            if (Objects.equals(c.getAtividade().getID(), idAtividade)) {
                combo.addItem(c);
            }
        }
    }

    private void carregarPlanoContas() {
        if (listPlanoContas == null) {
            listPlanoContas = DiversasHibernate.getPlanoConta();
        }
        cb_plano.removeAllItems();
        cb_plano.addItem(new PlanoContaBeans(0, "-"));
        for (PlanoContaBeans p : listPlanoContas) {
            cb_plano.addItem(p);
        }
    }

    private JTable carregarTabelaClass() {
        Centralizar = new CentralizarTabela();
        tb_class.setModel(getTableModel());
        Centralizar.centralizarTabela(tb_class);
        ((DefaultTableCellRenderer) tb_class.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        for (int c = 0; c < TbClass.getColumnCount(); c++) {
            if (c == TbClass.ID || c == TbClass.IDCOLABORADOR) {
                tb_class.getColumnModel().getColumn(c).setPreferredWidth(0);
                tb_class.getColumnModel().getColumn(c).setMinWidth(0);
                tb_class.getColumnModel().getColumn(c).setMaxWidth(0);
            }
        }
        tb_class.getColumnModel().getColumn(TbClass.CENTRO_RESULTADO).setPreferredWidth(120);
        tb_class.getColumnModel().getColumn(TbClass.PLANOCONTA).setPreferredWidth(120); // Plano COnta
        tb_class.getColumnModel().getColumn(TbClass.FAZENDA).setPreferredWidth(120); // Fazenda
        tb_class.getColumnModel().getColumn(TbClass.VALOR).setPreferredWidth(80); // valor

        return tb_class;
    }

    private TableModelColaboradorClass getTableModel() {
        if (TbClass == null) {
            TbClass = new TableModelColaboradorClass();
        }
        return TbClass;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scPClassifocacao = new javax.swing.JScrollPane();
        tb_class = new javax.swing.JTable();
        jPanelClassificacao = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        cb_atividade = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        cb_fazendaClas = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        txt_valorClass = new javax.swing.JTextField();
        cb_centroResultado = new componentes.UJComboBox();
        cb_plano = new javax.swing.JComboBox<>();
        btn_addClass = new javax.swing.JButton();
        btn_editarClass = new javax.swing.JButton();
        btn_delClass = new javax.swing.JButton();
        btn_novo = new javax.swing.JButton();
        btn_salvarPedido = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Classificação dos Funcionários");

        scPClassifocacao.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        scPClassifocacao.setPreferredSize(new java.awt.Dimension(454, 70));
        scPClassifocacao.setRequestFocusEnabled(false);

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

        jPanelClassificacao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));
        jPanelClassificacao.setPreferredSize(new java.awt.Dimension(1028, 80));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel34.setText("Atividade");

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

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel35.setText("C. de Resultado");

        cb_fazendaClas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cb_fazendaClasFocusGained(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel36.setText("Fazenda");

        jLabel85.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel85.setText("Conta");

        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel88.setText("Valor");

        txt_valorClass.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_valorClass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_valorClassFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_valorClassFocusLost(evt);
            }
        });

        cb_centroResultado.setAutocompletar(true);
        cb_centroResultado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cb_centroResultadoFocusGained(evt);
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

        javax.swing.GroupLayout jPanelClassificacaoLayout = new javax.swing.GroupLayout(jPanelClassificacao);
        jPanelClassificacao.setLayout(jPanelClassificacaoLayout);
        jPanelClassificacaoLayout.setHorizontalGroup(
            jPanelClassificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelClassificacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_atividade, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_centroResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel85)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_plano, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_fazendaClas, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel88)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_valorClass, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_addClass)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_editarClass)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_delClass)
                .addContainerGap())
        );
        jPanelClassificacaoLayout.setVerticalGroup(
            jPanelClassificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelClassificacaoLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelClassificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel35)
                    .addComponent(cb_atividade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel85)
                    .addComponent(cb_fazendaClas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36)
                    .addComponent(cb_centroResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_plano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel88)
                    .addComponent(txt_valorClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelClassificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(btn_addClass)
                        .addComponent(btn_editarClass)
                        .addComponent(btn_delClass)))
                .addContainerGap())
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
        btn_salvarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/salvar_32_32.png"))); // NOI18N
        btn_salvarPedido.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_salvarPedido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_salvarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salvarPedidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelClassificacao, javax.swing.GroupLayout.DEFAULT_SIZE, 1170, Short.MAX_VALUE)
                    .addComponent(scPClassifocacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_salvarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelClassificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scPClassifocacao, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_salvarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb_atividadeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_atividadeItemStateChanged
        if (cb_atividade.getSelectedIndex() > 0) {
            carregarCentroResultado(getAtividade(cb_atividade).getID(), cb_centroResultado);
        }
    }//GEN-LAST:event_cb_atividadeItemStateChanged

    private void cb_atividadeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cb_atividadeFocusGained
        setExpandedComboBox(evt.getComponent());
    }//GEN-LAST:event_cb_atividadeFocusGained

    private void cb_fazendaClasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cb_fazendaClasFocusGained
        //setExpandedComboBox(evt.getComponent());
    }//GEN-LAST:event_cb_fazendaClasFocusGained

    private void txt_valorClassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorClassFocusGained
        txt_valorClass.selectAll();
    }//GEN-LAST:event_txt_valorClassFocusGained

    private void txt_valorClassFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorClassFocusLost
        //txt_valorClass.setText(Corretores.ConverterDecimalPorcentagem(txt_valorClass.getText()));
    }//GEN-LAST:event_txt_valorClassFocusLost

    private void btn_addClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addClassActionPerformed
        ClassB = new CadColaboradorClassBeans();
        addClassificacao(ClassB);
    }//GEN-LAST:event_btn_addClassActionPerformed

    private void btn_editarClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarClassActionPerformed
        int rowIndex = tb_class.getSelectedRow();
        if (VerificarValorEditadoClas()) {
            if (editarClassificacao(TbClass.getBeans(rowIndex), rowIndex)) {
                btn_addClass.setEnabled(true);
                btn_editarClass.setEnabled(false);
                btn_delClass.setEnabled(false);
                limparCamposClass();
            }
        }
    }//GEN-LAST:event_btn_editarClassActionPerformed

    private void btn_delClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delClassActionPerformed
        int index = tb_class.getSelectedRow();
        if (TbClass.getBeans(index).getId() != null) {
            int excluir = JOptionPane.showConfirmDialog(null, "<Html> Está Ação irá <B>EXCLUIR Permanentemente </B>, <br> este Registro, deseja Prosseguir? </html>", "Atenção", JOptionPane.YES_NO_OPTION);
            if (excluir == JOptionPane.YES_OPTION) {
                if (FuncD.DeletarClassicacao(TbClass.getBeans(index))) {
                    TbClass.excluirLinha(index);
                    limparCamposClass();
                    cb_atividade.requestFocus();
                }
            }
        } else {
            TbClass.excluirLinha(index);
            limparCamposClass();
            cb_atividade.requestFocus();
        }
        limparConteiners(jPanelClassificacao);
        btn_addClass.setEnabled(true);
        btn_editarClass.setEnabled(false);
        btn_delClass.setEnabled(false);
    }//GEN-LAST:event_btn_delClassActionPerformed

    private void cb_centroResultadoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cb_centroResultadoFocusGained
        //setExpandedComboBox(evt.getComponent());
    }//GEN-LAST:event_cb_centroResultadoFocusGained

    private void tb_classMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_classMouseClicked
        int index = tb_class.getSelectedRow();
        if (evt.getClickCount() == 2) {
//            preecherCamposClass(TbClass.getBeans(tb_class.getSelectedRow()));
            //ClassB = FuncD.buscarClassificacao((Intege) TbClass.getValueAt(index, TbClass.ID));
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

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed

    }//GEN-LAST:event_btn_novoActionPerformed

    private void btn_salvarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarPedidoActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(null, "Deseja Salvar esta Classificação?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION) {
            if (ValidarPermissoes.validarPermissaoInsert(frmCadColaborador.class.getSimpleName())) {
                if (Objects.equals(TbClass.somarValorTabela(TbClass.VALOR), new Double(100.00))) {
                    if (FuncD.editarClassificaoLote(listaIDFuncionarios, getListaClassificao(TbClass.getLista()))) {

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "A Somatória das Classificações devem ser IGUAL a 100%!", "Erro", 0, FormatarICO.ICObtnSair());
                }
            }
        }
    }//GEN-LAST:event_btn_salvarPedidoActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmCadColaboradorClassificacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCadColaboradorClassificacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCadColaboradorClassificacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCadColaboradorClassificacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmCadColaboradorClassificacao dialog = new frmCadColaboradorClassificacao(new javax.swing.JFrame(), true, new ArrayList());
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btn_addClass;
    javax.swing.JButton btn_delClass;
    javax.swing.JButton btn_editarClass;
    javax.swing.JButton btn_novo;
    javax.swing.JButton btn_salvarPedido;
    javax.swing.JComboBox<AtividadeBeans> cb_atividade;
    componentes.UJComboBox cb_centroResultado;
    public javax.swing.JComboBox<PropriedadesBeans> cb_fazendaClas;
    private javax.swing.JComboBox<PlanoContaBeans> cb_plano;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel88;
    javax.swing.JPanel jPanelClassificacao;
    javax.swing.JScrollPane scPClassifocacao;
    javax.swing.JTable tb_class;
    public javax.swing.JTextField txt_valorClass;
    // End of variables declaration//GEN-END:variables

    private List<CadColaboradorClassBeans> getListaClassificao(List<CadColaboradorClassBeans> listaTabela) {
        List<CadColaboradorClassBeans> listaPopulada = new ArrayList<>();
        for (int i = 0; i < listaIDFuncionarios.size(); i++) {
            CadColaboradorBeans funcionario = new CadColaboradorBeans(listaIDFuncionarios.get(i));
            for (int y = 0; y < listaTabela.size(); y++) {
                CadColaboradorClassBeans clas = listaTabela.get(y);
                CadColaboradorClassBeans newClas = new CadColaboradorClassBeans();
                newClas.setIdCentro(clas.getIdCentro());
                newClas.setIdColaborador(funcionario);
                newClas.setIdFazenda(clas.getIdFazenda());
                newClas.setIdPlanoConta(clas.getIdPlanoConta());
                newClas.setValor(clas.getValor());
                listaPopulada.add(newClas);
            }
        }
        return listaPopulada;
    }

    private Boolean addClassificacao(CadColaboradorClassBeans clas) {
        try {
            clas.setIdColaborador(null);
            clas.setIdCentro(getCentroResultado(cb_centroResultado));
            clas.setIdPlanoConta(getPlanoConta(cb_plano));
            clas.setIdFazenda(getFazenda(cb_fazendaClas));
            clas.setValor(Corretores.ConverterStringDouble(txt_valorClass.getText()));
            if (verificaBeans(clas) && VerificarValorClas()) {
                TbClass.addBeans(clas);
                cb_atividade.requestFocus();
                limparCamposClass();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Adicionar Classificação!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }

    private Boolean editarClassificacao(CadColaboradorClassBeans clas, int rowIndex) {
        try {
            clas.setIdColaborador(FuncB);
            clas.setIdCentro(getCentroResultado(cb_centroResultado));
            clas.setIdFazenda(getFazenda(cb_fazendaClas));
            clas.setIdPlanoConta(getPlanoConta(cb_plano));
            clas.setValor(Corretores.ConverterStringDouble(txt_valorClass.getText()));
            if (verificaBeans(clas)) {
                TbClass.setBeans(clas, rowIndex);
                limparCamposClass();
                cb_atividade.requestFocus();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Editar Classificação!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }

    private Boolean VerificarValorClas() {
        Double ValorTotal = TbClass.somarValorTabela(TbClass.VALOR);
        Double ValorJTextField = Corretores.ConverterStringDouble(txt_valorClass.getText());
        Double I = (ValorTotal + ValorJTextField);
        if (I > 100) {
            JOptionPane.showMessageDialog(null, "<Html> A classificação de <B><font color=RED> " + I + "% </font></B>, Excedeu os 100%. </html>", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
            return true;   
    }

    private Boolean VerificarValorEditadoClas() {
        int index = tb_class.getSelectedRow();
        Double ValorTotal = TbClass.somarValorTabela(TbClass.VALOR);
        Double ValorSelecionado = (Double) TbClass.getValueAt(index, TbClass.VALOR);
        Double JTextField = Corretores.ConverterStringDouble(txt_valorClass.getText());
        Double Y = (ValorTotal - ValorSelecionado + JTextField);
        if (Y > 100) {
            JOptionPane.showMessageDialog(null, "<Html> A classificação de <B><font color=RED> " + Y + "% </font></B>, Excedeu os 100%. </html>", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }

    private boolean verificaBeans(CadColaboradorClassBeans clas) {

        if (clas.getIdCentro().getID() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Centro de Resultado!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (clas.getIdPlanoConta().getID() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Plano de Conta!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (clas.getIdFazenda().getCodigo() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Fazenda!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (clas.getValor() == 0 || clas.getValor() > 100) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Valor!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }

        return true;
    }

    private AtividadeBeans getAtividade(JComboBox<AtividadeBeans> combo) {
        return (AtividadeBeans) combo.getSelectedItem();
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

    private CentroDeResultado getCentroResultado(UJComboBox combo) {
        return (CentroDeResultado) combo.getSelectedItem();
    }

    private void setCentroResultado(UJComboBox combo, Long ID) {
        for (int i = 0; i < combo.getModel().getSize(); i++) {
            CentroDeResultado c = (CentroDeResultado) combo.getItemAt(i);
            if (Objects.equals(c.getID(), ID)) {
                combo.setSelectedIndex(i);
            }
        }
    }

    private PlanoContaBeans getPlanoConta(JComboBox<PlanoContaBeans> combo) {
        return (PlanoContaBeans) combo.getSelectedItem();
    }

    private void setPlanoConta(JComboBox<PlanoContaBeans> combo, Integer ID) {
        for (int i = 0; i < combo.getModel().getSize(); i++) {
            PlanoContaBeans p = (PlanoContaBeans) combo.getItemAt(i);
            if (Objects.equals(p.getID(), ID)) {
                combo.setSelectedIndex(i);
            }
        }
    }

    private PropriedadesBeans getFazenda(JComboBox<PropriedadesBeans> comboBox) {
        return (PropriedadesBeans) comboBox.getSelectedItem();
    }

    private void setFazenda(JComboBox<PropriedadesBeans> cb, Integer IdFazenda) {
        for (int i = 0; i < cb.getModel().getSize(); i++) {
            if (cb.getItemAt(i).getCodigo() == IdFazenda) {
                cb.setSelectedIndex(i);
            }
        }
    }

    private void limparCamposClass() {
        cb_atividade.setSelectedIndex(0);
        cb_centroResultado.setSelectedIndex(0);
        cb_plano.setSelectedIndex(0);
        cb_fazendaClas.setSelectedIndex(0);
        txt_valorClass.setText("0%");
    }

    private void setExpandedComboBox(Component c) {
        if (c instanceof JComboBox) {
            ((JComboBox) c).setPopupVisible(true);
        } else if (c instanceof UJComboBox) {
            ((UJComboBox) c).setPopupVisible(true);
        }
    }

    public void limparConteiners(Container conteiner) {
        for (Component c : conteiner.getComponents()) {
            if (c instanceof JTextField) {
                JTextField field = (JTextField) c;
                field.setText("");
            } else if (c instanceof JComboBox) {
                JComboBox f = (JComboBox) c;
                f.setSelectedIndex(0);
            } else if (c instanceof JCheckBox) {
                JCheckBox f = (JCheckBox) c;
                f.setSelected(false);
            }
        }
    }

}
