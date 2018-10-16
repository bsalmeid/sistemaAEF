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
@Table(name = "cad_moeda")
public class MoedaBeans implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cad_moeda_id", nullable = false)
    private Integer idMoeda;
    
    @Column(name = "cad_moeda_nome", nullable = false, unique = true, length = 40)
    private String Moeda;
    
    @Column(name = "cad_moeda_sifra", nullable = false, length = 30)
    private String Sifra;
    
    @Column(name = "cad_moeda_vlrConverteRS", nullable =true, scale = 9, precision = 2)
    private Double Conversao;

    @Column(name = "cad_moeda_status", nullable =false, columnDefinition = "BIT", length = 1)
    private Boolean status;

    public MoedaBeans() {
        
    }

    public MoedaBeans(Integer idMoeda) {
        this.idMoeda = idMoeda;
    }
    
    public MoedaBeans(Integer idMoeda, String Moeda) {
        this.idMoeda = idMoeda;
        this.Moeda = Moeda;
    }
    
    @Override
    public String toString(){
        return getMoeda();
    }
    
    public Integer getIdMoeda() {
        return idMoeda;
    }

    public void setIdMoeda(Integer idMoeda) {
        this.idMoeda = idMoeda;
    }

    public String getMoeda() {
        return Moeda;
    }

    public void setMoeda(String Moeda) {
        this.Moeda = Moeda;
    }

    public String getSifra() {
        return Sifra;
    }

    public void setSifra(String Sifra) {
        this.Sifra = Sifra;
    }

    public Double getConversao() {
        return Conversao;
    }

    public void setConversao(Double Conversao) {
        this.Conversao = Conversao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
    
}
