package Financeiro;

import Beans.AnoSafra;
import Beans.ContaBancariaBeans;
import DAO.DiversasHibernate;
import static GUI.Principal.listContaOrigem;
import Utilitarios.ValidarPermissoes;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FrmCadContaBancaria extends javax.swing.JInternalFrame {

    ContaBancariaBeans ContaB;
    FinanceiroDAO FinanceiroD;

    public FrmCadContaBancaria() {
        initComponents();
        ContaB = new ContaBancariaBeans();
        FinanceiroD = new FinanceiroDAO();
        carregarConta();
    }

    private void carregarConta() {
        if (listContaOrigem == null) {
            listContaOrigem = DiversasHibernate.getListaContasBancarias();
        } else if (listContaOrigem != null) {
            listContaOrigem = DiversasHibernate.getListaContasBancarias();
        }
        cb_conta.removeAllItems();
        cb_conta.addItem(new ContaBancariaBeans(0, "Novo"));
        for (ContaBancariaBeans C : listContaOrigem) {
            cb_conta.addItem(C);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelConta = new javax.swing.JPanel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        btn_cadastrar = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        ch_status = new javax.swing.JCheckBox();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        cb_conta = new javax.swing.JComboBox<>();
        txt_nomeTitular = new javax.swing.JTextField();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        txt_nomeBanco = new javax.swing.JTextField();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        txt_agencia = new javax.swing.JTextField();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        txt_conta = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setTitle("Cadastro Conta Bancária");
        setToolTipText("");

        jPanelConta.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Nome do Titular");

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

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Conta Bancária ");

        cb_conta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_contaItemStateChanged(evt);
            }
        });

        txt_nomeTitular.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Nome do Banco");

        txt_nomeBanco.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Agência");

        txt_agencia.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Número da Conta");

        txt_conta.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanelContaLayout = new javax.swing.GroupLayout(jPanelConta);
        jPanelConta.setLayout(jPanelContaLayout);
        jPanelContaLayout.setHorizontalGroup(
            jPanelContaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelContaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelContaLayout.createSequentialGroup()
                        .addGroup(jPanelContaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelContaLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_nomeBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_agencia))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelContaLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_nomeTitular, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelContaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelContaLayout.createSequentialGroup()
                                .addComponent(ch_status)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_cadastrar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_editar))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelContaLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_conta))))
                    .addGroup(jPanelContaLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_conta, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelContaLayout.setVerticalGroup(
            jPanelContaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelContaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelContaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cb_conta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelContaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_editar)
                    .addComponent(btn_cadastrar)
                    .addComponent(ch_status)
                    .addComponent(jLabel6)
                    .addComponent(txt_nomeTitular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelContaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txt_nomeBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txt_agencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txt_conta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setBounds(0, 0, 655, 153);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cadastrarActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(this, "Deseja cadastrar a Conta Bancária?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (cadastrar == JOptionPane.YES_OPTION) {
            ContaB = new ContaBancariaBeans();
            popular(ContaB);
            if (verificar(ContaB) && ValidarPermissoes.validarPermissaoInsert(FrmCadContaBancaria.class.getSimpleName())) {
                if (FinanceiroD.salvarContaBancria(ContaB)) {
                    limparCampos(jPanelConta);
                    carregarConta();
                    cb_conta.grabFocus();
                }
            }
        }
    }//GEN-LAST:event_btn_cadastrarActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        int editar = JOptionPane.showConfirmDialog(this, "Deseja editar a Conta Bancária?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (editar == JOptionPane.YES_OPTION) {
            popular(ContaB);
            if (verificar(ContaB) && ValidarPermissoes.validarPermissaoUpdate(FrmCadContaBancaria.class.getSimpleName())) {
                if (FinanceiroD.atualizarContaBancaria(ContaB)) {
                    limparCampos(jPanelConta);
                    carregarConta();
                    cb_conta.grabFocus();
                }
            }
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void cb_contaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_contaItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (cb_conta.getSelectedIndex() == 0) {
                btn_cadastrar.setEnabled(true);
                btn_editar.setEnabled(false);
                limparCampos(jPanelConta);
                cb_conta.grabFocus();
            } else {
                ContaB = (ContaBancariaBeans) cb_conta.getSelectedItem();
                preencherCampos(ContaB);
                btn_cadastrar.setEnabled(false);
                btn_editar.setEnabled(true);
            }
        }
    }//GEN-LAST:event_cb_contaItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cadastrar;
    private javax.swing.JButton btn_editar;
    private javax.swing.JComboBox<ContaBancariaBeans> cb_conta;
    private javax.swing.JCheckBox ch_status;
    private javax.swing.JPanel jPanelConta;
    private javax.swing.JTextField txt_agencia;
    private javax.swing.JTextField txt_codigo;
    private javax.swing.JTextField txt_conta;
    private javax.swing.JTextField txt_nomeBanco;
    private javax.swing.JTextField txt_nomeTitular;
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

    public void preencherCampos(ContaBancariaBeans C) {
        txt_codigo.setText(C.getIdConta().toString());
        txt_nomeTitular.setText(C.getDescricao());
        ch_status.setSelected(C.isStatus());
        txt_nomeBanco.setText(C.getBanco());
        txt_agencia.setText(C.getAgencia());
        txt_conta.setText(C.getConta());
    }

    public void popular(ContaBancariaBeans C) {
        C.setDescricao(txt_nomeTitular.getText());
        C.setStatus(ch_status.isSelected());
        C.setBanco(txt_nomeBanco.getText());
        C.setAgencia(txt_agencia.getText());
        C.setConta(txt_conta.getText());
    }

    public boolean verificar(ContaBancariaBeans C) {
        if (C.getDescricao().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha o Nome do Titular", "Atenção", 0);
            return false;
        }
        return true;

    }

}
