package GUI;

import DAO.InventarioDAO;
import Beans.CategoriaEquipamentosBeans;
import Beans.InventarioBeans;
import Beans.ModeloEquipamentosBeans;
import Beans.MarcaEquipamentosBeans;
import Beans.PropriedadesBeans;
import DAO.DiversasHibernate;
import static GUI.Principal.ListaPropriedades;
import TableModel.TableModelConsultaInventario;
import static GUI.Principal.listaCategoriaEquipamentos;
import static GUI.Principal.listaMarcaEquipamentos;
import static GUI.Principal.listaModeloEquipamentos;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import Utilitarios.ValidarPermissoes;
import com.toedter.calendar.JDateChooser;
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

public class FrmCadInventario extends javax.swing.JInternalFrame {

    CentralizarTabela Centralizar;
    InventarioDAO InventD;
    InventarioBeans InventB;
    CategoriaEquipamentosBeans CatB;
    MarcaEquipamentosBeans MarcaB;
    ModeloEquipamentosBeans ModeloB;
    TableModelConsultaInventario TbConInventario;

    public FrmCadInventario() {
        initComponents();
        Centralizar = new CentralizarTabela();
        InventD = new InventarioDAO();
        InventB = new InventarioBeans();
        habilitarCampos(false);
        habilitarJButton(false);
        carregarModelo();
        carregarCategoria();
        carregarMarca();
        carregarFazendas();
        getTabelaConsInventario();
    }

    private void carregarModelo() {
        if (listaModeloEquipamentos == null) {
            listaModeloEquipamentos = InventD.listarModelo();
        }
        cb_modelo.addItem(new ModeloEquipamentosBeans(0, "-"));
        cb_modeloBuscar.addItem(new ModeloEquipamentosBeans(0, "-"));
        for (ModeloEquipamentosBeans m : listaModeloEquipamentos) {
            cb_modelo.addItem(m);
            cb_modeloBuscar.addItem(m);
        }
    }

    private void carregarCategoria() {
        if (listaCategoriaEquipamentos == null) {
            listaCategoriaEquipamentos = InventD.listarCategoria();
        }
        cb_categoria.addItem(new CategoriaEquipamentosBeans(0, "-"));
        cb_categoriaBuscar.addItem(new CategoriaEquipamentosBeans(0, "-"));
        for (CategoriaEquipamentosBeans c : listaCategoriaEquipamentos) {
            cb_categoria.addItem(c);
            cb_categoriaBuscar.addItem(c);
        }
    }

    private void carregarMarca() {
        if (listaMarcaEquipamentos == null) {
            listaMarcaEquipamentos = InventD.listarMarca();
        }
        cb_marca.addItem(new MarcaEquipamentosBeans(0, "-"));
        cb_marcaBuscar.addItem(new MarcaEquipamentosBeans(0, "-"));
        for (MarcaEquipamentosBeans m : listaMarcaEquipamentos) {
            cb_marca.addItem(m);
            cb_marcaBuscar.addItem(m);
        }
    }

    private void carregarFazendas() {
        if (ListaPropriedades == null) {
            ListaPropriedades = DiversasHibernate.getListaFazendas();
        }
        cb_fazenda.addItem(new PropriedadesBeans(0, "-"));
        cb_fazendaBuscar.addItem(new PropriedadesBeans(0, "-"));
        for (PropriedadesBeans p : ListaPropriedades) {
            cb_fazenda.addItem(p);
            cb_fazendaBuscar.addItem(p);
        }
    }

    private void carregarModelo(Integer idMarca, JComboBox<ModeloEquipamentosBeans> combo) {
        if (listaModeloEquipamentos == null) {
            listaModeloEquipamentos = InventD.listarModelo();
        }
        combo.removeAllItems();
        combo.addItem(new ModeloEquipamentosBeans(0, "-"));
        for (ModeloEquipamentosBeans m : listaModeloEquipamentos) {
            if (Objects.equals(m.getId_marca().getID(), idMarca)) {
                combo.addItem(m);
            }
        }
    }

    private TableModelConsultaInventario getTableModelConsInventario() {
        if (TbConInventario == null) {
            TbConInventario = new TableModelConsultaInventario();
        }
        return TbConInventario;
    }

    private JTable getTabelaConsInventario() {
        tb_conInventario.setModel(getTableModelConsInventario());
        Centralizar.centralizarTabela(tb_conInventario);
        ((DefaultTableCellRenderer) tb_conInventario.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        tb_conInventario.getColumnModel().getColumn(TbConInventario.ID_INVENTARIO).setPreferredWidth(2);
        tb_conInventario.getColumnModel().getColumn(TbConInventario.FAZENDA).setPreferredWidth(70);
        tb_conInventario.getColumnModel().getColumn(TbConInventario.CATEGORIA).setPreferredWidth(70);
        tb_conInventario.getColumnModel().getColumn(TbConInventario.MARCA).setPreferredWidth(40);
        tb_conInventario.getColumnModel().getColumn(TbConInventario.MODELO).setPreferredWidth(100);
        tb_conInventario.getColumnModel().getColumn(TbConInventario.FROTA).setPreferredWidth(30);
        tb_conInventario.getColumnModel().getColumn(TbConInventario.AQUISICAO).setPreferredWidth(30);
        tb_conInventario.getColumnModel().getColumn(TbConInventario.MOTORIZADO).setPreferredWidth(1);
        tb_conInventario.getColumnModel().getColumn(TbConInventario.LARGURA).setPreferredWidth(15);
        tb_conInventario.getColumnModel().getColumn(TbConInventario.ANO).setPreferredWidth(15);
        tb_conInventario.getColumnModel().getColumn(TbConInventario.SERIE).setPreferredWidth(120);
        tb_conInventario.getColumnModel().getColumn(TbConInventario.DESCRICAO).setPreferredWidth(150);
        tb_conInventario.getColumnModel().getColumn(TbConInventario.STATUS).setPreferredWidth(1);

        return tb_conInventario;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tp_consulta = new org.jdesktop.swingx.JXTaskPane();
        jPanelConsulta = new javax.swing.JPanel();
        btn_pesquisar = new javax.swing.JButton();
        javax.swing.JLabel jLabel14 = new javax.swing.JLabel();
        txt_frotaBuscar = new javax.swing.JTextField();
        javax.swing.JLabel jLabel15 = new javax.swing.JLabel();
        txt_descricaoBuscar = new javax.swing.JTextField();
        cb_modeloBuscar = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel20 = new javax.swing.JLabel();
        cb_categoriaBuscar = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel21 = new javax.swing.JLabel();
        cb_marcaBuscar = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel22 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        ch_statusBuscar = new javax.swing.JCheckBox();
        cb_fazendaBuscar = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel24 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_conInventario = new javax.swing.JTable();
        btn_novo = new javax.swing.JButton();
        btn_salvar = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        tp_cadastro = new org.jdesktop.swingx.JXTaskPane();
        jPanelCadInventario = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        txt_frota = new javax.swing.JTextField();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        txt_aquisicao = new com.toedter.calendar.JDateChooser();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        txt_largura = new javax.swing.JTextField();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        txt_ano = new javax.swing.JTextField();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        txt_serie = new javax.swing.JTextField();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        txt_observacao = new javax.swing.JTextField();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        txt_descricao = new javax.swing.JTextField();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        ch_motorizado = new javax.swing.JCheckBox();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        ch_status = new javax.swing.JCheckBox();
        javax.swing.JLabel jLabel17 = new javax.swing.JLabel();
        cb_categoria = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel18 = new javax.swing.JLabel();
        cb_marca = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel19 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel23 = new javax.swing.JLabel();
        cb_modelo = new javax.swing.JComboBox<>();
        cb_fazenda = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro de Inventário");

        tp_consulta.setTitle("Opções de Consulta");

        jPanelConsulta.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelConsulta.setToolTipText("");

        btn_pesquisar.setMnemonic('e');
        btn_pesquisar.setText("Pesquisar");
        btn_pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisarActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText(" Nº de Frota");

        txt_frotaBuscar.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Descrição");

        txt_descricaoBuscar.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Categoria");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Marca");

        cb_marcaBuscar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_marcaBuscarItemStateChanged(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Modelo");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Status");

        ch_statusBuscar.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        ch_statusBuscar.setSelected(true);
        ch_statusBuscar.setText("Ativo");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Fazenda");

        javax.swing.GroupLayout jPanelConsultaLayout = new javax.swing.GroupLayout(jPanelConsulta);
        jPanelConsulta.setLayout(jPanelConsultaLayout);
        jPanelConsultaLayout.setHorizontalGroup(
            jPanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_fazendaBuscar, 0, 101, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_categoriaBuscar, 0, 101, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_marcaBuscar, 0, 101, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_modeloBuscar, 0, 101, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_frotaBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_descricaoBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ch_statusBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_pesquisar)
                .addContainerGap())
        );
        jPanelConsultaLayout.setVerticalGroup(
            jPanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel21)
                    .addComponent(cb_marcaBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(cb_modeloBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txt_frotaBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txt_descricaoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesquisar)
                    .addComponent(jLabel11)
                    .addComponent(ch_statusBuscar)
                    .addGroup(jPanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24)
                        .addComponent(cb_fazendaBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel20)
                        .addComponent(cb_categoriaBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        tp_consulta.getContentPane().add(jPanelConsulta);

        tb_conInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb_conInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_conInventarioMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_conInventario);

        btn_novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/inserir_40x40.png"))); // NOI18N
        btn_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novoActionPerformed(evt);
            }
        });

        btn_salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/salvar_32_32.png"))); // NOI18N
        btn_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salvarActionPerformed(evt);
            }
        });

        btn_editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/editar_32_32.png"))); // NOI18N
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });

        tp_cadastro.setTitle("Dados Cadastrais");

        jPanelCadInventario.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Código");

        txt_codigo.setEditable(false);
        txt_codigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_codigo.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Nº Frota");

        txt_frota.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Data da Aquisição");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Largura");

        txt_largura.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Ano");

        txt_ano.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Nº Série");

        txt_serie.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Observação");

        txt_observacao.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Descrição");

        txt_descricao.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Motorizado");

        ch_motorizado.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Status");

        ch_status.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        ch_status.setText("Ativo");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Categoria");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Marca");

        cb_marca.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_marcaItemStateChanged(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Fazenda");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("Modelo");

        javax.swing.GroupLayout jPanelCadInventarioLayout = new javax.swing.GroupLayout(jPanelCadInventario);
        jPanelCadInventario.setLayout(jPanelCadInventarioLayout);
        jPanelCadInventarioLayout.setHorizontalGroup(
            jPanelCadInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCadInventarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCadInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCadInventarioLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_frota, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_aquisicao, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_largura, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_ano, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ch_motorizado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_serie))
                    .addGroup(jPanelCadInventarioLayout.createSequentialGroup()
                        .addGroup(jPanelCadInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanelCadInventarioLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_descricao))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelCadInventarioLayout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_categoria, 0, 202, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelCadInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelCadInventarioLayout.createSequentialGroup()
                                .addComponent(cb_marca, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ch_status)
                                .addGap(0, 15, Short.MAX_VALUE))
                            .addGroup(jPanelCadInventarioLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_observacao)))))
                .addContainerGap())
        );
        jPanelCadInventarioLayout.setVerticalGroup(
            jPanelCadInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCadInventarioLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanelCadInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txt_frota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txt_aquisicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txt_largura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txt_ano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(ch_motorizado)
                    .addComponent(jLabel10)
                    .addComponent(txt_serie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCadInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel9)
                    .addComponent(txt_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txt_observacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCadInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel19)
                    .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(cb_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(cb_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(cb_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(ch_status))
                .addContainerGap())
        );

        tp_cadastro.getContentPane().add(jPanelCadInventario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1018, 1018, 1018)
                        .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tp_cadastro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(tp_consulta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                        .addContainerGap())))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_editar, btn_novo, btn_salvar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tp_cadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tp_consulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_editar, btn_novo, btn_salvar});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisarActionPerformed
        TbConInventario.limpar();
        TbConInventario.addLista(InventD.listarInventarioString(getWhere()));
        limparCampos(jPanelConsulta);
        JCheckBox(jPanelConsulta);
        cb_fazendaBuscar.grabFocus();
    }//GEN-LAST:event_btn_pesquisarActionPerformed

    private void tb_conInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_conInventarioMouseClicked
        int linha = tb_conInventario.getSelectedRow();
        if (evt.getClickCount() == 2) {
            int editar = JOptionPane.showConfirmDialog(this, "Deseja editar o Inventário?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (editar == JOptionPane.YES_OPTION) {
                limparCampos(jPanelCadInventario);
                InventB = InventD.buscarInventario((Integer) TbConInventario.getValueAt(linha, TbConInventario.ID_INVENTARIO));
                preencherCampos(InventB);
                habilitarCampos(true);
                habilitarJButton(true);
            }
        }
    }//GEN-LAST:event_tb_conInventarioMouseClicked

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        int editar = JOptionPane.showConfirmDialog(this, "Deseja editar o Inventário?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (editar == JOptionPane.YES_OPTION) {
            popular(InventB);
            if (verificar(InventB) && ValidarPermissoes.validarPermissaoUpdate(FrmCadInventario.class.getSimpleName())) {
                if (InventD.atualizarInventario(InventB)) {
                    habilitarCampos(false);
                    habilitarJButton(false);
                    limparCampos(jPanelCadInventario);
                }
            }
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void btn_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(this, "Deseja cadastrar o Inventário?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (cadastrar == JOptionPane.YES_OPTION) {
            popular(InventB);
            if (verificar(InventB) && ValidarPermissoes.validarPermissaoInsert(FrmCadInventario.class.getSimpleName())) {
                if (InventD.salvarInventario(InventB)) {
                    limparCampos(jPanelCadInventario);
                    txt_frota.grabFocus();
                }
            }
        }
    }//GEN-LAST:event_btn_salvarActionPerformed

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed
        InventB = new InventarioBeans();
        habilitarCampos(true);
        habilitarJButton(true);
        btn_editar.setEnabled(false);
        limparCampos(jPanelCadInventario);
        txt_frota.grabFocus();
    }//GEN-LAST:event_btn_novoActionPerformed

    private void cb_marcaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_marcaItemStateChanged
        if (cb_marca.getSelectedIndex() > 0) {
            carregarModelo(getMarca(cb_marca).getID(), cb_modelo);
        }
    }//GEN-LAST:event_cb_marcaItemStateChanged

    private void cb_marcaBuscarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_marcaBuscarItemStateChanged
        if (cb_marcaBuscar.getSelectedIndex() > 0) {
            carregarModelo(getMarca(cb_marcaBuscar).getID(), cb_modeloBuscar);
        }
    }//GEN-LAST:event_cb_marcaBuscarItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_novo;
    private javax.swing.JButton btn_pesquisar;
    private javax.swing.JButton btn_salvar;
    private javax.swing.JComboBox<CategoriaEquipamentosBeans> cb_categoria;
    private javax.swing.JComboBox<CategoriaEquipamentosBeans> cb_categoriaBuscar;
    private javax.swing.JComboBox<PropriedadesBeans> cb_fazenda;
    private javax.swing.JComboBox<PropriedadesBeans> cb_fazendaBuscar;
    private javax.swing.JComboBox<MarcaEquipamentosBeans> cb_marca;
    private javax.swing.JComboBox<MarcaEquipamentosBeans> cb_marcaBuscar;
    private javax.swing.JComboBox<ModeloEquipamentosBeans> cb_modelo;
    private javax.swing.JComboBox<ModeloEquipamentosBeans> cb_modeloBuscar;
    private javax.swing.JCheckBox ch_motorizado;
    private javax.swing.JCheckBox ch_status;
    private javax.swing.JCheckBox ch_statusBuscar;
    private javax.swing.JPanel jPanelCadInventario;
    private javax.swing.JPanel jPanelConsulta;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tb_conInventario;
    private org.jdesktop.swingx.JXTaskPane tp_cadastro;
    private org.jdesktop.swingx.JXTaskPane tp_consulta;
    private javax.swing.JTextField txt_ano;
    private com.toedter.calendar.JDateChooser txt_aquisicao;
    private javax.swing.JTextField txt_codigo;
    private javax.swing.JTextField txt_descricao;
    private javax.swing.JTextField txt_descricaoBuscar;
    private javax.swing.JTextField txt_frota;
    private javax.swing.JTextField txt_frotaBuscar;
    private javax.swing.JTextField txt_largura;
    private javax.swing.JTextField txt_observacao;
    private javax.swing.JTextField txt_serie;
    // End of variables declaration//GEN-END:variables

    private String getWhere() {
        String s = "";

        if (cb_fazendaBuscar.getSelectedIndex() > 0) {
            s += " and T.id_fazenda = " +getFazenda(cb_fazenda).getCodigo();
        }
        if (cb_categoriaBuscar.getSelectedIndex() > 0) {
            s += " and T.id_categoria = "+ getCategoria(cb_categoria).getID();
        }
        if (cb_marcaBuscar.getSelectedIndex() > 0) {
            s += " and T.id_marca = " + getMarca(cb_marca).getID();
        }
        if (cb_modeloBuscar.getSelectedIndex() > 0) {
            s += " and T.id_modelo.descricao = " + getModelo(cb_modelo).getID();
        }
        if (!ch_statusBuscar.isSelected()) {
            s += " and T.status = '0'";
        } else if (ch_statusBuscar.isSelected()) {
            s += " and T.status = '1'";
        }
        if (!txt_frotaBuscar.getText().equals("")) {
            s += " and T.nFrota like '%" + txt_frotaBuscar.getText() + "%'";
        }
        if (!txt_descricaoBuscar.getText().equals("")) {
            s += " and T.descricao like '%" + txt_descricaoBuscar.getText() + "%'";
        }
        if (!s.equals("")) {
            s = " WHERE " + s.replaceFirst("and", "");
        }
        return s;
    }

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
            } else if (c instanceof JDateChooser) {
                ((JDateChooser) c).setDate(null);
            }
        }
    }

    private void JCheckBox(Container container) {
        for (int i = 0; i < container.getComponentCount(); i++) {
            Component c = container.getComponent(i);
            if (c instanceof JCheckBox) {
                ((JCheckBox) c).setSelected(true);
            }
        }
    }

    final void habilitarCampos(boolean valor) {
        txt_descricao.setEnabled(valor);
        txt_largura.setEnabled(valor);
        ch_status.setEnabled(valor);
        txt_frota.setEnabled(valor);
        txt_aquisicao.setEnabled(valor);
        txt_ano.setEnabled(valor);
        txt_serie.setEnabled(valor);
        txt_observacao.setEnabled(valor);
        cb_modelo.setEnabled(valor);
        cb_categoria.setEnabled(valor);
        cb_fazenda.setEnabled(valor);
        cb_marca.setEnabled(valor);
        ch_motorizado.setEnabled(valor);
    }

    final void habilitarJButton(boolean valor) {
        btn_salvar.setEnabled(valor);
        btn_editar.setEnabled(valor);
    }

    private void preencherCampos(InventarioBeans I) {
        txt_codigo.setText(I.getID().toString());
        txt_frota.setText(I.getnFrota());
        txt_aquisicao.setDate(I.getDataAquisicao());
        txt_largura.setText(Corretores.ConverterDoubleDecimal(I.getLarguraTrabalho()));
        txt_ano.setText(I.getAno());
        txt_serie.setText(I.getSerie());
        txt_observacao.setText(I.getObservacao());
        txt_descricao.setText(I.getDescricao());
        ch_status.setSelected(I.getStatus());
        ch_motorizado.setSelected(I.getMotorizado());
        
        setMarca(cb_marca, I.getIdmarca());
        setCategoria(cb_categoria, I.getIdcategoria());
        setFazenda(cb_fazenda, I.getIdfazenda());
        setModelo(cb_modelo, I.getIdmodelo());    
    }

    private void popular(InventarioBeans I) {
        I.setnFrota(txt_frota.getText());
        I.setDataAquisicao(txt_aquisicao.getDate());
        I.setLarguraTrabalho(Corretores.ConverterStringDouble(txt_largura.getText()));
        I.setAno(txt_ano.getText());
        I.setSerie(txt_serie.getText());
        I.setObservacao(txt_observacao.getText());
        I.setDescricao(txt_descricao.getText());
        I.setMotorizado(ch_motorizado.isSelected());
        I.setStatus(ch_status.isSelected());
        I.setId_categoria(getCategoria(cb_categoria));
        I.setId_marca(getMarca(cb_marca));
        I.setId_modelo(getModelo(cb_modelo));
        I.setId_fazenda(getFazenda(cb_fazenda));
    }

    private MarcaEquipamentosBeans getMarca(JComboBox<MarcaEquipamentosBeans> combo) {
        return (MarcaEquipamentosBeans) combo.getSelectedItem();
    }

    private ModeloEquipamentosBeans getModelo(JComboBox<ModeloEquipamentosBeans> combo) {
        return (ModeloEquipamentosBeans) combo.getSelectedItem();
    }

    private CategoriaEquipamentosBeans getCategoria(JComboBox<CategoriaEquipamentosBeans> combo) {
        return (CategoriaEquipamentosBeans) combo.getSelectedItem();
    }

    private PropriedadesBeans getFazenda(JComboBox<PropriedadesBeans> combo) {
        return (PropriedadesBeans) combo.getSelectedItem();
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

    private void setModelo(JComboBox<ModeloEquipamentosBeans> combo, Integer ID) {
        for (int i = 0; i < combo.getModel().getSize(); i++) {
            if (Objects.equals(combo.getItemAt(i).getID(), ID)) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }

    private void setFazenda(JComboBox<PropriedadesBeans> combo, Integer ID) {
        for (int i = 0; i < combo.getModel().getSize(); i++) {
            if (Objects.equals(combo.getItemAt(i).getCodigo(), ID)) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }

    public boolean verificar(InventarioBeans I) {
        if (I.getnFrota().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha o Número da frota do Inventário", "Atenção", 0);
            return false;
        }
        if (I.getDescricao().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha a descrição do Inventário", "Atenção", 0);
            return false;
        }
        if (I.getId_categoria().getID() == 0) {
            JOptionPane.showMessageDialog(null, "Preencha a Categoria do Item", "Atenção", 0);
            return false;
        }
        if (I.getId_marca().getID() == 0) {
            JOptionPane.showMessageDialog(null, "Preencha a Marca do Item", "Atenção", 0);
            return false;
        }
        if (I.getId_modelo().getID() == 0) {
            JOptionPane.showMessageDialog(null, "Preencha a Modelo do Item", "Atenção", 0);
            return false;
        }
        if (I.getId_fazenda().getCodigo() == 0) {
            JOptionPane.showMessageDialog(null, "Preencha a Fazenda do Item", "Atenção", 0);
            return false;
        }
        return true;
    }
}
