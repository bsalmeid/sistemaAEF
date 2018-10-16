package GUI;

import Beans.CadUsuario;
import Beans.CadUsuarioFazendas;
import Beans.CadUsuarioPermissoes;
import Beans.PropriedadesBeans;
import Beans.UsuarioBeans;
import Controller.UsuarioController;
import DAO.Diversas;
import DAO.DiversasHibernate;
import DAO.UsuarioDAO;
import Icones.FormatarICO;
import TableModel.TableModelUsuarioFazenda;
import TableModel.TableModelUsuarioPerm;
import Utilitarios.CentralizarTabela;
import Utilitarios.ValidarPermissoes;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class FrmCadUsuario extends javax.swing.JInternalFrame {

    CadUsuario UsuarioB;
    CadUsuarioPermissoes PermissaoB;
    UsuarioDAO UsuarioD;
    Diversas DiversasD;
    TableModelUsuarioFazenda TbFazendas;
    TableModelUsuarioPerm TbPermissoes;
    CentralizarTabela Centralizar;
    List<CadUsuarioPermissoes> listaPermissoes;
    List<PropriedadesBeans> listaFazendas;
    List<CadUsuarioFazendas> listaUsuarioFazendas;

    public FrmCadUsuario() {
        initComponents();
        popularListaPermissoes();
        Centralizar = new CentralizarTabela();
        listaPermissoes = new ArrayList<>();
        listaFazendas = new ArrayList<>();

        UsuarioD = new UsuarioDAO();
        DiversasD = new Diversas();
        getTablePermissoes();
        getTableFazendas();

    }

    private JTable getTablePermissoes() {
        tb_permissoes.setModel(getTableModelPermissao());
        ((DefaultTableCellRenderer) tb_permissoes.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        Centralizar.centralizarTabela(tb_permissoes);

        tb_permissoes.getColumnModel().getColumn(TbPermissoes.ID).setMaxWidth(0);
        tb_permissoes.getColumnModel().getColumn(TbPermissoes.ID).setMinWidth(0);
        tb_permissoes.getColumnModel().getColumn(TbPermissoes.ID).setPreferredWidth(0);

        tb_permissoes.getColumnModel().getColumn(TbPermissoes.ID_USER).setMaxWidth(0);
        tb_permissoes.getColumnModel().getColumn(TbPermissoes.ID_USER).setMinWidth(0);
        tb_permissoes.getColumnModel().getColumn(TbPermissoes.ID_USER).setPreferredWidth(0);

        tb_permissoes.getColumnModel().getColumn(TbPermissoes.NOME_CLASSE).setMaxWidth(0);
        tb_permissoes.getColumnModel().getColumn(TbPermissoes.NOME_CLASSE).setMinWidth(0);
        tb_permissoes.getColumnModel().getColumn(TbPermissoes.NOME_CLASSE).setPreferredWidth(0);

        tb_permissoes.getColumnModel().getColumn(TbPermissoes.NOME_AMIGAVEL).setPreferredWidth(250);

        return tb_permissoes;
    }

    private JTable getTableFazendas() {
        tb_fazendas.setModel(getTableModelFazendas());
        ((DefaultTableCellRenderer) tb_fazendas.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        Centralizar.centralizarTabela(tb_fazendas);

        tb_fazendas.getColumnModel().getColumn(TbFazendas.ID).setMaxWidth(0);
        tb_fazendas.getColumnModel().getColumn(TbFazendas.ID).setMinWidth(0);
        tb_fazendas.getColumnModel().getColumn(TbFazendas.ID).setPreferredWidth(0);

        tb_fazendas.getColumnModel().getColumn(TbFazendas.ID_USER).setMaxWidth(0);
        tb_fazendas.getColumnModel().getColumn(TbFazendas.ID_USER).setMinWidth(0);
        tb_fazendas.getColumnModel().getColumn(TbFazendas.ID_USER).setPreferredWidth(0);

        tb_fazendas.getColumnModel().getColumn(TbFazendas.ID_FAZENDA).setMaxWidth(80);
        tb_fazendas.getColumnModel().getColumn(TbFazendas.ID_FAZENDA).setMinWidth(80);
        tb_fazendas.getColumnModel().getColumn(TbFazendas.ID_FAZENDA).setPreferredWidth(80);

        tb_fazendas.getColumnModel().getColumn(TbFazendas.ACESSO).setMaxWidth(80);
        tb_fazendas.getColumnModel().getColumn(TbFazendas.ACESSO).setMinWidth(80);
        tb_fazendas.getColumnModel().getColumn(TbFazendas.ACESSO).setPreferredWidth(80);

        return tb_fazendas;
    }

    private TableModelUsuarioPerm getTableModelPermissao() {
        if (TbPermissoes == null) {
            TbPermissoes = new TableModelUsuarioPerm();
        }
        return TbPermissoes;
    }

    private TableModelUsuarioFazenda getTableModelFazendas() {
        if (TbFazendas == null) {
            TbFazendas = new TableModelUsuarioFazenda();
        }
        return TbFazendas;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopup = new javax.swing.JPopupMenu();
        jMenuMarcar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuDesmarcar = new javax.swing.JMenuItem();
        btn_novo = new javax.swing.JButton();
        btn_cadastrar = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();
        txt_pesquisar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_nome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_login = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_senha1 = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        txt_telefone = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_senha2 = new javax.swing.JPasswordField();
        ch_status = new javax.swing.JCheckBox();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_permissoes = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_fazendas = new javax.swing.JTable();

        jMenuMarcar.setText("Marcar Selecionados");
        jMenuMarcar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuMarcarActionPerformed(evt);
            }
        });
        jPopup.add(jMenuMarcar);
        jPopup.add(jSeparator1);

        jMenuDesmarcar.setText("Desmarcar Selecionados");
        jMenuDesmarcar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuDesmarcarActionPerformed(evt);
            }
        });
        jPopup.add(jMenuDesmarcar);

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Cadastro de Usuários");
        setToolTipText("");
        setNormalBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setPreferredSize(new java.awt.Dimension(700, 620));

        btn_novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/inserir.png"))); // NOI18N
        btn_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novoActionPerformed(evt);
            }
        });

        btn_cadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/salvar.png"))); // NOI18N
        btn_cadastrar.setEnabled(false);
        btn_cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cadastrarActionPerformed(evt);
            }
        });

        btn_editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/editar.png"))); // NOI18N
        btn_editar.setEnabled(false);
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });

        btn_excluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/deletar.png"))); // NOI18N
        btn_excluir.setEnabled(false);
        btn_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirActionPerformed(evt);
            }
        });

        txt_pesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar.png"))); // NOI18N
        txt_pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_pesquisarActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Código");

        txt_codigo.setEditable(false);
        txt_codigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_codigo.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Nome");

        txt_nome.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Login");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("E-mail");

        txt_email.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Senha");

        txt_senha1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_senha1.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Telefone");

        txt_telefone.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Confirmar Senha");

        txt_senha2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_senha2.setEnabled(false);

        ch_status.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_status.setText("Status");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_email, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                            .addComponent(txt_nome))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_telefone)
                            .addComponent(txt_login)))
                    .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txt_senha1, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_senha2, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ch_status)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(txt_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txt_login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txt_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6)
                    .addComponent(txt_senha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txt_senha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ch_status))
                .addContainerGap())
        );

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jScrollPane1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jScrollPane1PropertyChange(evt);
            }
        });

        tb_permissoes.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_permissoes.setCellSelectionEnabled(true);
        tb_permissoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_permissoesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_permissoes);

        jTabbedPane1.addTab("Rotinas", jScrollPane1);

        tb_fazendas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tb_fazendas);

        jTabbedPane1.addTab("Fazendas", jScrollPane3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                        .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 73, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Rotinas");

        setBounds(0, 0, 621, 633);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cadastrarActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(null, "Deseja Cadastrar este Usuário?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION) {
            UsuarioB = new CadUsuario();
            popularUsuario(UsuarioB);
            if (verificarBeans(UsuarioB) && ValidarPermissoes.validarPermissaoInsert(FrmCadUsuario.class.getSimpleName())) {
                if (UsuarioD.SalvarUsuarioNovo(UsuarioB)) {
                    limparCampos();
                    desabilitarCampos();
                }
            }
        }
    }//GEN-LAST:event_btn_cadastrarActionPerformed

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed
        novo();
        btn_editar.setEnabled(false);
        btn_excluir.setEnabled(false);

    }//GEN-LAST:event_btn_novoActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        int excluir = JOptionPane.showConfirmDialog(null, "Deseja Excluir este Usuário?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (excluir == JOptionPane.YES_OPTION) {
            popularUsuario(UsuarioB);
            UsuarioB.setId(new Integer(txt_codigo.getText()));
            if (ValidarPermissoes.validarPermissaoDelete(FrmCadUsuario.class.getSimpleName())) {
                if (UsuarioD.ExcluiroNovo(UsuarioB)) {
                    limparCampos();
                    desabilitarCampos();
                }
            }
        }
    }//GEN-LAST:event_btn_excluirActionPerformed

    private void txt_pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_pesquisarActionPerformed
        UsuarioB = UsuarioD.BuscarUsuario(txt_login.getText());
        if (UsuarioB != null) {
            popularListaPermissoes();
            preencherFormulario(preencherPermissoesRotinas(UsuarioB));
            btn_editar.setEnabled(true);
            btn_excluir.setEnabled(true);
        }
    }//GEN-LAST:event_txt_pesquisarActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja editar este Usuário?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION) {
            popularUsuario(UsuarioB);
            UsuarioB.setId(new Integer(txt_codigo.getText()));
            if (verificarBeans(UsuarioB) && ValidarPermissoes.validarPermissaoUpdate(FrmCadUsuario.class.getSimpleName())) {
                if (UsuarioD.EditarUsuarioNovo(UsuarioB)) {
                    limparCampos();
                    desabilitarCampos();
                }
            }
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void tb_permissoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_permissoesMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            if (jPopup.isVisible() == true) {
                jPopup.setVisible(false);
            } else {
                jPopup.setVisible(true);
                jPopup.show(this, 0, 0);
                jPopup.setLocation(evt.getLocationOnScreen());
            }
        }

    }//GEN-LAST:event_tb_permissoesMouseClicked

    private void jScrollPane1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jScrollPane1PropertyChange

    }//GEN-LAST:event_jScrollPane1PropertyChange

    private void jMenuMarcarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuMarcarActionPerformed
        int[] columnIndex = tb_permissoes.getSelectedColumns();
        int[] index = tb_permissoes.getSelectedRows();
        for (int c = 0; c < columnIndex.length; c++) {
            if (columnIndex[c] == TbPermissoes.SELECT || columnIndex[c] == TbPermissoes.DELETE || columnIndex[c] == TbPermissoes.UPDATE || columnIndex[c] == TbPermissoes.INSERT || columnIndex[c] == TbPermissoes.VIEW) {
                for (int i = 0; i < index.length; i++) {
                    TbPermissoes.setValueAt(true, index[i], columnIndex[c]);
                }
            }
        }
    }//GEN-LAST:event_jMenuMarcarActionPerformed

    private void jMenuDesmarcarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuDesmarcarActionPerformed
        int column = tb_permissoes.getSelectedColumn();
        if (column == TbPermissoes.SELECT || column == TbPermissoes.DELETE || column == TbPermissoes.UPDATE || column == TbPermissoes.INSERT || column == TbPermissoes.VIEW) {
            int[] index = tb_permissoes.getSelectedRows();
            for (int i = 0; i < index.length; i++) {
                TbPermissoes.setValueAt(false, index[i], column);
            }
        }
    }//GEN-LAST:event_jMenuDesmarcarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btn_cadastrar;
    javax.swing.JButton btn_editar;
    javax.swing.JButton btn_excluir;
    javax.swing.JButton btn_novo;
    javax.swing.JCheckBox ch_status;
    javax.swing.JLabel jLabel1;
    javax.swing.JLabel jLabel2;
    javax.swing.JLabel jLabel3;
    javax.swing.JLabel jLabel4;
    javax.swing.JLabel jLabel5;
    javax.swing.JLabel jLabel6;
    javax.swing.JLabel jLabel7;
    javax.swing.JMenuItem jMenuDesmarcar;
    javax.swing.JMenuItem jMenuMarcar;
    javax.swing.JPanel jPanel6;
    javax.swing.JPopupMenu jPopup;
    javax.swing.JScrollPane jScrollPane1;
    javax.swing.JScrollPane jScrollPane3;
    javax.swing.JPopupMenu.Separator jSeparator1;
    javax.swing.JTabbedPane jTabbedPane1;
    javax.swing.JTable tb_fazendas;
    javax.swing.JTable tb_permissoes;
    javax.swing.JTextField txt_codigo;
    javax.swing.JTextField txt_email;
    javax.swing.JTextField txt_login;
    javax.swing.JTextField txt_nome;
    javax.swing.JButton txt_pesquisar;
    javax.swing.JPasswordField txt_senha1;
    public static javax.swing.JPasswordField txt_senha2;
    javax.swing.JTextField txt_telefone;
    // End of variables declaration//GEN-END:variables

    private List<CadUsuarioPermissoes> popularListaPermissoes() {
        // Cadastros
        listaPermissoes = new ArrayList<>();
        listaPermissoes.add(new CadUsuarioPermissoes("FrmCadUsuario", "Cadastro de Usuários"));
        listaPermissoes.add(new CadUsuarioPermissoes("FrmCadAlmoxarif", "Cadastro Peças"));
        listaPermissoes.add(new CadUsuarioPermissoes("frmCadColaborador", "Cadastro de Colaborador"));
        listaPermissoes.add(new CadUsuarioPermissoes("frmCadFornecedores", "Cadatro de Fornecedores"));
        listaPermissoes.add(new CadUsuarioPermissoes("frmCadInsumos", "Cadastro de Insumos"));
        listaPermissoes.add(new CadUsuarioPermissoes("frmCadPluviometro", "Cadastro de Pluviometros"));
        listaPermissoes.add(new CadUsuarioPermissoes("FrmCadFazendas", "Cadastro de Propriedades"));
        listaPermissoes.add(new CadUsuarioPermissoes("frmResumoCadColaborador", "Consulta Colaboradores"));
        listaPermissoes.add(new CadUsuarioPermissoes("FrmCadTransportadora", "Cadastro de Transportadoras"));
        listaPermissoes.add(new CadUsuarioPermissoes("FrmCadInventario", "Cadastro de Inventário"));
        listaPermissoes.add(new CadUsuarioPermissoes("FrmCadCategoria", "Cadastro de Categorias"));
        listaPermissoes.add(new CadUsuarioPermissoes("FrmCadMarca", "Cadastro de Marcas"));
        listaPermissoes.add(new CadUsuarioPermissoes("FrmCadModelo", "Cadastro de Modelos"));
        listaPermissoes.add(new CadUsuarioPermissoes("FrmCadCentroResultado", "Cadastro de Centro de Resultado"));
        listaPermissoes.add(new CadUsuarioPermissoes("FrmCadAtividade", "Cadastro de Atividade"));
        listaPermissoes.add(new CadUsuarioPermissoes("FrmCadAnoSafra", "Cadastro de Ano da Safra"));
        listaPermissoes.add(new CadUsuarioPermissoes("FrmCadCultivo", "Cadastro de Cultivo"));
        listaPermissoes.add(new CadUsuarioPermissoes("FrmCadCultura", "Cadastro de Cultura"));
        listaPermissoes.add(new CadUsuarioPermissoes("FrmCadBanco", "Cadastro de Banco"));
        listaPermissoes.add(new CadUsuarioPermissoes("FrmCadCompradorGados", "Cadastro de Comprador de Gados"));
        listaPermissoes.add(new CadUsuarioPermissoes("FrmCadContaBancaria", "Cadastro de Conta Bancária"));
        listaPermissoes.add(new CadUsuarioPermissoes("FrmCadGrupoContas", "Cadastro de Grupo de Contas"));
        listaPermissoes.add(new CadUsuarioPermissoes("FrmCadMoeda", "Cadastro de Moeda"));
        listaPermissoes.add(new CadUsuarioPermissoes("FrmCadUnidades", "Cadastro de Unidades"));
    
        // Formularios de Movimento de Gado
        listaPermissoes.add(new CadUsuarioPermissoes("FrmRastreabilidadeBovina", "Rastreabilidade Bovina"));
        listaPermissoes.add(new CadUsuarioPermissoes("frmCompraGado", "Compra de Gado"));
        listaPermissoes.add(new CadUsuarioPermissoes("frmEntradaGado", "Entrada de Gado"));
        listaPermissoes.add(new CadUsuarioPermissoes("frmEscalasAbate", "Escalas de Abate"));
        listaPermissoes.add(new CadUsuarioPermissoes("frmFreteGado", "Acerto de Frete de Gado"));
        listaPermissoes.add(new CadUsuarioPermissoes("frmGTA", "Resumo de GTA's"));
        listaPermissoes.add(new CadUsuarioPermissoes("frmRelBovino", "Reletórios de Gado"));
        listaPermissoes.add(new CadUsuarioPermissoes("frmRelEntradaGado", "Relatorio de Entrada de Gado"));
        listaPermissoes.add(new CadUsuarioPermissoes("frmRelatorioMinutas", "Relatorio Minutas"));
        listaPermissoes.add(new CadUsuarioPermissoes("frmSaidaGado", "Saida de Gado"));
        listaPermissoes.add(new CadUsuarioPermissoes("frmVendaGado", "Venda de Gado"));

        // Pedidos, Almoxarifados, Central de Compras
        listaPermissoes.add(new CadUsuarioPermissoes("FrmConsultaEstoqueAlmoxarifado", "Estoque Almoxarifado"));
        listaPermissoes.add(new CadUsuarioPermissoes("FrmConsultaLocalizadorAlmox", "Localizador Almoxarifado"));
        listaPermissoes.add(new CadUsuarioPermissoes("FrmConsultaPecas", "Consulta de Peças"));
        listaPermissoes.add(new CadUsuarioPermissoes("FrmConsultarNFe", "Consultar NFe"));
        listaPermissoes.add(new CadUsuarioPermissoes("FrmEntradaAlmoxarifado", "Entrada do Almoxarifado"));
        listaPermissoes.add(new CadUsuarioPermissoes("FrmSaidaAlmoxarifado", "Saídas do Almoxarifado"));
        listaPermissoes.add(new CadUsuarioPermissoes("FrmImportarNFe", "Importar NFe"));
        listaPermissoes.add(new CadUsuarioPermissoes("FrmPedidosAnalise", "Análise de Pedidos"));
        listaPermissoes.add(new CadUsuarioPermissoes("FrmPedidosCentral", "Central de Compras"));
        listaPermissoes.add(new CadUsuarioPermissoes("FrmPedidosCotacao", "Cotações de Pedidos"));
        listaPermissoes.add(new CadUsuarioPermissoes("FrmPedidosFazenda", "Gerar Pedidos de Suprimentos"));
        listaPermissoes.add(new CadUsuarioPermissoes("FrmPedidosFechamento", "Compra de Suprimentos"));
        listaPermissoes.add(new CadUsuarioPermissoes("FrmPedidosSolicitacao", "Criar Solicitações"));
        listaPermissoes.add(new CadUsuarioPermissoes("FrmRemessaMercadorias", "Criar Remessa de Mercadorias"));
        listaPermissoes.add(new CadUsuarioPermissoes("FrmRemessaConsulta", "Consultar Remessas"));
        
        // Insumos Agícolas
        listaPermissoes.add(new CadUsuarioPermissoes("frmPedidosInsumos", "Pedidos Agrícolas"));
        listaPermissoes.add(new CadUsuarioPermissoes("frmSaidaInsumos", "Saída de Insumos"));
        listaPermissoes.add(new CadUsuarioPermissoes("frmEntradaInsumos", "Entrada de Insumos"));
        listaPermissoes.add(new CadUsuarioPermissoes("frmEstoqueInsumos", "Estoque de Insumos"));

        // Financeiro
        listaPermissoes.add(new CadUsuarioPermissoes("frmPagamentos", "Pagamentos"));
        listaPermissoes.add(new CadUsuarioPermissoes("frmRelPagamento", "Relatórios de Financeiros"));
        listaPermissoes.add(new CadUsuarioPermissoes("frmResPagamentos", "Paínel de Pagamentos"));
        listaPermissoes.add(new CadUsuarioPermissoes("frmTransfContas", "Transferências Bancárias"));
        listaPermissoes.add(new CadUsuarioPermissoes("frmRecebimentos", "Recebimentos"));
        listaPermissoes.add(new CadUsuarioPermissoes("frmRecebResumo", "Resumo Recebíveis"));

        // Operações
        listaPermissoes.add(new CadUsuarioPermissoes("frmPlantio", "Plantio"));
        listaPermissoes.add(new CadUsuarioPermissoes("frmAplicacoes", "Aplicações"));
        listaPermissoes.add(new CadUsuarioPermissoes("frmOperacoes", "Operações"));
        listaPermissoes.add(new CadUsuarioPermissoes("frmRelatorioOperacoes", "Relatório de Operações"));
        listaPermissoes.add(new CadUsuarioPermissoes("frmPluviosidade", "Pluviosidade"));
        //listaPermissoes.add(new CadUsuarioPermissoes("", ""));
        //listaPermissoes.add(new CadUsuarioPermissoes("", ""));
        //listaPermissoes.add(new CadUsuarioPermissoes("", ""));

        return listaPermissoes;
    }

    private void popularUsuario(CadUsuario usuario) {
        usuario.setNome(txt_nome.getText());
        usuario.setEmail(txt_email.getText());
        usuario.setTelefone(txt_telefone.getText());
        usuario.setLogin(txt_login.getText());
        usuario.setSenha(txt_senha1.getText());
        usuario.setListPermissao(TbPermissoes.getLista());
        usuario.setListFazendas(TbFazendas.getLista());
        usuario.setStatus(ch_status.isSelected());
    }

    private void preencherFormulario(CadUsuario usuario) {
        TbPermissoes.limpar();
        TbFazendas.limpar();
        TbPermissoes.addLista(usuario.getListPermissao());
        TbFazendas.addLista(preencherPermissoesFazendas(usuario));
        txt_codigo.setText(usuario.getId().toString());
        txt_nome.setText(usuario.getNome());
        txt_login.setText(usuario.getLogin());
        txt_email.setText(usuario.getEmail());
        txt_telefone.setText(usuario.getTelefone());
        txt_senha1.setText(usuario.getSenha());
        txt_senha2.setText(usuario.getSenha());
        ch_status.setSelected(usuario.isStatus());
    }

    private List<CadUsuarioFazendas> getListaFazendaUsuario() {
        listaUsuarioFazendas = new ArrayList<>();
        getListaFazendas();
        for (int i = 0; i < listaFazendas.size(); i++) {
            CadUsuarioFazendas uf = new CadUsuarioFazendas();
            uf.setIdFazenda(listaFazendas.get(i));
            uf.setFazenda(listaFazendas.get(i).getNome());
            uf.setIdUsuario(UsuarioB);
            uf.setAcesso(false);
            listaUsuarioFazendas.add(uf);
        }
        return listaUsuarioFazendas;
    }

    private List<PropriedadesBeans> getListaFazendas() {
        listaFazendas = DiversasHibernate.getListaFazendas();
        return listaFazendas;
    }

    private CadUsuario preencherPermissoesRotinas(CadUsuario usuario) {
        List<CadUsuarioPermissoes> listaUsuario = usuario.getListPermissao();
        for (CadUsuarioPermissoes permissao : listaPermissoes) {
            for (CadUsuarioPermissoes pUsuario : listaUsuario) {
                if (permissao.getNomeClasse().equals(pUsuario.getNomeClasse())) {
                    permissao.setId(pUsuario.getId());
                    permissao.setIdUsuario(usuario);
                    permissao.setDelete(pUsuario.getDelete());
                    permissao.setView(pUsuario.getView());
                    permissao.setUpdate(pUsuario.getUpdate());
                    permissao.setInsert(pUsuario.getInsert());
                    permissao.setSelect(pUsuario.getSelect());
                    break;
                }
            }
        }
        usuario.setListPermissao(listaPermissoes);
        return usuario;
    }

    private List<CadUsuarioFazendas> preencherPermissoesFazendas(CadUsuario usuario) {
        listaUsuarioFazendas = new ArrayList<>();
        getListaFazendas();
        for (int i = 0; i < listaFazendas.size(); i++) {
            CadUsuarioFazendas uf = new CadUsuarioFazendas();
            uf.setIdFazenda(listaFazendas.get(i));
            uf.setFazenda(listaFazendas.get(i).getNome());
            uf.setIdUsuario(usuario);
            uf.setAcesso(false);
            for (CadUsuarioFazendas uf2 : usuario.getListFazendas()) {
                if (uf.getIdFazenda().getCodigo() == uf2.getIdFazenda().getCodigo()) {
                    uf.setId(uf2.getId());
                    uf.setAcesso(uf2.getAcesso());
                }
            }
            listaUsuarioFazendas.add(uf);
        }
        return listaUsuarioFazendas;
    }

    private Boolean verificarBeans(CadUsuario usuario) {
        if (usuario.getNome().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Nome!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (usuario.getEmail().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Email!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (usuario.getLogin().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Login!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (usuario.getTelefone().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Telefone!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }

        if (!usuario.getSenha().equals(txt_senha2.getText()) || usuario.getSenha().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Senha!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }

        /*    if (UsuarioD.BuscarUsuario(usuario.getLogin()) != null) {
            JOptionPane.showMessageDialog(null, "Login já Existente!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }*/
        return true;
    }

    private void novo() {
        limparCampos();
        TbPermissoes.limpar();
        TbFazendas.limpar();
        TbFazendas.addLista(getListaFazendaUsuario());
        TbPermissoes.addLista(popularListaPermissoes());
        txt_nome.setEnabled(true);
        txt_login.setEnabled(true);
        txt_email.setEnabled(true);
        txt_telefone.setEnabled(true);
        txt_senha1.setEnabled(true);
        txt_senha2.setEnabled(true);
        btn_cadastrar.setEnabled(true);
        ch_status.setEnabled(true);
    }

    private void limparCampos() {
        TbPermissoes.limpar();
        TbFazendas.limpar();
        UsuarioB = new CadUsuario();
        txt_codigo.setText("");
        txt_nome.setText("");
        txt_login.setText("");
        txt_email.setText("");
        txt_telefone.setText("");
        txt_senha1.setText("");
        txt_senha2.setText("");
        ch_status.setSelected(false);
    }

    private void desabilitarCampos() {
        txt_nome.setEnabled(false);
        txt_email.setEnabled(false);
        txt_telefone.setEnabled(false);
        txt_senha1.setEnabled(false);
        txt_senha2.setEnabled(false);
        btn_cadastrar.setEnabled(false);
        btn_editar.setEnabled(false);
        btn_excluir.setEnabled(false);
        ch_status.setEnabled(false);
    }

}
