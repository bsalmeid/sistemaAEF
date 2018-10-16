/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Beans.FreteGadoBeans;
import Beans.FreteTableModel;
import Beans.CompradorGadoBeans;
import Beans.ListFazendasBeans;
import Beans.TransportadorasBeans;
import DAO.Diversas;
import DAO.DiversasHibernate;
import DAO.RelatoriosGadoDAO;
import static GUI.Principal.ListFazPermitidas;
import static GUI.Principal.listCompradores;
import static GUI.Principal.listTransportadoras;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import com.towel.swing.table.TableFilter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author agroa
 */
public class frmRelatorioMinutas extends javax.swing.JInternalFrame {

    FreteTableModel TbMinutas;
    FreteGadoBeans RelatorioB;
    CentralizarTabela centralizar;
    Diversas DiversasD;
    MaskFormatter FormatoPlaca;
    RelatoriosGadoDAO RelatoriosD;
    TableFilter TbFilter;

    public frmRelatorioMinutas() {
        initComponents();
        getTblMinutas();
        DiversasD = new Diversas();
        RelatoriosD = new RelatoriosGadoDAO();
        RelatorioB = new FreteGadoBeans();
        carregarFazPermitidas();
        carregarCompradores();
        comboBoxCarregarTransportadora();
        
    }

    private JTable getTblMinutas() {
        tb_minutas.setModel(getTableModel());

        ((DefaultTableCellRenderer) tb_minutas.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        centralizar = new CentralizarTabela();
        centralizar.centralizarTabela(tb_minutas);
        //tb_minutas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tb_minutas.getColumnModel().getColumn(0).setPreferredWidth(50);
        tb_minutas.getColumnModel().getColumn(1).setPreferredWidth(70);
        tb_minutas.getColumnModel().getColumn(2).setPreferredWidth(70);
        tb_minutas.getColumnModel().getColumn(3).setPreferredWidth(90);
        tb_minutas.getColumnModel().getColumn(4).setPreferredWidth(90);
        tb_minutas.getColumnModel().getColumn(5).setPreferredWidth(100);
        tb_minutas.getColumnModel().getColumn(6).setPreferredWidth(100);
        tb_minutas.getColumnModel().getColumn(7).setPreferredWidth(110);
        tb_minutas.getColumnModel().getColumn(8).setPreferredWidth(80);
        tb_minutas.getColumnModel().getColumn(9).setPreferredWidth(60);
        tb_minutas.getColumnModel().getColumn(10).setPreferredWidth(70);
        tb_minutas.getColumnModel().getColumn(11).setPreferredWidth(70);
        tb_minutas.getColumnModel().getColumn(12).setPreferredWidth(60);
        tb_minutas.getColumnModel().getColumn(13).setPreferredWidth(0);
        tb_minutas.getColumnModel().getColumn(13).setMinWidth(0);
        tb_minutas.getColumnModel().getColumn(13).setMaxWidth(0);
        tb_minutas.getColumnModel().getColumn(14).setPreferredWidth(0);
        tb_minutas.getColumnModel().getColumn(14).setMinWidth(0);
        tb_minutas.getColumnModel().getColumn(14).setMaxWidth(0);
        tb_minutas.getColumnModel().getColumn(15).setPreferredWidth(0);
        tb_minutas.getColumnModel().getColumn(15).setMinWidth(0);
        tb_minutas.getColumnModel().getColumn(15).setMaxWidth(0);
        tb_minutas.getColumnModel().getColumn(16).setPreferredWidth(0);
        tb_minutas.getColumnModel().getColumn(16).setMinWidth(0);
        tb_minutas.getColumnModel().getColumn(16).setMaxWidth(0);
        tb_minutas.getColumnModel().getColumn(17).setPreferredWidth(0);
        tb_minutas.getColumnModel().getColumn(17).setMinWidth(0);
        tb_minutas.getColumnModel().getColumn(17).setMaxWidth(0);
        tb_minutas.getColumnModel().getColumn(18).setPreferredWidth(0);
        tb_minutas.getColumnModel().getColumn(18).setMinWidth(0);
        tb_minutas.getColumnModel().getColumn(18).setMaxWidth(0);

        return tb_minutas;

    }

    private void carregarFazPermitidas() {
        ListFazPermitidas = new ArrayList<>();
        if (ListFazPermitidas.isEmpty()) {
            ListFazPermitidas = DiversasD.ListpmFazenda();
        }
        cb_destino.addItem("-");
        cb_origem.addItem("-");
        for (ListFazendasBeans list : ListFazPermitidas) {
            cb_destino.addItem(list);
            cb_origem.addItem(list.toString());
        }
    }

    private void carregarCompradores() {
        if (listCompradores == null) {
            listCompradores = DiversasHibernate.getListaCompradorgado();
        }
        CompradorGadoBeans b = new CompradorGadoBeans();
        b.setIdComprador(0);
        b.setComprador("-");
        cb_comprador.addItem(b);
        for (CompradorGadoBeans list : listCompradores) {
            cb_comprador.addItem(list);
        }

    }

    private FreteTableModel getTableModel() {
        if (TbMinutas == null) {
            TbMinutas = new FreteTableModel();
        }
        return TbMinutas;
    }

    private void comboBoxCarregarTransportadora() {
        if (listTransportadoras == null) {
            listTransportadoras = new ArrayList<>();
        }
        listTransportadoras.clear();
        listTransportadoras = (DiversasD.ListTransportadorasGado());
        cb_transportadora.addItem("-");
        for (TransportadorasBeans list : listTransportadoras) {
            cb_transportadora.addItem(list.toString());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        txt_dtInicial = new com.toedter.calendar.JDateChooser();
        txt_dtFinal = new com.toedter.calendar.JDateChooser();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        cb_origem = new javax.swing.JComboBox<>();
        cb_destino = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        txt_nCompra = new javax.swing.JTextField();
        txt_nRomaneio = new javax.swing.JTextField();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        txt_nMin = new javax.swing.JTextField();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        cb_comprador = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        cb_transportadora = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        try {

            FormatoPlaca = new MaskFormatter("AAA-####");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar máscara", "Erro", 0);
        }
        txt_placa = new JFormattedTextField(FormatoPlaca);
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        cb_operacao = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        sc_panel1 = new javax.swing.JScrollPane();
        tb_minutas = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Relatório de Minutas de Gado");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Data");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("à");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Origem");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Destino");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Nº Compra");

        cb_origem.setEditable(true);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Romaneio");

        txt_nCompra.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txt_nRomaneio.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Nº da Minuta");

        txt_nMin.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Comprador");

        jButton1.setText("Pesquisar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Transportadora");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Placa ");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Operação");

        cb_operacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Compra", "Transf (+)", "Nascimento", "Era" }));

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
                        .addComponent(cb_comprador, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nMin, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nRomaneio, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_transportadora, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_operacao, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_origem, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_destino, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_placa, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txt_dtFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_dtInicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cb_operacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cb_origem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(cb_destino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jButton1)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txt_placa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cb_transportadora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(cb_comprador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txt_nMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6)
                        .addComponent(txt_nCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_nRomaneio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tb_minutas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb_minutas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tb_minutasMousePressed(evt);
            }
        });
        sc_panel1.setViewportView(tb_minutas);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sc_panel1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sc_panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        popularBeans();
        RelatoriosD.consultarEntradas(RelatorioB, TbMinutas, getSQLWhere());

    }//GEN-LAST:event_jButton1ActionPerformed

    private void tb_minutasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_minutasMousePressed
        TipTextQMinuta(tb_minutas, 10);
    }//GEN-LAST:event_tb_minutasMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<CompradorGadoBeans> cb_comprador;
    private javax.swing.JComboBox<Object> cb_destino;
    private javax.swing.JComboBox<String> cb_operacao;
    private javax.swing.JComboBox<String> cb_origem;
    private javax.swing.JComboBox<Object> cb_transportadora;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane sc_panel1;
    private javax.swing.JTable tb_minutas;
    private com.toedter.calendar.JDateChooser txt_dtFinal;
    private com.toedter.calendar.JDateChooser txt_dtInicial;
    private javax.swing.JTextField txt_nCompra;
    private javax.swing.JTextField txt_nMin;
    private javax.swing.JTextField txt_nRomaneio;
    javax.swing.JTextField txt_placa;
    // End of variables declaration//GEN-END:variables

    private void popularBeans() {
        try {
            RelatorioB.setDtInicial(new SimpleDateFormat("dd/MM/yyyy").format(txt_dtInicial.getDate()));
            RelatorioB.setDtFinal(new SimpleDateFormat("dd/MM/yyyy").format(txt_dtFinal.getDate()));
        } catch (Exception e) {
        }
        if (cb_origem.getSelectedItem().equals("-")) {
            RelatorioB.setOrigem("");
        } else {
            RelatorioB.setOrigem(cb_origem.getSelectedItem().toString());
        }
        if (cb_destino.getSelectedItem().equals("-")) {
            RelatorioB.setDestino("");
        } else {
            RelatorioB.setDestino(cb_destino.getSelectedItem().toString());
        }
        if (txt_placa.getText().equals("   -    ")) {
            RelatorioB.setPlaca("");
        } else {
            RelatorioB.setPlaca(txt_placa.getText());
        }
        if (cb_transportadora.getSelectedItem().equals("-")) {
            RelatorioB.setTransportadora("");
        } else {
            RelatorioB.setTransportadora(cb_transportadora.getSelectedItem().toString());
        }
        if (txt_nMin.getText().equals("")) {
            RelatorioB.setnMin("");
        } else {
            RelatorioB.setnMin(txt_nMin.getText());
        }

        if (txt_nCompra.getText().equals("")) {
            RelatorioB.setnCompra("");
        } else {
            RelatorioB.setnCompra(txt_nCompra.getText());
        }

    }

    private String getSQLWhere() {
        String s = "";

        if (cb_transportadora.getSelectedIndex() != 0) {
            s += " and ent_gado_fis_trans = '" + cb_transportadora.getSelectedItem().toString() + "' ";
        }
        if (!txt_nMin.getText().equals("")) {
            s += " and ent_gado_fis_nMin like '" + txt_nMin.getText() + "' ";
        }
        if (!txt_placa.getText().equals("   -    ")) {
            s += " and ent_gado_fis_placa like '%" + txt_placa.getText() + "%' ";
        }
        if (cb_origem.getSelectedIndex() != 0) {
            s += " and nf.ent_gado_fazOrigem like '%" + cb_origem.getSelectedItem().toString() + "%' ";
        }
        if (!txt_nCompra.getText().equals("")) {
            s += " and ent_gado_fis_nCompra = " + txt_nCompra.getText() + " ";
        }
        if (cb_destino.getSelectedIndex() != 0) {
            s += " and ent_gado_fis_destFisico = '" + cb_destino.getSelectedItem().toString() + "' ";
        }
        if (cb_operacao.getSelectedIndex() > 0) {
            s += "and nf.ent_gado_motivo like '%" + cb_operacao.getSelectedItem().toString() + "%'";
        }
        if (cb_comprador.getSelectedIndex() != 0) {
            s += " having Comprador = '" + cb_comprador.getSelectedItem().toString() + "' ";
        }

        return s;
    }

    private void TipTextQMinuta(JTable tabela, int column) {
        tabela.setToolTipText(null);
        int[] linha = tabela.getSelectedRows();
        if (linha.length > 1) {
            String texto = "";
            double SomaValorDocumento = 0.0;
            for (int i = 0; i < linha.length; i++) {
                SomaValorDocumento += Corretores.ConverterReaisDouble(tabela.getValueAt(linha[i], column).toString());
            }

            texto = "<html><FONT FACE=\"Tahoma\" SIZE=3>"
                    + "Quantidade de Cabeças na Minuta é: <B>" + Corretores.ConverterDoubleReais(SomaValorDocumento).replace("R$", "") + "</B><br>"
                    + "</FONT></html>";
            ToolTipManager.sharedInstance().setInitialDelay(150);
            ToolTipManager.sharedInstance().setDismissDelay(30000);
            tabela.setToolTipText(texto);
        }
    }

}
