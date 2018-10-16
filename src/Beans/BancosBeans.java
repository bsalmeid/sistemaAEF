/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cad_bancos")
public class BancosBeans implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cad_id", nullable = false)
    private Integer IdBanco;
    
    @Column(name = "cad_bancoNome", nullable = false, length = 150)
    private String NomeBanco;
    
    @Column(name = "cad_codCompe", nullable = false)
    private Integer CodCompe;
    
    @Column(name = "status", columnDefinition = "BIT", nullable = false)
    private Boolean Status;

    public BancosBeans() {
        
    }

    public BancosBeans(Integer IdBanco, String NomeBanco) {
        this.IdBanco = IdBanco;
        this.NomeBanco = NomeBanco;
    }
    
    @Override
    public String toString() {
        return getNomeBanco();
    }

    public Integer getCodCompe() {
        return CodCompe;
    }

    public void setCodCompe(Integer CodCompe) {
        this.CodCompe = CodCompe;
    }

    public Integer getIdBanco() {
        return IdBanco;
    }

    public void setIdBanco(Integer IdBanco) {
        this.IdBanco = IdBanco;
    }

    public String getNomeBanco() {
        return NomeBanco;
    }

    public void setNomeBanco(String NomeBanco) {
        this.NomeBanco = NomeBanco;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean Status) {
        this.Status = Status;
    }
    

}
