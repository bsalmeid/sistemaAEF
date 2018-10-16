/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Almoxarifado.FrmCadAlmoxarif;
import Almoxarifado.CadAlmoxarifadoBeans;
import Almoxarifado.CadItensAlmoxCodigos;
import Beans.EntradaAlmoxarifadoBeans;
import Beans.ListFazendasBeans;
import Almoxarifado.LocalizadorAlmoxarifadoBeans;
import DAO.CadAlmoxarifadaoDAO;
import NFe.NFeBeans;
import static GUI.Principal.listAlmoxarifado;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import DAO.Diversas;
import DAO.EntradaAlmoxarifadoDAO;
import static GUI.Principal.ListFazPermitidas;
import static GUI.Principal.listLocalizadorAlmox;
import static GUI.frmLogin.UsuarioLogado;
import static GUI.frmLogin.dataAtual;
import Icones.FormatarICO;
import TableModel.TableModelProdutosNFe;
import Utilitarios.CentralizarTabela;
import NFe.LerArqXML;
import NFe.NFeProdutosBeans;
import TableModel.TableModelConsultaEntradaMercadoria;
import Utilitarios.Corretores;
import com.sun.glass.events.KeyEvent;
import com.toedter.calendar.JDateChooser;
import componentes.UJComboBox;
import java.awt.Component;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class FrmEntradaAlmoxarifado extends javax.swing.JInternalFrame {

    Diversas DiversasD;
    MaskFormatter CNPJMask;
    MaskFormatter CPFMask;
    NFeBeans nfeB;
    TableModelProdutosNFe TbProdutos;
    TableModelConsultaEntradaMercadoria TbEntrada;
    CentralizarTabela Centralizar;
    EntradaAlmoxarifadoBeans EntradaB;
    EntradaAlmoxarifadoDAO EntradaD;
    CadAlmoxarifadaoDAO CadD;
    FrmConsultaPecas frmConsulta;
    UJComboBox cb_localizadorTabela;
    TableColumn LocalizadorTableColumn;
    String CodigoProduto;
    TableModelCellRenderer cellRenderer;
    CellRendererConsultaEntrada cellRendererConsultaEntrada;

    public FrmEntradaAlmoxarifado() {
        initComponents();
        DiversasD = new Diversas();
        Centralizar = new CentralizarTabela();
        EntradaD = new EntradaAlmoxarifadoDAO();
        carregarTabela();
        carregarTabelaConsulta();
        carregarListLocalizador();
        CadD = new CadAlmoxarifadaoDAO();
        carregarAlmoxarifado();
        carregarFazPermitidas();
        maskFormaterCPF();
        desabilitarCampos();
        lbl_fazenda.setVisible(false);
        cb_fazenda.setVisible(false);
        try {
            txt_data.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(dataAtual));
        } catch (ParseException ex) {
            Logger.getLogger(frmResPagamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private void carregarListLocalizador() {
        if (listLocalizadorAlmox == null) {
            listLocalizadorAlmox = DiversasD.ListLocalizadorAlmox();
        }
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

    private void maskFormaterCPF() {
        try {
            CNPJMask = new MaskFormatter("##.###.###/####-##");
            CPFMask = new MaskFormatter("###.###.###-##");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    private JTable carregarTabela() {
        tb_pecas.setModel(getTableModel());
        ((DefaultTableCellRenderer) tb_pecas.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        Centralizar.centralizarTabela(tb_pecas);
        cellRenderer = new TableModelCellRenderer();

        tb_pecas.getColumnModel().getColumn(TbProdutos.ID_DB).setMaxWidth(0);
        tb_pecas.getColumnModel().getColumn(TbProdutos.ID_DB).setMinWidth(0);
        tb_pecas.getColumnModel().getColumn(TbProdutos.ID_DB).setPreferredWidth(0);

        tb_pecas.getColumnModel().getColumn(TbProdutos.ID_CADASTRO).setMaxWidth(80);
        tb_pecas.getColumnModel().getColumn(TbProdutos.ID_CADASTRO).setMinWidth(40);
        tb_pecas.getColumnModel().getColumn(TbProdutos.ID_CADASTRO).setPreferredWidth(70);

        tb_pecas.getColumnModel().getColumn(TbProdutos.NCM).setMaxWidth(0);
        tb_pecas.getColumnModel().getColumn(TbProdutos.NCM).setMinWidth(0);
        tb_pecas.getColumnModel().getColumn(TbProdutos.NCM).setPreferredWidth(0);

        tb_pecas.getColumnModel().getColumn(TbProdutos.VDESC).setMaxWidth(0);
        tb_pecas.getColumnModel().getColumn(TbProdutos.VDESC).setMinWidth(0);
        tb_pecas.getColumnModel().getColumn(TbProdutos.VDESC).setPreferredWidth(0);

        //tb_pecas.getColumnModel().getColumn(TbProdutos.XPROD).setMaxWidth(250);
        tb_pecas.getColumnModel().getColumn(TbProdutos.XPROD).setMinWidth(120);
        tb_pecas.getColumnModel().getColumn(TbProdutos.XPROD).setPreferredWidth(250);

        tb_pecas.getColumnModel().getColumn(TbProdutos.VALORUNITFINAL).setMaxWidth(0);
        tb_pecas.getColumnModel().getColumn(TbProdutos.VALORUNITFINAL).setMinWidth(0);
        tb_pecas.getColumnModel().getColumn(TbProdutos.VALORUNITFINAL).setPreferredWidth(0);

        tb_pecas.getColumnModel().getColumn(TbProdutos.CEST).setMaxWidth(0);
        tb_pecas.getColumnModel().getColumn(TbProdutos.CEST).setMinWidth(0);
        tb_pecas.getColumnModel().getColumn(TbProdutos.CEST).setPreferredWidth(0);

        tb_pecas.getColumnModel().getColumn(TbProdutos.ID_LOCALIZADOR).setMaxWidth(0);
        tb_pecas.getColumnModel().getColumn(TbProdutos.ID_LOCALIZADOR).setMinWidth(0);
        tb_pecas.getColumnModel().getColumn(TbProdutos.ID_LOCALIZADOR).setPreferredWidth(0);

        tb_pecas.getColumnModel().getColumn(TbProdutos.VUNCOM).setCellRenderer(cellRenderer);
        tb_pecas.getColumnModel().getColumn(TbProdutos.VPROD).setCellRenderer(cellRenderer);
        tb_pecas.getColumnModel().getColumn(TbProdutos.QCOM).setCellRenderer(cellRenderer);

        return tb_pecas;
    }

    private JTable carregarTabelaConsulta() {
        tb_entrada.setModel(getTableModelEntrada());
        ((DefaultTableCellRenderer) tb_entrada.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        Centralizar.centralizarTabela(tb_entrada);
        cellRendererConsultaEntrada = new CellRendererConsultaEntrada();

        tb_entrada.getColumnModel().getColumn(TbEntrada.ID_ENTRADA).setMaxWidth(90);
        tb_entrada.getColumnModel().getColumn(TbEntrada.ID_ENTRADA).setMinWidth(50);
        tb_entrada.getColumnModel().getColumn(TbEntrada.ID_ENTRADA).setPreferredWidth(70);

        tb_entrada.getColumnModel().getColumn(TbEntrada.ID_FORNECEDOR).setMaxWidth(0);
        tb_entrada.getColumnModel().getColumn(TbEntrada.ID_FORNECEDOR).setMinWidth(0);
        tb_entrada.getColumnModel().getColumn(TbEntrada.ID_FORNECEDOR).setPreferredWidth(0);

        tb_entrada.getColumnModel().getColumn(TbEntrada.CNPJ).setMaxWidth(0);
        tb_entrada.getColumnModel().getColumn(TbEntrada.CNPJ).setMinWidth(0);
        tb_entrada.getColumnModel().getColumn(TbEntrada.CNPJ).setPreferredWidth(0);

        tb_entrada.getColumnModel().getColumn(TbEntrada.DOCUMENTO_ENTRADA).setMaxWidth(90);
        tb_entrada.getColumnModel().getColumn(TbEntrada.DOCUMENTO_ENTRADA).setMinWidth(50);
        tb_entrada.getColumnModel().getColumn(TbEntrada.DOCUMENTO_ENTRADA).setPreferredWidth(70);

        tb_entrada.getColumnModel().getColumn(TbEntrada.ID_ITEM).setMaxWidth(90);
        tb_entrada.getColumnModel().getColumn(TbEntrada.ID_ITEM).setMinWidth(50);
        tb_entrada.getColumnModel().getColumn(TbEntrada.ID_ITEM).setPreferredWidth(70);

        tb_entrada.getColumnModel().getColumn(TbEntrada.FORNECEDOR).setMaxWidth(250);
        tb_entrada.getColumnModel().getColumn(TbEntrada.FORNECEDOR).setMinWidth(120);
        tb_entrada.getColumnModel().getColumn(TbEntrada.FORNECEDOR).setPreferredWidth(180);

        tb_entrada.getColumnModel().getColumn(TbEntrada.DESCRICAO).setMaxWidth(250);
        tb_entrada.getColumnModel().getColumn(TbEntrada.DESCRICAO).setMinWidth(120);
        tb_entrada.getColumnModel().getColumn(TbEntrada.DESCRICAO).setPreferredWidth(180);

        tb_entrada.getColumnModel().getColumn(TbEntrada.LOCALIZADOR).setPreferredWidth(70);

        tb_entrada.getColumnModel().getColumn(TbEntrada.ID_LOCALIZADOR).setMaxWidth(0);
        tb_entrada.getColumnModel().getColumn(TbEntrada.ID_LOCALIZADOR).setMinWidth(0);
        tb_entrada.getColumnModel().getColumn(TbEntrada.ID_LOCALIZADOR).setPreferredWidth(0);

        tb_entrada.getColumnModel().getColumn(TbEntrada.QUANTIDADE).setCellRenderer(cellRendererConsultaEntrada);
        tb_entrada.getColumnModel().getColumn(TbEntrada.VALORUNIT).setCellRenderer(cellRendererConsultaEntrada);
        return tb_entrada;
    }

    private TableModelProdutosNFe getTableModel() {
        if (TbProdutos == null) {
            TbProdutos = new TableModelProdutosNFe();
        }
        return TbProdutos;
    }

    private TableModelConsultaEntradaMercadoria getTableModelEntrada() {
        if (TbEntrada == null) {
            TbEntrada = new TableModelConsultaEntradaMercadoria();
        }
        return TbEntrada;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopup = new javax.swing.JPopupMenu();
        menu_consulta = new javax.swing.JMenu();
        menu_consulta_fabr = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menu_consulta_fornecedor = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator4 = new javax.swing.JPopupMenu.Separator();
        menu_consulta_lote = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        menu_consultaLocalizador = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        menu_consulta_localizador_lote = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenu1 = new javax.swing.JMenu();
        menu_cadastrar_novo = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator3 = new javax.swing.JPopupMenu.Separator();
        menu_cadastrar_conversao = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator5 = new javax.swing.JPopupMenu.Separator();
        menu_cadastrar_lote = new javax.swing.JMenuItem();
        jPopupEditar = new javax.swing.JPopupMenu();
        jMenuEditar = new javax.swing.JMenuItem();
        jTabbedPane = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txt_data =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        cb_almoxarifado = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        cb_operacao = new javax.swing.JComboBox<>();
        lbl_fazenda = new javax.swing.JLabel();
        cb_fazenda = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        txt_nNf = new javax.swing.JTextField();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        cb_tipoPessoa = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        txt_cpf = new javax.swing.JFormattedTextField();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        txt_nomeFantasia = new javax.swing.JTextField();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        txt_razaoSocial = new javax.swing.JTextField();
        lbl_id_fornecedor = new javax.swing.JLabel();
        txt_idFornecedor = new javax.swing.JTextField();
        btn_pesqFornecedor = new javax.swing.JButton();
        btn_importarDados = new javax.swing.JButton();
        btn_lerNFe = new javax.swing.JButton();
        javax.swing.JLabel jLabel16 = new javax.swing.JLabel();
        txt_obs = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        txt_idItem = new javax.swing.JTextField();
        btn_pesqItem = new javax.swing.JButton();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        txt_quantidade = new javax.swing.JTextField();
        javax.swing.JLabel jLabel13 = new javax.swing.JLabel();
        txt_unidade = new javax.swing.JTextField();
        javax.swing.JLabel jLabel14 = new javax.swing.JLabel();
        txt_valorUnitario = new javax.swing.JTextField();
        btn_add = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        btn_del = new javax.swing.JButton();
        txt_descricao = new javax.swing.JTextField();
        javax.swing.JLabel jLabel15 = new javax.swing.JLabel();
        cb_localizador = new componentes.UJComboBox();
        javax.swing.JLabel jLabel17 = new javax.swing.JLabel();
        btn_consultaLocalizador = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_pecas = new javax.swing.JTable();
        btn_novo = new javax.swing.JButton();
        btn_salvar = new javax.swing.JButton();
        btn_editarEntrada = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_entrada = new javax.swing.JTable();
        jXTaskPane1 = new org.jdesktop.swingx.JXTaskPane();
        jPanel6 = new javax.swing.JPanel();
        txt_dtInicial =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        txt_dtFinal =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        jLabel4 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        cb_almoxarifadoConsult = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel18 = new javax.swing.JLabel();
        cb_operacaoConsult = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel19 = new javax.swing.JLabel();
        txt_nNfConsult = new javax.swing.JTextField();
        javax.swing.JLabel jLabel21 = new javax.swing.JLabel();
        txt_cpfConsult = new javax.swing.JFormattedTextField();
        lbl_id_fornecedor1 = new javax.swing.JLabel();
        txt_idFornecedorConsult = new javax.swing.JTextField();
        btn_pesqFornecedor1 = new javax.swing.JButton();
        javax.swing.JLabel jLabel22 = new javax.swing.JLabel();
        txt_idItemConsult = new javax.swing.JTextField();
        btn_pesqItem1 = new javax.swing.JButton();
        javax.swing.JLabel jLabel23 = new javax.swing.JLabel();
        txt_descricaoConsult = new javax.swing.JTextField();
        javax.swing.JLabel jLabel24 = new javax.swing.JLabel();
        txt_codigoConsult = new javax.swing.JTextField();
        btn_pesquisar = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel25 = new javax.swing.JLabel();
        cb_localizadorConsult = new componentes.UJComboBox();

        menu_consulta.setText("Consultar Código");
        menu_consulta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        menu_consulta_fabr.setText("Por Código Fabricante");
        menu_consulta_fabr.setBorderPainted(true);
        menu_consulta_fabr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_consulta_fabrActionPerformed(evt);
            }
        });
        menu_consulta.add(menu_consulta_fabr);
        menu_consulta.add(jSeparator1);

        menu_consulta_fornecedor.setText("Por Código de Fornecedor");
        menu_consulta_fornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_consulta_fornecedorActionPerformed(evt);
            }
        });
        menu_consulta.add(menu_consulta_fornecedor);
        menu_consulta.add(jSeparator4);

        menu_consulta_lote.setText("Consultar Lote de Codigos");
        menu_consulta_lote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_consulta_loteActionPerformed(evt);
            }
        });
        menu_consulta.add(menu_consulta_lote);
        menu_consulta.add(jSeparator6);

        menu_consultaLocalizador.setText("Consulta Localizador Único");
        menu_consultaLocalizador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_consultaLocalizadorActionPerformed(evt);
            }
        });
        menu_consulta.add(menu_consultaLocalizador);
        menu_consulta.add(jSeparator7);

        menu_consulta_localizador_lote.setText("Consulta Localizador em Lote");
        menu_consulta_localizador_lote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_consulta_localizador_loteActionPerformed(evt);
            }
        });
        menu_consulta.add(menu_consulta_localizador_lote);

        jPopup.add(menu_consulta);
        jPopup.add(jSeparator2);

        jMenu1.setText("Cadastrar");
        jMenu1.setEnabled(false);
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        menu_cadastrar_novo.setText("Cadastrar Novo Item");
        menu_cadastrar_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_cadastrar_novoActionPerformed(evt);
            }
        });
        jMenu1.add(menu_cadastrar_novo);
        jMenu1.add(jSeparator3);

        menu_cadastrar_conversao.setText("Cadastrar Código do Fornecedor");
        menu_cadastrar_conversao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_cadastrar_conversaoActionPerformed(evt);
            }
        });
        jMenu1.add(menu_cadastrar_conversao);
        jMenu1.add(jSeparator5);

        menu_cadastrar_lote.setText("Cadastrar Código em Lote");
        menu_cadastrar_lote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_cadastrar_loteActionPerformed(evt);
            }
        });
        jMenu1.add(menu_cadastrar_lote);

        jPopup.add(jMenu1);

        jMenuEditar.setText("Editar Entrada");
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
        setTitle("Entrada de Mercadorias");

        jTabbedPane.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPaneStateChanged(evt);
            }
        });

        jPanel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

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
        jLabel3.setText("Origem");

        cb_operacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Compra", "Transferência", "Outras Entradas" }));
        cb_operacao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_operacaoItemStateChanged(evt);
            }
        });

        lbl_fazenda.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_fazenda.setText("Fazenda");

        cb_fazenda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_fazendaItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(txt_data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cb_almoxarifado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cb_operacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_fazenda)
                    .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Nº Doc");

        txt_nNf.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Tipo Pessoa");

        cb_tipoPessoa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "CPF", "CNPJ" }));
        cb_tipoPessoa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_tipoPessoaItemStateChanged(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("CNPJ");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Nome Fantasia");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Razão Social");

        lbl_id_fornecedor.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_id_fornecedor.setText("ID Fornecedor");
        lbl_id_fornecedor.setToolTipText("");

        txt_idFornecedor.setEditable(false);
        txt_idFornecedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btn_pesqFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar pequeno.png"))); // NOI18N
        btn_pesqFornecedor.setPreferredSize(new java.awt.Dimension(30, 25));
        btn_pesqFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesqFornecedorActionPerformed(evt);
            }
        });

        btn_importarDados.setText("Importar Dados");
        btn_importarDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_importarDadosActionPerformed(evt);
            }
        });

        btn_lerNFe.setText("Ler Xml NFe");
        btn_lerNFe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lerNFeActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Observação");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nNf, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_tipoPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_id_fornecedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_idFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn_pesqFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_importarDados, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_lerNFe, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_nomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_razaoSocial, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_obs)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_lerNFe)
                    .addComponent(btn_importarDados)
                    .addComponent(btn_pesqFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_idFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_id_fornecedor)
                    .addComponent(txt_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(cb_tipoPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txt_nNf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_nomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txt_razaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel16)
                    .addComponent(txt_obs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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
        txt_quantidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_quantidadeFocusGained(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Unid");

        txt_unidade.setEditable(false);
        txt_unidade.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Valor Unitário");

        txt_valorUnitario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_valorUnitario.setText("R$ 0,00");
        txt_valorUnitario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_valorUnitarioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_valorUnitarioFocusLost(evt);
            }
        });

        btn_add.setText("add");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        btn_editar.setText("Editar");
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });

        btn_del.setText("Del");
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_idItem, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btn_pesqItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_descricao, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_unidade, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_quantidade, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_valorUnitario, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_localizador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btn_consultaLocalizador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_add)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_editar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_del)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5)
                    .addComponent(txt_idItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesqItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txt_unidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txt_valorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_add)
                    .addComponent(btn_editar)
                    .addComponent(btn_del)
                    .addComponent(jLabel6)
                    .addComponent(txt_quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(txt_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cb_localizador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17)
                    .addComponent(btn_consultaLocalizador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        tb_pecas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tb_pecasKeyPressed(evt);
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
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
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_editarEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane.addTab("Entrada", jPanel4);

        tb_entrada.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_entrada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_entradaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_entrada);

        jXTaskPane1.setTitle("Opções de Pesquisa");

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
        jLabel18.setText("Origem");

        cb_operacaoConsult.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Compra", "Transferência" }));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Nº Doc");

        txt_nNfConsult.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("CNPJ");

        txt_cpfConsult.setEditable(false);

        lbl_id_fornecedor1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_id_fornecedor1.setText("ID Fornecedor");
        lbl_id_fornecedor1.setToolTipText("");

        txt_idFornecedorConsult.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_idFornecedorConsult.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_idFornecedorConsultFocusLost(evt);
            }
        });

        btn_pesqFornecedor1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar pequeno.png"))); // NOI18N
        btn_pesqFornecedor1.setPreferredSize(new java.awt.Dimension(30, 25));
        btn_pesqFornecedor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesqFornecedor1ActionPerformed(evt);
            }
        });

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

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("Localizador");

        cb_localizadorConsult.setEditable(true);
        cb_localizadorConsult.setMaximumRowCount(20);
        cb_localizadorConsult.setAutocompletar(true);

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
                        .addComponent(cb_almoxarifadoConsult, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_operacaoConsult, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nNfConsult, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_localizadorConsult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_cpfConsult, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_id_fornecedor1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_idFornecedorConsult, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn_pesqFornecedor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_idItemConsult, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn_pesqItem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_descricaoConsult, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_codigoConsult, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_pesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)))
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
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(cb_localizadorConsult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel25)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_pesquisar)
                    .addComponent(txt_codigoConsult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(txt_descricaoConsult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(btn_pesqItem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_idItemConsult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(btn_pesqFornecedor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_idFornecedorConsult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_id_fornecedor1)
                    .addComponent(txt_cpfConsult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addContainerGap())
        );

        jXTaskPane1.getContentPane().add(jPanel6);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jXTaskPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXTaskPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Consulta", jPanel5);

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

    private void btn_pesqItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesqItemActionPerformed
        FrmConsultaPecas consultaPecas = new FrmConsultaPecas();
        this.getParent().add(consultaPecas);
        consultaPecas.tb_pecas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    int index = consultaPecas.tb_pecas.getSelectedRow();
                    txt_idItem.setText(consultaPecas.TbPecas.getValueAt(index, 0).toString());
                    txt_descricao.setText(consultaPecas.TbPecas.getValueAt(index, consultaPecas.TbPecas.DESCRICAO).toString());
                    CodigoProduto = consultaPecas.TbPecas.getValueAt(index, consultaPecas.TbPecas.CODIGO).toString();
                    txt_unidade.setText("");
                    consultaPecas.dispose();
                }
            }
        });

        consultaPecas.setVisible(true);
    }//GEN-LAST:event_btn_pesqItemActionPerformed

    private void btn_pesqFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesqFornecedorActionPerformed
        frmCadFornecedores fornecedor = new frmCadFornecedores();
        this.getParent().add(fornecedor);
        fornecedor.btn_Salvar.setEnabled(false);
        fornecedor.btn_novo1.setEnabled(false);
        fornecedor.setVisible(true);
        fornecedor.tb_fornecedores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    int index = fornecedor.tb_fornecedores.getSelectedRow();
                    String CNPJ = fornecedor.TbForn.getValueAt(index, 7).toString();
                    if (CNPJ.length() > 14) {
                        cb_tipoPessoa.setSelectedIndex(2);
                    } else {
                        cb_tipoPessoa.setSelectedIndex(1);
                    }
                    txt_cpf.setText(CNPJ);
                    txt_idFornecedor.setText(fornecedor.TbForn.getValueAt(index, 0).toString());
                    txt_nomeFantasia.setText(fornecedor.TbForn.getValueAt(index, 1).toString());
                    txt_razaoSocial.setText(fornecedor.TbForn.getValueAt(index, 2).toString());
                    fornecedor.dispose();
                }
            }
        });
    }//GEN-LAST:event_btn_pesqFornecedorActionPerformed

    private void cb_tipoPessoaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_tipoPessoaItemStateChanged
        if (cb_tipoPessoa.getSelectedItem().toString().equals("CPF")) {
            txt_cpf.setValue(null);
            txt_cpf.setFormatterFactory(new DefaultFormatterFactory(CPFMask));
        } else if (cb_tipoPessoa.getSelectedItem().toString().equals("CNPJ")) {
            txt_cpf.setValue(null);
            txt_cpf.setFormatterFactory(new DefaultFormatterFactory(CNPJMask));
        } else {
            txt_cpf.setValue(null);
        }
    }//GEN-LAST:event_cb_tipoPessoaItemStateChanged

    private void btn_lerNFeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lerNFeActionPerformed
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XML FILES", "xml", "XML");
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(filter);
        int option = fc.showOpenDialog(tb_pecas);
        if (option == JFileChooser.APPROVE_OPTION) {
            //File fileName = new File(fc.getSelectedFile().getAbsolutePath());
            //String path = fc.getSelectedFile().getParentFile().getPath();
            LerArqXML carregarXML = new LerArqXML(fc.getSelectedFile().getAbsolutePath());
            nfeB = carregarXML.lerXML();
            txt_nomeFantasia.setText(nfeB.getxFant());
            txt_razaoSocial.setText(nfeB.getxNome());
            txt_nNf.setText(nfeB.getnNF().toString());
            if (nfeB.getCNPJ().length() > 11) {
                txt_cpf.setValue(null);
                txt_cpf.setFormatterFactory(new DefaultFormatterFactory(CNPJMask));
            } else {
                txt_cpf.setValue(null);
                txt_cpf.setFormatterFactory(new DefaultFormatterFactory(CPFMask));
            }
            txt_cpf.setText(nfeB.getCNPJ());
            TbProdutos.limpar();
            TbProdutos.addLista(nfeB.getListProdutos());
        }
    }//GEN-LAST:event_btn_lerNFeActionPerformed

    private void btn_importarDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_importarDadosActionPerformed
        FrmConsultarNFe consultarNFe = new FrmConsultarNFe();
        consultarNFe.tb_nfe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    TbProdutos.limpar();
                    int index = consultarNFe.tb_nfe.getSelectedRow();
                    nfeB = new NFeBeans();
                    nfeB = consultarNFe.TbNFe.getBeans(index);
                    nfeB.setListProdutos(consultarNFe.NFeD.ConsultarProdutosNFe(nfeB.getID()));
                    preencherCampos(nfeB);
                    consultarNFe.dispose();
                }
            }
        });
        this.getParent().add(consultarNFe);
        consultarNFe.setVisible(true);
    }//GEN-LAST:event_btn_importarDadosActionPerformed

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
            EntradaB = new EntradaAlmoxarifadoBeans();
            popularBeans(EntradaB);
            if (verificarBeans(EntradaB)) {
                if (EntradaD.salvarEntrada(EntradaB) == true) {
                    desabilitarCampos();
                    limparCampos();
                }
            }
        }
    }//GEN-LAST:event_btn_salvarActionPerformed

    private void btn_editarEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarEntradaActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja Editar esta Entrada?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION) {
            popularBeans(EntradaB);
            if (verificarBeans(EntradaB)) {
                if (EntradaD.EditarEntrada(EntradaB)) {
                    limparCampos();
                    desabilitarCampos();
                }
            }
        }
    }//GEN-LAST:event_btn_editarEntradaActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        int excluirSaidaInsumos = JOptionPane.showConfirmDialog(null, "Atenção, Está Ação Excluíra Permanentemente este Registro, Deseja Continuar", "Atenção", JOptionPane.YES_NO_OPTION);
        if (excluirSaidaInsumos == JOptionPane.YES_OPTION) {
            if (EntradaD.ExcluirEntrada(EntradaB.getID()) == true) {
                desabilitarCampos();
                limparCampos();
            }
        }
    }//GEN-LAST:event_btn_excluirActionPerformed

    private void menu_consulta_fabrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_consulta_fabrActionPerformed
        int index = tb_pecas.getSelectedRow();
        carregarFrmConsulta(index);
        frmConsulta.txt_codigo.setText(TbProdutos.getValueAt(index, TbProdutos.CPROD).toString());
        frmConsulta.ch_nao.setSelected(false);
        frmConsulta.ch_sim.setSelected(true);
        frmConsulta.btn_consulta.doClick();
    }//GEN-LAST:event_menu_consulta_fabrActionPerformed

    private void menu_consulta_fornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_consulta_fornecedorActionPerformed
        int index = tb_pecas.getSelectedRow();
        Integer ID_Fornecedor = Integer.parseInt(txt_idFornecedor.getText());
        String Codigo = TbProdutos.getValueAt(index, TbProdutos.CPROD).toString();
        if (ID_Fornecedor > 0) {
            carregarFrmConsulta(index);
            frmConsulta.txt_codigo.setText(Codigo);
            frmConsulta.txt_fornecedor.setText(txt_nomeFantasia.getText());
            frmConsulta.ID_Fornecedor = ID_Fornecedor;
            frmConsulta.ch_nao.setSelected(true);
            frmConsulta.ch_sim.setSelected(false);
            //frmConsulta.CadD.consultarCodigoPorFornecedor(frmConsulta.TbPecas, Codigo , ID_Fornecedor);
            frmConsulta.btn_consulta.doClick();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um fornecedor Cadastrado, ou cadastre o fornecedor desta NFe!", "Erro", 0, FormatarICO.ICObtnSair());
        }

    }//GEN-LAST:event_menu_consulta_fornecedorActionPerformed

    private void menu_consulta_loteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_consulta_loteActionPerformed
        String Codigos = "";
        int idFornecedor = new Integer(txt_idFornecedor.getText());
        if (idFornecedor > 0) {
            for (int i = 0; i < TbProdutos.getRowCount(); i++) {
                Codigos += ",'" + TbProdutos.getValueAt(i, TbProdutos.CPROD).toString() + "'";
            }
            preencherID_Cadastro(CadD.consultarListaDeCodigoCodigoFornecedor(idFornecedor, Codigos.replaceFirst(",", "")));
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um fornecedor Cadastrado, ou cadastre o fornecedor desta NFe!", "Erro", 0, FormatarICO.ICObtnSair());
        }

    }//GEN-LAST:event_menu_consulta_loteActionPerformed

    private void menu_cadastrar_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_cadastrar_novoActionPerformed
        int index = tb_pecas.getSelectedRow();
        int idFornecedor = new Integer(txt_idFornecedor.getText());
        if (idFornecedor > 0) {
            FrmCadAlmoxarif cadItem = new FrmCadAlmoxarif();
            this.getParent().add(cadItem);
            cadItem.btn_novo.doClick();
            cadItem.txt_codigo.setText(TbProdutos.getValueAt(index, TbProdutos.CPROD).toString());
            cadItem.txt_descricao.setText(TbProdutos.getValueAt(index, TbProdutos.XPROD).toString());
            cadItem.txt_fornecedor.setText(txt_nomeFantasia.getText());
            cadItem.CNPJ_Fornecedor = txt_cpf.getText();
            cadItem.ID_Fornecedor = new Integer(txt_idFornecedor.getText());
            cadItem.setVisible(true);
        }
    }//GEN-LAST:event_menu_cadastrar_novoActionPerformed

    private void menu_cadastrar_conversaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_cadastrar_conversaoActionPerformed
        int index = tb_pecas.getSelectedRow();
        int idFornecedor = new Integer(txt_idFornecedor.getText());
        if (idFornecedor > 0) {
            if (TbProdutos.getValueAt(index, TbProdutos.ID_CADASTRO) != null) {
                int cadastrar = JOptionPane.showConfirmDialog(null, "Deseja cadastrar esta conversão para este Item?", "Atenção", JOptionPane.YES_NO_OPTION);
                if (cadastrar == JOptionPane.YES_OPTION) {
                    CadD.CadastrarConversao(TbProdutos.getBeans(index), idFornecedor, txt_cpf.getText());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione o Código Interno ao qual este produto se refere!", "Erro", 0, FormatarICO.ICObtnSair());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um fornecedor Cadastrado, ou cadastre o fornecedor desta NFe!", "Erro", 0, FormatarICO.ICObtnSair());
        }

    }//GEN-LAST:event_menu_cadastrar_conversaoActionPerformed

    private void menu_cadastrar_loteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_cadastrar_loteActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(null, "Está Ação irá cadastrar todas as conversões da nota fiscal, deseja continuar?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION) {
            int idFornecedor = new Integer(txt_idFornecedor.getText());
            if (idFornecedor > 0) {
                CadD.CadastrarConversaoEmLOTE(TbProdutos.getLista(), idFornecedor, txt_cpf.getText());
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um fornecedor Cadastrado, ou cadastre o fornecedor desta NFe!", "Erro", 0, FormatarICO.ICObtnSair());
            }
        }
    }//GEN-LAST:event_menu_cadastrar_loteActionPerformed

    private void tb_pecasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_pecasKeyPressed
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_F2:
                menu_consulta_fabr.doClick();
                break;
            case KeyEvent.VK_F3:
                menu_consulta_fornecedor.doClick();
                break;
            case KeyEvent.VK_F4:
                menu_consulta_lote.doClick();
                break;
            case KeyEvent.VK_F5:
                //    menu_cadastrar_novo.doClick();
                break;
            case KeyEvent.VK_F6:
                //    menu_cadastrar_conversao.doClick();
                break;
            default:
                break;
        }
    }//GEN-LAST:event_tb_pecasKeyPressed

    private void cb_almoxarifadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_almoxarifadoItemStateChanged
        ComboBoxLocalizar(getIdAlmoxarifado(cb_almoxarifado), cb_localizador);
    }//GEN-LAST:event_cb_almoxarifadoItemStateChanged

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        if (!txt_idItem.getText().equals("") && !txt_idItem.getText().equals("0")) {
            AddProduto();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione o Código do Item!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }//GEN-LAST:event_btn_addActionPerformed

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

        if (evt.getClickCount() == 2) {
            int index = tb_pecas.getSelectedRow();
            preencherDadosProduto(index);
            btn_add.setEnabled(false);
            btn_editar.setEnabled(true);
            btn_del.setEnabled(true);
        } else {
            btn_add.setEnabled(true);
            btn_editar.setEnabled(false);
            btn_del.setEnabled(false);
        }
    }//GEN-LAST:event_tb_pecasMouseClicked

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

    private void txt_valorUnitarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorUnitarioFocusLost
        txt_valorUnitario.setText(Corretores.ConverterDecimalReais(txt_valorUnitario.getText()));
    }//GEN-LAST:event_txt_valorUnitarioFocusLost

    private void btn_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delActionPerformed
        int excluirSaidaInsumos = JOptionPane.showConfirmDialog(null, "Deseja Excluir este produto?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (excluirSaidaInsumos == JOptionPane.YES_OPTION) {
            int index = tb_pecas.getSelectedRow();
            TbProdutos.excluirLinha(index);
            btn_add.setEnabled(true);
            btn_editar.setEnabled(false);
            btn_del.setEnabled(false);
            limparDadosProduto();
        }
    }//GEN-LAST:event_btn_delActionPerformed

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
                lbl_id_fornecedor.setText("ID Fornecedor");
                btn_pesqFornecedor.setEnabled(true);
                btn_importarDados.setEnabled(true);
                btn_lerNFe.setEnabled(true);
                break;
            case 2: // caso Transferencia
                lbl_fazenda.setVisible(true);
                cb_fazenda.setVisible(true);
                lbl_id_fornecedor.setText("ID Fazenda");
                btn_pesqFornecedor.setEnabled(false);
                btn_importarDados.setEnabled(false);
                btn_lerNFe.setEnabled(false);
                cb_tipoPessoa.setSelectedIndex(1);
                txt_cpf.setText("019.899.109-63");
                break;
            case 3: // outras entradas
                lbl_fazenda.setVisible(false);
                cb_fazenda.setVisible(false);
                lbl_id_fornecedor.setText("ID Fornecedor");
                btn_pesqFornecedor.setEnabled(false);
                btn_importarDados.setEnabled(false);
                btn_lerNFe.setEnabled(false);
                cb_tipoPessoa.setSelectedIndex(1);
                txt_cpf.setText("000.000.000-00");
                break;
            default:
        }

    }//GEN-LAST:event_cb_operacaoItemStateChanged

    private void cb_fazendaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_fazendaItemStateChanged
        if (cb_operacao.getSelectedIndex() == 2) {
            txt_idFornecedor.setText(getIdFazenda(cb_fazenda).toString());
        }
    }//GEN-LAST:event_cb_fazendaItemStateChanged

    private void cb_almoxarifadoConsultItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_almoxarifadoConsultItemStateChanged
        ComboBoxLocalizar(getIdAlmoxarifado(cb_almoxarifadoConsult), cb_localizadorConsult);
    }//GEN-LAST:event_cb_almoxarifadoConsultItemStateChanged

    private void btn_pesqFornecedor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesqFornecedor1ActionPerformed
        frmCadFornecedores fornecedor = new frmCadFornecedores();
        this.getParent().add(fornecedor);
        fornecedor.btn_Salvar.setEnabled(false);
        fornecedor.btn_novo1.setEnabled(false);
        fornecedor.setVisible(true);
        fornecedor.tb_fornecedores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    int index = fornecedor.tb_fornecedores.getSelectedRow();
                    String CNPJ = fornecedor.TbForn.getValueAt(index, 7).toString();
                    if (CNPJ.length() > 14) {

                    } else {

                    }
                    txt_cpfConsult.setText(CNPJ);
                    txt_idFornecedorConsult.setText(fornecedor.TbForn.getValueAt(index, 0).toString());
                    fornecedor.dispose();
                }
            }
        });
    }//GEN-LAST:event_btn_pesqFornecedor1ActionPerformed

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

    private void txt_idFornecedorConsultFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_idFornecedorConsultFocusLost
        if (txt_idFornecedorConsult.getText().equals("")) {
            txt_cpfConsult.setText("");
        }
    }//GEN-LAST:event_txt_idFornecedorConsultFocusLost

    private void btn_pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisarActionPerformed
        TbProdutos.limpar();
        if (cb_almoxarifadoConsult.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione o Almoxarifado para Realizar Consulta!", "Erro", 0, FormatarICO.ICObtnSair());
        } else {
            EntradaD.consultarEntradas(getWhereConsultaEntrada(), TbEntrada);
        }
    }//GEN-LAST:event_btn_pesquisarActionPerformed

    private void tb_entradaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_entradaMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            if (jPopupEditar.isVisible() == true) {
                jPopupEditar.setVisible(false);
            } else {
                jPopupEditar.setVisible(true);
                jPopupEditar.show(this, 0, 0);
                jPopupEditar.setLocation(evt.getLocationOnScreen());
            }
        }
    }//GEN-LAST:event_tb_entradaMouseClicked

    private void jMenuEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuEditarActionPerformed
        Integer ID = (Integer) TbEntrada.getValueAt(tb_entrada.getSelectedRow(), TbEntrada.ID_ENTRADA);
        if (ID > 0) {
            limparCampos();
            EntradaB = EntradaD.getEntrada(ID);
            preencherFormulario(EntradaB);
            jTabbedPane.setSelectedIndex(0);
            habilitarCampos();
            btn_salvar.setEnabled(false);
            btn_editarEntrada.setEnabled(true);
            btn_excluir.setEnabled(true);
        }
    }//GEN-LAST:event_jMenuEditarActionPerformed

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
            ConsultaLocalizador.TbProdutos.addLista(EntradaD.consultaEntradaLocalizador(getIdAlmoxarifado(cb_almoxarifado), new Integer(txt_idItem.getText())));
            ConsultaLocalizador.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione o Almoxarifado de Destino!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }//GEN-LAST:event_btn_consultaLocalizadorActionPerformed

    private void menu_consulta_localizador_loteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_consulta_localizador_loteActionPerformed
        if (cb_almoxarifado.getSelectedIndex() != 0) {
            int consultarLote = JOptionPane.showConfirmDialog(null, "Está ação irá alterar todos os localizadores, Deseja Continuar?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (consultarLote == JOptionPane.YES_OPTION) {
                preencherID_Localizador(EntradaD.consultarLocalizadorEmLote(getIdAlmoxarifado(cb_almoxarifado), getLoteIDCadastro()));
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione o Almoxarifado de Destino!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }//GEN-LAST:event_menu_consulta_localizador_loteActionPerformed

    private void jTabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPaneStateChanged
        if (jTabbedPane.getSelectedIndex() == 0) {
            getRootPane().setDefaultButton(null);
        } else if (jTabbedPane.getSelectedIndex() == 1) {
            getRootPane().setDefaultButton(btn_pesquisar);
        }
    }//GEN-LAST:event_jTabbedPaneStateChanged

    private void txt_quantidadeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_quantidadeFocusGained
        txt_quantidade.selectAll();
    }//GEN-LAST:event_txt_quantidadeFocusGained

    private void txt_valorUnitarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorUnitarioFocusGained
        txt_valorUnitario.selectAll();
    }//GEN-LAST:event_txt_valorUnitarioFocusGained

    private void menu_consultaLocalizadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_consultaLocalizadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menu_consultaLocalizadorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    javax.swing.JButton btn_consultaLocalizador;
    private javax.swing.JButton btn_del;
    private javax.swing.JButton btn_editar;
    javax.swing.JButton btn_editarEntrada;
    javax.swing.JButton btn_excluir;
    private javax.swing.JButton btn_importarDados;
    private javax.swing.JButton btn_lerNFe;
    javax.swing.JButton btn_novo;
    javax.swing.JButton btn_pesqFornecedor;
    javax.swing.JButton btn_pesqFornecedor1;
    javax.swing.JButton btn_pesqItem;
    javax.swing.JButton btn_pesqItem1;
    private javax.swing.JButton btn_pesquisar;
    javax.swing.JButton btn_salvar;
    private javax.swing.JComboBox<CadAlmoxarifadoBeans> cb_almoxarifado;
    private javax.swing.JComboBox<CadAlmoxarifadoBeans> cb_almoxarifadoConsult;
    private javax.swing.JComboBox<ListFazendasBeans> cb_fazenda;
    private componentes.UJComboBox cb_localizador;
    private componentes.UJComboBox cb_localizadorConsult;
    private javax.swing.JComboBox<String> cb_operacao;
    private javax.swing.JComboBox<String> cb_operacaoConsult;
    private javax.swing.JComboBox<String> cb_tipoPessoa;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuEditar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    public javax.swing.JPopupMenu jPopup;
    private javax.swing.JPopupMenu jPopupEditar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JTabbedPane jTabbedPane;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane1;
    private javax.swing.JLabel lbl_fazenda;
    private javax.swing.JLabel lbl_id_fornecedor;
    private javax.swing.JLabel lbl_id_fornecedor1;
    private javax.swing.JMenuItem menu_cadastrar_conversao;
    private javax.swing.JMenuItem menu_cadastrar_lote;
    private javax.swing.JMenuItem menu_cadastrar_novo;
    public javax.swing.JMenu menu_consulta;
    private javax.swing.JMenuItem menu_consultaLocalizador;
    public javax.swing.JMenuItem menu_consulta_fabr;
    private javax.swing.JMenuItem menu_consulta_fornecedor;
    private javax.swing.JMenuItem menu_consulta_localizador_lote;
    private javax.swing.JMenuItem menu_consulta_lote;
    private javax.swing.JTable tb_entrada;
    private javax.swing.JTable tb_pecas;
    private javax.swing.JTextField txt_codigoConsult;
    private javax.swing.JFormattedTextField txt_cpf;
    private javax.swing.JFormattedTextField txt_cpfConsult;
    private com.toedter.calendar.JDateChooser txt_data;
    private javax.swing.JTextField txt_descricao;
    private javax.swing.JTextField txt_descricaoConsult;
    private com.toedter.calendar.JDateChooser txt_dtFinal;
    private com.toedter.calendar.JDateChooser txt_dtInicial;
    private javax.swing.JTextField txt_idFornecedor;
    private javax.swing.JTextField txt_idFornecedorConsult;
    private javax.swing.JTextField txt_idItem;
    private javax.swing.JTextField txt_idItemConsult;
    private javax.swing.JTextField txt_nNf;
    private javax.swing.JTextField txt_nNfConsult;
    private javax.swing.JTextField txt_nomeFantasia;
    private javax.swing.JTextField txt_obs;
    private javax.swing.JTextField txt_quantidade;
    private javax.swing.JTextField txt_razaoSocial;
    private javax.swing.JTextField txt_unidade;
    private javax.swing.JTextField txt_valorUnitario;
    // End of variables declaration//GEN-END:variables

    private void AddProduto() {
        NFeProdutosBeans produto = new NFeProdutosBeans();
        produto.setID_DB(0);
        produto.setID_Cadastro(new Integer(txt_idItem.getText()));
        produto.setcProd(CodigoProduto);
        produto.setxProd(txt_descricao.getText());
        produto.setuCom(txt_unidade.getText());
        produto.setqCom(Corretores.ConverterStringDouble(txt_quantidade.getText()));
        produto.setvUnCom(Corretores.ConverterReaisDouble(txt_valorUnitario.getText()));
        produto.setvProd(produto.getqCom() * produto.getvUnCom());
        produto.setLocalizador(getLocalizador());
        produto.setId_localizador(getIdLocalizador(cb_localizador));
        TbProdutos.addBeans(produto);
        limparDadosProduto();
    }

    private void EditarProduto(Integer index) {
        NFeProdutosBeans produto = TbProdutos.getBeans(index);
        produto.setID_Cadastro(new Integer(txt_idItem.getText()));
        produto.setcProd(CodigoProduto);
        produto.setxProd(txt_descricao.getText());
        produto.setuCom(txt_unidade.getText());
        produto.setqCom(Corretores.ConverterStringDouble(txt_quantidade.getText()));
        produto.setvUnCom(Corretores.ConverterReaisDouble(txt_valorUnitario.getText()));
        produto.setvProd(produto.getqCom() * produto.getvUnCom());
        produto.setLocalizador(getLocalizador());
        produto.setId_localizador(getIdLocalizador(cb_localizador));
        TbProdutos.SetBeans(index, produto);
        limparDadosProduto();
    }

    private void preencherDadosProduto(Integer index) {
        txt_idItem.setText(TbProdutos.getValueAt(index, TbProdutos.ID_CADASTRO).toString());
        CodigoProduto = TbProdutos.getValueAt(index, TbProdutos.CPROD).toString();
        txt_descricao.setText(TbProdutos.getValueAt(index, TbProdutos.XPROD).toString());
        txt_unidade.setText(TbProdutos.getValueAt(index, TbProdutos.UCOM).toString());
        txt_quantidade.setText(Corretores.ConverterDoubleDecimal((Double) TbProdutos.getValueAt(index, TbProdutos.QCOM)));
        txt_valorUnitario.setText(Corretores.ConverterDoubleReais((Double) TbProdutos.getValueAt(index, TbProdutos.VUNCOM)));
        setComboBoxLocalizador((Integer) (TbProdutos.getValueAt(index, TbProdutos.ID_LOCALIZADOR)));

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

    private String getLocalizador() {
        LocalizadorAlmoxarifadoBeans localizador;
        try {
            localizador = (LocalizadorAlmoxarifadoBeans) cb_localizador.getSelectedObject();
            return localizador.getDescricao();
        } catch (Exception e) {
            return "";
        }
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

    private void setJComboBoxFazenda(JComboBox<ListFazendasBeans> cb, Integer IdFazenda) {
        for (int i = 0; i < cb.getModel().getSize(); i++) {
            if (cb.getItemAt(i).getID() == IdFazenda) {
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

    private void preencherID_Cadastro(List<CadItensAlmoxCodigos> listaID) {
        for (int i = 0; i < TbProdutos.getRowCount(); i++) {
            for (CadItensAlmoxCodigos codigo : listaID) {
                if (codigo.getCodigo().equals(TbProdutos.getValueAt(i, TbProdutos.CPROD))) {
                    TbProdutos.setValueAt(codigo.getID(), i, TbProdutos.ID_DB);
                    TbProdutos.setValueAt(codigo.getID_ITEM(), i, TbProdutos.ID_CADASTRO);
                    break;
                }
            }
        }
    }

    private void carregarFrmConsulta(Integer indexOrigem) {
        frmConsulta = new FrmConsultaPecas();
        frmConsulta.tb_pecas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    int index = frmConsulta.tb_pecas.getSelectedRow();
                    TbProdutos.setValueAt(frmConsulta.TbPecas.getValueAt(index, 0), indexOrigem, TbProdutos.ID_CADASTRO);
                    frmConsulta.dispose();
                }
            }
        });
        this.getParent().add(frmConsulta);
        frmConsulta.setVisible(true);
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

    public void preencherCampos(NFeBeans nfe) {
        txt_nNf.setText(nfe.getnNF().toString());
        txt_idFornecedor.setText(nfe.getID_Fornecedor().toString());
        cb_tipoPessoa.setSelectedIndex(2);
        txt_cpf.setText(nfe.getCNPJ());
        txt_nomeFantasia.setText(nfe.getxFant());
        txt_razaoSocial.setText(nfe.getxNome());
        TbProdutos.addLista(nfe.getListProdutos());
    }

    public void preencherFormulario(EntradaAlmoxarifadoBeans beans) {
        txt_data.setDate(Corretores.ConverterStringDateDDMMAAAA(beans.getData()));
        setJComboBoxAlmoxarifado(cb_almoxarifado, beans.getID_Almoxarifado());
        setJComboBoxFazenda(cb_fazenda, beans.getID_Fazenda());
        cb_operacao.setSelectedIndex(beans.getID_Operacao());
        txt_nNf.setText(beans.getnDoc().toString());
        txt_idFornecedor.setText(beans.getID_Fornecedor().toString());
        cb_tipoPessoa.setSelectedIndex(beans.getID_TipoPessoa());
        txt_cpf.setText(beans.getCNPJ());
        txt_nomeFantasia.setText(beans.getNomeFantasia());
        txt_razaoSocial.setText(beans.getRazaoSocial());
        TbProdutos.addLista(beans.getProdutos());
    }

    private void habilitarCampos() {
        txt_data.setEnabled(true);
        cb_almoxarifado.setEnabled(true);
        cb_operacao.setEnabled(true);
        cb_fazenda.setEnabled(true);
        txt_nNf.setEnabled(true);
        cb_tipoPessoa.setEnabled(true);
        txt_cpf.setEnabled(true);
        txt_nomeFantasia.setEnabled(true);
        txt_razaoSocial.setEnabled(true);
        btn_salvar.setEnabled(true);
        btn_add.setEnabled(true);
        btn_pesqItem.setEnabled(true);
    }

    private void limparCampos() {
        //txt_data.setDate(null);
        cb_almoxarifado.setSelectedIndex(0);
        cb_operacao.setSelectedIndex(0);
        cb_fazenda.setSelectedIndex(0);
        txt_nNf.setText("");
        cb_tipoPessoa.setSelectedIndex(0);
        txt_cpf.setText("");
        txt_idFornecedor.setText("0");
        txt_nomeFantasia.setText("");
        txt_razaoSocial.setText("");
        TbProdutos.limpar();
    }

    private void limparDadosProduto() {
        txt_idItem.setText("0");
        txt_descricao.setText("");
        txt_unidade.setText("");
        txt_quantidade.setText("0");
        txt_valorUnitario.setText("R$ 0,00");
        cb_localizador.setSelectedIndex(0);
        CodigoProduto = null;
    }

    private void popularBeans(EntradaAlmoxarifadoBeans beans) {
        beans.setID_Almoxarifado(getIdAlmoxarifado(cb_almoxarifado));
        beans.setUsuario(UsuarioLogado);
        beans.setData(new SimpleDateFormat("dd/MM/yyyy").format(txt_data.getDate()));
        beans.setID_Operacao(getIdOrigem(cb_operacao));
        beans.setID_Fazenda(getIdFazenda(cb_fazenda));
        beans.setID_TipoPessoa(cb_tipoPessoa.getSelectedIndex());
        beans.setnDoc(getInteger(txt_nNf));
        beans.setCNPJ(txt_cpf.getText());
        beans.setID_Fornecedor(getInteger(txt_idFornecedor));
        beans.setNomeFantasia(txt_nomeFantasia.getText());
        beans.setRazaoSocial(txt_razaoSocial.getText());
        beans.setObservacao(txt_obs.getText());
        beans.setProdutos(TbProdutos.getLista());
    }

    private Integer getInteger(JTextField txt) {
        if (txt.getText().equals("")) {
            return 0;
        } else {
            return new Integer(txt.getText());
        }
    }

    private void desabilitarCampos() {
        txt_data.setEnabled(false);
        cb_almoxarifado.setEnabled(false);
        cb_operacao.setEnabled(false);
        cb_fazenda.setEnabled(false);
        txt_nNf.setEnabled(false);
        cb_tipoPessoa.setEnabled(false);
        txt_cpf.setEnabled(false);
        txt_nomeFantasia.setEnabled(false);
        txt_razaoSocial.setEnabled(false);
        btn_editarEntrada.setEnabled(false);
        btn_salvar.setEnabled(false);
        btn_excluir.setEnabled(false);
        btn_add.setEnabled(false);
        btn_pesqItem.setEnabled(false);
    }

    private boolean verificarBeans(EntradaAlmoxarifadoBeans beans) {
        if (beans.getnDoc() == 0) {
            JOptionPane.showMessageDialog(null, "Informe o Número do Documento de Origem!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (beans.getID_Almoxarifado() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione o Almoxarifado de Destino!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (beans.getID_Operacao() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione o Tipo de Operação de Origem!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (beans.getID_Operacao() == 1) {
            if (beans.getID_Fornecedor() == 0) {
                JOptionPane.showMessageDialog(null, "O Campo Id Fornecedor é Obrigatório!", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
        }
        if (beans.getID_Operacao() == 2) {
            if (beans.getID_Fazenda() == 0) {
                JOptionPane.showMessageDialog(null, "O Campo Fazenda é Obrigatório, para transfêrencias!", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
        }
        if (beans.getProdutos().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Adicione ao menos um item a entrada!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }

    private String getStringDate(Date dt) {
        String s;
        s = new SimpleDateFormat("dd/MM/yyyy").format(dt);
        return s;
    }

    public String getWhereConsultaEntrada() {
        String s = "";
        if (txt_dtInicial.getDate() != null && txt_dtFinal.getDate() != null) {
            s += " and ea.data Between '" + Corretores.ConverterParaSQL(getStringDate(txt_dtInicial.getDate()))
                    + "' AND '" + Corretores.ConverterParaSQL(getStringDate(txt_dtFinal.getDate())) + "'";
        }
        if (cb_almoxarifadoConsult.getSelectedIndex() != 0) {
            s += " and ea.id_almoxarifado = " + getIdAlmoxarifado(cb_almoxarifadoConsult);
        }
        if (cb_operacaoConsult.getSelectedIndex() != 0) {
            s += " and ea.id_operacao = " + getIdOrigem(cb_operacaoConsult);
        }
        if (!txt_nNfConsult.getText().equals("")) {
            s += " and ea.n_doc = " + txt_nNfConsult.getText();
        }
        if (cb_localizadorConsult.getSelectedIndex() != 0) {
            s += " and eai.id_localizador = " + getIdLocalizador(cb_localizadorConsult);
        }
        if (!txt_idFornecedorConsult.getText().equals("")) {
            s += " and ea.id_fornecedor = " + txt_idFornecedorConsult.getText();
        }
        if (!txt_idItemConsult.getText().equals("")) {
            s += " and eai.id_cadastro = " + txt_idItemConsult.getText();
        }
        if (!txt_descricaoConsult.getText().equals("") && txt_idItemConsult.getText().equals("")) {
            s += " and eai.descricao like '%" + txt_descricaoConsult.getText() + "%'";
        }
        if (!txt_codigoConsult.getText().equals("")) {
            s += " and eai.codigoProduto like '%" + txt_codigoConsult.getText() + "%'";
        }

        if (!s.equals("")) {
            s = " WHERE " + s.replaceFirst("and", "");
        }
        System.out.println(s);
        return s;
    }

    public String getLoteIDCadastro() {
        String s = "";
        for (int i = 0; i < TbProdutos.getRowCount(); i++) {
            s += "," + TbProdutos.getValueAt(i, TbProdutos.ID_CADASTRO);
        }
        if (!s.equals("")) {
            s = s.replaceFirst(",", "");
        }
        return s;
    }

    private void preencherID_Localizador(List<NFeProdutosBeans> listaID) {
        for (int i = 0; i < TbProdutos.getRowCount(); i++) {
            for (NFeProdutosBeans codigo : listaID) {
                if (codigo.getID_Cadastro().equals(TbProdutos.getValueAt(i, TbProdutos.ID_CADASTRO))) {
                    TbProdutos.setValueAt(codigo.getLocalizador(), i, TbProdutos.LOCALIZADOR);
                    TbProdutos.setValueAt(codigo.getId_localizador(), i, TbProdutos.ID_LOCALIZADOR);
                    break;
                }
            }
        }
    }
     
}

class TableModelCellRenderer extends DefaultTableCellRenderer {

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

class CellRendererConsultaEntrada extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setHorizontalAlignment(SwingConstants.CENTER);
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Object val = table.getValueAt(row, column);
        TableModelConsultaEntradaMercadoria tableModel = (TableModelConsultaEntradaMercadoria) table.getModel();
        if (column == tableModel.QUANTIDADE) {
            Double valorD = (Double) val;
            setText(new DecimalFormat("###,##0.00").format(valorD));
        }
        if (column == tableModel.VALORUNIT) {
            Double valorD = (Double) val;
            setText(new DecimalFormat("R$ ###,##0.00").format(valorD));
        }
        return this;
    }

}
