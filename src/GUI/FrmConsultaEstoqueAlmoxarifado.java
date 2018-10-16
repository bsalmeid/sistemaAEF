/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Almoxarifado.CadAlmoxarifadoBeans;
import Almoxarifado.CategoriaAlmoxarif;
import Beans.CategoriaEquipamentosBeans;
import Beans.ModeloEquipamentosBeans;
import Beans.MarcaEquipamentosBeans;
import DAO.Diversas;
import DAO.DiversasHibernate;
import static GUI.Principal.listAlmoxarifado;
import static GUI.Principal.listCategoriaAlmoxarif;
import static GUI.frmLogin.UsuarioLogado;
import Icones.FormatarICO;
import TableModel.TableModelConsultaEstoqueAlmoxarifado;
import TableModel.TbConsultaEstoqueAlmoxarifado;
import Utilitarios.CentralizarTabela;
import Utilitarios.Conexao;
import com.toedter.calendar.JDateChooser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import static GUI.Principal.listaModeloEquipamentos;
import static GUI.Principal.listaCategoriaEquipamentos;

/**
 *
 * @author agroa
 */
public class FrmConsultaEstoqueAlmoxarifado extends javax.swing.JInternalFrame {

    CentralizarTabela Centralizar;
    Diversas DiversasD;
    List<MarcaEquipamentosBeans> listMarcasEquip;
    TableModelConsultaEstoqueAlmoxarifado TbEstoque;

    public FrmConsultaEstoqueAlmoxarifado() {
        initComponents();
        Centralizar = new CentralizarTabela();
        DiversasD = new Diversas();
        carregarCategorias();
        carregarMarcasEquip();
        carregarCaregoriasAlmoxarif();
        carregarAlmoxarifado();
        carregarTabela();
        getRootPane().setDefaultButton(btn_pesquisa);

    }

    private void carregarAlmoxarifado() {
        if (listAlmoxarifado == null) {
            listAlmoxarifado = DiversasD.ListAlmoxarifado(UsuarioLogado);
        }
        listAlmoxarifado.forEach((a) -> {
            cb_almoxarifado.addItem(a);
        });
    }

    private void carregarMarcasEquip() {
        if (listMarcasEquip == null) {
            listMarcasEquip = DiversasD.ListMarcaEquipamentos();
        }

        for (MarcaEquipamentosBeans l : listMarcasEquip) {
            cb_marca.addItem(l);
        }
    }

    private void carregarCategorias() {
        if (listaCategoriaEquipamentos == null) {
            listaCategoriaEquipamentos = DiversasD.ListCategoriaEquipmentos();
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
            listaModeloEquipamentos = DiversasD.ListModeloEquipmentos();
        }
        ModeloEquipamentosBeans b = new ModeloEquipamentosBeans();
        cb_modelo.removeAllItems();
        b.setID(0);
        b.setIDCategoria(0);
        b.setIDMarca(0);
        b.setCategoria("-");
        b.setMarca("-");
        b.setModelo("-");
        b.setStatus(true);
        cb_modelo.addItem(b);
        for (ModeloEquipamentosBeans list : listaModeloEquipamentos) {
            if (list.getIDCategoria() == idCategoria && list.getIDMarca() == idMarca) {
                cb_modelo.addItem(list);
            }
        }
    }

    private JTable carregarTabela() {
        tb_pecas.setModel(getTableModel());
        ((DefaultTableCellRenderer) tb_pecas.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        Centralizar.centralizarTabela(tb_pecas);

        tb_pecas.getColumnModel().getColumn(TbEstoque.ID).setPreferredWidth(80);

        tb_pecas.getColumnModel().getColumn(TbEstoque.CODIGO).setPreferredWidth(130);
        //tb_pecas.getColumnModel().getColumn(TbEstoque.CODIGO).setMinWidth(70);
        //tb_pecas.getColumnModel().getColumn(TbEstoque.CODIGO).setMaxWidth(70);

        tb_pecas.getColumnModel().getColumn(TbEstoque.DESCRICAO).setPreferredWidth(200);
        //tb_pecas.getColumnModel().getColumn(TbEstoque.DESCRICAO).setMinWidth(100);
        //tb_pecas.getColumnModel().getColumn(TbEstoque.DESCRICAO).setMaxWidth(120);

        tb_pecas.getColumnModel().getColumn(TbEstoque.ENTRADA_INTERVALO).setPreferredWidth(100);
        //tb_pecas.getColumnModel().getColumn(TbEstoque.ENTRADA_INTERVALO).setMinWidth(100);
        //tb_pecas.getColumnModel().getColumn(TbEstoque.ENTRADA_INTERVALO).setMaxWidth(120);

        tb_pecas.getColumnModel().getColumn(TbEstoque.SAIDA_INTERVALO).setPreferredWidth(100);
        //tb_pecas.getColumnModel().getColumn(TbEstoque.SAIDA_INTERVALO).setMinWidth(100);
        //tb_pecas.getColumnModel().getColumn(TbEstoque.SAIDA_INTERVALO).setMaxWidth(120);

        tb_pecas.getColumnModel().getColumn(TbEstoque.ESTOQUE).setPreferredWidth(100);
        //tb_pecas.getColumnModel().getColumn(TbEstoque.ESTOQUE).setMinWidth(140);
        //tb_pecas.getColumnModel().getColumn(TbEstoque.ESTOQUE).setMaxWidth(180);

        return tb_pecas;
    }

    private TableModelConsultaEstoqueAlmoxarifado getTableModel() {
        if (TbEstoque == null) {
            TbEstoque = new TableModelConsultaEstoqueAlmoxarifado();
        }
        return TbEstoque;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tb_pecas = new javax.swing.JTable();
        jXTaskPane1 = new org.jdesktop.swingx.JXTaskPane();
        jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        txt_dataInicial =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        cb_almoxarifado = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        txt_dataInicial1 =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        cb_categoriaAlmox = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        txt_descricao = new javax.swing.JTextField();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cb_categoria = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cb_marca = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cb_modelo = new javax.swing.JComboBox<>();
        btn_pesquisa = new javax.swing.JButton();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        cb_condEstoque = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Estoque do Almoxarifado");

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
        jScrollPane1.setViewportView(tb_pecas);

        jXTaskPane1.setTitle("Opções de Consulta");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Data");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Almoxarifado");

        cb_almoxarifado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_almoxarifadoItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("à");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("ID");

        txt_id.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Cat. Item");

        cb_categoriaAlmox.setPreferredSize(new java.awt.Dimension(120, 20));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Descrição");

        txt_descricao.setPreferredSize(new java.awt.Dimension(130, 20));
        txt_descricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_descricaoActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Codigo");

        txt_codigo.setPreferredSize(new java.awt.Dimension(130, 20));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Categoria");

        cb_categoria.setPreferredSize(new java.awt.Dimension(120, 20));
        cb_categoria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_categoriaItemStateChanged(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Marca");

        cb_marca.setPreferredSize(new java.awt.Dimension(130, 20));
        cb_marca.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_marcaItemStateChanged(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Modelo");

        cb_modelo.setBorder(null);
        cb_modelo.setPreferredSize(new java.awt.Dimension(130, 20));

        btn_pesquisa.setText("Pesquisar");
        btn_pesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisaActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Condição");

        cb_condEstoque.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Estoque > 0", "Estoque < 0", "Estoque = 0", "Todos" }));
        cb_condEstoque.setSelectedIndex(3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_marca, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_condEstoque, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_pesquisa)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_dataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_dataInicial1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_almoxarifado, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_categoriaAlmox, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_descricao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(txt_dataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txt_dataInicial1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cb_almoxarifado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(cb_categoriaAlmox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txt_codigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txt_descricao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel8)
                    .addComponent(cb_categoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(cb_marca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(cb_modelo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesquisa)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel11)
                        .addComponent(cb_condEstoque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
        );

        jXTaskPane1.getContentPane().add(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jXTaskPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXTaskPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb_almoxarifadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_almoxarifadoItemStateChanged

    }//GEN-LAST:event_cb_almoxarifadoItemStateChanged

    private void cb_categoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_categoriaItemStateChanged
        if (cb_marca.getSelectedIndex() > 0) {
            carregarModelos(getIdCategoria(cb_categoria), getIdMarca(cb_marca));
        }
    }//GEN-LAST:event_cb_categoriaItemStateChanged

    private void cb_marcaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_marcaItemStateChanged
        carregarModelos(getIdCategoria(cb_categoria), getIdMarca(cb_marca));
    }//GEN-LAST:event_cb_marcaItemStateChanged

    private void btn_pesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisaActionPerformed
        if (cb_almoxarifado.getSelectedIndex() != 0) {
            consultarEstoque(getIdAlmoxarifado(cb_almoxarifado), getWhere());
        } else {
            JOptionPane.showMessageDialog(null, "Selecione o Almoxarifado!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }//GEN-LAST:event_btn_pesquisaActionPerformed

    private void txt_descricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_descricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_descricaoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_pesquisa;
    private javax.swing.JComboBox<CadAlmoxarifadoBeans> cb_almoxarifado;
    private javax.swing.JComboBox<CategoriaEquipamentosBeans> cb_categoria;
    private javax.swing.JComboBox<CategoriaAlmoxarif> cb_categoriaAlmox;
    private javax.swing.JComboBox<String> cb_condEstoque;
    private javax.swing.JComboBox<MarcaEquipamentosBeans> cb_marca;
    private javax.swing.JComboBox<ModeloEquipamentosBeans> cb_modelo;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane1;
    private javax.swing.JTable tb_pecas;
    public javax.swing.JTextField txt_codigo;
    private com.toedter.calendar.JDateChooser txt_dataInicial;
    private com.toedter.calendar.JDateChooser txt_dataInicial1;
    private javax.swing.JTextField txt_descricao;
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

    public Integer getIdAlmoxarifado(JComboBox<CadAlmoxarifadoBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return comboBox.getItemAt(comboBox.getSelectedIndex()).getID();
        }
        return 0;
    }

    public void consultarEstoque(Integer IdAlmoxarifado, String WHERE) {
        Conexao.ReiniciarCon();
        String Select = "Select Distinct  ci.id as Item, cc.codigo as Codigo, ci.descricao as Descricao, \n"
                + "  IFNULL((SELECT cu.Descricao FROM cad_unidades cu Where cu.id = ci.id),'-') as Unidade,\n"
                + "   -- IFNULL((SELECT SUM(eai.quantidade) FROM entrada_almoxarifado_itens eai JOIN entrada_almoxarifado ea ON ea.id = eai.id_entrada WHERE ea.data BETWEEN '2018-03-01' AND '2018-03-31' and ea.id_almoxarifado = 1 AND eai.id_cadastro = ci.id) ,0.00) as TotalEnt, \n"
                + "   -- IFNULL((SELECT SUM(sai.quantidade) FROM saida_almoxarifado_itens sai JOIN saida_almoxarifado sa ON sa.id = sai.id_saida WHERE sa.data BETWEEN '2018-03-01' AND '2018-03-31' and sa.id_almoxarifado = 1 AND sai.id_item = ci.id),0.00) as TotalSaida,\n"
                + "@QE:=IFNULL((SELECT SUM(eai.quantidade) FROM entrada_almoxarifado_itens eai JOIN entrada_almoxarifado ea ON ea.id = eai.id_entrada WHERE ea.id_almoxarifado = ? AND eai.id_cadastro = ci.id) ,0.00) as QEnt, \n"
                + "@QS:=IFNULL((SELECT SUM(sai.quantidade) FROM saida_almoxarifado_itens sai JOIN saida_almoxarifado sa ON sa.id = sai.id_saida WHERE sa.id_almoxarifado = ? AND sai.id_item = ci.id),0.00) as QSaida,\n"
                + "Round((@QE - @QS),2) as Estoque\n"
                + "FROM cad_itens_almoxarif ci \n"
                + "	LEFT JOIN cad_itens_almox_codigos cc ON cc.id_item = ci.id\n"
                + "     LEFT JOIN cad_itens_almox_aplic ca ON ca.id_item = ci.id\n"
                + WHERE 
                ;
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(Select);
            st.setInt(1, IdAlmoxarifado);
            st.setInt(2, IdAlmoxarifado);
            ResultSet rs = st.executeQuery();
            TbEstoque.limpar();
            while (rs.next()) {
                TbConsultaEstoqueAlmoxarifado item = new TbConsultaEstoqueAlmoxarifado();
                item.setID(rs.getInt("Item"));
                item.setCodigo(rs.getString("Codigo"));
                item.setDescricao(rs.getString("Descricao"));
                item.setUnidade(rs.getString("Unidade"));
                item.setEntradaIntervalo(0.0);
                item.setSaidaIntervalo(0.0);
                item.setEstoque(rs.getDouble("Estoque"));
                TbEstoque.addBeans(item);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao Realizar Consulta Item!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }

    private String getWhere(){
        String s = "";
        if (cb_categoriaAlmox.getSelectedIndex() != 0){
            s += " and ci.id_categoria = " + getIdCategoriaAlmoxarifado(cb_categoriaAlmox);
        }
        if (cb_categoria.getSelectedIndex() != 0){
            s += " and ca.id_categoria_aplic = " + getIdCategoria(cb_categoria);
        }
        if (cb_marca.getSelectedIndex() != 0){
            s += " and ca.id_marca_aplic = " + getIdMarca(cb_marca);
        }
        if (cb_modelo.getSelectedIndex() != 0){
            s += " and ca.id_modelo_aplic = " + getIdModelo(cb_modelo);
        }
        if (!txt_codigo.getText().equals("")){
            s += " and cc.codigo like '%" + txt_codigo.getText() + "%'";
        }
        if (!txt_descricao.getText().equals("")){
            s += " and ci.descricao like '%" + txt_descricao.getText() + "%'";
        }
        if (!txt_id.getText().equals("")){
            s += " and ci.id = " + txt_id.getText();
        }
        if (!s.equals("")){
            s = " WHERE " + s.replaceFirst("and", "") + " ";
        }
        if (cb_condEstoque.getSelectedIndex() != 3){
            s += getCondicaoEstoque();
        }
        return s;
    }
    
    private String getCondicaoEstoque() {
        String s = "";
        if (cb_condEstoque.getSelectedItem().equals("Estoque > 0")) {
            s = " GROUP BY Item, Codigo\n  HAVING Estoque > 0";
        } else if (cb_condEstoque.getSelectedItem().equals("Estoque < 0")) {
            s = " GROUP BY Item, Codigo\n HAVING Estoque < 0 ";
        } else if (cb_condEstoque.getSelectedItem().equals("Estoque = 0")) {
            s = " GROUP BY Item, Codigo\n HAVING Estoque = 0";
        } else if (cb_condEstoque.getSelectedItem().equals("Todos")) {
            s = " GROUP BY Item, Codigo";
        }
        return s;
    }
    
        
        
        
        
        
}
