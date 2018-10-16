/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.util.List;

/**
 *
 * @author agroa
 */
public class ListPluviometroBeans {

    private Integer ID;
    private Integer IdFazenda;
    private String Descricao;
    private List<TalhaoBeans> listTalhao;

    @Override
    public String toString() {
        return getDescricao();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getIdFazenda() {
        return IdFazenda;
    }

    public void setIdFazenda(Integer IdFazenda) {
        this.IdFazenda = IdFazenda;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

}
