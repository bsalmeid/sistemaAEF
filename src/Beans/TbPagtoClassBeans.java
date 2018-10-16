/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.math.BigDecimal;

/**
 *
 * @author Bruno
 */
public class TbPagtoClassBeans {
    
    private int ID;
    private int IDPagto;
    private String Atividade;
    private String TipoDespesa;
    private int IdPlanoContas; 
    private int planoContas;
    private String DesConta;
    private String Fazenda;
    private int idFazenda;
    private String nFrota;
    private int IdAplicacao; // id do número de frota
    private BigDecimal ValorClas;
    private String descricao;

    public int getIdFazenda() {
        return idFazenda;
    }

    public void setIdFazenda(int idFazenda) {
        this.idFazenda = idFazenda;
    }

    public String getnFrota() {
        return nFrota;
    }

    public void setnFrota(String nFrota) {
        this.nFrota = nFrota;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDesConta() {
        return DesConta;
    }

    public void setDesConta(String DesConta) {
        this.DesConta = DesConta;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDPagto() {
        return IDPagto;
    }

    public void setIDPagto(int IDPagto) {
        this.IDPagto = IDPagto;
    }

    public String getAtividade() {
        return Atividade;
    }

    public void setAtividade(String Atividade) {
        this.Atividade = Atividade;
    }

    public String getTipoDespesa() {
        return TipoDespesa;
    }

    public void setTipoDespesa(String TipoDespesa) {
        this.TipoDespesa = TipoDespesa;
    }

    public int getPlanoContas() {
        return planoContas;
    }

    public void setPlanoContas(int planoContas) {
        this.planoContas = planoContas;
    }

    public int getIdPlanoContas() {
        return IdPlanoContas;
    }

    public void setIdPlanoContas(int IdPlanoContas) {
        this.IdPlanoContas = IdPlanoContas;
    }

    public String getFazenda() {
        return Fazenda;
    }

    public void setFazenda(String Fazenda) {
        this.Fazenda = Fazenda;
    }

    public int getIdAplicacao() {
        return IdAplicacao;
    }

    public void setIdAplicacao(int IdAplicacao) {
        this.IdAplicacao = IdAplicacao;
    }

    public BigDecimal getValorClas() {
        return ValorClas;
    }

    public void setValorClas(BigDecimal ValorClas) {
        this.ValorClas = ValorClas;
    }
    
    
    
    
}
