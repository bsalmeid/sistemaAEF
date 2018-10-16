

package Beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cad_plano_conta")
public class PlanoContaBeans implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pc_id", nullable = false)
    private Integer ID;
    
    @Column(name = "pc_conta", nullable = false, unique = true)
    private Integer Conta;
    
    @Column(name = "pc_descricao", nullable = false, length = 80)
    private String Descricao;
     
    @Column(name = "pc_tipo", nullable = false, length = 15)
    private String Tipo;
    
    @Column(name = "pc_status", nullable = false, columnDefinition = "BIT", length = 1)
    private Boolean status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_grupo", referencedColumnName = "id", nullable = true)
    private GrupoContasBeans grupoConta;
    
    
    public PlanoContaBeans() {
    
    }

    public PlanoContaBeans(Integer ID, String Descricao) {
        this.ID = ID;
        this.Descricao = Descricao;
    }
   
    public PlanoContaBeans(Integer ID, Integer Conta, String Descricao) {
        this.ID = ID;
        this.Conta = Conta;
        this.Descricao = Descricao;
    }

    @Override
    public String toString() {
        return Conta + " - " + Descricao;
    }
    
    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getConta() {
        return Conta;
    }

    public void setConta(Integer Conta) {
        this.Conta = Conta;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public GrupoContasBeans getGrupoConta() {
        return grupoConta;
    }

    public void setGrupoConta(GrupoContasBeans grupoConta) {
        this.grupoConta = grupoConta;
    }
   
    
}
