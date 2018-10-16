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
public class TbFreteBeans {
    
    private int ID;
    private int nRomaneio;
    private int nCompra;
    private String Comprador;
    private String dtEntrada;
    private String OrigemGTA;
    private String Destino;
    private String Transportadora;
    private String Placa;
    private String nMIN;
    private int qMin;
    private int qOrigem;
    private int KM;
    private BigDecimal VlrKM;
    private BigDecimal VlrFrete;
    private boolean StatusAtual;
    private boolean StatusDB;
    private String SituacaoPagto;
    private String dtPagto;

    public String getSituacaoPagto() {
        return SituacaoPagto;
    }

    public void setSituacaoPagto(String SituacaoPagto) {
        this.SituacaoPagto = SituacaoPagto;
    }

    
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getnRomaneio() {
        return nRomaneio;
    }

    public void setnRomaneio(int nRomaneio) {
        this.nRomaneio = nRomaneio;
    }

    public int getnCompra() {
        return nCompra;
    }

    public void setnCompra(int nCompra) {
        this.nCompra = nCompra;
    }

    public String getComprador() {
        return Comprador;
    }

    public void setComprador(String Comprador) {
        this.Comprador = Comprador;
    }

    public String getDtEntrada() {
        return dtEntrada;
    }

    public void setDtEntrada(String dtEntrada) {
        this.dtEntrada = dtEntrada;
    }

    public String getOrigemGTA() {
        return OrigemGTA;
    }

    public void setOrigemGTA(String OrigemGTA) {
        this.OrigemGTA = OrigemGTA;
    }

    public String getDestino() {
        return Destino;
    }

    public void setDestino(String Destino) {
        this.Destino = Destino;
    }

    public String getTransportadora() {
        return Transportadora;
    }

    public void setTransportadora(String Transportadora) {
        this.Transportadora = Transportadora;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String Placa) {
        this.Placa = Placa;
    }

    public String getnMIN() {
        return nMIN;
    }

    public void setnMIN(String nMIN) {
        this.nMIN = nMIN;
    }

    public int getqMin() {
        return qMin;
    }

    public void setqMin(int qMin) {
        this.qMin = qMin;
    }

    public int getqOrigem() {
        return qOrigem;
    }

    public void setqOrigem(int qOrigem) {
        this.qOrigem = qOrigem;
    }

    public int getKM() {
        return KM;
    }

    public void setKM(int KM) {
        this.KM = KM;
    }

    public BigDecimal getVlrKM() {
        return VlrKM;
    }

    public void setVlrKM(BigDecimal VlrKM) {
        this.VlrKM = VlrKM;
    }

    public BigDecimal getVlrFrete() {
        return VlrFrete;
    }

    public void setVlrFrete(BigDecimal VlrFrete) {
        this.VlrFrete = VlrFrete;
    }

    public boolean isStatusAtual() {
        return StatusAtual;
    }

    public void setStatusAtual(boolean StatusAtual) {
        this.StatusAtual = StatusAtual;
    }

    public boolean isStatusDB() {
        return StatusDB;
    }

    public void setStatusDB(boolean StatusDB) {
        this.StatusDB = StatusDB;
    }

    public String getDtPagto() {
        return dtPagto;
    }

    public void setDtPagto(String dtPagto) {
        this.dtPagto = dtPagto;
    }
    
    
    
    



    
}
