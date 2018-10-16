package GUI;

import Almoxarifado.FrmCadAlmoxarif;
import Beans.CadUsuario;
import DAO.UsuarioDAO;
import Financeiro.FrmCadBanco;
import Financeiro.FrmCadCompradorGados;
import Financeiro.FrmCadContaBancaria;
import Financeiro.FrmCadGrupoContas;
import Financeiro.FrmCadMoeda;
import Utilitarios.Conexao;
import Utilitarios.ConfigXML;
import Utilitarios.ValidarPermissoes;
import java.awt.HeadlessException;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public final class frmLogin extends javax.swing.JFrame {

    public static CadUsuario UsuarioLogadoBeans;
    UsuarioDAO UsuarioD;
    Connection conexao = null;
    Principal TelaPrincipal = new Principal();
    public static String UsuarioLogado = null;
    public static String dataAtual = null;
    public static Integer IdUsuario = null;
    public static String NomeUsuario = null;

    public frmLogin() {

        initComponents();
        ImageIcon icone = new ImageIcon("imagens/iconeAEF.jpg");
        setIconImage(icone.getImage());
        cb_tipoConexao.setSelectedItem(ConfigXML.LerDadosTag("UltimaConexao"));
        Conexao.setDadosConexao(cb_tipoConexao.getSelectedItem().toString());
        getStatusLabel();
        getRootPane().setDefaultButton(btn_login);
        preencherUsuarioSenha();

    }

    public void logar() {
        try {
            UsuarioD = new UsuarioDAO();
            UsuarioLogadoBeans = UsuarioD.BuscarUsuario(txt_usuario.getText(), txt_senha.getText());

            if (UsuarioLogadoBeans != null && UsuarioLogadoBeans.isStatus() == true) {
                verificarPermissoes();
                TelaPrincipal.setVisible(true);
                NomeUsuario = UsuarioLogadoBeans.getLogin();
                IdUsuario = UsuarioLogadoBeans.getId();
                UsuarioLogado = txt_usuario.getText();
                dataAtual = new SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date(System.currentTimeMillis()));
                this.dispose();
            } else if (UsuarioLogadoBeans != null && UsuarioLogadoBeans.isStatus() == false) {
                JOptionPane.showMessageDialog(null, "Usuário Inativo, solicite a reativação ao Administrador!");
            } else if (UsuarioLogadoBeans == null) {
                JOptionPane.showMessageDialog(null, "Usuário ou Senha inválido");
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void verificarPermissoes() {

        Principal.MenuCadPropriedades.setEnabled(ValidarPermissoes.validarPermissaoView(FrmCadFazendas.class.getSimpleName()));
        Principal.MenuCadInsumos.setEnabled(ValidarPermissoes.validarPermissaoView(frmCadInsumos.class.getSimpleName()));
        Principal.MenuCadPecas.setEnabled(ValidarPermissoes.validarPermissaoView(FrmCadAlmoxarif.class.getSimpleName()));
        Principal.MenuConultaPecas.setEnabled(ValidarPermissoes.validarPermissaoView(FrmConsultaPecas.class.getSimpleName()));
        Principal.MenuCadFornecedores.setEnabled(ValidarPermissoes.validarPermissaoView(frmCadFornecedores.class.getSimpleName()));
        Principal.MenuCadUsuarios.setEnabled(ValidarPermissoes.validarPermissaoView(FrmCadUsuario.class.getSimpleName()));
        Principal.MenuCadPluviometros.setEnabled(ValidarPermissoes.validarPermissaoView(frmCadPluviometro.class.getSimpleName()));
        Principal.MenuCadColaboradoes.setEnabled(ValidarPermissoes.validarPermissaoView(frmCadColaborador.class.getSimpleName()));
        Principal.MenuResumoColaborador.setEnabled(ValidarPermissoes.validarPermissaoView(frmResumoCadColaborador.class.getSimpleName()));
        Principal.MenuCadTransportadora.setEnabled(ValidarPermissoes.validarPermissaoView(FrmCadTransportadora.class.getSimpleName()));
        Principal.MenuCadUnidades.setEnabled(ValidarPermissoes.validarPermissaoView(FrmCadUnidades.class.getSimpleName()));
        
        Principal.MenuCadInventario.setEnabled(ValidarPermissoes.validarPermissaoView(FrmCadInventario.class.getSimpleName()));
        Principal.MenuCadModelos.setEnabled(ValidarPermissoes.validarPermissaoView(FrmCadModelo.class.getSimpleName()));
        Principal.MenuCadMarcas.setEnabled(ValidarPermissoes.validarPermissaoView(FrmCadMarca.class.getSimpleName()));
        Principal.MenuCadCategorias.setEnabled(ValidarPermissoes.validarPermissaoView(FrmCadCategoria.class.getSimpleName()));
        Principal.MenuCadCentroResultado.setEnabled(ValidarPermissoes.validarPermissaoView(FrmCadCentroResultado.class.getSimpleName()));
        Principal.MenuCadAtividade.setEnabled(ValidarPermissoes.validarPermissaoView(FrmCadAtividade.class.getSimpleName()));
        Principal.MenuCadAnoSafra.setEnabled(ValidarPermissoes.validarPermissaoView(FrmCadAnoSafra.class.getSimpleName()));
        Principal.MenuCadCultivo.setEnabled(ValidarPermissoes.validarPermissaoView(FrmCadCultivo.class.getSimpleName()));
        Principal.MenuCadCultura.setEnabled(ValidarPermissoes.validarPermissaoView(FrmCadCultura.class.getSimpleName()));
        
        Principal.MenuCadBanco.setEnabled(ValidarPermissoes.validarPermissaoView(FrmCadBanco.class.getSimpleName()));
        Principal.MenuCadCompradorGados.setEnabled(ValidarPermissoes.validarPermissaoView(FrmCadCompradorGados.class.getSimpleName()));
        Principal.MenuCadContaBancaria.setEnabled(ValidarPermissoes.validarPermissaoView(FrmCadContaBancaria.class.getSimpleName()));
        Principal.MenuCadGrupoContas.setEnabled(ValidarPermissoes.validarPermissaoView(FrmCadGrupoContas.class.getSimpleName()));
        Principal.MenuCadMoedas.setEnabled(ValidarPermissoes.validarPermissaoView(FrmCadMoeda.class.getSimpleName()));

        Principal.menuEntGado.setEnabled(ValidarPermissoes.validarPermissaoView(frmEntradaGado.class.getSimpleName()));
        Principal.menuSaidaGado.setEnabled(ValidarPermissoes.validarPermissaoView(frmSaidaGado.class.getSimpleName()));
        Principal.menuCompraGado.setEnabled(ValidarPermissoes.validarPermissaoView(frmCompraGado.class.getSimpleName()));
        Principal.menuVendaGado.setEnabled(ValidarPermissoes.validarPermissaoView(frmVendaGado.class.getSimpleName()));
        Principal.menuAbates.setEnabled(ValidarPermissoes.validarPermissaoView(frmEscalasAbate.class.getSimpleName()));
        Principal.menuAcertoFrete.setEnabled(ValidarPermissoes.validarPermissaoView(frmFreteGado.class.getSimpleName()));

        Principal.menuPagamentos.setEnabled(ValidarPermissoes.validarPermissaoView(frmPagamentos.class.getSimpleName()));
        Principal.menuResPagto.setEnabled(ValidarPermissoes.validarPermissaoView(frmResPagamentos.class.getSimpleName()));
        Principal.menuRelPagto.setEnabled(ValidarPermissoes.validarPermissaoView(frmRelPagamento.class.getSimpleName()));
        Principal.menuRecebimentos.setEnabled(ValidarPermissoes.validarPermissaoView(frmRecebimentos.class.getSimpleName()));
        Principal.menuResumoReceb.setEnabled(ValidarPermissoes.validarPermissaoView(frmRecebResumo.class.getSimpleName()));
        Principal.menuTransf.setEnabled(ValidarPermissoes.validarPermissaoView(frmTransfContas.class.getSimpleName()));

        Principal.MenuPedidoInsumos.setEnabled(ValidarPermissoes.validarPermissaoView(frmPedidosInsumos.class.getSimpleName()));
        Principal.MenuPedidoEntInsumo.setEnabled(ValidarPermissoes.validarPermissaoView(frmEntradaInsumos.class.getSimpleName()));
        Principal.MenuPedidoSaidaInsumo.setEnabled(ValidarPermissoes.validarPermissaoView(frmSaidaInsumos.class.getSimpleName()));
        Principal.MenuPedidosEstoque.setEnabled(ValidarPermissoes.validarPermissaoView(frmEstoqueInsumos.class.getSimpleName()));

        Principal.MenuPedidoSupri.setEnabled(ValidarPermissoes.validarPermissaoView(FrmPedidosFazenda.class.getSimpleName()));
        Principal.MenuEntradaSupri.setEnabled(ValidarPermissoes.validarPermissaoView(FrmEntradaAlmoxarifado.class.getSimpleName()));
        Principal.MenuSaidaSupr.setEnabled(ValidarPermissoes.validarPermissaoView(FrmSaidaAlmoxarifado.class.getSimpleName()));
        Principal.MenuEstoqueSupr.setEnabled(ValidarPermissoes.validarPermissaoView(FrmConsultaEstoqueAlmoxarifado.class.getSimpleName()));
        Principal.MenuPedidoCentral.setEnabled(ValidarPermissoes.validarPermissaoView(FrmPedidosCentral.class.getSimpleName()));

        Principal.MenuSolicitacao.setEnabled(ValidarPermissoes.validarPermissaoView(FrmPedidosSolicitacao.class.getSimpleName()));
        Principal.MenuCotacao.setEnabled(ValidarPermissoes.validarPermissaoView(FrmPedidosCotacao.class.getSimpleName()));
        Principal.MenuAnaliseCotacao.setEnabled(ValidarPermissoes.validarPermissaoView(FrmPedidosAnalise.class.getSimpleName()));
        Principal.MenuFechamentoCotacao.setEnabled(ValidarPermissoes.validarPermissaoView(FrmPedidosFechamento.class.getSimpleName()));
        Principal.MenuRemessa.setEnabled(ValidarPermissoes.validarPermissaoView(FrmRemessaMercadorias.class.getSimpleName()));
        Principal.MenuConsultaRemessa.setEnabled(ValidarPermissoes.validarPermissaoView(FrmRemessaConsulta.class.getSimpleName()));
        
        Principal.MenuImportarNFe.setEnabled(ValidarPermissoes.validarPermissaoView(FrmImportarNFe.class.getSimpleName()));

        Principal.MenuOpPlantio.setEnabled(ValidarPermissoes.validarPermissaoView(frmPlantio.class.getSimpleName()));
        Principal.MenuOpAplicacoes.setEnabled(ValidarPermissoes.validarPermissaoView(frmAplicacoes.class.getSimpleName()));
        Principal.MenuOpOperacoes.setEnabled(ValidarPermissoes.validarPermissaoView(frmOperacoes.class.getSimpleName()));
        Principal.MenuChuvas.setEnabled(ValidarPermissoes.validarPermissaoView(frmPluviosidade.class.getSimpleName()));

    }

    public void getStatusLabel() {
        lblstatus.setIcon(null);
        Conexao.setDadosConexao(cb_tipoConexao.getSelectedItem().toString());
        try {
            if (Conexao.getConnection() != null) {
                lblstatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/dbok.png")));
            } else {
                lblstatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/dberro.png")));
            }
        } catch (Exception e) {
            lblstatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/dberro.png")));
        }
    }

    public void preencherUsuarioSenha() {
        if (Conexao.Usuario.equals("root")) {
            txt_usuario.setText("bsalmeida");
            txt_senha.setText("123");
        } else {
            txt_usuario.setText("");
            txt_senha.setText("");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_usuario = new javax.swing.JTextField();
        txt_senha = new javax.swing.JPasswordField();
        btn_login = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cb_tipoConexao = new javax.swing.JComboBox<>();
        lblstatus = new javax.swing.JLabel();
        lb_fundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        txt_usuario.setToolTipText("");
        getContentPane().add(txt_usuario);
        txt_usuario.setBounds(185, 113, 330, 40);

        txt_senha.setToolTipText("");
        getContentPane().add(txt_senha);
        txt_senha.setBounds(185, 180, 330, 40);

        btn_login.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_login.setText("Login");
        btn_login.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_login.setOpaque(false);
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });
        getContentPane().add(btn_login);
        btn_login.setBounds(300, 230, 210, 50);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Conexão");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 260, 50, 20);

        cb_tipoConexao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Local", "TCP/IP - Local", "TCP/IP - HostNet", "TCP/IP - Vilhena" }));
        cb_tipoConexao.setBorder(null);
        cb_tipoConexao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_tipoConexaoItemStateChanged(evt);
            }
        });
        getContentPane().add(cb_tipoConexao);
        cb_tipoConexao.setBounds(80, 260, 150, 20);

        lblstatus.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblstatus.setForeground(new java.awt.Color(248, 248, 248));
        getContentPane().add(lblstatus);
        lblstatus.setBounds(10, 20, 50, 40);

        lb_fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/login.jpg"))); // NOI18N
        lb_fundo.setMinimumSize(new java.awt.Dimension(450, 230));
        lb_fundo.setPreferredSize(new java.awt.Dimension(450, 230));
        getContentPane().add(lb_fundo);
        lb_fundo.setBounds(0, 0, 530, 310);

        setSize(new java.awt.Dimension(535, 338));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
        Conexao.setDadosConexao(cb_tipoConexao.getSelectedItem().toString());
        Conexao.ReiniciarCon();
        ConfigXML.Editar("UltimaConexao", cb_tipoConexao.getSelectedItem().toString());
        logar();
    }//GEN-LAST:event_btn_loginActionPerformed

    private void cb_tipoConexaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_tipoConexaoItemStateChanged
        Conexao.fecharConexao();
        Conexao.setDadosConexao(cb_tipoConexao.getSelectedItem().toString());
        //System.out.println("Usuario: " + Conexao.URL);
        getStatusLabel();
        preencherUsuarioSenha();
    }//GEN-LAST:event_cb_tipoConexaoItemStateChanged

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
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new frmLogin().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_login;
    private javax.swing.JComboBox<String> cb_tipoConexao;
    private javax.swing.JLabel jLabel1;
    private static javax.swing.JLabel lb_fundo;
    private javax.swing.JLabel lblstatus;
    private javax.swing.JPasswordField txt_senha;
    private javax.swing.JTextField txt_usuario;
    // End of variables declaration//GEN-END:variables

}
