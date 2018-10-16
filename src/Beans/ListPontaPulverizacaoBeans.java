/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author agroa
 */
public class ListPontaPulverizacaoBeans {
    
    private Integer ID;
    private String Ponta;
    private String Fabricante;

    
    
    @Override
    public String toString(){
        return getPonta();
    }
    
    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getPonta() {
        return Ponta;
    }

    public void setPonta(String Ponta) {
        this.Ponta = Ponta;
    }

    public String getFabricante() {
        return Fabricante;
    }

    public void setFabricante(String Fabricante) {
        this.Fabricante = Fabricante;
    }
    
    
    
}
