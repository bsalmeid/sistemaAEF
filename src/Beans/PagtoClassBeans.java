package Beans;

import Beans.PagamentosBeans;
import Beans.*;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "escrituracao_classificacao")
public class PagtoClassBeans implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "clas_id", nullable = false)
    private Long ID;

    @ManyToOne
    @JoinColumn(name = "clas_idpagto", referencedColumnName = "pagto_id", nullable = false)
    private PagamentosBeans Pagto;

    @ManyToOne()
    @JoinColumn(name = "clas_idFazenda", referencedColumnName = "cad_fazendas_id", nullable = false)
    private PropriedadesBeans FazendaB;

    @ManyToOne()
    @JoinColumn(name = "clas_id_planocontas", referencedColumnName = "pc_id", nullable = false)
    private PlanoContaBeans PlanoConta;

    @ManyToOne
    @JoinColumn(name = "clas_idAtividade", referencedColumnName = "id", nullable = false)
    private AtividadeBeans AtividadeBeans;

    @ManyToOne
    @JoinColumn(name = "clas_idCentro", referencedColumnName = "id", nullable = true)
    private CentroDeResultado CentroResultado;

    @ManyToOne
    @JoinColumn(name = "clas_idinvent", nullable = true, referencedColumnName = "id")
    private InventarioBeans IdAplicacao; // id do número de frota

    @Column(name = "clas_atividade", nullable = false)
    private String Atividade;

    @Column(name = "clas_tipodespesa", nullable = false)
    private String TipoDespesa;

    @Column(name = "clas_planoconta", nullable = false)
    private Integer IdPlanoContas;

    @Column(name = "clas_descconta", nullable = false)
    private String DesConta;

    @Column(name = "clas_fazenda", nullable = false)
    private String Fazenda;

    @Column(name = "clas_nFrota", nullable = true)
    private String nFrota;

    @Column(name = "clas_valor", nullable = false)
    private Double ValorClas;

    @Column(name = "clas_descricao", nullable = false)
    private String descricao;

    public PagtoClassBeans() {

    }

    public String getnFrota() {
        return nFrota;
    }

    public void setnFrota(String nFrota) {
        this.nFrota = nFrota;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDesConta() {
        return DesConta;
    }

    public void setDesConta(String DesConta) {
        this.DesConta = DesConta;
    }

    public String getAtividade() {
        return Atividade;
    }

    public void setAtividade(String Atividade) {
        this.Atividade = Atividade;
    }

    public String getTipoDespesa() {
        return TipoDespesa;
    }

    public void setTipoDespesa(String TipoDespesa) {
        this.TipoDespesa = TipoDespesa;
    }

    public Integer getIdPlanoContas() {
        return IdPlanoContas;
    }

    public void setIdPlanoContas(Integer IdPlanoContas) {
        this.IdPlanoContas = IdPlanoContas;
    }

    public String getFazenda() {
        return Fazenda;
    }

    public void setFazenda(String Fazenda) {
        this.Fazenda = Fazenda;
    }

    public Double getValorClas() {
        return ValorClas;
    }

    public void setValorClas(Double ValorClas) {
        this.ValorClas = ValorClas;
    }

    public PagamentosBeans getPagto() {
        return Pagto;
    }

    public void setPagto(PagamentosBeans Pagto) {
        this.Pagto = Pagto;
    }

    public PropriedadesBeans getFazendaB() {
        return FazendaB;
    }

    public void setFazendaB(PropriedadesBeans FazendaB) {
        this.FazendaB = FazendaB;
    }

    public PlanoContaBeans getPlanoConta() {
        return PlanoConta;
    }

    public void setPlanoConta(PlanoContaBeans PlanoConta) {
        this.PlanoConta = PlanoConta;
    }

    public AtividadeBeans getAtividadeBeans() {
        return AtividadeBeans;
    }

    public void setAtividadeBeans(AtividadeBeans AtividadeBeans) {
        this.AtividadeBeans = AtividadeBeans;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public CentroDeResultado getCentroResultado() {
        return CentroResultado;
    }

    public void setCentroResultado(CentroDeResultado CentroResultado) {
        this.CentroResultado = CentroResultado;
    }

    public InventarioBeans getIdAplicacao() {
        return IdAplicacao;
    }

    public void setIdAplicacao(InventarioBeans IdAplicacao) {
        this.IdAplicacao = IdAplicacao;
    }

}
