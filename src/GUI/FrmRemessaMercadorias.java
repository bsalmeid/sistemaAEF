package GUI;

import Beans.CadUnidadesBeans;
import Beans.InventarioBeans;
import Beans.PedidoAlmoxarifadoItens;
import Beans.PedidosAlmoxarifadoFechamentoItens;
import Beans.PropriedadesBeans;
import Beans.RemessaMercadoriaBeans;
import Beans.RemessaMercadoriaItens;
import DAO.Diversas;
import DAO.DiversasHibernate;
import DAO.RemessasDAO;
import static GUI.Principal.listInventario;
import static GUI.Principal.listStatusItemPedido;
import static GUI.Principal.listUnidades;
import static GUI.Principal.listaFazPermitida;
import GerarRelatorios.RelatoriosPedidosMercadoria;
import Icones.FormatarICO;
import TableModel.TableModelPedidoMercadoria;
import TableModel.TableModelPedidosFechamento;
import TableModel.TableModelRemessas;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import Utilitarios.ValidarPermissoes;
import com.toedter.calendar.JDateChooser;
import componentes.UJComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class FrmRemessaMercadorias extends javax.swing.JInternalFrame {

    Diversas DiversasD;
    TableModelRemessas TbItens;
    CentralizarTabela Centralizar;
    MaskFormatter FormatoPlaca;
    RemessaMercadoriaBeans RemessaB;
    PedidoAlmoxarifadoItens itemSelecionado;
    RemessasDAO remessaD;

    public FrmRemessaMercadorias() {
        initComponents();
        Centralizar = new CentralizarTabela();
        DiversasD = new Diversas();
        remessaD = new RemessasDAO();
        TbItens = new TableModelRemessas();
        RemessaB = new RemessaMercadoriaBeans();
        carregarStatusItem();
        carregarFazPermitidas();
        carregarUnidades();
        carregarTabela();
        carregarInventario();
        txt_placa.setFormatterFactory(new DefaultFormatterFactory(MaskFormatterPlaca()));
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

    private void carregarStatusItem() {
        if (listStatusItemPedido == null) {
            listStatusItemPedido = DiversasD.ListarStatusItemPedido();
        }
    }

    private void carregarInventario() {
        if (listInventario == null) {
            listInventario = DiversasHibernate.getListaInventario();
        }
        cb_aplicacao.addItem(new InventarioBeans(0, "-", "-"));
        for (InventarioBeans frota : listInventario) {
            cb_aplicacao.addItem(frota);
        }
    }

    private void carregarFazPermitidas() {
        if (listaFazPermitida == null) {
            listaFazPermitida = DiversasHibernate.getListaFazendasPermitidas();
        }
        cb_fazenda.addItem(new PropriedadesBeans(0, "-"));
        for (PropriedadesBeans list : listaFazPermitida) {
            cb_fazenda.addItem(list);
        }
    }

    private JTable carregarTabela() {
        tb_itens.setModel(getTableModel());
        ((DefaultTableCellRenderer) tb_itens.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        Centralizar.centralizarTabela(tb_itens);
        for (int c = 0; c < TbItens.getColumnCount(); c++) {
            if (c == TbItens.ID) {
                tb_itens.getColumnModel().getColumn(c).setMaxWidth(0);
                tb_itens.getColumnModel().getColumn(c).setMinWidth(0);
                tb_itens.getColumnModel().getColumn(c).setPreferredWidth(0);
            }
        }
        tb_itens.getColumnModel().getColumn(TbItens.N_ITEM).setPreferredWidth(80);
        tb_itens.getColumnModel().getColumn(TbItens.CODIGO).setPreferredWidth(90);
        tb_itens.getColumnModel().getColumn(TbItens.DESCRICAO).setPreferredWidth(180);
        tb_itens.getColumnModel().getColumn(TbItens.QUANTIDADE).setPreferredWidth(70);

        return tb_itens;
    }

    private TableModelRemessas getTableModel() {
        if (TbItens == null) {
            TbItens = new TableModelRemessas();
        }
        return TbItens;
    }

    private MaskFormatter MaskFormatterPlaca() {
        try {
            FormatoPlaca = new MaskFormatter("AAA-####");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar máscara", "Erro", 0);
        }
        return FormatoPlaca;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel lbl_fazenda = new javax.swing.JLabel();
        cb_fazenda = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        txt_data =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel lbl_fazenda2 = new javax.swing.JLabel();
        ch_emitido = new javax.swing.JCheckBox();
        ch_naoEmitido = new javax.swing.JCheckBox();
        javax.swing.JLabel lbl_fazenda1 = new javax.swing.JLabel();
        txt_motorista = new javax.swing.JTextField();
        javax.swing.JLabel lbl_fazenda3 = new javax.swing.JLabel();
        txt_placa = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_itens = new javax.swing.JTable();
        btn_novo = new javax.swing.JButton();
        btn_salvar = new javax.swing.JButton();
        btn_editarRemessa = new javax.swing.JButton();
        btn_excluirRemessa = new javax.swing.JButton();
        tp_importar = new org.jdesktop.swingx.JXTaskPane();
        jPanel2 = new javax.swing.JPanel();
        btn_pesquisaPedido = new javax.swing.JButton();
        btn_pesquisaSolicitacao = new javax.swing.JButton();
        btn_pesquisaFechamento = new javax.swing.JButton();
        tp_manual = new org.jdesktop.swingx.JXTaskPane();
        jPanel3 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        txt_idItem = new javax.swing.JTextField();
        btn_pesquisarItem = new javax.swing.JButton();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        txt_nPedido = new javax.swing.JTextField();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        txt_nItemPedido = new javax.swing.JTextField();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        txt_quantidade = new javax.swing.JTextField();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        cb_unidade = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        txt_descricao = new javax.swing.JTextField();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        txt_solicitante = new javax.swing.JTextField();
        btn_add = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();
        javax.swing.JLabel jLabel14 = new javax.swing.JLabel();
        cb_aplicacao = new componentes.UJComboBox();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Remessa de Mercadoria");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_fazenda.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_fazenda.setText("Fazenda");

        cb_fazenda.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Data");

        lbl_fazenda2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_fazenda2.setText("Status Remessa");

        buttonGroup1.add(ch_emitido);
        ch_emitido.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_emitido.setText("Emitido");

        buttonGroup1.add(ch_naoEmitido);
        ch_naoEmitido.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_naoEmitido.setSelected(true);
        ch_naoEmitido.setText("Não Emitido");

        lbl_fazenda1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_fazenda1.setText("Motorista");

        txt_motorista.setEnabled(false);

        lbl_fazenda3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_fazenda3.setText("Placa");

        txt_placa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_placa.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_data, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbl_fazenda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_fazenda1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_motorista, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_fazenda3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_placa, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                .addComponent(lbl_fazenda2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ch_emitido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ch_naoEmitido)
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(txt_data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_fazenda)
                    .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_fazenda2)
                    .addComponent(ch_naoEmitido)
                    .addComponent(ch_emitido)
                    .addComponent(lbl_fazenda1)
                    .addComponent(txt_motorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_fazenda3)
                    .addComponent(txt_placa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        btn_salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/salvar_32_32.png"))); // NOI18N
        btn_salvar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_salvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_salvar.setEnabled(false);
        btn_salvar.setMaximumSize(new java.awt.Dimension(45, 45));
        btn_salvar.setMinimumSize(new java.awt.Dimension(45, 45));
        btn_salvar.setPreferredSize(new java.awt.Dimension(45, 45));
        btn_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salvarActionPerformed(evt);
            }
        });

        btn_editarRemessa.setBackground(new java.awt.Color(255, 255, 255));
        btn_editarRemessa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/editar_pequeno.png"))); // NOI18N
        btn_editarRemessa.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_editarRemessa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_editarRemessa.setEnabled(false);
        btn_editarRemessa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarRemessaActionPerformed(evt);
            }
        });

        btn_excluirRemessa.setBackground(new java.awt.Color(255, 255, 255));
        btn_excluirRemessa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/deletar_40x40.png"))); // NOI18N
        btn_excluirRemessa.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_excluirRemessa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_excluirRemessa.setEnabled(false);
        btn_excluirRemessa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirRemessaActionPerformed(evt);
            }
        });

        tp_importar.setTitle("Importar Itens");
        tp_importar.setAnimated(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_pesquisaPedido.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_pesquisaPedido.setText("Itens do Pedido");
        btn_pesquisaPedido.setEnabled(false);
        btn_pesquisaPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisaPedidoActionPerformed(evt);
            }
        });

        btn_pesquisaSolicitacao.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_pesquisaSolicitacao.setText("Itens da Solicitação");
        btn_pesquisaSolicitacao.setEnabled(false);
        btn_pesquisaSolicitacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisaSolicitacaoActionPerformed(evt);
            }
        });

        btn_pesquisaFechamento.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_pesquisaFechamento.setText("Itens do Fechamento");
        btn_pesquisaFechamento.setEnabled(false);
        btn_pesquisaFechamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisaFechamentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_pesquisaPedido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_pesquisaSolicitacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_pesquisaFechamento)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_pesquisaPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(btn_pesquisaFechamento)
                        .addComponent(btn_pesquisaSolicitacao)))
                .addContainerGap())
        );

        tp_importar.getContentPane().add(jPanel2);

        tp_manual.setExpanded(false);
        tp_manual.setTitle("Inserir Itens Manualmente");
        tp_manual.setAnimated(false);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Item Pedido");

        txt_idItem.setEditable(false);
        txt_idItem.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btn_pesquisarItem.setText("jButton4");
        btn_pesquisarItem.setEnabled(false);
        btn_pesquisarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisarItemActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Nº Pedido");

        txt_nPedido.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Nº Item");

        txt_nItemPedido.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Quant.");

        txt_quantidade.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Unid.");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Código");

        txt_codigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Descrição");

        txt_descricao.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Solicitante");

        txt_solicitante.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btn_add.setText("Adicionar");
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

        btn_excluir.setText("Excluir");
        btn_excluir.setEnabled(false);
        btn_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Aplicação");

        cb_aplicacao.setEditable(true);
        cb_aplicacao.setMaximumRowCount(20);
        cb_aplicacao.setAutocompletar(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_idItem, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_pesquisarItem, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nItemPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_unidade, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_codigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_solicitante))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_aplicacao, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_add)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_editar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_excluir)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txt_solicitante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(cb_unidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txt_quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(txt_nItemPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_nPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(btn_pesquisarItem)
                            .addComponent(txt_idItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_add, javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(btn_editar, javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(btn_excluir, javax.swing.GroupLayout.Alignment.CENTER)
                            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(jLabel14)
                                .addComponent(cb_aplicacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jPanel3Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel8)))
                .addContainerGap())
        );

        tp_manual.getContentPane().add(jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_editarRemessa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_excluirRemessa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tp_importar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tp_manual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tp_importar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tp_manual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_editarRemessa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_excluirRemessa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        addItem();
    }//GEN-LAST:event_btn_addActionPerformed

    private void btn_pesquisaSolicitacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisaSolicitacaoActionPerformed
        if (cb_fazenda.getSelectedIndex() > 0) {
            FrmPedidosSolicitacao frmSolicitacao = new FrmPedidosSolicitacao();
            frmSolicitacao.jMenuEnviarRemessa.setVisible(true);
            frmSolicitacao.jMenuEnviarRemessa.addActionListener(ActionListerSolicitacao(frmSolicitacao));
            this.getParent().add(frmSolicitacao);
            frmSolicitacao.setJComboBoxFazenda(frmSolicitacao.cb_fazenda, getFazenda(cb_fazenda).getCodigo());
            frmSolicitacao.jTabbedPane1.setSelectedIndex(2);
            frmSolicitacao.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "O Campo Fazenda é Obrigatório para Importação!", "Erro", 0, FormatarICO.ICObtnSair());
        }

    }//GEN-LAST:event_btn_pesquisaSolicitacaoActionPerformed

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed
        if (!TbItens.getLista().isEmpty()) {
            int limpar = JOptionPane.showConfirmDialog(null, "Está ação irá apagar todos os dados da tela, Deseja Continuar?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (limpar == JOptionPane.YES_OPTION) {
                btn_salvar.setEnabled(true);
                btn_editarRemessa.setEnabled(false);
                btn_excluir.setEnabled(false);
                btn_add.setEnabled(true);
                novo();
            }
        } else {
            btn_salvar.setEnabled(true);
            btn_editarRemessa.setEnabled(false);
            btn_excluir.setEnabled(false);
            btn_add.setEnabled(true);
            novo();
        }
    }//GEN-LAST:event_btn_novoActionPerformed

    private void btn_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarActionPerformed
        int salvar = JOptionPane.showConfirmDialog(null, "Deseja Salvar esta Remessa?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (salvar == JOptionPane.YES_OPTION) {
            popularBeans(RemessaB);
            if (verificarBeans(RemessaB)) {
                if (remessaD.salvarRemessa(RemessaB)) {
                    int imprimir = JOptionPane.showConfirmDialog(null, "Deseja Imprimir esta Remessa?", "Atenção", JOptionPane.YES_NO_OPTION);
                    if (imprimir == JOptionPane.YES_OPTION) {
                        RelatoriosPedidosMercadoria.gerarRemessa(RemessaB.getId(), RemessaB.getFazendaDestino().getNome());
                    }
                    novo();
                }
            }
        }
    }//GEN-LAST:event_btn_salvarActionPerformed

    private void btn_editarRemessaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarRemessaActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja Editar esta Remessa?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION) {
            popularBeans(RemessaB);
            if (verificarBeans(RemessaB) && ValidarPermissoes.validarPermissaoUpdate(FrmRemessaMercadorias.class.getSimpleName())) {
                if (remessaD.editatRemessa(RemessaB)) {
                    novo();
                }
            }
        }
    }//GEN-LAST:event_btn_editarRemessaActionPerformed

    private void btn_excluirRemessaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirRemessaActionPerformed
        int excluirSaidaInsumos = JOptionPane.showConfirmDialog(null, "Atenção, Está Ação Excluíra Permanentemente este Registro, Deseja Continuar", "Atenção", JOptionPane.YES_NO_OPTION);
        if (excluirSaidaInsumos == JOptionPane.YES_OPTION && ValidarPermissoes.validarPermissaoDelete(FrmRemessaMercadorias.class.getSimpleName())) {
            if (remessaD.deletarRemessa(RemessaB)) {
                novo();
            }
        }
    }//GEN-LAST:event_btn_excluirRemessaActionPerformed

    private void btn_pesquisarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisarItemActionPerformed
        if (cb_fazenda.getSelectedIndex() > 0) {
            FrmPedidosFazenda pedidos = new FrmPedidosFazenda();
            pedidos.tb_consulta.addMouseListener(mouseAdapterPedidosFazenda(pedidos));
            this.getParent().add(pedidos);
            pedidos.setJComboBoxFazenda(pedidos.cb_fazendaConsulta, getFazenda(cb_fazenda).getCodigo());
            pedidos.jTabbedPane1.setSelectedIndex(1);
            pedidos.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "O Campo Fazenda é Obrigatório para Importação!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }//GEN-LAST:event_btn_pesquisarItemActionPerformed

    private void btn_pesquisaPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisaPedidoActionPerformed
        if (cb_fazenda.getSelectedIndex() > 0) {
            FrmPedidosFazenda pedidos = new FrmPedidosFazenda();
            pedidos.jMenuEnviarRemessa.addActionListener(ActionListerPedidoFazendas(pedidos));
            this.getParent().add(pedidos);
            pedidos.setJComboBoxFazenda(pedidos.cb_fazendaConsulta, getFazenda(cb_fazenda).getCodigo());
            pedidos.jTabbedPane1.setSelectedIndex(1);
            pedidos.setVisible(true);
            pedidos.jMenuEnviarRemessa.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "O Campo Fazenda é Obrigatório para Importação!", "Erro", 0, FormatarICO.ICObtnSair());
        }

    }//GEN-LAST:event_btn_pesquisaPedidoActionPerformed

    private void tb_itensMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_itensMouseClicked
        int rowIndex = tb_itens.getSelectedRow();
        if (rowIndex >= 0) {
            if (evt.getClickCount() == 2) {
                preencherCampos(TbItens.getBeans(rowIndex));
                btn_editar.setEnabled(true);
                btn_excluir.setEnabled(true);
                btn_add.setEnabled(false);
            } else {
                limparCamposItens();
                btn_add.setEnabled(true);
                btn_editar.setEnabled(false);
                btn_excluir.setEnabled(false);
            }
        }
    }//GEN-LAST:event_tb_itensMouseClicked

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja Editar este Item?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION) {
            int rowIndex = tb_itens.getSelectedRow();
            editarItem(TbItens.getBeans(rowIndex), rowIndex);
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        int rowIndex = tb_itens.getSelectedRow();
        if (rowIndex > 0) {
            RemessaMercadoriaItens item = TbItens.getBeans(rowIndex);
            if (item.getID() != null) {
                int excluir = JOptionPane.showConfirmDialog(null, "Atenção, Está Ação Excluíra Permanentemente este Item, Deseja Continuar", "Atenção", JOptionPane.YES_NO_OPTION);
                if (excluir == JOptionPane.YES_OPTION && ValidarPermissoes.validarPermissaoDelete(FrmRemessaMercadorias.class.getSimpleName())) {
                    if (remessaD.deletarItem(item)) {
                        TbItens.excluirLinha(rowIndex);
                    }
                }
            } else {
                TbItens.excluirLinha(rowIndex);
            }
        }
    }//GEN-LAST:event_btn_excluirActionPerformed

    private void btn_pesquisaFechamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisaFechamentoActionPerformed
        if (cb_fazenda.getSelectedIndex() > 0) {
            FrmPedidosFechamento frmFechamento = new FrmPedidosFechamento();
            this.getParent().add(frmFechamento);
            frmFechamento.setVisible(true);
            frmFechamento.jTabbedPane1.setSelectedIndex(1);
            frmFechamento.jMenuEnviarRemessa.setVisible(true);
            frmFechamento.jMenuEnviarRemessa.addActionListener(ActionListerFechamento(frmFechamento));
        } else {
            JOptionPane.showMessageDialog(null, "O Campo Fazenda é Obrigatório para Importação!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }//GEN-LAST:event_btn_pesquisaFechamentoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_editar;
    javax.swing.JButton btn_editarRemessa;
    private javax.swing.JButton btn_excluir;
    javax.swing.JButton btn_excluirRemessa;
    javax.swing.JButton btn_novo;
    private javax.swing.JButton btn_pesquisaFechamento;
    private javax.swing.JButton btn_pesquisaPedido;
    private javax.swing.JButton btn_pesquisaSolicitacao;
    private javax.swing.JButton btn_pesquisarItem;
    javax.swing.JButton btn_salvar;
    private javax.swing.ButtonGroup buttonGroup1;
    private componentes.UJComboBox cb_aplicacao;
    private javax.swing.JComboBox<PropriedadesBeans> cb_fazenda;
    private javax.swing.JComboBox<CadUnidadesBeans> cb_unidade;
    private javax.swing.JCheckBox ch_emitido;
    private javax.swing.JCheckBox ch_naoEmitido;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_itens;
    public org.jdesktop.swingx.JXTaskPane tp_importar;
    public org.jdesktop.swingx.JXTaskPane tp_manual;
    private javax.swing.JTextField txt_codigo;
    private com.toedter.calendar.JDateChooser txt_data;
    private javax.swing.JTextField txt_descricao;
    private javax.swing.JTextField txt_idItem;
    private javax.swing.JTextField txt_motorista;
    private javax.swing.JTextField txt_nItemPedido;
    private javax.swing.JTextField txt_nPedido;
    private javax.swing.JFormattedTextField txt_placa;
    private javax.swing.JTextField txt_quantidade;
    private javax.swing.JTextField txt_solicitante;
    // End of variables declaration//GEN-END:variables

    private void novo() {
        habilitarCampos(true);
        limparCamposItens();
        limparCamposRemessa();
        RemessaB = new RemessaMercadoriaBeans();
        btn_salvar.setEnabled(true);
        btn_add.setEnabled(true);
        btn_editarRemessa.setEnabled(false);
        btn_excluirRemessa.setEnabled(false);
        TbItens.limpar();
    }

    public void preencherCampos(RemessaMercadoriaBeans beans) {
        habilitarCampos(true);
        txt_data.setDate(beans.getDataEnvio());
        txt_motorista.setText(beans.getMotorista());
        txt_placa.setText(beans.getPlacaVeiculo());
        setJComboBoxFazenda(cb_fazenda, beans.getFazendaDestino().getCodigo());
        TbItens.addLista(beans.getListaItens());
        if (beans.getStatus()) {
            ch_emitido.setSelected(beans.getStatus());
            btn_editar.setEnabled(false);
            btn_excluirRemessa.setEnabled(false);
            btn_excluir.setEnabled(false);
        } else {
            ch_naoEmitido.setSelected(true);
            btn_salvar.setEnabled(false);
            btn_add.setEnabled(true);
            btn_editarRemessa.setEnabled(true);
            btn_excluirRemessa.setEnabled(true);
        }
    }

    public void preencherCampos(RemessaMercadoriaItens item) {
        txt_codigo.setText(item.getCodigo());
        txt_descricao.setText(item.getDescricao());
        txt_quantidade.setText(Corretores.ConverterDoubleDecimal(item.getQuantidade()));
        txt_solicitante.setText(item.getRecebedor());
        txt_nItemPedido.setText(item.getnItem());
        txt_nPedido.setText(item.getnPedido());
        setJComboBoxUnidade(cb_unidade, item.getUnidade());
        setJComboBoxInventario(cb_aplicacao, item.getInventario());
        if (item.getItemPedido() != null) {
            itemSelecionado = item.getItemPedido();
            txt_idItem.setText(itemSelecionado.getId().toString());
        }
    }

    private void popularBeans(RemessaMercadoriaBeans beans) {
        beans.setDataEnvio(txt_data.getDate());
        beans.setDataLancamento(new Date(System.currentTimeMillis()));
        beans.setFazendaDestino(getFazenda(cb_fazenda));
        beans.setMotorista(txt_motorista.getText());
        beans.setPlacaVeiculo(txt_placa.getText());
        beans.setUsuario(frmLogin.UsuarioLogadoBeans);
        beans.setListaItens(TbItens.getLista());
        beans.setStatus(ch_emitido.isSelected());
    }

    private void addItem() {
        RemessaMercadoriaItens item = new RemessaMercadoriaItens();
        item.setRemessa(RemessaB);
        item.setCodigo(txt_codigo.getText());
        item.setDescricao(txt_descricao.getText());
        item.setItemPedido(itemSelecionado);
        item.setInventario(getInventario(cb_aplicacao).getnFrota());
        item.setQuantidade(Corretores.ConverterStringDouble(txt_quantidade.getText()));
        item.setRecebedor(txt_solicitante.getText());
        item.setUnidade(getUnidade(cb_unidade).getDescricao());
        item.setnItem(txt_nItemPedido.getText());
        item.setnPedido(txt_nPedido.getText());
        if (verificarBeans(item)) {
            TbItens.addBeans(item);
            limparCamposItens();
        }
    }

    private void editarItem(RemessaMercadoriaItens item, int rowIndex) {
        if (item.getItemPedido() != null) {
            itemSelecionado.setQuantidade(Corretores.ConverterStringDouble(txt_quantidade.getText()));
        } else {
            item.setRemessa(RemessaB);
            item.setCodigo(txt_codigo.getText());
            item.setDescricao(txt_descricao.getText());
            item.setInventario(getInventario(cb_aplicacao).getnFrota());
            item.setQuantidade(Corretores.ConverterStringDouble(txt_quantidade.getText()));
            item.setRecebedor(txt_solicitante.getText());
            item.setUnidade(getUnidade(cb_unidade).getDescricao());
            item.setnItem(txt_nItemPedido.getText());
            item.setnPedido(txt_nPedido.getText());
        }
        item.setItemPedido(itemSelecionado);
        if (verificarBeans(item)) {
            TbItens.editarItem(item, rowIndex);
            limparCamposItens();
            btn_add.setEnabled(true);
            btn_editar.setEnabled(false);
            btn_excluir.setEnabled(false);
        }
    }

    private Boolean verificarBeans(RemessaMercadoriaItens item) {
        if (item.getQuantidade() == 0.00) {
            JOptionPane.showMessageDialog(null, "A Quanticade deve ser maior que 0!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (item.getDescricao().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Descricão!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (item.getRecebedor().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Recebedor!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (item.getUnidade().equals("-")) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Unidade!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }

    private Boolean verificarBeans(RemessaMercadoriaBeans beans) {
        if (beans.getDataEnvio() == null) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Data!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (beans.getFazendaDestino() == null) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Fazenda!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (beans.getMotorista().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Motorista!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (beans.getPlacaVeiculo().equals("   -    ")) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Placa!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (beans.getListaItens().isEmpty()) {
            JOptionPane.showMessageDialog(null, "A Remessa deve conter pelo 1 (um) Item!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }

    private void limparCamposItens() {
        txt_codigo.setText("");
        txt_descricao.setText("");
        txt_quantidade.setText("0");
        txt_solicitante.setText("");
        txt_nItemPedido.setText("");
        txt_nPedido.setText("");
        cb_unidade.setSelectedIndex(0);
        cb_aplicacao.setSelectedIndex(0);
        txt_idItem.setText("");
        itemSelecionado = null;
    }

    private void limparCamposRemessa() {
        txt_data.setDate(new Date());
        cb_fazenda.setSelectedIndex(0);
        txt_motorista.setText("");
        txt_placa.setValue(null);
        ch_naoEmitido.setSelected(true);
    }

    private void habilitarCampos(Boolean b) {
        txt_data.setEnabled(b);
        cb_fazenda.setEnabled(b);
        txt_motorista.setEnabled(b);
        txt_placa.setEnabled(b);
        btn_pesquisarItem.setEnabled(b);
        btn_pesquisaPedido.setEnabled(b);
        btn_pesquisaSolicitacao.setEnabled(b);
        btn_pesquisaFechamento.setEnabled(b);
        btn_add.setEnabled(b);
    }

    private MouseAdapter mouseAdapterPedidosFazenda(FrmPedidosFazenda frmPedidos) {
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int rowIndex = frmPedidos.tb_consulta.getSelectedRow();
                    if (rowIndex >= 0) {
                        PedidoAlmoxarifadoItens item = frmPedidos.TbConsulta.getBeans(rowIndex);
                        itemSelecionado = item;
                        txt_idItem.setText(item.getId().toString());
                        txt_nPedido.setText(item.getIdPedido().getId().toString());
                        txt_nItemPedido.setText(String.valueOf(item.getnItem()));
                        txt_codigo.setText(item.getCodigo());
                        txt_descricao.setText(item.getDescricao());
                        txt_quantidade.setText(Corretores.ConverterDoubleDecimal(item.getQuantidade()));
                        txt_solicitante.setText(item.getSolicitante());
                        setJComboBoxInventario(cb_aplicacao, item.getIdInventario());
                        frmPedidos.dispose();
                    }
                }
            }
        };
        return adapter;
    }

    private ActionListener ActionListerPedidoFazendas(FrmPedidosFazenda frmPedidos) {
        ActionListener adapter = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                int rowIndex = frmPedidos.tb_consulta.getSelectedRow();
                if (rowIndex >= 0) {
                    TbItens.addLista(importarItens(listaSelecionadaResumo(frmPedidos.tb_consulta, frmPedidos.TbConsulta)));
                    frmPedidos.dispose();
                }

            }
        };
        return adapter;
    }

    private ActionListener ActionListerSolicitacao(FrmPedidosSolicitacao frmSolicitacao) {
        ActionListener adapter = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                int rowIndex = frmSolicitacao.tb_consulta.getSelectedRow();
                if (rowIndex >= 0) {
                    TbItens.addLista(importarItens(listaSelecionadaResumo(frmSolicitacao.tb_consulta, frmSolicitacao.TbConsulta)));
                    frmSolicitacao.dispose();
                }

            }
        };
        return adapter;
    }

    private ActionListener ActionListerFechamento(FrmPedidosFechamento frmFechamento) {
        ActionListener adapter = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                int rowIndex = frmFechamento.tb_consulta.getSelectedRow();
                if (rowIndex >= 0) {
                    TbItens.addLista(importarItensFechamento(listaSelecionadaFechamento(frmFechamento.tb_consulta, frmFechamento.TbConsulta)));
                    frmFechamento.dispose();
                }

            }
        };
        return adapter;
    }

    public void setJComboBoxFazenda(JComboBox<PropriedadesBeans> cb, Integer IdFazenda) {
        for (int i = 0; i < cb.getModel().getSize(); i++) {
            if (cb.getItemAt(i).getCodigo() == IdFazenda) {
                cb.setSelectedIndex(i);
                break;
            }
        }
    }

    public void setJComboBoxUnidade(JComboBox<CadUnidadesBeans> cb, String unid) {
        for (int i = 0; i < cb.getModel().getSize(); i++) {
            if (cb.getItemAt(i).getDescricao().equals(unid)) {
                cb.setSelectedIndex(i);
                break;
            }
        }
    }

    public void setJComboBoxInventario(UJComboBox cb, Integer idFrota) {
        for (int i = 0; i < cb.getModel().getSize(); i++) {
            InventarioBeans frota = (InventarioBeans) cb.getItemAt(i);
            if (Objects.equals(frota.getID(), idFrota)) {
                cb.setSelectedIndex(i);
                break;
            }
        }
    }

    public void setJComboBoxInventario(UJComboBox cb, String Frota) {
        for (int i = 0; i < cb.getModel().getSize(); i++) {
            InventarioBeans frota = (InventarioBeans) cb.getItemAt(i);
            if (Objects.equals(frota.getnFrota(), Frota)) {
                cb.setSelectedIndex(i);
                break;
            }
        }
    }

    private PropriedadesBeans getFazenda(JComboBox<PropriedadesBeans> combo) {
        return (PropriedadesBeans) combo.getSelectedItem();
    }

    private InventarioBeans getInventario(UJComboBox combo) {
        return (InventarioBeans) combo.getSelectedItem();
    }

    private CadUnidadesBeans getUnidade(JComboBox<CadUnidadesBeans> combo) {
        return (CadUnidadesBeans) combo.getSelectedItem();
    }

    private List<RemessaMercadoriaItens> importarItens(List<PedidoAlmoxarifadoItens> listaItens) {
        List<RemessaMercadoriaItens> listaRemessa = new ArrayList<>();
        for (int i = 0; i < listaItens.size(); i++) {
            PedidoAlmoxarifadoItens item = listaItens.get(i);
            if (item.getIdPedido().getIdFazenda().getCodigo() == getFazenda(cb_fazenda).getCodigo()) {
                RemessaMercadoriaItens itemRemessa = new RemessaMercadoriaItens();
                itemRemessa.setItemPedido(item);
                itemRemessa.setRemessa(RemessaB);
                itemRemessa.setInventario(item.getInventario());
                itemRemessa.setCodigo(item.getCodigo());
                itemRemessa.setDescricao(item.getDescricao());
                itemRemessa.setQuantidade(item.getQuantidade());
                itemRemessa.setRecebedor(item.getSolicitante());
                itemRemessa.setnItem(String.valueOf(item.getnItem()));
                itemRemessa.setnPedido(item.getIdPedido().getId().toString());
                itemRemessa.setUnidade(item.getUnidade());
                if (contemLista(TbItens.getLista(), itemRemessa)) {
                    int editar = JOptionPane.showConfirmDialog(null, "O Item " + itemRemessa.getCodigo() + " já se Encontra Nesta Remessa, Deseja Inseri-lo novamente?", "Atenção", JOptionPane.YES_NO_OPTION);
                    if (editar == JOptionPane.YES_OPTION) {
                        listaRemessa.add(itemRemessa);
                    }
                } else {
                    listaRemessa.add(itemRemessa);
                }
            } else {
                JOptionPane.showMessageDialog(null, "O Item " + item.getDescricao() + " não está Vinculado a Fazenda de Destino Selecionada!", "Erro", 0, FormatarICO.ICObtnSair());
            }
        }
        return listaRemessa;
    }

    private List<RemessaMercadoriaItens> importarItensFechamento(List<PedidosAlmoxarifadoFechamentoItens> listaItens) {
        List<RemessaMercadoriaItens> listaRemessa = new ArrayList<>();
        for (int i = 0; i < listaItens.size(); i++) {
            PedidosAlmoxarifadoFechamentoItens item = listaItens.get(i);
            if (item.getId_item().getIdPedido().getIdFazenda().getCodigo() == getFazenda(cb_fazenda).getCodigo()) {
                RemessaMercadoriaItens itemRemessa = new RemessaMercadoriaItens();
                itemRemessa.setItemPedido(item.getId_item());
                itemRemessa.setRemessa(RemessaB);
                itemRemessa.setInventario(item.getId_item().getInventario());
                itemRemessa.setCodigo(item.getCodigo());
                itemRemessa.setDescricao(item.getDescricao());
                itemRemessa.setQuantidade(item.getQuantidade());
                itemRemessa.setRecebedor(item.getId_item().getSolicitante());
                itemRemessa.setnItem(String.valueOf(item.getN_item_pedido()));
                itemRemessa.setnPedido(item.getId_pedido().toString());
                itemRemessa.setUnidade(item.getUnidade());
                if (contemLista(TbItens.getLista(), itemRemessa)) {
                    int editar = JOptionPane.showConfirmDialog(null, "O Item " + itemRemessa.getCodigo() + " já se Encontra Nesta Remessa, Deseja Inseri-lo novamente?", "Atenção", JOptionPane.YES_NO_OPTION);
                    if (editar == JOptionPane.YES_OPTION) {
                        listaRemessa.add(itemRemessa);
                    }
                } else {
                    listaRemessa.add(itemRemessa);
                }
            } else {
                JOptionPane.showMessageDialog(null, "O Item " + item.getDescricao() + " não está Vinculado a Fazenda de Destino Selecionada!", "Erro", 0, FormatarICO.ICObtnSair());
            }
        }
        return listaRemessa;
    }

    public List<PedidoAlmoxarifadoItens> listaSelecionadaResumo(JTable tb_resumo, TableModelPedidoMercadoria tableModel) {
        List<PedidoAlmoxarifadoItens> listaItens = new ArrayList<>();
        int[] linhas = tb_resumo.getSelectedRows();
        for (int i = 0; i < linhas.length; i++) {
            PedidoAlmoxarifadoItens item = tableModel.getBeans(linhas[i]);
            switch (item.getIdStatusItem()) { // Veriificar Status ITEM
                case 5: // Status ENVIADO
                    int verificarItemEnviado = JOptionPane.showConfirmDialog(null, "O Item " + item.getCodigo() + " já se Encontra Enviado, Deseja Inseri-lo novamente?", "Atenção", JOptionPane.YES_NO_OPTION);
                    if (verificarItemEnviado == JOptionPane.YES_OPTION) {
                        listaItens.add(item);
                    }
                    break;
                case 6: // Status CANCELADO
                    int verificarItemCancelado = JOptionPane.showConfirmDialog(null, "O Item " + item.getCodigo() + " está CANCELADO, Deseja Inseri-lo novamente?", "Atenção", JOptionPane.YES_NO_OPTION);
                    if (verificarItemCancelado == JOptionPane.YES_OPTION) {
                        listaItens.add(item);
                    }
                    break;
                default:
                    listaItens.add(item);
                    break;
            }
        }
        return listaItens;
    }

    public List<PedidosAlmoxarifadoFechamentoItens> listaSelecionadaFechamento(JTable tb_resumo, TableModelPedidosFechamento tableModel) {
        List<PedidosAlmoxarifadoFechamentoItens> listaItens = new ArrayList<>();
        int[] linhas = tb_resumo.getSelectedRows();
        for (int i = 0; i < linhas.length; i++) {
            PedidosAlmoxarifadoFechamentoItens item = tableModel.getBeans(linhas[i]);
            switch (item.getId_item().getIdStatusItem()) { // Veriificar Status ITEM
                case 5: // Status ENVIADO
                    int verificarItemEnviado = JOptionPane.showConfirmDialog(null, "O Item " + item.getCodigo() + " já se Encontra ENVIADO, Deseja Inseri-lo novamente?", "Atenção", JOptionPane.YES_NO_OPTION);
                    if (verificarItemEnviado == JOptionPane.YES_OPTION) {
                        listaItens.add(item);
                    }
                    break;
                case 6: // Status CANCELADO
                    int verificarItemCancelado = JOptionPane.showConfirmDialog(null, "O Item " + item.getCodigo() + " está CANCELADO, Deseja Inseri-lo novamente?", "Atenção", JOptionPane.YES_NO_OPTION);
                    if (verificarItemCancelado == JOptionPane.YES_OPTION) {
                        listaItens.add(item);
                    }
                    break;
                default:
                    listaItens.add(item);
                    break;
            }
        }
        return listaItens;
    }

    private Boolean contemLista(List<RemessaMercadoriaItens> lista, RemessaMercadoriaItens other) {
        for (RemessaMercadoriaItens r : lista) {
            //  System.out.println("R = " + String.valueOf(r.getItemPedido()) + " - " + String.valueOf(other.getItemPedido()));
            if (r.getID() != null && other.getID() != null) {
                if (Objects.equals(r.getItemPedido().getId(), other.getItemPedido().getId())) {
                    return true;
                }
            }
        }
        return false;
    }

}
