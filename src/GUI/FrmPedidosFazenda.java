/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Almoxarifado.CadAlmoxarifadoBeans;
import Beans.CategoriaEquipamentosBeans;
import Beans.InventarioBeans;
import Beans.ModeloEquipamentosBeans;
import Beans.ListSetorTrabalhoBeans;
import Beans.ListUrgenciaPedidoMercadoria;
import Beans.MarcaEquipamentosBeans;
import Beans.PedidoAlmoxarifadoItens;
import Beans.PedidosAlmoxarifado;
import Beans.PropriedadesBeans;
import DAO.Diversas;
import DAO.DiversasHibernate;
import DAO.PedidosAlmoxarifadoDAO;
import static GUI.Principal.listAlmoxarifado;
import static GUI.Principal.listInventario;
import static GUI.Principal.listSetorTrabalho;
import static GUI.Principal.listStatusItemPedido;
import static GUI.frmLogin.IdUsuario;
import static GUI.frmLogin.UsuarioLogado;
import GerarRelatorios.RelatoriosPedidosMercadoria;
import Icones.FormatarICO;
import TableModel.TableModelPedidoMercadoria;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import com.toedter.calendar.JDateChooser;
import componentes.UJComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import static GUI.Principal.listaModeloEquipamentos;
import static GUI.Principal.listaCategoriaEquipamentos;
import static GUI.Principal.listaFazPermitida;
import java.util.Date;

/**
 *
 * @author agroa
 */
public class FrmPedidosFazenda extends javax.swing.JInternalFrame {

    String CodigoProduto;
    Diversas DiversasD;
    CentralizarTabela Centralizar;
    PedidosAlmoxarifado PedidoB;
    PedidoAlmoxarifadoItens ItemB;
    TableModelPedidoMercadoria TbItens;
    PedidosAlmoxarifadoDAO PedidosD;
    TableModelPedidoMercadoria TbConsulta;
    List<MarcaEquipamentosBeans> listMarcasEquip;

    public FrmPedidosFazenda() {
        initComponents();
        Centralizar = new CentralizarTabela();
        DiversasD = new Diversas();
        PedidosD = new PedidosAlmoxarifadoDAO();
        PedidoB = new PedidosAlmoxarifado();
        carregarModelos();
        carregarFazPermitidas();
        carregarSetores();
        carregarInventario();
        carregarTabela();
        carregarUrgencia();
        carregarAlmoxarifado();
        carregarCategorias();
        carregarMarcasEquip();
        carregarTabelaConsulta();
        carregarStatusItem();
        jMenuEnviarRemessa.setVisible(false);
        txt_dtFinal.setDate(new Date(System.currentTimeMillis()));
        txt_dtInicial.setDate(Corretores.somarDias(txt_dtFinal.getDate(), -90));

    }

    private void carregarStatusItem() {
        if (listStatusItemPedido == null) {
            listStatusItemPedido = DiversasD.ListarStatusItemPedido();
        }
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

    private void carregarModelos() {
        if (listaModeloEquipamentos == null) {
            listaModeloEquipamentos = DiversasHibernate.getListaModeloInventario();
        }
        ModeloEquipamentosBeans b = new ModeloEquipamentosBeans(0, "-");
        cb_modelo.removeAllItems();
        cb_modelo.addItem(b);
        for (ModeloEquipamentosBeans list : listaModeloEquipamentos) {
            cb_modelo.addItem(list);
        }
    }

    private void carregarModelos(Integer idCategoria, Integer idMarca) {
        if (listaModeloEquipamentos == null) {
            listaModeloEquipamentos = DiversasHibernate.getListaModeloInventario();
        }
        ModeloEquipamentosBeans b = new ModeloEquipamentosBeans(0, "-");
        cb_modelo.removeAllItems();
        cb_modelo.addItem(b);
        for (ModeloEquipamentosBeans list : listaModeloEquipamentos) {
            if (Objects.equals(list.getId_categoria().getID(), idCategoria) && Objects.equals(list.getId_marca().getID(), idMarca)) {
                cb_modelo.addItem(list);
            }
        }
    }

    private void carregarFazPermitidas() {
        if (listaFazPermitida == null) {
            listaFazPermitida = DiversasHibernate.getListaFazendasPermitidas();
        }
        cb_fazenda.addItem(new PropriedadesBeans(0, "-"));
        cb_fazendaConsulta.addItem(new PropriedadesBeans(0, "-"));
        for (PropriedadesBeans list : listaFazPermitida) {
            cb_fazenda.addItem(list);
            cb_fazendaConsulta.addItem(list);
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
        cb_setor.addItem(l);
        cb_setorConsulta.addItem(l);
        for (ListSetorTrabalhoBeans list : listSetorTrabalho) {
            cb_setor.addItem(list);
            cb_setorConsulta.addItem(list);
        }
    }

    private void carregarInventario() {
        if (listInventario == null) {
            listInventario = DiversasHibernate.getListaInventario();
        }
        cb_aplicacao.removeAllItems();
        InventarioBeans l = new InventarioBeans(0, "-", "-");
        cb_aplicacao.addItem(l);
        cb_aplicacaoConsulta.addItem(l);
        for (InventarioBeans frota : listInventario) {
            cb_aplicacao.addItem(frota);
            cb_aplicacaoConsulta.addItem(frota);
        }
    }

    private void carregarAlmoxarifado() {
        if (listAlmoxarifado == null) {
            listAlmoxarifado = DiversasD.ListAlmoxarifado(UsuarioLogado);
        }
        listAlmoxarifado.forEach((a) -> {
            cb_almoxarifado.addItem(a);
        });
    }

    private JTable carregarTabela() {
        tb_itens.setModel(getTableModel());
        ((DefaultTableCellRenderer) tb_itens.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        Centralizar.centralizarTabela(tb_itens);
        for (int c = 0; c < TbItens.getColumnCount(); c++) {
            if (c == TbItens.ID || c == TbItens.FAZENDA || c == TbItens.DATA || c == TbItens.ID_PEDIDO || c == TbItens.ID_COMPRA || c == TbItens.ID_INVENTARIO
                    || c == TbItens.ID_SETOR || c == TbItens.ID_SOLICITACAO || c == TbItens.ID_SOLICITANTE || c == TbItens.ID_STATUS_ITEM
                    || c == TbItens.ID_URGENCIA) {
                tb_itens.getColumnModel().getColumn(c).setMaxWidth(0);
                tb_itens.getColumnModel().getColumn(c).setMinWidth(0);
                tb_itens.getColumnModel().getColumn(c).setPreferredWidth(0);
            }
        }
        tb_itens.getColumnModel().getColumn(TbItens.N_ITEM).setPreferredWidth(80);
        tb_itens.getColumnModel().getColumn(TbItens.CODIGO).setPreferredWidth(90);
        tb_itens.getColumnModel().getColumn(TbItens.DESCRICAO).setPreferredWidth(180);
        tb_itens.getColumnModel().getColumn(TbItens.INVENTARIO).setPreferredWidth(100);
        tb_itens.getColumnModel().getColumn(TbItens.STATUS_ITEM).setPreferredWidth(80);
        tb_itens.getColumnModel().getColumn(TbItens.ID_ITEM).setPreferredWidth(80);
        tb_itens.getColumnModel().getColumn(TbItens.QUANTIDADE).setPreferredWidth(70);

        return tb_itens;
    }

    private JTable carregarTabelaConsulta() {
        tb_consulta.setModel(getTableModelConsulta());
        ((DefaultTableCellRenderer) tb_consulta.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        Centralizar.centralizarTabela(tb_consulta);
        //cellRenderer = new TableModelCellRenderer();
        for (int c = 0; c < TbConsulta.getColumnCount(); c++) {
            if (c == TbConsulta.ID || c == TbConsulta.ID_COMPRA || c == TbConsulta.ID_INVENTARIO || c == TbConsulta.ID_SETOR
                    || c == TbConsulta.ID_SOLICITANTE || c == TbConsulta.ID_STATUS_ITEM || c == TbConsulta.ID_URGENCIA) {
                tb_consulta.getColumnModel().getColumn(c).setMaxWidth(0);
                tb_consulta.getColumnModel().getColumn(c).setMinWidth(0);
                tb_consulta.getColumnModel().getColumn(c).setPreferredWidth(0);
            }
        }
        tb_consulta.getColumnModel().getColumn(TbConsulta.N_ITEM).setPreferredWidth(80);
        tb_consulta.getColumnModel().getColumn(TbConsulta.CODIGO).setPreferredWidth(90);
        tb_consulta.getColumnModel().getColumn(TbConsulta.DESCRICAO).setPreferredWidth(180);
        tb_consulta.getColumnModel().getColumn(TbConsulta.INVENTARIO).setPreferredWidth(100);
        tb_consulta.getColumnModel().getColumn(TbConsulta.STATUS_ITEM).setPreferredWidth(80);
        tb_consulta.getColumnModel().getColumn(TbConsulta.ID_ITEM).setPreferredWidth(80);
        tb_consulta.getColumnModel().getColumn(TbConsulta.QUANTIDADE).setPreferredWidth(70);
        return tb_consulta;
    }

    private TableModel getTableModel() {
        if (TbItens == null) {
            TbItens = new TableModelPedidoMercadoria();
        }
        return TbItens;
    }

    private TableModel getTableModelConsulta() {
        if (TbConsulta == null) {
            TbConsulta = new TableModelPedidoMercadoria();
        }
        return TbConsulta;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupEditar = new javax.swing.JPopupMenu();
        jMenuEditar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuVisualizar = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuEnviarRemessa = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        javax.swing.JPanel jPanel3 = new javax.swing.JPanel();
        txt_data =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel lbl_fazenda = new javax.swing.JLabel();
        cb_fazenda = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        txt_idPedido = new javax.swing.JTextField();
        javax.swing.JLabel lbl_fazenda1 = new javax.swing.JLabel();
        cb_almoxarifado = new javax.swing.JComboBox<>();
        javax.swing.JLabel lbl_fazenda2 = new javax.swing.JLabel();
        ch_naoEmitido = new javax.swing.JCheckBox();
        ch_emitido = new javax.swing.JCheckBox();
        javax.swing.JPanel jPanel4 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        txt_idItem = new javax.swing.JTextField();
        btn_pesqItem = new javax.swing.JButton();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        txt_quantidade = new javax.swing.JTextField();
        javax.swing.JLabel jLabel13 = new javax.swing.JLabel();
        txt_unidade = new javax.swing.JTextField();
        javax.swing.JLabel jLabel14 = new javax.swing.JLabel();
        btn_add = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        btn_del = new javax.swing.JButton();
        txt_descricao = new javax.swing.JTextField();
        javax.swing.JLabel jLabel15 = new javax.swing.JLabel();
        cb_aplicacao = new componentes.UJComboBox();
        javax.swing.JLabel jLabel33 = new javax.swing.JLabel();
        cb_setor = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        txt_solicitante = new javax.swing.JTextField();
        ch_lote = new javax.swing.JCheckBox();
        cb_urgencia = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel16 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_itens = new javax.swing.JTable();
        btn_novo = new javax.swing.JButton();
        btn_salvar = new javax.swing.JButton();
        btn_editarPedido = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
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
        ch_emitido1 = new javax.swing.JCheckBox();
        ch_naoEmitido1 = new javax.swing.JCheckBox();
        btn_pesquisar = new javax.swing.JButton();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        cb_categoria = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        cb_marca = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel20 = new javax.swing.JLabel();
        cb_modelo = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel34 = new javax.swing.JLabel();
        cb_setorConsulta = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel35 = new javax.swing.JLabel();
        cb_fazendaConsulta = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_consulta = new javax.swing.JTable();

        jMenuEditar.setText("Editar Pedido");
        jMenuEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEditarActionPerformed(evt);
            }
        });
        jPopupEditar.add(jMenuEditar);
        jPopupEditar.add(jSeparator1);

        jMenuVisualizar.setText("Visualizar");
        jMenuVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuVisualizarActionPerformed(evt);
            }
        });
        jPopupEditar.add(jMenuVisualizar);
        jPopupEditar.add(jSeparator2);

        jMenuEnviarRemessa.setText("Enviar p/ Remessa");
        jPopupEditar.add(jMenuEnviarRemessa);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Gerar Pedido");

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_data.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Data");

        lbl_fazenda.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_fazenda.setText("Fazenda");

        cb_fazenda.setEnabled(false);
        cb_fazenda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_fazendaItemStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("ID");

        txt_idPedido.setEditable(false);
        txt_idPedido.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lbl_fazenda1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_fazenda1.setText("Almxorifado");

        cb_almoxarifado.setEnabled(false);
        cb_almoxarifado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_almoxarifadoItemStateChanged(evt);
            }
        });

        lbl_fazenda2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_fazenda2.setText("Status Pedido");

        buttonGroup1.add(ch_naoEmitido);
        ch_naoEmitido.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_naoEmitido.setText("Não Emitido");

        buttonGroup1.add(ch_emitido);
        ch_emitido.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_emitido.setSelected(true);
        ch_emitido.setText("Emitido");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addComponent(txt_idPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_data, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_fazenda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_fazenda1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cb_almoxarifado, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_fazenda2)
                .addGap(18, 18, 18)
                .addComponent(ch_emitido)
                .addGap(34, 34, 34)
                .addComponent(ch_naoEmitido)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(txt_data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_fazenda)
                    .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txt_idPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_fazenda1)
                    .addComponent(cb_almoxarifado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_fazenda2)
                    .addComponent(ch_naoEmitido)
                    .addComponent(ch_emitido))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("ID");

        txt_idItem.setEditable(false);
        txt_idItem.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btn_pesqItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar pequeno.png"))); // NOI18N
        btn_pesqItem.setPreferredSize(new java.awt.Dimension(30, 25));
        btn_pesqItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesqItemActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Quantidade");

        txt_quantidade.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Unid");

        txt_unidade.setEditable(false);
        txt_unidade.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Aplicação");

        btn_add.setText("add");
        btn_add.setEnabled(false);
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        btn_editar.setText("Editar");
        btn_editar.setEnabled(false);
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });

        btn_del.setText("Del");
        btn_del.setEnabled(false);
        btn_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delActionPerformed(evt);
            }
        });

        txt_descricao.setEditable(false);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Descrição");

        cb_aplicacao.setEditable(true);
        cb_aplicacao.setMaximumRowCount(20);
        cb_aplicacao.setAutocompletar(true);

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel33.setText("Setor");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Solicitante");

        txt_solicitante.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        ch_lote.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_lote.setText("Em Lote");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Urgência");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Código");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_idItem, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn_pesqItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ch_lote)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_codigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_unidade, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_aplicacao, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_setor, 0, 162, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_solicitante, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_urgencia, 0, 132, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_add)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_editar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_del)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5)
                    .addComponent(txt_idItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesqItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txt_unidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(txt_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ch_lote)
                        .addComponent(jLabel16)
                        .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel9)
                    .addComponent(txt_solicitante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel33)
                        .addComponent(cb_setor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_add)
                    .addComponent(btn_editar)
                    .addComponent(btn_del)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel14)
                        .addComponent(cb_aplicacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addComponent(txt_quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cb_urgencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap())
        );

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
        });
        jScrollPane1.setViewportView(tb_itens);

        btn_novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/inserir_40x40.png"))); // NOI18N
        btn_novo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_novo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novoActionPerformed(evt);
            }
        });

        btn_salvar.setBackground(new java.awt.Color(255, 255, 255));
        btn_salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/salvar.png"))); // NOI18N
        btn_salvar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_salvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_salvar.setDisabledIcon(null);
        btn_salvar.setEnabled(false);
        btn_salvar.setMaximumSize(new java.awt.Dimension(45, 45));
        btn_salvar.setMinimumSize(new java.awt.Dimension(45, 45));
        btn_salvar.setPreferredSize(new java.awt.Dimension(45, 45));
        btn_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salvarActionPerformed(evt);
            }
        });

        btn_editarPedido.setBackground(new java.awt.Color(255, 255, 255));
        btn_editarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/editar_pequeno.png"))); // NOI18N
        btn_editarPedido.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_editarPedido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_editarPedido.setEnabled(false);
        btn_editarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarPedidoActionPerformed(evt);
            }
        });

        btn_excluir.setBackground(new java.awt.Color(255, 255, 255));
        btn_excluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/deletar_40x40.png"))); // NOI18N
        btn_excluir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_excluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_excluir.setEnabled(false);
        btn_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_editarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_editarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Pedido", jPanel2);

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
        cb_aplicacaoConsulta.setPreferredSize(new java.awt.Dimension(160, 20));

        ch_emitido1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_emitido1.setSelected(true);
        ch_emitido1.setText("Emitido");

        ch_naoEmitido1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_naoEmitido1.setText("Não Emitido");

        btn_pesquisar.setText("Pesquisar");
        btn_pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisarActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Categoria");

        cb_categoria.setPreferredSize(new java.awt.Dimension(80, 20));
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

        cb_setorConsulta.setPreferredSize(new java.awt.Dimension(140, 20));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel35.setText("Fazenda");

        cb_fazendaConsulta.setPreferredSize(new java.awt.Dimension(140, 20));
        cb_fazendaConsulta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_fazendaConsultaItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_fazendaConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_setorConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_categoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_marca, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_modelo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_aplicacaoConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(5, 5, 5)
                        .addComponent(txt_nPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_idItemConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn_pesqItem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_codigoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_descricaoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ch_emitido1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ch_naoEmitido1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_pesquisar)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(txt_dtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txt_nPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txt_dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txt_idItemConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesqItem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(txt_codigoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18)
                        .addComponent(txt_descricaoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ch_emitido1)
                    .addComponent(ch_naoEmitido1)
                    .addComponent(btn_pesquisar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel35)
                    .addComponent(cb_fazendaConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel34)
                    .addComponent(cb_setorConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11)
                    .addComponent(cb_categoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(cb_marca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_modelo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_aplicacaoConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jPanel5Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.CENTER))
                .addGap(14, 14, 14))
        );

        tb_consulta.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_consulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_consultaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_consulta);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Consulta", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Consulta");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        int excluirSaidaInsumos = JOptionPane.showConfirmDialog(null, "Atenção, Está Ação Excluíra Permanentemente este Registro, Deseja Continuar", "Atenção", JOptionPane.YES_NO_OPTION);
        if (excluirSaidaInsumos == JOptionPane.YES_OPTION) {
            if (true == true) {
                desabilitarCampos();
                limparCampos();
            }
        }
    }//GEN-LAST:event_btn_excluirActionPerformed

    private void btn_editarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarPedidoActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja Editar esta Entrada?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION) {

        }
    }//GEN-LAST:event_btn_editarPedidoActionPerformed

    private void btn_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarActionPerformed
        String caseEmitido = "Deseja Salvar Este Pedido?";
        String caseNAOEmitido = "<html><B>Este Pedido Não Será Emitido, <br>"
                + "deseja Prosseguir?</B></html>";
        int cadastrar = JOptionPane.showConfirmDialog(null, (ch_emitido.isSelected() ? caseEmitido : caseNAOEmitido), "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION) {
            PedidoB = new PedidosAlmoxarifado();
            popularBeans(PedidoB);
            if (verificarBeans(PedidoB)) {
                if (PedidosD.salvar(PedidoB) == true) {
                    if (PedidoB.getStatusEmissao() == true) { // condição necessário para enviar ou não por e-mail caso o pedido não seja emitido
                        RelatoriosPedidosMercadoria.gerarPedido(PedidoB, true);
                    } else {
                        RelatoriosPedidosMercadoria.gerarPedido(PedidoB, false);
                    }
                    desabilitarCampos();
                    limparCampos();
                }
            }
        }
    }//GEN-LAST:event_btn_salvarActionPerformed

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed
        btn_salvar.setEnabled(true);
        btn_editarPedido.setEnabled(false);
        btn_excluir.setEnabled(false);
        btn_pesqItem.setEnabled(true);
        btn_add.setEnabled(true);
        tb_itens.setEnabled(true);
        habilitarCampos();
        limparCampos();
    }//GEN-LAST:event_btn_novoActionPerformed

    private void btn_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delActionPerformed
        int index = tb_itens.getSelectedRow();
        Long ID = (Long) TbItens.getValueAt(index, TbItens.ID);
        if (ID == 0) {
            TbItens.excluirLinha(index);
            btn_add.setEnabled(true);
            btn_editar.setEnabled(false);
            btn_del.setEnabled(false);
            limparCamposItens();
            atualizarNumeroDeItem();
        } else if (ID > 0) {
            int excluirSaidaInsumos = JOptionPane.showConfirmDialog(null, "Está ação irá excluir permanentemente este produto, deseja continuar?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (excluirSaidaInsumos == JOptionPane.YES_OPTION) {
                if (true) {
                    // falta fazet a exclusão do DB
                    TbItens.excluirLinha(index);
                    btn_add.setEnabled(true);
                    btn_editar.setEnabled(false);
                    btn_del.setEnabled(false);
                    atualizarNumeroDeItem();
                    limparCamposItens();
                }
            }
        }
    }//GEN-LAST:event_btn_delActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja Editar este Produto?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION) {
            int index = tb_itens.getSelectedRow();
            EditarProduto(index);
            btn_add.setEnabled(true);
            btn_editar.setEnabled(false);
            btn_del.setEnabled(false);
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        if (!txt_idItem.getText().equals("") && !txt_idItem.getText().equals("0")) {
            AddProduto(PedidoB);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione o Código do Item!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }//GEN-LAST:event_btn_addActionPerformed

    private void btn_pesqItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesqItemActionPerformed

        FrmConsultaPecas consultaPecas = new FrmConsultaPecas();
        this.getParent().add(consultaPecas);
        consultaPecas.tb_pecas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    int index = consultaPecas.tb_pecas.getSelectedRow();
                    Integer IdItem = (Integer) consultaPecas.TbPecas.getValueAt(index, 0);
                    txt_idItem.setText(IdItem.toString());
                    txt_descricao.setText(consultaPecas.TbPecas.getValueAt(index, consultaPecas.TbPecas.DESCRICAO).toString());
                    txt_codigo.setText(consultaPecas.TbPecas.getValueAt(index, consultaPecas.TbPecas.CODIGO).toString());
                    txt_unidade.setText(consultaPecas.TbPecas.getValueAt(index, consultaPecas.TbPecas.UNIDADE).toString());
                    if (ch_lote.isSelected()) {
                        AddProduto(PedidoB);
                        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());
                                tb_itens.setRowSelectionInterval(tb_itens.getRowCount() - 1, tb_itens.getRowCount() - 1);
                            }
                        });

                    } else {
                        consultaPecas.dispose();
                    }
                }
            }
        });
        consultaPecas.setVisible(true);

    }//GEN-LAST:event_btn_pesqItemActionPerformed

    private void cb_fazendaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_fazendaItemStateChanged

    }//GEN-LAST:event_cb_fazendaItemStateChanged

    private void cb_almoxarifadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_almoxarifadoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_almoxarifadoItemStateChanged

    private void tb_itensMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_itensMouseClicked
        int rowIndex = tb_itens.getSelectedRow();
        if (evt.getClickCount() == 2) {
            btn_del.setEnabled(true);
            btn_editar.setEnabled(true);
            btn_add.setEnabled(false);
            if (TbItens.getRowCount() > 0) {
                if (rowIndex >= 0) {
                    preencherCamposItem(TbItens.getBeans(rowIndex));
                }
            }
        } else {
            btn_del.setEnabled(false);
            btn_editar.setEnabled(false);
            btn_add.setEnabled(true);
        }
    }//GEN-LAST:event_tb_itensMouseClicked

    private void btn_pesqItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesqItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_pesqItem1ActionPerformed

    private void cb_categoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_categoriaItemStateChanged
        if (cb_marca.getSelectedIndex() > 0) {
            carregarModelos(getIdCategoria(cb_categoria), getIdMarca(cb_marca));
        }
    }//GEN-LAST:event_cb_categoriaItemStateChanged

    private void cb_marcaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_marcaItemStateChanged
        carregarModelos(getIdCategoria(cb_categoria), getIdMarca(cb_marca));
    }//GEN-LAST:event_cb_marcaItemStateChanged

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        if (jTabbedPane1.getSelectedIndex() == 1) {
            getRootPane().setDefaultButton(btn_pesquisar);
        } else {
            getRootPane().setDefaultButton(null);
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void btn_pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisarActionPerformed
        TbConsulta.limpar();
        TbConsulta.addLista(PedidosD.ConsultaPedidos(getSqlWhere()));
    }//GEN-LAST:event_btn_pesquisarActionPerformed

    private void jMenuEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuEditarActionPerformed
        Long ID = (Long) TbConsulta.getValueAt(tb_consulta.getSelectedRow(), TbConsulta.ID_PEDIDO);
        if (ID > 0) {
            limparCampos();
            PedidoB = new PedidosAlmoxarifado();
            PedidoB = PedidosD.buscarPorCodigo(ID);
            preencherFormulario(PedidoB);
            jTabbedPane1.setSelectedIndex(0);
            habilitarCampos();

        }
    }//GEN-LAST:event_jMenuEditarActionPerformed

    private void tb_consultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_consultaMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            if (jPopupEditar.isVisible() == true) {
                jPopupEditar.setVisible(false);
            } else {
                jPopupEditar.setVisible(true);
                jPopupEditar.show(this, 0, 0);
                jPopupEditar.setLocation(evt.getLocationOnScreen());
            }
        }
    }//GEN-LAST:event_tb_consultaMouseClicked

    private void jMenuVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuVisualizarActionPerformed
        int index = tb_consulta.getSelectedRow();
        RelatoriosPedidosMercadoria.gerarPedido(TbConsulta.getBeans(index).getIdPedido(), false);
    }//GEN-LAST:event_jMenuVisualizarActionPerformed

    private void cb_fazendaConsultaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_fazendaConsultaItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_fazendaConsultaItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_del;
    private javax.swing.JButton btn_editar;
    javax.swing.JButton btn_editarPedido;
    javax.swing.JButton btn_excluir;
    javax.swing.JButton btn_novo;
    javax.swing.JButton btn_pesqItem;
    javax.swing.JButton btn_pesqItem1;
    private javax.swing.JButton btn_pesquisar;
    javax.swing.JButton btn_salvar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<CadAlmoxarifadoBeans> cb_almoxarifado;
    private componentes.UJComboBox cb_aplicacao;
    private componentes.UJComboBox cb_aplicacaoConsulta;
    private javax.swing.JComboBox<Beans.CategoriaEquipamentosBeans> cb_categoria;
    private javax.swing.JComboBox<PropriedadesBeans> cb_fazenda;
    public javax.swing.JComboBox<PropriedadesBeans> cb_fazendaConsulta;
    private javax.swing.JComboBox<MarcaEquipamentosBeans> cb_marca;
    private javax.swing.JComboBox<Beans.ModeloEquipamentosBeans> cb_modelo;
    private javax.swing.JComboBox<ListSetorTrabalhoBeans> cb_setor;
    private javax.swing.JComboBox<ListSetorTrabalhoBeans> cb_setorConsulta;
    private javax.swing.JComboBox<ListUrgenciaPedidoMercadoria> cb_urgencia;
    private javax.swing.JCheckBox ch_emitido;
    private javax.swing.JCheckBox ch_emitido1;
    private javax.swing.JCheckBox ch_lote;
    private javax.swing.JCheckBox ch_naoEmitido;
    private javax.swing.JCheckBox ch_naoEmitido1;
    private javax.swing.JMenuItem jMenuEditar;
    public javax.swing.JMenuItem jMenuEnviarRemessa;
    private javax.swing.JMenuItem jMenuVisualizar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupEditar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    public javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JTable tb_consulta;
    private javax.swing.JTable tb_itens;
    private javax.swing.JTextField txt_codigo;
    private javax.swing.JTextField txt_codigoConsulta;
    private com.toedter.calendar.JDateChooser txt_data;
    private javax.swing.JTextField txt_descricao;
    private javax.swing.JTextField txt_descricaoConsulta;
    private com.toedter.calendar.JDateChooser txt_dtFinal;
    private com.toedter.calendar.JDateChooser txt_dtInicial;
    private javax.swing.JTextField txt_idItem;
    private javax.swing.JTextField txt_idItemConsulta;
    private javax.swing.JTextField txt_idPedido;
    private javax.swing.JTextField txt_nPedido;
    private javax.swing.JTextField txt_quantidade;
    private javax.swing.JTextField txt_solicitante;
    private javax.swing.JTextField txt_unidade;
    // End of variables declaration//GEN-END:variables

    private void desabilitarCampos() {
        txt_data.setEnabled(false);
        cb_fazenda.setEnabled(false);
        btn_add.setEnabled(false);
        cb_almoxarifado.setEnabled(false);
    }

    private void limparCampos() {
        txt_idPedido.setText("");
        txt_data.setDate(null);
        cb_fazenda.setSelectedIndex(0);
        cb_almoxarifado.setSelectedIndex(0);
        txt_idItem.setText("");
        txt_codigo.setText("");
        txt_descricao.setText("");
        txt_unidade.setText("");
        txt_quantidade.setText("0");
        cb_setor.setSelectedIndex(0);
        cb_aplicacao.setSelectedIndex(0);
        cb_urgencia.setSelectedIndex(0);
        txt_solicitante.setText("");
        TbItens.limpar();
    }

    private void popularBeans(PedidosAlmoxarifado pedido) {
        pedido.setData(txt_data.getDate());
        pedido.setIdFazenda(getIdFazenda(cb_fazenda));
        pedido.setStatusEmissao(getStatusPedido());
        pedido.setIdUsuario(IdUsuario);
        pedido.setListaItens(TbItens.getLista());
    }

    private Boolean verificarBeans(PedidosAlmoxarifado pedido) {
        if (pedido.getData() == null) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Data!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (pedido.getIdFazenda() == null) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Fazenda!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (pedido.getListaItens().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O Pedido Não Possuí nenhum Item!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }

        for (int i = 0; i < pedido.getListaItens().size(); i++) {
            if (pedido.getListaItens().get(i).getQuantidade() == 0) {
                JOptionPane.showMessageDialog(null, "O Item número " + (i + 1) + ", está sem quantidade!", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
        }

        return true;
    }

    private void habilitarCampos() {
        txt_data.setEnabled(true);
        cb_fazenda.setEnabled(true);
        cb_almoxarifado.setEnabled(true);
        btn_add.setEnabled(true);
    }

    private void limparCamposItens() {

    }

    private void EditarProduto(int index) {

    }

    private void AddProduto(PedidosAlmoxarifado Pedido) {
        PedidoAlmoxarifadoItens item = new PedidoAlmoxarifadoItens();
        item.setId(0L);
        item.setIdPedido(Pedido);
        item.setData(txt_data.getDate());
        item.setnItem(TbItens.getRowCount() + 1);
        item.setIdSolicitante(0);
        item.setSolicitante(txt_solicitante.getText());
        item.setIdItem(new Integer(txt_idItem.getText()));
        item.setCodigo(txt_codigo.getText());
        item.setDescricao(txt_descricao.getText());
        item.setQuantidade(Corretores.ConverterStringDouble(txt_quantidade.getText()));
        item.setUnidade(txt_unidade.getText());
        item.setIdSetor(getIdSetor(cb_setor));
        item.setSetor(cb_setor.getSelectedItem().toString());
        item.setIdInventario(getIdAplicacao(cb_aplicacao));
        item.setInventario(cb_aplicacao.getSelectedItem().toString());
        item.setIdUrgencia(getIdUrgencia(cb_urgencia));
        item.setUrgencia(cb_urgencia.getSelectedItem().toString());
        item.setIdStatusItem(0);
        item.setIdSolicitacao(null);
        item.setIdCompra(0);
        item.setStatus_pedido(false);
        if (verificarItem(item)) {
            TbItens.addBeans(item);
        }

    }

    private Boolean verificarItem(PedidoAlmoxarifadoItens item) {
        for (int i = 0; i < TbItens.getRowCount(); i++) {
            PedidoAlmoxarifadoItens item2 = TbItens.getBeans(i);
            if (item.getIdItem() == item2.getIdItem()) {
                int cadastrar = JOptionPane.showConfirmDialog(null, "<html> <B>Este Item já se Encontra da Lista!</B> <br> Deseja incluí-lo novamente? </html>", "Atenção", JOptionPane.YES_NO_OPTION);
                return cadastrar == JOptionPane.YES_OPTION;
            }
        }
        return true;
    }

    private void AddProdutoEmLote() {

    }

    private PropriedadesBeans getIdFazenda(JComboBox<PropriedadesBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return (PropriedadesBeans) comboBox.getSelectedItem();
        }
        return null;
    }

    private Integer getIdUrgencia(JComboBox<ListUrgenciaPedidoMercadoria> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return comboBox.getItemAt(comboBox.getSelectedIndex()).getId();
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

    private Boolean getStatusPedido() {
        if (ch_emitido.isSelected()) {
            return true;
        } else {
            return false;
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

    private void setJComboBoxSetor(JComboBox<ListSetorTrabalhoBeans> cb, Integer IdSetor) {
        for (int i = 0; i < cb.getModel().getSize(); i++) {
            if (cb.getItemAt(i).getID() == IdSetor) {
                cb.setSelectedIndex(i);
                break;
            }
        }
    }

    public void setJComboBoxFazenda(JComboBox<PropriedadesBeans> cb, Integer IdFazenda) {
        for (int i = 0; i < cb.getModel().getSize(); i++) {
            if (cb.getItemAt(i).getCodigo() == IdFazenda) {
                cb.setSelectedIndex(i);
                break;
            }
        }
    }

    private void setJComboBoxAlmoxarifado(JComboBox<CadAlmoxarifadoBeans> cb, Integer ID) {
        for (int i = 0; i < cb.getModel().getSize(); i++) {
            if (cb.getItemAt(i).getID() == ID) {
                cb.setSelectedIndex(i);
                break;
            }
        }
    }

    private void setJComboBoxUrgencia(JComboBox<ListUrgenciaPedidoMercadoria> cb, Integer ID) {
        for (int i = 0; i < cb.getModel().getSize(); i++) {
            if (cb.getItemAt(i).getId() == ID) {
                cb.setSelectedIndex(i);
                break;
            }
        }
    }

    private void setComboBoxAplicacao(UJComboBox comboBox, Integer ID) {
        comboBox.setSelectedIndex(0);
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            InventarioBeans aplicacao = (InventarioBeans) cb_aplicacao.getItemAt(i);
            if (Objects.equals(aplicacao.getID(), ID)) {
                comboBox.setSelectedIndex(i);
                break;
            }
        }
    }

    private void atualizarNumeroDeItem() {
        for (int i = 0; i < TbItens.getRowCount(); i++) {
            TbItens.setValueAt(i + 1, i, TbItens.N_ITEM);
        }
    }

    private void preencherCamposItem(PedidoAlmoxarifadoItens item) {
        txt_idItem.setText(String.valueOf(item.getIdItem()));
        txt_codigo.setText(item.getCodigo());
        txt_descricao.setText(item.getDescricao());
        txt_unidade.setText(item.getUnidade());
        txt_quantidade.setText(new DecimalFormat("#,##0.00").format(item.getQuantidade()));
        setComboBoxAplicacao(cb_aplicacao, item.getIdInventario());
        setJComboBoxSetor(cb_setor, item.getIdSetor());
        setJComboBoxUrgencia(cb_urgencia, item.getIdUrgencia());
        txt_solicitante.setText(item.getSolicitante());
    }

    private String getSqlWhere() {
        String s = "";
        System.out.println(s);
        String joinInventario = "";

        if (cb_fazendaConsulta.getSelectedIndex() == 0) {
            s += " and p.id_fazenda IN " + DiversasHibernate.getIdFazendaPermitida();
        } else {
            s += " and p.id_fazenda = " + getIdFazenda(cb_fazendaConsulta).getCodigo();
        }
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
        if (cb_setorConsulta.getSelectedIndex() > 0) {
            s += " and pi.id_setor = " + getIdSetor(cb_setorConsulta);
        }
        if (cb_aplicacaoConsulta.getSelectedIndex() > 0) {
            s += " and pi.id_inventario = " + getIdAplicacao(cb_aplicacaoConsulta);
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
        if (ch_emitido1.isSelected() && !ch_naoEmitido1.isSelected()) {
            s += " and p.status_emissao = 1";
        } else if (!ch_emitido1.isSelected() && ch_naoEmitido1.isSelected()) {
            s += " and p.status_emissao = 0";
        } else {
            s += "";
        }
        if (!s.equals("")) {
            s = " WHERE " + s.replaceFirst("and", "");
        }
        if (!joinInventario.equals("")) { // condição deve vir por último para fazer a junção com o Join com cad_inventario
            s = joinInventario + s;
        }
        //System.out.println(s);
        return s;
    }

    private void preencherFormulario(PedidosAlmoxarifado pedido) {
        txt_idPedido.setText(pedido.getId().toString());
        txt_data.setDate(pedido.getData());
        setJComboBoxFazenda(cb_fazenda, pedido.getIdFazenda().getCodigo());
        if (pedido.getStatusEmissao()) { // caso o pedido já tenha sido emitido
            ch_emitido.setSelected(true);
            btn_editarPedido.setEnabled(false);
            btn_excluir.setEnabled(false);
            btn_salvar.setEnabled(false);
            tb_itens.setEnabled(false);
        } else {// caso o pedido NÂO tenha sido emitido
            ch_naoEmitido.setSelected(true);
            btn_editarPedido.setEnabled(true);
            btn_excluir.setEnabled(true);
            tb_itens.setEnabled(true);
            btn_salvar.setEnabled(false);
        }
        TbItens.limpar();
        TbItens.addLista(pedido.getListaItens());
    }

}
