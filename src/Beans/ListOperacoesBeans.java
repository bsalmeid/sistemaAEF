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
public class ListOperacoesBeans {
    
    private Integer ID;
    private Integer idCultura;
    private String Nome;
    private boolean usoAplicacoes;
    private boolean usoInsumos;
    private boolean usoFClimaticos;

    @Override
    public String toString(){
        return getNome();
    }

    public boolean isUsoAplicacoes() {
        return usoAplicacoes;
    }

    public void setUsoAplicacoes(boolean usoAplicacoes) {
        this.usoAplicacoes = usoAplicacoes;
    }
    
    
    
    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getIdCultura() {
        return idCultura;
    }

    public void setIdCultura(Integer idCultura) {
        this.idCultura = idCultura;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public boolean isUsoInsumos() {
        return usoInsumos;
    }

    public void setUsoInsumos(boolean usoInsumos) {
        this.usoInsumos = usoInsumos;
    }

    public boolean isUsoFClimaticos() {
        return usoFClimaticos;
    }

    public void setUsoFClimaticos(boolean usoFClimaticos) {
        this.usoFClimaticos = usoFClimaticos;
    }
    
    
    
}
