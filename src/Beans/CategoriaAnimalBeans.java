package Beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cad_categoria_animal")
public class CategoriaAnimalBeans implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer ID;

    @Column(name = "descricao", nullable = false, length = 45)
    private String Descricao;

    @Column(name = "sexo", nullable = false, length = 45)
    private String Sexo;

    @Column(name = "status", columnDefinition = "BIT", length = 1, nullable = false)
    private Boolean status;

    public CategoriaAnimalBeans() {
    }

    public CategoriaAnimalBeans(Integer ID, String Descricao) {
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

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
