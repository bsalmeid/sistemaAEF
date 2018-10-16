/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Beans.AnoSafra;
import Beans.ListAplicacaoBeans;
import Beans.CEIBeans;
import Beans.ListColaborador;
import Beans.CultivoBeans;
import Beans.CulturaBeans;
import Beans.ListFazendasBeans;
import Beans.InventarioBeans;
import Beans.ListOperacoesBeans;
import Beans.OperacoesBeans;
import DAO.Diversas;
import DAO.OperacoesDAO;
import static GUI.Principal.ListFazPermitidas;
import static GUI.Principal.frmAplicacoes;
import static GUI.Principal.listAnoSafra;
import static GUI.Principal.listAplicacao;
import static GUI.Principal.listCEI;
import static GUI.Principal.listCultivo;
import static GUI.Principal.listCultura;
import static GUI.Principal.listInventario;
import static GUI.Principal.listOperacoes;
import static GUI.Principal.listTalhao;
import Icones.FormatarICO;
import TableModel.TableModelDadosClima;
import TableModel.TableModelTalhoesAplic;
import TableModel.TbDadosClimaticosBeans;
import TableModel.TbTalhaoAplicBeans;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import com.toedter.calendar.JDateChooser;
import componentes.UJComboBox;
import java.awt.Component;
import java.awt.Dimension;
import java.math.BigInteger;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author agroa
 */
public class frmOperacoes extends javax.swing.JDialog {

    Diversas DiversasD;
    OperacoesBeans OperacoesB;
    OperacoesDAO OperacoesD;
    TableModelTalhoesAplic TbTalhaoOP;
    CentralizarTabela Centralizar;
    CellRenderer CellRenderer;
    frmDialogData DialogData;
    public Integer IdOS;
    Integer IdOperador;
    List<ListColaborador> listColaborador;
    TableModelDadosClima TbClima;
    MaskFormatter HoraMask;

    public frmOperacoes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        DiversasD = new Diversas();
        OperacoesB = new OperacoesBeans();
        OperacoesD = new OperacoesDAO();
        Centralizar = new CentralizarTabela();
        IdOS = 0;
        IdOperador = 0;
        desabilitarCampos();
        carregarFazPermitidas();
        carregarAnoSafra();
        carregarEpocaCultivo();
        carregarCulturas();
        carregarInventario();
        TabelaTalhoes();
        TabelaClima();
        //getJComboxFuncionario();
        maskFormaterCPF();
    }

    private void getJComboxFuncionario() {
        /*
        cb_funcionario.setEditable(true);
        JTextComponent editor = (JTextComponent) cb_funcionario.getEditor().getEditorComponent();
        editor.setDocument(new SearchableComboBox(cb_funcionario));
        cb_funcionario.setSelectedIndex(-1);
         */
    }

    private void maskFormaterCPF() {
        try {
            HoraMask = new MaskFormatter("##:##");
            txt_horaClima.setFormatterFactory(new DefaultFormatterFactory(HoraMask));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    private void carregarListCEI() {
        if (listCEI == null) {
            listCEI = DiversasD.ListCEI();
        }
        CEIBeans b = new CEIBeans();
        b.setIdCEI(0);
        b.setDescricao("-");
        b.setCEI(new BigInteger("0"));
        b.setStatus(true);
        //cb_fazenda1.addItem(b);
        for (CEIBeans list : listCEI) {
            //  cb_fazenda1.addItem(list);
        }

    }

    private void carregarListOperador(Integer idFazenda) {
        if (listColaborador == null) {
            listColaborador = DiversasD.ListFuncionarios(idFazenda);
        } else {
            listColaborador.clear();
            listColaborador = DiversasD.ListFuncionarios(idFazenda);
        }
        ListColaborador b = new ListColaborador();
        b.setID(0);
        b.setCodigo(0);
        b.setNome("-");
        cb_funcionario.removeAllItems();
        cb_funcionario.addItem(b);
        for (ListColaborador list : listColaborador) {
            cb_funcionario.addItem(list);
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
        cb_fazenda1.addItem(l);
        for (ListFazendasBeans list : ListFazPermitidas) {
            cb_fazenda.addItem(list);
            cb_fazenda1.addItem(list);
        }

    }

    private void carregarAnoSafra() {
        
        if (listAnoSafra == null) {
            listAnoSafra = DiversasD.listAnoSafra();
        }
        AnoSafra l = new AnoSafra();
        l.setIdSafra(0);
        l.setAnoSafra("-");
        cb_safra.addItem(l);
        for (AnoSafra list : listAnoSafra) {
            cb_safra.addItem(list);
        }
    }

    private void carregarEpocaCultivo() {
       
        if (listCultivo == null) {
            listCultivo = DiversasD.ListCultivo();
        }
        CultivoBeans l = new CultivoBeans();
        l.setIDCultivo(0);
        l.setCultivo("-");
        cb_cultivo.addItem(l);
        for (CultivoBeans list : listCultivo) {
            cb_cultivo.addItem(list);
        }
    }

    private void carregarCulturas() {
        
        if (listCultura == null) {
            listCultura = DiversasD.ListCultura();
        }
        CulturaBeans l = new CulturaBeans();
        l.setIDCultura(0);
        l.setNomeCultura("-");
        cb_cultura.addItem(l);
        for (CulturaBeans list : listCultura) {
            cb_cultura.addItem(list);
        }
    }

    private void carregarInventario() {
        if (listInventario == null) {
            listInventario = DiversasD.ListInventario();
        }
    }

    private void carregarAplicacoes(Integer idCultura, JComboBox<ListAplicacaoBeans> cbDestino) {
        if (listAplicacao == null) {
            //listAplicacao = new ArrayList<>();
            listAplicacao = DiversasD.ListAplicacao();
        }
        cbDestino.removeAllItems();
        ListAplicacaoBeans l = new ListAplicacaoBeans();
        l.setID(0);
        l.setAplicacao("-");
        cbDestino.addItem(l);
        for (ListAplicacaoBeans list : listAplicacao) {
            if (Objects.equals(list.getIDCultura(), idCultura)) {
                cbDestino.addItem(list);
            }
        }
    }

    private void carregarOperacoes(Integer idCultura, JComboBox<ListOperacoesBeans> cbDestino) {

        if (listOperacoes == null) {
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
        tb_talhoes.getColumnModel().getColumn(3).setPreferredWidth(0);// Data Inicio
        tb_talhoes.getColumnModel().getColumn(3).setMinWidth(0);
        tb_talhoes.getColumnModel().getColumn(3).setMaxWidth(0);
        tb_talhoes.getColumnModel().getColumn(4).setPreferredWidth(0);// Data Termino
        tb_talhoes.getColumnModel().getColumn(4).setMinWidth(0);
        tb_talhoes.getColumnModel().getColumn(4).setMaxWidth(0);
        tb_talhoes.getColumnModel().getColumn(8).setPreferredWidth(0);// ID DB
        tb_talhoes.getColumnModel().getColumn(8).setMinWidth(0);
        tb_talhoes.getColumnModel().getColumn(8).setMaxWidth(0);

        tb_talhoes.getColumnModel().getColumn(6).setCellRenderer(CellRenderer);
        tb_talhoes.getColumnModel().getColumn(7).setCellRenderer(CellRenderer);
        tb_talhoes.getColumnModel().getColumn(9).setCellRenderer(CellRenderer);
        return tb_talhoes;
    }

    private JTable TabelaClima() {
        tb_dadosClima.setModel(getTableModelClima());
        ((DefaultTableCellRenderer) tb_dadosClima.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        Centralizar.centralizarTabela(tb_dadosClima);
        tb_dadosClima.getColumnModel().getColumn(0).setPreferredWidth(0);// ID Insumo
        tb_dadosClima.getColumnModel().getColumn(0).setMinWidth(0);
        tb_dadosClima.getColumnModel().getColumn(0).setMaxWidth(0);
        tb_dadosClima.getColumnModel().getColumn(1).setPreferredWidth(0);// ID Insumo
        tb_dadosClima.getColumnModel().getColumn(1).setMinWidth(0);
        tb_dadosClima.getColumnModel().getColumn(1).setMaxWidth(0);
        return tb_dadosClima;
    }

    private TableModelTalhoesAplic getTableModelTalhao() {
        if (TbTalhaoOP == null) {
            TbTalhaoOP = new TableModelTalhoesAplic();
        }
        return TbTalhaoOP;
    }

    private TableModelDadosClima getTableModelClima() {
        if (TbClima == null) {
            TbClima = new TableModelDadosClima();
        }
        return TbClima;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        txt_data =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        cb_safra = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        cb_cultivo = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        cb_cultura = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        cb_operacao = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cb_aplicacao = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_dadosClima = new javax.swing.JTable();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        txt_dataClima =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        txt_horaClima = new javax.swing.JFormattedTextField();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        txt_vento = new javax.swing.JTextField();
        javax.swing.JLabel jLabel14 = new javax.swing.JLabel();
        txt_umidade = new javax.swing.JTextField();
        javax.swing.JLabel jLabel23 = new javax.swing.JLabel();
        txt_temp = new javax.swing.JTextField();
        javax.swing.JLabel jLabel24 = new javax.swing.JLabel();
        txt_chuva = new javax.swing.JTextField();
        btn_addClima = new javax.swing.JButton();
        btn_delClima = new javax.swing.JButton();
        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txt_idOperador = new javax.swing.JTextField();
        javax.swing.JLabel jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        cb_fazenda1 = new javax.swing.JComboBox<>();
        cb_funcionario = new componentes.UJComboBox();
        jPanel6 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        txt_nFrotaTrator = new javax.swing.JTextField();
        txt_modeloTrator = new javax.swing.JTextField();
        javax.swing.JLabel jLabel15 = new javax.swing.JLabel();
        txt_hmInicial = new javax.swing.JTextField();
        javax.swing.JLabel jLabel16 = new javax.swing.JLabel();
        txt_hmFinal = new javax.swing.JTextField();
        txt_hmTrabalhada = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_nFrotaImplemento = new javax.swing.JTextField();
        txt_modeloImplemento = new javax.swing.JTextField();
        javax.swing.JLabel jLabel17 = new javax.swing.JLabel();
        txt_largura = new javax.swing.JTextField();
        javax.swing.JLabel jLabel22 = new javax.swing.JLabel();
        txt_velocidade = new javax.swing.JTextField();
        javax.swing.JPanel jPanel3 = new javax.swing.JPanel();
        cb_fazenda = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        txt_talhao = new javax.swing.JTextField();
        btn_addTalhao = new javax.swing.JButton();
        btn_excluirTalhao = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_talhoes = new javax.swing.JTable();
        javax.swing.JLabel jLabel25 = new javax.swing.JLabel();
        txt_areaTrabalhada = new javax.swing.JTextField();
        btn_novo = new javax.swing.JButton();
        btn_salvarPedido = new javax.swing.JButton();
        btn_editarPedido = new javax.swing.JButton();
        btn_excluirPedido = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Operações Mecânizadas");

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Data");

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

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Operação");

        cb_operacao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_operacaoItemStateChanged(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Aplicação");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_operacao, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_aplicacao, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(152, 152, 152)
                        .addComponent(jLabel18)
                        .addGap(13, 27, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_data, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_safra, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_cultivo, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_cultura, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addComponent(cb_cultura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(cb_operacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(cb_aplicacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel18)))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Fatores Climáticos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tb_dadosClima.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tb_dadosClima.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_dadosClimaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_dadosClima);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Data");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Hora");

        txt_horaClima.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Vento");

        txt_vento.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("U%");

        txt_umidade.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("Temp");

        txt_temp.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Chuva");

        txt_chuva.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btn_addClima.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_addClima.setText("+");
        btn_addClima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addClimaActionPerformed(evt);
            }
        });

        btn_delClima.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_delClima.setText("-");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_dataClima, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_horaClima)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_vento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_umidade)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_temp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_chuva)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_addClima)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_delClima)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel9)
                    .addComponent(txt_dataClima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txt_horaClima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txt_vento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txt_umidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(txt_temp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(txt_chuva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_addClima)
                    .addComponent(btn_delClima))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Operação", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Colaborador", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("ID Operador");

        txt_idOperador.setEditable(false);
        txt_idOperador.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_idOperador.setPreferredSize(new java.awt.Dimension(70, 20));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Operador");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Fazenda");

        cb_fazenda1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_fazenda1ItemStateChanged(evt);
            }
        });

        cb_funcionario.setMaximumRowCount(20);
        cb_funcionario.setAutocompletar(true);
        cb_funcionario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_funcionarioItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_fazenda1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_funcionario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_idOperador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel21)
                    .addComponent(cb_fazenda1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(txt_idOperador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20)
                    .addComponent(cb_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Trator", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Trator");

        txt_nFrotaTrator.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nFrotaTrator.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_nFrotaTratorFocusLost(evt);
            }
        });

        txt_modeloTrator.setEditable(false);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("H.M.I");

        txt_hmInicial.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_hmInicial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_hmInicialKeyReleased(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("H.M.F");

        txt_hmFinal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_hmFinal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_hmFinalKeyReleased(evt);
            }
        });

        txt_hmTrabalhada.setEditable(false);
        txt_hmTrabalhada.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Impl");

        txt_nFrotaImplemento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nFrotaImplemento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_nFrotaImplementoFocusLost(evt);
            }
        });

        txt_modeloImplemento.setEditable(false);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Largura T");

        txt_largura.setEditable(false);
        txt_largura.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Velocidade. T");

        txt_velocidade.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nFrotaTrator, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_modeloTrator, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nFrotaImplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_modeloImplemento)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_largura, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel22))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_hmFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_hmInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_hmTrabalhada, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                    .addComponent(txt_velocidade))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_nFrotaTrator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_modeloTrator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txt_hmInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txt_hmFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_hmTrabalhada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(txt_largura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22)
                        .addComponent(txt_velocidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(txt_nFrotaImplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_modeloImplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Talhões Aplicados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Fazenda");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Nº Talhão");

        txt_talhao.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btn_addTalhao.setText("Add Talhão");
        btn_addTalhao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addTalhaoActionPerformed(evt);
            }
        });

        btn_excluirTalhao.setText("Excluir");

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

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("Area Trab");

        txt_areaTrabalhada.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_talhao, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_areaTrabalhada, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_addTalhao, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_excluirTalhao, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(txt_areaTrabalhada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(txt_talhao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_addTalhao)
                        .addComponent(btn_excluirTalhao)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
        );

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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_salvarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_editarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_excluirPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_salvarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_editarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_excluirPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jScrollPane3.setViewportView(jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb_culturaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_culturaItemStateChanged
        carregarAplicacoes(getIdCultura(cb_cultura), cb_aplicacao);
        carregarOperacoes(getIdCultura(cb_cultura), cb_operacao);
    }//GEN-LAST:event_cb_culturaItemStateChanged

    private void cb_operacaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_operacaoItemStateChanged
        getUsoFatoresClimaticos(cb_operacao);
    }//GEN-LAST:event_cb_operacaoItemStateChanged

    private void tb_talhoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_talhoesMouseClicked

    }//GEN-LAST:event_tb_talhoesMouseClicked

    private void tb_talhoesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tb_talhoesPropertyChange
        int coluna = tb_talhoes.getSelectedColumn();
        int linha = tb_talhoes.getSelectedRow();

        if (coluna == 5) {
            if ((Double) TbTalhaoOP.getValueAt(linha, 5) <= 100) {
                double areaAplic = (Double) TbTalhaoOP.getValueAt(linha, 5) * (Double) TbTalhaoOP.getValueAt(linha, 6) / 100;
                TbTalhaoOP.setValueAt(Double.valueOf(new DecimalFormat("#,##0.00").format(areaAplic).replace(".", "").replace(",", ".")), linha, 11);
                rateioHorasTalhao();
            } else {
                JOptionPane.showMessageDialog(null, "A Porcentagem da área aplicada deve ser entre 0 e 100%!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            }

        } else if (coluna == 11) {
            Double porcentagemArea = (Double) TbTalhaoOP.getValueAt(linha, 11) / (Double) TbTalhaoOP.getValueAt(linha, 6) * 100;
            if (porcentagemArea <= 100.00) {
                TbTalhaoOP.setValueAt(Double.valueOf(new DecimalFormat("#,##0.00").format(porcentagemArea).replace(".", "").replace(",", ".")), linha, 5);
                rateioHorasTalhao();
            } else {
                JOptionPane.showMessageDialog(null, "Área digitada incompatível com a área do talhão!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            }
        }
    }//GEN-LAST:event_tb_talhoesPropertyChange

    private void btn_addTalhaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addTalhaoActionPerformed
        TbTalhaoAplicBeans b = new TbTalhaoAplicBeans();
        double area = getAreaTalhao();
        if (area > 0) {
            b.setIdFazenda(getIdFazenda(cb_fazenda));
            b.setAreaTalhao(getAreaTalhao());
            b.setIdTalha(getIDTalhao(txt_talhao.getText(), getIdFazenda(cb_fazenda)));
            b.setPorcentAplic(100.00);
            b.setnTalhao(txt_talhao.getText());
            b.setAreaPlanejada(0.0);
            b.setAreaAplic(Corretores.ConverterStringDouble(txt_areaTrabalhada.getText()));
            b.setAreaAplicTotal(0.0);
            b.setIdDB(0);
            TbTalhaoOP.addBeans(b);
            rateioHorasTalhao();
            txt_talhao.setRequestFocusEnabled(true);
            txt_talhao.requestFocus();
            txt_talhao.selectAll();

        } else {
            JOptionPane.showMessageDialog(null, "Talhão Não Localizado", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }
    }//GEN-LAST:event_btn_addTalhaoActionPerformed

    private void txt_nFrotaTratorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_nFrotaTratorFocusLost
        if (!txt_nFrotaTrator.getText().equals("")) {
            InventarioBeans i;
            i = getModeloNFrota(txt_nFrotaTrator.getText());
            if (i != null) {
                String modelo = i.getMarca() + " - " + i.getModelo();
                txt_modeloTrator.setText(modelo);
                Double HoraM = OperacoesD.buscarUltimaHoraTrator(getIdInventario(txt_nFrotaTrator.getText()));
                txt_hmInicial.setText(new DecimalFormat("###,##0.0").format(HoraM));
            }
        } else {
            txt_nFrotaTrator.setText("");
            txt_modeloTrator.setText("");
        }
    }//GEN-LAST:event_txt_nFrotaTratorFocusLost

    private void txt_nFrotaImplementoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_nFrotaImplementoFocusLost
        if (!txt_nFrotaImplemento.getText().equals("")) {
            InventarioBeans i;
            i = getModeloNFrota(txt_nFrotaImplemento.getText());
            if (i != null) {
                txt_modeloImplemento.setText(i.getMarca() + " - " + i.getModelo());
                txt_largura.setText(i.getLarguraTrabalho().toString().replace(".", ","));
            }
        } else {
            txt_nFrotaImplemento.setText("");
            txt_modeloImplemento.setText("");
        }


    }//GEN-LAST:event_txt_nFrotaImplementoFocusLost

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed
        btn_salvarPedido.setEnabled(true);
        btn_editarPedido.setEnabled(false);

        habilitarCampos();
        limparCampos();
    }//GEN-LAST:event_btn_novoActionPerformed

    private void btn_salvarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarPedidoActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(null, "Deseja Salvar esta Ordem de Serviço?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION) {
            popularBeans();
            if (verificarBeans(OperacoesB)) {
                if (OperacoesD.inserirOperacao(OperacoesB, TbTalhaoOP, TbClima)) {
                    OperacoesD.atualizarTalhoes(IdOS, TbTalhaoOP);
                    if (frmAplicacoes != null) {
                        frmAplicacoes.OrdemD.atualizarTabelaTalhoes(IdOS, frmAplicacoes.TbTalhao);
                    }
                    limparCampos();
                    desabilitarCampos();
                }
            }
        }
    }//GEN-LAST:event_btn_salvarPedidoActionPerformed

    private void btn_editarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarPedidoActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja editar esta entrada?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION) {

        }
    }//GEN-LAST:event_btn_editarPedidoActionPerformed

    private void btn_excluirPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirPedidoActionPerformed
        int excluir = JOptionPane.showConfirmDialog(null, "Deseja excluir este movimento?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (excluir == JOptionPane.YES_OPTION) {

        }
    }//GEN-LAST:event_btn_excluirPedidoActionPerformed

    private void tb_dadosClimaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_dadosClimaMouseClicked
        int coluna = tb_dadosClima.getSelectedColumn();
        if (coluna == 0) {
            if (DialogData == null) {
                DialogData = new frmDialogData(null, true);
            }
            DialogData.setLocation(evt.getX() + 50, evt.getY() + 100);
            DialogData.setVisible(true);
            TbClima.setValueAt(DialogData.getDate(), tb_dadosClima.getSelectedRow(), tb_dadosClima.getSelectedColumn());
            DialogData.dispose();
        }
    }//GEN-LAST:event_tb_dadosClimaMouseClicked

    private void txt_hmInicialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_hmInicialKeyReleased
        setHmTrabalhada();
    }//GEN-LAST:event_txt_hmInicialKeyReleased

    private void txt_hmFinalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_hmFinalKeyReleased
        setHmTrabalhada();
    }//GEN-LAST:event_txt_hmFinalKeyReleased

    private void cb_fazenda1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_fazenda1ItemStateChanged
        int idFazenda = getIdFazenda(cb_fazenda1);
        if (idFazenda > 0) {
            carregarListOperador(idFazenda);
        }
    }//GEN-LAST:event_cb_fazenda1ItemStateChanged

    private void cb_funcionarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_funcionarioItemStateChanged
        try {
            ListColaborador c = new ListColaborador();
            c = (ListColaborador) cb_funcionario.getSelectedObject();
            txt_idOperador.setText(c.getCodigo().toString());
            IdOperador = c.getID();
        } catch (Exception e) {
        }

    }//GEN-LAST:event_cb_funcionarioItemStateChanged

    private void btn_addClimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addClimaActionPerformed
        try {
            TbDadosClimaticosBeans d = new TbDadosClimaticosBeans();
            d.setID(0);
            d.setID_OS(IdOS);
            d.setID_OP(0);
            d.setData(new SimpleDateFormat("dd/MM/yyyy").format(txt_dataClima.getDate()));
            d.setHora(new Time(new SimpleDateFormat("HH:mm").parse(txt_horaClima.getText()).getTime()));
            d.setVento(Corretores.ConverterStringDouble(txt_vento.getText()));
            d.setUmidade(Corretores.ConverterStringDouble(txt_umidade.getText()));
            d.setTemperatura(Corretores.ConverterStringDouble(txt_temp.getText()));
            d.setChuva(Corretores.ConverterStringDouble(txt_chuva.getText()));
            TbClima.addBeans(d);

            txt_dataClima.setDate(null);
            txt_horaClima.setText("");
            txt_vento.setText("");
            txt_umidade.setText("");
            txt_temp.setText("");
            txt_chuva.setText("");
        } catch (ParseException e) {
        }
    }//GEN-LAST:event_btn_addClimaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmOperacoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmOperacoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmOperacoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmOperacoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmOperacoes dialog = new frmOperacoes(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_addClima;
    public javax.swing.JButton btn_addTalhao;
    private javax.swing.JButton btn_delClima;
    javax.swing.JButton btn_editarPedido;
    javax.swing.JButton btn_excluirPedido;
    public javax.swing.JButton btn_excluirTalhao;
    public javax.swing.JButton btn_novo;
    public javax.swing.JButton btn_salvarPedido;
    public javax.swing.JComboBox<ListAplicacaoBeans> cb_aplicacao;
    public javax.swing.JComboBox<CultivoBeans> cb_cultivo;
    public javax.swing.JComboBox<CulturaBeans> cb_cultura;
    private javax.swing.JComboBox<ListFazendasBeans> cb_fazenda;
    private javax.swing.JComboBox<ListFazendasBeans> cb_fazenda1;
    private componentes.UJComboBox cb_funcionario;
    public javax.swing.JComboBox<ListOperacoesBeans> cb_operacao;
    public javax.swing.JComboBox<AnoSafra> cb_safra;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tb_dadosClima;
    public javax.swing.JTable tb_talhoes;
    private javax.swing.JTextField txt_areaTrabalhada;
    private javax.swing.JTextField txt_chuva;
    public com.toedter.calendar.JDateChooser txt_data;
    public com.toedter.calendar.JDateChooser txt_dataClima;
    private javax.swing.JTextField txt_hmFinal;
    private javax.swing.JTextField txt_hmInicial;
    private javax.swing.JTextField txt_hmTrabalhada;
    private javax.swing.JFormattedTextField txt_horaClima;
    private javax.swing.JTextField txt_idOperador;
    private javax.swing.JTextField txt_largura;
    private javax.swing.JTextField txt_modeloImplemento;
    private javax.swing.JTextField txt_modeloTrator;
    private javax.swing.JTextField txt_nFrotaImplemento;
    private javax.swing.JTextField txt_nFrotaTrator;
    private javax.swing.JTextField txt_talhao;
    private javax.swing.JTextField txt_temp;
    private javax.swing.JTextField txt_umidade;
    private javax.swing.JTextField txt_velocidade;
    private javax.swing.JTextField txt_vento;
    // End of variables declaration//GEN-END:variables

    private void setHmTrabalhada() {
        try {
            double HmInicial = Corretores.ConverQuilosDouble(txt_hmInicial.getText());
            double HmFinal = Corretores.ConverQuilosDouble(txt_hmFinal.getText());
            double HmTrabalhada = HmFinal - HmInicial;
            txt_hmTrabalhada.setText(new DecimalFormat("###,##0.00").format(HmTrabalhada));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Digitado! ", "Erro", 0, FormatarICO.ICObtnSair());
        }

    }

    private Integer getIdCultura(JComboBox comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return listCultura.get(comboBox.getSelectedIndex() - 1).getIDCultura();
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

    private Integer getIdFazenda(JComboBox<ListFazendasBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return comboBox.getItemAt(comboBox.getSelectedIndex()).getID();
        }
        return 0;
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

    private Integer getIdOperacao(JComboBox<ListOperacoesBeans> comboBox) {
        int id = 0;
        if (comboBox.getSelectedIndex() > 0) {
            id = comboBox.getItemAt(comboBox.getSelectedIndex()).getID();
        }
        return id;
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

    private String getNFrota(Integer IdInventario) {
        String s = "";
        for (InventarioBeans list : listInventario) {
            if (list.getID().equals(IdInventario)) {
                return list.getnFrota();
            }
        }
        return s;
    }

    private String getModeloInventario(Integer IdInventario) {
        String s = "";
        for (InventarioBeans list : listInventario) {
            if (list.getID().equals(IdInventario)) {
                return list.getMarca() + " - " + list.getModelo();
            }
        }
        return s;
    }

    private void setJComboBoxAnoSafra(JComboBox<AnoSafra> cb, Integer IdSafra) {
        for (int i = 0; i < cb.getModel().getSize(); i++) {
            if (cb.getModel().getElementAt(i).getIdSafra() == IdSafra) {
                cb.setSelectedIndex(i);
            }
        }
    }

    private void setJComboBoxFazenda(JComboBox<ListFazendasBeans> cb, Integer IdFazenda) {
        for (int i = 0; i < cb.getModel().getSize(); i++) {
            if (cb.getModel().getElementAt(i).getID() == IdFazenda) {
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

    private void setJComboBoxLocalEstoque(JComboBox<ListFazendasBeans> cb, Integer IdPonta) {
        for (int i = 0; i < cb.getModel().getSize(); i++) {
            if (cb.getModel().getElementAt(i).getID() == IdPonta) {
                cb.setSelectedIndex(i);
            }
        }
    }

    private void setJComboBoxFuncionario(UJComboBox Combo, Integer id) {
        for (int i = 0; i < Combo.getModel().getSize(); i++) {
            ListColaborador b = (ListColaborador) Combo.getModel().getElementAt(i);
            if (Objects.equals(b.getID(), id)) {
                Combo.setSelectedItem(b);
                break;
            }
        }
    }

    private Integer getIdAplicacao(JComboBox comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return listAplicacao.get(comboBox.getSelectedIndex() - 1).getID();
        }
        return 0;
    }

    private InventarioBeans getModeloNFrota(String nFrota) {
        InventarioBeans s = null;
        if (!nFrota.equals("")) {
            for (int i = 0; i < listInventario.size(); i++) {
                if (listInventario.get(i).getnFrota().equals(nFrota)) {
                    return listInventario.get(i);
                }
            }
            //JOptionPane.showMessageDialog(null, "Nº de Frota Não Encontrado! ", "Erro", 0, FormatarICO.ICObtnSair());
        }
        return s;
    }

    private Integer getIdInventario(String nFrota) {
        if (!nFrota.equals("")) {
            for (int i = 0; i < listInventario.size(); i++) {
                if (listInventario.get(i).getnFrota().equals(nFrota)) {
                    return listInventario.get(i).getID();
                }
            }
            JOptionPane.showMessageDialog(null, "Nº de Frota Não Encontrado! ", "Erro", 0, FormatarICO.ICObtnSair());
        }
        return 0;
    }

    private void popularBeans() {
        OperacoesB.setDataOperacao(new SimpleDateFormat("dd/MM/yyyy").format(txt_data.getDate()));
        OperacoesB.setIdOS(IdOS);
        OperacoesB.setIdSafra(getIdSafra(cb_safra));
        OperacoesB.setIdCultivo(getIdCultivo(cb_cultivo));
        OperacoesB.setIdCultura(getIdCultura(cb_cultura));
        OperacoesB.setIdOperacao(getIdOperacao(cb_operacao));
        OperacoesB.setIdAplicacao(getIdAplicacao(cb_cultivo));
        OperacoesB.setIdTrator(getIdInventario(txt_nFrotaTrator.getText()));
        OperacoesB.setIdImplemento(getIdInventario(txt_nFrotaImplemento.getText()));
        OperacoesB.setIdCei(cb_fazenda1.getModel().getElementAt(cb_fazenda1.getSelectedIndex()).getID());
        OperacoesB.setCodigoOperador(getIdOperador());
        OperacoesB.setIdOperador(IdOperador);
        OperacoesB.setLarguraT(Corretores.ConverterStringDouble(txt_largura.getText()));
        OperacoesB.setVelocidadeT(Corretores.ConverterStringDouble(txt_velocidade.getText()));
        OperacoesB.setHmInicial(Corretores.ConverQuilosDouble(txt_hmInicial.getText()));
        OperacoesB.setHmFinal(Corretores.ConverQuilosDouble(txt_hmFinal.getText()));
        OperacoesB.setHmTrablhada(Corretores.ConverQuilosDouble(txt_hmTrabalhada.getText()));

    }

    public void preencherCampos(OperacoesBeans b) {
        txt_data.setDate(Corretores.ConverterStringDateDDMMAAAA(b.getDataOperacao()));
        IdOS = b.getIdOS();
        setJComboBoxAnoSafra(cb_safra, b.getIdSafra());
        setJComboBoxCultivo(cb_cultivo, b.getIdCultivo());
        setJComboBoxCultura(cb_cultura, b.getIdCultura());
        setJComboBoxOperacoes(cb_operacao, b.getIdOperacao());
        setJComboBoxAplicacoes(cb_aplicacao, b.getIdAplicacao());
        setJComboBoxFazenda(cb_fazenda1, b.getIdCei());
        setJComboBoxFuncionario(cb_funcionario, b.getIdOperador());
        txt_nFrotaTrator.setText(getNFrota(b.getIdTrator()));
        txt_nFrotaImplemento.setText(getNFrota(b.getIdImplemento()));
        txt_modeloTrator.setText(getModeloInventario(b.getIdTrator()));
        txt_modeloImplemento.setText(getModeloInventario(b.getIdImplemento()));
        txt_largura.setText(b.getLarguraT().toString().replace(".", ","));
        txt_velocidade.setText(b.getVelocidadeT().toString().replace(".", ","));
        txt_hmInicial.setText(b.getHmInicial().toString().replace(".", ","));
        txt_hmFinal.setText(b.getHmFinal().toString().replace(".", ","));
        txt_hmTrabalhada.setText(b.getHmTrablhada().toString().replace(".", ","));
        TbTalhaoOP.addLista(b.getListTalhoesAplic());
        TbClima.addLista(b.getListDadosClima());
        // arrumar area planejada buscar da tabela OS_Talhões
    }

    private Integer getIdOperador() {
        if (!txt_idOperador.getText().equals("")) {
            return Integer.parseInt(txt_idOperador.getText());
        }
        return 0;
    }

    private boolean verificarBeans(OperacoesBeans b) {
        if (VerificarHoraTrabalhada() == false) {
            return false;
        }
        try {
            if (!Corretores.verificaDataMensal(txt_data.getDate())) {
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Data!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }

        if (b.getIdSafra() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Safra!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getIdCultivo() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Cultivo!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getIdCultura() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Cultura!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getIdOperacao() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Operação!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (getUsoInsumos(cb_operacao) && b.getIdAplicacao() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Aplicacão!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (IdOperador == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Operador!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }

        return true;
    }

    private Boolean VerificarHoraTrabalhada() {
        Double HoraM = 0.0;
        Double AreaAplicadaTotal;
        Double AreaLancada;
        try {
            for (int i = 0; i < TbTalhaoOP.getRowCount(); i++) {
                if (((Double) TbTalhaoOP.getValueAt(i, 9)) != null) {
                    HoraM += ((Double) TbTalhaoOP.getValueAt(i, 9));
                }
                AreaAplicadaTotal = (Double) TbTalhaoOP.getValueAt(i, 10);
                AreaLancada = (Double) TbTalhaoOP.getValueAt(i, 11);
                if ((AreaAplicadaTotal + AreaLancada) > (Double) TbTalhaoOP.getValueAt(i, 6)) {
                    JOptionPane.showMessageDialog(null, "A Área Trabalhada está maior do que a area do talhão!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
                    return false;
                }
            }
            double difHorimetro = HoraM - Double.parseDouble(txt_hmTrabalhada.getText().replace(",", "."));
            if (difHorimetro > 0.01 || difHorimetro < -0.01) {
                JOptionPane.showMessageDialog(null, "A Hora Máquina aplicada aos talhões está diferente do Horimetro!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Verifique os campos da Tabela!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }

        return true;
    }

    private boolean getUsoInsumos(JComboBox<ListOperacoesBeans> cbOperacao) {
        boolean b = false;
        try {
            b = cbOperacao.getItemAt(cbOperacao.getSelectedIndex()).isUsoInsumos();
            cb_aplicacao.setEnabled(b);
        } catch (Exception e) {
        }
        return b;
    }

    private void limparCampos() {

        if (IdOS > 0) {
            txt_nFrotaTrator.setText("");
            txt_nFrotaImplemento.setText("");
            txt_modeloTrator.setText("-");
            txt_modeloImplemento.setText("-");
            txt_hmFinal.setText("");
            txt_hmInicial.setText("");
            txt_hmTrabalhada.setText("0.0");
            txt_idOperador.setText("");
            cb_funcionario.setSelectedIndex(-1);
            // txt_operador.setText("-");
            TbClima.limpar();
        } else {
            txt_nFrotaTrator.setText("");
            txt_nFrotaImplemento.setText("");
            txt_modeloTrator.setText("-");
            txt_modeloImplemento.setText("-");
            txt_hmFinal.setText("");
            txt_hmInicial.setText("");
            txt_hmTrabalhada.setText("0.0");
            txt_idOperador.setText("");
            cb_funcionario.setSelectedIndex(-1);
            TbClima.limpar();
            TbTalhaoOP.limpar();
            cb_safra.setSelectedIndex(0);
            cb_cultivo.setSelectedIndex(0);
            cb_cultura.setSelectedIndex(0);
            cb_operacao.setSelectedIndex(0);
            cb_aplicacao.setSelectedIndex(0);
        }

    }

    public void desabilitarCampos() {
        if (IdOS > 0) {
            txt_idOperador.setEnabled(false);
            cb_funcionario.setEnabled(false);
            cb_fazenda1.setEnabled(false);
            txt_nFrotaTrator.setEnabled(false);
            txt_nFrotaImplemento.setEnabled(false);
            txt_modeloTrator.setEnabled(false);
            txt_modeloImplemento.setEnabled(false);
            txt_hmInicial.setEnabled(false);
            txt_hmFinal.setEnabled(false);
        } else {
            txt_data.setEnabled(false);
            cb_safra.setEnabled(false);
            cb_cultivo.setEnabled(false);
            cb_cultura.setEnabled(false);
            cb_operacao.setEnabled(false);
            cb_aplicacao.setEnabled(false);
            cb_funcionario.setEnabled(false);
            txt_idOperador.setEnabled(false);
            cb_fazenda1.setEnabled(false);
            txt_nFrotaTrator.setEnabled(false);
            txt_nFrotaImplemento.setEnabled(false);
            txt_modeloTrator.setEnabled(false);
            txt_modeloImplemento.setEnabled(false);
            txt_hmInicial.setEnabled(false);
            txt_hmFinal.setEnabled(false);
            cb_fazenda.setEnabled(false);
            txt_talhao.setEnabled(false);
            btn_addTalhao.setEnabled(false);
            btn_excluirTalhao.setEnabled(false);
        }
    }

    public void habilitarCampos() {
        if (IdOS > 0) {
            txt_idOperador.setEnabled(true);
            cb_funcionario.setEnabled(true);
            cb_fazenda1.setEnabled(true);
            txt_nFrotaTrator.setEnabled(true);
            txt_nFrotaImplemento.setEnabled(true);
            txt_modeloTrator.setEnabled(true);
            txt_modeloImplemento.setEnabled(true);
            txt_hmInicial.setEnabled(true);
            txt_hmFinal.setEnabled(true);
        } else {
            txt_data.setEnabled(true);
            cb_safra.setEnabled(true);
            cb_cultivo.setEnabled(true);
            cb_cultura.setEnabled(true);
            cb_operacao.setEnabled(true);
            cb_aplicacao.setEnabled(true);
            txt_idOperador.setEnabled(true);
            cb_funcionario.setEnabled(true);
            cb_fazenda1.setEnabled(true);
            txt_nFrotaTrator.setEnabled(true);
            txt_nFrotaImplemento.setEnabled(true);
            txt_modeloTrator.setEnabled(true);
            txt_modeloImplemento.setEnabled(true);
            txt_hmInicial.setEnabled(true);
            txt_hmFinal.setEnabled(true);
            cb_fazenda.setEnabled(true);
            txt_talhao.setEnabled(true);
            btn_addTalhao.setEnabled(true);
            btn_excluirTalhao.setEnabled(true);
        }

    }

    private void getUsoFatoresClimaticos(JComboBox<ListOperacoesBeans> cbOperacao) {
        try {
            boolean b = cbOperacao.getItemAt(cbOperacao.getSelectedIndex()).isUsoFClimaticos();
            cb_aplicacao.setEnabled(b);
            int h = jPanel4.getSize().height;
            if (b) {
                this.setSize(new Dimension(this.getPreferredSize().width, this.getPreferredSize().height + h));
            } else {
                this.setSize(new Dimension(this.getPreferredSize().width, this.getPreferredSize().height - h));
            }
            jPanel4.setVisible(b);
        } catch (Exception e) {
        }
    }

    private void rateioHorasTalhao() {
        double horaTotal = Corretores.ConverterStringDouble(txt_hmTrabalhada.getText());
        double areaTotal = 0.00;
        double rendOP = 0.00;     // rendimento ha/hora máquina
        for (int i = 0; i < TbTalhaoOP.getRowCount(); i++) {
            areaTotal += (Double) TbTalhaoOP.getValueAt(i, 11);
        }
        if (areaTotal > 0.00) {
            rendOP = horaTotal / areaTotal;
            if (rendOP > 0.00) {
                for (int i = 0; i < TbTalhaoOP.getRowCount(); i++) {
                    double areaTalhao = (Double) TbTalhaoOP.getValueAt(i, 11);
                    TbTalhaoOP.setValueAt(rendOP * areaTalhao, i, 9);
                }
            }
        }
    }

    class CellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setHorizontalAlignment(SwingConstants.CENTER);
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            Object val = table.getValueAt(row, column);
            if (val instanceof Double) {
                if (column == 9) {
                    Double valorD = (Double) val;
                    setText(new DecimalFormat("#,###,##0.00").format(valorD));

                } else {
                    Double valorD = (Double) val;
                    setText(new DecimalFormat("#,###,##0.000").format(valorD));
                }
            }
            return this;
        }
    }

}
