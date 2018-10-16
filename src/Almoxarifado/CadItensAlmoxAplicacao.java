package Almoxarifado;

import Beans.CategoriaEquipamentosBeans;
import Beans.MarcaEquipamentosBeans;
import Beans.ModeloEquipamentosBeans;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "cad_itens_almox_aplic")
public class CadItensAlmoxAplicacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer ID;
    
    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_item", referencedColumnName = "id", nullable = false)
    private CadItensAlmoxarifadoBeans id_item;
    
  /*@ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria_item")
    private CategoriaAlmoxarif id_categoria_item;*/
    
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria_aplic", referencedColumnName = "id", nullable = true)
    private CategoriaEquipamentosBeans id_categoria_aplic;
    
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_marca_aplic", referencedColumnName = "id", nullable = true)
    private MarcaEquipamentosBeans id_marca_aplic;
    
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_modelo_aplic", referencedColumnName = "id", nullable = true)
    private ModeloEquipamentosBeans id_modelo_aplic;
    
    @Column(name = "marca")
    private String Marca;
    
    @Column(name = "modelo")
    private String Modelo;
                
    @Transient
    private Integer IdItem;
    
    @Transient
    private Integer IdCategoriaItem;
    
    @Transient
    private Integer IdCategoriaAplic;
    
    @Transient
    private Integer IdMarcaAplic;
    
    @Transient
    private Integer IdModeloAplica;

    public CadItensAlmoxAplicacao() {
    }

    public CadItensAlmoxAplicacao(Integer ID) {
        this.ID = ID;
    }
    
    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getIdItem() {
        return IdItem;
    }

    public void setIdItem(Integer IdItem) {
        this.IdItem = IdItem;
    }

    public Integer getIdCategoriaItem() {
        return IdCategoriaItem;
    }

    public void setIdCategoriaItem(Integer IdCategoriaItem) {
        this.IdCategoriaItem = IdCategoriaItem;
    }

    public Integer getIdCategoriaAplic() {
        return IdCategoriaAplic;
    }

    public void setIdCategoriaAplic(Integer IdCategoriaAplic) {
        this.IdCategoriaAplic = IdCategoriaAplic;
    }

    public Integer getIdMarcaAplic() {
        return IdMarcaAplic;
    }

    public void setIdMarcaAplic(Integer IdMarcaAplic) {
        this.IdMarcaAplic = IdMarcaAplic;
    }

    public Integer getIdModeloAplica() {
        return IdModeloAplica;
    }

    public void setIdModeloAplica(Integer IdModeloAplica) {
        this.IdModeloAplica = IdModeloAplica;
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

    public CadItensAlmoxarifadoBeans getId_item() {
        return id_item;
    }

    public void setId_item(CadItensAlmoxarifadoBeans id_item) {
        this.id_item = id_item;
    }

  /* public CategoriaAlmoxarif getId_categoria_item() {
        return id_categoria_item;
    }

    public void setId_categoria_item(CategoriaAlmoxarif id_categoria_item) {
        this.id_categoria_item = id_categoria_item;
    }*/

    public CategoriaEquipamentosBeans getId_categoria_aplic() {
        return id_categoria_aplic;
    }

    public void setId_categoria_aplic(CategoriaEquipamentosBeans id_categoria_aplic) {
        this.id_categoria_aplic = id_categoria_aplic;
    }

    public MarcaEquipamentosBeans getId_marca_aplic() {
        return id_marca_aplic;
    }

    public void setId_marca_aplic(MarcaEquipamentosBeans id_marca_aplic) {
        this.id_marca_aplic = id_marca_aplic;
    }

    public ModeloEquipamentosBeans getId_modelo_aplic() {
        return id_modelo_aplic;
    }

    public void setId_modelo_aplic(ModeloEquipamentosBeans id_modelo_aplic) {
        this.id_modelo_aplic = id_modelo_aplic;
    }
    
}
