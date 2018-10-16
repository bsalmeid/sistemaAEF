package GUI;

import DAO.InventarioDAO;
import Beans.CategoriaEquipamentosBeans;
import Beans.ModeloEquipamentosBeans;
import Beans.MarcaEquipamentosBeans;
import TableModel.TableModelConsultaModeloInv;
import static GUI.Principal.listaCategoriaEquipamentos;
import static GUI.Principal.listaMarcaEquipamentos;
import Utilitarios.CentralizarTabela;
import Utilitarios.ValidarPermissoes;
import java.awt.Component;
import java.awt.Container;
import java.util.Objects;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class FrmCadModelo extends javax.swing.JInternalFrame {

    TableModelConsultaModeloInv TbConModelo;
    InventarioDAO InventD;
    CentralizarTabela Centralizar;
    ModeloEquipamentosBeans ModeloB;
    MarcaEquipamentosBeans MarcaB;
    CategoriaEquipamentosBeans CatB;

    public FrmCadModelo() {
        initComponents();
        Centralizar = new CentralizarTabela();
        InventD = new InventarioDAO();
        ModeloB = new ModeloEquipamentosBeans();
        habilitarCampos(false);
        habilitarJButton(false);
        carregarCategorias();
        carregarMarcas();
        getTabelaConModelo();
    }

    private void carregarCategorias() {
        if (listaCategoriaEquipamentos == null) {
            listaCategoriaEquipamentos = InventD.listarCategoria();
        }
        cb_categoria.addItem(new CategoriaEquipamentosBeans(0, "-"));
        for (CategoriaEquipamentosBeans c : listaCategoriaEquipamentos) {
            cb_categoria.addItem(c);
        }
    }

    private void carregarMarcas() {
        if (listaMarcaEquipamentos == null) {
            listaMarcaEquipamentos = InventD.listarMarca();
        }
        cb_marca.addItem(new MarcaEquipamentosBeans(0, "-"));
        for (MarcaEquipamentosBeans m : listaMarcaEquipamentos) {
            cb_marca.addItem(m);
        }
    }

    private TableModelConsultaModeloInv getTableModelConModelo() {
        if (TbConModelo == null) {
            TbConModelo = new TableModelConsultaModeloInv();
        }
        return TbConModelo;
    }

    private JTable getTabelaConModelo() {
        tb_modelo.setModel(getTableModelConModelo());
        Centralizar.centralizarTabela(tb_modelo);
        ((DefaultTableCellRenderer) tb_modelo.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        tb_modelo.getColumnModel().getColumn(TbConModelo.ID_MODELO).setPreferredWidth(10);
        tb_modelo.getColumnModel().getColumn(TbConModelo.CATEGORIA).setPreferredWidth(100);
        tb_modelo.getColumnModel().getColumn(TbConModelo.MARCA).setPreferredWidth(100);
        tb_modelo.getColumnModel().getColumn(TbConModelo.MODELO).setPreferredWidth(100);
        tb_modelo.getColumnModel().getColumn(TbConModelo.LARGURA).setPreferredWidth(30);
        tb_modelo.getColumnModel().getColumn(TbConModelo.STATUS).setPreferredWidth(10);
        TbConModelo.limpar();
        TbConModelo.addLista(InventD.listarModelo());
        return tb_modelo;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelCadModelo = new javax.swing.JPanel();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        txt_descricao = new javax.swing.JTextField();
        javax.swing.JLabel jLabel14 = new javax.swing.JLabel();
        txt_largura = new javax.swing.JTextField();
        javax.swing.JLabel jLabel13 = new javax.swing.JLabel();
        ch_status = new javax.swing.JCheckBox();
        cb_categoria = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        cb_marca = new javax.swing.JComboBox<>();
        btn_editar = new javax.swing.JButton();
        btn_salvar = new javax.swing.JButton();
        btn_novo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_modelo = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro de Modelos");

        jPanelCadModelo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Código");

        txt_codigo.setEditable(false);
        txt_codigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_codigo.setEnabled(false);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Modelo");

        txt_descricao.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Largura");

        txt_largura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_largura.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_larguraFocusLost(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Status");

        ch_status.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        ch_status.setText("Ativo");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Categoria");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Marca");

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

        javax.swing.GroupLayout jPanelCadModeloLayout = new javax.swing.GroupLayout(jPanelCadModelo);
        jPanelCadModelo.setLayout(jPanelCadModeloLayout);
        jPanelCadModeloLayout.setHorizontalGroup(
            jPanelCadModeloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCadModeloLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCadModeloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCadModeloLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_largura, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ch_status))
                    .addGroup(jPanelCadModeloLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_marca, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelCadModeloLayout.setVerticalGroup(
            jPanelCadModeloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCadModeloLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCadModeloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel11)
                    .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txt_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txt_largura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(ch_status))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCadModeloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6)
                    .addComponent(cb_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cb_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelCadModeloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(0, 10, Short.MAX_VALUE))
        );

        tb_modelo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tb_modelo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_modeloMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_modelo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanelCadModelo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelCadModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Deseja cadastrar um novo Modelo?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
                == JOptionPane.YES_OPTION) {
            popular(ModeloB);
            if (verificar(ModeloB) && ValidarPermissoes.validarPermissaoInsert(FrmCadModelo.class.getSimpleName())) {
                if (InventD.salvarModelo(ModeloB)) {
                    limparCampos(jPanelCadModelo);
                    txt_descricao.grabFocus();
                    habilitarCampos(false);
                    habilitarJButton(false);
                    limparCampos(jPanelCadModelo);
                    TbConModelo.limpar();
                    TbConModelo.addLista(InventD.listarModelo());
                }
            }
        }
    }//GEN-LAST:event_btn_salvarActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Deseja editar o Modelo?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
                == JOptionPane.YES_OPTION) {
            if (verificar(ModeloB) && ValidarPermissoes.validarPermissaoUpdate(FrmCadModelo.class.getSimpleName())) {
                editarModelo(ModeloB);
                InventD.atualizarModelo(ModeloB);
                habilitarCampos(false);
                habilitarJButton(false);
                limparCampos(jPanelCadModelo);
                TbConModelo.limpar();
                TbConModelo.addLista(InventD.listarModelo());
            }
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void txt_larguraFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_larguraFocusLost
        //txt_largura.setText(Corretores.ConverterDoubleDecimal(txt_largura.getText()));
    }//GEN-LAST:event_txt_larguraFocusLost

    private void tb_modeloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_modeloMouseClicked
        int linha = tb_modelo.getSelectedRow();
        if (evt.getClickCount() == 2) {
            limparCampos(jPanelCadModelo);
            ModeloB = InventD.buscarModelo((TbConModelo.getValueAt(linha, TbConModelo.MODELO)).toString());
            preencherCampos(ModeloB);
            habilitarCampos(true);
            habilitarJButton(true);
            btn_salvar.setEnabled(false);
            btn_editar.setEnabled(true);
            txt_descricao.grabFocus();
        }else {
            habilitarCampos(true);
            btn_editar.setEnabled(false);
            btn_salvar.setEnabled(true);
            limparCampos(jPanelCadModelo);
        }
    }//GEN-LAST:event_tb_modeloMouseClicked

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed
        habilitarCampos(true);
        habilitarJButton(true);
        btn_editar.setEnabled(false);
        limparCampos(jPanelCadModelo);
        TbConModelo.limpar();
        TbConModelo.addLista(InventD.listarModelo());
        txt_descricao.grabFocus();
    }//GEN-LAST:event_btn_novoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_novo;
    private javax.swing.JButton btn_salvar;
    private javax.swing.JComboBox<CategoriaEquipamentosBeans> cb_categoria;
    private javax.swing.JComboBox<MarcaEquipamentosBeans> cb_marca;
    private javax.swing.JCheckBox ch_status;
    private javax.swing.JPanel jPanelCadModelo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_modelo;
    private javax.swing.JTextField txt_codigo;
    private javax.swing.JTextField txt_descricao;
    private javax.swing.JTextField txt_largura;
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
        txt_largura.setEnabled(valor);
        ch_status.setEnabled(valor);
        cb_categoria.setEnabled(valor);
        cb_marca.setEnabled(valor);
    }

    final void habilitarJButton(boolean valor) {
        btn_salvar.setEnabled(valor);
        btn_editar.setEnabled(valor);
    }

    private void popular(ModeloEquipamentosBeans M) {
        M.setId_categoria(getCategoria(cb_categoria));
        M.setId_marca(getMarca(cb_marca));
        M.setDescricao(txt_descricao.getText());
        M.setLargura(Double.valueOf(txt_largura.getText().replaceAll(",", ".")));
        M.setStatus(ch_status.isSelected());
    }

    private MarcaEquipamentosBeans getMarca(JComboBox<MarcaEquipamentosBeans> combo) {
        return (MarcaEquipamentosBeans) combo.getSelectedItem();
    }

    private CategoriaEquipamentosBeans getCategoria(JComboBox<CategoriaEquipamentosBeans> combo) {
        return (CategoriaEquipamentosBeans) combo.getSelectedItem();
    }

    private void setMarca(JComboBox<MarcaEquipamentosBeans> combo, Integer ID) {
        for (int i = 0; i < combo.getModel().getSize(); i++) {
            if (Objects.equals(combo.getItemAt(i).getID(), ID)) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }

    private void setCategoria(JComboBox<CategoriaEquipamentosBeans> combo, Integer ID) {
        for (int i = 0; i < combo.getModel().getSize(); i++) {
            if (Objects.equals(combo.getItemAt(i).getID(), ID)) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }

    private void editarModelo(ModeloEquipamentosBeans M) {
        M.setId_categoria(getCategoria(cb_categoria));
        M.setId_marca(getMarca(cb_marca));
        M.setDescricao(txt_descricao.getText());
        M.setLargura(Double.valueOf(txt_largura.getText().replaceAll(",", ".")));
        M.setStatus(ch_status.isSelected());
        M.setID(Integer.valueOf(txt_codigo.getText()));
    }

    public boolean verificar(ModeloEquipamentosBeans M) {
        if (txt_descricao.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha a descrição do Modelo", "Atenção", 0);
            return false;
        }
        return true;
    }

    private void preencherCampos(ModeloEquipamentosBeans M) {
        txt_codigo.setText(M.getID().toString());
        txt_descricao.setText(M.getDescricao());
        txt_largura.setText(M.getLargura().toString().replaceAll(",", "."));
        ch_status.setSelected(M.getStatus());
        setMarca(cb_marca, M.getId_marca().getID());
        setCategoria(cb_categoria, M.getId_categoria().getID());
    }
}
