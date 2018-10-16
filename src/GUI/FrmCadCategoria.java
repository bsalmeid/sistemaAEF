package GUI;

import DAO.InventarioDAO;
import Beans.CategoriaEquipamentosBeans;
import TableModel.TableModelCadastroCategoriaInv;
import Utilitarios.CentralizarTabela;
import Utilitarios.ValidarPermissoes;
import java.awt.Component;
import java.awt.Container;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Usuario
 */
public class FrmCadCategoria extends javax.swing.JInternalFrame {

    CentralizarTabela Centralizar;
    InventarioDAO InventD;
    CategoriaEquipamentosBeans CatB;
    TableModelCadastroCategoriaInv TbCadCategoria;

    public FrmCadCategoria() {
        initComponents();
        Centralizar = new CentralizarTabela();
        InventD = new InventarioDAO();
        CatB = new CategoriaEquipamentosBeans();
        habilitarCampos(false);
        habilitarJButton(false);

        getTabelaConsCategoria();

    }

    private TableModelCadastroCategoriaInv getTableModelConsCategoria() {
        if (TbCadCategoria == null) {
            TbCadCategoria = new TableModelCadastroCategoriaInv();
        }
        return TbCadCategoria;
    }

    private JTable getTabelaConsCategoria() {
        tb_cadCategoria.setModel(getTableModelConsCategoria());
        Centralizar.centralizarTabela(tb_cadCategoria);
        ((DefaultTableCellRenderer) tb_cadCategoria.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        tb_cadCategoria.getColumnModel().getColumn(TbCadCategoria.ID_CATEGORIA).setPreferredWidth(10);
        tb_cadCategoria.getColumnModel().getColumn(TbCadCategoria.CATEGORIA).setPreferredWidth(100);
        tb_cadCategoria.getColumnModel().getColumn(TbCadCategoria.STATUS).setPreferredWidth(10);

        TbCadCategoria.limpar();
        TbCadCategoria.addLista(InventD.listarCategoria());

        return tb_cadCategoria;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelCadCategoria = new javax.swing.JPanel();
        txt_codigo = new javax.swing.JTextField();
        javax.swing.JLabel jLabel18 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel19 = new javax.swing.JLabel();
        txt_descricao = new javax.swing.JTextField();
        ch_status = new javax.swing.JCheckBox();
        javax.swing.JLabel jLabel20 = new javax.swing.JLabel();
        btn_editar = new javax.swing.JButton();
        btn_salvar = new javax.swing.JButton();
        btn_novo = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tb_cadCategoria = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro de Categorias");

        jPanelCadCategoria.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_codigo.setEditable(false);
        txt_codigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_codigo.setEnabled(false);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Código");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Categoria");

        txt_descricao.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        ch_status.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        ch_status.setText("Ativo");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Status");

        btn_editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/editar.png"))); // NOI18N
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });

        btn_salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/salvar.png"))); // NOI18N
        btn_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salvarActionPerformed(evt);
            }
        });

        btn_novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/inserir_40x40.png"))); // NOI18N
        btn_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCadCategoriaLayout = new javax.swing.GroupLayout(jPanelCadCategoria);
        jPanelCadCategoria.setLayout(jPanelCadCategoriaLayout);
        jPanelCadCategoriaLayout.setHorizontalGroup(
            jPanelCadCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCadCategoriaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ch_status)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelCadCategoriaLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_editar, btn_novo, btn_salvar});

        jPanelCadCategoriaLayout.setVerticalGroup(
            jPanelCadCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCadCategoriaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCadCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel18)
                    .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(txt_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(ch_status)
                    .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanelCadCategoriaLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_editar, btn_novo, btn_salvar});

        tb_cadCategoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tb_cadCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_cadCategoriaMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tb_cadCategoria);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addComponent(jPanelCadCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelCadCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(this, "Deseja cadastrar a Categoria?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (cadastrar == JOptionPane.YES_OPTION) {
            popular(CatB);
            if (verificar(CatB) && ValidarPermissoes.validarPermissaoInsert(FrmCadCategoria.class.getSimpleName())) {
                if (InventD.salvarCategoria(CatB)) {
                    TbCadCategoria.addRow(CatB);
                    limparCampos(jPanelCadCategoria);
                    txt_descricao.grabFocus();
                    habilitarCampos(false);
                    habilitarJButton(false);
                    limparCampos(jPanelCadCategoria);
                    TbCadCategoria.limpar();
                    TbCadCategoria.addLista(InventD.listarCategoria());
                }
            }
        }
    }//GEN-LAST:event_btn_salvarActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        int editar = JOptionPane.showConfirmDialog(this, "Deseja editar a Categoria?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if( editar== JOptionPane.YES_OPTION) {
            if (verificar(CatB) && ValidarPermissoes.validarPermissaoUpdate(FrmCadCategoria.class.getSimpleName())) {
                editar(CatB);
                InventD.atualizarCategoria(CatB);
                habilitarCampos(false);
                habilitarJButton(false);
                limparCampos(jPanelCadCategoria);
                TbCadCategoria.limpar();
                TbCadCategoria.addLista(InventD.listarCategoria());
            }
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed
        limparCampos(jPanelCadCategoria);
        habilitarCampos(true);
        btn_salvar.setEnabled(true);
        btn_editar.setEnabled(false);
        txt_descricao.grabFocus();
    }//GEN-LAST:event_btn_novoActionPerformed

    private void tb_cadCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_cadCategoriaMouseClicked
        int linha = tb_cadCategoria.getSelectedRow();
        if (evt.getClickCount() == 2) {
            habilitarCampos(true);
            habilitarJButton(true);
            btn_editar.setEnabled(true);
            btn_salvar.setEnabled(false);
            CatB = InventD.buscarCategoria((TbCadCategoria.getValueAt(linha, TbCadCategoria.CATEGORIA)).toString());
            preencherCampos(CatB);
            txt_descricao.grabFocus();
        } else {
            habilitarCampos(true);
            btn_editar.setEnabled(false);
            btn_salvar.setEnabled(true);
            limparCampos(jPanelCadCategoria);
        }
    }//GEN-LAST:event_tb_cadCategoriaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_novo;
    private javax.swing.JButton btn_salvar;
    private javax.swing.JCheckBox ch_status;
    private javax.swing.JPanel jPanelCadCategoria;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable tb_cadCategoria;
    private javax.swing.JTextField txt_codigo;
    private javax.swing.JTextField txt_descricao;
    // End of variables declaration//GEN-END:variables

    private void limparCampos(Container container) {
        for (int i = 0; i < container.getComponentCount(); i++) {
            Component c = container.getComponent(i);
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            } else if (c instanceof JComboBox) {
                ((JComboBox) c).setSelectedIndex(0);
            } else if (c instanceof JFormattedTextField) {
                ((JFormattedTextField) c).setValue(null);
            } else if (c instanceof JCheckBox) {
                ((JCheckBox) c).setSelected(false);
            }
        }
    }

    final void habilitarCampos(boolean valor) {
        txt_descricao.setEnabled(valor);
        ch_status.setEnabled(valor);
    }

    final void habilitarJButton(boolean valor) {
        btn_salvar.setEnabled(valor);
        btn_editar.setEnabled(valor);
    }

    private void popular(CategoriaEquipamentosBeans C) {
        C.setCategoria(txt_descricao.getText());
        C.setStatus(ch_status.isSelected());

    }

    private void editar(CategoriaEquipamentosBeans C) {
        C.setCategoria(txt_descricao.getText());
        C.setStatus(ch_status.isSelected());
        C.setID(Integer.valueOf(txt_codigo.getText()));
    }

    public boolean verificar(CategoriaEquipamentosBeans C) {
        if (txt_descricao.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha a descrição da Categoria", "Atenção", 0);
            return false;
        }
        return true;
    }

    private void preencherCampos(CategoriaEquipamentosBeans C) {
        txt_codigo.setText(C.getID().toString());
        txt_descricao.setText(C.getCategoria());
        ch_status.setSelected(C.getStatus());
    }

}
