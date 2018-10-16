package GUI;

import DAO.FornecedoresDAO;
import TableModel.TableModelFornecedores;
import Utilitarios.CentralizarTabela;
import java.text.ParseException;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class FrmConsultaFornecedor extends javax.swing.JDialog {

    TableModelFornecedores TbForn;
    CentralizarTabela Centralizar;
    MaskFormatter CNPJMask;
    MaskFormatter CPFMask;
    FornecedoresDAO FornD;

    public FrmConsultaFornecedor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        FornD = new FornecedoresDAO();
        initComponents();
        getRootPane().setDefaultButton(btn_pesquisar);
        getTabela();
        maskFormaterCPF();
        txt_consulta.requestFocus();

    }

    private JTable getTabela() {
        tb_forn.setModel(getTableModel());
        Centralizar = new CentralizarTabela();
        Centralizar.centralizarTabela(tb_forn);
        ((DefaultTableCellRenderer) tb_forn.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        for (int c = 0; c < TbForn.getColumnCount(); c++) {
            if (c == TbForn.ID || c == TbForn.EMAIL || c == TbForn.ID_SEGMENTO || c == TbForn.TELEFONE 
                    || c == TbForn.TIPO_PESSOA || c == TbForn.BANCO || c == TbForn.AGENCIA || c == TbForn.CONTA) {
                tb_forn.getColumnModel().getColumn(c).setPreferredWidth(0);
                tb_forn.getColumnModel().getColumn(c).setMinWidth(0);
                tb_forn.getColumnModel().getColumn(c).setMaxWidth(0);
            }
        }
        return tb_forn;
    }

    private TableModelFornecedores getTableModel() {
        if (TbForn == null) {
            TbForn = new TableModelFornecedores();
        }
        return TbForn;
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

        jPanel1 = new javax.swing.JPanel();
        cb_doc = new javax.swing.JComboBox<>();
        txt_cpf = new javax.swing.JFormattedTextField();
        cb_campo = new javax.swing.JComboBox<>();
        txt_consulta = new javax.swing.JTextField();
        btn_pesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_forn = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta Fornecedor/Prestador");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Opções de Consulta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N

        cb_doc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "CPF", "CNPJ" }));
        cb_doc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_docItemStateChanged(evt);
            }
        });

        cb_campo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Nome Fantasia", "Razão Social" }));

        btn_pesquisar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_pesquisar.setText("Pesquisar");
        btn_pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cb_campo, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_consulta, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cb_doc, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txt_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cb_doc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_campo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_consulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesquisar))
                .addContainerGap())
        );

        tb_forn.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tb_forn);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb_docItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_docItemStateChanged
        txt_cpf.setText(null);
        txt_cpf.setValue(null);
        txt_cpf.setFormatterFactory(null);
        switch (cb_doc.getSelectedIndex()) {
            case 0:
                txt_cpf.setFormatterFactory(null);
                break;
            case 1:
                txt_cpf.setFormatterFactory(new DefaultFormatterFactory(CPFMask));
                break;
            case 2:
                txt_cpf.setFormatterFactory(new DefaultFormatterFactory(CNPJMask));
                break;
            default:
                break;
        }
    }//GEN-LAST:event_cb_docItemStateChanged

    private void btn_pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisarActionPerformed
        TbForn.limpar();
        TbForn.addLista(FornD.consultaFornecedores(getWhere()));
    }//GEN-LAST:event_btn_pesquisarActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmConsultaFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmConsultaFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmConsultaFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmConsultaFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmConsultaFornecedor dialog = new FrmConsultaFornecedor(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_pesquisar;
    private javax.swing.JComboBox<String> cb_campo;
    public javax.swing.JComboBox<String> cb_doc;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tb_forn;
    private javax.swing.JTextField txt_consulta;
    public javax.swing.JFormattedTextField txt_cpf;
    // End of variables declaration//GEN-END:variables

    private String getWhere() {
        String s = "";
        if (!txt_consulta.getText().equals("")) {
            if (cb_campo.getSelectedItem().toString().equals("Nome Fantasia")) {
                s += " and F.NomeFantasia like '%" + txt_consulta.getText() + "%'";
            } else if (cb_campo.getSelectedItem().toString().equals("Razão Social")) {
                s += " and F.RazaoSocial like '%" + txt_consulta.getText() + "%'";
            } else {
                s += " and F.NomeFantasia like '%" + txt_consulta.getText() + "%' OR" + " F.RazaoSocial like '%" + txt_consulta.getText() + "%'";
            }
        }
        if (txt_cpf.getValue() != null) {
            s += "  and F.cnpj = '" + txt_cpf.getText() + "'";
        }

        if (!s.equals("")) {
            s = " Where " + s.replaceFirst("and", "");
        }
        return s;
    }

}
