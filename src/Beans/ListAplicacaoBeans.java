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
public class ListAplicacaoBeans {
    
    private Integer ID;
    private Integer IDCultura;
    private String Aplicacao;

    @Override
    public String toString(){
        return getAplicacao();
    }
    
    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getIDCultura() {
        return IDCultura;
    }

    public void setIDCultura(Integer IDCultura) {
        this.IDCultura = IDCultura;
    }

    public String getAplicacao() {
        return Aplicacao;
    }

    public void setAplicacao(String Aplicacao) {
        this.Aplicacao = Aplicacao;
    }
    
    
    
}
