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
public class PedidosBeans {
    
    Integer ID;
    String nPedido;
    String Safra;
    String dtEmissao;
    Integer idFornecedor;
    String Fornecedor;
    Integer idFazenda;
    String Fazenda;
    String Moeda;
    BigDecimal ValorTotal;
    String dtVencimento;
    String StatusPedido;
    BigDecimal ValorPago;
    BigDecimal valorNF;
    String Observacao;

    // Variaveis para SQL;
    
    String SQLnPedido;
    String SQLFornecedor;
    String SQLInsumo;
    String SQLSifra;
    String SQLIdCategoria;
    String SQLIdFazenda;

    public String getObservacao() {
        return Observacao;
    }

    public void setObservacao(String Observacao) {
        this.Observacao = Observacao;
    }
    
    public BigDecimal getValorPago() {
        return ValorPago;
    }

    public void setValorPago(BigDecimal ValorPago) {
        this.ValorPago = ValorPago;
    }

    public BigDecimal getValorNF() {
        return valorNF;
    }

    public void setValorNF(BigDecimal valorNF) {
        this.valorNF = valorNF;
    }

    public String getSQLnPedido() {
        return SQLnPedido;
    }

    public void setSQLnPedido(String SQLnPedido) {
        this.SQLnPedido = SQLnPedido;
    }

    public String getSQLFornecedor() {
        return SQLFornecedor;
    }

    public void setSQLFornecedor(String SQLFornecedor) {
        this.SQLFornecedor = SQLFornecedor;
    }

    public String getSQLInsumo() {
        return SQLInsumo;
    }

    public void setSQLInsumo(String SQLInsumo) {
        this.SQLInsumo = SQLInsumo;
    }

    public String getSQLSifra() {
        return SQLSifra;
    }

    public void setSQLSifra(String SQLSifra) {
        this.SQLSifra = SQLSifra;
    }

    public String getSQLIdCategoria() {
        return SQLIdCategoria;
    }

    public void setSQLIdCategoria(String SQLIdCategoria) {
        this.SQLIdCategoria = SQLIdCategoria;
    }

    public String getSQLIdFazenda() {
        return SQLIdFazenda;
    }

    public void setSQLIdFazenda(String SQLIdFazenda) {
        this.SQLIdFazenda = SQLIdFazenda;
    }
    
    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getnPedido() {
        return nPedido;
    }

    public void setnPedido(String nPedido) {
        this.nPedido = nPedido;
    }

    public String getSafra() {
        return Safra;
    }

    public void setSafra(String Safra) {
        this.Safra = Safra;
    }

    public String getDtEmissao() {
        return dtEmissao;
    }

    public void setDtEmissao(String dtEmissao) {
        this.dtEmissao = dtEmissao;
    }

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getFornecedor() {
        return Fornecedor;
    }

    public void setFornecedor(String Fornecedor) {
        this.Fornecedor = Fornecedor;
    }

    public Integer getIdFazenda() {
        return idFazenda;
    }

    public void setIdFazenda(Integer idFazenda) {
        this.idFazenda = idFazenda;
    }

    public String getFazenda() {
        return Fazenda;
    }

    public void setFazenda(String Fazenda) {
        this.Fazenda = Fazenda;
    }

    public String getMoeda() {
        return Moeda;
    }

    public void setMoeda(String Moeda) {
        this.Moeda = Moeda;
    }

    public BigDecimal getValorTotal() {
        return ValorTotal;
    }

    public void setValorTotal(BigDecimal ValorTotal) {
        this.ValorTotal = ValorTotal;
    }

    public String getDtVencimento() {
        return dtVencimento;
    }

    public void setDtVencimento(String dtVencimento) {
        this.dtVencimento = dtVencimento;
    }

    public String getStatusPedido() {
        return StatusPedido;
    }

    public void setStatusPedido(String StatusPedido) {
        this.StatusPedido = StatusPedido;
    }
    
    
    
}
