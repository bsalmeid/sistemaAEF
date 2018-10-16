/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Almoxarifado;

import Beans.CadUnidadesBeans;
import Beans.CategoriaEquipamentosBeans;
import Beans.FornecedoresBeans;
import Beans.ModeloEquipamentosBeans;
import Beans.MarcaEquipamentosBeans;
import DAO.CadAlmoxarifadaoDAO;
import DAO.Diversas;
import DAO.DiversasHibernate;
import GUI.frmCadFornecedores;
import static GUI.Principal.listCategoriaAlmoxarif;
import static GUI.Principal.listUnidades;
import Icones.FormatarICO;
import TableModel.TableModelCadAlmoxarifAplic;
import TableModel.TableModelCadAlmoxarifCod;
import Utilitarios.CentralizarTabela;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import static GUI.Principal.listaModeloEquipamentos;
import static GUI.Principal.listaCategoriaEquipamentos;
import Utilitarios.ValidarPermissoes;
import java.util.Objects;

/**
 *
 * @author agroa
 */
public class FrmCadAlmoxarif extends javax.swing.JInternalFrame {

    Diversas DiversasD;
    List<MarcaEquipamentosBeans> listMarcasEquip;
    public TableModelCadAlmoxarifAplic TbAplic;
    CentralizarTabela Centralizar;
    public TableModelCadAlmoxarifCod TbCodigo;
    public Integer ID_Fornecedor;
    public String CNPJ_Fornecedor;
    public CadItensAlmoxarifadoBeans CadB;
    CadAlmoxarifadaoDAO CadD;
    public Integer ID_Item;
    FornecedoresBeans FornB;

    public FrmCadAlmoxarif() {
        initComponents();
        DiversasD = new Diversas();
        CadB = new CadItensAlmoxarifadoBeans();
        CadD = new CadAlmoxarifadaoDAO();
        Centralizar = new CentralizarTabela();
        ID_Fornecedor = 0;
        ID_Item = 0;
        carregarCategorias();
        carregarMarcasEquip();
        carregarCaregoriasAlmoxarif();
        carregarTbAplic();
        carregarTbCodigo();
        carregarUnidades();
    }

    private void carregarMarcasEquip() {
        if (listMarcasEquip == null) {
            listMarcasEquip = DiversasHibernate.getListaMarca();
        }
        cb_marca.addItem(new MarcaEquipamentosBeans(0, "-"));
        for (MarcaEquipamentosBeans l : listMarcasEquip) {
            cb_marca.addItem(l);
        }
    }

    private void carregarCategorias() {
        if (listaCategoriaEquipamentos == null) {
            listaCategoriaEquipamentos = DiversasHibernate.getListaCategoria();
        }
        cb_categoria.addItem(new CategoriaEquipamentosBeans(0, "-"));
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

    private void carregarUnidades() {
        if (listUnidades == null) {
            listUnidades = DiversasHibernate.getUnidades();
        }
        cb_unidade.addItem(new CadUnidadesBeans(0, "-"));
        for (CadUnidadesBeans u : listUnidades) {
            cb_unidade.addItem(u);
        }
    }

    private void carregarModelos(Integer idCategoria, Integer idMarca, JComboBox<ModeloEquipamentosBeans> combo) {
        if (listaModeloEquipamentos == null) {
            listaModeloEquipamentos = DiversasHibernate.getListaModeloInventario();
        }
        ModeloEquipamentosBeans b = new ModeloEquipamentosBeans(0, "-");
        cb_modelo.removeAllItems();
        cb_modelo.addItem(b);
        for (ModeloEquipamentosBeans list : listaModeloEquipamentos) {
            if (Objects.equals(list.getId_categoria().getID(), idCategoria) && Objects.equals(list.getId_marca().getID(), idMarca)) {
                combo.addItem(list);
            }
        }
    }

    private JTable carregarTbAplic() {
        tb_aplic.setModel(getTbModelAplic());
        ((DefaultTableCellRenderer) tb_aplic.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        Centralizar.centralizarTabela(tb_aplic);

        tb_aplic.getColumnModel().getColumn(TbAplic.ID_CATEGORIA_APLIC).setMaxWidth(0);
        tb_aplic.getColumnModel().getColumn(TbAplic.ID_CATEGORIA_APLIC).setMinWidth(0);
        tb_aplic.getColumnModel().getColumn(TbAplic.ID_CATEGORIA_APLIC).setPreferredWidth(0);

        tb_aplic.getColumnModel().getColumn(TbAplic.ID_CATEGORIA_ITEM).setMaxWidth(0);
        tb_aplic.getColumnModel().getColumn(TbAplic.ID_CATEGORIA_ITEM).setMinWidth(0);
        tb_aplic.getColumnModel().getColumn(TbAplic.ID_CATEGORIA_ITEM).setPreferredWidth(0);

        tb_aplic.getColumnModel().getColumn(TbAplic.ID_ITEM).setMaxWidth(0);
        tb_aplic.getColumnModel().getColumn(TbAplic.ID_ITEM).setMinWidth(0);
        tb_aplic.getColumnModel().getColumn(TbAplic.ID_ITEM).setPreferredWidth(0);

        tb_aplic.getColumnModel().getColumn(TbAplic.ID_MARCA_APLIC).setMaxWidth(0);
        tb_aplic.getColumnModel().getColumn(TbAplic.ID_MARCA_APLIC).setMinWidth(0);
        tb_aplic.getColumnModel().getColumn(TbAplic.ID_MARCA_APLIC).setPreferredWidth(0);

        tb_aplic.getColumnModel().getColumn(TbAplic.ID_MODELO_APLIC).setMaxWidth(0);
        tb_aplic.getColumnModel().getColumn(TbAplic.ID_MODELO_APLIC).setMinWidth(0);
        tb_aplic.getColumnModel().getColumn(TbAplic.ID_MODELO_APLIC).setPreferredWidth(0);

        return tb_aplic;
    }

    private JTable carregarTbCodigo() {
        tb_codigo.setModel(getTbModelCodigo());
        ((DefaultTableCellRenderer) tb_codigo.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        Centralizar.centralizarTabela(tb_codigo);

        for (int c = 0; c < tb_codigo.getColumnCount(); c++) {
            if (c == TbCodigo.ID_FORNECEDOR || c == TbCodigo.ID || c == TbCodigo.ID_ITEM) {
                //          tb_codigo.getColumnModel().getColumn(c).setMaxWidth(0);
                //          tb_codigo.getColumnModel().getColumn(c).setMinWidth(0);
                //         tb_codigo.getColumnModel().getColumn(c).setPreferredWidth(0);
            }
        }
        tb_codigo.getColumnModel().getColumn(TbCodigo.CODIGO_CATALOGO).setPreferredWidth(50);
        return tb_codigo;
    }

    private TableModelCadAlmoxarifAplic getTbModelAplic() {
        if (TbAplic == null) {
            TbAplic = new TableModelCadAlmoxarifAplic();
        }
        return TbAplic;
    }

    private TableModelCadAlmoxarifCod getTbModelCodigo() {
        if (TbCodigo == null) {
            TbCodigo = new TableModelCadAlmoxarifCod();
        }
        return TbCodigo;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btn_novo = new javax.swing.JButton();
        btn_salvarPedido = new javax.swing.JButton();
        btn_editarPedido = new javax.swing.JButton();
        btn_excluirPedido = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        cb_categoriaAlmox = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        txt_descricao = new javax.swing.JTextField();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        cb_unidade = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        txt_obs = new javax.swing.JTextField();
        ch_status = new javax.swing.JCheckBox();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tb_aplic = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        cb_categoria = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cb_modelo = new javax.swing.JComboBox<>();
        btn_addAplic = new javax.swing.JButton();
        btn_delAplic = new javax.swing.JButton();
        btn_editarAplic = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        cb_marca = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        ch_nao = new javax.swing.JCheckBox();
        ch_sim = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_codigo = new javax.swing.JTable();
        btn_delCodigo = new javax.swing.JButton();
        btn_editarCodigo = new javax.swing.JButton();
        btn_addCodigo = new javax.swing.JButton();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        txt_fornecedor = new javax.swing.JTextField();
        btn_pesqFornecedor = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro de Itens de Almoxarifado");
        setFont(new java.awt.Font("Agency FB", 1, 10)); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setPreferredSize(new java.awt.Dimension(318, 86));

        btn_novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/inserir-32.png"))); // NOI18N
        btn_novo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_novo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novoActionPerformed(evt);
            }
        });

        btn_salvarPedido.setBackground(new java.awt.Color(255, 255, 255));
        btn_salvarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/salvar_32_32.png"))); // NOI18N
        btn_salvarPedido.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_salvarPedido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_salvarPedido.setEnabled(false);
        btn_salvarPedido.setMaximumSize(new java.awt.Dimension(45, 45));
        btn_salvarPedido.setMinimumSize(new java.awt.Dimension(45, 45));
        btn_salvarPedido.setPreferredSize(new java.awt.Dimension(45, 45));
        btn_salvarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salvarPedidoActionPerformed(evt);
            }
        });

        btn_editarPedido.setBackground(new java.awt.Color(255, 255, 255));
        btn_editarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/editar_32_32.png"))); // NOI18N
        btn_editarPedido.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_editarPedido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_editarPedido.setEnabled(false);
        btn_editarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarPedidoActionPerformed(evt);
            }
        });

        btn_excluirPedido.setBackground(new java.awt.Color(255, 255, 255));
        btn_excluirPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/deletar_32_32.png"))); // NOI18N
        btn_excluirPedido.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_excluirPedido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_excluirPedido.setEnabled(false);
        btn_excluirPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirPedidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_salvarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_editarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_excluirPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_salvarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_editarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_excluirPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Categoria");

        cb_categoriaAlmox.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Descrição");

        txt_descricao.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_descricao.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Unid");

        cb_unidade.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Observação");

        txt_obs.setEnabled(false);

        ch_status.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_status.setSelected(true);
        ch_status.setText("Ativo");
        ch_status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ch_statusActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Status");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_obs, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel3)
                        .addGap(6, 6, 6)
                        .addComponent(ch_status, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_categoriaAlmox, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_unidade, 0, 157, Short.MAX_VALUE)))
                .addGap(3, 3, 3))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txt_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cb_categoriaAlmox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel11)
                    .addComponent(cb_unidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(ch_status)
                    .addComponent(jLabel3)
                    .addComponent(txt_obs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setPreferredSize(new java.awt.Dimension(516, 220));

        tb_aplic.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_aplic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_aplicMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tb_aplic);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Categoria");

        cb_categoria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_categoriaItemStateChanged(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Modelo");

        cb_modelo.setBorder(null);

        btn_addAplic.setText("Add");
        btn_addAplic.setEnabled(false);
        btn_addAplic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addAplicActionPerformed(evt);
            }
        });

        btn_delAplic.setText("Del");
        btn_delAplic.setEnabled(false);
        btn_delAplic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delAplicActionPerformed(evt);
            }
        });

        btn_editarAplic.setText("Editar");
        btn_editarAplic.setEnabled(false);
        btn_editarAplic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarAplicActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Marca");

        cb_marca.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_marcaItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_marca, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_modelo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_addAplic, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_editarAplic, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_delAplic, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel8)
                    .addComponent(cb_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(cb_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_addAplic)
                    .addComponent(btn_editarAplic)
                    .addComponent(btn_delAplic)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(cb_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setPreferredSize(new java.awt.Dimension(516, 220));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Codigo");

        txt_codigo.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Código de Catalogo");

        buttonGroup1.add(ch_nao);
        ch_nao.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_nao.setText("Não");

        buttonGroup1.add(ch_sim);
        ch_sim.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_sim.setText("Sim");

        tb_codigo.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_codigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_codigoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_codigo);

        btn_delCodigo.setText("Del");
        btn_delCodigo.setEnabled(false);
        btn_delCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delCodigoActionPerformed(evt);
            }
        });

        btn_editarCodigo.setText("Editar");
        btn_editarCodigo.setEnabled(false);
        btn_editarCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarCodigoActionPerformed(evt);
            }
        });

        btn_addCodigo.setText("Add");
        btn_addCodigo.setEnabled(false);
        btn_addCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addCodigoActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Fornecedor");

        btn_pesqFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar pequeno.png"))); // NOI18N
        btn_pesqFornecedor.setPreferredSize(new java.awt.Dimension(30, 25));
        btn_pesqFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesqFornecedorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_codigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ch_nao)
                        .addGap(10, 10, 10)
                        .addComponent(ch_sim))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_fornecedor)
                        .addGap(0, 0, 0)
                        .addComponent(btn_pesqFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_addCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_editarCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_delCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(ch_sim)
                    .addComponent(ch_nao)
                    .addComponent(jLabel5)
                    .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6)
                    .addComponent(txt_fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesqFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_addCodigo)
                    .addComponent(btn_editarCodigo)
                    .addComponent(btn_delCodigo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1075, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)))
        );

        setBounds(0, 0, 1103, 404);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_pesqFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesqFornecedorActionPerformed
        frmCadFornecedores fornecedor = new frmCadFornecedores();
        this.getParent().add(fornecedor);
        fornecedor.btn_novo1.setEnabled(false);
        fornecedor.getRootPane().setDefaultButton(fornecedor.btn_pesquisarCompra);
        fornecedor.setVisible(true);
        fornecedor.tb_fornecedores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    int index = fornecedor.tb_fornecedores.getSelectedRow();
                    ID_Fornecedor = (Integer) fornecedor.tb_fornecedores.getValueAt(index, 0);
                    FornB = DiversasHibernate.getFornecedor(ID_Fornecedor);
                    txt_fornecedor.setText(fornecedor.tb_fornecedores.getValueAt(index, 1).toString());
                    CNPJ_Fornecedor = fornecedor.tb_fornecedores.getValueAt(index, 7).toString();
                    fornecedor.dispose();
                }
            }
        });
    }//GEN-LAST:event_btn_pesqFornecedorActionPerformed

    private void cb_categoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_categoriaItemStateChanged
        if (cb_marca.getSelectedIndex() > 0) {
            carregarModelos(getIdCategoria(cb_categoria), getIdMarca(cb_marca), cb_modelo);
        }

    }//GEN-LAST:event_cb_categoriaItemStateChanged

    private void cb_marcaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_marcaItemStateChanged
        carregarModelos(getIdCategoria(cb_categoria), getIdMarca(cb_marca), cb_modelo);
    }//GEN-LAST:event_cb_marcaItemStateChanged

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed
        btn_salvarPedido.setEnabled(true);
        btn_editarPedido.setEnabled(false);
        btn_excluirPedido.setEnabled(false);
        CadB = new CadItensAlmoxarifadoBeans();
        FornB = null;
        habilitarCampos();
        limparCampos();
    }//GEN-LAST:event_btn_novoActionPerformed

    private void btn_salvarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarPedidoActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(null, "Deseja Salvar esta Ordem de Serviço?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION) {
            popularBeansCad(CadB);
            if (verificarBeans(CadB) && ValidarPermissoes.validarPermissaoInsert(FrmCadAlmoxarif.class.getSimpleName())) {
                if (CadD.CadastrarItem(CadB)) {
                    desabilitarCampos();
                    limparCampos();
                }
            }
        }
    }//GEN-LAST:event_btn_salvarPedidoActionPerformed

    private void btn_editarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarPedidoActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja Editar este Item?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION) {
            popularBeansCad(CadB);
            if (verificarBeans(CadB) && ValidarPermissoes.validarPermissaoUpdate(FrmCadAlmoxarif.class.getSimpleName())) {
                if (CadD.EditarItem(CadB)) {
                    limparCampos();
                    desabilitarCampos();
                }
            }
        }
    }//GEN-LAST:event_btn_editarPedidoActionPerformed

    private void btn_excluirPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirPedidoActionPerformed

        int excluirSaidaInsumos = JOptionPane.showConfirmDialog(null, "", "Atenção", JOptionPane.YES_NO_OPTION);
        if (excluirSaidaInsumos == JOptionPane.YES_OPTION) {

        }

    }//GEN-LAST:event_btn_excluirPedidoActionPerformed

    private void btn_addAplicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addAplicActionPerformed
        CadItensAlmoxAplicacao aplic = new CadItensAlmoxAplicacao();
        aplic.setId_item(CadB);
        aplic.setId_categoria_aplic(getCategoriaAplicacao(cb_categoria));
        aplic.setId_marca_aplic(getMarca(cb_marca));
        aplic.setId_modelo_aplic(getModelo(cb_modelo));
        aplic.setMarca(cb_marca.getSelectedItem().toString());
        aplic.setModelo(cb_modelo.getSelectedItem().toString());
        if (verificaBeansAplicacao(aplic)) {
            TbAplic.addBeans(aplic);
        } else {
            JOptionPane.showMessageDialog(null, "Todos os campos da aplicação devem estar preenchidos!", "Erro", 0, FormatarICO.ICObtnSair());
        }

    }//GEN-LAST:event_btn_addAplicActionPerformed

    private void btn_delAplicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delAplicActionPerformed
        int index = tb_aplic.getSelectedRow();
        CadItensAlmoxAplicacao aplic = (CadItensAlmoxAplicacao) TbAplic.getBeans(index);
        if (aplic.getID() == null) {
            TbAplic.excluirLinha(index);
        } else {
            int excluirCodigo = JOptionPane.showConfirmDialog(null, "Este Procedimento irá excluir PERMANENTEMENTE esta Aplicação, Deseja Continuar?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (excluirCodigo == JOptionPane.YES_OPTION) {
                CadD.DeletarAplicacao(aplic);
                TbAplic.excluirLinha(index);
            }
        }
        btn_delAplic.setEnabled(false);
        btn_editarAplic.setEnabled(false);
        btn_addAplic.setEnabled(true);
    }//GEN-LAST:event_btn_delAplicActionPerformed

    private void tb_aplicMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_aplicMouseClicked
        if (evt.getClickCount() == 2) {
            btn_delAplic.setEnabled(true);
            btn_editarAplic.setEnabled(true);
            btn_addAplic.setEnabled(false);
            preencherCamposAplic(TbAplic.getBeans(tb_aplic.getSelectedRow()));
        } else {
            btn_delAplic.setEnabled(false);
            btn_editarAplic.setEnabled(false);
            btn_addAplic.setEnabled(true);
            limparCamposAplic();
        }
    }//GEN-LAST:event_tb_aplicMouseClicked

    private void btn_editarAplicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarAplicActionPerformed
        int index = tb_aplic.getSelectedRow();
        CadItensAlmoxAplicacao aplic = TbAplic.getBeans(index);
        aplic.setId_categoria_aplic(getCategoriaAplicacao(cb_categoria));
        aplic.setId_marca_aplic(getMarca(cb_marca));
        aplic.setId_modelo_aplic(getModelo(cb_modelo));
        if (verificaBeansAplicacao(aplic)) {
            TbAplic.setBeans(aplic, index);
            btn_delAplic.setEnabled(false);
            btn_editarAplic.setEnabled(false);
            btn_addAplic.setEnabled(true);
            limparCamposAplic();
        }
    }//GEN-LAST:event_btn_editarAplicActionPerformed

    private void btn_addCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addCodigoActionPerformed
        CadItensAlmoxCodigos beans = new CadItensAlmoxCodigos();
        beans.setId_item(CadB);
        beans.setCodigoCatalogo(ch_sim.isSelected());
        beans.setCodigo(txt_codigo.getText());
        beans.setId_fornecedor(FornB);
        beans.setID_Fornecedor(ID_Fornecedor);
        beans.setCNPJ_Fornecedor(CNPJ_Fornecedor);
        beans.setFornecedor(txt_fornecedor.getText());
        if (verificarBeansCodigo()) {
            TbCodigo.addBeans(beans);
        }
    }//GEN-LAST:event_btn_addCodigoActionPerformed

    private void tb_codigoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_codigoMouseClicked
        int index = tb_codigo.getSelectedRow();
        if (evt.getClickCount() == 2) {
            btn_delCodigo.setEnabled(true);
            btn_editarCodigo.setEnabled(true);
            btn_addCodigo.setEnabled(false);
            preencherCamposCodigo(TbCodigo.getBeans(index));
        } else {
            limparCamposCodigo();
            btn_delCodigo.setEnabled(false);
            btn_editarCodigo.setEnabled(false);
            btn_addCodigo.setEnabled(true);
        }
    }//GEN-LAST:event_tb_codigoMouseClicked

    private void btn_delCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delCodigoActionPerformed
        int index = tb_codigo.getSelectedRow();
        CadItensAlmoxCodigos codigo = (CadItensAlmoxCodigos) TbCodigo.getBeans(index);
        if (codigo.getID() == null) {
            TbCodigo.excluirLinha(index);
            limparCamposCodigo();
        } else {
            int excluirCodigo = JOptionPane.showConfirmDialog(null, "Este Procedimento irá excluir PERMANENTEMENTE este Código, Deseja Continuar?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (excluirCodigo == JOptionPane.YES_OPTION) {
                CadD.DeletarCodigo(codigo);
                TbCodigo.excluirLinha(index);
            }
        }
        btn_delCodigo.setEnabled(false);
        btn_editarCodigo.setEnabled(false);
        btn_addCodigo.setEnabled(true);
    }//GEN-LAST:event_btn_delCodigoActionPerformed

    private void btn_editarCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarCodigoActionPerformed
        int index = tb_codigo.getSelectedRow();
        TbCodigo.setValueAt(txt_codigo.getText(), index, TbCodigo.CODIGO);
        TbCodigo.setValueAt(ID_Fornecedor, index, TbCodigo.ID_FORNECEDOR);
        TbCodigo.setValueAt(CNPJ_Fornecedor, index, TbCodigo.CNPJ_FORNECEDOR);
        TbCodigo.setValueAt(txt_fornecedor.getText(), index, TbCodigo.FORNECEDOR);
        TbCodigo.setValueAt(ch_sim.isSelected(), index, TbCodigo.CODIGO_CATALOGO);
        btn_editarCodigo.setEnabled(false);
        btn_delCodigo.setEnabled(false);
        btn_addCodigo.setEnabled(true);
    }//GEN-LAST:event_btn_editarCodigoActionPerformed

    private void ch_statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ch_statusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ch_statusActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_addAplic;
    private javax.swing.JButton btn_addCodigo;
    private javax.swing.JButton btn_delAplic;
    private javax.swing.JButton btn_delCodigo;
    private javax.swing.JButton btn_editarAplic;
    private javax.swing.JButton btn_editarCodigo;
    public javax.swing.JButton btn_editarPedido;
    public javax.swing.JButton btn_excluirPedido;
    public javax.swing.JButton btn_novo;
    javax.swing.JButton btn_pesqFornecedor;
    public javax.swing.JButton btn_salvarPedido;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<CategoriaEquipamentosBeans> cb_categoria;
    private javax.swing.JComboBox<CategoriaAlmoxarif> cb_categoriaAlmox;
    private javax.swing.JComboBox<MarcaEquipamentosBeans> cb_marca;
    private javax.swing.JComboBox<ModeloEquipamentosBeans> cb_modelo;
    private javax.swing.JComboBox<CadUnidadesBeans> cb_unidade;
    private javax.swing.JCheckBox ch_nao;
    private javax.swing.JCheckBox ch_sim;
    private javax.swing.JCheckBox ch_status;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable tb_aplic;
    private javax.swing.JTable tb_codigo;
    public javax.swing.JTextField txt_codigo;
    public javax.swing.JTextField txt_descricao;
    public javax.swing.JTextField txt_fornecedor;
    private javax.swing.JTextField txt_obs;
    // End of variables declaration//GEN-END:variables

    private void popularBeansCad(CadItensAlmoxarifadoBeans CadB) {
        CadB.setId_categoria(getCategoriaAlmoxarifado(cb_categoriaAlmox));
        CadB.setIDCategoria(getIdCategoriaAlmoxarifado(cb_categoriaAlmox));
        CadB.setDescricao(txt_descricao.getText());
        CadB.setId_unidade(getUnidade(cb_unidade));
        CadB.setIDUnidade(getIdUnidade(cb_unidade));
        CadB.setStatus(ch_status.isSelected());
        CadB.setObservacao(txt_obs.getText());
        CadB.setListaAplicacao(TbAplic.getLista());
        CadB.setListaCodigo(TbCodigo.getLista());
    }

    private Boolean verificaBeansAplicacao(CadItensAlmoxAplicacao beans) {
        if (beans.getId_categoria_aplic() == null) {
            JOptionPane.showMessageDialog(null, "Verifiquqe o campo Categoria da Aplicação!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (beans.getId_marca_aplic() == null) {
            JOptionPane.showMessageDialog(null, "Verifiquqe o campo Marca da Aplicação!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (beans.getId_modelo_aplic() == null) {
            JOptionPane.showMessageDialog(null, "Verifiquqe o campo Modelo da Aplicação", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }

        return true;
    }

    private boolean verificarBeans(CadItensAlmoxarifadoBeans beans) {
        if (beans.getId_categoria() == null) {
            JOptionPane.showMessageDialog(null, "Verifiquqe o campo Categoria!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (beans.getId_unidade() == null) {
            JOptionPane.showMessageDialog(null, "Verifiquqe o campo Unidade!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (beans.getDescricao().equals("") || beans.getDescricao() == null) {
            JOptionPane.showMessageDialog(null, "Verifiquqe o campo categoria!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (beans.getListaAplicacao().isEmpty()) {
            int cadastrar = JOptionPane.showConfirmDialog(null, "Este Item não possuí nenhuma aplicação, deseja continuar?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (cadastrar == JOptionPane.NO_OPTION) {
                return false;
            }
        }
        if (beans.getListaCodigo().isEmpty()) {
            int cadastrar = JOptionPane.showConfirmDialog(null, "Este Item não possuí nenhum código, deseja continuar?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (cadastrar == JOptionPane.NO_OPTION) {
                return false;
            }
        }
        return true;
    }

    private Boolean verificarBeansCodigo() {
        if (txt_codigo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifiquqe o campo Codigo!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (ch_sim.isSelected() == false && ch_nao.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Marque a Opção de Código de Catalogo!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (ch_nao.isSelected() == true && FornB == null) {
            JOptionPane.showMessageDialog(null, "Caso Código não seja de Catalogo, o Fornecedor é Obrigatório!!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }

    private CategoriaEquipamentosBeans getCategoriaAplicacao(JComboBox<CategoriaEquipamentosBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return (CategoriaEquipamentosBeans) comboBox.getSelectedItem();
        }
        return null;
    }

    private Integer getIdCategoria(JComboBox<CategoriaEquipamentosBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return comboBox.getItemAt(comboBox.getSelectedIndex()).getID();
        }
        return 0;
    }

    private CadUnidadesBeans getUnidade(JComboBox<CadUnidadesBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return (CadUnidadesBeans) comboBox.getSelectedItem();
        }
        return null;
    }

    private Integer getIdUnidade(JComboBox<CadUnidadesBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return comboBox.getItemAt(comboBox.getSelectedIndex()).getID();
        }
        return 0;
    }

    private CategoriaAlmoxarif getCategoriaAlmoxarifado(JComboBox<CategoriaAlmoxarif> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return (CategoriaAlmoxarif) comboBox.getSelectedItem();
        }
        return null;
    }

    private Integer getIdCategoriaAlmoxarifado(JComboBox<CategoriaAlmoxarif> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return comboBox.getItemAt(comboBox.getSelectedIndex()).getID();
        }
        return 0;
    }

    private ModeloEquipamentosBeans getModelo(JComboBox<ModeloEquipamentosBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return (ModeloEquipamentosBeans) comboBox.getSelectedItem();
        }
        return null;
    }

    private MarcaEquipamentosBeans getMarca(JComboBox<MarcaEquipamentosBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return (MarcaEquipamentosBeans) comboBox.getSelectedItem();
        }
        return null;
    }

    private Integer getIdMarca(JComboBox<MarcaEquipamentosBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return comboBox.getItemAt(comboBox.getSelectedIndex()).getID();
        }
        return 0;
    }

    private void setComboBoxModelo(JComboBox<ModeloEquipamentosBeans> comboBox, Integer ID) {
        for (int i = 0; i < comboBox.getModel().getSize(); i++) {
            if (Objects.equals(comboBox.getModel().getElementAt(i).getID(), ID)) {
                comboBox.setSelectedIndex(i);
                break;
            }
        }
    }

    private void setComboBoxMarca(JComboBox<MarcaEquipamentosBeans> comboBox, Integer ID) {
        for (int i = 0; i < comboBox.getModel().getSize(); i++) {
            if (Objects.equals(comboBox.getModel().getElementAt(i).getID(), ID)) {
                comboBox.setSelectedIndex(i);
                break;
            }
        }
    }

    private void setComboBoxCategoriaAplic(JComboBox<CategoriaEquipamentosBeans> comboBox, Integer ID) {
        for (int i = 0; i < comboBox.getModel().getSize(); i++) {
            if (Objects.equals(comboBox.getModel().getElementAt(i).getID(), ID)) {
                comboBox.setSelectedIndex(i);
                break;
            }
        }
    }

    private void setComboBoxCategoria(JComboBox<CategoriaAlmoxarif> comboBox, Integer ID) {
        for (int i = 0; i < comboBox.getModel().getSize(); i++) {
            if (Objects.equals(comboBox.getModel().getElementAt(i).getID(), ID)) {
                comboBox.setSelectedIndex(i);
                break;
            }
        }
    }

    private void setComboBoxUnidade(JComboBox<CadUnidadesBeans> comboBox, Integer ID) {
        for (int i = 0; i < comboBox.getModel().getSize(); i++) {
            if (Objects.equals(comboBox.getItemAt(i).getID(), ID)) {
                comboBox.setSelectedIndex(i);
                break;
            }
        }
    }

    public void habilitarCampos() {
        cb_categoria.setEnabled(true);
        cb_categoriaAlmox.setEnabled(true);
        cb_unidade.setEnabled(true);
        txt_descricao.setEnabled(true);
        txt_obs.setEnabled(true);
        txt_codigo.setEnabled(true);
        btn_pesqFornecedor.setEnabled(true);
        btn_salvarPedido.setEnabled(true);
        btn_editarPedido.setEnabled(false);
        btn_excluirPedido.setEnabled(false);
        btn_addCodigo.setEnabled(true);
        btn_addAplic.setEnabled(true);
    }

    private void limparCampos() {
        cb_categoriaAlmox.setSelectedIndex(0);
        txt_descricao.setText("");
        txt_obs.setText("");
        ch_status.setSelected(true);
        txt_codigo.setText("");
        txt_fornecedor.setText("");
        ID_Fornecedor = 0;
        CNPJ_Fornecedor = "";
        ch_nao.setSelected(false);
        ch_sim.setSelected(false);
        cb_categoria.setSelectedItem(0);
        cb_marca.setSelectedIndex(0);
        cb_modelo.setSelectedIndex(0);
        cb_unidade.setSelectedIndex(0);
        TbAplic.limpar();
        TbCodigo.limpar();
    }

    private void limparCamposCodigo() {
        txt_codigo.setText("");
        txt_fornecedor.setText("");
        buttonGroup1.setSelected(null, false);
        FornB = null;
    }

    private void limparCamposAplic() {
        cb_categoria.setSelectedIndex(0);
        cb_marca.setSelectedIndex(0);
        cb_modelo.setSelectedIndex(0);
    }

    private void desabilitarCampos() {
        cb_categoria.setEnabled(false);
        cb_unidade.setEnabled(false);
        txt_descricao.setEnabled(false);
        txt_obs.setEnabled(false);
        btn_salvarPedido.setEnabled(false);
        btn_editarPedido.setEnabled(false);
        btn_excluirPedido.setEnabled(false);
        btn_addCodigo.setEnabled(false);
        btn_addAplic.setEnabled(false);
        btn_novo.setEnabled(true);
    }

    public void preencherFormulario(CadItensAlmoxarifadoBeans beans) {
        ID_Item = beans.getID();
        setComboBoxCategoria(cb_categoriaAlmox, beans.getId_categoria().getID());
        txt_descricao.setText(beans.getDescricao());
        txt_obs.setText(beans.getObservacao());
        ch_status.setSelected(beans.getStatus());
        TbCodigo.addLista(beans.getListaCodigo());
        TbAplic.addLista(beans.getListaAplicacao());
        if (beans.getId_unidade() != null) {
            setComboBoxUnidade(cb_unidade, beans.getId_unidade().getID());
        }
    }

    private void preencherCamposCodigo(CadItensAlmoxCodigos codigo) {
        try {
            txt_codigo.setText(codigo.getCodigo());
            if (codigo.getCodigoCatalogo() == true) {
                ch_sim.setSelected(true);
            } else {
                ch_nao.setSelected(true);
            }
            if (codigo.getId_fornecedor() != null) {
                txt_fornecedor.setText(codigo.getId_fornecedor().getRazaoSocial());
                FornB = codigo.getId_fornecedor();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Preencher Campos!", "Erro", 0, FormatarICO.ICObtnSair());
        }

    }

    private void preencherCamposAplic(CadItensAlmoxAplicacao beans) {
        setComboBoxCategoriaAplic(cb_categoria, beans.getId_categoria_aplic().getID());
        setComboBoxMarca(cb_marca, beans.getId_marca_aplic().getID());
        setComboBoxModelo(cb_modelo, beans.getId_modelo_aplic().getID());
    }

}
