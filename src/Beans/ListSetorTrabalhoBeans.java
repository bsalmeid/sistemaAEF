
package Beans;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "cad_setor_servico")
public class ListSetorTrabalhoBeans implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer ID;
    
    @Column(name = "descricao")
    private String Descricao;
    
    @Column(name = "status", columnDefinition = "BIT", length = 1)
    private Boolean Status;

    public ListSetorTrabalhoBeans(Integer ID, String Descricao) {
        this.ID = ID;
        this.Descricao = Descricao;
    }

    public ListSetorTrabalhoBeans() {
    }
    
    @Override
    public String toString(){
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
        return Status;
    }

    public void setStatus(Boolean Status) {
        this.Status = Status;
    }
      
}
