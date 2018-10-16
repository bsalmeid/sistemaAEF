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
public class ListRacaAnimalBeans {
    
    private Integer ID;
    private String Descricao;
    private String Abreviacao;

    @Override
    public String toString(){
        return getDescricao();
    }

    public String getAbreviacao() {
        return Abreviacao;
    }

    public void setAbreviacao(String Abreviacao) {
        this.Abreviacao = Abreviacao;
    }
    
    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }
    
    
    
}
