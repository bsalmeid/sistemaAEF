package Almoxarifado;

import Beans.FornecedoresBeans;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "cad_itens_almox_codigos")
public class CadItensAlmoxCodigos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer ID;
    
    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_item", referencedColumnName = "id", nullable = false)
    private CadItensAlmoxarifadoBeans id_item;
    
    @ManyToOne(optional = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "id_fornecedor", referencedColumnName = "cad_fornecedor_id", nullable = true)
    private FornecedoresBeans id_fornecedor;
    
   /* @ManyToOne(optional = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    private CategoriaAlmoxarif id_categoria;*/
    
    @Column(name = "codigo")
    private String Codigo;
    
    @Column(name = "codigo_catalogo", columnDefinition = "BIT", length = 1)
    private Boolean CodigoCatalogo;
    
    @Column(name = "cnpj_fornecedor")
    private String CNPJ_Fornecedor;
   
    @Column(name = "fornecedor")
    private String Fornecedor;
    
    @Transient
    private Integer ID_ITEM;
    
    @Transient
    private Integer ID_Fornecedor;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getID_ITEM() {
        return ID_ITEM;
    }

    public void setID_ITEM(Integer ID_ITEM) {
        this.ID_ITEM = ID_ITEM;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public Boolean getCodigoCatalogo() {
        return CodigoCatalogo;
    }

    public void setCodigoCatalogo(Boolean CodigoCatalogo) {
        this.CodigoCatalogo = CodigoCatalogo;
    }

    public Integer getID_Fornecedor() {
        return ID_Fornecedor;
    }

    public void setID_Fornecedor(Integer ID_Fornecedor) {
        this.ID_Fornecedor = ID_Fornecedor;
    }

    public String getCNPJ_Fornecedor() {
        return CNPJ_Fornecedor;
    }

    public void setCNPJ_Fornecedor(String CNPJ_Fornecedor) {
        this.CNPJ_Fornecedor = CNPJ_Fornecedor;
    }

    public String getFornecedor() {
        return Fornecedor;
    }

    public void setFornecedor(String Fornecedor) {
        this.Fornecedor = Fornecedor;
    }

    public CadItensAlmoxarifadoBeans getId_item() {
        return id_item;
    }

    public void setId_item(CadItensAlmoxarifadoBeans id_item) {
        this.id_item = id_item;
    }

    public FornecedoresBeans getId_fornecedor() {
        return id_fornecedor;
    }

    public void setId_fornecedor(FornecedoresBeans id_fornecedor) {
        this.id_fornecedor = id_fornecedor;
    }

  /*  public CategoriaAlmoxarif getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(CategoriaAlmoxarif id_categoria) {
        this.id_categoria = id_categoria;
    }*/
    
    

}
