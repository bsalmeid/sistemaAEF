package Beans;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "cad_cargos_salario")
public class CargosBeans implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer Id;
    
    @Column(name = "descricao")
    private String Descricao;
    
    @Column(name = "salario", precision = 2, scale = 7)
    private Double Salario;
    
    @Column(name = "status", columnDefinition = "BIT", length = 1)
    private Boolean Status;

    public CargosBeans() {
    }

    public CargosBeans(Integer Id, String Descricao, Double Salario) {
        this.Id = Id;
        this.Descricao = Descricao;
        this.Salario = Salario;
    }
    
    
    @Override
    public String toString(){
        return Descricao;
    }
    
    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public Double getSalario() {
        return Salario;
    }

    public void setSalario(Double Salario) {
        this.Salario = Salario;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean Status) {
        this.Status = Status;
    }
    
}
