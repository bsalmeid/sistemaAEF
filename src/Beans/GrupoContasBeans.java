package Beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "cad_grupo_contas")
public class GrupoContasBeans implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer ID;

    @Column(name = "descricao", unique = true, length = 120, nullable = false)
    private String descricao;

    @Column(name = "entrada", columnDefinition = "BIT", length = 1, nullable = false)
    private Boolean entrada;

    @Column(name = "saida", columnDefinition = "BIT", length = 1, nullable = false)
    private Boolean saida;
   
    @Column(name = "transferencia", columnDefinition = "BIT", length = 1, nullable = false)
    private Boolean transferencia;
    
    @Column(name = "status", columnDefinition = "BIT", length = 1, nullable = false)
    private Boolean status;
    
    public GrupoContasBeans() {}

    public GrupoContasBeans(Integer ID, String descricao) {
        this.ID = ID;
        this.descricao = descricao;
    }
    
    public GrupoContasBeans(Integer ID, String descricao, Boolean status) {
        this.ID = ID;
        this.descricao = descricao;
        this.status = status;
    }
    
    @Override
    public String toString() {
        return  descricao;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }    

    public Boolean getEntrada() {
        return entrada;
    }

    public void setEntrada(Boolean entrada) {
        this.entrada = entrada;
    }

    public Boolean getSaida() {
        return saida;
    }

    public void setSaida(Boolean saida) {
        this.saida = saida;
    }

    public Boolean getTransferencia() {
        return transferencia;
    }

    public void setTransferencia(Boolean transferencia) {
        this.transferencia = transferencia;
    }

    
}
