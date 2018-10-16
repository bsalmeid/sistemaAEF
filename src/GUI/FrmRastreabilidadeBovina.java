/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Beans.CompraGadoBeans;
import Beans.CategoriaAnimalBeans;
import Beans.CompradorGadoBeans;
import Beans.ListFazendasBeans;
import Beans.ListRacaAnimalBeans;
import Beans.ListStatusAnimalBeans;
import ComboBoxModel.CbModelStatusAnimal;
import DAO.CompraGadoDAO;
import DAO.Diversas;
import DAO.RastreabilidadeDAO;
import static GUI.Principal.ListFazPermitidas;
import static GUI.Principal.listCategoriaAnimal;
import static GUI.Principal.listCompradores;
import static GUI.Principal.listRacaAnimal;
import static GUI.Principal.listStatusAnimal;
import Icones.FormatarICO;
import TableModel.TableModelRastreabilidade;
import TableModel.TbRastreabilidadeBeans;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import org.jdesktop.swingx.JXTaskPane;

/**
 *
 * @author agroa
 */
public class FrmRastreabilidadeBovina extends javax.swing.JInternalFrame {

    Diversas DiversasD;
    TableModelRastreabilidade TbGado;
    CompraGadoBeans CompraB;
    CentralizarTabela Centralizar;
    RastreabilidadeDAO RastD;
    CellRenderer cellRenderer;
    JDialog jd_selecionarPorta;
    JComboBox<String> cb_portaCOM;
    protected String idBrinco;
    protected Integer nBrinco;
    String Biotipo;
    ListRacaAnimalBeans Raca;
    String Classificacao;

    public FrmRastreabilidadeBovina() {

        UIManager.getLookAndFeelDefaults().put(JXTaskPane.uiClassID,
                "org.jdesktop.swingx.plaf.misc.GlossyTaskPaneUI");
        initComponents();
        jp_dadosCompra.setVisible(false);
        DiversasD = new Diversas();
        Centralizar = new CentralizarTabela();
        RastD = new RastreabilidadeDAO();
        CompraB = new CompraGadoBeans();
        cellRenderer = new CellRenderer();
        carregarRacaAnimal();
        carregarTabela();
        ch_macho.setSelected(true);
        carregarFazPermitidas();
        carregarCompradores();
        carregarStatusAnimal();
        

    }

    public String getClassificacao() {
        return Classificacao;
    }

    public void setClassificacao(String[] codigoBrinco) {
        this.Classificacao = "";
        for (int i = 1; i < codigoBrinco.length; i++) {
            this.Classificacao += (codigoBrinco[i] + ".");
        }
    }

    public String getBiotipo() {
        return Biotipo;
    }

    public void setBiotipo(String[] codigoBrinco) {
        // metodo para definir o biotipo do animal atraves do codigo do brinco
        if (codigoBrinco.length > 1) {
            try {
                String letraBiotipo = codigoBrinco[1].replaceAll("[^a-z]", "").toLowerCase();
                switch (letraBiotipo) {
                    case "c":
                        this.Biotipo = "Cruzado";
                        break;
                    case "z":
                        this.Biotipo = "Zebu";
                        break;
                    case "m":
                        this.Biotipo = "Misto";
                        break;
                    default:
                        throw new IndexOutOfBoundsException("columnIndex out of bounds");
                }
            } catch (Exception e) {
                System.out.println("Erro Switch biotipo");
                this.Biotipo = "-";
            }
        } else {
            this.Biotipo = "-";
        }
    }

    public ListRacaAnimalBeans getRacaAnimalBeans() {
        return Raca;
    }

    public void setIdRaca(String[] codigoBrinco) {
        ListRacaAnimalBeans l = new ListRacaAnimalBeans();
        l.setID(0);
        l.setDescricao("-");
        this.Raca = l;
        if (codigoBrinco.length > 2) {
            for (int i = 0; i < listRacaAnimal.size(); i++) {
                if (codigoBrinco[2].equals(listRacaAnimal.get(i).getAbreviacao())) {
                    this.Raca = listRacaAnimal.get(i);
                    break;
                }
            }
        }
    }

    public String getIdBrinco() {
        return idBrinco;
    }

    public void setIdBrinco(String idBrinco) {
        this.idBrinco = idBrinco;
    }

    public Integer getnBrinco() {
        return nBrinco;
    }

    public void setnBrinco(Integer nBrinco) {
        this.nBrinco = nBrinco;
    }

    private void carregarCategoriaAnimal(JComboBox<CategoriaAnimalBeans> comboBox, String sexo) {
        if (listCategoriaAnimal == null) {
            listCategoriaAnimal = DiversasD.ListCategoriaAnimal();
        }
        CategoriaAnimalBeans l = new CategoriaAnimalBeans();
        l.setID(0);
        l.setDescricao("-");
        comboBox.removeAllItems();
        comboBox.addItem(l);
        for (CategoriaAnimalBeans list : listCategoriaAnimal) {
            if (sexo.equals(list.getSexo())) {
                comboBox.addItem(list);
            }
        }

    }

    private void carregarRacaAnimal() {

        if (listRacaAnimal == null) {
            listRacaAnimal = DiversasD.ListRacaAnimal();
        }
        ListRacaAnimalBeans l = new ListRacaAnimalBeans();
        l.setID(0);
        l.setDescricao("-");
        cb_raca.addItem(l);
        for (ListRacaAnimalBeans list : listRacaAnimal) {
            cb_raca.addItem(list);
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

    private void carregarCompradores() {
        if (listCompradores == null) {
            listCompradores = DiversasD.listCompradorGado();
        }
        CompradorGadoBeans b = new CompradorGadoBeans();
        b.setIdComprador(0);
        b.setComprador("-");
        cb_comprador.addItem(b);
        cb_comprador1.addItem(b);
        for (CompradorGadoBeans list : listCompradores) {
            cb_comprador.addItem(list);
            cb_comprador1.addItem(list);
        }
    }

    private void carregarStatusAnimal() {
        /*   if (listStatusAnimal == null) {
            listStatusAnimal = DiversasD.ListStatusAnimal();
        }
        for (ListStatusAnimalBeans list : listStatusAnimal) {
            cb_statusAnimal.addItem(list);
        }*/
        CbModelStatusAnimal statusAnimal = new CbModelStatusAnimal();
        cb_statusAnimal.setModel(statusAnimal);
    }

    private Integer getComprador(JComboBox<CompradorGadoBeans> comboBox) {
        int i = 0;
        i = comboBox.getItemAt(comboBox.getSelectedIndex()).getIdComprador();
        return i;
    }

    private JTable carregarTabela() {
        tb_gado.setModel(getTableModel());
        ((DefaultTableCellRenderer) tb_gado.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        Centralizar.centralizarTabela(tb_gado);

        tb_gado.getColumnModel().getColumn(6).setMaxWidth(0);
        tb_gado.getColumnModel().getColumn(6).setMinWidth(0);
        tb_gado.getColumnModel().getColumn(6).setPreferredWidth(0);

        tb_gado.getColumnModel().getColumn(9).setMaxWidth(0);
        tb_gado.getColumnModel().getColumn(9).setMinWidth(0);
        tb_gado.getColumnModel().getColumn(9).setPreferredWidth(0);

        tb_gado.getColumnModel().getColumn(13).setMaxWidth(0);
        tb_gado.getColumnModel().getColumn(13).setMinWidth(0);
        tb_gado.getColumnModel().getColumn(13).setPreferredWidth(0);

        tb_gado.getColumnModel().getColumn(16).setMaxWidth(0);
        tb_gado.getColumnModel().getColumn(16).setMinWidth(0);
        tb_gado.getColumnModel().getColumn(16).setPreferredWidth(0);

        tb_gado.getColumnModel().getColumn(18).setMaxWidth(0);
        tb_gado.getColumnModel().getColumn(18).setMinWidth(0);
        tb_gado.getColumnModel().getColumn(18).setPreferredWidth(0);

        tb_gado.getColumnModel().getColumn(19).setMaxWidth(0);
        tb_gado.getColumnModel().getColumn(19).setMinWidth(0);
        tb_gado.getColumnModel().getColumn(19).setPreferredWidth(0);

        tb_gado.getColumnModel().getColumn(21).setMaxWidth(0);
        tb_gado.getColumnModel().getColumn(21).setMinWidth(0);
        tb_gado.getColumnModel().getColumn(21).setPreferredWidth(0);

        tb_gado.getColumnModel().getColumn(24).setCellRenderer(cellRenderer);
        tb_gado.getColumnModel().getColumn(25).setCellRenderer(cellRenderer);
        tb_gado.getColumnModel().getColumn(10).setCellRenderer(cellRenderer);

        return tb_gado;
    }

    private TableModelRastreabilidade getTableModel() {
        if (TbGado == null) {
            TbGado = new TableModelRastreabilidade();
        }
        return TbGado;
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        jXTaskPane1 = new org.jdesktop.swingx.JXTaskPane();
        jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        txt_data = new com.toedter.calendar.JDateChooser();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        cb_motivo = new javax.swing.JComboBox<>();
        btn_lerDados = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        cb_fazenda = new javax.swing.JComboBox<>();
        btn_cadastrarAnimais = new javax.swing.JButton();
        jp_dadosCompra = new javax.swing.JPanel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        cb_comprador = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        cb_forma = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        txt_valorUnit = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txt_valorArr = new javax.swing.JTextField();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        txt_nCompra = new javax.swing.JTextField();
        btn_pesquisarCompra = new javax.swing.JButton();
        jXTaskPane2 = new org.jdesktop.swingx.JXTaskPane();
        jXPanel1 = new org.jdesktop.swingx.JXPanel();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        txt_idf = new javax.swing.JTextField();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        txt_ide = new javax.swing.JTextField();
        ch_macho = new javax.swing.JCheckBox();
        ch_femea = new javax.swing.JCheckBox();
        cb_categoria = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_peso = new javax.swing.JTextField();
        btt_habilitar = new javax.swing.JToggleButton();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        cb_biotipo = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        cb_raca = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        txt_idfMatriz = new javax.swing.JTextField();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        txt_ideMatriz = new javax.swing.JTextField();
        ch_aplicarProtocolo = new javax.swing.JCheckBox();
        cb_protocolo = new javax.swing.JComboBox<>();
        btn_add = new javax.swing.JButton();
        btn_del = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        javax.swing.JLabel jLabel29 = new javax.swing.JLabel();
        cb_statusAnimal = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_gado = new javax.swing.JTable();
        jXTaskPane3 = new org.jdesktop.swingx.JXTaskPane();
        jXPanel2 = new org.jdesktop.swingx.JXPanel();
        javax.swing.JLabel jLabel16 = new javax.swing.JLabel();
        cb_motivo1 = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        cb_fazenda1 = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel17 = new javax.swing.JLabel();
        txt_data1 = new com.toedter.calendar.JDateChooser();
        javax.swing.JLabel jLabel18 = new javax.swing.JLabel();
        txt_data2 = new com.toedter.calendar.JDateChooser();
        javax.swing.JLabel jLabel19 = new javax.swing.JLabel();
        txt_nCompra1 = new javax.swing.JTextField();
        btn_pesquisarCompra1 = new javax.swing.JButton();
        javax.swing.JLabel jLabel20 = new javax.swing.JLabel();
        cb_comprador1 = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        cb_forma1 = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel24 = new javax.swing.JLabel();
        txt_nBrinco1 = new javax.swing.JTextField();
        javax.swing.JLabel jLabel25 = new javax.swing.JLabel();
        txt_nBrinco2 = new javax.swing.JTextField();
        javax.swing.JLabel jLabel26 = new javax.swing.JLabel();
        cb_idBrinco = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel27 = new javax.swing.JLabel();
        cb_biotipo1 = new javax.swing.JComboBox<>();
        cb_raca1 = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel28 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        ch_macho1 = new javax.swing.JCheckBox();
        ch_femea1 = new javax.swing.JCheckBox();
        cb_categoria1 = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro de Animais Rastreados");

        jXTaskPane1.setTitle("Originação");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Data");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Motivo");

        cb_motivo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Compra", "Transf (+)", "Nascimento", "Era" }));
        cb_motivo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_motivoItemStateChanged(evt);
            }
        });

        btn_lerDados.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_lerDados.setText("Ler Dados do Arquivo");
        btn_lerDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lerDadosActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Fazenda");

        cb_fazenda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_fazendaItemStateChanged(evt);
            }
        });

        btn_cadastrarAnimais.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_cadastrarAnimais.setText("Cadastrar Animais");
        btn_cadastrarAnimais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cadastrarAnimaisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_data, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_motivo, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_fazenda, 0, 155, Short.MAX_VALUE)
                .addGap(269, 269, 269)
                .addComponent(btn_cadastrarAnimais, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_lerDados)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(txt_data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cb_motivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_lerDados)
                    .addComponent(jLabel21)
                    .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cadastrarAnimais))
                .addContainerGap())
        );

        jXTaskPane1.getContentPane().add(jPanel1);

        jp_dadosCompra.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Comprador");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Forma de Negociação");

        cb_forma.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Peso", "Cabeça" }));
        cb_forma.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_formaItemStateChanged(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Vlr Unit");

        txt_valorUnit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_valorUnitFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_valorUnitFocusLost(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Vlr /@");

        txt_valorArr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_valorArrFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_valorArrFocusLost(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Nº Compra");

        txt_nCompra.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btn_pesquisarCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar pequeno.png"))); // NOI18N
        btn_pesquisarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisarCompraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_dadosCompraLayout = new javax.swing.GroupLayout(jp_dadosCompra);
        jp_dadosCompra.setLayout(jp_dadosCompraLayout);
        jp_dadosCompraLayout.setHorizontalGroup(
            jp_dadosCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_dadosCompraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_pesquisarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_comprador, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_forma, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_valorUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_valorArr, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(274, 274, 274))
        );
        jp_dadosCompraLayout.setVerticalGroup(
            jp_dadosCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_dadosCompraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_dadosCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_pesquisarCompra, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txt_nCompra, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jp_dadosCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(cb_comprador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)
                        .addComponent(cb_forma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)
                        .addComponent(txt_valorUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15)
                        .addComponent(txt_valorArr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jXTaskPane1.getContentPane().add(jp_dadosCompra);

        jXTaskPane2.setExpanded(false);
        jXTaskPane2.setTitle("Dados do Animal");
        jXTaskPane2.setAnimated(false);

        jXPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("ID Fazenda");

        txt_idf.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("ID Eletrônica");

        txt_ide.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        buttonGroup1.add(ch_macho);
        ch_macho.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_macho.setText("Macho");
        ch_macho.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ch_machoItemStateChanged(evt);
            }
        });

        buttonGroup1.add(ch_femea);
        ch_femea.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_femea.setText("Femea");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Categoria");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Peso");

        txt_peso.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btt_habilitar.setText("Habilitar Leitura");
        btt_habilitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btt_habilitarActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Biótipo");

        cb_biotipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Zebu", "Cruzado", "Misto", "N/D" }));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Raça");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("IDF Matriz");

        txt_idfMatriz.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("IDE Matriz");

        txt_ideMatriz.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        ch_aplicarProtocolo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_aplicarProtocolo.setText("Protocolo");

        cb_protocolo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_protocolo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_protocoloActionPerformed(evt);
            }
        });

        btn_add.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_add.setText("Add");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        btn_del.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_del.setText("Del");
        btn_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delActionPerformed(evt);
            }
        });

        btn_editar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_editar.setText("Editar");
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setText("Status");

        cb_statusAnimal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_statusAnimalItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jXPanel1Layout = new javax.swing.GroupLayout(jXPanel1);
        jXPanel1.setLayout(jXPanel1Layout);
        jXPanel1Layout.setHorizontalGroup(
            jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jXPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_idf)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_ide)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_peso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btt_habilitar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ch_macho)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ch_femea)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8))
                    .addGroup(jXPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_raca, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_idfMatriz, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_ideMatriz, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_statusAnimal, 0, 1, Short.MAX_VALUE)
                        .addGap(12, 12, 12)
                        .addComponent(ch_aplicarProtocolo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb_protocolo, 0, 1, Short.MAX_VALUE)
                    .addComponent(cb_categoria, 0, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jXPanel1Layout.createSequentialGroup()
                        .addComponent(btn_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_editar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_del, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jXPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_biotipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jXPanel1Layout.setVerticalGroup(
            jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6)
                    .addComponent(txt_idf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txt_ide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ch_macho)
                    .addComponent(ch_femea)
                    .addComponent(jLabel8)
                    .addComponent(cb_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_peso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(btt_habilitar))
                    .addComponent(jLabel10)
                    .addComponent(cb_biotipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel11)
                    .addComponent(cb_raca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txt_idfMatriz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txt_ideMatriz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cb_protocolo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ch_aplicarProtocolo)
                        .addComponent(btn_add)
                        .addComponent(btn_del)
                        .addComponent(btn_editar)
                        .addComponent(jLabel29)
                        .addComponent(cb_statusAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jXTaskPane2.getContentPane().add(jXPanel1);

        tb_gado.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_gado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_gadoMouseClicked(evt);
            }
        });
        tb_gado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tb_gadoKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tb_gado);

        jXTaskPane3.setTitle("Opçoes de Consulta");
        jXTaskPane3.setAnimated(false);

        jXPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Motivo");

        cb_motivo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Compra", "Transf (+)", "Nascimento", "Era" }));
        cb_motivo1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_motivo1ItemStateChanged(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Fazenda");

        cb_fazenda1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_fazenda1ItemStateChanged(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Data");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("à");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Nº Compra");

        txt_nCompra1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btn_pesquisarCompra1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar pequeno.png"))); // NOI18N
        btn_pesquisarCompra1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisarCompra1ActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Comprador");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("Forma de Negociação");

        cb_forma1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Peso", "Cabeça" }));
        cb_forma1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_forma1ItemStateChanged(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Brinco");

        txt_nBrinco1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("à");

        txt_nBrinco2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("Brinco");

        cb_idBrinco.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cb_idBrinco.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "a", "f", "m", "n", "p", "v", " " }));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText("Biótipo");

        cb_biotipo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Zebu", "Cruzado", "Misto", "N/D" }));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setText("Raça");

        jButton1.setText("Pesquisar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        buttonGroup2.add(ch_macho1);
        ch_macho1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_macho1.setText("Macho");
        ch_macho1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ch_macho1ItemStateChanged(evt);
            }
        });

        buttonGroup2.add(ch_femea1);
        ch_femea1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_femea1.setText("Femea");

        javax.swing.GroupLayout jXPanel2Layout = new javax.swing.GroupLayout(jXPanel2);
        jXPanel2.setLayout(jXPanel2Layout);
        jXPanel2Layout.setHorizontalGroup(
            jXPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jXPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jXPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_motivo1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_fazenda1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_data1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_data2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nBrinco1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nBrinco2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_idBrinco, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nCompra1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_pesquisarCompra1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jXPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_comprador1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_forma1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ch_macho1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ch_femea1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_categoria1, 0, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_biotipo1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_raca1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jXPanel2Layout.setVerticalGroup(
            jXPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jXPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel16)
                    .addComponent(cb_motivo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(cb_fazenda1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(txt_data1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(txt_data2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(txt_nBrinco1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(txt_nBrinco2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(cb_idBrinco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(txt_nCompra1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesquisarCompra1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jXPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton1)
                    .addComponent(cb_raca1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_biotipo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel27)
                    .addComponent(cb_categoria1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ch_femea1)
                    .addComponent(ch_macho1)
                    .addComponent(cb_forma1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(cb_comprador1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addContainerGap())
        );

        jXTaskPane3.getContentPane().add(jXPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jXTaskPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jXTaskPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jXTaskPane3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jXTaskPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXTaskPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXTaskPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_pesquisarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisarCompraActionPerformed
        frmPesquisarCompra compra = new frmPesquisarCompra(null, false);
        compra.tb_compras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    int linhaSelect = compra.tb_compras.getSelectedRow();
                    CompraGadoDAO CompraD = new CompraGadoDAO();
                    txt_nCompra.setText(compra.TbCompra.getValueAt(linhaSelect, 0).toString());
                    CompraB = CompraD.buscarCompra((Integer) compra.TbCompra.getValueAt(linhaSelect, 0));
                    preencherDadosCompra(CompraB);
                    compra.dispose();
                }
            }
        });

        compra.setLocationRelativeTo(null);
        compra.setVisible(true);
    }//GEN-LAST:event_btn_pesquisarCompraActionPerformed

    private void btt_habilitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btt_habilitarActionPerformed
        if (btt_habilitar.isSelected() == true) {
            txt_peso.setEditable(false);
            selecionarPortaCOM(evt);
            /*   leitura = new SerialComLeitura("COM3", 9600, 100);
            leitura.HabilitarLeitura();
            leitura.ObterIdDaPorta();
            leitura.AbrirPorta();
            leitura.LerDados();
            txt_ler.setText(leitura.getPeso());
            //Controle de tempo da leitura aberta na serial
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                System.out.println("Erro na Thread: " + ex);
            }*/
        } else if (btt_habilitar.isSelected() == false) {
            //    leitura.FecharCom();
            //    txt_peso.setEditable(true);
        }

    }//GEN-LAST:event_btt_habilitarActionPerformed

    private void ch_machoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ch_machoItemStateChanged
        if (ch_macho.isSelected()) {
            carregarCategoriaAnimal(cb_categoria, "Macho");
        } else {
            carregarCategoriaAnimal(cb_categoria1, "Femea");
        }
    }//GEN-LAST:event_ch_machoItemStateChanged

    private void btn_lerDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lerDadosActionPerformed
        if (verificarBeansLerDados() == false) {

        } else {
            try {
                FileNameExtensionFilter filter = new FileNameExtensionFilter("EXCEL FILES", "xls", "excel");
                JFileChooser fc = new JFileChooser();
                fc.setFileFilter(filter);
                int option = fc.showOpenDialog(tb_gado);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File fileName = new File(fc.getSelectedFile().getAbsolutePath());
                    String path = fc.getSelectedFile().getParentFile().getPath();
                    WorkbookSettings conf = new WorkbookSettings();
                    conf.setEncoding("ISO-8859-1");
                    Workbook workbook = Workbook.getWorkbook(fileName, conf);
                    Sheet planilha = workbook.getSheet(0);
                    TbGado.limpar();
                    TbGado.addLista(ListaDadosPlanilha(planilha));
                    preencherIDTabela(RastD.verificarIDF(TbGado));
                }
            } catch (IOException ex) {
                //  Logger.getLogger(frmImportarFolhaPagto.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BiffException ex) {
                //Logger.getLogger(frmImportarFolhaPagto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_lerDadosActionPerformed

    private void cb_fazendaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_fazendaItemStateChanged

    }//GEN-LAST:event_cb_fazendaItemStateChanged

    private void btn_cadastrarAnimaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cadastrarAnimaisActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(null, "Deseja Cadastrar Estes Animais?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION) {
            List<TbRastreabilidadeBeans> listaID = RastD.cadastrarAnimal(TbGado);
            if (listaID != null) {
                preencherIDTabela(listaID);
                getAnimaisNaoCadastrados();
            }
        }
    }//GEN-LAST:event_btn_cadastrarAnimaisActionPerformed

    private void cb_motivoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_motivoItemStateChanged
        if (cb_motivo.getSelectedItem().toString().equals("Compra")) {
            jp_dadosCompra.setVisible(true);
        } else {
            jp_dadosCompra.setVisible(false);
        }
    }//GEN-LAST:event_cb_motivoItemStateChanged

    private void txt_valorUnitFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorUnitFocusLost
        Double valorUnit = 0.0;
        if (!txt_valorUnit.getText().equals("")) {
            try {
                valorUnit = Double.valueOf(txt_valorUnit.getText().replace("R$", "").replace(",", "."));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Verifique o valor digitado!", "Erro", 0, FormatarICO.ICObtnSair());
            }
            txt_valorUnit.setText(Corretores.ConverterDoubleReais(valorUnit));
        }
    }//GEN-LAST:event_txt_valorUnitFocusLost

    private void cb_formaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_formaItemStateChanged
        if (cb_forma.getSelectedItem().toString().equals("Peso")) {
            txt_valorUnit.setEnabled(false);
            txt_valorArr.setEnabled(true);
            txt_valorUnit.setText("R$ 0,00");
        } else if (cb_forma.getSelectedItem().toString().equals("Cabeça")) {
            txt_valorUnit.setEnabled(true);
            txt_valorArr.setEnabled(false);
            txt_valorArr.setText("R$ 0,00");
        } else {
            txt_valorUnit.setEnabled(false);
            txt_valorArr.setEnabled(false);
        }
    }//GEN-LAST:event_cb_formaItemStateChanged

    private void txt_valorArrFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorArrFocusLost
        Double valorArr = 0.00;
        if (!txt_valorArr.getText().equals("")) {
            try {
                valorArr = Double.valueOf(txt_valorArr.getText().replaceAll("R$", "").replaceAll(",", "."));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Verifique o valor digitado!", "Erro", 0, FormatarICO.ICObtnSair());
            }
            txt_valorArr.setText(Corretores.ConverterDoubleReais(valorArr));
        }
    }//GEN-LAST:event_txt_valorArrFocusLost

    private void txt_valorUnitFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorUnitFocusGained
        txt_valorUnit.selectAll();
    }//GEN-LAST:event_txt_valorUnitFocusGained

    private void txt_valorArrFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorArrFocusGained
        txt_valorArr.selectAll();
    }//GEN-LAST:event_txt_valorArrFocusGained

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        int linhasSelecionadas = tb_gado.getSelectedRowCount();
        int linha[] = tb_gado.getSelectedRows();
        int cadastrar = JOptionPane.showConfirmDialog(null, "Está ação irá editar " + linha.length + " registros, \n"
                + "verifique atentamente se eseja editar todos eles?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION) {
            if (linhasSelecionadas > 0) {
                editarRegistroUnico(linha);
            }
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void tb_gadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_gadoMouseClicked
        preencherFormulario(tb_gado.getSelectedRow());
    }//GEN-LAST:event_tb_gadoMouseClicked

    private void tb_gadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_gadoKeyReleased
        preencherFormulario(tb_gado.getSelectedRow());
    }//GEN-LAST:event_tb_gadoKeyReleased

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(null, "Deseja Adicionar Este Registro a Tabela?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION) {
            TbRastreabilidadeBeans l = new TbRastreabilidadeBeans();
            l.setID(0);
            l.setIDF(txt_idf.getText());
            l.setIDE(txt_ide.getText());
            l.setPeso(Corretores.ConverterStringDouble(txt_peso.getText()));
            l.setBiotipo(cb_biotipo.getSelectedItem().toString());
            l.setData(getData(txt_data.getDate()));
            l.setIdStatus(getIdStatusAnimal(cb_statusAnimal));
            l.setStatusAnimal(cb_statusAnimal.getSelectedItem().toString());
            l.setMotivo(cb_motivo.getSelectedItem().toString());
            l.setnCompra(getIdCompra());
            l.setIdFazenda(getIdFazenda(cb_fazenda));
            l.setFazenda(cb_fazenda.getSelectedItem().toString());
            l.setSexo(getSexo());
            l.setIdCategoria(getIdCategoria(cb_categoria));
            l.setCategoria(cb_categoria.getSelectedItem().toString());
            l.setBiotipo(cb_biotipo.getSelectedItem().toString());
            l.setIdRaca(getIdRaca(cb_raca));
            l.setRaca(cb_raca.getSelectedItem().toString());
            l.setIDEMatriz(txt_ideMatriz.getText());
            l.setIDFMatriz(txt_idfMatriz.getText());
            l.setProtocolo(0);

            if (getIdCompra() > 0) { // CASO SEJA SELECIONADO UM NÚMERO DA COMPRA UTILIZAR OS DADOS DO BEANS COMPRAB
                l.setComprador(CompraB.getComprador());
                l.setIdComprador(CompraB.getCompradorB().getIdComprador());
                l.setFormaNeg(CompraB.getNegociacao());
                if (CompraB.getNegociacao().equals("Peso")) {
                    l.setValorAr(CompraB.getReaisArr());
                    l.setValorUnit(l.getPeso() * CompraB.getReaisArr() / 30);
                } else if (CompraB.getNegociacao().equals("Cabeça")) {
                    l.setValorUnit(CompraB.getValorUnit());
                    l.setValorAr(l.getValorUnit() / l.getPeso() * 30);
                }
            } else { // CASO OS DADOS DA COMPRA SEJA FEITO MANUALMENTE
                l.setComprador(cb_comprador.getSelectedItem().toString());
                l.setIdComprador(getIdComprador(cb_comprador));
                l.setFormaNeg(cb_forma.getSelectedItem().toString());

                if (l.getFormaNeg().equals("Peso")) {
                    l.setValorUnit(l.getPeso() * Corretores.ConverterReaisDouble(txt_valorArr.getText()) / 30);
                    l.setValorAr(Corretores.ConverterReaisDouble(txt_valorArr.getText()));
                } else if (l.getFormaNeg().equals("Cabeça")) {
                    l.setValorUnit(Corretores.ConverterReaisDouble(txt_valorUnit.getText()));
                    if (l.getPeso() > 0.00) {
                        l.setValorAr(Corretores.ConverterReaisDouble(txt_valorUnit.getText()) / (l.getPeso() / 30));
                    } else {
                        l.setValorAr(0.00);
                    }
                } else {
                    l.setValorUnit(0.00);
                    l.setValorAr(0.00);
                }
            }
            TbGado.addBeans(l);
            limparDadosAnimal();
        }
    }//GEN-LAST:event_btn_addActionPerformed

    private void cb_motivo1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_motivo1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_motivo1ItemStateChanged

    private void cb_fazenda1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_fazenda1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_fazenda1ItemStateChanged

    private void btn_pesquisarCompra1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisarCompra1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_pesquisarCompra1ActionPerformed

    private void cb_forma1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_forma1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_forma1ItemStateChanged

    private void ch_macho1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ch_macho1ItemStateChanged
        if (ch_macho.isSelected()) {
            carregarCategoriaAnimal(cb_categoria1, "Macho");
        } else {
            carregarCategoriaAnimal(cb_categoria1, "Femea");
        }
    }//GEN-LAST:event_ch_macho1ItemStateChanged

    private void cb_protocoloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_protocoloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_protocoloActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        RastD.consultarBrinco(SQLWhere(), TbGado);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delActionPerformed
        int excluir = JOptionPane.showConfirmDialog(null, "Deseja Excluir Este Registro a Tabela?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (excluir == JOptionPane.YES_OPTION) {
            Integer rowIndex = tb_gado.getSelectedRow();
            TbGado.excluirLinha(rowIndex);
        }
    }//GEN-LAST:event_btn_delActionPerformed

    private void cb_statusAnimalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_statusAnimalItemStateChanged
       
    }//GEN-LAST:event_cb_statusAnimalItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_cadastrarAnimais;
    private javax.swing.JButton btn_del;
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_lerDados;
    javax.swing.JButton btn_pesquisarCompra;
    javax.swing.JButton btn_pesquisarCompra1;
    javax.swing.JToggleButton btt_habilitar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cb_biotipo;
    private javax.swing.JComboBox<String> cb_biotipo1;
    private javax.swing.JComboBox<CategoriaAnimalBeans> cb_categoria;
    private javax.swing.JComboBox<CategoriaAnimalBeans> cb_categoria1;
    private javax.swing.JComboBox<CompradorGadoBeans> cb_comprador;
    private javax.swing.JComboBox<CompradorGadoBeans> cb_comprador1;
    private javax.swing.JComboBox<ListFazendasBeans> cb_fazenda;
    private javax.swing.JComboBox<ListFazendasBeans> cb_fazenda1;
    private javax.swing.JComboBox<String> cb_forma;
    private javax.swing.JComboBox<String> cb_forma1;
    private javax.swing.JComboBox<String> cb_idBrinco;
    private javax.swing.JComboBox<String> cb_motivo;
    private javax.swing.JComboBox<String> cb_motivo1;
    private javax.swing.JComboBox<String> cb_protocolo;
    private javax.swing.JComboBox<ListRacaAnimalBeans> cb_raca;
    private javax.swing.JComboBox<ListRacaAnimalBeans> cb_raca1;
    private javax.swing.JComboBox<Object> cb_statusAnimal;
    private javax.swing.JCheckBox ch_aplicarProtocolo;
    private javax.swing.JCheckBox ch_femea;
    private javax.swing.JCheckBox ch_femea1;
    private javax.swing.JCheckBox ch_macho;
    private javax.swing.JCheckBox ch_macho1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXPanel jXPanel1;
    private org.jdesktop.swingx.JXPanel jXPanel2;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane1;
    public org.jdesktop.swingx.JXTaskPane jXTaskPane2;
    public org.jdesktop.swingx.JXTaskPane jXTaskPane3;
    private javax.swing.JPanel jp_dadosCompra;
    private javax.swing.JTable tb_gado;
    private com.toedter.calendar.JDateChooser txt_data;
    private com.toedter.calendar.JDateChooser txt_data1;
    private com.toedter.calendar.JDateChooser txt_data2;
    private javax.swing.JTextField txt_ide;
    private javax.swing.JTextField txt_ideMatriz;
    private javax.swing.JTextField txt_idf;
    private javax.swing.JTextField txt_idfMatriz;
    private javax.swing.JTextField txt_nBrinco1;
    private javax.swing.JTextField txt_nBrinco2;
    private javax.swing.JTextField txt_nCompra;
    private javax.swing.JTextField txt_nCompra1;
    public javax.swing.JTextField txt_peso;
    private javax.swing.JTextField txt_valorArr;
    private javax.swing.JTextField txt_valorUnit;
    // End of variables declaration//GEN-END:variables

    private String corrigirNbrinco(String nBrinco) {
        Integer numero;
        if (!nBrinco.contains(".")) {
            nBrinco = nBrinco + ".";
        }
        String[] codigoBrinco = nBrinco.split("[.]");
        String ultimaLetra = codigoBrinco[0].substring(codigoBrinco[0].length() - 1, codigoBrinco[0].length()).toLowerCase();
        try {
            String num = codigoBrinco[0].replaceAll("[^0-9]", "");
            numero = Integer.parseInt(num);
        } catch (NumberFormatException e) {
            numero = 0;
        }

        // capturar o Biotipo e Raça do Animal
        setBiotipo(codigoBrinco);
        setClassificacao(codigoBrinco);
        setIdRaca(codigoBrinco);
        return String.format("%05d", numero) + ultimaLetra;
    }

    private String getData(Date data) {
        String s = "";
        try {
            s = new SimpleDateFormat("dd/MM/yyyy").format(data);
        } catch (Exception e) {
        }

        return s;
    }

    private Integer getIdCompra() {
        Integer n = 0;
        if (!txt_nCompra.getText().equals("")) {
            n = Integer.parseInt(txt_nCompra.getText());
        }
        return n;
    }

    private Integer getIdCategoria(JComboBox<CategoriaAnimalBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return comboBox.getItemAt(comboBox.getSelectedIndex()).getID();
        }
        return 0;
    }

    private Integer getIdComprador(JComboBox<CompradorGadoBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return comboBox.getItemAt(comboBox.getSelectedIndex()).getIdComprador();
        }
        return 0;
    }

    private Integer getIdStatusAnimal(JComboBox combo) {
        CbModelStatusAnimal model = (CbModelStatusAnimal) combo.getModel();
        return model.getID();

    }

    private Integer getIdRaca(JComboBox<ListRacaAnimalBeans> comboBox) {
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

    private String getSexo() {
        String s = "";
        if (ch_macho.isSelected()) {
            s = "Macho";
        } else if (ch_femea.isSelected()) {
            s = "Femea";
        }
        return s;
    }

    private void setComboBoxComprador(JComboBox<CompradorGadoBeans> comboBox, String Comprador) {
        for (int i = 0; i < comboBox.getModel().getSize(); i++) {
            if (comboBox.getModel().getElementAt(i).getComprador().equals(Comprador)) {
                comboBox.setSelectedIndex(i);
            }
        }
    }

    private void setComboBoxFazenda(JComboBox<ListFazendasBeans> comboBox, Integer ID) {
        for (int i = 0; i < comboBox.getModel().getSize(); i++) {
            if (comboBox.getModel().getElementAt(i).getID() == ID) {
                comboBox.setSelectedIndex(i);
            }
        }
    }

    private void setComboBoxRaca(JComboBox<ListRacaAnimalBeans> comboBox, Integer ID) {
        for (int i = 0; i < comboBox.getModel().getSize(); i++) {
            if (comboBox.getModel().getElementAt(i).getID() == ID) {
                comboBox.setSelectedIndex(i);
            }
        }
    }

    private void setComboBoxCategoria(JComboBox<CategoriaAnimalBeans> comboBox, Integer idCategoria) {
        for (int i = 0; i < comboBox.getModel().getSize(); i++) {
            if (comboBox.getModel().getElementAt(i).getID() == idCategoria) {
                comboBox.setSelectedIndex(i);
            }
        }
    }

    private void preencherIDTabela(List<TbRastreabilidadeBeans> listaID) {
        for (int y = 0; y < listaID.size(); y++) {
            Integer ID = listaID.get(y).getID();
            String Brinco = listaID.get(y).getIDF();
            for (int i = 0; i < TbGado.getRowCount(); i++) {
                if (TbGado.getValueAt(i, TbGado.IDF).toString().equals(Brinco)) {
                    TbGado.setValueAt(ID, i, TbGado.ID);
                    break;
                }
            }
        }
    }

    private void preencherDadosCompra(CompraGadoBeans bean) {
        setComboBoxComprador(cb_comprador, bean.getComprador());
        cb_forma.setSelectedItem(bean.getNegociacao());
        txt_valorUnit.setText(Corretores.ConverterDoubleReais(bean.getValorUnit()));
        txt_valorArr.setText(Corretores.ConverterDoubleReais(bean.getReaisArr()));
    }

    private void preencherFormulario(Integer linha) {
        TbRastreabilidadeBeans b = TbGado.getBeans(linha);
        txt_data.setDate(Corretores.ConverterStringDateDDMMAAAA(b.getData()));
        cb_motivo.setSelectedItem(b.getMotivo());
        setComboBoxFazenda(cb_fazenda, b.getIdFazenda());
        txt_nCompra.setText(b.getnCompra().toString());
        setComboBoxComprador(cb_comprador, b.getComprador());
        cb_forma.setSelectedItem(b.getFormaNeg());
        txt_valorUnit.setText(Corretores.ConverterDoubleReais(b.getValorUnit()));
        txt_valorArr.setText(Corretores.ConverterDoubleReais(b.getValorAr()));
        txt_idf.setText(b.getIDF());
        txt_ide.setText(b.getIDE());
        txt_peso.setText((b.getPeso().toString().replace(".", ",")));
        if (b.getSexo().equals("Macho")) {
            ch_macho.setSelected(true);
        } else {
            ch_femea.setSelected(true);
        }
        setComboBoxCategoria(cb_categoria, b.getIdCategoria());
        cb_biotipo.setSelectedItem(b.getBiotipo());
        setComboBoxRaca(cb_raca, b.getIdRaca());
        txt_idfMatriz.setText(b.getIDFMatriz());
        txt_ideMatriz.setText(b.getIDEMatriz());
    }

    private void editarRegistroUnico(int[] linhas) {
        for (int i = 0; i < linhas.length; i++) {
            TbRastreabilidadeBeans l = new TbRastreabilidadeBeans();
            l.setID((Integer) TbGado.getValueAt(linhas[i], 0));

            if (linhas.length == 1) {
                l.setIDF(txt_idf.getText());
                l.setIDE(txt_ide.getText());
                l.setPeso(Corretores.ConverterStringDouble(txt_peso.getText()));
            } else if (linhas.length > 1) {
                l.setIDF(TbGado.getValueAt(linhas[i], TbGado.IDF).toString());
                l.setIDE(TbGado.getValueAt(linhas[i], TbGado.IDE).toString());
                l.setPeso((Double) TbGado.getValueAt(linhas[i], TbGado.PESO));
            }
            
            l.setBiotipo(cb_biotipo.getSelectedItem().toString());
            l.setData(getData(txt_data.getDate()));
            l.setIdStatus(getIdStatusAnimal(cb_statusAnimal));
            l.setStatusAnimal(cb_statusAnimal.getSelectedItem().toString());
            l.setMotivo(cb_motivo.getSelectedItem().toString());
            l.setnCompra(getIdCompra());
            l.setIdFazenda(getIdFazenda(cb_fazenda));
            l.setFazenda(cb_fazenda.getSelectedItem().toString());
            l.setSexo(getSexo());
            l.setIdCategoria(getIdCategoria(cb_categoria));
            l.setCategoria(cb_categoria.getSelectedItem().toString());
            l.setBiotipo(cb_biotipo.getSelectedItem().toString());
            l.setIdRaca(getIdRaca(cb_raca));
            l.setRaca(cb_raca.getSelectedItem().toString());
            l.setIDEMatriz("");
            l.setIDFMatriz("");
            l.setProtocolo(0);
            l.setCampoEditado(true);

            if (getIdCompra() > 0) { // CASO SEJA SELECIONADO UM NÚMERO DA COMPRA UTILIZAR OS DADOS DO BEANS COMPRAB
                l.setComprador(CompraB.getComprador());
                l.setIdComprador(CompraB.getCompradorB().getIdComprador());
                l.setFormaNeg(CompraB.getNegociacao());
                if (CompraB.getNegociacao().equals("Peso")) {
                    l.setValorAr(CompraB.getReaisArr());
                    l.setValorUnit((Double) TbGado.getValueAt(linhas[i], 8) * CompraB.getReaisArr() / 30);
                } else if (CompraB.getNegociacao().equals("Cabeça")) {
                    l.setValorUnit(Double.valueOf(CompraB.getValorUnit()));
                    l.setValorAr(l.getValorUnit() / l.getPeso() * 30);
                }
            } else { // CASO OS DADOS DA COMPRA SEJA FEITO MANUALMENTE
                l.setComprador(cb_comprador.getSelectedItem().toString());
                l.setIdComprador(getIdComprador(cb_comprador));
                l.setFormaNeg(cb_forma.getSelectedItem().toString());

                if (l.getFormaNeg().equals("Peso")) {
                    l.setValorUnit((Double) TbGado.getValueAt(linhas[i], 8) * Corretores.ConverterReaisDouble(txt_valorArr.getText()) / 30);
                    l.setValorAr(Corretores.ConverterReaisDouble(txt_valorArr.getText()));
                } else if (l.getFormaNeg().equals("Cabeça")) {
                    l.setValorUnit(Corretores.ConverterReaisDouble(txt_valorUnit.getText()));
                    if ((Double) TbGado.getValueAt(linhas[i], 8) > 0.00) {
                        l.setValorAr(Corretores.ConverterReaisDouble(txt_valorUnit.getText()) / ((Double) TbGado.getValueAt(linhas[i], 8) / 30));
                    } else {
                        l.setValorAr(0.00);
                    }
                } else {
                    l.setValorUnit(0.00);
                    l.setValorAr(0.00);
                }
            }
            TbGado.SetObject(l, linhas[i]);
        }
    }

    private List<TbRastreabilidadeBeans> ListaDadosPlanilha(Sheet planilha) {
        List<TbRastreabilidadeBeans> lista = new ArrayList<>();
        for (int i = 1; i < planilha.getRows(); i++) {
            if (!planilha.getCell(1, i).getContents().equals("")) {
                TbRastreabilidadeBeans l = new TbRastreabilidadeBeans();
                l.setIDF(corrigirNbrinco(planilha.getCell(0, i).getContents()));
                l.setPeso(Corretores.ConverterStringDouble(planilha.getCell(1, i).getContents()));
                String IDE = planilha.getCell(2, i).getContents();
                if (IDE.length() > 0) {
                    l.setIDE(IDE);
                } else {
                    l.setIDE("");
                }

                l.setID(0);
                l.setBiotipo(cb_biotipo.getSelectedItem().toString());
                l.setData(getData(txt_data.getDate()));
                l.setIdStatus(getIdStatusAnimal(cb_statusAnimal));
                l.setStatusAnimal(cb_statusAnimal.getSelectedItem().toString());
                l.setMotivo(cb_motivo.getSelectedItem().toString());
                l.setnCompra(getIdCompra());
                l.setIdFazenda(getIdFazenda(cb_fazenda));
                l.setFazenda(cb_fazenda.getSelectedItem().toString());
                l.setSexo(getSexo());
                l.setIdCategoria(getIdCategoria(cb_categoria));
                l.setCategoria(cb_categoria.getSelectedItem().toString());

                if (!getBiotipo().equals("-")) {
                    l.setBiotipo(getBiotipo());
                } else {
                    l.setBiotipo(cb_biotipo.getSelectedItem().toString());
                }

                l.setClassificacao(this.Classificacao);
                l.setIdRaca(this.Raca.getID());
                l.setRaca(this.Raca.getDescricao());
                l.setIDEMatriz("");
                l.setIDFMatriz("");
                l.setProtocolo(0);

                if (getIdCompra() > 0) {
                    l.setComprador(CompraB.getComprador());
                    l.setIdComprador(CompraB.getCompradorB().getIdComprador());
                    l.setFormaNeg(CompraB.getNegociacao());
                    if (CompraB.getNegociacao().equals("Peso")) {
                        l.setValorAr(Double.valueOf(CompraB.getReaisArr()));
                        l.setValorUnit(l.getPeso() * CompraB.getReaisArr() / 30);
                    } else if (CompraB.getNegociacao().equals("Cabeça")) {
                        l.setValorUnit(Double.valueOf(CompraB.getValorUnit()));
                        l.setValorAr(l.getValorUnit() / l.getPeso() * 30);
                    }
                } else {
                    l.setComprador("");
                    l.setIdComprador(0);
                    l.setFormaNeg("");
                    l.setValorUnit(0.0);
                    l.setValorAr(0.0);
                }
                TbGado.addBeans(l);
            }
        }
        return lista;
    }

    private void limparDadosAnimal() {
        txt_idf.setText("");
        txt_ide.setText("");
        txt_peso.setText("");
        cb_categoria.setSelectedIndex(0);
        cb_biotipo.setSelectedIndex(0);
        cb_raca.setSelectedIndex(0);
        txt_idfMatriz.setText("");
        txt_ideMatriz.setText("");

    }

    private Boolean verificarBeansLerDados() {
        if (txt_data.getDate() == null || txt_data.getDate().equals("__/__/____")) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Data!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (cb_fazenda.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Fazenda!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (cb_motivo.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Motivo!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (cb_statusAnimal.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Status do Animal!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        
        return true;
    }

    private JDialog selecionarPortaCOM(ActionEvent evt) {
        jd_selecionarPorta = new JDialog();
        jd_selecionarPorta.setTitle("Selecione a Porta COM");
        jd_selecionarPorta.setSize(btt_habilitar.getSize());
        jd_selecionarPorta.setDefaultCloseOperation(0);
        jd_selecionarPorta.setUndecorated(true);
        jd_selecionarPorta.setLocationRelativeTo(btt_habilitar);
        jd_selecionarPorta.setModal(true);
        //jd_selecionarPorta.setLayout(new HorizontalLayout());
        cb_portaCOM = new JComboBox();
        cb_portaCOM.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                jd_selecionarPorta.setVisible(false);
            }

        });
        cb_portaCOM.addItem("-");
        cb_portaCOM.addItem("COM1");
        cb_portaCOM.addItem("COM2");
        cb_portaCOM.addItem("COM3");
        cb_portaCOM.addItem("COM4");

        //JLabel lbLabel = new JLabel("Porta");
        //lbLabel.setSize(new Dimension(80, 70));
        //lbLabel.setVisible(true);
        //jd_selecionarPorta.add(lbLabel);
        jd_selecionarPorta.add(cb_portaCOM);
        jd_selecionarPorta.setVisible(true);

        return jd_selecionarPorta;

    }

    private void CapturarNBrinco(String nBrinco) {
        int nChar = nBrinco.length();
        this.setIdBrinco(nBrinco.substring(nChar));
        try {
            this.setnBrinco(new Integer(nBrinco.substring(0, nChar - 1)));
        } catch (Exception e) {
            this.setnBrinco(0);
        }

    }

    private String SQLWhere() {
        String s = "";
        if (cb_motivo1.getSelectedIndex() > 0) {
            s += " and car.origem_entrada = '" + cb_motivo1.getSelectedItem().toString() + "'";
        }
        if (cb_fazenda1.getSelectedIndex() > 0) {
            s += " and car.id_fazenda = " + getIdFazenda(cb_fazenda1);
        }
        if (txt_data1.getDate() != null && txt_data2.getDate() != null) {
            String dt1 = Corretores.ConverterDateStringDDMMAAA(txt_data1.getDate());
            String dt2 = Corretores.ConverterDateStringDDMMAAA(txt_data2.getDate());
            s += " and car.data_cadastro between " + Corretores.ConverterParaSQL(dt1) + " and " + Corretores.ConverterParaSQL(dt2);
        }
        if (!txt_nBrinco1.getText().equals("") && !txt_nBrinco2.getText().equals("")) {
            Integer brinco1 = new Integer(txt_nBrinco1.getText());
            Integer brinco2 = new Integer(txt_nBrinco2.getText());
            s += " and car.IDF between " + brinco1 + " and " + brinco2;
        }
        if (cb_idBrinco.getSelectedIndex() > 0) {
            s += " and car.id_brinco = '" + cb_idBrinco.getSelectedItem().toString() + "'";
        }
        if (!txt_nCompra1.getText().equals("")) {
            Integer nCompra = new Integer(txt_nCompra1.getText());
            s += " and car.n_compra = " + nCompra;
        }
        if (cb_comprador1.getSelectedIndex() > 0) {
            s += " and car.id_comprador = " + getIdComprador(cb_comprador1);
        }
        if (cb_forma1.getSelectedIndex() > 0) {
            s += " and car.forma = '" + cb_forma1.getSelectedItem().toString() + "'";
        }
        if (cb_categoria1.getSelectedIndex() > 0) {
            s += " and car.id_categoria = " + getIdCategoria(cb_categoria1);
        }
        if (cb_biotipo1.getSelectedIndex() > 0) {
            s += " and car.biotipo = '" + cb_biotipo1.getSelectedItem().toString() + "'";
        }
        if (cb_raca1.getSelectedIndex() > 0) {
            s += " and car.id_raca = " + getIdRaca(cb_raca1);
        }

        if (!s.equals("")) {
            s = " Where " + s.replaceFirst("and", "");
        }
        //System.out.println(s);
        return s;
    }

    private Integer getAnimaisNaoCadastrados() {
        Integer nAnimais = 0;
        for (int i = 0; i < TbGado.getRowCount(); i++) {
            if (TbGado.getBeans(i).getID() == 0) {
                nAnimais += 1;
            }
        }
        if (nAnimais > 0) {
            JOptionPane.showMessageDialog(null, nAnimais + " Animal(s) não foram cadastrados!", "Executado", 1, FormatarICO.ICObtnSair());
        }
        return nAnimais;
    }

    class CellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setHorizontalAlignment(SwingConstants.CENTER);
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            Color background = table.getBackground();
            Color foreground = table.getForeground();
            Object val = table.getValueAt(row, column);
            
            TableModelRastreabilidade model = (TableModelRastreabilidade)table.getModel();
            
            if (column == model.PRECOUNIT || column == model.VALORAR) {
                if (val instanceof Double) {
                    Double valorD = (Double) val;
                    setText(new DecimalFormat("R$ #,##0.00").format(valorD));
                }

            } else if (column == model.PESO) {
                if (val instanceof Double) {
                    Double valorD = (Double) val;
                    setText(new DecimalFormat("#,##0.00 kg").format(valorD));
                }
            }

            if (isSelected) {
                return this;
            }

            /* if ((Integer) table.getValueAt(row, 0) == 0) {
                Color vermelho = new Color(244, 176, 132);
                setBackground(vermelho);
            } else {
                setBackground(background);
            } */
            return this;
        }
    }

}
