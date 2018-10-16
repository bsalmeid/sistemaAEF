package GUI;

import Beans.CadUnidadesBeans;
import DAO.CadUnidadesDAO;
import TableModel.TableModelCadUnidades;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import Utilitarios.ValidarPermissoes;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class FrmCadUnidades extends javax.swing.JInternalFrame {

    CadUnidadesBeans UnidadeB;
    CadUnidadesDAO UnidadeD;
    TableModelCadUnidades TbUnidade;
    CentralizarTabela Centralizar;

    public FrmCadUnidades() {
        initComponents();
        Centralizar = new CentralizarTabela();
        UnidadeB = new CadUnidadesBeans();
        UnidadeD = new CadUnidadesDAO();
        habilitarCampos(false);
        tabelaUnidades();
    }

    private TableModelCadUnidades getTbUnidades() {
        if (TbUnidade == null) {
            TbUnidade = new TableModelCadUnidades();

        }
        return TbUnidade;
    }

    private JTable tabelaUnidades() {
        tb_Unidades.setModel(getTbUnidades());
        Centralizar.centralizarTabela(tb_Unidades);
        ((DefaultTableCellRenderer) tb_Unidades.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        tb_Unidades.getColumnModel().getColumn(TbUnidade.ID_UNIDADES).setMinWidth(0);
        tb_Unidades.getColumnModel().getColumn(TbUnidade.ID_UNIDADES).setMaxWidth(0);
        tb_Unidades.getColumnModel().getColumn(TbUnidade.ID_UNIDADES).setPreferredWidth(0);
        tb_Unidades.getColumnModel().getColumn(TbUnidade.DESCRICAO).setPreferredWidth(100);
        tb_Unidades.getColumnModel().getColumn(TbUnidade.CONVERSAO).setPreferredWidth(80);
        tb_Unidades.getColumnModel().getColumn(TbUnidade.STATUS).setPreferredWidth(10);
        TbUnidade.limpar();
        TbUnidade.addLista(UnidadeD.listarUnidades());
        return tb_Unidades;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelUnidades = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        ch_status = new javax.swing.JCheckBox();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        txt_descricao = new javax.swing.JTextField();
        txt_conversao = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_Unidades = new javax.swing.JTable();
        btn_novo = new javax.swing.JButton();
        btn_salvar = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Cadastros de Unidades");

        jPanelUnidades.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));
        jPanelUnidades.setToolTipText("Cadastros de Unidades");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Descrição");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Conversão(Kg/L)");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Status");

        txt_descricao.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txt_conversao.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanelUnidadesLayout = new javax.swing.GroupLayout(jPanelUnidades);
        jPanelUnidades.setLayout(jPanelUnidadesLayout);
        jPanelUnidadesLayout.setHorizontalGroup(
            jPanelUnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUnidadesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_conversao, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ch_status)
                .addContainerGap())
        );
        jPanelUnidadesLayout.setVerticalGroup(
            jPanelUnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUnidadesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelUnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(ch_status)
                    .addComponent(jLabel3)
                    .addComponent(txt_conversao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txt_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        tb_Unidades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb_Unidades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_UnidadesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_Unidades);

        btn_novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/inserir_40x40.png"))); // NOI18N
        btn_novo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_novo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novoActionPerformed(evt);
            }
        });

        btn_salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/salvar_32_32.png"))); // NOI18N
        btn_salvar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_salvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_salvar.setEnabled(false);
        btn_salvar.setMaximumSize(new java.awt.Dimension(45, 45));
        btn_salvar.setMinimumSize(new java.awt.Dimension(45, 45));
        btn_salvar.setPreferredSize(new java.awt.Dimension(45, 45));
        btn_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salvarActionPerformed(evt);
            }
        });

        btn_editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/editar_pequeno.png"))); // NOI18N
        btn_editar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_editar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_editar.setEnabled(false);
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanelUnidades, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed
        UnidadeB = new CadUnidadesBeans();
        limparCampos(jPanelUnidades);
        habilitarCampos(true);
        btn_editar.setEnabled(true);
        btn_salvar.setEnabled(true);
    }//GEN-LAST:event_btn_novoActionPerformed

    private void btn_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(this, "Deseja cadastrar a Unidade?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (cadastrar == JOptionPane.YES_OPTION) {
            popular(UnidadeB);
            if (verificar(UnidadeB) && ValidarPermissoes.validarPermissaoInsert(FrmCadUnidades.class.getSimpleName())) {
                if (UnidadeD.salvarUnidades(UnidadeB)) {
                    limparCampos(jPanelUnidades);
                    TbUnidade.limpar();
                    TbUnidade.addLista(UnidadeD.listarUnidades());
                }
            }
        }
    }//GEN-LAST:event_btn_salvarActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        int editar = JOptionPane.showConfirmDialog(this, "Deseja editar a Unidade?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (editar == JOptionPane.YES_OPTION) {
            popular(UnidadeB);
            if (verificar(UnidadeB) && ValidarPermissoes.validarPermissaoUpdate(FrmCadUnidades.class.getSimpleName())) {
                if (UnidadeD.editarUnidades(UnidadeB)) {
                    limparCampos(jPanelUnidades);
                    TbUnidade.limpar();
                    TbUnidade.addLista(UnidadeD.listarUnidades());
                }
            }
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void tb_UnidadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_UnidadesMouseClicked
        int index = tb_Unidades.getSelectedRow();
        if (evt.getClickCount() == 2) {
            if (JOptionPane.showConfirmDialog(this, "Deseja Editar Esta Unidade?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
                    == JOptionPane.YES_OPTION) {
                limparCampos(jPanelUnidades);
                habilitarCampos(true);
                btn_editar.setEnabled(true);
                btn_salvar.setEnabled(true);
                UnidadeB = UnidadeD.buscarUnidade((Integer) TbUnidade.getValueAt(index, TbUnidade.ID_UNIDADES));
                preencherCampos(TbUnidade.getItem(tb_Unidades.getSelectedRow()));
            }
        }
    }//GEN-LAST:event_tb_UnidadesMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_novo;
    private javax.swing.JButton btn_salvar;
    private javax.swing.JCheckBox ch_status;
    private javax.swing.JPanel jPanelUnidades;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_Unidades;
    private javax.swing.JTextField txt_conversao;
    private javax.swing.JTextField txt_descricao;
    // End of variables declaration//GEN-END:variables

    private void limparCampos(Container container) {
        for (int i = 0; i < container.getComponentCount(); i++) {
            Component c = container.getComponent(i);
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            } else if (c instanceof JCheckBox) {
                ((JCheckBox) c).setSelected(false);
            }
        }
    }

    final void habilitarCampos(boolean valor) {
        txt_descricao.setEnabled(valor);
        txt_conversao.setEnabled(valor);
        ch_status.setEnabled(valor);
    }

    public void preencherCampos(CadUnidadesBeans U) {
        txt_descricao.setText(U.getDescricao());
        txt_conversao.setText(Corretores.ConverterDoubleDecimal(U.getConversaoKg_L()));
        ch_status.setSelected(U.getStatus());
    }

    public void popular(CadUnidadesBeans U) {
        U.setDescricao(txt_descricao.getText());
        U.setConversaoKg_L(Corretores.ConverterStringDouble(txt_conversao.getText()));
        U.setStatus(ch_status.isSelected());
    }

    public boolean verificar(CadUnidadesBeans U) {
        if (U.getDescricao().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha a Descrição da Unidade", "Atenção", 0);
            return false;
        }
        if (U.getConversaoKg_L() == 0) {
            JOptionPane.showMessageDialog(null, "Preencha a Conversão(Kg/L)", "Atenção", 0);
            return false;
        }
        return true;
    }

}