/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Beans.AnoSafra;
import Beans.ListAplicacaoBeans;
import Beans.CultivoBeans;
import Beans.CulturaBeans;
import Beans.ListFazendasBeans;
import Beans.ListOperacoesBeans;
import Beans.ListPontaPulverizacaoBeans;
import Beans.OrdemServicoBeans;
import DAO.Diversas;
import DAO.OrdemServicoDAO;
import static GUI.Principal.ListFazPermitidas;
import static GUI.Principal.listAnoSafra;
import static GUI.Principal.listAplicacao;
import static GUI.Principal.listCultivo;
import static GUI.Principal.listCultura;
import static GUI.Principal.listOperacoes;
import static GUI.Principal.listPontasPulverizacao;
import static GUI.Principal.listTalhao;
import GerarRelatorios.RelatoriosOperacoes;
import TableModel.TableModelInsumosAplic;
import TableModel.TableModelOrdemServico;
import TableModel.TableModelTalhoesAplic;
import TableModel.TbInsumosAplicBeans;
import TableModel.TbTalhaoAplicBeans;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author agroa
 */
public class frmAplicacoes extends javax.swing.JInternalFrame {

    Diversas DiversasD;
    JList listInsumos;
    OrdemServicoBeans OrdemB;
    OrdemServicoDAO OrdemD;
    TableModelInsumosAplic TbInsumos;
    TableModelTalhoesAplic TbTalhao;
    CentralizarTabela Centralizar;
    frmPesquisarInsumo PesquisarInsumo;
    TbInsumosAplicBeans InsumosB;
    CellRenderer CellRenderer;
    frmDialogData DialogData;
    JPopupMenu menu_cbFazenda;
    JList<ListFazendasBeans> jl_fazendas;
    JScrollPane sc_jlistFazendas;
    TableModelOrdemServico TbOS;
    JCheckBox ch_listFazendas;
    Integer IdOrdem;
    RelatoriosOperacoes relOp;

    public frmAplicacoes() {
        initComponents();
        DiversasD = new Diversas();
        OrdemB = new OrdemServicoBeans();
        OrdemD = new OrdemServicoDAO();
        Centralizar = new CentralizarTabela();
        InsumosB = new TbInsumosAplicBeans();
        jl_fazendas = new JList();
        sc_jlistFazendas = new JScrollPane();
        carregarFazPermitidas();
        carregarAnoSafra();
        carregarEpocaCultivo();
        carregarCulturas();
        carregarPontas();
        TabelaInsumos();
        TabelaTalhoes();
        TabelaOS();
        desabilitarCampos();
        jLabel24.setVisible(false);
        jLabel25.setVisible(false);
        cb_localEstoque.setVisible(false);
        txt_areaTotal.setVisible(false);
    }

    private void carregarFazPermitidas() {
        ListFazPermitidas = new ArrayList<>();
        if (ListFazPermitidas.isEmpty()) {
            ListFazPermitidas = DiversasD.ListpmFazenda();
        }
        ListFazendasBeans l = new ListFazendasBeans();
        DefaultListModel<ListFazendasBeans> lista = new DefaultListModel<>();
        l.setID(0);
        l.setNomeFazenda("-");
        cb_fazenda.addItem(l);
        cb_localEstoque.addItem(l);
        //cb_fazendaPesq.addItem(l);
        for (ListFazendasBeans list : ListFazPermitidas) {
            cb_fazenda.addItem(list);
            cb_localEstoque.addItem(list);
            lista.addElement(list);
        }
        ListcellRenderer cellRenderer = new ListcellRenderer();

        sc_jlistFazendas.setViewportView(jl_fazendas);
        jl_fazendas.setModel(lista);
        jl_fazendas.setCellRenderer(cellRenderer);

    }

    private void carregarAnoSafra() {
        listAnoSafra = new ArrayList<>();
        if (listAnoSafra.isEmpty()) {
            listAnoSafra = DiversasD.listAnoSafra();
        }
        AnoSafra l = new AnoSafra();
        l.setIdSafra(0);
        l.setAnoSafra("-");
        cb_safra.addItem(l);
        cb_safraPesq.addItem(l);
        for (AnoSafra list : listAnoSafra) {
            cb_safra.addItem(list);
            cb_safraPesq.addItem(list);
        }
    }

    private void carregarEpocaCultivo() {
        listCultivo = new ArrayList<>();
        if (listCultivo.isEmpty()) {
            listCultivo = DiversasD.ListCultivo();
        }
        CultivoBeans l = new CultivoBeans();
        l.setIDCultivo(0);
        l.setCultivo("-");
        cb_cultivo.addItem(l);
        cb_cultivoPesq.addItem(l);
        for (CultivoBeans list : listCultivo) {
            cb_cultivo.addItem(list);
            cb_cultivoPesq.addItem(list);
        }
    }

    private void carregarCulturas() {
        listCultura = new ArrayList<>();
        if (listCultura.isEmpty()) {
            listCultura = DiversasD.ListCultura();
        }
        CulturaBeans l = new CulturaBeans();
        l.setIDCultura(0);
        l.setNomeCultura("-");
        cb_cultura.addItem(l);
        cb_culturaPesq.addItem(l);
        for (CulturaBeans list : listCultura) {
            cb_cultura.addItem(list);
            cb_culturaPesq.addItem(list);
        }
    }

    private void carregarAplicacoes(Integer idCultura, JComboBox<ListAplicacaoBeans> cbDestino) {
        if (listAplicacao == null) {
            listAplicacao = new ArrayList<>();
            listAplicacao = DiversasD.ListAplicacao();
        }
        ListAplicacaoBeans l = new ListAplicacaoBeans();
        l.setID(0);
        l.setAplicacao("-");
        cbDestino.removeAllItems();
        cbDestino.addItem(l);
        for (ListAplicacaoBeans list : listAplicacao) {
            if (Objects.equals(list.getIDCultura(), idCultura)) {
                cbDestino.addItem(list);
            }
        }
    }

    private void carregarOperacoes(Integer idCultura, JComboBox<ListOperacoesBeans> cbDestino) {
        listOperacoes = new ArrayList<>();
        if (listOperacoes.isEmpty()) {
            listOperacoes = DiversasD.ListOperacoes();
        }
        cbDestino.removeAllItems();
        ListOperacoesBeans l = new ListOperacoesBeans();
        l.setID(0);
        l.setNome("-");
        cbDestino.addItem(l);
        for (ListOperacoesBeans list : listOperacoes) {
            if (list.getIdCultura() == idCultura) {
                cbDestino.addItem(list);
            }
        }
    }

    private void carregarPontas() {
        if (listPontasPulverizacao == null) {
            listPontasPulverizacao = DiversasD.ListPontaPulverizacao();
        }
        ListPontaPulverizacaoBeans b = new ListPontaPulverizacaoBeans();
        b.setID(0);
        b.setPonta("-");
        b.setFabricante("-");
        cb_ponta.addItem(b);
        for (ListPontaPulverizacaoBeans list : listPontasPulverizacao) {
            cb_ponta.addItem(list);
        }

    }

    private JTable TabelaInsumos() {
        tb_insumos.setModel(getTableModelInsumos());
        CellRenderer = new CellRenderer();
        ((DefaultTableCellRenderer) tb_insumos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        Centralizar.centralizarTabela(tb_insumos);
        tb_insumos.getColumnModel().getColumn(0).setPreferredWidth(0);// ID Insumo
        tb_insumos.getColumnModel().getColumn(0).setMinWidth(0);
        tb_insumos.getColumnModel().getColumn(0).setMaxWidth(0);
        tb_insumos.getColumnModel().getColumn(1).setPreferredWidth(90);//Insumo
        tb_insumos.getColumnModel().getColumn(1).setMinWidth(70);
        tb_insumos.getColumnModel().getColumn(1).setMaxWidth(120);
        tb_insumos.getColumnModel().getColumn(2).setPreferredWidth(0);// ID Categoria
        tb_insumos.getColumnModel().getColumn(2).setMinWidth(0);
        tb_insumos.getColumnModel().getColumn(2).setMaxWidth(0);
        tb_insumos.getColumnModel().getColumn(3).setPreferredWidth(90);// Categoria
        tb_insumos.getColumnModel().getColumn(3).setMinWidth(70);
        tb_insumos.getColumnModel().getColumn(3).setMaxWidth(120);
        tb_insumos.getColumnModel().getColumn(4).setPreferredWidth(0);// Dose COntrol
        tb_insumos.getColumnModel().getColumn(4).setMinWidth(0);
        tb_insumos.getColumnModel().getColumn(4).setMaxWidth(0);
        tb_insumos.getColumnModel().getColumn(5).setPreferredWidth(0);// Dose Minima
        tb_insumos.getColumnModel().getColumn(5).setMinWidth(0);
        tb_insumos.getColumnModel().getColumn(5).setMaxWidth(0);
        tb_insumos.getColumnModel().getColumn(6).setPreferredWidth(0);// Dose Maxima
        tb_insumos.getColumnModel().getColumn(6).setMinWidth(0);
        tb_insumos.getColumnModel().getColumn(6).setMaxWidth(0);
        tb_insumos.getColumnModel().getColumn(10).setPreferredWidth(0);// Dose Maxima
        tb_insumos.getColumnModel().getColumn(10).setMinWidth(0);
        tb_insumos.getColumnModel().getColumn(10).setMaxWidth(0);
        tb_insumos.getColumnModel().getColumn(7).setCellRenderer(CellRenderer);
        tb_insumos.getColumnModel().getColumn(8).setCellRenderer(CellRenderer);
        tb_insumos.getColumnModel().getColumn(9).setCellRenderer(CellRenderer);
        return tb_insumos;
    }

    private JTable TabelaTalhoes() {
        tb_talhoes.setModel(getTableModelTalhao());
        ((DefaultTableCellRenderer) tb_talhoes.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        Centralizar.centralizarTabela(tb_talhoes);
        CellRenderer = new CellRenderer();
        tb_talhoes.getColumnModel().getColumn(0).setPreferredWidth(0);// ID Insumo
        tb_talhoes.getColumnModel().getColumn(0).setMinWidth(0);
        tb_talhoes.getColumnModel().getColumn(0).setMaxWidth(0);
        tb_talhoes.getColumnModel().getColumn(2).setPreferredWidth(0);// ID Insumo
        tb_talhoes.getColumnModel().getColumn(2).setMinWidth(0);
        tb_talhoes.getColumnModel().getColumn(2).setMaxWidth(0);
        tb_talhoes.getColumnModel().getColumn(3).setPreferredWidth(0);// Data Termino
        tb_talhoes.getColumnModel().getColumn(3).setMinWidth(0);
        tb_talhoes.getColumnModel().getColumn(3).setMaxWidth(0);
        tb_talhoes.getColumnModel().getColumn(8).setPreferredWidth(0);// Dose Maxima
        tb_talhoes.getColumnModel().getColumn(8).setMinWidth(0);
        tb_talhoes.getColumnModel().getColumn(8).setMaxWidth(0);
        tb_talhoes.getColumnModel().getColumn(9).setPreferredWidth(0);// Hora Máquina
        tb_talhoes.getColumnModel().getColumn(9).setMinWidth(0);
        tb_talhoes.getColumnModel().getColumn(9).setMaxWidth(0);
        tb_talhoes.getColumnModel().getColumn(11).setPreferredWidth(0);// AreaAplicada
        tb_talhoes.getColumnModel().getColumn(11).setMinWidth(0);
        tb_talhoes.getColumnModel().getColumn(11).setMaxWidth(0);
        tb_talhoes.getColumnModel().getColumn(6).setCellRenderer(CellRenderer);
        tb_talhoes.getColumnModel().getColumn(7).setCellRenderer(CellRenderer);
        return tb_talhoes;
    }

    private JTable TabelaOS() {
        tb_os.setModel(getTableModelOS());
        ((DefaultTableCellRenderer) tb_os.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        Centralizar.centralizarTabela(tb_os);
        tb_os.getColumnModel().getColumn(0).setPreferredWidth(50);// ID OS
        tb_os.getColumnModel().getColumn(0).setMinWidth(50);
        tb_os.getColumnModel().getColumn(0).setMaxWidth(70);
        tb_os.getColumnModel().getColumn(1).setPreferredWidth(30);// Status
        tb_os.getColumnModel().getColumn(1).setMinWidth(30);
        tb_os.getColumnModel().getColumn(1).setMaxWidth(30);
        tb_os.getColumnModel().getColumn(2).setPreferredWidth(90);// Data Ordem
        tb_os.getColumnModel().getColumn(2).setMinWidth(90);
        tb_os.getColumnModel().getColumn(2).setMaxWidth(120);
        tb_os.getColumnModel().getColumn(6).setPreferredWidth(140);// Opercacão
        tb_os.getColumnModel().getColumn(6).setMinWidth(120);
        tb_os.getColumnModel().getColumn(6).setMaxWidth(180);
        tb_os.getColumnModel().getColumn(7).setPreferredWidth(140);// Aplicação
        tb_os.getColumnModel().getColumn(7).setMinWidth(120);
        tb_os.getColumnModel().getColumn(7).setMaxWidth(180);
        tb_os.getColumnModel().getColumn(8).setPreferredWidth(120);// ID OS
        tb_os.getColumnModel().getColumn(8).setMinWidth(90);
        tb_os.getColumnModel().getColumn(8).setMaxWidth(150);
        return tb_os;
    }

    private TableModelInsumosAplic getTableModelInsumos() {
        if (TbInsumos == null) {
            TbInsumos = new TableModelInsumosAplic();
        }
        return TbInsumos;
    }

    private TableModelTalhoesAplic getTableModelTalhao() {
        if (TbTalhao == null) {
            TbTalhao = new TableModelTalhoesAplic();
        }
        return TbTalhao;
    }

    private TableModelOrdemServico getTableModelOS() {
        if (TbOS == null) {
            TbOS = new TableModelOrdemServico();
        }
        return TbOS;
    }

    private JList getListInsumos() {

        return listInsumos;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopup = new javax.swing.JPopupMenu();
        jMenu_editar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenu_addOperacao = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenu_Imprimir = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        cb_safra = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        cb_cultivo = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        cb_cultura = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        cb_aplicacao = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        txt_responsavel = new javax.swing.JTextField();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        txt_data =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        cb_operacao = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        txt_estadio = new javax.swing.JTextField();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        txt_calda = new javax.swing.JTextField();
        javax.swing.JLabel jLabel16 = new javax.swing.JLabel();
        txt_observacao = new javax.swing.JTextField();
        ch_execucao = new javax.swing.JCheckBox();
        ch_finalizado = new javax.swing.JCheckBox();
        javax.swing.JLabel jLabel17 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel13 = new javax.swing.JLabel();
        cb_ponta = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        sc_pane = new javax.swing.JScrollPane();
        tb_insumos = new javax.swing.JTable();
        btn_addInsumo = new javax.swing.JButton();
        btn_excluirInsumo = new javax.swing.JButton();
        ch_gerarBaixaEstoque = new javax.swing.JCheckBox();
        jLabel24 = new javax.swing.JLabel();
        cb_localEstoque = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_talhoes = new javax.swing.JTable();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        cb_fazenda = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        txt_talhao = new javax.swing.JTextField();
        btn_addTalhao = new javax.swing.JButton();
        btn_excluirTalhao = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        txt_areaTotal = new javax.swing.JTextField();
        btn_salvarPedido = new javax.swing.JButton();
        btn_editarPedido = new javax.swing.JButton();
        btn_excluirPedido = new javax.swing.JButton();
        btn_novo = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        txt_data1 =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel14 = new javax.swing.JLabel();
        txt_data2 =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel15 = new javax.swing.JLabel();
        cb_safraPesq = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel18 = new javax.swing.JLabel();
        cb_cultivoPesq = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel19 = new javax.swing.JLabel();
        cb_culturaPesq = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel20 = new javax.swing.JLabel();
        cb_operacaoPesq = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel21 = new javax.swing.JLabel();
        cb_aplicacaoPesq = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel22 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel23 = new javax.swing.JLabel();
        txt_talhaoPesq = new javax.swing.JTextField();
        btn_pesquisar = new javax.swing.JButton();
        txt_fazenda = new javax.swing.JTextField();
        javax.swing.JLabel jLabel26 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_os = new javax.swing.JTable();

        jMenu_editar.setText("Editar Ordem");
        jMenu_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_editarActionPerformed(evt);
            }
        });
        jPopup.add(jMenu_editar);
        jPopup.add(jSeparator1);

        jMenu_addOperacao.setText("Adicionar Operação");
        jMenu_addOperacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_addOperacaoActionPerformed(evt);
            }
        });
        jPopup.add(jMenu_addOperacao);
        jPopup.add(jSeparator2);

        jMenu_Imprimir.setText("Imprimir Ordem de Serviço");
        jMenu_Imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_ImprimirActionPerformed(evt);
            }
        });
        jPopup.add(jMenu_Imprimir);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Ordem de Serviços");

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Safra");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Cultivo");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Cultura");

        cb_cultura.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_culturaItemStateChanged(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Aplicação");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Responsável");

        txt_responsavel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_responsavel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_responsavelFocusGained(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Data");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Operação");

        cb_operacao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_operacaoItemStateChanged(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Estádio Fenológico");

        txt_estadio.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Calda ");

        txt_calda.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Observação");

        buttonGroup1.add(ch_execucao);
        ch_execucao.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_execucao.setText("Em Execução");

        buttonGroup1.add(ch_finalizado);
        ch_finalizado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_finalizado.setText("Finalizado");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Status");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Ponta de Pulverização");

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
                        .addComponent(cb_aplicacao, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_estadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_calda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_ponta, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_responsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_data, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_safra, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_cultivo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_cultura, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_operacao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ch_execucao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ch_finalizado)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_observacao)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(txt_data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cb_safra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cb_cultivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(cb_cultura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(cb_operacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(cb_aplicacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(txt_estadio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(txt_calda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)
                        .addComponent(cb_ponta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_responsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel16)
                    .addComponent(txt_observacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ch_execucao)
                    .addComponent(ch_finalizado)
                    .addComponent(jLabel17))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Insumos Aplicados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tb_insumos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb_insumos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tb_insumos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_insumosMouseClicked(evt);
            }
        });
        tb_insumos.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tb_insumosPropertyChange(evt);
            }
        });
        sc_pane.setViewportView(tb_insumos);

        btn_addInsumo.setText("Add Insumo");
        btn_addInsumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addInsumoActionPerformed(evt);
            }
        });

        btn_excluirInsumo.setText("Excluir Insumo");
        btn_excluirInsumo.setEnabled(false);
        btn_excluirInsumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirInsumoActionPerformed(evt);
            }
        });

        ch_gerarBaixaEstoque.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_gerarBaixaEstoque.setText("Gerar Baixa no Estoque");
        ch_gerarBaixaEstoque.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ch_gerarBaixaEstoqueItemStateChanged(evt);
            }
        });
        ch_gerarBaixaEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ch_gerarBaixaEstoqueActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Local de Estoque");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sc_pane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_addInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_excluirInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                        .addComponent(ch_gerarBaixaEstoque))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_localEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_addInsumo)
                    .addComponent(btn_excluirInsumo)
                    .addComponent(ch_gerarBaixaEstoque))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sc_pane, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(cb_localEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Talhões Aplicados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tb_talhoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb_talhoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_talhoesMouseClicked(evt);
            }
        });
        tb_talhoes.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tb_talhoesPropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(tb_talhoes);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Fazenda");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Nº Talhão");

        txt_talhao.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_talhao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_talhaoFocusGained(evt);
            }
        });

        btn_addTalhao.setText("Add Talhão");
        btn_addTalhao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addTalhaoActionPerformed(evt);
            }
        });

        btn_excluirTalhao.setText("Excluir");
        btn_excluirTalhao.setEnabled(false);
        btn_excluirTalhao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirTalhaoActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("Área Total");

        txt_areaTotal.setEditable(false);
        txt_areaTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 32, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_talhao, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_addTalhao, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_excluirTalhao, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_areaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txt_talhao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_addTalhao)
                    .addComponent(btn_excluirTalhao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txt_areaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btn_salvarPedido.setBackground(new java.awt.Color(255, 255, 255));
        btn_salvarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/salvar.png"))); // NOI18N
        btn_salvarPedido.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_salvarPedido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_salvarPedido.setDisabledIcon(null);
        btn_salvarPedido.setEnabled(false);
        btn_salvarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salvarPedidoActionPerformed(evt);
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

        btn_excluirPedido.setBackground(new java.awt.Color(255, 255, 255));
        btn_excluirPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/deletar_40x40.png"))); // NOI18N
        btn_excluirPedido.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_excluirPedido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_excluirPedido.setEnabled(false);
        btn_excluirPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirPedidoActionPerformed(evt);
            }
        });

        btn_novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/inserir_40x40.png"))); // NOI18N
        btn_novo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_novo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_salvarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_editarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_excluirPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_salvarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_editarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_excluirPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Emitir Ordem", jPanel4);

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Data");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("à");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Safra");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Cultivo");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Cultura");

        cb_culturaPesq.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_culturaPesqItemStateChanged(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Operação");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Aplicação");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Fazendas");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("Nº Talhão");

        txt_talhaoPesq.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btn_pesquisar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_pesquisar.setText("Pesquisar");
        btn_pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisarActionPerformed(evt);
            }
        });

        txt_fazenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txt_fazendaMouseEntered(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("Status");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Em Execução", "Finalizado" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_data1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_data2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_safraPesq, 0, 197, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_cultivoPesq, 0, 198, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_operacaoPesq, 0, 131, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_aplicacaoPesq, 0, 131, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_fazenda, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_talhaoPesq, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cb_culturaPesq, 0, 120, Short.MAX_VALUE)
                    .addComponent(btn_pesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(txt_data1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txt_data2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(cb_safraPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(cb_cultivoPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(cb_culturaPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22)
                        .addComponent(jLabel23)
                        .addComponent(txt_talhaoPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_pesquisar)
                        .addComponent(txt_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(cb_operacaoPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21)
                        .addComponent(cb_aplicacaoPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel26)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jScrollPane2.setPreferredSize(new java.awt.Dimension(452, 300));

        tb_os.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb_os.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_osMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tb_osMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tb_os);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Ordens de Serviço", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb_culturaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_culturaItemStateChanged
        carregarAplicacoes(getIdCultura(cb_cultura), cb_aplicacao);
        carregarOperacoes(getIdCultura(cb_cultura), cb_operacao);
    }//GEN-LAST:event_cb_culturaItemStateChanged

    private void txt_responsavelFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_responsavelFocusGained
        txt_responsavel.selectAll();
    }//GEN-LAST:event_txt_responsavelFocusGained

    private void cb_operacaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_operacaoItemStateChanged
        getUsoInsumos(cb_operacao);
        getUsoFatoresClimaticos(cb_operacao);
    }//GEN-LAST:event_cb_operacaoItemStateChanged

    private void btn_addInsumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addInsumoActionPerformed
    frmPesquisarInsumos();
    }//GEN-LAST:event_btn_addInsumoActionPerformed

    private void ch_gerarBaixaEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ch_gerarBaixaEstoqueActionPerformed

    }//GEN-LAST:event_ch_gerarBaixaEstoqueActionPerformed

    private void tb_insumosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_insumosMouseClicked
        if (evt.getClickCount() == 2) {
            btn_excluirInsumo.setEnabled(true);
        } else {
            btn_excluirInsumo.setEnabled(false);
        }
    }//GEN-LAST:event_tb_insumosMouseClicked

    private void btn_excluirInsumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirInsumoActionPerformed
        int linha = tb_insumos.getSelectedRow();
        if (linha >= 0) {
            TbInsumos.excluirLinha(linha);
            btn_excluirInsumo.setEnabled(false);
        }
    }//GEN-LAST:event_btn_excluirInsumoActionPerformed

    private void tb_insumosPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tb_insumosPropertyChange
        int coluna = tb_insumos.getSelectedColumn();
        if (coluna == 7) {
            atualizarConsumoInsumos();
        }
    }//GEN-LAST:event_tb_insumosPropertyChange

    private void btn_addTalhaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addTalhaoActionPerformed
        TbTalhaoAplicBeans b = new TbTalhaoAplicBeans();
        double area = getAreaTalhao();
        if (TbTalhao.getRowCount() > 0 && !Objects.equals(getIdFazenda(cb_fazenda), TbTalhao.getValueAt(0, 2))) {
            JOptionPane.showMessageDialog(null, "A ordem de serviço deve conter apenas uma fazenda!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        } else {
            if (area > 0) {
                b.setIdFazenda(getIdFazenda(cb_fazenda));
                b.setAreaTalhao(getAreaTalhao());
                b.setIdTalha(getIDTalhao(txt_talhao.getText(), getIdFazenda(cb_fazenda)));
                b.setPorcentAplic(100.00);
                b.setnTalhao(txt_talhao.getText());
                b.setAreaPlanejada(getAreaTalhao());
                b.setAreaAplic(0.0);
                b.setAreaAplicTotal(0.0);
                b.setIdDB(0);
                TbTalhao.addBeans(b);
                atualizarConsumoInsumos();
                exibirAreaTotal();
                
            } else {
                JOptionPane.showMessageDialog(null, "Talhão Não Localizado", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            }
        }
        txt_talhao.requestFocus();

    }//GEN-LAST:event_btn_addTalhaoActionPerformed

    private void tb_talhoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_talhoesMouseClicked
        int coluna = tb_talhoes.getSelectedColumn();
        // Excluir Talhao;
        if (evt.getClickCount() == 2) {
            btn_excluirTalhao.setEnabled(true);
        }

        if (coluna == 3 || coluna == 4) {
            if (DialogData == null) {
                DialogData = new frmDialogData(null, true);
            }
            DialogData.setLocationRelativeTo(null);
            DialogData.setVisible(true);
            TbTalhao.setValueAt(DialogData.getDate(), tb_talhoes.getSelectedRow(), tb_talhoes.getSelectedColumn());
            DialogData.dispose();
        }
    }//GEN-LAST:event_tb_talhoesMouseClicked

    private void tb_talhoesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tb_talhoesPropertyChange
        int coluna = tb_talhoes.getSelectedColumn();
        int linha = tb_talhoes.getSelectedRow();
        if (coluna == 5) {
            if ((Double) TbTalhao.getValueAt(linha, 5) <= 100) {
                double areaAplic = (Double) TbTalhao.getValueAt(linha, 5) * (Double) TbTalhao.getValueAt(linha, 6) / 100;
                TbTalhao.setValueAt(Double.valueOf(new DecimalFormat("#,##0.00").format(areaAplic).replace(".", "").replace(",", ".")), linha, 7);
            } else {
                JOptionPane.showMessageDialog(null, "A Porcentagem da área aplicada deve ser entre 0 e 100%!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            }

        } else if (coluna == 7) {
            Double porcentagemArea = (Double) TbTalhao.getValueAt(linha, 7) / (Double) TbTalhao.getValueAt(linha, 6) * 100;
            if (porcentagemArea <= 100.00) {
                TbTalhao.setValueAt(Double.valueOf(new DecimalFormat("#,##0.00").format(porcentagemArea).replace(".", "").replace(",", ".")), linha, 5);
            } else {
                JOptionPane.showMessageDialog(null, "Área digitada incompatível com a área do talhão!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            }
        }
        atualizarConsumoInsumos();
        exibirAreaTotal();
    }//GEN-LAST:event_tb_talhoesPropertyChange

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed
        btn_salvarPedido.setEnabled(true);
        btn_editarPedido.setEnabled(false);
        btn_excluirPedido.setEnabled(false);
        tb_insumos.setEnabled(true);
        habilitarCampos();
        limparCampos();
        exibirAreaTotal();
    }//GEN-LAST:event_btn_novoActionPerformed

    private void btn_salvarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarPedidoActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(null, "Deseja Salvar esta Ordem de Serviço?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION) {
            popularBeans();
            if (verificarBeans()) {
                if (OrdemD.InserirOrdem(OrdemB, TbInsumos, TbTalhao)) {
                    gerarRelatorioOS(OrdemB.getIdOrdem());
                    limparCampos();
                    desabilitarCampos();
                }
            }
        }
    }//GEN-LAST:event_btn_salvarPedidoActionPerformed

    private void btn_editarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarPedidoActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja editar esta entrada?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION) {
            popularBeans();
            if (verificarBeans()) {
                if (OrdemD.EditarOS(OrdemB, TbInsumos, TbTalhao)) {
                    limparCampos();
                    desabilitarCampos();
                }
            }
        }
    }//GEN-LAST:event_btn_editarPedidoActionPerformed

    private void btn_excluirPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirPedidoActionPerformed
        String excluirSaidaEstoque;
        if (ch_gerarBaixaEstoque.isSelected() == true) {
            excluirSaidaEstoque = "Está ação excluíra o respectivo movimento de saída de insumo,Deseja excluir este movimento?";
        } else {
            excluirSaidaEstoque = "Deseja Excluir esta Ordem de Serviço";
        }
        int excluirSaidaInsumos = JOptionPane.showConfirmDialog(null, excluirSaidaEstoque, "Atenção", JOptionPane.YES_NO_OPTION);
        if (excluirSaidaInsumos == JOptionPane.YES_OPTION) {
            if (OrdemD.excluirOrdemServiço(OrdemB.getIdOrdem())) {;
                limparCampos();
                desabilitarCampos();
            }
        }
    }//GEN-LAST:event_btn_excluirPedidoActionPerformed

    private void cb_culturaPesqItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_culturaPesqItemStateChanged
        carregarAplicacoes(getIdCultura(cb_culturaPesq), cb_aplicacaoPesq);
        carregarOperacoes(getIdCultura(cb_culturaPesq), cb_operacaoPesq);
    }//GEN-LAST:event_cb_culturaPesqItemStateChanged

    private void txt_fazendaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_fazendaMouseEntered
        popupFazendas();
    }//GEN-LAST:event_txt_fazendaMouseEntered

    private void btn_pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisarActionPerformed
        OrdemD.relatorioOrdens(TbOS, getSQLWhere());
    }//GEN-LAST:event_btn_pesquisarActionPerformed

    private void tb_osMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_osMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            if (jPopup.isVisible() == true) {
                jPopup.setVisible(false);
            } else {
                jPopup.setVisible(true);
                jPopup.show(this, 0, 0);
                jPopup.setLocation(evt.getLocationOnScreen());

            }
        }
    }//GEN-LAST:event_tb_osMouseClicked

    private void jMenu_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_editarActionPerformed
        int linha = tb_os.getSelectedRow();
        if (linha >= 0) {
            IdOrdem = (Integer) TbOS.getValueAt(linha, 0);
            OrdemD.buscarOS(OrdemB, IdOrdem, TbInsumos, TbTalhao);
            preencherFormulario();
            habilitarCampos();
            jTabbedPane1.setSelectedIndex(0);
            btn_editarPedido.setEnabled(true);
            btn_salvarPedido.setEnabled(false);
            btn_excluirPedido.setEnabled(true);
            exibirAreaTotal();
            if (OrdemB.getGerarBaixaEstoque()) {
                tb_insumos.setEnabled(false);
                tb_talhoes.setEnabled(false);
                btn_addInsumo.setEnabled(false);
                btn_addTalhao.setEnabled(false);
                ch_gerarBaixaEstoque.setEnabled(false);
                cb_localEstoque.setEnabled(false);
            } else {
                tb_insumos.setEnabled(true);
                tb_talhoes.setEnabled(true);
                btn_addInsumo.setEnabled(true);
                btn_addTalhao.setEnabled(true);
                ch_gerarBaixaEstoque.setEnabled(true);
                cb_localEstoque.setEnabled(true);
            }
        }
    }//GEN-LAST:event_jMenu_editarActionPerformed

    private void jMenu_addOperacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_addOperacaoActionPerformed
        jMenu_editar.doClick();
        frmOperacoes Operacoes = new frmOperacoes(null, true);
        Operacoes.setLocationRelativeTo(null);
        Operacoes.txt_data.setDate(txt_data.getDate());
        Operacoes.cb_safra.setSelectedIndex(cb_safra.getSelectedIndex());
        Operacoes.cb_cultivo.setSelectedIndex(cb_cultivo.getSelectedIndex());
        Operacoes.cb_cultura.setSelectedIndex(this.cb_cultura.getSelectedIndex());
        Operacoes.cb_operacao.setSelectedIndex(this.cb_operacao.getSelectedIndex());
        Operacoes.cb_aplicacao.setSelectedIndex(this.cb_aplicacao.getSelectedIndex());
        for (int i = 0; i < TbTalhao.getRowCount(); i++) {
            TbTalhaoAplicBeans b = new TbTalhaoAplicBeans();
            b.setIdTalha((Integer) TbTalhao.getValueAt(i, 0));
            b.setIdFazenda((Integer) TbTalhao.getValueAt(i, 2));
            b.setnTalhao(TbTalhao.getValueAt(i, 1).toString());
            double areaAplic = ((Double) TbTalhao.getValueAt(i, 10) / (Double) TbTalhao.getValueAt(i, 6));
            b.setPorcentAplic(Double.parseDouble(new DecimalFormat("#,##0.00").format(areaAplic * 100).replace(",", ".")));
            b.setAreaTalhao((Double) TbTalhao.getValueAt(i, 6));
            b.setAreaPlanejada((Double) TbTalhao.getValueAt(i, 7));
            b.setAreaAplicTotal((Double) TbTalhao.getValueAt(i, 10));
            b.setAreaAplic(0.00);
            b.setIdDB((Integer) TbTalhao.getValueAt(i, 8));
            Operacoes.TbTalhaoOP.addBeans(b);
        }
        Operacoes.IdOS = OrdemB.getIdOrdem(); // Setar ID da OS para add operacoa.
        Operacoes.btn_novo.setEnabled(false);
        Operacoes.btn_addTalhao.setEnabled(false);
        Operacoes.btn_excluirTalhao.setEnabled(false);
        Operacoes.btn_salvarPedido.setEnabled(true);
        Operacoes.cb_safra.setEnabled(false);
        Operacoes.cb_cultivo.setEnabled(false);
        Operacoes.cb_cultura.setEnabled(false);
        Operacoes.cb_operacao.setEnabled(false);
        Operacoes.cb_aplicacao.setEnabled(false);
        Operacoes.habilitarCampos();
        Operacoes.setVisible(true);

    }//GEN-LAST:event_jMenu_addOperacaoActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        if (jTabbedPane1.getSelectedIndex() == 0) {
            this.setSize(new Dimension(1041, 548));
        } else if (jTabbedPane1.getSelectedIndex() == 1) {
            this.setSize(new Dimension(1248, 620));
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void ch_gerarBaixaEstoqueItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ch_gerarBaixaEstoqueItemStateChanged
        boolean b = ch_gerarBaixaEstoque.isSelected();
        jLabel24.setVisible(b);
        cb_localEstoque.setVisible(b);
    }//GEN-LAST:event_ch_gerarBaixaEstoqueItemStateChanged

    private void btn_excluirTalhaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirTalhaoActionPerformed
        int linha = tb_talhoes.getSelectedRow();
        if (linha >= 0) {
            TbTalhao.excluirLinha(linha);
            btn_excluirTalhao.setEnabled(false);
        }
        exibirAreaTotal();

    }//GEN-LAST:event_btn_excluirTalhaoActionPerformed

    private void jMenu_ImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_ImprimirActionPerformed
        int idOS = (Integer) TbOS.getValueAt(tb_os.getSelectedRow(), 0);
        if (idOS > 0){
            gerarRelatorioOS(idOS);
        }
    }//GEN-LAST:event_jMenu_ImprimirActionPerformed

    private void txt_talhaoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_talhaoFocusGained
        txt_talhao.selectAll();
    }//GEN-LAST:event_txt_talhaoFocusGained

    private void tb_osMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_osMousePressed
      TipTextSomaValorPagto(tb_os);
    }//GEN-LAST:event_tb_osMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_addInsumo;
    private javax.swing.JButton btn_addTalhao;
    javax.swing.JButton btn_editarPedido;
    private javax.swing.JButton btn_excluirInsumo;
    javax.swing.JButton btn_excluirPedido;
    private javax.swing.JButton btn_excluirTalhao;
    javax.swing.JButton btn_novo;
    private javax.swing.JButton btn_pesquisar;
    javax.swing.JButton btn_salvarPedido;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<ListAplicacaoBeans> cb_aplicacao;
    private javax.swing.JComboBox<ListAplicacaoBeans> cb_aplicacaoPesq;
    private javax.swing.JComboBox<Beans.CultivoBeans> cb_cultivo;
    private javax.swing.JComboBox<Beans.CultivoBeans> cb_cultivoPesq;
    private javax.swing.JComboBox<Beans.CulturaBeans> cb_cultura;
    private javax.swing.JComboBox<Beans.CulturaBeans> cb_culturaPesq;
    private javax.swing.JComboBox<ListFazendasBeans> cb_fazenda;
    private javax.swing.JComboBox<ListFazendasBeans> cb_localEstoque;
    javax.swing.JComboBox<ListOperacoesBeans> cb_operacao;
    javax.swing.JComboBox<ListOperacoesBeans> cb_operacaoPesq;
    private javax.swing.JComboBox<ListPontaPulverizacaoBeans> cb_ponta;
    private javax.swing.JComboBox<Beans.AnoSafra> cb_safra;
    private javax.swing.JComboBox<Beans.AnoSafra> cb_safraPesq;
    private javax.swing.JCheckBox ch_execucao;
    private javax.swing.JCheckBox ch_finalizado;
    private javax.swing.JCheckBox ch_gerarBaixaEstoque;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JMenuItem jMenu_Imprimir;
    private javax.swing.JMenuItem jMenu_addOperacao;
    private javax.swing.JMenuItem jMenu_editar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPopupMenu jPopup;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JScrollPane sc_pane;
    private javax.swing.JTable tb_insumos;
    private javax.swing.JTable tb_os;
    private javax.swing.JTable tb_talhoes;
    private javax.swing.JTextField txt_areaTotal;
    private javax.swing.JTextField txt_calda;
    private com.toedter.calendar.JDateChooser txt_data;
    private com.toedter.calendar.JDateChooser txt_data1;
    private com.toedter.calendar.JDateChooser txt_data2;
    private javax.swing.JTextField txt_estadio;
    private javax.swing.JTextField txt_fazenda;
    private javax.swing.JTextField txt_observacao;
    private javax.swing.JTextField txt_responsavel;
    private javax.swing.JTextField txt_talhao;
    private javax.swing.JTextField txt_talhaoPesq;
    // End of variables declaration//GEN-END:variables

    private void popularBeans() {
        try {
            OrdemB.setDtOrdem(new SimpleDateFormat("dd/MM/yyyy").format(txt_data.getDate()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Verifique o campo data!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }
        OrdemB.setIdSafra(getIdSafra(cb_safra));
        OrdemB.setIdCultivo(getIdCultivo(cb_cultivo));
        OrdemB.setIdCultura(getIdCultura(cb_cultura));
        OrdemB.setIdOperacao(getIdOperacao(cb_operacao));
        OrdemB.setIdAplicacao(getIdAplicacao(cb_aplicacao));
        OrdemB.setEstadioFen(txt_estadio.getText());
        OrdemB.setCalda(Corretores.ConverQuilosDouble(txt_calda.getText()));
        OrdemB.setIdPonta(getIdPonta(cb_ponta));
        OrdemB.setPontaPulverizacao(cb_ponta.getSelectedItem().toString());
        OrdemB.setResponsavel(txt_responsavel.getText());
        OrdemB.setStatus(getStatusOrdem());
        OrdemB.setObservacao(txt_observacao.getText());
        OrdemB.setGerarBaixaEstoque(ch_gerarBaixaEstoque.isSelected());
        OrdemB.setIdLocalEstoque(getIdFazenda(cb_localEstoque));

    }

    private void preencherFormulario() {
        txt_data.setDate(Date.valueOf(OrdemB.getDtOrdem()));
        setJComboBoxAnoSafra(cb_safra, OrdemB.getIdSafra());
        setJComboBoxCultivo(cb_cultivo, OrdemB.getIdCultivo());
        setJComboBoxCultura(cb_cultura, OrdemB.getIdCultura());
        setJComboBoxOperacoes(cb_operacao, OrdemB.getIdOperacao());
        setJComboBoxAplicacoes(cb_aplicacao, OrdemB.getIdAplicacao());
        txt_calda.setText(new DecimalFormat("#,##0.0").format(OrdemB.getCalda()));
        txt_estadio.setText(OrdemB.getEstadioFen());
        setJComboBoxPontaPulverizacao(cb_ponta, OrdemB.getIdPonta());
        txt_responsavel.setText(OrdemB.getResponsavel());
        if (OrdemB.getStatus()) {
            ch_finalizado.setSelected(true);
        } else {
            ch_execucao.setSelected(true);
        }
        ch_gerarBaixaEstoque.setSelected(OrdemB.getGerarBaixaEstoque());
        setJComboBoxLocalEstoque(cb_localEstoque, OrdemB.getIdLocalEstoque());
    }

    private Boolean verificarBeans() {
        if (!Corretores.verificaDataMensal(txt_data.getDate())) {
            return false;
        }
        if (OrdemB.getIdSafra() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Safra!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }
        if (OrdemB.getIdCultivo() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Cultivo!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }
        if (OrdemB.getIdCultura() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Cultura!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }
        if (OrdemB.getIdOperacao() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Operação!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }
        if (getUsoAplicacao(cb_operacao) && OrdemB.getIdAplicacao() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Aplicacão!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }
        if (getUsoInsumos(cb_operacao) && TbInsumos.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Está opercação exige a aplicação de insumos!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }

        if (ch_gerarBaixaEstoque.isSelected() && cb_localEstoque.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Caso a opção Gerar Baixa em Estoque esteja marcado, o campo Local de Estoque é obrigatório!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }

        if (txt_responsavel.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "O responsavel é obrigatório!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }

        if (verificarTabelaInsumos() == false) {
            return false;
        }

        return true;
    }

    private Boolean verificarTabelaInsumos() {
        for (int i = 0; i < TbInsumos.getRowCount(); i++) {
            double qConsumido = (Double) TbInsumos.getValueAt(i, 8);
            double qRetirado = (Double) TbInsumos.getValueAt(i, 9);

            if (qRetirado != 0 && OrdemB.getGerarBaixaEstoque() == false) {
                JOptionPane.showMessageDialog(null, "Para retirar produtos do estoque a caixa Gerar Baixa Estoque deve ser habilitada!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
                return false;
            }

            if (qConsumido > qRetirado && OrdemB.getGerarBaixaEstoque() == true) {
                int cadastrar = JOptionPane.showConfirmDialog(null, "A Quantidade a retirar de " + TbInsumos.getValueAt(i, 1).toString() + " é menor do que a quantidade necessária para, Deseja Continuar?", "Atenção", JOptionPane.YES_NO_OPTION);
                if (cadastrar == JOptionPane.NO_OPTION) {
                    return false;
                }
            }
        }
        return true;
    }

    private Boolean getStatusOrdem() {
        if (ch_execucao.isSelected()) {
            return false;
        }
        if (ch_finalizado.isSelected()) {
            return true;
        }
        return false;
    }

    private Integer getIdOperacao(JComboBox<ListOperacoesBeans> comboBox) {
        int id = 0;
        if (comboBox.getSelectedIndex() > 0) {
            id = comboBox.getItemAt(comboBox.getSelectedIndex()).getID();
        }
        return id;
    }

    private Integer getIdPonta(JComboBox<ListPontaPulverizacaoBeans> comboBox) {
        int id = 0;
        if (comboBox.getSelectedIndex() > 0) {
            id = comboBox.getItemAt(comboBox.getSelectedIndex()).getID();
        }
        return id;
    }

    private Integer getIdCultura(JComboBox comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return listCultura.get(comboBox.getSelectedIndex() - 1).getIDCultura();
        }
        return 0;
    }

    private Integer getIdCultivo(JComboBox comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return listCultivo.get(comboBox.getSelectedIndex() - 1).getIDCultivo();
        }
        return 0;
    }

    private Integer getIdSafra(JComboBox comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return listAnoSafra.get(comboBox.getSelectedIndex() - 1).getIdSafra();
        }
        return 0;
    }

    private void setJComboBoxAnoSafra(JComboBox<AnoSafra> cb, Integer IdSafra) {
        for (int i = 0; i < cb.getModel().getSize(); i++) {
            if (cb.getModel().getElementAt(i).getIdSafra() == IdSafra) {
                cb.setSelectedIndex(i);
            }
        }
    }

    private void setJComboBoxCultivo(JComboBox<CultivoBeans> cb, Integer IdCultivo) {
        for (int i = 0; i < cb.getModel().getSize(); i++) {
            if (cb.getModel().getElementAt(i).getIDCultivo() == IdCultivo) {
                cb.setSelectedIndex(i);
            }
        }
    }

    private void setJComboBoxCultura(JComboBox<CulturaBeans> cb, Integer IdCultura) {
        for (int i = 0; i < cb.getModel().getSize(); i++) {
            if (cb.getModel().getElementAt(i).getIDCultura() == IdCultura) {
                cb.setSelectedIndex(i);
            }
        }
    }

    private void setJComboBoxOperacoes(JComboBox<ListOperacoesBeans> cb, Integer IdOperacao) {
        for (int i = 0; i < cb.getModel().getSize(); i++) {
            if (cb.getModel().getElementAt(i).getID() == IdOperacao) {
                cb.setSelectedIndex(i);
            }
        }
    }

    private void setJComboBoxAplicacoes(JComboBox<ListAplicacaoBeans> cb, Integer IdAplicacao) {
        for (int i = 0; i < cb.getModel().getSize(); i++) {
            if (cb.getModel().getElementAt(i).getID() == IdAplicacao) {
                cb.setSelectedIndex(i);
            }
        }
    }

    private void setJComboBoxPontaPulverizacao(JComboBox<ListPontaPulverizacaoBeans> cb, Integer IdPonta) {
        for (int i = 0; i < cb.getModel().getSize(); i++) {
            if (cb.getModel().getElementAt(i).getID() == IdPonta) {
                cb.setSelectedIndex(i);
            }
        }
    }

    private void setJComboBoxLocalEstoque(JComboBox<ListFazendasBeans> cb, Integer IdPonta) {
        for (int i = 0; i < cb.getModel().getSize(); i++) {
            if (cb.getModel().getElementAt(i).getID() == IdPonta) {
                cb.setSelectedIndex(i);
            }
        }
    }

    private Integer getIdAplicacao(JComboBox<ListAplicacaoBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0 && comboBox.getItemCount() > 0) {
           return comboBox.getModel().getElementAt(comboBox.getSelectedIndex()).getID();
        }
        return 0;
    }

    public frmPesquisarInsumo frmPesquisarInsumos() {
        PesquisarInsumo = new frmPesquisarInsumo(null, true);
        PesquisarInsumo.setLocationRelativeTo(null);
        PesquisarInsumo.tb_insumos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int i = PesquisarInsumo.tb_insumos.getSelectedRow();
                    InsumosB = new TbInsumosAplicBeans();
                    InsumosB.setIdInsumo((Integer) PesquisarInsumo.tb_insumos.getValueAt(i, 0));
                    InsumosB.setInsumo(PesquisarInsumo.tb_insumos.getValueAt(i, 3).toString());
                    InsumosB.setIdCategoria((Integer) PesquisarInsumo.tb_insumos.getValueAt(i, 1));
                    InsumosB.setCategoria(PesquisarInsumo.TbInsumos.getValueAt(i, 2).toString());
                    InsumosB.setDoseControl((Boolean) PesquisarInsumo.tb_insumos.getValueAt(i, 5));
                    InsumosB.setDoseMin((Double) PesquisarInsumo.tb_insumos.getValueAt(i, 6));
                    InsumosB.setDoseMax((Double) PesquisarInsumo.tb_insumos.getValueAt(i, 7));
                    InsumosB.setDoseAplic(new Double("0.000"));
                    InsumosB.setQuantConsumida(new Double("0.000"));
                    InsumosB.setQuantRetirada(new Double("0.000"));
                    InsumosB.setIdDB(0);
                    TbInsumos.addBeans(InsumosB);
                    PesquisarInsumo.txt_nomeComercial.setRequestFocusEnabled(true);
                    PesquisarInsumo.txt_nomeComercial.requestFocus();
                    PesquisarInsumo.txt_nomeComercial.selectAll();
                }
            }
        });
        PesquisarInsumo.setVisible(true);
        return PesquisarInsumo;
    }

    private Integer getIdFazenda(JComboBox comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return ListFazPermitidas.get(comboBox.getSelectedIndex() - 1).getID();
        }
        return 0;
    }

    private Double getAreaTalhao() {
        double area = 0.00;
        if (listTalhao == null) {
            listTalhao = DiversasD.ListTalhao();
        }
        for (int i = 0; i < listTalhao.size(); i++) {
            int idFazendaSel = getIdFazenda(cb_fazenda);
            int idFazenda = listTalhao.get(i).getIdFazenda();
            String nomeTalhao = listTalhao.get(i).getTalhao();
            if (idFazenda == idFazendaSel && nomeTalhao.equals(txt_talhao.getText())) {
                return listTalhao.get(i).getArea();
            }
        }
        return area;
    }

    private Integer getIDTalhao(String Talhao, Integer IdFazendaSel) {
        Integer ID = 0;
        if (listTalhao == null) {
            listTalhao = DiversasD.ListTalhao();
        }
        for (int i = 0; i < listTalhao.size(); i++) {
            int idFazendaSel = IdFazendaSel;
            int idFazendaLista = listTalhao.get(i).getIdFazenda();
            String nomeTalhao = listTalhao.get(i).getTalhao();
            if (idFazendaLista == idFazendaSel && nomeTalhao.equals(Talhao)) {
                return listTalhao.get(i).getID();
            }
        }
        return ID;
    }

    private Double atualizarAreaAplic() {
        double areaAplicada = 0.00;
        for (int i = 0; i < TbTalhao.getRowCount(); i++) {
            areaAplicada += (Double) TbTalhao.getValueAt(i, 7);
        }
        return areaAplicada;
    }

    private void atualizarConsumoInsumos() {
        double qConsumida = 0.000;
        for (int i = 0; i < TbInsumos.getRowCount(); i++) {
            qConsumida = (Double) TbInsumos.getValueAt(i, 7) * atualizarAreaAplic();
            TbInsumos.setValueAt(qConsumida, i, 8);
        }
    }

    private boolean getUsoInsumos(JComboBox<ListOperacoesBeans> cbOperacao) {
        boolean b = false;
        try {
            b = cbOperacao.getItemAt(cbOperacao.getSelectedIndex()).isUsoInsumos();
            btn_addInsumo.setEnabled(b);
            tb_insumos.setEnabled(b);
        } catch (Exception e) {
        }
        return b;
    }

    private boolean getUsoAplicacao(JComboBox<ListOperacoesBeans> cbOperacao) {
        boolean b = false;
        try {
            b = cbOperacao.getItemAt(cbOperacao.getSelectedIndex()).isUsoAplicacoes();
            cb_aplicacao.setEnabled(b);
        } catch (Exception e) {
        }
        return b;
    }
    
    private void getUsoFatoresClimaticos(JComboBox<ListOperacoesBeans> cbOperacao) {
        try {
            boolean b = cbOperacao.getItemAt(cbOperacao.getSelectedIndex()).isUsoFClimaticos();
            cb_aplicacao.setEnabled(b);
            txt_calda.setEnabled(b);
            cb_ponta.setEnabled(b);
        } catch (Exception e) {
        }
    }

    private void habilitarCampos() {
        txt_data.setEnabled(true);
        cb_safra.setEnabled(true);
        cb_cultivo.setEnabled(true);
        cb_cultura.setEnabled(true);
        cb_operacao.setEnabled(true);
        cb_aplicacao.setEnabled(true);
        txt_estadio.setEnabled(true);
        txt_calda.setEnabled(true);
        cb_ponta.setEnabled(true);
        txt_responsavel.setEnabled(true);
        ch_execucao.setEnabled(true);
        ch_gerarBaixaEstoque.setEnabled(true);
        txt_observacao.setEnabled(true);
        cb_localEstoque.setEnabled(true);
        btn_addTalhao.setEnabled(true);
        tb_talhoes.setEnabled(true);
        tb_insumos.setEnabled(true);
    }

    private void limparCampos() {
        txt_data.setDate(null);
        cb_safra.setSelectedIndex(0);
        cb_cultivo.setSelectedIndex(0);
        cb_cultura.setSelectedIndex(0);
        cb_operacao.setSelectedIndex(0);
        cb_aplicacao.setSelectedIndex(0);
        txt_estadio.setText("");
        txt_calda.setText("");
        cb_ponta.setSelectedIndex(0);
        txt_responsavel.setText("");
        ch_execucao.setSelected(true);
        ch_gerarBaixaEstoque.setSelected(false);
        txt_observacao.setText("");
        TbInsumos.limpar();
        TbTalhao.limpar();
    }

    private void desabilitarCampos() {
        txt_data.setEnabled(false);
        cb_safra.setEnabled(false);
        cb_cultivo.setEnabled(false);
        cb_cultura.setEnabled(false);
        cb_operacao.setEnabled(false);
        cb_aplicacao.setEnabled(false);
        txt_estadio.setEnabled(false);
        txt_calda.setEnabled(false);
        cb_ponta.setEnabled(false);
        txt_responsavel.setEnabled(false);
        ch_execucao.setEnabled(false);
        ch_gerarBaixaEstoque.setEnabled(false);
        txt_observacao.setEnabled(false);

    }

    private void popupFazendas() {
        if (menu_cbFazenda == null) {
            menu_cbFazenda = new JPopupMenu();
            menu_cbFazenda.setLayout(new FlowLayout());
            menu_cbFazenda.add(sc_jlistFazendas);
            menu_cbFazenda.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseExited(MouseEvent evt) {
                    menu_cbFazenda.setVisible(false);
                    menu_cbFazenda.repaint();
                }
            });
            jl_fazendas.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent evt) {
                    txt_fazenda.setText("");
                    int[] q = jl_fazendas.getSelectedIndices();
                    if (q.length > 1) {
                        txt_fazenda.setText("");
                        String s = "";
                        for (int i = 0; i < jl_fazendas.getModel().getSize(); i++) {
                            if (jl_fazendas.isSelectedIndex(i)) {
                                s += jl_fazendas.getModel().getElementAt(i).getNomeFazenda() + ", ";
                                txt_fazenda.setText(s);
                            }
                        }
                    } else if (q.length == 1) {
                        txt_fazenda.setText(jl_fazendas.getSelectedValue().toString());
                    }
                }
            });
        }
        if (!menu_cbFazenda.isVisible()) {
            menu_cbFazenda.setLocation(txt_fazenda.getLocationOnScreen().x, txt_fazenda.getLocationOnScreen().y);
            menu_cbFazenda.setSize(new Dimension(txt_fazenda.getSize().width, 200));
            jl_fazendas.setPreferredSize(new Dimension(txt_fazenda.getSize().width, jl_fazendas.getPreferredSize().height));
            menu_cbFazenda.setVisible(true);
        }
    }

    private String getSQLWhere() {
        String s = "";
        
        if (txt_data1.getDate() != null && txt_data2.getDate() != null) {
            s += " and os.data_ordem BETWEEN '" + Corretores.ConverterDateAAAA_MM_DD(txt_data1.getDate()) + "' and '" + Corretores.ConverterDateAAAA_MM_DD(txt_data2.getDate()) + "'";
        }
        if (cb_safraPesq.getSelectedIndex() > 0) {
            s += " and os.id_safra = " + getIdSafra(cb_safraPesq);
        }
        if (cb_cultivoPesq.getSelectedIndex() > 0) {
            s += " and os.id_cultivo = " + getIdCultivo(cb_cultivoPesq);
        }
        if (cb_culturaPesq.getSelectedIndex() > 0) {
            s += " and os.id_cultura = " + getIdCultura(cb_culturaPesq);
        }
        if (cb_operacaoPesq.getSelectedIndex() > 0) {
            s += "  and os.id_operacao = " + getIdOperacao(cb_operacaoPesq);
        }
        if (cb_aplicacaoPesq.getSelectedIndex() > 0) {
            s += "  and os.id_aplicacao = " + getIdAplicacao(cb_aplicacaoPesq);
        }
        int[] index = jl_fazendas.getSelectedIndices();
        if (!txt_talhaoPesq.getText().equals("")) {
            if (index.length == 1) {
                int idFazenda = (Integer) jl_fazendas.getModel().getElementAt(jl_fazendas.getSelectedIndex()).getID();
                s += " and ost.id_talhao = " + getIDTalhao(txt_talhaoPesq.getText(), idFazenda);
            } else {
                JOptionPane.showMessageDialog(null, "Para realizar buscas por talhão apenas uma fazenda deve estar selecionada!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            }
        }
        if (index.length >= 1) {
            s += " and ost.id_fazenda IN " + getIdFazendaSelect();
        }
        return s;
    }

    private String getIdFazendaSelect() {
        String s = "";
        for (int i = 0; i < jl_fazendas.getModel().getSize(); i++) {
            if (jl_fazendas.isSelectedIndex(i)) {
                s += ", " + jl_fazendas.getModel().getElementAt(i).getID();
            }
        }
        s = "(" + s.replaceFirst(",", "") + ")";
        return s;
    }

    private void exibirAreaTotal() {
        double areaT = 0;
        if (TbTalhao.getRowCount() > 0) {
            jLabel25.setVisible(true);
            txt_areaTotal.setVisible(true);
            txt_areaTotal.setText(new DecimalFormat("###,##0.00 ha").format(atualizarAreaAplic()));
        } else {
            jLabel25.setVisible(false);
            txt_areaTotal.setVisible(false);
            txt_areaTotal.setText(new DecimalFormat("###,##0.00 ha").format(areaT));
        }
    }

    private void gerarRelatorioOS(Integer idOS) {
        int imprimir = JOptionPane.showConfirmDialog(null, "Deseja Imprimir esta Ordem de Serviço?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (imprimir == JOptionPane.YES_OPTION) {
             if (relOp == null){
                 relOp = new RelatoriosOperacoes();
             }
             relOp.gerarImprimirOrdemServico(idOS);
        }
    }

        private void TipTextSomaValorPagto(JTable tabela) {
        tabela.setToolTipText(null);
        int[] linha = tabela.getSelectedRows();
        if (linha.length > 1) {
            String texto;
            double SomaAreaTalhao = 0.0;
            double SomaAreaPlanejada = 0.0;

            for (int i = 0; i < linha.length; i++) {
                SomaAreaTalhao +=  ((Double)TbOS.getValueAt(linha[i], TbOS.AREATALHAO));
                SomaAreaPlanejada += ((Double)TbOS.getValueAt(linha[i], TbOS.AREAPLANEJADA));
            }
            texto = "<html><FONT FACE=\"Tahoma\" SIZE=3>"
                    + "Area Talhões: <B>" + Corretores.ConverterParaDecimal(SomaAreaTalhao, "ha") + "</B><br>"
                    + "Area Planejada: <B>" + Corretores.ConverterParaDecimal(SomaAreaPlanejada, "ha") + "</B><br>"
                    + "</FONT></html>";
            ToolTipManager.sharedInstance().setInitialDelay(150);
            ToolTipManager.sharedInstance().setDismissDelay(30000);
            tabela.setToolTipText(texto);
        }
    }

    
    class CellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setHorizontalAlignment(SwingConstants.CENTER);
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            Object val = table.getValueAt(row, column);
            if (val instanceof Double) {
                if (column == 8) {
                    Double valorD = (Double) val;
                    setText(new DecimalFormat("#,###,##0").format(valorD));
                } else {
                    Double valorD = (Double) val;
                    setText(new DecimalFormat("#,###,##0.000").format(valorD));
                }
            }
            return this;
        }
    }

    class ListcellRenderer implements ListCellRenderer {

        JCheckBox ch;
        JLabel label;

        @Override
        public Component getListCellRendererComponent(JList jlist, Object e, int i, boolean bln, boolean bln1) {
            jlist.getModel().toString();
            ch = new JCheckBox();
            ch.setSelected(bln);
            ch.setText(jlist.getModel().getElementAt(i).toString());
            return ch;
        }

    }

}
