/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;
public class OrdemServicoBeans {
    
    private Integer idOrdem;
    private String dtOrdem;
    private Integer idSafra;
    private Integer idCultivo;
    private Integer idCultura;
    private Integer idOperacao;
    private Integer idAplicacao;
    private String estadioFen;
    private Double Calda;
    private Integer idPonta;
    private String PontaPulverizacao;
    private Double Umidade;
    private Double Temperatura;
    private Double Vento;
    private String Responsavel;
    private Boolean Status;
    private String Observacao;
    private Boolean gerarBaixaEstoque;
    
    private Integer idLocalEstoque;

    public Integer getIdLocalEstoque() {
        return idLocalEstoque;
    }

    public void setIdLocalEstoque(Integer idLocalEstoque) {
        this.idLocalEstoque = idLocalEstoque;
    }
    
    

    public Integer getIdPonta() {
        return idPonta;
    }

    public void setIdPonta(Integer idPonta) {
        this.idPonta = idPonta;
    }

    public String getPontaPulverizacao() {
        return PontaPulverizacao;
    }

    public void setPontaPulverizacao(String PontaPulverizacao) {
        this.PontaPulverizacao = PontaPulverizacao;
    }
    
    public Boolean getGerarBaixaEstoque() {
        return gerarBaixaEstoque;
    }

    public void setGerarBaixaEstoque(Boolean gerarBaixaEstoque) {
        this.gerarBaixaEstoque = gerarBaixaEstoque;
    }
    
    public Double getCalda() {
        return Calda;
    }

    public void setCalda(Double Calda) {
        this.Calda = Calda;
    }

    public Double getUmidade() {
        return Umidade;
    }

    public void setUmidade(Double Umidade) {
        this.Umidade = Umidade;
    }

    public Double getTemperatura() {
        return Temperatura;
    }

    public void setTemperatura(Double Temperatura) {
        this.Temperatura = Temperatura;
    }

    public Double getVento() {
        return Vento;
    }

    public void setVento(Double Vento) {
        this.Vento = Vento;
    }

    public Integer getIdOrdem() {
        return idOrdem;
    }

    public void setIdOrdem(Integer idOrdem) {
        this.idOrdem = idOrdem;
    }

    public String getDtOrdem() {
        return dtOrdem;
    }

    public void setDtOrdem(String dtOrdem) {
        this.dtOrdem = dtOrdem;
    }

    public Integer getIdSafra() {
        return idSafra;
    }

    public void setIdSafra(Integer idSafra) {
        this.idSafra = idSafra;
    }

    public Integer getIdCultivo() {
        return idCultivo;
    }

    public void setIdCultivo(Integer idCultivo) {
        this.idCultivo = idCultivo;
    }

    public Integer getIdCultura() {
        return idCultura;
    }

    public void setIdCultura(Integer idCultura) {
        this.idCultura = idCultura;
    }

    public Integer getIdOperacao() {
        return idOperacao;
    }

    public void setIdOperacao(Integer idOperacao) {
        this.idOperacao = idOperacao;
    }

    public Integer getIdAplicacao() {
        return idAplicacao;
    }

    public void setIdAplicacao(Integer idAplicacao) {
        this.idAplicacao = idAplicacao;
    }

    public String getEstadioFen() {
        return estadioFen;
    }

    public void setEstadioFen(String estadioFen) {
        this.estadioFen = estadioFen;
    }

    public String getResponsavel() {
        return Responsavel;
    }

    public void setResponsavel(String Responsavel) {
        this.Responsavel = Responsavel;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean Status) {
        this.Status = Status;
    }

    public String getObservacao() {
        return Observacao;
    }

    public void setObservacao(String Observacao) {
        this.Observacao = Observacao;
    }
    
    
}
