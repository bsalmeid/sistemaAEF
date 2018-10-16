package GUI;

import Beans.AtividadeBeans;
import DAO.CentroResultadoDAO;
import DAO.DiversasHibernate;
import static GUI.Principal.listaAtividades;
import Utilitarios.ValidarPermissoes;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FrmCadAtividade extends javax.swing.JInternalFrame {

    AtividadeBeans AtividadeB;
    CentroResultadoDAO ResultadoD;

    public FrmCadAtividade() {
        initComponents();
        AtividadeB = new AtividadeBeans();
        ResultadoD = new CentroResultadoDAO();
        carregarAtividade();
    }

    private void carregarAtividade() {
        if (listaAtividades == null) {
            listaAtividades = DiversasHibernate.getListaAtividade();
        } else if(listaAtividades != null) {
            listaAtividades = DiversasHibernate.getListaAtividade();
        }
        cb_atividade.removeAllItems();
        cb_atividade.addItem(new AtividadeBeans(0, "Novo"));
        for (AtividadeBeans A : listaAtividades) {
            cb_atividade.addItem(A);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelAtividade = new javax.swing.JPanel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        btn_cadastrar = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        ch_status = new javax.swing.JCheckBox();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        txt_descricao = new javax.swing.JTextField();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        cb_atividade = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setTitle("Cadastro de Atividade");

        jPanelAtividade.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Atividade");

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
        jLabel4.setText("Atividade");

        cb_atividade.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_atividadeItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanelAtividadeLayout = new javax.swing.GroupLayout(jPanelAtividade);
        jPanelAtividade.setLayout(jPanelAtividadeLayout);
        jPanelAtividadeLayout.setHorizontalGroup(
            jPanelAtividadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAtividadeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAtividadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelAtividadeLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelAtividadeLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_atividade, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ch_status)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_cadastrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_editar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelAtividadeLayout.setVerticalGroup(
            jPanelAtividadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAtividadeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAtividadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cb_atividade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelAtividadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
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
                .addComponent(jPanelAtividade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelAtividade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setBounds(0, 0, 629, 131);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cadastrarActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(this, "Deseja cadastrar a Atividade?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (cadastrar == JOptionPane.YES_OPTION) {
            AtividadeB = new AtividadeBeans();
            popular(AtividadeB);
            if (verificar(AtividadeB) && ValidarPermissoes.validarPermissaoInsert(FrmCadAtividade.class.getSimpleName())) {
                if (ResultadoD.salvarAtividade(AtividadeB)) {
                    limparCampos(jPanelAtividade);
                    carregarAtividade();
                    cb_atividade.grabFocus();
                }
            }
        }
    }//GEN-LAST:event_btn_cadastrarActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        int editar = JOptionPane.showConfirmDialog(this, "Deseja editar a Atividade?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (editar == JOptionPane.YES_OPTION) {
            popular(AtividadeB);
            if (verificar(AtividadeB) && ValidarPermissoes.validarPermissaoUpdate(FrmCadAtividade.class.getSimpleName())) {
                if (ResultadoD.atualizarAtividade(AtividadeB)) {
                    limparCampos(jPanelAtividade);
                    carregarAtividade();
                    cb_atividade.grabFocus();
                }
            }
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void cb_atividadeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_atividadeItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (cb_atividade.getSelectedIndex() == 0) {
                btn_cadastrar.setEnabled(true);
                btn_editar.setEnabled(false);
                limparCampos(jPanelAtividade);
                cb_atividade.grabFocus();
            } else {
                AtividadeB = (AtividadeBeans) cb_atividade.getSelectedItem();
                preencherCampos(AtividadeB);
                btn_cadastrar.setEnabled(false);
                btn_editar.setEnabled(true);
            }
        }
    }//GEN-LAST:event_cb_atividadeItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cadastrar;
    private javax.swing.JButton btn_editar;
    private javax.swing.JComboBox<AtividadeBeans> cb_atividade;
    private javax.swing.JCheckBox ch_status;
    private javax.swing.JPanel jPanelAtividade;
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

    public void preencherCampos(AtividadeBeans A) {
        txt_codigo.setText(A.getID().toString());
        txt_descricao.setText(A.getDescricao());
        ch_status.setSelected(A.getStatus());
    }

    public void popular(AtividadeBeans a) {
        a.setDescricao(txt_descricao.getText());
        a.setStatus(ch_status.isSelected());
    }

    public boolean verificar(AtividadeBeans A) {
        if (txt_descricao.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha a descrição da Atividade", "Atenção", 0);
            return false;
        }
        return true;
    }

}
