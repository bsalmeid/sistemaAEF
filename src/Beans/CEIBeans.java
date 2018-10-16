
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.*;

@Entity
@Table(name = "cad_cei")
public class CEIBeans implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer idCEI;
    
    @Column(name = "cei", columnDefinition = "BIGINT(20) DEFAULT 0" )
    private BigInteger CEI;
    
    @Column(name = "descricao")
    private String Descricao;
    
    @Column(name = "status", columnDefinition = "BIT", length = 1)
    private Boolean Status;

    public CEIBeans() {
    
    }
    
    public CEIBeans(Integer idCEI, String Descricao) {
        this.idCEI = idCEI;
        this.Descricao = Descricao;
    }
   
    @Override
    public String toString(){
        return getDescricao();
    }
    
    public Integer getIdCEI() {
        return idCEI;
    }

    public void setIdCEI(Integer idCEI) {
        this.idCEI = idCEI;
    }

    public BigInteger getCEI() {
        return CEI;
    }

    public void setCEI(BigInteger CEI) {
        this.CEI = CEI;
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


