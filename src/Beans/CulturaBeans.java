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
@Table(name = "cad_culturas")
public class CulturaBeans extends DefaultMutableTreeNode implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer IDCultura;
    
    @Column(name = "cultura", length = 80, unique = true, nullable = false)
    private String NomeCultura;
    
    @Column(name = "status", length = 1, columnDefinition = "BIT" , nullable = false)
    private Boolean status;
    
    @Transient
    List<ListFazendasBeans> listFazendas;
    
    @Transient
    private Boolean selected = false;
    
    @Transient
    public final static int DIG_IN_SELECTION = 4;
    
    @Transient
    protected int selectionMode;
    
    @Transient
    protected boolean isSelected;

    
    public CulturaBeans() {
        listFazendas = new ArrayList<>();
    }

    public CulturaBeans(Integer IDCultura, String NomeCultura) {
        this.IDCultura = IDCultura;
        this.NomeCultura = NomeCultura;
    }

    @Override
    public String toString() {
        return getNomeCultura();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
    public Integer getIDCultura() {
        return IDCultura;
    }

    public void setIDCultura(Integer IDCultura) {
        this.IDCultura = IDCultura;
    }

    public String getNomeCultura() {
        return NomeCultura;
    }

    public void setNomeCultura(String NomeCultura) {
        this.NomeCultura = NomeCultura;
    }

    public List<ListFazendasBeans> getListFazendas() {
        return listFazendas;
    }

    public void setListFazendas(List<ListFazendasBeans> listFazendas) {
        this.listFazendas = listFazendas;
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

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
        if ((selectionMode == DIG_IN_SELECTION)
                && (children != null)) {
            Enumeration menum = children.elements();
            while (menum.hasMoreElements()) {
                ListFazendasBeans node = (ListFazendasBeans) menum.nextElement();
                node.setSelected(isSelected);
            }
        }
    }

}
