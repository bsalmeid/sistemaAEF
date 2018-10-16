package Beans;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "pagamentos")
public class PagamentosBeans implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pagto_id", nullable = false)
    private Long ID;

    @Column(name = "pagto_dataLan", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date DataLancamento;

    @OneToMany(mappedBy = "Pagto", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = PagtoClassBeans.class)
    private List<PagtoClassBeans> listaClassificacao;

    @OneToMany(mappedBy = "Pagto", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = PagtoNFBeans.class)
    private List<PagtoNFBeans> listaNotaFiscal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fornecedor", referencedColumnName = "cad_fornecedor_id", nullable = false)
    private FornecedoresBeans FornecedorB;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "pagto_idContaOrigem", referencedColumnName = "cad_conta_id", nullable = false)
    private ContaBancariaBeans IDContaOrigem;

    @ManyToOne
    @JoinColumn(name = "pagto_idMoeda", referencedColumnName = "cad_moeda_id", nullable = false)
    private MoedaBeans MoedaBeans;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pagto_nCompra", nullable = true, referencedColumnName = "compra_gado_id")
    private CompraGadoBeans nCompra;
    
    @Column(name = "pagto_User", nullable = true)
    private String usuario;

    @Column(name = "pagto_DtPrevista", nullable = false, length = 120)
    @Temporal(TemporalType.DATE)
    private Date DtPrevista;

    @Column(name = "pagto_DataPagto", nullable = true, length = 120)
    @Temporal(TemporalType.DATE)
    private Date DtRealizado;

    @Column(name = "pagto_nPedido", nullable = true)
    private Integer nPedido;

    @Column(name = "pagto_moeda", nullable = false, length = 45)
    private String Moeda;

    @Column(name = "pagto_valormoeda", nullable = false, scale = 11, precision = 2)
    private Double ValorEmMoeda;

    @Column(name = "pagto_taxa", nullable = false, scale = 11, precision = 2)
    private Double TaxaConverte;

    @Column(name = "pagto_valorPagto", nullable = false, scale = 11, precision = 2)
    private Double ValorPagamento;

    @Column(name = "pagto_valorCompra", nullable = false, scale = 11, precision = 2)
    private Double ValorCompra;  //Previsão

    @Column(name = "pagto_forma", nullable = false, length = 120)
    private String FormaPagto;

    @Column(name = "pagto_nDoc", nullable = false, length = 120)
    private String nDocumento;

    @Column(name = "pagto_banco", nullable = false, length = 120)
    private String Banco;

    @Column(name = "pagto_agencia", nullable = false, length = 120)
    private String agencia;

    @Column(name = "pagto_conta", nullable = false, length = 120)
    private String conta;

    @Column(name = "pagto_titular", nullable = false, length = 120)
    private String titular;

    @Column(name = "pagto_cpf", nullable = false, length = 120)
    private String cpf;

    @Column(name = "pagto_cpf_cnpj", nullable = false, length = 120)
    private String CPF_CNPJ;

    @Column(name = "pagto_status", columnDefinition = "BIT", length = 1, nullable = false)
    private Boolean status;

    @Transient
    private Double ValorClas;

    @Transient
    private Double ValorNF;

    public PagamentosBeans() {

    }

    public PagamentosBeans(PagamentosBeans pg, Double ValorClas, Double ValorNF) {
        this.ID = pg.getID();
        this.IDContaOrigem = pg.getIDContaOrigem();
        this.MoedaBeans = pg.getMoedaBeans();
        this.DtPrevista = pg.getDtPrevista();
        this.DtRealizado = pg.getDtRealizado();
        this.Moeda = pg.getMoeda();
        this.ValorEmMoeda = pg.getValorEmMoeda();
        this.TaxaConverte = pg.getTaxaConverte();
        this.ValorPagamento = pg.getValorPagamento();
        this.ValorCompra = pg.getValorCompra();
        this.FormaPagto = pg.getFormaPagto();
        this.nDocumento = pg.getnDocumento();
        this.titular = pg.getTitular();
        this.cpf = pg.getCpf();
        this.status = pg.getStatus();
        this.ValorClas = ValorClas;
        this.ValorNF = ValorNF;
    }

    public PagamentosBeans(Long ID, ContaBancariaBeans Conta ,Date DtPrevista, Date DtRealizado, String Moeda, Double ValorEmMoeda, Double TaxaConverte, Double ValorPagamento, Double ValorCompra, String FormaPagto, String nDocumento, String titular, String cpf, Boolean status, Double ValorClas, Double ValorNF) {
        this.ID = ID;
        this.IDContaOrigem = Conta;
        this.DtPrevista = DtPrevista;
        this.DtRealizado = DtRealizado;
        this.Moeda = Moeda;
        this.ValorEmMoeda = ValorEmMoeda;
        this.TaxaConverte = TaxaConverte;
        this.ValorPagamento = ValorPagamento;
        this.ValorCompra = ValorCompra;
        this.FormaPagto = FormaPagto;
        this.nDocumento = nDocumento;
        this.titular = titular;
        this.cpf = cpf;
        this.status = status;
        this.ValorClas = (ValorClas != null ? ValorClas : 0) ;
        this.ValorNF = (ValorNF != null ? ValorNF : 0) ;    
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Date getDataLancamento() {
        return DataLancamento;
    }

    public void setDataLancamento(Date DataLancamento) {
        this.DataLancamento = DataLancamento;
    }

    public List<PagtoClassBeans> getListaClassificacao() {
        return listaClassificacao;
    }

    public void setListaClassificacao(List<PagtoClassBeans> listaClassificacao) {
        this.listaClassificacao = listaClassificacao;
    }

    public List<PagtoNFBeans> getListaNotaFiscal() {
        return listaNotaFiscal;
    }

    public void setListaNotaFiscal(List<PagtoNFBeans> listaNotaFiscal) {
        this.listaNotaFiscal = listaNotaFiscal;
    }

    public FornecedoresBeans getFornecedorB() {
        return FornecedorB;
    }

    public void setFornecedorB(FornecedoresBeans FornecedorB) {
        this.FornecedorB = FornecedorB;
    }

    public ContaBancariaBeans getIDContaOrigem() {
        return IDContaOrigem;
    }

    public void setIDContaOrigem(ContaBancariaBeans IDContaOrigem) {
        this.IDContaOrigem = IDContaOrigem;
    }

    public MoedaBeans getMoedaBeans() {
        return MoedaBeans;
    }

    public void setMoedaBeans(MoedaBeans MoedaBeans) {
        this.MoedaBeans = MoedaBeans;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getDtPrevista() {
        return DtPrevista;
    }

    public void setDtPrevista(Date DtPrevista) {
        this.DtPrevista = DtPrevista;
    }

    public Date getDtRealizado() {
        return DtRealizado;
    }

    public void setDtRealizado(Date DtRealizado) {
        this.DtRealizado = DtRealizado;
    }

    public Integer getnPedido() {
        return nPedido;
    }

    public void setnPedido(Integer nPedido) {
        this.nPedido = nPedido;
    }

    public CompraGadoBeans getnCompra() {
        return nCompra;
    }

    public void setnCompra(CompraGadoBeans nCompra) {
        this.nCompra = nCompra;
    }

    public String getMoeda() {
        return Moeda;
    }

    public void setMoeda(String Moeda) {
        this.Moeda = Moeda;
    }

    public Double getValorEmMoeda() {
        return ValorEmMoeda;
    }

    public void setValorEmMoeda(Double ValorEmMoeda) {
        this.ValorEmMoeda = ValorEmMoeda;
    }

    public Double getTaxaConverte() {
        return TaxaConverte;
    }

    public void setTaxaConverte(Double TaxaConverte) {
        this.TaxaConverte = TaxaConverte;
    }

    public Double getValorPagamento() {
        return ValorPagamento;
    }

    public void setValorPagamento(Double ValorPagamento) {
        this.ValorPagamento = ValorPagamento;
    }

    public Double getValorCompra() {
        return ValorCompra;
    }

    public void setValorCompra(Double ValorCompra) {
        this.ValorCompra = ValorCompra;
    }

    public String getFormaPagto() {
        return FormaPagto;
    }

    public void setFormaPagto(String FormaPagto) {
        this.FormaPagto = FormaPagto;
    }

    public String getnDocumento() {
        return nDocumento;
    }

    public void setnDocumento(String nDocumento) {
        this.nDocumento = nDocumento;
    }

    public String getBanco() {
        return Banco;
    }

    public void setBanco(String Banco) {
        this.Banco = Banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCPF_CNPJ() {
        return CPF_CNPJ;
    }

    public void setCPF_CNPJ(String CPF_CNPJ) {
        this.CPF_CNPJ = CPF_CNPJ;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Double getValorClas() {
        return ValorClas;
    }

    public void setValorClas(Double ValorClas) {
        this.ValorClas = ValorClas;
    }

    public Double getValorNF() {
        return ValorNF;
    }

    public void setValorNF(Double ValorNF) {
        this.ValorNF = ValorNF;
    }
    
}
