package GUI;

import Beans.EscalasAbateBeans;
import Beans.ListFrigorificosBeans;
import Controller.EscalasAbateController;
import DAO.Diversas;
import DAO.EscalasAbateDAO;
import static GUI.Principal.listFrigorificos;
import static GUI.frmPesquisarVenda.pesquisarNVenda;
import Icones.FormatarICO;
import Utilitarios.CentralizarTabela;
import Utilitarios.Conexao;
import Utilitarios.Corretores;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class frmEscalasAbate extends javax.swing.JInternalFrame {

    EscalasAbateDAO EscalasD;
    EscalasAbateBeans EscalasB;
    EscalasAbateController EscalasC;
    DefaultTableModel TbEscalas;
    frmPesquisarVenda TelaPesquisarVenda;
    frmRecebimentos Recebimentos;
    CentralizarTabela Centralizar;
    Diversas DiversasD;

    public frmEscalasAbate() {
        initComponents();
        Centralizar = new CentralizarTabela();
        EscalasD = new EscalasAbateDAO();
        EscalasB = new EscalasAbateBeans();
        EscalasC = new EscalasAbateController();
        DiversasD = new Diversas();
        TbEscalas = (DefaultTableModel) tb_escalas.getModel();
        EscalasD.preencherFazendaDestino();
        carregarFrigorificos();

        txt_pesqDtInicial.setDate(Corretores.PrimeiroDiaMes());
        txt_pesqDtFinal.setDate(Corretores.UltimoDiaMes());
        cb_cliente.setModel(new DefaultComboBoxModel(new Vector(EscalasD.buscarClientes())));
        tbEscalasGado();

    }

    private void carregarFrigorifico() {

    }

    private JTable tbEscalasGado() {
        Centralizar.centralizarTabela(tb_escalas);
        ((DefaultTableCellRenderer) tb_escalas.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        return tb_escalas;
    }

    private void carregarFrigorificos() {
        if (listFrigorificos == null) {
            listFrigorificos = DiversasD.ListFrigorificos();
        }

        cb_pesqDestino.addItem("-");
        for (ListFrigorificosBeans list : listFrigorificos) {
            cb_pesqDestino.addItem(list);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.ButtonGroup buttonGroup1 = new javax.swing.ButtonGroup();
        jPopMenu = new javax.swing.JPopupMenu();
        jMenuBoletim = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuRecebimento = new javax.swing.JMenuItem();
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel20 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel21 = new javax.swing.JLabel();
        txt_pesqDtInicial = new com.toedter.calendar.JDateChooser();
        txt_pesqDtFinal = new com.toedter.calendar.JDateChooser();
        chb_dtEmbarque = new javax.swing.JCheckBox();
        chb_dtPagto = new javax.swing.JCheckBox();
        javax.swing.JLabel jLabel22 = new javax.swing.JLabel();
        cb_pesqDestino = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel23 = new javax.swing.JLabel();
        cb_pesqFazOrigem = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel24 = new javax.swing.JLabel();
        cb_pesqCategoria = new javax.swing.JComboBox<>();
        btn_pesquisar3 = new javax.swing.JButton();
        txt_PesqnVenda = new javax.swing.JTextField();
        javax.swing.JLabel jLabel25 = new javax.swing.JLabel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        tb_escalas = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        txt_qtEscalaT = new javax.swing.JTextField();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel18 = new javax.swing.JLabel();
        txt_reaisArT = new javax.swing.JTextField();
        txt_pesoArT = new javax.swing.JTextField();
        javax.swing.JLabel jLabel26 = new javax.swing.JLabel();
        txt_rendT = new javax.swing.JTextField();
        txt_valorT = new javax.swing.JTextField();
        javax.swing.JLabel jLabel27 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel28 = new javax.swing.JLabel();
        txt_aReceberT = new javax.swing.JTextField();
        javax.swing.JPanel jPanel3 = new javax.swing.JPanel();
        btn_editar = new javax.swing.JButton();
        btn_Salvar = new javax.swing.JButton();
        btn_novo = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();
        txt_dtEmbarque = new com.toedter.calendar.JDateChooser();
        txt_dtPagamento = new com.toedter.calendar.JDateChooser();
        txt_dtAbate = new com.toedter.calendar.JDateChooser();
        txt_qEscala = new javax.swing.JTextField();
        cb_categoria = new javax.swing.JComboBox<>();
        txt_valorAr = new javax.swing.JTextField();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        txt_PesoMorto = new javax.swing.JTextField();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        txt_pesoVTotal = new javax.swing.JTextField();
        txt_valorTotal = new javax.swing.JTextField();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        txt_qEmbarque = new javax.swing.JTextField();
        javax.swing.JLabel jLabel13 = new javax.swing.JLabel();
        txt_rend = new javax.swing.JTextField();
        javax.swing.JLabel jLabel14 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel15 = new javax.swing.JLabel();
        txt_pesoVar = new javax.swing.JTextField();
        javax.swing.JLabel jLabel16 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel17 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel19 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        cb_cliente = new javax.swing.JComboBox<>();
        cb_FazOrigem = new javax.swing.JComboBox<>();
        txt_nescala = new javax.swing.JTextField();
        txt_nVenda = new javax.swing.JTextField();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        txt_Observacao = new javax.swing.JTextArea();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        cb_statusEscala = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel29 = new javax.swing.JLabel();

        jMenuBoletim.setText("Imprimir Boletim");
        jMenuBoletim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuBoletimActionPerformed(evt);
            }
        });
        jPopMenu.add(jMenuBoletim);
        jPopMenu.add(jSeparator1);

        jMenuRecebimento.setText("Agendar Recebimento");
        jMenuRecebimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuRecebimentoActionPerformed(evt);
            }
        });
        jPopMenu.add(jMenuRecebimento);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Controle de Escalas de Abate");
        setNormalBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setPreferredSize(new java.awt.Dimension(1250, 688));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 420));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setMaximumSize(new java.awt.Dimension(32767, 110));
        jPanel2.setPreferredSize(new java.awt.Dimension(900, 87));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Data Inicial");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Data Final");

        buttonGroup1.add(chb_dtEmbarque);
        chb_dtEmbarque.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chb_dtEmbarque.setSelected(true);
        chb_dtEmbarque.setText("Data Embarque");

        buttonGroup1.add(chb_dtPagto);
        chb_dtPagto.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chb_dtPagto.setText("Data Pagto");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Origem");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("Destino");

        cb_pesqFazOrigem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Conf. Juliana", "Conf. Nova Vida" }));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Categoria");

        cb_pesqCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Touros", "Vaca Parida", "Vaca Descarte", "Vaca Solteira", "Vacas", "Novilhas", "Bezerras", "Bezerros", "Garrotes", "Bois Magros", "Bois" }));

        btn_pesquisar3.setBackground(new java.awt.Color(255, 255, 255));
        btn_pesquisar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar.png"))); // NOI18N
        btn_pesquisar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisar3ActionPerformed(evt);
            }
        });

        txt_PesqnVenda.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_PesqnVenda.setMaximumSize(new java.awt.Dimension(120, 110));
        txt_PesqnVenda.setMinimumSize(new java.awt.Dimension(110, 0));
        txt_PesqnVenda.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_PesqnVendaFocusLost(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("Cto Venda");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_pesqDtFinal, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(txt_pesqDtInicial, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(chb_dtPagto)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel22)
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(chb_dtEmbarque)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cb_pesqFazOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel24))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cb_pesqDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel25)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cb_pesqCategoria, 0, 121, Short.MAX_VALUE)
                    .addComponent(txt_PesqnVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                .addComponent(btn_pesquisar3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_pesqCategoria)
                            .addComponent(chb_dtPagto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_pesqFazOrigem))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_PesqnVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cb_pesqDestino)
                                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(chb_dtEmbarque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_pesqDtInicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_pesqDtFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_pesquisar3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(3, 3, 3)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 471));

        tb_escalas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nº Escala", "Nº Venda", "Origem", "Destino", "Embarque", "Abate", "Pagamento", "Q. Escala", "Q. Embarcado", "Categoria", "Reias /@", "Peso M.", "Peso V.", "Rend. %", "Peso M. (Kg)", "Peso M. (@)", "Valor a Receber", "Valor Recebido", "Obs", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_escalas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tb_escalas.setMaximumSize(new java.awt.Dimension(1800, 500));
        tb_escalas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_escalasMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tb_escalasMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tb_escalas);
        if (tb_escalas.getColumnModel().getColumnCount() > 0) {
            tb_escalas.getColumnModel().getColumn(0).setMinWidth(50);
            tb_escalas.getColumnModel().getColumn(0).setPreferredWidth(50);
            tb_escalas.getColumnModel().getColumn(0).setMaxWidth(70);
            tb_escalas.getColumnModel().getColumn(1).setMinWidth(50);
            tb_escalas.getColumnModel().getColumn(1).setPreferredWidth(50);
            tb_escalas.getColumnModel().getColumn(1).setMaxWidth(70);
            tb_escalas.getColumnModel().getColumn(2).setMinWidth(70);
            tb_escalas.getColumnModel().getColumn(2).setPreferredWidth(70);
            tb_escalas.getColumnModel().getColumn(2).setMaxWidth(100);
            tb_escalas.getColumnModel().getColumn(3).setMinWidth(70);
            tb_escalas.getColumnModel().getColumn(3).setPreferredWidth(70);
            tb_escalas.getColumnModel().getColumn(3).setMaxWidth(90);
            tb_escalas.getColumnModel().getColumn(4).setMinWidth(80);
            tb_escalas.getColumnModel().getColumn(4).setPreferredWidth(80);
            tb_escalas.getColumnModel().getColumn(4).setMaxWidth(110);
            tb_escalas.getColumnModel().getColumn(5).setMinWidth(80);
            tb_escalas.getColumnModel().getColumn(5).setPreferredWidth(80);
            tb_escalas.getColumnModel().getColumn(5).setMaxWidth(110);
            tb_escalas.getColumnModel().getColumn(6).setMinWidth(80);
            tb_escalas.getColumnModel().getColumn(6).setPreferredWidth(80);
            tb_escalas.getColumnModel().getColumn(6).setMaxWidth(110);
            tb_escalas.getColumnModel().getColumn(7).setMinWidth(70);
            tb_escalas.getColumnModel().getColumn(7).setPreferredWidth(70);
            tb_escalas.getColumnModel().getColumn(7).setMaxWidth(90);
            tb_escalas.getColumnModel().getColumn(9).setMinWidth(100);
            tb_escalas.getColumnModel().getColumn(9).setPreferredWidth(80);
            tb_escalas.getColumnModel().getColumn(9).setMaxWidth(100);
            tb_escalas.getColumnModel().getColumn(10).setMinWidth(80);
            tb_escalas.getColumnModel().getColumn(10).setPreferredWidth(80);
            tb_escalas.getColumnModel().getColumn(10).setMaxWidth(110);
            tb_escalas.getColumnModel().getColumn(11).setMinWidth(90);
            tb_escalas.getColumnModel().getColumn(11).setPreferredWidth(90);
            tb_escalas.getColumnModel().getColumn(11).setMaxWidth(120);
            tb_escalas.getColumnModel().getColumn(12).setMinWidth(90);
            tb_escalas.getColumnModel().getColumn(12).setPreferredWidth(90);
            tb_escalas.getColumnModel().getColumn(12).setMaxWidth(120);
            tb_escalas.getColumnModel().getColumn(13).setMinWidth(70);
            tb_escalas.getColumnModel().getColumn(13).setPreferredWidth(70);
            tb_escalas.getColumnModel().getColumn(13).setMaxWidth(90);
            tb_escalas.getColumnModel().getColumn(14).setMinWidth(90);
            tb_escalas.getColumnModel().getColumn(14).setPreferredWidth(90);
            tb_escalas.getColumnModel().getColumn(14).setMaxWidth(120);
            tb_escalas.getColumnModel().getColumn(15).setMinWidth(80);
            tb_escalas.getColumnModel().getColumn(15).setPreferredWidth(80);
            tb_escalas.getColumnModel().getColumn(15).setMaxWidth(100);
            tb_escalas.getColumnModel().getColumn(16).setMinWidth(100);
            tb_escalas.getColumnModel().getColumn(16).setPreferredWidth(115);
            tb_escalas.getColumnModel().getColumn(16).setMaxWidth(130);
            tb_escalas.getColumnModel().getColumn(17).setMinWidth(100);
            tb_escalas.getColumnModel().getColumn(17).setPreferredWidth(115);
            tb_escalas.getColumnModel().getColumn(17).setMaxWidth(130);
            tb_escalas.getColumnModel().getColumn(18).setMinWidth(0);
            tb_escalas.getColumnModel().getColumn(18).setPreferredWidth(0);
            tb_escalas.getColumnModel().getColumn(18).setMaxWidth(0);
            tb_escalas.getColumnModel().getColumn(19).setMinWidth(70);
            tb_escalas.getColumnModel().getColumn(19).setPreferredWidth(90);
            tb_escalas.getColumnModel().getColumn(19).setMaxWidth(100);
        }

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Totais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel4.setPreferredSize(new java.awt.Dimension(12, 55));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Quant. Escalada");

        txt_qtEscalaT.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("R$/@");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Peso Médio");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("Rend. %");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText("Valor Total");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setText("Saldo à Receber");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_qtEscalaT, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_reaisArT, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_pesoArT, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_rendT, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_valorT, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_aReceberT, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_qtEscalaT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel18)
                    .addComponent(txt_reaisArT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_pesoArT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(txt_rendT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(txt_valorT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(txt_aReceberT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 967, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 967, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setPreferredSize(new java.awt.Dimension(257, 428));

        btn_editar.setBackground(new java.awt.Color(255, 255, 255));
        btn_editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/editar_pequeno.png"))); // NOI18N
        btn_editar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn_editar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_editar.setEnabled(false);
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });

        btn_Salvar.setBackground(new java.awt.Color(255, 255, 255));
        btn_Salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/salvar.png"))); // NOI18N
        btn_Salvar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn_Salvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Salvar.setDisabledIcon(null);
        btn_Salvar.setEnabled(false);
        btn_Salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalvarActionPerformed(evt);
            }
        });

        btn_novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/inserir_40x40.png"))); // NOI18N
        btn_novo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn_novo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novoActionPerformed(evt);
            }
        });

        btn_excluir.setBackground(new java.awt.Color(255, 255, 255));
        btn_excluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/deletar_40x40.png"))); // NOI18N
        btn_excluir.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn_excluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_excluir.setEnabled(false);
        btn_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirActionPerformed(evt);
            }
        });

        txt_dtEmbarque.setEnabled(false);
        txt_dtEmbarque.setMinimumSize(new java.awt.Dimension(86, 20));
        txt_dtEmbarque.setPreferredSize(new java.awt.Dimension(86, 28));
        txt_dtEmbarque.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txt_dtEmbarquePropertyChange(evt);
            }
        });

        txt_dtPagamento.setEnabled(false);
        txt_dtPagamento.setMinimumSize(new java.awt.Dimension(86, 20));
        txt_dtPagamento.setPreferredSize(new java.awt.Dimension(86, 28));
        txt_dtPagamento.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txt_dtPagamentoPropertyChange(evt);
            }
        });

        txt_dtAbate.setEnabled(false);
        txt_dtAbate.setMinimumSize(new java.awt.Dimension(86, 20));
        txt_dtAbate.setPreferredSize(new java.awt.Dimension(86, 28));

        txt_qEscala.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_qEscala.setEnabled(false);
        txt_qEscala.setMinimumSize(new java.awt.Dimension(86, 20));
        txt_qEscala.setPreferredSize(new java.awt.Dimension(86, 28));

        cb_categoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Touros", "Vaca Parida", "Vaca Descarte", "Vaca Solteira", "Vacas", "Novilhas", "Bezerras", "Bezerros", "Garrotes", "Bois Magros", "Bois" }));
        cb_categoria.setEnabled(false);
        cb_categoria.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        cb_categoria.setMinimumSize(new java.awt.Dimension(86, 20));
        cb_categoria.setPreferredSize(new java.awt.Dimension(86, 28));

        txt_valorAr.setEnabled(false);
        txt_valorAr.setMinimumSize(new java.awt.Dimension(86, 20));
        txt_valorAr.setPreferredSize(new java.awt.Dimension(86, 28));
        txt_valorAr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_valorArFocusLost(evt);
            }
        });
        txt_valorAr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_valorArKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Qt Escala");
        jLabel9.setMaximumSize(new java.awt.Dimension(86, 35));
        jLabel9.setMinimumSize(new java.awt.Dimension(86, 20));
        jLabel9.setPreferredSize(new java.awt.Dimension(86, 28));

        txt_PesoMorto.setEnabled(false);
        txt_PesoMorto.setMinimumSize(new java.awt.Dimension(86, 20));
        txt_PesoMorto.setPreferredSize(new java.awt.Dimension(86, 28));
        txt_PesoMorto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_PesoMortoFocusLost(evt);
            }
        });
        txt_PesoMorto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_PesoMortoKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Categoria");
        jLabel10.setMaximumSize(new java.awt.Dimension(86, 35));
        jLabel10.setMinimumSize(new java.awt.Dimension(86, 20));
        jLabel10.setPreferredSize(new java.awt.Dimension(86, 28));

        txt_pesoVTotal.setEnabled(false);
        txt_pesoVTotal.setMinimumSize(new java.awt.Dimension(86, 20));
        txt_pesoVTotal.setPreferredSize(new java.awt.Dimension(86, 28));
        txt_pesoVTotal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_pesoVTotalFocusLost(evt);
            }
        });

        txt_valorTotal.setEditable(false);
        txt_valorTotal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_valorTotal.setForeground(new java.awt.Color(0, 51, 255));
        txt_valorTotal.setMinimumSize(new java.awt.Dimension(86, 20));
        txt_valorTotal.setPreferredSize(new java.awt.Dimension(86, 28));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Reais / @");
        jLabel11.setMaximumSize(new java.awt.Dimension(86, 35));
        jLabel11.setMinimumSize(new java.awt.Dimension(86, 20));
        jLabel11.setPreferredSize(new java.awt.Dimension(86, 28));

        txt_qEmbarque.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_qEmbarque.setText("0");
        txt_qEmbarque.setEnabled(false);
        txt_qEmbarque.setMinimumSize(new java.awt.Dimension(86, 20));
        txt_qEmbarque.setPreferredSize(new java.awt.Dimension(86, 28));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("R$ a Receber");
        jLabel13.setMaximumSize(new java.awt.Dimension(86, 35));
        jLabel13.setMinimumSize(new java.awt.Dimension(86, 20));
        jLabel13.setPreferredSize(new java.awt.Dimension(86, 28));

        txt_rend.setEditable(false);
        txt_rend.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txt_rend.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_rend.setMinimumSize(new java.awt.Dimension(86, 20));
        txt_rend.setPreferredSize(new java.awt.Dimension(86, 28));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Peso Morto");
        jLabel14.setMaximumSize(new java.awt.Dimension(86, 35));
        jLabel14.setMinimumSize(new java.awt.Dimension(86, 20));
        jLabel14.setPreferredSize(new java.awt.Dimension(86, 28));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Peso Vivo Total");
        jLabel15.setMaximumSize(new java.awt.Dimension(86, 35));
        jLabel15.setMinimumSize(new java.awt.Dimension(86, 20));
        jLabel15.setPreferredSize(new java.awt.Dimension(86, 28));

        txt_pesoVar.setEditable(false);
        txt_pesoVar.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txt_pesoVar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_pesoVar.setMinimumSize(new java.awt.Dimension(86, 20));
        txt_pesoVar.setPreferredSize(new java.awt.Dimension(86, 28));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Emb.");
        jLabel16.setMaximumSize(new java.awt.Dimension(86, 35));
        jLabel16.setPreferredSize(new java.awt.Dimension(86, 28));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Rend %");
        jLabel17.setMaximumSize(new java.awt.Dimension(86, 35));
        jLabel17.setMinimumSize(new java.awt.Dimension(86, 20));
        jLabel17.setPreferredSize(new java.awt.Dimension(86, 28));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("@");
        jLabel19.setPreferredSize(new java.awt.Dimension(86, 28));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Data Embarque");
        jLabel6.setMaximumSize(new java.awt.Dimension(86, 35));
        jLabel6.setMinimumSize(new java.awt.Dimension(86, 20));
        jLabel6.setPreferredSize(new java.awt.Dimension(86, 28));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Data Abate");
        jLabel7.setMaximumSize(new java.awt.Dimension(86, 35));
        jLabel7.setMinimumSize(new java.awt.Dimension(86, 20));
        jLabel7.setPreferredSize(new java.awt.Dimension(86, 28));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Pagamento");
        jLabel8.setMaximumSize(new java.awt.Dimension(86, 35));
        jLabel8.setMinimumSize(new java.awt.Dimension(86, 20));
        jLabel8.setPreferredSize(new java.awt.Dimension(86, 28));

        cb_cliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));
        cb_cliente.setEnabled(false);
        cb_cliente.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        cb_cliente.setMinimumSize(new java.awt.Dimension(86, 20));
        cb_cliente.setPreferredSize(new java.awt.Dimension(86, 28));

        cb_FazOrigem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Conf. Juliana", "Conf. Nova Vida" }));
        cb_FazOrigem.setEnabled(false);
        cb_FazOrigem.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        cb_FazOrigem.setMinimumSize(new java.awt.Dimension(86, 20));
        cb_FazOrigem.setPreferredSize(new java.awt.Dimension(86, 28));

        txt_nescala.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nescala.setEnabled(false);
        txt_nescala.setMinimumSize(new java.awt.Dimension(86, 20));
        txt_nescala.setPreferredSize(new java.awt.Dimension(86, 28));

        txt_nVenda.setEditable(false);
        txt_nVenda.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nVenda.setEnabled(false);
        txt_nVenda.setMinimumSize(new java.awt.Dimension(86, 20));
        txt_nVenda.setPreferredSize(new java.awt.Dimension(86, 28));
        txt_nVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_nVendaMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Nº Venda");
        jLabel1.setMaximumSize(new java.awt.Dimension(86, 35));
        jLabel1.setMinimumSize(new java.awt.Dimension(86, 20));
        jLabel1.setPreferredSize(new java.awt.Dimension(86, 28));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Nº Escala");
        jLabel2.setPreferredSize(new java.awt.Dimension(86, 28));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Origem");
        jLabel3.setMaximumSize(new java.awt.Dimension(86, 35));
        jLabel3.setMinimumSize(new java.awt.Dimension(86, 20));
        jLabel3.setPreferredSize(new java.awt.Dimension(86, 28));

        txt_Observacao.setColumns(20);
        txt_Observacao.setRows(5);
        txt_Observacao.setText("Obs.:");
        txt_Observacao.setEnabled(false);
        jScrollPane2.setViewportView(txt_Observacao);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Cliente");
        jLabel4.setMaximumSize(new java.awt.Dimension(86, 35));
        jLabel4.setMinimumSize(new java.awt.Dimension(86, 20));
        jLabel4.setPreferredSize(new java.awt.Dimension(86, 28));

        cb_statusEscala.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Previsão", "Abatido", "Pagto Previsto", "Recebido" }));
        cb_statusEscala.setEnabled(false);
        cb_statusEscala.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        cb_statusEscala.setMinimumSize(new java.awt.Dimension(86, 20));
        cb_statusEscala.setPreferredSize(new java.awt.Dimension(86, 28));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setText("Status");
        jLabel29.setMaximumSize(new java.awt.Dimension(86, 35));
        jLabel29.setMinimumSize(new java.awt.Dimension(86, 20));
        jLabel29.setPreferredSize(new java.awt.Dimension(86, 28));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txt_nVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txt_nescala, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(cb_FazOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(cb_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txt_dtEmbarque, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txt_dtAbate, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txt_dtPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txt_qEscala, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txt_qEmbarque, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(cb_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txt_valorAr, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txt_PesoMorto, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txt_pesoVTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txt_rend, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txt_pesoVar, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txt_valorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(cb_statusEscala, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(btn_Salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nescala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_FazOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_dtEmbarque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_dtAbate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_dtPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_qEscala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_qEmbarque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_valorAr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_PesoMorto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_pesoVTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_rend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_pesoVar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_valorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_statusEscala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 971, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)))
        );

        setBounds(0, 0, 1271, 623);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed
        btn_Salvar.setEnabled(true);
        btn_editar.setEnabled(false);
        txt_nescala.setEnabled(false);
        habilitarCampos();
        limparCampos();
    }//GEN-LAST:event_btn_novoActionPerformed

    private void btn_SalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalvarActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(null, "Deseja Salvar esta Escala de Gado?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION) {
            popularBeans();
            EscalasC.controleInserirEscala(EscalasB, TbEscalas);
            btn_pesquisar3.doClick();
            limparCampos();
            desabilitarCampos();
        }
    }//GEN-LAST:event_btn_SalvarActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja editar esta Escala de Gado?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION) {
            if (!txt_nescala.getText().equals("")) {
                popularBeans();
                EscalasB.setnEscala(Integer.parseInt(txt_nescala.getText()));
                EscalasC.controleEditarEscala(EscalasB);
                btn_pesquisar3.doClick();
                limparCampos();
                desabilitarCampos();
                btn_editar.setEnabled(false);
                btn_excluir.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Selecione a escala que deseja editar!", "Erro", 0, FormatarICO.ICObtnSair());
            }
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        int excluir = JOptionPane.showConfirmDialog(null, "Deseja excluir esta Escala de Gado?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (excluir == JOptionPane.YES_OPTION) {
            EscalasB.setnEscala(Integer.parseInt(txt_nescala.getText()));
            EscalasC.controleExcuirEscala(EscalasB);
            btn_pesquisar3.doClick();
            limparCampos();
            desabilitarCampos();
            btn_editar.setEnabled(false);
            btn_excluir.setEnabled(false);
        }
    }//GEN-LAST:event_btn_excluirActionPerformed

    private void txt_PesoMortoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_PesoMortoFocusLost
        txt_PesoMorto.setText(Corretores.ConverterDoubleQuilos(txt_PesoMorto.getText()));
        txt_rend.setText(calcularRendimento());
        txt_pesoVar.setText(calcularPesoMedio());
        txt_valorTotal.setText(calcularValorTotal());
    }//GEN-LAST:event_txt_PesoMortoFocusLost

    private void txt_pesoVTotalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_pesoVTotalFocusLost
        txt_pesoVTotal.setText(Corretores.ConverterDoubleQuilos(txt_pesoVTotal.getText()));
        txt_rend.setText(calcularRendimento());
        txt_pesoVar.setText(calcularPesoMedio());
    }//GEN-LAST:event_txt_pesoVTotalFocusLost

    private void btn_pesquisar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisar3ActionPerformed
        popularBeansPesquisa();
        EscalasC.controlePreencherTabela(EscalasB, TbEscalas, getSQLWhere());
        totalizarTabela();
    }//GEN-LAST:event_btn_pesquisar3ActionPerformed

    private void tb_escalasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_escalasMouseClicked

        desabilitarCampos();
        btn_Salvar.setEnabled(false);
        if (evt.getClickCount() == 2) {
            int editar = JOptionPane.showConfirmDialog(null, "Deseja editar esta escala de embarque?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (editar == JOptionPane.YES_OPTION) {
                preencherCampos();
                btn_editar.setEnabled(true);
                btn_excluir.setEnabled(true);
                btn_Salvar.setEnabled(false);
                habilitarCampos();
            }
        }

        if (evt.getButton() == MouseEvent.BUTTON3) {
            if (jPopMenu.isVisible() == true) {
                jPopMenu.setVisible(false);
            } else {
                jPopMenu.setVisible(true);
                jPopMenu.show(this, 0, 0);
                jPopMenu.setLocation(evt.getLocationOnScreen());
            }
        }

    }//GEN-LAST:event_tb_escalasMouseClicked

    private void txt_valorArKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_valorArKeyReleased
        txt_valorTotal.setText(calcularValorTotal());
    }//GEN-LAST:event_txt_valorArKeyReleased

    private void txt_PesoMortoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PesoMortoKeyReleased
        txt_valorTotal.setText(calcularValorTotal());
    }//GEN-LAST:event_txt_PesoMortoKeyReleased

    private void txt_valorArFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorArFocusLost
        txt_valorAr.setText(Corretores.ConverterDecimalReais(txt_valorAr.getText()));
    }//GEN-LAST:event_txt_valorArFocusLost

    private void txt_nVendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_nVendaMouseClicked
        if (evt.getClickCount() == 2) {
            TelaPesquisarVenda = new frmPesquisarVenda(null, true);
            TelaPesquisarVenda.setVisible(true);
            txt_nVenda.setText(pesquisarNVenda.toString());
        }

    }//GEN-LAST:event_txt_nVendaMouseClicked

    private void jMenuBoletimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuBoletimActionPerformed
        int confirma = JOptionPane.showConfirmDialog(null, "Deseja imprimir o boletim deste abate?", "Atenção", JOptionPane.YES_NO_OPTION);
        Conexao.ReiniciarCon();
        if (confirma == JOptionPane.YES_OPTION) {
            Map p = new HashMap();
            JasperReport relatorio;
            JasperPrint impressao;
            JasperViewer jrviewer = null;

            int nEscala = Integer.parseInt(TbEscalas.getValueAt(tb_escalas.getSelectedRow(), 0).toString());
            try {
                if (nEscala > 0) {
                    p.put("nEscala", nEscala);
                    //p.put("SUBREPORT_DIR", getClass().getResource("/Relatorios/").toString());
                    relatorio = JasperCompileManager.compileReport(new File("").getClass().getResourceAsStream("/Relatorios/boletimAbate.jrxml"));
                    //relatorio = (JasperReport) JRLoader.loadObject(new File("").getClass().getResourceAsStream("/Relatorios/previsaoPagtos.jrxml"));
                    impressao = JasperFillManager.fillReport(relatorio, p, Conexao.getConnection());
                    jrviewer = new JasperViewer(impressao, false);
                    jrviewer.setVisible(true);
                    jrviewer.toFront();

                } else {
                    JOptionPane.showMessageDialog(null, "Verifique o intervalo de datas!");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_jMenuBoletimActionPerformed

    private void jMenuRecebimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuRecebimentoActionPerformed
        if (Recebimentos == null) {
            Recebimentos = new frmRecebimentos();
        }
        int index = tb_escalas.getSelectedRow();
        this.getParent().add(Recebimentos);
        try {
            Recebimentos.txt_dtRecebimento.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(TbEscalas.getValueAt(index, 6).toString()));
        } catch (ParseException ex) {
            Logger.getLogger(frmEscalasAbate.class.getName()).log(Level.SEVERE, null, ex);
        }
        Recebimentos.txt_nEscala.setText(TbEscalas.getValueAt(index, 0).toString());
        Recebimentos.txt_valorTotal.setText(TbEscalas.getValueAt(index, 16).toString());
        Recebimentos.txt_descricao.setText("Venda de " + TbEscalas.getValueAt(index, 7) + " " + TbEscalas.getValueAt(index, 9));
        Recebimentos.ch_previsto.setSelected(true);
        Recebimentos.setVisible(true);
    }//GEN-LAST:event_jMenuRecebimentoActionPerformed

    private void txt_dtEmbarquePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txt_dtEmbarquePropertyChange
        if (txt_nescala.getText().equals("")) {
            txt_dtAbate.setDate(getDataAbate());
            txt_dtPagamento.setDate(getDataPagamento());
        }

    }//GEN-LAST:event_txt_dtEmbarquePropertyChange

    private void txt_PesqnVendaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_PesqnVendaFocusLost
        try {
            if (!txt_PesqnVenda.getText().equals("")) {
                int i = Integer.parseInt(txt_PesqnVenda.getText());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Verifique o campo digitado!", "Erro", 0, FormatarICO.ICObtnSair());
            txt_PesqnVenda.setText(null);
        }
    }//GEN-LAST:event_txt_PesqnVendaFocusLost

    private void tb_escalasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_escalasMousePressed
        TipTextQMinuta(tb_escalas, 7, 8);
    }//GEN-LAST:event_tb_escalasMousePressed

    private void txt_dtPagamentoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txt_dtPagamentoPropertyChange
        getDataPagamento();
    }//GEN-LAST:event_txt_dtPagamentoPropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btn_Salvar;
    javax.swing.JButton btn_editar;
    javax.swing.JButton btn_excluir;
    javax.swing.JButton btn_novo;
    javax.swing.JButton btn_pesquisar3;
    public static javax.swing.JComboBox<String> cb_FazOrigem;
    javax.swing.JComboBox<String> cb_categoria;
    javax.swing.JComboBox<String> cb_cliente;
    javax.swing.JComboBox<String> cb_pesqCategoria;
    public static javax.swing.JComboBox<Object> cb_pesqDestino;
    public static javax.swing.JComboBox<String> cb_pesqFazOrigem;
    javax.swing.JComboBox<String> cb_statusEscala;
    javax.swing.JCheckBox chb_dtEmbarque;
    javax.swing.JCheckBox chb_dtPagto;
    javax.swing.JMenuItem jMenuBoletim;
    javax.swing.JMenuItem jMenuRecebimento;
    javax.swing.JPanel jPanel4;
    javax.swing.JPopupMenu jPopMenu;
    javax.swing.JTable tb_escalas;
    javax.swing.JTextArea txt_Observacao;
    javax.swing.JTextField txt_PesoMorto;
    javax.swing.JTextField txt_PesqnVenda;
    javax.swing.JTextField txt_aReceberT;
    com.toedter.calendar.JDateChooser txt_dtAbate;
    com.toedter.calendar.JDateChooser txt_dtEmbarque;
    com.toedter.calendar.JDateChooser txt_dtPagamento;
    javax.swing.JTextField txt_nVenda;
    javax.swing.JTextField txt_nescala;
    javax.swing.JTextField txt_pesoArT;
    javax.swing.JTextField txt_pesoVTotal;
    javax.swing.JTextField txt_pesoVar;
    com.toedter.calendar.JDateChooser txt_pesqDtFinal;
    com.toedter.calendar.JDateChooser txt_pesqDtInicial;
    javax.swing.JTextField txt_qEmbarque;
    javax.swing.JTextField txt_qEscala;
    javax.swing.JTextField txt_qtEscalaT;
    javax.swing.JTextField txt_reaisArT;
    javax.swing.JTextField txt_rend;
    javax.swing.JTextField txt_rendT;
    javax.swing.JTextField txt_valorAr;
    javax.swing.JTextField txt_valorT;
    javax.swing.JTextField txt_valorTotal;
    // End of variables declaration//GEN-END:variables

    final void popularBeans() {
        EscalasB.setnVenda(Integer.parseInt(txt_nVenda.getText()));
        EscalasB.setOrigem(cb_FazOrigem.getSelectedItem().toString());
        EscalasB.setCliente(cb_cliente.getSelectedItem().toString());
        EscalasB.setDtEmbarque(new SimpleDateFormat("dd/MM/yyyy").format(txt_dtEmbarque.getDate()));
        EscalasB.setDtAbate(new SimpleDateFormat("dd/MM/yyyy").format(txt_dtAbate.getDate()));
        EscalasB.setDtPagamento(new SimpleDateFormat("dd/MM/yyyy").format(txt_dtPagamento.getDate()));
        EscalasB.setQtEscala(Integer.parseInt(txt_qEscala.getText()));
        EscalasB.setCategoria(cb_categoria.getSelectedItem().toString());
        EscalasB.setReaisAr(Corretores.ConverterReaisDouble(txt_valorAr.getText()));
        EscalasB.setPesoMorto(Corretores.ConverQuilosDouble(txt_PesoMorto.getText()));
        EscalasB.setPesoVivoTotal(Corretores.ConverQuilosDouble(txt_pesoVTotal.getText()));
        EscalasB.setQtEmbarcada(Integer.parseInt(txt_qEmbarque.getText()));
        EscalasB.setValorReceber(Corretores.ConverterReaisDouble(txt_valorTotal.getText()));
        EscalasB.setObservacao(txt_Observacao.getText());
        EscalasB.setStatus(cb_statusEscala.getSelectedItem().toString());

    }

    final void limparCampos() {
        txt_Observacao.setText("");
        txt_PesoMorto.setText("0,00 kg");
        txt_dtAbate.setDate(null);
        txt_dtEmbarque.setDate(null);
        txt_dtPagamento.setDate(null);
        txt_nVenda.setText("");
        txt_nescala.setText("");
        txt_pesoVTotal.setText("0,00 kg");
        txt_pesoVar.setText("");
        txt_qEmbarque.setText("0");
        txt_qEscala.setText("");
        txt_rend.setText("0.0%");
        txt_valorAr.setText("R$ 0,00");
        txt_valorTotal.setText("R$ 0,00");
        cb_FazOrigem.setSelectedItem("-");
        cb_categoria.setSelectedItem("-");
        cb_cliente.setSelectedItem("-");
        cb_pesqCategoria.setSelectedItem("-");
        cb_statusEscala.setSelectedItem("-");
    }

    final void habilitarCampos() {
        txt_Observacao.setEnabled(true);
        txt_PesoMorto.setEnabled(true);
        txt_dtAbate.setEnabled(true);
        txt_dtEmbarque.setEnabled(true);
        txt_dtPagamento.setEnabled(true);
        txt_nVenda.setEnabled(true);
        txt_pesoVTotal.setEnabled(true);
        txt_qEmbarque.setEnabled(true);
        txt_qEscala.setEnabled(true);
        txt_valorAr.setEnabled(true);
        cb_FazOrigem.setEnabled(true);
        cb_categoria.setEnabled(true);
        cb_cliente.setEnabled(true);
        cb_statusEscala.setEnabled(true);
    }

    final void desabilitarCampos() {
        txt_Observacao.setEnabled(false);
        txt_PesoMorto.setEnabled(false);
        txt_dtAbate.setEnabled(false);
        txt_dtEmbarque.setEnabled(false);
        txt_dtPagamento.setEnabled(false);
        txt_nVenda.setEnabled(false);
        txt_pesoVTotal.setEnabled(false);
        txt_qEmbarque.setEnabled(false);
        txt_qEscala.setEnabled(false);
        txt_valorAr.setEnabled(false);
        cb_FazOrigem.setEnabled(false);
        cb_categoria.setEnabled(false);
        cb_cliente.setEnabled(false);
        cb_statusEscala.setEnabled(false);
    }

    final void popularBeansPesquisa() {
        if (chb_dtPagto.isSelected()) {
            EscalasB.setTipoDataPesquisa("escalaAbate_dtPagto");
        } else if (chb_dtEmbarque.isSelected()) {
            EscalasB.setTipoDataPesquisa("escalaAbate_dtEmbarque");
        }

        EscalasB.setPesqDataInicial(new SimpleDateFormat("dd/MM/yyyy").format(txt_pesqDtInicial.getDate()));
        EscalasB.setPesqDataFinal(new SimpleDateFormat("dd/MM/yyyy").format(txt_pesqDtFinal.getDate()));
        if (cb_pesqFazOrigem.getSelectedItem().equals("-")) {
            EscalasB.setPesqOrigem("");
        } else {
            EscalasB.setPesqOrigem(cb_pesqFazOrigem.getSelectedItem().toString());
        }
        if (cb_pesqDestino.getSelectedItem().equals("-")) {
            EscalasB.setPesqCliente("");
        } else {
            EscalasB.setPesqOrigem(cb_pesqDestino.getSelectedItem().toString());
        }
        if (cb_pesqCategoria.getSelectedItem().equals("-")) {
            EscalasB.setPesqCategoria("");
        } else {
            EscalasB.setPesqOrigem(cb_pesqCategoria.getSelectedItem().toString());
        }

    }

    final void preencherCampos() {
        int linha = tb_escalas.getSelectedRow();
        txt_nescala.setText(TbEscalas.getValueAt(linha, 0).toString());
        txt_nVenda.setText(TbEscalas.getValueAt(linha, 1).toString());
        cb_FazOrigem.setSelectedItem(TbEscalas.getValueAt(linha, 2).toString());
        cb_cliente.setSelectedItem(TbEscalas.getValueAt(linha, 3).toString());
        try {
            txt_dtEmbarque.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(TbEscalas.getValueAt(linha, 4).toString()));
            txt_dtAbate.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(TbEscalas.getValueAt(linha, 5).toString()));
            txt_dtPagamento.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(TbEscalas.getValueAt(linha, 6).toString()));
        } catch (ParseException ex) {

        }
        txt_qEscala.setText(TbEscalas.getValueAt(linha, 7).toString());
        txt_qEmbarque.setText(TbEscalas.getValueAt(linha, 8).toString());
        cb_categoria.setSelectedItem(TbEscalas.getValueAt(linha, 9).toString());
        txt_valorAr.setText(TbEscalas.getValueAt(linha, 10).toString());
        txt_PesoMorto.setText(TbEscalas.getValueAt(linha, 11).toString());
        txt_pesoVTotal.setText(TbEscalas.getValueAt(linha, 12).toString());
        txt_rend.setText(TbEscalas.getValueAt(linha, 13).toString());
        txt_pesoVar.setText(TbEscalas.getValueAt(linha, 15).toString());
        txt_valorTotal.setText(TbEscalas.getValueAt(linha, 16).toString());
        txt_Observacao.setText(TbEscalas.getValueAt(linha, 18).toString());
        cb_statusEscala.setSelectedItem(TbEscalas.getValueAt(linha, 19));
    }

    private String calcularRendimento() {
        System.out.println(Corretores.ConverQuilosDouble(txt_pesoVTotal.getText()));
        if (Corretores.ConverQuilosDouble(txt_pesoVTotal.getText()) != 0) {
            try {
                double rendimento;
                rendimento = (Corretores.ConverQuilosDouble(txt_PesoMorto.getText()) / Corretores.ConverQuilosDouble(txt_pesoVTotal.getText()));
                return new DecimalFormat("#0.00%").format(rendimento);
            } catch (Exception e) {
                return "0.0%";
            }
        } else {
            return "0.0%";
        }
    }

    private String calcularPesoMedio() {
        try {
            double pesoMedio = (Corretores.ConverQuilosDouble(txt_PesoMorto.getText()) / Integer.parseInt(txt_qEscala.getText())) / 15;
            return new DecimalFormat("#0.0 @").format(pesoMedio);
        } catch (Exception e) {
            return "0.0 @";
        }
    }

    private String calcularValorTotal() {
        try {
            double valor = (Corretores.ConverQuilosDouble(txt_PesoMorto.getText()) / 15) * Corretores.ConverterReaisDouble(txt_valorAr.getText());
            return new DecimalFormat("R$ #,###,##0.00").format(valor);
        } catch (Exception e) {
            return "R$ 0.00";
        }
    }

    private void totalizarTabela() {
        int SomaCabeca = 0;
        double SomaValorT = 0;
        double PesoMortoTotal = 0;
        double PesoVivoTotal = 0;
        double ValorTotalAbatido = 0;
        int SomaCabecaAbatida = 0;
        for (int i = 0; i <= tb_escalas.getRowCount() - 1; i++) {
            SomaCabeca += Integer.parseInt(tb_escalas.getValueAt(i, 7).toString());
            SomaValorT += Double.parseDouble(tb_escalas.getValueAt(i, 16).toString().replace("R$", "").replace(".", "").replace(",", "."));
            if (!tb_escalas.getValueAt(i, 11).equals("0,00 kg")) {
                PesoMortoTotal += Corretores.ConverQuilosDouble(tb_escalas.getValueAt(i, 11).toString());
                PesoVivoTotal += Corretores.ConverQuilosDouble(tb_escalas.getValueAt(i, 12).toString());
                ValorTotalAbatido += Double.parseDouble(tb_escalas.getValueAt(i, 16).toString().replace("R$", "").replace(".", "").replace(",", "."));
                SomaCabecaAbatida += Integer.parseInt(tb_escalas.getValueAt(i, 7).toString());
            }
        }
        txt_qtEscalaT.setText(String.valueOf(SomaCabeca));
        txt_reaisArT.setText(new DecimalFormat("R$ #,##0.00").format((ValorTotalAbatido / PesoMortoTotal) * 15));
        txt_pesoArT.setText(new DecimalFormat("#0.0 @").format((PesoMortoTotal / 15) / SomaCabecaAbatida));
        txt_rendT.setText(new DecimalFormat("#0.00 %").format(PesoMortoTotal / PesoVivoTotal));
        txt_valorT.setText(new DecimalFormat("R$ #,###,##0.00").format(SomaValorT));
        txt_aReceberT.setText(new DecimalFormat("R$ #,###,##0.00").format(0));
    }

    private Date getDataPagamento() {
        Date d = null;
        if (txt_dtAbate.getDate() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(txt_dtAbate.getDate());
            c.add(Calendar.DAY_OF_WEEK, 30);
            int dia = c.get(c.DAY_OF_WEEK);
            String Data;
            switch (dia) {

                case Calendar.SUNDAY:
                    c.add(Calendar.DAY_OF_WEEK, 1);
                    Data = new SimpleDateFormat("dd/MMM/yyyy").format(c.getTime());
                    JOptionPane.showMessageDialog(null, "A previsão do recebimento coincidiu ao Domingo, e será postergada para Segunda-Feira - " + Data, "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
                    break;
                case Calendar.SATURDAY:
                    c.add(Calendar.DAY_OF_WEEK, 2);
                    Data = new SimpleDateFormat("dd/MMM/yyyy").format(c.getTime());
                    JOptionPane.showMessageDialog(null, "A previsão do recebimento coincidiu ao Sábado, e será postergada para Segunda-Feira - " + Data, "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
                    break;
            }
            d = c.getTime();
        }

        return d;

    }

    private Date getDataAbate() {
        Date d = null;
        if (txt_dtEmbarque.getDate() != null) {
            d = txt_dtEmbarque.getDate();
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            c.add(Calendar.DAY_OF_WEEK, 1);
            d = c.getTime();
        }
        return d;
    }

    private String getSQLWhere() {
        String s = "";
        if (cb_pesqFazOrigem.getSelectedIndex() > 0) {
            s += " And escalaAbate_origem like '%" + cb_pesqFazOrigem.getSelectedItem().toString() + "%'";
        }
        if (cb_pesqDestino.getSelectedIndex() > 0) {
            s += " And escalaAbate_destino like '%" + cb_pesqDestino.getSelectedItem().toString() + "%'";
        }
        if (cb_pesqCategoria.getSelectedIndex() > 0) {
            s += " And escalaAbate_categoria like '%" + cb_pesqCategoria.getSelectedItem().toString() + "%'";
        }
        if (!txt_PesqnVenda.getText().equals("")) {
            s += "and escalaAbate_idVenda = " + txt_PesqnVenda.getText();
        }
        return s;
    }

    private void TipTextQMinuta(JTable tabela, int column1, int column2) {
        tabela.setToolTipText(null);
        int[] linha = tabela.getSelectedRows();
        if (linha.length > 1) {
            String texto = "";
            int SomaQEscalado = 0;
            int SomaQEmbarcado = 0;
            for (int i = 0; i < linha.length; i++) {
                SomaQEscalado += (Integer) (tabela.getValueAt(linha[i], column1));
                SomaQEmbarcado += (Integer) (tabela.getValueAt(linha[i], column2));
            }

            texto = "<html><FONT FACE=\"Tahoma\" SIZE=3>"
                    + "Quantidade de Cabeças Escalada é: <B>" + new DecimalFormat("#,###,##0").format(SomaQEscalado) + "</B><br>"
                    + "Quantidade de Cabeças Embarcada é: <B>" + new DecimalFormat("#,###,##0").format(SomaQEmbarcado) + "</B><br>"
                    + "</FONT></html>";
            ToolTipManager.sharedInstance().setInitialDelay(150);
            ToolTipManager.sharedInstance().setDismissDelay(30000);
            tabela.setToolTipText(texto);
        }
    }

    public String weekDay(Calendar cal) {
        return new DateFormatSymbols().getWeekdays()[cal.get(Calendar.DAY_OF_WEEK)];
    }

}
