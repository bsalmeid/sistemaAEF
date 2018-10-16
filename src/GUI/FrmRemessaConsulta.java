package GUI;

import Beans.InventarioBeans;
import Beans.PropriedadesBeans;
import Beans.RemessaMercadoriaBeans;
import DAO.DiversasHibernate;
import DAO.RemessasDAO;
import static GUI.Principal.ListaPropriedades;
import static GUI.Principal.listInventario;
import static GUI.Principal.listaFazPermitida;
import GerarRelatorios.RelatoriosPedidosMercadoria;
import Icones.FormatarICO;
import TableModel.TableModelRemessasConsulta;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import Utilitarios.ValidarPermissoes;
import com.toedter.calendar.JDateChooser;
import componentes.UJComboBox;
import java.awt.event.MouseEvent;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class FrmRemessaConsulta extends javax.swing.JInternalFrame {

    RemessasDAO remessaD;
    TableModelRemessasConsulta TbConsulta;
    CentralizarTabela Centralizar;
    MaskFormatter FormatoPlaca;

    public FrmRemessaConsulta() {
        initComponents();
        Centralizar = new CentralizarTabela();
        remessaD = new RemessasDAO();
        carregarTabela();
        carregarInventario();
        carregarFazendas();
        txt_placa.setFormatterFactory(new DefaultFormatterFactory(MaskFormatterPlaca()));
        txt_dtFinal.setDate(new Date());
        txt_dtInicial.setDate(Corretores.somarDias(txt_dtFinal.getDate(), -45));
    }

    private JTable carregarTabela() {
        tb_consulta.setModel(getTableModel());
        ((DefaultTableCellRenderer) tb_consulta.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        Centralizar.centralizarTabela(tb_consulta);
        for (int c = 0; c < TbConsulta.getColumnCount(); c++) {

        }
        tb_consulta.getColumnModel().getColumn(TbConsulta.N_ITEM).setPreferredWidth(80);
        tb_consulta.getColumnModel().getColumn(TbConsulta.CODIGO).setPreferredWidth(90);
        tb_consulta.getColumnModel().getColumn(TbConsulta.DESCRICAO).setPreferredWidth(180);
        tb_consulta.getColumnModel().getColumn(TbConsulta.QUANTIDADE).setPreferredWidth(70);
        return tb_consulta;
    }

    private void carregarInventario() {
        if (listInventario == null) {
            listInventario = DiversasHibernate.getListaInventario();
        }
        cb_aplicacao.addItem(new InventarioBeans(0, "-", "-"));
        for (InventarioBeans frota : listInventario) {
            cb_aplicacao.addItem(frota);
        }
    }

    private void carregarFazendas() {
        if (ListaPropriedades == null) {
            ListaPropriedades = DiversasHibernate.getListaFazendas();
        }
    }

    private TableModelRemessasConsulta getTableModel() {
        if (TbConsulta == null) {
            TbConsulta = new TableModelRemessasConsulta();
        }
        return TbConsulta;
    }

    private MaskFormatter MaskFormatterPlaca() {
        try {
            FormatoPlaca = new MaskFormatter("AAA-####");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar máscara", "Erro", 0);
        }
        return FormatoPlaca;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupEditar = new javax.swing.JPopupMenu();
        jMenuEditar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuVisualizar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        txt_dtInicial =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel lbl_fazenda = new javax.swing.JLabel();
        cb_fazenda = new javax.swing.JComboBox<>();
        javax.swing.JLabel lbl_fazenda1 = new javax.swing.JLabel();
        txt_motorista = new javax.swing.JTextField();
        javax.swing.JLabel lbl_fazenda3 = new javax.swing.JLabel();
        txt_placa = new javax.swing.JFormattedTextField();
        javax.swing.JLabel lbl_fazenda2 = new javax.swing.JLabel();
        ch_emitido = new javax.swing.JCheckBox();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        txt_dtFinal =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        txt_nPedido = new javax.swing.JTextField();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        txt_descricao = new javax.swing.JTextField();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        txt_nSolicitacao = new javax.swing.JTextField();
        btn_pesquisar = new javax.swing.JButton();
        javax.swing.JLabel jLabel14 = new javax.swing.JLabel();
        cb_aplicacao = new componentes.UJComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_consulta = new javax.swing.JTable();

        jMenuEditar.setText("Editar Remessa");
        jMenuEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEditarActionPerformed(evt);
            }
        });
        jPopupEditar.add(jMenuEditar);
        jPopupEditar.add(jSeparator1);

        jMenuVisualizar.setText("Visualizar");
        jMenuVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuVisualizarActionPerformed(evt);
            }
        });
        jPopupEditar.add(jMenuVisualizar);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Consultar Remessas");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Data");

        lbl_fazenda.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_fazenda.setText("Fazenda");

        cb_fazenda.setEnabled(false);
        cb_fazenda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_fazendaItemStateChanged(evt);
            }
        });

        lbl_fazenda1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_fazenda1.setText("Motorista");

        txt_motorista.setEnabled(false);

        lbl_fazenda3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_fazenda3.setText("Placa");

        txt_placa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_placa.setEnabled(false);

        lbl_fazenda2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_fazenda2.setText("Status");

        ch_emitido.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_emitido.setSelected(true);
        ch_emitido.setText("Emitido");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("a");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Nº Pedido");

        txt_nPedido.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Código");

        txt_codigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Descrição");

        txt_descricao.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Nº Solicitação");

        txt_nSolicitacao.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btn_pesquisar.setText("Pesquisar");
        btn_pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisarActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Aplicação");

        cb_aplicacao.setEditable(true);
        cb_aplicacao.setMaximumRowCount(20);
        cb_aplicacao.setAutocompletar(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_fazenda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_fazenda1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_motorista)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_fazenda3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_placa, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nSolicitacao, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_fazenda2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ch_emitido, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_aplicacao, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_codigo, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_descricao, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(txt_dtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txt_dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_fazenda)
                    .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_fazenda2)
                    .addComponent(ch_emitido)
                    .addComponent(jLabel5)
                    .addComponent(txt_nSolicitacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_fazenda3)
                    .addComponent(txt_placa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_fazenda1)
                    .addComponent(txt_motorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel7)
                    .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txt_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesquisar)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel14)
                        .addComponent(cb_aplicacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addComponent(txt_nPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        tb_consulta.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_consulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_consultaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_consulta);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb_fazendaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_fazendaItemStateChanged

    }//GEN-LAST:event_cb_fazendaItemStateChanged

    private void btn_pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisarActionPerformed
        TbConsulta.limpar();
        if (txt_dtInicial.getDate() != null && txt_dtFinal.getDate() != null && ValidarPermissoes.validarPermissaoSelect(FrmRemessaConsulta.class.getSimpleName())) {
            TbConsulta.addLista(remessaD.consultarRemessa(SQLWhere()));
        } else {
            JOptionPane.showMessageDialog(null, "O Campo Data É Obrigatório!", "Erro", 0, FormatarICO.ICObtnSair());
        }

    }//GEN-LAST:event_btn_pesquisarActionPerformed

    private void jMenuEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuEditarActionPerformed
        int index = tb_consulta.getSelectedRow();
        if (index > 0 && ValidarPermissoes.validarPermissaoUpdate(FrmRemessaMercadorias.class.getSimpleName())) {
            Long ID = (Long) TbConsulta.getValueAt(index, TbConsulta.ID_REMESSA);
            RemessaMercadoriaBeans remessa = remessaD.getRemessa(ID);
            if (remessa != null) {
                FrmRemessaMercadorias frmRemessa = new FrmRemessaMercadorias();
                this.getParent().add(frmRemessa);
                frmRemessa.tp_manual.setExpanded(false);
                frmRemessa.tp_importar.setExpanded(false);
                frmRemessa.RemessaB = remessa;
                frmRemessa.preencherCampos(remessa);
                frmRemessa.setVisible(true);
            }
        }
    }//GEN-LAST:event_jMenuEditarActionPerformed

    private void jMenuVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuVisualizarActionPerformed
        int index = tb_consulta.getSelectedRow();
        if (index >= 0) {
            int imprimir = JOptionPane.showConfirmDialog(null, "Deseja Imprimir esta Remessa?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (imprimir == JOptionPane.YES_OPTION) {
                Long idRemessa = (Long) TbConsulta.getValueAt(index, TbConsulta.ID_REMESSA);
                String fazenda = TbConsulta.getValueAt(index, TbConsulta.FAZENDA).toString();
                RelatoriosPedidosMercadoria.gerarRemessa(idRemessa, fazenda);
            }
        }
    }//GEN-LAST:event_jMenuVisualizarActionPerformed

    private void tb_consultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_consultaMouseClicked

        if (evt.getButton() == MouseEvent.BUTTON3) {
            if (jPopupEditar.isVisible() == true) {
                jPopupEditar.setVisible(false);
            } else {
                jPopupEditar.setVisible(true);
                jPopupEditar.show(this, 0, 0);
                jPopupEditar.setLocation(evt.getLocationOnScreen());
            }
        }
    }//GEN-LAST:event_tb_consultaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_pesquisar;
    private componentes.UJComboBox cb_aplicacao;
    private javax.swing.JComboBox<PropriedadesBeans> cb_fazenda;
    private javax.swing.JCheckBox ch_emitido;
    private javax.swing.JMenuItem jMenuEditar;
    private javax.swing.JMenuItem jMenuVisualizar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupEditar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTable tb_consulta;
    private javax.swing.JTextField txt_codigo;
    private javax.swing.JTextField txt_descricao;
    private com.toedter.calendar.JDateChooser txt_dtFinal;
    private com.toedter.calendar.JDateChooser txt_dtInicial;
    private javax.swing.JTextField txt_motorista;
    private javax.swing.JTextField txt_nPedido;
    private javax.swing.JTextField txt_nSolicitacao;
    private javax.swing.JFormattedTextField txt_placa;
    // End of variables declaration//GEN-END:variables

    private String SQLWhere() {
        String s;
        s = " WHERE R.dataEnvio BETWEEN '" + Corretores.ConverterDateAAAA_MM_DD(txt_dtInicial.getDate()) + "' AND '" + Corretores.ConverterDateAAAA_MM_DD(txt_dtFinal.getDate()) + "'";
        s += " and R.status = " + ch_emitido.isSelected();
        if (cb_fazenda.getSelectedIndex() > 0) {
            s += " and R.fazendaDestino = " + getFazenda(cb_fazenda).getCodigo();
        }
        if (!txt_motorista.getText().equals("")) {
            s += " and R.motorista like '%" + txt_motorista.getText() + "%'";
        }
        if (!txt_placa.getText().equals("   -    ")) {
            s += " and R.placaVeiculo = " + txt_placa.getText();
        }
        if (!txt_nSolicitacao.getText().equals("")) {
            s += " and I.itemPedido.idSolicitacao = " + txt_nSolicitacao.getText(); // Verificar Necessidade JOIN
        }
        if (!txt_nPedido.getText().equals("")) {
            s += " and I.nPedido = " + txt_nPedido.getText();
        }
        if (cb_aplicacao.getSelectedIndex() > 0) {
            s += " and I.Inventario = " + getInventario(cb_aplicacao).getnFrota();
        }
        if (!txt_codigo.getText().equals("")) {
            s += " and I.Codigo like '%" + txt_codigo.getText() + "%'";
        }
        if (!txt_descricao.getText().equals("")) {
            s += " and I.Descricao like '%" + txt_descricao.getText() + "%'";
        }
        return s;
    }

    private PropriedadesBeans getFazenda(JComboBox<PropriedadesBeans> combo) {
        return (PropriedadesBeans) combo.getSelectedItem();
    }

    private InventarioBeans getInventario(UJComboBox combo) {
        return (InventarioBeans) combo.getSelectedItem();
    }

}
