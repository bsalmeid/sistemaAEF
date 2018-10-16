/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Almoxarifado.FrmCadAlmoxarif;
import Almoxarifado.CadItensAlmoxarifadoBeans;
import Almoxarifado.CategoriaAlmoxarif;
import Beans.CategoriaEquipamentosBeans;
import Beans.ModeloEquipamentosBeans;
import Beans.MarcaEquipamentosBeans;
import DAO.CadAlmoxarifadaoDAO;
import DAO.Diversas;
import DAO.DiversasHibernate;
import static GUI.Principal.listCategoriaAlmoxarif;
import TableModel.TableModelConsultaCadAlmoxarif;
import Utilitarios.CentralizarTabela;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import static GUI.Principal.listaModeloEquipamentos;
import static GUI.Principal.listaCategoriaEquipamentos;
import java.util.Objects;


public class FrmConsultaPecas extends javax.swing.JInternalFrame {

    List<MarcaEquipamentosBeans> listMarcasEquip;
    Diversas DiversasD;
    Integer ID_Fornecedor;
    public String CNPJ_Fornecedor;
    CadAlmoxarifadaoDAO CadD;
    CentralizarTabela Centralizar;
    TableModelConsultaCadAlmoxarif TbPecas;

    public FrmConsultaPecas() {
        initComponents();
        DiversasD = new Diversas();
        carregarCategorias();
        carregarMarcasEquip();
        carregarCaregoriasAlmoxarif();
        CadD = new CadAlmoxarifadaoDAO();
        Centralizar = new CentralizarTabela();
        carregarTabela();
        getRootPane().setDefaultButton(btn_consulta);
    }

    private void carregarMarcasEquip() {
        if (listMarcasEquip == null) {
            listMarcasEquip = DiversasHibernate.getListaMarca();
        }
        MarcaEquipamentosBeans m = new MarcaEquipamentosBeans(0, "-");
        cb_marca.addItem(m);
        for (MarcaEquipamentosBeans l : listMarcasEquip) {
            cb_marca.addItem(l);
        }
    }

    private void carregarCategorias() {
        if (listaCategoriaEquipamentos == null) {
            listaCategoriaEquipamentos = DiversasHibernate.getListaCategoria();
        }
        CategoriaEquipamentosBeans b = new CategoriaEquipamentosBeans();
        b.setID(0);
        b.setCategoria("-");
        b.setStatus(true);
        cb_categoria.addItem(b);
        for (CategoriaEquipamentosBeans list : listaCategoriaEquipamentos) {
            cb_categoria.addItem(list);
        }
    }

    private void carregarCaregoriasAlmoxarif() {
        if (listCategoriaAlmoxarif == null) {
            listCategoriaAlmoxarif = DiversasHibernate.getListaCategoriaAlmoxarifado();
        }
        cb_categoriaAlmox.addItem(new CategoriaAlmoxarif(0, "-"));
        for (CategoriaAlmoxarif l : listCategoriaAlmoxarif) {
            cb_categoriaAlmox.addItem(l);
        }

    }

    private void carregarModelos(Integer idCategoria, Integer idMarca) {
        if (listaModeloEquipamentos == null) {
            listaModeloEquipamentos = DiversasHibernate.getListaModeloInventario();
        }
        cb_modelo.removeAllItems();
        cb_modelo.addItem(new ModeloEquipamentosBeans(0, "-"));
        for (ModeloEquipamentosBeans list : listaModeloEquipamentos) {
            if (Objects.equals(list.getId_categoria().getID(), idCategoria) 
                    && Objects.equals(list.getId_marca().getID(), idMarca)) {
                cb_modelo.addItem(list);
            }
        }
    }

    private JTable carregarTabela() {
        tb_pecas.setModel(getTableModel());
        ((DefaultTableCellRenderer) tb_pecas.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        Centralizar.centralizarTabela(tb_pecas);

        tb_pecas.getColumnModel().getColumn(TbPecas.ID).setPreferredWidth(60);
        tb_pecas.getColumnModel().getColumn(TbPecas.ID).setMinWidth(50);
        tb_pecas.getColumnModel().getColumn(TbPecas.ID).setMaxWidth(70);

        tb_pecas.getColumnModel().getColumn(TbPecas.ORIGINAL).setPreferredWidth(70);
        tb_pecas.getColumnModel().getColumn(TbPecas.ORIGINAL).setMinWidth(70);
        tb_pecas.getColumnModel().getColumn(TbPecas.ORIGINAL).setMaxWidth(70);

        tb_pecas.getColumnModel().getColumn(TbPecas.MODELO).setPreferredWidth(100);
        tb_pecas.getColumnModel().getColumn(TbPecas.MODELO).setMinWidth(100);
        tb_pecas.getColumnModel().getColumn(TbPecas.MODELO).setMaxWidth(120);

        tb_pecas.getColumnModel().getColumn(TbPecas.CODIGO).setPreferredWidth(140);
        tb_pecas.getColumnModel().getColumn(TbPecas.CODIGO).setMinWidth(140);
        tb_pecas.getColumnModel().getColumn(TbPecas.CODIGO).setMaxWidth(180);

        return tb_pecas;
    }

    private TableModelConsultaCadAlmoxarif getTableModel() {
        if (TbPecas == null) {
            TbPecas = new TableModelConsultaCadAlmoxarif();
        }
        return TbPecas;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopup = new javax.swing.JPopupMenu();
        menu_editar = new javax.swing.JMenuItem();
        jXTaskPane1 = new org.jdesktop.swingx.JXTaskPane();
        jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        cb_categoriaAlmox = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        txt_descricao = new javax.swing.JTextField();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        ch_status = new javax.swing.JCheckBox();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        ch_nao = new javax.swing.JCheckBox();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        txt_fornecedor = new javax.swing.JTextField();
        btn_pesqFornecedor = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cb_categoria = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cb_modelo = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cb_marca = new javax.swing.JComboBox<>();
        btn_consulta = new javax.swing.JButton();
        ch_sim = new javax.swing.JCheckBox();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_pecas = new javax.swing.JTable();

        menu_editar.setText("Editar Cadastro");
        menu_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_editarActionPerformed(evt);
            }
        });
        jPopup.add(menu_editar);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Consulta de Cadastro");

        jXTaskPane1.setTitle("Opções de Pesquisa");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Categoria");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Descrição");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Status");

        ch_status.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_status.setSelected(true);
        ch_status.setText("Ativo");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Codigo");

        txt_codigo.setPreferredSize(new java.awt.Dimension(50, 20));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Código de Catalogo");

        ch_nao.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_nao.setSelected(true);
        ch_nao.setText("Não");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Fornecedor");

        txt_fornecedor.setEditable(false);
        txt_fornecedor.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_fornecedor.setPreferredSize(new java.awt.Dimension(100, 20));

        btn_pesqFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar_15_15.png"))); // NOI18N
        btn_pesqFornecedor.setPreferredSize(new java.awt.Dimension(30, 20));
        btn_pesqFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesqFornecedorActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Categoria");

        cb_categoria.setPreferredSize(new java.awt.Dimension(90, 20));
        cb_categoria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_categoriaItemStateChanged(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Modelo");

        cb_modelo.setBorder(null);
        cb_modelo.setPreferredSize(new java.awt.Dimension(80, 20));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Marca");

        cb_marca.setPreferredSize(new java.awt.Dimension(80, 20));
        cb_marca.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_marcaItemStateChanged(evt);
            }
        });

        btn_consulta.setText("Pesquisar");
        btn_consulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_consultaActionPerformed(evt);
            }
        });

        ch_sim.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_sim.setSelected(true);
        ch_sim.setText("Sim");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("ID");

        txt_id.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_categoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_marca, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_modelo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_consulta, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_codigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ch_nao, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ch_sim, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_fornecedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(btn_pesqFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_categoriaAlmox, 0, 170, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_descricao, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ch_status)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(cb_categoriaAlmox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txt_descricao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(ch_status)
                    .addComponent(jLabel7)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(txt_codigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(ch_nao)
                    .addComponent(jLabel6)
                    .addComponent(txt_fornecedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesqFornecedor, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(ch_sim))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_consulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_modelo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addComponent(cb_marca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10)
                    .addComponent(cb_categoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8))
                .addContainerGap())
        );

        jXTaskPane1.getContentPane().add(jPanel1);

        tb_pecas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tb_pecas.setCellSelectionEnabled(true);
        tb_pecas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_pecasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_pecas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXTaskPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jXTaskPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_pesqFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesqFornecedorActionPerformed
        frmCadFornecedores fornecedor = new frmCadFornecedores();
        this.getParent().add(fornecedor);
        fornecedor.setVisible(true);
        fornecedor.tb_fornecedores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    int index = fornecedor.tb_fornecedores.getSelectedRow();
                    ID_Fornecedor = (Integer) fornecedor.tb_fornecedores.getValueAt(index, 0);
                    txt_fornecedor.setText(fornecedor.tb_fornecedores.getValueAt(index, 1).toString());
                    CNPJ_Fornecedor = fornecedor.tb_fornecedores.getValueAt(index, 7).toString();
                    fornecedor.dispose();
                }
            }
        });
    }//GEN-LAST:event_btn_pesqFornecedorActionPerformed

    private void cb_categoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_categoriaItemStateChanged
        if (cb_marca.getSelectedIndex() > 0) {
            carregarModelos(getIdCategoria(cb_categoria), getIdMarca(cb_marca));
        }
    }//GEN-LAST:event_cb_categoriaItemStateChanged

    private void cb_marcaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_marcaItemStateChanged
        carregarModelos(getIdCategoria(cb_categoria), getIdMarca(cb_marca));
    }//GEN-LAST:event_cb_marcaItemStateChanged

    private void btn_consultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_consultaActionPerformed
        CadD.consultarCadastro(TbPecas, getSQLWhere());
    }//GEN-LAST:event_btn_consultaActionPerformed

    private void tb_pecasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_pecasMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            if (jPopup.isVisible() == true) {
                jPopup.setVisible(false);
            } else {
                jPopup.setVisible(true);
                jPopup.show(this, 0, 0);
                jPopup.setLocation(evt.getLocationOnScreen());
            }
        }
    }//GEN-LAST:event_tb_pecasMouseClicked

    private void menu_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_editarActionPerformed
        if (Principal.MenuCadPecas.isEnabled()) {
            Integer ID = (Integer) TbPecas.getValueAt(tb_pecas.getSelectedRow(), TbPecas.ID);
            if (ID > 0) {
                CadItensAlmoxarifadoBeans beans = CadD.getItemBeans(ID);
                if (beans != null) {
                    FrmCadAlmoxarif cadItem = new FrmCadAlmoxarif();
                    this.getParent().add(cadItem);
                    cadItem.setVisible(true);
                    cadItem.habilitarCampos();
                    cadItem.CadB = beans;
                    cadItem.preencherFormulario(beans);
                    cadItem.btn_editarPedido.setEnabled(true);
                    cadItem.btn_salvarPedido.setEnabled(false);
                    cadItem.btn_excluirPedido.setEnabled(false);
                    cadItem.btn_novo.setEnabled(false);
                }
            }
        } else {

        }
    }//GEN-LAST:event_menu_editarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_consulta;
    javax.swing.JButton btn_pesqFornecedor;
    private javax.swing.JComboBox<Beans.CategoriaEquipamentosBeans> cb_categoria;
    private javax.swing.JComboBox<CategoriaAlmoxarif> cb_categoriaAlmox;
    private javax.swing.JComboBox<MarcaEquipamentosBeans> cb_marca;
    private javax.swing.JComboBox<Beans.ModeloEquipamentosBeans> cb_modelo;
    public javax.swing.JCheckBox ch_nao;
    public javax.swing.JCheckBox ch_sim;
    private javax.swing.JCheckBox ch_status;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopup;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane1;
    private javax.swing.JMenuItem menu_editar;
    public javax.swing.JTable tb_pecas;
    public javax.swing.JTextField txt_codigo;
    private javax.swing.JTextField txt_descricao;
    public javax.swing.JTextField txt_fornecedor;
    private javax.swing.JTextField txt_id;
    // End of variables declaration//GEN-END:variables

    private Integer getIdCategoria(JComboBox<CategoriaEquipamentosBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return comboBox.getItemAt(comboBox.getSelectedIndex()).getID();
        }
        return 0;
    }

    private Integer getIdCategoriaAlmoxarifado(JComboBox<CategoriaAlmoxarif> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return comboBox.getItemAt(comboBox.getSelectedIndex()).getID();
        }
        return 0;
    }

    private Integer getIdModelo(JComboBox<ModeloEquipamentosBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return comboBox.getItemAt(comboBox.getSelectedIndex()).getID();
        }
        return 0;
    }

    private Integer getIdMarca(JComboBox<MarcaEquipamentosBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return comboBox.getItemAt(comboBox.getSelectedIndex()).getID();
        }
        return 0;
    }

    private String getSQLWhere() {
        String s = "";
        if (!txt_id.getText().equals("")) {
            s += " and ci.id = " + txt_id.getText();
        }
        if (cb_categoriaAlmox.getSelectedIndex() > 0) {
            s += " and ci.id_categoria = " + getIdCategoriaAlmoxarifado(cb_categoriaAlmox);
        }
        if (!txt_descricao.getText().equals("")) {
            s += " and ci.descricao like '%" + txt_descricao.getText() + "%'";
        }
        if (!txt_codigo.getText().equals("")) {
            s += " and cc.codigo like '%" + txt_codigo.getText() + "%'";
        }

        if (ch_nao.isSelected() & ch_sim.isSelected()) {
            s += "";
        } else if (ch_nao.isSelected()) {
            s += " and cc.codigo_catalogo = 0";
        } else if (ch_sim.isSelected()) {
            s += " and cc.codigo_catalogo = 1";
        }

        if (ch_status.isSelected()) {
            s += " and ci.status = 1";
        } else {
            s += " and ci.status = 0";
        }
        if (cb_categoria.getSelectedIndex() > 0) {
            s += " and ca.id_categoria_aplic = " + getIdCategoria(cb_categoria);
        }
        if (cb_marca.getSelectedIndex() > 0) {
            s += " and ca.id_marca_aplic = " + getIdMarca(cb_marca);
        }
        if (cb_modelo.getSelectedIndex() > 0) {
            s += " and ca.id_modelo_aplic = " + getIdModelo(cb_modelo);
        }

        if (!s.equals("")) {
            s = s.replaceFirst("and", "");
            s = " Where " + s;
        }
        return s;
    }

}
