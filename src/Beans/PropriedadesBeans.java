package Beans;

import java.io.Serializable;
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


@Entity
@Table(name = "cad_fazendas")
public class PropriedadesBeans implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cad_fazendas_id", nullable = false)
    private int Codigo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "cidade_bean", referencedColumnName = "Id", nullable = false)
    private CidadesBeans CidadeBeans;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "fazenda", orphanRemoval = false, targetEntity = TalhaoBeans.class)
    private List<TalhaoBeans> listaTalhao;

    @Column(name = "cad_fazendas_id_produtor", nullable = false, length = 11)
    private Integer idProdutor;

    @Column(name = "cad_fazendas_nome", nullable = false, length = 120)
    private String Nome;

    @Column(name = "cad_fazendas_end", nullable = false, length = 255)
    private String Endereco;

    @Column(name = "cad_fazendas_obs", nullable = true, length = 255)
    private String Observacao;

    @Column(name = "cad_fazendas_cidade", nullable = false, length = 120)
    private String Cidade;

    @Column(name = "cad_fazendas_area", nullable = false, scale = 7, precision = 2)
    private Double Area;

    @Column(name = "cad_fazendas_uf", nullable = false, length = 120)
    private String Estado;

    @Column(name = "cad_fazendas_tipo", nullable = true, length = 255, columnDefinition = "String default teste")
    private String Tipo;

    @Column(name = "cad_fazendas_stat", nullable = false, length = 120)
    private String Status;

    @Column(name = "cad_fazendas_ins_est", nullable = false, length = 120)
    private String Inscricao;

    public PropriedadesBeans() {
    }

    public PropriedadesBeans(int Codigo, String Nome) {
        this.Codigo = Codigo;
        this.Nome = Nome;
    }
    
    public CidadesBeans getCidadeBeans() {
        return CidadeBeans;
    }

    public void setCidadeBeans(CidadesBeans CidadeBeans) {
        this.CidadeBeans = CidadeBeans;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String Endereco) {
        this.Endereco = Endereco;
    }

    public String getObservacao() {
        return Observacao;
    }

    public void setObservacao(String Observacao) {
        this.Observacao = Observacao;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String Cidade) {
        this.Cidade = Cidade;
    }

    public Double getArea() {
        return Area;
    }

    public void setArea(Double Area) {
        this.Area = Area;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getInscricao() {
        return Inscricao;
    }

    public void setInscricao(String Inscricao) {
        this.Inscricao = Inscricao;
    }

    public List<TalhaoBeans> getListaTalhao() {
        return listaTalhao;
    }

    public void setListaTalhao(List<TalhaoBeans> listaTalhao) {
        this.listaTalhao = listaTalhao;
    }

    public Integer getIdProdutor() {
        return idProdutor;
    }

    public void setIdProdutor(Integer idProdutor) {
        this.idProdutor = idProdutor;
    }

    @Override
    public String toString() {
        return this.Nome;
    }

    
    
    
    
}

