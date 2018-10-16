package GUI;

import Beans.CidadesBeans;
import Beans.EstadosBeans;
import Beans.TalhaoBeans;
import Beans.PropriedadesBeans;
import Controller.PropriedadesController;
import DAO.DiversasHibernate;
import DAO.PropriedadesDAO;
import Icones.FormatarICO;
import TableModel.TableModelCadFazenda;
import TableModel.TableModelCadTalhao;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import Utilitarios.ValidarPermissoes;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public final class FrmCadFazendas extends javax.swing.JInternalFrame {

    PropriedadesBeans FazendaB;
    PropriedadesController FazendaC;
    PropriedadesDAO FazendaD;
    TableModelCadTalhao TbTalhao;
    List<EstadosBeans> listaEstados;
    TableModelCadFazenda TbFazenda;
    Integer linhaSelecionada = null;

    public FrmCadFazendas() {
        initComponents();
        FazendaD = new PropriedadesDAO();
        FazendaB = new PropriedadesBeans();
        FazendaC = new PropriedadesController();
        carregarTabela();
        carregarTabelaTalhao();
        getComboBoxEstados();
    }

    public JTable carregarTabela() {
        tb_fazendas.setModel(getTableModelFazendas());
        ((DefaultTableCellRenderer) tb_fazendas.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        CentralizarTabela Centralizar = new CentralizarTabela();
        Centralizar.centralizarTabela(tb_fazendas);
        TbFazenda.addLista(FazendaD.consultarFazendas());
        return tb_fazendas;
    }

    public JTable carregarTabelaTalhao() {
        tb_talhao.setModel(getTableModelTalhao());
        ((DefaultTableCellRenderer) tb_talhao.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        CentralizarTabela Centralizar = new CentralizarTabela();
        Centralizar.centralizarTabela(tb_talhao);

        for (int c = 0; c < TbTalhao.getColumnCount(); c++) {
            if (c == TbTalhao.PLUVIOMETRO || c == TbTalhao.FAZENDA) {
                tb_talhao.getColumnModel().getColumn(c).setMaxWidth(0);
                tb_talhao.getColumnModel().getColumn(c).setMinWidth(0);
                tb_talhao.getColumnModel().getColumn(c).setPreferredWidth(0);
                tb_talhao.getColumnModel().getColumn(c).setResizable(false);
            }
        }
        return tb_talhao;
    }

    public TableModelCadTalhao getTableModelTalhao() {
        if (TbTalhao == null) {
            TbTalhao = new TableModelCadTalhao();
        }
        return TbTalhao;
    }

    public TableModelCadFazenda getTableModelFazendas() {
        if (TbFazenda == null) {
            TbFazenda = new TableModelCadFazenda();
        }
        return TbFazenda;
    }

    public void getComboBoxEstados() {
        if (listaEstados == null) {
            listaEstados = new ArrayList<>();
            listaEstados.add(new EstadosBeans(0, "-", "-"));
            listaEstados.addAll(DiversasHibernate.getListaEstados());
        }
        for (EstadosBeans e : listaEstados) {
            cb_estado.addItem(e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.ButtonGroup buttonGroup1 = new javax.swing.ButtonGroup();
        tb_pane = new javax.swing.JTabbedPane();
        javax.swing.JScrollPane jScrollPane4 = new javax.swing.JScrollPane();
        javax.swing.JPanel jPanel3 = new javax.swing.JPanel();
        org.jdesktop.swingx.JXTaskPane jXTaskPane1 = new org.jdesktop.swingx.JXTaskPane();
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        txt_Fazenda = new javax.swing.JTextField();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        cb_tipo = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        cb_status = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        txt_endereco = new javax.swing.JTextField();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        txt_areaTotal = new javax.swing.JTextField();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        txt_ins = new javax.swing.JTextField();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        txt_observacao = new javax.swing.JTextField();
        javax.swing.JLabel jLabel14 = new javax.swing.JLabel();
        txt_areaMapeada = new javax.swing.JTextField();
        cb_estado = new componentes.UJComboBox();
        cb_cidade = new componentes.UJComboBox();
        javax.swing.JPanel jPanel5 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        txt_talhao = new javax.swing.JTextField();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        txt_areaTalhao = new javax.swing.JTextField();
        javax.swing.JLabel jLabel13 = new javax.swing.JLabel();
        cb_cultivo = new javax.swing.JComboBox<>();
        btn_add = new javax.swing.JButton();
        btn_del = new javax.swing.JButton();
        btn_editarT = new javax.swing.JButton();
        ch_status = new javax.swing.JCheckBox();
        ch_mecanizavel = new javax.swing.JCheckBox();
        javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        tb_talhao = new javax.swing.JTable();
        javax.swing.JSeparator jSeparator1 = new javax.swing.JSeparator();
        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        btn_novo = new javax.swing.JButton();
        btn_cadastrar = new javax.swing.JButton();
        txt_pesquisar = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        javax.swing.JSeparator jSeparator2 = new javax.swing.JSeparator();
        javax.swing.JPanel jPanel4 = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        tb_fazendas = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Cadastro de Propriedades");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setNormalBounds(new java.awt.Rectangle(0, 0, 125, 125));
        setPreferredSize(new java.awt.Dimension(975, 660));

        tb_pane.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tb_pane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tb_paneStateChanged(evt);
            }
        });

        jXTaskPane1.setTitle("Dados da Propriedade");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Código");

        txt_codigo.setEditable(false);
        txt_codigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_codigo.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Nome");

        txt_Fazenda.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Tipo");

        cb_tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Arrendada", "Própria" }));
        cb_tipo.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Status");

        cb_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ativo", "Inativo" }));
        cb_status.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Cidade");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Estado");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Endereço");

        txt_endereco.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Área");

        txt_areaTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_areaTotal.setEnabled(false);
        txt_areaTotal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_areaTotalFocusLost(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Ins. Est");

        txt_ins.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_ins.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Observação");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Área Mapeada");

        txt_areaMapeada.setEditable(false);
        txt_areaMapeada.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        cb_estado.setAutocompletar(true);
        cb_estado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_estadoItemStateChanged(evt);
            }
        });

        cb_cidade.setAutocompletar(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(8, 8, 8)
                        .addComponent(txt_areaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14)
                        .addGap(8, 8, 8)
                        .addComponent(txt_areaMapeada, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_ins, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_status, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(107, 107, 107))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_observacao))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_endereco))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(8, 8, 8)
                                .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_Fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_estado, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_cidade, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)))
                        .addGap(19, 19, 19))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(txt_Fazenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cb_tipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cb_estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_cidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_endereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(txt_areaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txt_areaMapeada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ins, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(cb_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel9)
                    .addComponent(txt_observacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jXTaskPane1.getContentPane().add(jPanel1);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Talhão");

        txt_talhao.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Área(ha)");

        txt_areaTalhao.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_areaTalhao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_areaTalhaoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_areaTalhaoFocusLost(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Cultivo");

        cb_cultivo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Anual", "Perene" }));

        btn_add.setText("Add");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        btn_del.setText("Del");
        btn_del.setEnabled(false);

        btn_editarT.setText("Editar");
        btn_editarT.setEnabled(false);
        btn_editarT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarTActionPerformed(evt);
            }
        });

        ch_status.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_status.setText("Status");

        ch_mecanizavel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_mecanizavel.setText("Mecânizavel");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_talhao, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_areaTalhao, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_cultivo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ch_mecanizavel)
                .addGap(18, 18, 18)
                .addComponent(ch_status)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_add)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_editarT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_del)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(txt_areaTalhao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)
                        .addComponent(cb_cultivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_add)
                        .addComponent(btn_editarT)
                        .addComponent(btn_del)
                        .addComponent(ch_status)
                        .addComponent(ch_mecanizavel))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(txt_talhao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        tb_talhao.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_talhao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_talhaoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_talhao);

        btn_novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/inserir.png"))); // NOI18N
        btn_novo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novoActionPerformed(evt);
            }
        });

        btn_cadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/salvar.png"))); // NOI18N
        btn_cadastrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cadastrar.setEnabled(false);
        btn_cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cadastrarActionPerformed(evt);
            }
        });

        txt_pesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar.png"))); // NOI18N
        txt_pesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txt_pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_pesquisarActionPerformed(evt);
            }
        });

        btn_editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/editar.png"))); // NOI18N
        btn_editar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_editar.setEnabled(false);
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_editar)
                    .addComponent(txt_pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cadastrar)
                    .addComponent(btn_novo)))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jXTaskPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jXTaskPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        jScrollPane4.setViewportView(jPanel3);

        tb_pane.addTab("Cadastro", jScrollPane4);

        tb_fazendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Status", "Nome", "Tipo", "Área", "Cidade", "Estado", "Insc. Estadual"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_fazendas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_fazendasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_fazendas);
        if (tb_fazendas.getColumnModel().getColumnCount() > 0) {
            tb_fazendas.getColumnModel().getColumn(0).setResizable(false);
            tb_fazendas.getColumnModel().getColumn(0).setPreferredWidth(30);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
                .addContainerGap())
        );

        tb_pane.addTab("Consulta", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tb_pane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tb_pane)
        );

        setBounds(0, 0, 975, 663);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed
        limparCampos();
        btn_cadastrar.setEnabled(true);
        habilitarCampos();
        int Ultimo = 0;
        txt_codigo.setText(FazendaC.controleBuscarUltimo(Ultimo).toString());
        btn_editar.setEnabled(false);
    }//GEN-LAST:event_btn_novoActionPerformed

    private void btn_cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cadastrarActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(null, "Deseja Cadastrar esta Propriedade?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION) {
            popularBeans(FazendaB);
            if (verificarBeans(FazendaB) && ValidarPermissoes.validarPermissaoInsert(FrmCadFazendas.class.getSimpleName())) {
                if (FazendaD.cadastrarFazendas(FazendaB)) {
                    limparCampos();
                    btn_cadastrar.setEnabled(false);
                    desabilitarCampos();
                }
            }

        }
    }//GEN-LAST:event_btn_cadastrarActionPerformed

    private void txt_pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_pesquisarActionPerformed

    }//GEN-LAST:event_txt_pesquisarActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        int atualizar = JOptionPane.showConfirmDialog(null, "Deseja atualizar o cadastro desta propriedade?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (atualizar == JOptionPane.YES_OPTION) {
            popularBeans(FazendaB);
            if (verificarBeans(FazendaB) && ValidarPermissoes.validarPermissaoUpdate(FrmCadFazendas.class.getSimpleName())) {
                if (FazendaD.editarFazendas(FazendaB)) {
                    limparCampos();
                    btn_editar.setEnabled(false);
                    desabilitarCampos();
                }
            }
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        addTalhao();
    }//GEN-LAST:event_btn_addActionPerformed

    private void cb_estadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_estadoItemStateChanged
        if (evt.getItem() != null) {
            EstadosBeans e = ((EstadosBeans) evt.getItem());
            cb_cidade.clear();
            cb_cidade.addItem(new CidadesBeans(0, null, "-"));
            if (e.getListaCidades() != null) {
                for (CidadesBeans c : e.getListaCidades()) {
                    cb_cidade.addItem(c);
                }
            }
        } else {
            cb_cidade.clear();
            cb_cidade.addItem(new CidadesBeans(0, null, "-"));
        }
    }//GEN-LAST:event_cb_estadoItemStateChanged

    private void txt_areaTotalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_areaTotalFocusLost
        txt_areaTotal.setText(Corretores.ConverterParaDecimal(txt_areaTotal.getText(), "ha"));
    }//GEN-LAST:event_txt_areaTotalFocusLost

    private void txt_areaTalhaoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_areaTalhaoFocusGained
        txt_areaTalhao.selectAll();
    }//GEN-LAST:event_txt_areaTalhaoFocusGained

    private void txt_areaTalhaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_areaTalhaoFocusLost
        txt_areaTalhao.setText(Corretores.ConverterParaDecimal(txt_areaTalhao.getText(), "ha"));
    }//GEN-LAST:event_txt_areaTalhaoFocusLost

    private void tb_fazendasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_fazendasMouseClicked
        if (evt.getClickCount() == 2) {
            int consultar = JOptionPane.showConfirmDialog(null, "Deseja Consultar esta Propriedade?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (consultar == JOptionPane.YES_OPTION) {
                FazendaB = FazendaD.buscarFazendas((Integer) TbFazenda.getValueAt(tb_fazendas.getSelectedRow(), 0));
                preencherFormulario(FazendaB);
                tb_pane.setSelectedIndex(0);
                habilitarCampos();
                btn_editar.setEnabled(true);
                btn_cadastrar.setEnabled(false);
            }
        }
    }//GEN-LAST:event_tb_fazendasMouseClicked

    private void tb_paneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tb_paneStateChanged
        if (tb_pane.getSelectedIndex() == 1) {
            TbFazenda.limpar();
            TbFazenda.addLista(FazendaD.consultarFazendas());
        }
    }//GEN-LAST:event_tb_paneStateChanged

    private void tb_talhaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_talhaoMouseClicked
        if (evt.getClickCount() == 2) {
            linhaSelecionada = tb_talhao.getSelectedRow();
            preencherCamposTalhao(TbTalhao.getBeans(linhaSelecionada));
            btn_editarT.setEnabled(true);
            btn_del.setEnabled(true);
        } else {
            btn_editarT.setEnabled(false);
            btn_del.setEnabled(false);
            limparCamposTalhao();
        }
    }//GEN-LAST:event_tb_talhaoMouseClicked

    private void btn_editarTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarTActionPerformed
        if (linhaSelecionada != null && linhaSelecionada >= 0) {
            editarTalhao(TbTalhao.getBeans(linhaSelecionada), linhaSelecionada);
            btn_editarT.setEnabled(false);
            btn_del.setEnabled(false);
            limparCamposTalhao();
        }
    }//GEN-LAST:event_btn_editarTActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btn_add;
    javax.swing.JButton btn_cadastrar;
    javax.swing.JButton btn_del;
    javax.swing.JButton btn_editar;
    javax.swing.JButton btn_editarT;
    javax.swing.JButton btn_novo;
    componentes.UJComboBox cb_cidade;
    javax.swing.JComboBox<String> cb_cultivo;
    componentes.UJComboBox cb_estado;
    javax.swing.JComboBox<String> cb_status;
    javax.swing.JComboBox<String> cb_tipo;
    javax.swing.JCheckBox ch_mecanizavel;
    javax.swing.JCheckBox ch_status;
    javax.swing.JTable tb_fazendas;
    javax.swing.JTabbedPane tb_pane;
    javax.swing.JTable tb_talhao;
    javax.swing.JTextField txt_Fazenda;
    javax.swing.JTextField txt_areaMapeada;
    javax.swing.JTextField txt_areaTalhao;
    javax.swing.JTextField txt_areaTotal;
    javax.swing.JTextField txt_codigo;
    javax.swing.JTextField txt_endereco;
    javax.swing.JTextField txt_ins;
    javax.swing.JTextField txt_observacao;
    javax.swing.JButton txt_pesquisar;
    javax.swing.JTextField txt_talhao;
    // End of variables declaration//GEN-END:variables

    private void popularBeans(PropriedadesBeans fazenda) {
        fazenda.setCodigo(Integer.parseInt(txt_codigo.getText()));
        fazenda.setIdProdutor(1);
        fazenda.setEstado(cb_estado.getModel().getSelectedItem().toString());
        fazenda.setStatus(cb_status.getSelectedItem().toString());
        fazenda.setNome(txt_Fazenda.getText());
        fazenda.setArea(Corretores.ConverterStringDouble(txt_areaTotal.getText()));
        fazenda.setCidadeBeans((CidadesBeans) cb_cidade.getSelectedItem());
        fazenda.setCidade(cb_cidade.getSelectedItem().toString());
        fazenda.setEndereco(txt_endereco.getText());
        fazenda.setInscricao(txt_ins.getText());
        fazenda.setTipo(cb_tipo.getSelectedItem().toString());
        fazenda.setObservacao(txt_observacao.getText());
        fazenda.setListaTalhao(TbTalhao.getLista());
    }

    private void preencherFormulario(PropriedadesBeans fazenda) {
        limparCampos();
        txt_codigo.setText(String.valueOf(fazenda.getCodigo()));
        cb_tipo.setSelectedItem(fazenda.getTipo());
        cb_estado.setSelectedItem(fazenda.getEstado());
        cb_cidade.setSelectedItem(fazenda.getCidade());
        cb_status.setSelectedItem(fazenda.getStatus());
        txt_Fazenda.setText(fazenda.getNome());
        txt_areaTotal.setText(Corretores.ConverterParaDecimal(fazenda.getArea().toString().replace(".", ","), "ha"));
        txt_endereco.setText(fazenda.getEndereco());
        txt_ins.setText(fazenda.getInscricao());
        txt_observacao.setText(fazenda.getObservacao());
        TbTalhao.addLista(fazenda.getListaTalhao());
        txt_areaMapeada.setText(Corretores.ConverterParaDecimal(somarAreaTalhao(), "ha"));
    }

    private void preencherCamposTalhao(TalhaoBeans t) {
        txt_areaTalhao.setText(Corretores.ConverterParaDecimal(t.getArea(), "ha"));
        txt_talhao.setText(t.getTalhao());
        cb_cultivo.setSelectedItem(t.getCultivo());
        ch_mecanizavel.setSelected(t.isMecanizavel());
        ch_status.setSelected(t.isStatus());
    }

    private void limparCampos() {
        cb_estado.setSelectedIndex(0);
        cb_cidade.setSelectedIndex(0);
        cb_status.setSelectedItem("-");
        cb_tipo.setSelectedIndex(0);
        txt_Fazenda.setText("");
        txt_areaTotal.setText("");
        txt_codigo.setText("");
        txt_endereco.setText("");
        txt_ins.setText("");
        txt_observacao.setText("");
        txt_areaMapeada.setText("");
        TbTalhao.limpar();
    }

    private void limparCamposTalhao() {
        txt_talhao.setText("");
        txt_areaTalhao.setText("0");
        cb_cultivo.setSelectedIndex(0);
        ch_status.setSelected(false);
        ch_mecanizavel.setSelected(false);
        linhaSelecionada = null;
    }

    private void habilitarCampos() {
        cb_estado.setEnabled(true);
        cb_status.setEnabled(true);
        cb_tipo.setEnabled(true);
        txt_Fazenda.setEnabled(true);
        txt_areaTotal.setEnabled(true);
        cb_cidade.setEnabled(true);
        txt_observacao.setEnabled(true);
        txt_endereco.setEnabled(true);
        txt_ins.setEnabled(true);
    }

    private void desabilitarCampos() {
        cb_estado.setEnabled(false);
        cb_status.setEnabled(false);
        cb_tipo.setEnabled(false);
        txt_Fazenda.setEnabled(false);
        txt_areaTotal.setEnabled(false);
        cb_cidade.setEnabled(false);
        txt_observacao.setEnabled(false);
        txt_endereco.setEnabled(false);
        txt_ins.setEnabled(false);
    }

    private void addTalhao() {
        TalhaoBeans t = new TalhaoBeans();
        t.setFazenda(FazendaB);
        t.setTalhao(txt_talhao.getText());
        t.setCultivo(cb_cultivo.getSelectedItem().toString());
        t.setTipoCultura(cb_cultivo.getSelectedIndex());
        t.setArea(Corretores.ConverterStringDouble(txt_areaTalhao.getText()));
        t.setStatus(ch_status.isSelected());
        t.setMecanizavel(ch_mecanizavel.isSelected());
        if (verificarNome(t.getTalhao())) {
            TbTalhao.addBeans(t);
            txt_talhao.grabFocus();
            txt_talhao.selectAll();
            txt_areaTalhao.setText("0");
        } else {
            JOptionPane.showMessageDialog(null, "O Nome do Talhão deve ser Único por Propriedade!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }

    private Boolean editarTalhao(TalhaoBeans t, int row) {
        if (verificarNomeEdicao(txt_talhao.getText(), t.getID())) {
            t.setFazenda(FazendaB);
            t.setTalhao(txt_talhao.getText());
            t.setCultivo(cb_cultivo.getSelectedItem().toString());
            t.setTipoCultura(cb_cultivo.getSelectedIndex());
            t.setArea(Corretores.ConverterStringDouble(txt_areaTalhao.getText()));
            t.setStatus(ch_status.isSelected());
            t.setMecanizavel(ch_mecanizavel.isSelected());
            TbTalhao.editarBean(t, row);
            txt_areaTalhao.setText("0");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "O Nome do Talhão deve ser Único por Propriedade!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
    }

    private Boolean verificarNomeEdicao(String nomeTalhao, Integer ID) {
        for (int i = 0; i < TbTalhao.getRowCount(); i++) {
            if (TbTalhao.getBeans(i).getTalhao().equals(nomeTalhao) && TbTalhao.getBeans(i).getID() != ID) {
                return false;
            }
        }
        return true;
    }
    
    private Boolean verificarNome(String nomeTalhao) {
        for (int i = 0; i < TbTalhao.getRowCount(); i++) {
            if (TbTalhao.getBeans(i).getTalhao().equals(nomeTalhao)) {
                return false;
            }
        }
        return true;
    }

    private Boolean verificarBeans(PropriedadesBeans fazenda) {
        if (fazenda.getArea() == null || fazenda.getArea() == 0.00) {
            JOptionPane.showMessageDialog(null, "Verifique o campo área total!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (fazenda.getTipo() == null) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Tipo de Exploração!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }

    private Double somarAreaTalhao() {
        Double AreaTotal = 0.0;
        for (TalhaoBeans t : TbTalhao.getLista()) {
            AreaTotal += t.getArea();
        }
        return AreaTotal;
    }

}
