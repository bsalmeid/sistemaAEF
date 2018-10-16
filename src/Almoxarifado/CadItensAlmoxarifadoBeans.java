package Almoxarifado;

import Beans.CadUnidadesBeans;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "cad_itens_almoxarif")
public class CadItensAlmoxarifadoBeans implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer ID;
    
    @ManyToOne(optional = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "id_unidade", referencedColumnName = "id", nullable = false)
    private CadUnidadesBeans id_unidade;
    
    @ManyToOne(optional = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categoria", referencedColumnName = "id", nullable = false)
    private CategoriaAlmoxarif id_categoria;
    
    @OneToMany(mappedBy = "id_item",cascade = CascadeType.ALL,orphanRemoval = true, fetch =  FetchType.LAZY, targetEntity = CadItensAlmoxCodigos.class)
    public List<CadItensAlmoxCodigos> listaCodigo;
    
    @OneToMany(mappedBy = "id_item",cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = CadItensAlmoxAplicacao.class)
    public List<CadItensAlmoxAplicacao> listaAplicacao;

    @Column(name = "descricao")
    private String Descricao;
    
    @Column(name = "status", columnDefinition = "BIT", length = 1)
    private Boolean Status;
    
    @Column(name = "observacao")
    private String Observacao;
    
    @Transient
    private Integer IDCategoria;
    
    @Transient
    private Integer IDUnidade;

    @Override
    public String toString() {
        return "CadItensAlmoxarifadoBeans{" + "ID=" + ID + ", id_unidade=" + id_unidade + ", id_categoria=" + id_categoria + ", Descricao=" + Descricao + ", Status=" + Status + ", Observacao=" + Observacao + '}';
    }
    
    public CadItensAlmoxarifadoBeans() {
    
    }

    public CadItensAlmoxarifadoBeans(Integer ID) {
        this.ID = ID;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getIDCategoria() {
        return IDCategoria;
    }

    public void setIDCategoria(Integer IDCategoria) {
        this.IDCategoria = IDCategoria;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public Integer getIDUnidade() {
        return IDUnidade;
    }

    public void setIDUnidade(Integer IDUnidade) {
        this.IDUnidade = IDUnidade;
    }

    public List<CadItensAlmoxAplicacao> getListaAplicacao() {
        return listaAplicacao;
    }

    public void setListaAplicacao(List<CadItensAlmoxAplicacao> listaAplicacao) {
        this.listaAplicacao = listaAplicacao;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean Status) {
        this.Status = Status;
    }

    public List<CadItensAlmoxCodigos> getListaCodigo() {
        return listaCodigo;
    }

    public void setListaCodigo(List<CadItensAlmoxCodigos> listaCodigo) {
        this.listaCodigo = listaCodigo;
    }

    public String getObservacao() {
        return Observacao;
    }

    public void setObservacao(String Observacao) {
        this.Observacao = Observacao;
    }

    public CadUnidadesBeans getId_unidade() {
        return id_unidade;
    }

    public void setId_unidade(CadUnidadesBeans id_unidade) {
        this.id_unidade = id_unidade;
    }

    public CategoriaAlmoxarif getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(CategoriaAlmoxarif id_categoria) {
        this.id_categoria = id_categoria;
    }
    
}
