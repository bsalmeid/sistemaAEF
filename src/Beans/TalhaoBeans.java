/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
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
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author agroa
 */
@Entity
@Table(name = "cad_talhao")
public class TalhaoBeans extends DefaultMutableTreeNode implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer ID;

    @ManyToOne
    @JoinColumn(name = "id_fazenda", referencedColumnName = "cad_fazendas_id", nullable = false)
    private PropriedadesBeans fazenda;
    
    @Transient // Variavel Necessária para utilizar as classes antigas sem Hibernate
    private Integer IdFazenda;

    @Column(name = "id_pluviometro", nullable = true)
    private Integer IdPluviometro;
    
    @Transient
    private String Pluviometro;
    
    @Column(name = "cultivo", nullable = false, length = 80)
    private String Cultivo;
    
    @Column(name = "tipo_cultura", nullable = false, length = 80)
    private Integer tipoCultura;
    
    @Column(name = "talhao", nullable = false, length = 80)
    private String Talhao;

    @Column(name = "mecanizavel" , columnDefinition = "BIT", length = 1 ,nullable = false)
    private boolean mecanizavel;
    
    @Column(name = "area", nullable = false, precision = 7, scale = 2)
    private Double Area;

    @Column(name = "status" , columnDefinition = "BIT", length = 1 ,nullable = false)
    private boolean status;
  
    @Transient
    private Boolean selected = true;

    @Transient
    public final static int DIG_IN_SELECTION = 4;

    @Transient
    protected int selectionMode;

    @Transient
    protected boolean isSelected;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.ID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TalhaoBeans other = (TalhaoBeans) obj;
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return getTalhao();
    }

    public String getPluviometro() {
        return Pluviometro;
    }

    public boolean isMecanizavel() {
        return mecanizavel;
    }

    public void setMecanizavel(boolean mecanizavel) {
        this.mecanizavel = mecanizavel;
    }
    
    public Integer getTipoCultura() {
        return tipoCultura;
    }

    public void setTipoCultura(Integer tipoCultura) {
        this.tipoCultura = tipoCultura;
    }    
    
    public void setPluviometro(String Pluviometro) {
        this.Pluviometro = Pluviometro;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public Integer getIdPluviometro() {
        return IdPluviometro;
    }

    public void setIdPluviometro(Integer IdPluviometro) {
        this.IdPluviometro = IdPluviometro;
    }

    public Integer getIdFazenda() {
        return IdFazenda;
    }

    public void setIdFazenda(Integer IdFazenda) {
        this.IdFazenda = IdFazenda;
    }

    public PropriedadesBeans getFazenda() {
        return fazenda;
    }

    public void setFazenda(PropriedadesBeans fazenda) {
        this.fazenda = fazenda;
    }
    
    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getTalhao() {
        return Talhao;
    }

    public void setTalhao(String Talhao) {
        this.Talhao = Talhao;
    }

    public Double getArea() {
        return Area;
    }

    public void setArea(Double Area) {
        this.Area = Area;
    }

    public int getSelectionMode() {
        return selectionMode;
    }

    public void setSelectionMode(int selectionMode) {
        this.selectionMode = selectionMode;
    }

    public boolean isIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public String getCultivo() {
        return Cultivo;
    }

    public void setCultivo(String Cultivo) {
        this.Cultivo = Cultivo;
    }

    
    
}
