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
public class ListUrgenciaPedidoMercadoria {
    
    private Integer id;
    private String descricao;
    private Long prazoEntrega;
    private Boolean status;

    @Override
    public String toString() {
        return  this.getDescricao();
    }

    
    
    public ListUrgenciaPedidoMercadoria(Integer id, String descricao, Long prazoEntrega, Boolean status) {
        this.id = id;
        this.descricao = descricao;
        this.prazoEntrega = prazoEntrega;
        this.status = status;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getPrazoEntrega() {
        return prazoEntrega;
    }

    public void setPrazoEntrega(Long prazoEntrega) {
        this.prazoEntrega = prazoEntrega;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
    
    
}
