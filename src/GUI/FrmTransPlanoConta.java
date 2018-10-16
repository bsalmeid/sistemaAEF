package GUI;

import Beans.AtividadeBeans;
import Beans.CentroDeResultado;
import Beans.InventarioBeans;
import Beans.PagtoClassBeans;
import Beans.PlanoContaBeans;
import Beans.PropriedadesBeans;
import DAO.DiversasHibernate;
import static GUI.Principal.ListaPropriedades;
import static GUI.Principal.listInventario;
import static GUI.Principal.listPlanoContas;
import static GUI.Principal.listaAtividades;
import static GUI.Principal.listaCentroResultado;
import Icones.FormatarICO;
import TableModel.TableModelPagtoClass;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import Utilitarios.ReaisCellRenderer;
import com.toedter.calendar.JDateChooser;
import componentes.UJComboBox;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class FrmTransPlanoConta extends javax.swing.JInternalFrame {

    TableModelPagtoClass TbClass;
    CentralizarTabela Centralizar;

    public FrmTransPlanoConta() {
        initComponents();
        carregarFazendas();
        carregarInventario();
        carregarCentroResultado();
        carregarAtividades();
        carregarPlanoContas();
        carregarTabelaClass();

    }

    private void carregarFazendas() {
        if (ListaPropriedades == null) {
            ListaPropriedades = DiversasHibernate.getListaFazendas();
        }
        cb_fazendaClas.addItem(new PropriedadesBeans(0, "-"));
        for (PropriedadesBeans p : ListaPropriedades) {
            cb_fazendaClas.addItem(p);
        }
    }

    private void carregarInventario() {
        if (listInventario == null) {
            listInventario = DiversasHibernate.getListaInventario();
        }
        cb_nFrota.removeAllItems();
        InventarioBeans l = new InventarioBeans(0, "-", "-");
        cb_nFrota.addItem(l);
        for (InventarioBeans frota : listInventario) {
            cb_nFrota.addItem(frota);
        }
    }

    private void carregarCentroResultado() {
        if (listaCentroResultado == null) {
            listaCentroResultado = DiversasHibernate.getListaCentroResultado();
        }
        cb_centroResultado.removeAllItems();
        cb_centroResultado.addItem(new CentroDeResultado(0L, "-"));
        for (CentroDeResultado c : listaCentroResultado) {
            cb_centroResultado.addItem(c);
        }
    }

    private void carregarCentroResultado(Integer idAtividade) {
        if (listaCentroResultado == null) {
            listaCentroResultado = DiversasHibernate.getListaCentroResultado();
        }
        cb_centroResultado.removeAllItems();
        cb_centroResultado.addItem(new CentroDeResultado(0L, "-"));
        for (CentroDeResultado c : listaCentroResultado) {
            if (c.getAtividade().getID() == idAtividade) {
                cb_centroResultado.addItem(c);
            }
        }
    }

    private void carregarAtividades() {
        if (listaAtividades == null) {
            listaAtividades = DiversasHibernate.getListaAtividade();
        }
        cb_atividade.addItem(new AtividadeBeans(0, "-", true));
        for (AtividadeBeans a : listaAtividades) {
            cb_atividade.addItem(a);
        }
    }

    private void carregarPlanoContas() {
        if (listPlanoContas == null) {
            listPlanoContas = DiversasHibernate.getPlanoConta();
        }
        cb_planoConta.addItem(new PlanoContaBeans(0, 0, "-"));
        for (PlanoContaBeans p : listPlanoContas) {
            cb_planoConta.addItem(p);
        }
    }

    private JTable carregarTabelaClass() {
        Centralizar = new CentralizarTabela();
        tb_class.setModel(getTableModel());
        Centralizar.centralizarTabela(tb_class);
        ((DefaultTableCellRenderer) tb_class.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        for (int c = 0; c < TbClass.getColumnCount(); c++) {
            if (c == TbClass.ID || c == TbClass.IDPAGTO || c == TbClass.IDPLANO || c == TbClass.IDFAZENDA || c == TbClass.IDAPLICACAO || c == TbClass.TIPODESPESA) {
                tb_class.getColumnModel().getColumn(c).setPreferredWidth(0);
                tb_class.getColumnModel().getColumn(c).setMinWidth(0);
                tb_class.getColumnModel().getColumn(c).setMaxWidth(0);
            }
        }
        tb_class.getColumnModel().getColumn(2).setPreferredWidth(120); // Atividade
        tb_class.getColumnModel().getColumn(5).setPreferredWidth(120); // Plano COnta
        tb_class.getColumnModel().getColumn(6).setPreferredWidth(130); // Plano Descrição
        tb_class.getColumnModel().getColumn(7).setPreferredWidth(120); // Fazenda
        tb_class.getColumnModel().getColumn(8).setPreferredWidth(80); // Aplicação
        tb_class.getColumnModel().getColumn(8).setMaxWidth(70); // Aplicação
        tb_class.getColumnModel().getColumn(9).setPreferredWidth(160); // Descriçao
        tb_class.getColumnModel().getColumn(10).setPreferredWidth(120); // valor
        tb_class.getColumnModel().getColumn(TbClass.VALOR).setCellRenderer(new ReaisCellRenderer());

        return tb_class;
    }

    private TableModelPagtoClass getTableModel() {
        if (TbClass == null) {
            TbClass = new TableModelPagtoClass();
        }
        return TbClass;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel22 = new javax.swing.JLabel();
        cb_atividade = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel23 = new javax.swing.JLabel();
        cb_fazendaClas = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel24 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel25 = new javax.swing.JLabel();
        cb_planoConta = new componentes.UJComboBox();
        btn_pesquisarPlano = new javax.swing.JButton();
        javax.swing.JLabel jLabel26 = new javax.swing.JLabel();
        cb_nFrota = new componentes.UJComboBox();
        javax.swing.JLabel jLabel27 = new javax.swing.JLabel();
        txt_descricaoClass = new javax.swing.JTextField();
        javax.swing.JLabel jLabel28 = new javax.swing.JLabel();
        txt_valorClass = new javax.swing.JTextField();
        btn_addClass = new javax.swing.JButton();
        btn_editarClass = new javax.swing.JButton();
        btn_delClass = new javax.swing.JButton();
        cb_centroResultado = new componentes.UJComboBox();
        btn_repetirValor1 = new javax.swing.JButton();
        btn_pesqEmissor2 = new javax.swing.JButton();
        scPClassifocacao = new javax.swing.JScrollPane();
        tb_class = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel29 = new javax.swing.JLabel();
        cb_atividade1 = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel30 = new javax.swing.JLabel();
        cb_fazendaClas1 = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel31 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel32 = new javax.swing.JLabel();
        cb_planoConta1 = new componentes.UJComboBox();
        btn_pesquisarPlano1 = new javax.swing.JButton();
        javax.swing.JLabel jLabel33 = new javax.swing.JLabel();
        cb_nFrota1 = new componentes.UJComboBox();
        javax.swing.JLabel jLabel34 = new javax.swing.JLabel();
        txt_descricaoClass1 = new javax.swing.JTextField();
        javax.swing.JLabel jLabel35 = new javax.swing.JLabel();
        txt_valorClass1 = new javax.swing.JTextField();
        cb_centroResultado1 = new componentes.UJComboBox();
        btn_pesqEmissor3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txt_dataPagtoPrevista =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Transferência de Centro de Custo");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()), "Centro de Custo de Destino", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Atividade");

        cb_atividade.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_atividadeItemStateChanged(evt);
            }
        });
        cb_atividade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cb_atividadeFocusGained(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("C. de Resultado");

        cb_fazendaClas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cb_fazendaClasFocusGained(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Fazenda");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("Conta");

        cb_planoConta.setAutocompletar(true);
        cb_planoConta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cb_planoContaFocusGained(evt);
            }
        });

        btn_pesquisarPlano.setText("jButton1");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("Nº Frota");

        cb_nFrota.setAutocompletar(true);
        cb_nFrota.setRequestFocusEnabled(true);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText("Descrição");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setText("Valor");

        txt_valorClass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_valorClassFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_valorClassFocusLost(evt);
            }
        });

        btn_addClass.setText("Add");
        btn_addClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addClassActionPerformed(evt);
            }
        });

        btn_editarClass.setText("Editar");
        btn_editarClass.setEnabled(false);
        btn_editarClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarClassActionPerformed(evt);
            }
        });

        btn_delClass.setText("Del");
        btn_delClass.setEnabled(false);
        btn_delClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delClassActionPerformed(evt);
            }
        });

        cb_centroResultado.setAutocompletar(true);
        cb_centroResultado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cb_centroResultadoFocusGained(evt);
            }
        });

        btn_repetirValor1.setBackground(new java.awt.Color(255, 255, 255));
        btn_repetirValor1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/refresh.png"))); // NOI18N
        btn_repetirValor1.setBorder(null);
        btn_repetirValor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_repetirValor1ActionPerformed(evt);
            }
        });

        btn_pesqEmissor2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar pequeno_1.png"))); // NOI18N
        btn_pesqEmissor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesqEmissor2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_atividade, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_centroResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_planoConta, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn_pesquisarPlano, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_fazendaClas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_nFrota, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn_pesqEmissor2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_descricaoClass, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_valorClass, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn_repetirValor1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(btn_addClass)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_editarClass)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_delClass)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel23)
                    .addComponent(cb_atividade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel25)
                    .addComponent(cb_planoConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesquisarPlano)
                    .addComponent(cb_fazendaClas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(cb_centroResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel26)
                    .addComponent(cb_nFrota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(txt_descricaoClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(txt_valorClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_addClass)
                    .addComponent(btn_editarClass)
                    .addComponent(btn_delClass)
                    .addComponent(btn_repetirValor1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_pesqEmissor2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        scPClassifocacao.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        scPClassifocacao.setPreferredSize(new java.awt.Dimension(454, 220));

        tb_class.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb_class.setRowSelectionAllowed(false);
        tb_class.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tb_class.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_classMouseClicked(evt);
            }
        });
        scPClassifocacao.setViewportView(tb_class);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Centro de Custo de Origem", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setText("Atividade");

        cb_atividade1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_atividade1ItemStateChanged(evt);
            }
        });
        cb_atividade1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cb_atividade1FocusGained(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel30.setText("C. de Resultado");

        cb_fazendaClas1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cb_fazendaClas1FocusGained(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel31.setText("Fazenda");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel32.setText("Conta");

        cb_planoConta1.setAutocompletar(true);
        cb_planoConta1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cb_planoConta1FocusGained(evt);
            }
        });

        btn_pesquisarPlano1.setText("jButton1");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel33.setText("Nº Frota");

        cb_nFrota1.setAutocompletar(true);
        cb_nFrota1.setRequestFocusEnabled(true);

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel34.setText("Descrição");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel35.setText("Valor");

        txt_valorClass1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_valorClass1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_valorClass1FocusLost(evt);
            }
        });

        cb_centroResultado1.setAutocompletar(true);
        cb_centroResultado1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cb_centroResultado1FocusGained(evt);
            }
        });

        btn_pesqEmissor3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar pequeno_1.png"))); // NOI18N
        btn_pesqEmissor3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesqEmissor3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_atividade1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_centroResultado1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_planoConta1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn_pesquisarPlano1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel31))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_nFrota1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn_pesqEmissor3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_descricaoClass1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel35)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_valorClass1)
                    .addComponent(cb_fazendaClas1, 0, 152, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel30)
                    .addComponent(cb_atividade1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel32)
                    .addComponent(cb_planoConta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesquisarPlano1)
                    .addComponent(cb_fazendaClas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(cb_centroResultado1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel33)
                    .addComponent(cb_nFrota1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(txt_descricaoClass1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35)
                    .addComponent(txt_valorClass1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesqEmissor3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Data Transf:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Usuário");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_dataPagtoPrevista, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3)
                    .addComponent(txt_dataPagtoPrevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scPClassifocacao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scPClassifocacao, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb_atividadeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_atividadeItemStateChanged

        if (cb_atividade.getSelectedIndex() > 0) {
            carregarCentroResultado(getAtividade(cb_atividade).getID());
        }
    }//GEN-LAST:event_cb_atividadeItemStateChanged

    private void cb_atividadeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cb_atividadeFocusGained
        setExpandedComboBox(evt.getComponent());
    }//GEN-LAST:event_cb_atividadeFocusGained

    private void cb_fazendaClasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cb_fazendaClasFocusGained
        setExpandedComboBox(evt.getComponent());
    }//GEN-LAST:event_cb_fazendaClasFocusGained

    private void cb_planoContaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cb_planoContaFocusGained
        setExpandedComboBox(evt.getComponent());
    }//GEN-LAST:event_cb_planoContaFocusGained

    private void txt_valorClassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorClassFocusGained
        txt_valorClass.selectAll();
    }//GEN-LAST:event_txt_valorClassFocusGained

    private void txt_valorClassFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorClassFocusLost
        txt_valorClass.setText(Corretores.ConverterDecimalReais(txt_valorClass.getText()));
    }//GEN-LAST:event_txt_valorClassFocusLost

    private void btn_addClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addClassActionPerformed
        PagtoClassBeans clas = new PagtoClassBeans();
        addClassificacao(clas);
    }//GEN-LAST:event_btn_addClassActionPerformed

    private void btn_editarClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarClassActionPerformed
        int rowIndex = tb_class.getSelectedRow();
        if (editarClassificacao(TbClass.getBeans(rowIndex), rowIndex)) {
            btn_addClass.setEnabled(true);
            btn_editarClass.setEnabled(false);
            btn_delClass.setEnabled(false);
            limparCamposClass();
        }
    }//GEN-LAST:event_btn_editarClassActionPerformed

    private void btn_delClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delClassActionPerformed
        int rowIndex = tb_class.getSelectedRow();
        if (TbClass.getBeans(rowIndex).getID() != null) {
            int excluir = JOptionPane.showConfirmDialog(null, "<Html> Está Ação irá <B>EXCLUIR Permanentemente </B>, <br> este Registro, deseja Prosseguir? </html>", "Atenção", JOptionPane.YES_NO_OPTION);
            if (excluir == JOptionPane.YES_OPTION) {

            }
        } else {
            TbClass.excluirLinha(rowIndex);
            atualizarValorClas();
        }
        btn_addClass.setEnabled(true);
        btn_editarClass.setEnabled(false);
        btn_delClass.setEnabled(false);
    }//GEN-LAST:event_btn_delClassActionPerformed

    private void cb_centroResultadoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cb_centroResultadoFocusGained
        setExpandedComboBox(evt.getComponent());
    }//GEN-LAST:event_cb_centroResultadoFocusGained

    private void btn_repetirValor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_repetirValor1ActionPerformed

    }//GEN-LAST:event_btn_repetirValor1ActionPerformed

    private void btn_pesqEmissor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesqEmissor2ActionPerformed
        FrmConsInventario frmConsulta = new FrmConsInventario(null, false);
        frmConsulta.tb_conInventario.addMouseListener(getConsultaInventarioListener(frmConsulta));
        frmConsulta.setLocationRelativeTo(null);
        frmConsulta.setVisible(true);
    }//GEN-LAST:event_btn_pesqEmissor2ActionPerformed

    private void tb_classMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_classMouseClicked
        if (evt.getClickCount() == 2) {
            preecherCamposClass(TbClass.getBeans(tb_class.getSelectedRow()));
            cb_atividade.requestFocus();
            btn_addClass.setEnabled(false);
            btn_editarClass.setEnabled(true);
            btn_delClass.setEnabled(true);
        } else {
            limparCamposClass();
            btn_addClass.setEnabled(true);
            btn_editarClass.setEnabled(false);
            btn_delClass.setEnabled(false);
        }
    }//GEN-LAST:event_tb_classMouseClicked

    private void cb_atividade1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_atividade1ItemStateChanged

        if (cb_atividade.getSelectedIndex() > 0) {
            carregarCentroResultado(getAtividade(cb_atividade).getID());
        }
    }//GEN-LAST:event_cb_atividade1ItemStateChanged

    private void cb_atividade1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cb_atividade1FocusGained
        setExpandedComboBox(evt.getComponent());
    }//GEN-LAST:event_cb_atividade1FocusGained

    private void cb_fazendaClas1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cb_fazendaClas1FocusGained
        setExpandedComboBox(evt.getComponent());
    }//GEN-LAST:event_cb_fazendaClas1FocusGained

    private void cb_planoConta1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cb_planoConta1FocusGained
        setExpandedComboBox(evt.getComponent());
    }//GEN-LAST:event_cb_planoConta1FocusGained

    private void txt_valorClass1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorClass1FocusGained
        txt_valorClass.selectAll();
    }//GEN-LAST:event_txt_valorClass1FocusGained

    private void txt_valorClass1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorClass1FocusLost
        txt_valorClass.setText(Corretores.ConverterDecimalReais(txt_valorClass.getText()));
    }//GEN-LAST:event_txt_valorClass1FocusLost

    private void cb_centroResultado1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cb_centroResultado1FocusGained
        setExpandedComboBox(evt.getComponent());
    }//GEN-LAST:event_cb_centroResultado1FocusGained

    private void btn_pesqEmissor3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesqEmissor3ActionPerformed
        FrmConsInventario frmConsulta = new FrmConsInventario(null, false);
        frmConsulta.tb_conInventario.addMouseListener(getConsultaInventarioListener(frmConsulta));
        frmConsulta.setLocationRelativeTo(null);
        frmConsulta.setVisible(true);
    }//GEN-LAST:event_btn_pesqEmissor3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btn_addClass;
    javax.swing.JButton btn_delClass;
    javax.swing.JButton btn_editarClass;
    public javax.swing.JButton btn_pesqEmissor2;
    public javax.swing.JButton btn_pesqEmissor3;
    javax.swing.JButton btn_pesquisarPlano;
    javax.swing.JButton btn_pesquisarPlano1;
    javax.swing.JButton btn_repetirValor1;
    javax.swing.JComboBox<AtividadeBeans> cb_atividade;
    javax.swing.JComboBox<AtividadeBeans> cb_atividade1;
    componentes.UJComboBox cb_centroResultado;
    componentes.UJComboBox cb_centroResultado1;
    public javax.swing.JComboBox<PropriedadesBeans> cb_fazendaClas;
    public javax.swing.JComboBox<PropriedadesBeans> cb_fazendaClas1;
    componentes.UJComboBox cb_nFrota;
    componentes.UJComboBox cb_nFrota1;
    componentes.UJComboBox cb_planoConta;
    componentes.UJComboBox cb_planoConta1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    javax.swing.JPanel jPanel4;
    javax.swing.JPanel jPanel5;
    private javax.swing.JTextField jTextField1;
    javax.swing.JScrollPane scPClassifocacao;
    javax.swing.JTable tb_class;
    com.toedter.calendar.JDateChooser txt_dataPagtoPrevista;
    public javax.swing.JTextField txt_descricaoClass;
    public javax.swing.JTextField txt_descricaoClass1;
    public javax.swing.JTextField txt_valorClass;
    public javax.swing.JTextField txt_valorClass1;
    // End of variables declaration//GEN-END:variables

    private MouseAdapter getConsultaInventarioListener(FrmConsInventario frmConsulta) {
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linha = frmConsulta.tb_conInventario.getSelectedRow();
                if (e.getClickCount() == 2) {
                    if (linha >= 0) {
                        setComboBoxInventario(cb_nFrota, (Integer) frmConsulta.TbConInventario.getValueAt(linha, frmConsulta.TbConInventario.ID_INVENTARIO));
                        frmConsulta.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Selecione um Número da Frota", "Atenção!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        };
        return adapter;
    }

    private void setExpandedComboBox(Component c) {
        if (c instanceof JComboBox) {
            ((JComboBox) c).setPopupVisible(true);
        } else if (c instanceof UJComboBox) {
            ((UJComboBox) c).setPopupVisible(true);
        }
    }

    private void limparCamposClass() {
        cb_atividade.setSelectedIndex(0);
        cb_centroResultado.setSelectedIndex(0);
        cb_planoConta.setSelectedIndex(0);
        cb_fazendaClas.setSelectedIndex(0);
        cb_nFrota.setSelectedIndex(0);
        txt_descricaoClass.setText("");
        txt_valorClass.setText("R$ 0,00");
    }

    private AtividadeBeans getAtividade(JComboBox<AtividadeBeans> combo) {
        return (AtividadeBeans) combo.getSelectedItem();
    }

    private InventarioBeans getInventario(UJComboBox combo) {
        if (combo.getSelectedIndex() > 0) {
            return (InventarioBeans) combo.getSelectedItem();
        } else {
            return null;
        }
    }

    private PropriedadesBeans getFazenda(JComboBox combo) {
        return (PropriedadesBeans) combo.getSelectedItem();
    }

    private PlanoContaBeans getPlanoConta(UJComboBox combo) {
        return (PlanoContaBeans) combo.getSelectedItem();
    }

    private CentroDeResultado getCentroResultado(UJComboBox combo) {
        return (CentroDeResultado) combo.getSelectedItem();
    }

    private void setAtividade(JComboBox<AtividadeBeans> combo, Integer id) {
        combo.setSelectedIndex(0);
        for (int i = 0; i < combo.getModel().getSize(); i++) {
            if (Objects.equals(combo.getItemAt(i).getID(), id)) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }

    public void setComboFazenda(JComboBox<PropriedadesBeans> combobox, Integer idFazenda) {
        combobox.setSelectedIndex(0);
        for (int i = 0; i < combobox.getModel().getSize(); i++) {
            if (combobox.getItemAt(i).getCodigo() == idFazenda) {
                combobox.setSelectedIndex(i);
                break;
            }
        }
    }

    private void setComboBoxInventario(UJComboBox combo, Integer ID) {
        for (int i = 0; i < combo.getModel().getSize(); i++) {
            InventarioBeans frota = (InventarioBeans) combo.getItemAt(i);
            if (Objects.equals(frota.getID(), ID)) {
                combo.setSelectedIndex(i);
            }
        }
    }

    private void setComboBoxCentroResultado(UJComboBox combo, Long ID) {
        for (int i = 0; i < combo.getModel().getSize(); i++) {
            CentroDeResultado c = (CentroDeResultado) combo.getItemAt(i);
            if (Objects.equals(c.getID(), ID)) {
                combo.setSelectedIndex(i);
            }
        }
    }

    private void setComboBoxPlanoConta(UJComboBox combo, Integer ID) {
        for (int i = 0; i < combo.getModel().getSize(); i++) {
            PlanoContaBeans p = (PlanoContaBeans) combo.getItemAt(i);
            if (Objects.equals(p.getID(), ID)) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }

    private Boolean addClassificacao(PagtoClassBeans clas) {
        try {
            clas.setAtividadeBeans(getAtividade(cb_atividade));
            clas.setAtividade(getAtividade(cb_atividade).getDescricao());
            clas.setCentroResultado(getCentroResultado(cb_centroResultado));
            clas.setTipoDespesa(getPlanoConta(cb_planoConta).getGrupoConta().getDescricao());
            clas.setIdAplicacao(getInventario(cb_nFrota));
            clas.setnFrota((getInventario(cb_nFrota) == null ? "-" : (getInventario(cb_nFrota).getnFrota())));
            clas.setFazenda(getFazenda(cb_fazendaClas).getNome());
            clas.setFazendaB(getFazenda(cb_fazendaClas));
            clas.setIdPlanoContas(getPlanoConta(cb_planoConta).getID());
            clas.setDesConta(getPlanoConta(cb_planoConta).getDescricao());
            clas.setPlanoConta(getPlanoConta(cb_planoConta));
            clas.setDescricao(txt_descricaoClass.getText());
            clas.setValorClas(Corretores.ConverterReaisDouble(txt_valorClass.getText()));
            if (verificaBeans(clas)) {
                TbClass.addBeans(clas);
                limparCamposClass();
                cb_atividade.requestFocus();
                atualizarValorClas();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Adicionar Classificação!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }

    private Boolean editarClassificacao(PagtoClassBeans clas, int rowIndex) {
        try {
            clas.setAtividade(getAtividade(cb_atividade).getDescricao());
            clas.setAtividadeBeans(getAtividade(cb_atividade));
            clas.setCentroResultado(getCentroResultado(cb_centroResultado));
            clas.setTipoDespesa((getPlanoConta(cb_planoConta).getDescricao()));
            clas.setIdAplicacao(getInventario(cb_nFrota));
            clas.setnFrota((getInventario(cb_nFrota) == null ? "-" : (getInventario(cb_nFrota).getnFrota())));
            clas.setFazenda(getFazenda(cb_fazendaClas).getNome());
            clas.setFazendaB(getFazenda(cb_fazendaClas));
            clas.setIdPlanoContas(getPlanoConta(cb_planoConta).getID());
            clas.setDesConta(getPlanoConta(cb_planoConta).getDescricao());
            clas.setPlanoConta(getPlanoConta(cb_planoConta));
            clas.setDescricao(txt_descricaoClass.getText());
            clas.setValorClas(Corretores.ConverterReaisDouble(txt_valorClass.getText()));
            if (verificaBeans(clas)) {
                TbClass.setBeans(clas, rowIndex);
                atualizarValorClas();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Editar Classificação!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }

    private boolean verificaBeans(PagtoClassBeans clas) {
        if (clas.getAtividadeBeans().getID() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Atividade!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (clas.getCentroResultado().getID() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Centro de Resultado!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (clas.getFazendaB().getCodigo() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Fazenda!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (clas.getPlanoConta().getID() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Plano de Conta!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (clas.getTipoDespesa().equals("-")) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Plano Tipo de Despesa!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (clas.getValorClas() == 0.00) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Valor!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (clas.getDescricao().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Descrição!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }

    private void preecherCamposClass(PagtoClassBeans clas) {
        setAtividade(cb_atividade, clas.getAtividadeBeans().getID());
        if (clas.getCentroResultado() != null) {
            setComboBoxCentroResultado(cb_centroResultado, clas.getCentroResultado().getID());
        }
        cb_planoConta.setSelectedItem(clas.getPlanoConta());
        setComboFazenda(cb_fazendaClas, clas.getFazendaB().getCodigo());
        if (clas.getIdAplicacao() != null) {
            setComboBoxInventario(cb_nFrota, clas.getIdAplicacao().getID());
        }
        txt_descricaoClass.setText(clas.getDescricao());
        txt_valorClass.setText(Corretores.ConverterDoubleReais(clas.getValorClas()));
    }

    private void atualizarValorClas() {
        //tp_clas.setTitle("Classificação - " + Corretores.ConverterDoubleReais(TbClass.somarValorTabela(TbClass.VALOR)));
    }

}
