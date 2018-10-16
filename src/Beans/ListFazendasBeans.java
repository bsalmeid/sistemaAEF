/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Bruno
 */
public class ListFazendasBeans extends DefaultMutableTreeNode {

    private int ID;
    private String NomeFazenda;
    private List<TalhaoBeans> listTalhoes;
    private Boolean selected = false;
    public final static int DIG_IN_SELECTION = 4;
    protected int selectionMode;
    protected boolean isSelected;

    public ListFazendasBeans() {
        listTalhoes = new ArrayList<>();
    }

    public ListFazendasBeans(int ID, String NomeFazenda) {
        this.ID = ID;
        this.NomeFazenda = NomeFazenda;
    }
    
    @Override
    public String toString() {
        return getNomeFazenda();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNomeFazenda() {
        return NomeFazenda;
    }

    public void setNomeFazenda(String NomeFazenda) {
        this.NomeFazenda = NomeFazenda;
    }

    public List<TalhaoBeans> getListTalhoes() {
        return listTalhoes;
    }

    public void setListTalhoes(List<TalhaoBeans> listTalhoes) {
        this.listTalhoes = listTalhoes;
    }

    public int getSelectionMode() {
        return selectionMode;
    }

    public void setSelectionMode(int selectionMode) {
        this.selectionMode = selectionMode;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
        if ((selectionMode == DIG_IN_SELECTION)
                && (children != null)) {
            Enumeration menum = children.elements();
            while (menum.hasMoreElements()) {
                TalhaoBeans node = (TalhaoBeans) menum.nextElement();
                node.setSelected(isSelected);
            }
        }
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public boolean isIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

}
