/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author Bruno
 */
public class FreteGadoBeans {
    
    private String dtInicial;
    private String dtFinal;
    private String Transportadora;
    private String Placa;
    private String nMin;
    private String Destino;
    private String nCompra;
    private String Origem;
    private Integer StatusConferencia;
    private String StatusPagto;
    
    
    
    

    public Integer getStatusConferencia() {
        return StatusConferencia;
    }

    public void setStatusConferencia(Integer StatusConferencia) {
        this.StatusConferencia = StatusConferencia;
    }

    public String getStatusPagto() {
        return StatusPagto;
    }

    public void setStatusPagto(String StatusPagto) {
        this.StatusPagto = StatusPagto;
    }
    
    public String getOrigem() {
        return Origem;
    }

    public void setOrigem(String Origem) {
        this.Origem = Origem;
    }

    public FreteGadoBeans() {
    }

    public String getnCompra() {
        return nCompra;
    }

    public void setnCompra(String nCompra) {
        this.nCompra = nCompra;
    }



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

    public String getnMin() {
        return nMin;
    }

    public void setnMin(String nMin) {
        this.nMin = nMin;
    }

    public String getDestino() {
        return Destino;
    }

    public void setDestino(String Destino) {
        this.Destino = Destino;
    }

    
    
    
    
}
