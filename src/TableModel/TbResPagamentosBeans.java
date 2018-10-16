/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import java.math.BigDecimal;

/**
 *
 * @author Bruno
 */
public class TbResPagamentosBeans {
    
    private int ID;
    private int IDPagto;
    private String contaOrigem;
    private String dtPrevista;
    private String dtPagamento;
    private String titular;
    private String formaPagto;
    private int nDoc;
    private String bancoDestino;
    private String Agencia;
    private String contaCorrente;
    private String moeda;
    private BigDecimal ValorEmMoeda;
    private BigDecimal taxa;
    private BigDecimal ValorPrevisto;
    private BigDecimal ValorPagto;
    private BigDecimal ValorFiscal;
    private BigDecimal ValorClassificado;
    private boolean Status;

    
    public BigDecimal getValorEmMoeda() {
        return ValorEmMoeda;
    }

    public void setValorEmMoeda(BigDecimal ValorEmMoeda) {
        this.ValorEmMoeda = ValorEmMoeda;
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

    public String getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(String contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public String getDtPrevista() {
        return dtPrevista;
    }

    public void setDtPrevista(String dtPrevista) {
        this.dtPrevista = dtPrevista;
    }

    public String getDtPagamento() {
        return dtPagamento;
    }

    public void setDtPagamento(String dtPagamento) {
        this.dtPagamento = dtPagamento;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getFormaPagto() {
        return formaPagto;
    }

    public void setFormaPagto(String formaPagto) {
        this.formaPagto = formaPagto;
    }

    public int getnDoc() {
        return nDoc;
    }

    public void setnDoc(int nDoc) {
        this.nDoc = nDoc;
    }

    public String getBancoDestino() {
        return bancoDestino;
    }

    public void setBancoDestino(String bancoDestino) {
        this.bancoDestino = bancoDestino;
    }

    public String getAgencia() {
        return Agencia;
    }

    public void setAgencia(String Agencia) {
        this.Agencia = Agencia;
    }

    public String getContaCorrente() {
        return contaCorrente;
    }

    public void setContaCorrente(String contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public BigDecimal getTaxa() {
        return taxa;
    }

    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
    }

    public BigDecimal getValorPrevisto() {
        return ValorPrevisto;
    }

    public void setValorPrevisto(BigDecimal ValorPrevisto) {
        this.ValorPrevisto = ValorPrevisto;
    }

    public BigDecimal getValorPagto() {
        return ValorPagto;
    }

    public void setValorPagto(BigDecimal ValorPagto) {
        this.ValorPagto = ValorPagto;
    }

    public BigDecimal getValorFiscal() {
        return ValorFiscal;
    }

    public void setValorFiscal(BigDecimal ValorFiscal) {
        this.ValorFiscal = ValorFiscal;
    }

    public BigDecimal getValorClassificado() {
        return ValorClassificado;
    }

    public void setValorClassificado(BigDecimal ValorClassificado) {
        this.ValorClassificado = ValorClassificado;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }
    
         
            
            
    
    
    
    
    
}
