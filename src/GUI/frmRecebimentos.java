package GUI;

import Beans.AtividadeBeans;
import Beans.BancosBeans;
import Beans.CentroDeResultado;
import Beans.ContaBancariaBeans;
import Beans.FornecedoresBeans;
import Beans.MoedaBeans;
import Beans.PlanoContaBeans;
import Beans.PropriedadesBeans;
import Beans.RecebimentosBeans;
import DAO.Diversas;
import DAO.DiversasHibernate;
import DAO.RecebimentosDAO;
import static GUI.Principal.ListaPropriedades;
import static GUI.Principal.listContaOrigem;
import static GUI.Principal.listMoeda;
import static GUI.Principal.listPlanoContas;
import static GUI.Principal.listaAtividades;
import static GUI.Principal.listaCentroResultado;
import static GUI.frmLogin.UsuarioLogadoBeans;
import Icones.FormatarICO;
import TableModel.TableModelRecebimentos;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import Utilitarios.ValidarPermissoes;
import com.sun.glass.events.KeyEvent;
import com.toedter.calendar.JDateChooser;
import componentes.UJComboBox;
import java.awt.AWTKeyStroke;
import java.awt.Component;
import java.awt.Container;
import java.awt.KeyboardFocusManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class frmRecebimentos extends javax.swing.JInternalFrame {

    RecebimentosBeans RectoB;
    RecebimentosDAO RectoD;
    Diversas DiversasD;
    MaskFormatter CPFMask;
    MaskFormatter CNPJMask;
    MaskFormatter DataMask;
    TableModelRecebimentos TbRecebimentos;
    CentralizarTabela Centralizar;
    FornecedoresBeans ClienteB;
    Integer escalaAbate = 0;
    Integer nCtoGrao = 0;

    public frmRecebimentos() {
        initComponents();
        DiversasD = new Diversas();
        RectoB = new RecebimentosBeans();
        RectoD = new RecebimentosDAO();
        maskFormaterCPF();
        carregarFazendas();
        carregarPlanoContas();
        carregarContasOrigem();
        carregarMoeda();
        carregarAtividades();
        carregarCentroResultado();
        KeyPress(jPanel1);
        txt_dtRecebimento.requestFocus();

    }

    private void KeyPress(Container container) {
        for (Component c : container.getComponents()) {
            HashSet<AWTKeyStroke> conjForward = new HashSet<>(c.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
            conjForward.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
            //  conjForward.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_DOWN, 0));
            c.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conjForward);
        }
    }

    private void carregarFazendas() {
        if (ListaPropriedades == null) {
            ListaPropriedades = DiversasHibernate.getListaFazendas();
        }
        cb_fazenda.addItem(new PropriedadesBeans(0, "-"));
        for (PropriedadesBeans p : ListaPropriedades) {
            cb_fazenda.addItem(p);
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
            if (p.getGrupoConta() != null) {
                if (p.getGrupoConta().getEntrada() == true || p.getGrupoConta().getTransferencia() == true) {
                    cb_planoConta.addItem(p);
                }
            }

        }
    }

    private void carregarContasOrigem() {
        if (listContaOrigem == null) {
            listContaOrigem = DiversasHibernate.getListaContasBancarias();
        }
        cb_contaDestino.addItem(new ContaBancariaBeans(0, "-"));
        for (ContaBancariaBeans list : listContaOrigem) {
            cb_contaDestino.addItem(list);
        }
    }

    private void maskFormaterCPF() {
        try {
            CNPJMask = new MaskFormatter("##.###.###/####-##");
            CPFMask = new MaskFormatter("###.###.###-##");
            DataMask = new MaskFormatter("##/##/####");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    private void carregarMoeda() {
        if (listMoeda == null) {
            listMoeda = DiversasHibernate.getListaMoeda();
        }
        cb_moeda.addItem(new MoedaBeans(0, "-"));
        for (MoedaBeans list : listMoeda) {
            cb_moeda.addItem(list);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        cb_doc = new javax.swing.JComboBox<>();
        cb_fazenda = new javax.swing.JComboBox<>();
        txt_idCliente = new javax.swing.JTextField();
        txt_valorMoeda = new javax.swing.JTextField();
        txt_taxa = new javax.swing.JTextField();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        txt_valorTotal = new javax.swing.JTextField();
        txt_id = new javax.swing.JTextField();
        txt_descricao = new javax.swing.JTextField();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel13 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        cb_atividade = new javax.swing.JComboBox<>();
        txt_nEscala = new javax.swing.JTextField();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        cb_contaDestino = new javax.swing.JComboBox<>();
        cb_moeda = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel14 = new javax.swing.JLabel();
        txt_dtRecebimento =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        ch_previsto = new javax.swing.JCheckBox();
        ch_recebido = new javax.swing.JCheckBox();
        txt_nomeCliente = new javax.swing.JTextField();
        btn_editarPagto = new javax.swing.JButton();
        btn_excluirPagto = new javax.swing.JButton();
        btn_pesquisarPagto = new javax.swing.JButton();
        btn_SalvarPagto = new javax.swing.JButton();
        txt_cpf = new javax.swing.JFormattedTextField();
        cb_planoConta = new componentes.UJComboBox();
        jLabel25 = new javax.swing.JLabel();
        cb_centroResultado = new componentes.UJComboBox();
        btn_cadastrar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Recebimentos");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setName(""); // NOI18N

        cb_doc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "CPF", "CNPJ" }));
        cb_doc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_docItemStateChanged(evt);
            }
        });

        txt_idCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_idCliente.setText("0");

        txt_valorMoeda.setText("0,00");
        txt_valorMoeda.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_valorMoedaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_valorMoedaFocusLost(evt);
            }
        });
        txt_valorMoeda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_valorMoedaKeyReleased(evt);
            }
        });

        txt_taxa.setText("R$ 0,00");
        txt_taxa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_taxaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_taxaFocusLost(evt);
            }
        });
        txt_taxa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_taxaKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("ID");

        txt_valorTotal.setText("R$ 0,00");
        txt_valorTotal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_valorTotalFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_valorTotalFocusLost(evt);
            }
        });

        txt_id.setEditable(false);
        txt_id.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txt_descricao.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Conta Destino");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Atividade");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Nº Escala");

        cb_atividade.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_atividadeItemStateChanged(evt);
            }
        });

        txt_nEscala.setEditable(false);
        txt_nEscala.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nEscala.setText("0");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Moeda");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Valor em Moeda");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Taxa");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Valor em R$");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Descrição");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("CNPJ / CPF");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Cliente");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Fazenda");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Plano Conta");

        cb_moeda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_moedaItemStateChanged(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Data do Recebimento");

        buttonGroup1.add(ch_previsto);
        ch_previsto.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_previsto.setText("Previsto");

        buttonGroup1.add(ch_recebido);
        ch_recebido.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_recebido.setText("Recebido");

        btn_editarPagto.setBackground(new java.awt.Color(255, 255, 255));
        btn_editarPagto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/editar_32_32.png"))); // NOI18N
        btn_editarPagto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_editarPagto.setEnabled(false);
        btn_editarPagto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarPagtoActionPerformed(evt);
            }
        });

        btn_excluirPagto.setBackground(new java.awt.Color(255, 255, 255));
        btn_excluirPagto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/deletar_32_32.png"))); // NOI18N
        btn_excluirPagto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_excluirPagto.setEnabled(false);
        btn_excluirPagto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirPagtoActionPerformed(evt);
            }
        });

        btn_pesquisarPagto.setBackground(new java.awt.Color(255, 255, 255));
        btn_pesquisarPagto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar.png"))); // NOI18N
        btn_pesquisarPagto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_pesquisarPagto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisarPagtoActionPerformed(evt);
            }
        });

        btn_SalvarPagto.setBackground(new java.awt.Color(255, 255, 255));
        btn_SalvarPagto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/salvar_32_32.png"))); // NOI18N
        btn_SalvarPagto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_SalvarPagto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalvarPagtoActionPerformed(evt);
            }
        });

        txt_cpf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_cpf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_cpfFocusLost(evt);
            }
        });

        cb_planoConta.setAutocompletar(true);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("C. Resultado");

        cb_centroResultado.setAutocompletar(true);
        cb_centroResultado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cb_centroResultadoFocusGained(evt);
            }
        });

        btn_cadastrar.setBackground(new java.awt.Color(255, 255, 255));
        btn_cadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar pequeno.png"))); // NOI18N
        btn_cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cadastrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14)
                        .addGap(4, 4, 4)
                        .addComponent(txt_dtRecebimento, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(ch_previsto)
                        .addGap(18, 18, 18)
                        .addComponent(ch_recebido)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel3)
                        .addGap(4, 4, 4)
                        .addComponent(txt_nEscala, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_planoConta, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_doc, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addGap(4, 4, 4)
                        .addComponent(txt_idCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txt_nomeCliente)
                        .addGap(0, 0, 0)
                        .addComponent(btn_cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_contaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(4, 4, 4)
                                .addComponent(cb_atividade, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel25)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cb_centroResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_descricao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(4, 4, 4)
                                .addComponent(cb_moeda, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_valorMoeda, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_taxa, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_valorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_SalvarPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_editarPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_pesquisarPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_excluirPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(319, 319, 319))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txt_dtRecebimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ch_previsto)
                    .addComponent(ch_recebido)
                    .addComponent(jLabel3)
                    .addComponent(txt_nEscala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(cb_contaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cb_moeda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txt_valorMoeda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txt_taxa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txt_valorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txt_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cb_centroResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(cb_atividade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel12)
                    .addComponent(cb_planoConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(cb_doc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txt_idCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_excluirPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesquisarPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_editarPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_SalvarPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_SalvarPagtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalvarPagtoActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(null, "Deseja Salvar este Recebimento?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION) {
            popularBeans(RectoB);
            if (verificarBeans(RectoB) && ValidarPermissoes.validarPermissaoInsert(frmRecebimentos.class.getSimpleName())) {
                if (RectoD.inserirRecebimento(RectoB)) {
                    limparFormulario();
                    btn_SalvarPagto.setEnabled(true);
                    btn_editarPagto.setEnabled(false);
                    btn_excluirPagto.setEnabled(false);
                }
            }
        }
    }//GEN-LAST:event_btn_SalvarPagtoActionPerformed

    private void btn_editarPagtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarPagtoActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja Editar este Pagamento?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION) {
            popularBeans(RectoB);
            if (verificarBeans(RectoB) && ValidarPermissoes.validarPermissaoUpdate(frmRecebimentos.class.getSimpleName())) {
                if (RectoD.editarRecebimento(RectoB)) {
                    limparFormulario();
                    btn_SalvarPagto.setEnabled(true);
                    btn_editarPagto.setEnabled(false);
                    btn_excluirPagto.setEnabled(false);
                }
            }
        }
    }//GEN-LAST:event_btn_editarPagtoActionPerformed

    private void btn_pesquisarPagtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisarPagtoActionPerformed

    }//GEN-LAST:event_btn_pesquisarPagtoActionPerformed

    private void btn_excluirPagtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirPagtoActionPerformed
        int excluir = JOptionPane.showConfirmDialog(null, "Atenção, está operação excluira todas as informações gerenciais e fiscais, deseja continuar?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (excluir == JOptionPane.YES_OPTION) {
            if (true) {
                limparFormulario();
                btn_SalvarPagto.setEnabled(true);
                txt_id.setEditable(true);
                btn_editarPagto.setEnabled(false);
                btn_excluirPagto.setEnabled(false);
            }
        }
    }//GEN-LAST:event_btn_excluirPagtoActionPerformed

    private void cb_docItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_docItemStateChanged
        if (cb_doc.getSelectedItem().toString().equals("CPF")) {
            txt_cpf.setValue(null);
            txt_cpf.setFormatterFactory(new DefaultFormatterFactory(CPFMask));
        } else {
            txt_cpf.setValue(null);
            txt_cpf.setFormatterFactory(new DefaultFormatterFactory(CNPJMask));
        }
    }//GEN-LAST:event_cb_docItemStateChanged

    private void txt_valorMoedaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorMoedaFocusGained
        txt_valorMoeda.selectAll();
    }//GEN-LAST:event_txt_valorMoedaFocusGained

    private void txt_taxaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_taxaFocusGained
        txt_taxa.selectAll();
    }//GEN-LAST:event_txt_taxaFocusGained

    private void txt_valorTotalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorTotalFocusGained
        txt_valorTotal.selectAll();
    }//GEN-LAST:event_txt_valorTotalFocusGained

    private void txt_valorMoedaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorMoedaFocusLost
        txt_valorMoeda.setText(Corretores.ConverterDecimalReais(txt_valorMoeda.getText(), getMoeda(cb_moeda).getSifra()));
    }//GEN-LAST:event_txt_valorMoedaFocusLost

    private void txt_valorTotalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorTotalFocusLost
        txt_valorTotal.setText(Corretores.ConverterDecimalReais(txt_valorTotal.getText()));
    }//GEN-LAST:event_txt_valorTotalFocusLost

    private void cb_moedaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_moedaItemStateChanged
        String moeda = cb_moeda.getSelectedItem().toString();
        if (moeda.equals("Real")) {
            txt_valorMoeda.setEnabled(false);
            txt_taxa.setEnabled(false);
            txt_valorTotal.setEditable(true);
        } else {
            txt_valorMoeda.setEnabled(true);
            txt_taxa.setEnabled(true);
            txt_valorTotal.setEditable(false);
        }
    }//GEN-LAST:event_cb_moedaItemStateChanged

    private void txt_valorMoedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_valorMoedaKeyReleased
        calcularValorTotal();
    }//GEN-LAST:event_txt_valorMoedaKeyReleased

    private void txt_taxaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_taxaKeyReleased
        calcularValorTotal();
    }//GEN-LAST:event_txt_taxaKeyReleased

    private void txt_taxaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_taxaFocusLost
        txt_taxa.setText(Corretores.ConverterDecimalReais(txt_taxa.getText()));
    }//GEN-LAST:event_txt_taxaFocusLost

    private void cb_centroResultadoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cb_centroResultadoFocusGained
        setExpandedComboBox(evt.getComponent());
    }//GEN-LAST:event_cb_centroResultadoFocusGained

    private void cb_atividadeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_atividadeItemStateChanged
        if (cb_atividade.getSelectedIndex() > 0) {
            carregarCentroResultado(getAtividade(cb_atividade).getID());
        }
    }//GEN-LAST:event_cb_atividadeItemStateChanged

    private void btn_cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cadastrarActionPerformed
        FrmConsultaFornecedor consulta = new FrmConsultaFornecedor(null, true);
        consulta.tb_forn.addMouseListener(getConsultaFornecedorAdapter(consulta));
        consulta.setLocationRelativeTo(null);
        consulta.setVisible(true);
    }//GEN-LAST:event_btn_cadastrarActionPerformed

    private void txt_cpfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cpfFocusLost
        String t = txt_cpf.getText().replaceAll("[^0-9]", "");
        if (!t.equals("")) {
            ClienteB = DiversasHibernate.buscarFornecedor(txt_cpf.getText());
            if (ClienteB != null) {
                preencherCamposFornecedor(ClienteB);
            } else if (ClienteB == null) {
                int editar = JOptionPane.showConfirmDialog(null, "<html> <B>Fornecedor Não Encontrado,</B> <br> Deseja Cadastrar Novo Fornecedor? </html>", "Atenção", JOptionPane.YES_NO_OPTION);
                if (editar == JOptionPane.YES_OPTION) {
                    frmCadFornecedores frmFornecedor = new frmCadFornecedores();
                    this.getParent().add(frmFornecedor);
                    frmFornecedor.btn_novo1.doClick();
                    frmFornecedor.cb_tipoPessoa.setSelectedItem(cb_doc.getSelectedItem());
                    frmFornecedor.txt_cnpj.setText(txt_cpf.getText());
                    frmFornecedor.setVisible(true);
                    frmFornecedor.txt_nomeFantasia.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_txt_cpfFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btn_SalvarPagto;
    javax.swing.JButton btn_cadastrar;
    javax.swing.JButton btn_editarPagto;
    javax.swing.JButton btn_excluirPagto;
    javax.swing.JButton btn_pesquisarPagto;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<AtividadeBeans> cb_atividade;
    componentes.UJComboBox cb_centroResultado;
    private javax.swing.JComboBox<ContaBancariaBeans> cb_contaDestino;
    private javax.swing.JComboBox<String> cb_doc;
    private javax.swing.JComboBox<PropriedadesBeans> cb_fazenda;
    private javax.swing.JComboBox<MoedaBeans> cb_moeda;
    private componentes.UJComboBox cb_planoConta;
    public javax.swing.JCheckBox ch_previsto;
    public javax.swing.JCheckBox ch_recebido;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JFormattedTextField txt_cpf;
    public javax.swing.JTextField txt_descricao;
    public com.toedter.calendar.JDateChooser txt_dtRecebimento;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_idCliente;
    public javax.swing.JTextField txt_nEscala;
    private javax.swing.JTextField txt_nomeCliente;
    private javax.swing.JTextField txt_taxa;
    private javax.swing.JTextField txt_valorMoeda;
    public javax.swing.JTextField txt_valorTotal;
    // End of variables declaration//GEN-END:variables

    private MouseAdapter getConsultaFornecedorAdapter(FrmConsultaFornecedor frmConsulta) {
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linha = frmConsulta.tb_forn.getSelectedRow();
                if (e.getClickCount() == 2) {
                    if (linha >= 0) {
                        ClienteB = DiversasHibernate.getFornecedor((Integer) frmConsulta.TbForn.getValueAt(linha, frmConsulta.TbForn.ID));
                        if (ClienteB != null) {
                            preencherCamposFornecedor(ClienteB);
                        }
                        frmConsulta.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Selecione um Número da Frota", "Atenção!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        };
        return adapter;
    }

    private void preencherCamposFornecedor(FornecedoresBeans ClienteB) {
        if (ClienteB.getCnpj().length() > 14) {
            cb_doc.setSelectedItem("CNPJ");
            txt_cpf.setText(ClienteB.getCnpj());
        } else {
            cb_doc.setSelectedItem("CPF");
            txt_cpf.setText(ClienteB.getCnpj());
        }
        txt_idCliente.setText(String.valueOf(ClienteB.getID()));
        txt_nomeCliente.setText(ClienteB.getRazaoSocial());

    }

    private void setExpandedComboBox(Component c) {
        if (c instanceof JComboBox) {
            ((JComboBox) c).setPopupVisible(true);
        } else if (c instanceof UJComboBox) {
            ((UJComboBox) c).setPopupVisible(true);
        }
    }

    private void popularBeans(RecebimentosBeans beans) {
        beans.setUsuario(UsuarioLogadoBeans);
        beans.setDtLancamento(new Date(System.currentTimeMillis()));
        beans.setDtRecebimentos(txt_dtRecebimento.getDate());
        // Manter a Ordem para Evitar novos Getters do ComboBox
        beans.setAtividade(getAtividade(cb_atividade));
        beans.setCentroResultado(getCentroResultado(cb_centroResultado));
        beans.setIdConta(getContaBancaria(cb_contaDestino));
        beans.setIdFazenda(getFazenda(cb_fazenda));
        beans.setIdMoeda(getMoeda(cb_moeda));
        beans.setPlanoConta(getPlanoConta(cb_planoConta));

        beans.setIdCliente(ClienteB);
        beans.setCNPJ(ClienteB.getCnpj());
        beans.setNomeCliente(ClienteB.getRazaoSocial());

        beans.setDescricao(txt_descricao.getText());
        beans.setContaDestino(beans.getIdConta().getDescricao());
        beans.setDescPlanoConta(beans.getPlanoConta().getDescricao());
        beans.setFazenda(beans.getIdFazenda().getNome());

        beans.setStatus(ch_recebido.isSelected());
        beans.setMoeda(beans.getIdMoeda().getMoeda());
        beans.setSifra(beans.getIdMoeda().getSifra());
        beans.setTaxa(Corretores.ConverterStringDouble(txt_taxa.getText()));
        beans.setValorEmMoeda(Corretores.ConverterStringDouble(txt_valorMoeda.getText()));
        beans.setValorTotal(Corretores.ConverterStringDouble(txt_valorTotal.getText()));
        
        escalaAbate = new Integer(txt_nEscala.getText());
        beans.setnCtoGrao(nCtoGrao);
        beans.setnEscala(escalaAbate);
    }

    private Boolean verificarBeans(RecebimentosBeans beans) {
        if (beans.getDtRecebimentos() == null) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Data Prevista!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (beans.getDtRecebimentos() != null && Corretores.verificarData(beans.getDtRecebimentos())) {

        }
        if (beans.getAtividade().getID() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Atividade!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (beans.getCentroResultado().getID() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Centro de Resultado!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (beans.getIdCliente() == null) {
            JOptionPane.showMessageDialog(null, "Verifique o Cliente!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (beans.getIdConta().getIdConta() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Conta Bancária!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (beans.getIdFazenda().getCodigo() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Fazenda!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (beans.getIdMoeda().getIdMoeda() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Moeda!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (beans.getPlanoConta().getID() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Plano de Contas!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (beans.getDescricao().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Descrição!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }

    private AtividadeBeans getAtividade(JComboBox<AtividadeBeans> combo) {
        return (AtividadeBeans) combo.getSelectedItem();
    }

    private PropriedadesBeans getFazenda(JComboBox combo) {
        return (PropriedadesBeans) combo.getSelectedItem();
    }

    private ContaBancariaBeans getContaBancaria(JComboBox<ContaBancariaBeans> combo) {
        return (ContaBancariaBeans) combo.getSelectedItem();
    }

    private PlanoContaBeans getPlanoConta(UJComboBox combo) {
        return (PlanoContaBeans) combo.getSelectedItem();
    }

    private CentroDeResultado getCentroResultado(UJComboBox combo) {
        return (CentroDeResultado) combo.getSelectedItem();
    }

    private MoedaBeans getMoeda(JComboBox<MoedaBeans> combo) {
        return (MoedaBeans) combo.getSelectedItem();
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

    private void setComboBoxBanco(JComboBox<BancosBeans> combobox, String nomeBanco) {
        combobox.setSelectedIndex(0);
        for (int i = 0; i < combobox.getModel().getSize(); i++) {
            if (combobox.getItemAt(i).getNomeBanco().equals(nomeBanco)) {
                combobox.setSelectedIndex(i);
                break;
            }
        }
    }

    private void setComboBoxBanco(JComboBox<BancosBeans> combobox, Integer id) {
        combobox.setSelectedIndex(0);
        for (int i = 0; i < combobox.getModel().getSize(); i++) {
            if (Objects.equals(combobox.getItemAt(i).getIdBanco(), id)) {
                combobox.setSelectedIndex(i);
                break;
            }
        }
    }

    private void setComboBoxContaOrigem(JComboBox<ContaBancariaBeans> combobox, Integer id) {
        combobox.setSelectedIndex(0);
        for (int i = 0; i < combobox.getModel().getSize(); i++) {
            if (Objects.equals(combobox.getItemAt(i).getIdConta(), id)) {
                combobox.setSelectedIndex(i);
                break;
            }
        }
    }

    private void setComboBoxMoeda(JComboBox<MoedaBeans> combobox, Integer id) {
        combobox.setSelectedIndex(0);
        for (int i = 0; i < combobox.getModel().getSize(); i++) {
            if (Objects.equals(combobox.getItemAt(i).getIdMoeda(), id)) {
                combobox.setSelectedIndex(i);
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

    public void setComboAtividade(JComboBox<AtividadeBeans> combobox, Integer idAtividade) {
        combobox.setSelectedIndex(0);
        for (int i = 0; i < combobox.getModel().getSize(); i++) {
            if (Objects.equals(combobox.getItemAt(i).getID(), idAtividade)) {
                combobox.setSelectedIndex(i);
                break;
            }
        }
    }

    private void setComboBoxCentroResultado(UJComboBox combo, CentroDeResultado centro) {
        if (centro != null) {
            for (int i = 0; i < combo.getModel().getSize(); i++) {
                CentroDeResultado c = (CentroDeResultado) combo.getItemAt(i);
                if (Objects.equals(c.getID(), centro.getID())) {
                    combo.setSelectedIndex(i);
                }
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

    private void limparFormulario() {
        txt_id.setText("");
        txt_nEscala.setText("0");
        txt_dtRecebimento.setDate(null);
        ch_previsto.setSelected(true);
        cb_contaDestino.setSelectedIndex(0);
        cb_centroResultado.setSelectedIndex(0);
        cb_moeda.setSelectedIndex(0);
        txt_valorMoeda.setText("0,00");
        txt_taxa.setText("R$ 0,00");
        txt_valorTotal.setText("R$ 0,00");
        cb_atividade.setSelectedIndex(0);
        txt_descricao.setText("");
        cb_fazenda.setSelectedIndex(0);
        cb_planoConta.setSelectedIndex(0);
        cb_doc.setSelectedIndex(0);
        txt_cpf.setText("");
        txt_idCliente.setText("0");
        txt_nomeCliente.setText("");
    }

    public void preencherFormulario(RecebimentosBeans rec) {
        RectoB = rec;
        ClienteB = rec.getIdCliente();
        txt_nEscala.setText(rec.getnEscala().toString());
        txt_dtRecebimento.setDate(rec.getDtRecebimentos());
        setComboBoxContaOrigem(cb_contaDestino, rec.getIdConta().getIdConta());
        setComboBoxMoeda(cb_moeda, rec.getIdMoeda().getIdMoeda());
        txt_valorMoeda.setText(Corretores.ConverterDoubleReais(rec.getValorEmMoeda(), rec.getIdMoeda().getSifra()));
        txt_taxa.setText(Corretores.ConverterDoubleReais(rec.getTaxa()));
        txt_valorTotal.setText(Corretores.ConverterDoubleReais(rec.getValorTotal()));
        setComboAtividade(cb_atividade, rec.getAtividade().getID());
        setComboBoxCentroResultado(cb_centroResultado, rec.getCentroResultado());
        txt_descricao.setText(rec.getDescricao());
        preencherDadosFornecedor(rec);
        setComboFazenda(cb_fazenda, rec.getIdFazenda().getCodigo());
        setComboBoxPlanoConta(cb_planoConta, rec.getPlanoConta().getID());
        if (rec.isStatus()) {
            ch_recebido.setSelected(true);
        } else {
            ch_previsto.setSelected(true);
        }

    }

    private void preencherDadosFornecedor(RecebimentosBeans rec) {
        try {
        if (rec.getCNPJ().length() > 14) {
            cb_doc.setSelectedIndex(2);
        } else {
            cb_doc.setSelectedIndex(1);
        }
        txt_cpf.setText(RectoB.getCNPJ());
        txt_idCliente.setText(String.valueOf(RectoB.getIdCliente().getID()));
        txt_nomeCliente.setText(RectoB.getNomeCliente());            
        } catch (Exception e) {
              JOptionPane.showMessageDialog(null, " Erro ao Preencher Dados Fornecedor!", "Erro", 0, FormatarICO.ICObtnSair());
        }

    }

    private void calcularValorTotal() {
        String vf = "R$ 0,00";
        if (!txt_valorMoeda.getText().equals("") && !txt_taxa.getText().equals("")) {
            double valorMoeda = Double.parseDouble(txt_valorMoeda.getText().replaceAll("[^0-9&&[^,]]", "").replace(",", "."));
            double taxa = Double.parseDouble(txt_taxa.getText().replaceAll("[^0-9&&[^,]]", "").replace(",", "."));
            double ValorTotal = valorMoeda * taxa;
            vf = new DecimalFormat("R$ #,###,###,##0.00").format(ValorTotal);
        }
        txt_valorTotal.setText(vf);
    }

}
