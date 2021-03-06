/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.NFeDAO;
import NFe.TableModelNFe;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author agroa
 */
public class FrmConsultarNFe extends javax.swing.JInternalFrame {

    private MaskFormatter CNPJMask;
    private MaskFormatter CPFMask;
    TableModelNFe TbNFe;
    NFeDAO NFeD;
    CentralizarTabela Centralizar;

    public FrmConsultarNFe() {
        initComponents();
        NFeD = new NFeDAO();
        Centralizar = new CentralizarTabela();
        maskFormaterCPF();
        carregarTabela();

    }

    private void maskFormaterCPF() {
        try {
            CNPJMask = new MaskFormatter("##.###.###/####-##");
            CPFMask = new MaskFormatter("###.###.###-##");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    private TableModelNFe getTableModel() {
        if (TbNFe == null) {
            TbNFe = new TableModelNFe();
        }
        return TbNFe;
    }

    private JTable carregarTabela() {
        tb_nfe.setModel(getTableModel());
        ((DefaultTableCellRenderer) tb_nfe.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        Centralizar.centralizarTabela(tb_nfe);
        for (int j = 0; j < TbNFe.getColumnCount(); j++) {
            tb_nfe.getColumnModel().getColumn(j).setMaxWidth(0);
            tb_nfe.getColumnModel().getColumn(j).setMinWidth(0);
            tb_nfe.getColumnModel().getColumn(j).setPreferredWidth(0);
        }

        tb_nfe.getColumnModel().getColumn(TbNFe.ID).setMaxWidth(60);
        tb_nfe.getColumnModel().getColumn(TbNFe.ID).setMinWidth(30);
        tb_nfe.getColumnModel().getColumn(TbNFe.ID).setPreferredWidth(50);

        tb_nfe.getColumnModel().getColumn(TbNFe.ID_FORNECEDOR).setMaxWidth(60);
        tb_nfe.getColumnModel().getColumn(TbNFe.ID_FORNECEDOR).setMinWidth(30);
        tb_nfe.getColumnModel().getColumn(TbNFe.ID_FORNECEDOR).setPreferredWidth(50);

        tb_nfe.getColumnModel().getColumn(TbNFe.NNF).setMaxWidth(120);
        tb_nfe.getColumnModel().getColumn(TbNFe.NNF).setMinWidth(70);
        tb_nfe.getColumnModel().getColumn(TbNFe.NNF).setPreferredWidth(90);

        tb_nfe.getColumnModel().getColumn(TbNFe.SELECIONADO).setMaxWidth(30);
        tb_nfe.getColumnModel().getColumn(TbNFe.SELECIONADO).setMinWidth(30);
        tb_nfe.getColumnModel().getColumn(TbNFe.SELECIONADO).setPreferredWidth(30);
        
        tb_nfe.getColumnModel().getColumn(TbNFe.DHEMI).setMaxWidth(130);
        tb_nfe.getColumnModel().getColumn(TbNFe.DHEMI).setMinWidth(110);
        tb_nfe.getColumnModel().getColumn(TbNFe.DHEMI).setPreferredWidth(130);

        tb_nfe.getColumnModel().getColumn(TbNFe.CNPJ).setMaxWidth(150);
        tb_nfe.getColumnModel().getColumn(TbNFe.CNPJ).setMinWidth(110);
        tb_nfe.getColumnModel().getColumn(TbNFe.CNPJ).setPreferredWidth(150);

        tb_nfe.getColumnModel().getColumn(TbNFe.XFANT).setMaxWidth(180);
        tb_nfe.getColumnModel().getColumn(TbNFe.XFANT).setMinWidth(110);
        tb_nfe.getColumnModel().getColumn(TbNFe.XFANT).setPreferredWidth(180);

        tb_nfe.getColumnModel().getColumn(TbNFe.IEDEST).setMaxWidth(130);
        tb_nfe.getColumnModel().getColumn(TbNFe.IEDEST).setMinWidth(110);
        tb_nfe.getColumnModel().getColumn(TbNFe.IEDEST).setPreferredWidth(130);

        tb_nfe.getColumnModel().getColumn(TbNFe.VNF).setMaxWidth(150);
        tb_nfe.getColumnModel().getColumn(TbNFe.VNF).setMinWidth(110);
        tb_nfe.getColumnModel().getColumn(TbNFe.VNF).setPreferredWidth(130);
        return tb_nfe;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        txt_dtInicial = new com.toedter.calendar.JDateChooser();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        txt_nNf = new javax.swing.JTextField();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        cb_tipoPessoa = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        txt_cpf = new javax.swing.JFormattedTextField();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        txt_dtFinal = new com.toedter.calendar.JDateChooser();
        btn_pesquisar = new javax.swing.JButton();
        btn_pesqFornecedor = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_nfe = new javax.swing.JTable();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Consulta de NFe");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setToolTipText("");
        jPanel1.setName("Consultar NFe's"); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Data");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Nº Doc");

        txt_nNf.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Tipo Pessoa");

        cb_tipoPessoa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "CPF", "CNPJ" }));
        cb_tipoPessoa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_tipoPessoaItemStateChanged(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("CNPJ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("à");

        btn_pesquisar.setText("Pesquisar");
        btn_pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisarActionPerformed(evt);
            }
        });

        btn_pesqFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar pequeno.png"))); // NOI18N
        btn_pesqFornecedor.setPreferredSize(new java.awt.Dimension(30, 25));
        btn_pesqFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesqFornecedorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_dtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nNf, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_tipoPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_pesqFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_pesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
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
                    .addComponent(txt_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(cb_tipoPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txt_nNf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(btn_pesqFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesquisar))
                .addContainerGap())
        );

        tb_nfe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tb_nfe.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(tb_nfe);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb_tipoPessoaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_tipoPessoaItemStateChanged
        if (cb_tipoPessoa.getSelectedItem().toString().equals("CPF")) {
            txt_cpf.setValue(null);
            txt_cpf.setFormatterFactory(new DefaultFormatterFactory(CPFMask));
        } else if (cb_tipoPessoa.getSelectedItem().toString().equals("CNPJ")) {
            txt_cpf.setValue(null);
            txt_cpf.setFormatterFactory(new DefaultFormatterFactory(CNPJMask));
        } else {
            txt_cpf.setValue(null);
            txt_cpf.setFormatterFactory(null);
        }
    }//GEN-LAST:event_cb_tipoPessoaItemStateChanged

    private void btn_pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisarActionPerformed
        NFeD.ConsultarNFe(TbNFe,getSQLWhere());
    }//GEN-LAST:event_btn_pesquisarActionPerformed

    private void btn_pesqFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesqFornecedorActionPerformed
        frmCadFornecedores fornecedor = new frmCadFornecedores();
        this.getParent().add(fornecedor);
        fornecedor.setVisible(true);
        fornecedor.btn_novo1.setEnabled(false);
        fornecedor.tb_fornecedores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    int index = fornecedor.tb_fornecedores.getSelectedRow();
                    String CNPJ = fornecedor.TbForn.getValueAt(index, 7).toString();
                    if (CNPJ.length() > 14) {
                        cb_tipoPessoa.setSelectedIndex(2);
                    } else {
                        cb_tipoPessoa.setSelectedIndex(1);
                    }
                    txt_cpf.setText(CNPJ);
                    fornecedor.dispose();
                }
            }
        });
    }//GEN-LAST:event_btn_pesqFornecedorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btn_pesqFornecedor;
    private javax.swing.JButton btn_pesquisar;
    private javax.swing.JComboBox<String> cb_tipoPessoa;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tb_nfe;
    private javax.swing.JFormattedTextField txt_cpf;
    private com.toedter.calendar.JDateChooser txt_dtFinal;
    private com.toedter.calendar.JDateChooser txt_dtInicial;
    private javax.swing.JTextField txt_nNf;
    // End of variables declaration//GEN-END:variables

    private String getStringDate(Date dt) {
        String s;
        s = new SimpleDateFormat("dd/MM/yyyy").format(dt);
        return s;
    }
    
    public String getSQLWhere(){
        String s = "";
        if (txt_dtInicial.getDate() != null && txt_dtFinal.getDate() != null) {
            s += " and nf.dhEmi Between '" + Corretores.ConverterParaSQL(getStringDate(txt_dtInicial.getDate()))
                    + "' AND '" + Corretores.ConverterParaSQL(getStringDate(txt_dtFinal.getDate())) + "'";
        }
        if (!txt_nNf.getText().equals("")){
            s += " and nf.nNF = " + txt_nNf.getText();
        }   
        if (cb_tipoPessoa.getSelectedIndex() != 0){
            s += " and nf.CNPJ = '" + txt_cpf.getText().replaceAll("[^0-9]", "") + "'";
        }
        if (!s.equals("")){
            s = " WHERE " + s.replaceFirst("and", "");
        }
        return s;
    }    
    
    
}
