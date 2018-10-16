/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Beans.ListSetorTrabalhoBeans;
import Beans.PedidoAlmoxarifadoItens;
import Beans.PedidosAlmoxarifadoSolicitacao;
import Beans.PropriedadesBeans;
import DAO.Diversas;
import DAO.DiversasHibernate;
import DAO.PedidosAlmoxarifadoDAO;
import static GUI.Principal.listSetorTrabalho;
import static GUI.Principal.listStatusItemPedido;
import static GUI.Principal.listaFazPermitida;
import static GUI.frmLogin.IdUsuario;
import static GUI.frmLogin.NomeUsuario;
import static GUI.frmLogin.dataAtual;
import Icones.FormatarICO;
import TableModel.TableModelPedidoMercadoria;
import TableModel.TableModelResumoSolicitacao;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import Utilitarios.FrmProgressBar;
import Utilitarios.ThreadLoadProgressBar;
import Utilitarios.ValidarPermissoes;
import com.toedter.calendar.JDateChooser;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import jxl.WorkbookSettings;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FrmPedidosSolicitacao extends javax.swing.JInternalFrame {

    TableModelPedidoMercadoria TbItensSolicitacao;
    TableModelResumoSolicitacao TbResumo;
    TableModelPedidoMercadoria TbItensResumo;
    TableModelPedidoMercadoria TbConsulta;
    CentralizarTabela Centralizar;
    PedidosAlmoxarifadoSolicitacao SolicitacaoB;
    PedidosAlmoxarifadoDAO PedidosD;
    Diversas DiversasD;
    ThreadLoadProgressBar thread;
    FrmProgressBar frm;

    public FrmPedidosSolicitacao() {
        initComponents();
        Centralizar = new CentralizarTabela();
        PedidosD = new PedidosAlmoxarifadoDAO();
        DiversasD = new Diversas();
        carregarTabela();
        txt_data.setDate(new Date(System.currentTimeMillis()));
        txt_usuario.setText(NomeUsuario);
        carregarStatusItem();
        carregarSetores();
        carregarFazPermitidas();
        carregarTabelaConsulta();
        carregarTabelaResumo();
        carregarTabelaItensResumo();
        tb_itens.setVisible(false);
        jScrollPane4.setVisible(false);

        Calendar hoje = Calendar.getInstance();
        hoje.setTime(new Date(System.currentTimeMillis()));
        txt_dtFinalResumo.setDate(hoje.getTime());
        hoje.add(Calendar.DAY_OF_MONTH, -90);
        txt_dtInicialResumo.setDate(hoje.getTime());
        jMenuEnviarRemessa.setVisible(false); // mater oculta para ativar apenas quando acessar via remessa
    }

    private void carregarStatusItem() {
        if (listStatusItemPedido == null) {
            listStatusItemPedido = DiversasD.ListarStatusItemPedido();
        }

    }

    private void carregarFazPermitidas() {
        if (listaFazPermitida == null) {
            listaFazPermitida = DiversasHibernate.getListaFazendasPermitidas();
        }
        cb_fazenda.addItem(new PropriedadesBeans(0, "-"));
        cb_fazendaResumo.addItem(new PropriedadesBeans(0, "-"));
        for (PropriedadesBeans list : listaFazPermitida) {
            cb_fazenda.addItem(list);
            cb_fazendaResumo.addItem(list);
        }
    }

    private void carregarSetores() {
        if (listSetorTrabalho == null) {
            listSetorTrabalho = DiversasD.ListSetoresTrabalho();
        }
        ListSetorTrabalhoBeans l = new ListSetorTrabalhoBeans();
        l.setID(0);
        l.setDescricao("-");
        cb_setorConsulta.addItem(l);
        cb_setorResumo.addItem(l);
        for (Beans.ListSetorTrabalhoBeans list : listSetorTrabalho) {
            cb_setorConsulta.addItem(list);
            cb_setorResumo.addItem(list);
        }
    }

    private TableModel getTableModel() {
        if (TbItensSolicitacao == null) {
            TbItensSolicitacao = new TableModelPedidoMercadoria();
        }
        return TbItensSolicitacao;
    }

    private TableModel getTableModelConsulta() {
        if (TbConsulta == null) {
            TbConsulta = new TableModelPedidoMercadoria();
        }
        return TbConsulta;
    }

    private TableModel getTableModelItensResumo() {
        if (TbItensResumo == null) {
            TbItensResumo = new TableModelPedidoMercadoria();
        }
        return TbItensResumo;
    }

    private TableModel getTableModelResumo() {
        if (TbResumo == null) {
            TbResumo = new TableModelResumoSolicitacao();
        }
        return TbResumo;
    }

    private JTable carregarTabela() {
        tb_solicitacao.setModel(getTableModel());
        ((DefaultTableCellRenderer) tb_solicitacao.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        Centralizar.centralizarTabela(tb_solicitacao);
        //cellRenderer = new TableModelCellRenderer();
        for (int c = 0; c < TbItensSolicitacao.getColumnCount(); c++) {
            if (c == TbItensSolicitacao.ID || c == TbItensSolicitacao.ID_COMPRA || c == TbItensSolicitacao.ID_INVENTARIO
                    || c == TbItensSolicitacao.ID_SETOR || c == TbItensSolicitacao.ID_SOLICITANTE || c == TbItensSolicitacao.ID_STATUS_ITEM
                    || c == TbItensSolicitacao.ID_URGENCIA) {
                tb_solicitacao.getColumnModel().getColumn(c).setMaxWidth(0);
                tb_solicitacao.getColumnModel().getColumn(c).setMinWidth(0);
                tb_solicitacao.getColumnModel().getColumn(c).setPreferredWidth(0);
            }
        }
        tb_solicitacao.getColumnModel().getColumn(TbItensSolicitacao.N_ITEM).setPreferredWidth(80);
        tb_solicitacao.getColumnModel().getColumn(TbItensSolicitacao.CODIGO).setPreferredWidth(90);
        tb_solicitacao.getColumnModel().getColumn(TbItensSolicitacao.DESCRICAO).setPreferredWidth(180);
        tb_solicitacao.getColumnModel().getColumn(TbItensSolicitacao.INVENTARIO).setPreferredWidth(100);
        tb_solicitacao.getColumnModel().getColumn(TbItensSolicitacao.STATUS_ITEM).setPreferredWidth(80);
        tb_solicitacao.getColumnModel().getColumn(TbItensSolicitacao.ID_ITEM).setPreferredWidth(80);
        tb_solicitacao.getColumnModel().getColumn(TbItensSolicitacao.QUANTIDADE).setPreferredWidth(70);

        return tb_solicitacao;
    }

    private JTable carregarTabelaResumo() {
        tb_resumo.setModel(getTableModelResumo());
        ((DefaultTableCellRenderer) tb_resumo.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        Centralizar.centralizarTabela(tb_resumo);
        //cellRenderer = new TableModelCellRenderer();
        return tb_resumo;
    }

    private JTable carregarTabelaItensResumo() {
        tb_itens.setModel(getTableModelItensResumo());
        ((DefaultTableCellRenderer) tb_itens.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        Centralizar.centralizarTabela(tb_itens);
        //cellRenderer = new TableModelCellRenderer();
        for (int c = 0; c < TbItensResumo.getColumnCount(); c++) {
            if (c == TbItensResumo.ID || c == TbItensResumo.ID_COMPRA || c == TbItensResumo.ID_INVENTARIO
                    || c == TbItensResumo.ID_SETOR || c == TbItensResumo.ID_SOLICITANTE || c == TbItensResumo.ID_STATUS_ITEM
                    || c == TbItensResumo.ID_URGENCIA || c == TbItensResumo.DATA || c == TbItensResumo.SOLICITANTE
                    || c == TbItensResumo.ID_ITEM || c == TbItensResumo.UNIDADE || c == TbItensResumo.SETOR || c == TbItensResumo.ID_SOLICITACAO || c == TbItensResumo.URGENCIA) {
                tb_itens.getColumnModel().getColumn(c).setMaxWidth(0);
                tb_itens.getColumnModel().getColumn(c).setMinWidth(0);
                tb_itens.getColumnModel().getColumn(c).setPreferredWidth(0);
            }
        }
        tb_itens.getColumnModel().getColumn(TbItensResumo.FAZENDA).setPreferredWidth(60);
        tb_itens.getColumnModel().getColumn(TbItensResumo.N_ITEM).setPreferredWidth(60);
        tb_itens.getColumnModel().getColumn(TbItensResumo.CODIGO).setPreferredWidth(90);
        tb_itens.getColumnModel().getColumn(TbItensResumo.DESCRICAO).setPreferredWidth(180);
        tb_itens.getColumnModel().getColumn(TbItensResumo.INVENTARIO).setPreferredWidth(100);
        tb_itens.getColumnModel().getColumn(TbItensResumo.STATUS_ITEM).setPreferredWidth(80);
        tb_itens.getColumnModel().getColumn(TbItensResumo.QUANTIDADE).setPreferredWidth(70);

        return tb_itens;
    }

    private JTable carregarTabelaConsulta() {
        tb_consulta.setModel(getTableModelConsulta());
        ((DefaultTableCellRenderer) tb_consulta.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        Centralizar.centralizarTabela(tb_consulta);
        //cellRenderer = new TableModelCellRenderer();
        for (int c = 0; c < TbConsulta.getColumnCount(); c++) {
            if (c == TbConsulta.ID || c == TbConsulta.ID_COMPRA || c == TbConsulta.ID_INVENTARIO
                    || c == TbConsulta.ID_SETOR || c == TbConsulta.ID_SOLICITANTE || c == TbConsulta.ID_STATUS_ITEM
                    || c == TbConsulta.ID_URGENCIA) {
                tb_consulta.getColumnModel().getColumn(c).setMaxWidth(0);
                tb_consulta.getColumnModel().getColumn(c).setMinWidth(0);
                tb_consulta.getColumnModel().getColumn(c).setPreferredWidth(0);
            }
        }
        tb_consulta.getColumnModel().getColumn(TbConsulta.N_ITEM).setPreferredWidth(80);
        tb_consulta.getColumnModel().getColumn(TbConsulta.CODIGO).setPreferredWidth(90);
        tb_consulta.getColumnModel().getColumn(TbConsulta.DESCRICAO).setPreferredWidth(180);
        tb_consulta.getColumnModel().getColumn(TbConsulta.INVENTARIO).setPreferredWidth(100);
        tb_consulta.getColumnModel().getColumn(TbConsulta.STATUS_ITEM).setPreferredWidth(80);
        tb_consulta.getColumnModel().getColumn(TbConsulta.ID_ITEM).setPreferredWidth(80);
        tb_consulta.getColumnModel().getColumn(TbConsulta.QUANTIDADE).setPreferredWidth(70);

        return tb_consulta;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupSolicitacao = new javax.swing.JPopupMenu();
        jMenuExcluir = new javax.swing.JMenuItem();
        jPopupConsulta = new javax.swing.JPopupMenu();
        jMenuExportar = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenuEnviarRemessa = new javax.swing.JMenuItem();
        jPopupResumo = new javax.swing.JPopupMenu();
        jMenuExportar1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuStstus = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenuAnalise = new javax.swing.JMenuItem();
        jPopupResumoItens = new javax.swing.JPopupMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuStatusComprado = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuStatusRecebido = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuStatusEnviado = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuStatusCancelado = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuExcluirItem = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        txt_data =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        txt_usuario = new javax.swing.JTextField();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        txt_descricao = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_solicitacao = new javax.swing.JTable();
        btn_novo = new javax.swing.JButton();
        btn_salvar = new javax.swing.JButton();
        btn_editarPedido = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel13 = new javax.swing.JLabel();
        txt_dtInicialResumo =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel14 = new javax.swing.JLabel();
        txt_dtFinalResumo =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel15 = new javax.swing.JLabel();
        txt_nSolicitacao1 = new javax.swing.JTextField();
        javax.swing.JLabel jLabel16 = new javax.swing.JLabel();
        txt_nPedido1 = new javax.swing.JTextField();
        javax.swing.JLabel jLabel18 = new javax.swing.JLabel();
        cb_setorResumo = new javax.swing.JComboBox<>();
        javax.swing.JLabel lbl_fazenda1 = new javax.swing.JLabel();
        cb_fazendaResumo = new javax.swing.JComboBox<>();
        btn_pesquisarResumo = new javax.swing.JButton();
        ch_status = new javax.swing.JCheckBox();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_itens = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tb_resumo = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        txt_dtInicial =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        txt_dtFinal =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        txt_nSolicitacao = new javax.swing.JTextField();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        txt_nPedido = new javax.swing.JTextField();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        txt_codigoConsulta = new javax.swing.JTextField();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        cb_setorConsulta = new javax.swing.JComboBox<>();
        javax.swing.JLabel lbl_fazenda = new javax.swing.JLabel();
        cb_fazenda = new javax.swing.JComboBox<>();
        btn_pesquisar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_consulta = new javax.swing.JTable();

        jMenuExcluir.setText("Excluir Item");
        jMenuExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuExcluirActionPerformed(evt);
            }
        });
        jPopupSolicitacao.add(jMenuExcluir);

        jMenuExportar.setText("Exportar Planilha");
        jMenuExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuExportarActionPerformed(evt);
            }
        });
        jPopupConsulta.add(jMenuExportar);
        jPopupConsulta.add(jSeparator6);

        jMenuEnviarRemessa.setText("Enviar p/ Remessa");
        jPopupConsulta.add(jMenuEnviarRemessa);

        jMenuExportar1.setText("Exportar Planilha");
        jMenuExportar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuExportar1ActionPerformed(evt);
            }
        });
        jPopupResumo.add(jMenuExportar1);
        jPopupResumo.add(jSeparator1);

        jMenuStstus.setText("Alterar Status");
        jMenuStstus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuStstusActionPerformed(evt);
            }
        });
        jPopupResumo.add(jMenuStstus);
        jPopupResumo.add(jSeparator7);

        jMenuAnalise.setText("Abrir Análise");
        jMenuAnalise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuAnaliseActionPerformed(evt);
            }
        });
        jPopupResumo.add(jMenuAnalise);

        jMenu1.setText("Alterar Status P/");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jMenuStatusComprado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuStatusComprado.setText("Comprado");
        jMenuStatusComprado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuStatusCompradoActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuStatusComprado);
        jMenu1.add(jSeparator2);

        jMenuStatusRecebido.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuStatusRecebido.setText("Recebido");
        jMenuStatusRecebido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuStatusRecebidoActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuStatusRecebido);
        jMenu1.add(jSeparator3);

        jMenuStatusEnviado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuStatusEnviado.setText("Enviado");
        jMenuStatusEnviado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuStatusEnviadoActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuStatusEnviado);
        jMenu1.add(jSeparator4);

        jMenuStatusCancelado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuStatusCancelado.setText("Cancelado");
        jMenuStatusCancelado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuStatusCanceladoActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuStatusCancelado);

        jPopupResumoItens.add(jMenu1);
        jPopupResumoItens.add(jSeparator5);

        jMenuExcluirItem.setText("Excluir Item");
        jMenuExcluirItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuExcluirItemActionPerformed(evt);
            }
        });
        jPopupResumoItens.add(jMenuExcluirItem);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Solicitações");

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Data");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Usuario");

        txt_usuario.setEditable(false);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Descrição");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_data, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(433, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(txt_data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txt_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txt_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tb_solicitacao.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_solicitacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_solicitacaoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_solicitacao);

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_editarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Criar Solicitação", jPanel2);

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Data");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("à");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Nº Solicitação");

        txt_nSolicitacao1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Nº Pedido");

        txt_nPedido1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Setor");

        cb_setorResumo.setPreferredSize(new java.awt.Dimension(90, 20));

        lbl_fazenda1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_fazenda1.setText("Fazenda");

        cb_fazendaResumo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_fazendaResumoItemStateChanged(evt);
            }
        });

        btn_pesquisarResumo.setText("Pesquisar");
        btn_pesquisarResumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisarResumoActionPerformed(evt);
            }
        });

        ch_status.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_status.setText("Status");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_dtInicialResumo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_dtFinalResumo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nSolicitacao1, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nPedido1, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_setorResumo, 0, 135, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_fazenda1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cb_fazendaResumo, 0, 143, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ch_status)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_pesquisarResumo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel13)
                    .addComponent(txt_dtInicialResumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txt_dtFinalResumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txt_nSolicitacao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txt_nPedido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(cb_setorResumo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_fazenda1)
                    .addComponent(cb_fazendaResumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesquisarResumo)
                    .addComponent(ch_status))
                .addContainerGap())
        );

        tb_itens.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_itens.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_itensMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tb_itens);

        jScrollPane5.setPreferredSize(new java.awt.Dimension(350, 402));

        tb_resumo.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_resumo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_resumoMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tb_resumo);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane4)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Consulta Status", jPanel5);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Data");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("à");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Nº Solicitação");

        txt_nSolicitacao.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Nº Pedido");

        txt_nPedido.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Código");

        txt_codigoConsulta.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Setor");

        cb_setorConsulta.setPreferredSize(new java.awt.Dimension(90, 20));

        lbl_fazenda.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_fazenda.setText("Fazenda");

        cb_fazenda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_fazendaItemStateChanged(evt);
            }
        });

        btn_pesquisar.setText("Pesquisar");
        btn_pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_dtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nSolicitacao, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_codigoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_setorConsulta, 0, 121, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_fazenda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cb_fazenda, 0, 126, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_pesquisar)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6)
                    .addComponent(txt_dtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txt_dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txt_nSolicitacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txt_nPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txt_codigoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(cb_setorConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_fazenda)
                    .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesquisar))
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Consulta Itens", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed
        novo();
    }//GEN-LAST:event_btn_novoActionPerformed

    private void btn_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(null, "Deseja Salvar esta Solicitacao de Mercadorias?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION) {
            SolicitacaoB = new PedidosAlmoxarifadoSolicitacao();
            popularBeans(SolicitacaoB);
            if (verificarBeans(SolicitacaoB) && ValidarPermissoes.validarPermissaoInsert(FrmPedidosSolicitacao.class.getSimpleName())) {
                if (verificarStatusItem(PedidosD.verificarStatusItem(TbItensSolicitacao.getLista()))) {
                    if (PedidosD.salvarSolicitacao(SolicitacaoB)) {
                        if (exportarPlanilha(TbItensSolicitacao, SolicitacaoB.getId()) == true) {
                            JOptionPane.showMessageDialog(null, "Planilha Exportada Com Sucesso!", "Erro", 0, FormatarICO.ICObtnOk());
                        } else {
                            JOptionPane.showMessageDialog(null, "Erro ao Exportar Planilha", "Erro", 0, FormatarICO.ICObtnSair());
                        }
                        limparCampos();
                        this.dispose();
                    }
                }
            }
        }
    }//GEN-LAST:event_btn_salvarActionPerformed

    private void btn_editarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarPedidoActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja Editar esta Entrada?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION) {
            popularBeans(SolicitacaoB);
            if (verificarBeans(SolicitacaoB) && ValidarPermissoes.validarPermissaoUpdate(FrmPedidosSolicitacao.class.getSimpleName())) {
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

    private void tb_solicitacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_solicitacaoMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            if (jPopupSolicitacao.isVisible() == true) {
                jPopupSolicitacao.setVisible(false);
            } else {
                jPopupSolicitacao.setVisible(true);
                jPopupSolicitacao.show(this, 0, 0);
                jPopupSolicitacao.setLocation(evt.getLocationOnScreen());
            }
        }
    }//GEN-LAST:event_tb_solicitacaoMouseClicked

    private void jMenuExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuExcluirActionPerformed
        int excluirSaidaInsumos = JOptionPane.showConfirmDialog(null, "Atenção, Deseja Excluir este Item da Lista?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (excluirSaidaInsumos == JOptionPane.YES_OPTION) {
            int index = tb_solicitacao.getSelectedRow();
            if (index >= 0) {
                TbItensSolicitacao.excluirLinha(index);
            }
        }
    }//GEN-LAST:event_jMenuExcluirActionPerformed

    private void cb_fazendaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_fazendaItemStateChanged

    }//GEN-LAST:event_cb_fazendaItemStateChanged

    private void btn_pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisarActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        thread = new ThreadLoadProgressBar() {
            @Override
            public void run() {
                TbConsulta.limpar();
                TbConsulta.addLista(PedidosD.ConsultaSolicitacoes(getSqlWhere(), thread.dialog));
                thread.dialog.dispose();
            }
        };
        this.setCursor(null);
    }//GEN-LAST:event_btn_pesquisarActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        if (jTabbedPane1.getSelectedIndex() == 1) {
            getRootPane().setDefaultButton(btn_pesquisar);
        } else {
            getRootPane().setDefaultButton(null);
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

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

    private void jMenuExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuExportarActionPerformed
        int index = tb_consulta.getSelectedRow();
        if (index >= 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Long idSolicitacao = (Long) TbConsulta.getValueAt(index, TbConsulta.ID_SOLICITACAO);
            thread = new ThreadLoadProgressBar() {
                @Override
                public void run() {
                    PedidosAlmoxarifadoSolicitacao solicitacao = PedidosD.buscarSolicitacaoPorCodigo(idSolicitacao, thread.dialog);
                    thread.dialog.dispose();
                    exportarPlanilha(new TableModelPedidoMercadoria(solicitacao.getListaSolicitacao()), solicitacao.getId());
                }
            };
            this.setCursor(null);
        }
    }//GEN-LAST:event_jMenuExportarActionPerformed

    private void cb_fazendaResumoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_fazendaResumoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_fazendaResumoItemStateChanged

    private void btn_pesquisarResumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisarResumoActionPerformed
        TbResumo.limpar();
        TbResumo.addLista(PedidosD.resumoSolicitacao(getWhereResumo()));
        tb_itens.setVisible(false);
        jScrollPane4.setVisible(false);
        this.revalidate();
        this.repaint();
    }//GEN-LAST:event_btn_pesquisarResumoActionPerformed

    private void tb_resumoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_resumoMouseClicked
        int rowIndex = tb_resumo.getSelectedRow();
        if (evt.getClickCount() == 2) {
            tb_itens.setVisible(true);
            jScrollPane4.setVisible(true);
            this.revalidate();
            this.repaint();
            TbItensResumo.limpar();
            TbItensResumo.addLista(PedidosD.ConsultaPedidos(" and pi.id_solicitacao = " + TbResumo.getValueAt(rowIndex, TbResumo.ID)));
        }
        if (evt.getButton() == MouseEvent.BUTTON3) {
            if (jPopupResumo.isVisible() == true) {
                jPopupResumo.setVisible(false);
            } else {
                jPopupResumo.setVisible(true);
                jPopupResumo.show(this, 0, 0);
                jPopupResumo.setLocation(evt.getLocationOnScreen());
            }
        }
    }//GEN-LAST:event_tb_resumoMouseClicked

    private void jMenuExportar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuExportar1ActionPerformed
        int index = tb_resumo.getSelectedRow();
        if (index >= 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Long idSolicitacao = (Long) TbResumo.getValueAt(index, TbResumo.ID);
            thread = new ThreadLoadProgressBar() {
                @Override
                public void run() {
                    PedidosAlmoxarifadoSolicitacao solicitacao = PedidosD.buscarSolicitacaoPorCodigo(idSolicitacao, thread.dialog);
                    thread.dialog.dispose();
                    exportarPlanilha(new TableModelPedidoMercadoria(solicitacao.getListaSolicitacao()), solicitacao.getId());
                }
            };
            this.setCursor(null);
        }
    }//GEN-LAST:event_jMenuExportar1ActionPerformed

    private void jMenuStstusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuStstusActionPerformed
        int rowIndex = tb_resumo.getSelectedRow();
        if (rowIndex >= 0) {
            alterarStatus(rowIndex);
        }
    }//GEN-LAST:event_jMenuStstusActionPerformed

    private void tb_itensMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_itensMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            if (jPopupResumoItens.isVisible() == true) {
                jPopupResumoItens.setVisible(false);
            } else {
                jPopupResumoItens.setVisible(true);
                jPopupResumoItens.show(this, 0, 0);
                jPopupResumoItens.setLocation(evt.getLocationOnScreen());
            }
        }
    }//GEN-LAST:event_tb_itensMouseClicked

    private void jMenuStatusCompradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuStatusCompradoActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "<html>Está Ação irá alterar o Status dos Itens Selecionados, <br><B>Deseja Continuar? </B> </html>", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION && ValidarPermissoes.validarPermissaoUpdate(FrmPedidosSolicitacao.class.getSimpleName())) {
            PedidosD.editarStatusItem(listaSelecionadaResumo(tb_itens), 3);
        }
    }//GEN-LAST:event_jMenuStatusCompradoActionPerformed

    private void jMenuStatusRecebidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuStatusRecebidoActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "<html>Está Ação irá alterar o Status dos Itens Selecionados, <br><B>Deseja Continuar? </B> </html>", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION && ValidarPermissoes.validarPermissaoUpdate(FrmPedidosSolicitacao.class.getSimpleName())) {
            PedidosD.editarStatusItem(listaSelecionadaResumo(tb_itens), 4);
        }
    }//GEN-LAST:event_jMenuStatusRecebidoActionPerformed

    private void jMenuStatusEnviadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuStatusEnviadoActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "<html>Está Ação irá alterar o Status dos Itens Selecionados, <br><B>Deseja Continuar? </B> </html>", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION && ValidarPermissoes.validarPermissaoUpdate(FrmPedidosSolicitacao.class.getSimpleName())) {
            PedidosD.editarStatusItem(listaSelecionadaResumo(tb_itens), 5);
        }
    }//GEN-LAST:event_jMenuStatusEnviadoActionPerformed

    private void jMenuStatusCanceladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuStatusCanceladoActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "<html>Está Ação irá alterar o Status dos Itens Selecionados, <br><B>Deseja Continuar? </B> </html>", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION && ValidarPermissoes.validarPermissaoUpdate(FrmPedidosSolicitacao.class.getSimpleName())) {
            PedidosD.editarStatusItem(listaSelecionadaResumo(tb_itens), 6);
        }
    }//GEN-LAST:event_jMenuStatusCanceladoActionPerformed

    private void jMenuExcluirItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuExcluirItemActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "<html>Está Ação irá Excluir Permanentemente Este Item, <br><B>Deseja Continuar? </B> </html>", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION && ValidarPermissoes.validarPermissaoDelete(FrmPedidosSolicitacao.class.getSimpleName())) {
            int rowIndex = tb_itens.getSelectedRow();
            if (rowIndex >= 0) {
                PedidoAlmoxarifadoItens item = TbItensResumo.getBeans(rowIndex);
                excluirItemSolicitacao(item, rowIndex);
            }
        }
    }//GEN-LAST:event_jMenuExcluirItemActionPerformed

    private void jMenuAnaliseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAnaliseActionPerformed
     int rowIndex = tb_resumo.getSelectedRow();
     if (rowIndex >= 0){
         abrirAnalise(TbResumo.getBeans(rowIndex).getSolic());
     }
    }//GEN-LAST:event_jMenuAnaliseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btn_editarPedido;
    javax.swing.JButton btn_excluir;
    javax.swing.JButton btn_novo;
    public javax.swing.JButton btn_pesquisar;
    public javax.swing.JButton btn_pesquisarResumo;
    javax.swing.JButton btn_salvar;
    public javax.swing.JComboBox<PropriedadesBeans> cb_fazenda;
    private javax.swing.JComboBox<PropriedadesBeans> cb_fazendaResumo;
    private javax.swing.JComboBox<ListSetorTrabalhoBeans> cb_setorConsulta;
    private javax.swing.JComboBox<ListSetorTrabalhoBeans> cb_setorResumo;
    private javax.swing.JCheckBox ch_status;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuAnalise;
    public javax.swing.JMenuItem jMenuEnviarRemessa;
    private javax.swing.JMenuItem jMenuExcluir;
    private javax.swing.JMenuItem jMenuExcluirItem;
    private javax.swing.JMenuItem jMenuExportar;
    private javax.swing.JMenuItem jMenuExportar1;
    private javax.swing.JMenuItem jMenuStatusCancelado;
    private javax.swing.JMenuItem jMenuStatusComprado;
    private javax.swing.JMenuItem jMenuStatusEnviado;
    private javax.swing.JMenuItem jMenuStatusRecebido;
    private javax.swing.JMenuItem jMenuStstus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPopupMenu jPopupConsulta;
    private javax.swing.JPopupMenu jPopupResumo;
    private javax.swing.JPopupMenu jPopupResumoItens;
    private javax.swing.JPopupMenu jPopupSolicitacao;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    public javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JTable tb_consulta;
    private javax.swing.JTable tb_itens;
    private javax.swing.JTable tb_resumo;
    public javax.swing.JTable tb_solicitacao;
    private javax.swing.JTextField txt_codigoConsulta;
    private com.toedter.calendar.JDateChooser txt_data;
    private javax.swing.JTextField txt_descricao;
    private com.toedter.calendar.JDateChooser txt_dtFinal;
    private com.toedter.calendar.JDateChooser txt_dtFinalResumo;
    private com.toedter.calendar.JDateChooser txt_dtInicial;
    private com.toedter.calendar.JDateChooser txt_dtInicialResumo;
    private javax.swing.JTextField txt_nPedido;
    private javax.swing.JTextField txt_nPedido1;
    private javax.swing.JTextField txt_nSolicitacao;
    private javax.swing.JTextField txt_nSolicitacao1;
    private javax.swing.JTextField txt_usuario;
    // End of variables declaration//GEN-END:variables

    private void abrirAnalise(PedidosAlmoxarifadoSolicitacao solic){
        FrmPedidosAnalise frmAnalise = new FrmPedidosAnalise();
        this.getParent().add(frmAnalise);
        frmAnalise.setVisible(true);
        frmAnalise.solicitacao = solic;
        frmAnalise.txt_idSolicitacao.setText(solic.getId().toString());
        frmAnalise.btn_buscarAnalise.doClick();
        
    }
    
    private List<PedidoAlmoxarifadoItens> listaSelecionadaResumo(JTable tb_resumo) {
        List<PedidoAlmoxarifadoItens> listaItens = new ArrayList<>();
        int[] linhas = tb_resumo.getSelectedRows();
        for (int i = 0; i < linhas.length; i++) {
            listaItens.add(TbItensResumo.getBeans(linhas[i]));
        }
        /* for(PedidoAlmoxarifadoItens i : listaItens){
            System.out.println("ID ITEM: " + i.getDescricao());
        }*/
        return listaItens;
    }

    private void excluirItemSolicitacao(PedidoAlmoxarifadoItens item, int rowIndex) {
        if (item.getIdSolicitacao().getStatus() == false) {
            if (item.getIdStatusItem() != 3 && item.getIdStatusItem() != 4 && item.getIdStatusItem() != 5) { // Verificar se o ID do Status é Diferente de Comprado, Recebido e Enviado 
                if (PedidosD.excluirItemSolicitacao(item)) { // DAO para Excluir Item
                    TbItensResumo.excluirLinha(rowIndex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Para Excluir Este Item é Necessário que o Status seja Diferente de Comprado, Recebido ou Enviado", "Salvo", 0, FormatarICO.ICObtnSair());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Para Excluir Este Item é Necessário Marcar A Solicitacao Como Aberta!", "Salvo", 0, FormatarICO.ICObtnSair());
        }
    }

    private void alterarStatus(int rowIndex) {
        Long idSolicitacao = (Long) TbResumo.getValueAt(rowIndex, TbResumo.ID);
        Boolean status = !(Boolean) TbResumo.getValueAt(rowIndex, TbResumo.STATUS);
        int editarStatus = JOptionPane.showConfirmDialog(null, "Deseja Alterar o Status Desta Solicitacão!", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editarStatus == JOptionPane.YES_OPTION) {
            if (PedidosD.editarStatusSolicitacao(idSolicitacao, status)) {
                TbResumo.setValueAt(status, rowIndex, TbResumo.STATUS);
            }
        }
    }

    private void popularBeans(PedidosAlmoxarifadoSolicitacao beans) {
        beans.setData(txt_data.getDate());
        beans.setIdUsuario(IdUsuario);
        beans.setDescricao(txt_descricao.getText());
        beans.setListaSolicitacao(TbItensSolicitacao.getLista());
        beans.setStatus(false);
    }

    public void novo() {
        btn_salvar.setEnabled(true);
        btn_editarPedido.setEnabled(false);
        btn_excluir.setEnabled(false);
        habilitarCampos();
        limparCampos();
    }

    private void desabilitarCampos() {
        btn_salvar.setEnabled(false);
        btn_editarPedido.setEnabled(false);
        btn_excluir.setEnabled(false);
    }

    private void limparCampos() {
        TbItensSolicitacao.limpar();
    }

    private void habilitarCampos() {

    }

    private boolean verificarBeans(PedidosAlmoxarifadoSolicitacao beans) {
        if (beans.getData() == null) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Data!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (beans.getDescricao().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Descrição!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (beans.getIdUsuario() == null) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Usuário!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (beans.getStatus() == null) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Status!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (beans.getListaSolicitacao().isEmpty()) {
            JOptionPane.showMessageDialog(null, "A Solicitação Não Possui Nenhum Item!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }

    private Integer getIdSetor(JComboBox<ListSetorTrabalhoBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return comboBox.getItemAt(comboBox.getSelectedIndex()).getID();
        }
        return 0;
    }

     public void setJComboBoxFazenda(JComboBox<PropriedadesBeans> cb, Integer IdFazenda) {
        for (int i = 0; i < cb.getModel().getSize(); i++) {
            if (cb.getItemAt(i).getCodigo() == IdFazenda) {
                cb.setSelectedIndex(i);
                break;
            }
        }
    }

    private Integer getIdFazenda(JComboBox<PropriedadesBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return ((PropriedadesBeans) comboBox.getSelectedItem()).getCodigo();
        }
        return 0;
    }

    public String getSqlWhere() {
        String s = "";
        String joinInventario = "";

        if (!txt_nPedido.getText().equals("")) {
            s += " and p.id = " + txt_nPedido.getText();
        }
        if (txt_dtInicial.getDate() != null && txt_dtFinal.getDate() != null) {
            s += " and p.data BETWEEN '" + Corretores.ConverterDateAAAA_MM_DD(txt_dtInicial.getDate()) + "' and '" + Corretores.ConverterDateAAAA_MM_DD(txt_dtFinal.getDate()) + "'";
        }
        if (cb_setorConsulta.getSelectedIndex() > 0) {
            s += " and pi.id_setor = " + getIdSetor(cb_setorConsulta);
        }
        if (cb_fazenda.getSelectedIndex() > 0) {
            s += " and p.id_fazenda = " + getIdFazenda(cb_fazenda);
        }
        if (!txt_codigoConsulta.getText().equals("")) {
            s += " and pi.codigo like '%" + txt_codigoConsulta.getText() + "%'";
        }
        if (!txt_nSolicitacao.getText().equals("")) {
            s += " and pi.id_solicitacao = " + txt_nSolicitacao.getText();
        }

        /*      if (cb_aplicacaoConsulta.getSelectedIndex() > 0) {
            s += " and pi.id_inventario = " + getIdAplicacao(cb_aplicacaoConsulta);
        }
        
                if (!txt_descricaoConsulta.getText().equals("")) {
            s += " and pi.descricao like '%" + txt_descricaoConsulta.getText() + "%'";
        }
        
                if (!txt_idItemConsulta.getText().equals("")) {
            s += " and pi.id_item = " + txt_idItemConsulta.getText();
        }
        if (cb_fazenda.getSelectedIndex() > 0) {
            s += " and p.id_fazenda = " + getIdFazenda(cb_fazenda);
        }
        if (cb_urgencia.getSelectedIndex() > 0) {
            s += " and pi.id_urgencia = " + getIdUrgencia(cb_urgencia);
        }
        if (cb_marca.getSelectedIndex() > 0){
            s += " and ci.id_marca = " + getIdMarca(cb_marca);
            joinInventario = " LEFT JOIN cad_inventario ci ON ci.id = pi.id_inventario ";
        }
        if (cb_categoria.getSelectedIndex() > 0){
            s += " and ci.id_categoria = " + getIdCategoria(cb_categoria);
            joinInventario = " LEFT JOIN cad_inventario ci ON ci.id = pi.id_inventario ";
        }
        if (cb_modelo.getSelectedIndex() > 0){
            s += " and ci.id_modelo = " + getIdModelo(cb_modelo);
            joinInventario = " LEFT JOIN cad_inventario ci ON ci.id = pi.id_inventario ";
        }  */
        s += " and p.status_emissao = 1 and pi.id_solicitacao > 0"; // condição para buscar apenas itens emitidos e com solicitacao Emitida

        if (!s.equals("")) {
            s = " WHERE " + s.replaceFirst("and", "");
        }

        System.out.println(s);
        return s;
    }

    public String getWhereResumo() {
        String s = "";
        s += " and solic.status = " + ch_status.isSelected();
        if (txt_dtInicialResumo.getDate() != null && txt_dtInicialResumo.getDate() != null) {
            s += " and solic.data BETWEEN '" + Corretores.ConverterDateAAAA_MM_DD(txt_dtInicialResumo.getDate()) + "' AND '" + Corretores.ConverterDateAAAA_MM_DD(txt_dtFinalResumo.getDate()) + "'";
        }
        if (getLong(txt_nSolicitacao1.getText()) != null && getLong(txt_nSolicitacao1.getText()) > 0L) {
            s += " and solic.id = " + txt_nSolicitacao1.getText();
        }
        return s;
    }

    private Boolean verificarStatusItem(List<PedidoAlmoxarifadoItens> lista) {
        Boolean b = true;
        System.out.println("Tamanho Lista: " + lista.size());
        for (int i = 0; i < lista.size(); i++) {
            PedidoAlmoxarifadoItens item = lista.get(i);
            if (item.getIdStatusItem() != 0) {
                JOptionPane.showMessageDialog(null, "O Item Nº " + item.getnItem()
                        + " - " + item.getCodigo() + ", já se encontra em outra Solicitação!, Exclua e tente Novamente!", "Erro", 0, FormatarICO.ICObtnSair());
                b = false;
            }
        }
        return b;
    }

    public Boolean exportarPlanilha(TableModelPedidoMercadoria model, Long lastID) {
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel File", "xlsx", "XLSX");
        fc.setDialogTitle("Selecione o Local do Arquivo");
        fc.setFileFilter(filter);
        fc.setSelectedFile(new File("Solicitação Nº " + lastID));
        int option = fc.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            String fileName = fc.getSelectedFile().getAbsolutePath();
            try {
                WorkbookSettings conf = new WorkbookSettings();
                conf.setEncoding("ISO-8859-1");
                File caminhoCanonical = Corretores.ValidarArquivoModelo("/src/Arquivos/Planilhas_Modelo/model_solicitacao_novo.xlsx");
                XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(caminhoCanonical));
                XSSFSheet planilha = workbook.getSheetAt(0);

                XSSFRow rowDados = planilha.getRow(1);
                XSSFCell cellSolicitacao = rowDados.getCell(2);
                XSSFCell cellUsuario = rowDados.getCell(6);
                XSSFCell cellData = rowDados.getCell(8);

                cellSolicitacao.setCellValue(lastID); // Adicionar Número da Solicitação
                cellSolicitacao.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                cellUsuario.setCellValue(NomeUsuario);
                cellData.setCellValue(dataAtual);
                popularPlanilha(model, planilha);
                workbook.write(new FileOutputStream(fileName + ".xlsx"));
                JOptionPane.showMessageDialog(null, "Planilha Criada Com Sucesso!", "Erro", 1, FormatarICO.ICObtnOk());
            } catch (IOException ex) {
                Logger.getLogger(frmImportarFolhaPagto.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex + " Erro ao Criar Planilha!", "Erro", 1, FormatarICO.ICObtnSair());
            }
        }
        return true;
    }

    public Long getLong(String str) {
        try {
            return new Long(str);
        } catch (Exception e) {
            return null;
        }
    }

    public void popularPlanilha(TableModelPedidoMercadoria model, XSSFSheet planilha) {
        for (int i = 6; i < model.getRowCount() + 6; i++) {
            XSSFRow row = planilha.getRow(i);
            for (int c = 0; c < model.getColumnCount(); c++) {
                XSSFCell cell = row.getCell(c);
                int index = i - 6;
                Object str = null;
                switch (c) {
                    case 0:
                        cell.setCellValue(Double.parseDouble(model.getValueAt(index, model.ID).toString()));
                        cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                        break;
                    case 1:
                        cell.setCellValue(Double.parseDouble(model.getValueAt(index, model.ID_ITEM).toString())); // ID Cadastrado
                        cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                        break;
                    case 2:
                        cell.setCellValue(Double.parseDouble(model.getValueAt(index, model.ID_PEDIDO).toString()));
                        cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                        break;
                    case 3:
                        cell.setCellValue(model.getValueAt(index, model.N_ITEM).toString());
                        //cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                        break;
                    case 4:
                        cell.setCellValue((Double) model.getValueAt(index, model.QUANTIDADE));
                        cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                        break;
                    case 5:
                        cell.setCellValue(model.getValueAt(index, model.UNIDADE).toString());
                        break;
                    case 6:
                        cell.setCellValue(model.getValueAt(index, model.DESCRICAO).toString());
                        break;
                    case 7:
                        str = model.getValueAt(index, model.CODIGO);
                        if (str == null) {
                            cell.setCellValue("");
                        } else {
                            cell.setCellValue(str.toString());
                        }
                        break;
                    case 8:
                        str = model.getValueAt(index, model.INVENTARIO);
                        if (str == null) {
                            cell.setCellValue("");
                        } else {
                            cell.setCellValue(str.toString());
                        }
                        break;
                    default:
                }
            }
        }
    }

}
