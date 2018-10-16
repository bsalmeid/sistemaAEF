/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Almoxarifado.CadAlmoxarifadoBeans;
import Beans.ListFazendasBeans;
import Beans.InventarioBeans;
import Almoxarifado.LocalizadorAlmoxarifadoBeans;
import Beans.ListSetorTrabalhoBeans;
import Beans.SaidaAlmoxarifadoBeans;
import DAO.Diversas;
import DAO.DiversasHibernate;
import DAO.SaidaAlmoxarifadoDAO;
import static GUI.Principal.listAlmoxarifado;
import static GUI.Principal.listInventario;
import static GUI.Principal.listLocalizadorAlmox;
import static GUI.Principal.listSetorTrabalho;
import static GUI.frmLogin.UsuarioLogado;
import Icones.FormatarICO;
import TableModel.TableModelConsultaSaidaMercadoria;
import TableModel.TableModelProdutosNFe;
import TableModel.TableModelSaidaMercadoria;
import TableModel.TbSaidaAlmoxarifadoBeans;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import com.toedter.calendar.JDateChooser;
import componentes.UJComboBox;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class FrmSaidaAlmoxarifado extends javax.swing.JInternalFrame {

    Diversas DiversasD;
    TableModelSaidaMercadoria TbSaida;
    CentralizarTabela Centralizar;
    SaidaAlmoxarifadoBeans SaidaB;
    SaidaAlmoxarifadoDAO SaidaD;
    String CodigoProduto;
    TableModelConsultaSaidaMercadoria TbConsulta;
    List<ListFazendasBeans> listaFazendas;

    public FrmSaidaAlmoxarifado() {
        initComponents();
        DiversasD = new Diversas();
        Centralizar = new CentralizarTabela();
        SaidaB = new SaidaAlmoxarifadoBeans();
        SaidaD = new SaidaAlmoxarifadoDAO();
        carregarListLocalizador();
        carregarAlmoxarifado();
        carregarFazPermitidas();
        carregarSetores();
        carregarInventario();
        carregarTabela();
        carregarTabelaConsulta();

    }

    private void carregarAlmoxarifado() {
        if (listAlmoxarifado == null) {
            listAlmoxarifado = DiversasD.ListAlmoxarifado(UsuarioLogado);
        }
        listAlmoxarifado.forEach((a) -> {
            cb_almoxarifado.addItem(a);
            cb_almoxarifadoConsult.addItem(a);
        });
    }

    private void carregarFazPermitidas() {
        if (listaFazendas == null) {
            listaFazendas = DiversasD.listaDeFazendas();
        }
        ListFazendasBeans l = new ListFazendasBeans();
        l.setID(0);
        l.setNomeFazenda("-");
        cb_fazenda.addItem(l);
        cb_fazendaConsult.addItem(l);
        for (ListFazendasBeans list : listaFazendas) {
            cb_fazenda.addItem(list);
            cb_fazendaConsult.addItem(list);
        }
    }

    private void carregarListLocalizador() {
        if (listLocalizadorAlmox == null) {
            listLocalizadorAlmox = DiversasD.ListLocalizadorAlmox();
        }
    }

    private void carregarSetores() {
        if (listSetorTrabalho == null) {
            listSetorTrabalho = DiversasD.ListSetoresTrabalho();
        }
        ListSetorTrabalhoBeans l = new ListSetorTrabalhoBeans();
        l.setID(0);
        l.setDescricao("-");
        cb_setor.addItem(l);
        cb_setorConsult.addItem(l);
        for (ListSetorTrabalhoBeans list : listSetorTrabalho) {
            cb_setor.addItem(list);
            cb_setorConsult.addItem(list);
        }
    }

    private void carregarInventario() {
        if (listInventario == null) {
            listInventario = DiversasHibernate.getListaInventario();
        }
        cb_aplicacao.removeAllItems();
        InventarioBeans l = new InventarioBeans();
        l.setID(0);
        l.setCategoria("-");
        l.setnFrota("-");
        l.setModelo("-");
        cb_aplicacao.addItem(l);
        for (InventarioBeans frota : listInventario) {
            cb_aplicacao.addItem(frota);
        }
    }

    private TableModelSaidaMercadoria getTableModel() {
        if (TbSaida == null) {
            TbSaida = new TableModelSaidaMercadoria();
        }
        return TbSaida;
    }

    private TableModelConsultaSaidaMercadoria getTableModelConsulta() {
        if (TbConsulta == null) {
            TbConsulta = new TableModelConsultaSaidaMercadoria();
        }
        return TbConsulta;
    }

    private JTable carregarTabela() {
        tb_pecas.setModel(getTableModel());
        ((DefaultTableCellRenderer) tb_pecas.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        Centralizar.centralizarTabela(tb_pecas);
        //cellRenderer = new TableModelCellRenderer();

        tb_pecas.getColumnModel().getColumn(TbSaida.ID).setMaxWidth(0);
        tb_pecas.getColumnModel().getColumn(TbSaida.ID).setMinWidth(0);
        tb_pecas.getColumnModel().getColumn(TbSaida.ID).setPreferredWidth(0);
        
        tb_pecas.getColumnModel().getColumn(TbSaida.ID_APLICACAO).setMaxWidth(0);
        tb_pecas.getColumnModel().getColumn(TbSaida.ID_APLICACAO).setMinWidth(0);
        tb_pecas.getColumnModel().getColumn(TbSaida.ID_APLICACAO).setPreferredWidth(0);
        
        tb_pecas.getColumnModel().getColumn(TbSaida.ID_LOCALIZADOR).setMaxWidth(0);
        tb_pecas.getColumnModel().getColumn(TbSaida.ID_LOCALIZADOR).setMinWidth(0);
        tb_pecas.getColumnModel().getColumn(TbSaida.ID_LOCALIZADOR).setPreferredWidth(0);
        
        tb_pecas.getColumnModel().getColumn(TbSaida.ID_SETOR_SERVICO).setMaxWidth(0);
        tb_pecas.getColumnModel().getColumn(TbSaida.ID_SETOR_SERVICO).setMinWidth(0);
        tb_pecas.getColumnModel().getColumn(TbSaida.ID_SETOR_SERVICO).setPreferredWidth(0);
        
        return tb_pecas;
    }

    private JTable carregarTabelaConsulta() {
        tb_consulta.setModel(getTableModelConsulta());
        ((DefaultTableCellRenderer) tb_consulta.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        Centralizar.centralizarTabela(tb_consulta);
        //cellRenderer = new TableModelCellRenderer();
        tb_consulta.getColumnModel().getColumn(TbConsulta.ID_APLICACAO).setMaxWidth(0);
        tb_consulta.getColumnModel().getColumn(TbConsulta.ID_APLICACAO).setMinWidth(0);
        tb_consulta.getColumnModel().getColumn(TbConsulta.ID_APLICACAO).setPreferredWidth(0);

        tb_consulta.getColumnModel().getColumn(TbConsulta.ID_SETOR).setMaxWidth(0);
        tb_consulta.getColumnModel().getColumn(TbConsulta.ID_SETOR).setMinWidth(0);
        tb_consulta.getColumnModel().getColumn(TbConsulta.ID_SETOR).setPreferredWidth(0);

        tb_consulta.getColumnModel().getColumn(TbConsulta.ID_LOCALIZADOR).setMaxWidth(0);
        tb_consulta.getColumnModel().getColumn(TbConsulta.ID_LOCALIZADOR).setMinWidth(0);
        tb_consulta.getColumnModel().getColumn(TbConsulta.ID_LOCALIZADOR).setPreferredWidth(0);
    
        return tb_consulta;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupEditar = new javax.swing.JPopupMenu();
        jMenuEditar = new javax.swing.JMenuItem();
        jTabbedPane = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        javax.swing.JPanel jPanel3 = new javax.swing.JPanel();
        txt_data =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        cb_almoxarifado = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        cb_operacao = new javax.swing.JComboBox<>();
        lbl_fazenda = new javax.swing.JLabel();
        cb_fazenda = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        txt_nNf = new javax.swing.JTextField();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        txt_responsavel = new javax.swing.JTextField();
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
        cb_localizador = new componentes.UJComboBox();
        javax.swing.JLabel jLabel17 = new javax.swing.JLabel();
        btn_consultaLocalizador = new javax.swing.JButton();
        cb_aplicacao = new componentes.UJComboBox();
        javax.swing.JLabel jLabel16 = new javax.swing.JLabel();
        txt_obs = new javax.swing.JTextField();
        javax.swing.JLabel jLabel33 = new javax.swing.JLabel();
        cb_setor = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        txt_recebedor = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_pecas = new javax.swing.JTable();
        btn_novo = new javax.swing.JButton();
        btn_salvar = new javax.swing.JButton();
        btn_editarEntrada = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        javax.swing.JPanel jPanel6 = new javax.swing.JPanel();
        txt_dtInicial = new com.toedter.calendar.JDateChooser();
        txt_dtFinal = new com.toedter.calendar.JDateChooser();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        cb_almoxarifadoConsult = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel18 = new javax.swing.JLabel();
        cb_operacaoConsult = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel19 = new javax.swing.JLabel();
        txt_nNfConsult = new javax.swing.JTextField();
        javax.swing.JLabel jLabel22 = new javax.swing.JLabel();
        txt_idItemConsult = new javax.swing.JTextField();
        btn_pesqItem1 = new javax.swing.JButton();
        javax.swing.JLabel jLabel23 = new javax.swing.JLabel();
        txt_descricaoConsult = new javax.swing.JTextField();
        javax.swing.JLabel jLabel24 = new javax.swing.JLabel();
        txt_codigoConsult = new javax.swing.JTextField();
        btn_pesquisar = new javax.swing.JButton();
        javax.swing.JLabel jLabel20 = new javax.swing.JLabel();
        javax.swing.JLabel lbl_fazenda1 = new javax.swing.JLabel();
        cb_fazendaConsult = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel34 = new javax.swing.JLabel();
        cb_setorConsult = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel21 = new javax.swing.JLabel();
        txt_obsConsult = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_consulta = new javax.swing.JTable();

        jMenuEditar.setText("Editar Saida");
        jMenuEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEditarActionPerformed(evt);
            }
        });
        jPopupEditar.add(jMenuEditar);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Saída de Mercadorias");

        jTabbedPane.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jPanel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_data.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Data");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Almoxarifado");

        cb_almoxarifado.setEnabled(false);
        cb_almoxarifado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_almoxarifadoItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Origem");

        cb_operacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Uso", "Transferência", "Outras Saídas" }));
        cb_operacao.setEnabled(false);
        cb_operacao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_operacaoItemStateChanged(evt);
            }
        });

        lbl_fazenda.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_fazenda.setText("Fazenda");

        cb_fazenda.setEnabled(false);
        cb_fazenda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_fazendaItemStateChanged(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Nº Doc");

        txt_nNf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nNf.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Responsável");

        txt_responsavel.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_data, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_almoxarifado, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_operacao, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_fazenda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nNf, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_responsavel)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(txt_data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cb_almoxarifado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cb_operacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_fazenda)
                    .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nNf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txt_responsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
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

        cb_localizador.setEditable(true);
        cb_localizador.setMaximumRowCount(20);
        cb_localizador.setAutocompletar(true);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Localizador");

        btn_consultaLocalizador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar pequeno.png"))); // NOI18N
        btn_consultaLocalizador.setPreferredSize(new java.awt.Dimension(30, 25));
        btn_consultaLocalizador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_consultaLocalizadorActionPerformed(evt);
            }
        });

        cb_aplicacao.setEditable(true);
        cb_aplicacao.setMaximumRowCount(20);
        cb_aplicacao.setAutocompletar(true);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Observação");

        txt_obs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_obsActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel33.setText("Setor");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Recebedor");

        txt_recebedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_unidade, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_quantidade, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_aplicacao, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_localizador, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn_consultaLocalizador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_setor, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_recebedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_obs, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(btn_add)
                            .addComponent(btn_editar)
                            .addComponent(btn_del)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel5)
                            .addComponent(txt_idItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_pesqItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(txt_unidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel6)
                            .addComponent(txt_quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15)
                                .addComponent(txt_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cb_localizador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17)
                            .addComponent(btn_consultaLocalizador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_aplicacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_obs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16)
                                .addComponent(jLabel33)
                                .addComponent(cb_setor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(jLabel9)
                                .addComponent(txt_recebedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

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
        tb_pecas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_pecasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_pecas);

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

        btn_editarEntrada.setBackground(new java.awt.Color(255, 255, 255));
        btn_editarEntrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/editar_pequeno.png"))); // NOI18N
        btn_editarEntrada.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_editarEntrada.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_editarEntrada.setEnabled(false);
        btn_editarEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarEntradaActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_editarEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_editarEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane.addTab("Saídas", jPanel1);

        jPanel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("à");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Almoxarifado");

        cb_almoxarifadoConsult.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_almoxarifadoConsultItemStateChanged(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Operação");

        cb_operacaoConsult.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Compra", "Transferência" }));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Nº Doc");

        txt_nNfConsult.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("ID Cadastro");

        txt_idItemConsult.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btn_pesqItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar pequeno.png"))); // NOI18N
        btn_pesqItem1.setPreferredSize(new java.awt.Dimension(30, 25));
        btn_pesqItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesqItem1ActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("Descrição");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Código");

        btn_pesquisar.setText("Pesquisar");
        btn_pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisarActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Data");

        lbl_fazenda1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_fazenda1.setText("Fazenda");

        cb_fazendaConsult.setEnabled(false);
        cb_fazendaConsult.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_fazendaConsultItemStateChanged(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel34.setText("Setor");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Observação");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_almoxarifadoConsult, 0, 165, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_operacaoConsult, 0, 123, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nNfConsult, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_fazenda1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_fazendaConsult, 0, 179, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_setorConsult, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_idItemConsult, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn_pesqItem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_descricaoConsult)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_codigoConsult)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_obsConsult)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel20)
                    .addComponent(txt_dtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txt_dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(cb_almoxarifadoConsult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(cb_operacaoConsult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(txt_nNfConsult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_fazenda1)
                    .addComponent(cb_fazendaConsult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_pesquisar)
                    .addComponent(txt_codigoConsult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(txt_descricaoConsult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(btn_pesqItem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_idItemConsult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel34)
                        .addComponent(cb_setorConsult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel21)
                    .addComponent(txt_obsConsult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Consultas", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed
        btn_salvar.setEnabled(true);
        btn_editarEntrada.setEnabled(false);
        btn_excluir.setEnabled(false);
        btn_pesqItem.setEnabled(true);
        btn_add.setEnabled(true);
        habilitarCampos();
        limparCampos();
    }//GEN-LAST:event_btn_novoActionPerformed

    private void btn_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(null, "Deseja Salvar esta Entrada de Mercadorias?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION) {
            popularBeans();
            if (verificarBeans(SaidaB)) {
                if (SaidaD.inserirSaida(SaidaB) == true) {
                    desabilitarCampos();
                    limparCampos();
                }
            }
        }
    }//GEN-LAST:event_btn_salvarActionPerformed

    private void btn_editarEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarEntradaActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja Editar esta Entrada?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION) {
            popularBeans();
            if (verificarBeans(SaidaB)) {
                if (SaidaD.editarSaida(SaidaB)) {
                    limparCampos();
                    desabilitarCampos();
                }
            }
        }
    }//GEN-LAST:event_btn_editarEntradaActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        int excluirSaidaInsumos = JOptionPane.showConfirmDialog(null, "Atenção, Está Ação Excluíra Permanentemente este Registro, Deseja Continuar", "Atenção", JOptionPane.YES_NO_OPTION);
        if (excluirSaidaInsumos == JOptionPane.YES_OPTION) {
            if (SaidaD.excluirSaida(SaidaB.getID()) == true) {
                desabilitarCampos();
                limparCampos();
            }
        }
    }//GEN-LAST:event_btn_excluirActionPerformed

    private void cb_almoxarifadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_almoxarifadoItemStateChanged
        ComboBoxLocalizar(getIdAlmoxarifado(cb_almoxarifado), cb_localizador);
    }//GEN-LAST:event_cb_almoxarifadoItemStateChanged

    private void cb_operacaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_operacaoItemStateChanged
        int index = cb_operacao.getSelectedIndex();
        switch (index) {
            case 0:
                lbl_fazenda.setVisible(false);
                cb_fazenda.setVisible(false);
                break;
            case 1: // caso Compras
                lbl_fazenda.setVisible(false);
                cb_fazenda.setVisible(false);
                break;
            case 2: // caso Transferencia
                lbl_fazenda.setVisible(true);
                cb_fazenda.setVisible(true);
            default:
        }

    }//GEN-LAST:event_cb_operacaoItemStateChanged

    private void cb_fazendaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_fazendaItemStateChanged

    }//GEN-LAST:event_cb_fazendaItemStateChanged

    private void btn_pesqItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesqItemActionPerformed
        if (cb_almoxarifado.getSelectedIndex() != 0) {
            FrmConsultaPecas consultaPecas = new FrmConsultaPecas();
            this.getParent().add(consultaPecas);
            consultaPecas.tb_pecas.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    if (me.getClickCount() == 2) {
                        int index = consultaPecas.tb_pecas.getSelectedRow();
                        Integer IdItem = (Integer) consultaPecas.TbPecas.getValueAt(index, 0);
                        txt_idItem.setText(IdItem.toString());
                        setComboBoxLocalizador(SaidaD.getUltimoLocalizadorItem(IdItem, getIdAlmoxarifado(cb_almoxarifado)));
                        txt_descricao.setText(consultaPecas.TbPecas.getValueAt(index, consultaPecas.TbPecas.DESCRICAO).toString());
                        CodigoProduto = consultaPecas.TbPecas.getValueAt(index, consultaPecas.TbPecas.CODIGO).toString();
                        txt_unidade.setText("");
                        consultaPecas.dispose();
                    }
                }
            });
            consultaPecas.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione o Almoxarifado de Destino!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }//GEN-LAST:event_btn_pesqItemActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        if (!txt_idItem.getText().equals("") && !txt_idItem.getText().equals("0")) {
            AddProduto();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione o Código do Item!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }//GEN-LAST:event_btn_addActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja Editar este Produto?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION) {
            int index = tb_pecas.getSelectedRow();
            EditarProduto(index);
            btn_add.setEnabled(true);
            btn_editar.setEnabled(false);
            btn_del.setEnabled(false);
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void btn_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delActionPerformed
        int index = tb_pecas.getSelectedRow();
        Integer ID = (Integer) TbSaida.getValueAt(index, TbSaida.ID);
        if (ID == 0) {
            TbSaida.excluirLinha(index);
            btn_add.setEnabled(true);
            btn_editar.setEnabled(false);
            btn_del.setEnabled(false);
            limparCamposProdutos();
        } else if (ID > 0) {
            int excluirSaidaInsumos = JOptionPane.showConfirmDialog(null, "Está ação irá excluir permanentemente este produto, deseja continuar?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (excluirSaidaInsumos == JOptionPane.YES_OPTION) {
                if (SaidaD.excluirItem(ID)) {
                    TbSaida.excluirLinha(index);
                    btn_add.setEnabled(true);
                    btn_editar.setEnabled(false);
                    btn_del.setEnabled(false);
                    limparCamposProdutos();
                }
            }
        }
    }//GEN-LAST:event_btn_delActionPerformed

    private void btn_consultaLocalizadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_consultaLocalizadorActionPerformed
        if (cb_almoxarifado.getSelectedIndex() != 0) {
            FrmConsultaLocalizadorAlmox ConsultaLocalizador = new FrmConsultaLocalizadorAlmox();
            ConsultaLocalizador.tb_pecas.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    if (me.getClickCount() == 2) {
                        int index = ConsultaLocalizador.tb_pecas.getSelectedRow();
                        int Id_LocalizadorColumn = ConsultaLocalizador.TbProdutos.ID_LOCALIZADOR;
                        setComboBoxLocalizador((Integer) ConsultaLocalizador.TbProdutos.getValueAt(index, Id_LocalizadorColumn));
                        ConsultaLocalizador.dispose();
                    }
                }
            });
            this.getParent().add(ConsultaLocalizador);
            ConsultaLocalizador.setJComboBoxAlmoxarifado(ConsultaLocalizador.cb_almoxarifado, getIdAlmoxarifado(cb_almoxarifado));
            ConsultaLocalizador.txt_idItem.setText(txt_idItem.getText());
            //ConsultaLocalizador.TbProdutos.addLista(EntradaD.consultaEntradaLocalizador(getIdAlmoxarifado(cb_almoxarifado), new Integer(txt_idItem.getText())));
            ConsultaLocalizador.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione o Almoxarifado de Destino!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }//GEN-LAST:event_btn_consultaLocalizadorActionPerformed

    private void txt_obsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_obsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_obsActionPerformed

    private void cb_almoxarifadoConsultItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_almoxarifadoConsultItemStateChanged

    }//GEN-LAST:event_cb_almoxarifadoConsultItemStateChanged

    private void btn_pesqItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesqItem1ActionPerformed
        FrmConsultaPecas consultaPecas = new FrmConsultaPecas();
        this.getParent().add(consultaPecas);
        consultaPecas.tb_pecas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    int index = consultaPecas.tb_pecas.getSelectedRow();
                    txt_idItemConsult.setText(consultaPecas.TbPecas.getValueAt(index, 0).toString());
                    consultaPecas.dispose();
                }
            }
        });
        consultaPecas.setVisible(true);
    }//GEN-LAST:event_btn_pesqItem1ActionPerformed

    private void btn_pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisarActionPerformed
        TbConsulta.limpar();
        if (cb_almoxarifadoConsult.getSelectedIndex() != 0) {
            SaidaD.consultaSaida(TbConsulta, getStringWhere());
        } else {
            JOptionPane.showMessageDialog(null, "Selecione o Almoxarifado para Realizar Consulta!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }//GEN-LAST:event_btn_pesquisarActionPerformed

    private void cb_fazendaConsultItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_fazendaConsultItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_fazendaConsultItemStateChanged

    private void jMenuEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuEditarActionPerformed
        Integer ID = (Integer) TbConsulta.getValueAt(tb_consulta.getSelectedRow(), TbConsulta.ID_SAIDA);
        if (ID > 0) {
            limparCampos();
            SaidaB = SaidaD.getItem(ID);
            preencherFormulario(SaidaB);
            jTabbedPane.setSelectedIndex(0);
            habilitarCampos();
            btn_salvar.setEnabled(false);
            btn_editarEntrada.setEnabled(true);
            btn_excluir.setEnabled(true);
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

    private void tb_pecasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_pecasMouseClicked
        if (evt.getClickCount() == 2) {
            int index = tb_pecas.getSelectedRow();
            preencherDadosProduto(index);
            btn_add.setEnabled(false);
            btn_editar.setEnabled(true);
            btn_del.setEnabled(true);
        } else {
            limparCamposProdutos();
            btn_add.setEnabled(true);
            btn_editar.setEnabled(false);
            btn_del.setEnabled(false);
        }
    }//GEN-LAST:event_tb_pecasMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    javax.swing.JButton btn_consultaLocalizador;
    private javax.swing.JButton btn_del;
    private javax.swing.JButton btn_editar;
    javax.swing.JButton btn_editarEntrada;
    javax.swing.JButton btn_excluir;
    javax.swing.JButton btn_novo;
    javax.swing.JButton btn_pesqItem;
    javax.swing.JButton btn_pesqItem1;
    private javax.swing.JButton btn_pesquisar;
    javax.swing.JButton btn_salvar;
    private javax.swing.JComboBox<CadAlmoxarifadoBeans> cb_almoxarifado;
    private javax.swing.JComboBox<CadAlmoxarifadoBeans> cb_almoxarifadoConsult;
    private componentes.UJComboBox cb_aplicacao;
    private javax.swing.JComboBox<ListFazendasBeans> cb_fazenda;
    private javax.swing.JComboBox<ListFazendasBeans> cb_fazendaConsult;
    private componentes.UJComboBox cb_localizador;
    private javax.swing.JComboBox<String> cb_operacao;
    private javax.swing.JComboBox<String> cb_operacaoConsult;
    private javax.swing.JComboBox<ListSetorTrabalhoBeans> cb_setor;
    private javax.swing.JComboBox<ListSetorTrabalhoBeans> cb_setorConsult;
    private javax.swing.JMenuItem jMenuEditar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupEditar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JLabel lbl_fazenda;
    private javax.swing.JTable tb_consulta;
    private javax.swing.JTable tb_pecas;
    private javax.swing.JTextField txt_codigoConsult;
    private com.toedter.calendar.JDateChooser txt_data;
    private javax.swing.JTextField txt_descricao;
    private javax.swing.JTextField txt_descricaoConsult;
    private com.toedter.calendar.JDateChooser txt_dtFinal;
    private com.toedter.calendar.JDateChooser txt_dtInicial;
    private javax.swing.JTextField txt_idItem;
    private javax.swing.JTextField txt_idItemConsult;
    private javax.swing.JTextField txt_nNf;
    private javax.swing.JTextField txt_nNfConsult;
    private javax.swing.JTextField txt_obs;
    private javax.swing.JTextField txt_obsConsult;
    private javax.swing.JTextField txt_quantidade;
    private javax.swing.JTextField txt_recebedor;
    private javax.swing.JTextField txt_responsavel;
    private javax.swing.JTextField txt_unidade;
    // End of variables declaration//GEN-END:variables

    private void habilitarCampos() {
        txt_data.setEnabled(true);
        cb_almoxarifado.setEnabled(true);
        cb_operacao.setEnabled(true);
        cb_fazenda.setEnabled(true);
        txt_nNf.setEnabled(true);
        btn_salvar.setEnabled(true);
        btn_add.setEnabled(true);
        btn_pesqItem.setEnabled(true);
    }

    private void limparCampos() {
        txt_data.setDate(null);
        cb_almoxarifado.setSelectedIndex(0);
        cb_operacao.setSelectedIndex(0);
        cb_fazenda.setSelectedIndex(0);
        txt_nNf.setText("");
        txt_responsavel.setText("");
        TbSaida.limpar();
    }

    private void limparCamposProdutos() {
        txt_idItem.setText("");
        txt_descricao.setText("");
        txt_unidade.setText("");
        txt_quantidade.setText("");
        cb_aplicacao.setSelectedIndex(0);
        cb_localizador.setSelectedIndex(0);
        cb_setor.setSelectedIndex(0);
        txt_recebedor.setText("");
        txt_obs.setText("");
        CodigoProduto = "";
    }

    private void popularBeans() {
        SaidaB.setData(Corretores.ConverterDateStringDDMMAAA(txt_data.getDate()));
        SaidaB.setIdAlmoxarifado(getIdAlmoxarifado(cb_almoxarifado));
        SaidaB.setIdOperacao(cb_operacao.getSelectedIndex());
        SaidaB.setIdFazenda(getIdFazenda(cb_fazenda));
        SaidaB.setNDoc(getInteger(txt_nNf));
        SaidaB.setResponsavel(txt_responsavel.getText());
        SaidaB.setProdutos(TbSaida.getLista());

    }

    private boolean verificarBeans(SaidaAlmoxarifadoBeans b) {
        return true;
    }

    private void desabilitarCampos() {
        txt_data.setEnabled(false);
        cb_almoxarifado.setEnabled(false);
        cb_operacao.setEnabled(false);
        cb_fazenda.setEnabled(false);
        txt_nNf.setEnabled(false);
        btn_salvar.setEnabled(false);
        btn_add.setEnabled(false);
        btn_pesqItem.setEnabled(false);
    }

    private void AddProduto() {
        TbSaidaAlmoxarifadoBeans b = new TbSaidaAlmoxarifadoBeans();
        b.setID(0);
        b.setIdItem(getInteger(txt_idItem));
        b.setCodigo(CodigoProduto);
        b.setDescricao(txt_descricao.getText());
        b.setUnidade(txt_unidade.getText());
        b.setQuantidade(Corretores.ConverQuilosDouble(txt_quantidade.getText()));
        b.setIdAplicacao(getIdAplicacao(cb_aplicacao));
        b.setAplicacao(cb_aplicacao.getSelectedItem().toString());
        b.setIdLocalizador(getIdLocalizador(cb_localizador));
        b.setLocalizador(cb_localizador.getSelectedItem().toString());
        b.setIdSetorServico(getIdSetor(cb_setor));
        b.setSetorServico(cb_setor.getSelectedItem().toString());
        b.setRecebedor(txt_recebedor.getText());
        b.setObservacao(txt_obs.getText());
        TbSaida.addBeans(b);
        limparCamposProdutos();
    }

    private void EditarProduto(Integer index) {
        TbSaidaAlmoxarifadoBeans produto = TbSaida.getBeans(index);
        produto.setIdItem(new Integer(txt_idItem.getText()));
        produto.setCodigo(CodigoProduto);
        produto.setDescricao(txt_descricao.getText());
        produto.setUnidade(txt_unidade.getText());
        produto.setQuantidade(Corretores.ConverterStringDouble(txt_quantidade.getText()));
        produto.setLocalizador(getLocalizador());
        produto.setIdLocalizador(getIdLocalizador(cb_localizador));
        produto.setIdAplicacao(getIdAplicacao(cb_aplicacao));
        produto.setIdSetorServico(getIdSetor(cb_setor));
        produto.setAplicacao(cb_aplicacao.getSelectedItem().toString());
        produto.setSetorServico(cb_setor.getSelectedItem().toString());
        produto.setRecebedor(txt_recebedor.getText());
        produto.setObservacao(txt_obs.getText());
        TbSaida.SetBeans(index, produto);
        limparCamposProdutos();
    }

    private void preencherDadosProduto(Integer index) {
        txt_idItem.setText(TbSaida.getValueAt(index, TbSaida.ID_ITEM).toString());
        CodigoProduto = TbSaida.getValueAt(index, TbSaida.CODIGO).toString();
        txt_descricao.setText(TbSaida.getValueAt(index, TbSaida.DESCRICAO).toString());
        txt_unidade.setText(TbSaida.getValueAt(index, TbSaida.UNIDADE).toString());
        txt_quantidade.setText(Corretores.ConverterDoubleDecimal((Double) TbSaida.getValueAt(index, TbSaida.QUANTIDADE)));
        setComboBoxLocalizador((Integer) (TbSaida.getValueAt(index, TbSaida.ID_LOCALIZADOR)));
        setComboBoxAplicacao((Integer) (TbSaida.getValueAt(index, TbSaida.ID_APLICACAO)));
        setJComboBoxSetor(cb_setor, (Integer) TbSaida.getValueAt(index, TbSaida.ID_SETOR_SERVICO));
        txt_recebedor.setText(TbSaida.getValueAt(index, TbSaida.RECEBEDOR).toString());
        txt_obs.setText(TbSaida.getValueAt(index, TbSaida.OBSERVACAO).toString());
    }

    private void ComboBoxLocalizar(Integer IdAlmoxarifado, UJComboBox comboBox) {
        comboBox.removeAllItems();
        LocalizadorAlmoxarifadoBeans bean = new LocalizadorAlmoxarifadoBeans();
        bean.setID(0);
        bean.setDescricao("-");
        bean.setId_Almoxarifado(0);
        comboBox.addItem(bean);
        for (LocalizadorAlmoxarifadoBeans l : listLocalizadorAlmox) {
            if (Objects.equals(l.getId_Almoxarifado(), IdAlmoxarifado)) {
                comboBox.addItem(l);
            }
        }
    }

    private Integer getIdSetor(JComboBox<ListSetorTrabalhoBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return comboBox.getItemAt(comboBox.getSelectedIndex()).getID();
        }
        return 0;
    }

    private Integer getIdLocalizador(UJComboBox comboBox) {
        LocalizadorAlmoxarifadoBeans localizador;
        try {
            localizador = (LocalizadorAlmoxarifadoBeans) comboBox.getSelectedObject();
            return localizador.getID();
        } catch (Exception e) {
            return 0;
        }
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

    private String getLocalizador() {
        LocalizadorAlmoxarifadoBeans localizador;
        try {
            localizador = (LocalizadorAlmoxarifadoBeans) cb_localizador.getSelectedObject();
            return localizador.getDescricao();
        } catch (Exception e) {
            return "";
        }
    }

    private Integer getIdAlmoxarifado(JComboBox<CadAlmoxarifadoBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return comboBox.getItemAt(comboBox.getSelectedIndex()).getID();
        }
        return 0;
    }

    private Integer getIdFazenda(JComboBox<ListFazendasBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return comboBox.getItemAt(comboBox.getSelectedIndex()).getID();
        }
        return 0;
    }

    private Integer getIdOrigem(JComboBox comboBox) {
        return comboBox.getSelectedIndex();
    }

    private Integer getInteger(JTextField txt) {
        if (txt.getText().equals("")) {
            return 0;
        } else {
            return new Integer(txt.getText());
        }
    }

    private String getStringDate(Date dt) {
        String s;
        s = new SimpleDateFormat("dd/MM/yyyy").format(dt);
        return s;
    }

    private void setComboBoxLocalizador(Integer IdLocalizador) {
        cb_localizador.setSelectedIndex(0);
        for (int i = 0; i < cb_localizador.getItemCount(); i++) {
            LocalizadorAlmoxarifadoBeans localizador = (LocalizadorAlmoxarifadoBeans) cb_localizador.getItemAt(i);
            if (localizador.getID() == IdLocalizador) {
                cb_localizador.setSelectedIndex(i);
            }
        }
    }

    private void setComboBoxAplicacao(Integer ID) {
        cb_aplicacao.setSelectedIndex(0);
        for (int i = 0; i < cb_aplicacao.getItemCount(); i++) {
            InventarioBeans aplicacao = (InventarioBeans) cb_aplicacao.getItemAt(i);
            if (Objects.equals(aplicacao.getID(), ID)) {
                cb_aplicacao.setSelectedIndex(i);
            }
        }
    }

    private void setJComboBoxFazenda(JComboBox<ListFazendasBeans> cb, Integer IdFazenda) {
        for (int i = 0; i < cb.getModel().getSize(); i++) {
            if (cb.getItemAt(i).getID() == IdFazenda) {
                cb.setSelectedIndex(i);
            }
        }
    }

    private void setJComboBoxSetor(JComboBox<ListSetorTrabalhoBeans> cb, Integer ID) {
        for (int i = 0; i < cb.getModel().getSize(); i++) {
            if (cb.getItemAt(i).getID() == ID) {
                cb.setSelectedIndex(i);
            }
        }
    }

    private void setJComboBoxAlmoxarifado(JComboBox<CadAlmoxarifadoBeans> cb, Integer ID) {
        for (int i = 0; i < cb.getModel().getSize(); i++) {
            if (cb.getItemAt(i).getID() == ID) {
                cb.setSelectedIndex(i);
            }
        }
    }

    private void preencherFormulario(SaidaAlmoxarifadoBeans SaidaB) {
        txt_data.setDate(Corretores.ConverterStringDateDDMMAAAA(SaidaB.getData()));
        setJComboBoxAlmoxarifado(cb_almoxarifado, SaidaB.getIdAlmoxarifado());
        setJComboBoxFazenda(cb_fazenda, SaidaB.getIdFazenda());
        cb_operacao.setSelectedIndex(SaidaB.getIdOperacao());
        txt_nNf.setText(SaidaB.getNDoc().toString());
        txt_responsavel.setText(SaidaB.getResponsavel());
        TbSaida.addLista(SaidaB.getProdutos());
    }

    private String getStringWhere(){
        String s = "";
        if (cb_almoxarifadoConsult.getSelectedIndex() != 0) {
            s += " and sa.id_almoxarifado = " + getIdAlmoxarifado(cb_almoxarifadoConsult);
        }
        if (txt_dtInicial.getDate() != null && txt_dtFinal.getDate() != null) {
            s += " and sa.data Between '" + Corretores.ConverterParaSQL(getStringDate(txt_dtInicial.getDate()))
                    + "' AND '" + Corretores.ConverterParaSQL(getStringDate(txt_dtFinal.getDate())) + "'";
        }
        if (cb_operacaoConsult.getSelectedIndex() != 0) {
            s += " and sa.id_operacao = " + getIdOrigem(cb_operacaoConsult);
        }
        
        if (cb_setorConsult.getSelectedIndex() != 0) {
            s += " and sa.id_setor_servico = " + getIdSetor(cb_setorConsult);
        }
        if (cb_fazendaConsult.getSelectedIndex() != 0) {
            s += " and sa.id_fazenda = " + getIdFazenda(cb_fazendaConsult);
        }
        
        if (!txt_idItemConsult.getText().equals("")) {
            s += " and sai.id_item = " + txt_idItemConsult.getText();
        }
        if (!txt_descricaoConsult.getText().equals("") && txt_idItemConsult.getText().equals("")) {
            s += " and sai.descricao like '%" + txt_descricaoConsult.getText() + "%'";
        }
        if (!txt_codigoConsult.getText().equals("")) {
            s += " and sai.codigo like '%" + txt_codigoConsult.getText() + "%'";
        }
        
        if (!s.equals("")) {
            s = " WHERE " + s.replaceFirst("and", "");
        }
        return s;
    }
    
}

class TableCellRendererSaidaAlmoxarifao extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setHorizontalAlignment(SwingConstants.CENTER);
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Object val = table.getValueAt(row, column);
        TableModelProdutosNFe tableModel = (TableModelProdutosNFe) table.getModel();
        if (column == tableModel.QCOM) {
            Double valorD = (Double) val;
            setText(new DecimalFormat("###,##0.00").format(valorD));
        }
        if (column == tableModel.VUNCOM || column == tableModel.VALORUNITFINAL || column == tableModel.VPROD) {
            Double valorD = (Double) val;
            setText(new DecimalFormat("R$ ###,##0.00").format(valorD));
        }

        return this;
    }
}
