package GUI;

import Controller.EntradaGadoController;

import Utilitarios.Conexao;
import java.text.SimpleDateFormat;
import Beans.EntradaGadoBeans;
import Beans.CompradorGadoBeans;
import DAO.Diversas;
import DAO.DiversasHibernate;
import DAO.EntradaGadoDAO;
import static GUI.Principal.listCompradores;
import Icones.FormatarICO;
import Utilitarios.Corretores;
import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

public class frmEntradaGado extends javax.swing.JInternalFrame {

    EntradaGadoBeans EntradaGadoB;
    EntradaGadoController EntradaGadoC;
    EntradaGadoDAO EntradaGadoD;
    
    DefaultTableModel TbNF;
    
    DefaultTableModel TbGTA;
    DefaultTableModel TbPesquisa;
    MaskFormatter FormatoCPF;
    MaskFormatter FormatoPlaca;
    frmPesquisarCompra telaPesquisarCompra;
    Diversas DiversasD;

    public frmEntradaGado() {
        initComponents();
        EntradaGadoC = new EntradaGadoController();
        EntradaGadoB = new EntradaGadoBeans();
        EntradaGadoD = new EntradaGadoDAO();
        DiversasD = new Diversas();

        EntradaGadoC.controlePmFazendas();
        
        TbNF = (DefaultTableModel) tb_notasFiscais.getModel();
        
        TbGTA = (DefaultTableModel) tb_entradasGTA.getModel();
        TbPesquisa = (DefaultTableModel) tb_pesquisaRom.getModel();
        cb_transportadora.setModel(new DefaultComboBoxModel(new Vector(EntradaGadoD.buscarTransportadora())));
        ajustarTamanhoForm(TbPainel.getSelectedIndex());
        txt_pesqDataInicial.setDate(Corretores.PrimeiroDiaMes());
        txt_pesqDataFinal.setDate(Corretores.UltimoDiaMes());
        carregarCompradores();
    }

    private void carregarCompradores() {
        if (listCompradores == null) {
            listCompradores = DiversasHibernate.getListaCompradorgado();
        }
        CompradorGadoBeans b = new CompradorGadoBeans();
        b.setIdComprador(0);
        b.setComprador("-");
        cb_comprador.addItem(b);
        cb_pesqComprador.addItem(b);
        for (CompradorGadoBeans list : listCompradores) {
            cb_comprador.addItem(list);
            cb_pesqComprador.addItem(list);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        txt_codigo_rom = new javax.swing.JTextField();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        cb_fazenda = new javax.swing.JComboBox<>();
        cb_motivo = new javax.swing.JComboBox<>();
        cb_comprador = new javax.swing.JComboBox<>();
        cb_num_compra = new javax.swing.JComboBox<>();
        TbPainel = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        txt_produtor = new javax.swing.JTextField();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        txt_num_nf = new javax.swing.JTextField();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        txt_valor_nf = new javax.swing.JTextField();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        txt_ins_est = new javax.swing.JTextField();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        txt_quant_nf = new javax.swing.JTextField();
        txt_valor_cb = new javax.swing.JTextField();
        javax.swing.JLabel jLabel13 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel14 = new javax.swing.JLabel();
        try {

            FormatoCPF = new MaskFormatter("###.###.###-##");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar máscara", "Erro", 0);
        }
        txt_cpf = new JFormattedTextField(FormatoCPF);
        txt_fazendaOrigem = new javax.swing.JComboBox<>();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        tb_notasFiscais = new javax.swing.JTable();
        btn_SalvarNF = new javax.swing.JButton();
        btn_pesquisarNF = new javax.swing.JButton();
        btn_editarNF = new javax.swing.JButton();
        btn_excluirNF = new javax.swing.JButton();
        txt_dataNF = new com.toedter.calendar.JDateChooser();
        btn_relEntrada = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        javax.swing.JPanel jPanel4 = new javax.swing.JPanel();
        txt_q_f_total = new javax.swing.JTextField();
        txt_q_m_total = new javax.swing.JTextField();
        javax.swing.JLabel jLabel27 = new javax.swing.JLabel();
        cb_destinoGTA = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel26 = new javax.swing.JLabel();
        txt_q_m_m36 = new javax.swing.JTextField();
        txt_q_f_m36 = new javax.swing.JTextField();
        txt_q_f_36 = new javax.swing.JTextField();
        txt_q_f_24 = new javax.swing.JTextField();
        txt_q_f_12 = new javax.swing.JTextField();
        javax.swing.JLabel jLabel21 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel20 = new javax.swing.JLabel();
        txt_q_m_12 = new javax.swing.JTextField();
        txt_q_m_24 = new javax.swing.JTextField();
        txt_q_m_36 = new javax.swing.JTextField();
        javax.swing.JLabel jLabel24 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel23 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel22 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel19 = new javax.swing.JLabel();
        cb_tipo_trans = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel18 = new javax.swing.JLabel();
        txt_quant_gta = new javax.swing.JTextField();
        javax.swing.JLabel jLabel17 = new javax.swing.JLabel();
        txt_fazenda_origem_gta = new javax.swing.JTextField();
        javax.swing.JLabel jLabel16 = new javax.swing.JLabel();
        txt_num_gta = new javax.swing.JTextField();
        javax.swing.JLabel jLabel15 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel41 = new javax.swing.JLabel();
        txt_cidade_oriGta = new javax.swing.JTextField();
        javax.swing.JLabel jLabel42 = new javax.swing.JLabel();
        txt_data_gta = new com.toedter.calendar.JDateChooser();
        javax.swing.JPanel jPanel5 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel28 = new javax.swing.JLabel();
        try {

            FormatoPlaca = new MaskFormatter("AAA-####");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar máscara", "Erro", 0);
        }
        txt_placa = new JFormattedTextField(FormatoPlaca);
        javax.swing.JLabel jLabel29 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel30 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel31 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel32 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel33 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel34 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel35 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel36 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel37 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel38 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel39 = new javax.swing.JLabel();
        txt_tara = new javax.swing.JTextField();
        txt_peso_bruto = new javax.swing.JTextField();
        txt_peso_liq = new javax.swing.JTextField();
        txt_peso_medio = new javax.swing.JTextField();
        txt_num_minuta = new javax.swing.JTextField();
        txt_km = new javax.swing.JTextField();
        txt_quant_fisico = new javax.swing.JTextField();
        txt_refugos = new javax.swing.JTextField();
        cb_destinoFisico = new javax.swing.JComboBox<>();
        cb_categoria = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel40 = new javax.swing.JLabel();
        cb_transportadora = new javax.swing.JComboBox<>();
        txt_data_entrada_fisica = new com.toedter.calendar.JDateChooser();
        javax.swing.JLabel jLabel44 = new javax.swing.JLabel();
        txt_quant_fisicoOri = new javax.swing.JTextField();
        javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        tb_entradasGTA = new javax.swing.JTable();
        javax.swing.JButton btn_SalvarGTA = new javax.swing.JButton();
        javax.swing.JButton txt_pesquisarGTA = new javax.swing.JButton();
        btn_editarGTA = new javax.swing.JButton();
        btn_excluirGTA = new javax.swing.JButton();
        javax.swing.JPanel jPanel3 = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane3 = new javax.swing.JScrollPane();
        tb_pesquisaRom = new javax.swing.JTable();
        btn_pesqEntradas = new javax.swing.JButton();
        txt_pesqDataInicial = new com.toedter.calendar.JDateChooser();
        txt_pesqDataFinal = new com.toedter.calendar.JDateChooser();
        javax.swing.JLabel jLabel45 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel46 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel47 = new javax.swing.JLabel();
        txt_pesqProdutor = new javax.swing.JTextField();
        javax.swing.JLabel jLabel48 = new javax.swing.JLabel();
        cb_pesqMotivo = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel49 = new javax.swing.JLabel();
        cb_pesqComprador = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel50 = new javax.swing.JLabel();
        cb_pesqFazenda = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel51 = new javax.swing.JLabel();
        txt_pesqNcompra = new javax.swing.JTextField();
        btn_relEntradaRom = new javax.swing.JButton();
        javax.swing.JPanel jPanel6 = new javax.swing.JPanel();
        txt_SMIN = new javax.swing.JTextField();
        javax.swing.JLabel jLabel52 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel53 = new javax.swing.JLabel();
        txt_SGTA = new javax.swing.JTextField();
        javax.swing.JLabel jLabel54 = new javax.swing.JLabel();
        txt_pesqOrigem = new javax.swing.JTextField();
        rb_novo_rom = new javax.swing.JRadioButton();
        rb_adic_romaneio_exist = new javax.swing.JRadioButton();
        javax.swing.JLabel jLabel25 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel43 = new javax.swing.JLabel();
        txt_ID = new javax.swing.JTextField();
        btn_pesquisarCompra = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Entrada de Gado");
        setToolTipText("");
        setAutoscrolls(true);
        setPreferredSize(new java.awt.Dimension(1035, 645));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Romaneio");

        txt_codigo_rom.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_codigo_rom.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Fazenda");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Motivo");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Comprador");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Nº da Compra");

        cb_motivo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Compra", "Transf (+)", "Nascimento", "Era" }));
        cb_motivo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_motivoItemStateChanged(evt);
            }
        });

        cb_num_compra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0" }));

        TbPainel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        TbPainel.setAutoscrolls(true);
        TbPainel.setName("Notas Fiscais"); // NOI18N
        TbPainel.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                TbPainelStateChanged(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Notas Fiscais"));
        jPanel1.setAutoscrolls(true);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Data N.F");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Produtor");

        txt_num_nf.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Nº NF");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Quant. NF");

        txt_valor_nf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_valor_nfActionPerformed(evt);
            }
        });
        txt_valor_nf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_valor_nfKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Valor NF");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Fazenda Origem");

        txt_ins_est.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Ins. Est.");

        txt_quant_nf.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txt_valor_cb.setEditable(false);
        txt_valor_cb.setEnabled(false);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("R$/ Cab");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("CPF/CNPJ");

        txt_fazendaOrigem.setEditable(true);
        txt_fazendaOrigem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_fazendaOrigemActionPerformed(evt);
            }
        });

        tb_notasFiscais.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Romaneio", "Produtor", "CPF / CNPJ", "Fazenda Origem", "Ins. Est.", "Data N.F", "Nº NF", "Qt NF", "Valor NF", "R$/cab"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_notasFiscais.getTableHeader().setReorderingAllowed(false);
        tb_notasFiscais.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_notasFiscaisMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tb_notasFiscaisMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tb_notasFiscais);
        if (tb_notasFiscais.getColumnModel().getColumnCount() > 0) {
            tb_notasFiscais.getColumnModel().getColumn(0).setMinWidth(20);
            tb_notasFiscais.getColumnModel().getColumn(0).setPreferredWidth(30);
            tb_notasFiscais.getColumnModel().getColumn(0).setMaxWidth(60);
            tb_notasFiscais.getColumnModel().getColumn(1).setMinWidth(60);
            tb_notasFiscais.getColumnModel().getColumn(1).setPreferredWidth(60);
            tb_notasFiscais.getColumnModel().getColumn(1).setMaxWidth(60);
            tb_notasFiscais.getColumnModel().getColumn(2).setMinWidth(100);
            tb_notasFiscais.getColumnModel().getColumn(2).setPreferredWidth(150);
            tb_notasFiscais.getColumnModel().getColumn(2).setMaxWidth(200);
            tb_notasFiscais.getColumnModel().getColumn(3).setResizable(false);
            tb_notasFiscais.getColumnModel().getColumn(3).setPreferredWidth(120);
            tb_notasFiscais.getColumnModel().getColumn(4).setMinWidth(100);
            tb_notasFiscais.getColumnModel().getColumn(4).setPreferredWidth(150);
            tb_notasFiscais.getColumnModel().getColumn(4).setMaxWidth(200);
            tb_notasFiscais.getColumnModel().getColumn(5).setResizable(false);
            tb_notasFiscais.getColumnModel().getColumn(5).setPreferredWidth(100);
            tb_notasFiscais.getColumnModel().getColumn(6).setResizable(false);
            tb_notasFiscais.getColumnModel().getColumn(6).setPreferredWidth(70);
            tb_notasFiscais.getColumnModel().getColumn(7).setResizable(false);
            tb_notasFiscais.getColumnModel().getColumn(7).setPreferredWidth(50);
            tb_notasFiscais.getColumnModel().getColumn(8).setResizable(false);
            tb_notasFiscais.getColumnModel().getColumn(8).setPreferredWidth(55);
            tb_notasFiscais.getColumnModel().getColumn(9).setMinWidth(100);
            tb_notasFiscais.getColumnModel().getColumn(9).setPreferredWidth(100);
            tb_notasFiscais.getColumnModel().getColumn(9).setMaxWidth(150);
        }

        btn_SalvarNF.setBackground(new java.awt.Color(255, 255, 255));
        btn_SalvarNF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/salvar.png"))); // NOI18N
        btn_SalvarNF.setEnabled(false);
        btn_SalvarNF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalvarNFActionPerformed(evt);
            }
        });

        btn_pesquisarNF.setBackground(new java.awt.Color(255, 255, 255));
        btn_pesquisarNF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar.png"))); // NOI18N
        btn_pesquisarNF.setEnabled(false);
        btn_pesquisarNF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisarNFActionPerformed(evt);
            }
        });

        btn_editarNF.setBackground(new java.awt.Color(255, 255, 255));
        btn_editarNF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/editar.png"))); // NOI18N
        btn_editarNF.setEnabled(false);
        btn_editarNF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarNFActionPerformed(evt);
            }
        });

        btn_excluirNF.setBackground(new java.awt.Color(255, 255, 255));
        btn_excluirNF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/deletar.png"))); // NOI18N
        btn_excluirNF.setEnabled(false);
        btn_excluirNF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirNFActionPerformed(evt);
            }
        });

        btn_relEntrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/print.png"))); // NOI18N
        btn_relEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_relEntradaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(296, Short.MAX_VALUE)
                .addComponent(btn_SalvarNF, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_pesquisarNF, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btn_editarNF, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_excluirNF, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_relEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 296, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dataNF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_num_nf, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_produtor, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_quant_nf, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_valor_nf, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_cpf)))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_fazendaOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_valor_cb, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_ins_est, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txt_num_nf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_quant_nf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(txt_valor_nf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(txt_valor_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)
                        .addComponent(jLabel12)
                        .addComponent(txt_ins_est, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_dataNF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_produtor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel14)
                    .addComponent(txt_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_fazendaOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_excluirNF, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_editarNF, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesquisarNF, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_SalvarNF, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_relEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(136, Short.MAX_VALUE))
        );

        TbPainel.addTab("Notas Fiscais", jPanel1);

        jPanel2.setAutoscrolls(true);
        jPanel2.setVerifyInputWhenFocusTarget(false);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "GTA's")));
        jPanel4.setPreferredSize(new java.awt.Dimension(440, 252));

        txt_q_f_total.setEditable(false);
        txt_q_f_total.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_q_f_total.setText("0");

        txt_q_m_total.setEditable(false);
        txt_q_m_total.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_q_m_total.setText("0");

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Total Animais");
        jLabel27.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("+ 36 m");
        jLabel26.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_q_m_m36.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_q_m_m36.setText("0");
        txt_q_m_m36.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_q_m_36KeyReleased(evt);
            }
        });

        txt_q_f_m36.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_q_f_m36.setText("0");
        txt_q_f_m36.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_q_f_12KeyReleased(evt);
            }
        });

        txt_q_f_36.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_q_f_36.setText("0");
        txt_q_f_36.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_q_f_12KeyReleased(evt);
            }
        });

        txt_q_f_24.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_q_f_24.setText("0");
        txt_q_f_24.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_q_f_12KeyReleased(evt);
            }
        });

        txt_q_f_12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_q_f_12.setText("0");
        txt_q_f_12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_q_f_12KeyReleased(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Fêmeas");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Machos");

        txt_q_m_12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_q_m_12.setText("0");
        txt_q_m_12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_q_m_36KeyReleased(evt);
            }
        });

        txt_q_m_24.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_q_m_24.setText("0");
        txt_q_m_24.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_q_m_36KeyReleased(evt);
            }
        });

        txt_q_m_36.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_q_m_36.setText("0");
        txt_q_m_36.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_q_m_36KeyReleased(evt);
            }
        });

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("25 a 36");
        jLabel24.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("13 a 24 m");
        jLabel23.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Até 12 m");
        jLabel22.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Tipo Trans.");

        cb_tipo_trans.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Rodoviário", "Tangido" }));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Destino GTA");

        txt_quant_gta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_quant_gta.setText("0");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Quant. GTA");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Faz. Origem");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Nº GTA");

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel41.setText("Municipio");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel42.setText("Data GTA");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txt_q_f_12, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txt_q_f_24, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_q_m_12)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_q_m_24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_q_f_36, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(txt_q_m_36, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_q_f_m36, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addComponent(txt_q_f_total))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txt_q_m_m36, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                            .addComponent(txt_q_m_total))))
                .addContainerGap(71, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_num_gta, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_data_gta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel17)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_tipo_trans, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_fazenda_origem_gta, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_quant_gta, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel41)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_destinoGTA, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_cidade_oriGta))))
                .addGap(22, 22, 22))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_data_gta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txt_num_gta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txt_fazenda_origem_gta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41)
                    .addComponent(txt_cidade_oriGta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(cb_destinoGTA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(cb_tipo_trans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_quant_gta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27))
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txt_q_m_12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_q_m_24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_q_m_36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_q_m_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_q_m_m36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txt_q_f_12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_q_f_24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_q_f_36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_q_f_m36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_q_f_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Minutas"));
        jPanel5.setPreferredSize(new java.awt.Dimension(440, 252));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setText("Placa");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setText("Tara");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel30.setText("Peso Bruto");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel31.setText("Peso Líquido");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel32.setText("Peso Médio");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel33.setText("Nº Minuta");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel34.setText("Quilometros");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel35.setText("Qt Cabeças Destino");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel36.setText("Destino Físico");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel37.setText("Categoria ");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel38.setText("Nº Refugos");

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel39.setText("Data Entrada Física");

        txt_tara.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_tara.setText("0");
        txt_tara.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_taraKeyReleased(evt);
            }
        });

        txt_peso_bruto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_peso_bruto.setText("0");
        txt_peso_bruto.setToolTipText("");
        txt_peso_bruto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_taraKeyReleased(evt);
            }
        });

        txt_peso_liq.setEditable(false);
        txt_peso_liq.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_peso_liq.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txt_peso_medio.setEditable(false);
        txt_peso_medio.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_peso_medio.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txt_quant_fisico.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_quant_fisico.setText("0");
        txt_quant_fisico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_taraKeyReleased(evt);
            }
        });

        txt_refugos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_refugos.setText("0");

        cb_categoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Touros", "Vaca Parida", "Vaca Descarte", "Vaca Solteira", "Vacas", "Novilhas", "Bezerras", "Bezerros", "Garrotes", "Bois Magros", "Bois", " " }));

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel40.setText("Transportadora");

        cb_transportadora.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel44.setText("Qt Cabeças Origem");

        txt_quant_fisicoOri.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_quant_fisicoOri.setText("0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40)
                            .addComponent(jLabel36))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_transportadora, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(cb_destinoFisico, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel37)
                                .addGap(18, 18, 18)
                                .addComponent(cb_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_quant_fisico, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel39)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_data_entrada_fisica, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel34))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel33)
                                        .addGap(3, 3, 3)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel28)
                                            .addComponent(jLabel31))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_peso_liq, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_placa, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel29)
                                        .addGap(35, 35, 35))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel38)
                                        .addGap(11, 11, 11)))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_refugos, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_tara, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel30)
                                    .addComponent(jLabel32)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_quant_fisicoOri, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_km, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_num_minuta, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_peso_medio, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_peso_bruto, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(txt_placa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(txt_tara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(txt_peso_bruto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txt_peso_liq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(txt_peso_medio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38)
                    .addComponent(txt_refugos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(txt_quant_fisico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44)
                    .addComponent(txt_quant_fisicoOri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(cb_destinoFisico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37)
                    .addComponent(cb_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(cb_transportadora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33)
                    .addComponent(txt_num_minuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txt_data_entrada_fisica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel34)
                        .addComponent(txt_km, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tb_entradasGTA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nº GTA", "Dt GTA", "Faz. Origem", "Origem", "Trans.", "Dest. GTA", "Q.GTA", "M12", "M24", "M36", "M+36", "M GTA", "F+36", "F36", "F24", "F12", "F GTA", "Placa", "Tara", "Peso B.", "Peso L.", "Qt Destino", "Qt Origem", "Peso M", "Nº Ref.", "Destin Fisico", "Data Ent.", "Categoria", "Transp.", "Nº Min", "KM", "Romaneio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_entradasGTA.getTableHeader().setReorderingAllowed(false);
        tb_entradasGTA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_entradasGTAMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tb_entradasGTAMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tb_entradasGTA);
        if (tb_entradasGTA.getColumnModel().getColumnCount() > 0) {
            tb_entradasGTA.getColumnModel().getColumn(0).setResizable(false);
            tb_entradasGTA.getColumnModel().getColumn(0).setPreferredWidth(40);
            tb_entradasGTA.getColumnModel().getColumn(2).setMinWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(2).setPreferredWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(2).setMaxWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(5).setMinWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(5).setPreferredWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(5).setMaxWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(7).setMinWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(7).setPreferredWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(7).setMaxWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(8).setMinWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(8).setPreferredWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(8).setMaxWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(9).setMinWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(9).setPreferredWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(9).setMaxWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(10).setMinWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(10).setPreferredWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(10).setMaxWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(11).setMinWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(11).setPreferredWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(11).setMaxWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(12).setMinWidth(30);
            tb_entradasGTA.getColumnModel().getColumn(12).setPreferredWidth(30);
            tb_entradasGTA.getColumnModel().getColumn(13).setMinWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(13).setPreferredWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(13).setMaxWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(14).setMinWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(14).setPreferredWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(14).setMaxWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(15).setMinWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(15).setPreferredWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(15).setMaxWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(16).setMinWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(16).setPreferredWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(16).setMaxWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(17).setMinWidth(30);
            tb_entradasGTA.getColumnModel().getColumn(17).setPreferredWidth(30);
            tb_entradasGTA.getColumnModel().getColumn(19).setMinWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(19).setPreferredWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(19).setMaxWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(20).setMinWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(20).setPreferredWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(20).setMaxWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(21).setMinWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(21).setPreferredWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(21).setMaxWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(25).setMinWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(25).setPreferredWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(25).setMaxWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(28).setMinWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(28).setPreferredWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(28).setMaxWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(32).setMinWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(32).setPreferredWidth(0);
            tb_entradasGTA.getColumnModel().getColumn(32).setMaxWidth(0);
        }

        btn_SalvarGTA.setBackground(new java.awt.Color(255, 255, 255));
        btn_SalvarGTA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/salvar.png"))); // NOI18N
        btn_SalvarGTA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalvarGTAActionPerformed(evt);
            }
        });

        txt_pesquisarGTA.setBackground(new java.awt.Color(255, 255, 255));
        txt_pesquisarGTA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar.png"))); // NOI18N
        txt_pesquisarGTA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_pesquisarGTAActionPerformed(evt);
            }
        });

        btn_editarGTA.setBackground(new java.awt.Color(255, 255, 255));
        btn_editarGTA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/editar.png"))); // NOI18N
        btn_editarGTA.setEnabled(false);
        btn_editarGTA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarGTAActionPerformed(evt);
            }
        });

        btn_excluirGTA.setBackground(new java.awt.Color(255, 255, 255));
        btn_excluirGTA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/deletar.png"))); // NOI18N
        btn_excluirGTA.setEnabled(false);
        btn_excluirGTA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirGTAActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(332, 332, 332)
                        .addComponent(btn_SalvarGTA, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_pesquisarGTA, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btn_editarGTA, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_excluirGTA, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_excluirGTA, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_editarGTA, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_pesquisarGTA, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_SalvarGTA, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TbPainel.addTab("GTA's", jPanel2);

        jScrollPane3.setPreferredSize(new java.awt.Dimension(452, 200));

        tb_pesquisaRom.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tb_pesquisaRom.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Romaneio", "Destino", "Origem", "Data Entrada", "Motivo", "Nº Compra", "Comprador", "Produtor", "Valor Total NF", "Valor Médio NF", "QNF", "QGTA", "QMin"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_pesquisaRom.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane3.setViewportView(tb_pesquisaRom);
        if (tb_pesquisaRom.getColumnModel().getColumnCount() > 0) {
            tb_pesquisaRom.getColumnModel().getColumn(0).setMinWidth(50);
            tb_pesquisaRom.getColumnModel().getColumn(0).setPreferredWidth(50);
            tb_pesquisaRom.getColumnModel().getColumn(0).setMaxWidth(70);
            tb_pesquisaRom.getColumnModel().getColumn(1).setMinWidth(90);
            tb_pesquisaRom.getColumnModel().getColumn(1).setPreferredWidth(90);
            tb_pesquisaRom.getColumnModel().getColumn(1).setMaxWidth(120);
            tb_pesquisaRom.getColumnModel().getColumn(3).setMinWidth(90);
            tb_pesquisaRom.getColumnModel().getColumn(3).setPreferredWidth(90);
            tb_pesquisaRom.getColumnModel().getColumn(3).setMaxWidth(90);
            tb_pesquisaRom.getColumnModel().getColumn(4).setMinWidth(70);
            tb_pesquisaRom.getColumnModel().getColumn(4).setPreferredWidth(70);
            tb_pesquisaRom.getColumnModel().getColumn(4).setMaxWidth(90);
            tb_pesquisaRom.getColumnModel().getColumn(5).setMinWidth(70);
            tb_pesquisaRom.getColumnModel().getColumn(5).setPreferredWidth(70);
            tb_pesquisaRom.getColumnModel().getColumn(5).setMaxWidth(90);
            tb_pesquisaRom.getColumnModel().getColumn(6).setMinWidth(90);
            tb_pesquisaRom.getColumnModel().getColumn(6).setPreferredWidth(90);
            tb_pesquisaRom.getColumnModel().getColumn(6).setMaxWidth(120);
            tb_pesquisaRom.getColumnModel().getColumn(7).setMinWidth(130);
            tb_pesquisaRom.getColumnModel().getColumn(7).setPreferredWidth(150);
            tb_pesquisaRom.getColumnModel().getColumn(7).setMaxWidth(180);
            tb_pesquisaRom.getColumnModel().getColumn(8).setMinWidth(90);
            tb_pesquisaRom.getColumnModel().getColumn(8).setPreferredWidth(120);
            tb_pesquisaRom.getColumnModel().getColumn(8).setMaxWidth(150);
            tb_pesquisaRom.getColumnModel().getColumn(9).setMinWidth(120);
            tb_pesquisaRom.getColumnModel().getColumn(9).setPreferredWidth(90);
            tb_pesquisaRom.getColumnModel().getColumn(9).setMaxWidth(70);
            tb_pesquisaRom.getColumnModel().getColumn(10).setResizable(false);
            tb_pesquisaRom.getColumnModel().getColumn(10).setPreferredWidth(50);
            tb_pesquisaRom.getColumnModel().getColumn(11).setPreferredWidth(50);
            tb_pesquisaRom.getColumnModel().getColumn(12).setPreferredWidth(50);
        }

        btn_pesqEntradas.setBackground(new java.awt.Color(255, 255, 255));
        btn_pesqEntradas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar.png"))); // NOI18N
        btn_pesqEntradas.setOpaque(false);
        btn_pesqEntradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesqEntradasActionPerformed(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel45.setText("Data Inicial");

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel46.setText("Data Final");

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel47.setText("Produtor");

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel48.setText("Motivo");

        cb_pesqMotivo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Compra", "Transf (+)", "Nascimento", "Era", " " }));

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel49.setText("Comprador");

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel50.setText("Destino");

        cb_pesqFazenda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel51.setText("Nº Compra");

        btn_relEntradaRom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/print.png"))); // NOI18N
        btn_relEntradaRom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_relEntradaRomActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Total"));
        jPanel6.setName(""); // NOI18N
        jPanel6.setPreferredSize(new java.awt.Dimension(100, 50));

        txt_SMIN.setEditable(false);
        txt_SMIN.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel52.setText("Q Min");

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel53.setText("Q GTA");

        txt_SGTA.setEditable(false);
        txt_SGTA.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel53)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_SGTA, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel52)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_SMIN, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_SGTA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53)
                    .addComponent(jLabel52)
                    .addComponent(txt_SMIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel54.setText("Origem");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel45)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_pesqDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_pesqDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_pesqProdutor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_pesqMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_pesqOrigem)
                            .addComponent(cb_pesqComprador, 0, 125, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_pesqNcompra, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_pesqFazenda, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 46, Short.MAX_VALUE)
                        .addComponent(btn_relEntradaRom, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btn_pesqEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 990, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_pesqEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_pesqDataInicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_pesqProdutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_pesqDataFinal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel46, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel48)
                                        .addComponent(cb_pesqMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel49)
                                        .addComponent(cb_pesqComprador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel50)
                                    .addComponent(cb_pesqFazenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_pesqOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel54))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel51)
                                    .addComponent(txt_pesqNcompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(btn_relEntradaRom, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        TbPainel.addTab("Relatório de Entradas", jPanel3);

        buttonGroup1.add(rb_novo_rom);
        rb_novo_rom.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rb_novo_rom.setText("Novo Romaneio");
        rb_novo_rom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_novo_romActionPerformed(evt);
            }
        });

        buttonGroup1.add(rb_adic_romaneio_exist);
        rb_adic_romaneio_exist.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rb_adic_romaneio_exist.setText("Adicionar Entrada em Romaneio");
        rb_adic_romaneio_exist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_adic_romaneio_existActionPerformed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel43.setText("ID");

        txt_ID.setEditable(false);
        txt_ID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_ID.setEnabled(false);

        btn_pesquisarCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar pequeno.png"))); // NOI18N
        btn_pesquisarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisarCompraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(cb_motivo, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_comprador, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_num_compra, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_codigo_rom, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel25)
                        .addGap(50, 50, 50)
                        .addComponent(rb_novo_rom)
                        .addGap(75, 75, 75)
                        .addComponent(rb_adic_romaneio_exist)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_pesquisarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TbPainel)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txt_codigo_rom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rb_novo_rom)
                        .addComponent(rb_adic_romaneio_exist)
                        .addComponent(jLabel25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel43)
                        .addComponent(txt_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_pesquisarCompra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(cb_num_compra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(cb_motivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(cb_comprador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TbPainel)
                .addContainerGap())
        );

        TbPainel.getAccessibleContext().setAccessibleName("GTA's");

        setBounds(0, 0, 1035, 636);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_SalvarGTAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalvarGTAActionPerformed
        int salvar = JOptionPane.showConfirmDialog(null, "Deseja salvar este GTA e Minuta?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (salvar == JOptionPane.YES_OPTION & VerificarNcompra()) {
            popularBeansGTA();
            EntradaGadoB.setNumCompra(cb_num_compra.getSelectedItem().toString());
            if (EntradaGadoC.controleInserirGTA(EntradaGadoB) == true) {
                EntradaGadoC.controlePreencherTabelaGTA(EntradaGadoB, TbGTA);
                limparCamposGTA();
            }
        }
    }//GEN-LAST:event_btn_SalvarGTAActionPerformed

    private void txt_pesquisarGTAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_pesquisarGTAActionPerformed
        EntradaGadoB.setCodigoROM(Integer.parseInt(txt_codigo_rom.getText()));
        EntradaGadoB.setFazendaEnt(cb_fazenda.getSelectedItem().toString());

        if (EntradaGadoC.controleBuscarRomaneio(EntradaGadoB, TbNF) == true) {
            EntradaGadoC.controlePreencherTabelaGTA(EntradaGadoB, TbGTA);
            cb_motivo.setSelectedItem(EntradaGadoB.getMotivo());

            if (cb_motivo.getSelectedItem() == "Compra") {
                cb_comprador.setSelectedItem(EntradaGadoB.getComprador());
                cb_num_compra.setSelectedItem(EntradaGadoB.getNumCompra());
            }
        }
        buttonGroup1.clearSelection();
        btn_pesquisarNF.setEnabled(false);
    }//GEN-LAST:event_txt_pesquisarGTAActionPerformed

    private void btn_editarGTAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarGTAActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja editar este GTA e Minuta?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION & VerificarNcompra()) {
            popularBeansGTA();
            EntradaGadoB.setID(Integer.parseInt(txt_ID.getText()));
            EntradaGadoB.setNumCompra(cb_num_compra.getSelectedItem().toString());
            EntradaGadoB.setCodigoROM(Integer.parseInt(txt_codigo_rom.getText()));
            EntradaGadoC.controleEditarGTA(EntradaGadoB);
            EntradaGadoC.controlePreencherTabelaGTA(EntradaGadoB, TbGTA);
            btn_editarGTA.setEnabled(false);
            btn_excluirGTA.setEnabled(false);
            limparCamposGTA();
        }
    }//GEN-LAST:event_btn_editarGTAActionPerformed

    private void btn_excluirGTAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirGTAActionPerformed
        int excluir = JOptionPane.showConfirmDialog(null, "Deseja excluir este GTA e Minuta? Os dados serão perdidos permanentemente!", "Atenção", JOptionPane.YES_NO_OPTION);

        if (excluir == JOptionPane.YES_OPTION) {
            popularBeansGTA();
            EntradaGadoB.setID(Integer.parseInt(txt_ID.getText()));
            EntradaGadoB.setCodigoROM(Integer.parseInt(txt_codigo_rom.getText()));
            EntradaGadoC.controleDeletarGTA(EntradaGadoB, TbGTA);
            btn_editarGTA.setEnabled(false);
            btn_excluirGTA.setEnabled(false);
            limparCamposGTA();
        }
    }//GEN-LAST:event_btn_excluirGTAActionPerformed

    private void btn_SalvarNFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalvarNFActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(null, "Deseja salvar esta Nota Fiscal?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION & VerificarNcompra()) {
            polularBeansNF();
            boolean retorno = EntradaGadoC.controleInserirNF(EntradaGadoB, TbNF);
            EntradaGadoC.controleBuscarRomaneio(EntradaGadoB, TbNF);
            if (retorno == true) {
                rb_adic_romaneio_exist.setSelected(true);

                int novoRegistro = JOptionPane.showConfirmDialog(null, "Deseja salvar outra Nota Fiscal neste Romaneio?", "Atenção", JOptionPane.YES_NO_OPTION);
                if (novoRegistro == JOptionPane.YES_OPTION) {
                    limparCamposNF();

                } else {
                    int continuarPreenchimento = JOptionPane.showConfirmDialog(null, "Deseja prosseguir o preenchimento deste Romaneio?", "Atenção", JOptionPane.YES_NO_OPTION);
                    if (continuarPreenchimento == JOptionPane.YES_OPTION) {
                        TbPainel.setSelectedIndex(1);
                        limparCamposNF();
                    } else {
                        limparCamposNF();
                    }
                }
            }
        }
    }//GEN-LAST:event_btn_SalvarNFActionPerformed

    private void btn_pesquisarNFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisarNFActionPerformed

        EntradaGadoB.setCodigoROM(Integer.parseInt(txt_codigo_rom.getText()));
        EntradaGadoB.setFazendaEnt(cb_fazenda.getSelectedItem().toString());

        if (EntradaGadoC.controleBuscarRomaneio(EntradaGadoB, TbNF) == true) {
            EntradaGadoC.controlePreencherTabelaGTA(EntradaGadoB, TbGTA);
            cb_motivo.setSelectedItem(EntradaGadoB.getMotivo());

            if (cb_motivo.getSelectedItem() == "Compra") {
                cb_comprador.setSelectedItem(EntradaGadoB.getComprador());

                // cb_num_compra.addItem(EntradaGadoB.getNumCompra());
                cb_num_compra.setEditable(true);
                cb_num_compra.setSelectedItem(EntradaGadoB.getNumCompra());
                cb_num_compra.setEditable(false);
            }
            btn_SalvarNF.setEnabled(true);
        } else if (cb_motivo.getSelectedItem() != "-") {
            btn_SalvarNF.setEnabled(true);
        }

    }//GEN-LAST:event_btn_pesquisarNFActionPerformed

    private void btn_editarNFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarNFActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja editar esta Nota Fiscal?", "Atenção", JOptionPane.YES_NO_OPTION);

        if (editar == JOptionPane.YES_OPTION & VerificarNcompra()) {
            polularBeansNF();
            EntradaGadoB.setID(Integer.parseInt(txt_ID.getText()));
            EntradaGadoC.controleEditarNF(EntradaGadoB, TbNF);
            btn_editarNF.setEnabled(false);
        }

    }//GEN-LAST:event_btn_editarNFActionPerformed

    private void btn_excluirNFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirNFActionPerformed
        int excluir = JOptionPane.showConfirmDialog(null, "Deseja excluir esta Nota Fiscal? Os dados serão perdidos permanentemente!", "Atenção", JOptionPane.YES_NO_OPTION);

        if (excluir == JOptionPane.YES_OPTION) {
            EntradaGadoB.setID(Integer.parseInt(txt_ID.getText()));
            EntradaGadoC.controleDeletarNF(EntradaGadoB, TbNF);
            btn_editarNF.setEnabled(false);
            btn_excluirNF.setEnabled(false);
            limparCamposNF();
        }
    }//GEN-LAST:event_btn_excluirNFActionPerformed

    private void rb_novo_romActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_novo_romActionPerformed
        limparCamposNF();
        limparCamposGTA();
        TbNF.setNumRows(0);
        TbGTA.setNumRows(0);
        txt_codigo_rom.setEditable(false);
        txt_codigo_rom.setEnabled(false);
        Integer Ultimo = null;
        txt_codigo_rom.setText(EntradaGadoC.controleBuscarUltimo().toString());
        btn_SalvarNF.setEnabled(true);
        btn_pesquisarNF.setEnabled(false);

    }//GEN-LAST:event_rb_novo_romActionPerformed

    private void rb_adic_romaneio_existActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_adic_romaneio_existActionPerformed
        limparCamposNF();
        txt_codigo_rom.setEditable(true);
        txt_codigo_rom.setEnabled(true);
        txt_codigo_rom.setText("");
        btn_pesquisarNF.setEnabled(true);
        btn_SalvarNF.setEnabled(false);
    }//GEN-LAST:event_rb_adic_romaneio_existActionPerformed

    private void txt_valor_nfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_valor_nfKeyReleased

        try {
            double ValorNF = (Corretores.ConverterReaisDouble(txt_valor_nf.getText()));
            double QuantNF = Double.parseDouble(txt_quant_nf.getText());
            double Valor = ValorNF / QuantNF;
            String ValorM = new DecimalFormat("#,##0.00").format(Valor);
            txt_valor_cb.setText(ValorM);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txt_valor_nfKeyReleased

    private void cb_motivoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_motivoItemStateChanged
        if (cb_motivo.getSelectedItem() == "Transf (+)") {
            txt_produtor.setText("Alceu Elias Feldmann");
            txt_cpf.setText("01989910963");
            txt_fazendaOrigem.setModel(cb_destinoGTA.getModel());
            txt_fazendaOrigem.setEditable(false);
        } else {
            txt_produtor.setText("");
            txt_cpf.setText("");
            txt_fazendaOrigem.setEditable(true);
            txt_fazendaOrigem.setEditable(true);
        }
    }//GEN-LAST:event_cb_motivoItemStateChanged

    private void tb_notasFiscaisMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_notasFiscaisMousePressed

        txt_produtor.setText(TbNF.getValueAt(tb_notasFiscais.getSelectedRow(), 2).toString());
        txt_cpf.setText(TbNF.getValueAt(tb_notasFiscais.getSelectedRow(), 3).toString());
        txt_fazendaOrigem.setSelectedItem(TbNF.getValueAt(tb_notasFiscais.getSelectedRow(), 4).toString());
        txt_ins_est.setText(TbNF.getValueAt(tb_notasFiscais.getSelectedRow(), 5).toString());
        txt_num_nf.setText(TbNF.getValueAt(tb_notasFiscais.getSelectedRow(), 7).toString());
        txt_quant_nf.setText(TbNF.getValueAt(tb_notasFiscais.getSelectedRow(), 8).toString());
        txt_valor_nf.setText(TbNF.getValueAt(tb_notasFiscais.getSelectedRow(), 9).toString());
        txt_valor_cb.setText(TbNF.getValueAt(tb_notasFiscais.getSelectedRow(), 10).toString());
        txt_ID.setText(TbNF.getValueAt(tb_notasFiscais.getSelectedRow(), 0).toString());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dte = null;
        try {
            dte = sdf.parse(TbNF.getValueAt(tb_notasFiscais.getSelectedRow(), 6).toString());
            txt_dataNF.setDate(dte);
        } catch (ParseException ex) {
            Logger.getLogger(frmEntradaGado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tb_notasFiscaisMousePressed

    private void tb_notasFiscaisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_notasFiscaisMouseClicked
        if (evt.getClickCount() == 2) {
            int editar = JOptionPane.showConfirmDialog(null, "Deseja editar esta Nota Fiscal?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (editar == JOptionPane.YES_OPTION) {
                btn_editarNF.setEnabled(true);
                btn_excluirNF.setEnabled(true);
            } else {
                btn_editarNF.setEnabled(false);
            }
        }
    }//GEN-LAST:event_tb_notasFiscaisMouseClicked

    private void txt_q_m_36KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_q_m_36KeyReleased
        somarGTAm();
    }//GEN-LAST:event_txt_q_m_36KeyReleased

    private void txt_q_f_12KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_q_f_12KeyReleased
        somarGTAf();
    }//GEN-LAST:event_txt_q_f_12KeyReleased

    private void txt_taraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_taraKeyReleased
        calcularPesoM();
    }//GEN-LAST:event_txt_taraKeyReleased

    private void tb_entradasGTAMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_entradasGTAMousePressed
        preencherCamposGTA();
    }//GEN-LAST:event_tb_entradasGTAMousePressed

    private void tb_entradasGTAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_entradasGTAMouseClicked
        if (evt.getClickCount() == 2) {
            int editar = JOptionPane.showConfirmDialog(null, "Deseja editar os dados do GTA e da Minuta?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (editar == JOptionPane.YES_OPTION) {
                btn_editarGTA.setEnabled(true);
                btn_excluirGTA.setEnabled(true);
            } else {
                btn_editarGTA.setEnabled(false);
                btn_excluirGTA.setEnabled(false);
            }
        }
    }//GEN-LAST:event_tb_entradasGTAMouseClicked

    private void btn_relEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_relEntradaActionPerformed
        int confirma = JOptionPane.showConfirmDialog(null, "Deseja visualizar o relatório de entrada?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            Map p = new HashMap();
            JasperReport relatorio;
            JasperPrint impressao;

            try {
                if (!txt_codigo_rom.getText().equals("")) {
                    p.put("codRom", Integer.parseInt(txt_codigo_rom.getText()));
                    p.put("SUBREPORT_DIR", getClass().getResource("/Relatorios/").toString());
                    relatorio = JasperCompileManager.compileReport(new File("").getClass().getResourceAsStream("/Relatorios/Romaneio.jrxml"));
                    impressao = JasperFillManager.fillReport(relatorio, p, Conexao.getConnection());
                    // String caminho = (new File("").getAbsolutePath() + "\\relatorios\\" + ("RomaneioEntrada" + txt_codigo_rom.getText() + ".pdf")); 
                    String caminho = (System.getProperty("user.home") + ("\\RomaneioEntrada" + txt_codigo_rom.getText() + ".pdf"));
                    // System.out.println(caminho);
                    JasperExportManager.exportReportToPdfFile(impressao, caminho);
                    Runtime.getRuntime().exec("cmd /c start " + caminho);
                    File file = new File(caminho);
                    file.deleteOnExit();

                    int enviar = JOptionPane.showConfirmDialog(null, "Deseja enviar este relatório por e-mail?", "Atenção", JOptionPane.YES_NO_OPTION);
                    if (enviar == JOptionPane.YES_OPTION) {
                        try {
                            // Cria a mensagem de e-mail
                            MultiPartEmail email = new MultiPartEmail();
                            email.setDebug(false);
                            email.setHostName("smtp.aefvilhena.com");
                            email.setSmtpPort(587);
                            email.setAuthentication("brunoalmeida=aefvilhena.com", "niv89dy");
                            email.addTo("brunoalmeida@aefvilhena.com");
                            email.addTo("celso@aefvilhena.com");
                            email.addTo("geni@aefvilhena.com");
                            email.setFrom("brunoalmeida@aefvilhena.com");
                            email.setSubject("Romaneio de Entrada de Gado");
                            email.setMsg("Segue o Romaneio de Entrada");
                            email.attach(file);
                            email.send();
                            JOptionPane.showMessageDialog(null, "E-mail enviado com sucesso!", "Executado", 1, new ImageIcon(getClass().getResource("/Icones/ok.png")));
                        } catch (EmailException e) {
                            JOptionPane.showMessageDialog(null, "Erro ao Enviar E-mail! " + e, "Erro", 0, FormatarICO.ICObtnSair());
                        }

                    }
                    //JasperViewer view = new JasperViewer(impressao, false);
                    //view.setTitle("Relatório de Entrada de Gado");
                    //view.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Verifique o número do romaneio de entrada!!");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_btn_relEntradaActionPerformed

    private void btn_pesquisarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisarCompraActionPerformed
        telaPesquisarCompra = new frmPesquisarCompra(null, true);
        telaPesquisarCompra.setVisible(true);

        cb_num_compra.setEditable(true);
        cb_motivo.setSelectedItem("Compra");
        cb_num_compra.setSelectedItem(frmPesquisarCompra.frmPesquisaCompraNCompra);
        setComboBoxComprador(cb_comprador,frmPesquisarCompra.frmPesquisaCompraComprador);
        txt_produtor.setText(frmPesquisarCompra.frmPesquisaCompraFornecedor);
        txt_cpf.setText(frmPesquisarCompra.frmPesquisaCompraCPF);

        frmPesquisarCompra.frmPesquisaCompraNCompra = "";
        frmPesquisarCompra.frmPesquisaCompraComprador = "";
        frmPesquisarCompra.frmPesquisaCompraFornecedor = "";
        frmPesquisarCompra.frmPesquisaCompraCPF = "";
        cb_num_compra.setEditable(false);

    }//GEN-LAST:event_btn_pesquisarCompraActionPerformed

    private void txt_fazendaOrigemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_fazendaOrigemActionPerformed

    }//GEN-LAST:event_txt_fazendaOrigemActionPerformed

    private void btn_pesqEntradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesqEntradasActionPerformed
        popularBeansPesquisa();
        EntradaGadoC.controlePesquisarRom(EntradaGadoB, TbPesquisa);
        totalizarTabela();
    }//GEN-LAST:event_btn_pesqEntradasActionPerformed

    private void txt_valor_nfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_valor_nfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_valor_nfActionPerformed

    private void btn_relEntradaRomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_relEntradaRomActionPerformed
        try {
            int nr = Integer.parseInt(TbPesquisa.getValueAt(tb_pesquisaRom.getSelectedRow(), 0).toString());
            ImprimiRelatorioEntrada(nr);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Selecione um Romaneio de Entrada para gerar o Relatório!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }//GEN-LAST:event_btn_relEntradaRomActionPerformed

    private void TbPainelStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_TbPainelStateChanged
        ajustarTamanhoForm(TbPainel.getSelectedIndex());
    }//GEN-LAST:event_TbPainelStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JTabbedPane TbPainel;
    javax.swing.JButton btn_SalvarNF;
    javax.swing.JButton btn_editarGTA;
    javax.swing.JButton btn_editarNF;
    javax.swing.JButton btn_excluirGTA;
    javax.swing.JButton btn_excluirNF;
    javax.swing.JButton btn_pesqEntradas;
    javax.swing.JButton btn_pesquisarCompra;
    javax.swing.JButton btn_pesquisarNF;
    javax.swing.JButton btn_relEntrada;
    javax.swing.JButton btn_relEntradaRom;
    javax.swing.ButtonGroup buttonGroup1;
    javax.swing.JComboBox<String> cb_categoria;
    javax.swing.JComboBox<CompradorGadoBeans> cb_comprador;
    public static javax.swing.JComboBox<String> cb_destinoFisico;
    public static javax.swing.JComboBox<String> cb_destinoGTA;
    public static javax.swing.JComboBox<String> cb_fazenda;
    javax.swing.JComboBox<String> cb_motivo;
    javax.swing.JComboBox<String> cb_num_compra;
    javax.swing.JComboBox<CompradorGadoBeans> cb_pesqComprador;
    public static javax.swing.JComboBox<String> cb_pesqFazenda;
    javax.swing.JComboBox<String> cb_pesqMotivo;
    javax.swing.JComboBox<String> cb_tipo_trans;
    javax.swing.JComboBox<String> cb_transportadora;
    javax.swing.JPanel jPanel1;
    javax.swing.JPanel jPanel2;
    javax.swing.JRadioButton rb_adic_romaneio_exist;
    javax.swing.JRadioButton rb_novo_rom;
    javax.swing.JTable tb_entradasGTA;
    javax.swing.JTable tb_notasFiscais;
    javax.swing.JTable tb_pesquisaRom;
    javax.swing.JTextField txt_ID;
    javax.swing.JTextField txt_SGTA;
    javax.swing.JTextField txt_SMIN;
    javax.swing.JTextField txt_cidade_oriGta;
    javax.swing.JTextField txt_codigo_rom;
    javax.swing.JTextField txt_cpf;
    com.toedter.calendar.JDateChooser txt_dataNF;
    com.toedter.calendar.JDateChooser txt_data_entrada_fisica;
    com.toedter.calendar.JDateChooser txt_data_gta;
    javax.swing.JComboBox<String> txt_fazendaOrigem;
    javax.swing.JTextField txt_fazenda_origem_gta;
    javax.swing.JTextField txt_ins_est;
    javax.swing.JTextField txt_km;
    javax.swing.JTextField txt_num_gta;
    javax.swing.JTextField txt_num_minuta;
    javax.swing.JTextField txt_num_nf;
    javax.swing.JTextField txt_peso_bruto;
    javax.swing.JTextField txt_peso_liq;
    javax.swing.JTextField txt_peso_medio;
    com.toedter.calendar.JDateChooser txt_pesqDataFinal;
    com.toedter.calendar.JDateChooser txt_pesqDataInicial;
    javax.swing.JTextField txt_pesqNcompra;
    javax.swing.JTextField txt_pesqOrigem;
    javax.swing.JTextField txt_pesqProdutor;
    javax.swing.JTextField txt_placa;
    javax.swing.JTextField txt_produtor;
    javax.swing.JTextField txt_q_f_12;
    javax.swing.JTextField txt_q_f_24;
    javax.swing.JTextField txt_q_f_36;
    javax.swing.JTextField txt_q_f_m36;
    javax.swing.JTextField txt_q_f_total;
    javax.swing.JTextField txt_q_m_12;
    javax.swing.JTextField txt_q_m_24;
    javax.swing.JTextField txt_q_m_36;
    javax.swing.JTextField txt_q_m_m36;
    javax.swing.JTextField txt_q_m_total;
    javax.swing.JTextField txt_quant_fisico;
    javax.swing.JTextField txt_quant_fisicoOri;
    javax.swing.JTextField txt_quant_gta;
    javax.swing.JTextField txt_quant_nf;
    javax.swing.JTextField txt_refugos;
    javax.swing.JTextField txt_tara;
    javax.swing.JTextField txt_valor_cb;
    javax.swing.JTextField txt_valor_nf;
    // End of variables declaration//GEN-END:variables

    private void ajustarTamanhoForm(Integer index) {
        if (index == 0) {
            this.setSize(this.getPreferredSize().width, 510);
        } else if (index >= 1) {
            this.setSize(this.getPreferredSize().width, this.getPreferredSize().height);
        }
    }

    final void polularBeansNF() {

        EntradaGadoB.setCodigoROM(Integer.parseInt(txt_codigo_rom.getText()));
        if (rb_novo_rom.isSelected()) {
            EntradaGadoB.setMetodoInsercao("NovoRom");
        } else if (rb_adic_romaneio_exist.isSelected()) {
            EntradaGadoB.setMetodoInsercao("RomExist");
        }
        EntradaGadoB.setFazendaEnt(cb_fazenda.getSelectedItem().toString());
        EntradaGadoB.setMotivo(cb_motivo.getSelectedItem().toString());
        EntradaGadoB.setComprador(cb_comprador.getSelectedItem().toString());
        EntradaGadoB.setNumCompra(cb_num_compra.getSelectedItem().toString());
        try {
            EntradaGadoB.setDataNF(new SimpleDateFormat("dd/MM/yyyy").format(txt_dataNF.getDate()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Nº da NF", "Erro", 0, Icones.FormatarICO.ICObtnSair());
        }
        EntradaGadoB.setNumNf(txt_num_nf.getText());
        EntradaGadoB.setQuantNF(txt_quant_nf.getText());
        EntradaGadoB.setValorNF(txt_valor_nf.getText().replace(",", "."));
        EntradaGadoB.setReaisCab(txt_valor_cb.getText());
        EntradaGadoB.setInscricao(txt_ins_est.getText());
        EntradaGadoB.setProdutor(txt_produtor.getText());
        EntradaGadoB.setCPFProdutor(txt_cpf.getText());
        EntradaGadoB.setFazendaOrigem(txt_fazendaOrigem.getSelectedItem().toString());
    }

    public void popularBeansGTA() {
        //Dados GTA
        try {
            EntradaGadoB.setCodigoROM(Integer.parseInt(txt_codigo_rom.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Selecione um Novo Romaneio ou busque um Romaneio Existente", "Erro", 0, Icones.FormatarICO.ICObtnSair());
        }
        EntradaGadoB.setNumGTA(txt_num_gta.getText());
        EntradaGadoB.setQuantGTA(txt_quant_gta.getText());
        if (txt_num_gta.getText().length() > 0) {
            try {
                EntradaGadoB.setDataGTA(new SimpleDateFormat("dd/MM/yyyy").format(txt_data_gta.getDate()));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Caso o campo Nº GTA esteja Preenchido o campo Data GTA é obrigatório", "Erro", 0, Icones.FormatarICO.ICObtnSair());
            }
        } else {
            EntradaGadoB.setDataGTA("01/01/1901");
        }

        EntradaGadoB.setFazendaOrigemGTA(txt_fazenda_origem_gta.getText());
        EntradaGadoB.setMunicipioGTA(txt_cidade_oriGta.getText());
        EntradaGadoB.setTipoTransp(cb_tipo_trans.getSelectedItem().toString());
        EntradaGadoB.setDestinoGTA(cb_destinoGTA.getSelectedItem().toString());
        EntradaGadoB.setMate12(txt_q_m_12.getText());
        EntradaGadoB.setMate24(txt_q_m_24.getText());
        EntradaGadoB.setMate36(txt_q_m_36.getText());
        EntradaGadoB.setMateM36(txt_q_m_m36.getText());
        EntradaGadoB.setMTotal(txt_q_m_total.getText());

        EntradaGadoB.setFate12(txt_q_f_12.getText());
        EntradaGadoB.setFate24(txt_q_f_24.getText());
        EntradaGadoB.setFate36(txt_q_f_36.getText());
        EntradaGadoB.setFateM36(txt_q_f_m36.getText());
        EntradaGadoB.setFTotal(txt_q_f_total.getText());

        //Dados Minuta
        EntradaGadoB.setPlaca(txt_placa.getText());
        EntradaGadoB.setTara(txt_tara.getText());
        EntradaGadoB.setPesoBruto(txt_peso_bruto.getText());
        EntradaGadoB.setQuilometro(txt_km.getText());
        EntradaGadoB.setQuantCab(txt_quant_fisico.getText());
        EntradaGadoB.setQuantCabOrigem(Integer.parseInt(txt_quant_fisicoOri.getText()));
        EntradaGadoB.setNumRefugo(txt_refugos.getText());
        EntradaGadoB.setDestinoFisico(cb_destinoFisico.getSelectedItem().toString());
        EntradaGadoB.setCategoria(cb_categoria.getSelectedItem().toString());
        EntradaGadoB.setTransportadora(cb_transportadora.getSelectedItem().toString());
        EntradaGadoB.setNumMinuta(txt_num_minuta.getText());

        if (Integer.parseInt(txt_quant_fisico.getText()) > 0) {
            try {
                EntradaGadoB.setDataEntradaFisica(new SimpleDateFormat("dd/MM/yyyy").format(txt_data_entrada_fisica.getDate()));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Caso o campo Quant. de Cabeças Destino seja maior que zero a Data Entrada Física é Obrigatório", "Erro", 0, Icones.FormatarICO.ICObtnSair());
            }
        } else {
            EntradaGadoB.setDataEntradaFisica("01/01/1901");
        }
    }

    final void popularBeansPesquisa() {
        try {
            EntradaGadoB.setPesqDataInicial(new SimpleDateFormat("dd/MM/yyyy").format(txt_pesqDataInicial.getDate()));
            EntradaGadoB.setPesqDataFinal(new SimpleDateFormat("dd/MM/yyyy").format(txt_pesqDataFinal.getDate()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "O Intervalo de Data é Obrigatório", "Erro", 0, Icones.FormatarICO.ICObtnSair());
        }

        if (cb_pesqMotivo.getSelectedItem().equals("-")) {
            EntradaGadoB.setPesqMotivo("");
        } else {
            EntradaGadoB.setPesqMotivo(cb_pesqMotivo.getSelectedItem().toString());
        }

        if (cb_pesqComprador.getSelectedIndex() == 0) {
            EntradaGadoB.setPesqComprador("");
        } else {
            EntradaGadoB.setPesqComprador(cb_pesqComprador.getSelectedItem().toString());
        }

        if (cb_pesqFazenda.getSelectedItem().equals("-")) {
            EntradaGadoB.setPesqFazenda("");
        } else {
            EntradaGadoB.setPesqFazenda(cb_pesqFazenda.getSelectedItem().toString());
        }

        EntradaGadoB.setPesqNcompra(txt_pesqNcompra.getText());
        EntradaGadoB.setPesqProdutor(txt_pesqProdutor.getText());
        EntradaGadoB.setPesqFazOrigem(txt_pesqOrigem.getText());
    }

    final void limparCamposNF() {
        //    txt_codigo_rom.setText("");
        //    cb_fazenda.setSelectedItem("-");
        //    cb_motivo.setSelectedItem("-");
        //    cb_comprador.setSelectedItem("-");        
        //    cb_num_compra.setSelectedItem("0");        
        txt_dataNF.setDate(null);
        txt_dataNF.setDateFormatString("dd/MM/yyyy");
        txt_num_nf.setText("");
        txt_quant_nf.setText("");
        txt_valor_nf.setText("");
        txt_valor_cb.setText("");
        txt_ins_est.setText("");
        txt_produtor.setText("");
        txt_cpf.setText("");
        txt_fazendaOrigem.setSelectedItem("");
    }

    final void somarGTAm() {
        int Qmtotal = 0;
        try {
            Qmtotal = Integer.parseInt(txt_q_m_12.getText()) + Integer.parseInt(txt_q_m_24.getText()) + Integer.parseInt(txt_q_m_36.getText()) + Integer.parseInt(txt_q_m_m36.getText());
            txt_q_m_total.setText(String.valueOf(Qmtotal));
        } catch (Exception e) {
            System.out.println(Qmtotal);
        }

    }

    final void somarGTAf() {
        int Qftotal = Integer.parseInt(txt_q_f_12.getText()) + Integer.parseInt(txt_q_f_24.getText()) + Integer.parseInt(txt_q_f_36.getText()) + Integer.parseInt(txt_q_f_m36.getText());
        txt_q_f_total.setText(String.valueOf(Qftotal));
    }

    private void setComboBoxComprador(JComboBox<CompradorGadoBeans> comboBox, String Comprador) {
        for (int i = 0; i < comboBox.getModel().getSize(); i++) {
            if (comboBox.getModel().getElementAt(i).getComprador().equals(Comprador)) {
                comboBox.setSelectedIndex(i);
            }
        }
    }

    final void calcularPesoM() {
        float PesoL = 0;
        float PesoM = 0;
        int nCab = 0;

        try {
            PesoL = Float.parseFloat(txt_peso_bruto.getText()) - Float.parseFloat(txt_tara.getText());
            String Liquido = new DecimalFormat("#,##0.00").format(PesoL);
            txt_peso_liq.setText(String.valueOf(Liquido));
        } catch (Exception e) {
            //  JOptionPane.showMessageDialog(null, "O campo peso bruto e tarda devem conter um númerno no formato 0.00", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }

        try {
            nCab = Integer.parseInt(txt_quant_fisico.getText());
            if (nCab > 0) {
                PesoM = (PesoL / nCab);
                String media = new DecimalFormat("#,##0.00 kg").format(PesoM);
                txt_peso_medio.setText(String.valueOf(media));
            } else {
                String media = new DecimalFormat("#,##0.00 kg").format(PesoM);
                txt_peso_medio.setText(String.valueOf(media));
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "O campo número de cabeças deve conter um número inteiro", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }
    }

    final void preencherCamposGTA() {
        int linha = tb_entradasGTA.getSelectedRow();

        txt_ID.setText(TbGTA.getValueAt(linha, 0).toString());
        txt_codigo_rom.setText(TbGTA.getValueAt(linha, 32).toString());

        txt_num_gta.setText(TbGTA.getValueAt(linha, 1).toString());
        txt_quant_gta.setText(TbGTA.getValueAt(linha, 7).toString());

        txt_fazenda_origem_gta.setText(TbGTA.getValueAt(linha, 3).toString());
        txt_cidade_oriGta.setText(TbGTA.getValueAt(linha, 4).toString());
        cb_tipo_trans.setSelectedItem(TbGTA.getValueAt(linha, 5).toString());
        cb_destinoGTA.setSelectedItem(TbGTA.getValueAt(linha, 6).toString());

        txt_q_m_12.setText(TbGTA.getValueAt(linha, 8).toString());
        txt_q_m_24.setText(TbGTA.getValueAt(linha, 9).toString());
        txt_q_m_36.setText(TbGTA.getValueAt(linha, 10).toString());
        txt_q_m_m36.setText(TbGTA.getValueAt(linha, 11).toString());
        txt_q_m_total.setText(TbGTA.getValueAt(linha, 12).toString());

        txt_q_f_12.setText(TbGTA.getValueAt(linha, 13).toString());
        txt_q_f_24.setText(TbGTA.getValueAt(linha, 14).toString());
        txt_q_f_36.setText(TbGTA.getValueAt(linha, 15).toString());
        txt_q_f_m36.setText(TbGTA.getValueAt(linha, 16).toString());
        txt_q_f_total.setText(TbGTA.getValueAt(linha, 17).toString());

        //Dados Minuta
        txt_placa.setText(TbGTA.getValueAt(linha, 18).toString());
        txt_tara.setText(TbGTA.getValueAt(linha, 19).toString());
        txt_peso_bruto.setText(TbGTA.getValueAt(linha, 20).toString());
        txt_peso_liq.setText(TbGTA.getValueAt(linha, 21).toString());
        txt_peso_medio.setText(TbGTA.getValueAt(linha, 24).toString());
        txt_km.setText(TbGTA.getValueAt(linha, 31).toString());
        txt_quant_fisico.setText(TbGTA.getValueAt(linha, 22).toString());
        txt_quant_fisicoOri.setText(TbGTA.getValueAt(linha, 23).toString());
        txt_refugos.setText(TbGTA.getValueAt(linha, 25).toString());
        cb_destinoFisico.setSelectedItem(TbGTA.getValueAt(linha, 26).toString());
        cb_categoria.setSelectedItem(TbGTA.getValueAt(linha, 28).toString());
        cb_transportadora.setSelectedItem(TbGTA.getValueAt(linha, 29).toString());
        txt_num_minuta.setText(TbGTA.getValueAt(linha, 30).toString());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dtEntFisica;
        Date dtEntGTA;

        try {
            dtEntFisica = sdf.parse(TbGTA.getValueAt(linha, 27).toString());
            dtEntGTA = sdf.parse(TbGTA.getValueAt(linha, 2).toString());
            txt_data_entrada_fisica.setDate(dtEntFisica);
            txt_data_gta.setDate(dtEntGTA);
        } catch (ParseException ex) {

        }
    }

    final void limparCamposGTA() {
        txt_ID.setText("");
        //txt_codigo_rom.setText();
        txt_num_gta.setText("");
        txt_quant_gta.setText("0");

        txt_fazenda_origem_gta.setText("");
        txt_cidade_oriGta.setText("");
        cb_tipo_trans.setSelectedItem("-");
        cb_destinoGTA.setSelectedItem("-");

        txt_q_m_12.setText("0");
        txt_q_m_24.setText("0");
        txt_q_m_36.setText("0");
        txt_q_m_m36.setText("0");
        txt_q_m_total.setText("0");

        txt_q_f_12.setText("0");
        txt_q_f_24.setText("0");
        txt_q_f_36.setText("0");
        txt_q_f_m36.setText("0");
        txt_q_f_total.setText("0");

        //Dados Minuta
        txt_placa.setText("");
        txt_tara.setText("0");
        txt_peso_bruto.setText("0");
        txt_peso_liq.setText("0");
        txt_peso_medio.setText("0");
        txt_km.setText("0");
        txt_quant_fisico.setText("0");
        txt_quant_fisicoOri.setText("0");
        txt_refugos.setText("0");
        cb_destinoFisico.setSelectedItem("-");
        cb_categoria.setSelectedItem("-");
        cb_transportadora.setSelectedItem("-");
        txt_num_minuta.setText("");
    }

    final boolean VerificarNcompra() {
        if (cb_motivo.getSelectedItem().equals("Compra") & !cb_num_compra.getSelectedItem().equals("0")) {
            return true;
        } else if (cb_motivo.getSelectedItem().equals("Compra") & (cb_num_compra.getSelectedItem().equals("0") || cb_num_compra.getSelectedItem().equals(""))) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Nº da Compra", "Erro", 0, Icones.FormatarICO.ICObtnSair());
            return false;
        } else {
            return true;
        }
    }

    final void ImprimiRelatorioEntrada(Integer NRomaneio) {
        int confirma = JOptionPane.showConfirmDialog(null, "Deseja visualizar o relatório de entrada?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            Map p = new HashMap();
            JasperReport relatorio;
            JasperPrint impressao;
            Conexao.ReiniciarCon();
            try {
                if (!NRomaneio.equals("")) {
                    p.put("codRom", NRomaneio);
                    p.put("SUBREPORT_DIR", getClass().getResource("/Relatorios/").toString());
                    relatorio = JasperCompileManager.compileReport(new File("").getClass().getResourceAsStream("/Relatorios/Romaneio.jrxml"));
                    impressao = JasperFillManager.fillReport(relatorio, p, Conexao.getConnection());
                    String caminho = (System.getProperty("user.home") + ("\\RomaneioEntrada" + txt_codigo_rom.getText() + ".pdf"));
                    JasperExportManager.exportReportToPdfFile(impressao, caminho);
                    Runtime.getRuntime().exec("cmd /c start " + caminho);
                    File file = new File(caminho);
                    file.deleteOnExit();
                } else {
                    JOptionPane.showMessageDialog(null, "Verifique o número do romaneio de entrada!!");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    final void totalizarTabela() {
        int SomaQGTA = 0;
        int SomaQMIN = 0;
        for (int i = 0; i <= tb_pesquisaRom.getRowCount() - 1; i++) {
            SomaQGTA += Integer.parseInt(tb_pesquisaRom.getValueAt(i, 11).toString());
            SomaQMIN += Integer.parseInt(tb_pesquisaRom.getValueAt(i, 12).toString());
        }
        txt_SGTA.setText(String.valueOf(SomaQGTA));
        txt_SMIN.setText(String.valueOf(SomaQMIN));
    }

}
