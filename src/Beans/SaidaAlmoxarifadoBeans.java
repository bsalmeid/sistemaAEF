/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import TableModel.TbSaidaAlmoxarifadoBeans;
import java.util.List;

/**
 *
 * @author agroa
 */
public class SaidaAlmoxarifadoBeans {

    private Integer ID;
    private String Data;
    private Integer IdAlmoxarifado;
    private Integer IdOperacao;
    private Integer IdFazenda;
    private Integer NDoc;
    private String Responsavel;
    private List<TbSaidaAlmoxarifadoBeans> Produtos;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    public Integer getIdAlmoxarifado() {
        return IdAlmoxarifado;
    }

    public void setIdAlmoxarifado(Integer IdAlmoxarifado) {
        this.IdAlmoxarifado = IdAlmoxarifado;
    }

    public Integer getIdOperacao() {
        return IdOperacao;
    }

    public void setIdOperacao(Integer IdOperacao) {
        this.IdOperacao = IdOperacao;
    }

    public Integer getIdFazenda() {
        return IdFazenda;
    }

    public void setIdFazenda(Integer IdFazenda) {
        this.IdFazenda = IdFazenda;
    }

    public Integer getNDoc() {
        return NDoc;
    }

    public void setNDoc(Integer NDoc) {
        this.NDoc = NDoc;
    }

    public String getResponsavel() {
        return Responsavel;
    }

    public void setResponsavel(String Responsavel) {
        this.Responsavel = Responsavel;
    }

    public List<TbSaidaAlmoxarifadoBeans> getProdutos() {
        return Produtos;
    }

    public void setProdutos(List<TbSaidaAlmoxarifadoBeans> Produtos) {
        this.Produtos = Produtos;
    }
    
    
}
