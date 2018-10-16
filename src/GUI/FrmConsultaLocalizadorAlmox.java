/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Almoxarifado.CadAlmoxarifadoBeans;
import Almoxarifado.LocalizadorAlmoxarifadoBeans;
import DAO.Diversas;
import static GUI.Principal.listAlmoxarifado;
import static GUI.Principal.listLocalizadorAlmox;
import static GUI.frmLogin.UsuarioLogado;
import TableModel.TableModelProdutosNFe;
import Utilitarios.CentralizarTabela;
import componentes.UJComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author agroa
 */
public class FrmConsultaLocalizadorAlmox extends javax.swing.JInternalFrame {

    Diversas DiversasD;
    TableModelProdutosNFe TbProdutos;
    CentralizarTabela Centralizar;

    public FrmConsultaLocalizadorAlmox() {
        initComponents();
        DiversasD = new Diversas();
        Centralizar = new CentralizarTabela();
        carregarAlmoxarifado();
        carregarListLocalizador();
        carregarTabela();

    }

    private void carregarAlmoxarifado() {
        if (listAlmoxarifado == null) {
            listAlmoxarifado = DiversasD.ListAlmoxarifado(UsuarioLogado);
        }
        listAlmoxarifado.forEach((a) -> {
            cb_almoxarifado.addItem(a);
            cb_almoxarifado.addItem(a);
        });
    }

    private void carregarListLocalizador() {
        if (listLocalizadorAlmox == null) {
            listLocalizadorAlmox = DiversasD.ListLocalizadorAlmox();
        }
    }

    private void ComboBoxLocalizar(Integer IdAlmoxarifado, UJComboBox comboBox) {
        comboBox.removeAllItems();
        LocalizadorAlmoxarifadoBeans bean = new LocalizadorAlmoxarifadoBeans();
        bean.setID(0);
        bean.setDescricao("-");
        bean.setId_Almoxarifado(0);
        comboBox.addItem(bean);
        for (LocalizadorAlmoxarifadoBeans l : listLocalizadorAlmox) {
            if (Objects.equals(l.getId_Almoxarifado(), IdAlmoxarifado)) {
                comboBox.addItem(l);
            }
        }
    }

    private JTable carregarTabela() {
        tb_pecas.setModel(getTableModel());
        ((DefaultTableCellRenderer) tb_pecas.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        Centralizar.centralizarTabela(tb_pecas);
        //cellRenderer = new TableModelCellRenderer();

        tb_pecas.getColumnModel().getColumn(TbProdutos.ID_DB).setMaxWidth(0);
        tb_pecas.getColumnModel().getColumn(TbProdutos.ID_DB).setMinWidth(0);
        tb_pecas.getColumnModel().getColumn(TbProdutos.ID_DB).setPreferredWidth(0);

        tb_pecas.getColumnModel().getColumn(TbProdutos.ID_CADASTRO).setMaxWidth(80);
        tb_pecas.getColumnModel().getColumn(TbProdutos.ID_CADASTRO).setMinWidth(40);
        tb_pecas.getColumnModel().getColumn(TbProdutos.ID_CADASTRO).setPreferredWidth(70);

        tb_pecas.getColumnModel().getColumn(TbProdutos.NCM).setMaxWidth(0);
        tb_pecas.getColumnModel().getColumn(TbProdutos.NCM).setMinWidth(0);
        tb_pecas.getColumnModel().getColumn(TbProdutos.NCM).setPreferredWidth(0);

        tb_pecas.getColumnModel().getColumn(TbProdutos.VDESC).setMaxWidth(0);
        tb_pecas.getColumnModel().getColumn(TbProdutos.VDESC).setMinWidth(0);
        tb_pecas.getColumnModel().getColumn(TbProdutos.VDESC).setPreferredWidth(0);

        //tb_pecas.getColumnModel().getColumn(TbProdutos.XPROD).setMaxWidth(250);
        tb_pecas.getColumnModel().getColumn(TbProdutos.XPROD).setMinWidth(120);
        tb_pecas.getColumnModel().getColumn(TbProdutos.XPROD).setPreferredWidth(250);

        tb_pecas.getColumnModel().getColumn(TbProdutos.VUNCOM).setMaxWidth(0);
        tb_pecas.getColumnModel().getColumn(TbProdutos.VUNCOM).setMinWidth(0);
        tb_pecas.getColumnModel().getColumn(TbProdutos.VUNCOM).setPreferredWidth(0);

        tb_pecas.getColumnModel().getColumn(TbProdutos.QCOM).setMaxWidth(0);
        tb_pecas.getColumnModel().getColumn(TbProdutos.QCOM).setMinWidth(0);
        tb_pecas.getColumnModel().getColumn(TbProdutos.QCOM).setPreferredWidth(0);

        tb_pecas.getColumnModel().getColumn(TbProdutos.VPROD).setMaxWidth(0);
        tb_pecas.getColumnModel().getColumn(TbProdutos.VPROD).setMinWidth(0);
        tb_pecas.getColumnModel().getColumn(TbProdutos.VPROD).setPreferredWidth(0);

        tb_pecas.getColumnModel().getColumn(TbProdutos.VALORUNITFINAL).setMaxWidth(0);
        tb_pecas.getColumnModel().getColumn(TbProdutos.VALORUNITFINAL).setMinWidth(0);
        tb_pecas.getColumnModel().getColumn(TbProdutos.VALORUNITFINAL).setPreferredWidth(0);

        tb_pecas.getColumnModel().getColumn(TbProdutos.CEST).setMaxWidth(0);
        tb_pecas.getColumnModel().getColumn(TbProdutos.CEST).setMinWidth(0);
        tb_pecas.getColumnModel().getColumn(TbProdutos.CEST).setPreferredWidth(0);

        tb_pecas.getColumnModel().getColumn(TbProdutos.ID_LOCALIZADOR).setMaxWidth(0);
        tb_pecas.getColumnModel().getColumn(TbProdutos.ID_LOCALIZADOR).setMinWidth(0);
        tb_pecas.getColumnModel().getColumn(TbProdutos.ID_LOCALIZADOR).setPreferredWidth(0);

        return tb_pecas;
    }

    private TableModelProdutosNFe getTableModel() {
        if (TbProdutos == null) {
            TbProdutos = new TableModelProdutosNFe();
        }
        return TbProdutos;
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
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        cb_almoxarifado = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        txt_idItem = new javax.swing.JTextField();
        btn_pesqItem = new javax.swing.JButton();
        javax.swing.JLabel jLabel15 = new javax.swing.JLabel();
        txt_descricao = new javax.swing.JTextField();
        javax.swing.JLabel jLabel17 = new javax.swing.JLabel();
        cb_localizador = new componentes.UJComboBox();
        btn_pesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_pecas = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Consulta Localizador");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Almoxarifado");

        cb_almoxarifado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_almoxarifadoItemStateChanged(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("ID");

        txt_idItem.setEditable(false);
        txt_idItem.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btn_pesqItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar pequeno.png"))); // NOI18N
        btn_pesqItem.setPreferredSize(new java.awt.Dimension(30, 25));
        btn_pesqItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesqItemActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Descrição");

        txt_descricao.setEditable(false);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Localizador");

        cb_localizador.setEditable(true);
        cb_localizador.setMaximumRowCount(20);
        cb_localizador.setAutocompletar(true);
        cb_localizador.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_localizadorItemStateChanged(evt);
            }
        });
        cb_localizador.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cb_localizadorFocusLost(evt);
            }
        });

        btn_pesquisar.setText("Pesquisar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_almoxarifado, 0, 159, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_idItem, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btn_pesqItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_descricao, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_localizador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_pesquisar)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(cb_almoxarifado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txt_idItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesqItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(txt_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cb_localizador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(btn_pesquisar))
                .addContainerGap())
        );

        tb_pecas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tb_pecas);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb_almoxarifadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_almoxarifadoItemStateChanged
        ComboBoxLocalizar(getIdAlmoxarifado(cb_almoxarifado), cb_localizador);
    }//GEN-LAST:event_cb_almoxarifadoItemStateChanged

    private void btn_pesqItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesqItemActionPerformed
        FrmConsultaPecas consultaPecas = new FrmConsultaPecas();
        this.getParent().add(consultaPecas);
        consultaPecas.tb_pecas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    int index = consultaPecas.tb_pecas.getSelectedRow();
                    txt_idItem.setText(consultaPecas.TbPecas.getValueAt(index, 0).toString());
                    txt_descricao.setText(consultaPecas.TbPecas.getValueAt(index, consultaPecas.TbPecas.DESCRICAO).toString());
                    consultaPecas.dispose();
                }
            }
        });

        consultaPecas.setVisible(true);
    }//GEN-LAST:event_btn_pesqItemActionPerformed

    private void cb_localizadorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_localizadorItemStateChanged

    }//GEN-LAST:event_cb_localizadorItemStateChanged

    private void cb_localizadorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cb_localizadorFocusLost

    }//GEN-LAST:event_cb_localizadorFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btn_pesqItem;
    private javax.swing.JButton btn_pesquisar;
    public javax.swing.JComboBox<CadAlmoxarifadoBeans> cb_almoxarifado;
    public componentes.UJComboBox cb_localizador;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tb_pecas;
    private javax.swing.JTextField txt_descricao;
    public javax.swing.JTextField txt_idItem;
    // End of variables declaration//GEN-END:variables

    public Integer getIdAlmoxarifado(JComboBox<CadAlmoxarifadoBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return comboBox.getItemAt(comboBox.getSelectedIndex()).getID();
        }
        return 0;
    }

    public void setJComboBoxAlmoxarifado(JComboBox<CadAlmoxarifadoBeans> cb, Integer ID) {
        for (int i = 0; i < cb.getModel().getSize(); i++) {
            if (cb.getItemAt(i).getID() == ID) {
                cb.setSelectedIndex(i);
            }
        }
    }

    public Integer getIdLocalizador() {
        LocalizadorAlmoxarifadoBeans localizador;
        try {
            localizador = (LocalizadorAlmoxarifadoBeans) cb_localizador.getSelectedObject();
            return localizador.getID();
        } catch (Exception e) {
            return 0;
        }
    }

    public String getLocalizador() {
        LocalizadorAlmoxarifadoBeans localizador;
        try {
            localizador = (LocalizadorAlmoxarifadoBeans) cb_localizador.getSelectedObject();
            return localizador.getDescricao();
        } catch (Exception e) {
            return "";
        }
    }

    public void setComboBoxLocalizador(Integer IdLocalizador) {
        cb_localizador.setSelectedIndex(0);
        for (int i = 0; i < cb_localizador.getItemCount(); i++) {
            LocalizadorAlmoxarifadoBeans localizador = (LocalizadorAlmoxarifadoBeans) cb_localizador.getItemAt(i);
            if (localizador.getID() == IdLocalizador) {
                cb_localizador.setSelectedIndex(i);
            }
        }
    }

}
