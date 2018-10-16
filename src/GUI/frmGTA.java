/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Beans.ListFazendasBeans;
import DAO.Diversas;
import static GUI.Principal.ListFazendas;
import TableModel.TableModelGTA;
import TableModel.TbGTABeans;
import Utilitarios.CentralizarTabela;
import Utilitarios.Conexao;
import Utilitarios.Corretores;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import static java.awt.Font.BOLD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Bruno
 */
public class frmGTA extends javax.swing.JInternalFrame {

    CentralizarTabela Centralizar;
    TableModelGTA TbGTA;
    String DataInicial;
    String DataFinal;
    Diversas DiversasD;
    String SQLOrigem;
    String SQLDestino;
    String SQLnGTA;
    CellRenderer renderer;
    
    public frmGTA() {
        initComponents();
        DiversasD = new Diversas();
        renderer = new CellRenderer();
        
        getTabelaGTA();
        carregarFazendas();
          
    }

    public JTable getTabelaGTA(){
        tb_gta.setModel(getTableModel());
        Centralizar = new CentralizarTabela();
        Centralizar.centralizarTabela(tb_gta);
        ((DefaultTableCellRenderer)tb_gta.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        tb_gta.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tb_gta.getColumnModel().getColumn(0).setPreferredWidth(0);// ID
        tb_gta.getColumnModel().getColumn(0).setMinWidth(0);
        tb_gta.getColumnModel().getColumn(0).setMaxWidth(0);
        tb_gta.getColumnModel().getColumn(4).setPreferredWidth(120);// Orige,
        tb_gta.getColumnModel().getColumn(4).setMinWidth(0);
        tb_gta.getColumnModel().getColumn(4).setMaxWidth(180);
        tb_gta.getColumnModel().getColumn(5).setPreferredWidth(120);// Destino
        tb_gta.getColumnModel().getColumn(5).setMinWidth(0);
        tb_gta.getColumnModel().getColumn(6).setMaxWidth(180);
        tb_gta.getColumnModel().getColumn(11).setCellRenderer(renderer);
        return tb_gta;
    }
    
    private TableModelGTA getTableModel(){
        if(TbGTA == null){
            TbGTA = new TableModelGTA();
        }
        return TbGTA;
    }
    
    private void carregarFazendas(){
        ListFazendas = new ArrayList<>();
        if (ListFazendas.isEmpty()) {
            ListFazendas = DiversasD.listaDeFazendas();
        }
        cb_origem.addItem("-");
        cb_destino.addItem("-");
        for (ListFazendasBeans list : ListFazendas) {
            cb_origem.addItem(list);
            cb_destino.addItem(list);
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        txt_dataInicial = new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        txt_dataFinal = new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        cb_origem = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        cb_destino = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        txt_nGTA = new javax.swing.JTextField();
        btn_pesquisar = new javax.swing.JButton();
        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        tb_gta = new javax.swing.JTable();
        javax.swing.JPanel jPanel3 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        txt_m012 = new javax.swing.JTextField();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        txt_m1324 = new javax.swing.JTextField();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        txt_m2536 = new javax.swing.JTextField();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        txt_m36 = new javax.swing.JTextField();
        txt_mTotal = new javax.swing.JTextField();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Controle de GTA's");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("De");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("a");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Origem");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Destino");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Nº GTA");

        txt_nGTA.setHorizontalAlignment(javax.swing.JTextField.CENTER);

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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_dataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_dataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_origem, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cb_destino, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_nGTA, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_pesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_dataInicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_dataFinal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb_origem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb_destino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_nGTA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_pesquisar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tb_gta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tb_gta);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("M 0-12");

        txt_m012.setEditable(false);
        txt_m012.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("M 13-24");

        txt_m1324.setEditable(false);
        txt_m1324.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("M 25-36");

        txt_m2536.setEditable(false);
        txt_m2536.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("M > 36");

        txt_m36.setEditable(false);
        txt_m36.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txt_mTotal.setEditable(false);
        txt_mTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("M Total");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(409, 409, 409)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_m012, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_m1324, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_m2536, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_m36, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_mTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_m012, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txt_m1324, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txt_m2536, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txt_m36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txt_mTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisarActionPerformed
        if (verificarBeans()) {
            popularBeansPesquisa();
            buscarGTAEntrada();
            somarTabela();
        }
    }//GEN-LAST:event_btn_pesquisarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btn_pesquisar;
    javax.swing.JComboBox<Object> cb_destino;
    javax.swing.JComboBox<Object> cb_origem;
    javax.swing.JTable tb_gta;
    com.toedter.calendar.JDateChooser txt_dataFinal;
    com.toedter.calendar.JDateChooser txt_dataInicial;
    javax.swing.JTextField txt_m012;
    javax.swing.JTextField txt_m1324;
    javax.swing.JTextField txt_m2536;
    javax.swing.JTextField txt_m36;
    javax.swing.JTextField txt_mTotal;
    javax.swing.JTextField txt_nGTA;
    // End of variables declaration//GEN-END:variables

    private void popularBeansPesquisa(){
        DataInicial = new SimpleDateFormat("yyyy-MM-dd").format(txt_dataInicial.getDate());
        DataFinal = new SimpleDateFormat("yyyy-MM-dd").format(txt_dataFinal.getDate());
        SQLOrigem = "";
        SQLDestino = "";
        SQLnGTA = "";
        if (cb_origem.getSelectedIndex() > 0){
              SQLOrigem = " and ent_gado_fis_fazGTA like '%" + cb_origem.getSelectedItem().toString() + "%' ";
        }
        if (cb_destino.getSelectedIndex() > 0){
              SQLDestino = " and ent_gado_fis_destGTA like '%" + cb_destino.getSelectedItem().toString() + "%' ";
        }
        if (!txt_nGTA.getText().equals("")){
              SQLnGTA = " and ent_gado_fis_ngta like '%" + txt_nGTA.getText() + "%' ";
        }
        
    }
    
    private Boolean verificarBeans(){
        if (txt_dataInicial.getDate() == null || txt_dataFinal.getDate() == null){
            JOptionPane.showMessageDialog(null, "O campo data é obrigatório!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }
        
        
        return true;
    }
    
    public void buscarGTAEntrada(){
    Conexao.ReiniciarCon();
    String SQLGTA = "select ent_gado_fis_id as IDTabela, " +
            "ent_gado_fis_nromaneio as Romaneio, " +
            "ent_gado_fis_data_gta as DataEntrada, " +
            "ent_gado_fis_ngta as nGTA, " +
            "ent_gado_fis_fazGTA as Origem, " +
            "ent_gado_fis_destGTA as Destino, " +
            "ent_gado_fis_m12 as M012, " +
            "ent_gado_fis_m24 as M1324, " +
            "ent_gado_fis_m36 as M2536, " +
            "ent_gado_fis_mm36 as M36, " +
            "@MT:=ent_gado_fis_m_total as mTotal, " +
            "ent_gado_fis_f12 as F012, " +
            "ent_gado_fis_f24 as F1324, " +
            "ent_gado_fis_f36 as F2536, " +
            "ent_gado_fis_fm36 as F36, " +
            "@FT:=ent_gado_fis_f_total as fTotal, " +
            "@FT + @MT as Qtotal " +
            "from ent_gado_fisica " +
            "where ent_gado_fis_data_gta between '" + DataInicial + "' and '" + DataFinal + "'" +
            SQLOrigem + SQLDestino + SQLnGTA;
  
    try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLGTA);
            ResultSet rs = st.executeQuery();
            List<TbGTABeans> listaGTA = new ArrayList<>();
            TbGTA.limpar();
            while(rs.next()){
                TbGTABeans l = new TbGTABeans();
                l.setIdTabela(rs.getInt("IDTabela")); 
                l.setRomaneio(rs.getInt("Romaneio"));
                l.setData(Corretores.ConverterParaJava(rs.getString("DataEntrada")));
                l.setnGTA(rs.getInt("nGTA"));
                l.setOrigem(rs.getString("Origem"));
                l.setDestino(rs.getString("Destino"));
                l.setM012(rs.getInt("M012"));
                l.setM1324(rs.getInt("M1324"));
                l.setM2536(rs.getInt("M2536"));
                l.setM36(rs.getInt("M36"));
                l.setMachoTotal(rs.getInt("mTotal"));
                l.setF012(rs.getInt("F012"));
                l.setF1324(rs.getInt("F1324"));
                l.setF2536(rs.getInt("F2536"));
                l.setF36(rs.getInt("F36"));
                l.setFemeaTotal(rs.getInt("fTotal"));
                l.setQTotal(rs.getInt("QTotal"));
                listaGTA.add(l);
            }
            TbGTA.addLista(listaGTA);
            
        } catch (SQLException ex) {
            Logger.getLogger(frmGTA.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}

    public void somarTabela(){
    int m012 = 0;
    int m1324 = 0;
    int m2536 = 0;
    int m36 = 0;
    int mTotal = 0;
    for (int i = 0; i < TbGTA.getRowCount(); i++){
        m012 += Integer.valueOf(TbGTA.getValueAt(i, 7).toString());
        m1324 += Integer.valueOf(TbGTA.getValueAt(i, 8).toString());
        m2536 += Integer.valueOf(TbGTA.getValueAt(i, 9).toString());
        m36 += Integer.valueOf(TbGTA.getValueAt(i, 10).toString());
        mTotal += Integer.valueOf(TbGTA.getValueAt(i, 11).toString());
    }
    txt_m012.setText(String.valueOf(m012));
    txt_m1324.setText(String.valueOf(m1324));
    txt_m2536.setText(String.valueOf(m2536));
    txt_m36.setText(String.valueOf(m36));
    txt_mTotal.setText(String.valueOf(mTotal));
            
}
    
    class CellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            setHorizontalAlignment(SwingConstants.CENTER);
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (isSelected) {
                return this;
            }    
                if (column == 11) {
                        setBackground(Color.GREEN);
                        setFont(new Font("Tahoma", Font.BOLD, 12));
                } else {
                    setBackground(table.getBackground());
                    setForeground(table.getForeground());
                }
            return this;
        }



    }
    
}

