package GUI;

import Beans.AnoSafra;
import Beans.AtividadeBeans;
import Beans.CentroDeResultado;
import Beans.CultivoBeans;
import Beans.CulturaBeans;
import DAO.CentroResultadoDAO;
import DAO.DiversasHibernate;
import static GUI.Principal.listAnoSafra;
import static GUI.Principal.listCultivo;
import static GUI.Principal.listCultura;
import static GUI.Principal.listaAtividades;
import static GUI.Principal.listaCentroResultado;
import Utilitarios.ValidarPermissoes;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ItemEvent;
import java.util.Objects;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FrmCadCentroResultado extends javax.swing.JInternalFrame {

    AtividadeBeans AtividadeB;
    AnoSafra SafraB;
    CentroDeResultado ResultadoB;
    CulturaBeans CulturaB;
    CultivoBeans CultivoB;
    CentroResultadoDAO ResultadoD;

    public FrmCadCentroResultado() {
        initComponents();
        ResultadoB = new CentroDeResultado();
        ResultadoD = new CentroResultadoDAO();
        carregarAtividade();
        carregarAnoSafra();
        carregarCultivo();
        carregarCultura();
        carregarResultado();
    }

    private void carregarAtividade() {
        if (listaAtividades == null) {
            listaAtividades = DiversasHibernate.getListaAtividade();
        }
        cb_atividade.addItem(new AtividadeBeans(0, "-"));
        for (AtividadeBeans A : listaAtividades) {
            cb_atividade.addItem(A);
        }
    }

    private void carregarAnoSafra() {
        if (listAnoSafra == null) {
            listAnoSafra = ResultadoD.listarAnoSafra();
        }
        cb_safra.addItem(new AnoSafra(0, "-"));
        for (AnoSafra A : listAnoSafra) {
            cb_safra.addItem(A);
        }
    }

    private void carregarCultivo() {
        if (listCultivo == null) {
            listCultivo = ResultadoD.listarCultivo();
        }
        cb_cultivo.addItem(new CultivoBeans(0, "-"));
        for (CultivoBeans C : listCultivo) {
            cb_cultivo.addItem(C);
        }
    }

    private void carregarCultura() {
        if (listCultura == null) {
            listCultura = ResultadoD.listarCultura();
        }
        cb_cultura.addItem(new CulturaBeans(0, "-"));
        for (CulturaBeans C : listCultura) {
            cb_cultura.addItem(C);
        }
    }

    private void carregarResultado() {
        if (listaCentroResultado == null) {
            listaCentroResultado = DiversasHibernate.getListaCentroResultado();
        } else if (listaCentroResultado != null) {
            listaCentroResultado = DiversasHibernate.getListaCentroResultado();
        }
        cb_centro.removeAllItems();
        cb_centro.addItem(new CentroDeResultado(Long.valueOf(0), "Novo"));
        for (CentroDeResultado C : listaCentroResultado) {
            cb_centro.addItem(C);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelResultado = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        cb_atividade = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        cb_safra = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        cb_cultivo = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        cb_cultura = new javax.swing.JComboBox<>();
        btn_cadastrar = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        ch_status = new javax.swing.JCheckBox();
        cb_centro = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setTitle("Cadastro de Centro de Resultado");

        jPanelResultado.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Centro de Resultado");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Atividade");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Ano da Safra");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Cultivo");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Cultura");

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

        btn_excluir.setText("Excluir");
        btn_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Código");

        txt_codigo.setEditable(false);
        txt_codigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Status");

        ch_status.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        ch_status.setText("Ativo");

        cb_centro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_centroItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanelResultadoLayout = new javax.swing.GroupLayout(jPanelResultado);
        jPanelResultado.setLayout(jPanelResultadoLayout);
        jPanelResultadoLayout.setHorizontalGroup(
            jPanelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelResultadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelResultadoLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_atividade, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_cultivo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_cultura, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_safra, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelResultadoLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_centro, 0, 238, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ch_status)
                        .addGap(18, 18, 18)
                        .addComponent(btn_cadastrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_editar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_excluir)))
                .addContainerGap())
        );
        jPanelResultadoLayout.setVerticalGroup(
            jPanelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelResultadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(btn_cadastrar)
                    .addComponent(btn_editar)
                    .addComponent(btn_excluir)
                    .addComponent(jLabel7)
                    .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(ch_status)
                    .addComponent(cb_centro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(cb_atividade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cb_cultivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cb_cultura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cb_safra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelResultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelResultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setBounds(0, 0, 874, 131);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cadastrarActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(this, "Deseja cadastrar o Centro de Resultado?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (cadastrar == JOptionPane.YES_OPTION) {
            ResultadoB = new CentroDeResultado();
            popular(ResultadoB);
            if (verificar(ResultadoB) && ValidarPermissoes.validarPermissaoInsert(FrmCadCentroResultado.class.getSimpleName())) {
                if (ResultadoD.salvarCentroResultado(ResultadoB)) {
                    limparCampos(jPanelResultado);
                    carregarResultado();
                    cb_centro.grabFocus();
                }
            }
        }
    }//GEN-LAST:event_btn_cadastrarActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        int editar = JOptionPane.showConfirmDialog(this, "Deseja editar o Centro de Resultado?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (editar == JOptionPane.YES_OPTION) {
            popular(ResultadoB);
            if (verificar(ResultadoB) && ValidarPermissoes.validarPermissaoUpdate(FrmCadCentroResultado.class.getSimpleName())) {
                if (ResultadoD.atualizarCentroResultado(ResultadoB)) {
                    limparCampos(jPanelResultado);
                    carregarResultado();
                    cb_centro.grabFocus();
                }
            }
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void cb_centroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_centroItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (cb_centro.getSelectedIndex() == 0) {
                btn_cadastrar.setEnabled(true);
                btn_editar.setEnabled(false);
                btn_excluir.setEnabled(false);
                limparCampos(jPanelResultado);
                cb_centro.grabFocus();
            } else {
                ResultadoB = (CentroDeResultado) cb_centro.getSelectedItem();
                preencherCampos(ResultadoB);
                btn_cadastrar.setEnabled(false);
                btn_editar.setEnabled(true);
                btn_excluir.setEnabled(true);
            }
        }
    }//GEN-LAST:event_cb_centroItemStateChanged

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        int remover = JOptionPane.showConfirmDialog(this, "Deseja deletar o Centro de Resultado?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (remover == JOptionPane.YES_OPTION) {
            if (ResultadoD.removerCentroResultado(ResultadoB) && ValidarPermissoes.validarPermissaoDelete(FrmCadCentroResultado.class.getSimpleName())) {
                limparCampos(jPanelResultado);
                carregarResultado();
                cb_centro.grabFocus();
            }
        }
    }//GEN-LAST:event_btn_excluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cadastrar;
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_excluir;
    private javax.swing.JComboBox<AtividadeBeans> cb_atividade;
    private javax.swing.JComboBox<CentroDeResultado> cb_centro;
    private javax.swing.JComboBox<CultivoBeans> cb_cultivo;
    private javax.swing.JComboBox<CulturaBeans> cb_cultura;
    private javax.swing.JComboBox<AnoSafra> cb_safra;
    private javax.swing.JCheckBox ch_status;
    private javax.swing.JPanel jPanelResultado;
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

    public void preencherCampos(CentroDeResultado C) {
        txt_codigo.setText(C.getID().toString());
        ch_status.setSelected(C.getStatus());
        setSafra(cb_safra, C.getAnoSafra().getIdSafra());
        setAtividade(cb_atividade, C.getAtividade().getID());
        setCultivo(cb_cultivo, C.getCultivo().getIDCultivo());
        setCultura(cb_cultura, C.getCultura().getIDCultura());
    }

    public void popular(CentroDeResultado C) {
        C.setStatus(ch_status.isSelected());
        C.setAnoSafra(getSafra(cb_safra));
        C.setAtividade(getAtividade(cb_atividade));
        C.setCultivo(getCultivo(cb_cultivo));
        C.setCultura(getCultura(cb_cultura));
    }

    private AnoSafra getSafra(JComboBox<AnoSafra> combo) {
        return (AnoSafra) combo.getSelectedItem();
    }

    private void setSafra(JComboBox<AnoSafra> combo, Integer ID) {
        for (int i = 0; i < combo.getModel().getSize(); i++) {
            if (Objects.equals(combo.getItemAt(i).getIdSafra(), ID)) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }

    private AtividadeBeans getAtividade(JComboBox<AtividadeBeans> combo) {
        return (AtividadeBeans) combo.getSelectedItem();
    }

    private void setAtividade(JComboBox<AtividadeBeans> combo, Integer ID) {
        for (int i = 0; i < combo.getModel().getSize(); i++) {
            if (Objects.equals(combo.getItemAt(i).getID(), ID)) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }

    private CultivoBeans getCultivo(JComboBox<CultivoBeans> combo) {
        return (CultivoBeans) combo.getSelectedItem();
    }

    private void setCultivo(JComboBox<CultivoBeans> combo, Integer ID) {
        for (int i = 0; i < combo.getModel().getSize(); i++) {
            if (Objects.equals(combo.getItemAt(i).getIDCultivo(), ID)) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }

    private CulturaBeans getCultura(JComboBox<CulturaBeans> combo) {
        return (CulturaBeans) combo.getSelectedItem();
    }

    private void setCultura(JComboBox<CulturaBeans> combo, Integer ID) {
        for (int i = 0; i < combo.getModel().getSize(); i++) {
            if (Objects.equals(combo.getItemAt(i).getIDCultura(), ID)) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }

    public boolean verificar(CentroDeResultado C) {

        if (cb_atividade.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione a Atividade", "Atenção", 0);
            return false;
        }
        if (cb_cultivo.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione o Cultivo", "Atenção", 0);
            return false;
        }
        if (cb_cultura.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione a Cultura", "Atenção", 0);
            return false;
        }
        if (cb_safra.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione o Ano da Safra", "Atenção", 0);
            return false;
        }
        return true;
    }

}
