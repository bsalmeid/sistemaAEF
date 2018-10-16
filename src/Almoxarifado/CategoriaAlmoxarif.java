package Almoxarifado;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "cad_categoria_almoxarifado")
public class CategoriaAlmoxarif implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer ID;
    
    @Column(name = "descricao")
    private String Descricao;
    
    @Column(name = "status", columnDefinition = "BIT", length = 1)
    private Boolean status;
    
    public CategoriaAlmoxarif() {
    }

    public CategoriaAlmoxarif(Integer ID, String Descricao) {
        this.ID = ID;
        this.Descricao = Descricao;
    }

    @Override
    public String toString() {
        return getDescricao();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
