package Beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "recebimentos")
public class RecebimentosBeans implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rec_id", nullable = false)
    private Integer ID;

    @ManyToOne
    @JoinColumn(name = "rec_usuario", nullable = false, referencedColumnName = "id")
    @Temporal(TemporalType.DATE)
    private CadUsuario usuario;

    @Column(name = "rec_dataLan", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date DtLancamento;

    @Column(name = "rec_dataRecebimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date DtRecebimentos;

    @ManyToOne()
    @JoinColumn(name = "rec_idConta", nullable = false, referencedColumnName = "cad_conta_id")
    private ContaBancariaBeans idConta;

    @Column(name = "rec_contaDestino", nullable = false, length = 120)
    private String contaDestino;

    @Column(name = "rec_nEscala", nullable = false)
    private Integer nEscala;

    @Column(name = "rec_nCtoGrao", nullable = false)
    private Integer nCtoGrao;

    @ManyToOne
    @JoinColumn(name = "rec_idMoeda", nullable = false, referencedColumnName = "cad_moeda_id")
    private MoedaBeans idMoeda;

    @Column(name = "rec_moeda", nullable = false, length = 80)
    private String Moeda;

    @Transient
    private String Sifra;

    @Column(name = "rec_valorMoeda", nullable = false, precision = 4, scale = 11)
    private Double ValorEmMoeda;

    @Column(name = "rec_taxa", nullable = false, precision = 4, scale = 11)
    private Double Taxa;

    @Column(name = "rec_valorTotal", nullable = false, precision = 4, scale = 11)
    private Double ValorTotal;

    @Column(name = "rec_descricao", nullable = false, length = 180)
    private String Descricao;

    @Column(name = "rec_cnpj", nullable = false, length = 80)
    private String CNPJ;

    @ManyToOne
    @JoinColumn(name = "rec_idCliente", nullable = true, referencedColumnName = "cad_fornecedor_id")
    private FornecedoresBeans idCliente;

    @Column(name = "rec_nomeCliente", nullable = false, length = 180)
    private String nomeCliente;

    @ManyToOne
    @JoinColumn(name = "rec_idFazenda", nullable = false, referencedColumnName = "cad_fazendas_id")
    private PropriedadesBeans idFazenda;

    @Column(name = "rec_fazenda", nullable = false)
    private String Fazenda;

    @ManyToOne
    @JoinColumn(name = "rec_atividade", nullable = false, referencedColumnName = "id")
    private AtividadeBeans Atividade;

    @ManyToOne
    @JoinColumn(name = "rec_id_centro_resultado", nullable = false, referencedColumnName = "id")
    private CentroDeResultado CentroResultado;

    @ManyToOne
    @JoinColumn(name = "rec_planoConta", nullable = false, referencedColumnName = "pc_id")
    private PlanoContaBeans PlanoConta;

    @Column(name = "rec_descConta", nullable = false)
    private String DescPlanoConta;

    @Column(name = "rec_status", nullable = false, columnDefinition = "BIT", length = 1)
    private boolean Status;

    @Transient
    private String dtInicial;
    
    @Transient
    private String dtFinal;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public CadUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(CadUsuario usuario) {
        this.usuario = usuario;
    }

    public Date getDtLancamento() {
        return DtLancamento;
    }

    public void setDtLancamento(Date DtLancamento) {
        this.DtLancamento = DtLancamento;
    }

    public Date getDtRecebimentos() {
        return DtRecebimentos;
    }

    public void setDtRecebimentos(Date DtRecebimentos) {
        this.DtRecebimentos = DtRecebimentos;
    }

    public ContaBancariaBeans getIdConta() {
        return idConta;
    }

    public void setIdConta(ContaBancariaBeans idConta) {
        this.idConta = idConta;
    }

    public String getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(String contaDestino) {
        this.contaDestino = contaDestino;
    }

    public Integer getnEscala() {
        return nEscala;
    }

    public void setnEscala(Integer nEscala) {
        this.nEscala = nEscala;
    }

    public Integer getnCtoGrao() {
        return nCtoGrao;
    }

    public void setnCtoGrao(Integer nCtoGrao) {
        this.nCtoGrao = nCtoGrao;
    }

    public MoedaBeans getIdMoeda() {
        return idMoeda;
    }

    public void setIdMoeda(MoedaBeans idMoeda) {
        this.idMoeda = idMoeda;
    }

    public String getMoeda() {
        return Moeda;
    }

    public void setMoeda(String Moeda) {
        this.Moeda = Moeda;
    }

    public String getSifra() {
        return Sifra;
    }

    public void setSifra(String Sifra) {
        this.Sifra = Sifra;
    }

    public Double getValorEmMoeda() {
        return ValorEmMoeda;
    }

    public void setValorEmMoeda(Double ValorEmMoeda) {
        this.ValorEmMoeda = ValorEmMoeda;
    }

    public Double getTaxa() {
        return Taxa;
    }

    public void setTaxa(Double Taxa) {
        this.Taxa = Taxa;
    }

    public Double getValorTotal() {
        return ValorTotal;
    }

    public void setValorTotal(Double ValorTotal) {
        this.ValorTotal = ValorTotal;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public FornecedoresBeans getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(FornecedoresBeans idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public PropriedadesBeans getIdFazenda() {
        return idFazenda;
    }

    public void setIdFazenda(PropriedadesBeans idFazenda) {
        this.idFazenda = idFazenda;
    }

    public String getFazenda() {
        return Fazenda;
    }

    public void setFazenda(String Fazenda) {
        this.Fazenda = Fazenda;
    }

    public AtividadeBeans getAtividade() {
        return Atividade;
    }

    public void setAtividade(AtividadeBeans Atividade) {
        this.Atividade = Atividade;
    }

    public CentroDeResultado getCentroResultado() {
        return CentroResultado;
    }

    public void setCentroResultado(CentroDeResultado CentroResultado) {
        this.CentroResultado = CentroResultado;
    }

    public PlanoContaBeans getPlanoConta() {
        return PlanoConta;
    }

    public void setPlanoConta(PlanoContaBeans PlanoConta) {
        this.PlanoConta = PlanoConta;
    }

    public String getDescPlanoConta() {
        return DescPlanoConta;
    }

    public void setDescPlanoConta(String DescPlanoConta) {
        this.DescPlanoConta = DescPlanoConta;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public String getDtInicial() {
        return dtInicial;
    }

    public void setDtInicial(String dtInicial) {
        this.dtInicial = dtInicial;
    }

    public String getDtFinal() {
        return dtFinal;
    }

    public void setDtFinal(String dtFinal) {
        this.dtFinal = dtFinal;
    }

}
