package Financeiro;

import Beans.MoedaBeans;
import DAO.DiversasHibernate;
import static GUI.Principal.listMoeda;
import Utilitarios.Corretores;
import Utilitarios.ValidarPermissoes;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FrmCadMoeda extends javax.swing.JInternalFrame {

    MoedaBeans MoedaB;
    FinanceiroDAO FinanceiroD;

    public FrmCadMoeda() {
        initComponents();
        MoedaB = new MoedaBeans();
        FinanceiroD = new FinanceiroDAO();
        carregarMoeda();
    }

    private void carregarMoeda() {
        if (listMoeda == null) {
            listMoeda = DiversasHibernate.getListaMoeda();
        } else if (listMoeda != null) {
            listMoeda = DiversasHibernate.getListaMoeda();
        }
        cb_moeda.removeAllItems();
        cb_moeda.addItem(new MoedaBeans(0, "Novo"));
        for (MoedaBeans M : listMoeda) {
            cb_moeda.addItem(M);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelMoeda = new javax.swing.JPanel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        btn_cadastrar = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        ch_status = new javax.swing.JCheckBox();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        cb_moeda = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        txt_moeda = new javax.swing.JTextField();
        txt_sifra = new javax.swing.JTextField();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        txt_conversao = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setTitle("Cadastro de Moeda");

        jPanelMoeda.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Moeda");

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
        jLabel4.setText("Moeda");

        cb_moeda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_moedaItemStateChanged(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Cifrão");

        txt_moeda.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txt_sifra.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Conversão");

        txt_conversao.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanelMoedaLayout = new javax.swing.GroupLayout(jPanelMoeda);
        jPanelMoeda.setLayout(jPanelMoedaLayout);
        jPanelMoedaLayout.setHorizontalGroup(
            jPanelMoedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMoedaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMoedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMoedaLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(txt_sifra, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_conversao, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelMoedaLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_moeda, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_moeda)))
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
        jPanelMoedaLayout.setVerticalGroup(
            jPanelMoedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMoedaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMoedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMoedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(cb_moeda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelMoedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txt_moeda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMoedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6)
                    .addComponent(ch_status)
                    .addComponent(btn_cadastrar)
                    .addComponent(btn_editar)
                    .addComponent(jLabel7)
                    .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txt_sifra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txt_conversao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelMoeda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelMoeda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setBounds(0, 0, 651, 127);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cadastrarActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(this, "Deseja cadastrar o Ano da Safra?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (cadastrar == JOptionPane.YES_OPTION) {
            MoedaB = new MoedaBeans();
            popular(MoedaB);
            if (verificar(MoedaB) && ValidarPermissoes.validarPermissaoInsert(FrmCadMoeda.class.getSimpleName())) {
                if (FinanceiroD.salvarMoeda(MoedaB)) {
                    limparCampos(jPanelMoeda);
                    carregarMoeda();
                    cb_moeda.grabFocus();
                }
            }
        }
    }//GEN-LAST:event_btn_cadastrarActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        int editar = JOptionPane.showConfirmDialog(this, "Deseja editar o Ano da Safra?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (editar == JOptionPane.YES_OPTION) {
            popular(MoedaB);
            if (verificar(MoedaB) && ValidarPermissoes.validarPermissaoUpdate(FrmCadMoeda.class.getSimpleName())) {
                if (FinanceiroD.atualizarMoeda(MoedaB)) {
                    limparCampos(jPanelMoeda);
                    carregarMoeda();
                    cb_moeda.grabFocus();
                }
            }
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void cb_moedaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_moedaItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (cb_moeda.getSelectedIndex() == 0) {
                btn_cadastrar.setEnabled(true);
                btn_editar.setEnabled(false);
                limparCampos(jPanelMoeda);
                cb_moeda.grabFocus();
            } else {
                MoedaB = (MoedaBeans) cb_moeda.getSelectedItem();
                preencherCampos(MoedaB);
                btn_cadastrar.setEnabled(false);
                btn_editar.setEnabled(true);
            }
        }
    }//GEN-LAST:event_cb_moedaItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cadastrar;
    private javax.swing.JButton btn_editar;
    private javax.swing.JComboBox<MoedaBeans> cb_moeda;
    private javax.swing.JCheckBox ch_status;
    private javax.swing.JPanel jPanelMoeda;
    private javax.swing.JTextField txt_codigo;
    private javax.swing.JTextField txt_conversao;
    private javax.swing.JTextField txt_moeda;
    private javax.swing.JTextField txt_sifra;
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

    public void preencherCampos(MoedaBeans M) {
        txt_codigo.setText(M.getIdMoeda().toString());
        txt_moeda.setText(M.getMoeda());
        txt_sifra.setText(M.getSifra());
        txt_conversao.setText(Corretores.ConverterDoubleDecimal(M.getConversao()));
        ch_status.setSelected(M.getStatus());
    }

    public void popular(MoedaBeans M) {
        M.setMoeda(txt_moeda.getText());
        M.setSifra(txt_sifra.getText());
        M.setConversao(Corretores.ConverterStringDouble(txt_conversao.getText()));
        M.setStatus(ch_status.isSelected());
    }

    public boolean verificar(MoedaBeans M) {
        if (M.getMoeda().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha a descrição da Moeda", "Atenção", 0);
            return false;
        }
        return true;

    }

}
