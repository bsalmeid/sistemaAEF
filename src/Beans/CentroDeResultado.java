/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.Transient;

@Entity
@Table(name = "cad_centro_resultado")
public class CentroDeResultado implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long ID;
    
    @Transient
    private String descricao;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_atividade", nullable = false, referencedColumnName = "id")
    private AtividadeBeans Atividade;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_safra", nullable = false, referencedColumnName = "cad_safra_id")
    private AnoSafra anoSafra;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cultivo", nullable = false, referencedColumnName = "id")
    private CultivoBeans Cultivo;
        
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cultura", nullable = false, referencedColumnName = "id")
    private CulturaBeans Cultura;
    
    @Column(name = "status", length = 1, columnDefinition = "BIT" , nullable = false)
    private Boolean status;

    public CentroDeResultado() {
    }

    public CentroDeResultado(Long ID, String descricao) {
        this.ID = ID;
        this.descricao = descricao;
    }
    
    @Override
    public String toString() {
        try {
            return Cultura.getNomeCultura() + " - " + Cultivo.getCultivo() + " - " + anoSafra.getAnoSafra();
        } catch (Exception e) {
           return descricao;
        }
    } 
    
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public AtividadeBeans getAtividade() {
        return Atividade;
    }

    public void setAtividade(AtividadeBeans Atividade) {
        this.Atividade = Atividade;
    }

    public AnoSafra getAnoSafra() {
        return anoSafra;
    }

    public void setAnoSafra(AnoSafra AnoSafra) {
        this.anoSafra = AnoSafra;
    }

    public CultivoBeans getCultivo() {
        return Cultivo;
    }

    public void setCultivo(CultivoBeans Cultivo) {
        this.Cultivo = Cultivo;
    }

    public CulturaBeans getCultura() {
        return Cultura;
    }

    public void setCultura(CulturaBeans Cultura) {
        this.Cultura = Cultura;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDescricao() {
        return toString();
    }

    public void setDescricao(String descricao) {
        this.descricao = toString();
    }
    
    
        
   
    
    
    
}
