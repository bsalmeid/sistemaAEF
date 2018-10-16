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
@Table(name = "cad_epocacultivo")
public class CultivoBeans extends DefaultMutableTreeNode implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer IDCultivo;
    
    @Column(name = "cultivo", unique = true, length = 80, nullable = false )
    private String Cultivo;
    
    @Column(name = "status", columnDefinition = "BIT", length = 1, nullable = false)
    private Boolean status;
    
    @Transient
    private List<CulturaBeans> listCultura;

    @Transient
    private Boolean selected = false;

    @Transient
    public final static int DIG_IN_SELECTION = 4;

    @Transient
    protected int selectionMode;

    @Transient
    protected boolean isSelected;

    
    public CultivoBeans() {
        listCultura = new ArrayList<>();
    }

    public CultivoBeans(Integer IDCultivo, String Cultivo) {
        this.IDCultivo = IDCultivo;
        this.Cultivo = Cultivo;
    }
    
    public CultivoBeans(Integer IDCultivo) {
        this.IDCultivo = IDCultivo;
    }

    public List<CulturaBeans> getListCultura() {
        return listCultura;
    }

    public void setListCultura(List<CulturaBeans> listCultura) {
        this.listCultura = listCultura;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
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

    @Override
    public String toString() {
        return getCultivo();
    }

    public Integer getIDCultivo() {
        return IDCultivo;
    }

    public void setIDCultivo(Integer IDCultivo) {
        this.IDCultivo = IDCultivo;
    }

    public String getCultivo() {
        return Cultivo;
    }

    public void setCultivo(String Cultivo) {
        this.Cultivo = Cultivo;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
        if ((selectionMode == DIG_IN_SELECTION)
                && (children != null)) {
            Enumeration menum = children.elements();
            while (menum.hasMoreElements()) {
                CulturaBeans node = (CulturaBeans) menum.nextElement();
                node.setSelected(isSelected);
            }
        }
    }

}
