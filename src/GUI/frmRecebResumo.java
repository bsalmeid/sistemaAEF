package GUI;

import Beans.AtividadeBeans;
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
import TableModel.TableModelRecebimentos;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import com.toedter.calendar.JDateChooser;
import componentes.UJComboBox;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.MaskFormatter;

public class frmRecebResumo extends javax.swing.JInternalFrame {

    RecebimentosBeans RectoB;
    RecebimentosDAO RectoD;
    Diversas DiversasD;
    MaskFormatter CPFMask;
    MaskFormatter CNPJMask;
    TableModelRecebimentos TbRecebimentos;
    CentralizarTabela Centralizar;
    RendererRecebimentos rendererRecebimentos;
    FornecedoresBeans ClienteB;

    public frmRecebResumo() {
        initComponents();
        RectoD = new RecebimentosDAO();
        Centralizar = new CentralizarTabela();
        rendererRecebimentos = new RendererRecebimentos();
        tabelaRecebimentos();
        carregarAtividades();
        carregarCentroResultado();
        carregarContasOrigem();
        carregarPlanoContas();
        carregarMoeda();
        maskFormaterCPF();
        carregarFazendas();

        txt_dtInicial.setDate(Corretores.PrimeiroDiaMes());
        txt_dtFinal.setDate(Corretores.UltimoDiaMes());

    }

    private JTable tabelaRecebimentos() {
        tb_recebimentos.setModel(getTableModelRecebimentos());
        Centralizar.centralizarTabela(tb_recebimentos);
        ((DefaultTableCellRenderer) tb_recebimentos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        for (int c = 0; c < tb_recebimentos.getColumnCount(); c++) {
            if (c == TbRecebimentos.ID || c == TbRecebimentos.IDCONTA || c == TbRecebimentos.NCTOGRAO || c == TbRecebimentos.IDMOEDA || c == TbRecebimentos.IDFAZENDA
                    || c == TbRecebimentos.IDMOEDA || c == TbRecebimentos.SIFRA || c == TbRecebimentos.IDCLIENTE || c == TbRecebimentos.CNPJ || c == TbRecebimentos.DESCPLANOCONTAS) {
                tb_recebimentos.getColumnModel().getColumn(c).setPreferredWidth(0);// ID conta
                tb_recebimentos.getColumnModel().getColumn(c).setMinWidth(0);
                tb_recebimentos.getColumnModel().getColumn(c).setMaxWidth(0);
            }
        }

        tb_recebimentos.getColumnModel().getColumn(TbRecebimentos.STATUS).setPreferredWidth(40);// ID
        tb_recebimentos.getColumnModel().getColumn(TbRecebimentos.ID).setPreferredWidth(40);// ID
        tb_recebimentos.getColumnModel().getColumn(TbRecebimentos.DTRECEBIMENTO).setPreferredWidth(90);// Data Recebimento
        tb_recebimentos.getColumnModel().getColumn(TbRecebimentos.CONTADESTINO).setPreferredWidth(110);// Conta Bancária
        tb_recebimentos.getColumnModel().getColumn(TbRecebimentos.MOEDA).setPreferredWidth(60);// Moeda
        tb_recebimentos.getColumnModel().getColumn(TbRecebimentos.DESCRICAO).setPreferredWidth(130);// Descricao
        tb_recebimentos.getColumnModel().getColumn(TbRecebimentos.VALORTOTAL).setPreferredWidth(130);// Valor Total

        tb_recebimentos.getColumnModel().getColumn(TbRecebimentos.VALORMOEDA).setCellRenderer(rendererRecebimentos);
        tb_recebimentos.getColumnModel().getColumn(TbRecebimentos.TAXA).setCellRenderer(rendererRecebimentos);
        tb_recebimentos.getColumnModel().getColumn(TbRecebimentos.VALORTOTAL).setCellRenderer(rendererRecebimentos);
        return tb_recebimentos;
    }

    private TableModelRecebimentos getTableModelRecebimentos() {
        if (TbRecebimentos == null) {
            TbRecebimentos = new TableModelRecebimentos();
        }
        return TbRecebimentos;
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

    private void carregarFazendas() {
        if (ListaPropriedades == null) {
            ListaPropriedades = DiversasHibernate.getListaFazendas();
        }
        cb_fazenda.addItem(new PropriedadesBeans(0, "-"));
        for (PropriedadesBeans p : ListaPropriedades) {
            cb_fazenda.addItem(p);
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

    private void carregarMoeda() {
        if (listMoeda == null) {
            listMoeda = DiversasHibernate.getListaMoeda();
        }
        cb_moeda.addItem(new MoedaBeans(0, "-"));
        for (MoedaBeans list : listMoeda) {
            cb_moeda.addItem(list);
        }
    }

    private void maskFormaterCPF() {
        try {
            CNPJMask = new MaskFormatter("##.###.###/####-##");
            CPFMask = new MaskFormatter("###.###.###-##");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopup = new javax.swing.JPopupMenu();
        jMenuEditar = new javax.swing.JMenuItem();
        jPanel3 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel15 = new javax.swing.JLabel();
        txt_dtInicial =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel16 = new javax.swing.JLabel();
        txt_dtFinal =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel18 = new javax.swing.JLabel();
        cb_contaDestino = new javax.swing.JComboBox<>();
        btn_pesquisar = new javax.swing.JButton();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        cb_moeda = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        txt_idCliente = new javax.swing.JTextField();
        txt_nomeCliente = new javax.swing.JTextField();
        btn_cadastrar = new javax.swing.JButton();
        javax.swing.JLabel jLabel13 = new javax.swing.JLabel();
        cb_atividade = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        cb_centroResultado = new componentes.UJComboBox();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        cb_planoConta = new componentes.UJComboBox();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        cb_fazenda = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_recebimentos = new javax.swing.JTable();

        jMenuEditar.setText("Editar Movimento");
        jMenuEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEditarActionPerformed(evt);
            }
        });
        jPopup.add(jMenuEditar);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Resumo de Contas a Receber");

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Data");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("à");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Conta de Destino");

        btn_pesquisar.setText("Pesquisar");
        btn_pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Moeda");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Cliente");

        txt_idCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_idCliente.setText("0");

        txt_nomeCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_nomeClienteFocusLost(evt);
            }
        });
        txt_nomeCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_nomeClienteKeyReleased(evt);
            }
        });

        btn_cadastrar.setBackground(new java.awt.Color(255, 255, 255));
        btn_cadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar pequeno.png"))); // NOI18N
        btn_cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cadastrarActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Atividade");

        cb_atividade.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_atividadeItemStateChanged(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("C. Resultado");

        cb_centroResultado.setAutocompletar(true);
        cb_centroResultado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cb_centroResultadoFocusGained(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Plano Conta");

        cb_planoConta.setAutocompletar(true);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Fazenda");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_contaDestino, 0, 127, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(4, 4, 4)
                        .addComponent(cb_moeda, 0, 88, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_idCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_atividade, 0, 208, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_centroResultado, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_planoConta, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_pesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)))
                .addGap(9, 9, 9))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txt_nomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_idCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(cb_moeda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cb_contaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(txt_dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txt_dtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(btn_cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel13)
                    .addComponent(cb_atividade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(cb_centroResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(cb_planoConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesquisar))
                .addContainerGap())
        );

        tb_recebimentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb_recebimentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_recebimentosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_recebimentos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisarActionPerformed
        TbRecebimentos.limpar();
        TbRecebimentos.addLista(RectoD.consultaRecebimentos(getSQLWhere()));
    }//GEN-LAST:event_btn_pesquisarActionPerformed

    private void tb_recebimentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_recebimentosMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            if (jPopup.isVisible() == true) {
                jPopup.setVisible(false);
            } else {
                jPopup.setVisible(true);
                jPopup.show(this, 0, 0);
                jPopup.setLocation(evt.getLocationOnScreen());
            }
        }
    }//GEN-LAST:event_tb_recebimentosMouseClicked

    private void jMenuEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuEditarActionPerformed
        int index = tb_recebimentos.getSelectedRow();
        if (index >= 0) {
            RecebimentosBeans rec = RectoD.getRecebimento((Integer) TbRecebimentos.getValueAt(index, TbRecebimentos.ID));
            if (rec != null) {
                frmRecebimentos frmRec = new frmRecebimentos();
                this.getParent().add(frmRec);
                frmRec.setVisible(true);
                frmRec.preencherFormulario(rec);
                frmRec.btn_SalvarPagto.setEnabled(false);
                frmRec.btn_editarPagto.setEnabled(true);
                frmRec.btn_excluirPagto.setEnabled(true);
            }
        }
    }//GEN-LAST:event_jMenuEditarActionPerformed

    private void btn_cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cadastrarActionPerformed
        FrmConsultaFornecedor consulta = new FrmConsultaFornecedor(null, true);
        consulta.tb_forn.addMouseListener(getConsultaFornecedorAdapter(consulta));
        consulta.setLocationRelativeTo(null);
        consulta.setVisible(true);
    }//GEN-LAST:event_btn_cadastrarActionPerformed

    private void cb_atividadeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_atividadeItemStateChanged
        if (cb_atividade.getSelectedIndex() > 0) {
            carregarCentroResultado(getAtividade(cb_atividade).getID());
        }
    }//GEN-LAST:event_cb_atividadeItemStateChanged

    private void cb_centroResultadoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cb_centroResultadoFocusGained
        setExpandedComboBox(evt.getComponent());
    }//GEN-LAST:event_cb_centroResultadoFocusGained

    private void txt_nomeClienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_nomeClienteFocusLost
        if (!txt_nomeCliente.getText().equals("") && !txt_idCliente.getText().equals("0")) {
            txt_idCliente.setText("0");
            ClienteB = null;
        }
    }//GEN-LAST:event_txt_nomeClienteFocusLost

    private void txt_nomeClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nomeClienteKeyReleased
        if (!txt_nomeCliente.getText().equals("") && !txt_idCliente.getText().equals("0")) {
            txt_idCliente.setText("0");
            ClienteB = null;
        }
    }//GEN-LAST:event_txt_nomeClienteKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btn_cadastrar;
    private javax.swing.JButton btn_pesquisar;
    private javax.swing.JComboBox<AtividadeBeans> cb_atividade;
    componentes.UJComboBox cb_centroResultado;
    private javax.swing.JComboBox<ContaBancariaBeans> cb_contaDestino;
    private javax.swing.JComboBox<PropriedadesBeans> cb_fazenda;
    private javax.swing.JComboBox<MoedaBeans> cb_moeda;
    private componentes.UJComboBox cb_planoConta;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JMenuItem jMenuEditar;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopup;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_recebimentos;
    private com.toedter.calendar.JDateChooser txt_dtFinal;
    private com.toedter.calendar.JDateChooser txt_dtInicial;
    private javax.swing.JTextField txt_idCliente;
    private javax.swing.JTextField txt_nomeCliente;
    // End of variables declaration//GEN-END:variables

    private void setExpandedComboBox(Component c) {
        if (c instanceof JComboBox) {
            ((JComboBox) c).setPopupVisible(true);
        } else if (c instanceof UJComboBox) {
            ((UJComboBox) c).setPopupVisible(true);
        }
    }

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
        txt_idCliente.setText(String.valueOf(ClienteB.getID()));
        txt_nomeCliente.setText(ClienteB.getRazaoSocial());

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

    private String getSQLWhere() {
        String s = "";
        if (cb_contaDestino.getSelectedIndex() > 0) {
            s += " and R.idConta = " + getContaBancaria(cb_contaDestino).getIdConta();
        }
        if (txt_dtInicial.getDate() != null && txt_dtFinal.getDate() != null) {
            s += " and R.DtRecebimentos BETWEEN '" + Corretores.ConverterDateAAAA_MM_DD(txt_dtInicial.getDate()) + "' and '" + Corretores.ConverterDateAAAA_MM_DD(txt_dtFinal.getDate()) + "'";
        }
        if (cb_moeda.getSelectedIndex() > 0) {
            s += " and R.idMoeda = " + getMoeda(cb_moeda).getIdMoeda();
        }
        if (cb_atividade.getSelectedIndex() > 0) {
            s += " and R.Atividade = " + getAtividade(cb_atividade).getID();
        }
        if (cb_centroResultado.getSelectedIndex() > 0) {
            s += " and R.CentroResultado = " + getCentroResultado(cb_centroResultado).getID();
        }
        if (cb_fazenda.getSelectedIndex() > 0) {
            s += " and R.idFazenda = " + getFazenda(cb_fazenda).getCodigo();
        }
        if (cb_planoConta.getSelectedIndex() > 0) {
            s += " and R.PlanoConta = " + getPlanoConta(cb_planoConta).getID();
        }
        if (ClienteB != null) {
            s += " and R.idCliente = " + ClienteB.getID();
        } else {
            if (!txt_nomeCliente.getText().equals("")) {
                s += " and R.nomeCliente like '%" + txt_nomeCliente.getText() + "%'";
            }
        }

        if (!s.equals("")) {
            s = " WHERE " + s.replaceFirst("and", "");
        }
        return s;
    }

}

class RendererRecebimentos extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setHorizontalAlignment(SwingConstants.CENTER);
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Object val = table.getValueAt(row, column);

        if (val instanceof Double) {
            Double valor = (Double) val;
            String Sifra;
            if (column == 21 || column == 22) {
                Sifra = "R$";
            } else {
                Sifra = table.getValueAt(row, 11).toString();
            }
            if (valor != null) {
                setText(new DecimalFormat(Sifra + " #,###,##0.00").format(valor));
                setHorizontalAlignment(SwingConstants.RIGHT);
            } else {
                setText("");
            }
        }
        return this;
    }
}
