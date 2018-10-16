package GUI;

import Beans.AtividadeBeans;
import Beans.CadColaboradorBeans;
import Beans.CEIBeans;
import Beans.CadColaboradorClassBeans;
import Beans.CadUsuario;
import Beans.CargosBeans;
import Beans.CentroDeResultado;
import Beans.CidadesBeans;
import Beans.ListDeficienciaBeans;
import Beans.ListEstadoCivil;
import Beans.EstadosBeans;
import Beans.ListSetorTrabalhoBeans;
import Beans.PropriedadesBeans;
import DAO.CadColaboradorDAO;
import DAO.Diversas;
import DAO.DiversasHibernate;
import static GUI.Principal.ListaPropriedades;
import static GUI.Principal.listCEI;
import static GUI.Principal.listCargosFuncoes;
import static GUI.Principal.listDeficiencias;
import static GUI.Principal.listEstadoCivil;
import static GUI.Principal.listEstados;
import static GUI.Principal.listSetorTrabalho;
import GerarRelatorios.RelatoriosOperacoes;
import Icones.FormatarICO;
import TableModel.TableModelFilhosFuncionarios;
import Beans.FilhosFuncionariosBeans;
import Beans.PlanoContaBeans;
import static GUI.Principal.ListaPropriedadesPermitidas;
import static GUI.Principal.listPlanoContas;
import static GUI.Principal.listaAtividades;
import static GUI.Principal.listaCentroResultado;
import GerarRelatorios.RelatoriosFuncionario;
import TableModel.TableModelColaboradorClass;
import Utilitarios.CentralizarTabela;
import Utilitarios.Corretores;
import Utilitarios.ValidarPermissoes;
import com.toedter.calendar.JDateChooser;
import componentes.UJComboBox;
import java.awt.Component;
import java.awt.Container;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class frmCadColaborador extends javax.swing.JInternalFrame {

    Diversas DiversasD;
    CadColaboradorDAO FuncD;
    CadColaboradorBeans FuncB;
    FilhosFuncionariosBeans FilhosB;
    CadColaboradorClassBeans ClassB;
    MaskFormatter CPFMask;
    MaskFormatter TELEFONEMask;
    TableModelFilhosFuncionarios TbFilhos;
    TableModelColaboradorClass TbClass;
    CentralizarTabela Centralizar;

    public frmCadColaborador() {
        initComponents();
        Centralizar = new CentralizarTabela();
        FuncD = new CadColaboradorDAO();
        DiversasD = new Diversas();
        FuncB = new CadColaboradorBeans();
        FilhosB = new FilhosFuncionariosBeans();
        ClassB = new CadColaboradorClassBeans();
        carregarListCEI();
        carregarEstados();
        carregarFazPermitidas();
        carregarFazendas();
        carregarCargosFuncoes();
        carregarEstadoCivil();
        carregarDeficiencias();
        carregarSetores();
        carregarAtividades();
        carregarCentroResultado();
        carregarPlanoContas();
        carregarTabelaClass();
        maskFormaterCPF();
        desabilitarCampos();
        tabelaFilhos();
        System.out.println("FIM CONSTRUTOR Cad Colaborador");
    }

    private void carregarListCEI() {
        if (listCEI == null) {
            listCEI = DiversasHibernate.getListaCEI();
        }
        CEIBeans b = new CEIBeans();
        b.setIdCEI(0);
        b.setDescricao("-");
        cb_cei.addItem(b);
        for (CEIBeans list : listCEI) {
            cb_cei.addItem(list);
        }

    }

    private void carregarEstados() {
        if (listEstados == null) {
            listEstados = DiversasD.ListEstados();
        }
        EstadosBeans b = new EstadosBeans();
        b.setIdEtado(0);
        b.setEstado("-");
        b.setAbreviacao("-");
        cb_ufFunc.addItem(b);
        cb_ufBeneficiario.addItem(b);
        cb_ufNascimentoFunc.addItem(b);
        cb_ufCasamento.addItem(b);
        cb_filhoUf.addItem(b);
        cb_ufConta.addItem(b);
        cb_ufCTPS.addItem(b);
        for (EstadosBeans list : listEstados) {
            cb_ufFunc.addItem(list);
            cb_ufBeneficiario.addItem(list);
            cb_ufNascimentoFunc.addItem(list);
            cb_ufCasamento.addItem(list);
            cb_filhoUf.addItem(list);
            cb_ufConta.addItem(list);
            cb_ufCTPS.addItem(list);
        }
    }

    private void carregarFazPermitidas() {
        //CadUsuario CadB = new CadUsuario();
        if (ListaPropriedadesPermitidas == null) {
            ListaPropriedadesPermitidas = DiversasHibernate.getListaFazendasPermitidas();
        }
        PropriedadesBeans P = new PropriedadesBeans();
        P.setCodigo(0);
        P.setNome("-");
        cb_fazenda.addItem(P);
        for (PropriedadesBeans l : ListaPropriedadesPermitidas) {
            cb_fazenda.addItem(l);
        }

    }

    private void carregarCargosFuncoes() {
        if (listCargosFuncoes == null) {
            listCargosFuncoes = DiversasHibernate.getListaCargos();
        }
        cb_funcao.addItem(new CargosBeans(0, "-", 0.00));
        for (CargosBeans list : listCargosFuncoes) {
            cb_funcao.addItem(list);
        }
    }

    private void carregarSetores() {
        if (listSetorTrabalho == null) {
            listSetorTrabalho = DiversasHibernate.getListaSetoresTrabalho();
        }
        cb_setor.addItem(new ListSetorTrabalhoBeans(0, "-"));
        for (ListSetorTrabalhoBeans list : listSetorTrabalho) {
            cb_setor.addItem(list);
        }
    }

    private void carregarDeficiencias() {
        if (listDeficiencias == null) {
            listDeficiencias = DiversasD.ListDeficiencias();
        }
        for (ListDeficienciaBeans list : listDeficiencias) {
            cb_deficiencia.addItem(list);
        }
    }

    private void carregarEstadoCivil() {
        if (listEstadoCivil == null) {
            listEstadoCivil = DiversasD.ListEstadoCivil();
        }
        for (ListEstadoCivil list : listEstadoCivil) {
            cb_estadoCivil.addItem(list);
        }
    }

    private void carregarAtividades() {
        if (listaAtividades == null) {
            listaAtividades = DiversasHibernate.getListaAtividade();
        }
        cb_atividade.addItem(new AtividadeBeans(0, "-", true));
        for (AtividadeBeans a : listaAtividades) {
            cb_atividade.addItem(a);
        }
    }

    private void carregarFazendas() {
        if (ListaPropriedades == null) {
            ListaPropriedades = DiversasHibernate.getListaFazendas();
        }
        cb_fazendaClas.addItem(new PropriedadesBeans(0, "-"));
        for (PropriedadesBeans L : ListaPropriedades) {
            cb_fazendaClas.addItem(L);
        }
    }

    private void carregarCentroResultado() {
        if (listaCentroResultado == null) {
            listaCentroResultado = DiversasHibernate.getListaCentroResultado();
        }
        cb_centroResultado.addItem(new CentroDeResultado(0L, "-"));
        for (CentroDeResultado c : listaCentroResultado) {
            cb_centroResultado.addItem(c);
        }
    }

    private void carregarCentroResultado(Integer idAtividade, UJComboBox combo) {
        if (listaCentroResultado == null) {
            listaCentroResultado = DiversasHibernate.getListaCentroResultado();
        }
        combo.removeAllItems();
        combo.addItem(new CentroDeResultado(0L, "-"));
        for (CentroDeResultado c : listaCentroResultado) {
            if (Objects.equals(c.getAtividade().getID(), idAtividade)) {
                combo.addItem(c);
            }
        }
    }

    private void carregarPlanoContas() {
        if (listPlanoContas == null) {
            listPlanoContas = DiversasHibernate.getPlanoConta();
        }
        cb_plano.addItem(new PlanoContaBeans(0, "-"));
        for (PlanoContaBeans p : listPlanoContas) {
            cb_plano.addItem(p);
        }
    }

    private void maskFormaterCPF() {
        try {
            CPFMask = new MaskFormatter("###.###.###-##");
            txt_cpfFunc.setFormatterFactory(new DefaultFormatterFactory(CPFMask));
            txt_cpfConj.setFormatterFactory(new DefaultFormatterFactory(CPFMask));
            txt_cpfFilho.setFormatterFactory(new DefaultFormatterFactory(CPFMask));

            TELEFONEMask = new MaskFormatter("(##)#####-####");
            txt_telBenef.setFormatterFactory(new DefaultFormatterFactory(TELEFONEMask));
            txt_telFunc.setFormatterFactory(new DefaultFormatterFactory(TELEFONEMask));

        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    private TableModelFilhosFuncionarios getTbFilhos() {
        if (TbFilhos == null) {
            TbFilhos = new TableModelFilhosFuncionarios();
        }
        return TbFilhos;
    }

    private JTable tabelaFilhos() {
        tb_filhos.setModel(getTbFilhos());
        Centralizar.centralizarTabela(tb_filhos);
        ((DefaultTableCellRenderer) tb_filhos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        tb_filhos.getColumnModel().getColumn(TbFilhos.ID).setMinWidth(0);// ID
        tb_filhos.getColumnModel().getColumn(TbFilhos.ID).setMaxWidth(0);// ID
        tb_filhos.getColumnModel().getColumn(TbFilhos.ID).setPreferredWidth(0);// ID

        return tb_filhos;
    }

    private JTable carregarTabelaClass() {
        Centralizar = new CentralizarTabela();
        tb_class.setModel(getTableModel());
        Centralizar.centralizarTabela(tb_class);
        ((DefaultTableCellRenderer) tb_class.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        for (int c = 0; c < TbClass.getColumnCount(); c++) {
            if (c == TbClass.ID || c == TbClass.IDCOLABORADOR) {
                tb_class.getColumnModel().getColumn(c).setPreferredWidth(0);
                tb_class.getColumnModel().getColumn(c).setMinWidth(0);
                tb_class.getColumnModel().getColumn(c).setMaxWidth(0);
            }
        }

        tb_class.getColumnModel().getColumn(TbClass.CENTRO_RESULTADO).setPreferredWidth(120);
        tb_class.getColumnModel().getColumn(TbClass.PLANOCONTA).setPreferredWidth(120); // Plano COnta
        tb_class.getColumnModel().getColumn(TbClass.FAZENDA).setPreferredWidth(120); // Fazenda
        tb_class.getColumnModel().getColumn(TbClass.VALOR).setPreferredWidth(80); // valor

        return tb_class;
    }

    private TableModelColaboradorClass getTableModel() {
        if (TbClass == null) {
            TbClass = new TableModelColaboradorClass();
        }
        return TbClass;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        cb_cei = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        txt_codigoMaster = new javax.swing.JTextField();
        cb_tipoContrato = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        cb_fazenda = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        cb_funcao = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        txt_salario = new javax.swing.JTextField();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        txt_dtAdmissao =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        txt_dtDemissao =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        cb_dsr = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel56 = new javax.swing.JLabel();
        txt_hEnt1 = new javax.swing.JTextField();
        txt_hSaida1 = new javax.swing.JTextField();
        javax.swing.JLabel jLabel57 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel58 = new javax.swing.JLabel();
        txt_hEnt2 = new javax.swing.JTextField();
        javax.swing.JLabel jLabel59 = new javax.swing.JLabel();
        txt_hSaida2 = new javax.swing.JTextField();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        cb_status = new javax.swing.JComboBox<>();
        ch_comodato = new javax.swing.JCheckBox();
        javax.swing.JLabel jLabel33 = new javax.swing.JLabel();
        cb_setor = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel86 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        txt_zona = new javax.swing.JTextField();
        javax.swing.JLabel jLabel23 = new javax.swing.JLabel();
        txt_secao = new javax.swing.JTextField();
        javax.swing.JLabel jLabel24 = new javax.swing.JLabel();
        txt_habilitacao = new javax.swing.JTextField();
        javax.swing.JLabel jLabel25 = new javax.swing.JLabel();
        cb_categoriaHab = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel26 = new javax.swing.JLabel();
        txt_reservista = new javax.swing.JTextField();
        javax.swing.JLabel jLabel27 = new javax.swing.JLabel();
        txt_dtNascFunc =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        ch_deficiente = new javax.swing.JCheckBox();
        cb_deficiencia = new javax.swing.JComboBox<>();
        txt_cpfFunc = new javax.swing.JFormattedTextField();
        javax.swing.JLabel jLabel29 = new javax.swing.JLabel();
        txt_cartaoSUS = new javax.swing.JTextField();
        javax.swing.JLabel jLabel30 = new javax.swing.JLabel();
        cb_corPele = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        txt_nomeFunc = new javax.swing.JTextField();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel48 = new javax.swing.JLabel();
        cb_escolaridade = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel13 = new javax.swing.JLabel();
        txt_rgFunc = new javax.swing.JTextField();
        javax.swing.JLabel jLabel14 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel49 = new javax.swing.JLabel();
        txt_orgExpFunc = new javax.swing.JTextField();
        txt_beneficiario = new javax.swing.JTextField();
        javax.swing.JLabel jLabel15 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel50 = new javax.swing.JLabel();
        txt_dtRgFunc =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        txt_endFunc = new javax.swing.JTextField();
        javax.swing.JLabel jLabel16 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel51 = new javax.swing.JLabel();
        txt_ctpsFunc = new javax.swing.JTextField();
        txt_endBeneficiario = new javax.swing.JTextField();
        javax.swing.JLabel jLabel17 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel52 = new javax.swing.JLabel();
        cb_ufFunc = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel53 = new javax.swing.JLabel();
        cb_ufBeneficiario = new javax.swing.JComboBox<>();
        txt_serieCtps = new javax.swing.JTextField();
        javax.swing.JLabel jLabel18 = new javax.swing.JLabel();
        txt_dtCtps =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        txt_pis = new javax.swing.JTextField();
        javax.swing.JLabel jLabel54 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel19 = new javax.swing.JLabel();
        txt_cidadeFunc = new javax.swing.JTextField();
        javax.swing.JLabel jLabel20 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel55 = new javax.swing.JLabel();
        txt_dtPis =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        txt_cidadeBeneficiario = new javax.swing.JTextField();
        javax.swing.JLabel jLabel21 = new javax.swing.JLabel();
        txt_titulo = new javax.swing.JTextField();
        javax.swing.JLabel jLabel22 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel77 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel78 = new javax.swing.JLabel();
        txt_telFunc = new javax.swing.JFormattedTextField();
        txt_telBenef = new javax.swing.JFormattedTextField();
        javax.swing.JLabel jLabel87 = new javax.swing.JLabel();
        cb_ufCTPS = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel89 = new javax.swing.JLabel();
        txt_sexo = new javax.swing.JTextField();
        javax.swing.JLabel jLabel90 = new javax.swing.JLabel();
        txt_nCalcado = new javax.swing.JTextField();
        javax.swing.JLabel jLabel91 = new javax.swing.JLabel();
        txt_bairroFunc = new javax.swing.JTextField();
        javax.swing.JLabel jLabel92 = new javax.swing.JLabel();
        txt_bairroBeneficiario = new javax.swing.JTextField();
        javax.swing.JLabel jLabel93 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel94 = new javax.swing.JLabel();
        txt_TipoSangue = new javax.swing.JTextField();
        txt_TipoAlergia = new javax.swing.JTextField();
        tp_filiacao = new org.jdesktop.swingx.JXTaskPane();
        jPanel3 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel31 = new javax.swing.JLabel();
        txt_pai = new javax.swing.JTextField();
        javax.swing.JLabel jLabel32 = new javax.swing.JLabel();
        txt_mae = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel79 = new javax.swing.JLabel();
        txt_nascimentoLivro = new javax.swing.JTextField();
        javax.swing.JLabel jLabel80 = new javax.swing.JLabel();
        txt_nascimentoFolha = new javax.swing.JTextField();
        javax.swing.JLabel jLabel81 = new javax.swing.JLabel();
        txt_nascimentoNumero = new javax.swing.JTextField();
        javax.swing.JLabel jLabel82 = new javax.swing.JLabel();
        txt_dtCertidaoNasc =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        txt_nascimentoCidadeFunc = new javax.swing.JTextField();
        javax.swing.JLabel jLabel83 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel84 = new javax.swing.JLabel();
        cb_ufNascimentoFunc = new javax.swing.JComboBox<>();
        jXTaskPane4 = new org.jdesktop.swingx.JXTaskPane();
        jPanel4 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel37 = new javax.swing.JLabel();
        txt_nomeConjuge = new javax.swing.JTextField();
        javax.swing.JLabel jLabel41 = new javax.swing.JLabel();
        txt_profissaoConj = new javax.swing.JTextField();
        javax.swing.JLabel jLabel43 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel44 = new javax.swing.JLabel();
        txt_rgConj = new javax.swing.JTextField();
        javax.swing.JLabel jLabel45 = new javax.swing.JLabel();
        txt_orgConj = new javax.swing.JTextField();
        cb_escolaridadeConj = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel47 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel60 = new javax.swing.JLabel();
        txt_casamentoLivro = new javax.swing.JTextField();
        javax.swing.JLabel jLabel61 = new javax.swing.JLabel();
        txt_casamentoFolha = new javax.swing.JTextField();
        javax.swing.JLabel jLabel62 = new javax.swing.JLabel();
        txt_casamentoNumero = new javax.swing.JTextField();
        javax.swing.JLabel jLabel46 = new javax.swing.JLabel();
        txt_dtCasamento =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        txt_casamentoCidade = new javax.swing.JTextField();
        javax.swing.JLabel jLabel64 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel63 = new javax.swing.JLabel();
        cb_ufCasamento = new javax.swing.JComboBox<>();
        txt_cpfConj = new javax.swing.JFormattedTextField();
        javax.swing.JLabel jLabel28 = new javax.swing.JLabel();
        cb_estadoCivil = new javax.swing.JComboBox<>();
        jXTaskPane5 = new org.jdesktop.swingx.JXTaskPane();
        ch_filhos = new javax.swing.JCheckBox();
        ch_salario_familia = new javax.swing.JCheckBox();
        jPanel6 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel39 = new javax.swing.JLabel();
        txt_nomeFilho = new javax.swing.JTextField();
        javax.swing.JLabel jLabel65 = new javax.swing.JLabel();
        cb_filhoUf = new javax.swing.JComboBox<>();
        txt_filhoLivro = new javax.swing.JTextField();
        txt_filhoCidade = new javax.swing.JTextField();
        javax.swing.JLabel jLabel68 = new javax.swing.JLabel();
        txt_filhoN = new javax.swing.JTextField();
        javax.swing.JLabel jLabel69 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel70 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel67 = new javax.swing.JLabel();
        txt_filhoFolha = new javax.swing.JTextField();
        javax.swing.JLabel jLabel66 = new javax.swing.JLabel();
        txt_dtNascFilho =  new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        javax.swing.JLabel jLabel71 = new javax.swing.JLabel();
        btn_addFilho = new javax.swing.JButton();
        btn_filhoDel = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_filhos = new javax.swing.JTable();
        txt_cpfFilho = new javax.swing.JFormattedTextField();
        jXTaskPane1 = new org.jdesktop.swingx.JXTaskPane();
        jPanel7 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel38 = new javax.swing.JLabel();
        txt_banco = new javax.swing.JTextField();
        javax.swing.JLabel jLabel40 = new javax.swing.JLabel();
        txt_codigoBanco = new javax.swing.JTextField();
        javax.swing.JLabel jLabel42 = new javax.swing.JLabel();
        txt_agencia = new javax.swing.JTextField();
        javax.swing.JLabel jLabel72 = new javax.swing.JLabel();
        txt_conta = new javax.swing.JTextField();
        javax.swing.JLabel jLabel73 = new javax.swing.JLabel();
        txt_cidadeConta = new javax.swing.JTextField();
        javax.swing.JLabel jLabel74 = new javax.swing.JLabel();
        cb_ufConta = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel75 = new javax.swing.JLabel();
        cb_tipoConta = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel76 = new javax.swing.JLabel();
        txt_titular = new javax.swing.JTextField();
        tp_clas = new org.jdesktop.swingx.JXTaskPane();
        jPanel10 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        cb_atividade = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        cb_fazendaClas = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        txt_valorClass = new javax.swing.JTextField();
        btn_addClass = new javax.swing.JButton();
        btn_editarClass = new javax.swing.JButton();
        btn_delClass = new javax.swing.JButton();
        cb_centroResultado = new componentes.UJComboBox();
        cb_plano = new javax.swing.JComboBox<>();
        scPClassifocacao = new javax.swing.JScrollPane();
        tb_class = new javax.swing.JTable();
        btn_novo = new javax.swing.JButton();
        btn_salvarPedido = new javax.swing.JButton();
        btn_editarPedido = new javax.swing.JButton();
        btn_excluirPedido = new javax.swing.JButton();

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Tipo Contrato");

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Cadastro de Colaborador");
        setNormalBounds(new java.awt.Rectangle(0, 0, 125, 10));
        setPreferredSize(new java.awt.Dimension(1065, 1080));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("CEI");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Código");

        txt_codigoMaster.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        cb_tipoContrato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Normal (44 h)", "Safra" }));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Fazenda");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Função");

        cb_funcao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_funcaoItemStateChanged(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Sálario Base");

        txt_salario.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Data Admissão");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Data Demissão");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("DSR");

        cb_dsr.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Domingo", "Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira", "Sábado" }));

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel56.setText("Horário");

        txt_hEnt1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txt_hSaida1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel57.setText("à");

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel58.setText("E");

        txt_hEnt2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel59.setText("à");

        txt_hSaida2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Status");

        cb_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Aguardando Registro", "Ativo", "Inativo", "INSS", "Licença Maternidade", "Licença Paternidade", "Outras Licenças" }));

        ch_comodato.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_comodato.setText("Comodato");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel33.setText("Setor");

        jLabel86.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel86.setText("Tipo Contrato");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_cei, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_codigoMaster, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel86)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_tipoContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_setor, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_funcao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_salario, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_dtAdmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_dsr, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel56)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_hEnt1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ch_comodato)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_status, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_dtDemissao, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_hSaida1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel58)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_hEnt2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_hSaida2)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(cb_funcao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel33)
                        .addComponent(cb_setor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(cb_cei, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(txt_codigoMaster, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cb_tipoContrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(cb_fazenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel86)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txt_salario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel8)
                        .addComponent(txt_dtAdmissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(cb_dsr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel56)
                        .addComponent(txt_hEnt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel57)
                        .addComponent(txt_hSaida1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel58)
                        .addComponent(txt_hEnt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel59)
                        .addComponent(txt_hSaida2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel9)
                    .addComponent(txt_dtDemissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(cb_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ch_comodato)))
                .addContainerGap())
        );

        jXTaskPane2.setTitle("Infornações do Colaborador");

        jPanel8.setPreferredSize(new java.awt.Dimension(1070, 270));

        txt_zona.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("Seção");

        txt_secao.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Habilitação");

        txt_habilitacao.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("Categoria");

        cb_categoriaHab.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "A", "B", "AB", "C", "D", "E" }));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("Cateira de Reservista");

        txt_reservista.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText("Data Nascimento");

        ch_deficiente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_deficiente.setText("Deficiente Fisíco");

        txt_cpfFunc.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setText("Cartão SUS");

        txt_cartaoSUS.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel30.setText("Cor Da Pele");

        cb_corPele.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Negra", "Branca", "Amarela", "Indigêna", "Pardo", "Outros" }));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Nome");

        txt_nomeFunc.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("CPF");

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel48.setText("Escolaridade");

        cb_escolaridade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Analfabeto", "Fundamental Incompleto", "Fundamental Completo", "Ensino Médio Incompleto", "Ensino Médio Completo", "Ensino Superior Incompleto", "Ensino Superior Completo" }));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("RG");

        txt_rgFunc.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Orgão Expedidor");

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel49.setText("Beneficiário");

        txt_orgExpFunc.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txt_beneficiario.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Emissão");

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel50.setText("Endereço Funcionário");

        txt_endFunc.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("CTPS");

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel51.setText("Endereço Beneficiário");

        txt_ctpsFunc.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txt_endBeneficiario.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Série");

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel52.setText("UF");

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel53.setText("UF");

        txt_serieCtps.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Data Emissão");

        txt_pis.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel54.setText("Cidade");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("PIS");

        txt_cidadeFunc.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Data Emissão PIS");

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel55.setText("Cidade");

        txt_cidadeBeneficiario.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Titulo Eleitor");

        txt_titulo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Zona");

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel77.setText("Telefone");

        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel78.setText("Telefone");

        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel87.setText("UF");

        jLabel89.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel89.setText("Sexo");

        txt_sexo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel90.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel90.setText("Número do calçado");

        txt_nCalcado.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel91.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel91.setText("Bairro");

        txt_bairroFunc.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel92.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel92.setText("Bairro");

        txt_bairroBeneficiario.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel93.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel93.setText("Tipo Sanguíneo");

        jLabel94.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel94.setText("Tipo de Alergia a Medicação");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_nomeFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_cpfFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_dtNascFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_ctpsFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_rgFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_orgExpFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_dtRgFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel89)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_sexo))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_serieCtps, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_dtCtps, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(jLabel87)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_ufCTPS, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel51)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_endBeneficiario))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel50)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_endFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel91)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_bairroFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel92)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_bairroBeneficiario)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel55)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_cidadeBeneficiario))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel54)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_cidadeFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel52)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_ufFunc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel53)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_ufBeneficiario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel77, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel78, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_telBenef, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(txt_telFunc)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_pis, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_dtPis, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_zona, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_secao, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel90))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_habilitacao, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_categoriaHab, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_reservista, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ch_deficiente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cb_deficiencia, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel49)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_beneficiario))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel29)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_cartaoSUS, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel30)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cb_corPele, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel48)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_escolaridade, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel93)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_TipoSangue, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel94)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_TipoAlergia)
                            .addComponent(txt_nCalcado, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(txt_nomeFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(jLabel13)
                        .addComponent(txt_rgFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)
                        .addComponent(txt_orgExpFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_cpfFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel15)
                        .addComponent(txt_dtRgFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel89)
                            .addComponent(txt_sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel27)
                        .addComponent(txt_dtNascFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txt_ctpsFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(txt_serieCtps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel18)
                        .addComponent(txt_dtCtps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel87)
                        .addComponent(cb_ufCTPS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel90)
                    .addComponent(txt_nCalcado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_secao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(txt_zona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(txt_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(txt_dtPis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(txt_pis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txt_habilitacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(cb_categoriaHab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(txt_reservista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ch_deficiente)
                    .addComponent(cb_deficiencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel29)
                    .addComponent(txt_cartaoSUS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel30)
                        .addComponent(cb_corPele, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel48)
                        .addComponent(cb_escolaridade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel93)
                        .addComponent(jLabel94)
                        .addComponent(txt_TipoSangue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_TipoAlergia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(txt_beneficiario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel52)
                        .addComponent(cb_ufFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel54)
                        .addComponent(txt_cidadeFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel91)
                        .addComponent(txt_bairroFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel50)
                        .addComponent(txt_endFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel77)
                        .addComponent(txt_telFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel53)
                        .addComponent(cb_ufBeneficiario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel55)
                        .addComponent(txt_cidadeBeneficiario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel92)
                        .addComponent(txt_bairroBeneficiario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel51)
                        .addComponent(txt_endBeneficiario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel78)
                        .addComponent(txt_telBenef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jXTaskPane2.getContentPane().add(jPanel8);

        tp_filiacao.setTitle("Filiação");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel31.setText("Pai");

        txt_pai.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel32.setText("Mãe");

        txt_mae.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Certidão de Nascimento"));

        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel79.setText("Livro nº");

        txt_nascimentoLivro.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel80.setText("Folha");

        txt_nascimentoFolha.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel81.setText("Nº");

        txt_nascimentoNumero.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel82.setText("Emissão");

        txt_nascimentoCidadeFunc.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel83.setText("Cidade");

        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel84.setText("UF");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel79)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nascimentoLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel80)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nascimentoFolha, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel81)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nascimentoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel82)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_dtCertidaoNasc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel83)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nascimentoCidadeFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel84)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_ufNascimentoFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_nascimentoLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel79)
                        .addComponent(txt_nascimentoFolha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel80)
                        .addComponent(txt_nascimentoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel81))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel82)
                        .addComponent(txt_dtCertidaoNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel84)
                            .addComponent(cb_ufNascimentoFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel83)
                            .addComponent(txt_nascimentoCidadeFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_pai, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_mae)
                        .addGap(431, 431, 431))
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txt_pai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(txt_mae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tp_filiacao.getContentPane().add(jPanel3);

        jXTaskPane4.setExpanded(false);
        jXTaskPane4.setTitle("Estado Cívil");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel37.setText("Nome");

        txt_nomeConjuge.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel41.setText("Profissão");

        txt_profissaoConj.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel43.setText("CPF");

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel44.setText("RG");

        txt_rgConj.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel45.setText("Orgão Expedidor");

        txt_orgConj.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        cb_escolaridadeConj.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Analfabeto", "Fundamental Incompleto", "Fundamental Completo", "Ensino Médio Incompleto", "Ensino Médio Completo", "Ensino Superior Incompleto", "Ensino Superior Completo" }));

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel47.setText("Escolaridade");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Certidão de Casamento"));

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel60.setText("Livro nº");

        txt_casamentoLivro.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel61.setText("Folha");

        txt_casamentoFolha.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel62.setText("Nº");

        txt_casamentoNumero.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel46.setText("Emissão");

        txt_casamentoCidade.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel64.setText("Cidade");

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel63.setText("UF");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_casamentoLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel61)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_casamentoFolha, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel62)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_casamentoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_dtCasamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel64)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_casamentoCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel63)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_ufCasamento, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_casamentoLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel60)
                        .addComponent(txt_casamentoFolha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel61)
                        .addComponent(txt_casamentoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel62))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel46)
                        .addComponent(txt_dtCasamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel63)
                            .addComponent(cb_ufCasamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel64)
                            .addComponent(txt_casamentoCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        txt_cpfConj.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setText("Estado Civíl");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel47)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_escolaridadeConj, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel41)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_profissaoConj, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_estadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel37)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_nomeConjuge)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_cpfConj, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_rgConj, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel45)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_orgConj, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel28)
                        .addComponent(cb_estadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(txt_nomeConjuge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel43)
                            .addComponent(jLabel44)
                            .addComponent(txt_rgConj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45)
                            .addComponent(txt_orgConj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_cpfConj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel47)
                        .addComponent(cb_escolaridadeConj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel41)
                        .addComponent(txt_profissaoConj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jXTaskPane4.getContentPane().add(jPanel4);

        jXTaskPane5.setExpanded(false);
        jXTaskPane5.setTitle("Filhos");

        ch_filhos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_filhos.setText("Filhos");
        jXTaskPane5.getContentPane().add(ch_filhos);

        ch_salario_familia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_salario_familia.setText("Recebimento do Salário Família");
        jXTaskPane5.getContentPane().add(ch_salario_familia);

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel39.setText("Nome");

        txt_nomeFilho.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel65.setText("CPF");

        txt_filhoLivro.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txt_filhoCidade.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel68.setText("Nº");

        txt_filhoN.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel69.setText("Nascimento");

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel70.setText("Cidade");

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel67.setText("Folha");

        txt_filhoFolha.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel66.setText("Livro nº");

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel71.setText("UF");

        btn_addFilho.setText("add");
        btn_addFilho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addFilhoActionPerformed(evt);
            }
        });

        btn_filhoDel.setText("Del");
        btn_filhoDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_filhoDelActionPerformed(evt);
            }
        });

        tb_filhos.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_filhos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_filhosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_filhos);

        txt_cpfFilho.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_nomeFilho, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel65)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_cpfFilho, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel66)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_filhoLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel67)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_filhoFolha, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel68)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_filhoN, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel69)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dtNascFilho, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel70)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_filhoCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel71)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_filhoUf, 0, 1, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_addFilho)
                        .addGap(1, 1, 1)
                        .addComponent(btn_filhoDel)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(txt_nomeFilho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel65)
                    .addComponent(txt_cpfFilho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_filhoLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel66)
                        .addComponent(txt_filhoFolha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel67)
                        .addComponent(txt_filhoN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel68))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel69)
                        .addComponent(txt_dtNascFilho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel71)
                            .addComponent(cb_filhoUf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel70)
                            .addComponent(txt_filhoCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_addFilho)
                            .addComponent(btn_filhoDel))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jXTaskPane5.getContentPane().add(jPanel6);

        jXTaskPane1.setExpanded(false);
        jXTaskPane1.setTitle("Dados Bancários");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel38.setText("Banco");

        txt_banco.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel40.setText("Cód. Banco");

        txt_codigoBanco.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel42.setText("Ag");

        txt_agencia.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel72.setText("Conta");

        txt_conta.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel73.setText("Cidade");

        txt_cidadeConta.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel74.setText("UF");

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel75.setText("Tipo de Conta");

        cb_tipoConta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Salário", "Poupança", "Corrente" }));

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel76.setText("Titular");

        txt_titular.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_banco, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_codigoBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_agencia, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel72)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_conta, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel73)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_cidadeConta, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel74)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_ufConta, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel76)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_titular, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel75)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_tipoConta, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76)
                    .addComponent(txt_titular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel75)
                    .addComponent(cb_tipoConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(txt_banco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(txt_codigoBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42)
                    .addComponent(txt_agencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel72)
                    .addComponent(txt_conta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel73)
                    .addComponent(txt_cidadeConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel74)
                    .addComponent(cb_ufConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jXTaskPane1.getContentPane().add(jPanel7);

        tp_clas.setTitle("Classificação");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel34.setText("Atividade");

        cb_atividade.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_atividadeItemStateChanged(evt);
            }
        });
        cb_atividade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cb_atividadeFocusGained(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel35.setText("C. de Resultado");

        cb_fazendaClas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cb_fazendaClasFocusGained(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel36.setText("Fazenda");

        jLabel85.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel85.setText("Conta");

        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel88.setText("Valor");

        txt_valorClass.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_valorClass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_valorClassFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_valorClassFocusLost(evt);
            }
        });

        btn_addClass.setText("Add");
        btn_addClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addClassActionPerformed(evt);
            }
        });

        btn_editarClass.setText("Editar");
        btn_editarClass.setEnabled(false);
        btn_editarClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarClassActionPerformed(evt);
            }
        });

        btn_delClass.setText("Del");
        btn_delClass.setEnabled(false);
        btn_delClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delClassActionPerformed(evt);
            }
        });

        cb_centroResultado.setAutocompletar(true);
        cb_centroResultado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cb_centroResultadoFocusGained(evt);
            }
        });

        scPClassifocacao.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        scPClassifocacao.setPreferredSize(new java.awt.Dimension(454, 70));
        scPClassifocacao.setRequestFocusEnabled(false);

        tb_class.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb_class.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tb_class.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_classMouseClicked(evt);
            }
        });
        scPClassifocacao.setViewportView(tb_class);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scPClassifocacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_atividade, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_centroResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel85)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_plano, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_fazendaClas, 0, 220, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel88)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_valorClass, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_addClass)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_editarClass)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_delClass)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel35)
                    .addComponent(cb_atividade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel85)
                    .addComponent(cb_fazendaClas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36)
                    .addComponent(cb_centroResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_plano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel88)
                    .addComponent(txt_valorClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scPClassifocacao, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_addClass)
                    .addComponent(btn_editarClass)
                    .addComponent(btn_delClass))
                .addContainerGap())
        );

        tp_clas.getContentPane().add(jPanel10);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jXTaskPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tp_filiacao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jXTaskPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jXTaskPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jXTaskPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tp_clas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXTaskPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tp_filiacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXTaskPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXTaskPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXTaskPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tp_clas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(219, 219, 219))
        );

        jScrollPane1.setViewportView(jPanel2);

        btn_novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/inserir_40x40.png"))); // NOI18N
        btn_novo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_novo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novoActionPerformed(evt);
            }
        });

        btn_salvarPedido.setBackground(new java.awt.Color(255, 255, 255));
        btn_salvarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/salvar_32_32.png"))); // NOI18N
        btn_salvarPedido.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_salvarPedido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_salvarPedido.setEnabled(false);
        btn_salvarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salvarPedidoActionPerformed(evt);
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

        btn_excluirPedido.setBackground(new java.awt.Color(255, 255, 255));
        btn_excluirPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/deletar_40x40.png"))); // NOI18N
        btn_excluirPedido.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_excluirPedido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_excluirPedido.setEnabled(false);
        btn_excluirPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirPedidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_salvarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_editarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_excluirPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_salvarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_editarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_excluirPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setBounds(0, 0, 1185, 958);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed
        FuncB = new CadColaboradorBeans();
        habilitarCampos();
        limparCampos();
        btn_salvarPedido.setEnabled(true);
        btn_editarPedido.setEnabled(false);
        cb_status.setSelectedIndex(1);
        cb_status.setEnabled(false);
    }//GEN-LAST:event_btn_novoActionPerformed

    private void btn_salvarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarPedidoActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(null, "Deseja Salvar este Pedido?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION) {
            popularBeans(FuncB);
            if (verificarBeans(FuncB) && ValidarPermissoes.validarPermissaoInsert(frmCadColaborador.class.getSimpleName())) {
                if (FuncD.salvarFuncionario(FuncB)) {
                    int imprimir = JOptionPane.showConfirmDialog(null, "Deseja Imprimir a Ficha Cadastral deste Colaborador?", "Atenção", JOptionPane.YES_NO_OPTION);
                    if (imprimir == JOptionPane.YES_OPTION) {
                        RelatoriosFuncionario.imprimirRelatorioFuncionario(FuncB.getId());
                    }
                    limparCampos();
                    TbClass.limpar();
                    TbFilhos.limpar();
                    desabilitarCampos();
                }
            }
        }
    }//GEN-LAST:event_btn_salvarPedidoActionPerformed

    private void btn_editarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarPedidoActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja editar esta entrada?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION) {
            popularBeans(FuncB);
            if (VerificarValorClas() && ValidarPermissoes.validarPermissaoUpdate(frmCadColaborador.class.getSimpleName())) {
                if (FuncD.atualizarFuncionario(FuncB)) {
                    int imprimir = JOptionPane.showConfirmDialog(null, "Deseja Imprimir a Ficha Cadastral deste Colaborador?", "Atenção", JOptionPane.YES_NO_OPTION);
                    if (imprimir == JOptionPane.YES_OPTION) {
                        RelatoriosFuncionario.imprimirRelatorioFuncionario(FuncB.getId());
                    }
                    limparCampos();
                    TbClass.limpar();
                    TbFilhos.limpar();
                    desabilitarCampos();
                }
            }
        }
    }//GEN-LAST:event_btn_editarPedidoActionPerformed

    private void btn_excluirPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirPedidoActionPerformed
        int excluir = JOptionPane.showConfirmDialog(null, "Deseja excluir este Colaborador?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (excluir == JOptionPane.YES_OPTION && ValidarPermissoes.validarPermissaoDelete(frmCadColaborador.class.getSimpleName())) {
            if (FuncD.removerFuncionario(FuncB)) {
                limparCampos();
                TbClass.limpar();
                TbFilhos.limpar();
                desabilitarCampos();
                btn_salvarPedido.setEnabled(false);
                btn_editarPedido.setEnabled(false);
                btn_excluirPedido.setEnabled(false);
                btn_novo.setEnabled(true);
            }
        }
    }//GEN-LAST:event_btn_excluirPedidoActionPerformed

    private void cb_funcaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_funcaoItemStateChanged
        txt_salario.setText(Corretores.ConverterDoubleReais(getSalarioFuncao(cb_funcao)));
    }//GEN-LAST:event_cb_funcaoItemStateChanged

    private void btn_addFilhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addFilhoActionPerformed
        inserirFilhos();
    }//GEN-LAST:event_btn_addFilhoActionPerformed

    private void tb_filhosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_filhosMouseClicked
        int index = tb_filhos.getSelectedRow();
        if (evt.getClickCount() == 2) {
            btn_filhoDel.setEnabled(true);
            FilhosB = FuncD.buscarFilhos((Integer) TbFilhos.getValueAt(index, TbFilhos.ID));
        } else {
            btn_filhoDel.setEnabled(false);
        }

    }//GEN-LAST:event_tb_filhosMouseClicked

    private void btn_filhoDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_filhoDelActionPerformed
        int index = tb_filhos.getSelectedRow();
        int excluir = JOptionPane.showConfirmDialog(null, "<Html> Está Ação irá <B>EXCLUIR Permanentemente </B>, <br> este Registro, deseja Prosseguir? </html>", "Atenção", JOptionPane.YES_NO_OPTION);
        if (excluir == JOptionPane.YES_OPTION) {
            if (index >= 0) {
                if (FuncD.removerFilhos(FilhosB)) {
                    TbFilhos.excluirLinha(index);
                } else {
                    TbFilhos.excluirLinha(index);
                }
            }
        }
    }//GEN-LAST:event_btn_filhoDelActionPerformed

    private void cb_atividadeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_atividadeItemStateChanged
        if (cb_atividade.getSelectedIndex() > 0) {
            carregarCentroResultado(getAtividade(cb_atividade).getID(), cb_centroResultado);
        }
    }//GEN-LAST:event_cb_atividadeItemStateChanged

    private void cb_atividadeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cb_atividadeFocusGained
        setExpandedComboBox(evt.getComponent());
    }//GEN-LAST:event_cb_atividadeFocusGained

    private void cb_fazendaClasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cb_fazendaClasFocusGained
        //setExpandedComboBox(evt.getComponent());
    }//GEN-LAST:event_cb_fazendaClasFocusGained

    private void txt_valorClassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorClassFocusGained
        txt_valorClass.selectAll();
    }//GEN-LAST:event_txt_valorClassFocusGained

    private void txt_valorClassFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_valorClassFocusLost
        //txt_valorClass.setText(Corretores.ConverterDecimalPorcentagem(txt_valorClass.getText()));
    }//GEN-LAST:event_txt_valorClassFocusLost

    private void btn_addClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addClassActionPerformed
        ClassB = new CadColaboradorClassBeans();
        addClassificacao(ClassB);
    }//GEN-LAST:event_btn_addClassActionPerformed

    private void btn_editarClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarClassActionPerformed
        int rowIndex = tb_class.getSelectedRow();
        if (VerificarValorEditadoClas()) {
            if (editarClassificacao(TbClass.getBeans(rowIndex), rowIndex)) {
                btn_addClass.setEnabled(true);
                btn_editarClass.setEnabled(false);
                btn_delClass.setEnabled(false);
                limparCamposClass();
            }
        }
    }//GEN-LAST:event_btn_editarClassActionPerformed

    private void btn_delClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delClassActionPerformed
        int index = tb_class.getSelectedRow();
        if (TbClass.getBeans(index).getId() != null) {
            int excluir = JOptionPane.showConfirmDialog(null, "<Html> Está Ação irá <B>EXCLUIR Permanentemente </B>, <br> este Registro, deseja Prosseguir? </html>", "Atenção", JOptionPane.YES_NO_OPTION);
            if (excluir == JOptionPane.YES_OPTION) {
                if (FuncD.DeletarClassicacao(TbClass.getBeans(index))) {
                    TbClass.excluirLinha(index);
                    limparCamposClass();
                    cb_atividade.requestFocus();
                    atualizarValorClas();
                }
            }
        } else {
            TbClass.excluirLinha(index);
            limparCamposClass();
            cb_atividade.requestFocus();
            atualizarValorClas();
        }
        limparConteiners(jPanel4);
        btn_addClass.setEnabled(true);
        btn_editarClass.setEnabled(false);
        btn_delClass.setEnabled(false);

    }//GEN-LAST:event_btn_delClassActionPerformed

    private void cb_centroResultadoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cb_centroResultadoFocusGained
        //setExpandedComboBox(evt.getComponent());
    }//GEN-LAST:event_cb_centroResultadoFocusGained

    private void tb_classMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_classMouseClicked
        int index = tb_class.getSelectedRow();
        if (evt.getClickCount() == 2) {
            preecherCamposClass(TbClass.getBeans(tb_class.getSelectedRow()));
            ClassB = FuncD.buscarClassificacao((Long) TbClass.getValueAt(index, TbClass.ID));
            cb_atividade.requestFocus();
            btn_addClass.setEnabled(false);
            btn_editarClass.setEnabled(true);
            btn_delClass.setEnabled(true);
        } else {
            limparCamposClass();
            btn_addClass.setEnabled(true);
            btn_editarClass.setEnabled(false);
            btn_delClass.setEnabled(false);
        }
    }//GEN-LAST:event_tb_classMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btn_addClass;
    private javax.swing.JButton btn_addFilho;
    javax.swing.JButton btn_delClass;
    javax.swing.JButton btn_editarClass;
    javax.swing.JButton btn_editarPedido;
    javax.swing.JButton btn_excluirPedido;
    private javax.swing.JButton btn_filhoDel;
    javax.swing.JButton btn_novo;
    javax.swing.JButton btn_salvarPedido;
    javax.swing.JComboBox<AtividadeBeans> cb_atividade;
    private javax.swing.JComboBox<String> cb_categoriaHab;
    private javax.swing.JComboBox<CEIBeans> cb_cei;
    componentes.UJComboBox cb_centroResultado;
    private javax.swing.JComboBox<String> cb_corPele;
    private javax.swing.JComboBox<ListDeficienciaBeans> cb_deficiencia;
    private javax.swing.JComboBox<String> cb_dsr;
    private javax.swing.JComboBox<String> cb_escolaridade;
    private javax.swing.JComboBox<String> cb_escolaridadeConj;
    private javax.swing.JComboBox<ListEstadoCivil> cb_estadoCivil;
    private javax.swing.JComboBox<PropriedadesBeans> cb_fazenda;
    public javax.swing.JComboBox<PropriedadesBeans> cb_fazendaClas;
    private javax.swing.JComboBox<EstadosBeans> cb_filhoUf;
    private javax.swing.JComboBox<Beans.CargosBeans> cb_funcao;
    private javax.swing.JComboBox<PlanoContaBeans> cb_plano;
    private javax.swing.JComboBox<ListSetorTrabalhoBeans> cb_setor;
    private javax.swing.JComboBox<String> cb_status;
    private javax.swing.JComboBox<String> cb_tipoConta;
    private javax.swing.JComboBox<String> cb_tipoContrato;
    private javax.swing.JComboBox<EstadosBeans> cb_ufBeneficiario;
    private javax.swing.JComboBox<EstadosBeans> cb_ufCTPS;
    private javax.swing.JComboBox<EstadosBeans> cb_ufCasamento;
    private javax.swing.JComboBox<EstadosBeans> cb_ufConta;
    private javax.swing.JComboBox<EstadosBeans> cb_ufFunc;
    private javax.swing.JComboBox<EstadosBeans> cb_ufNascimentoFunc;
    private javax.swing.JCheckBox ch_comodato;
    private javax.swing.JCheckBox ch_deficiente;
    private javax.swing.JCheckBox ch_filhos;
    private javax.swing.JCheckBox ch_salario_familia;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JPanel jPanel1;
    javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public org.jdesktop.swingx.JXTaskPane jXTaskPane1;
    public final org.jdesktop.swingx.JXTaskPane jXTaskPane2 = new org.jdesktop.swingx.JXTaskPane();
    public org.jdesktop.swingx.JXTaskPane jXTaskPane4;
    public org.jdesktop.swingx.JXTaskPane jXTaskPane5;
    javax.swing.JScrollPane scPClassifocacao;
    javax.swing.JTable tb_class;
    private javax.swing.JTable tb_filhos;
    public org.jdesktop.swingx.JXTaskPane tp_clas;
    public org.jdesktop.swingx.JXTaskPane tp_filiacao;
    private javax.swing.JTextField txt_TipoAlergia;
    private javax.swing.JTextField txt_TipoSangue;
    private javax.swing.JTextField txt_agencia;
    private javax.swing.JTextField txt_bairroBeneficiario;
    private javax.swing.JTextField txt_bairroFunc;
    private javax.swing.JTextField txt_banco;
    private javax.swing.JTextField txt_beneficiario;
    private javax.swing.JTextField txt_cartaoSUS;
    private javax.swing.JTextField txt_casamentoCidade;
    private javax.swing.JTextField txt_casamentoFolha;
    private javax.swing.JTextField txt_casamentoLivro;
    private javax.swing.JTextField txt_casamentoNumero;
    private javax.swing.JTextField txt_cidadeBeneficiario;
    private javax.swing.JTextField txt_cidadeConta;
    private javax.swing.JTextField txt_cidadeFunc;
    private javax.swing.JTextField txt_codigoBanco;
    private javax.swing.JTextField txt_codigoMaster;
    private javax.swing.JTextField txt_conta;
    private javax.swing.JFormattedTextField txt_cpfConj;
    private javax.swing.JFormattedTextField txt_cpfFilho;
    private javax.swing.JFormattedTextField txt_cpfFunc;
    private javax.swing.JTextField txt_ctpsFunc;
    private com.toedter.calendar.JDateChooser txt_dtAdmissao;
    private com.toedter.calendar.JDateChooser txt_dtCasamento;
    private com.toedter.calendar.JDateChooser txt_dtCertidaoNasc;
    private com.toedter.calendar.JDateChooser txt_dtCtps;
    private com.toedter.calendar.JDateChooser txt_dtDemissao;
    private com.toedter.calendar.JDateChooser txt_dtNascFilho;
    private com.toedter.calendar.JDateChooser txt_dtNascFunc;
    private com.toedter.calendar.JDateChooser txt_dtPis;
    private com.toedter.calendar.JDateChooser txt_dtRgFunc;
    private javax.swing.JTextField txt_endBeneficiario;
    private javax.swing.JTextField txt_endFunc;
    private javax.swing.JTextField txt_filhoCidade;
    private javax.swing.JTextField txt_filhoFolha;
    private javax.swing.JTextField txt_filhoLivro;
    private javax.swing.JTextField txt_filhoN;
    private javax.swing.JTextField txt_hEnt1;
    private javax.swing.JTextField txt_hEnt2;
    private javax.swing.JTextField txt_hSaida1;
    private javax.swing.JTextField txt_hSaida2;
    private javax.swing.JTextField txt_habilitacao;
    private javax.swing.JTextField txt_mae;
    private javax.swing.JTextField txt_nCalcado;
    private javax.swing.JTextField txt_nascimentoCidadeFunc;
    private javax.swing.JTextField txt_nascimentoFolha;
    private javax.swing.JTextField txt_nascimentoLivro;
    private javax.swing.JTextField txt_nascimentoNumero;
    private javax.swing.JTextField txt_nomeConjuge;
    private javax.swing.JTextField txt_nomeFilho;
    private javax.swing.JTextField txt_nomeFunc;
    private javax.swing.JTextField txt_orgConj;
    private javax.swing.JTextField txt_orgExpFunc;
    private javax.swing.JTextField txt_pai;
    private javax.swing.JTextField txt_pis;
    private javax.swing.JTextField txt_profissaoConj;
    private javax.swing.JTextField txt_reservista;
    private javax.swing.JTextField txt_rgConj;
    private javax.swing.JTextField txt_rgFunc;
    private javax.swing.JTextField txt_salario;
    private javax.swing.JTextField txt_secao;
    private javax.swing.JTextField txt_serieCtps;
    private javax.swing.JTextField txt_sexo;
    private javax.swing.JFormattedTextField txt_telBenef;
    private javax.swing.JFormattedTextField txt_telFunc;
    private javax.swing.JTextField txt_titular;
    private javax.swing.JTextField txt_titulo;
    public javax.swing.JTextField txt_valorClass;
    private javax.swing.JTextField txt_zona;
    // End of variables declaration//GEN-END:variables

    public void popularBeans(CadColaboradorBeans b) {
        try {
            b.setIdCei(getCEI(cb_cei));
            b.setCodigoMaster(getCodigoMaster(txt_codigoMaster));
            b.setTipoContrato(cb_tipoContrato.getSelectedItem().toString());
            b.setIdFazenda(getFazenda(cb_fazenda));
            b.setIdSetor(getSetor(cb_setor));
            b.setIdFuncao(getFuncao(cb_funcao));
            b.setSalario(getSalarioFuncao(cb_funcao));
            b.setDtAdmissao(Corretores.ConverterStringDateDDMMAAAA(getData(txt_dtAdmissao.getDate(), "Data Admissão")));
            b.setDSR(cb_dsr.getSelectedItem().toString());
            b.setHent1(getTime(txt_hEnt1.getText()));
            b.setHsaida1(getTime(txt_hSaida1.getText()));
            b.setHent2(getTime(txt_hEnt2.getText()));
            b.setHsaida2(getTime(txt_hSaida2.getText()));
            b.setComodato(ch_comodato.isSelected());
            b.setAtivo(cb_status.getSelectedItem().toString());
            b.setDtDemissao(Corretores.ConverterStringDateDDMMAAAA(getData(txt_dtDemissao.getDate(), "Data Demissão")));

            // Dados Colaborador;
            b.setNomeFunc(txt_nomeFunc.getText());
            b.setCPFFunc(txt_cpfFunc.getText());
            b.setRGFunc(txt_rgFunc.getText());
            b.setOrdaoExpFunc(txt_orgExpFunc.getText());
            b.setDtEmissaoRgFunc(Corretores.ConverterStringDateDDMMAAAA(getData(txt_dtRgFunc.getDate(), "Data Emissão RG do Colaborador")));
            b.setDtNascimentoFunc(Corretores.ConverterStringDateDDMMAAAA(getData(txt_dtNascFunc.getDate(), "Data de Nascimento do Colaborador")));
            b.setCTPS(txt_ctpsFunc.getText());
            b.setSerieCTPS(txt_serieCtps.getText());
            b.setDtEmissaoCTPS(Corretores.ConverterStringDateDDMMAAAA(getData(txt_dtCtps.getDate(), "Data de Emissão da Carteira de Trabalho")));
            b.setPIS(txt_pis.getText());
            b.setDtEmissaoPIS(Corretores.ConverterStringDateDDMMAAAA(getData(txt_dtCtps.getDate(), "Data de Emissão do PIS")));
            b.setTituloEleitoral(txt_titulo.getText());
            b.setZonaEleitoral(txt_zona.getText());
            b.setSecaoEleitoral(txt_secao.getText());
            b.setHabilitacao(txt_habilitacao.getText());
            b.setCategoria(cb_categoriaHab.getSelectedItem().toString());
            b.setReservista(txt_reservista.getText());
            b.setDeficiente(ch_deficiente.isSelected());
            b.setTipoDeficiencia(cb_deficiencia.getSelectedItem().toString());
            b.setCartaoSUS(txt_cartaoSUS.getText());
            b.setCorDaPele(cb_corPele.getSelectedItem().toString());
            b.setEscolaridade(cb_escolaridade.getSelectedItem().toString());
            b.setBeneficiario(txt_beneficiario.getText());
            b.setEnderecoFuncionario(txt_endFunc.getText());
            b.setCidadeFuncionario(txt_cidadeFunc.getText());
            b.setUFFuncionario(getEstado(cb_ufFunc));//
            b.setTelefoneFunc(txt_telFunc.getText());
            b.setEnderecoBeneficario(txt_endBeneficiario.getText());
            b.setCidadeBeneficiario(txt_cidadeBeneficiario.getText());
            b.setUFBeneficiario(getEstado(cb_ufBeneficiario));
            b.setTelefoneBenef(txt_telBenef.getText());
            b.setUFCtps(getEstado(cb_ufCTPS));
            b.setSexo(txt_sexo.getText());
            b.setnCalcado(txt_nCalcado.getText());
            b.setBairroBeneficiario(txt_bairroBeneficiario.getText());
            b.setBairroFuncionario(txt_bairroFunc.getText());
            b.setTipoSangue(txt_TipoSangue.getText());
            b.setTipoAlergia(txt_TipoAlergia.getText());

            // Dados Filiação
            b.setNomePai(txt_pai.getText());
            b.setNomeMae(txt_mae.getText());
            b.setCert_nasc_livro(txt_nascimentoLivro.getText());
            b.setCert_nasc_folha(txt_nascimentoFolha.getText());
            b.setCert_nasc_num(txt_nascimentoNumero.getText());
            b.setCert_nasc_data(Corretores.ConverterStringDateDDMMAAAA(getData(txt_dtCertidaoNasc.getDate(), "")));
            b.setCert_nasc_cidade(txt_nascimentoCidadeFunc.getText());
            b.setCert_nasc_uf(getEstado(cb_ufNascimentoFunc));

            // Dados Estado Civil
            b.setEstadoCivil(cb_estadoCivil.getSelectedItem().toString());
            b.setNomeConjuge(txt_nomeConjuge.getText());
            b.setCPFConjuge(txt_cpfConj.getText());
            b.setRGConjuge(txt_rgConj.getText());
            b.setOrgExpedidoConjuge(txt_orgConj.getText());
            b.setEscolaridadeConjuge(cb_escolaridadeConj.getSelectedItem().toString());
            b.setProfissaoConjuge(txt_profissaoConj.getText());
            b.setLivroCasamento(txt_casamentoLivro.getText());
            b.setFolhaCasamento(txt_casamentoFolha.getText());
            b.setNumeroCasamento(txt_casamentoNumero.getText());
            b.setDataCasamento(Corretores.ConverterStringDateDDMMAAAA(getData(txt_dtCasamento.getDate(), "Data de Casamento")));
            b.setCidadeCasamento(txt_casamentoCidade.getText());
            b.setUfCasamento(getEstado(cb_ufCasamento));

            b.setTemFilhos(ch_filhos.isSelected());
            b.setSalarioFamilia(ch_salario_familia.isSelected());

            // dados Bancários
            b.setTitular(txt_titular.getText());
            b.setTipoConta(cb_tipoConta.getSelectedItem().toString());
            b.setBanco(txt_banco.getText());
            b.setCodigoBanco(getCodigoBanco(txt_codigoBanco));
            b.setAgencia(txt_agencia.getText());
            b.setContaBancaria(txt_conta.getText());
            b.setCidadeAgencia(txt_cidadeConta.getText());
            b.setEstadoAgencia(getEstado(cb_ufConta));

            //Listas
            b.setListaClassificacao(TbClass.getLista());
            b.setListFilhos(TbFilhos.getLista());//Cadastro de Filhos Na Tabela

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e + "Erro ao Popular Colaboradores", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }

    public void preencherFormulario(CadColaboradorBeans b) {
        try {
            setCEI(cb_cei, b.getId_cei());// ID_cei
            txt_codigoMaster.setText(b.getCodigoMaster().toString());// codigo master
            cb_tipoContrato.setSelectedItem(b.getTipoContrato());// tipo contrato
            setFazenda(cb_fazenda, b.getId_fazenda());// id fazenda
            setSetor(cb_setor, b.getId_setor());
            setFuncao(cb_funcao, b.getId_funcao());// id função
            txt_salario.setText(Corretores.ConverterDoubleReais(b.getSalario()));// salario
            txt_dtAdmissao.setDate((b.getDtAdmissao()));// dt admissao
            cb_dsr.setSelectedItem(b.getDSR());// dia descanco
            txt_hEnt1.setText(b.getHent1().toString());// h entrada1
            txt_hSaida1.setText(b.getHsaida1().toString());//h saida 1
            txt_hEnt2.setText(b.getHent2().toString());// h entrada1
            txt_hSaida2.setText(b.getHsaida2().toString());// h saida2
            ch_comodato.setSelected(b.getComodato());//comodato
            cb_status.setSelectedItem(setComboBoxString(b.getAtivo()));//Status
            txt_dtDemissao.setDate((b.getDtDemissao()));//dt_demissao
            txt_nomeFunc.setText(b.getNomeFunc());//nome
            txt_cpfFunc.setText(b.getCPFFunc());//cpf_func
            txt_rgFunc.setText(b.getRGFunc());//rg_func
            txt_orgExpFunc.setText(b.getOrdaoExpFunc());//org_exp_func
            txt_dtRgFunc.setDate((b.getDtEmissaoRgFunc()));//dt_emissao_rg_func
            txt_dtNascFunc.setDate((b.getDtNascimentoFunc()));//dt_nasc_func
            txt_ctpsFunc.setText(b.getCTPS());//ctps
            txt_serieCtps.setText(b.getSerieCTPS());//serie_ctps
            txt_dtCtps.setDate((b.getDtEmissaoCTPS()));//dt_emissao_ctps
            txt_pis.setText(b.getPIS());//pis
            txt_dtPis.setDate((b.getDtEmissaoPIS()));//dt_emissao_pis
            txt_titulo.setText(b.getTituloEleitoral());//titulo_eleitoral
            txt_zona.setText(b.getZonaEleitoral());//zona_eleitoral
            txt_secao.setText(b.getSecaoEleitoral());//sessao_eleitoral
            txt_habilitacao.setText(b.getHabilitacao());//habilitacao
            cb_categoriaHab.setSelectedItem(setComboBoxString(b.getCategoria()));//categoria_habilitacao
            txt_reservista.setText(b.getReservista());//reservista
            ch_deficiente.setSelected(b.getDeficiente());//deficiente

            cb_deficiencia.setSelectedItem(setComboBoxString(b.getTipoDeficiencia()));
            txt_cartaoSUS.setText(b.getCartaoSUS());//cartao_sus
            cb_corPele.setSelectedItem(setComboBoxString(b.getCorDaPele()));//cor_pele
            cb_escolaridade.setSelectedItem(setComboBoxString(b.getEscolaridade()));//escolaridade_func

            txt_beneficiario.setText(b.getBeneficiario());//beneficiario
            txt_endBeneficiario.setText(b.getEnderecoBeneficario());//end_beneficiario

            txt_cidadeBeneficiario.setText(b.getCidadeBeneficiario());//cidade_beneficiario
            txt_telBenef.setText(b.getTelefoneBenef());// Telefone Beneficiário
            txt_endFunc.setText(b.getEnderecoFuncionario());//end_func

            txt_cidadeFunc.setText(b.getCidadeFuncionario());//cidade_func
            txt_telFunc.setText(b.getTelefoneFunc());// Telefone Funcionário
            txt_sexo.setText(b.getSexo());
            txt_nCalcado.setText(b.getnCalcado());
            txt_bairroBeneficiario.setText(b.getBairroBeneficiario());
            txt_bairroFunc.setText(b.getBairroFuncionario());
            txt_TipoAlergia.setText(b.getTipoAlergia());
            txt_TipoSangue.setText(b.getTipoSangue());

            txt_pai.setText(b.getNomePai());//nome_pai
            txt_mae.setText(b.getNomeMae());//nome_mae
            txt_nascimentoLivro.setText(b.getCert_nasc_livro());
            txt_nascimentoFolha.setText(b.getCert_nasc_folha());
            txt_nascimentoNumero.setText(b.getCert_nasc_num());
            txt_dtCertidaoNasc.setDate((b.getCert_nasc_data()));
            txt_nascimentoCidadeFunc.setText(b.getCert_nasc_cidade());

            txt_nomeConjuge.setText(b.getNomeConjuge());//nome_conj
            txt_cpfConj.setText(b.getCPFConjuge());//cpf_conj
            txt_rgConj.setText(b.getRGConjuge());//rg_conj
            txt_orgConj.setText(b.getOrgExpedidoConjuge());//org_exp_rg_conj
            cb_escolaridadeConj.setSelectedItem(setComboBoxString(b.getEscolaridadeConjuge()));                        //escolaridade_conj
            txt_profissaoConj.setText(b.getProfissaoConjuge());//profissao_conj
            txt_casamentoLivro.setText(b.getLivroCasamento());//livro_casamento
            txt_casamentoFolha.setText(b.getFolhaCasamento());//folha_casamento
            txt_casamentoNumero.setText(b.getNumeroCasamento());//numero_casamento
            txt_dtCasamento.setDate((b.getDataCasamento()));//dt_casamento
            txt_casamentoCidade.setText(b.getCidadeCasamento());//cidade_casamento

            ch_filhos.setSelected(b.getTemFilhos());//filhos
            ch_salario_familia.setSelected(b.getSalarioFamilia());//Salario Familia

            txt_titular.setText(b.getTitular());//titular_conta
            cb_tipoConta.setSelectedItem(setComboBoxString(b.getTipoConta()));//tipo_conta
            txt_banco.setText(b.getBanco());//banco
            txt_codigoBanco.setText(setComboBoxString(b.getCodigoBanco()));//codigo_banco
            txt_agencia.setText(b.getAgencia());//agencia
            txt_conta.setText(b.getContaBancaria());//conta
            txt_cidadeConta.setText(b.getCidadeAgencia());//cidade_agencia

            setDeficiencia(cb_deficiencia, b.getTipoDeficiencia());//deficiencia
            setEstado(cb_ufBeneficiario, b.getId_uf_beneficario());//uf_beneficiario
            setEstado(cb_ufFunc, b.getId_uf_funcionario());//uf_func
            setEstado(cb_ufCTPS, b.getId_uf_ctps());//uf_func
            setEstado(cb_ufNascimentoFunc, b.getId_uf_cert_nasc());//cb_ufNascimentoFunc
            setEstadoCivil(cb_estadoCivil, b.getEstadoCivil());// estado civil
            setEstado(cb_ufCasamento, b.getId_uf_casamento());//uf_casamento
            setEstado(cb_ufConta, b.getId_uf_agencia());//uf_agencia

            TbFilhos.addLista(b.getListFilhos());//Tabela de Lista de Filhos
            TbClass.addLista(b.getListaClassificacao());//Tabela de lista de Classificação(%)
            atualizarValorClas();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e + "Erro ao Preencher Campos de Colaboradores", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }

    private boolean verificarBeans(CadColaboradorBeans b) {
        if (b.getIdCei().getIdCEI() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o campo CEI!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getTipoContrato().equals("-")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Tipo de Contrato!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getCodigoMaster() == null) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Código Master", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getIdFazenda().getCodigo() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Fazenda", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getIdFuncao().getId() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Função", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        /* if (b.getSalario() == 0.0) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Salário", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } */
        if (b.getDtAdmissao() == null) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Data de Admissão", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getDSR().equals("-")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo DSR", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getDtAdmissao() == null) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Data de Admissão", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getHent1().equals(0) || b.getHsaida1().equals(0) || b.getHent2().equals(0) || b.getHsaida2().equals(0)) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Horário de Trabalho", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getNomeFunc().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Nome do Funcionário", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getCPFFunc().equals("   .   .   -  ")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo CPF do funcionário", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getRGFunc().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo RG do funcionário", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getOrdaoExpFunc().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Orgão Expedidor", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getDtEmissaoRgFunc() == null) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Data de Emissão do RG!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getDtNascimentoFunc() == null) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Data de Nascimento", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getCTPS().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Carteira de Trabalho", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getSerieCTPS().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo série da carteira de trabalho", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getDtEmissaoCTPS() == null) {
            JOptionPane.showMessageDialog(null, "Verifique o campo data de emissão da CTPS", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }

        // para o caso de não ter nº de PIS
        if (b.getPIS().equals("")) {
            if (b.getTituloEleitoral().equals("")) {
                JOptionPane.showMessageDialog(null, "Verifique o campo Titulo Eleitoral", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
            if (b.getZonaEleitoral().equals("")) {
                JOptionPane.showMessageDialog(null, "Verifique o campo zona eleitoral", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
            if (b.getSerieCTPS().equals("")) {
                JOptionPane.showMessageDialog(null, "Verifique o campo sessão eleitoral", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
            // Dados para quando o Funcionário for casado
            if (b.getEstadoCivil().equals("Casado")) {
                if (b.getNomeConjuge().equals("")) {
                    JOptionPane.showMessageDialog(null, "Verifique o campo nome do Conjuge!", "Erro", 0, FormatarICO.ICObtnSair());
                    return false;
                }
                if (b.getCPFConjuge().equals("")) {
                    JOptionPane.showMessageDialog(null, "Verifique o CPF do conjuge!", "Erro", 0, FormatarICO.ICObtnSair());
                    return false;
                }
                if (b.getRGConjuge().equals("")) {
                    JOptionPane.showMessageDialog(null, "Verifique o campo RG do Conjuge", "Erro", 0, FormatarICO.ICObtnSair());
                    return false;
                }
                if (b.getOrgExpedidoConjuge().equals("")) {
                    JOptionPane.showMessageDialog(null, "Verifique o campo orgão expediro do RG do Conjuge!", "Erro", 0, FormatarICO.ICObtnSair());
                    return false;
                }

                if (b.getEscolaridadeConjuge().equals("")) {
                    JOptionPane.showMessageDialog(null, "Verifique o campo escolaridade do conjuge", "Erro", 0, FormatarICO.ICObtnSair());
                    return false;
                }
                if (b.getLivroCasamento().equals("")) {
                    JOptionPane.showMessageDialog(null, "Verifique o campo Livro da Certidão de Casamento!", "Erro", 0, FormatarICO.ICObtnSair());
                    return false;
                }
                if (b.getFolhaCasamento().equals("")) {
                    JOptionPane.showMessageDialog(null, "Verifique campo Folha da Certidão de Casamento!", "Erro", 0, FormatarICO.ICObtnSair());
                    return false;
                }
                if (b.getNumeroCasamento().equals("")) {
                    JOptionPane.showMessageDialog(null, "Verifique campo Nº da Certidão de Casamento!", "Erro", 0, FormatarICO.ICObtnSair());
                    return false;
                }
                if (b.getDataCasamento().equals("")) {
                    JOptionPane.showMessageDialog(null, "Verifique campo Data da Certidão de Casamento!", "Erro", 0, FormatarICO.ICObtnSair());
                    return false;
                }
                if (b.getCidadeCasamento().equals("")) {
                    JOptionPane.showMessageDialog(null, "Verifique campo Cidade de emissão da Certidão de Casamento!", "Erro", 0, FormatarICO.ICObtnSair());
                    return false;
                }
                if (b.getUfCasamento().equals("")) {
                    JOptionPane.showMessageDialog(null, "Verifique campo Estado de emissão da Certidão de Casamento!", "Erro", 0, FormatarICO.ICObtnSair());
                    return false;
                }
            }
        }

        if (b.getDtEmissaoPIS() == null) {
            JOptionPane.showMessageDialog(null, "Verifique o campo data de emissão do PIS", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getSerieCTPS().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo série da carteira de trabalho", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getDeficiente() == true && b.getTipoDeficiencia().equals("-")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Deficiência", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getCartaoSUS().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo cartão SUS!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getCorDaPele().equals("-")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo cor da pele", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getEscolaridade().equals("-")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo escolaridade", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getBeneficiario().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo beneficiário", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getEscolaridade().equals("-")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo escolaridade", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getEnderecoBeneficario().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo beneficiário!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getUFBeneficiario().equals("-")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo beneficiário!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getCidadeBeneficiario().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo cidade beneficiário!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getTelefoneBenef().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo telefone do beneficiário!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getEnderecoFuncionario().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo endereço do funcionário!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getCidadeFuncionario().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo cidade do funcionário!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getUFFuncionario().equals("-")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo estado do beneficiário!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getNomePai().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo nome do pai!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (b.getNomeMae().equals("-")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo nome da mãe!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }

        if (b.getTemFilhos() == true && b.getSalarioFamilia() == true && TbFilhos.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Para o Recebimento do Salário Família, os dados da Certidão de Nascimneto são Obrigatórios!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }

        return true;
    }

    public void inserirFilhos() {
        FilhosFuncionariosBeans F = new FilhosFuncionariosBeans();
        F.setId_funcionario(FuncB);
        F.setNome(txt_nomeFilho.getText());
        F.setCPF(txt_cpfFilho.getText());
        F.setLivro(txt_filhoLivro.getText());
        F.setFolha(txt_filhoFolha.getText());
        F.setNumero(txt_filhoN.getText());
        F.setDataNascimento(txt_dtNascFilho.getDate());
        F.setCidade(txt_filhoCidade.getText());
        F.setEstado(getEstado(cb_filhoUf));
        if (verificarDadosFilhos(F)) {
            TbFilhos.addBeans(F);
            txt_nomeFilho.grabFocus();
            txt_nomeFilho.selectAll();
            limparConteiners(jPanel6);
        }
    }

    private Boolean verificarDadosFilhos(FilhosFuncionariosBeans F) {
        if (F.getNome().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Nome do Filho", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (F.getCPF().equals("   .   .   -  ")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo CPF do Filho", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (F.getLivro().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Livro da Certidão de Nascimento", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (F.getFolha().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Folha da Certidão de Nascimento", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (F.getNumero().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Número da Certidão de Nascimento", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (F.getDataNascimento() == null) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Data de Nascimento", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (F.getCidade().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Cidade de Nascimento", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (F.getEstado().getIdEtado() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Estado de Nascimento", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }

    private Boolean addClassificacao(CadColaboradorClassBeans clas) {
        try {
            clas.setIdColaborador(FuncB);
            clas.setIdCentro(getCentroResultado(cb_centroResultado));
            clas.setIdPlanoConta(getPlanoConta(cb_plano));
            clas.setIdFazenda(getFazenda(cb_fazendaClas));
            clas.setValor(Corretores.ConverterStringDouble(txt_valorClass.getText()));
            if (verificaBeans(clas) && VerificarValorClas()) {
                TbClass.addBeans(clas);
                cb_atividade.requestFocus();
                limparCamposClass();
                atualizarValorClas();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Adicionar Classificação!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }

    private Boolean editarClassificacao(CadColaboradorClassBeans clas, int rowIndex) {
        try {
            clas.setIdColaborador(FuncB);
            clas.setIdCentro(getCentroResultado(cb_centroResultado));
            clas.setIdFazenda(getFazenda(cb_fazendaClas));
            clas.setIdPlanoConta(getPlanoConta(cb_plano));
            clas.setValor(Corretores.ConverterStringDouble(txt_valorClass.getText()));
            if (verificaBeans(clas)) {
                TbClass.setBeans(clas, rowIndex);
                limparCamposClass();
                cb_atividade.requestFocus();
                atualizarValorClas();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Editar Classificação!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }
    
    private Boolean VerificarValorClas() {
        Double columnIndex = TbClass.somarValorTabela(TbClass.VALOR);
        Double JTextField = Corretores.ConverterStringDouble(txt_valorClass.getText());
        Double I = (columnIndex + JTextField);
        if (I > 100) {
            JOptionPane.showMessageDialog(null, "<Html> A classificação de <B><font color=RED> " + I + "% </font></B>, Excedeu os 100%. </html>", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } else {
            return true;
        }
    }

    private Boolean VerificarValorEditadoClas() {
        int index = tb_class.getSelectedRow();
        Double ValorTotal = TbClass.somarValorTabela(TbClass.VALOR);
        Double ValorSelecionado = (Double) TbClass.getValueAt(index, TbClass.VALOR);
        Double JTextField = Corretores.ConverterStringDouble(txt_valorClass.getText());
        Double I = (ValorTotal - ValorSelecionado + JTextField);
        if (I > 100) {
            JOptionPane.showMessageDialog(null, "<Html> A classificação de <B><font color=RED> " + I + "% </font></B>, Excedeu os 100%. </html>", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }

    private void preecherCamposClass(CadColaboradorClassBeans clas) {
        setAtividade(cb_atividade, clas.getIdCentro().getAtividade().getID());
        setCentroResultado(cb_centroResultado, clas.getIdCentro().getID());
        setPlanoConta(cb_plano, clas.getIdPlanoConta().getID());
        setFazenda(cb_fazendaClas, clas.getIdFazenda().getCodigo());
        txt_valorClass.setText(Corretores.ConverterDoublePorcentagem(clas.getValor()));
    }

    private void atualizarValorClas() {
        tp_clas.setTitle("Classificação - " + Corretores.ConverterDoublePorcentagem(TbClass.somarValorTabela(TbClass.VALOR) / 100));
    }
    
    private boolean verificaBeans(CadColaboradorClassBeans clas) {

        if (clas.getIdCentro().getID() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Centro de Resultado!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (clas.getIdPlanoConta().getID() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Plano de Conta!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (clas.getIdFazenda().getCodigo() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Fazenda!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        if (clas.getValor() == 0 || clas.getValor() > 100) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Valor!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }

        return true;
    }

    public void habilitarCampos() {
        setEnabledContainers(jPanel1, true);
        setEnabledContainers(jPanel2, true);
        setEnabledContainers(jPanel3, true);
        setEnabledContainers(jPanel4, true);
        setEnabledContainers(jPanel5, true);
        setEnabledContainers(jPanel6, true);
        setEnabledContainers(jPanel7, true);
        setEnabledContainers(jPanel8, true);
        setEnabledContainers(jPanel9, true);
        setEnabledContainers(jPanel10, true);
        setEnabledContainers(jXTaskPane5, true);
        btn_novo.setEnabled(true);
        btn_editarPedido.setEnabled(false);
        btn_excluirPedido.setEnabled(false);

    }

    public void desabilitarCampos() {
        setEnabledContainers(jPanel1, false);
        setEnabledContainers(jPanel2, false);
        setEnabledContainers(jPanel3, false);
        setEnabledContainers(jPanel4, false);
        setEnabledContainers(jPanel5, false);
        setEnabledContainers(jPanel6, false);
        setEnabledContainers(jPanel7, false);
        setEnabledContainers(jPanel8, false);
        setEnabledContainers(jPanel9, false);
        setEnabledContainers(jPanel10, false);
        setEnabledContainers(jXTaskPane5, false);
        btn_novo.setEnabled(true);
        btn_editarPedido.setEnabled(false);
        btn_excluirPedido.setEnabled(false);
    }

    public void limparCampos() {
        limparConteiners(jPanel1);
        limparConteiners(jPanel2);
        limparConteiners(jPanel3);
        limparConteiners(jPanel4);
        limparConteiners(jPanel5);
        limparConteiners(jPanel6);
        limparConteiners(jPanel7);
        limparConteiners(jPanel8);
        limparConteiners(jPanel9);
        limparConteiners(jPanel10);
        limparConteiners(jXTaskPane5);
        limparConteiners(tp_clas);
        ch_filhos.setSelected(false);
        ch_salario_familia.setSelected(false);
        TbFilhos.limpar();
        TbClass.limpar();
    }

    private void limparCamposClass() {
        cb_atividade.setSelectedIndex(0);
        cb_centroResultado.setSelectedIndex(0);
        cb_plano.setSelectedIndex(0);
        cb_fazendaClas.setSelectedIndex(0);
        txt_valorClass.setText("0%");
    }

    public void limparConteiners(Container conteiner) {
        for (Component c : conteiner.getComponents()) {
            if (c instanceof JTextField) {
                JTextField field = (JTextField) c;
                field.setText("");
            } else if (c instanceof JComboBox) {
                JComboBox f = (JComboBox) c;
                f.setSelectedIndex(0);
            } else if (c instanceof JCheckBox) {
                JCheckBox f = (JCheckBox) c;
                f.setSelected(false);
            } else if (c instanceof JDateChooser) {
                JDateChooser f = (JDateChooser) c;
                f.setDate(null);
            } else if (c instanceof JFormattedTextField) {
                JFormattedTextField f = (JFormattedTextField) c;
                f.setText("");
            }
        }
    }

    public void setEnabledContainers(Container conteiner, Boolean b) {
        for (Component c : conteiner.getComponents()) {
            c.setEnabled(b);
        }
    }

    private String getData(Date dt, String Campo) {
        String s = null;
        try {
            s = new SimpleDateFormat("dd/MM/yyyy").format(dt);
        } catch (Exception e) {
            // JOptionPane.showMessageDialog(null, "Verifique o campo " + Campo + "!", "Erro", 0, FormatarICO.ICObtnSair());
        }
        return s;
    }

    private String setComboBoxString(String str) {
        if (str == null) {
            return str = "-";
        }
        return str;
    }

    private String setComboBoxString(Integer str) {
        if (str == null) {
            return "0";
        }
        return String.valueOf(str);
    }

    private Time getTime(String time) {
        Time t = new Time(0);
        try {
            Long h1 = new SimpleDateFormat("HH:mm").parse(time).getTime();
            t.setTime(h1);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Verifique o horário de trabalho!", "Erro", 0, FormatarICO.ICObtnSair());
        }
        return t;
    }

    private Integer getCodigoMaster(JTextField txt) {
        Integer id = 0;
        try {
            id = Integer.parseInt(txt.getText());
        } catch (Exception e) {
        }
        return id;
    }

    private Integer getCodigoBanco(JTextField txt) {
        Integer id = 0;
        try {
            id = Integer.parseInt(txt.getText());
        } catch (Exception e) {
        }
        return id;
    }

    private void setEstadoCivil(JComboBox<ListEstadoCivil> cb, String estado) {
        if (estado != null) {
            for (int i = 0; i < cb.getModel().getSize(); i++) {
                if (cb.getItemAt(i).getDescricao().equals(estado)) {
                    cb.setSelectedIndex(i);
                }
            }
        } else {
            cb.setSelectedIndex(0);
        }
    }

    private Double getSalarioFuncao(JComboBox<CargosBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return comboBox.getItemAt(comboBox.getSelectedIndex()).getSalario();
        }
        return 0.00;
    }

    private CEIBeans getCEI(JComboBox<CEIBeans> combo) {
        return (CEIBeans) combo.getSelectedItem();
    }

    private void setCEI(JComboBox<CEIBeans> cb, Integer IdCEI) {
        for (int i = 0; i < cb.getModel().getSize(); i++) {
            if (Objects.equals(cb.getItemAt(i).getIdCEI(), IdCEI)) {
                cb.setSelectedIndex(i);
                break;
            }
        }
    }

    private PropriedadesBeans getFazenda(JComboBox<PropriedadesBeans> comboBox) {
        return (PropriedadesBeans) comboBox.getSelectedItem();
    }

    private void setFazenda(JComboBox<PropriedadesBeans> cb, Integer IdFazenda) {
        for (int i = 0; i < cb.getModel().getSize(); i++) {
            if (cb.getItemAt(i).getCodigo() == IdFazenda) {
                cb.setSelectedIndex(i);
            }
        }
    }

    private ListSetorTrabalhoBeans getSetor(JComboBox<ListSetorTrabalhoBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return (ListSetorTrabalhoBeans) comboBox.getSelectedItem();
        }
        return null;
    }

    private void setSetor(JComboBox<ListSetorTrabalhoBeans> cb, Integer IdSetor) {
        for (int i = 0; i < cb.getModel().getSize(); i++) {
            if (cb.getItemAt(i).getID() == IdSetor) {
                cb.setSelectedIndex(i);
            }
        }
    }

    private CargosBeans getFuncao(JComboBox<CargosBeans> comboBox) {
        if (comboBox.getSelectedIndex() != 0) {
            return (CargosBeans) comboBox.getSelectedItem();
        }
        return null;
    }

    private void setFuncao(JComboBox<CargosBeans> cb, Integer IdFuncao) {
        for (int i = 0; i < cb.getModel().getSize(); i++) {
            if (cb.getItemAt(i).getId() == IdFuncao) {
                cb.setSelectedIndex(i);
            }
        }
    }

    private EstadosBeans getEstado(JComboBox<EstadosBeans> combo) {
        return (EstadosBeans) combo.getSelectedItem();
    }

    private void setEstado(JComboBox<EstadosBeans> combo, Integer ID) {
        if (ID != null) {
            for (int i = 0; i < combo.getModel().getSize(); i++) {
                if (Objects.equals(combo.getItemAt(i).getIdEtado(), ID)) {
                    combo.setSelectedIndex(i);
                    break;
                }
            }
        } else {
            combo.setSelectedIndex(0);
        }
    }

    private CidadesBeans getCidade(JComboBox<CidadesBeans> combo) {
        return (CidadesBeans) combo.getSelectedItem();
    }

    private void setCidade(JComboBox<CidadesBeans> combo, Integer ID) {
        for (int i = 0; i < combo.getModel().getSize(); i++) {
            if (Objects.equals(combo.getItemAt(i).getId(), ID)) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }

    private ListDeficienciaBeans getDeficiencia(JComboBox<ListDeficienciaBeans> combo) {
        return (ListDeficienciaBeans) combo.getSelectedItem();
    }

    private void setDeficiencia(JComboBox<ListDeficienciaBeans> combo, String nome) {
        if (nome != null) {
            for (int i = 0; i < combo.getModel().getSize(); i++) {
                if (Objects.equals(combo.getItemAt(i).getDeficiencia(), nome)) {
                    combo.setSelectedIndex(i);
                    break;
                }
            }
        } else {
            combo.setSelectedIndex(0);
        }
    }

    private AtividadeBeans getAtividade(JComboBox<AtividadeBeans> combo) {
        return (AtividadeBeans) combo.getSelectedItem();
    }

    private void setAtividade(JComboBox<AtividadeBeans> combo, Integer id) {
        combo.setSelectedIndex(0);
        for (int i = 0; i < combo.getModel().getSize(); i++) {
            if (Objects.equals(combo.getItemAt(i).getID(), id)) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }

    private CentroDeResultado getCentroResultado(UJComboBox combo) {
        return (CentroDeResultado) combo.getSelectedItem();
    }

    private void setCentroResultado(UJComboBox combo, Long ID) {
        for (int i = 0; i < combo.getModel().getSize(); i++) {
            CentroDeResultado c = (CentroDeResultado) combo.getItemAt(i);
            if (Objects.equals(c.getID(), ID)) {
                combo.setSelectedIndex(i);
            }
        }
    }

    private PlanoContaBeans getPlanoConta(JComboBox<PlanoContaBeans> combo) {
        return (PlanoContaBeans) combo.getSelectedItem();
    }

    private void setPlanoConta(JComboBox<PlanoContaBeans> combo, Integer ID) {
        for (int i = 0; i < combo.getModel().getSize(); i++) {
            PlanoContaBeans p = (PlanoContaBeans) combo.getItemAt(i);
            if (Objects.equals(p.getID(), ID)) {
                combo.setSelectedIndex(i);
            }
        }
    }

    private void setExpandedComboBox(Component c) {
        if (c instanceof JComboBox) {
            ((JComboBox) c).setPopupVisible(true);
        } else if (c instanceof UJComboBox) {
            ((UJComboBox) c).setPopupVisible(true);
        }
    }

    private void EnviarEmail() {
        RelatoriosOperacoes rel = new RelatoriosOperacoes();
        rel.enviarEmail("C.I de Admissão - " + txt_nomeFunc.getText(), "");
    }
}
