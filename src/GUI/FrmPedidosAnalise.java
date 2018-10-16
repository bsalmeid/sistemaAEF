/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Beans.FornecedoresBeans;
import Beans.PedidoAlmoxarifadoItens;
import Beans.PedidosAlmoxarifadoCotacao;
import Beans.PedidosAlmoxarifadoFechamentoItens;
import Beans.PedidosAlmoxarifadoSolicitacao;
import DAO.PedidosAlmoxarifadoDAO;
import static GUI.frmLogin.NomeUsuario;
import static GUI.frmLogin.dataAtual;
import Icones.FormatarICO;
import TableModel.TableModelPedidoMercadoria;
import TreeModel.AnaliseMercadoriaListener;
import TreeModel.TreeTableAnaliseMercadoria;
import TreeModel.TreeTableFechamento;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import Utilitarios.FrmProgressBar;
import Utilitarios.ThreadLoadProgressBar;
import Utilitarios.ValidarPermissoes;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;
import jxl.WorkbookSettings;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdesktop.swingx.JXTreeTable;

public class FrmPedidosAnalise extends javax.swing.JInternalFrame {

    TreeTableAnaliseMercadoria TreeTableAnalise;
    TreeTableFechamento TreeTableFechamento;
    List<PedidoAlmoxarifadoItens> listaItens;
    List<PedidosAlmoxarifadoFechamentoItens> listaFechamento;
    List<PedidosAlmoxarifadoCotacao> listaCotacao;
    public PedidosAlmoxarifadoSolicitacao solicitacao;
    List<FornecedoresBeans> listaFornecedores;
    PedidosAlmoxarifadoDAO PedidosD;
    ThreadLoadProgressBar thread;
    FrmProgressBar frm;

    public FrmPedidosAnalise() {
        initComponents();
        PedidosD = new PedidosAlmoxarifadoDAO();
        listaItens = new ArrayList<>();
        getTreeTableFechamento(getTreeTableModelFechamento());
        getTreeTableOperacao(getTreeTableModel());

    }

    private JXTreeTable getTreeTableOperacao(TreeTableAnaliseMercadoria treeModel) {
        table_analise.setTreeTableModel(treeModel);
        // ((DefaultTableCellRenderer) table_analise.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        CellRenderTabela renderer = new CellRenderTabela(table_analise);
        for (int c = 0; c < TreeTableAnalise.getColumnCount(); c++) {
            if (c == TreeTableAnalise.SETOR || c == TreeTableAnalise.ID_SOLICITACAO || c == TreeTableAnalise.STATUS_ITEM
                    || c == TreeTableAnalise.DATA || c == TreeTableAnalise.SOLICITANTE || c == TreeTableAnalise.ID_COMPRA
                    || c == TreeTableAnalise.INVENTARIO || c == TreeTableAnalise.ID_INVENTARIO
                    || c == TreeTableAnalise.ID_SOLICITANTE || c == TreeTableAnalise.ID_STATUS_ITEM
                    || c == TreeTableAnalise.ID_URGENCIA || c == TreeTableAnalise.URGENCIA) {

                table_analise.getColumnModel().getColumn(c).setPreferredWidth(0);
                table_analise.getColumnModel().getColumn(c).setMinWidth(0);
                table_analise.getColumnModel().getColumn(c).setMaxWidth(0);
            }
        }
        table_analise.getColumnModel().getColumn(TreeTableAnalise.ID).setMaxWidth(80);
        table_analise.getColumnModel().getColumn(TreeTableAnalise.ID_PEDIDO).setMaxWidth(80);
        table_analise.getColumnModel().getColumn(TreeTableAnalise.N_ITEM).setMaxWidth(80);
        table_analise.getColumnModel().getColumn(TreeTableAnalise.CODIGO).setMaxWidth(90);
        table_analise.getColumnModel().getColumn(TreeTableAnalise.DESCRICAO).setPreferredWidth(160);
        table_analise.getColumnModel().getColumn(TreeTableAnalise.DESCRICAO).setPreferredWidth(200);
        table_analise.getColumnModel().getColumn(TreeTableAnalise.INVENTARIO).setMaxWidth(100);
        table_analise.getColumnModel().getColumn(TreeTableAnalise.STATUS_ITEM).setMaxWidth(80);
        table_analise.getColumnModel().getColumn(TreeTableAnalise.ID_ITEM).setMaxWidth(80);
        table_analise.getColumnModel().getColumn(TreeTableAnalise.QUANTIDADE).setMaxWidth(100);
        table_analise.getColumnModel().getColumn(TreeTableAnalise.ID_SETOR).setMaxWidth(50);
        table_analise.getColumnModel().getColumn(TreeTableAnalise.UNIDADE).setMaxWidth(80);
        //table_analise.setTreeCellRenderer(renderer);
        for (int c = 0; c < TreeTableAnalise.getColumnCount(); c++) {
            table_analise.getColumnModel().getColumn(c).setCellRenderer(renderer);
        }

        AnaliseMercadoriaListener listeners = new AnaliseMercadoriaListener(table_analise, tb_fechamento);
        table_analise.addMouseListener(listeners);
        table_analise.addKeyListener(listeners);
        return table_analise;
    }

    public JXTreeTable getTreeTableFechamento(TreeTableFechamento treeModel) {
        tb_fechamento.setTreeTableModel(treeModel);
        CentralizarTabela Centralizar = new CentralizarTabela();
        Centralizar.centralizarTabela(table_analise);
        ajustarColuna();
        return tb_fechamento;
    }

    public void ajustarColuna() {
        for (int c = 0; c < TreeTableFechamento.getColumnCount(); c++) {
            if (c == TreeTableFechamento.ID_CADASTRO
                    || c == TreeTableFechamento.ID_ITEM
                    || c == TreeTableFechamento.ID_PEDIDO
                    || c == TreeTableFechamento.N_ITEM_PEDIDO) {
                tb_fechamento.getColumnModel().getColumn(c).setMaxWidth(0);
                tb_fechamento.getColumnModel().getColumn(c).setMinWidth(0);
                tb_fechamento.getColumnModel().getColumn(c).setPreferredWidth(0);
            }
        }
        tb_fechamento.getColumnModel().getColumn(TreeTableFechamento.ID_FORNECEDOR).setPreferredWidth(50);
        tb_fechamento.getColumnModel().getColumn(TreeTableFechamento.FORNECEDOR).setPreferredWidth(100);
        tb_fechamento.getColumnModel().getColumn(TreeTableFechamento.DESCRICAO).setPreferredWidth(120);
        tb_fechamento.getColumnModel().getColumn(TreeTableFechamento.CODIGO).setPreferredWidth(90);
        tb_fechamento.getColumnModel().getColumn(TreeTableFechamento.QUANTIDADE).setPreferredWidth(50);
        tb_fechamento.getColumnModel().getColumn(TreeTableFechamento.VALOR_UNIT).setPreferredWidth(80);
        tb_fechamento.getColumnModel().getColumn(TreeTableFechamento.VALOR_TOTAL).setPreferredWidth(80);

        for (int c = 0; c < TreeTableFechamento.getColumnCount(); c++) {
            tb_fechamento.getColumnModel().getColumn(c).setCellRenderer(new CellRendererFechamento());
        }

    }

    private TreeTableAnaliseMercadoria getTreeTableModel() {
        if (TreeTableAnalise == null) {
            TreeTableAnalise = new TreeTableAnaliseMercadoria(listaItens);
        }
        return TreeTableAnalise;
    }

    public TreeTableFechamento getTreeTableModelFechamento() {
        if (TreeTableFechamento == null) {
            TreeTableFechamento = new TreeTableFechamento();
        }
        return TreeTableFechamento;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopup = new javax.swing.JPopupMenu();
        jMenuContrair = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuExpandir = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuExportar = new javax.swing.JMenuItem();
        jPopup1 = new javax.swing.JPopupMenu();
        jMenuContrair1 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuExpandir1 = new javax.swing.JMenuItem();
        sc_tree_table = new javax.swing.JScrollPane();
        table_analise = new org.jdesktop.swingx.JXTreeTable();
        jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        txt_idSolicitacao = new javax.swing.JTextField();
        btn_pesqSolicitacao = new javax.swing.JButton();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        txt_data =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        btn_buscarAnalise = new javax.swing.JButton();
        ch_exibir = new javax.swing.JCheckBox();
        sc_tb_fechamento = new javax.swing.JScrollPane();
        tb_fechamento = new org.jdesktop.swingx.JXTreeTable();
        jPanel2 = new javax.swing.JPanel();
        btn_novo = new javax.swing.JButton();
        btn_salvar = new javax.swing.JButton();
        btn_editarPedido = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();

        jMenuContrair.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuContrair.setText("Contrair Tudo");
        jMenuContrair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuContrairActionPerformed(evt);
            }
        });
        jPopup.add(jMenuContrair);
        jPopup.add(jSeparator1);

        jMenuExpandir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuExpandir.setText("Expandir Tudo");
        jMenuExpandir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuExpandirActionPerformed(evt);
            }
        });
        jPopup.add(jMenuExpandir);
        jPopup.add(jSeparator3);

        jMenuExportar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuExportar.setText("Exportar Análise");
        jMenuExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuExportarActionPerformed(evt);
            }
        });
        jPopup.add(jMenuExportar);

        jMenuContrair1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuContrair1.setText("Contrair Tudo");
        jMenuContrair1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuContrair1ActionPerformed(evt);
            }
        });
        jPopup1.add(jMenuContrair1);
        jPopup1.add(jSeparator2);

        jMenuExpandir1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuExpandir1.setText("Expandir Tudo");
        jMenuExpandir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuExpandir1ActionPerformed(evt);
            }
        });
        jPopup1.add(jMenuExpandir1);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        table_analise.setCellSelectionEnabled(true);
        table_analise.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_analiseMouseClicked(evt);
            }
        });
        table_analise.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                table_analiseValueChanged(evt);
            }
        });
        sc_tree_table.setViewportView(table_analise);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Nº Solicitacao");

        txt_idSolicitacao.setEditable(false);
        txt_idSolicitacao.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_idSolicitacao.setToolTipText("");

        btn_pesqSolicitacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar pequeno.png"))); // NOI18N
        btn_pesqSolicitacao.setPreferredSize(new java.awt.Dimension(30, 25));
        btn_pesqSolicitacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesqSolicitacaoActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Data");

        btn_buscarAnalise.setText("Pesquisar");
        btn_buscarAnalise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarAnaliseActionPerformed(evt);
            }
        });

        ch_exibir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_exibir.setText("Exibir Selecionados");
        ch_exibir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ch_exibirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_data, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_idSolicitacao, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btn_pesqSolicitacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ch_exibir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_buscarAnalise, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(txt_data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txt_idSolicitacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesqSolicitacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_buscarAnalise)
                    .addComponent(ch_exibir))
                .addContainerGap())
        );

        sc_tb_fechamento.setVisible(ch_exibir.isSelected());

        tb_fechamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_fechamentoMouseClicked(evt);
            }
        });
        tb_fechamento.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tb_fechamentoPropertyChange(evt);
            }
        });
        sc_tb_fechamento.setViewportView(tb_fechamento);
        tb_fechamento.getAccessibleContext().setAccessibleParent(this);

        btn_novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/inserir_40x40.png"))); // NOI18N
        btn_novo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_novo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novoActionPerformed(evt);
            }
        });

        btn_salvar.setBackground(new java.awt.Color(255, 255, 255));
        btn_salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/salvar.png"))); // NOI18N
        btn_salvar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_salvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_salvar.setDisabledIcon(null);
        btn_salvar.setMaximumSize(new java.awt.Dimension(45, 45));
        btn_salvar.setMinimumSize(new java.awt.Dimension(45, 45));
        btn_salvar.setPreferredSize(new java.awt.Dimension(45, 45));
        btn_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salvarActionPerformed(evt);
            }
        });

        btn_editarPedido.setBackground(new java.awt.Color(255, 255, 255));
        btn_editarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/editar_pequeno.png"))); // NOI18N
        btn_editarPedido.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_editarPedido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_editarPedido.setEnabled(false);
        btn_editarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarPedidoActionPerformed(evt);
            }
        });

        btn_excluir.setBackground(new java.awt.Color(255, 255, 255));
        btn_excluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/deletar_40x40.png"))); // NOI18N
        btn_excluir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_excluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_excluir.setEnabled(false);
        btn_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_editarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_novo, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(btn_salvar, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(btn_editarPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(btn_excluir, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(sc_tree_table, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sc_tb_fechamento, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sc_tb_fechamento, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                    .addComponent(sc_tree_table))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_pesqSolicitacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesqSolicitacaoActionPerformed
        FrmPedidosSolicitacao frmSolicitacao = new FrmPedidosSolicitacao();
        this.getParent().add(frmSolicitacao);
        frmSolicitacao.setVisible(true);
        frmSolicitacao.jTabbedPane1.setSelectedIndex(2);
        frmSolicitacao.tb_consulta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int index = frmSolicitacao.tb_consulta.getSelectedRow();
                    Long idSolicitacao = (Long) frmSolicitacao.TbConsulta.getValueAt(index, frmSolicitacao.TbConsulta.ID_SOLICITACAO);
                    txt_idSolicitacao.setText(idSolicitacao.toString());
                    thread = new ThreadLoadProgressBar() {
                        @Override
                        public void run() {
                            solicitacao = PedidosD.buscarSolicitacaoPorCodigo(idSolicitacao, thread.dialog);
                            thread.dialog.dispose();
                            frmSolicitacao.dispose();
                        }
                    };
                }
            }
        });
    }//GEN-LAST:event_btn_pesqSolicitacaoActionPerformed

    private void btn_buscarAnaliseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarAnaliseActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (table_analise.getSelectedRowCount() > 0) {
            int buscar = JOptionPane.showConfirmDialog(null, "<html>Está Ação irá Alterar todos os dados Atuais,<b> Deseja Continuar? </html>", "Atenção", JOptionPane.YES_NO_OPTION);
            if (buscar == JOptionPane.YES_OPTION) {
                thread = new ThreadLoadProgressBar() {
                    @Override
                    public void run() {
                        carregarAnalise(thread.dialog);
                    }
                };
            }
        } else {
            thread = new ThreadLoadProgressBar() {
                @Override
                public void run() {
                    carregarAnalise(thread.dialog);
                }
            };
        }
        this.setCursor(null);
    }//GEN-LAST:event_btn_buscarAnaliseActionPerformed

    private void table_analiseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_analiseMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            if (jPopup.isVisible() == true) {
                jPopup.setVisible(false);
            } else {
                jPopup.setVisible(true);
                jPopup.show(this, 0, 0);
                jPopup.setLocation(evt.getLocationOnScreen());
            }
        }
    }//GEN-LAST:event_table_analiseMouseClicked

    private void table_analiseValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_table_analiseValueChanged

    }//GEN-LAST:event_table_analiseValueChanged

    private void ch_exibirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ch_exibirActionPerformed
        sc_tb_fechamento.setVisible(ch_exibir.isSelected());
        this.revalidate();
        this.repaint();
    }//GEN-LAST:event_ch_exibirActionPerformed

    private void tb_fechamentoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tb_fechamentoPropertyChange
        int row = tb_fechamento.getSelectedRow();
        int column = tb_fechamento.getSelectedColumn();
        if (column == TreeTableFechamento.QUANTIDADE) {
            TreePath pathItem = tb_fechamento.getPathForRow(row);
            if (pathItem != null) {
                if (pathItem.getLastPathComponent() instanceof PedidosAlmoxarifadoFechamentoItens) {
                    PedidosAlmoxarifadoFechamentoItens item = (PedidosAlmoxarifadoFechamentoItens) pathItem.getLastPathComponent();
                    if (!tb_fechamento.getValueAt(row, column).equals("")) {
                        TreeTableFechamento.setValueAt(tb_fechamento.getValueAt(row, column), item, TreeTableFechamento.QUANTIDADE);
                        double valor_total = item.getValor_unit() * item.getQuantidade();
                        TreeTableFechamento.setValueAt(valor_total, item, TreeTableFechamento.VALOR_TOTAL);

                    } else {
                        JOptionPane.showMessageDialog(null, "A Quantidade deve ser maior ou igual a 0", "Erro", 0, FormatarICO.ICObtnSair());
                    }
                }
            }
        }

    }//GEN-LAST:event_tb_fechamentoPropertyChange

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed


    }//GEN-LAST:event_btn_novoActionPerformed

    private void btn_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(null, "Deseja Salvar Está Analise", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION && ValidarPermissoes.validarPermissaoInsert(FrmPedidosAnalise.class.getSimpleName())) {
            thread = new ThreadLoadProgressBar() {
                @Override
                public void run() {
                    if (PedidosD.salvarAnalise(getListaCotacao(), solicitacao, thread.dialog)) {
                    }
                }
            };
        }
    }//GEN-LAST:event_btn_salvarActionPerformed

    private void btn_editarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarPedidoActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja Editar esta Analise?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION) {

        }
    }//GEN-LAST:event_btn_editarPedidoActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        int excluirSaidaInsumos = JOptionPane.showConfirmDialog(null, "Atenção, Está Ação Excluíra Permanentemente este Registro, Deseja Continuar", "Atenção", JOptionPane.YES_NO_OPTION);
        if (excluirSaidaInsumos == JOptionPane.YES_OPTION) {
            if (true == true) {

            }
        }
    }//GEN-LAST:event_btn_excluirActionPerformed

    private void jMenuContrairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuContrairActionPerformed
        ContrairArvore(table_analise);
    }//GEN-LAST:event_jMenuContrairActionPerformed

    private void tb_fechamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_fechamentoMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            if (jPopup1.isVisible() == true) {
                jPopup1.setVisible(false);
            } else {
                jPopup1.setVisible(true);
                jPopup1.show(this, 0, 0);
                jPopup1.setLocation(evt.getLocationOnScreen());
            }
        }
    }//GEN-LAST:event_tb_fechamentoMouseClicked

    private void jMenuContrair1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuContrair1ActionPerformed
        ContrairArvore(tb_fechamento);
    }//GEN-LAST:event_jMenuContrair1ActionPerformed

    private void jMenuExpandir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuExpandir1ActionPerformed
        ExpandirArvore(tb_fechamento);
    }//GEN-LAST:event_jMenuExpandir1ActionPerformed

    private void jMenuExpandirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuExpandirActionPerformed
        ExpandirArvore(table_analise);
    }//GEN-LAST:event_jMenuExpandirActionPerformed

    private void jMenuExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuExportarActionPerformed
        exportarPlanilha(TreeTableAnalise, solicitacao.getId());
    }//GEN-LAST:event_jMenuExportarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_buscarAnalise;
    javax.swing.JButton btn_editarPedido;
    javax.swing.JButton btn_excluir;
    javax.swing.JButton btn_novo;
    javax.swing.JButton btn_pesqSolicitacao;
    javax.swing.JButton btn_salvar;
    private javax.swing.JCheckBox ch_exibir;
    private javax.swing.JMenuItem jMenuContrair;
    private javax.swing.JMenuItem jMenuContrair1;
    private javax.swing.JMenuItem jMenuExpandir;
    private javax.swing.JMenuItem jMenuExpandir1;
    private javax.swing.JMenuItem jMenuExportar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopup;
    private javax.swing.JPopupMenu jPopup1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    public javax.swing.JScrollPane sc_tb_fechamento;
    private javax.swing.JScrollPane sc_tree_table;
    public org.jdesktop.swingx.JXTreeTable table_analise;
    public org.jdesktop.swingx.JXTreeTable tb_fechamento;
    private com.toedter.calendar.JDateChooser txt_data;
    public javax.swing.JTextField txt_idSolicitacao;
    // End of variables declaration//GEN-END:variables

    private void carregarAnalise(FrmProgressBar frm) {
        if (solicitacao != null) {
            listaItens = PedidosD.buscarSolicitacaoPorCodigo(solicitacao.getId(), frm).getListaSolicitacao();
            listaCotacao = PedidosD.ConsultaCotacoes(solicitacao.getId(), frm);
            PedidosAlmoxarifadoCotacao cotacao;
            PedidoAlmoxarifadoItens item;
            List<PedidosAlmoxarifadoCotacao> listaParcialCotacao;
            for (int i = 0; i < listaItens.size(); i++) {
                item = listaItens.get(i);
                listaParcialCotacao = new ArrayList<>();
                for (int j = 0; j < listaCotacao.size(); j++) {
                    cotacao = listaCotacao.get(j);
                    if (Objects.equals(item.getId(), cotacao.getIdItemPedido().getId())) {
                        listaParcialCotacao.add(cotacao);
                        if (cotacao.isItem_selecionado() == true) { // verificar se alguma cotaçao é selecionada para marcar selecionado o item
                            item.setSelected(true);
                        }
                        if (cotacao.isItem_comprado() == true) { // se o item já estiver sido comprado, diminuir a quantidade
                            item.setQuantidade(item.getQuantidade() - cotacao.getQuant_fechamento());
                        }
                    }
                }
                frm.setProgressBar(false, 0, listaCotacao.size() * 100, "Processando Cotações");
                item.setListaCotacoes(listaParcialCotacao);
            }
            frm.setString("Preenchendo Árvore de Cotações");
            frm.setProgressBar(false, 0, listaItens.size() * 1000, "Processando Cotações");
            frm.setValue(0);
            frm.setIndeterminate(true);

            TreeTableFechamento = new TreeTableFechamento(carregarFornecedoresNova(listaItens), tb_fechamento);
            TreeTableAnalise = new TreeTableAnaliseMercadoria(listaItens);
            getTreeTableFechamento(TreeTableFechamento);
            getTreeTableOperacao(TreeTableAnalise);
            frm.dispose();
        } else {
            frm.dispose();
            JOptionPane.showMessageDialog(null, "Selecione uma Solicitação!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }

    private List<PedidosAlmoxarifadoCotacao> getListaCotacao() {
        List<PedidosAlmoxarifadoCotacao> listaFinal = new ArrayList<>();
        listaItens = TreeTableAnalise.getListPrincipal();
        for (int i = 0; i < listaItens.size(); i++) {
            List<PedidosAlmoxarifadoCotacao> listaCotacao = listaItens.get(i).getListaCotacoes();
            for (PedidosAlmoxarifadoCotacao cotacao : listaCotacao) {
                listaFinal.add(cotacao);
            }
        }
        return listaFinal;
    }

    private List<FornecedoresBeans> carregarFornecedoresNova(List<PedidoAlmoxarifadoItens> listaItens) {
        listaFornecedores = new ArrayList<>();
        for (PedidoAlmoxarifadoItens item : listaItens) {
            //System.out.println("ITEM: " + item.getCodigo());
            List<PedidosAlmoxarifadoCotacao> listaCotacao = item.getListaCotacoes();
            for (PedidosAlmoxarifadoCotacao cotacao : listaCotacao) {
                //System.out.println("Cotacao: " + cotacao.getFornecedor());
                FornecedoresBeans f = new FornecedoresBeans(cotacao.getIdFornecedor(), cotacao.getFornecedor()); // instancia um novo fornecedore com base na cotacao
                f.setValorTotalFechamento(0.00);
                if (!listaFornecedores.contains(f)) { // verifica se nao tem fornecedor na lista para adicionar
                   // System.out.println("ID : " + f.getID() + " - " + f.getNomeFantasia());
                    List<PedidosAlmoxarifadoFechamentoItens> listFechamento = new ArrayList<>();
                    f.setListaFechamento(listFechamento);
                    listaFornecedores.add(f);
                }
                if (cotacao.isItem_selecionado() == true) {
                    for (FornecedoresBeans f2 : listaFornecedores) {
                        if (f2.getID() == cotacao.getIdFornecedor()) {
                            PedidosAlmoxarifadoFechamentoItens fechamento = new PedidosAlmoxarifadoFechamentoItens();
                            fechamento.setIdCotacao(cotacao);
                            fechamento.setCodigo(item.getCodigo());
                            fechamento.setDescricao(item.getDescricao());
                            fechamento.setMarca(cotacao.getMarcaPeca());
                            fechamento.setFornecedor(cotacao.getFornecedor());
                            fechamento.setID_Fornecedor(cotacao.getIdFornecedor());
                            fechamento.setIdCadastro(item.getIdItem());
                            fechamento.setId_item(cotacao.getIdItemPedido());
                            fechamento.setId_pedido(item.getIdPedido().getId());
                            fechamento.setN_item_pedido(item.getnItem());
                            fechamento.setQuantidade(cotacao.getQuant_fechamento() > 0 ? cotacao.getQuant_fechamento() : item.getQuantidade());
                            fechamento.setStatus_fechamento(false);
                            fechamento.setValor_unit(cotacao.getValorUnit());
                            fechamento.setValor_total(fechamento.getQuantidade() * cotacao.getValorUnit()); // Usa a Quantidade jà definida pela Lógica do Fechamento
                            f2.setValorTotalFechamento(f2.getValorTotalFechamento() + fechamento.getValor_total());
                            f2.getListaFechamento().add(fechamento);
                            break;
                        }
                    }
                }
            }
        }
        return listaFornecedores;
    }

    private void ExpandirArvore(JXTreeTable jTreeTable) {
        jTreeTable.expandAll();

    }

    private void ContrairArvore(JXTreeTable jTreeTable) {
        jTreeTable.collapseAll();

    }

    public Boolean exportarPlanilha(TreeTableAnaliseMercadoria model, Long solicitacao) {
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel File", "xlsx", "XLSX");
        fc.setDialogTitle("Selecione o Local do Arquivo");
        fc.setFileFilter(filter);
        fc.setSelectedFile(new File("Análise Solicitação Nº " + solicitacao));
        int option = fc.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            String fileName = fc.getSelectedFile().getAbsolutePath();
            try {
                WorkbookSettings conf = new WorkbookSettings();
                conf.setEncoding("ISO-8859-1");
                File caminhoCanonical = Corretores.ValidarArquivoModelo("/src/Arquivos/Planilhas_Modelo/model_analise.xlsx");
                XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(caminhoCanonical));
                XSSFSheet planilha = workbook.getSheetAt(0);
                XSSFSheet planilhaCotacao = workbook.getSheetAt(1);

                XSSFRow rowDados = planilha.getRow(1);
                XSSFRow row2 = planilha.getRow(2);
                XSSFCell cellSolicitacao = rowDados.getCell(0);
                cellSolicitacao.setCellValue("Análise Solic - Nº " + solicitacao);
                popularPlanilha(model, planilha, planilhaCotacao);

                workbook.write(new FileOutputStream(fileName + ".xlsx"));
                JOptionPane.showMessageDialog(null, "Planilha Criada com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
            } catch (IOException ex) {
                Logger.getLogger(frmImportarFolhaPagto.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex + " Erro ao Criar Planilha!", "Erro", 1, FormatarICO.ICObtnSair());
            }
        }
        return true;
    }

    public void popularPlanilha(TreeTableAnaliseMercadoria model, XSSFSheet planilha, XSSFSheet planilhaCotacao) {
        List<PedidoAlmoxarifadoItens> itens = model.getListPrincipal();
        int r = 2;
        for (int i = 3; i < itens.size() + 3; i++) {
            PedidoAlmoxarifadoItens item = itens.get(i - 3);
            XSSFRow row = planilha.getRow(i);
            popularLinhaItem(model, row, item);

            List<PedidosAlmoxarifadoCotacao> cotacoes = item.getListaCotacoes();
            for (int y = 0; y < cotacoes.size(); y++) {
                PedidosAlmoxarifadoCotacao cot = cotacoes.get(y);
                XSSFRow rowCot = planilhaCotacao.createRow(r);

                XSSFCell id_cot = rowCot.createCell(0);
                XSSFCell id_item = rowCot.createCell(1);
                XSSFCell id_cadastro = rowCot.createCell(2);
                XSSFCell id_fornecedor = rowCot.createCell(3);
                XSSFCell fornecedor = rowCot.createCell(4);
                XSSFCell descricao = rowCot.createCell(5);
                XSSFCell codigo = rowCot.createCell(6);
                XSSFCell itemOriginal = rowCot.createCell(7);
                XSSFCell marca = rowCot.createCell(8);
                XSSFCell vlrUnit = rowCot.createCell(9);
                XSSFCell itemComprado = rowCot.createCell(10);
                XSSFCell quantComprado = rowCot.createCell(11);

                id_cot.setCellValue(Double.parseDouble(cot.getId().toString()));
                id_item.setCellValue(Double.parseDouble(cot.getIdItemPedido().getId().toString()));
                id_cadastro.setCellValue(Double.parseDouble(String.valueOf(cot.getIdItemPedido().getIdItem())));
                id_fornecedor.setCellValue(Double.parseDouble(cot.getIdFornecedor().toString()));
                fornecedor.setCellValue(cot.getFornecedor());
                descricao.setCellValue(cot.getIdItemPedido().getDescricao());
                codigo.setCellValue(cot.getIdItemPedido().getCodigo());
                itemOriginal.setCellValue(cot.getItem_original() ? "Sim" : "Não");
                marca.setCellValue(cot.getMarcaPeca());
                vlrUnit.setCellValue(cot.getValorUnit());
                itemComprado.setCellValue(cot.isItem_comprado() ? "Sim" : "Não");
                quantComprado.setCellValue(cot.getQuant_fechamento());
                r += 1;
            }
        }
    }

    private void popularLinhaItem(TreeTableAnaliseMercadoria model, XSSFRow row, PedidoAlmoxarifadoItens item) {
        for (int c = 0; c < model.getColumnCount(); c++) {
            XSSFCell cell = row.getCell(c);
            switch (c) {
                case 0: // ID Item
                    cell.setCellValue(Double.parseDouble(item.getId().toString()));
                    cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                    break;
                case 1: // Nº Pedido
                    cell.setCellValue(Double.parseDouble(item.getIdPedido().getId().toString())); // ID Cadastrado
                    cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                    break;
                case 2: // Nº Item
                    cell.setCellValue(Double.parseDouble(String.valueOf(item.getnItem())));
                    cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                    break;
                case 3: // ID Cadastro
                    cell.setCellValue(Double.parseDouble(String.valueOf(item.getIdItem())));
                    cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                    break;
                case 4: // Descricao
                    cell.setCellValue(item.getDescricao());
                    //cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                    break;
                case 5: // Código
                    cell.setCellValue(item.getCodigo());
                    break;
                case 6: // Quantidade
                    cell.setCellValue(item.getQuantidade());
                    cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                    break;
                case 7: // Fornecedor
                    for (PedidosAlmoxarifadoCotacao cot : item.getListaCotacoes()) {
                        if (cot.isItem_selecionado()) {
                            cell.setCellValue(cot.getFornecedor());
                            XSSFCell cellMarca = row.getCell(8);
                            XSSFCell cellVlr = row.getCell(9);
                            cellMarca.setCellValue(cot.getMarcaPeca());
                            cellVlr.setCellValue(cot.getValorUnit());
                        }
                    }
                    break;
                case 8: // Marca

                    break;

                default:
            }
        }
    }

    private class CellRenderTabela extends JCheckBox implements TableCellRenderer, TreeCellRenderer {

        JCheckBox check;
        JLabel label;
        JXTreeTable tree_table;

        public CellRenderTabela(JXTreeTable tree_table) {
            label = new JLabel();
            label.setEnabled(false);
            this.tree_table = tree_table;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelect, boolean bln1, int row, int column) {
            //Component renderer = getTableCellRendererComponent(table, value, isSelect, bln1, row, column);
            check = new JCheckBox();
            label = new JLabel();
            label.setOpaque(true);

            try {
                String text = table.getValueAt(row, column).toString();
                label.setForeground(table.getForeground());
                label.setBackground(table.getBackground());
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setText(text != null ? text : "");
            } catch (Exception e) {
            }

            if (table.getValueAt(row, 0) instanceof Long) {
                try {
                    if (((Long) table.getValueAt(row, 1)) > 0) {
                        label.setForeground(Color.black);
                        label.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 11));
                        label.setBackground(new Color(255, 222, 163, 255));
                        label.add(check);
                    }
                } catch (Exception e) {
                }
            }

            Object obj = table.getValueAt(row, 11);
            if (column == 11 && obj instanceof Boolean) {
                Boolean val = (Boolean) obj;
                check.setOpaque(true);
                check.setSelected(val);

                return check;
            } else if (column == 11 && !(obj instanceof Boolean)) {
                label.setText("");
            }

            if (obj instanceof Boolean) {
                Boolean val = (Boolean) obj;
                if (val == true && column != 11) {
                    label.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 11));

                    if (table.getValueAt(row, 0) instanceof Long) {

                    } else {
                        label.setBackground(Color.green);
                    }

                    if (isSelect) {
                        label.setBackground(table.getSelectionBackground());
                    }
                    return label;
                }
            }

            if (isSelect) {
                label.setBackground(table.getSelectionBackground());
            }

            return label;
        }

        @Override
        public Component getTreeCellRendererComponent(JTree jtree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            if (value instanceof PedidoAlmoxarifadoItens) {
                PedidoAlmoxarifadoItens item = (PedidoAlmoxarifadoItens) value;
                label.setOpaque(true);//adding this line I solved my problem
                label.setBackground(Color.GREEN);
                //tree_table.setClosedIcon(FormatarICO.IconeTreTableAnaliseMercadoria());
                label.setText(item.getId() > 0 ? item.getId().toString() : "");
            } else {
                label.setText(null);
            }
            return label;
        }

    }

    private class CellRendererFechamento implements TableCellRenderer {

        JLabel label;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object o, boolean isSelected, boolean hasFocus, int row, int column) {
            label = new JLabel();
            label.setOpaque(true);

            try {
                String text = table.getValueAt(row, column).toString();
                label.setForeground(table.getForeground());
                label.setBackground(table.getBackground());
                label.setHorizontalAlignment(SwingConstants.CENTER);
                if (column == 10 || column == 11) {
                    if (table.getValueAt(row, column) instanceof Double) {
                        Double valor = (Double) table.getValueAt(row, column);
                        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
                        label.setText(valor != null ? nf.format(valor) : "");
                    }
                } else {
                    label.setText(text != null ? text : "");
                }
            } catch (Exception e) {
            }

            if (table.getValueAt(row, 0) instanceof Integer) {
                try {
                    if (((Integer) table.getValueAt(row, 0)) > 0) {
                        label.setForeground(Color.black);
                        label.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 11));
                        label.setBackground(new Color(255, 222, 163, 255));
                        return label;
                    }
                } catch (Exception e) {
                }
            }
            if (isSelected) {
                label.setBackground(table.getSelectionBackground());
                return label;
            }
            return label;
        }
    }

}
