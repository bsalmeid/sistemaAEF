

package Beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name = "cad_marca_equipamentos")
public class MarcaEquipamentosBeans implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer ID;

    @Column(name = "marca")
    private String Marca;

    @Column(name = "status", columnDefinition = "BIT", length = 1, nullable = false)
    private Boolean Status;

    @OneToMany(mappedBy = "id_marca", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ModeloEquipamentosBeans> listModelos;

    @OneToMany(mappedBy = "id_marca", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<InventarioBeans> listInventario;

    public MarcaEquipamentosBeans() {
    }

    public MarcaEquipamentosBeans(Integer ID, String Marca) {
        this.ID = ID;
        this.Marca = Marca;
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

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return getMarca();
    }

    
}
