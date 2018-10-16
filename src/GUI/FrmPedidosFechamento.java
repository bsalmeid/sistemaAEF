/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Beans.PedidosAlmoxarifadoFechamento;
import Beans.PedidosAlmoxarifadoSolicitacao;
import DAO.PedidosAlmoxarifadoDAO;
import static GUI.frmLogin.IdUsuario;
import static GUI.frmLogin.dataAtual;
import Icones.FormatarICO;
import TableModel.TableModelPedidosFechamento;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import Utilitarios.FrmProgressBar;
import Utilitarios.ThreadLoadProgressBar;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import jxl.WorkbookSettings;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author agroa
 */
public class FrmPedidosFechamento extends javax.swing.JInternalFrame {

    TableModelPedidosFechamento TbItens;
    TableModelPedidosFechamento TbConsulta;
    PedidosAlmoxarifadoSolicitacao solicitacao;
    PedidosAlmoxarifadoDAO PedidosD;
    ThreadLoadProgressBar thread;
    FrmProgressBar frm;
    PedidosAlmoxarifadoFechamento fechamentoB;

    public FrmPedidosFechamento() {
        initComponents();
        PedidosD = new PedidosAlmoxarifadoDAO();
        cerregarTabela();
        cerregarTabelaConsulta();
        jMenuEnviarRemessa.setVisible(false);
        
    }

    private JTable cerregarTabela() {
        tb_fechamento.setModel(getTableModel());
        ((DefaultTableCellRenderer) tb_fechamento.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        CentralizarTabela Centralizar = new CentralizarTabela();
        CellRenderTabela renderer = new CellRenderTabela();
        Centralizar.centralizarTabela(tb_fechamento);

        for (int c = 0; c < TbItens.getColumnCount(); c++) {
            if (c == TbItens.ID || c == TbItens.ID_FORNECEDOR || c == TbItens.ID_COTACAO
                    || c == TbItens.PEDIDO_ALMOXARIFADO_ITEM || c == TbItens.FORNECEDOR) {
                tb_fechamento.getColumnModel().getColumn(c).setMaxWidth(0);
                tb_fechamento.getColumnModel().getColumn(c).setMinWidth(0);
                tb_fechamento.getColumnModel().getColumn(c).setPreferredWidth(0);
            }
        }
        tb_fechamento.getColumnModel().getColumn(TbItens.ID_PEDIDO).setPreferredWidth(70);
        tb_fechamento.getColumnModel().getColumn(TbItens.ID_CADASTRO).setPreferredWidth(70);
        tb_fechamento.getColumnModel().getColumn(TbItens.N_ITEM_PEDIDO).setPreferredWidth(70);
        tb_fechamento.getColumnModel().getColumn(TbItens.DESCRICAO).setPreferredWidth(230);
        tb_fechamento.getColumnModel().getColumn(TbItens.STATUS_FECHAMENTO).setPreferredWidth(50);
        tb_fechamento.getColumnModel().getColumn(15).setCellRenderer(renderer);
        tb_fechamento.getColumnModel().getColumn(16).setCellRenderer(renderer);
        return tb_fechamento;
    }

    private JTable cerregarTabelaConsulta() {
        tb_consulta.setModel(getTableModelConsulta());
        ((DefaultTableCellRenderer) tb_consulta.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        CentralizarTabela Centralizar = new CentralizarTabela();
        CellRenderTabela renderer = new CellRenderTabela();
        Centralizar.centralizarTabela(tb_consulta);

        for (int c = 0; c < TbItens.getColumnCount(); c++) {
            if (c == TbItens.ID || c == TbItens.ID_FORNECEDOR || c == TbItens.ID_COTACAO
                    || c == TbItens.PEDIDO_ALMOXARIFADO_ITEM) {
                tb_consulta.getColumnModel().getColumn(c).setMaxWidth(0);
                tb_consulta.getColumnModel().getColumn(c).setMinWidth(0);
                tb_consulta.getColumnModel().getColumn(c).setPreferredWidth(0);
            }
        }
        tb_consulta.getColumnModel().getColumn(TbItens.ID_PEDIDO).setPreferredWidth(70);
        tb_consulta.getColumnModel().getColumn(TbItens.ID_CADASTRO).setPreferredWidth(70);
        tb_consulta.getColumnModel().getColumn(TbItens.N_ITEM_PEDIDO).setPreferredWidth(70);
        tb_consulta.getColumnModel().getColumn(TbItens.DESCRICAO).setPreferredWidth(230);
        tb_consulta.getColumnModel().getColumn(TbItens.STATUS_FECHAMENTO).setPreferredWidth(50);
        tb_consulta.getColumnModel().getColumn(15).setCellRenderer(renderer);
        tb_consulta.getColumnModel().getColumn(16).setCellRenderer(renderer);
        return tb_consulta;
    }

    private TableModelPedidosFechamento getTableModel() {
        if (TbItens == null) {
            TbItens = new TableModelPedidosFechamento();
        }
        return TbItens;
    }

    private TableModelPedidosFechamento getTableModelConsulta() {
        if (TbConsulta == null) {
            TbConsulta = new TableModelPedidosFechamento();
        }
        return TbConsulta;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupConsulta = new javax.swing.JPopupMenu();
        jMenuEditar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuExportar = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuEnviarRemessa = new javax.swing.JMenuItem();
        jPopupGerar = new javax.swing.JPopupMenu();
        jMenuExcluirItem = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        javax.swing.JPanel jPanel3 = new javax.swing.JPanel();
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        txt_idSolicitacao = new javax.swing.JTextField();
        btn_pesqSolicitacao = new javax.swing.JButton();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        txt_idFornecedor = new javax.swing.JTextField();
        btn_pesqFornecedor = new javax.swing.JButton();
        txt_nomeFantasia = new javax.swing.JTextField();
        btn_pesquisar = new javax.swing.JButton();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        txt_data =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        ch_emitido = new javax.swing.JCheckBox();
        ch_naoEmitido = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_fechamento = new javax.swing.JTable();
        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        btn_novo = new javax.swing.JButton();
        btn_salvar = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        txt_totalProduto = new javax.swing.JTextField();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        txt_totalPedido = new javax.swing.JTextField();
        javax.swing.JLabel jLabel13 = new javax.swing.JLabel();
        txt_desconto = new javax.swing.JTextField();
        btn_repetirValor = new javax.swing.JButton();
        javax.swing.JPanel jPanel4 = new javax.swing.JPanel();
        javax.swing.JPanel jPanel5 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel14 = new javax.swing.JLabel();
        txt_dtInicial =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel15 = new javax.swing.JLabel();
        txt_dtFinal =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel16 = new javax.swing.JLabel();
        txt_idSolicitacaoConsulta = new javax.swing.JTextField();
        javax.swing.JLabel jLabel17 = new javax.swing.JLabel();
        txt_idFornecedorConsulta = new javax.swing.JTextField();
        btn_pesqFornecedor1 = new javax.swing.JButton();
        javax.swing.JLabel jLabel18 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        javax.swing.JLabel jLabel19 = new javax.swing.JLabel();
        txt_idPedido = new javax.swing.JTextField();
        btn_buscarFechamentos = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_consulta = new javax.swing.JTable();

        jMenuEditar.setText("Editar Fechamento");
        jMenuEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEditarActionPerformed(evt);
            }
        });
        jPopupConsulta.add(jMenuEditar);
        jPopupConsulta.add(jSeparator1);

        jMenuExportar.setText("Exportar Planilha");
        jMenuExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuExportarActionPerformed(evt);
            }
        });
        jPopupConsulta.add(jMenuExportar);
        jPopupConsulta.add(jSeparator2);

        jMenuEnviarRemessa.setText("Enviar p/ Remessa");
        jPopupConsulta.add(jMenuEnviarRemessa);

        jMenuExcluirItem.setText("Excluir Item");
        jMenuExcluirItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuExcluirItemActionPerformed(evt);
            }
        });
        jPopupGerar.add(jMenuExcluirItem);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Finalizar Compra");

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Nº Solicitacao");

        txt_idSolicitacao.setEditable(false);
        txt_idSolicitacao.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_idSolicitacao.setToolTipText("");

        btn_pesqSolicitacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar pequeno.png"))); // NOI18N
        btn_pesqSolicitacao.setEnabled(false);
        btn_pesqSolicitacao.setPreferredSize(new java.awt.Dimension(30, 25));
        btn_pesqSolicitacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesqSolicitacaoActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Fornecedor");

        txt_idFornecedor.setEditable(false);
        txt_idFornecedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btn_pesqFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar pequeno.png"))); // NOI18N
        btn_pesqFornecedor.setEnabled(false);
        btn_pesqFornecedor.setPreferredSize(new java.awt.Dimension(30, 25));
        btn_pesqFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesqFornecedorActionPerformed(evt);
            }
        });

        txt_nomeFantasia.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        btn_pesquisar.setText("Pesquisar");
        btn_pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisarActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Data");

        txt_data.setEnabled(false);

        buttonGroup1.add(ch_emitido);
        ch_emitido.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_emitido.setSelected(true);
        ch_emitido.setText("Emitido");

        buttonGroup1.add(ch_naoEmitido);
        ch_naoEmitido.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_naoEmitido.setText("Não Emitido");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_data, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_idSolicitacao, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btn_pesqSolicitacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_idFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btn_pesqFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nomeFantasia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ch_emitido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ch_naoEmitido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel9)
                    .addComponent(txt_idSolicitacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesqSolicitacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txt_idFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesqFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesquisar)
                    .addComponent(jLabel10)
                    .addComponent(txt_data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ch_naoEmitido)
                    .addComponent(ch_emitido))
                .addContainerGap())
        );

        tb_fechamento.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tb_fechamento);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        btn_editar.setBackground(new java.awt.Color(255, 255, 255));
        btn_editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/editar_pequeno.png"))); // NOI18N
        btn_editar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_editar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_editar.setEnabled(false);
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
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

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Valor Total dos Produtos");

        txt_totalProduto.setEditable(false);
        txt_totalProduto.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_totalProduto.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Valor do Pedido");

        txt_totalPedido.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_totalPedido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_totalPedido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_totalPedidoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_totalPedidoFocusLost(evt);
            }
        });
        txt_totalPedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_totalPedidoKeyReleased(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("% Desc.");

        txt_desconto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_desconto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_descontoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_descontoFocusLost(evt);
            }
        });
        txt_desconto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_descontoKeyReleased(evt);
            }
        });

        btn_repetirValor.setBackground(new java.awt.Color(255, 255, 255));
        btn_repetirValor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/refresh.png"))); // NOI18N
        btn_repetirValor.setBorder(null);
        btn_repetirValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_repetirValorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_totalProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_totalPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_repetirValor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_desconto, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_excluir)
                    .addComponent(btn_editar)
                    .addComponent(btn_salvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_novo)
                    .addComponent(txt_desconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(btn_repetirValor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_totalPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txt_totalProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Criar Fechamento", jPanel3);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Data");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("a");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Nº Solicitacao");

        txt_idSolicitacaoConsulta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_idSolicitacaoConsulta.setToolTipText("");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Fornecedor");

        txt_idFornecedorConsulta.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btn_pesqFornecedor1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar pequeno.png"))); // NOI18N
        btn_pesqFornecedor1.setPreferredSize(new java.awt.Dimension(30, 25));
        btn_pesqFornecedor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesqFornecedor1ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Código");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Nº Pedido");

        btn_buscarFechamentos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_buscarFechamentos.setText("Pesquisar");
        btn_buscarFechamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarFechamentosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_dtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_idSolicitacaoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_idFornecedorConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btn_pesqFornecedor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_idPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_buscarFechamentos, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel14)
                    .addComponent(txt_dtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txt_dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txt_idSolicitacaoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(txt_idFornecedorConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesqFornecedor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(txt_idPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_buscarFechamentos))
                .addContainerGap())
        );

        tb_consulta.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_consulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_consultaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_consulta);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Consultar", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
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

    private void btn_pesqFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesqFornecedorActionPerformed
        frmCadFornecedores fornecedor = new frmCadFornecedores();
        this.getParent().add(fornecedor);
        fornecedor.btn_Salvar.setEnabled(false);
        fornecedor.btn_novo1.setEnabled(false);
        fornecedor.getRootPane().setDefaultButton(fornecedor.btn_pesquisarCompra);
        fornecedor.setVisible(true);
        fornecedor.tb_fornecedores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    int index = fornecedor.tb_fornecedores.getSelectedRow();
                    String CNPJ = fornecedor.TbForn.getValueAt(index, 7).toString();
                    txt_idFornecedor.setText(fornecedor.TbForn.getValueAt(index, 0).toString());
                    txt_nomeFantasia.setText(fornecedor.TbForn.getValueAt(index, 1).toString()
                            + " - " + fornecedor.TbForn.getValueAt(index, 2).toString());
                    fornecedor.dispose();
                }
            }
        });
    }//GEN-LAST:event_btn_pesqFornecedorActionPerformed

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed
        novo();
    }//GEN-LAST:event_btn_novoActionPerformed

    private void btn_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(null, "Deseja Salvar Este Fechamento", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION) {
            fechamentoB = new PedidosAlmoxarifadoFechamento();
            popularBeans(fechamentoB);
            if (verificarBeans(fechamentoB)) {
                thread = new ThreadLoadProgressBar() {
                    @Override
                    public void run() {
                        if (PedidosD.salvarFechamento(fechamentoB, thread.dialog)) {
                            limparCampos();
                            desabilitarCampos();
                        }
                    }
                };
            }
        }
    }//GEN-LAST:event_btn_salvarActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja Editar este Fechamneto", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION) {
            popularBeans(fechamentoB);
            if (verificarBeans(fechamentoB)) {
                thread = new ThreadLoadProgressBar() {
                    @Override
                    public void run() {
                        if (PedidosD.EditarFechamento(fechamentoB, thread.dialog)) {
                            limparCampos();
                            desabilitarCampos();
                        }
                    }
                };
            }
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        int excluirSaidaInsumos = JOptionPane.showConfirmDialog(null, "Atenção, Está Ação Excluíra Permanentemente este Registro, Deseja Continuar", "Atenção", JOptionPane.YES_NO_OPTION);
        if (excluirSaidaInsumos == JOptionPane.YES_OPTION) {
            if (true == true) {

            }
        }
    }//GEN-LAST:event_btn_excluirActionPerformed

    private void btn_pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisarActionPerformed
        if (!txt_idSolicitacao.getText().equals("") && !txt_idFornecedor.getText().equals("")) {
            thread = new ThreadLoadProgressBar() {
                @Override
                public void run() {
                    TbItens.limpar();
                    TbItens.addLista(PedidosD.ConsultaFechamento(solicitacao.getId(), new Integer(txt_idFornecedor.getText()), thread.dialog));
                    somarTabela();
                }
            };
        } else {
            JOptionPane.showMessageDialog(null, "Os Campos Solicitaçao e Fornecedor são Obrigatórios!", "Erro", 0, FormatarICO.ICObtnSair());
        }

    }//GEN-LAST:event_btn_pesquisarActionPerformed

    private void tb_fechamentoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tb_fechamentoPropertyChange
        somarTabela();
    }//GEN-LAST:event_tb_fechamentoPropertyChange

    private void txt_descontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_descontoKeyReleased
        double desc = Corretores.ConverterStringDouble(txt_desconto.getText().replace(".", ",")) / 100;
        double valorProdutos = Corretores.ConverterStringDouble(txt_totalProduto.getText());
        txt_totalPedido.setText(Corretores.ConverterDoubleReais((1 - desc) * valorProdutos));
    }//GEN-LAST:event_txt_descontoKeyReleased

    private void btn_repetirValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_repetirValorActionPerformed
        txt_totalPedido.setText(txt_totalProduto.getText());
        txt_desconto.setText(new DecimalFormat("#0.00%").format(0.0));
    }//GEN-LAST:event_btn_repetirValorActionPerformed

    private void txt_totalPedidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_totalPedidoKeyReleased
        double valorPedido = Corretores.ConverterStringDouble(txt_totalPedido.getText());
        double valorProdutos = Corretores.ConverterStringDouble(txt_totalProduto.getText());
        txt_desconto.setText(new DecimalFormat("#0.00%").format(1 - valorPedido / valorProdutos));

    }//GEN-LAST:event_txt_totalPedidoKeyReleased

    private void txt_totalPedidoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_totalPedidoFocusGained
        txt_totalPedido.selectAll();
    }//GEN-LAST:event_txt_totalPedidoFocusGained

    private void txt_descontoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_descontoFocusGained
        txt_desconto.selectAll();
    }//GEN-LAST:event_txt_descontoFocusGained

    private void txt_totalPedidoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_totalPedidoFocusLost
        txt_totalPedido.setText(Corretores.ConverterDecimalReais(txt_totalPedido.getText()));
    }//GEN-LAST:event_txt_totalPedidoFocusLost

    private void txt_descontoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_descontoFocusLost
        txt_desconto.setText(new DecimalFormat("#0.00%").format(Corretores.ConverterStringDouble(txt_desconto.getText()) / 100));
    }//GEN-LAST:event_txt_descontoFocusLost

    private void btn_pesqFornecedor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesqFornecedor1ActionPerformed
        frmCadFornecedores fornecedor = new frmCadFornecedores();
        this.getParent().add(fornecedor);
        fornecedor.btn_Salvar.setEnabled(false);
        fornecedor.btn_novo1.setEnabled(false);
        fornecedor.getRootPane().setDefaultButton(fornecedor.btn_pesquisarCompra);
        fornecedor.setVisible(true);
        fornecedor.tb_fornecedores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    int index = fornecedor.tb_fornecedores.getSelectedRow();
                    txt_idFornecedorConsulta.setText(fornecedor.TbForn.getValueAt(index, 0).toString());
                    //String CNPJ = fornecedor.TbForn.getValueAt(index, 7).toString();
                    //txt_nomeFantasia.setText(fornecedor.TbForn.getValueAt(index, 1).toString()
                    //        + " - " + fornecedor.TbForn.getValueAt(index, 2).toString());
                    fornecedor.dispose();
                }
            }
        });
    }//GEN-LAST:event_btn_pesqFornecedor1ActionPerformed

    private void btn_buscarFechamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarFechamentosActionPerformed
        thread = new ThreadLoadProgressBar() {
            @Override
            public void run() {
                TbConsulta.limpar();
                TbConsulta.addLista(PedidosD.ConsultaFechamentoCriado(thread.dialog, getSQLWhere()));
            }
        };

    }//GEN-LAST:event_btn_buscarFechamentosActionPerformed

    private void tb_consultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_consultaMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            if (jPopupConsulta.isVisible() == true) {
                jPopupConsulta.setVisible(false);
            } else {
                jPopupConsulta.setVisible(true);
                jPopupConsulta.show(this, 0, 0);
                jPopupConsulta.setLocation(evt.getLocationOnScreen());
            }
        }
    }//GEN-LAST:event_tb_consultaMouseClicked

    private void jMenuEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuEditarActionPerformed
        int index = tb_consulta.getSelectedRow();
        Long IdFechamento = (Long) TbConsulta.getValueAt(index, TbConsulta.ID_FECHAMENTO);
        if (index >= 0) {
            thread = new ThreadLoadProgressBar() {
                @Override
                public void run() {
                    fechamentoB = new PedidosAlmoxarifadoFechamento();
                    fechamentoB = PedidosD.buscarFechamento(IdFechamento, thread.dialog);
                    preencherFormulario(fechamentoB);
                    jTabbedPane1.setSelectedIndex(0);
                }
            };
        }
    }//GEN-LAST:event_jMenuEditarActionPerformed

    private void tb_fechamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_fechamentoMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            if (jPopupGerar.isVisible() == true) {
                jPopupGerar.setVisible(false);
            } else {
                jPopupGerar.setVisible(true);
                jPopupGerar.show(this, 0, 0);
                jPopupGerar.setLocation(evt.getLocationOnScreen());
            }
        }
    }//GEN-LAST:event_tb_fechamentoMouseClicked

    private void jMenuExcluirItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuExcluirItemActionPerformed
        int index = tb_fechamento.getSelectedRow();
        if (index >= 0) {
            Long id = (Long) TbItens.getValueAt(index, 0);
            if (id == 0) {
                TbItens.excluirLinha(index);
            } else {
                int excluirSaidaInsumos = JOptionPane.showConfirmDialog(null, "Atenção, Está Ação Excluíra Permanentemente este Item, Deseja Continuar", "Atenção", JOptionPane.YES_NO_OPTION);
                if (excluirSaidaInsumos == JOptionPane.YES_OPTION) {
                    thread = new ThreadLoadProgressBar() {
                        @Override
                        public void run() {
                            if (PedidosD.excluirFechamentoItem(TbItens.getBeans(index), thread.dialog)) {
                                TbItens.excluirLinha(index);
                            }
                        }
                    };
                }
            }
        }
    }//GEN-LAST:event_jMenuExcluirItemActionPerformed

    private void jMenuExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuExportarActionPerformed
        int index = tb_consulta.getSelectedRow();
        Long IdFechamento = (Long) TbConsulta.getValueAt(index, TbConsulta.ID_FECHAMENTO);
        if (index >= 0) {
            thread = new ThreadLoadProgressBar() {
                @Override
                public void run() {
                    fechamentoB = new PedidosAlmoxarifadoFechamento();
                    fechamentoB = PedidosD.buscarFechamento(IdFechamento, thread.dialog);
                    exportarPlanilha(new TableModelPedidosFechamento(fechamentoB.getListItens()), fechamentoB.getListItens().get(0).getIdCotacao().getIdSolicitacao().getId());
                    
                }
            };
        }
    }//GEN-LAST:event_jMenuExportarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_buscarFechamentos;
    javax.swing.JButton btn_editar;
    javax.swing.JButton btn_excluir;
    javax.swing.JButton btn_novo;
    javax.swing.JButton btn_pesqFornecedor;
    javax.swing.JButton btn_pesqFornecedor1;
    javax.swing.JButton btn_pesqSolicitacao;
    private javax.swing.JButton btn_pesquisar;
    javax.swing.JButton btn_repetirValor;
    javax.swing.JButton btn_salvar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox ch_emitido;
    private javax.swing.JCheckBox ch_naoEmitido;
    private javax.swing.JMenuItem jMenuEditar;
    public javax.swing.JMenuItem jMenuEnviarRemessa;
    private javax.swing.JMenuItem jMenuExcluirItem;
    private javax.swing.JMenuItem jMenuExportar;
    private javax.swing.JPopupMenu jPopupConsulta;
    private javax.swing.JPopupMenu jPopupGerar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    public javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JTable tb_consulta;
    private javax.swing.JTable tb_fechamento;
    private javax.swing.JTextField txt_codigo;
    private com.toedter.calendar.JDateChooser txt_data;
    private javax.swing.JTextField txt_desconto;
    private com.toedter.calendar.JDateChooser txt_dtFinal;
    private com.toedter.calendar.JDateChooser txt_dtInicial;
    private javax.swing.JTextField txt_idFornecedor;
    private javax.swing.JTextField txt_idFornecedorConsulta;
    private javax.swing.JTextField txt_idPedido;
    private javax.swing.JTextField txt_idSolicitacao;
    private javax.swing.JTextField txt_idSolicitacaoConsulta;
    private javax.swing.JTextField txt_nomeFantasia;
    private javax.swing.JTextField txt_totalPedido;
    private javax.swing.JTextField txt_totalProduto;
    // End of variables declaration//GEN-END:variables

    public String getSQLWhere(){
        String s = "";
        if (!txt_codigo.getText().equals("")){
           s += " and item.codigo like '%" + txt_codigo.getText()  + "%'";
        }
        if (!txt_idPedido.getText().equals("")){
           s += " and item.id_pedido = " + getInteger(txt_idPedido.getText());
        }
        if (!txt_idFornecedorConsulta.getText().equals("")){
           s += " and item.ID_Fornecedor = " + getInteger(txt_idFornecedorConsulta.getText());
        }
        if (!txt_idSolicitacaoConsulta.getText().equals("")){
           s += " and item.idCotacao.idSolicitacao.id = " + getInteger(txt_idSolicitacaoConsulta.getText());
        }
        if (txt_dtInicial.getDate() != null && txt_dtFinal.getDate() != null){
            s += " and f.data between '" + new SimpleDateFormat("yyyy-MM-dd").format(txt_dtInicial.getDate()) 
                    + "' and '" +new SimpleDateFormat("yyyy-MM-dd").format(txt_dtFinal.getDate())  + "'";
        }    
        return s;
    }
     
    private Integer getInteger(String s){
        Integer i = 0;
        try {
              i = Integer.parseInt(s.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
             JOptionPane.showMessageDialog(null, "Verifique os Campos Númericos Digitados!", "Erro", 0, FormatarICO.ICObtnSair());
        }
       return i;
    }
    
    public void novo() {
        btn_pesqSolicitacao.setEnabled(true);
        btn_pesqFornecedor.setEnabled(true);
        btn_salvar.setEnabled(true);
        txt_data.setEnabled(true);
        limparCampos();
    }

    public void limparCampos() {
        TbItens.limpar();
        txt_data.setDate(null);
        txt_idSolicitacao.setText("");
        txt_idFornecedor.setText("");
        txt_nomeFantasia.setText("");
        txt_totalProduto.setText(Corretores.ConverterDoubleReais(0.00));
        txt_totalPedido.setText(Corretores.ConverterDoubleReais(0.00));
    }

    public void desabilitarCampos() {
        btn_pesqSolicitacao.setEnabled(false);
        btn_pesqFornecedor.setEnabled(false);
        btn_salvar.setEnabled(false);
        txt_data.setEnabled(false);
    }

    public void somarTabela() {
        Double valor = 0.00;
        for (int i = 0; i < TbItens.getRowCount(); i++) {
            boolean status = (Boolean) TbItens.getValueAt(i, TbItens.STATUS_FECHAMENTO);
            if (status == false){
                // se o item estiver com status de fechamento false ele é somado nos produtos
                valor += (Double) TbItens.getValueAt(i, TbItens.VALOR_TOTAL);
            }
        }
        txt_totalProduto.setText(Corretores.ConverterDoubleReais(valor));
        txt_totalPedido.setText(Corretores.ConverterDoubleReais(0.0));
    }

    public void preencherFormulario(PedidosAlmoxarifadoFechamento f) {
        txt_data.setDate(f.getData());
        ch_emitido.setSelected(f.getStatusEmissao());
        TbItens.limpar();
        TbItens.addLista(f.getListItens());
        somarTabela();
        txt_totalPedido.setText(Corretores.ConverterDoubleReais(f.getValor_pedido()));
        txt_idSolicitacao.setText(f.getListItens().get(0).getIdCotacao().getIdSolicitacao().getId().toString());
        txt_idFornecedor.setText(f.getListItens().get(0).getID_Fornecedor().toString());
        txt_nomeFantasia.setText(f.getListItens().get(0).getFornecedor());
        if (f.getStatusEmissao() == false) {
            ch_naoEmitido.setSelected(true);
            btn_salvar.setEnabled(false);
            btn_editar.setEnabled(true);
            btn_excluir.setEnabled(true);
        }
    }

    private void popularBeans(PedidosAlmoxarifadoFechamento bean) {
        bean.setData(txt_data.getDate());
        bean.setIdUsuario(IdUsuario);
        bean.setListItens(TbItens.getLista());
        bean.setStatusEmissao(ch_emitido.isSelected());
        bean.setValor_pedido(Corretores.ConverterReaisDouble(txt_totalPedido.getText()));
    }

    private Boolean verificarBeans(PedidosAlmoxarifadoFechamento bean) {
        if (bean.getData() == null) {
            JOptionPane.showMessageDialog(null, "Selecione uma Data Válida!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (bean.getValor_pedido() == 0.0 || bean.getValor_pedido() == null) {
            JOptionPane.showMessageDialog(null, "Informe o Valor Final do Pedido!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (bean.getListItens().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione os Itens que Serão Incluídos no Pedido", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (bean.getStatusEmissao() == true) {
            int cadastrar = JOptionPane.showConfirmDialog(null, "<html>Após Salvar este Pedido ele <b>NÃO Poderá ser Editado</b>, <br>Deseja Prosseguir?</b>"
                    + "</html>", "Atenção", JOptionPane.YES_NO_OPTION);
            if (cadastrar == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null, "Selecione os Itens que Serão Incluídos no Pedido", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
        }
        return true;
    }

    public Boolean exportarPlanilha(TableModelPedidosFechamento model, Long idSolicitacao) {
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel File", "xlsx", "XLSX");
        fc.setDialogTitle("Selecione o Local do Arquivo");
        fc.setFileFilter(filter);
        fc.setSelectedFile(new File("Fechamento Solicitação Nº " + idSolicitacao + " - " + model.getLista().get(0).getFornecedor()));
        int option = fc.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            String fileName = fc.getSelectedFile().getAbsolutePath();
            try {
                WorkbookSettings conf = new WorkbookSettings();
                conf.setEncoding("ISO-8859-1");
                File caminhoCanonical = Corretores.ValidarArquivoModelo("/src/Arquivos/Planilhas_Modelo/model_pedido_mercadoria.xlsx");
                XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(caminhoCanonical));
                XSSFSheet planilha = workbook.getSheetAt(0);

                XSSFRow rowDados = planilha.getRow(1);
                XSSFCell cellSolicitacao = rowDados.getCell(1);
                XSSFCell cellFornecedor = rowDados.getCell(4);
                XSSFCell cellData = rowDados.getCell(7);

                cellSolicitacao.setCellValue(idSolicitacao); 
                cellSolicitacao.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                cellFornecedor.setCellValue(model.getValueAt(0, model.FORNECEDOR).toString());
                cellData.setCellValue(dataAtual);
                popularPlanilha(model, planilha);
                workbook.write(new FileOutputStream(fileName + ".xlsx"));
                JOptionPane.showMessageDialog(null, "Planilha Exportada Com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
            } catch (IOException ex) {
                 JOptionPane.showMessageDialog(null,"<html>Erro ao Exportar Planilha! <br>"
                         + ex + "</hmtl>", "Erro", 0, FormatarICO.ICObtnSair());
            }
        }
        return true;
    }

    public void popularPlanilha(TableModelPedidosFechamento model, XSSFSheet planilha) {
        int linhaInicio = 4;
        for (int i = linhaInicio; i < model.getRowCount() + linhaInicio; i++) {
            XSSFRow row = planilha.getRow(i);
            for (int c = 0; c < model.getColumnCount(); c++) {
                XSSFCell cell = row.getCell(c);
                int index = i - linhaInicio;
                switch (c) {
                    case 0:
                        cell.setCellValue(model.getValueAt(index, model.FAZENDA).toString());
                        break;
                    case 1:
                        cell.setCellValue(Double.parseDouble(model.getValueAt(index, model.ID_PEDIDO).toString())); 
                        cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                        break;
                    case 2:
                        cell.setCellValue(Double.parseDouble(model.getValueAt(index, model.N_ITEM_PEDIDO).toString()));
                        cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                        break;
                    case 3:
                        cell.setCellValue(Double.parseDouble(model.getValueAt(index, model.ID_CADASTRO).toString()));
                        cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                        break;
                    case 4:
                        cell.setCellValue(model.getValueAt(index, model.DESCRICAO).toString());
                        break;
                    case 5:
                        cell.setCellValue(model.getValueAt(index, model.CODIGO).toString());
                        break;
                    case 6:
                        cell.setCellValue(model.getValueAt(index, model.MARCA).toString());
                        break;
                    case 7:
                        cell.setCellValue((Double)model.getValueAt(index, model.QUANTIDADE));
                        cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                        break;
                    case 8:
                        cell.setCellValue(model.getValueAt(index, model.UNIDADE).toString());
                        break;    
                    case 9:
                        cell.setCellValue((Double)model.getValueAt(index, model.VALORUNIT));
                        cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                        break;  
                    case 10:
                        cell.setCellValue((Double)model.getValueAt(index, model.VALOR_TOTAL));
                        cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                        break;      
                    default:
                }
            }
        }
    }
    
    private class CellRenderTabela extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            Object val = table.getValueAt(row, column);

            if (val instanceof Double && (column == 15 || column == 16)) {
                Double valor = (Double) val;
                NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
                setText(valor != null ? nf.format(valor) : "");
                if (valor > 0) {
                    setFont(new Font("Tahoma", Font.BOLD, 11));
                }
            }
            setHorizontalTextPosition(SwingConstants.CENTER);
            if (isSelected) {
                return renderer;
            }
            if (column == 15 || column == 16) {
                setForeground(Color.black);
                setBackground(new Color(255, 222, 163, 255));
            }
            return renderer;
        }
    }

}
