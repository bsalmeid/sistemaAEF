/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Beans.FreteGadoBeans;
import Beans.FreteTableModel;
import Beans.PagamentosBeans;
import Beans.TransportadorasBeans;
import Beans.PagtoDiversosBeans;
import DAO.Diversas;
import DAO.DiversasHibernate;
import DAO.FreteGadoDAO;
import static GUI.Principal.OrigemContasAPagar;
import static GUI.Principal.listTransportadoras;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import Utilitarios.ReaisCellRenderer;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.MaskFormatter;

public class frmFreteGado extends javax.swing.JInternalFrame {

    Diversas DiversasD;
    MaskFormatter FormatoPlaca;
    DefaultTableModel TbFrete;
    FreteGadoDAO FreteD;
    FreteGadoBeans FreteB;
    JTextField TextField;
    TableColumn ColunaVlrFrete;
    Integer linhaSelecionada;
    Double valorTotal;
    JComboBox cbStatusPagto;
    frmPagamentos Pagamentos;
    PagtoDiversosBeans PagtoDiversosB;
    frmFreteGado frmFreteGado;
    public static FreteTableModel TbFreteTeste;
    CentralizarTabela centralizar;
    JComboBox cb_transpTable;
    TableColumn tbcTransportadora;

    public frmFreteGado() {
        initComponents();
        DiversasD = new Diversas();
        FreteD = new FreteGadoDAO();
        FreteB = new FreteGadoBeans();
        PagtoDiversosB = new PagtoDiversosBeans();
        ArrayList Fazendas = DiversasD.pmfazenda();
        cb_destino.setModel(new DefaultComboBoxModel(new Vector(Fazendas)));
        cb_origem.setModel(new DefaultComboBoxModel(new Vector(Fazendas)));
        cb_transp.setModel(new DefaultComboBoxModel(new Vector(DiversasHibernate.getListaTransportadoras())));
        getTblSocios();
        comboBoxCarregarTransportadora();
        txt_dtInicial.setDate(Corretores.PrimeiroDiaMes());
        txt_dtFinal.setDate(Corretores.UltimoDiaMes());

    }

    private JTable getTblSocios() {

        tb_frete.setModel(getTableModel());
        ((DefaultTableCellRenderer) tb_frete.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        centralizar = new CentralizarTabela();
        centralizar.centralizarTabela(tb_frete);
        comboBoxTransportadora();
        tb_frete.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tb_frete.getColumnModel().getColumn(0).setPreferredWidth(50);
        tb_frete.getColumnModel().getColumn(1).setPreferredWidth(70);
        tb_frete.getColumnModel().getColumn(2).setPreferredWidth(70);
        tb_frete.getColumnModel().getColumn(3).setPreferredWidth(90);
        tb_frete.getColumnModel().getColumn(4).setPreferredWidth(90);
        tb_frete.getColumnModel().getColumn(5).setPreferredWidth(100);
        tb_frete.getColumnModel().getColumn(6).setPreferredWidth(100);
        tb_frete.getColumnModel().getColumn(7).setPreferredWidth(110);
        tb_frete.getColumnModel().getColumn(8).setPreferredWidth(80);
        tb_frete.getColumnModel().getColumn(9).setPreferredWidth(60);
        tb_frete.getColumnModel().getColumn(10).setPreferredWidth(70);
        tb_frete.getColumnModel().getColumn(11).setPreferredWidth(70);
        tb_frete.getColumnModel().getColumn(12).setPreferredWidth(60);
        tb_frete.getColumnModel().getColumn(13).setPreferredWidth(80);
        tb_frete.getColumnModel().getColumn(14).setPreferredWidth(100);
        tb_frete.getColumnModel().getColumn(15).setPreferredWidth(50);
        tb_frete.getColumnModel().getColumn(16).setPreferredWidth(0);
        tb_frete.getColumnModel().getColumn(16).setMinWidth(0);
        tb_frete.getColumnModel().getColumn(16).setMaxWidth(0);
        tb_frete.getColumnModel().getColumn(17).setPreferredWidth(70);
        tb_frete.getColumnModel().getColumn(18).setPreferredWidth(90);
        tb_frete.getColumnModel().getColumn(13).setCellRenderer(new ReaisCellRenderer());
        tb_frete.getColumnModel().getColumn(14).setCellRenderer(new ReaisCellRenderer());
        cbStatusPagto = new JComboBox();
        cbStatusPagto.setModel(new DefaultComboBoxModel());
        cbStatusPagto.addItem("-");
        cbStatusPagto.addItem("A Pagar");
        cbStatusPagto.addItem("Pago");
        TableColumn StatusPagto = tb_frete.getColumnModel().getColumn(17);
        StatusPagto.setCellEditor(new DefaultCellEditor(cbStatusPagto));
        return tb_frete;

    }

    private FreteTableModel getTableModel() {
        if (TbFreteTeste == null) {
            TbFreteTeste = new FreteTableModel();
        }
        return TbFreteTeste;
    }

    private JComboBox comboBoxTransportadora() {
        if (cb_transpTable == null) {
            cb_transpTable = new JComboBox();
            tbcTransportadora = new TableColumn();
            tbcTransportadora = tb_frete.getColumnModel().getColumn(7);
            tbcTransportadora.setCellEditor(new DefaultCellEditor(cb_transpTable));
        }
        return cb_transpTable;
    }

    private void comboBoxCarregarTransportadora() {
        if (listTransportadoras == null) {
            listTransportadoras = (DiversasHibernate.getListaTransportadoras());
        }
        //comboBoxTransportadora().addItem("-");
        for (TransportadorasBeans list : listTransportadoras) {
            comboBoxTransportadora().addItem(list.toString());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopUp = new javax.swing.JPopupMenu();
        jMenu_romaneio = new javax.swing.JMenuItem();
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        txt_nCompra = new javax.swing.JTextField();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        try {

            FormatoPlaca = new MaskFormatter("AAA-####");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar máscara", "Erro", 0);
        }
        txt_placa = new JFormattedTextField(FormatoPlaca);
        txt_nMin = new javax.swing.JTextField();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        cb_destino = new javax.swing.JComboBox<>();
        txt_dtFinal = new com.toedter.calendar.JDateChooser();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        txt_dtInicial = new com.toedter.calendar.JDateChooser();
        btn_pesquisar1 = new javax.swing.JButton();
        cb_transp = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        cb_origem = new javax.swing.JComboBox<>();
        ch_fretePago = new javax.swing.JCheckBox();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        cb_statusPagto = new javax.swing.JComboBox<>();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        tb_frete = new javax.swing.JTable();
        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        txt_vlrKM = new javax.swing.JTextField();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        txt_vlrTotal = new javax.swing.JTextField();
        btn_rVlrKM = new javax.swing.JButton();
        btn_rVlrTotal = new javax.swing.JButton();
        btn_Salvar = new javax.swing.JButton();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        txt_vlrSelecionado = new javax.swing.JTextField();
        ch_marcarTodos = new javax.swing.JCheckBox();
        javax.swing.JButton jButton1 = new javax.swing.JButton();
        btn_gerarPagto = new javax.swing.JButton();

        jMenu_romaneio.setText("Abrir Romaneio");
        jMenu_romaneio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_romaneioActionPerformed(evt);
            }
        });
        jPopUp.add(jMenu_romaneio);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Acerto de Frete de Gado");
        setToolTipText("");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Nº Compra");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Placa ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Nº Min");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Transportadora");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Destino");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Data Final");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Data Inicial");

        btn_pesquisar1.setBackground(new java.awt.Color(255, 255, 255));
        btn_pesquisar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar.png"))); // NOI18N
        btn_pesquisar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisar1ActionPerformed(evt);
            }
        });

        cb_transp.setEditable(true);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Origem");

        cb_origem.setEditable(true);

        ch_fretePago.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_fretePago.setText("Exibir Minutas Conferidas");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Status Pagto");

        cb_statusPagto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "A Pagar", "Pago" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_dtInicial, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addComponent(txt_dtFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel10))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cb_transp, 0, 129, Short.MAX_VALUE)
                    .addComponent(cb_origem, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_placa, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_destino, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_nMin))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_statusPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ch_fretePago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_pesquisar1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(txt_placa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)
                                .addComponent(txt_nMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cb_transp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ch_fretePago))
                            .addComponent(txt_dtInicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_dtFinal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(txt_nCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cb_destino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)
                                .addComponent(jLabel10)
                                .addComponent(cb_origem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12)
                                .addComponent(cb_statusPagto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btn_pesquisar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        tb_frete.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Título 1", "Título 2", "Título 3", "Título 4", "Título 5", "Título 6", "Título 7", "Título 8", "Título 9", "Título 10", "Título 11", "Título 12", "Título 13", "Título 14", "Título 15", "Título 16", "Título 17"
            }
        ));
        tb_frete.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tb_frete.getTableHeader().setReorderingAllowed(false);
        tb_frete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_freteMouseClicked(evt);
            }
        });
        tb_frete.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tb_fretePropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(tb_frete);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Replicar: R$/KM");

        txt_vlrKM.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_vlrKMFocusLost(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Replicar: Vlr Total");

        txt_vlrTotal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_vlrTotalFocusLost(evt);
            }
        });

        btn_rVlrKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/ico_caixa_15_15.png"))); // NOI18N
        btn_rVlrKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_rVlrKMActionPerformed(evt);
            }
        });

        btn_rVlrTotal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/ico_caixa_15_15.png"))); // NOI18N
        btn_rVlrTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_rVlrTotalActionPerformed(evt);
            }
        });

        btn_Salvar.setBackground(new java.awt.Color(255, 255, 255));
        btn_Salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/salvar_32_32.png"))); // NOI18N
        btn_Salvar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_Salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalvarActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Valor Total");

        txt_vlrSelecionado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_vlrSelecionadoFocusLost(evt);
            }
        });

        ch_marcarTodos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_marcarTodos.setText("Selecionar Todos");
        ch_marcarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ch_marcarTodosActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/excel_icon.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btn_gerarPagto.setBackground(new java.awt.Color(255, 255, 255));
        btn_gerarPagto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/ico_caixa.png"))); // NOI18N
        btn_gerarPagto.setToolTipText("");
        btn_gerarPagto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_gerarPagtoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_vlrKM, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_rVlrKM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_vlrTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_rVlrTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(252, 252, 252)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_vlrSelecionado, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ch_marcarTodos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(btn_Salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btn_gerarPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel8)
                    .addComponent(txt_vlrKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_rVlrKM)
                    .addComponent(jLabel9)
                    .addComponent(txt_vlrTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_rVlrTotal)
                    .addComponent(jLabel11)
                    .addComponent(txt_vlrSelecionado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ch_marcarTodos)
                    .addComponent(btn_Salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_gerarPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                .addContainerGap())
        );

        setBounds(0, 0, 1329, 600);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_pesquisar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisar1ActionPerformed
        popularBeans();
        FreteD.buscarEntradas(FreteB, TbFreteTeste);
    }//GEN-LAST:event_btn_pesquisar1ActionPerformed

    private void btn_SalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalvarActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(null, "Deseja Salvar estes valores de frete?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION) {
            FreteD.atualizarFrete(TbFreteTeste);
        }
    }//GEN-LAST:event_btn_SalvarActionPerformed

    private void txt_vlrKMFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_vlrKMFocusLost
        txt_vlrKM.setText(Corretores.ConverterDecimalReais(txt_vlrKM.getText()));
    }//GEN-LAST:event_txt_vlrKMFocusLost

    private void txt_vlrTotalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_vlrTotalFocusLost
        txt_vlrTotal.setText(Corretores.ConverterDecimalReais(txt_vlrTotal.getText()));
    }//GEN-LAST:event_txt_vlrTotalFocusLost

    private void btn_rVlrKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_rVlrKMActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(null, "Deseja replicar este valor unitário a todas as Minutas?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION) {
            for (int i = 0; i < TbFreteTeste.getRowCount(); i++) {
                TbFreteTeste.setValueAt(new BigDecimal(Corretores.ConverterReaisDouble(txt_vlrKM.getText())), i, 13);
            }
            calcularTabelaVlrKM();
        }
    }//GEN-LAST:event_btn_rVlrKMActionPerformed

    private void btn_rVlrTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_rVlrTotalActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja replicar este valor total a todas as Minutas?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION) {
            for (int i = 0; i < TbFreteTeste.getRowCount(); i++) {
                TbFreteTeste.setValueAt(new BigDecimal(Corretores.ConverterReaisDouble(txt_vlrTotal.getText())), i, 14);
            }
            calcularTabelaVlrTotal();
        }
    }//GEN-LAST:event_btn_rVlrTotalActionPerformed

    private void txt_vlrSelecionadoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_vlrSelecionadoFocusLost

    }//GEN-LAST:event_txt_vlrSelecionadoFocusLost

    private void ch_marcarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ch_marcarTodosActionPerformed
        boolean valor;
        if (ch_marcarTodos.isSelected() == true) {
            valor = true;
        } else {
            valor = false;
        }
        for (int i = 0; i < TbFreteTeste.getRowCount(); i++) {
            TbFreteTeste.setValueAt(valor, i, 15);
        }
        somarTabela();

    }//GEN-LAST:event_ch_marcarTodosActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser fc = new JFileChooser();
        JTable RdiGrid = null;

        int option = fc.showSaveDialog(RdiGrid);
        if (option == JFileChooser.APPROVE_OPTION) {
            String filename = fc.getSelectedFile().getName();
            String path = fc.getSelectedFile().getParentFile().getPath();
            int len = filename.length();
            String ext = "";
            String file = "";
            if (len > 4) {
                ext = filename.substring(len - 4, len);
            }
            if (ext.equals(".xls")) {
                file = path + "\\" + filename;
            } else {
                file = path + "\\" + filename + ".xls";
            }

            try {
                toExcel(RdiGrid, new File(file));
            } catch (IOException ex) {
                Logger.getLogger(frmCompraGado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_gerarPagtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_gerarPagtoActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(null, "Deseja gerar um contas a pagar do frete?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION) {
            Pagamentos = new frmPagamentos();
            this.getParent().add(Pagamentos);
            Pagamentos.setVisible(true);
            FreteD.popularListaID(TbFreteTeste);
            Pagamentos.ch_novoPagto.setSelected(true);
            Pagamentos.ch_novoPagto.doClick();
            Pagamentos.txt_valorPagtoPrevisto.setText(txt_vlrSelecionado.getText());
            OrigemContasAPagar = "frmFreteGado"; // Mater está possição para não perder a instancia no Frm Pagamento
        }
    }//GEN-LAST:event_btn_gerarPagtoActionPerformed

    private void tb_fretePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tb_fretePropertyChange
        int colunaEditada = tb_frete.getSelectedColumn();
        switch (colunaEditada) {
            case 13: {
                BigDecimal vlrTotal;
                int i = tb_frete.getSelectedRow();
                vlrTotal = BigDecimal.valueOf(Double.parseDouble(TbFreteTeste.getValueAt(i, 13).toString()) * Double.parseDouble(TbFreteTeste.getValueAt(i, 12).toString()));
                tb_frete.setValueAt((vlrTotal), i, 14);
                break;
            }
            case 14: {
                int i = tb_frete.getSelectedRow();
                BigDecimal vlrTotal;
                if (Double.parseDouble(TbFreteTeste.getValueAt(i, 12).toString()) > 0) {
                    vlrTotal = BigDecimal.valueOf(Double.parseDouble(TbFreteTeste.getValueAt(i, 14).toString()) / Double.parseDouble(TbFreteTeste.getValueAt(i, 12).toString()));
                    tb_frete.setValueAt((vlrTotal), i, 13);
                }
                break;
            }
            case 15:
                somarTabela();
                break;
            default:
                break;
        }

    }//GEN-LAST:event_tb_fretePropertyChange

    private void tb_freteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_freteMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            if (jPopUp.isVisible() == true) {
                jPopUp.setVisible(false);
            } else {
                jPopUp.setVisible(true);
                jPopUp.show(this, 0, 0);
                jPopUp.setLocation(evt.getLocationOnScreen());
            }
        }
    }//GEN-LAST:event_tb_freteMouseClicked

    private void jMenu_romaneioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_romaneioActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja editar este Pedido?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION) {
            int linha = tb_frete.getSelectedRow();
            if (linha >= 0) {
                frmEntradaGado EntradaGado = new frmEntradaGado();
                this.getParent().add(EntradaGado);
                EntradaGado.setVisible(true);
                EntradaGado.rb_adic_romaneio_exist.setSelected(true);
                EntradaGado.btn_pesquisarNF.setEnabled(true);
                EntradaGado.txt_codigo_rom.setText(TbFreteTeste.getValueAt(linha, 1).toString());
                EntradaGado.cb_fazenda.setSelectedItem(TbFreteTeste.getValueAt(linha, 6).toString());
                EntradaGado.btn_pesquisarNF.doClick();
            }
        }
    }//GEN-LAST:event_jMenu_romaneioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btn_Salvar;
    javax.swing.JButton btn_gerarPagto;
    javax.swing.JButton btn_pesquisar1;
    javax.swing.JButton btn_rVlrKM;
    javax.swing.JButton btn_rVlrTotal;
    javax.swing.JComboBox<String> cb_destino;
    javax.swing.JComboBox<String> cb_origem;
    javax.swing.JComboBox<String> cb_statusPagto;
    javax.swing.JComboBox<String> cb_transp;
    javax.swing.JCheckBox ch_fretePago;
    javax.swing.JCheckBox ch_marcarTodos;
    javax.swing.JMenuItem jMenu_romaneio;
    javax.swing.JPopupMenu jPopUp;
    javax.swing.JTable tb_frete;
    com.toedter.calendar.JDateChooser txt_dtFinal;
    com.toedter.calendar.JDateChooser txt_dtInicial;
    javax.swing.JTextField txt_nCompra;
    javax.swing.JTextField txt_nMin;
    javax.swing.JTextField txt_placa;
    javax.swing.JTextField txt_vlrKM;
    javax.swing.JTextField txt_vlrSelecionado;
    javax.swing.JTextField txt_vlrTotal;
    // End of variables declaration//GEN-END:variables

    final void popularBeans() {
        try {
            FreteB.setDtInicial(new SimpleDateFormat("dd/MM/yyyy").format(txt_dtInicial.getDate()));
            FreteB.setDtFinal(new SimpleDateFormat("dd/MM/yyyy").format(txt_dtFinal.getDate()));
        } catch (Exception e) {
        }
        if (cb_origem.getSelectedItem().equals("-")) {
            FreteB.setOrigem("");
        } else {
            FreteB.setOrigem(cb_origem.getSelectedItem().toString());
        }
        if (cb_destino.getSelectedItem().equals("-")) {
            FreteB.setDestino("");
        } else {
            FreteB.setDestino(cb_destino.getSelectedItem().toString());
        }
        if (txt_placa.getText().equals("   -    ")) {
            FreteB.setPlaca("");
        } else {
            FreteB.setPlaca(txt_placa.getText());
        }
        if (cb_transp.getSelectedItem().equals("-")) {
            FreteB.setTransportadora("");
        } else {
            FreteB.setTransportadora(cb_transp.getSelectedItem().toString());
        }
        if (txt_nMin.getText().equals("")) {
            FreteB.setnMin("");
        } else {
            FreteB.setnMin(txt_nMin.getText());
        }

        if (txt_nCompra.getText().equals("")) {
            FreteB.setnCompra("");
        } else {
            FreteB.setnCompra(txt_nCompra.getText());
        }

        if (ch_fretePago.isSelected()) {
            FreteB.setStatusConferencia(1);
        } else {
            FreteB.setStatusConferencia(0);
        }
        if (cb_statusPagto.getSelectedItem().equals("-")) {
            FreteB.setStatusPagto("");
        } else {
            FreteB.setStatusPagto(cb_statusPagto.getSelectedItem().toString());
        }
    }

    private boolean verificarBeans() {

        return true;
    }

    final void calcularTabelaVlrKM() {
        BigDecimal vlrTotal = new BigDecimal("0.0");
        for (int i = 0; i < TbFreteTeste.getRowCount(); i++) {
            if (Integer.parseInt(TbFreteTeste.getValueAt(i, 12).toString()) != 0) {
                vlrTotal = ((BigDecimal) TbFreteTeste.getValueAt(i, 13)).multiply(new BigDecimal(TbFreteTeste.getValueAt(i, 12).toString()));
                TbFreteTeste.setValueAt(vlrTotal, i, 14);
            }
        }
    }

    final void calcularTabelaVlrTotal() {
        BigDecimal vlrTotal = new BigDecimal("0.0");
        for (int i = 0; i < TbFreteTeste.getRowCount(); i++) {
            if (Integer.parseInt(TbFreteTeste.getValueAt(i, 12).toString()) != 0) {
                vlrTotal = ((BigDecimal) TbFreteTeste.getValueAt(i, 14)).divide(new BigDecimal(TbFreteTeste.getValueAt(i, 12).toString()), 6, RoundingMode.HALF_UP);
                TbFreteTeste.setValueAt(vlrTotal, i, 13);
            }
        }
    }

    final void somarTabela() {
        BigDecimal valorT = new BigDecimal("0.0");
        for (int i = 0; i < TbFreteTeste.getRowCount(); i++) {
            if (Boolean.parseBoolean(TbFreteTeste.getValueAt(i, 15).toString()) == true) {
                valorT = valorT.add((BigDecimal) TbFreteTeste.getValueAt(i, 14));
            }
        }
        txt_vlrSelecionado.setText(new DecimalFormat("R$ #,###,##0.00").format(valorT));
    }

    public void toExcel(JTable table, File file) throws IOException {
        FreteTableModel model = TbFreteTeste;
        FileWriter excel = new FileWriter(file);

        for (int i = 0; i < model.getColumnCount(); i++) {
            if (i == 19 || i == 20 || i == 21) {
                excel.write("\t");
            } else {
                excel.write(model.getColumnName(i) + "\t");
            }
        }

        excel.write("\n");
        for (int i = 0; i < model.getRowCount(); i++) {
            for (int j = 0; j < model.getColumnCount(); j++) {
                excel.write(model.getValueAt(i, j).toString().replace("kg", "").replace("R$", "") + "\t");
            }
            excel.write("\n");
        }
        excel.close();

        //System.out.println("write out to: " + file); 
    }

    public void atualizarIDPagamento(PagamentosBeans pagto) {
        //System.out.println("Entrou Frem Gado");
        FreteD.gerarPagto(TbFreteTeste, pagto);
    }

}
