package GUI;

import DAO.InventarioDAO;
import Beans.MarcaEquipamentosBeans;
import TableModel.TableModelCadastroMarcaInv;
import Utilitarios.CentralizarTabela;
import Utilitarios.ValidarPermissoes;
import java.awt.Component;
import java.awt.Container;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class FrmCadMarca extends javax.swing.JInternalFrame {

    CentralizarTabela Centralizar;
    InventarioDAO InventD;
    TableModelCadastroMarcaInv TbCadMarca;
    MarcaEquipamentosBeans MarcaB;

    public FrmCadMarca() {
        initComponents();
        Centralizar = new CentralizarTabela();
        InventD = new InventarioDAO();
        MarcaB = new MarcaEquipamentosBeans();
        habilitarCampos(false);
        habilitarJButton(false);

        getTabelaConsMarca();
    }

    private TableModelCadastroMarcaInv getTableModelConsMarca() {
        if (TbCadMarca == null) {
            TbCadMarca = new TableModelCadastroMarcaInv();
        }
        return TbCadMarca;
    }

    private JTable getTabelaConsMarca() {
        tb_cadMarca.setModel(getTableModelConsMarca());
        Centralizar.centralizarTabela(tb_cadMarca);
        ((DefaultTableCellRenderer) tb_cadMarca.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        tb_cadMarca.getColumnModel().getColumn(TbCadMarca.ID_MARCA).setPreferredWidth(10);
        tb_cadMarca.getColumnModel().getColumn(TbCadMarca.MARCA).setPreferredWidth(100);
        tb_cadMarca.getColumnModel().getColumn(TbCadMarca.STATUS).setPreferredWidth(10);

        TbCadMarca.limpar();
        TbCadMarca.addLista(InventD.listarMarca());

        return tb_cadMarca;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelCadMarcas = new javax.swing.JPanel();
        javax.swing.JLabel jLabel15 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        javax.swing.JLabel jLabel16 = new javax.swing.JLabel();
        txt_descricao = new javax.swing.JTextField();
        javax.swing.JLabel jLabel17 = new javax.swing.JLabel();
        ch_status = new javax.swing.JCheckBox();
        btn_novo = new javax.swing.JButton();
        btn_salvar = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tb_cadMarca = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro de Marcas");

        jPanelCadMarcas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Código");

        txt_codigo.setEditable(false);
        txt_codigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_codigo.setEnabled(false);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Marca");

        txt_descricao.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Status");

        ch_status.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        ch_status.setText("Ativo");

        btn_novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/inserir_40x40.png"))); // NOI18N
        btn_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novoActionPerformed(evt);
            }
        });

        btn_salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/salvar.png"))); // NOI18N
        btn_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salvarActionPerformed(evt);
            }
        });

        btn_editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/editar.png"))); // NOI18N
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCadMarcasLayout = new javax.swing.GroupLayout(jPanelCadMarcas);
        jPanelCadMarcas.setLayout(jPanelCadMarcasLayout);
        jPanelCadMarcasLayout.setHorizontalGroup(
            jPanelCadMarcasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCadMarcasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ch_status)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelCadMarcasLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_editar, btn_novo, btn_salvar});

        jPanelCadMarcasLayout.setVerticalGroup(
            jPanelCadMarcasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCadMarcasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCadMarcasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel15)
                    .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txt_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(ch_status)
                    .addGroup(jPanelCadMarcasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanelCadMarcasLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_editar, btn_novo, btn_salvar});

        tb_cadMarca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tb_cadMarca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_cadMarcaMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tb_cadMarca);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelCadMarcas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelCadMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed
        limparCampos(jPanelCadMarcas);
        habilitarCampos(true);
        btn_salvar.setEnabled(true);
        btn_editar.setEnabled(false);
        txt_descricao.grabFocus();    }//GEN-LAST:event_btn_novoActionPerformed

    private void btn_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Deseja cadastrar a Categoria?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
                == JOptionPane.YES_OPTION) {
            popular(MarcaB);
            if (verificar(MarcaB) && ValidarPermissoes.validarPermissaoInsert(FrmCadMarca.class.getSimpleName())) {
                if (InventD.salvarMarca(MarcaB)) {
                    TbCadMarca.addRow(MarcaB);
                    limparCampos(jPanelCadMarcas);
                    txt_descricao.grabFocus();
                    habilitarCampos(false);
                    habilitarJButton(false);
                    limparCampos(jPanelCadMarcas);
                    TbCadMarca.limpar();
                    TbCadMarca.addLista(InventD.listarMarca());
                }
            }
        }
    }//GEN-LAST:event_btn_salvarActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Deseja editar a Marca?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
                == JOptionPane.YES_OPTION) {
            if (verificar(MarcaB) && ValidarPermissoes.validarPermissaoUpdate(FrmCadMarca.class.getSimpleName())) {
                editar(MarcaB);
                InventD.atualizarMarca(MarcaB);
                habilitarCampos(false);
                habilitarJButton(false);
                limparCampos(jPanelCadMarcas);
                TbCadMarca.limpar();
                TbCadMarca.addLista(InventD.listarMarca());
            }
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void tb_cadMarcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_cadMarcaMouseClicked
        int linha = tb_cadMarca.getSelectedRow();
        if (evt.getClickCount() == 2) {
            habilitarCampos(true);
            habilitarJButton(true);
            btn_editar.setEnabled(true);
            btn_salvar.setEnabled(false);
            MarcaB = InventD.buscarMarca((TbCadMarca.getValueAt(linha, TbCadMarca.MARCA)).toString());
            preencherCampos(MarcaB);
            txt_descricao.grabFocus();
        } else {
            habilitarCampos(true);
            btn_editar.setEnabled(false);
            btn_salvar.setEnabled(true);
            limparCampos(jPanelCadMarcas);
        }
    }//GEN-LAST:event_tb_cadMarcaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_novo;
    private javax.swing.JButton btn_salvar;
    private javax.swing.JCheckBox ch_status;
    private javax.swing.JPanel jPanelCadMarcas;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable tb_cadMarca;
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

    private void popular(MarcaEquipamentosBeans M) {
        M.setMarca(txt_descricao.getText());
        M.setStatus(ch_status.isSelected());

    }

    private void editar(MarcaEquipamentosBeans M) {
        M.setMarca(txt_descricao.getText());
        M.setStatus(ch_status.isSelected());
        M.setID(Integer.valueOf(txt_codigo.getText()));
    }

    private void preencherCampos(MarcaEquipamentosBeans M) {
        txt_codigo.setText(M.getID().toString());
        txt_descricao.setText(M.getMarca());
        ch_status.setSelected(M.getStatus());
    }

    public boolean verificar(MarcaEquipamentosBeans M) {
        if (txt_descricao.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha a descrição da Marca", "Atenção", 0);
            return false;
        }
        return true;
    }

}
