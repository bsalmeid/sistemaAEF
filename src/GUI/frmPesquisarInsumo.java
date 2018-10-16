/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Beans.ListCategoriaInsumos;
import DAO.Diversas;
import static GUI.Principal.PedidosInsumos;
import Utilitarios.CentralizarTabela;
import Utilitarios.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bruno
 */
public class frmPesquisarInsumo extends javax.swing.JDialog {

    ListCategoriaInsumos listCategoria;
    List<ListCategoriaInsumos> listaCategoria;
    Diversas DiversasD;
    DefaultTableModel TbInsumos;
    CentralizarTabela Centralizar;
    
    
    public frmPesquisarInsumo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        DiversasD = new Diversas();
        listaCategoria = new ArrayList<>();
        TbInsumos = (DefaultTableModel) tb_insumos.getModel();
        Centralizar = new CentralizarTabela();
        Centralizar.centralizarTabela(tb_insumos);
        ((DefaultTableCellRenderer)tb_insumos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        
        carregarCategorias();
        getRootPane().setDefaultButton(btn_pesquisar);
    }

    private void carregarCategorias(){
        if (listaCategoria.isEmpty()){
            listaCategoria = DiversasD.listCategoriaInsumos();
        }
        cb_categoria.addItem("-");
            for (ListCategoriaInsumos list : listaCategoria) {
            cb_categoria.addItem(list);
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

        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        tb_insumos = new javax.swing.JTable();
        javax.swing.JPanel jPanel3 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        cb_categoria = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        txt_nomeComercial = new javax.swing.JTextField();
        btn_pesquisar = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pesquisar Insumo");
        setModal(true);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tb_insumos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Id Categoria", "Categoria", "Produto", "Unidade", "DoseControl", "DoseMin", "DoseMax"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
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
        tb_insumos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_insumosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_insumos);
        if (tb_insumos.getColumnModel().getColumnCount() > 0) {
            tb_insumos.getColumnModel().getColumn(0).setMinWidth(60);
            tb_insumos.getColumnModel().getColumn(0).setPreferredWidth(60);
            tb_insumos.getColumnModel().getColumn(0).setMaxWidth(60);
            tb_insumos.getColumnModel().getColumn(1).setMinWidth(0);
            tb_insumos.getColumnModel().getColumn(1).setPreferredWidth(0);
            tb_insumos.getColumnModel().getColumn(1).setMaxWidth(0);
            tb_insumos.getColumnModel().getColumn(2).setMinWidth(150);
            tb_insumos.getColumnModel().getColumn(2).setPreferredWidth(150);
            tb_insumos.getColumnModel().getColumn(2).setMaxWidth(150);
            tb_insumos.getColumnModel().getColumn(3).setMinWidth(180);
            tb_insumos.getColumnModel().getColumn(3).setPreferredWidth(180);
            tb_insumos.getColumnModel().getColumn(3).setMaxWidth(220);
            tb_insumos.getColumnModel().getColumn(4).setMinWidth(0);
            tb_insumos.getColumnModel().getColumn(4).setPreferredWidth(0);
            tb_insumos.getColumnModel().getColumn(4).setMaxWidth(0);
            tb_insumos.getColumnModel().getColumn(5).setMinWidth(0);
            tb_insumos.getColumnModel().getColumn(5).setPreferredWidth(0);
            tb_insumos.getColumnModel().getColumn(5).setMaxWidth(0);
            tb_insumos.getColumnModel().getColumn(6).setMinWidth(0);
            tb_insumos.getColumnModel().getColumn(6).setPreferredWidth(0);
            tb_insumos.getColumnModel().getColumn(6).setMaxWidth(0);
            tb_insumos.getColumnModel().getColumn(7).setMinWidth(0);
            tb_insumos.getColumnModel().getColumn(7).setPreferredWidth(0);
            tb_insumos.getColumnModel().getColumn(7).setMaxWidth(0);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Categoria");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Nome Comercial");

        btn_pesquisar.setText("Pesquisar");
        btn_pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nomeComercial, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_pesquisar)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cb_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_nomeComercial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesquisar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisarActionPerformed
        buscarInsumo();
    }//GEN-LAST:event_btn_pesquisarActionPerformed

    private void tb_insumosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_insumosMouseClicked
    if (PedidosInsumos != null){
        System.out.println("teste 1ª mousecliked");
        if(evt.getClickCount()==2){
            getFrmPedidos();
            int i = tb_insumos.getSelectedRow();
                PedidosInsumos.txt_idInsumo.setText(TbInsumos.getValueAt(i,0).toString());
                PedidosInsumos.txt_insumo.setText(TbInsumos.getValueAt(i,3).toString());
                PedidosInsumos.txt_idCategoria.setText(TbInsumos.getValueAt(i,1).toString());
                PedidosInsumos.txt_categoria.setText(TbInsumos.getValueAt(i,2).toString());
                PedidosInsumos.txt_unid.setText(TbInsumos.getValueAt(i,4).toString());
            this.dispose();
        }
    }
    }//GEN-LAST:event_tb_insumosMouseClicked

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
            java.util.logging.Logger.getLogger(frmPesquisarInsumo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPesquisarInsumo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPesquisarInsumo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPesquisarInsumo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmPesquisarInsumo dialog = new frmPesquisarInsumo(new javax.swing.JFrame(), true);
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
    javax.swing.JButton btn_pesquisar;
    javax.swing.JComboBox<Object> cb_categoria;
    javax.swing.JTable tb_insumos;
    javax.swing.JTextField txt_nomeComercial;
    // End of variables declaration//GEN-END:variables

    public void buscarInsumo(){
        Conexao.ReiniciarCon();
        String SQLSelection = "select cad_ins_id as ID, cad_idCategoria as IdCategoria, cad_ins_categoria as Categoria, cad_ins_nome as Nome, " +
        " cad_ins_unid as Unid, cad_ins_doseControl as DoseControl, cad_ins_doseMin as DoseMin, cad_ins_doseMax as DoseMax FROM cad_insumos WHERE " + SQLCategoria() + " cad_ins_nome like '%" + txt_nomeComercial.getText()  + "%'";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            TbInsumos.setNumRows(0);
            while(rs.next()){
             TbInsumos.addRow(new Object[]{rs.getInt("ID"), rs.getInt("IdCategoria"), rs.getString("Categoria"),
                                rs.getString("Nome"), rs.getString("Unid"), rs.getBoolean("DoseControl"), rs.getDouble("DoseMin"),rs.getDouble("DoseMax")});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " Erro ao buscar insumos", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        } 
    }
    
    public String SQLCategoria(){
       int index = cb_categoria.getSelectedIndex();
       String s = "";
       if (index > 0){
            String id = listaCategoria.get(cb_categoria.getSelectedIndex()-1).getID().toString();
            return " cad_idCategoria = " + id + " and";
       }
        return "";
    }
    
    public frmPedidosInsumos getFrmPedidos(){
        if (PedidosInsumos == null){
            PedidosInsumos = new frmPedidosInsumos();
        }
        return PedidosInsumos;
    }


}
