package Almoxarifado;

import Beans.ListFazendasBeans;
import Beans.PropriedadesBeans;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "cad_almoxarifado")
public class CadAlmoxarifadoBeans implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer ID;
            
    @ManyToOne(optional = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "id_fazenda")
    private PropriedadesBeans id_fazenda;
    
    @Column(name = "descricao")
    private String Descricao;
    
    @Column(name = "status", columnDefinition = "BIT", length = 1)
    private Boolean Status;
    
    @Transient
    private Integer ID_Fazenda;

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

    public Integer getID_Fazenda() {
        return ID_Fazenda;
    }

    public void setID_Fazenda(Integer ID_Fazenda) {
        this.ID_Fazenda = ID_Fazenda;
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

    public PropriedadesBeans getId_fazenda() {
        return id_fazenda;
    }

    public void setId_fazenda(PropriedadesBeans id_fazenda) {
        this.id_fazenda = id_fazenda;
    }

    
    
}
