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

public class StatusItemPedido {
    
    private Integer ID;
    private String Descricao;
    private Boolean Status;

    @Override
    public String toString() {
        return getDescricao();
    }
    
    public StatusItemPedido(Integer ID, String Descricao, Boolean Status) {
        this.ID = ID;
        this.Descricao = Descricao;
        this.Status = Status;
    }

    public StatusItemPedido() {
    
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

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean Status) {
        this.Status = Status;
    }

    
    
    
    
}
