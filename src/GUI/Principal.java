package GUI;

import Almoxarifado.FrmCadAlmoxarif;
import Beans.AtividadeBeans;
import Almoxarifado.CadAlmoxarifadoBeans;
import Beans.CadUnidadesBeans;
import Beans.AnoSafra;
import Beans.ListAplicacaoBeans;
import Beans.BancosBeans;
import Beans.CentroDeResultado;
import Beans.CEIBeans;
import Beans.CargosBeans;
import Almoxarifado.CategoriaAlmoxarif;
import Beans.CategoriaAnimalBeans;
import Beans.CategoriaEquipamentosBeans;
import Beans.ListCategoriaInsumos;
import Beans.CompradorGadoBeans;
import Beans.ContaBancariaBeans;
import Beans.ListCultivarBeans;
import Beans.CultivoBeans;
import Beans.CulturaBeans;
import Beans.ListDeficienciaBeans;
import Beans.ListEstadoCivil;
import Beans.EstadosBeans;
import Beans.GrupoContasBeans;
import Beans.ListFazendasBeans;
import Beans.ListFrigorificosBeans;
import Beans.ListColaborador;
import Beans.InventarioBeans;
import Almoxarifado.LocalizadorAlmoxarifadoBeans;
import Beans.CidadesBeans;
import Beans.ModeloEquipamentosBeans;
import Beans.MoedaBeans;
import Beans.ListOperacoesBeans;
import Beans.ListPluviometroBeans;
import Beans.ListPontaPulverizacaoBeans;
import Beans.ListRacaAnimalBeans;
import Beans.ListSetorTrabalhoBeans;
import Beans.ListStatusAnimalBeans;
import Beans.TalhaoBeans;
import Beans.TipoDocPagto;
import Beans.TransportadorasBeans;
import Beans.PlanoContaBeans;
import Beans.PropriedadesBeans;
import Beans.StatusItemPedido;
import Beans.MarcaEquipamentosBeans;
import Financeiro.FrmCadBanco;
import Financeiro.FrmCadCompradorGados;
import Financeiro.FrmCadContaBancaria;
import Financeiro.FrmCadGrupoContas;
import Financeiro.FrmCadMoeda;
import Utilitarios.FundoTela;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.beans.PropertyVetoException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.plaf.LookAndFeelAddons;
import org.jdesktop.swingx.plaf.macosx.MacOSXLookAndFeelAddons;

public class Principal extends javax.swing.JFrame {

    public static teste frmTeste;
    public static FundoTela tela;
    telausuario usuario;
    FrmCadUsuario frmUsuario;
    FrmCadFazendas fazendas;
    frmEntradaGado EntradaGado;
    frmCompraGado CompraGado;
    frmVendaGado VendaGado;
    frmEscalasAbate EscalasAbate;
    frmSaidaGado SaidaGado;
    frmCadInsumos CadInsumos;
    public static frmPedidosInsumos PedidosInsumos;
    public static frmEntradaInsumos EntradaInsumos;
    public static frmSaidaInsumos SaidaInsumos;
    public static frmFreteGado FreteGado;
    public static frmResPagamentos ResPagamentos;
    frmCadFornecedores frmFornecedores;
    frmGTA frmControleGTA;
    frmRelPagamento frmRelPagamento;
    public static String OrigemContasAPagar;
    frmPagamentos Pagamentos;
    frmRecebimentos Recebimentos;
    frmRelBovino RelatoriosGado;
    frmTransfContas frmTransfContas;
    frmEstoqueInsumos frmEstoqueInsumos;
    frmImportarFolhaPagto frmImportarFolhaPagto;
    frmRelatorioOperacoes frmRelatorioOperacoes;
    frmPlantio frmPlantio;
    frmCadPluviometro frmCadPluviometro;
    frmPluviosidade frmPluviosidade;
    frmCadColaborador frmCadColaborador;
    frmResumoCadColaborador frmResumoCadColaborador;
    FrmRastreabilidadeBovina frmRastreabilidade;
    public static frmAplicacoes frmAplicacoes;
    public static List<ListFazendasBeans> ListFazendas;
    public static List<PropriedadesBeans> ListaPropriedades;
    public static List<PropriedadesBeans> ListaPropriedadesPermitidas;
    public static List<ListFazendasBeans> ListFazPermitidas;
    public static List<PlanoContaBeans> listPlanoContas;
    public static List<BancosBeans> listBancos;
    public static List<ContaBancariaBeans> listContaOrigem;
    public static List<AnoSafra> listAnoSafra;
    public static List<MoedaBeans> listMoeda;
    public static List<ListCategoriaInsumos> listCategoriaInsumos;
    public static List<CompradorGadoBeans> listCompradores;
    public static List<TransportadorasBeans> listTransportadoras;
    public static List<CultivoBeans> listCultivo;
    public static List<CulturaBeans> listCultura;
    public static List<ListAplicacaoBeans> listAplicacao;
    public static List<InventarioBeans> listInventario;
    public static List<ListFrigorificosBeans> listFrigorificos;
    public static List<TalhaoBeans> listTalhao;
    public static List<ListOperacoesBeans> listOperacoes;
    public static List<CategoriaEquipamentosBeans> listaCategoriaEquipamentos;
    public static List<ModeloEquipamentosBeans> listaModeloEquipamentos;
    public static List<ListPontaPulverizacaoBeans> listPontasPulverizacao;
    public static List<MarcaEquipamentosBeans> listaMarcaEquipamentos;
    public static List<CEIBeans> listCEI;
    public static List<ListColaborador> listFuncionarios;
    public static List<ListCultivarBeans> listCultivares;
    public static List<TipoDocPagto> listTipoDocPagto;
    public static List<ListPluviometroBeans> listPluviometro;
    public static List<EstadosBeans> listEstados;
    public static List<CidadesBeans> listCidades;
    public static List<CargosBeans> listCargosFuncoes;
    public static List<ListEstadoCivil> listEstadoCivil;
    public static List<ListDeficienciaBeans> listDeficiencias;
    public static List<ListSetorTrabalhoBeans> listSetorTrabalho;
    public static List<CategoriaAnimalBeans> listCategoriaAnimal;
    public static List<ListRacaAnimalBeans> listRacaAnimal;
    public static List<ListStatusAnimalBeans> listStatusAnimal;
    public static List<CategoriaAlmoxarif> listCategoriaAlmoxarif;
    public static List<CadAlmoxarifadoBeans> listAlmoxarifado;
    public static List<CadUnidadesBeans> listUnidades;
    public static List<LocalizadorAlmoxarifadoBeans> listLocalizadorAlmox;
    public static List<StatusItemPedido> listStatusItemPedido;
    public static List<AtividadeBeans> listaAtividades;
    public static List<GrupoContasBeans> listaGrupoContas;
    public static List<CentroDeResultado> listaCentroResultado;
    public static List<PropriedadesBeans> listaFazPermitida;
    public static Integer alturaTela;
    public static Integer larguraTela;

    public Principal() {
        UIManager.getLookAndFeelDefaults().put(JXTaskPane.uiClassID,
                "org.jdesktop.swingx.plaf.misc.GlossyTaskPaneUI");

        initComponents();
        OrigemContasAPagar = "";
        ImageIcon icone = new ImageIcon("imagens/iconeAEF.jpg");
        setIconImage(icone.getImage());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new GridLayout());
        tela = new FundoTela("/Icones/fundoTela.jpg");
        getContentPane().add(tela);
        jMenuItem4.setVisible(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Menu = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MenuCadPropriedades = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator1 = new javax.swing.JPopupMenu.Separator();
        MenuCadInsumos = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenu9 = new javax.swing.JMenu();
        MenuCadPecas = new javax.swing.JMenuItem();
        jSeparator36 = new javax.swing.JPopupMenu.Separator();
        MenuCadUnidades = new javax.swing.JMenuItem();
        jSeparator65 = new javax.swing.JPopupMenu.Separator();
        MenuConultaPecas = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator3 = new javax.swing.JPopupMenu.Separator();
        MenuCadUsuarios = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator5 = new javax.swing.JPopupMenu.Separator();
        MenuCadFornecedores = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator28 = new javax.swing.JPopupMenu.Separator();
        MenuCadPluviometros = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator30 = new javax.swing.JPopupMenu.Separator();
        jMenu7 = new javax.swing.JMenu();
        MenuCadColaboradoes = new javax.swing.JMenuItem();
        jSeparator31 = new javax.swing.JPopupMenu.Separator();
        MenuResumoColaborador = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator40 = new javax.swing.JPopupMenu.Separator();
        jMenu13 = new javax.swing.JMenu();
        MenuCadBanco = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        MenuCadContaBancaria = new javax.swing.JMenuItem();
        jSeparator46 = new javax.swing.JPopupMenu.Separator();
        MenuCadCompradorGados = new javax.swing.JMenuItem();
        jSeparator57 = new javax.swing.JPopupMenu.Separator();
        MenuCadGrupoContas = new javax.swing.JMenuItem();
        jSeparator58 = new javax.swing.JPopupMenu.Separator();
        MenuCadMoedas = new javax.swing.JMenuItem();
        jSeparator59 = new javax.swing.JPopupMenu.Separator();
        MenuCadPlanoContas = new javax.swing.JMenuItem();
        jSeparator60 = new javax.swing.JPopupMenu.Separator();
        MenuCadTipoDocumentos = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator51 = new javax.swing.JPopupMenu.Separator();
        jMenu12 = new javax.swing.JMenu();
        MenuCadCentroResultado = new javax.swing.JMenuItem();
        jSeparator53 = new javax.swing.JPopupMenu.Separator();
        MenuCadAtividade = new javax.swing.JMenuItem();
        jSeparator54 = new javax.swing.JPopupMenu.Separator();
        MenuCadAnoSafra = new javax.swing.JMenuItem();
        jSeparator55 = new javax.swing.JPopupMenu.Separator();
        MenuCadCultivo = new javax.swing.JMenuItem();
        jSeparator56 = new javax.swing.JPopupMenu.Separator();
        MenuCadCultura = new javax.swing.JMenuItem();
        jSeparator47 = new javax.swing.JPopupMenu.Separator();
        jMenu11 = new javax.swing.JMenu();
        MenuCadInventario = new javax.swing.JMenuItem();
        jSeparator48 = new javax.swing.JPopupMenu.Separator();
        MenuCadModelos = new javax.swing.JMenuItem();
        jSeparator49 = new javax.swing.JPopupMenu.Separator();
        MenuCadMarcas = new javax.swing.JMenuItem();
        jSeparator50 = new javax.swing.JPopupMenu.Separator();
        MenuCadCategorias = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator52 = new javax.swing.JPopupMenu.Separator();
        MenuCadTransportadora = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        menuMovGado = new javax.swing.JMenu();
        menuEntGado = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenu8 = new javax.swing.JMenu();
        menuRastreabilidade = new javax.swing.JMenuItem();
        jSeparator34 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator35 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator33 = new javax.swing.JPopupMenu.Separator();
        menuSaidaGado = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator7 = new javax.swing.JPopupMenu.Separator();
        menuCompraGado = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator8 = new javax.swing.JPopupMenu.Separator();
        menuVendaGado = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator18 = new javax.swing.JPopupMenu.Separator();
        menuAbates = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator19 = new javax.swing.JPopupMenu.Separator();
        menuAcertoFrete = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator24 = new javax.swing.JPopupMenu.Separator();
        menuRelGado = new javax.swing.JMenuItem();
        MenuCad = new javax.swing.JMenu();
        menuPagamentos = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator23 = new javax.swing.JPopupMenu.Separator();
        menuTransf = new javax.swing.JMenuItem();
        jSeparator61 = new javax.swing.JPopupMenu.Separator();
        menuTransfCCusto = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator25 = new javax.swing.JPopupMenu.Separator();
        jMenu6 = new javax.swing.JMenu();
        menuRecebimentos = new javax.swing.JMenuItem();
        jSeparator62 = new javax.swing.JPopupMenu.Separator();
        menuResumoReceb = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator20 = new javax.swing.JPopupMenu.Separator();
        menuResPagto = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator22 = new javax.swing.JPopupMenu.Separator();
        menuRelPagto = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator21 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        Almoxarifado = new javax.swing.JMenu();
        MenuPedidoInsumos = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator9 = new javax.swing.JPopupMenu.Separator();
        jMenu10 = new javax.swing.JMenu();
        MenuEntradaSupri = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator37 = new javax.swing.JPopupMenu.Separator();
        MenuSaidaSupr = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator38 = new javax.swing.JPopupMenu.Separator();
        MenuEstoqueSupr = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator41 = new javax.swing.JPopupMenu.Separator();
        MenuImportarNFe = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator39 = new javax.swing.JPopupMenu.Separator();
        MenuPedidoSupri = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator10 = new javax.swing.JPopupMenu.Separator();
        jMenu4 = new javax.swing.JMenu();
        MenuPedidoCentral = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator42 = new javax.swing.JPopupMenu.Separator();
        MenuSolicitacao = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator43 = new javax.swing.JPopupMenu.Separator();
        MenuCotacao = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator44 = new javax.swing.JPopupMenu.Separator();
        MenuAnaliseCotacao = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator45 = new javax.swing.JPopupMenu.Separator();
        MenuFechamentoCotacao = new javax.swing.JMenuItem();
        jSeparator63 = new javax.swing.JPopupMenu.Separator();
        jMenu14 = new javax.swing.JMenu();
        MenuRemessa = new javax.swing.JMenuItem();
        jSeparator64 = new javax.swing.JPopupMenu.Separator();
        MenuConsultaRemessa = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator11 = new javax.swing.JPopupMenu.Separator();
        MenuPedidoEntInsumo = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator12 = new javax.swing.JPopupMenu.Separator();
        MenuPedidoSaidaInsumo = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator26 = new javax.swing.JPopupMenu.Separator();
        MenuPedidosEstoque = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        MenuArmEntrada = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator13 = new javax.swing.JPopupMenu.Separator();
        MenuArmSaida = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator14 = new javax.swing.JPopupMenu.Separator();
        MenuArmVenda = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        MenuOpPlantio = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator15 = new javax.swing.JPopupMenu.Separator();
        MenuOpOperacoes = new javax.swing.JMenu();
        jmenu_anotacoes = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator32 = new javax.swing.JPopupMenu.Separator();
        jmenu_resumoOperacoes = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator16 = new javax.swing.JPopupMenu.Separator();
        MenuOpAplicacoes = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator17 = new javax.swing.JPopupMenu.Separator();
        MenuOpMonit = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator27 = new javax.swing.JPopupMenu.Separator();
        MenuResumoOp = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator29 = new javax.swing.JPopupMenu.Separator();
        MenuChuvas = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Sistema AEF");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowDeactivated(java.awt.event.WindowEvent evt) {
                formWindowDeactivated(evt);
            }
        });

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/ferramentas.png"))); // NOI18N
        jMenu1.setText("Cadastros");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        MenuCadPropriedades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Fazenda32x32.png"))); // NOI18N
        MenuCadPropriedades.setText("Propriedades");
        MenuCadPropriedades.setEnabled(false);
        MenuCadPropriedades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadPropriedadesActionPerformed(evt);
            }
        });
        jMenu1.add(MenuCadPropriedades);
        jMenu1.add(jSeparator1);

        MenuCadInsumos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Insumos32x32.png"))); // NOI18N
        MenuCadInsumos.setText("Insumos");
        MenuCadInsumos.setEnabled(false);
        MenuCadInsumos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadInsumosActionPerformed(evt);
            }
        });
        jMenu1.add(MenuCadInsumos);
        jMenu1.add(jSeparator2);

        jMenu9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Almoxarifado32x32.png"))); // NOI18N
        jMenu9.setText("Almoxarifado");

        MenuCadPecas.setText("Cadastro de Itens");
        MenuCadPecas.setEnabled(false);
        MenuCadPecas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadPecasActionPerformed(evt);
            }
        });
        jMenu9.add(MenuCadPecas);
        jMenu9.add(jSeparator36);

        MenuCadUnidades.setText("Cadastro de Unidades");
        MenuCadUnidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadUnidadesActionPerformed(evt);
            }
        });
        jMenu9.add(MenuCadUnidades);
        jMenu9.add(jSeparator65);

        MenuConultaPecas.setText("Consulta de Cadastro");
        MenuConultaPecas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuConultaPecasActionPerformed(evt);
            }
        });
        jMenu9.add(MenuConultaPecas);

        jMenu1.add(jMenu9);
        jMenu1.add(jSeparator3);

        MenuCadUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Usuario32x32.png"))); // NOI18N
        MenuCadUsuarios.setText("Usuários");
        MenuCadUsuarios.setEnabled(false);
        MenuCadUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadUsuariosActionPerformed(evt);
            }
        });
        jMenu1.add(MenuCadUsuarios);
        jMenu1.add(jSeparator5);

        MenuCadFornecedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Fornecedo28x28.png"))); // NOI18N
        MenuCadFornecedores.setText("Fornecedores");
        MenuCadFornecedores.setEnabled(false);
        MenuCadFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadFornecedoresActionPerformed(evt);
            }
        });
        jMenu1.add(MenuCadFornecedores);
        jMenu1.add(jSeparator28);

        MenuCadPluviometros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/pluviômetro28x28.png"))); // NOI18N
        MenuCadPluviometros.setText("Pluviômetros");
        MenuCadPluviometros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadPluviometrosActionPerformed(evt);
            }
        });
        jMenu1.add(MenuCadPluviometros);
        jMenu1.add(jSeparator30);

        jMenu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Colaboradores32x32.png"))); // NOI18N
        jMenu7.setText("Colaboradores");

        MenuCadColaboradoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/CadColaborador32x32.png"))); // NOI18N
        MenuCadColaboradoes.setText("Cadastro de Colaboradores");
        MenuCadColaboradoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadColaboradoesActionPerformed(evt);
            }
        });
        jMenu7.add(MenuCadColaboradoes);
        jMenu7.add(jSeparator31);

        MenuResumoColaborador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/ResColaborador32x32.png"))); // NOI18N
        MenuResumoColaborador.setText("Resumo dos Colaboradores");
        MenuResumoColaborador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuResumoColaboradorActionPerformed(evt);
            }
        });
        jMenu7.add(MenuResumoColaborador);

        jMenu1.add(jMenu7);
        jMenu1.add(jSeparator40);

        jMenu13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/CadFinanceiro32x28.png"))); // NOI18N
        jMenu13.setText("Cadastro do Financeiro");

        MenuCadBanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Banco32x28.png"))); // NOI18N
        MenuCadBanco.setText("Banco");
        MenuCadBanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadBancoActionPerformed(evt);
            }
        });
        jMenu13.add(MenuCadBanco);
        jMenu13.add(jSeparator4);

        MenuCadContaBancaria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/ContaBancaria32x28.png"))); // NOI18N
        MenuCadContaBancaria.setText("Conta Bancaria");
        MenuCadContaBancaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadContaBancariaActionPerformed(evt);
            }
        });
        jMenu13.add(MenuCadContaBancaria);
        jMenu13.add(jSeparator46);

        MenuCadCompradorGados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Comprador32x28.png"))); // NOI18N
        MenuCadCompradorGados.setText("Comprador de Gados");
        MenuCadCompradorGados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadCompradorGadosActionPerformed(evt);
            }
        });
        jMenu13.add(MenuCadCompradorGados);
        jMenu13.add(jSeparator57);

        MenuCadGrupoContas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/GrupoPessoas32x28.png"))); // NOI18N
        MenuCadGrupoContas.setText("Grupo de Contas");
        MenuCadGrupoContas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadGrupoContasActionPerformed(evt);
            }
        });
        jMenu13.add(MenuCadGrupoContas);
        jMenu13.add(jSeparator58);

        MenuCadMoedas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Moeda32x28.png"))); // NOI18N
        MenuCadMoedas.setText("Moeda");
        MenuCadMoedas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadMoedasActionPerformed(evt);
            }
        });
        jMenu13.add(MenuCadMoedas);
        jMenu13.add(jSeparator59);

        MenuCadPlanoContas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/PlanoContas32x28.png"))); // NOI18N
        MenuCadPlanoContas.setText("Plano de Contas");
        jMenu13.add(MenuCadPlanoContas);
        jMenu13.add(jSeparator60);

        MenuCadTipoDocumentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/TipoDocumento32x28.png"))); // NOI18N
        MenuCadTipoDocumentos.setText("Tipo de Documento");
        jMenu13.add(MenuCadTipoDocumentos);

        jMenu1.add(jMenu13);
        jMenu1.add(jSeparator51);

        jMenu12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Resultado24x24_2.png"))); // NOI18N
        jMenu12.setText("Centro de Resultado");

        MenuCadCentroResultado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Resultado24x24_2.png"))); // NOI18N
        MenuCadCentroResultado.setText("Centro de Resultado");
        MenuCadCentroResultado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadCentroResultadoActionPerformed(evt);
            }
        });
        jMenu12.add(MenuCadCentroResultado);
        jMenu12.add(jSeparator53);

        MenuCadAtividade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Trator32x24.png"))); // NOI18N
        MenuCadAtividade.setText("Atividade");
        MenuCadAtividade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadAtividadeActionPerformed(evt);
            }
        });
        jMenu12.add(MenuCadAtividade);
        jMenu12.add(jSeparator54);

        MenuCadAnoSafra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/AnoSafra24x24_4.png"))); // NOI18N
        MenuCadAnoSafra.setText("Ano da Safra");
        MenuCadAnoSafra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadAnoSafraActionPerformed(evt);
            }
        });
        jMenu12.add(MenuCadAnoSafra);
        jMenu12.add(jSeparator55);

        MenuCadCultivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Cultivo24x24.png"))); // NOI18N
        MenuCadCultivo.setText("Cultivo");
        MenuCadCultivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadCultivoActionPerformed(evt);
            }
        });
        jMenu12.add(MenuCadCultivo);
        jMenu12.add(jSeparator56);

        MenuCadCultura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Cultura24x24.png"))); // NOI18N
        MenuCadCultura.setText("Cultura");
        MenuCadCultura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadCulturaActionPerformed(evt);
            }
        });
        jMenu12.add(MenuCadCultura);

        jMenu1.add(jMenu12);
        jMenu1.add(jSeparator47);

        jMenu11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/inventario24x24.png"))); // NOI18N
        jMenu11.setText("Inventário");

        MenuCadInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/inventario24x24.png"))); // NOI18N
        MenuCadInventario.setText("Inventário");
        MenuCadInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadInventarioActionPerformed(evt);
            }
        });
        jMenu11.add(MenuCadInventario);
        jMenu11.add(jSeparator48);

        MenuCadModelos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/1_20x20.png"))); // NOI18N
        MenuCadModelos.setText("Modelos");
        MenuCadModelos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadModelosActionPerformed(evt);
            }
        });
        jMenu11.add(MenuCadModelos);
        jMenu11.add(jSeparator49);

        MenuCadMarcas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/2_20x20.png"))); // NOI18N
        MenuCadMarcas.setText("Marcas");
        MenuCadMarcas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadMarcasActionPerformed(evt);
            }
        });
        jMenu11.add(MenuCadMarcas);
        jMenu11.add(jSeparator50);

        MenuCadCategorias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/3_20x20.png"))); // NOI18N
        MenuCadCategorias.setText("Categorias");
        MenuCadCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadCategoriasActionPerformed(evt);
            }
        });
        jMenu11.add(MenuCadCategorias);

        jMenu1.add(jMenu11);
        jMenu1.add(jSeparator52);

        MenuCadTransportadora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Transportadora.png"))); // NOI18N
        MenuCadTransportadora.setText("Transportadoras");
        MenuCadTransportadora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadTransportadoraActionPerformed(evt);
            }
        });
        jMenu1.add(MenuCadTransportadora);

        jMenuItem4.setText("Teste");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        Menu.add(jMenu1);

        menuMovGado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/boi.png"))); // NOI18N
        menuMovGado.setText("Gado");
        menuMovGado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        menuEntGado.setText("Entrada de Gado");
        menuEntGado.setEnabled(false);
        menuEntGado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEntGadoActionPerformed(evt);
            }
        });
        menuMovGado.add(menuEntGado);
        menuMovGado.add(jSeparator6);

        jMenu8.setText("Rastreabilidade");

        menuRastreabilidade.setText("Cadastro de Animais");
        menuRastreabilidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRastreabilidadeActionPerformed(evt);
            }
        });
        jMenu8.add(menuRastreabilidade);
        jMenu8.add(jSeparator34);

        jMenuItem2.setText("Pesagens");
        jMenu8.add(jMenuItem2);
        jMenu8.add(jSeparator35);

        jMenuItem3.setText("Saída de Animais");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem3);

        menuMovGado.add(jMenu8);
        menuMovGado.add(jSeparator33);

        menuSaidaGado.setText("Saída de Gado");
        menuSaidaGado.setEnabled(false);
        menuSaidaGado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSaidaGadoActionPerformed(evt);
            }
        });
        menuMovGado.add(menuSaidaGado);
        menuMovGado.add(jSeparator7);

        menuCompraGado.setText("Compra de gado");
        menuCompraGado.setEnabled(false);
        menuCompraGado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCompraGadoActionPerformed(evt);
            }
        });
        menuMovGado.add(menuCompraGado);
        menuMovGado.add(jSeparator8);

        menuVendaGado.setText("Venda de Gado");
        menuVendaGado.setEnabled(false);
        menuVendaGado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuVendaGadoActionPerformed(evt);
            }
        });
        menuMovGado.add(menuVendaGado);
        menuMovGado.add(jSeparator18);

        menuAbates.setText("Escalas de Abate");
        menuAbates.setEnabled(false);
        menuAbates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAbatesActionPerformed(evt);
            }
        });
        menuMovGado.add(menuAbates);
        menuMovGado.add(jSeparator19);

        menuAcertoFrete.setText("Acerto de Frete");
        menuAcertoFrete.setEnabled(false);
        menuAcertoFrete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAcertoFreteActionPerformed(evt);
            }
        });
        menuMovGado.add(menuAcertoFrete);
        menuMovGado.add(jSeparator24);

        menuRelGado.setText("Relatórios de Gado");
        menuRelGado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRelGadoActionPerformed(evt);
            }
        });
        menuMovGado.add(menuRelGado);

        Menu.add(menuMovGado);

        MenuCad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/ico_caixa.png"))); // NOI18N
        MenuCad.setText("Financeiro");
        MenuCad.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        MenuCad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadActionPerformed(evt);
            }
        });

        menuPagamentos.setText("Pagamentos");
        menuPagamentos.setEnabled(false);
        menuPagamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPagamentosActionPerformed(evt);
            }
        });
        MenuCad.add(menuPagamentos);
        MenuCad.add(jSeparator23);

        menuTransf.setText("Tranferências Bancárias");
        menuTransf.setEnabled(false);
        menuTransf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTransfActionPerformed(evt);
            }
        });
        MenuCad.add(menuTransf);
        MenuCad.add(jSeparator61);

        menuTransfCCusto.setText("Transferência Centro Custo");
        menuTransfCCusto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTransfCCustoActionPerformed(evt);
            }
        });
        MenuCad.add(menuTransfCCusto);
        MenuCad.add(jSeparator25);

        jMenu6.setText("Contas a Receber");
        jMenu6.setToolTipText("");

        menuRecebimentos.setText("Recebimentos");
        menuRecebimentos.setEnabled(false);
        menuRecebimentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRecebimentosActionPerformed(evt);
            }
        });
        jMenu6.add(menuRecebimentos);
        jMenu6.add(jSeparator62);

        menuResumoReceb.setText("Resumo Recebíveis");
        menuResumoReceb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuResumoRecebActionPerformed(evt);
            }
        });
        jMenu6.add(menuResumoReceb);

        MenuCad.add(jMenu6);
        MenuCad.add(jSeparator20);

        menuResPagto.setText("Resumo de Pagamentos");
        menuResPagto.setEnabled(false);
        menuResPagto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuResPagtoActionPerformed(evt);
            }
        });
        MenuCad.add(menuResPagto);
        MenuCad.add(jSeparator22);

        menuRelPagto.setText("Relatórios de Pagamentos");
        menuRelPagto.setEnabled(false);
        menuRelPagto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRelPagtoActionPerformed(evt);
            }
        });
        MenuCad.add(menuRelPagto);
        MenuCad.add(jSeparator21);

        jMenuItem1.setText("Importar Folha de Pagto");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        MenuCad.add(jMenuItem1);

        Menu.add(MenuCad);

        Almoxarifado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/ico_pedidos.png"))); // NOI18N
        Almoxarifado.setText("Pedidos");
        Almoxarifado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        MenuPedidoInsumos.setText("Pedidos de Insumos");
        MenuPedidoInsumos.setEnabled(false);
        MenuPedidoInsumos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuPedidoInsumosActionPerformed(evt);
            }
        });
        Almoxarifado.add(MenuPedidoInsumos);
        Almoxarifado.add(jSeparator9);

        jMenu10.setText("Almoxarifado");

        MenuEntradaSupri.setText("Entrada de Mercadorias");
        MenuEntradaSupri.setEnabled(false);
        MenuEntradaSupri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuEntradaSupriActionPerformed(evt);
            }
        });
        jMenu10.add(MenuEntradaSupri);
        jMenu10.add(jSeparator37);

        MenuSaidaSupr.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        MenuSaidaSupr.setText("Saída de Mercadorias");
        MenuSaidaSupr.setEnabled(false);
        MenuSaidaSupr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuSaidaSuprActionPerformed(evt);
            }
        });
        jMenu10.add(MenuSaidaSupr);
        jMenu10.add(jSeparator38);

        MenuEstoqueSupr.setText("Estoque Mercadorias");
        MenuEstoqueSupr.setEnabled(false);
        MenuEstoqueSupr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuEstoqueSuprActionPerformed(evt);
            }
        });
        jMenu10.add(MenuEstoqueSupr);
        jMenu10.add(jSeparator41);

        MenuImportarNFe.setText("Importar NFe");
        MenuImportarNFe.setEnabled(false);
        MenuImportarNFe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuImportarNFeActionPerformed(evt);
            }
        });
        jMenu10.add(MenuImportarNFe);
        jMenu10.add(jSeparator39);

        MenuPedidoSupri.setText("Pedido");
        MenuPedidoSupri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuPedidoSupriActionPerformed(evt);
            }
        });
        jMenu10.add(MenuPedidoSupri);

        Almoxarifado.add(jMenu10);
        Almoxarifado.add(jSeparator10);

        jMenu4.setText("Gestão de Compras");

        MenuPedidoCentral.setText("Central de Compras");
        MenuPedidoCentral.setEnabled(false);
        MenuPedidoCentral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuPedidoCentralActionPerformed(evt);
            }
        });
        jMenu4.add(MenuPedidoCentral);
        jMenu4.add(jSeparator42);

        MenuSolicitacao.setText("Solicitações");
        MenuSolicitacao.setToolTipText("");
        MenuSolicitacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuSolicitacaoActionPerformed(evt);
            }
        });
        jMenu4.add(MenuSolicitacao);
        jMenu4.add(jSeparator43);

        MenuCotacao.setText("Cotações");
        MenuCotacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCotacaoActionPerformed(evt);
            }
        });
        jMenu4.add(MenuCotacao);
        jMenu4.add(jSeparator44);

        MenuAnaliseCotacao.setText("Análise de Cotações");
        MenuAnaliseCotacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuAnaliseCotacaoActionPerformed(evt);
            }
        });
        jMenu4.add(MenuAnaliseCotacao);
        jMenu4.add(jSeparator45);

        MenuFechamentoCotacao.setText("Finalizar Cotação");
        MenuFechamentoCotacao.setToolTipText("");
        MenuFechamentoCotacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuFechamentoCotacaoActionPerformed(evt);
            }
        });
        jMenu4.add(MenuFechamentoCotacao);
        jMenu4.add(jSeparator63);

        jMenu14.setText("Remessas");

        MenuRemessa.setText("Remessa de Mercadoria");
        MenuRemessa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuRemessaActionPerformed(evt);
            }
        });
        jMenu14.add(MenuRemessa);
        jMenu14.add(jSeparator64);

        MenuConsultaRemessa.setText("Consultar Remessas");
        MenuConsultaRemessa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuConsultaRemessaActionPerformed(evt);
            }
        });
        jMenu14.add(MenuConsultaRemessa);

        jMenu4.add(jMenu14);

        Almoxarifado.add(jMenu4);
        Almoxarifado.add(jSeparator11);

        MenuPedidoEntInsumo.setText("Entrada de Insumos");
        MenuPedidoEntInsumo.setEnabled(false);
        MenuPedidoEntInsumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuPedidoEntInsumoActionPerformed(evt);
            }
        });
        Almoxarifado.add(MenuPedidoEntInsumo);
        Almoxarifado.add(jSeparator12);

        MenuPedidoSaidaInsumo.setText("Saída de Insumos");
        MenuPedidoSaidaInsumo.setEnabled(false);
        MenuPedidoSaidaInsumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuPedidoSaidaInsumoActionPerformed(evt);
            }
        });
        Almoxarifado.add(MenuPedidoSaidaInsumo);
        Almoxarifado.add(jSeparator26);

        MenuPedidosEstoque.setText("Estoque de Insumos");
        MenuPedidosEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuPedidosEstoqueActionPerformed(evt);
            }
        });
        Almoxarifado.add(MenuPedidosEstoque);

        Menu.add(Almoxarifado);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Silo.png"))); // NOI18N
        jMenu2.setText("Armazéns");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        MenuArmEntrada.setText("Entrada de Grãos");
        MenuArmEntrada.setEnabled(false);
        jMenu2.add(MenuArmEntrada);
        jMenu2.add(jSeparator13);

        MenuArmSaida.setText("Saída de Grãos");
        MenuArmSaida.setEnabled(false);
        jMenu2.add(MenuArmSaida);
        jMenu2.add(jSeparator14);

        MenuArmVenda.setText("Venda de Grãos");
        MenuArmVenda.setEnabled(false);
        jMenu2.add(MenuArmVenda);

        Menu.add(jMenu2);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/agriculture1.png"))); // NOI18N
        jMenu5.setText("Operações");
        jMenu5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        MenuOpPlantio.setText("Plantio");
        MenuOpPlantio.setEnabled(false);
        MenuOpPlantio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuOpPlantioActionPerformed(evt);
            }
        });
        jMenu5.add(MenuOpPlantio);
        jMenu5.add(jSeparator15);

        MenuOpOperacoes.setText("Operações Mecânizadas");
        MenuOpOperacoes.setEnabled(false);

        jmenu_anotacoes.setText("Anotações");
        jmenu_anotacoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmenu_anotacoesActionPerformed(evt);
            }
        });
        MenuOpOperacoes.add(jmenu_anotacoes);
        MenuOpOperacoes.add(jSeparator32);

        jmenu_resumoOperacoes.setText("Resumo de Operações");
        jmenu_resumoOperacoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmenu_resumoOperacoesActionPerformed(evt);
            }
        });
        MenuOpOperacoes.add(jmenu_resumoOperacoes);

        jMenu5.add(MenuOpOperacoes);
        jMenu5.add(jSeparator16);

        MenuOpAplicacoes.setText("Aplicações");
        MenuOpAplicacoes.setEnabled(false);
        MenuOpAplicacoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuOpAplicacoesActionPerformed(evt);
            }
        });
        jMenu5.add(MenuOpAplicacoes);
        jMenu5.add(jSeparator17);

        MenuOpMonit.setText("Monitoramento");
        MenuOpMonit.setEnabled(false);
        jMenu5.add(MenuOpMonit);
        jMenu5.add(jSeparator27);

        MenuResumoOp.setText("Resumo Operações");
        MenuResumoOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuResumoOpActionPerformed(evt);
            }
        });
        jMenu5.add(MenuResumoOp);
        jMenu5.add(jSeparator29);

        MenuChuvas.setText("Registro de Chuvas");
        MenuChuvas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuChuvasActionPerformed(evt);
            }
        });
        jMenu5.add(MenuChuvas);

        Menu.add(jMenu5);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/sair.png"))); // NOI18N
        jMenu3.setText("Sair");
        jMenu3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });
        Menu.add(jMenu3);

        setJMenuBar(Menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 737, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 259, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuCadPropriedadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadPropriedadesActionPerformed
        fazendas = new FrmCadFazendas();
        tela.add(fazendas);
        getDimensaoTela();
        fazendas.setSize(fazendas.getSize().width, alturaTela);
        fazendas.setVisible(true);
    }//GEN-LAST:event_MenuCadPropriedadesActionPerformed

    private void MenuCadUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadUsuariosActionPerformed
        frmUsuario = new FrmCadUsuario();
        tela.add(frmUsuario);
        frmUsuario.setVisible(true);
    }//GEN-LAST:event_MenuCadUsuariosActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

    }//GEN-LAST:event_formWindowClosed

    private void formWindowDeactivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeactivated

    }//GEN-LAST:event_formWindowDeactivated

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int sair = JOptionPane.showConfirmDialog(null, "Deseja Sair?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void menuEntGadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEntGadoActionPerformed
        EntradaGado = new frmEntradaGado();
        tela.add(EntradaGado);
        EntradaGado.setVisible(true);
    }//GEN-LAST:event_menuEntGadoActionPerformed

    private void menuCompraGadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCompraGadoActionPerformed
        CompraGado = new frmCompraGado();
        tela.add(CompraGado);
        try {
            CompraGado.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        CompraGado.setVisible(true);
    }//GEN-LAST:event_menuCompraGadoActionPerformed

    private void menuVendaGadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuVendaGadoActionPerformed
        VendaGado = new frmVendaGado();
        tela.add(VendaGado);
        VendaGado.setVisible(true);
    }//GEN-LAST:event_menuVendaGadoActionPerformed

    private void menuAbatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAbatesActionPerformed
        EscalasAbate = new frmEscalasAbate();
        tela.add(EscalasAbate);
        EscalasAbate.setVisible(true);
    }//GEN-LAST:event_menuAbatesActionPerformed

    private void menuSaidaGadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSaidaGadoActionPerformed
        SaidaGado = new frmSaidaGado();
        tela.add(SaidaGado);
        SaidaGado.setVisible(true);
    }//GEN-LAST:event_menuSaidaGadoActionPerformed

    private void MenuCadInsumosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadInsumosActionPerformed
        CadInsumos = new frmCadInsumos();
        tela.add(CadInsumos);
        CadInsumos.setVisible(true);
    }//GEN-LAST:event_MenuCadInsumosActionPerformed

    private void MenuPedidoInsumosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuPedidoInsumosActionPerformed
        PedidosInsumos = new frmPedidosInsumos();
        tela.add(PedidosInsumos);
        PedidosInsumos.setVisible(true);
    }//GEN-LAST:event_MenuPedidoInsumosActionPerformed

    private void menuAcertoFreteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAcertoFreteActionPerformed
        FreteGado = new frmFreteGado();
        tela.add(FreteGado);
        FreteGado.setVisible(true);
    }//GEN-LAST:event_menuAcertoFreteActionPerformed

    private void menuPagamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPagamentosActionPerformed
        //    Pagamentos = new frmPagamentos();
        //    tela.add(Pagamentos);
        //    Pagamentos.setVisible(true);
        frmPagamentos pagto = new frmPagamentos();
        tela.add(pagto);
        pagto.tp_nf.setExpanded(true);
        pagto.setVisible(true);

    }//GEN-LAST:event_menuPagamentosActionPerformed

    private void menuResPagtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuResPagtoActionPerformed
        ResPagamentos = new frmResPagamentos();
        tela.add(ResPagamentos);
        ResPagamentos.setVisible(true);
    }//GEN-LAST:event_menuResPagtoActionPerformed

    private void MenuCadFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadFornecedoresActionPerformed
        frmFornecedores = new frmCadFornecedores();
        tela.add(frmFornecedores);
        frmFornecedores.setVisible(true);
    }//GEN-LAST:event_MenuCadFornecedoresActionPerformed

    private void menuRelPagtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRelPagtoActionPerformed
        frmRelPagamento = new frmRelPagamento();
        tela.add(frmRelPagamento);
        try {
            frmRelPagamento.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmRelPagamento.setVisible(true);
    }//GEN-LAST:event_menuRelPagtoActionPerformed

    private void MenuPedidoEntInsumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuPedidoEntInsumoActionPerformed
        EntradaInsumos = new frmEntradaInsumos();
        tela.add(EntradaInsumos);
        EntradaInsumos.setVisible(true);
    }//GEN-LAST:event_MenuPedidoEntInsumoActionPerformed

    private void menuRecebimentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRecebimentosActionPerformed
        Recebimentos = new frmRecebimentos();
        tela.add(Recebimentos);
        centralizarJIF(Recebimentos);
        Recebimentos.setVisible(true);
    }//GEN-LAST:event_menuRecebimentosActionPerformed

    private void menuRelGadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRelGadoActionPerformed
        RelatoriosGado = new frmRelBovino();
        tela.add(RelatoriosGado);
        RelatoriosGado.setVisible(true);
    }//GEN-LAST:event_menuRelGadoActionPerformed

    private void menuTransfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTransfActionPerformed
        frmTransfContas = new frmTransfContas();
        tela.add(frmTransfContas);
        frmTransfContas.setVisible(true);
    }//GEN-LAST:event_menuTransfActionPerformed

    private void MenuPedidoSaidaInsumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuPedidoSaidaInsumoActionPerformed
        SaidaInsumos = new frmSaidaInsumos();
        tela.add(SaidaInsumos);
        SaidaInsumos.setVisible(true);
    }//GEN-LAST:event_MenuPedidoSaidaInsumoActionPerformed

    private void MenuPedidosEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuPedidosEstoqueActionPerformed
        frmEstoqueInsumos = new frmEstoqueInsumos();
        tela.add(frmEstoqueInsumos);
        frmEstoqueInsumos.setVisible(true);
    }//GEN-LAST:event_MenuPedidosEstoqueActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed

    }//GEN-LAST:event_jMenu3ActionPerformed

    private void MenuCadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadActionPerformed

    }//GEN-LAST:event_MenuCadActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        frmImportarFolhaPagto = new frmImportarFolhaPagto();
        tela.add(frmImportarFolhaPagto);
        setMaximum(frmImportarFolhaPagto);
        frmImportarFolhaPagto.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void MenuOpAplicacoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuOpAplicacoesActionPerformed
        frmAplicacoes = new frmAplicacoes();
        tela.add(frmAplicacoes);
        frmAplicacoes.setVisible(true);
    }//GEN-LAST:event_MenuOpAplicacoesActionPerformed

    private void jmenu_anotacoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmenu_anotacoesActionPerformed
        frmOperacoes frmOperacoes = new frmOperacoes(null, true);
        frmOperacoes.setLocationRelativeTo(null);
        frmOperacoes.setVisible(true);
    }//GEN-LAST:event_jmenu_anotacoesActionPerformed

    private void MenuResumoOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuResumoOpActionPerformed
        frmRelatorioOperacoes = new frmRelatorioOperacoes();
        tela.add(frmRelatorioOperacoes);
        frmRelatorioOperacoes.setVisible(true);
    }//GEN-LAST:event_MenuResumoOpActionPerformed

    private void MenuOpPlantioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuOpPlantioActionPerformed
        frmPlantio = new frmPlantio();
        tela.add(frmPlantio);
        Toolkit tk = Toolkit.getDefaultToolkit();
        try {
            Dimension d = tk.getScreenSize();
            frmPlantio.setSize(d.width, 600);
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmPlantio.setVisible(true);
    }//GEN-LAST:event_MenuOpPlantioActionPerformed

    private void MenuCadPluviometrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadPluviometrosActionPerformed
        frmCadPluviometro = new frmCadPluviometro();
        tela.add(frmCadPluviometro);
        frmCadPluviometro.setVisible(true);
    }//GEN-LAST:event_MenuCadPluviometrosActionPerformed

    private void MenuChuvasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuChuvasActionPerformed
        frmPluviosidade = new frmPluviosidade();
        tela.add(frmPluviosidade);
        centralizarJIF(frmPluviosidade);
        frmPluviosidade.setVisible(true);
    }//GEN-LAST:event_MenuChuvasActionPerformed

    private void MenuCadColaboradoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadColaboradoesActionPerformed
        frmCadColaborador = new frmCadColaborador();
        tela.add(frmCadColaborador);
        frmCadColaborador.tp_filiacao.setExpanded(false);
        frmCadColaborador.tp_clas.setExpanded(false);
        frmCadColaborador.setSize(new Dimension(frmCadColaborador.getSize().width, tela.getSize().height));
        frmCadColaborador.setVisible(true);
    }//GEN-LAST:event_MenuCadColaboradoesActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed

    }//GEN-LAST:event_jMenu1ActionPerformed

    private void MenuResumoColaboradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuResumoColaboradorActionPerformed

        try {
            frmResumoCadColaborador = new frmResumoCadColaborador();
            tela.add(frmResumoCadColaborador);
            frmResumoCadColaborador.setMaximum(true);
            frmResumoCadColaborador.setVisible(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_MenuResumoColaboradorActionPerformed

    private void MenuPedidoCentralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuPedidoCentralActionPerformed

        FrmPedidosCentral centralPedidos = new FrmPedidosCentral();
        tela.add(centralPedidos);
        setMaximum(centralPedidos);
        centralPedidos.setVisible(true);


    }//GEN-LAST:event_MenuPedidoCentralActionPerformed

    private void jmenu_resumoOperacoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmenu_resumoOperacoesActionPerformed

        FrmResumoOperacoes resumoOpeacoes = new FrmResumoOperacoes();
        tela.add(resumoOpeacoes);
        setMaximum(resumoOpeacoes);
        resumoOpeacoes.setVisible(true);

    }//GEN-LAST:event_jmenu_resumoOperacoesActionPerformed

    private void menuRastreabilidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRastreabilidadeActionPerformed
        frmRastreabilidade = new FrmRastreabilidadeBovina();
        tela.add(frmRastreabilidade);

        setMaximum(frmRastreabilidade);
        frmRastreabilidade.setVisible(true);
        frmRastreabilidade.jXTaskPane2.setAnimated(true);
        frmRastreabilidade.jXTaskPane3.setAnimated(true);

    }//GEN-LAST:event_menuRastreabilidadeActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        teste frmTeste = new teste();
        tela.add(frmTeste);
        frmTeste.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void MenuCadPecasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadPecasActionPerformed
        FrmCadAlmoxarif frmCad = new FrmCadAlmoxarif();
        tela.add(frmCad);
        frmCad.setVisible(true);
    }//GEN-LAST:event_MenuCadPecasActionPerformed

    private void MenuConultaPecasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuConultaPecasActionPerformed
        FrmConsultaPecas consultaPecas = new FrmConsultaPecas();
        tela.add(consultaPecas);
        centralizarJIF(consultaPecas);
        consultaPecas.setVisible(true);
    }//GEN-LAST:event_MenuConultaPecasActionPerformed

    private void MenuEntradaSupriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuEntradaSupriActionPerformed
        FrmEntradaAlmoxarifado almoxarifado = new FrmEntradaAlmoxarifado();
        tela.add(almoxarifado);
        centralizarJIF(almoxarifado);
        almoxarifado.setVisible(true);
    }//GEN-LAST:event_MenuEntradaSupriActionPerformed

    private void MenuImportarNFeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuImportarNFeActionPerformed
        FrmImportarNFe importarNfe = new FrmImportarNFe();
        tela.add(importarNfe);
        importarNfe.setVisible(true);
    }//GEN-LAST:event_MenuImportarNFeActionPerformed

    private void MenuPedidoSupriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuPedidoSupriActionPerformed
        FrmPedidosFazenda pedido = new FrmPedidosFazenda();
        tela.add(pedido);
        centralizarJIF(pedido);
        pedido.setVisible(true);
    }//GEN-LAST:event_MenuPedidoSupriActionPerformed

    private void MenuSaidaSuprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuSaidaSuprActionPerformed
        FrmSaidaAlmoxarifado saidaAlmoxarifado = new FrmSaidaAlmoxarifado();
        tela.add(saidaAlmoxarifado);
        centralizarJIF(saidaAlmoxarifado);
        saidaAlmoxarifado.setVisible(true);
    }//GEN-LAST:event_MenuSaidaSuprActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        FrmCadUsuario teste = new FrmCadUsuario();
        tela.add(teste);
        teste.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void MenuEstoqueSuprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuEstoqueSuprActionPerformed
        FrmConsultaEstoqueAlmoxarifado consultaEstoque = new FrmConsultaEstoqueAlmoxarifado();
        tela.add(consultaEstoque);
        consultaEstoque.setVisible(true);
    }//GEN-LAST:event_MenuEstoqueSuprActionPerformed

    private void MenuCotacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCotacaoActionPerformed
        FrmPedidosCotacao frmCotacao = new FrmPedidosCotacao();
        tela.add(frmCotacao);
        setMaximum(frmCotacao);
        frmCotacao.setVisible(true);
    }//GEN-LAST:event_MenuCotacaoActionPerformed

    private void MenuSolicitacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuSolicitacaoActionPerformed
        FrmPedidosSolicitacao frmSolicitacao = new FrmPedidosSolicitacao();
        tela.add(frmSolicitacao);
        frmSolicitacao.setVisible(true);
    }//GEN-LAST:event_MenuSolicitacaoActionPerformed

    private void MenuAnaliseCotacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuAnaliseCotacaoActionPerformed
        FrmPedidosAnalise frmAnalise = new FrmPedidosAnalise();
        tela.add(frmAnalise);
        setMaximum(frmAnalise);
        frmAnalise.setVisible(true);
    }//GEN-LAST:event_MenuAnaliseCotacaoActionPerformed

    private void MenuFechamentoCotacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuFechamentoCotacaoActionPerformed
        FrmPedidosFechamento frmFechamento = new FrmPedidosFechamento();
        tela.add(frmFechamento);
        setMaximum(frmFechamento);
        frmFechamento.setVisible(true);

    }//GEN-LAST:event_MenuFechamentoCotacaoActionPerformed

    private void MenuCadTransportadoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadTransportadoraActionPerformed
        FrmCadTransportadora cadTransp = new FrmCadTransportadora();
        tela.add(cadTransp);
        cadTransp.setVisible(true);
    }//GEN-LAST:event_MenuCadTransportadoraActionPerformed

    private void MenuCadInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadInventarioActionPerformed
        FrmCadInventario cadI = new FrmCadInventario();
        tela.add(cadI);
        cadI.setVisible(true);
    }//GEN-LAST:event_MenuCadInventarioActionPerformed

    private void MenuCadModelosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadModelosActionPerformed
        FrmCadModelo cadM = new FrmCadModelo();
        tela.add(cadM);
        cadM.setVisible(true);
    }//GEN-LAST:event_MenuCadModelosActionPerformed

    private void MenuCadMarcasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadMarcasActionPerformed
        FrmCadMarca cadM = new FrmCadMarca();
        tela.add(cadM);
        cadM.setVisible(true);
    }//GEN-LAST:event_MenuCadMarcasActionPerformed

    private void MenuCadCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadCategoriasActionPerformed
        FrmCadCategoria cadC = new FrmCadCategoria();
        tela.add(cadC);
        cadC.setVisible(true);
    }//GEN-LAST:event_MenuCadCategoriasActionPerformed

    private void MenuCadCentroResultadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadCentroResultadoActionPerformed
        FrmCadCentroResultado resultado = new FrmCadCentroResultado();
        tela.add(resultado);
        centralizarJIF(resultado);
        resultado.setVisible(true);
    }//GEN-LAST:event_MenuCadCentroResultadoActionPerformed

    private void MenuCadAtividadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadAtividadeActionPerformed
        FrmCadAtividade atividade = new FrmCadAtividade();
        tela.add(atividade);
        centralizarJIF(atividade);
        atividade.setVisible(true);
    }//GEN-LAST:event_MenuCadAtividadeActionPerformed

    private void MenuCadAnoSafraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadAnoSafraActionPerformed
        FrmCadAnoSafra safra = new FrmCadAnoSafra();
        tela.add(safra);
        centralizarJIF(safra);
        safra.setVisible(true);
    }//GEN-LAST:event_MenuCadAnoSafraActionPerformed

    private void MenuCadCultivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadCultivoActionPerformed
        FrmCadCultivo cultivo = new FrmCadCultivo();
        tela.add(cultivo);
        centralizarJIF(cultivo);
        cultivo.setVisible(true);
    }//GEN-LAST:event_MenuCadCultivoActionPerformed

    private void MenuCadCulturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadCulturaActionPerformed
        FrmCadCultura cultura = new FrmCadCultura();
        tela.add(cultura);
        centralizarJIF(cultura);
        cultura.setVisible(true);
    }//GEN-LAST:event_MenuCadCulturaActionPerformed

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        int sair = JOptionPane.showConfirmDialog(null, "Deseja Sair?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_jMenu3MouseClicked

    private void MenuCadContaBancariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadContaBancariaActionPerformed
        FrmCadContaBancaria conta = new FrmCadContaBancaria();
        tela.add(conta);
        centralizarJIF(conta);
        conta.setVisible(true);
    }//GEN-LAST:event_MenuCadContaBancariaActionPerformed

    private void MenuCadBancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadBancoActionPerformed
        FrmCadBanco banco = new FrmCadBanco();
        tela.add(banco);
        centralizarJIF(banco);
        banco.setVisible(true);
    }//GEN-LAST:event_MenuCadBancoActionPerformed

    private void MenuCadCompradorGadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadCompradorGadosActionPerformed
        FrmCadCompradorGados comprador = new FrmCadCompradorGados();
        tela.add(comprador);
        centralizarJIF(comprador);
        comprador.setVisible(true);
    }//GEN-LAST:event_MenuCadCompradorGadosActionPerformed

    private void MenuCadGrupoContasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadGrupoContasActionPerformed
        FrmCadGrupoContas grupo = new FrmCadGrupoContas();
        tela.add(grupo);
        centralizarJIF(grupo);
        grupo.setVisible(true);
    }//GEN-LAST:event_MenuCadGrupoContasActionPerformed

    private void MenuCadMoedasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadMoedasActionPerformed
        FrmCadMoeda moeda = new FrmCadMoeda();
        tela.add(moeda);
        centralizarJIF(moeda);
        moeda.setVisible(true);
    }//GEN-LAST:event_MenuCadMoedasActionPerformed

    private void menuTransfCCustoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTransfCCustoActionPerformed
        FrmTransPlanoConta tPlanoConta = new FrmTransPlanoConta();
        tela.add(tPlanoConta);
        centralizarJIF(tPlanoConta);
        tPlanoConta.setVisible(true);

    }//GEN-LAST:event_menuTransfCCustoActionPerformed

    private void menuResumoRecebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuResumoRecebActionPerformed
        frmRecebResumo resumo = new frmRecebResumo();
        tela.add(resumo);
        centralizarJIF(resumo);
        resumo.setVisible(true);
    }//GEN-LAST:event_menuResumoRecebActionPerformed

    private void MenuRemessaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuRemessaActionPerformed
        FrmRemessaMercadorias remessa = new FrmRemessaMercadorias();
        tela.add(remessa);
        remessa.tp_importar.setExpanded(false);
        remessa.tp_manual.setExpanded(false);
        centralizarJIF(remessa);
        remessa.setVisible(true);
        remessa.tp_manual.setAnimated(true);
        remessa.tp_importar.setAnimated(true);
    }//GEN-LAST:event_MenuRemessaActionPerformed

    private void MenuConsultaRemessaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuConsultaRemessaActionPerformed
        FrmRemessaConsulta consulta = new FrmRemessaConsulta();
        tela.add(consulta);
        centralizarJIF(consulta);
        consulta.setVisible(true);
    }//GEN-LAST:event_MenuConsultaRemessaActionPerformed

    private void MenuCadUnidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadUnidadesActionPerformed
        FrmCadUnidades unidades = new FrmCadUnidades();
        tela.add(unidades);
        centralizarJIF(unidades);
        unidades.setVisible(true);
    }//GEN-LAST:event_MenuCadUnidadesActionPerformed

    /**
     * @param args the command line arguments
     */
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
                    //LookAndFeelAddons.setAddon(MacOSXLookAndFeelAddons.class);
                    break;
                }
            }
            UIManager.getLookAndFeelDefaults().put(JXTaskPane.uiClassID, "org.jdesktop.swingx.plaf.misc.GlossyTaskPaneUI");

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Almoxarifado;
    private javax.swing.JMenuBar Menu;
    public static javax.swing.JMenuItem MenuAnaliseCotacao;
    public static javax.swing.JMenuItem MenuArmEntrada;
    public static javax.swing.JMenuItem MenuArmSaida;
    public static javax.swing.JMenuItem MenuArmVenda;
    public javax.swing.JMenu MenuCad;
    public static javax.swing.JMenuItem MenuCadAnoSafra;
    public static javax.swing.JMenuItem MenuCadAtividade;
    public static javax.swing.JMenuItem MenuCadBanco;
    public static javax.swing.JMenuItem MenuCadCategorias;
    public static javax.swing.JMenuItem MenuCadCentroResultado;
    public static javax.swing.JMenuItem MenuCadColaboradoes;
    public static javax.swing.JMenuItem MenuCadCompradorGados;
    public static javax.swing.JMenuItem MenuCadContaBancaria;
    public static javax.swing.JMenuItem MenuCadCultivo;
    public static javax.swing.JMenuItem MenuCadCultura;
    public static javax.swing.JMenuItem MenuCadFornecedores;
    public static javax.swing.JMenuItem MenuCadGrupoContas;
    public static javax.swing.JMenuItem MenuCadInsumos;
    public static javax.swing.JMenuItem MenuCadInventario;
    public static javax.swing.JMenuItem MenuCadMarcas;
    public static javax.swing.JMenuItem MenuCadModelos;
    public static javax.swing.JMenuItem MenuCadMoedas;
    public static javax.swing.JMenuItem MenuCadPecas;
    public static javax.swing.JMenuItem MenuCadPlanoContas;
    public static javax.swing.JMenuItem MenuCadPluviometros;
    public static javax.swing.JMenuItem MenuCadPropriedades;
    public static javax.swing.JMenuItem MenuCadTipoDocumentos;
    public static javax.swing.JMenuItem MenuCadTransportadora;
    public static javax.swing.JMenuItem MenuCadUnidades;
    public static javax.swing.JMenuItem MenuCadUsuarios;
    public static javax.swing.JMenuItem MenuChuvas;
    public static javax.swing.JMenuItem MenuConsultaRemessa;
    public static javax.swing.JMenuItem MenuConultaPecas;
    public static javax.swing.JMenuItem MenuCotacao;
    public static javax.swing.JMenuItem MenuEntradaSupri;
    public static javax.swing.JMenuItem MenuEstoqueSupr;
    public static javax.swing.JMenuItem MenuFechamentoCotacao;
    public static javax.swing.JMenuItem MenuImportarNFe;
    public static javax.swing.JMenuItem MenuOpAplicacoes;
    public static javax.swing.JMenuItem MenuOpMonit;
    public static javax.swing.JMenu MenuOpOperacoes;
    public static javax.swing.JMenuItem MenuOpPlantio;
    public static javax.swing.JMenuItem MenuPedidoCentral;
    public static javax.swing.JMenuItem MenuPedidoEntInsumo;
    public static javax.swing.JMenuItem MenuPedidoInsumos;
    public static javax.swing.JMenuItem MenuPedidoSaidaInsumo;
    public static javax.swing.JMenuItem MenuPedidoSupri;
    public static javax.swing.JMenuItem MenuPedidosEstoque;
    public static javax.swing.JMenuItem MenuRemessa;
    public static javax.swing.JMenuItem MenuResumoColaborador;
    public static javax.swing.JMenuItem MenuResumoOp;
    public static javax.swing.JMenuItem MenuSaidaSupr;
    public static javax.swing.JMenuItem MenuSolicitacao;
    public static javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu14;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPopupMenu.Separator jSeparator31;
    private javax.swing.JPopupMenu.Separator jSeparator34;
    private javax.swing.JPopupMenu.Separator jSeparator35;
    private javax.swing.JPopupMenu.Separator jSeparator36;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator46;
    private javax.swing.JPopupMenu.Separator jSeparator47;
    private javax.swing.JPopupMenu.Separator jSeparator48;
    private javax.swing.JPopupMenu.Separator jSeparator49;
    private javax.swing.JPopupMenu.Separator jSeparator50;
    private javax.swing.JPopupMenu.Separator jSeparator53;
    private javax.swing.JPopupMenu.Separator jSeparator54;
    private javax.swing.JPopupMenu.Separator jSeparator55;
    private javax.swing.JPopupMenu.Separator jSeparator56;
    private javax.swing.JPopupMenu.Separator jSeparator57;
    private javax.swing.JPopupMenu.Separator jSeparator58;
    private javax.swing.JPopupMenu.Separator jSeparator59;
    private javax.swing.JPopupMenu.Separator jSeparator60;
    private javax.swing.JPopupMenu.Separator jSeparator61;
    private javax.swing.JPopupMenu.Separator jSeparator62;
    private javax.swing.JPopupMenu.Separator jSeparator63;
    private javax.swing.JPopupMenu.Separator jSeparator64;
    private javax.swing.JPopupMenu.Separator jSeparator65;
    public static javax.swing.JMenuItem jmenu_anotacoes;
    private javax.swing.JMenuItem jmenu_resumoOperacoes;
    public static javax.swing.JMenuItem menuAbates;
    public static javax.swing.JMenuItem menuAcertoFrete;
    public static javax.swing.JMenuItem menuCompraGado;
    public static javax.swing.JMenuItem menuEntGado;
    public javax.swing.JMenu menuMovGado;
    public static javax.swing.JMenuItem menuPagamentos;
    private javax.swing.JMenuItem menuRastreabilidade;
    public static javax.swing.JMenuItem menuRecebimentos;
    public static javax.swing.JMenuItem menuRelGado;
    public static javax.swing.JMenuItem menuRelPagto;
    public static javax.swing.JMenuItem menuResPagto;
    public static javax.swing.JMenuItem menuResumoReceb;
    public static javax.swing.JMenuItem menuSaidaGado;
    public static javax.swing.JMenuItem menuTransf;
    private javax.swing.JMenuItem menuTransfCCusto;
    public static javax.swing.JMenuItem menuVendaGado;
    // End of variables declaration//GEN-END:variables

    public JInternalFrame centralizarJIF(JInternalFrame jif) {
        Dimension dFrame = new Dimension(tela.getSize());
        Dimension dJif = new Dimension(jif.getSize());
        //jif.setLocation((dFrame.width - dJif.width) / 2, (dFrame.height - dJif.height) / 2);
        jif.setLocation((dFrame.width - dJif.width) / 2, 30);
        return jif;
    }

    private Dimension getTelaSize() {
        Dimension d = new Dimension();
        Toolkit tk = Toolkit.getDefaultToolkit();
        d = tk.getScreenSize();
        return d;
    }

    private Integer getScreenResolution() {
        Integer d;
        Toolkit tk = Toolkit.getDefaultToolkit();
        d = tk.getScreenResolution();
        return d;
    }

    private void setMaximum(JInternalFrame jif) {
        try {
            jif.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getDimensaoTela() {
        alturaTela = tela.getHeight();
        larguraTela = tela.getWidth();
    }

}
