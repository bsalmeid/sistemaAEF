package Financeiro;

import Beans.CidadesBeans;
import Beans.CompradorGadoBeans;
import Beans.EstadosBeans;
import DAO.DiversasHibernate;
import static GUI.Principal.listCidades;
import static GUI.Principal.listCompradores;
import static GUI.Principal.listEstados;
import Icones.FormatarICO;
import Utilitarios.Corretores;
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
import java.util.Objects;
import javax.swing.JFormattedTextField;

public class FrmCadCompradorGados extends javax.swing.JInternalFrame {

    CompradorGadoBeans CompradorB;
    FinanceiroDAO FinanceiroD;
    MaskFormatter TelefoneMask;

    public FrmCadCompradorGados() {
        initComponents();
        CompradorB =  new CompradorGadoBeans();
        FinanceiroD = new FinanceiroDAO();
        maskFormater();
        carregarComprador();
        carregarEstados();
        carregarCidades();
    }

    private void carregarComprador() {
        if (listCompradores == null) {
            listCompradores =  DiversasHibernate.getListaCompradorgado();
        } else if (listCompradores != null) {
            listCompradores =  DiversasHibernate.getListaCompradorgado();
        }
        cb_comprador.removeAllItems();
        cb_comprador.addItem(new CompradorGadoBeans(0, "Novo"));
        for (CompradorGadoBeans C : listCompradores) {
            cb_comprador.addItem(C);
        }
    }
    
    private void carregarEstados() {
        if (listEstados == null) {
            listEstados =  DiversasHibernate.getListaEstados();
        } 
        cb_estado.removeAllItems();
        cb_estado.addItem(new EstadosBeans(0, "-"));
        for (EstadosBeans C : listEstados) {
            cb_estado.addItem(C);
        }
    }
    
    private void carregarCidades() {
        if (listCidades == null) {
            listCidades =  DiversasHibernate.getListaCidades();
        }
        cb_cidade.removeAllItems();
        cb_cidade.addItem(new CidadesBeans(0, "-"));
        for (CidadesBeans C : listCidades) {
            cb_cidade.addItem(C);
        }
    }
    
    private void carregarCidades(Integer idEstado, JComboBox<CidadesBeans> combo) {
        if (listCidades == null) {
            listCidades =  DiversasHibernate.getListaCidades();
        }
        combo.removeAllItems();
        combo.addItem(new CidadesBeans(0, "-"));
        for (CidadesBeans C : listCidades) {
            if (Objects.equals(C.getId_UF().getIdEtado(), idEstado)) {
                combo.addItem(C);
            }            
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelComprador = new javax.swing.JPanel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        btn_cadastrar = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        ch_status = new javax.swing.JCheckBox();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        ft_telefone = new javax.swing.JFormattedTextField();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        txt_compradorGado = new javax.swing.JTextField();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        cb_cidade = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        txt_comissao = new javax.swing.JTextField();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        cb_estado = new javax.swing.JComboBox<>();
        txt_endereco = new javax.swing.JTextField();
        cb_comprador = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setTitle("Cadastro de Comprador de Gados");

        jPanelComprador.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Comprador ");

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

        ft_telefone.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Comprador de Gados");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Telefone");

        txt_compradorGado.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Endereço");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Cidade");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Email");

        txt_email.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Comissão");

        txt_comissao.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_comissao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_comissaoFocusLost(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("UF");

        cb_estado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_estadoItemStateChanged(evt);
            }
        });

        txt_endereco.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        cb_comprador.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_compradorItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanelCompradorLayout = new javax.swing.GroupLayout(jPanelComprador);
        jPanelComprador.setLayout(jPanelCompradorLayout);
        jPanelCompradorLayout.setHorizontalGroup(
            jPanelCompradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCompradorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCompradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCompradorLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ft_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_email)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_comissao, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCompradorLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_cidade, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_endereco))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCompradorLayout.createSequentialGroup()
                        .addGroup(jPanelCompradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelCompradorLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_comprador, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 167, Short.MAX_VALUE))
                            .addGroup(jPanelCompradorLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_compradorGado)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ch_status)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_cadastrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_editar)))
                .addContainerGap())
        );
        jPanelCompradorLayout.setVerticalGroup(
            jPanelCompradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCompradorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCompradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cb_comprador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCompradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(ch_status)
                    .addComponent(btn_cadastrar)
                    .addComponent(btn_editar)
                    .addComponent(jLabel7)
                    .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_compradorGado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCompradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cb_cidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(cb_estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txt_endereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCompradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ft_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel10)
                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txt_comissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelComprador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelComprador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setBounds(0, 0, 806, 179);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cadastrarActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(this, "Deseja cadastrar o Comprador de Gados?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (cadastrar == JOptionPane.YES_OPTION) {
            CompradorB =  new CompradorGadoBeans();
            popular(CompradorB);
            if (verificar(CompradorB) && ValidarPermissoes.validarPermissaoInsert(FrmCadCompradorGados.class.getSimpleName())) {
                if (FinanceiroD.salvarCompradorGado(CompradorB)) {
                    limparCampos(jPanelComprador);
                    carregarComprador();
                    cb_comprador.grabFocus();
                }
            }
        }
    }//GEN-LAST:event_btn_cadastrarActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        int editar = JOptionPane.showConfirmDialog(this, "Deseja editar Comprador de Gados?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (editar == JOptionPane.YES_OPTION) {
            popular(CompradorB);
            if (verificar(CompradorB) && ValidarPermissoes.validarPermissaoUpdate(FrmCadCompradorGados.class.getSimpleName())) {
                FinanceiroD.atualizarCompradorGado(CompradorB);
                limparCampos(jPanelComprador);
                carregarComprador();
                cb_comprador.grabFocus();
            }
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void cb_estadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_estadoItemStateChanged
        if (cb_estado.getSelectedIndex() > 0) {
            carregarCidades(getEstado(cb_estado).getIdEtado(), cb_cidade);
        }
    }//GEN-LAST:event_cb_estadoItemStateChanged

    private void txt_comissaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_comissaoFocusLost
        txt_comissao.setText(Corretores.ConverterDecimalReais(txt_comissao.getText()));
    }//GEN-LAST:event_txt_comissaoFocusLost

    private void cb_compradorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_compradorItemStateChanged
         if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (cb_comprador.getSelectedIndex() == 0) {
                //limparCampos(jPanelComprador);
                btn_cadastrar.setEnabled(true);
                btn_editar.setEnabled(false);
                cb_comprador.grabFocus();
            } else {
                CompradorB = (CompradorGadoBeans) cb_comprador.getSelectedItem();
                preencherCampos(CompradorB);
                btn_cadastrar.setEnabled(false);
                btn_editar.setEnabled(true);
            }
        }
    }//GEN-LAST:event_cb_compradorItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cadastrar;
    private javax.swing.JButton btn_editar;
    private javax.swing.JComboBox<CidadesBeans> cb_cidade;
    private javax.swing.JComboBox<CompradorGadoBeans> cb_comprador;
    private javax.swing.JComboBox<EstadosBeans> cb_estado;
    private javax.swing.JCheckBox ch_status;
    private javax.swing.JFormattedTextField ft_telefone;
    private javax.swing.JPanel jPanelComprador;
    private javax.swing.JTextField txt_codigo;
    private javax.swing.JTextField txt_comissao;
    private javax.swing.JTextField txt_compradorGado;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_endereco;
    // End of variables declaration//GEN-END:variables

    private void limparCampos(Container container) {
        for (int i = 0; i < container.getComponentCount(); i++) {
            Component c = container.getComponent(i);
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            } else if (c instanceof JFormattedTextField) {
                ((JFormattedTextField) c).setText("");
            }else if (c instanceof JComboBox) {
                ((JComboBox) c).setSelectedIndex(0);
            } else if (c instanceof JCheckBox) {
                ((JCheckBox) c).setSelected(false);
            }
        }
    }

    private void maskFormater() {
        try {
            TelefoneMask = new MaskFormatter("(##)#####-####");
            ft_telefone.setFormatterFactory(new DefaultFormatterFactory(TelefoneMask));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, ex + " Erro ao formatar Telefone!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }

    public void preencherCampos(CompradorGadoBeans C) {
        txt_codigo.setText(C.getIdComprador().toString());
        txt_compradorGado.setText(C.getComprador());
        ch_status.setSelected(C.getStatus());
        txt_endereco.setText(C.getEndereco());
        ft_telefone.setText(C.getTelefone());
        txt_email.setText(C.getEmail());
        txt_comissao.setText(Corretores.ConverterDoubleReais(C.getComissao()));
        setEstado(cb_estado, C.getCidade().getId_UF().getIdEtado());
        setCidade(cb_cidade, C.getCidade().getId());
        
        
    }

    public void popular(CompradorGadoBeans C) {
        C.setComprador(txt_compradorGado.getText());
        C.setStatus(ch_status.isSelected());
        C.setEndereco(txt_endereco.getText());
        C.setTelefone(ft_telefone.getText());
        C.setEmail(txt_email.getText());
        C.setComissao(Corretores.ConverterStringDouble(txt_comissao.getText()));
        C.setCidade(getCidade(cb_cidade));
    }

    public boolean verificar(CompradorGadoBeans C) {
        if (C.getComprador().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha o nome do Comprador", "Atenção", 0);
            return false;
        }
        return true;

    }
    
    private EstadosBeans getEstado(JComboBox<EstadosBeans> combo) {
        return (EstadosBeans) combo.getSelectedItem();   
    }
    
    private void setEstado(JComboBox<EstadosBeans> combo, Integer ID) {
        for (int i = 0; i < combo.getModel().getSize(); i++) {
            if (Objects.equals(combo.getItemAt(i).getIdEtado(), ID)) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }
    
    private CidadesBeans getCidade(JComboBox<CidadesBeans> combo) {
        return (CidadesBeans) combo.getSelectedItem();   
    }
    
    private void setCidade(JComboBox<CidadesBeans> combo, Integer ID) {
        for (int i = 0; i < combo.getModel().getSize(); i++) {
            if (Objects.equals(combo.getItemAt(i).getId(), ID)) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }

}
