package GUI;

import Almoxarifado.CadItensAlmoxCodigos;
import Almoxarifado.CadItensAlmoxarifadoBeans;
import Beans.FornecedoresBeans;
import Beans.PedidoAlmoxarifadoItens;
import Beans.PedidosAlmoxarifadoCotacao;
import Beans.PedidosAlmoxarifadoSolicitacao;
import DAO.CadAlmoxarifadaoDAO;
import DAO.DiversasHibernate;
import DAO.PedidosAlmoxarifadoDAO;
import Icones.FormatarICO;
import TableModel.TableModelPedidoCotacao;
import TableModel.TableModelPedidoCotacaoConsulta;
import TableModel.TbPedidosCotacao;
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
import java.io.FileInputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FrmPedidosCotacao extends javax.swing.JInternalFrame {

    PedidosAlmoxarifadoCotacao CotacaoB;
    PedidosAlmoxarifadoCotacao CotacaoEditada;
    CadAlmoxarifadaoDAO CadAlmoxD;
    TableModelPedidoCotacao TbImportacao;
    TableModelPedidoCotacaoConsulta TbConsulta;
    CentralizarTabela Centralizar;
    Integer idFornecedor;
    PedidosAlmoxarifadoSolicitacao solicitacao;
    PedidosAlmoxarifadoDAO PedidosD;
    ThreadLoadProgressBar thread;
    FrmProgressBar frm;
    FornecedoresBeans FornecedorB;

    public FrmPedidosCotacao() {
        initComponents();
        CotacaoB = new PedidosAlmoxarifadoCotacao();
        PedidosD = new PedidosAlmoxarifadoDAO();
        CadAlmoxD = new CadAlmoxarifadaoDAO();
        Centralizar = new CentralizarTabela();
        tabelaCotacao();
        tabelaConsulta();
        jPanel5.setVisible(false);

    }

    private TableModelPedidoCotacao getTableModelImportacao() {
        if (TbImportacao == null) {
            TbImportacao = new TableModelPedidoCotacao();
        }
        return TbImportacao;
    }

    private TableModelPedidoCotacaoConsulta getTableModelConsulta() {
        if (TbConsulta == null) {
            TbConsulta = new TableModelPedidoCotacaoConsulta();
        }
        return TbConsulta;
    }

    private JTable tabelaCotacao() {
        tb_cotacao.setModel(getTableModelImportacao());
        ((DefaultTableCellRenderer) tb_cotacao.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        Centralizar.centralizarTabela(tb_cotacao);
        CellRenderTabela RendererMoeda = new CellRenderTabela();

        tb_cotacao.getColumnModel().getColumn(TbImportacao.ID_ITEM_PEDIDO).setPreferredWidth(60);
        tb_cotacao.getColumnModel().getColumn(TbImportacao.ID_PEDIDO).setPreferredWidth(60);
        tb_cotacao.getColumnModel().getColumn(TbImportacao.N_ITEM_PEDIDO).setPreferredWidth(60);
        tb_cotacao.getColumnModel().getColumn(TbImportacao.QUANTIDADE).setPreferredWidth(75);
        tb_cotacao.getColumnModel().getColumn(TbImportacao.CODIGO).setPreferredWidth(120);
        tb_cotacao.getColumnModel().getColumn(TbImportacao.DESCRICAO).setPreferredWidth(180);

        tb_cotacao.getColumnModel().getColumn(TbImportacao.VALOR_ORIGINAL).setCellRenderer(RendererMoeda);
        tb_cotacao.getColumnModel().getColumn(TbImportacao.VALOR_M1).setCellRenderer(RendererMoeda);
        tb_cotacao.getColumnModel().getColumn(TbImportacao.MARCA_1).setCellRenderer(RendererMoeda);
        tb_cotacao.getColumnModel().getColumn(TbImportacao.VALOR_M2).setCellRenderer(RendererMoeda);
        tb_cotacao.getColumnModel().getColumn(TbImportacao.MARCA_2).setCellRenderer(RendererMoeda);
        return tb_cotacao;
    }

    private JTable tabelaConsulta() {
        tb_consulta.setModel(getTableModelConsulta());
        ((DefaultTableCellRenderer) tb_consulta.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        Centralizar.centralizarTabela(tb_consulta);
        CellRenderTabela RendererMoeda = new CellRenderTabela();

        tb_consulta.getColumnModel().getColumn(TbConsulta.ID_ITEM_PEDIDO).setPreferredWidth(60);
        tb_consulta.getColumnModel().getColumn(TbConsulta.ID_PEDIDO).setPreferredWidth(60);
        tb_consulta.getColumnModel().getColumn(TbConsulta.N_ITEM_PEDIDO).setPreferredWidth(60);
        tb_consulta.getColumnModel().getColumn(TbConsulta.QUANT_COMPRADA).setPreferredWidth(75);
        tb_consulta.getColumnModel().getColumn(TbConsulta.CODIGO).setPreferredWidth(120);
        tb_consulta.getColumnModel().getColumn(TbConsulta.DESCRICAO).setPreferredWidth(180);

        tb_consulta.getColumnModel().getColumn(TbConsulta.VALOR).setCellRenderer(RendererMoeda);
        return tb_consulta;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jPopupConsulta = new javax.swing.JPopupMenu();
        jMenuEditar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        txt_data =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        txt_idFornecedor = new javax.swing.JTextField();
        btn_pesqFornecedor = new javax.swing.JButton();
        txt_nomeFantasia = new javax.swing.JTextField();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        txt_idSolicitacao = new javax.swing.JTextField();
        btn_pesqSolicitacao = new javax.swing.JButton();
        btn_lerDados = new javax.swing.JButton();
        btn_manual = new javax.swing.JButton();
        btn_verificarCodigos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_cotacao = new javax.swing.JTable();
        btn_novo = new javax.swing.JButton();
        btn_salvar = new javax.swing.JButton();
        btn_editarPedido = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        txt_idSolicitacao1 = new javax.swing.JTextField();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        txt_idFornecedor1 = new javax.swing.JTextField();
        btn_pesqFornecedor1 = new javax.swing.JButton();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        txt_dtInicial =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        txt_dtFinal =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        txt_nPedido = new javax.swing.JTextField();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        txt_idItemConsulta = new javax.swing.JTextField();
        btn_pesqItem1 = new javax.swing.JButton();
        javax.swing.JLabel jLabel17 = new javax.swing.JLabel();
        txt_codigoConsulta = new javax.swing.JTextField();
        javax.swing.JLabel jLabel18 = new javax.swing.JLabel();
        txt_descricaoConsulta = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        btn_consulta = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_consulta = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel13 = new javax.swing.JLabel();
        txt_nSolicitacao = new javax.swing.JTextField();
        javax.swing.JLabel jLabel14 = new javax.swing.JLabel();
        txt_nPedidoCot = new javax.swing.JTextField();
        javax.swing.JLabel jLabel15 = new javax.swing.JLabel();
        txt_nItem = new javax.swing.JTextField();
        javax.swing.JLabel jLabel16 = new javax.swing.JLabel();
        txt_fornecedor = new javax.swing.JTextField();
        btn_editar = new javax.swing.JButton();
        btn_excluirCot = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();
        javax.swing.JLabel jLabel19 = new javax.swing.JLabel();
        txt_valor = new javax.swing.JTextField();
        javax.swing.JLabel jLabel20 = new javax.swing.JLabel();
        txt_marca = new javax.swing.JTextField();
        ch_original = new javax.swing.JCheckBox();

        jMenuEditar.setText("Editar Cotação");
        jMenuEditar.setToolTipText("");
        jMenuEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEditarActionPerformed(evt);
            }
        });
        jPopupConsulta.add(jMenuEditar);
        jPopupConsulta.add(jSeparator1);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Importa Cotaões");

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Data");

        txt_data.setEnabled(false);

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

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Nº Solicitacao");

        txt_idSolicitacao.setEditable(false);
        txt_idSolicitacao.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btn_pesqSolicitacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar pequeno.png"))); // NOI18N
        btn_pesqSolicitacao.setEnabled(false);
        btn_pesqSolicitacao.setPreferredSize(new java.awt.Dimension(30, 25));
        btn_pesqSolicitacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesqSolicitacaoActionPerformed(evt);
            }
        });

        btn_lerDados.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_lerDados.setText("Ler Dados");
        btn_lerDados.setEnabled(false);
        btn_lerDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lerDadosActionPerformed(evt);
            }
        });

        btn_manual.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_manual.setText("Cotação Manual");
        btn_manual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_manualActionPerformed(evt);
            }
        });

        btn_verificarCodigos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_verificarCodigos.setText("Verificar Conversões");
        btn_verificarCodigos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_verificarCodigosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_idFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btn_pesqFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nomeFantasia, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btn_verificarCodigos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_manual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_lerDados, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(txt_data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txt_idFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesqFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txt_idSolicitacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesqSolicitacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_lerDados)
                    .addComponent(btn_manual)
                    .addComponent(btn_verificarCodigos))
                .addContainerGap())
        );

        tb_cotacao.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tb_cotacao);

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
        btn_salvar.setEnabled(false);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_editarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_editarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Importar Cotações", jPanel1);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Nº Solicitacao");

        txt_idSolicitacao1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Fornecedor");

        txt_idFornecedor1.setEditable(false);
        txt_idFornecedor1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btn_pesqFornecedor1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar pequeno.png"))); // NOI18N
        btn_pesqFornecedor1.setPreferredSize(new java.awt.Dimension(30, 25));
        btn_pesqFornecedor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesqFornecedor1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Data");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("à");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Nº Pedido");

        txt_nPedido.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("ID Item");

        txt_idItemConsulta.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btn_pesqItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar pequeno.png"))); // NOI18N
        btn_pesqItem1.setPreferredSize(new java.awt.Dimension(30, 25));
        btn_pesqItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesqItem1ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Código");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Descrição");

        jCheckBox1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jCheckBox1.setText("Apenas Comprado");

        btn_consulta.setText("Pesquisar");
        btn_consulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_consultaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_idItemConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn_pesqItem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_codigoConsulta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_descricaoConsulta)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dtInicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dtFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_idSolicitacao1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_idFornecedor1)
                        .addGap(0, 0, 0)
                        .addComponent(btn_pesqFornecedor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addGap(5, 5, 5)
                        .addComponent(txt_nPedido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_consulta, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel10)
                    .addComponent(txt_idSolicitacao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txt_idFornecedor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesqFornecedor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txt_dtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txt_dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txt_nPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_consulta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel17)
                    .addComponent(txt_codigoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(txt_descricaoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1)
                    .addComponent(btn_pesqItem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_idItemConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
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

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Nº Solic.");

        txt_nSolicitacao.setEditable(false);
        txt_nSolicitacao.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Nº Pedido");

        txt_nPedidoCot.setEditable(false);
        txt_nPedidoCot.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Nº Item");

        txt_nItem.setEditable(false);
        txt_nItem.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Fornecedor");

        txt_fornecedor.setEditable(false);

        btn_editar.setText("Editar");
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });

        btn_excluirCot.setText("Excluir");
        btn_excluirCot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirCotActionPerformed(evt);
            }
        });

        btn_cancel.setText("Cancelar");
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Valor");

        txt_valor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_valor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_valorFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_valorFocusLost(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Marca");

        txt_marca.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ch_original, org.jdesktop.beansbinding.ELProperty.create("${!selected}"), txt_marca, org.jdesktop.beansbinding.BeanProperty.create("editable"));
        bindingGroup.addBinding(binding);

        ch_original.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_original.setText("Orig.");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nSolicitacao, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nPedidoCot, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nItem, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_fornecedor, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_valor, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ch_original)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_marca, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_cancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_editar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_excluirCot)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(txt_valor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(txt_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ch_original))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(txt_fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_editar)
                        .addComponent(btn_excluirCot)
                        .addComponent(btn_cancel))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(txt_nItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(txt_nPedidoCot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(txt_nSolicitacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Consultar Cotações", jPanel2);

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

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
                    FornecedorB = DiversasHibernate.getFornecedor((Integer) fornecedor.TbForn.getValueAt(index, 0));
                    String CNPJ = fornecedor.TbForn.getValueAt(index, 7).toString();
                    txt_idFornecedor.setText(fornecedor.TbForn.getValueAt(index, 0).toString());
                    txt_nomeFantasia.setText(fornecedor.TbForn.getValueAt(index, 1).toString() + " - " + fornecedor.TbForn.getValueAt(index, 2).toString());
                    fornecedor.dispose();
                }
            }
        });
    }//GEN-LAST:event_btn_pesqFornecedorActionPerformed

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
                            frmSolicitacao.dispose();
                            thread.dialog.dispose();
                        }
                    };
                }
            }
        });
    }//GEN-LAST:event_btn_pesqSolicitacaoActionPerformed

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed
        novo();
    }//GEN-LAST:event_btn_novoActionPerformed

    private void btn_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(null, "Deseja Salvar esta Solicitacao de Mercadorias?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION) {
            List<PedidosAlmoxarifadoCotacao> listCotacao = new ArrayList<>();
            popularBeans(listCotacao);
            if (verificarBeans(listCotacao)) {
                thread = new ThreadLoadProgressBar() {
                    @Override
                    public void run() {
                       if (PedidosD.salvarCotacao(listCotacao, thread.dialog)){
                           limparCampos();
                       };
                    }
                };
            }
        }
    }//GEN-LAST:event_btn_salvarActionPerformed

    private void btn_editarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarPedidoActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja Editar esta Entrada?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION) {
            List<PedidosAlmoxarifadoCotacao> listCotacao = new ArrayList<>();
            popularBeans(listCotacao);
            if (verificarBeans(listCotacao)) {
                if (true) {
                    limparCampos();
                    desabilitarCampos();
                }
            }
        }
    }//GEN-LAST:event_btn_editarPedidoActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        int excluirSaidaInsumos = JOptionPane.showConfirmDialog(null, "Atenção, Está Ação Excluíra Permanentemente este Registro, Deseja Continuar", "Atenção", JOptionPane.YES_NO_OPTION);
        if (excluirSaidaInsumos == JOptionPane.YES_OPTION) {
            if (true == true) {
                //  desabilitarCampos();
                //  limparCampos();
            }
        }
    }//GEN-LAST:event_btn_excluirActionPerformed

    private void btn_lerDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lerDadosActionPerformed
        if (verificarCampos()) {
            lerArquivo();
        }
    }//GEN-LAST:event_btn_lerDadosActionPerformed

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
                    txt_idFornecedor1.setText(fornecedor.TbForn.getValueAt(index, 0).toString());
                    //txt_nomeFantasia.setText(fornecedor.TbForn.getValueAt(index, 1).toString()
                    //        + " - " + fornecedor.TbForn.getValueAt(index, 2).toString());
                    fornecedor.dispose();
                }
            }
        });
    }//GEN-LAST:event_btn_pesqFornecedor1ActionPerformed

    private void btn_pesqItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesqItem1ActionPerformed
        FrmConsultaPecas consultaPecas = new FrmConsultaPecas();
        this.getParent().add(consultaPecas);
        consultaPecas.tb_pecas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    int index = consultaPecas.tb_pecas.getSelectedRow();
                    Integer IdItem = (Integer) consultaPecas.TbPecas.getValueAt(index, 0);
                    txt_idItemConsulta.setText(IdItem.toString());
                    consultaPecas.dispose();
                }
            }
        });
        consultaPecas.setVisible(true);
    }//GEN-LAST:event_btn_pesqItem1ActionPerformed

    private void btn_consultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_consultaActionPerformed
        TbConsulta.limpar();
        TbConsulta.addLista(PedidosD.BuscarCotacoes(getSQLWhere()));

    }//GEN-LAST:event_btn_consultaActionPerformed

    private void jMenuEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuEditarActionPerformed
        CotacaoEditada = new PedidosAlmoxarifadoCotacao();
        CotacaoEditada = TbConsulta.getBeans(tb_consulta.getSelectedRow());
        if (CotacaoEditada != null) {
            jPanel5.setVisible(true);

            preencherFormularioEditar(CotacaoEditada);
        }

    }//GEN-LAST:event_jMenuEditarActionPerformed

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

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        ocultarEditorCotacao();
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void txt_valorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorFocusGained
        txt_valor.selectAll();
    }//GEN-LAST:event_txt_valorFocusGained

    private void txt_valorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorFocusLost
        txt_valor.setText(Corretores.ConverterDecimalReais(txt_valor.getText()));
    }//GEN-LAST:event_txt_valorFocusLost

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja Editar esta Cotação de Mercadorias?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION) {
            popularCotacaoEditada(CotacaoEditada);
            if (PedidosD.editarCotacao(CotacaoEditada)) {
                ocultarEditorCotacao();
            }
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void btn_excluirCotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirCotActionPerformed
        int excluir = JOptionPane.showConfirmDialog(null, "Deseja Excluir esta Cotação?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (excluir == JOptionPane.YES_OPTION) {
            popularCotacaoEditada(CotacaoEditada);
            if (PedidosD.excluirCotacao(CotacaoEditada)) {
                ocultarEditorCotacao();
                TbConsulta.getLista().remove(CotacaoEditada);
            }
        }
    }//GEN-LAST:event_btn_excluirCotActionPerformed

    private void btn_manualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_manualActionPerformed
        if (verificarCampos()) {
            TbImportacao.limpar();
            for (PedidoAlmoxarifadoItens item : solicitacao.getListaSolicitacao()) {
                TbPedidosCotacao cotacao = new TbPedidosCotacao();
                TbImportacao.addBeans(popularCotacao(cotacao, item));
            }
            TbImportacao.isCellEditable(1, TbImportacao.VALOR_M1);
            TbImportacao.isCellEditable(1, TbImportacao.VALOR_M2);
            TbImportacao.isCellEditable(1, TbImportacao.VALOR_ORIGINAL);
            TbImportacao.isCellEditable(1, TbImportacao.MARCA_1);
            TbImportacao.isCellEditable(1, TbImportacao.MARCA_2);
        }
    }//GEN-LAST:event_btn_manualActionPerformed

    private void btn_verificarCodigosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_verificarCodigosActionPerformed
        int consulta = JOptionPane.showConfirmDialog(null, "Deseja Verificar os Códigos do Fornecedor?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (consulta == JOptionPane.YES_OPTION) {
            int contadorOrig = consultarCodigosFornecedor(new Integer(txt_idFornecedor.getText()), TbImportacao, TbImportacao.COD_F_ORIG, TbImportacao.ID_COD_F_ORIG);
            int contadorM1 = consultarCodigosFornecedor(new Integer(txt_idFornecedor.getText()), TbImportacao, TbImportacao.COD_F_M1, TbImportacao.ID_COD_F_M1);
            int contadorM2 = consultarCodigosFornecedor(new Integer(txt_idFornecedor.getText()), TbImportacao, TbImportacao.COD_F_M2, TbImportacao.ID_COD_F_M2);
            if (contadorOrig > 0) {
                int cadastrar = JOptionPane.showConfirmDialog(null, "Existem " + contadorOrig + " Conversões Originais não Localizadas no Cadastro, Deseja Cadastra-las?", "Atenção", JOptionPane.YES_NO_OPTION);
                if (cadastrar == JOptionPane.YES_OPTION) {
                    cadastrarConversao(TbImportacao, TbImportacao.COD_F_ORIG, TbImportacao.ID_COD_F_ORIG);
                }
            }
            if (contadorM1 > 0) {
                int cadastrar = JOptionPane.showConfirmDialog(null, "Existem " + contadorM1 + " Conversões da Marca-1 não Localizadas no Cadastro, Deseja Cadastra-las?", "Atenção", JOptionPane.YES_NO_OPTION);
                if (cadastrar == JOptionPane.YES_OPTION) {
                    cadastrarConversao(TbImportacao, TbImportacao.COD_F_M1, TbImportacao.ID_COD_F_M1);
                }
            }
            if (contadorM2 > 0) {
                int cadastrar = JOptionPane.showConfirmDialog(null, "Existem " + contadorM2 + " Conversões da Marca-2 não Localizadas no Cadastro, Deseja Cadastra-las?", "Atenção", JOptionPane.YES_NO_OPTION);
                if (cadastrar == JOptionPane.YES_OPTION) {
                    cadastrarConversao(TbImportacao, TbImportacao.COD_F_M2, TbImportacao.ID_COD_F_M2);
                }
            }
        }
    }//GEN-LAST:event_btn_verificarCodigosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_consulta;
    private javax.swing.JButton btn_editar;
    javax.swing.JButton btn_editarPedido;
    javax.swing.JButton btn_excluir;
    private javax.swing.JButton btn_excluirCot;
    private javax.swing.JButton btn_lerDados;
    private javax.swing.JButton btn_manual;
    javax.swing.JButton btn_novo;
    javax.swing.JButton btn_pesqFornecedor;
    javax.swing.JButton btn_pesqFornecedor1;
    javax.swing.JButton btn_pesqItem1;
    javax.swing.JButton btn_pesqSolicitacao;
    javax.swing.JButton btn_salvar;
    private javax.swing.JButton btn_verificarCodigos;
    private javax.swing.JCheckBox ch_original;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JMenuItem jMenuEditar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu jPopupConsulta;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    public javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tb_consulta;
    private javax.swing.JTable tb_cotacao;
    private javax.swing.JTextField txt_codigoConsulta;
    private com.toedter.calendar.JDateChooser txt_data;
    private javax.swing.JTextField txt_descricaoConsulta;
    private com.toedter.calendar.JDateChooser txt_dtFinal;
    private com.toedter.calendar.JDateChooser txt_dtInicial;
    private javax.swing.JTextField txt_fornecedor;
    private javax.swing.JTextField txt_idFornecedor;
    private javax.swing.JTextField txt_idFornecedor1;
    private javax.swing.JTextField txt_idItemConsulta;
    private javax.swing.JTextField txt_idSolicitacao;
    private javax.swing.JTextField txt_idSolicitacao1;
    private javax.swing.JTextField txt_marca;
    private javax.swing.JTextField txt_nItem;
    private javax.swing.JTextField txt_nPedido;
    private javax.swing.JTextField txt_nPedidoCot;
    private javax.swing.JTextField txt_nSolicitacao;
    private javax.swing.JTextField txt_nomeFantasia;
    private javax.swing.JTextField txt_valor;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    private void novo() {
        btn_salvar.setEnabled(true);
        btn_lerDados.setEnabled(true);
        btn_manual.setEnabled(true);
        btn_pesqFornecedor.setEnabled(true);
        btn_pesqSolicitacao.setEnabled(true);
        TbImportacao.limpar();
        txt_data.setEnabled(true);
        FornecedorB = null;
        idFornecedor = 0;
        txt_idFornecedor.setText("");
        txt_idSolicitacao.setText("");
        txt_data.setDate(null);
    }

    private void preencherFormularioEditar(PedidosAlmoxarifadoCotacao cot) {
        txt_nSolicitacao.setText(cot.getIdSolicitacao().getId().toString());
        txt_nPedidoCot.setText(String.valueOf(cot.getIdItemPedido().getIdPedido().getId()));
        txt_nItem.setText(String.valueOf(cot.getIdItemPedido().getnItem()));
        txt_fornecedor.setText(cot.getFornecedor());
        txt_valor.setText(Corretores.ConverterDoubleReais(cot.getValorUnit()));
        txt_marca.setText(cot.getMarcaPeca());
        ch_original.setSelected(cot.getItem_original());
    }

    private PedidosAlmoxarifadoCotacao popularCotacaoEditada(PedidosAlmoxarifadoCotacao cot) {
        cot.setValorUnit(Corretores.ConverterReaisDouble(txt_valor.getText()));
        cot.setItem_original(ch_original.isSelected());
        cot.setMarcaPeca(txt_marca.getText());
        return cot;
    }

    private void ocultarEditorCotacao() {
        CotacaoEditada = null;
        jPanel5.setVisible(false);
        txt_nSolicitacao.setText("");
        txt_nPedido.setText("");
        txt_nItem.setText("");
        txt_fornecedor.setText("");
        txt_valor.setText("");
        txt_marca.setText("");
    }

    private String getSQLWhere() {
        String s = "";
        if (txt_dtInicial.getDate() != null && txt_dtFinal.getDate() != null) {
            s += " and solic.data BETWEEN '" + Corretores.ConverterDateAAAA_MM_DD(txt_dtInicial.getDate()) + "' and '" + Corretores.ConverterDateAAAA_MM_DD(txt_dtFinal.getDate()) + "'";
        }
        if (!txt_idSolicitacao1.getText().equals("")) {
            s += " and solic.id = " + txt_idSolicitacao1.getText();
        }
        if (!txt_idFornecedor1.getText().equals("")) {
            s += " and cot.idFornecedor = " + txt_idFornecedor1.getText();
        }
        if (!txt_nPedido.getText().equals("")) {
            s += " and item.idPedido = " + txt_nPedido.getText();
        }
        if (!txt_idItemConsulta.getText().equals("")) {
            s += " and item.idItem = " + txt_idItemConsulta.getText();
        }
        if (!txt_codigoConsulta.getText().equals("")) {
            s += " and item.codigo like '%" + txt_codigoConsulta.getText() + "%'";
        }
        if (!txt_descricaoConsulta.getText().equals("")) {
            s += " and item.descricao like '%" + txt_descricaoConsulta.getText() + "%'";
        }
        if (!s.equals("")) {
            s = " WHERE " + s.replaceFirst("and", "");
        }
        return s;
    }

    private void popularBeans(List<PedidosAlmoxarifadoCotacao> listCotacao) {
        idFornecedor = new Integer(txt_idFornecedor.getText()); // Denifir ID Fornedor paa Teste
        for (int i = 0; i < TbImportacao.getRowCount(); i++) {
            TbPedidosCotacao cot = TbImportacao.getBeans(i);
            PedidoAlmoxarifadoItens itemPedido = (new PedidoAlmoxarifadoItens(cot.getIdItemPedido()));
            for (int c = 0; c < TbImportacao.getColumnCount(); c++) {
                PedidosAlmoxarifadoCotacao cotacao = new PedidosAlmoxarifadoCotacao();
                cotacao.setIdItemPedido(itemPedido);
                cotacao.setIdSolicitacao(solicitacao);
                cotacao.setIdFornecedor(idFornecedor);
                cotacao.setFornecedor(txt_nomeFantasia.getText());
                cotacao.setItem_selecionado(false);
                cotacao.setItem_comprado(false);
                cotacao.setQuant_cotacao(0.00);
                cotacao.setQuant_fechamento(0.00);
                switch (c) {
                    case 11:
                        if (cot.getValorOriginal() != null && cot.getValorOriginal() > 0) {
                            cotacao.setItem_original(true);
                            cotacao.setMarcaPeca("Original");
                            cotacao.setValorUnit(cot.getValorOriginal());
                            listCotacao.add(cotacao);
                        }
                        break;
                    case 14:
                        if (cot.getValorMarca1() != null && cot.getValorMarca1() > 0) {
                            cotacao.setItem_original(false);
                            cotacao.setMarcaPeca(cot.getMarca1());
                            cotacao.setValorUnit(cot.getValorMarca1());
                            listCotacao.add(cotacao);
                        }
                        break;
                    case 16:
                        if (cot.getValorMarca2() != null && cot.getValorMarca2() > 0) {
                            cotacao.setItem_original(false);
                            cotacao.setMarcaPeca(cot.getMarca2());
                            cotacao.setValorUnit(cot.getValorMarca2());
                            listCotacao.add(cotacao);
                        }
                        break;
                    default:
                }
            }
        }
        /* for (int j = 0; j < listCotacao.size(); j++) {
            System.out.println(listCotacao.get(j).toString());
        }*/
    }

    private TbPedidosCotacao popularCotacao(TbPedidosCotacao cotacao, PedidoAlmoxarifadoItens item) {
        cotacao.setCodigo(item.getCodigo());
        cotacao.setDescricao(item.getDescricao());
        cotacao.setIdItemPedido(item.getId());
        cotacao.setId_Pedido(item.getIdPedido().getId());
        cotacao.setId_cadastro(item.getIdItem());
        cotacao.setMarca1("");
        cotacao.setMarca2("");
        cotacao.setModelo("");
        cotacao.setQuantidade(item.getQuantidade());
        cotacao.setUnidade(item.getUnidade());
        cotacao.setValorMarca1(0.00);
        cotacao.setValorMarca2(0.00);
        cotacao.setValorOriginal(0.00);
        cotacao.setnItemPedido(item.getnItem());
        cotacao.setnSerie("");
        return cotacao;
    }

    private Boolean verificarCampos() {
        if (solicitacao == null) {
            JOptionPane.showMessageDialog(null, "O Campo Nº Solicitação deve ser preechido!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (txt_idFornecedor.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "O Campo Fornecedor deve ser preenchido!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }

        return true;
    }

    private boolean verificarBeans(List<PedidosAlmoxarifadoCotacao> listCotacaoB) {
        return true;
    }

    private void limparCampos() {
        txt_nomeFantasia.setText("");
        txt_data.setDate(null);
        txt_idFornecedor.setText("");
        txt_idSolicitacao.setText("");
        solicitacao = null;
        FornecedorB = null;
        TbImportacao.limpar();

    }

    private void desabilitarCampos() {
        btn_salvar.setEnabled(false);
        btn_lerDados.setEnabled(false);
        btn_pesqFornecedor.setEnabled(false);
        btn_pesqSolicitacao.setEnabled(false);
        txt_data.setEnabled(false);
    }

    private String selecionarArquivo() {
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel File", "xlsx", "XLSX");
        fc.setFileFilter(filter);
        int option = fc.showOpenDialog(tb_cotacao);
        if (option == JFileChooser.APPROVE_OPTION) {
            String fileName = fc.getSelectedFile().getAbsolutePath();
            return fileName;
        }
        return "";
    }

    private void lerArquivo() {
        String fileName = selecionarArquivo();
        int i = 0;
        int c = 0;
        if (!fileName.equals("")) {
            try {
                XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(fileName));
                XSSFSheet planilha = workbook.getSheetAt(0);
                TbImportacao.limpar();
                for (i = 6; i < planilha.getLastRowNum(); i++) {
                    TbPedidosCotacao cotacao = new TbPedidosCotacao();
                    for (c = 0; c < 21; c++) {
                        popularTabelaNovo(i, c, planilha, cotacao);
                    }
                    TbImportacao.addBeans(cotacao);
                }
                limparLinhasNulas(TbImportacao);
                btn_verificarCodigos.doClick();

            } catch (IOException | NumberFormatException | IllegalStateException | NullPointerException ex) {
                Logger.getLogger(FrmPedidosCotacao.class
                        .getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex + "! Verifique a Planilha na Linha:" + (i + 1) + " da Coluna:" + (c + 1), "Erro", 0, FormatarICO.ICObtnSair());
            }
        }
    }

    private void limparLinhasNulas(TableModelPedidoCotacao TbImportacao) {
        for (int j = TbImportacao.getRowCount(); j > 0; j--) {
            if (TbImportacao.getBeans(j - 1).getCodigo() != null) {
                break;
            }
            TbImportacao.excluirLinha(j - 1);
        }
    }

    private void popularTabela(int i, int c, XSSFSheet planilha, TbPedidosCotacao cotacao) throws IOException, NumberFormatException, NullPointerException, IllegalStateException {
        XSSFRow row = planilha.getRow(i);
        String IdItemPedido = trataRawValue(row.getCell(0).getRawValue());
        if (IdItemPedido != null && !IdItemPedido.equals("null")) {
            switch (c) { // coluna da planilha
                case 0:
                    cotacao.setIdItemPedido(Long.parseLong(trataRawValue(row.getCell(0).getRawValue())));
                case 1:
                    cotacao.setId_cadastro(new Integer((row.getCell(1).getRawValue())));
                case 2:
                    cotacao.setId_Pedido(Long.parseLong(trataRawValue(row.getCell(2).getRawValue())));
                case 3:
                    cotacao.setnItemPedido(Integer.parseInt(row.getCell(3).getStringCellValue()));
                case 4:
                    cotacao.setQuantidade(row.getCell(4).getNumericCellValue());
                case 5:
                    cotacao.setUnidade(row.getCell(5).getStringCellValue());
                case 6:
                    cotacao.setDescricao(row.getCell(6).getStringCellValue());
                case 7:
                    cotacao.setCodigo(row.getCell(7).getStringCellValue());
                case 8:
                    cotacao.setModelo(row.getCell(8).getStringCellValue());
                case 9:
                    cotacao.setnSerie(row.getCell(9).getStringCellValue());
                case 10:
                    cotacao.setValorOriginal(row.getCell(10).getNumericCellValue());
                case 11:
                // Coluna vazia
                case 12:
                    cotacao.setValorMarca1(row.getCell(12).getNumericCellValue());
                case 13:
                    cotacao.setMarca1(row.getCell(13).getStringCellValue());
                case 14:
                // coluna vazia
                case 15:
                    cotacao.setValorMarca2(row.getCell(15).getNumericCellValue());
                case 16:
                    cotacao.setMarca2(row.getCell(16).getStringCellValue());
                case 17:
                    cotacao.setPrazoEntrega(row.getCell(17).getStringCellValue());
                default:
            }
        } else {
            //throw new NullPointerException();
        }
    }

    private void popularTabelaNovo(int i, int c, XSSFSheet planilha, TbPedidosCotacao cotacao) throws IOException, NumberFormatException, NullPointerException, IllegalStateException {
        XSSFRow row = planilha.getRow(i);
        String IdItemPedido = null;
        if (row.getCell(0) != null) {
            IdItemPedido = trataRawValue((row.getCell(0).getRawValue()));
        }
        if (IdItemPedido != null) {
            switch (c) { // coluna da planilha
                case 0:
                    cotacao.setIdItemPedido(Long.parseLong(trataRawValue(row.getCell(c).getRawValue())));
                    break;
                case 1:
                    cotacao.setId_cadastro(new Integer((row.getCell(c).getRawValue())));
                    break;
                case 2:
                    cotacao.setId_Pedido(Long.parseLong(trataRawValue(row.getCell(c).getRawValue())));
                    break;
                case 3:
                    cotacao.setnItemPedido(Integer.parseInt(trataRawValue(row.getCell(c).getRawValue())));
                    break;
                case 4:
                    cotacao.setQuantidade(row.getCell(c).getNumericCellValue());
                    break;
                case 5:
                    cotacao.setUnidade(row.getCell(c).getStringCellValue());
                    break;
                case 6:
                    cotacao.setDescricao(row.getCell(c).getStringCellValue());
                    break;
                case 7:
                    cotacao.setCodigo(row.getCell(c).getStringCellValue());
                    break;
                case 8:
                    cotacao.setModelo(row.getCell(c).getStringCellValue());
                    break;
                case 9:
                    cotacao.setnSerie(row.getCell(c).getStringCellValue());
                    break;
                case 10: // Código Fornecedor Original;
                    cotacao.setCodFornecedorOrig(row.getCell(c).getStringCellValue());
                    break;
                case 11:
                    cotacao.setValorOriginal(row.getCell(c).getNumericCellValue());
                    break;
                case 12:
                // Coluna vazia
                case 13: // Códi fornecedor Marca-1
                    cotacao.setCodFornecedorMarca1(row.getCell(c).getStringCellValue());
                    break;
                case 14:
                    cotacao.setValorMarca1(row.getCell(c).getNumericCellValue());
                    break;
                case 15:
                    cotacao.setMarca1(row.getCell(c).getStringCellValue());
                    break;
                case 16:
                    break;
                case 17:
                    cotacao.setCodFornecedorMarca2(row.getCell(c).getStringCellValue());
                    break;
                case 18:
                    cotacao.setValorMarca2(row.getCell(c).getNumericCellValue());
                    break;
                case 19:
                    cotacao.setMarca2(row.getCell(c).getStringCellValue());
                    break;
                case 20:
                    cotacao.setPrazoEntrega(row.getCell(c).getStringCellValue());
                    break;
                default:
            }
        } else {
            //throw new NullPointerException();
        }
    }

    private String trataRawValue(String aValue) {
        String[] s = null;
        if (aValue == null) {
            return null;
        } else if (aValue.contains(".")) {
            s = aValue.split("[.]");
            return s[0];
        } else {
            return aValue;
        }
    }

    private Integer consultarCodigosFornecedor(Integer idFornecedor, TableModelPedidoCotacao TbImportacao, int columnCodF, int columnIdCodF) {
        List<CadItensAlmoxCodigos> listaCodigos = getListaCodigosCadastrados(idFornecedor, TbImportacao, columnCodF);
        // Preencer Lista Com ID dos Codigos Localizados;
        for (int i = 0; i < TbImportacao.getRowCount(); i++) {
            TbPedidosCotacao cot = TbImportacao.getBeans(i);
            for (CadItensAlmoxCodigos c : listaCodigos) {
                if (Objects.equals(cot.getId_cadastro(), c.getId_item().getID()) && Objects.equals(TbImportacao.getValueAt(i, columnCodF), c.getCodigo())) {
                    TbImportacao.setValueAt(c.getId_item().getID(), i, columnIdCodF);
                    break;
                }
            }
        }
        int contador = 0;
        for (int i = 0; i < TbImportacao.getRowCount(); i++) {
            if (TbImportacao.getValueAt(i, columnCodF) != null && TbImportacao.getValueAt(i, columnCodF).toString().equals("") == false) {
                if ((TbImportacao.getValueAt(i, columnIdCodF) == null)) {
                    contador += 1;
                }
            }
        }
        return contador;
    }

    private Boolean cadastrarConversao(TableModelPedidoCotacao TbImportacao, int columnCodF, int columnIdCodF) {
        for (int i = 0; i < TbImportacao.getRowCount(); i++) {
            if (TbImportacao.getValueAt(i, columnCodF) != null && TbImportacao.getValueAt(i, columnCodF).toString().equals("") == false) {
                if (((Integer) (TbImportacao.getValueAt(i, columnIdCodF)) == null)) {
                    CadItensAlmoxCodigos conversao = new CadItensAlmoxCodigos();
                    conversao.setCNPJ_Fornecedor(FornecedorB.getCnpj());
                    conversao.setCodigo(TbImportacao.getValueAt(i, columnCodF).toString());
                    conversao.setCodigoCatalogo((columnCodF == TbImportacao.COD_F_ORIG));
                    conversao.setFornecedor(FornecedorB.getRazaoSocial());
                    conversao.setID_Fornecedor(FornecedorB.getID());
                    conversao.setId_fornecedor(FornecedorB);
                    conversao.setId_item(new CadItensAlmoxarifadoBeans((Integer) (TbImportacao.getValueAt(i, TbImportacao.CADASTRO))));
                    if (CadAlmoxD.CadastrarConversaoEmLote(conversao)) {
                        TbImportacao.setValueAt(conversao.getId_item().getID(), i, columnIdCodF);
                    }
                }
            }
        }
        return true;
    }

    private List<CadItensAlmoxCodigos> getListaCodigosCadastrados(Integer idFornecedor, TableModelPedidoCotacao TbImportacao, int columnCodF) {
        List<CadItensAlmoxCodigos> listaCodigos = new ArrayList<>();
        String sCodigos = "";
        for (int i = 0; i < TbImportacao.getRowCount(); i++) {
            if (TbImportacao.getValueAt(i, columnCodF) != null && !TbImportacao.getValueAt(i, columnCodF).toString().equals("")) {
                sCodigos += ", '" + TbImportacao.getValueAt(i, columnCodF).toString() + "'";
            }
        }
        if (!sCodigos.equals("")) {
            sCodigos = "(" + sCodigos.replaceFirst(",", "") + ")";
            // System.out.println(sCodigos);
            listaCodigos = CadAlmoxD.buscarListaDeCodigos(sCodigos, idFornecedor);
        }
        return listaCodigos;
    }

    private class CellRenderTabela extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            Object val = table.getValueAt(row, column);
            if (val instanceof Double) {
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
            if (column == 10 || column == 11) {
                setForeground(Color.black);
                setBackground(new Color(255, 222, 163, 255));
            } else if (column == 12 || column == 13 || column == 14) {
                setForeground(Color.black);
                setBackground(new Color(0, 204, 153, 255));
            }
            return renderer;
        }
    }

}
