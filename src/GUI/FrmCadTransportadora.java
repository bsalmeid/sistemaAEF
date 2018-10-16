package GUI;

import Beans.CadPlacaBeans;
import Beans.TransportadorasBeans;
import DAO.TransportadorasDAO;
import Icones.FormatarICO;
import TableModel.TableModelCadastroTransportadoras;
import TableModel.TableModelConsultaTransportadoras;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import Utilitarios.ValidarPermissoes;
import java.awt.Component;
import java.awt.Container;
import java.text.ParseException;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Usuario
 */
public class FrmCadTransportadora extends JInternalFrame {

    MaskFormatter CNPJCPFMask;
    MaskFormatter CPFCNPJMask;
    MaskFormatter CPFMask;
    MaskFormatter PlacaMask;
    MaskFormatter BuscarPlacaMask;
    MaskFormatter TelMask;
    MaskFormatter CelMask;
    MaskFormatter ContatoMask;
    CentralizarTabela Centralizar;
    TransportadorasBeans TransB;
    CadPlacaBeans PlacaB;
    TableModelCadastroTransportadoras TbPlaca;
    TableModelConsultaTransportadoras TbConsulta;
    TransportadorasDAO TranspD;

    public FrmCadTransportadora() {
        initComponents();
        TranspD = new TransportadorasDAO();
        PlacaB = new CadPlacaBeans();
        TransB = new TransportadorasBeans();
        habilitarCamposPlaca(false);
        habilitarCamposTrans(false);
        habilitarJButtonTrans(false);
        habilitarJButtonPlaca(false);
        btn_novoPlaca.setEnabled(false);
        maskFormater();
        Centralizar = new CentralizarTabela();

        getTabelaCadastro();
        getTabelaConsulta();

    }

    private TableModelCadastroTransportadoras getTableModelCadastro() {
        if (TbPlaca == null) {
            TbPlaca = new TableModelCadastroTransportadoras();
        }
        return TbPlaca;
    }

    private TableModelConsultaTransportadoras getTabbleModelConsulta() {
        if (TbConsulta == null) {
            TbConsulta = new TableModelConsultaTransportadoras();
        }
        return TbConsulta;
    }

    private JTable getTabelaCadastro() {
        tb_cadastroPlacas.setModel(getTableModelCadastro());
        Centralizar.centralizarTabela(tb_cadastroPlacas);
        ((DefaultTableCellRenderer) tb_cadastroPlacas.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        tb_cadastroPlacas.getColumnModel().getColumn(TbPlaca.ID_PLACA).setPreferredWidth(10);
        tb_cadastroPlacas.getColumnModel().getColumn(TbPlaca.PLACA).setPreferredWidth(40);
        tb_cadastroPlacas.getColumnModel().getColumn(TbPlaca.VEICULO).setPreferredWidth(40);
        tb_cadastroPlacas.getColumnModel().getColumn(TbPlaca.CARROCERIA).setPreferredWidth(40);
        tb_cadastroPlacas.getColumnModel().getColumn(TbPlaca.MOTORISTA).setPreferredWidth(80);
        tb_cadastroPlacas.getColumnModel().getColumn(TbPlaca.CPF).setPreferredWidth(60);
        tb_cadastroPlacas.getColumnModel().getColumn(TbPlaca.TELEFONE).setPreferredWidth(40);
        tb_cadastroPlacas.getColumnModel().getColumn(TbPlaca.CAM_PROPRIO).setPreferredWidth(10);
        tb_cadastroPlacas.getColumnModel().getColumn(TbPlaca.MARCA).setPreferredWidth(40);
        tb_cadastroPlacas.getColumnModel().getColumn(TbPlaca.MODELO).setPreferredWidth(40);
        tb_cadastroPlacas.getColumnModel().getColumn(TbPlaca.ANO).setPreferredWidth(40);
        tb_cadastroPlacas.getColumnModel().getColumn(TbPlaca.CAPACIDADE).setPreferredWidth(40);
        tb_cadastroPlacas.getColumnModel().getColumn(TbPlaca.STATUS).setPreferredWidth(10);
        return tb_cadastroPlacas;
    }

    private JTable getTabelaConsulta() {
        tb_lista.setModel(getTabbleModelConsulta());
        Centralizar.centralizarTabela(tb_lista);
        ((DefaultTableCellRenderer) tb_lista.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        tb_lista.setModel(TbConsulta);
        tb_lista.getColumnModel().getColumn(TbConsulta.ID_PLACA).setPreferredWidth(5);
        tb_lista.getColumnModel().getColumn(TbConsulta.TRANSPORTADORA).setPreferredWidth(150);
        tb_lista.getColumnModel().getColumn(TbConsulta.PLACA).setPreferredWidth(40);
        tb_lista.getColumnModel().getColumn(TbConsulta.VEICULO).setPreferredWidth(80);
        tb_lista.getColumnModel().getColumn(TbConsulta.CARROCERIA).setPreferredWidth(80);
        tb_lista.getColumnModel().getColumn(TbConsulta.CAM_PROPRIO).setPreferredWidth(20);
        tb_lista.getColumnModel().getColumn(TbConsulta.MARCA).setPreferredWidth(80);
        tb_lista.getColumnModel().getColumn(TbConsulta.MODELO).setPreferredWidth(80);
        tb_lista.getColumnModel().getColumn(TbConsulta.ANO).setPreferredWidth(40);
        tb_lista.getColumnModel().getColumn(TbConsulta.CAPACIDADE).setPreferredWidth(100);
        tb_lista.getColumnModel().getColumn(TbConsulta.STATUS).setPreferredWidth(5);
        return tb_lista;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        abas = new javax.swing.JTabbedPane();
        aba1 = new javax.swing.JPanel();
        jPanelPlaca = new javax.swing.JPanel();
        txtCodigoPlaca = new javax.swing.JTextField();
        javax.swing.JLabel jLabel29 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel30 = new javax.swing.JLabel();
        txt_nomeMoto = new javax.swing.JTextField();
        javax.swing.JLabel jLabel31 = new javax.swing.JLabel();
        ft_placa = new javax.swing.JFormattedTextField();
        javax.swing.JLabel jLabel32 = new javax.swing.JLabel();
        ft_cpf = new javax.swing.JFormattedTextField();
        javax.swing.JLabel jLabel33 = new javax.swing.JLabel();
        txt_marca = new javax.swing.JTextField();
        javax.swing.JLabel jLabel34 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel35 = new javax.swing.JLabel();
        cb_carroceria = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel36 = new javax.swing.JLabel();
        ch_sim = new javax.swing.JCheckBox();
        ch_status = new javax.swing.JCheckBox();
        javax.swing.JLabel jLabel37 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel38 = new javax.swing.JLabel();
        ft_telefone = new javax.swing.JFormattedTextField();
        javax.swing.JLabel jLabel39 = new javax.swing.JLabel();
        txt_modelo = new javax.swing.JTextField();
        javax.swing.JLabel jLabel40 = new javax.swing.JLabel();
        txt_carga = new javax.swing.JTextField();
        javax.swing.JLabel jLabel41 = new javax.swing.JLabel();
        cb_veiculo = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_cadastroPlacas = new javax.swing.JTable();
        txt_ano = new javax.swing.JTextField();
        btn_delPlaca = new javax.swing.JButton();
        btn_editPlaca = new javax.swing.JButton();
        btn_addPlaca = new javax.swing.JButton();
        cb_telefone = new javax.swing.JComboBox<>();
        btn_novoPlaca = new javax.swing.JButton();
        btn_novo = new javax.swing.JButton();
        btn_salvarTransportadora = new javax.swing.JButton();
        jPanelTrans = new javax.swing.JPanel();
        javax.swing.JLabel jLabel57 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        txt_nomeTrans = new javax.swing.JTextField();
        javax.swing.JLabel jLabel58 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        javax.swing.JLabel jLabel59 = new javax.swing.JLabel();
        ft_contatoTrans = new javax.swing.JFormattedTextField();
        javax.swing.JLabel jLabel60 = new javax.swing.JLabel();
        cb_cpf = new javax.swing.JComboBox<>();
        ft_cnpjCpf = new javax.swing.JFormattedTextField();
        javax.swing.JLabel jLabel61 = new javax.swing.JLabel();
        cb_segmento = new javax.swing.JComboBox<>();
        ch_statusTrans = new javax.swing.JCheckBox();
        javax.swing.JLabel jLabel42 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel62 = new javax.swing.JLabel();
        ft_telefoneTrans = new javax.swing.JFormattedTextField();
        btn_alterarTransportadora = new javax.swing.JButton();
        btn_removerTransportadora = new javax.swing.JButton();
        javax.swing.JPanel aba2 = new javax.swing.JPanel();
        jPanelConsulta = new javax.swing.JPanel();
        javax.swing.JLabel jLabel21 = new javax.swing.JLabel();
        txt_buscarTrans = new javax.swing.JTextField();
        btn_buscar = new javax.swing.JButton();
        javax.swing.JLabel jLabel22 = new javax.swing.JLabel();
        ft_buscarPlaca = new javax.swing.JFormattedTextField();
        javax.swing.JLabel jLabel45 = new javax.swing.JLabel();
        cb_veiculo2 = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel46 = new javax.swing.JLabel();
        cb_carroceria2 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_lista = new javax.swing.JTable();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jScrollPane5.setViewportView(jEditorPane1);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro de Transportadoras");

        abas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        abas.setMinimumSize(new java.awt.Dimension(605, 450));
        abas.setPreferredSize(new java.awt.Dimension(670, 450));

        jPanelPlaca.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Cadastro de placas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanelPlaca.setToolTipText("Cadastro de placas");

        txtCodigoPlaca.setEditable(false);
        txtCodigoPlaca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigoPlaca.setEnabled(false);

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setText("Código");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel30.setText("Nome do motorista");

        txt_nomeMoto.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel31.setText("Placa");

        ft_placa.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        ft_placa.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel32.setText("CPF");

        ft_cpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        ft_cpf.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel33.setText("Marca");

        txt_marca.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel34.setText("Ano");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel35.setText("Carroceria");

        cb_carroceria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione a carroceria", "Caçamba", "Graneleira", "Boiadeira" }));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel36.setText("Caminhão Próprio");

        ch_sim.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        ch_sim.setText("Sim");

        ch_status.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        ch_status.setText("Ativo");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel37.setText("Status");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel38.setText("Telefone");

        ft_telefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        ft_telefone.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel39.setText("Modelo");

        txt_modelo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel40.setText("Carga");

        txt_carga.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_carga.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_cargaFocusLost(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel41.setText("Veículo");

        cb_veiculo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o veículo", "Rodo-trem", "Bi-trem", "Julieta", "Truck", "Toco", "LS" }));

        tb_cadastroPlacas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb_cadastroPlacas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_cadastroPlacasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tb_cadastroPlacas);

        txt_ano.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btn_delPlaca.setMnemonic('d');
        btn_delPlaca.setText("Del");
        btn_delPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delPlacaActionPerformed(evt);
            }
        });

        btn_editPlaca.setMnemonic('e');
        btn_editPlaca.setText("Edit");
        btn_editPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editPlacaActionPerformed(evt);
            }
        });

        btn_addPlaca.setMnemonic('a');
        btn_addPlaca.setText("Add");
        btn_addPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addPlacaActionPerformed(evt);
            }
        });

        cb_telefone.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Fixo", "Celular" }));
        cb_telefone.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_telefoneItemStateChanged(evt);
            }
        });

        btn_novoPlaca.setMnemonic('n');
        btn_novoPlaca.setText("Novo");
        btn_novoPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novoPlacaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelPlacaLayout = new javax.swing.GroupLayout(jPanelPlaca);
        jPanelPlaca.setLayout(jPanelPlacaLayout);
        jPanelPlacaLayout.setHorizontalGroup(
            jPanelPlacaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPlacaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPlacaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanelPlacaLayout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addGap(18, 18, 18)
                        .addComponent(txt_ano, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_veiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_carroceria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_novoPlaca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_addPlaca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_editPlaca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_delPlaca))
                    .addGroup(jPanelPlacaLayout.createSequentialGroup()
                        .addGroup(jPanelPlacaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelPlacaLayout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ft_placa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_marca)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_modelo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel40)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_carga)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel36)
                                .addGap(12, 12, 12))
                            .addGroup(jPanelPlacaLayout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodigoPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_nomeMoto, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ft_cpf, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel38)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)))
                        .addGroup(jPanelPlacaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelPlacaLayout.createSequentialGroup()
                                .addComponent(ch_sim)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel37)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ch_status)
                                .addGap(0, 3, Short.MAX_VALUE))
                            .addComponent(ft_telefone))))
                .addContainerGap())
        );

        jPanelPlacaLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_addPlaca, btn_delPlaca, btn_editPlaca});

        jPanelPlacaLayout.setVerticalGroup(
            jPanelPlacaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPlacaLayout.createSequentialGroup()
                .addGroup(jPanelPlacaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(ft_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38)
                    .addComponent(ft_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(txt_nomeMoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(txtCodigoPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(cb_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPlacaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(ch_status)
                    .addComponent(jLabel37)
                    .addComponent(ch_sim)
                    .addComponent(jLabel36)
                    .addComponent(txt_carga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(txt_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39)
                    .addComponent(txt_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33)
                    .addComponent(ft_placa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addGap(5, 5, 5)
                .addGroup(jPanelPlacaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_delPlaca)
                    .addComponent(btn_editPlaca)
                    .addComponent(btn_addPlaca)
                    .addComponent(cb_carroceria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35)
                    .addComponent(cb_veiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41)
                    .addComponent(txt_ano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(btn_novoPlaca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
        );

        jPanelPlacaLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_addPlaca, btn_delPlaca, btn_editPlaca, btn_novoPlaca});

        btn_novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/inserir.png"))); // NOI18N
        btn_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novoActionPerformed(evt);
            }
        });

        btn_salvarTransportadora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/salvar.png"))); // NOI18N
        btn_salvarTransportadora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salvarTransportadoraActionPerformed(evt);
            }
        });

        jPanelTrans.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Cadastro de Transportadoras", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanelTrans.setToolTipText("");

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel57.setText("Código");

        txtCodigo.setEditable(false);
        txtCodigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigo.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Nome da transportadora");

        txt_nomeTrans.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel58.setText("Email");

        txt_email.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel59.setText("Celular");

        ft_contatoTrans.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        ft_contatoTrans.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel60.setText("CPF/CNPJ");

        cb_cpf.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "CPF", "CNPJ" }));
        cb_cpf.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_cpfItemStateChanged(evt);
            }
        });

        ft_cnpjCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        ft_cnpjCpf.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel61.setText("Segmento");

        cb_segmento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o Segmento", "Gado" }));

        ch_statusTrans.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        ch_statusTrans.setText("Ativo");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel42.setText("Status");

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel62.setText("Telefone Fixo");

        ft_telefoneTrans.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        ft_telefoneTrans.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanelTransLayout = new javax.swing.GroupLayout(jPanelTrans);
        jPanelTrans.setLayout(jPanelTransLayout);
        jPanelTransLayout.setHorizontalGroup(
            jPanelTransLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTransLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTransLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTransLayout.createSequentialGroup()
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ft_contatoTrans, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel60)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ft_cnpjCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel61)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_segmento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ch_statusTrans))
                    .addGroup(jPanelTransLayout.createSequentialGroup()
                        .addComponent(jLabel57)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_nomeTrans, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel58)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel62)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ft_telefoneTrans, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelTransLayout.setVerticalGroup(
            jPanelTransLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTransLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanelTransLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel57)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txt_nomeTrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58)
                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62)
                    .addComponent(ft_telefoneTrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelTransLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(ch_statusTrans)
                    .addComponent(jLabel42)
                    .addComponent(cb_segmento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel61)
                    .addComponent(ft_cnpjCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60)
                    .addComponent(ft_contatoTrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59))
                .addContainerGap())
        );

        btn_alterarTransportadora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/editar.png"))); // NOI18N
        btn_alterarTransportadora.setPreferredSize(new java.awt.Dimension(41, 41));
        btn_alterarTransportadora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_alterarTransportadoraActionPerformed(evt);
            }
        });

        btn_removerTransportadora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/deletar2.png"))); // NOI18N
        btn_removerTransportadora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_removerTransportadoraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout aba1Layout = new javax.swing.GroupLayout(aba1);
        aba1.setLayout(aba1Layout);
        aba1Layout.setHorizontalGroup(
            aba1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aba1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(aba1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelPlaca, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelTrans, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, aba1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_salvarTransportadora, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_alterarTransportadora, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_removerTransportadora, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        aba1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_novo, btn_salvarTransportadora});

        aba1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_alterarTransportadora, btn_removerTransportadora});

        aba1Layout.setVerticalGroup(
            aba1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aba1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelTrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelPlaca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(aba1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(aba1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_salvarTransportadora, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_alterarTransportadora, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_removerTransportadora, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        aba1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_alterarTransportadora, btn_novo, btn_removerTransportadora, btn_salvarTransportadora});

        abas.addTab("Cadastro", aba1);

        aba2.setPreferredSize(new java.awt.Dimension(660, 430));

        jPanelConsulta.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Pesquisar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Nome da Transportadora");

        txt_buscarTrans.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btn_buscar.setMnemonic('e');
        btn_buscar.setText("Pesquisar");
        btn_buscar.setPreferredSize(new java.awt.Dimension(41, 41));
        btn_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Placa");

        ft_buscarPlaca.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel45.setText("Veículo");

        cb_veiculo2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o veículo", "Rodo-trem", "Bi-trem", "Julieta", "Truck", "Toco", "LS" }));

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel46.setText("Carroceria");

        cb_carroceria2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione a carroceria", "Caçamba", "Graneleira", "Boiadeira" }));

        javax.swing.GroupLayout jPanelConsultaLayout = new javax.swing.GroupLayout(jPanelConsulta);
        jPanelConsulta.setLayout(jPanelConsultaLayout);
        jPanelConsultaLayout.setHorizontalGroup(
            jPanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_buscarTrans, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ft_buscarPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_veiculo2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cb_carroceria2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_buscar, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelConsultaLayout.setVerticalGroup(
            jPanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultaLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel21)
                    .addComponent(txt_buscarTrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(ft_buscarPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45)
                    .addComponent(cb_veiculo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46)
                    .addComponent(cb_carroceria2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanelConsultaLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_buscar, cb_carroceria2, cb_veiculo2});

        tb_lista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb_lista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_listaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_lista);

        javax.swing.GroupLayout aba2Layout = new javax.swing.GroupLayout(aba2);
        aba2.setLayout(aba2Layout);
        aba2Layout.setHorizontalGroup(
            aba2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aba2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(aba2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanelConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        aba2Layout.setVerticalGroup(
            aba2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aba2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
        );

        abas.addTab("Consulta de Placas", aba2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(abas, javax.swing.GroupLayout.DEFAULT_SIZE, 1064, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(abas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setBounds(0, 0, 1080, 502);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_removerTransportadoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_removerTransportadoraActionPerformed
        int remover = JOptionPane.showConfirmDialog(this, "Deseja remover a transportadora do banco de dados", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if( remover == JOptionPane.YES_OPTION) {
            if (TranspD.deletarTransportadoras(TransB) && ValidarPermissoes.validarPermissaoDelete(FrmCadTransportadora.class.getSimpleName())) {
                habilitarCamposPlaca(false);
                habilitarCamposTrans(false);
                habilitarJButtonPlaca(false);
                habilitarJButtonTrans(false);
                btn_novoPlaca.setEnabled(false);
                limparCampos(jPanelTrans);
                TbPlaca.limpar();
            }
        }
    }//GEN-LAST:event_btn_removerTransportadoraActionPerformed

    private void btn_salvarTransportadoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarTransportadoraActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(this, "Deseja  realizar o cadastro da Transportadora?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (cadastrar == JOptionPane.YES_OPTION) {
            popularTransportadora(TransB);
            if (verificarTransportadora(TransB) && ValidarPermissoes.validarPermissaoInsert(FrmCadTransportadora.class.getSimpleName())) {
                if (TranspD.salvarTransportadora(TransB)) {
                    habilitarCamposPlaca(false);
                    habilitarCamposTrans(false);
                    habilitarJButtonPlaca(false);
                    habilitarJButtonTrans(false);
                    btn_novoPlaca.setEnabled(false);
                    limparCampos(jPanelTrans);
                    TbPlaca.limpar();
                }
            }
        }
    }//GEN-LAST:event_btn_salvarTransportadoraActionPerformed

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed
        habilitarCamposTrans(true);
        habilitarCamposPlaca(false);
        habilitarJButtonPlaca(false);
        habilitarJButtonTrans(false);
        btn_salvarTransportadora.setEnabled(true);
        btn_novoPlaca.setEnabled(true);
        limparCampos(jPanelPlaca);
        limparCampos(jPanelTrans);
        TbPlaca.limpar();
        txt_nomeTrans.grabFocus();
    }//GEN-LAST:event_btn_novoActionPerformed

    private void btn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarActionPerformed
        btn_salvarTransportadora.setEnabled(false);
        TbConsulta.limpar();
        TbConsulta.addLista(TranspD.buscarPlaca(getWhere()));
        ft_buscarPlaca.setValue(null);
        limparCampos(jPanelConsulta);
    }//GEN-LAST:event_btn_buscarActionPerformed

    private void btn_editPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editPlacaActionPerformed
        int linha = tb_cadastroPlacas.getSelectedRow();
        int cadastrar = JOptionPane.showConfirmDialog(null, "Deseja editar esta placa?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION) {
            if (linha >= 0) {
                CadPlacaBeans placa = TbPlaca.getItem(linha);
                editPlaca(placa);
                if (TranspD.atualizarPlaca(placa) && ValidarPermissoes.validarPermissaoUpdate(FrmCadTransportadora.class.getSimpleName())) {
                    limparCampos(jPanelPlaca);
                    ft_cpf.setValue(null);
                    ft_placa.setValue(null);
                    btn_editPlaca.setEnabled(false);
                    btn_delPlaca.setEnabled(false);
                    btn_addPlaca.setEnabled(true);
                }
            }
        }
    }//GEN-LAST:event_btn_editPlacaActionPerformed

    private void btn_addPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addPlacaActionPerformed
        habilitarCamposTrans(false);
        popularPlaca();
    }//GEN-LAST:event_btn_addPlacaActionPerformed

    private void txt_cargaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cargaFocusLost
        txt_carga.setText(Corretores.ConverterDoubleQuilos(txt_carga.getText()));
    }//GEN-LAST:event_txt_cargaFocusLost

    private void cb_cpfItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_cpfItemStateChanged
        switch (cb_cpf.getSelectedItem().toString()) {
            case "CPF":
                ft_cnpjCpf.setValue(null);
                ft_cnpjCpf.setFormatterFactory(new DefaultFormatterFactory(CPFCNPJMask));
                break;
            case "CNPJ":
                ft_cnpjCpf.setValue(null);
                ft_cnpjCpf.setFormatterFactory(new DefaultFormatterFactory(CNPJCPFMask));
                break;
            case "-":
                ft_cnpjCpf.setValue(null);
                ft_cnpjCpf.setFormatterFactory(null);
                break;
            default:
                break;
        }
    }//GEN-LAST:event_cb_cpfItemStateChanged

    private void cb_telefoneItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_telefoneItemStateChanged
        switch (cb_telefone.getSelectedItem().toString()) {
            case "Fixo":
                ft_telefone.setValue(null);
                ft_telefone.setFormatterFactory(new DefaultFormatterFactory(TelMask));
                break;
            case "Celular":
                ft_telefone.setValue(null);
                ft_telefone.setFormatterFactory(new DefaultFormatterFactory(CelMask));
                break;
            case "-":
                ft_telefone.setValue(null);
                ft_telefone.setFormatterFactory(null);
                break;
            default:
                break;
        }
    }//GEN-LAST:event_cb_telefoneItemStateChanged

    private void btn_delPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delPlacaActionPerformed
        int linha = tb_cadastroPlacas.getSelectedRow();
        if (JOptionPane.showConfirmDialog(this, "Deseja remover a placa do banco de dados", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
                == JOptionPane.YES_OPTION) {
            if (linha != -1) {
                if (TranspD.deletarPlaca(Long.valueOf(txtCodigoPlaca.getText())) && ValidarPermissoes.validarPermissaoDelete(FrmCadTransportadora.class.getSimpleName())) {
                    TbPlaca.removeRow(linha);
                    limparCampos(jPanelPlaca);
                }
            }
        }
    }//GEN-LAST:event_btn_delPlacaActionPerformed

    private void btn_novoPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoPlacaActionPerformed
        limparCampos(jPanelPlaca);
        habilitarCamposTrans(false);
        habilitarJButtonTrans(false);
        btn_editPlaca.setEnabled(false);
        btn_delPlaca.setEnabled(false);
        btn_addPlaca.setEnabled(true);
        btn_salvarTransportadora.setEnabled(true);
        habilitarCamposPlaca(true);
        txt_nomeMoto.grabFocus();
    }//GEN-LAST:event_btn_novoPlacaActionPerformed

    private void btn_alterarTransportadoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_alterarTransportadoraActionPerformed
        int alterar = JOptionPane.showConfirmDialog(this, "Deseja editar a Transportadora?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (alterar == JOptionPane.YES_OPTION) {
            if (verificarTransportadora(TransB) && ValidarPermissoes.validarPermissaoUpdate(FrmCadTransportadora.class.getSimpleName())) {
                popularTransportadora(TransB);
                TranspD.atualizarTransportadoras(TransB);
                habilitarCamposPlaca(false);
                habilitarCamposTrans(false);
                habilitarJButtonPlaca(false);
                habilitarJButtonTrans(false);
                btn_novoPlaca.setEnabled(false);
                limparCampos(jPanelTrans);
                TbPlaca.limpar();
            }
        }
    }//GEN-LAST:event_btn_alterarTransportadoraActionPerformed

    private void tb_cadastroPlacasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_cadastroPlacasMouseClicked
        int linha = tb_cadastroPlacas.getSelectedRow();
        if (evt.getClickCount() == 2) {
            habilitarCamposPlaca(true);
            habilitarCamposTrans(false);
            btn_editPlaca.setEnabled(true);
            btn_delPlaca.setEnabled(true);
            btn_addPlaca.setEnabled(false);
            btn_alterarTransportadora.setEnabled(false);
            btn_removerTransportadora.setEnabled(false);
            preencherCamposPlaca(TbPlaca.getItem(linha));
        } else {
            habilitarJButtonPlaca(false);

        }

    }//GEN-LAST:event_tb_cadastroPlacasMouseClicked

    private void tb_listaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_listaMouseClicked
        int linha = tb_lista.getSelectedRow();
        if (evt.getClickCount() == 2) {
            if (JOptionPane.showConfirmDialog(this, "Deseja Editar Esta Transportadora?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
                    == JOptionPane.YES_OPTION) {
                limparCampos(jPanelTrans);
                TbPlaca.limpar();
                TransB = TranspD.buscarTransportadora(((TransportadorasBeans) TbConsulta.getValueAt(linha, TbConsulta.TRANSPORTADORA)).getID());
                preencheerCampos(TransB);
                abas.setSelectedIndex(0);
                habilitarCamposTrans(true);
                habilitarJButtonTrans(true);
                habilitarCamposPlaca(false);
                habilitarJButtonPlaca(false);
                btn_novoPlaca.setEnabled(true);
                btn_salvarTransportadora.setEnabled(false);
            }
        }
    }//GEN-LAST:event_tb_listaMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel aba1;
    private javax.swing.JTabbedPane abas;
    private javax.swing.JButton btn_addPlaca;
    private javax.swing.JButton btn_alterarTransportadora;
    private javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_delPlaca;
    private javax.swing.JButton btn_editPlaca;
    private javax.swing.JButton btn_novo;
    private javax.swing.JButton btn_novoPlaca;
    private javax.swing.JButton btn_removerTransportadora;
    private javax.swing.JButton btn_salvarTransportadora;
    private javax.swing.JComboBox<String> cb_carroceria;
    private javax.swing.JComboBox<String> cb_carroceria2;
    private javax.swing.JComboBox<String> cb_cpf;
    private javax.swing.JComboBox<String> cb_segmento;
    private javax.swing.JComboBox<String> cb_telefone;
    private javax.swing.JComboBox<String> cb_veiculo;
    private javax.swing.JComboBox<String> cb_veiculo2;
    private javax.swing.JCheckBox ch_sim;
    private javax.swing.JCheckBox ch_status;
    private javax.swing.JCheckBox ch_statusTrans;
    private javax.swing.JFormattedTextField ft_buscarPlaca;
    private javax.swing.JFormattedTextField ft_cnpjCpf;
    private javax.swing.JFormattedTextField ft_contatoTrans;
    private javax.swing.JFormattedTextField ft_cpf;
    private javax.swing.JFormattedTextField ft_placa;
    private javax.swing.JFormattedTextField ft_telefone;
    private javax.swing.JFormattedTextField ft_telefoneTrans;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JPanel jPanelConsulta;
    private javax.swing.JPanel jPanelPlaca;
    private javax.swing.JPanel jPanelTrans;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable tb_cadastroPlacas;
    private javax.swing.JTable tb_lista;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCodigoPlaca;
    private javax.swing.JTextField txt_ano;
    private javax.swing.JTextField txt_buscarTrans;
    private javax.swing.JTextField txt_carga;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_marca;
    private javax.swing.JTextField txt_modelo;
    private javax.swing.JTextField txt_nomeMoto;
    private javax.swing.JTextField txt_nomeTrans;
    // End of variables declaration//GEN-END:variables

    final void habilitarCamposTrans(boolean valor) {
        txt_nomeTrans.setEnabled(valor);
        txt_email.setEnabled(valor);
        ft_cnpjCpf.setEnabled(valor);
        ft_contatoTrans.setEnabled(valor);
        ft_telefoneTrans.setEnabled(valor);
        cb_cpf.setEnabled(valor);
        cb_segmento.setEnabled(valor);
        ch_statusTrans.setEnabled(valor);
    }

    final void habilitarCamposPlaca(boolean valor) {
        txt_carga.setEnabled(valor);
        txt_nomeMoto.setEnabled(valor);
        txt_marca.setEnabled(valor);
        txt_modelo.setEnabled(valor);
        txt_ano.setEnabled(valor);
        ft_cpf.setEnabled(valor);
        ft_telefone.setEnabled(valor);
        ft_placa.setEnabled(valor);
        cb_carroceria.setEnabled(valor);
        cb_veiculo.setEnabled(valor);
        cb_telefone.setEnabled(valor);
        ch_sim.setEnabled(valor);
        ch_status.setEnabled(valor);
    }

    final void habilitarJButtonTrans(boolean valor) {
        btn_salvarTransportadora.setEnabled(valor);
        btn_alterarTransportadora.setEnabled(valor);
        btn_removerTransportadora.setEnabled(valor);
    }

    final void habilitarJButtonPlaca(boolean valor) {
        btn_delPlaca.setEnabled(valor);
        btn_addPlaca.setEnabled(valor);
        btn_editPlaca.setEnabled(valor);
    }

    private void maskFormater() {
        try {
            CPFCNPJMask = new MaskFormatter("###.###.###-##");
            CNPJCPFMask = new MaskFormatter("##.###.###/####-##");

            TelMask = new MaskFormatter("(##)####-####");
            ft_telefoneTrans.setFormatterFactory(new DefaultFormatterFactory(TelMask));

            CelMask = new MaskFormatter("(##)#####-####");
            ft_contatoTrans.setFormatterFactory(new DefaultFormatterFactory(CelMask));

            CPFMask = new MaskFormatter("###.###.###-##");
            ft_cpf.setFormatterFactory(new DefaultFormatterFactory(CPFMask));

            PlacaMask = new MaskFormatter("UUU-####");
            ft_placa.setFormatterFactory(new DefaultFormatterFactory(PlacaMask));

            BuscarPlacaMask = new MaskFormatter("UUU-####");
            ft_buscarPlaca.setFormatterFactory(new DefaultFormatterFactory(BuscarPlacaMask));

        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, ex + " Erro ao formatar maskFormater!", "Erro", 0, FormatarICO.ICObtnSair());

        }
    }

    private void popularTransportadora(TransportadorasBeans T) {
        T.setNome(txt_nomeTrans.getText());
        T.setEmail(txt_email.getText());
        T.setContato(ft_contatoTrans.getText());
        T.setCNPJ(ft_cnpjCpf.getText());
        T.setTelefone(ft_telefoneTrans.getText());
        T.setSegmento(cb_segmento.getSelectedItem().toString());
        T.setStatus(ch_statusTrans.isSelected());
        T.setListPlacas(TbPlaca.getLista());
    }

    private void preencheerCampos(TransportadorasBeans t) {
        txtCodigo.setText(t.getID().toString());
        txt_nomeTrans.setText(t.getNome());
        txt_email.setText(t.getEmail());
        ft_contatoTrans.setText(t.getContato());
        if (t.getCNPJ().length() == 14) {
            cb_cpf.setSelectedIndex(1);
            ft_cnpjCpf.setText(t.getCNPJ());
        } else {
            cb_cpf.setSelectedIndex(2);
            ft_cnpjCpf.setText(t.getCNPJ());
        }
        ft_telefoneTrans.setText(t.getTelefone());
        cb_segmento.setSelectedItem(t.getSegmento());
        ch_statusTrans.setSelected(t.getStatus());
        TbPlaca.addLista(t.getListPlacas());
    }

    private void preencherCamposPlaca(CadPlacaBeans placa) {
        txtCodigoPlaca.setText("" + placa.getId());
        txt_nomeMoto.setText("" + placa.getMotorista());
        ft_cpf.setText("" + placa.getCpf());
        if (placa.getTelefone().length() == 13) {
            cb_telefone.setSelectedIndex(1);
            ft_telefone.setText(placa.getTelefone());
        } else {
            cb_telefone.setSelectedIndex(2);
            ft_telefone.setText(placa.getTelefone());
        }
        cb_telefone.setSelectedItem("" + placa.getTelefone());
        ft_telefone.setText("" + placa.getTelefone());
        ft_placa.setText("" + placa.getPlaca());
        txt_marca.setText("" + placa.getMarca());
        txt_modelo.setText("" + placa.getModelo());
        txt_carga.setText("" + placa.getCapacidadeCarga());
        if (ch_sim.isSelected() == true) {
            ch_sim.setSelected(Boolean.valueOf("" + placa.getCamProprio()));
        } else if (ch_sim.isSelected() == false) {
            ch_sim.setSelected(Boolean.valueOf("" + placa.getCamProprio()));
        }
        if (ch_status.isSelected() == true) {
            ch_status.setSelected(Boolean.valueOf("" + placa.getStatus()));
        } else if (ch_status.isSelected() == false) {
            ch_status.setSelected(Boolean.valueOf("" + placa.getStatus()));
        }
        txt_ano.setText("" + placa.getAno());
        cb_veiculo.setSelectedItem("" + placa.getVeiculo());
        cb_carroceria.setSelectedItem("" + placa.getCarroceria());

    }

    private void editPlaca(CadPlacaBeans placa) {
        placa.setTransportadora(TransB);
        placa.setMotorista(txt_nomeMoto.getText());
        placa.setCpf(ft_cpf.getText());
        placa.setTelefone(ft_telefone.getText());
        placa.setPlaca(ft_placa.getText());
        placa.setMarca(txt_marca.getText());
        placa.setModelo(txt_modelo.getText());
        placa.setCapacidadeCarga(Corretores.ConverQuilosDouble(txt_carga.getText()));
        placa.setCamProprio(ch_sim.isSelected());
        placa.setStatus(ch_status.isSelected());
        placa.setAno(txt_ano.getText());
        placa.setVeiculo(cb_veiculo.getSelectedItem().toString());
        placa.setCarroceria(cb_carroceria.getSelectedItem().toString());
        placa.setId(Long.valueOf(txtCodigoPlaca.getText()));

    }

    private void popularPlaca() {
        CadPlacaBeans placa = new CadPlacaBeans();
        placa.setTransportadora(TransB);
        placa.setMotorista(txt_nomeMoto.getText());
        placa.setCpf(ft_cpf.getText());
        placa.setTelefone(ft_telefone.getText());
        placa.setPlaca(ft_placa.getText());
        placa.setMarca(txt_marca.getText());
        placa.setModelo(txt_modelo.getText());
        placa.setCapacidadeCarga(Corretores.ConverQuilosDouble(txt_carga.getText()));
        placa.setCamProprio(ch_sim.isSelected());
        placa.setStatus(ch_status.isSelected());
        placa.setAno(txt_ano.getText());
        placa.setVeiculo(cb_veiculo.getSelectedItem().toString());
        placa.setCarroceria(cb_carroceria.getSelectedItem().toString());
        if (verificarPlaca(placa)) {
            TbPlaca.addBeans(placa);
            txt_nomeMoto.grabFocus();
            txt_nomeMoto.selectAll();
            limparCampos(jPanelPlaca);
        }
    }

    private void limparCampos(Container container) {
        for (int i = 0; i < container.getComponentCount(); i++) {
            Component c = container.getComponent(i);
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            } else if (c instanceof JComboBox) {
                ((JComboBox) c).setSelectedIndex(0);
            } else if (c instanceof JFormattedTextField) {
                ((JFormattedTextField) c).setValue(null);
            } else if (c instanceof JCheckBox) {
                ((JCheckBox) c).setSelected(false);
            }
        }
    }

    public boolean verificarPlaca(CadPlacaBeans placa) {
        if (txt_nomeMoto.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha o nome do Motorista", "Atenção", 0);
            return false;
        }
        if (ft_placa.getText().equals("   -    ")) {
            JOptionPane.showMessageDialog(null, "Preencha a Placa", "Atenção", 0);
            return false;
        }

        return true;
    }

    public boolean verificarTransportadora(TransportadorasBeans t) {

        if (txt_nomeTrans.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha o nome da Transportadora", "Atenção", 0);
            return false;
        }
        if (ft_cnpjCpf.getText().equals("   .   .   -  ") || ft_cnpjCpf.getText().equals("  .   .   /    -  ") || ft_cnpjCpf.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha o CPF/CNPJ", "Atenção", 0);
            return false;
        }
        if (cb_segmento.getSelectedItem().equals("Selecione o Segmento")) {
            JOptionPane.showMessageDialog(null, "Selecione o Segmento", "Atenção", 0);
            return false;
        }
        return true;
    }

    public String getWhere() {
        String s = "";

        if (!txt_buscarTrans.getText().equals("")) {
            s += " and T.nome like '" + txt_buscarTrans.getText() + "%'";
        }
        if (!ft_buscarPlaca.getText().equals("   -    ")) {
            s += " and P.placa = '" + ft_buscarPlaca.getText() + "'";
        }
        if (cb_carroceria2.getSelectedIndex() > 0) {
            s += " and P.carroceria = '" + cb_carroceria2.getSelectedItem().toString() + "'";
        }
        if (cb_veiculo2.getSelectedIndex() > 0) {
            s += " and P.veiculo = '" + cb_veiculo2.getSelectedItem().toString() + "'";
        }
        if (!s.equals("")) {
            s = " WHERE " + s.replaceFirst("and", "");
        }
        // System.out.println(s);
        return s;
    }

}
