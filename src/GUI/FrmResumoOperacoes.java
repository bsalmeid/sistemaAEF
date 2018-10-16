/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Beans.AnoSafra;
import Beans.ListAplicacaoBeans;
import Beans.ListColaborador;
import Beans.CultivoBeans;
import Beans.CulturaBeans;
import Beans.ListFazendasBeans;
import Beans.InventarioBeans;
import Beans.ListOperacoesBeans;
import Beans.OperacoesBeans;
import DAO.Diversas;
import DAO.OperacoesDAO;
import static GUI.Principal.ListFazPermitidas;
import static GUI.Principal.listAnoSafra;
import static GUI.Principal.listAplicacao;
import static GUI.Principal.listCultivo;
import static GUI.Principal.listCultura;
import static GUI.Principal.listInventario;
import static GUI.Principal.listOperacoes;
import static GUI.Principal.listTalhao;
import Icones.FormatarICO;
import TableModel.TableModelResumoOperacoes;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author agroa
 */
public class FrmResumoOperacoes extends javax.swing.JInternalFrame {

    Diversas DiversasD;
    List<ListColaborador> listColaborador;
    TableModelResumoOperacoes TbOP;
    CentralizarTabela Centralizar;
    OperacoesDAO OperacoesD;

    public FrmResumoOperacoes() {
        initComponents();
        DiversasD = new Diversas();
        Centralizar = new CentralizarTabela();
        OperacoesD = new OperacoesDAO();
        carregarFazPermitidas();
        carregarAnoSafra();
        carregarEpocaCultivo();
        carregarCulturas();
        carregarInventario();
        getTabela();
    }

    private void carregarListOperador(Integer idFazenda) {

        if (listColaborador == null) {
            listColaborador = DiversasD.ListFuncionarios(idFazenda);
        } else {
            listColaborador.clear();
            listColaborador = DiversasD.ListFuncionarios(idFazenda);
        }
        ListColaborador b = new ListColaborador();
        b.setID(0);
        b.setCodigo(0);
        b.setNome("-");
        cb_funcionario.removeAllItems();
        cb_funcionario.addItem(b);
        for (ListColaborador list : listColaborador) {
            cb_funcionario.addItem(list);
        }
    }

    private void carregarFazPermitidas() {
        if (ListFazPermitidas == null) {
            ListFazPermitidas = DiversasD.ListpmFazenda();
        }
        ListFazendasBeans l = new ListFazendasBeans();
        l.setID(0);
        l.setNomeFazenda("-");
        cb_fazenda1.addItem(l);
        for (ListFazendasBeans list : ListFazPermitidas) {
            cb_fazenda1.addItem(list);
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
        for (AnoSafra list : listAnoSafra) {
            cb_safra.addItem(list);
        }
    }

    private void carregarEpocaCultivo() {
        listCultivo = new ArrayList<>();
        if (listCultivo.isEmpty()) {
            listCultivo = DiversasD.ListCultivo();
        }
        CultivoBeans l = new CultivoBeans();
        l.setIDCultivo(0);
        l.setCultivo("-");
        cb_cultivo.addItem(l);
        for (CultivoBeans list : listCultivo) {
            cb_cultivo.addItem(list);
        }
    }

    private void carregarCulturas() {
        listCultura = new ArrayList<>();
        if (listCultura.isEmpty()) {
            listCultura = DiversasD.ListCultura();
        }
        CulturaBeans l = new CulturaBeans();
        l.setIDCultura(0);
        l.setNomeCultura("-");
        cb_cultura.addItem(l);
        for (CulturaBeans list : listCultura) {
            cb_cultura.addItem(list);
        }
    }

    private void carregarInventario() {
        if (listInventario == null) {
            listInventario = DiversasD.ListInventario();
        }
    }

    private void carregarAplicacoes(Integer idCultura, JComboBox<ListAplicacaoBeans> cbDestino) {
        if (listAplicacao == null) {
            //listAplicacao = new ArrayList<>();
            listAplicacao = DiversasD.ListAplicacao();
        }
        cbDestino.removeAllItems();
        ListAplicacaoBeans l = new ListAplicacaoBeans();
        l.setID(0);
        l.setAplicacao("-");
        cbDestino.addItem(l);
        for (ListAplicacaoBeans list : listAplicacao) {
            if (Objects.equals(list.getIDCultura(), idCultura)) {
                cbDestino.addItem(list);
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

    private JTable getTabela() {
        tb_op.setModel(getTableModel());
        ((DefaultTableCellRenderer) tb_op.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        Centralizar.centralizarTabela(tb_op);
        tb_op.setAutoResizeMode(0);
        tb_op.getColumnModel().getColumn(9).setPreferredWidth(0);// ID Insumo
        tb_op.getColumnModel().getColumn(9).setMinWidth(0);
        tb_op.getColumnModel().getColumn(9).setMaxWidth(0);
        tb_op.getColumnModel().getColumn(11).setPreferredWidth(0);// ID Insumo
        tb_op.getColumnModel().getColumn(11).setMinWidth(0);
        tb_op.getColumnModel().getColumn(11).setMaxWidth(0);

        return tb_op;
    }

    private TableModelResumoOperacoes getTableModel() {
        if (TbOP == null) {
            TbOP = new TableModelResumoOperacoes();
        }
        return TbOP;
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
        jmenu_editar = new javax.swing.JMenuItem();
        jXTaskPane1 = new org.jdesktop.swingx.JXTaskPane();
        jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        txt_dtInicial =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        cb_safra = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        cb_cultivo = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        cb_cultura = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        cb_operacao = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        cb_aplicacao = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        cb_fazenda1 = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel20 = new javax.swing.JLabel();
        cb_funcionario = new componentes.UJComboBox();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        txt_nFrotaTrator = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_nFrotaImplemento = new javax.swing.JTextField();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        txt_dtFinal =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        txt_talhao = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_op = new javax.swing.JTable();

        jmenu_editar.setText("Editar Anotação");
        jmenu_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmenu_editarActionPerformed(evt);
            }
        });
        jPopup.add(jmenu_editar);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Pesquisar Fichas Operacionais");

        jXTaskPane1.setBackground(new java.awt.Color(214, 222, 247));
        jXTaskPane1.setTitle("Opções de Pesquisa");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Data");

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

        cb_operacao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_operacaoItemStateChanged(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Aplicação");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Fazenda");

        cb_fazenda1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_fazenda1ItemStateChanged(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Operador");

        cb_funcionario.setMaximumRowCount(20);
        cb_funcionario.setAutocompletar(true);
        cb_funcionario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_funcionarioItemStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Trator");

        txt_nFrotaTrator.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nFrotaTrator.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_nFrotaTratorFocusLost(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Impl");

        txt_nFrotaImplemento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nFrotaImplemento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_nFrotaImplementoFocusLost(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("à");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Nº Talhão");

        txt_talhao.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jButton1.setText("Pesquisar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_aplicacao, 0, 136, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_fazenda1, 0, 139, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_talhao, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_funcionario, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nFrotaTrator, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nFrotaImplemento, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_safra, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 15, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_cultivo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 15, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_cultura, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_operacao, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel12)
                    .addComponent(cb_operacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_cultura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cb_cultivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cb_safra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txt_dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txt_dtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel8)
                    .addComponent(cb_aplicacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(cb_fazenda1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txt_talhao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(cb_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txt_nFrotaTrator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txt_nFrotaImplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        jXTaskPane1.getContentPane().add(jPanel1);

        tb_op.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_op.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_opMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_op);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXTaskPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jXTaskPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb_culturaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_culturaItemStateChanged
        carregarAplicacoes(getIdCultura(cb_cultura), cb_aplicacao);
        carregarOperacoes(getIdCultura(cb_cultura), cb_operacao);
    }//GEN-LAST:event_cb_culturaItemStateChanged

    private void cb_operacaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_operacaoItemStateChanged

    }//GEN-LAST:event_cb_operacaoItemStateChanged

    private void cb_fazenda1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_fazenda1ItemStateChanged
        int idFazenda = getIdFazenda(cb_fazenda1);
        if (idFazenda > 0) {
            carregarListOperador(idFazenda);
        }
    }//GEN-LAST:event_cb_fazenda1ItemStateChanged

    private void cb_funcionarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_funcionarioItemStateChanged
        try {
            ListColaborador c = new ListColaborador();
            c = (ListColaborador) cb_funcionario.getSelectedObject();
            Integer IdOperador = c.getID();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cb_funcionarioItemStateChanged

    private void txt_nFrotaTratorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_nFrotaTratorFocusLost


    }//GEN-LAST:event_txt_nFrotaTratorFocusLost

    private void txt_nFrotaImplementoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_nFrotaImplementoFocusLost


    }//GEN-LAST:event_txt_nFrotaImplementoFocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String dtInicial = Corretores.ConverterDateStringDDMMAAA(txt_dtInicial.getDate());
        String dtFinal = Corretores.ConverterDateStringDDMMAAA(txt_dtFinal.getDate());
        System.out.println();
        if (dtInicial != null || dtFinal != null) {
            OperacoesD.ResumoOperações(TbOP, dtInicial, dtFinal, getWhere());
            
        } else {
            JOptionPane.showMessageDialog(null, "O intervalo de data é obrigatório! ", "Erro", 0, FormatarICO.ICObtnSair());
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void tb_opMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_opMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            if (jPopup.isVisible() == true) {
                jPopup.setVisible(false);
            } else {
                jPopup.setVisible(true);
                jPopup.show(this, 0, 0);
                jPopup.setLocation(evt.getLocationOnScreen());
            }
        }
    }//GEN-LAST:event_tb_opMouseClicked

    private void jmenu_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmenu_editarActionPerformed
        int id = (Integer) TbOP.getValueAt(tb_op.getSelectedRow(), 0);
        if (id > 0) {
            OperacoesBeans b = OperacoesD.buscarOperacoes(id);
            if (b != null) {
                frmOperacoes Operacoes = new frmOperacoes(null, false);
                Operacoes.setLocationRelativeTo(null);
                Operacoes.setVisible(true);
                Operacoes.preencherCampos(b);
                Operacoes.habilitarCampos();
                Operacoes.btn_editarPedido.setEnabled(true);
            }
        }
    }//GEN-LAST:event_jmenu_editarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox<ListAplicacaoBeans> cb_aplicacao;
    public javax.swing.JComboBox<CultivoBeans> cb_cultivo;
    public javax.swing.JComboBox<CulturaBeans> cb_cultura;
    private javax.swing.JComboBox<ListFazendasBeans> cb_fazenda1;
    private componentes.UJComboBox cb_funcionario;
    public javax.swing.JComboBox<ListOperacoesBeans> cb_operacao;
    public javax.swing.JComboBox<AnoSafra> cb_safra;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopup;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane1;
    private javax.swing.JMenuItem jmenu_editar;
    private javax.swing.JTable tb_op;
    public com.toedter.calendar.JDateChooser txt_dtFinal;
    public com.toedter.calendar.JDateChooser txt_dtInicial;
    private javax.swing.JTextField txt_nFrotaImplemento;
    private javax.swing.JTextField txt_nFrotaTrator;
    private javax.swing.JTextField txt_talhao;
    // End of variables declaration//GEN-END:variables

    private Integer getIdCultura(JComboBox comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return listCultura.get(comboBox.getSelectedIndex() - 1).getIDCultura();
        }
        return 0;
    }

    private Integer getIdFazenda(JComboBox<ListFazendasBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return comboBox.getItemAt(comboBox.getSelectedIndex()).getID();
        }
        return 0;
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

    private Integer getIdOperacao(JComboBox<ListOperacoesBeans> comboBox) {
        int id = 0;
        if (comboBox.getSelectedIndex() > 0) {
            id = comboBox.getItemAt(comboBox.getSelectedIndex()).getID();
        }
        return id;
    }

    private Integer getIdCultivo(JComboBox comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return listCultivo.get(comboBox.getSelectedIndex() - 1).getIDCultivo();
        }
        return 0;
    }

    private Integer getIdSafra(JComboBox comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return listAnoSafra.get(comboBox.getSelectedIndex() - 1).getIdSafra();
        }
        return 0;
    }

    private Integer getIdAplicacao(JComboBox comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return listAplicacao.get(comboBox.getSelectedIndex() - 1).getID();
        }
        return 0;
    }

    private Integer getIdInventario(String nFrota) {
        if (!nFrota.equals("")) {
            for (int i = 0; i < listInventario.size(); i++) {
                if (listInventario.get(i).getnFrota().equals(nFrota)) {
                    return listInventario.get(i).getID();
                }
            }
            JOptionPane.showMessageDialog(null, "Nº de Frota Não Encontrado! ", "Erro", 0, FormatarICO.ICObtnSair());
        }
        return 0;
    }

    private Integer getIdOperador() {
        Integer id = 0;
        if (cb_funcionario.getSelectedIndex() > 0) {
            ListColaborador c = (ListColaborador) cb_funcionario.getSelectedObject();
            return c.getID();
        }
        return id;
    }

    private String getWhere() {
        String s = "";
        if (cb_safra.getSelectedIndex() > 0) {
            s += " and opm.id_safra = " + getIdSafra(cb_safra);
        }
        if (cb_cultivo.getSelectedIndex() > 0) {
            s += " and opm.id_cultivo = " + getIdCultivo(cb_cultivo);
        }
        if (cb_cultura.getSelectedIndex() > 0) {
            s += " and opm.id_cultura = " + getIdCultura(cb_cultura);
        }
        if (cb_operacao.getSelectedIndex() > 0) {
            s += " and opm.id_operacao = " + getIdOperacao(cb_operacao);
        }
        if (cb_aplicacao.getSelectedIndex() > 0) {
            s += " and opm.id_aplicacao = " + getIdAplicacao(cb_aplicacao);
        }
        if (cb_fazenda1.getSelectedIndex() > 0) {
            s += " and opt.id_fazenda = " + getIdFazenda(cb_fazenda1);
        } else {
            s += " and opt.id_fazenda IN " + DiversasD.getIdFazendaPermitida();
        }
        if (!txt_talhao.getText().equals("")) {
            s += " and opt.id_talhao = " + getIdTalhao(txt_talhao.getText(), getIdFazenda(cb_fazenda1));
        }
        if (cb_funcionario.getSelectedIndex() > 0) {
            s += " and opm.id_operador = " + getIdAplicacao(cb_aplicacao);
        }
        if (!txt_nFrotaTrator.getText().equals("")) {
            s += " and opm.id_trator = " + getIdInventario(txt_nFrotaTrator.getText());
        }
        if (!txt_nFrotaImplemento.getText().equals("")) {
            s += " and opm.id_implemento = " + getIdInventario(txt_nFrotaImplemento.getText());
        }
        return s;
    }

}
