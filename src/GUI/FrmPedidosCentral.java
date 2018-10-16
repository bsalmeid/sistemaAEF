/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Almoxarifado.CadAlmoxarifadoBeans;
import Beans.CategoriaEquipamentosBeans;
import Beans.ListFazendasBeans;
import Beans.InventarioBeans;
import Beans.ModeloEquipamentosBeans;
import Beans.ListSetorTrabalhoBeans;
import Beans.ListUrgenciaPedidoMercadoria;
import Beans.MarcaEquipamentosBeans;
import Beans.PedidoAlmoxarifadoItens;
import Beans.StatusItemPedido;
import DAO.Diversas;
import DAO.DiversasHibernate;
import DAO.PedidosAlmoxarifadoDAO;
import static GUI.Principal.ListFazPermitidas;
import static GUI.Principal.listInventario;
import static GUI.Principal.listSetorTrabalho;
import static GUI.Principal.listStatusItemPedido;
import GerarRelatorios.RelatoriosPedidosMercadoria;
import Icones.FormatarICO;
import TableModel.TableModelPedidoMercadoria;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import com.toedter.calendar.JDateChooser;
import componentes.UJComboBox;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import static GUI.Principal.listaModeloEquipamentos;
import static GUI.Principal.listaCategoriaEquipamentos;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author agroa
 */
public class FrmPedidosCentral extends javax.swing.JInternalFrame {

    Diversas DiversasD;
    List<MarcaEquipamentosBeans> listMarcasEquip;
    TableModelPedidoMercadoria TbItens;
    CentralizarTabela Centralizar;
    PedidosAlmoxarifadoDAO PedidosD;
    FrmPedidosSolicitacao frmSolicitacao;
    JComboBox<StatusItemPedido> cb_statusItem;
    TableColumn TableColumnStatusItem;

    public FrmPedidosCentral() {
        initComponents();
        DiversasD = new Diversas();
        Centralizar = new CentralizarTabela();
        PedidosD = new PedidosAlmoxarifadoDAO();

        getRootPane().setDefaultButton(btn_pesqItem1);
        carregarCategorias();
        carregarMarcasEquip();
        carregarFazPermitidas();
        carregarUrgencia();
        carregarSetores();
        carregarInventario();
        getListStatusItem();
        cb_statusItem();
        carregarTabela();
        Calendar hoje = Calendar.getInstance();
        hoje.setTime(new Date(System.currentTimeMillis()));
        txt_dtFinal.setDate(hoje.getTime());
        hoje.add(Calendar.DAY_OF_MONTH, -90);
        txt_dtInicial.setDate(hoje.getTime());

    }

    private JComboBox<StatusItemPedido> cb_statusItem() {
        if (cb_statusItem == null) {
            cb_statusItem = new JComboBox<>();
            for (StatusItemPedido s : getListStatusItem()) {
                cb_statusItem.addItem(s);
                cb_statusItemConsulta.addItem(s);
            }
        }
        cb_statusItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int index = tb_itens.getSelectedRow();
                TbItens.setValueAt(cb_statusItem.getSelectedIndex(), index, TbItens.ID_STATUS_ITEM);
                cb_statusItem.setSelectedIndex(0);
            }
        });
        return cb_statusItem;
    }

    private List<StatusItemPedido> getListStatusItem() {
        if (listStatusItemPedido == null) {
            listStatusItemPedido = DiversasD.ListarStatusItemPedido();
        }
        return listStatusItemPedido;
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
        CategoriaEquipamentosBeans b = new CategoriaEquipamentosBeans(0,"-");
        cb_categoria.addItem(b);
        for (CategoriaEquipamentosBeans list : listaCategoriaEquipamentos) {
            cb_categoria.addItem(list);
        }
    }

    private void carregarModelos(Integer idCategoria, Integer idMarca) {
        if (listaModeloEquipamentos == null) {
            listaModeloEquipamentos = DiversasHibernate.getListaModeloInventario();
        }
        cb_modelo.removeAllItems();
        cb_modelo.addItem(new ModeloEquipamentosBeans(0, "-"));
        for (ModeloEquipamentosBeans list : listaModeloEquipamentos) {
            if (Objects.equals(list.getId_categoria().getID(), idCategoria) && Objects.equals(list.getId_marca().getID(), idMarca)) {
                cb_modelo.addItem(list);
            }
        }
    }

    private void carregarFazPermitidas() {
        if (ListFazPermitidas == null) {
            ListFazPermitidas = DiversasD.ListpmFazenda();
        }
        ListFazendasBeans l = new ListFazendasBeans();
        l.setID(0);
        l.setNomeFazenda("-");
        cb_fazenda.addItem(l);
        for (ListFazendasBeans list : ListFazPermitidas) {
            cb_fazenda.addItem(list);
        }
    }

    private void carregarUrgencia() {
        List<ListUrgenciaPedidoMercadoria> listaUrgencia = new ArrayList<>();
        ListUrgenciaPedidoMercadoria u1 = new ListUrgenciaPedidoMercadoria(0, "-", 0L, true);
        ListUrgenciaPedidoMercadoria u2 = new ListUrgenciaPedidoMercadoria(1, "Prazo 3 d", 3L, true);
        ListUrgenciaPedidoMercadoria u3 = new ListUrgenciaPedidoMercadoria(2, "Prazo 5 d", 5L, true);
        ListUrgenciaPedidoMercadoria u4 = new ListUrgenciaPedidoMercadoria(3, "Prazo 10 d", 10L, true);
        ListUrgenciaPedidoMercadoria u5 = new ListUrgenciaPedidoMercadoria(4, "Prazo 15 d", 15L, true);
        ListUrgenciaPedidoMercadoria u6 = new ListUrgenciaPedidoMercadoria(5, "Serviço Parado", 0L, true);
        ListUrgenciaPedidoMercadoria u7 = new ListUrgenciaPedidoMercadoria(6, "Máquina Parada", 0L, true);
        cb_urgencia.addItem(u1);
        cb_urgencia.addItem(u2);
        cb_urgencia.addItem(u3);
        cb_urgencia.addItem(u4);
        cb_urgencia.addItem(u5);
        cb_urgencia.addItem(u6);
        cb_urgencia.addItem(u7);
    }

    private void carregarSetores() {
        if (listSetorTrabalho == null) {
            listSetorTrabalho = DiversasD.ListSetoresTrabalho();
        }
        ListSetorTrabalhoBeans l = new ListSetorTrabalhoBeans();
        l.setID(0);
        l.setDescricao("-");
        cb_setorConsulta.addItem(l);
        for (ListSetorTrabalhoBeans list : listSetorTrabalho) {
            cb_setorConsulta.addItem(list);
        }
    }

    private void carregarInventario() {
        if (listInventario == null) {
            listInventario = DiversasHibernate.getListaInventario();
        }
        cb_aplicacaoConsulta.addItem(new InventarioBeans(0, "-", "-"));
        for (InventarioBeans frota : listInventario) {
            cb_aplicacaoConsulta.addItem(frota);
        }
    }

    private TableModel getTableModel() {
        if (TbItens == null) {
            TbItens = new TableModelPedidoMercadoria();
        }
        return TbItens;
    }

    private JTable carregarTabela() {
        tb_itens.setModel(getTableModel());
        ((DefaultTableCellRenderer) tb_itens.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        CellRenderTabela cellRenderTabela = new CellRenderTabela();

        for (int c = 0; c < TbItens.getColumnCount(); c++) {
            if (c == TbItens.ID || c == TbItens.ID_COMPRA || c == TbItens.ID_INVENTARIO
                    || c == TbItens.ID_SETOR || c == TbItens.ID_SOLICITANTE || c == TbItens.ID_STATUS_ITEM
                    || c == TbItens.ID_URGENCIA) {
                tb_itens.getColumnModel().getColumn(c).setMaxWidth(0);
                tb_itens.getColumnModel().getColumn(c).setMinWidth(0);
                tb_itens.getColumnModel().getColumn(c).setPreferredWidth(0);
            }
            tb_itens.getColumnModel().getColumn(c).setCellRenderer(cellRenderTabela);
        }

        tb_itens.getColumnModel().getColumn(TbItens.ID_PEDIDO).setPreferredWidth(70);
        tb_itens.getColumnModel().getColumn(TbItens.N_ITEM).setPreferredWidth(65);
        tb_itens.getColumnModel().getColumn(TbItens.CODIGO).setPreferredWidth(90);
        tb_itens.getColumnModel().getColumn(TbItens.DESCRICAO).setPreferredWidth(180);
        tb_itens.getColumnModel().getColumn(TbItens.INVENTARIO).setPreferredWidth(80);
        tb_itens.getColumnModel().getColumn(TbItens.STATUS_ITEM).setPreferredWidth(90);
        tb_itens.getColumnModel().getColumn(TbItens.ID_ITEM).setPreferredWidth(80);
        tb_itens.getColumnModel().getColumn(TbItens.QUANTIDADE).setPreferredWidth(60);
        TableColumnStatusItem = new TableColumn();
        TableColumnStatusItem = tb_itens.getColumnModel().getColumn(TbItens.STATUS_ITEM);
        TableColumnStatusItem.setCellEditor(new DefaultCellEditor(cb_statusItem));
        return tb_itens;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jPopupCentral = new javax.swing.JPopupMenu();
        jMenuEnviarSolicitacao = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuVisualizar = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_itens = new javax.swing.JTable();
        jXTaskPane1 = new org.jdesktop.swingx.JXTaskPane();
        javax.swing.JPanel jPanel5 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        txt_nPedido = new javax.swing.JTextField();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        txt_dtInicial =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        txt_dtFinal =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        txt_idItemConsulta = new javax.swing.JTextField();
        btn_pesqItem1 = new javax.swing.JButton();
        javax.swing.JLabel jLabel17 = new javax.swing.JLabel();
        txt_codigoConsulta = new javax.swing.JTextField();
        javax.swing.JLabel jLabel18 = new javax.swing.JLabel();
        txt_descricaoConsulta = new javax.swing.JTextField();
        javax.swing.JLabel jLabel19 = new javax.swing.JLabel();
        cb_aplicacaoConsulta = new componentes.UJComboBox();
        btn_pesquisar = new javax.swing.JButton();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        cb_categoria = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        cb_marca = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel20 = new javax.swing.JLabel();
        cb_modelo = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel34 = new javax.swing.JLabel();
        cb_setorConsulta = new javax.swing.JComboBox<>();
        javax.swing.JLabel lbl_fazenda = new javax.swing.JLabel();
        cb_fazenda = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        cb_urgencia = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel13 = new javax.swing.JLabel();
        cb_statusItemConsulta = new javax.swing.JComboBox<>();
        ch_status = new javax.swing.JCheckBox();

        jMenuEnviarSolicitacao.setText("Enviar Para Solicitação");
        jMenuEnviarSolicitacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEnviarSolicitacaoActionPerformed(evt);
            }
        });
        jPopupCentral.add(jMenuEnviarSolicitacao);
        jPopupCentral.add(jSeparator1);

        jMenuVisualizar.setText("Visualizar Pedido");
        jMenuVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuVisualizarActionPerformed(evt);
            }
        });
        jPopupCentral.add(jMenuVisualizar);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Central de Pedidos");

        tb_itens.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_itens.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_itensMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tb_itensMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tb_itens);

        jXTaskPane1.setTitle("Opções de Busca");

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Nº Pedido");

        txt_nPedido.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Data");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("à");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("ID Item");

        txt_idItemConsulta.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btn_pesqItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar pequeno.png"))); // NOI18N
        btn_pesqItem1.setPreferredSize(new java.awt.Dimension(30, 25));
        btn_pesqItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesqItem1ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Código");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Descrição");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Aplicação");

        cb_aplicacaoConsulta.setEditable(true);
        cb_aplicacaoConsulta.setMaximumRowCount(20);
        cb_aplicacaoConsulta.setAutocompletar(true);

        btn_pesquisar.setText("Pesquisar");
        btn_pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisarActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Categoria");

        cb_categoria.setPreferredSize(new java.awt.Dimension(90, 20));
        cb_categoria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_categoriaItemStateChanged(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Marca");

        cb_marca.setPreferredSize(new java.awt.Dimension(80, 20));
        cb_marca.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_marcaItemStateChanged(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Modelo");

        cb_modelo.setBorder(null);
        cb_modelo.setPreferredSize(new java.awt.Dimension(80, 20));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel34.setText("Setor");

        cb_setorConsulta.setPreferredSize(new java.awt.Dimension(90, 20));

        lbl_fazenda.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_fazenda.setText("Fazenda");

        cb_fazenda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_fazendaItemStateChanged(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Urgência");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Status Item");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ch_status, org.jdesktop.beansbinding.ELProperty.create("${selected}"), cb_statusItemConsulta, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        ch_status.setSelected(true);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_urgencia, 0, 143, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_setorConsulta, 0, 148, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_categoria, 0, 148, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_marca, 0, 138, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_modelo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_aplicacaoConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_fazenda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addGap(5, 5, 5)
                        .addComponent(txt_nPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_idItemConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn_pesqItem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_codigoConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_descricaoConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ch_status)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_statusItemConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3)
                    .addComponent(txt_nPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txt_idItemConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesqItem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(txt_codigoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18)
                        .addComponent(txt_descricaoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbl_fazenda)
                    .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txt_dtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txt_dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cb_modelo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(cb_marca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(cb_categoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel19)
                    .addComponent(cb_aplicacaoConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel34)
                        .addComponent(cb_setorConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cb_urgencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel13)
                    .addComponent(cb_statusItemConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesquisar)
                    .addComponent(ch_status))
                .addContainerGap())
        );

        jXTaskPane1.getContentPane().add(jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jXTaskPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXTaskPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_pesqItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesqItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_pesqItem1ActionPerformed

    private void btn_pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisarActionPerformed
        TbItens.limpar();
        TbItens.addLista(PedidosD.ConsultaPedidos(getSqlWhere()));
    }//GEN-LAST:event_btn_pesquisarActionPerformed

    private void cb_categoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_categoriaItemStateChanged
        if (cb_marca.getSelectedIndex() > 0) {
            carregarModelos(getIdCategoria(cb_categoria), getIdMarca(cb_marca));
        }
    }//GEN-LAST:event_cb_categoriaItemStateChanged

    private void cb_marcaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_marcaItemStateChanged
        carregarModelos(getIdCategoria(cb_categoria), getIdMarca(cb_marca));
    }//GEN-LAST:event_cb_marcaItemStateChanged

    private void cb_fazendaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_fazendaItemStateChanged

    }//GEN-LAST:event_cb_fazendaItemStateChanged

    private void jMenuEnviarSolicitacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuEnviarSolicitacaoActionPerformed
        Long ID = (Long) TbItens.getValueAt(tb_itens.getSelectedRow(), TbItens.ID_PEDIDO);
        if (ID > 0) {
            stateFormSolicitacao();
            enviarParaSolicitacao(tb_itens.getSelectedRows());
        }
    }//GEN-LAST:event_jMenuEnviarSolicitacaoActionPerformed

    private void jMenuVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuVisualizarActionPerformed
        int index = tb_itens.getSelectedRow();
        RelatoriosPedidosMercadoria.gerarPedido(TbItens.getBeans(index).getIdPedido(), false);
    }//GEN-LAST:event_jMenuVisualizarActionPerformed

    private void tb_itensMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_itensMouseClicked

        int rowIndex = tb_itens.getSelectedRow();
        int columnIndex = tb_itens.getSelectedColumn();
        if (rowIndex >= 0) {
            cb_statusItem.setSelectedIndex((Integer) TbItens.getValueAt(rowIndex, TbItens.ID_STATUS_ITEM));
        }
        if (evt.getButton() == MouseEvent.BUTTON3) {
            if (jPopupCentral.isVisible() == true) {
                jPopupCentral.setVisible(false);
            } else {
                jPopupCentral.setVisible(true);
                jPopupCentral.show(this, 0, 0);
                jPopupCentral.setLocation(evt.getLocationOnScreen());
            }
        }
    }//GEN-LAST:event_tb_itensMouseClicked

    private void tb_itensMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_itensMousePressed

    }//GEN-LAST:event_tb_itensMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btn_pesqItem1;
    private javax.swing.JButton btn_pesquisar;
    private componentes.UJComboBox cb_aplicacaoConsulta;
    private javax.swing.JComboBox<CategoriaEquipamentosBeans> cb_categoria;
    private javax.swing.JComboBox<ListFazendasBeans> cb_fazenda;
    private javax.swing.JComboBox<MarcaEquipamentosBeans> cb_marca;
    private javax.swing.JComboBox<ModeloEquipamentosBeans> cb_modelo;
    private javax.swing.JComboBox<ListSetorTrabalhoBeans> cb_setorConsulta;
    private javax.swing.JComboBox<StatusItemPedido> cb_statusItemConsulta;
    private javax.swing.JComboBox<ListUrgenciaPedidoMercadoria> cb_urgencia;
    private javax.swing.JCheckBox ch_status;
    private javax.swing.JMenuItem jMenuEnviarSolicitacao;
    private javax.swing.JMenuItem jMenuVisualizar;
    private javax.swing.JPopupMenu jPopupCentral;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane1;
    private javax.swing.JTable tb_itens;
    private javax.swing.JTextField txt_codigoConsulta;
    private javax.swing.JTextField txt_descricaoConsulta;
    private com.toedter.calendar.JDateChooser txt_dtFinal;
    private com.toedter.calendar.JDateChooser txt_dtInicial;
    private javax.swing.JTextField txt_idItemConsulta;
    private javax.swing.JTextField txt_nPedido;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    private void stateFormSolicitacao() {
        if (frmSolicitacao == null) {
            frmSolicitacao = new FrmPedidosSolicitacao();
            this.getParent().add(frmSolicitacao).setVisible(true);
            frmSolicitacao.novo();
        } else {
            try {
                if (frmSolicitacao.isVisible() == false) {
                    this.getParent().add(frmSolicitacao).setVisible(true);
                    frmSolicitacao.setVisible(true);
                }
                frmSolicitacao.setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(FrmPedidosCentral.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        frmSolicitacao.setSize(this.getSize().width, frmSolicitacao.getSize().height);
    }

    private void enviarParaSolicitacao(int[] linhasSelecionadas) {
        for (int i = 0; i < linhasSelecionadas.length; i++) {
            PedidoAlmoxarifadoItens item = TbItens.getBeans(linhasSelecionadas[i]);
            if (item.getIdSolicitacao() == null) {
                frmSolicitacao.TbItensSolicitacao.addBeans(item);
            } else {
                JOptionPane.showMessageDialog(null, "O Item Nº " + item.getnItem()
                        + " do Pedido Nº " + item.getIdPedido().getId() + ", já se encontra em outra Solicitação!", "Erro", 0, FormatarICO.ICObtnSair());
            }
        }
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frmSolicitacao.jScrollPane1.getVerticalScrollBar().setValue(frmSolicitacao.jScrollPane1.getVerticalScrollBar().getMaximum());
                frmSolicitacao.tb_solicitacao.setRowSelectionInterval(frmSolicitacao.tb_solicitacao.getRowCount() - 1, frmSolicitacao.tb_solicitacao.getRowCount() - 1);
            }
        });

    }

    private Integer getIdFazenda(JComboBox<ListFazendasBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return comboBox.getItemAt(comboBox.getSelectedIndex()).getID();
        }
        return 0;
    }

    private Integer getIdUrgencia(JComboBox<ListUrgenciaPedidoMercadoria> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return comboBox.getItemAt(comboBox.getSelectedIndex()).getId();
        }
        return 0;
    }

    private Integer getIdStatusItem(JComboBox<StatusItemPedido> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return comboBox.getItemAt(comboBox.getSelectedIndex()).getID();
        }
        return 0;
    }

    private Integer getIdAlmoxarifado(JComboBox<CadAlmoxarifadoBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return comboBox.getItemAt(comboBox.getSelectedIndex()).getID();
        }
        return 0;
    }

    private Integer getIdSetor(JComboBox<ListSetorTrabalhoBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return comboBox.getItemAt(comboBox.getSelectedIndex()).getID();
        }
        return 0;
    }

    private Integer getIdAplicacao(UJComboBox comboBox) {
        InventarioBeans obj;
        try {
            obj = (InventarioBeans) comboBox.getSelectedObject();
            return obj.getID();
        } catch (Exception e) {
            return 0;
        }
    }

    private Integer getIdCategoria(JComboBox<CategoriaEquipamentosBeans> comboBox) {
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

    private String getSqlWhere() {
        String s = "";
        String joinInventario = "";
        if (!txt_codigoConsulta.getText().equals("")) {
            s += " and pi.codigo like '%" + txt_codigoConsulta.getText() + "%'";
        }
        if (!txt_descricaoConsulta.getText().equals("")) {
            s += " and pi.descricao like '%" + txt_descricaoConsulta.getText() + "%'";
        }
        if (!txt_idItemConsulta.getText().equals("")) {
            s += " and pi.id_item = " + txt_idItemConsulta.getText();
        }
        if (!txt_nPedido.getText().equals("")) {
            s += " and p.id = " + txt_nPedido.getText();
        }
        if (txt_dtInicial.getDate() != null && txt_dtFinal.getDate() != null) {
            s += " and p.data BETWEEN '" + Corretores.ConverterDateAAAA_MM_DD(txt_dtInicial.getDate()) + "' and '" + Corretores.ConverterDateAAAA_MM_DD(txt_dtFinal.getDate()) + "'";
        }
        if (cb_statusItemConsulta.getSelectedIndex() >= 0 && ch_status.isSelected()) {
            s += " and pi.id_status_item = " + getIdStatusItem(cb_statusItemConsulta);
        }
        if (cb_setorConsulta.getSelectedIndex() > 0) {
            s += " and pi.id_setor = " + getIdSetor(cb_setorConsulta);
        }
        if (cb_aplicacaoConsulta.getSelectedIndex() > 0) {
            s += " and pi.id_inventario = " + getIdAplicacao(cb_aplicacaoConsulta);
        }
        if (cb_fazenda.getSelectedIndex() > 0) {
            s += " and p.id_fazenda = " + getIdFazenda(cb_fazenda);
        }
        if (cb_urgencia.getSelectedIndex() > 0) {
            s += " and pi.id_urgencia = " + getIdUrgencia(cb_urgencia);
        }
        if (cb_marca.getSelectedIndex() > 0) {
            s += " and ci.id_marca = " + getIdMarca(cb_marca);
            joinInventario = " LEFT JOIN cad_inventario ci ON ci.id = pi.id_inventario ";
        }
        if (cb_categoria.getSelectedIndex() > 0) {
            s += " and ci.id_categoria = " + getIdCategoria(cb_categoria);
            joinInventario = " LEFT JOIN cad_inventario ci ON ci.id = pi.id_inventario ";
        }
        if (cb_modelo.getSelectedIndex() > 0) {
            s += " and ci.id_modelo = " + getIdModelo(cb_modelo);
            joinInventario = " LEFT JOIN cad_inventario ci ON ci.id = pi.id_inventario ";
        }
        s += " and p.status_emissao = 1"; // condição para buscar apenas itens emitidos

        if (!s.equals("")) {
            s = " WHERE " + s.replaceFirst("and", "");
        }
        if (!joinInventario.equals("")) {
            s = joinInventario + s;
        }
        return s;
    }

    private class CellRenderTabela extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (isSelected) {
                return renderer;
            }
            Color background = table.getForeground();
            Color foreground = table.getBackground();
            ((JLabel) renderer).setOpaque(true);
            ((JLabel) renderer).setHorizontalAlignment(SwingConstants.CENTER);
            configurarCor(table, renderer, row, column, background, foreground);

            return renderer;
        }

        private void configurarCor(JTable table, Component renderer, int row, int column, Color background, Color foreground) {

            Integer idStatusItem = (Integer) table.getValueAt(row, 18);
            switch (idStatusItem) {
                case 0:
                    renderer.setBackground(table.getBackground());
                    renderer.setForeground(table.getForeground());
                    break;
                case 1:
                    foreground = Color.black;
                    background = new Color(255, 222, 163, 255);
                    renderer.setBackground(background);
                    renderer.setForeground(foreground);
                    break;
                case 2:
                    foreground = Color.black;
                    background = new Color(153, 204, 255, 255);
                    ;
                    renderer.setBackground(background);
                    renderer.setForeground(foreground);
                    break;
                case 3:
                    foreground = Color.black;
                    background = new Color(0, 204, 153, 255);
                    ;
                    renderer.setBackground(background);
                    renderer.setForeground(foreground);
                    break;
                case 4:
                    foreground = Color.black;
                    background = new Color(183, 191, 127);
                    ;
                    renderer.setBackground(background);
                    renderer.setForeground(foreground);
                    break;
                case 5:
                    foreground = Color.black;
                    background = new Color(0, 176, 80, 255);
                    renderer.setBackground(background);
                    renderer.setForeground(foreground);
                default:
                    break;
            }
        }

    }

}
