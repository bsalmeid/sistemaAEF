/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TreeModel;

import java.util.List;


public class TmOrdemServicoBeans {
 
    private Integer IdOs;
    private Integer idOPeracao;
    private Integer Operacao;
    private Integer idAplicacao;
    private String Aplicacao;
    private List<TmOSInsumosBeans> listInsumos;

    public Integer getIdOs() {
        return IdOs;
    }

    public void setIdOs(Integer IdOs) {
        this.IdOs = IdOs;
    }

    public Integer getIdOPeracao() {
        return idOPeracao;
    }

    public void setIdOPeracao(Integer idOPeracao) {
        this.idOPeracao = idOPeracao;
    }

    public Integer getOperacao() {
        return Operacao;
    }

    public void setOperacao(Integer Operacao) {
        this.Operacao = Operacao;
    }

    public Integer getIdAplicacao() {
        return idAplicacao;
    }

    public void setIdAplicacao(Integer idAplicacao) {
        this.idAplicacao = idAplicacao;
    }

    public String getAplicacao() {
        return Aplicacao;
    }

    public void setAplicacao(String Aplicacao) {
        this.Aplicacao = Aplicacao;
    }

    public List<TmOSInsumosBeans> getListInsumos() {
        return listInsumos;
    }

    public void setListInsumos(List<TmOSInsumosBeans> listInsumos) {
        this.listInsumos = listInsumos;
    }
    
    
    
    
            
            
    
    
    
}
