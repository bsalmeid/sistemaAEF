/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.swing.tree.DefaultMutableTreeNode;

@Entity
@Table(name = "cad_safra")
public class AnoSafra  extends DefaultMutableTreeNode implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cad_safra_id", nullable = false)
    private Integer idSafra;
    
    @Column(name = "cad_safra_ano", length = 80, nullable = false, unique = true)
    private String AnoSafra;
    
    @Column(name = "cad_safra_status", columnDefinition = "BIT", length = 1, nullable = false)
    private Boolean status;
    
    
    @Transient
    private List<CultivoBeans> listCultivos;
    
    @Transient
    private Boolean selected = false;
    
    @Transient
    public final static int DIG_IN_SELECTION = 4;
    
    @Transient
    protected int selectionMode;
   
    @Transient
    protected boolean isSelected;
    
    public AnoSafra() {
        listCultivos = new ArrayList<>();
    }

    public AnoSafra(Integer idSafra, String AnoSafra) {
        this.idSafra = idSafra;
        this.AnoSafra = AnoSafra;
    }

    @Override
    public String toString() {
        return getAnoSafra();
    }

    public Integer getIdSafra() {
        return idSafra;
    }

    public void setIdSafra(Integer idSafra) {
        this.idSafra = idSafra;
    }

    public String getAnoSafra() {
        return AnoSafra;
    }

    public void setAnoSafra(String AnoSafra) {
        this.AnoSafra = AnoSafra;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean Status) {
        this.status = Status;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public List<CultivoBeans> getListCultivos() {
        return listCultivos;
    }

    public void setListCultivos(List<CultivoBeans> listCultivos) {
        this.listCultivos = listCultivos;
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

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
        if ((selectionMode == DIG_IN_SELECTION)
                && (children != null)) {
            Enumeration menum = children.elements();
            while (menum.hasMoreElements()) {
                CultivoBeans node = (CultivoBeans) menum.nextElement();
                node.setSelected(isSelected);
            }
        }
    }

}
