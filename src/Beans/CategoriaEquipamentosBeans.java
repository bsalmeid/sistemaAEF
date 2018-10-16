
package Beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "cad_categoria_equipamentos")
public class CategoriaEquipamentosBeans implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer ID;

    @Column(name = "descricao")
    private String Categoria;

    @Column(name = "status", columnDefinition = "BIT", length = 1, nullable = false)
    private Boolean Status;
    
    @OneToMany(mappedBy = "id_categoria", cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<ModeloEquipamentosBeans> listModelos;
    
    @OneToMany(mappedBy = "id_categoria", cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<InventarioBeans> listInventario;
    
    public CategoriaEquipamentosBeans() {
    }

    public CategoriaEquipamentosBeans(Integer ID, String Categoria) {
        this.ID = ID;
        this.Categoria = Categoria;
    }

    public List<InventarioBeans> getListInventario() {
        return listInventario;
    }

    public void setListInventario(List<InventarioBeans> listInventario) {
        this.listInventario = listInventario;
    }
    
    public List<ModeloEquipamentosBeans> getListModelos() {
        return listModelos;
    }

    public void setListModelos(List<ModeloEquipamentosBeans> listModelos) {
        this.listModelos = listModelos;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean Status) {
        this.Status = Status;
    }
    
    @Override
    public String toString() {
        return getCategoria();
    }

}
