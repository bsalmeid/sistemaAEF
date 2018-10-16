/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Beans.AnoSafra;
import Beans.ListCultivarBeans;
import Beans.CultivoBeans;
import Beans.CulturaBeans;
import Beans.ListFazendasBeans;
import Beans.ListOperacoesBeans;
import Beans.PlantioBeans;
import DAO.Diversas;
import DAO.PlantioDAO;
import static GUI.Principal.ListFazPermitidas;
import static GUI.Principal.listAnoSafra;
import static GUI.Principal.listCultivares;
import static GUI.Principal.listCultivo;
import static GUI.Principal.listCultura;
import static GUI.Principal.listOperacoes;
import static GUI.Principal.listTalhao;
import Icones.FormatarICO;
import TableModel.TableModelPlantio;
import TableModel.TbPlantioBeans;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author agroa
 */
public class frmPlantio extends javax.swing.JInternalFrame {

    Diversas DiversasD;
    PlantioBeans PlantioB;
    PlantioDAO PlantioD;
    TableModelPlantio TbPlantio;
    CentralizarTabela Centralizar;

    public frmPlantio() {
        initComponents();
        PlantioB = new PlantioBeans();
        PlantioD = new PlantioDAO();
        DiversasD = new Diversas();
        carregarFazPermitidas();
        carregarAnoSafra();
        carregarEpocaCultivo();
        carregarCulturas();
        desabilitarCampos();
        carregarTabelaPlantio();

    }

    private void carregarFazPermitidas() {
        if (ListFazPermitidas == null) {
            ListFazPermitidas = DiversasD.ListpmFazenda();
        }
        ListFazendasBeans l = new ListFazendasBeans();
        DefaultListModel<ListFazendasBeans> lista = new DefaultListModel<>();
        l.setID(0);
        l.setNomeFazenda("-");
        cb_fazenda.addItem(l);
        cb_fazenda1.addItem(l);
        for (ListFazendasBeans list : ListFazPermitidas) {
            cb_fazenda.addItem(list);
            cb_fazenda1.addItem(list);
            lista.addElement(list);
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
        cb_safra1.addItem(l);
        for (AnoSafra list : listAnoSafra) {
            cb_safra.addItem(list);
            cb_safra1.addItem(list);
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
        cb_cultivo1.addItem(l);
        for (CultivoBeans list : listCultivo) {
            cb_cultivo.addItem(list);
            cb_cultivo1.addItem(list);
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
        cb_cultura1.addItem(l);
        for (CulturaBeans list : listCultura) {
            cb_cultura.addItem(list);
            cb_cultura1.addItem(list);
        }
    }

    private void carregarCultivares(JComboBox<ListCultivarBeans> comboCultivares, Integer IdCultura) {
        if (listCultivares == null) {
            listCultivares = DiversasD.LisCultivares();
        }
        comboCultivares.removeAllItems();
        ListCultivarBeans b = new ListCultivarBeans();
        b.setIdCultivar(0);
        b.setCultivar("-");
        comboCultivares.addItem(b);
        for (ListCultivarBeans list : listCultivares) {
            if (Objects.equals(list.getIdCultura(), IdCultura)) {
                comboCultivares.addItem(list);
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

    private JTable carregarTabelaPlantio() {
        tb_plantio.setModel(getTableModelPlantio());
        Centralizar = new CentralizarTabela();
        Centralizar.centralizarTabela(tb_plantio);
        ((DefaultTableCellRenderer) tb_plantio.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        tb_plantio.getColumnModel().getColumn(0).setPreferredWidth(0);// ID Insumo
        tb_plantio.getColumnModel().getColumn(0).setMinWidth(0);
        tb_plantio.getColumnModel().getColumn(0).setMaxWidth(0);
        tb_plantio.getColumnModel().getColumn(1).setPreferredWidth(0);// ID Insumo
        tb_plantio.getColumnModel().getColumn(1).setMinWidth(0);
        tb_plantio.getColumnModel().getColumn(1).setMaxWidth(0);
        return tb_plantio;
    }

    private TableModelPlantio getTableModelPlantio() {
        if (TbPlantio == null) {
            TbPlantio = new TableModelPlantio();
        }
        return TbPlantio;
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
        jMenu_editar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_dataPlantio =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        cb_safra = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        cb_cultivo = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        cb_cultura = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        cb_operacao = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        cb_fazenda = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        txt_talhao = new javax.swing.JTextField();
        txt_areaTalhao = new javax.swing.JTextField();
        javax.swing.JLabel jLabel13 = new javax.swing.JLabel();
        cb_cultivar = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        txt_porcentagem = new javax.swing.JTextField();
        txt_areaPlantada = new javax.swing.JTextField();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        txt_dataColheita = new com.toedter.calendar.JDateChooser();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        cb_equipe = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txt_observacao = new javax.swing.JTextArea();
        btn_novo1 = new javax.swing.JButton();
        btn_Salvar = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();
        javax.swing.JLabel jLabel19 = new javax.swing.JLabel();
        txt_stand = new javax.swing.JTextField();
        txt_populacao = new javax.swing.JTextField();
        txt_espacamento = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        cb_safra1 = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel14 = new javax.swing.JLabel();
        cb_cultivo1 = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel15 = new javax.swing.JLabel();
        cb_cultura1 = new javax.swing.JComboBox<>();
        cb_cultivar1 = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel16 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel17 = new javax.swing.JLabel();
        cb_fazenda1 = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel18 = new javax.swing.JLabel();
        txt_talhao1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_plantio = new javax.swing.JTable();

        jMenu_editar.setText("Editar Lançamento");
        jMenu_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_editarActionPerformed(evt);
            }
        });
        jPopup.add(jMenu_editar);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Plantio");
        setMaximumSize(new java.awt.Dimension(2147483647, 10000));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setFocusCycleRoot(true);
        jPanel1.setFocusTraversalPolicyProvider(true);
        jPanel1.setPreferredSize(new java.awt.Dimension(250, 548));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Data Plantio");

        txt_dataPlantio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_dataPlantioFocusGained(evt);
            }
        });
        txt_dataPlantio.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txt_dataPlantioPropertyChange(evt);
            }
        });

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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Fazenda");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Nº Talhão");

        txt_talhao.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_talhao.setEnabled(false);
        txt_talhao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_talhaoFocusLost(evt);
            }
        });

        txt_areaTalhao.setEditable(false);
        txt_areaTalhao.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_areaTalhao.setFocusable(false);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Cultivar");

        cb_cultivar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_cultivarItemStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Area Plant.");

        txt_porcentagem.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_porcentagem.setEnabled(false);
        txt_porcentagem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_porcentagemFocusGained(evt);
            }
        });
        txt_porcentagem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_porcentagemKeyReleased(evt);
            }
        });

        txt_areaPlantada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_areaPlantada.setEnabled(false);
        txt_areaPlantada.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_areaPlantadaFocusGained(evt);
            }
        });
        txt_areaPlantada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_areaPlantadaKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Colheita");

        txt_dataColheita.setEnabled(false);
        txt_dataColheita.setFocusable(false);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Equipe");

        cb_equipe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Máquina Própia", "Ivanir" }));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Observação");

        txt_observacao.setColumns(20);
        txt_observacao.setRows(5);
        txt_observacao.setEnabled(false);
        jScrollPane3.setViewportView(txt_observacao);

        btn_novo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/inserir_40x40.png"))); // NOI18N
        btn_novo1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn_novo1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_novo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novo1ActionPerformed(evt);
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

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Stand Inicial");

        txt_stand.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_stand.setEnabled(false);
        txt_stand.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_standFocusLost(evt);
            }
        });
        txt_stand.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_standKeyReleased(evt);
            }
        });

        txt_populacao.setEditable(false);
        txt_populacao.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_populacao.setFocusable(false);

        txt_espacamento.setEditable(false);
        txt_espacamento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_espacamento.setText("45");
        txt_espacamento.setFocusable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txt_porcentagem, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(txt_areaPlantada))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cb_cultura, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txt_talhao, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(1, 1, 1)
                                        .addComponent(txt_areaTalhao))
                                    .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cb_cultivar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cb_operacao, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txt_stand, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(txt_espacamento, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(txt_populacao))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(txt_dataPlantio, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(cb_safra, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(cb_cultivo, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_novo1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_Salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(txt_dataColheita, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(cb_equipe, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(txt_dataPlantio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_safra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_cultivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_cultura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cb_operacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cb_cultivar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txt_stand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_populacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_espacamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_talhao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_areaTalhao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_porcentagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_areaPlantada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel8)
                    .addComponent(txt_dataColheita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cb_equipe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_novo1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Safra");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Cultivo");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Cultura");

        cb_cultura1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_cultura1ItemStateChanged(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Cultivar");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Fazenda");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Nº Talhão");

        txt_talhao1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jButton1.setText("Pesquisar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_safra1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_cultivo1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_cultura1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_cultivar1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_fazenda1, 0, 146, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_talhao1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton1)
                    .addComponent(txt_talhao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(cb_fazenda1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(cb_cultivar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(cb_cultura1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(cb_cultivo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(cb_safra1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap())
        );

        tb_plantio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tb_plantio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_plantioMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tb_plantioMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tb_plantio);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb_culturaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_culturaItemStateChanged
        carregarOperacoes(getIdCultura(cb_cultura), cb_operacao);
        carregarCultivares(cb_cultivar, getIdCultura(cb_cultura));
    }//GEN-LAST:event_cb_culturaItemStateChanged

    private void txt_talhaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_talhaoFocusLost
        txt_areaTalhao.setText(new DecimalFormat("#,##0.00 ha").format(getAreaTalhao(txt_talhao.getText())));
    }//GEN-LAST:event_txt_talhaoFocusLost

    private void btn_novo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novo1ActionPerformed
        btn_Salvar.setEnabled(true);
        btn_editar.setEnabled(false);
        habilitarCampos();
        limparCampos();
        txt_dataPlantio.requestFocus();
    }//GEN-LAST:event_btn_novo1ActionPerformed

    private void btn_SalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalvarActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(null, "Deseja registrar este plantio?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION) {
            popularBeans();
            if (verificarBeans(PlantioB)) {
                if (PlantioD.inserirPlantio(PlantioB)) {
                    PlantioD.atulizarTabelaPlantio(PlantioB.getIdSafra(), PlantioB.getIdCultivo(), PlantioB.getIdFazenda(), TbPlantio, SQLIdFazendas());
                    limparCampos();
                    desabilitarCampos();
                }
            }
        }
    }//GEN-LAST:event_btn_SalvarActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja editar esta Escala de Gado?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION) {
            if (PlantioB.getID() > 0) {
                popularBeans();
                if (verificarBeans(PlantioB) == true) {
                    if (PlantioD.editarPlantio(PlantioB)) {
                        limparCampos();
                        desabilitarCampos();
                        btn_editar.setEnabled(false);
                        btn_excluir.setEnabled(false);
                        btn_Salvar.setEnabled(false);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione o registro que deseja editar!", "Erro", 0, FormatarICO.ICObtnSair());
            }
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        int excluir = JOptionPane.showConfirmDialog(null, "Deseja excluir esta Escala de Gado?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (excluir == JOptionPane.YES_OPTION) {
            if (PlantioD.deletarRegistro(PlantioB.getID()) == true) {
                limparCampos();
                desabilitarCampos();
                btn_editar.setEnabled(false);
                btn_excluir.setEnabled(false);
            }
        }
    }//GEN-LAST:event_btn_excluirActionPerformed

    private void cb_cultura1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_cultura1ItemStateChanged
        carregarCultivares(cb_cultivar1, getIdCultura(cb_cultura1));
    }//GEN-LAST:event_cb_cultura1ItemStateChanged

    private void txt_dataPlantioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txt_dataPlantioPropertyChange
        txt_dataColheita.setDate(getPrevisaoColheita(txt_dataPlantio.getDate()));
    }//GEN-LAST:event_txt_dataPlantioPropertyChange

    private void cb_cultivarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_cultivarItemStateChanged
        txt_dataColheita.setDate(getPrevisaoColheita(txt_dataPlantio.getDate()));
    }//GEN-LAST:event_cb_cultivarItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        PlantioD.atulizarTabelaPlantio(getIdSafra(cb_safra1), getIdCultivo(cb_cultivo1), getIdFazenda(cb_fazenda1), TbPlantio, SQLIdFazendas());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_porcentagemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_porcentagemKeyReleased
        double areaPlantada = Corretores.ConverterStringDouble(txt_areaTalhao.getText()) * Corretores.ConverterStringDouble(txt_porcentagem.getText()) / 100;
        txt_areaPlantada.setText(new DecimalFormat("#,##0.00 ha").format(areaPlantada));
    }//GEN-LAST:event_txt_porcentagemKeyReleased

    private void txt_porcentagemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_porcentagemFocusGained
        txt_porcentagem.selectAll();
    }//GEN-LAST:event_txt_porcentagemFocusGained

    private void txt_areaPlantadaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_areaPlantadaFocusGained
        txt_areaPlantada.selectAll();
    }//GEN-LAST:event_txt_areaPlantadaFocusGained

    private void txt_areaPlantadaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_areaPlantadaKeyReleased
        double porcentagem = 0;
        try {
            porcentagem = (Corretores.ConverterStringDouble(txt_areaPlantada.getText()) / Corretores.ConverterStringDouble(txt_areaTalhao.getText()));
        } catch (Exception e) {
            porcentagem = 0;
        }
        txt_porcentagem.setText(new DecimalFormat("#,##0.00 %").format(porcentagem));
    }//GEN-LAST:event_txt_areaPlantadaKeyReleased

    private void jMenu_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_editarActionPerformed
        int linha = tb_plantio.getSelectedRow();
        if (linha >= 0) {
            int IdPlantio = (Integer) TbPlantio.getValueAt(linha, 0);
            PlantioD.buscarLancamentoPlantio(PlantioB, IdPlantio);
            preencherFormulario();
            habilitarCampos();
            btn_editar.setEnabled(true);
            btn_excluir.setEnabled(true);
        }
    }//GEN-LAST:event_jMenu_editarActionPerformed

    private void tb_plantioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_plantioMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            if (jPopup.isVisible() == true) {
                jPopup.setVisible(false);
            } else {
                jPopup.setVisible(true);
                jPopup.show(this, 0, 0);
                jPopup.setLocation(evt.getLocationOnScreen());
            }
        } else {
            TipTextTbCompras();
            limparCampos();
            desabilitarCampos();
        }
    }//GEN-LAST:event_tb_plantioMouseClicked

    private void txt_standKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_standKeyReleased
        txt_populacao.setText(new DecimalFormat("#,###,##0 pl").format(calcularPopulacao()));
    }//GEN-LAST:event_txt_standKeyReleased

    private void txt_dataPlantioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_dataPlantioFocusGained

    }//GEN-LAST:event_txt_dataPlantioFocusGained

    private void txt_standFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_standFocusLost
        txt_populacao.setText(new DecimalFormat("#,###,##0 pl").format(calcularPopulacao()));
    }//GEN-LAST:event_txt_standFocusLost

    private void tb_plantioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_plantioMousePressed
        
    }//GEN-LAST:event_tb_plantioMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btn_Salvar;
    javax.swing.JButton btn_editar;
    javax.swing.JButton btn_excluir;
    javax.swing.JButton btn_novo1;
    private javax.swing.JComboBox<ListCultivarBeans> cb_cultivar;
    private javax.swing.JComboBox<ListCultivarBeans> cb_cultivar1;
    private javax.swing.JComboBox<Beans.CultivoBeans> cb_cultivo;
    private javax.swing.JComboBox<Beans.CultivoBeans> cb_cultivo1;
    private javax.swing.JComboBox<Beans.CulturaBeans> cb_cultura;
    private javax.swing.JComboBox<Beans.CulturaBeans> cb_cultura1;
    private javax.swing.JComboBox<String> cb_equipe;
    private javax.swing.JComboBox<ListFazendasBeans> cb_fazenda;
    private javax.swing.JComboBox<ListFazendasBeans> cb_fazenda1;
    javax.swing.JComboBox<ListOperacoesBeans> cb_operacao;
    private javax.swing.JComboBox<Beans.AnoSafra> cb_safra;
    private javax.swing.JComboBox<Beans.AnoSafra> cb_safra1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuItem jMenu_editar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopup;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tb_plantio;
    private javax.swing.JTextField txt_areaPlantada;
    private javax.swing.JTextField txt_areaTalhao;
    private com.toedter.calendar.JDateChooser txt_dataColheita;
    private com.toedter.calendar.JDateChooser txt_dataPlantio;
    private javax.swing.JTextField txt_espacamento;
    private javax.swing.JTextArea txt_observacao;
    private javax.swing.JTextField txt_populacao;
    private javax.swing.JTextField txt_porcentagem;
    private javax.swing.JTextField txt_stand;
    private javax.swing.JTextField txt_talhao;
    private javax.swing.JTextField txt_talhao1;
    // End of variables declaration//GEN-END:variables

    private Double calcularPopulacao() {
        Double populacao = Corretores.ConverterStringDouble(txt_stand.getText()) * (1000000 / Corretores.ConverterStringDouble(txt_espacamento.getText()));
        return populacao;
    }

    private Date getPrevisaoColheita(Date dataPlantio) {
        Date dtColheita = null;
        if (dataPlantio != null && cb_cultivar.getSelectedIndex() > 0) {
            Calendar c = Calendar.getInstance();
            c.setTime(dataPlantio);
            Integer cicloCultivar = cb_cultivar.getModel().getElementAt(cb_cultivar.getSelectedIndex()).getCiclo();
            c.add(Calendar.DAY_OF_WEEK, cicloCultivar);
            dtColheita = c.getTime();
        }

        return dtColheita;
    }

    private Integer getIdOperacao(JComboBox<ListOperacoesBeans> comboBox) {
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

    private Integer getIdCultivar(JComboBox<ListCultivarBeans> comboBox) {
        int id = 0;
        if (comboBox.getSelectedIndex() > 0) {
            id = comboBox.getItemAt(comboBox.getSelectedIndex()).getIdCultivar();
        }
        return id;
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

    private void setJComboBoxCultivar(JComboBox<ListCultivarBeans> cb, Integer IdCultivar) {
        for (int i = 0; i < cb.getModel().getSize(); i++) {
            if (cb.getModel().getElementAt(i).getIdCultivar() == IdCultivar) {
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

    private void setJComboBoxFazenda(JComboBox<ListFazendasBeans> cb, Integer IdFazenda) {
        for (int i = 0; i < cb.getModel().getSize(); i++) {
            if (cb.getModel().getElementAt(i).getID() == IdFazenda) {
                cb.setSelectedIndex(i);
            }
        }
    }

    private Integer getIdFazenda(JComboBox<ListFazendasBeans> comboBox) {
        if (comboBox.getSelectedIndex() > 0) {
            return comboBox.getItemAt(comboBox.getSelectedIndex()).getID();
        }
        return 0;
    }

    private Double getAreaTalhao(String nTalhao) {
        double area = 0.00;
        if (listTalhao == null) {
            listTalhao = DiversasD.ListTalhao();
        }
        for (int i = 0; i < listTalhao.size(); i++) {
            //System.out.println("Teset getArea For " + getIdFazenda(cb_fazenda));

            int idFazendaSel = getIdFazenda(cb_fazenda);
            int idFazenda = listTalhao.get(i).getIdFazenda();
            String nomeTalhao = listTalhao.get(i).getTalhao();
            if (idFazenda == idFazendaSel && nomeTalhao.equals(nTalhao)) {
                return listTalhao.get(i).getArea();
            }
        }
        //System.out.println("Area");
        return area;
    }

    private Integer getIdTalhao(String Talhao, Integer IdFazendaSel) {
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

    private void habilitarCampos() {
        txt_dataPlantio.setEnabled(true);
        cb_safra.setEnabled(true);
        cb_cultivo.setEnabled(true);
        cb_cultura.setEnabled(true);
        cb_operacao.setEnabled(true);
        cb_cultivar.setEnabled(true);
        cb_fazenda.setEnabled(true);
        cb_equipe.setEnabled(true);
        txt_stand.setEnabled(true);
        txt_talhao.setEnabled(true);
        txt_porcentagem.setEnabled(true);
        txt_areaPlantada.setEnabled(true);
        txt_observacao.setEnabled(true);
    }

    private void limparCampos() {
        txt_dataPlantio.setDate(null);
        cb_safra.setSelectedIndex(0);
        cb_cultivo.setSelectedIndex(0);
        cb_cultura.setSelectedIndex(0);
        cb_operacao.setSelectedIndex(0);
        cb_cultivar.setSelectedIndex(0);
        cb_fazenda.setSelectedIndex(0);
        txt_dataColheita.setDate(null);
        txt_areaTalhao.setText("0,00 ha");
        txt_talhao.setText("");
        txt_porcentagem.setText("0,00 %");
        txt_areaPlantada.setText("0.00 ha");
        cb_equipe.setSelectedIndex(0);
        txt_stand.setText("0");
        txt_populacao.setText("0 pl");
    }

    private void popularBeans() {
        try {
            PlantioB.setDataPlantio(new SimpleDateFormat("dd/MM/yyyy").format(txt_dataPlantio.getDate()));
        } catch (Exception e) {
            PlantioB.setDataPlantio(null);
        }
        PlantioB.setIdOrdemServico(0);
        PlantioB.setIdSafra(getIdSafra(cb_safra));
        PlantioB.setIdCultivo(getIdCultivo(cb_cultivo));
        PlantioB.setIdCultura(getIdCultura(cb_cultura));
        PlantioB.setIdOperacao(getIdOperacao(cb_operacao));
        PlantioB.setIdCultivar(getIdCultivar(cb_cultivar));
        PlantioB.setStandIncial(Corretores.ConverterStringDouble(txt_stand.getText()));
        PlantioB.setIdFazenda(getIdFazenda(cb_fazenda));
        PlantioB.setIdTalhao(getIdTalhao(txt_talhao.getText(), getIdFazenda(cb_fazenda)));
        PlantioB.setAreaTalhao(getAreaTalhao(txt_talhao.getText()));
        PlantioB.setPorcentagemPlantada(Corretores.ConverterStringDouble(txt_porcentagem.getText()));
        PlantioB.setAreaPlantada(Corretores.ConverterStringDouble(txt_areaPlantada.getText()));
        PlantioB.setDataColheitaPrevista(new SimpleDateFormat("dd/MM/yyyy").format(txt_dataColheita.getDate()));
        PlantioB.setIdEquipePlantio(cb_equipe.getSelectedIndex());
        PlantioB.setEquipePlantio(cb_equipe.getSelectedItem().toString());
        PlantioB.setObservacao(txt_observacao.getText());

    }

    private Boolean verificarBeans(PlantioBeans b) {
        if (PlantioB.getDataPlantio() == null) {
            JOptionPane.showMessageDialog(null, "Selecione o campo Data de Plantio!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }
        if (Corretores.verificaDataMensal(txt_dataPlantio.getDate()) == false) {
            return false;
        }
        if (b.getIdSafra() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione o campo Safra!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }
        if (b.getIdCultivo() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione o campo Cultivo!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }
        if (b.getIdOperacao() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione o campo Operação!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }
        if (b.getIdCultura() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione o campo Cultura!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }
        if (b.getIdCultivar() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione o campo Cultivar!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }
        if (b.getIdFazenda() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione o campo Fazenda", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }
        if (b.getIdTalhao() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Talhão!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }
        if (b.getIdEquipePlantio() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione o campo Equipe de Plantio!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }
        if (b.getAreaPlantada() == 0.00) {
            JOptionPane.showMessageDialog(null, "Verifique o campo área Plantada!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }
        if (b.getAreaTalhao() == 0.00) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Talhão!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }
        if (b.getStandIncial() == 0.00) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Stand Inicial!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }
        return true;
    }

    private void desabilitarCampos() {
        txt_dataPlantio.setEnabled(false);
        cb_safra.setEnabled(false);
        cb_cultivo.setEnabled(false);
        cb_cultura.setEnabled(false);
        cb_operacao.setEnabled(false);
        cb_cultivar.setEnabled(false);
        cb_fazenda.setEnabled(false);
        txt_dataColheita.setEnabled(false);
        cb_equipe.setEnabled(false);
        btn_Salvar.setEnabled(false);
        btn_editar.setEnabled(false);
        btn_excluir.setEnabled(false);
        txt_stand.setEnabled(false);
        txt_talhao.setEnabled(false);
        txt_porcentagem.setEnabled(false);
        txt_areaPlantada.setEnabled(false);
        txt_observacao.setEnabled(false);
    }

    private void preencherFormulario() {
        try {
            txt_dataPlantio.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(PlantioB.getDataPlantio()));
            txt_dataColheita.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(PlantioB.getDataColheitaPrevista()));
        } catch (ParseException ex) {
            Logger.getLogger(frmPlantio.class.getName()).log(Level.SEVERE, null, ex);
        }
        setJComboBoxAnoSafra(cb_safra, PlantioB.getIdSafra());
        setJComboBoxCultivo(cb_cultivo, PlantioB.getIdCultivo());
        setJComboBoxCultura(cb_cultura, PlantioB.getIdCultura());
        setJComboBoxOperacoes(cb_operacao, PlantioB.getIdOperacao());
        setJComboBoxCultivar(cb_cultivar, PlantioB.getIdCultivar());
        setJComboBoxFazenda(cb_fazenda, PlantioB.getIdFazenda());
        txt_stand.setText(new DecimalFormat("#,##0.0").format(PlantioB.getStandIncial()));
        txt_talhao.setText(PlantioB.getNomeTalhao());
        txt_areaTalhao.setText(new DecimalFormat("#,##0.00 ha").format(PlantioB.getAreaTalhao()));
        txt_porcentagem.setText(new DecimalFormat("##0.00 %").format(PlantioB.getPorcentagemPlantada() / 100));
        txt_areaPlantada.setText(new DecimalFormat("#,##0.00 ha").format(PlantioB.getAreaPlantada()));
        cb_equipe.setSelectedIndex(PlantioB.getIdEquipePlantio());
        txt_observacao.setText(PlantioB.getObservacao());
    }

    private String SQLIdFazendas() {
        String s = "";
        if (cb_fazenda1.getSelectedIndex() == 0) {
            s = DiversasD.getIdFazendaPermitida();
        } else {
            s = "(" + getIdFazenda(cb_fazenda1).toString() + ")";
            if (!txt_talhao1.getText().equals("")) {
                int idTalhao = getIdTalhao(txt_talhao1.getText(), getIdFazenda(cb_fazenda1));
                if (idTalhao > 0) {
                    s += " and id_talhao = " + getIdTalhao(txt_talhao1.getText(), getIdFazenda(cb_fazenda1)).toString();
                } else {
                    JOptionPane.showMessageDialog(null, "Talhão não localizado!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
                }
            }
        }
        if (cb_cultivar1.getSelectedIndex() > 0) {
            s += " and id_cultivar = " + getIdCultivar(cb_cultivar1).toString();
        }

        return s;
    }

    private void TipTextTbCompras() {
        tb_plantio.setToolTipText(null);
        int[] linha = tb_plantio.getSelectedRows();
        if (linha.length > 1) {
            String texto = "";
            List<TbPlantioBeans> lista = new ArrayList<>();
            texto = "<html><FONT FACE=\"Tahoma\" SIZE=3>";
            List<String> cultivares = new ArrayList();
            for (int i = 0; i < linha.length; i++) {
                lista.add(TbPlantio.getBeans(linha[i]));
                if (!cultivares.contains(TbPlantio.getBeans(linha[i]).getCultivar())) {
                    cultivares.add(TbPlantio.getBeans(linha[i]).getCultivar());
                }
            }
            for (int i = 0; i < cultivares.size(); i++) {
                double somaArea = 0.00;
                for (int y = 0; y < lista.size(); y++) {
                    if (cultivares.get(i).equals(lista.get(y).getCultivar())) {
                        somaArea += lista.get(y).getAreaPlantada();
                    }
                }
                texto += "Área Plantada - <B> " + cultivares.get(i) + " : " + Corretores.ConverterParaDecimal(somaArea, "ha") + "</B><br>";
            }
            texto += "</FONT></html>";
            ToolTipManager.sharedInstance().setInitialDelay(150);
            ToolTipManager.sharedInstance().setDismissDelay(30000);
            tb_plantio.setToolTipText(texto);
        }
    }

}
