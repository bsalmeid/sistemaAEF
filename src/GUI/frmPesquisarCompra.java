package GUI;

import Beans.CompradorGadoBeans;
import DAO.Diversas;
import DAO.DiversasHibernate;
import static GUI.Principal.listCompradores;
import GUI.frmEntradaGado;
import static GUI.frmLogin.UsuarioLogado;
import Utilitarios.CentralizarTabela;
import Utilitarios.Conexao;
import Utilitarios.Corretores;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

public class frmPesquisarCompra extends javax.swing.JDialog {

    MaskFormatter FormatoCPF;
    DefaultTableModel TbCompra;
    String comprador = "";
    String fornecedor = "";
    String cpf = "";
    String destino = "";
    String status = "";
    public static String frmPesquisaCompraFornecedor;
    public static String frmPesquisaCompraNCompra;
    public static String frmPesquisaCompraCPF;
    public static String frmPesquisaCompraComprador;
    frmPesquisarCompra telaPesquisarCompra;
    Diversas DiversasD;
    CentralizarTabela Centralizar;

    public frmPesquisarCompra(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        DiversasD = new Diversas();
        TbCompra = (DefaultTableModel) tb_compras.getModel();
        Centralizar = new CentralizarTabela();
        Centralizar.centralizarTabela(tb_compras);
        pmfazenda();

        Calendar dataAtual = Calendar.getInstance();
        Calendar primeiroDia = Calendar.getInstance();
        Calendar ultimoDia = Calendar.getInstance();
        //1º dia do mês atual
        primeiroDia.add(Calendar.DAY_OF_MONTH, -dataAtual.get(Calendar.DAY_OF_MONTH));
        primeiroDia.add(Calendar.DAY_OF_YEAR, 1);
        txt_dataInicial.setDate(primeiroDia.getTime());

        //Ultimo dia do mês atual
        ultimoDia.add(Calendar.MONTH, 1);
        ultimoDia.add(Calendar.DAY_OF_MONTH, -dataAtual.get(Calendar.DAY_OF_MONTH));
        txt_dataFinal.setDate(ultimoDia.getTime());
        carregarCompradores();
    }

    private void carregarCompradores() {
        if (listCompradores == null) {
            listCompradores = DiversasHibernate.getListaCompradorgado();
        }
        CompradorGadoBeans b = new CompradorGadoBeans();
        b.setIdComprador(0);
        b.setComprador("-");
        cb_compradorPesq.addItem(b);
        for (CompradorGadoBeans list : listCompradores) {
            cb_compradorPesq.addItem(list);
        }

    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        txt_dataInicial = new com.toedter.calendar.JDateChooser();
        txt_dataFinal = new com.toedter.calendar.JDateChooser();
        javax.swing.JLabel jLabel14 = new javax.swing.JLabel();
        txt_fornecedorPesq = new javax.swing.JTextField();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        cb_compradorPesq = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        try {

            FormatoCPF = new MaskFormatter("###.###.###-##");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar máscara", "Erro", 0);
        }
        txt_cpfPesq = new JFormattedTextField(FormatoCPF);
        btn_pesquisar1 = new javax.swing.JButton();
        cb_destinoPesq = new javax.swing.JComboBox<>();
        cb_statusPesq = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel15 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel16 = new javax.swing.JLabel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        tb_compras = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pesquisar Compras de Gado");
        setModal(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Pesquisar"));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Data Inicial");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Data Final");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Produtor");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Comprador");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("CPF");

        btn_pesquisar1.setBackground(new java.awt.Color(255, 255, 255));
        btn_pesquisar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar.png"))); // NOI18N
        btn_pesquisar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisar1ActionPerformed(evt);
            }
        });

        cb_statusPesq.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Em Aberto", "Encerrada" }));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Destino");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Status");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(cb_compradorPesq, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_cpfPesq, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_fornecedorPesq)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cb_destinoPesq, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_statusPesq, 0, 139, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_pesquisar1)
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_dataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_fornecedorPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_destinoPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_dataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_compradorPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_cpfPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_statusPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btn_pesquisar1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        tb_compras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nº Ordem", "Data Compra", "Comprador", "Destino", "Fornecedor", "CPF", "Qt Compra", "Qt NF", "Qt GTA", "Qt Min", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_compras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_comprasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_compras);
        if (tb_compras.getColumnModel().getColumnCount() > 0) {
            tb_compras.getColumnModel().getColumn(0).setMinWidth(40);
            tb_compras.getColumnModel().getColumn(0).setPreferredWidth(50);
            tb_compras.getColumnModel().getColumn(0).setMaxWidth(70);
            tb_compras.getColumnModel().getColumn(1).setMinWidth(60);
            tb_compras.getColumnModel().getColumn(1).setPreferredWidth(80);
            tb_compras.getColumnModel().getColumn(1).setMaxWidth(100);
            tb_compras.getColumnModel().getColumn(2).setMinWidth(90);
            tb_compras.getColumnModel().getColumn(2).setPreferredWidth(90);
            tb_compras.getColumnModel().getColumn(2).setMaxWidth(120);
            tb_compras.getColumnModel().getColumn(3).setMinWidth(90);
            tb_compras.getColumnModel().getColumn(3).setPreferredWidth(90);
            tb_compras.getColumnModel().getColumn(3).setMaxWidth(120);
            tb_compras.getColumnModel().getColumn(4).setMinWidth(120);
            tb_compras.getColumnModel().getColumn(4).setPreferredWidth(120);
            tb_compras.getColumnModel().getColumn(4).setMaxWidth(150);
            tb_compras.getColumnModel().getColumn(5).setMinWidth(70);
            tb_compras.getColumnModel().getColumn(5).setPreferredWidth(70);
            tb_compras.getColumnModel().getColumn(5).setMaxWidth(100);
            tb_compras.getColumnModel().getColumn(6).setMinWidth(40);
            tb_compras.getColumnModel().getColumn(6).setPreferredWidth(60);
            tb_compras.getColumnModel().getColumn(6).setMaxWidth(70);
            tb_compras.getColumnModel().getColumn(7).setMinWidth(40);
            tb_compras.getColumnModel().getColumn(7).setPreferredWidth(40);
            tb_compras.getColumnModel().getColumn(7).setMaxWidth(70);
            tb_compras.getColumnModel().getColumn(8).setMinWidth(40);
            tb_compras.getColumnModel().getColumn(8).setPreferredWidth(40);
            tb_compras.getColumnModel().getColumn(8).setMaxWidth(70);
            tb_compras.getColumnModel().getColumn(9).setMinWidth(40);
            tb_compras.getColumnModel().getColumn(9).setPreferredWidth(40);
            tb_compras.getColumnModel().getColumn(9).setMaxWidth(70);
            tb_compras.getColumnModel().getColumn(10).setMinWidth(70);
            tb_compras.getColumnModel().getColumn(10).setPreferredWidth(70);
            tb_compras.getColumnModel().getColumn(10).setMaxWidth(120);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 829, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 829, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                .addContainerGap())
        );

        setBounds(0, 0, 865, 493);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_pesquisar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisar1ActionPerformed
        popularDadosPesquisa();
        pesquisarCompras(TbCompra);


    }//GEN-LAST:event_btn_pesquisar1ActionPerformed

    private void tb_comprasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_comprasMouseClicked

        if (evt.getClickCount() == 2) {
            if (!TbCompra.getValueAt(tb_compras.getSelectedRow(), 10).equals("Encerrada")) {
                
                frmPesquisaCompraNCompra = TbCompra.getValueAt(tb_compras.getSelectedRow(), 0).toString();
                frmPesquisaCompraComprador = TbCompra.getValueAt(tb_compras.getSelectedRow(), 2).toString();
                frmPesquisaCompraFornecedor = TbCompra.getValueAt(tb_compras.getSelectedRow(), 4).toString();
                frmPesquisaCompraCPF = TbCompra.getValueAt(tb_compras.getSelectedRow(), 5).toString();

                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Você não pode alterar está compra, caso necessário solicite que será reaberta!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            }

        }
    }//GEN-LAST:event_tb_comprasMouseClicked

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(frmPesquisarCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPesquisarCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPesquisarCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPesquisarCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        System.out.println("teste main");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmPesquisarCompra dialog = new frmPesquisarCompra(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);

                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btn_pesquisar1;
    javax.swing.JComboBox<CompradorGadoBeans> cb_compradorPesq;
    public static javax.swing.JComboBox<String> cb_destinoPesq;
    javax.swing.JComboBox<String> cb_statusPesq;
    javax.swing.JTable tb_compras;
    javax.swing.JTextField txt_cpfPesq;
    com.toedter.calendar.JDateChooser txt_dataFinal;
    com.toedter.calendar.JDateChooser txt_dataInicial;
    javax.swing.JTextField txt_fornecedorPesq;
    // End of variables declaration//GEN-END:variables

    public void pesquisarCompras(DefaultTableModel TbCompra) {
        String SQLSelection = "select compra_gado_id, compra_gado_data, compra_gado_comprador, compra_gado_destino, compra_gado_fornecedor, compra_gado_cpf, compra_gado_quant, ";
        SQLSelection += "ifnull((select Sum(ent_gado_nf.ent_gado_QNF) From ent_gado_nf Where ent_gado_nf.ent_gado_ncompra = compra_gado.compra_gado_id),0) as QNF, ";
        SQLSelection += "ifnull((select Sum(ent_gado_fisica.ent_gado_fis_quantGTA) From ent_gado_fisica WHERE ent_gado_fis_nCompra = compra_gado.compra_gado_id ),0) as QGTA,";
        SQLSelection += "ifnull((select Sum(ent_gado_fisica.ent_gado_fis_quantCab) From ent_gado_fisica WHERE ent_gado_fis_nCompra = compra_gado.compra_gado_id),0) as QMin, compra_gado_stat ";
        SQLSelection += "from compra_gado WHERE compra_gado_data BETWEEN ? AND ? and compra_gado_comprador Like '%" + comprador + "%' ";
        SQLSelection += "and compra_gado_fornecedor Like '%" + fornecedor + "%' ";
        SQLSelection += "and compra_gado_cpf Like '%" + cpf + "%' ";
        SQLSelection += "and compra_gado_destino Like '%" + destino + "%' ";
        SQLSelection += "and compra_gado_stat Like '%" + status + "%' ";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            st.setString(1, Corretores.ConverterParaSQL(new SimpleDateFormat("dd/MM/yyyy").format(txt_dataInicial.getDate())));
            st.setString(2, Corretores.ConverterParaSQL(new SimpleDateFormat("dd/MM/yyyy").format(txt_dataFinal.getDate())));

            ResultSet rs = st.executeQuery();
            TbCompra.setNumRows(0);
            while (rs.next()) {
                TbCompra.addRow(new Object[]{rs.getInt(1), Corretores.ConverterParaJava(rs.getString(2)), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getString(11)});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }
    }

    public void popularDadosPesquisa() {

        if (cb_compradorPesq.getSelectedIndex() > 0) {
            comprador = cb_compradorPesq.getSelectedItem().toString();
        } else {
            comprador = "";
        }
        if (!txt_fornecedorPesq.getText().equals("")) {
            fornecedor = txt_fornecedorPesq.getText();
        }
        if (!txt_cpfPesq.getText().equals("   .   .   -  ")) {
            cpf = txt_cpfPesq.getText();
        }
        if (!cb_destinoPesq.getSelectedItem().equals("-")) {
            destino = cb_destinoPesq.getSelectedItem().toString();
        }
        if (!cb_statusPesq.getSelectedItem().equals("-")) {
            status = cb_statusPesq.getSelectedItem().toString();
        }

    }

    private void pmfazenda() {
        Conexao.ReiniciarCon();
        String SQLSelection = "select * from pm_user_fazenda where  login_usuario = ?";

        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            st.setString(1, UsuarioLogado);
            ResultSet rs = st.executeQuery();

            cb_destinoPesq.addItem("-");
            while (rs.next()) {
                if (rs.getInt(4) == 1) {
                    cb_destinoPesq.addItem(rs.getString(2));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar permissões", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }
    }

}
