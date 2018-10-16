/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;


public class ListEstadoCivil {

    private Integer Id;
    private String Descricao;
    private Boolean dadosConjuge;

    public ListEstadoCivil(Integer Id, String Descricao, Boolean dadosConjuge) {
        this.Id = Id;
        this.Descricao = Descricao;
        this.dadosConjuge = dadosConjuge;
    }

    
    @Override
    public String toString(){
        return getDescricao();
    }
    
    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public Boolean getDadosConjuge() {
        return dadosConjuge;
    }

    public void setDadosConjuge(Boolean dadosConjuge) {
        this.dadosConjuge = dadosConjuge;
    }
    
    
    
    
}
