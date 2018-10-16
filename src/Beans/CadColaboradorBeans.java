package Beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "cad_funcionarios")
public class CadColaboradorBeans implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer Id;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cei", referencedColumnName = "id", nullable = false)
    private CEIBeans IdCei;

    @Column(name = "id_cei", insertable = false, updatable = false, nullable = false)
    private Integer id_cei;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fazenda", referencedColumnName = "cad_fazendas_id", nullable = false)
    private PropriedadesBeans idFazenda;

    @Column(name = "id_fazenda", insertable = false, updatable = false, nullable = false)
    private Integer id_fazenda;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_setor", referencedColumnName = "id", nullable = false)
    private ListSetorTrabalhoBeans idSetor;

    @Column(name = "id_setor", insertable = false, updatable = false, nullable = false)
    private Integer id_setor;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcao", referencedColumnName = "id", nullable = false)
    private CargosBeans idFuncao;

    @Column(name = "id_funcao", insertable = false, updatable = false, nullable = false)
    private Integer id_funcao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uf_ctps", referencedColumnName = "id", nullable = true)
    private EstadosBeans UFCtps;

    @Column(name = "uf_ctps", insertable = false, updatable = false, nullable = false)
    private Integer id_uf_ctps;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uf_beneficiario", referencedColumnName = "id", nullable = true)
    private EstadosBeans UFBeneficiario;

    @Column(name = "uf_beneficiario", insertable = false, updatable = false, nullable = false)
    private Integer id_uf_beneficario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uf_func", referencedColumnName = "id", nullable = true)
    private EstadosBeans UFFuncionario;

    @Column(name = "uf_func", insertable = false, updatable = false, nullable = false)
    private Integer id_uf_funcionario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cert_nasc_uf", referencedColumnName = "id", nullable = true)
    private EstadosBeans Cert_nasc_uf;

    @Column(name = "cert_nasc_uf", insertable = false, updatable = false, nullable = false)
    private Integer id_uf_cert_nasc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uf_casamento", referencedColumnName = "id", nullable = true)
    private EstadosBeans UfCasamento;//Relacional

    @Column(name = "uf_casamento", insertable = false, updatable = false, nullable = false)
    private Integer id_uf_casamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uf_agencia", referencedColumnName = "id", nullable = true)
    private EstadosBeans EstadoAgencia;
    
    @Column(name = "uf_agencia", insertable = false, updatable = false, nullable = false)
    private Integer id_uf_agencia;

    //Listas
    @OneToMany(mappedBy = "id_funcionario", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FilhosFuncionariosBeans> listFilhos;

    @OneToMany(mappedBy = "IdColaborador", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    private List<CadColaboradorClassBeans> listaClassificacao;

    @Column(name = "codigo")
    private Integer CodigoMaster;

    @Column(name = "tipo_contrato")
    private String TipoContrato;

    @Column(name = "salario_base")
    private Double Salario;

    @Column(name = "dt_adimissao")
    @Temporal(TemporalType.DATE)
    private Date dtAdmissao;

    @Column(name = "dia_descanco")
    private String DSR;

    @Column(name = "h_entr1")
    @Temporal(TemporalType.TIME)
    private Date Hent1;

    @Column(name = "h_saida1")
    @Temporal(TemporalType.TIME)
    private Date Hsaida1;

    @Column(name = "h_ent2")
    @Temporal(TemporalType.TIME)
    private Date Hent2;

    @Column(name = "h_saida2")
    @Temporal(TemporalType.TIME)
    private Date Hsaida2;

    @Column(name = "comodato", columnDefinition = "BIT", length = 1)
    private Boolean Comodato;

    @Column(name = "Status")
    private String Ativo;

    @Column(name = "dt_demissao")
    @Temporal(TemporalType.DATE)
    private Date dtDemissao;

    // dados Colaborador
    @Column(name = "nome")
    private String NomeFunc;

    @Column(name = "cpf_func")
    private String CPFFunc;

    @Column(name = "rg_func")
    private String RGFunc;

    @Column(name = "sexo_func")
    private String Sexo;

    @Column(name = "calcado_func")
    private String nCalcado;

    @Column(name = "sangue_func")
    private String TipoSangue;

    @Column(name = "alergia_func")
    private String TipoAlergia;

    @Column(name = "org_exp_func")
    private String OrdaoExpFunc;

    @Column(name = "dt_emissao_rg_func")
    @Temporal(TemporalType.DATE)
    private Date dtEmissaoRgFunc;

    @Column(name = "dt_nasc_func")
    @Temporal(TemporalType.DATE)
    private Date dtNascimentoFunc;

    @Column(name = "ctps")
    private String CTPS;

    @Column(name = "serie_ctps")
    private String serieCTPS;

    @Column(name = "dt_emissao_ctps")
    @Temporal(TemporalType.DATE)
    private Date dtEmissaoCTPS;

    @Column(name = "pis")
    private String PIS;

    @Column(name = "dt_emissao_pis")
    @Temporal(TemporalType.DATE)
    private Date dtEmissaoPIS;

    @Column(name = "titulo_eleitoral")
    private String tituloEleitoral;

    @Column(name = "zona_eleitoral")
    private String zonaEleitoral;

    @Column(name = "sessao_eleitoral")
    private String secaoEleitoral;

    @Column(name = "habilitacao")
    private String Habilitacao;

    @Column(name = "categoria_habilitacao")
    private String categoria;

    @Column(name = "reservista")
    private String reservista;

    @Column(name = "deficiente", columnDefinition = "BIT", length = 1)
    private Boolean Deficiente;

    @Column(name = "deficiencia")
    private String TipoDeficiencia;

    @Column(name = "cartao_sus")
    private String cartaoSUS;

    @Column(name = "cor_pele")
    private String CorDaPele;

    @Column(name = "escolaridade_func")
    private String Escolaridade;

    @Column(name = "beneficiario")
    private String Beneficiario;

    @Column(name = "end_beneficiario")
    private String EnderecoBeneficario;

    @Column(name = "bairro_beneficiario")
    private String BairroBeneficiario;

    @Column(name = "cidade_beneficiario")
    private String CidadeBeneficiario;

    @Column(name = "telefone_benef")
    private String TelefoneBenef;

    @Column(name = "end_func")
    private String EnderecoFuncionario;

    @Column(name = "bairro_funcionario")
    private String BairroFuncionario;

    @Column(name = "cidade_func")
    private String CidadeFuncionario;

    @Column(name = "telefone_funcionario")
    private String TelefoneFunc;

    // dados Filiacão
    @Column(name = "nome_pai")
    private String nomePai;

    @Column(name = "nome_mae")
    private String nomeMae;

    @Column(name = "cert_nasc_livro")
    private String Cert_nasc_livro;

    @Column(name = "cert_nasc_folha")
    private String Cert_nasc_folha;

    @Column(name = "cert_nasc_num")
    private String Cert_nasc_num;

    @Column(name = "cert_nasc_data")
    @Temporal(TemporalType.DATE)
    private Date Cert_nasc_data;

    @Column(name = "cert_nasc_cidade")
    private String Cert_nasc_cidade;

    // Estado Civil
    @Column(name = "estado_civil")
    private String EstadoCivil;

    @Column(name = "nome_conj")
    private String NomeConjuge;

    @Column(name = "cpf_conj")
    private String CPFConjuge;

    @Column(name = "rg_conj")
    private String RGConjuge;

    @Column(name = "org_exp_rg_conj")
    private String OrgExpedidoConjuge;

    @Column(name = "escolaridade_conj")
    private String EscolaridadeConjuge;

    @Column(name = "profissao_conj")
    private String ProfissaoConjuge;

    @Column(name = "livro_casamento")
    private String LivroCasamento;

    @Column(name = "folha_casamento")
    private String FolhaCasamento;

    @Column(name = "numero_casamento")
    private String numeroCasamento;

    @Column(name = "dt_casamento")
    @Temporal(TemporalType.DATE)
    private Date DataCasamento;

    @Column(name = "cidade_casamento")
    private String CidadeCasamento;

    //Dados Filhos
    @Column(name = "filhos", columnDefinition = "BIT", length = 1)
    private Boolean temFilhos;

    @Column(name = "salario_familia", columnDefinition = "BIT", length = 1)
    private Boolean salarioFamilia;

    // Dados Bancarios
    @Column(name = "titular_conta")
    private String Titular;

    @Column(name = "tipo_conta")
    private String TipoConta;

    @Column(name = "banco")
    private String Banco;

    @Column(name = "codigo_banco")
    private Integer CodigoBanco;

    @Column(name = "agencia")
    private String Agencia;

    @Column(name = "conta")
    private String ContaBancaria;

    @Column(name = "cidade_agencia")
    private String CidadeAgencia;

    public CadColaboradorBeans() {
    
    }

    public CadColaboradorBeans(Integer Id) {
        this.Id = Id;
    }
    
    public CadColaboradorBeans(Integer Id, String NomeFunc) {
        this.Id = Id;
        this.NomeFunc = NomeFunc;
    }

    public List<FilhosFuncionariosBeans> getListFilhos() {
        return listFilhos;
    }

    public void setListFilhos(List<FilhosFuncionariosBeans> listFilhos) {
        this.listFilhos = listFilhos;
    }

    public EstadosBeans getUFBeneficiario() {
        return UFBeneficiario;
    }

    public void setUFBeneficiario(EstadosBeans UFBeneficiario) {
        this.UFBeneficiario = UFBeneficiario;
    }

    public EstadosBeans getUFFuncionario() {
        return UFFuncionario;
    }

    public void setUFFuncionario(EstadosBeans UFFuncionario) {
        this.UFFuncionario = UFFuncionario;
    }

    public EstadosBeans getCert_nasc_uf() {
        return Cert_nasc_uf;
    }

    public void setCert_nasc_uf(EstadosBeans Cert_nasc_uf) {
        this.Cert_nasc_uf = Cert_nasc_uf;
    }

    public EstadosBeans getUfCasamento() {
        return UfCasamento;
    }

    public void setUfCasamento(EstadosBeans UfCasamento) {
        this.UfCasamento = UfCasamento;
    }

    public EstadosBeans getEstadoAgencia() {
        return EstadoAgencia;
    }

    public void setEstadoAgencia(EstadosBeans EstadoAgencia) {
        this.EstadoAgencia = EstadoAgencia;
    }

    public CEIBeans getIdCei() {
        return IdCei;
    }

    public void setIdCei(CEIBeans IdCei) {
        this.IdCei = IdCei;
    }

    public PropriedadesBeans getIdFazenda() {
        return idFazenda;
    }

    public void setIdFazenda(PropriedadesBeans idFazenda) {
        this.idFazenda = idFazenda;
    }

    public ListSetorTrabalhoBeans getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(ListSetorTrabalhoBeans idSetor) {
        this.idSetor = idSetor;
    }

    public CargosBeans getIdFuncao() {
        return idFuncao;
    }

    public void setIdFuncao(CargosBeans idFuncao) {
        this.idFuncao = idFuncao;
    }

    public Date getDtAdmissao() {
        return dtAdmissao;
    }

    public void setDtAdmissao(Date dtAdmissao) {
        this.dtAdmissao = dtAdmissao;
    }

    public Date getDtDemissao() {
        return dtDemissao;
    }

    public void setDtDemissao(Date dtDemissao) {
        this.dtDemissao = dtDemissao;
    }

    public Date getDtEmissaoRgFunc() {
        return dtEmissaoRgFunc;
    }

    public void setDtEmissaoRgFunc(Date dtEmissaoRgFunc) {
        this.dtEmissaoRgFunc = dtEmissaoRgFunc;
    }

    public Date getDtNascimentoFunc() {
        return dtNascimentoFunc;
    }

    public void setDtNascimentoFunc(Date dtNascimentoFunc) {
        this.dtNascimentoFunc = dtNascimentoFunc;
    }

    public Date getDtEmissaoCTPS() {
        return dtEmissaoCTPS;
    }

    public void setDtEmissaoCTPS(Date dtEmissaoCTPS) {
        this.dtEmissaoCTPS = dtEmissaoCTPS;
    }

    public Date getDtEmissaoPIS() {
        return dtEmissaoPIS;
    }

    public void setDtEmissaoPIS(Date dtEmissaoPIS) {
        this.dtEmissaoPIS = dtEmissaoPIS;
    }

    public Date getCert_nasc_data() {
        return Cert_nasc_data;
    }

    public void setCert_nasc_data(Date Cert_nasc_data) {
        this.Cert_nasc_data = Cert_nasc_data;
    }

    public Date getDataCasamento() {
        return DataCasamento;
    }

    public void setDataCasamento(Date DataCasamento) {
        this.DataCasamento = DataCasamento;
    }

    public String getTelefoneBenef() {
        return TelefoneBenef;
    }

    public void setTelefoneBenef(String TelefoneBenef) {
        this.TelefoneBenef = TelefoneBenef;
    }

    public String getTelefoneFunc() {
        return TelefoneFunc;
    }

    public void setTelefoneFunc(String TelefoneFunc) {
        this.TelefoneFunc = TelefoneFunc;
    }

    public Boolean getSalarioFamilia() {
        return salarioFamilia;
    }

    public void setSalarioFamilia(Boolean salarioFamilia) {
        this.salarioFamilia = salarioFamilia;
    }

    public Integer getCodigoMaster() {
        return CodigoMaster;
    }

    public void setCodigoMaster(Integer CodigoMaster) {
        this.CodigoMaster = CodigoMaster;
    }

    public String getTipoContrato() {
        return TipoContrato;
    }

    public void setTipoContrato(String TipoContrato) {
        this.TipoContrato = TipoContrato;
    }

    public Double getSalario() {
        return Salario;
    }

    public void setSalario(Double Salario) {
        this.Salario = Salario;
    }

    public String getDSR() {
        return DSR;
    }

    public void setDSR(String DSR) {
        this.DSR = DSR;
    }

    public Date getHent1() {
        return Hent1;
    }

    public void setHent1(Date Hent1) {
        this.Hent1 = Hent1;
    }

    public Date getHsaida1() {
        return Hsaida1;
    }

    public void setHsaida1(Date Hsaida1) {
        this.Hsaida1 = Hsaida1;
    }

    public Date getHent2() {
        return Hent2;
    }

    public void setHent2(Date Hent2) {
        this.Hent2 = Hent2;
    }

    public Date getHsaida2() {
        return Hsaida2;
    }

    public void setHsaida2(Date Hsaida2) {
        this.Hsaida2 = Hsaida2;
    }

    public Boolean getComodato() {
        return Comodato;
    }

    public void setComodato(Boolean Comodato) {
        this.Comodato = Comodato;
    }

    public String getAtivo() {
        return Ativo;
    }

    public void setAtivo(String Ativo) {
        this.Ativo = Ativo;
    }

    public String getNomeFunc() {
        return NomeFunc;
    }

    public void setNomeFunc(String NomeFunc) {
        this.NomeFunc = NomeFunc;
    }

    public String getCPFFunc() {
        return CPFFunc;
    }

    public void setCPFFunc(String CPFFunc) {
        this.CPFFunc = CPFFunc;
    }

    public String getRGFunc() {
        return RGFunc;
    }

    public void setRGFunc(String RGFunc) {
        this.RGFunc = RGFunc;
    }

    public String getOrdaoExpFunc() {
        return OrdaoExpFunc;
    }

    public void setOrdaoExpFunc(String OrdaoExpFunc) {
        this.OrdaoExpFunc = OrdaoExpFunc;
    }

    public String getCTPS() {
        return CTPS;
    }

    public void setCTPS(String CTPS) {
        this.CTPS = CTPS;
    }

    public String getSerieCTPS() {
        return serieCTPS;
    }

    public void setSerieCTPS(String serieCTPS) {
        this.serieCTPS = serieCTPS;
    }

    public String getPIS() {
        return PIS;
    }

    public void setPIS(String PIS) {
        this.PIS = PIS;
    }

    public String getTituloEleitoral() {
        return tituloEleitoral;
    }

    public void setTituloEleitoral(String tituloEleitoral) {
        this.tituloEleitoral = tituloEleitoral;
    }

    public String getZonaEleitoral() {
        return zonaEleitoral;
    }

    public void setZonaEleitoral(String zonaEleitoral) {
        this.zonaEleitoral = zonaEleitoral;
    }

    public String getSecaoEleitoral() {
        return secaoEleitoral;
    }

    public void setSecaoEleitoral(String secaoEleitoral) {
        this.secaoEleitoral = secaoEleitoral;
    }

    public String getHabilitacao() {
        return Habilitacao;
    }

    public void setHabilitacao(String Habilitacao) {
        this.Habilitacao = Habilitacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getReservista() {
        return reservista;
    }

    public void setReservista(String reservista) {
        this.reservista = reservista;
    }

    public Boolean getDeficiente() {
        return Deficiente;
    }

    public void setDeficiente(Boolean Deficiente) {
        this.Deficiente = Deficiente;
    }

    public String getTipoDeficiencia() {
        return TipoDeficiencia;
    }

    public void setTipoDeficiencia(String TipoDeficiencia) {
        this.TipoDeficiencia = TipoDeficiencia;
    }

    public String getCartaoSUS() {
        return cartaoSUS;
    }

    public void setCartaoSUS(String cartaoSUS) {
        this.cartaoSUS = cartaoSUS;
    }

    public String getCorDaPele() {
        return CorDaPele;
    }

    public void setCorDaPele(String CorDaPele) {
        this.CorDaPele = CorDaPele;
    }

    public String getEscolaridade() {
        return Escolaridade;
    }

    public void setEscolaridade(String Escolaridade) {
        this.Escolaridade = Escolaridade;
    }

    public String getBeneficiario() {
        return Beneficiario;
    }

    public void setBeneficiario(String Beneficiario) {
        this.Beneficiario = Beneficiario;
    }

    public String getEnderecoFuncionario() {
        return EnderecoFuncionario;
    }

    public void setEnderecoFuncionario(String EnderecoFuncionario) {
        this.EnderecoFuncionario = EnderecoFuncionario;
    }

    public String getCidadeFuncionario() {
        return CidadeFuncionario;
    }

    public void setCidadeFuncionario(String CidadeFuncionario) {
        this.CidadeFuncionario = CidadeFuncionario;
    }

    public String getEnderecoBeneficario() {
        return EnderecoBeneficario;
    }

    public void setEnderecoBeneficario(String EnderecoBeneficario) {
        this.EnderecoBeneficario = EnderecoBeneficario;
    }

    public String getCidadeBeneficiario() {
        return CidadeBeneficiario;
    }

    public void setCidadeBeneficiario(String CidadeBeneficiario) {
        this.CidadeBeneficiario = CidadeBeneficiario;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getCert_nasc_livro() {
        return Cert_nasc_livro;
    }

    public void setCert_nasc_livro(String Cert_nasc_livro) {
        this.Cert_nasc_livro = Cert_nasc_livro;
    }

    public String getCert_nasc_folha() {
        return Cert_nasc_folha;
    }

    public void setCert_nasc_folha(String Cert_nasc_folha) {
        this.Cert_nasc_folha = Cert_nasc_folha;
    }

    public String getCert_nasc_num() {
        return Cert_nasc_num;
    }

    public void setCert_nasc_num(String Cert_nasc_num) {
        this.Cert_nasc_num = Cert_nasc_num;
    }

    public String getCert_nasc_cidade() {
        return Cert_nasc_cidade;
    }

    public void setCert_nasc_cidade(String Cert_nasc_cidade) {
        this.Cert_nasc_cidade = Cert_nasc_cidade;
    }

    public String getEstadoCivil() {
        return EstadoCivil;
    }

    public void setEstadoCivil(String EstadoCivil) {
        this.EstadoCivil = EstadoCivil;
    }

    public String getNomeConjuge() {
        return NomeConjuge;
    }

    public void setNomeConjuge(String NomeConjuge) {
        this.NomeConjuge = NomeConjuge;
    }

    public String getCPFConjuge() {
        return CPFConjuge;
    }

    public void setCPFConjuge(String CPFConjuge) {
        this.CPFConjuge = CPFConjuge;
    }

    public String getRGConjuge() {
        return RGConjuge;
    }

    public void setRGConjuge(String RGConjuge) {
        this.RGConjuge = RGConjuge;
    }

    public String getOrgExpedidoConjuge() {
        return OrgExpedidoConjuge;
    }

    public void setOrgExpedidoConjuge(String OrgExpedidoConjuge) {
        this.OrgExpedidoConjuge = OrgExpedidoConjuge;
    }

    public String getEscolaridadeConjuge() {
        return EscolaridadeConjuge;
    }

    public void setEscolaridadeConjuge(String EscolaridadeConjuge) {
        this.EscolaridadeConjuge = EscolaridadeConjuge;
    }

    public String getProfissaoConjuge() {
        return ProfissaoConjuge;
    }

    public void setProfissaoConjuge(String ProfissaoConjuge) {
        this.ProfissaoConjuge = ProfissaoConjuge;
    }

    public String getLivroCasamento() {
        return LivroCasamento;
    }

    public void setLivroCasamento(String LivroCasamento) {
        this.LivroCasamento = LivroCasamento;
    }

    public String getFolhaCasamento() {
        return FolhaCasamento;
    }

    public void setFolhaCasamento(String FolhaCasamento) {
        this.FolhaCasamento = FolhaCasamento;
    }

    public String getNumeroCasamento() {
        return numeroCasamento;
    }

    public void setNumeroCasamento(String numeroCasamento) {
        this.numeroCasamento = numeroCasamento;
    }

    public String getCidadeCasamento() {
        return CidadeCasamento;
    }

    public void setCidadeCasamento(String CidadeCasamento) {
        this.CidadeCasamento = CidadeCasamento;
    }

    public Boolean getTemFilhos() {
        return temFilhos;
    }

    public void setTemFilhos(Boolean temFilhos) {
        this.temFilhos = temFilhos;
    }

    public String getTitular() {
        return Titular;
    }

    public void setTitular(String Titular) {
        this.Titular = Titular;
    }

    public String getTipoConta() {
        return TipoConta;
    }

    public void setTipoConta(String TipoConta) {
        this.TipoConta = TipoConta;
    }

    public String getBanco() {
        return Banco;
    }

    public void setBanco(String Banco) {
        this.Banco = Banco;
    }

    public Integer getCodigoBanco() {
        return CodigoBanco;
    }

    public void setCodigoBanco(Integer CodigoBanco) {
        this.CodigoBanco = CodigoBanco;
    }

    public String getAgencia() {
        return Agencia;
    }

    public void setAgencia(String Agencia) {
        this.Agencia = Agencia;
    }

    public String getContaBancaria() {
        return ContaBancaria;
    }

    public void setContaBancaria(String ContaBancaria) {
        this.ContaBancaria = ContaBancaria;
    }

    public String getCidadeAgencia() {
        return CidadeAgencia;
    }

    public void setCidadeAgencia(String CidadeAgencia) {
        this.CidadeAgencia = CidadeAgencia;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public EstadosBeans getUFCtps() {
        return UFCtps;
    }

    public void setUFCtps(EstadosBeans UFCtps) {
        this.UFCtps = UFCtps;
    }

    public String getnCalcado() {
        return nCalcado;
    }

    public void setnCalcado(String nCalcado) {
        this.nCalcado = nCalcado;
    }

    public String getBairroBeneficiario() {
        return BairroBeneficiario;
    }

    public void setBairroBeneficiario(String BairroBeneficiario) {
        this.BairroBeneficiario = BairroBeneficiario;
    }

    public String getBairroFuncionario() {
        return BairroFuncionario;
    }

    public void setBairroFuncionario(String BairroFuncionario) {
        this.BairroFuncionario = BairroFuncionario;
    }

    public String getTipoSangue() {
        return TipoSangue;
    }

    public void setTipoSangue(String TipoSangue) {
        this.TipoSangue = TipoSangue;
    }

    public String getTipoAlergia() {
        return TipoAlergia;
    }

    public void setTipoAlergia(String TipoAlergia) {
        this.TipoAlergia = TipoAlergia;
    }

    public List<CadColaboradorClassBeans> getListaClassificacao() {
        return listaClassificacao;
    }

    public void setListaClassificacao(List<CadColaboradorClassBeans> listaClassificacao) {
        this.listaClassificacao = listaClassificacao;
    }

    public Integer getId_cei() {
        return id_cei;
    }

    public void setId_cei(Integer id_cei) {
        this.id_cei = id_cei;
    }

    public Integer getId_fazenda() {
        return id_fazenda;
    }

    public void setId_fazenda(Integer id_fazenda) {
        this.id_fazenda = id_fazenda;
    }

    public Integer getId_setor() {
        return id_setor;
    }

    public void setId_setor(Integer id_setor) {
        this.id_setor = id_setor;
    }

    public Integer getId_funcao() {
        return id_funcao;
    }

    public void setId_funcao(Integer id_funcao) {
        this.id_funcao = id_funcao;
    }

    public Integer getId_uf_ctps() {
        return id_uf_ctps;
    }

    public void setId_uf_ctps(Integer id_uf_ctps) {
        this.id_uf_ctps = id_uf_ctps;
    }

    public Integer getId_uf_beneficario() {
        return id_uf_beneficario;
    }

    public void setId_uf_beneficario(Integer id_uf_beneficario) {
        this.id_uf_beneficario = id_uf_beneficario;
    }

    public Integer getId_uf_funcionario() {
        return id_uf_funcionario;
    }

    public void setId_uf_funcionario(Integer id_uf_funcionario) {
        this.id_uf_funcionario = id_uf_funcionario;
    }

    public Integer getId_uf_cert_nasc() {
        return id_uf_cert_nasc;
    }

    public void setId_uf_cert_nasc(Integer id_uf_cert_nasc) {
        this.id_uf_cert_nasc = id_uf_cert_nasc;
    }

    public Integer getId_uf_casamento() {
        return id_uf_casamento;
    }

    public void setId_uf_casamento(Integer id_uf_casamento) {
        this.id_uf_casamento = id_uf_casamento;
    }

    public Integer getId_uf_agencia() {
        return id_uf_agencia;
    }

    public void setId_uf_agencia(Integer id_uf_agencia) {
        this.id_uf_agencia = id_uf_agencia;
    }

    
    
    @Override
    public String toString() {
        return this.NomeFunc;
    }

}
