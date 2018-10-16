package GUI;

import Beans.CultivoBeans;
import DAO.CentroResultadoDAO;
import static GUI.Principal.listCultivo;
import Utilitarios.ValidarPermissoes;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FrmCadCultivo extends javax.swing.JInternalFrame {

    CultivoBeans CultivoB;
    CentroResultadoDAO ResultadoD;

    public FrmCadCultivo() {
        initComponents();
        CultivoB = new CultivoBeans();
        ResultadoD = new CentroResultadoDAO();
        carregarCultivo();
    }

    private void carregarCultivo() {
        if (listCultivo == null) {
            listCultivo = ResultadoD.listarCultivo();
        } else if (listCultivo != null) {
            listCultivo = ResultadoD.listarCultivo();
        }
        cb_cultivo.removeAllItems();
        cb_cultivo.addItem(new CultivoBeans(0, "Novo"));
        for (CultivoBeans C : listCultivo) {
            cb_cultivo.addItem(C);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelCultivo = new javax.swing.JPanel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        btn_cadastrar = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        ch_status = new javax.swing.JCheckBox();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        txt_descricao = new javax.swing.JTextField();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        cb_cultivo = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setTitle("Cadastro de Cultivo");

        jPanelCultivo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Cultivo");

        btn_cadastrar.setText("Cadastrar");
        btn_cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cadastrarActionPerformed(evt);
            }
        });

        btn_editar.setText("Editar");
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Status");

        ch_status.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_status.setText("Ativo");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Código");

        txt_codigo.setEditable(false);
        txt_codigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txt_descricao.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Cultivo");

        cb_cultivo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_cultivoItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanelCultivoLayout = new javax.swing.GroupLayout(jPanelCultivo);
        jPanelCultivo.setLayout(jPanelCultivoLayout);
        jPanelCultivoLayout.setHorizontalGroup(
            jPanelCultivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCultivoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCultivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelCultivoLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCultivoLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_cultivo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ch_status)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_cadastrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_editar)
                .addContainerGap())
        );
        jPanelCultivoLayout.setVerticalGroup(
            jPanelCultivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCultivoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCultivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cb_cultivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCultivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(ch_status)
                    .addComponent(btn_cadastrar)
                    .addComponent(btn_editar)
                    .addComponent(jLabel7)
                    .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelCultivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelCultivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setBounds(0, 0, 615, 131);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cadastrarActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(this, "Deseja cadastrar o Cultivo?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (cadastrar == JOptionPane.YES_OPTION) {
            CultivoB = new CultivoBeans();
            popular(CultivoB);
            if (verificar(CultivoB) && ValidarPermissoes.validarPermissaoInsert(FrmCadCultivo.class.getSimpleName())) {
                if (ResultadoD.salvarCultivo(CultivoB)) {
                    limparCampos(jPanelCultivo);
                    carregarCultivo();
                    cb_cultivo.grabFocus();
                }
            }
        }
    }//GEN-LAST:event_btn_cadastrarActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        int editar = JOptionPane.showConfirmDialog(this, "Deseja editar o Cultivo?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (editar == JOptionPane.YES_OPTION) {
            popular(CultivoB);
            if (verificar(CultivoB) && ValidarPermissoes.validarPermissaoUpdate(FrmCadCultivo.class.getSimpleName())) {
                ResultadoD.atualizarCultivo(CultivoB);
                limparCampos(jPanelCultivo);
                carregarCultivo();
                cb_cultivo.grabFocus();
            }
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void cb_cultivoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_cultivoItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (cb_cultivo.getSelectedIndex() == 0) {
                btn_cadastrar.setEnabled(true);
                btn_editar.setEnabled(false);
                limparCampos(jPanelCultivo);
                cb_cultivo.grabFocus();
            } else {
                CultivoB = (CultivoBeans) cb_cultivo.getSelectedItem();
                preencherCampos(CultivoB);
                btn_cadastrar.setEnabled(false);
                btn_editar.setEnabled(true);
            }
        }
    }//GEN-LAST:event_cb_cultivoItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cadastrar;
    private javax.swing.JButton btn_editar;
    private javax.swing.JComboBox<CultivoBeans> cb_cultivo;
    private javax.swing.JCheckBox ch_status;
    private javax.swing.JPanel jPanelCultivo;
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
            } else if (c instanceof JCheckBox) {
                ((JCheckBox) c).setSelected(false);
            }
        }
    }

    public void preencherCampos(CultivoBeans C) {
        txt_codigo.setText(C.getIDCultivo().toString());
        txt_descricao.setText(C.getCultivo());
        ch_status.setSelected(C.getStatus());
    }

    public void popular(CultivoBeans C) {
        C.setCultivo(txt_descricao.getText());
        C.setStatus(ch_status.isSelected());
    }

    public boolean verificar(CultivoBeans c) {
        if (txt_descricao.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha a descrição do Cultivo", "Atenção", 0);
            return false;
        }
        return true;
    }

}
