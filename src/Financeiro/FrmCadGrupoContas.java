package Financeiro;

import Beans.GrupoContasBeans;
import DAO.DiversasHibernate;
import static GUI.Principal.listaGrupoContas;
import Utilitarios.ValidarPermissoes;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FrmCadGrupoContas extends javax.swing.JInternalFrame {

    GrupoContasBeans GrupoB;
    FinanceiroDAO FinanceiroD;

    public FrmCadGrupoContas() {
        initComponents();
        GrupoB = new GrupoContasBeans();
        FinanceiroD = new FinanceiroDAO();
        carregarGrupoContas();
    }

    private void carregarGrupoContas() {
        if (listaGrupoContas == null) {
            listaGrupoContas = DiversasHibernate.getListaGrupoConta();
        } else if (listaGrupoContas != null) {
            listaGrupoContas = DiversasHibernate.getListaGrupoConta();
        }
        cb_grupoConta.removeAllItems();
        cb_grupoConta.addItem(new GrupoContasBeans(0, "Novo"));
        for (GrupoContasBeans G : listaGrupoContas) {
            cb_grupoConta.addItem(G);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelGrupoContas = new javax.swing.JPanel();
        btn_cadastrar = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        ch_status = new javax.swing.JCheckBox();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        cb_grupoConta = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        ch_saida = new javax.swing.JCheckBox();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        ch_transferencia = new javax.swing.JCheckBox();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        ch_entrada = new javax.swing.JCheckBox();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        txt_descricao = new javax.swing.JTextField();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setTitle("Cadastro Grupo de Contas");

        jPanelGrupoContas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Grupo de Contas");

        cb_grupoConta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_grupoContaItemStateChanged(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Saída");

        ch_saida.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_saida.setText("Ativo");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Transferência");

        ch_transferencia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_transferencia.setText("Ativo");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Entrada");

        ch_entrada.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_entrada.setText("Ativo");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Descrição");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Código");

        txt_codigo.setEditable(false);
        txt_codigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanelGrupoContasLayout = new javax.swing.GroupLayout(jPanelGrupoContas);
        jPanelGrupoContas.setLayout(jPanelGrupoContasLayout);
        jPanelGrupoContasLayout.setHorizontalGroup(
            jPanelGrupoContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGrupoContasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelGrupoContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelGrupoContasLayout.createSequentialGroup()
                        .addGroup(jPanelGrupoContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelGrupoContasLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_grupoConta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelGrupoContasLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_cadastrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_editar))
                    .addGroup(jPanelGrupoContasLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ch_entrada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ch_transferencia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ch_saida)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ch_status)))
                .addContainerGap())
        );
        jPanelGrupoContasLayout.setVerticalGroup(
            jPanelGrupoContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelGrupoContasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelGrupoContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cb_grupoConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelGrupoContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_cadastrar)
                    .addComponent(btn_editar)
                    .addComponent(jLabel11)
                    .addComponent(txt_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelGrupoContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel10)
                    .addComponent(ch_entrada)
                    .addComponent(jLabel9)
                    .addComponent(ch_transferencia)
                    .addComponent(jLabel8)
                    .addComponent(ch_saida)
                    .addComponent(jLabel6)
                    .addComponent(ch_status))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelGrupoContas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelGrupoContas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setBounds(0, 0, 576, 148);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cadastrarActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(this, "Deseja cadastrar o Grupo de Contas?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (cadastrar == JOptionPane.YES_OPTION) {
            GrupoB = new GrupoContasBeans();
            popular(GrupoB);
            if (verificar(GrupoB) && ValidarPermissoes.validarPermissaoInsert(FrmCadGrupoContas.class.getSimpleName())) {
                if (FinanceiroD.salvarGrupoContas(GrupoB)) {
                    limparCampos(jPanelGrupoContas);
                    carregarGrupoContas();
                    cb_grupoConta.grabFocus();
                }
            }
        }
    }//GEN-LAST:event_btn_cadastrarActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        int editar = JOptionPane.showConfirmDialog(this, "Deseja editar o Grupo de Contas?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (editar == JOptionPane.YES_OPTION) {
            popular(GrupoB);
            if (verificar(GrupoB) && ValidarPermissoes.validarPermissaoUpdate(FrmCadGrupoContas.class.getSimpleName())) {
                if (FinanceiroD.atualizarGrupoContas(GrupoB)) {
                    limparCampos(jPanelGrupoContas);
                    carregarGrupoContas();
                    cb_grupoConta.grabFocus();
                }
            }
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void cb_grupoContaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_grupoContaItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (cb_grupoConta.getSelectedIndex() == 0) {
                btn_cadastrar.setEnabled(true);
                btn_editar.setEnabled(false);
                limparCampos(jPanelGrupoContas);
                cb_grupoConta.grabFocus();
            } else {
                GrupoB = (GrupoContasBeans) cb_grupoConta.getSelectedItem();
                preencherCampos(GrupoB);
                btn_cadastrar.setEnabled(false);
                btn_editar.setEnabled(true);
            }
        }
    }//GEN-LAST:event_cb_grupoContaItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cadastrar;
    private javax.swing.JButton btn_editar;
    private javax.swing.JComboBox<GrupoContasBeans> cb_grupoConta;
    private javax.swing.JCheckBox ch_entrada;
    private javax.swing.JCheckBox ch_saida;
    private javax.swing.JCheckBox ch_status;
    private javax.swing.JCheckBox ch_transferencia;
    private javax.swing.JPanel jPanelGrupoContas;
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

    public void preencherCampos(GrupoContasBeans G) {
        txt_codigo.setText(G.getID().toString());
        txt_descricao.setText(G.getDescricao());
        ch_entrada.setSelected(G.getEntrada());
        ch_transferencia.setSelected(G.getTransferencia());
        ch_saida.setSelected(G.getSaida());
        ch_status.setSelected(G.getStatus());
    }

    public void popular(GrupoContasBeans G) {
        G.setDescricao(txt_descricao.getText());
        G.setEntrada(ch_entrada.isSelected());
        G.setTransferencia(ch_transferencia.isSelected());
        G.setSaida(ch_saida.isSelected());
        G.setStatus(ch_status.isSelected());
    }

    public boolean verificar(GrupoContasBeans G) {
        if (G.getDescricao().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha a Descrição do Grupo de Contas", "Atenção", 0);
            return false;
        }
        return true;

    }

}
