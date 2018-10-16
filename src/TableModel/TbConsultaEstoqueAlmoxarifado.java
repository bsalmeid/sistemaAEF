/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

/**
 *
 * @author agroa
 */
public class TbConsultaEstoqueAlmoxarifado {

    private Integer ID;
    private String Codigo;
    private String Descricao;
    private String Unidade;
    private Double EntradaIntervalo;
    private Double SaidaIntervalo;
    private Double Estoque;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public String getUnidade() {
        return Unidade;
    }

    public void setUnidade(String Unidade) {
        this.Unidade = Unidade;
    }

    public Double getEntradaIntervalo() {
        return EntradaIntervalo;
    }

    public void setEntradaIntervalo(Double EntradaIntervalo) {
        this.EntradaIntervalo = EntradaIntervalo;
    }
    
    public Double getSaidaIntervalo() {
        return SaidaIntervalo;
    }

    public void setSaidaIntervalo(Double SaidaIntervalo) {
        this.SaidaIntervalo = SaidaIntervalo;
    }

    public Double getEstoque() {
        return Estoque;
    }

    public void setEstoque(Double Estoque) {
        this.Estoque = Estoque;
    }

    
}
