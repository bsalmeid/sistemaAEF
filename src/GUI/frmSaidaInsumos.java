/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Beans.AnoSafra;
import Beans.ListAplicacaoBeans;
import Beans.ListCategoriaInsumos;
import Beans.CultivoBeans;
import Beans.CulturaBeans;
import Beans.ListFazendasBeans;
import Beans.SaidaInsumosBeans;
import DAO.Diversas;
import DAO.SaidaInsumosDAO;
import static GUI.Principal.ListFazPermitidas;
import static GUI.Principal.listAnoSafra;
import static GUI.Principal.listAplicacao;
import static GUI.Principal.listCategoriaInsumos;
import static GUI.Principal.listCultivo;
import static GUI.Principal.listCultura;
import Icones.FormatarICO;
import TableModel.TableModelSaidaInsumos;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author agroa
 */
public class frmSaidaInsumos extends javax.swing.JInternalFrame {

    SaidaInsumosDAO SaidaD;
    SaidaInsumosBeans SaidaB;
    Diversas DiversasD;
    frmPesquisarInsumo frmPesqInsumos;
    TableModelSaidaInsumos TbInsumos;
    TableModelSaidaInsumos TbResumo;
    CentralizarTabela Centralizar;
    String Categoria;
    Integer rowIndexTbInsumos;

    public frmSaidaInsumos() {
        initComponents();
        DiversasD = new Diversas();
        SaidaD = new SaidaInsumosDAO();
        SaidaB = new SaidaInsumosBeans();
        carregarFazPermitidas();
        carregarAnoSafra();
        carregarEpocaCultivo();
        carregarCulturas();
        carregarTabelaInsumos();
        carregarTabelaResumo();
        carregarCategoriasInsumos();
        desabilitarCampos();
        ajustarTamanhoForm(jTabbedPane1.getSelectedIndex());
        txt_dtInicial.setDate(Corretores.PrimeiroDiaMes());
        txt_dtFinal.setDate(Corretores.UltimoDiaMes());
    }

    private JTable carregarTabelaInsumos() {
        tb_insumos.setModel(getTableModel());
        Centralizar = new CentralizarTabela();
        Centralizar.centralizarTabela(tb_insumos);
        ((DefaultTableCellRenderer) tb_insumos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        //tb_pedInsumos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tb_insumos.getColumnModel().getColumn(0).setPreferredWidth(40);// ID
        tb_insumos.getColumnModel().getColumn(1).setPreferredWidth(40);// ID Ordem
        tb_insumos.getColumnModel().getColumn(2).setPreferredWidth(90);// DATA
        tb_insumos.getColumnModel().getColumn(3).setPreferredWidth(0);// OPERAÇÃO
        tb_insumos.getColumnModel().getColumn(3).setMinWidth(0);
        tb_insumos.getColumnModel().getColumn(3).setMaxWidth(0);
        tb_insumos.getColumnModel().getColumn(4).setPreferredWidth(0);// ID LOCALSAIDA
        tb_insumos.getColumnModel().getColumn(4).setMinWidth(0);
        tb_insumos.getColumnModel().getColumn(4).setMaxWidth(0);
        tb_insumos.getColumnModel().getColumn(5).setPreferredWidth(90);// LOCAL DE SAIDA
        tb_insumos.getColumnModel().getColumn(6).setPreferredWidth(0);// ID SAFRA
        tb_insumos.getColumnModel().getColumn(6).setMinWidth(0);
        tb_insumos.getColumnModel().getColumn(6).setMaxWidth(0);
        tb_insumos.getColumnModel().getColumn(7).setPreferredWidth(70);// SAFRA
        tb_insumos.getColumnModel().getColumn(8).setPreferredWidth(0);// ID CULTIVO
        tb_insumos.getColumnModel().getColumn(8).setMinWidth(0);
        tb_insumos.getColumnModel().getColumn(8).setMaxWidth(0);
        tb_insumos.getColumnModel().getColumn(9).setPreferredWidth(70);// CULTIVO
        tb_insumos.getColumnModel().getColumn(10).setPreferredWidth(0);// ID CULTURA
        tb_insumos.getColumnModel().getColumn(10).setMinWidth(0);
        tb_insumos.getColumnModel().getColumn(10).setMaxWidth(0);
        tb_insumos.getColumnModel().getColumn(11).setPreferredWidth(70);// CULTURA
        tb_insumos.getColumnModel().getColumn(12).setPreferredWidth(0);// ID APLICAÇÃO 
        tb_insumos.getColumnModel().getColumn(12).setMinWidth(0);
        tb_insumos.getColumnModel().getColumn(12).setMaxWidth(0);
        tb_insumos.getColumnModel().getColumn(13).setPreferredWidth(100);// APLICAÇÃO
        tb_insumos.getColumnModel().getColumn(14).setPreferredWidth(60);// NDOC
        tb_insumos.getColumnModel().getColumn(15).setPreferredWidth(0);// RESPONSAVEL
        tb_insumos.getColumnModel().getColumn(15).setMinWidth(0);
        tb_insumos.getColumnModel().getColumn(15).setMaxWidth(0);
        tb_insumos.getColumnModel().getColumn(16).setPreferredWidth(0);// OBSERCAÇÃO
        tb_insumos.getColumnModel().getColumn(16).setMinWidth(0);
        tb_insumos.getColumnModel().getColumn(16).setMaxWidth(0);
        tb_insumos.getColumnModel().getColumn(17).setPreferredWidth(0);// ID INSUMO
        tb_insumos.getColumnModel().getColumn(17).setMinWidth(0);
        tb_insumos.getColumnModel().getColumn(17).setMaxWidth(0);
        tb_insumos.getColumnModel().getColumn(18).setPreferredWidth(90);// INSUMO
        tb_insumos.getColumnModel().getColumn(19).setPreferredWidth(60);// UNIDADE
        tb_insumos.getColumnModel().getColumn(20).setPreferredWidth(90);// CATEGORIA
        tb_insumos.getColumnModel().getColumn(21).setPreferredWidth(70);// QUANTIDADE
        return tb_insumos;
    }

    private JTable carregarTabelaResumo() {
        tb_saidaPesquisa.setModel(getTableModelResumo());
        Centralizar = new CentralizarTabela();
        Centralizar.centralizarTabela(tb_saidaPesquisa);
        ((DefaultTableCellRenderer) tb_saidaPesquisa.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        //tb_pedInsumos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tb_saidaPesquisa.getColumnModel().getColumn(0).setPreferredWidth(40);// ID
        tb_saidaPesquisa.getColumnModel().getColumn(1).setPreferredWidth(40);// ID Ordem
        tb_saidaPesquisa.getColumnModel().getColumn(2).setPreferredWidth(90);// DATA
        tb_saidaPesquisa.getColumnModel().getColumn(3).setPreferredWidth(0);// OPERAÇÃO
        tb_saidaPesquisa.getColumnModel().getColumn(3).setMinWidth(0);
        tb_saidaPesquisa.getColumnModel().getColumn(3).setMaxWidth(0);
        tb_saidaPesquisa.getColumnModel().getColumn(4).setPreferredWidth(0);// ID LOCALSAIDA
        tb_saidaPesquisa.getColumnModel().getColumn(4).setMinWidth(0);
        tb_saidaPesquisa.getColumnModel().getColumn(4).setMaxWidth(0);
        tb_saidaPesquisa.getColumnModel().getColumn(5).setPreferredWidth(90);// LOCAL DE SAIDA
        tb_saidaPesquisa.getColumnModel().getColumn(6).setPreferredWidth(0);// ID SAFRA
        tb_saidaPesquisa.getColumnModel().getColumn(6).setMinWidth(0);
        tb_saidaPesquisa.getColumnModel().getColumn(6).setMaxWidth(0);
        tb_saidaPesquisa.getColumnModel().getColumn(7).setPreferredWidth(70);// SAFRA
        tb_saidaPesquisa.getColumnModel().getColumn(8).setPreferredWidth(0);// ID CULTIVO
        tb_saidaPesquisa.getColumnModel().getColumn(8).setMinWidth(0);
        tb_saidaPesquisa.getColumnModel().getColumn(8).setMaxWidth(0);
        tb_saidaPesquisa.getColumnModel().getColumn(9).setPreferredWidth(70);// CULTIVO
        tb_saidaPesquisa.getColumnModel().getColumn(10).setPreferredWidth(0);// ID CULTURA
        tb_saidaPesquisa.getColumnModel().getColumn(10).setMinWidth(0);
        tb_saidaPesquisa.getColumnModel().getColumn(10).setMaxWidth(0);
        tb_saidaPesquisa.getColumnModel().getColumn(11).setPreferredWidth(70);// CULTURA
        tb_saidaPesquisa.getColumnModel().getColumn(12).setPreferredWidth(0);// ID APLICAÇÃO 
        tb_saidaPesquisa.getColumnModel().getColumn(12).setMinWidth(0);
        tb_saidaPesquisa.getColumnModel().getColumn(12).setMaxWidth(0);
        tb_saidaPesquisa.getColumnModel().getColumn(13).setPreferredWidth(100);// APLICAÇÃO
        tb_saidaPesquisa.getColumnModel().getColumn(14).setPreferredWidth(60);// NDOC
        tb_saidaPesquisa.getColumnModel().getColumn(15).setPreferredWidth(0);// RESPONSAVEL
        tb_saidaPesquisa.getColumnModel().getColumn(15).setMinWidth(0);
        tb_saidaPesquisa.getColumnModel().getColumn(15).setMaxWidth(0);
        tb_saidaPesquisa.getColumnModel().getColumn(16).setPreferredWidth(0);// OBSERCAÇÃO
        tb_saidaPesquisa.getColumnModel().getColumn(16).setMinWidth(0);
        tb_saidaPesquisa.getColumnModel().getColumn(16).setMaxWidth(0);
        tb_saidaPesquisa.getColumnModel().getColumn(17).setPreferredWidth(0);// ID INSUMO
        tb_saidaPesquisa.getColumnModel().getColumn(17).setMinWidth(0);
        tb_saidaPesquisa.getColumnModel().getColumn(17).setMaxWidth(0);
        tb_saidaPesquisa.getColumnModel().getColumn(18).setPreferredWidth(90);// INSUMO
        tb_saidaPesquisa.getColumnModel().getColumn(19).setPreferredWidth(60);// UNIDADE
        tb_saidaPesquisa.getColumnModel().getColumn(20).setPreferredWidth(90);// CATEGORIA
        tb_saidaPesquisa.getColumnModel().getColumn(21).setPreferredWidth(70);// QUANTIDADE

        return tb_saidaPesquisa;
    }

    private void carregarFazPermitidas() {
        ListFazPermitidas = new ArrayList<>();
        if (ListFazPermitidas.isEmpty()) {
            ListFazPermitidas = DiversasD.ListpmFazenda();
        }
        ListFazendasBeans l = new ListFazendasBeans();
        l.setID(0);
        l.setNomeFazenda("-");
        cb_fazenda.addItem(l);
        cb_fazendaPesq.addItem(l);
        for (ListFazendasBeans list : ListFazPermitidas) {
            cb_fazenda.addItem(list);
            cb_fazendaPesq.addItem(list);
        }
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
        cb_epocaCultivo.addItem(l);
        cb_cultivoPesq.addItem(l);
        for (CultivoBeans list : listCultivo) {
            cb_epocaCultivo.addItem(list);
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

    private void carregarAplicacoes(Integer idCultura) {
        if (listAplicacao == null) {
            listAplicacao = new ArrayList<>();
            listAplicacao = DiversasD.ListAplicacao();
        }
        ListAplicacaoBeans l = new ListAplicacaoBeans();
        l.setID(0);
        l.setAplicacao("-");
        cb_aplicacao.removeAllItems();
        cb_aplicacao.addItem(l);
        for (ListAplicacaoBeans list : listAplicacao) {
            if (list.getIDCultura() == idCultura) {
                cb_aplicacao.addItem(list);
            }
        }
    }

    private void carregarAplicacoesPesquisa(Integer idCultura) {

        if (listAplicacao == null) {
            listAplicacao = new ArrayList<>();
            listAplicacao = DiversasD.ListAplicacao();
        }
        ListAplicacaoBeans l = new ListAplicacaoBeans();
        l.setID(0);
        l.setAplicacao("-");
        cb_aplicacaoPesq.removeAllItems();
        cb_aplicacaoPesq.addItem(l);
        for (ListAplicacaoBeans list : listAplicacao) {
            if (list.getIDCultura() == idCultura) {
                cb_aplicacaoPesq.addItem(list);
            }
        }
    }

    private void carregarCategoriasInsumos() {
        if (listCategoriaInsumos == null) {
            listCategoriaInsumos = new ArrayList<>();
            listCategoriaInsumos = DiversasD.listCategoriaInsumos();
        }
        ListCategoriaInsumos c = new ListCategoriaInsumos();
        c.setID(0);
        c.setNome("-");
        cb_categoria.addItem(c);
        for (ListCategoriaInsumos list : listCategoriaInsumos) {
            cb_categoria.addItem(list);
        }

    }

    private TableModelSaidaInsumos getTableModel() {
        if (TbInsumos == null) {
            TbInsumos = new TableModelSaidaInsumos();
        }
        return TbInsumos;
    }

    private TableModelSaidaInsumos getTableModelResumo() {
        if (TbResumo == null) {
            TbResumo = new TableModelSaidaInsumos();
        }
        return TbResumo;
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
        jmenuEditar = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        txt_data =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        cb_fazenda = new javax.swing.JComboBox<>();
        cb_safra = new javax.swing.JComboBox<>();
        cb_cultura = new javax.swing.JComboBox<>();
        cb_epocaCultivo = new javax.swing.JComboBox<>();
        cb_aplicacao = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        txt_nDoc = new javax.swing.JTextField();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        txt_responsavel = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_observacao = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        txt_idInsumo = new javax.swing.JTextField();
        txt_insumo = new javax.swing.JTextField();
        javax.swing.JLabel jLabel13 = new javax.swing.JLabel();
        txt_unid = new javax.swing.JTextField();
        javax.swing.JLabel jLabel14 = new javax.swing.JLabel();
        txt_quantidade = new javax.swing.JTextField();
        btn_incluir = new javax.swing.JButton();
        btn_editarInsumo = new javax.swing.JButton();
        btn_excluirInsumo = new javax.swing.JButton();
        btn_pesqInsumo = new javax.swing.JButton();
        txt_categoria = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_insumos = new javax.swing.JTable();
        javax.swing.JLabel jLabel15 = new javax.swing.JLabel();
        txt_nOrdem = new javax.swing.JTextField();
        btn_novo = new javax.swing.JButton();
        btn_salvarPedido = new javax.swing.JButton();
        btn_editarPedido = new javax.swing.JButton();
        btn_excluirPedido = new javax.swing.JButton();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        cb_operacao = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel16 = new javax.swing.JLabel();
        txt_dtInicial =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        txt_dtFinal =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel17 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel18 = new javax.swing.JLabel();
        cb_fazendaPesq = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel19 = new javax.swing.JLabel();
        cb_operacaoPesq = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel20 = new javax.swing.JLabel();
        txt_insumoPesq = new javax.swing.JTextField();
        btn_pesqInsumoPesq = new javax.swing.JButton();
        javax.swing.JLabel jLabel21 = new javax.swing.JLabel();
        cb_categoria = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel22 = new javax.swing.JLabel();
        cb_safraPesq = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel23 = new javax.swing.JLabel();
        cb_cultivoPesq = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel24 = new javax.swing.JLabel();
        cb_culturaPesq = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel25 = new javax.swing.JLabel();
        txt_nDocPesq = new javax.swing.JTextField();
        btn_pesquisar = new javax.swing.JButton();
        cb_aplicacaoPesq = new javax.swing.JComboBox<>();
        txt_idInsumoPesq = new javax.swing.JTextField();
        javax.swing.JLabel jLabel26 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_saidaPesquisa = new javax.swing.JTable();

        jmenuEditar.setText("Editar Saída");
        jmenuEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmenuEditarActionPerformed(evt);
            }
        });
        jPopup.add(jmenuEditar);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Saída de Insumos");
        setMaximumSize(new java.awt.Dimension(1110, 674));
        setMinimumSize(new java.awt.Dimension(119, 50));

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("ID");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Data");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Local");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Safra");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Cultura");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Cultivo");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Aplicação");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Observação");

        txt_id.setEditable(false);
        txt_id.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        cb_cultura.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_culturaItemStateChanged(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Nº Documento");

        txt_nDoc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nDoc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_nDocFocusGained(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Responsável");

        txt_responsavel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_responsavelFocusGained(evt);
            }
        });

        txt_observacao.setColumns(20);
        txt_observacao.setRows(5);
        jScrollPane1.setViewportView(txt_observacao);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Insumo");

        txt_idInsumo.setEditable(false);
        txt_idInsumo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txt_insumo.setEditable(false);
        txt_insumo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Unid");

        txt_unid.setEditable(false);
        txt_unid.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Quant.");

        txt_quantidade.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_quantidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_quantidadeFocusGained(evt);
            }
        });

        btn_incluir.setText("Incluir");
        btn_incluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_incluirActionPerformed(evt);
            }
        });

        btn_editarInsumo.setText("Editar");
        btn_editarInsumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarInsumoActionPerformed(evt);
            }
        });

        btn_excluirInsumo.setText("Excluir");

        btn_pesqInsumo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar pequeno_1.png"))); // NOI18N
        btn_pesqInsumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesqInsumoActionPerformed(evt);
            }
        });

        txt_categoria.setEditable(false);
        txt_categoria.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_idInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txt_insumo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txt_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_pesqInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_unid, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_incluir, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_editarInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_excluirInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_pesqInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_idInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_insumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(txt_unid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(txt_quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_incluir)
                            .addComponent(btn_editarInsumo)
                            .addComponent(btn_excluirInsumo)
                            .addComponent(txt_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jScrollPane2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tb_insumos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb_insumos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_insumosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_insumos);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Ordem Aplicação");

        txt_nOrdem.setEditable(false);
        txt_nOrdem.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nOrdem.setText("0");

        btn_novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/inserir_40x40.png"))); // NOI18N
        btn_novo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_novo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novoActionPerformed(evt);
            }
        });

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

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Operação");

        cb_operacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Uso", "Transferencia", "Ajuste Estoque", "Outras Saídas" }));
        cb_operacao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_operacaoItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_salvarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_editarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_excluirPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(37, 37, 37)
                            .addComponent(jLabel15)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_nOrdem, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_data, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel12)
                            .addGap(7, 7, 7)
                            .addComponent(cb_operacao, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cb_safra, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, Short.MAX_VALUE)
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cb_epocaCultivo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cb_cultura, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, Short.MAX_VALUE)
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cb_aplicacao, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, Short.MAX_VALUE)
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_nDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_responsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane1))))
                .addContainerGap(202, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txt_nOrdem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(txt_data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cb_safra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cb_epocaCultivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(cb_operacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cb_cultura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cb_aplicacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txt_nDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txt_responsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_salvarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_editarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_excluirPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Saída", jPanel1);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Data");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("à");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Local ");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Operação");

        cb_operacaoPesq.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Uso", "Transferencia", "Ajuste Estoque", "Outras Saídas" }));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Insumo");

        txt_insumoPesq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_insumoPesqActionPerformed(evt);
            }
        });

        btn_pesqInsumoPesq.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar pequeno_1.png"))); // NOI18N
        btn_pesqInsumoPesq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesqInsumoPesqActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Categoria");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Safra");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("Cultivo");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Cultura");

        cb_culturaPesq.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_culturaPesqItemStateChanged(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("Nº Doc");

        txt_nDocPesq.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btn_pesquisar.setText("Pesquisar");
        btn_pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisarActionPerformed(evt);
            }
        });

        txt_idInsumoPesq.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("Aplicação");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_culturaPesq, 0, 105, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_aplicacaoPesq, 0, 134, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nDocPesq, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_idInsumoPesq, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txt_insumoPesq, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(btn_pesqInsumoPesq, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_categoria, 0, 127, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_pesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addGap(5, 5, 5)
                        .addComponent(txt_dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_fazendaPesq, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_operacaoPesq, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_safraPesq, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_cultivoPesq, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel16)
                    .addComponent(txt_dtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(txt_dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(cb_fazendaPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(cb_operacaoPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_safraPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(cb_cultivoPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel24)
                    .addComponent(cb_culturaPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(cb_aplicacaoPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(txt_nDocPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(txt_idInsumoPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_insumoPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesqInsumoPesq, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel21)
                    .addComponent(cb_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesquisar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tb_saidaPesquisa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb_saidaPesquisa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_saidaPesquisaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tb_saidaPesquisa);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Histórico", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1074, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed
        btn_salvarPedido.setEnabled(true);
        btn_editarPedido.setEnabled(false);
        txt_id.setEnabled(false);
        habilitarCampos();
        limparCampos();
    }//GEN-LAST:event_btn_novoActionPerformed

    private void btn_salvarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarPedidoActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(null, "Deseja Salvar esta Saida de Insumos?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION) {
            popularBeansSalvarSaida();
            if (verificarBeansSalvarSaida(SaidaB)) {
                if (SaidaD.inserirMovimento(SaidaB, TbInsumos)) {
                    limparCampos();
                }
            }
        }
    }//GEN-LAST:event_btn_salvarPedidoActionPerformed

    private void btn_editarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarPedidoActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja editar esta entrada?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION) {
            if (!txt_id.getText().equals("")) {
                popularBeansSalvarSaida();
                SaidaB.setIdDoc(Integer.parseInt(txt_id.getText()));
                if (verificarBeansSalvarSaida(SaidaB)) {
                    if (SaidaD.editarMovimento(SaidaB, TbInsumos)) {
                        limparCampos();
                        desabilitarCampos();
                        btn_editarPedido.setEnabled(false);
                        btn_excluirPedido.setEnabled(false);
                        txt_id.setText("");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione a entrada que deseja editar!", "Erro", 0, FormatarICO.ICObtnSair());
            }
        }
    }//GEN-LAST:event_btn_editarPedidoActionPerformed

    private void btn_excluirPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirPedidoActionPerformed
        int excluir = JOptionPane.showConfirmDialog(null, "Deseja excluir este movimento?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (excluir == JOptionPane.YES_OPTION) {
            int i = Integer.parseInt(txt_id.getText());
            if (i > 0) {
                if (SaidaD.excluirSaida(i)) {
                    limparCampos();
                    desabilitarCampos();
                    btn_editarPedido.setEnabled(false);
                    btn_excluirPedido.setEnabled(false);
                    btn_salvarPedido.setEnabled(false);
                }
            }
        }
    }//GEN-LAST:event_btn_excluirPedidoActionPerformed

    private void btn_pesqInsumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesqInsumoActionPerformed
        pesquisarInsumoOutrasOrigens();
    }//GEN-LAST:event_btn_pesqInsumoActionPerformed

    private void cb_culturaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_culturaItemStateChanged
        carregarAplicacoes(getIdCultura(cb_cultura));
    }//GEN-LAST:event_cb_culturaItemStateChanged

    private void btn_incluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_incluirActionPerformed
        incluirInsumo();
    }//GEN-LAST:event_btn_incluirActionPerformed

    private void txt_nDocFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_nDocFocusGained
        txt_nDoc.selectAll();
    }//GEN-LAST:event_txt_nDocFocusGained

    private void txt_responsavelFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_responsavelFocusGained
        txt_responsavel.selectAll();
    }//GEN-LAST:event_txt_responsavelFocusGained

    private void txt_quantidadeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_quantidadeFocusGained
        txt_quantidade.selectAll();
    }//GEN-LAST:event_txt_quantidadeFocusGained

    private void btn_pesqInsumoPesqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesqInsumoPesqActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_pesqInsumoPesqActionPerformed

    private void txt_insumoPesqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_insumoPesqActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_insumoPesqActionPerformed

    private void btn_pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisarActionPerformed
        String dtInicial = new SimpleDateFormat("dd/MM/yyyy").format(txt_dtInicial.getDate());
        String dtFinal = new SimpleDateFormat("dd/MM/yyyy").format(txt_dtFinal.getDate());
        SaidaD.pesquisarSaidas(TbResumo, getIdLocalEstoque(cb_fazendaPesq), dtInicial, dtFinal, SQLWhere());
    }//GEN-LAST:event_btn_pesquisarActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        ajustarTamanhoForm(jTabbedPane1.getSelectedIndex());
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void cb_culturaPesqItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_culturaPesqItemStateChanged
        carregarAplicacoesPesquisa(getIdCultura(cb_culturaPesq));
    }//GEN-LAST:event_cb_culturaPesqItemStateChanged

    private void jmenuEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmenuEditarActionPerformed
        int index = tb_saidaPesquisa.getSelectedRow();
        if (index >= 0) {
            btn_novo.doClick();
            if (SaidaD.BuscarSaida(TbInsumos, (Integer) TbResumo.getValueAt(index, 0), SaidaB)) {
                preencherFormulario();
                jTabbedPane1.setSelectedIndex(0);
                btn_salvarPedido.setEnabled(false);
                btn_editarPedido.setEnabled(true);
                btn_excluirPedido.setEnabled(true);
            }
        }
    }//GEN-LAST:event_jmenuEditarActionPerformed

    private void tb_saidaPesquisaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_saidaPesquisaMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            if (jPopup.isVisible() == true) {
                jPopup.setVisible(false);
            } else {
                jPopup.setVisible(true);
                jPopup.show(this, 0, 0);
                jPopup.setLocation(evt.getLocationOnScreen());
            }
        }
    }//GEN-LAST:event_tb_saidaPesquisaMouseClicked

    private void tb_insumosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_insumosMouseClicked
        if (evt.getClickCount() == 2) {
            int editar = JOptionPane.showConfirmDialog(null, "Deseja editar este insumo?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (editar == JOptionPane.YES_OPTION) {
                rowIndexTbInsumos = tb_insumos.getSelectedRow();
                txt_idInsumo.setText(TbInsumos.getValueAt(rowIndexTbInsumos, 17).toString());
                txt_insumo.setText(TbInsumos.getValueAt(rowIndexTbInsumos, 18).toString());
                txt_categoria.setText(TbInsumos.getValueAt(rowIndexTbInsumos, 20).toString());
                txt_unid.setText(TbInsumos.getValueAt(rowIndexTbInsumos, 19).toString());
                txt_quantidade.setText(TbInsumos.getValueAt(rowIndexTbInsumos, 21).toString());
                btn_incluir.setEnabled(false);
                btn_editarInsumo.setEnabled(true);
                btn_excluirInsumo.setEnabled(true);
               
            }
        }
    }//GEN-LAST:event_tb_insumosMouseClicked

    private void btn_editarInsumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarInsumoActionPerformed
        if (rowIndexTbInsumos != null) {
            if (editarInsumos(rowIndexTbInsumos)) {     
                btn_incluir.setEnabled(true);
                btn_editarInsumo.setEnabled(false);
                btn_excluirInsumo.setEnabled(false);
            }
        }
        rowIndexTbInsumos = null;
    }//GEN-LAST:event_btn_editarInsumoActionPerformed

    private void cb_operacaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_operacaoItemStateChanged
        getOperacaoUso();
    }//GEN-LAST:event_cb_operacaoItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_editarInsumo;
    javax.swing.JButton btn_editarPedido;
    private javax.swing.JButton btn_excluirInsumo;
    javax.swing.JButton btn_excluirPedido;
    private javax.swing.JButton btn_incluir;
    javax.swing.JButton btn_novo;
    javax.swing.JButton btn_pesqInsumo;
    javax.swing.JButton btn_pesqInsumoPesq;
    private javax.swing.JButton btn_pesquisar;
    javax.swing.JButton btn_salvarPedido;
    private javax.swing.JComboBox<ListAplicacaoBeans> cb_aplicacao;
    private javax.swing.JComboBox<ListAplicacaoBeans> cb_aplicacaoPesq;
    private javax.swing.JComboBox<ListCategoriaInsumos> cb_categoria;
    private javax.swing.JComboBox<CultivoBeans> cb_cultivoPesq;
    private javax.swing.JComboBox<CulturaBeans> cb_cultura;
    private javax.swing.JComboBox<CulturaBeans> cb_culturaPesq;
    private javax.swing.JComboBox<CultivoBeans> cb_epocaCultivo;
    private javax.swing.JComboBox<ListFazendasBeans> cb_fazenda;
    private javax.swing.JComboBox<ListFazendasBeans> cb_fazendaPesq;
    javax.swing.JComboBox<String> cb_operacao;
    private javax.swing.JComboBox<String> cb_operacaoPesq;
    private javax.swing.JComboBox<AnoSafra> cb_safra;
    private javax.swing.JComboBox<AnoSafra> cb_safraPesq;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopup;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenuItem jmenuEditar;
    private javax.swing.JTable tb_insumos;
    private javax.swing.JTable tb_saidaPesquisa;
    private javax.swing.JTextField txt_categoria;
    private com.toedter.calendar.JDateChooser txt_data;
    private com.toedter.calendar.JDateChooser txt_dtFinal;
    private com.toedter.calendar.JDateChooser txt_dtInicial;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_idInsumo;
    private javax.swing.JTextField txt_idInsumoPesq;
    private javax.swing.JTextField txt_insumo;
    private javax.swing.JTextField txt_insumoPesq;
    private javax.swing.JTextField txt_nDoc;
    private javax.swing.JTextField txt_nDocPesq;
    private javax.swing.JTextField txt_nOrdem;
    private javax.swing.JTextArea txt_observacao;
    private javax.swing.JTextField txt_quantidade;
    private javax.swing.JTextField txt_responsavel;
    private javax.swing.JTextField txt_unid;
    // End of variables declaration//GEN-END:variables

    private void limparCampos() {
        txt_id.setText("");
        txt_nOrdem.setText("0");
        txt_data.setDate(null);
        cb_operacao.setSelectedIndex(0);
        cb_fazenda.setSelectedIndex(0);
        cb_safra.setSelectedIndex(0);
        cb_epocaCultivo.setSelectedIndex(0);
        cb_cultura.setSelectedIndex(0);
        cb_aplicacao.setSelectedIndex(0);
        txt_nDoc.setText("0");
        txt_responsavel.setText("");
        txt_observacao.setText("");
        txt_insumo.setText("");
        txt_idInsumo.setText("");
        txt_unid.setText("");
        txt_categoria.setText("");
        txt_quantidade.setText("0");
        TbInsumos.limpar();
    }

    private void desabilitarCampos() {
        txt_id.setEnabled(false);
        txt_nOrdem.setEnabled(false);
        txt_data.setEnabled(false);
        cb_operacao.setEnabled(false);
        cb_fazenda.setEnabled(false);
        cb_safra.setEnabled(false);
        cb_epocaCultivo.setEnabled(false);
        cb_cultura.setEnabled(false);
        cb_aplicacao.setEnabled(false);
        txt_nDoc.setEnabled(false);
        txt_responsavel.setEnabled(false);
        txt_observacao.setEnabled(false);
        txt_insumo.setEnabled(false);
        txt_idInsumo.setEnabled(false);
        txt_unid.setEnabled(false);
        txt_categoria.setEnabled(false);
        txt_quantidade.setEnabled(false);
    }

    private void habilitarCampos() {
        txt_id.setEnabled(true);
        txt_nOrdem.setEnabled(true);
        txt_data.setEnabled(true);
        cb_operacao.setEnabled(true);
        cb_fazenda.setEnabled(true);
        cb_safra.setEnabled(true);
        cb_epocaCultivo.setEnabled(true);
        cb_cultura.setEnabled(true);
        cb_aplicacao.setEnabled(true);
        txt_nDoc.setEnabled(true);
        txt_responsavel.setEnabled(true);
        txt_observacao.setEnabled(true);
        txt_insumo.setEnabled(true);
        txt_idInsumo.setEnabled(true);
        txt_unid.setEnabled(true);
        txt_categoria.setEnabled(true);
        txt_quantidade.setEnabled(true);
    }

    private frmPesquisarInsumo pesquisarInsumoOutrasOrigens() {
        if (frmPesqInsumos == null) {
            frmPesqInsumos = new frmPesquisarInsumo(null, true);
            frmPesqInsumos.setLocationRelativeTo(null);
        }
        frmPesqInsumos.tb_insumos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int i = frmPesqInsumos.tb_insumos.getSelectedRow();
                    txt_idInsumo.setText(frmPesqInsumos.tb_insumos.getValueAt(i, 0).toString());
                    txt_insumo.setText(frmPesqInsumos.tb_insumos.getValueAt(i, 3).toString());
                    //    txt_idCategoria.setText(frmPesqInsumos.tb_insumos.getValueAt(i, 1).toString());
                    txt_categoria.setText(frmPesqInsumos.tb_insumos.getValueAt(i, 2).toString());
                    txt_unid.setText(frmPesqInsumos.tb_insumos.getValueAt(i, 4).toString());
                    frmPesqInsumos.dispose();
                }
            }
        });

        frmPesqInsumos.setVisible(true);
        return frmPesqInsumos;
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

    private Integer getIdAplicacao(JComboBox comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return listAplicacao.get(comboBox.getSelectedIndex() - 1).getID();
        }
        return 0;
    }

    private Integer getIdLocalEstoque(JComboBox comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return ListFazPermitidas.get(comboBox.getSelectedIndex() - 1).getID();
        }
        return 0;
    }

    private Integer getIdCategoria(JComboBox comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return listCategoriaInsumos.get(comboBox.getSelectedIndex() - 1).getID();
        }
        return 0;
    }

    private Integer setComboBoxAplicacao(Integer id) {
        int index = 0;
        for (int i = 0; i < listAplicacao.size(); i++) {
            if (listAplicacao.get(i).getID() == id) {
                index = i + 1;
                return index;
            }
        }
        return index;
    }

    private Integer setComboBoxCultivo(Integer id) {
        int index = 0;
        for (int i = 0; i < listCultivo.size(); i++) {
            if (listCultivo.get(i).getIDCultivo() == id) {
                index = i + 1;
                return index;
            }
        }
        return index;
    }

    private Integer setComboBoxSafra(Integer id) {
        int index = 0;
        for (int i = 0; i < listAnoSafra.size(); i++) {
            if (listAnoSafra.get(i).getIdSafra() == id) {
                index = i + 1;
                return index;
            }
        }
        return index;
    }

    private Integer setComboBoxLocalEstoque(Integer id) {
        int index = 0;
        for (int i = 0; i < ListFazPermitidas.size(); i++) {
            if (ListFazPermitidas.get(i).getID() == id) {
                index = i + 1;
                return index;
            }
        }
        return index;
    }

    private Integer setComboBoxCultura(Integer id) {
        int index = 0;
        for (int i = 0; i < listCultura.size(); i++) {
            if (listCultura.get(i).getIDCultura() == id) {
                index = i + 1;
                return index;
            }
        }
        return index;
    }

    private void incluirInsumo() {
        popularBeansIncluirInsumo();
        if (verificarBeansIncluirInsumo(SaidaB)) {
            TbInsumos.addBeans(SaidaB);
            txt_idInsumo.setText("0");
            txt_insumo.setText("");
            txt_unid.setText("");
            txt_categoria.setText("");
            txt_quantidade.setText("0");
        }
    }

    private boolean verificarBeansIncluirInsumo(SaidaInsumosBeans b) {
        if (!Corretores.verificarData(txt_data.getDate())) {
            return false;
        }
        if (b.getOperacao().equals("-")) {
            JOptionPane.showMessageDialog(null, "O campo operação deve ser selecionado", "Erro!", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getLocalSaida().equals("-")) {
            JOptionPane.showMessageDialog(null, "O campo Local de Estoque deve ser selecionado", "Erro!", 0, FormatarICO.ICObtnSair());
            return false;
        }

        if (getOperacaoUso()) {
            if (b.getSafra().equals("-")) {
                JOptionPane.showMessageDialog(null, "O campo safra deve ser selecionado", "Erro!", 0, FormatarICO.ICObtnSair());
                return false;
            }
            if (b.getCultura().equals("-")) {
                JOptionPane.showMessageDialog(null, "O campo Cultura deve ser selecionado", "Erro!", 0, FormatarICO.ICObtnSair());
                return false;
            }
            if (b.getCultivo().equals("-")) {
                JOptionPane.showMessageDialog(null, "O campo Cultivo deve ser selecionado", "Erro!", 0, FormatarICO.ICObtnSair());
                return false;
            }
        /*    if (b.getAplicacao().equals("-")) {
                JOptionPane.showMessageDialog(null, "O campo Aplicação deve ser selecionado", "Erro!", 0, FormatarICO.ICObtnSair());
                return false;
            }*/
        }

        if (b.getResonsavel().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo Responsavel deve ser preenchido!", "Erro!", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getIdInsumo() <= 0) {
            JOptionPane.showMessageDialog(null, "O campo Insumo deve ser selecionado", "Erro!", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getQuant() <= 0) {
            JOptionPane.showMessageDialog(null, "O campo Quantidade deve ser preenchido!", "Erro!", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getnDoc() < 0) {
            JOptionPane.showMessageDialog(null, "O campo Nº Documento deve ser preenchido!", "Erro!", 0, FormatarICO.ICObtnSair());
        }
        return true;
    }

    private void popularBeansIncluirInsumo() {
        SaidaB = new SaidaInsumosBeans();
        SaidaB.setID(0);
        SaidaB.setIDOrdem(Integer.parseInt(txt_nOrdem.getText()));
        SaidaB.setDtSaida(new SimpleDateFormat("dd/MM/yyyy").format(txt_data.getDate()));
        SaidaB.setOperacao(cb_operacao.getSelectedItem().toString());
        SaidaB.setIdLocalSaida(getIdLocalEstoque(cb_fazenda));
        SaidaB.setLocalSaida(cb_fazenda.getSelectedItem().toString());
        SaidaB.setIdSafra(getIdSafra(cb_safra));
        SaidaB.setSafra(cb_safra.getSelectedItem().toString());
        SaidaB.setIdCultivo(getIdCultivo(cb_epocaCultivo));
        SaidaB.setCultivo(cb_epocaCultivo.getSelectedItem().toString());
        SaidaB.setIdCultura(getIdCultura(cb_cultura));
        SaidaB.setCultura(cb_cultura.getSelectedItem().toString());
        SaidaB.setIdAplicao(getIdAplicacao(cb_aplicacao));
        SaidaB.setAplicacao(cb_aplicacao.getSelectedItem().toString());
        if (!txt_nDoc.getText().equals("")) {
            SaidaB.setnDoc(Integer.parseInt(txt_nDoc.getText()));
        } else {
            SaidaB.setnDoc(0);
        }
        SaidaB.setResonsavel(txt_responsavel.getText());
        SaidaB.setObservacao(txt_observacao.getText());
        if (!txt_idInsumo.getText().equals("")) {
            SaidaB.setIdInsumo(Integer.parseInt(txt_idInsumo.getText()));
        } else {
            SaidaB.setIdInsumo(0);
        }
        SaidaB.setNomeInsumo(txt_insumo.getText());
        SaidaB.setUnidade(txt_unid.getText());
        SaidaB.setCategoria(txt_categoria.getText());
        if (!txt_quantidade.getText().equals("")) {
            SaidaB.setQuant(Double.parseDouble(txt_quantidade.getText()));
        } else {
            SaidaB.setQuant(0.00);
        }
    }

    private void popularBeansSalvarSaida() {
        SaidaB = new SaidaInsumosBeans();
        SaidaB.setIDOrdem(Integer.parseInt(txt_nOrdem.getText()));
        SaidaB.setDtSaida(new SimpleDateFormat("dd/MM/yyyy").format(txt_data.getDate()));
        SaidaB.setOperacao(cb_operacao.getSelectedItem().toString());
        SaidaB.setIdLocalSaida(getIdLocalEstoque(cb_fazenda));
        SaidaB.setLocalSaida(cb_fazenda.getSelectedItem().toString());
        SaidaB.setIdSafra(getIdSafra(cb_safra));
        SaidaB.setSafra(cb_safra.getSelectedItem().toString());
        SaidaB.setIdCultivo(getIdCultivo(cb_epocaCultivo));
        SaidaB.setCultivo(cb_epocaCultivo.getSelectedItem().toString());
        SaidaB.setIdCultura(getIdCultura(cb_cultura));
        SaidaB.setCultura(cb_cultura.getSelectedItem().toString());
        SaidaB.setIdAplicao(getIdAplicacao(cb_aplicacao));
        SaidaB.setAplicacao(cb_aplicacao.getSelectedItem().toString());
        if (!txt_nDoc.getText().equals("")) {
            SaidaB.setnDoc(Integer.parseInt(txt_nDoc.getText()));
        } else {
            SaidaB.setnDoc(0);
        }
        SaidaB.setResonsavel(txt_responsavel.getText());
        SaidaB.setObservacao(txt_observacao.getText());
    }

    private boolean verificarBeansSalvarSaida(SaidaInsumosBeans b) {
        if (!Corretores.verificarData(txt_data.getDate())) {
            return false;
        }
        if (b.getOperacao().equals("-")) {
            JOptionPane.showMessageDialog(null, "O campo operação deve ser selecionado", "Erro!", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getLocalSaida().equals("-")) {
            JOptionPane.showMessageDialog(null, "O campo Local de Estoque deve ser selecionado", "Erro!", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (getOperacaoUso()) {
            if (b.getSafra().equals("-")) {
                JOptionPane.showMessageDialog(null, "O campo safra deve ser selecionado", "Erro!", 0, FormatarICO.ICObtnSair());
                return false;
            }
            if (b.getCultura().equals("-")) {
                JOptionPane.showMessageDialog(null, "O campo Cultura deve ser selecionado", "Erro!", 0, FormatarICO.ICObtnSair());
                return false;
            }
            if (b.getCultivo().equals("-")) {
                JOptionPane.showMessageDialog(null, "O campo Cultivo deve ser selecionado", "Erro!", 0, FormatarICO.ICObtnSair());
                return false;
            }
          /*  if (b.getAplicacao().equals("-")) {
                JOptionPane.showMessageDialog(null, "O campo Aplicação deve ser selecionado", "Erro!", 0, FormatarICO.ICObtnSair());
                return false;
            } */
        }

        if (b.getResonsavel().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo Responsavel deve ser preenchido!", "Erro!", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getnDoc() < 0) {
            JOptionPane.showMessageDialog(null, "O campo Nº Documento deve ser preenchido!", "Erro!", 0, FormatarICO.ICObtnSair());
        }
        return true;
    }

    private void preencherFormulario() {
        txt_id.setText(SaidaB.getIdDoc().toString());
        txt_nOrdem.setText(SaidaB.getIDOrdem().toString());
        txt_data.setDate(Corretores.ConverterStringDateDDMMAAAA(SaidaB.getDtSaida()));
        cb_operacao.setSelectedItem(SaidaB.getOperacao());
        cb_fazenda.setSelectedIndex(setComboBoxLocalEstoque(SaidaB.getIdLocalSaida()));
        cb_safra.setSelectedIndex(setComboBoxSafra(SaidaB.getIdSafra()));
        cb_epocaCultivo.setSelectedIndex(setComboBoxCultivo(SaidaB.getIdCultivo()));
        cb_cultura.setSelectedIndex(setComboBoxCultura(SaidaB.getIdCultura()));
        cb_aplicacao.setSelectedIndex(setComboBoxAplicacao(SaidaB.getIdAplicao()));
        txt_nDoc.setText(SaidaB.getnDoc().toString());
        txt_responsavel.setText(SaidaB.getResonsavel());
        txt_observacao.setText(SaidaB.getObservacao());
    }

    private void ajustarTamanhoForm(Integer index) {
        if (index == 0) {
            this.setSize(910, this.getPreferredSize().height);
        } else if (index >= 1) {
            this.setSize(1110, this.getPreferredSize().height);
        }
    }

    private Boolean editarInsumos(Integer i) {
        try {
            popularBeansIncluirInsumo();
            if (verificarBeansIncluirInsumo(SaidaB)) {
                for (int l = 0; l < TbInsumos.getRowCount(); l++) {
                    TbInsumos.setValueAt(SaidaB.getIDOrdem(), l, 1);  // id ordem;
                    TbInsumos.setValueAt(SaidaB.getDtSaida(), l, 2);  // DATA
                    TbInsumos.setValueAt(SaidaB.getOperacao(), l, 3); //OPERACAO
                    TbInsumos.setValueAt(SaidaB.getIdLocalSaida(), l, 4);  //ID LOCAL SAIDA
                    TbInsumos.setValueAt(SaidaB.getLocalSaida(), l, 5);  //LOCAL SAIDA
                    TbInsumos.setValueAt(SaidaB.getIdSafra(), l, 6);  //ID SAFRA
                    TbInsumos.setValueAt(SaidaB.getSafra(), l, 7);  // SAFRA
                    TbInsumos.setValueAt(SaidaB.getIdCultivo(), l, 8);  // ID CULTIVO
                    TbInsumos.setValueAt(SaidaB.getCultivo(), l, 9);  // CULTIVO
                    TbInsumos.setValueAt(SaidaB.getIdCultura(), l, 10);  // ID CULTURA
                    TbInsumos.setValueAt(SaidaB.getCultura(), l, 11);  // CULTURA
                    TbInsumos.setValueAt(SaidaB.getIdAplicao(), l, 12);  // ID APLICACAO
                    TbInsumos.setValueAt(SaidaB.getAplicacao(), l, 13);  // APLICACAO
                    TbInsumos.setValueAt(SaidaB.getnDoc(), l, 14);  //NDOC
                    TbInsumos.setValueAt(SaidaB.getResonsavel(), l, 15);  //RESPONSAVEL
                    TbInsumos.setValueAt(SaidaB.getObservacao(), l, 16);  // OBSERVACAO
                }

                TbInsumos.setValueAt(SaidaB.getIdInsumo(), i, 17);  // IDINSUMO
                TbInsumos.setValueAt(SaidaB.getNomeInsumo(), i, 18);  // INSUMO
                TbInsumos.setValueAt(SaidaB.getUnidade(), i, 19);  // UNIDADE
                TbInsumos.setValueAt(SaidaB.getCategoria(), i, 20);  // CATEGORIA
                TbInsumos.setValueAt(SaidaB.getQuant(), i, 21);  // QUANTIDADE
            } else {
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao editar Tabela! ", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }

    private String SQLWhere() {
        String s = "";

        if (cb_operacaoPesq.getSelectedIndex() > 0) {
            s += " and doc.operacao = '" + cb_operacaoPesq.getSelectedItem().toString() + "'";
            System.out.println(s);
        }
        if (cb_safraPesq.getSelectedIndex() > 0) {
            s += " and doc.idSafra = " + getIdSafra(cb_safraPesq);
        }
        if (cb_cultivoPesq.getSelectedIndex() > 0) {
            s += " and doc.idCultivo = " + getIdCultivo(cb_cultivoPesq);
        }
        if (cb_culturaPesq.getSelectedIndex() > 0) {
            s += " and doc.idCultura = " + getIdCultura(cb_culturaPesq);
        }
        if (cb_aplicacaoPesq.getSelectedIndex() > 0) {
            s += " and doc.idAplicacao = " + getIdAplicacao(cb_aplicacaoPesq);
        }
        if (cb_categoria.getSelectedIndex() > 0) {
            s += " and i.categoria = '" + cb_categoria.getSelectedItem().toString() + "'";
        }
        if (!txt_nDocPesq.getText().equals("")) {
            s += " and doc.nDoc = " + txt_nDocPesq.getText();
        }
        if (!txt_insumoPesq.getText().equals("")) {
            s += " and i.nome_insumo like '%" + txt_insumoPesq.getText() + "%'";
        }
        return s;
    }

    private Boolean getOperacaoUso() {
        if (!cb_operacao.getSelectedItem().equals("Uso")) {
            cb_safra.setEnabled(false);
            cb_epocaCultivo.setEnabled(false);
            cb_cultura.setEnabled(false);
            cb_aplicacao.setEnabled(false);
            return false;
        } else {
            cb_safra.setEnabled(true);
            cb_epocaCultivo.setEnabled(true);
            cb_cultura.setEnabled(true);
            cb_aplicacao.setEnabled(true);
        }
        return true;
    }

}
