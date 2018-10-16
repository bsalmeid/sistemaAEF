package GUI;

import Beans.AnoSafra;
import DAO.CentroResultadoDAO;
import static GUI.Principal.listAnoSafra;
import Icones.FormatarICO;
import Utilitarios.ValidarPermissoes;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class FrmCadAnoSafra extends javax.swing.JInternalFrame {

    AnoSafra AnoSafraB;
    CentroResultadoDAO ResultadoD;
    MaskFormatter AnoMask;

    public FrmCadAnoSafra() {
        initComponents();
        AnoSafraB = new AnoSafra();
        ResultadoD = new CentroResultadoDAO();
        maskFormater();
        carregarAnoSafra();
    }

    private void carregarAnoSafra() {
        if (listAnoSafra == null) {
            listAnoSafra = ResultadoD.listarAnoSafra();
        } else if (listAnoSafra != null) {
            listAnoSafra = ResultadoD.listarAnoSafra();
        }
        cb_safra.removeAllItems();
        cb_safra.addItem(new AnoSafra(0, "Novo"));
        for (AnoSafra A : listAnoSafra) {
            cb_safra.addItem(A);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelAnoSafra = new javax.swing.JPanel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        btn_cadastrar = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        ch_status = new javax.swing.JCheckBox();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        ft_anoSafra = new javax.swing.JFormattedTextField();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        cb_safra = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setTitle("Cadastro de Ano da Safra");

        jPanelAnoSafra.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Ano da Safra");

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

        ft_anoSafra.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Ano da Safra");

        cb_safra.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_safraItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanelAnoSafraLayout = new javax.swing.GroupLayout(jPanelAnoSafra);
        jPanelAnoSafra.setLayout(jPanelAnoSafraLayout);
        jPanelAnoSafraLayout.setHorizontalGroup(
            jPanelAnoSafraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAnoSafraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAnoSafraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelAnoSafraLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ft_anoSafra, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelAnoSafraLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_safra, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
        jPanelAnoSafraLayout.setVerticalGroup(
            jPanelAnoSafraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAnoSafraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAnoSafraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cb_safra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelAnoSafraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(ch_status)
                    .addComponent(btn_cadastrar)
                    .addComponent(btn_editar)
                    .addComponent(jLabel7)
                    .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ft_anoSafra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelAnoSafra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelAnoSafra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setBounds(0, 0, 662, 127);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cadastrarActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(this, "Deseja cadastrar o Ano da Safra?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (cadastrar == JOptionPane.YES_OPTION) {
            AnoSafraB = new AnoSafra();
            popular(AnoSafraB);
            if (verificar(AnoSafraB) && ValidarPermissoes.validarPermissaoInsert(FrmCadAnoSafra.class.getSimpleName())) {
                if (ResultadoD.salvarAnoSafa(AnoSafraB)) {
                    limparCampos(jPanelAnoSafra);
                    carregarAnoSafra();
                    cb_safra.grabFocus();
                }
            }
        }
    }//GEN-LAST:event_btn_cadastrarActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        int editar = JOptionPane.showConfirmDialog(this, "Deseja editar o Ano da Safra?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (editar == JOptionPane.YES_OPTION) {
            popular(AnoSafraB);
            if (verificar(AnoSafraB) && ValidarPermissoes.validarPermissaoUpdate(FrmCadAnoSafra.class.getSimpleName())) {
                ResultadoD.atualizarAnoSafra(AnoSafraB);
                limparCampos(jPanelAnoSafra);
                carregarAnoSafra();
                cb_safra.grabFocus();
            }
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void cb_safraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_safraItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (cb_safra.getSelectedIndex() == 0) {
                btn_cadastrar.setEnabled(true);
                btn_editar.setEnabled(false);
                limparCampos(jPanelAnoSafra);
                cb_safra.grabFocus();
            } else {
                AnoSafraB = (AnoSafra) cb_safra.getSelectedItem();
                preencherCampos(AnoSafraB);
                btn_cadastrar.setEnabled(false);
                btn_editar.setEnabled(true);
            }
        }
    }//GEN-LAST:event_cb_safraItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cadastrar;
    private javax.swing.JButton btn_editar;
    private javax.swing.JComboBox<AnoSafra> cb_safra;
    private javax.swing.JCheckBox ch_status;
    private javax.swing.JFormattedTextField ft_anoSafra;
    private javax.swing.JPanel jPanelAnoSafra;
    private javax.swing.JTextField txt_codigo;
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

    private void maskFormater() {
        try {
            AnoMask = new MaskFormatter("####/##");
            ft_anoSafra.setFormatterFactory(new DefaultFormatterFactory(AnoMask));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, ex + " Erro ao formatar AnoSafra!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }

    public void preencherCampos(AnoSafra A) {
        txt_codigo.setText(A.getIdSafra().toString());
        ft_anoSafra.setText(A.getAnoSafra());
        ch_status.setSelected(A.getStatus());
    }

    public void popular(AnoSafra A) {
        A.setAnoSafra(ft_anoSafra.getText());
        A.setStatus(ch_status.isSelected());
    }

    public boolean verificar(AnoSafra A) {
        if (ft_anoSafra.getText().equals("    /  ")) {
            JOptionPane.showMessageDialog(null, "Preencha o Ano da Safra", "Atenção", 0);
            return false;
        }
        return true;

    }

}
