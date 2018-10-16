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
public class EntradaInsumosBeans {
    Integer ID;
    String dtEntrada;
    String Operacao;
    Integer idItemPedInsumo;
    Integer idPedido;
    String nPedido;
    Integer idFornecedor;
    String Fornecedor;
    String TipoDoc;
    Integer nDoc;
    String dtEmissao;
    BigDecimal VlrTotal;
    Integer idFazenda;
    String Fazenda;
    String TipoFrete;
    Integer nCTE;
    String Placa;
    Double pesoNF;
    Double pesoBruto;
    Double tara;
    Double pesoLiq;
    Double QtEntrega;
    Double QtSaldo;

    private String dtInicial;
    private String dtFinal;
    private Integer idFazendaPesq; 
    private String SQLPesqFornecedor;
    private String SQLPesqProduto;

    public String getDtInicial() {
        return dtInicial;
    }

    public void setDtInicial(String dtInicial) {
        this.dtInicial = dtInicial;
    }

    public String getDtFinal() {
        return dtFinal;
    }

    public void setDtFinal(String dtFinal) {
        this.dtFinal = dtFinal;
    }

    public Integer getIdFazendaPesq() {
        return idFazendaPesq;
    }

    public void setIdFazendaPesq(Integer idFazendaPesq) {
        this.idFazendaPesq = idFazendaPesq;
    }

    public String getSQLPesqFornecedor() {
        return SQLPesqFornecedor;
    }

    public void setSQLPesqFornecedor(String SQLPesqFornecedor) {
        this.SQLPesqFornecedor = SQLPesqFornecedor;
    }

    public String getSQLPesqProduto() {
        return SQLPesqProduto;
    }

    public void setSQLPesqProduto(String SQLPesqProduto) {
        this.SQLPesqProduto = SQLPesqProduto;
    }
    
    
    
    public Double getQtEntrega() {
        return QtEntrega;
    }

    public void setQtEntrega(Double QtEntrega) {
        this.QtEntrega = QtEntrega;
    }

    public Double getQtSaldo() {
        return QtSaldo;
    }

    public void setQtSaldo(Double QtSaldo) {
        this.QtSaldo = QtSaldo;
    }
    
    

    public Integer getIdItemPedInsumo() {
        return idItemPedInsumo;
    }

    public void setIdItemPedInsumo(Integer idItemPedInsumo) {
        this.idItemPedInsumo = idItemPedInsumo;
    }
    
    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }
    
    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getDtEntrada() {
        return dtEntrada;
    }

    public void setDtEntrada(String dtEntrada) {
        this.dtEntrada = dtEntrada;
    }

    public String getOperacao() {
        return Operacao;
    }

    public void setOperacao(String Operacao) {
        this.Operacao = Operacao;
    }

    public String getnPedido() {
        return nPedido;
    }

    public void setnPedido(String nPedido) {
        this.nPedido = nPedido;
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

    public String getTipoDoc() {
        return TipoDoc;
    }

    public void setTipoDoc(String TipoDoc) {
        this.TipoDoc = TipoDoc;
    }

    public Integer getnDoc() {
        return nDoc;
    }

    public void setnDoc(Integer nDoc) {
        this.nDoc = nDoc;
    }

    public String getDtEmissao() {
        return dtEmissao;
    }

    public void setDtEmissao(String dtEmissao) {
        this.dtEmissao = dtEmissao;
    }

    public BigDecimal getVlrTotal() {
        return VlrTotal;
    }

    public void setVlrTotal(BigDecimal VlrTotal) {
        this.VlrTotal = VlrTotal;
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

    public String getTipoFrete() {
        return TipoFrete;
    }

    public void setTipoFrete(String TipoFrete) {
        this.TipoFrete = TipoFrete;
    }

    public Integer getnCTE() {
        return nCTE;
    }

    public void setnCTE(Integer nCTE) {
        this.nCTE = nCTE;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String Placa) {
        this.Placa = Placa;
    }

    public Double getPesoNF() {
        return pesoNF;
    }

    public void setPesoNF(Double pesoNF) {
        this.pesoNF = pesoNF;
    }

    public Double getPesoBruto() {
        return pesoBruto;
    }

    public void setPesoBruto(Double pesoBruto) {
        this.pesoBruto = pesoBruto;
    }

    public Double getTara() {
        return tara;
    }

    public void setTara(Double tara) {
        this.tara = tara;
    }

    public Double getPesoLiq() {
        return pesoLiq;
    }

    public void setPesoLiq(Double pesoLiq) {
        this.pesoLiq = pesoLiq;
    }
    
    
    
}
