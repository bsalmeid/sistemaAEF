
package Beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


@Entity
@Table(name = "cad_inventario")
public class InventarioBeans implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer ID;
        
    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    private CategoriaEquipamentosBeans id_categoria;
    
    @Column(name = "id_categoria", insertable = false, updatable = false, nullable = false)
    private Integer idcategoria;

    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_marca")
    private MarcaEquipamentosBeans id_marca;
    
    @Column(name = "id_marca", insertable = false, updatable = false, nullable = false)
    private Integer idmarca;

    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_modelo")
    private ModeloEquipamentosBeans id_modelo;
    
    @Column(name = "id_modelo", insertable = false, updatable = false, nullable = false)
    private Integer idmodelo;
    
    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fazenda")        
    private PropriedadesBeans id_fazenda;
    
    @Column(name = "id_fazenda", insertable = false, updatable = false, nullable = false)
    private Integer idfazenda;

    @Column(name = "n_frota",unique = true)
    private String nFrota;

    @Transient
    private String Categoria;

    @Transient
    private String Marca;

    @Transient
    private String Modelo;
    
    @Transient
    private String Fazenda;

    @Column(name = "data_aquisicao")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataAquisicao;

    @Column(name = "motorizado", columnDefinition = "BIT", length = 1, nullable = false)
    private Boolean motorizado;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "n_serie")
    private String serie;

    @Column(name = "largura_trabalho", precision = 5, scale = 2)
    private Double LarguraTrabalho;

    @Column(name = "ano")
    private String ano;

    @Column(name = "observacao")
    private String observacao;

    @Column(name = "status", columnDefinition = "BIT", length = 1, nullable = false)
    private Boolean status;

    
    public InventarioBeans() {
    }

    public InventarioBeans(Integer ID, CategoriaEquipamentosBeans id_categoria, MarcaEquipamentosBeans id_marca, ModeloEquipamentosBeans id_modelo, String nFrota, Date dataAquisicao, Boolean motorizado, Double LarguraTrabalho, String ano, String serie, String observacao, String descricao, Boolean status) {
        this.ID = ID;
        this.id_categoria = id_categoria;
        this.id_marca = id_marca;
        this.id_modelo = id_modelo;
        this.nFrota = nFrota;
        this.dataAquisicao = dataAquisicao;
        this.motorizado = motorizado;
        this.LarguraTrabalho = LarguraTrabalho;
        this.ano = ano;
        this.serie = serie;
        this.observacao = observacao;
        this.descricao = descricao;
        this.status = status;

    }    

    public InventarioBeans(Integer ID, String nFrota, String descricao) {
        this.ID = ID;
        this.nFrota = nFrota;
        this.descricao = descricao;
    }

    public PropriedadesBeans getId_fazenda() {
        return id_fazenda;
    }

    public void setId_fazenda(PropriedadesBeans id_fazenda) {
        this.id_fazenda = id_fazenda;
    }
    
    public CategoriaEquipamentosBeans getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(CategoriaEquipamentosBeans id_categoria) {
        this.id_categoria = id_categoria;
    }

    public MarcaEquipamentosBeans getId_marca() {
        return id_marca;
    }

    public void setId_marca(MarcaEquipamentosBeans id_marca) {
        this.id_marca = id_marca;
    }

    public ModeloEquipamentosBeans getId_modelo() {
        return id_modelo;
    }

    public void setId_modelo(ModeloEquipamentosBeans id_modelo) {
        this.id_modelo = id_modelo;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getnFrota() {
        return nFrota;
    }

    public Date getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(Date dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }
 
    public Boolean getMotorizado() {
        return motorizado;
    }

    public void setMotorizado(Boolean motorizado) {
        this.motorizado = motorizado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setnFrota(String nFrota) {
        this.nFrota = nFrota;
    }

    public Double getLarguraTrabalho() {
        return LarguraTrabalho;
    }

    public void setLarguraTrabalho(Double LarguraTrabalho) {
        this.LarguraTrabalho = LarguraTrabalho;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }

    public Integer getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }

    public Integer getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(Integer idmarca) {
        this.idmarca = idmarca;
    }

    public Integer getIdmodelo() {
        return idmodelo;
    }

    public void setIdmodelo(Integer idmodelo) {
        this.idmodelo = idmodelo;
    }

    public Integer getIdfazenda() {
        return idfazenda;
    }

    public void setIdfazenda(Integer idfazenda) {
        this.idfazenda = idfazenda;
    }

    public String getFazenda() {
        return Fazenda;
    }

    public void setFazenda(String Fazenda) {
        this.Fazenda = Fazenda;
    }
    
    
        
    @Override
    public String toString() {
        try {
            return  nFrota + " - " + id_modelo.getDescricao() + " - " + id_marca.getMarca();
        } catch (Exception e) {
            return "- -" ;
        }
        
    }


}
