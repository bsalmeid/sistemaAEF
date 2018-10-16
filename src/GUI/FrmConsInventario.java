package GUI;

import Beans.CategoriaEquipamentosBeans;
import Beans.InventarioBeans;
import Beans.MarcaEquipamentosBeans;
import Beans.ModeloEquipamentosBeans;
import Beans.PropriedadesBeans;
import DAO.DiversasHibernate;
import DAO.InventarioDAO;
import static GUI.Principal.ListaPropriedades;
import static GUI.Principal.listaCategoriaEquipamentos;
import static GUI.Principal.listaMarcaEquipamentos;
import static GUI.Principal.listaModeloEquipamentos;
import TableModel.TableModelInventarioFinanceiro;
import Utilitarios.CentralizarTabela;
import java.awt.Component;
import java.awt.Container;
import java.util.Objects;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class FrmConsInventario extends javax.swing.JDialog {

    CentralizarTabela Centralizar;
    InventarioDAO InventD;
    InventarioBeans InventB;
    TableModelInventarioFinanceiro TbConInventario;
    private Boolean confirmado = false;

    public FrmConsInventario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Centralizar = new CentralizarTabela();
        InventD = new InventarioDAO();
        InventB = new InventarioBeans();
        getTabelaConsInventario();
        carregarModelo();
        carregarCategoria();
        carregarMarca();
        carregarFazendas();
        getRootPane().setDefaultButton(btn_buscar);
    }

    private void carregarModelo() {
        if (listaModeloEquipamentos == null) {
            listaModeloEquipamentos = InventD.listarModelo();
        }
        cb_modelo.addItem(new ModeloEquipamentosBeans(0, "-"));
        for (ModeloEquipamentosBeans m : listaModeloEquipamentos) {
            cb_modelo.addItem(m);
        }
    }

    private void carregarCategoria() {
        if (listaCategoriaEquipamentos == null) {
            listaCategoriaEquipamentos = InventD.listarCategoria();
        }
        cb_categoria.addItem(new CategoriaEquipamentosBeans(0, "-"));
        for (CategoriaEquipamentosBeans c : listaCategoriaEquipamentos) {
            cb_categoria.addItem(c);
        }
    }

    private void carregarMarca() {
        if (listaMarcaEquipamentos == null) {
            listaMarcaEquipamentos = InventD.listarMarca();
        }
        cb_marca.addItem(new MarcaEquipamentosBeans(0, "-"));
        for (MarcaEquipamentosBeans m : listaMarcaEquipamentos) {
            cb_marca.addItem(m);
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

    private void carregarModelo(Integer idMarca, JComboBox<ModeloEquipamentosBeans> combo) {
        if (listaModeloEquipamentos == null) {
            listaModeloEquipamentos = InventD.listarModelo();
        }
        combo.removeAllItems();
        combo.addItem(new ModeloEquipamentosBeans(0, "-"));
        for (ModeloEquipamentosBeans m : listaModeloEquipamentos) {
            if (Objects.equals(m.getId_marca().getID(), idMarca)) {
                combo.addItem(m);
            }
        }
    }

    private TableModelInventarioFinanceiro getTableModelConsInventario() {
        if (TbConInventario == null) {
            TbConInventario = new TableModelInventarioFinanceiro();
        }
        return TbConInventario;
    }

    private JTable getTabelaConsInventario() {
        tb_conInventario.setModel(getTableModelConsInventario());
        Centralizar.centralizarTabela(tb_conInventario);
        ((DefaultTableCellRenderer) tb_conInventario.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        tb_conInventario.getColumnModel().getColumn(TbConInventario.ID_INVENTARIO).setMaxWidth(0);
        tb_conInventario.getColumnModel().getColumn(TbConInventario.ID_INVENTARIO).setMinWidth(0);
        tb_conInventario.getColumnModel().getColumn(TbConInventario.ID_INVENTARIO).setPreferredWidth(0);
        tb_conInventario.getColumnModel().getColumn(TbConInventario.CATEGORIA).setPreferredWidth(80);
        tb_conInventario.getColumnModel().getColumn(TbConInventario.MARCA).setPreferredWidth(80);
        tb_conInventario.getColumnModel().getColumn(TbConInventario.MODELO).setPreferredWidth(100);
        tb_conInventario.getColumnModel().getColumn(TbConInventario.FROTA).setPreferredWidth(30);
        tb_conInventario.getColumnModel().getColumn(TbConInventario.FAZENDA).setPreferredWidth(80);

        return tb_conInventario;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tb_conInventario = new javax.swing.JTable();
        jPanelConsulta = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        txt_frota = new javax.swing.JTextField();
        btn_buscar = new javax.swing.JButton();
        javax.swing.JLabel jLabel20 = new javax.swing.JLabel();
        cb_categoria = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel22 = new javax.swing.JLabel();
        cb_modelo = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel21 = new javax.swing.JLabel();
        cb_marca = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel24 = new javax.swing.JLabel();
        cb_fazenda = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pesquisar Número da Frota");
        setResizable(false);

        tb_conInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tb_conInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_conInventarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_conInventario);

        jPanelConsulta.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Nº Frota");

        txt_frota.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btn_buscar.setMnemonic('e');
        btn_buscar.setText("Pesquisar");
        btn_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Categoria");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Modelo");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Marca");

        cb_marca.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_marcaItemStateChanged(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Fazenda");

        javax.swing.GroupLayout jPanelConsultaLayout = new javax.swing.GroupLayout(jPanelConsulta);
        jPanelConsulta.setLayout(jPanelConsultaLayout);
        jPanelConsultaLayout.setHorizontalGroup(
            jPanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelConsultaLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_marca, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelConsultaLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_frota, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_categoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelConsultaLayout.setVerticalGroup(
            jPanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(txt_frota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(cb_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel21)
                    .addComponent(cb_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(cb_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_buscar))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanelConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarActionPerformed
        TbConInventario.limpar();
        TbConInventario.addLista(InventD.listarInventarioString(getWhere()));
        limparCampos(jPanelConsulta);
    }//GEN-LAST:event_btn_buscarActionPerformed

    private void tb_conInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_conInventarioMouseClicked

    }//GEN-LAST:event_tb_conInventarioMouseClicked

    private void cb_marcaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_marcaItemStateChanged
        if (cb_marca.getSelectedIndex() > 0) {
            carregarModelo(getMarca(cb_marca).getID(), cb_modelo);
        }
    }//GEN-LAST:event_cb_marcaItemStateChanged

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
            java.util.logging.Logger.getLogger(FrmConsInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmConsInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmConsInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmConsInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmConsInventario dialog = new FrmConsInventario(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btn_buscar;
    private javax.swing.JComboBox<CategoriaEquipamentosBeans> cb_categoria;
    private javax.swing.JComboBox<PropriedadesBeans> cb_fazenda;
    private javax.swing.JComboBox<MarcaEquipamentosBeans> cb_marca;
    private javax.swing.JComboBox<Beans.ModeloEquipamentosBeans> cb_modelo;
    private javax.swing.JPanel jPanelConsulta;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tb_conInventario;
    private javax.swing.JTextField txt_frota;
    // End of variables declaration//GEN-END:variables

    public Boolean getConfirmado() {
        return confirmado;
    }

    public void setConfirmado(Boolean confirmado) {
        this.confirmado = confirmado;
    }

    public InventarioBeans getInventB() {
        return InventB;
    }

    public void setInventB(InventarioBeans InventB) {
        this.InventB = InventB;
    }

    public String getWhere() {
        String s = "";

        if (cb_fazenda.getSelectedIndex() > 0) {
            s += " and T.id_fazenda = " + getFazenda(cb_fazenda).getCodigo();
        }
        if (cb_categoria.getSelectedIndex() > 0) {
            s += " and T.id_categoria = "+ getCategoria(cb_categoria).getID();
        }
        if (cb_marca.getSelectedIndex() > 0) {
            s += " and T.id_marca = " + getMarca(cb_marca).getID();
        }
        if (cb_modelo.getSelectedIndex() > 0) {
            s += " and T.id_modelo.descricao = " + getModelo(cb_modelo).getID();
        }
        if (!txt_frota.getText().equals("")) {
            s += " and T.nFrota like '%" + txt_frota.getText() + "%'";
        }
        if (!s.equals("")) {
            s = " WHERE " + s.replaceFirst("and", "");
        }
        return s;
    }

    private void limparCampos(Container container) {
        for (int i = 0; i < container.getComponentCount(); i++) {
            Component c = container.getComponent(i);
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            } else if (c instanceof JComboBox) {
                ((JComboBox) c).setSelectedIndex(0);
            }
        }
    }

    private MarcaEquipamentosBeans getMarca(JComboBox<MarcaEquipamentosBeans> combo) {
        return (MarcaEquipamentosBeans) combo.getSelectedItem();
    }

    private void setMarca(JComboBox<MarcaEquipamentosBeans> combo, Integer ID) {
        for (int i = 0; i < combo.getModel().getSize(); i++) {
            if (Objects.equals(combo.getItemAt(i).getID(), ID)) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }

    private ModeloEquipamentosBeans getModelo(JComboBox<ModeloEquipamentosBeans> combo) {
        return (ModeloEquipamentosBeans) combo.getSelectedItem();
    }

    private void setModelo(JComboBox<ModeloEquipamentosBeans> combo, Integer ID) {
        for (int i = 0; i < combo.getModel().getSize(); i++) {
            if (Objects.equals(combo.getItemAt(i).getID(), ID)) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }

    private CategoriaEquipamentosBeans getCategoria(JComboBox<CategoriaEquipamentosBeans> combo) {
        return (CategoriaEquipamentosBeans) combo.getSelectedItem();
    }

    private void setCategoria(JComboBox<CategoriaEquipamentosBeans> combo, Integer ID) {
        for (int i = 0; i < combo.getModel().getSize(); i++) {
            if (Objects.equals(combo.getItemAt(i).getID(), ID)) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }
    
    private PropriedadesBeans getFazenda(JComboBox<PropriedadesBeans> combo) {
        return (PropriedadesBeans) combo.getSelectedItem();
    }

}
