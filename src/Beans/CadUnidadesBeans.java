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
@Table( name = "cad_unidades")
public class CadUnidadesBeans implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer ID;
    
    @Column(name = "id_categoria", nullable = true)
    private Integer ID_Categoria_Unid;
    
    @Column(name = "Descricao", nullable = false, length = 150)
    private String Descricao;
    
    @Column(name = "conversaoKg_L", nullable = false, precision = 4, scale = 9)
    private Double ConversaoKg_L;
    
    @Column(name = "Status", columnDefinition = "BIT", length = 1)
    private Boolean Status; 

    public CadUnidadesBeans(Integer ID, String Descricao) {
        this.ID = ID;
        this.Descricao = Descricao;
    }

    public CadUnidadesBeans() {
    }
    

    
    @Override
    public String toString(){
        return getDescricao();
    }
    
    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getID_Categoria_Unid() {
        return ID_Categoria_Unid;
    }

    public void setID_Categoria_Unid(Integer ID_Categoria_Unid) {
        this.ID_Categoria_Unid = ID_Categoria_Unid;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public Double getConversaoKg_L() {
        return ConversaoKg_L;
    }

    public void setConversaoKg_L(Double ConversaoKg_L) {
        this.ConversaoKg_L = ConversaoKg_L;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean Status) {
        this.Status = Status;
    }
    
    
}
