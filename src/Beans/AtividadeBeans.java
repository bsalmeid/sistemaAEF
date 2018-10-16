/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cad_atividade")
public class AtividadeBeans implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer ID;
    
    @Column(name = "descricao", unique = true, length = 120, nullable = false)
    private String descricao;
    
    @Column(name = "status", columnDefinition = "BIT", length = 1, nullable = false)
    private Boolean status;
    
    @OneToMany(mappedBy = "Atividade", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<CentroDeResultado> centroDeResultados;

    
    public AtividadeBeans() {
    
    }

    public AtividadeBeans(Integer ID, String descricao, Boolean status) {
        this.ID = ID;
        this.descricao = descricao;
        this.status = status;
    }

    public AtividadeBeans(Integer ID, String descricao) {
        this.ID = ID;
        this.descricao = descricao;
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

    public List<CentroDeResultado> getCentroDeResultados() {
        return centroDeResultados;
    }

    public void setCentroDeResultados(List<CentroDeResultado> centroDeResultados) {
        this.centroDeResultados = centroDeResultados;
    }
    
    
   
}
