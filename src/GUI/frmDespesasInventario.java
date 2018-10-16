package GUI;

import Beans.CategoriaEquipamentosBeans;
import Beans.InventarioBeans;
import Beans.ModeloEquipamentosBeans;
import DAO.Diversas;
import DAO.DiversasHibernate;
import static GUI.Principal.listInventario;
import Utilitarios.Conexao;
import Utilitarios.Corretores;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import static GUI.Principal.listaCategoriaEquipamentos;
import static GUI.Principal.listaModeloEquipamentos;
import Icones.FormatarICO;
import componentes.UJComboBox;
import java.util.Objects;

public class frmDespesasInventario extends javax.swing.JDialog {

    Diversas DiversasD;

    public frmDespesasInventario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        DiversasD = new Diversas();
        carregarCategorias();
        txt_dtInicial.setDate(Corretores.PrimeiroDiaMes());
        txt_dtFinal.setDate(Corretores.UltimoDiaMes());
        carregarInventario();

    }

    private void carregarModelos(Integer idCategoria, JComboBox<ModeloEquipamentosBeans> combo) {
        if (listaModeloEquipamentos == null) {
            listaModeloEquipamentos = DiversasHibernate.getListaModeloInventario();
        }
        ModeloEquipamentosBeans b = new ModeloEquipamentosBeans(0, "-");
        cb_modelo.removeAllItems();
        cb_modelo.addItem(b);
        for (ModeloEquipamentosBeans list : listaModeloEquipamentos) {
            if (Objects.equals(list.getId_categoria().getID(), idCategoria)) {
                combo.addItem(list);
            }
        }
    }

    private void carregarCategorias() {
        if (listaCategoriaEquipamentos == null) {
            listaCategoriaEquipamentos = DiversasHibernate.getListaCategoria();
        }
        CategoriaEquipamentosBeans b = new CategoriaEquipamentosBeans();
        b.setID(0);
        b.setCategoria("-");
        b.setStatus(true);
        cb_categoria.addItem(b);
        for (CategoriaEquipamentosBeans list : listaCategoriaEquipamentos) {
            cb_categoria.addItem(list);
        }
    }

    private void carregarInventario() {
        if (listInventario == null) {
            listInventario = DiversasHibernate.getListaInventario();
        }
        cb_nFrota.removeAllItems();
        InventarioBeans l = new InventarioBeans(0, "-", "-");
        cb_nFrota.addItem(l);
        for (InventarioBeans frota : listInventario) {
            cb_nFrota.addItem(frota);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_dtInicial = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        txt_dtFinal = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        cb_categoria = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cb_modelo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        ch_completo = new javax.swing.JCheckBox();
        ch_sintetico = new javax.swing.JCheckBox();
        cb_nFrota = new componentes.UJComboBox();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Depesas de Inventário");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Data Inicial");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Data Final");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Categoria");

        cb_categoria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_categoriaItemStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Modelo");

        cb_modelo.setBorder(null);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Nº Frota");

        buttonGroup1.add(ch_completo);
        ch_completo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_completo.setText("Completo");

        buttonGroup1.add(ch_sintetico);
        ch_sintetico.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_sintetico.setSelected(true);
        ch_sintetico.setText("Sintético");

        cb_nFrota.setEditable(true);
        cb_nFrota.setAutocompletar(true);

        jButton1.setText("Gerar Relatório");
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_modelo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_nFrota, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(ch_completo)
                        .addGap(10, 10, 10)
                        .addComponent(ch_sintetico)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(cb_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(cb_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cb_nFrota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ch_completo)
                    .addComponent(ch_sintetico))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb_categoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_categoriaItemStateChanged
        carregarModelos(getIdCategoria(cb_categoria), cb_modelo);
    }//GEN-LAST:event_cb_categoriaItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        gerarRelatorio();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(frmDespesasInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmDespesasInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmDespesasInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmDespesasInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmDespesasInventario dialog = new frmDespesasInventario(new javax.swing.JFrame(), true);
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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<Beans.CategoriaEquipamentosBeans> cb_categoria;
    private javax.swing.JComboBox<Beans.ModeloEquipamentosBeans> cb_modelo;
    private componentes.UJComboBox cb_nFrota;
    private javax.swing.JCheckBox ch_completo;
    private javax.swing.JCheckBox ch_sintetico;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private com.toedter.calendar.JDateChooser txt_dtFinal;
    private com.toedter.calendar.JDateChooser txt_dtInicial;
    // End of variables declaration//GEN-END:variables

    private Integer getIdCategoria(JComboBox<CategoriaEquipamentosBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return ((CategoriaEquipamentosBeans) comboBox.getSelectedItem()).getID();
        }
        return 0;
    }

    private Integer getIdModelo(JComboBox<ModeloEquipamentosBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return ((ModeloEquipamentosBeans) comboBox.getSelectedItem()).getID();
        }
        return 0;
    }

    private InventarioBeans getInventario(UJComboBox combo) {
        if (combo.getSelectedIndex() > 0) {
            return (InventarioBeans) combo.getSelectedItem();
        } else {
            return null;
        }
    }

    private void gerarRelatorio() {
        int confirma = JOptionPane.showConfirmDialog(null, "Deseja gerar o relatório de despesas por inventário?", "Atenção", JOptionPane.YES_NO_OPTION);
        Conexao.ReiniciarCon();
        if (confirma == JOptionPane.YES_OPTION) {
            Map p = new HashMap();
            JasperReport relatorio;
            JasperPrint impressao;
            JasperViewer jrviewer = null;
            try {
                if (!txt_dtInicial.getDate().equals("")) {
                    p.put("dtInicial", txt_dtInicial.getDate());
                    p.put("dtFinal", txt_dtFinal.getDate());
                    p.put("sqlWhere", sqlWhere());
                    p.put("sqlValor", sqlValor());
                    p.put("sqlTitular", sqlTitular());
                    p.put("sqlDescricao", sqlDescricao());
                    p.put("sqlGroupBy", sqlGroupBy());
                    p.put("caminhoLogo", FormatarICO.logoTipo().getImage());
                    relatorio = JasperCompileManager.compileReport(new File("").getClass().getResourceAsStream("/Relatorios/DespesasInventarioPlanoContas.jrxml"));
                    impressao = JasperFillManager.fillReport(relatorio, p, Conexao.getConnection());
                    jrviewer = new JasperViewer(impressao, false);
                    jrviewer.setVisible(true);
                    jrviewer.toFront();
                } else {
                    JOptionPane.showMessageDialog(null, "Verifique o intervalo de datas!");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            this.dispose();
            jrviewer.toFront();
        }
    }

    private String sqlWhere() {
        String s = "";
        if (cb_categoria.getSelectedIndex() > 0) {
            s = " and ci.id_categoria = " + getIdCategoria(cb_categoria);
        }
        if (cb_modelo.getSelectedIndex() > 0) {
            s = " and ci.id_modelo = " + getIdModelo(cb_modelo);
        }
        if (cb_nFrota.getSelectedIndex() > 0) {
            s = " and ci.id = " + getInventario(cb_nFrota).getID();
        }
        return s;
    }

    private String sqlValor() {
        String s = "";
        if (ch_completo.isSelected()) {
            s = "ec.clas_valor";
        } else if (ch_sintetico.isSelected()) {
            s = "SUM(ec.clas_valor)";
        }
        return s;
    }

    private String sqlDescricao() {
        String s = "";
        if (ch_completo.isSelected()) {
            s = "ec.clas_descricao";
        } else if (ch_sintetico.isSelected()) {
            s = "'-'";
        }
        return s;
    }

    private String sqlTitular() {
        String s = "";
        if (ch_completo.isSelected()) {
            s = "pg.pagto_titular";
        } else if (ch_sintetico.isSelected()) {
            s = "'-'";
        }
        return s;
    }

    private String sqlGroupBy() {
        String s = "";
        if (ch_completo.isSelected()) {
            s = "";
        } else if (ch_sintetico.isSelected()) {
            s = " group by ID, nFrota, PlanoConta ,Categoria, Marca, Modelo, Titular ";
        }
        return s;
    }

}
