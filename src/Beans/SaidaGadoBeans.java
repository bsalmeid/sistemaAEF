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
public class SaidaGadoBeans {
    
    private Integer idSaida;
    private String DataSaida;
    private String Motivo;
    private Integer nEscala;
    
    //Aba Nota Fiscal
    private String nNF;
    private Double ValorNF;
    private Integer qtNF;
    private String Destino;
    private String Transportadora;
    private String nMin;
    private String Origem;
    private String Categoria;
    private String Placa;
    private Integer qtMin;
    private Double Tara;
    private Double PesoB;
    private Double PesoL;
    private Double PesoM;
    private String Observacao;
    
    private Integer idSaidaGTA;
    private String nGTA;
    private String gtaOrigem;
    private String gtaDestino;
    private String dataGTA;
    private Integer m012;
    private Integer m1324;
    private Integer m2536;
    private Integer m36;
    private Integer mTotal;
    private Integer f012;
    private Integer f1324;
    private Integer f2536;
    private Integer f36;
    private Integer fTotal;

    // Pesquisa
    
    private String DataInicial;
    private String DataFinal;
    private String pOrigem;
    private String pDestino;
    private String pMotivo;
    private String pNEscala;
    private String pTrans;

    public String getDataInicial() {
        return DataInicial;
    }

    public void setDataInicial(String DataInicial) {
        this.DataInicial = DataInicial;
    }

    public String getDataFinal() {
        return DataFinal;
    }

    public void setDataFinal(String DataFinal) {
        this.DataFinal = DataFinal;
    }

    public String getpOrigem() {
        return pOrigem;
    }

    public void setpOrigem(String pOrigem) {
        this.pOrigem = pOrigem;
    }

    public String getpDestino() {
        return pDestino;
    }

    public void setpDestino(String pDestino) {
        this.pDestino = pDestino;
    }

    public String getpMotivo() {
        return pMotivo;
    }

    public void setpMotivo(String pMotivo) {
        this.pMotivo = pMotivo;
    }

    public String getpNEscala() {
        return pNEscala;
    }

    public void setpNEscala(String pNEscala) {
        this.pNEscala = pNEscala;
    }

    public String getpTrans() {
        return pTrans;
    }

    public void setpTrans(String pTrans) {
        this.pTrans = pTrans;
    }
    
    public String getObservacao() {
        return Observacao;
    }

    public void setObservacao(String Observacao) {
        this.Observacao = Observacao;
    }
    
    
    

    public Integer getIdSaidaGTA() {
        return idSaidaGTA;
    }

    public void setIdSaidaGTA(Integer idSaidaGTA) {
        this.idSaidaGTA = idSaidaGTA;
    }

    public String getnGTA() {
        return nGTA;
    }

    public void setnGTA(String nGTA) {
        this.nGTA = nGTA;
    }

    public String getGtaOrigem() {
        return gtaOrigem;
    }

    public void setGtaOrigem(String gtaOrigem) {
        this.gtaOrigem = gtaOrigem;
    }

    public String getGtaDestino() {
        return gtaDestino;
    }

    public void setGtaDestino(String gtaDestino) {
        this.gtaDestino = gtaDestino;
    }

    public String getDataGTA() {
        return dataGTA;
    }

    public void setDataGTA(String dataGTA) {
        this.dataGTA = dataGTA;
    }

    public Integer getM012() {
        return m012;
    }

    public void setM012(Integer m012) {
        this.m012 = m012;
    }

    public Integer getM1324() {
        return m1324;
    }

    public void setM1324(Integer m1324) {
        this.m1324 = m1324;
    }

    public Integer getM2536() {
        return m2536;
    }

    public void setM2536(Integer m2536) {
        this.m2536 = m2536;
    }

    public Integer getM36() {
        return m36;
    }

    public void setM36(Integer m36) {
        this.m36 = m36;
    }

    public Integer getmTotal() {
        return mTotal;
    }

    public void setmTotal(Integer mTotal) {
        this.mTotal = mTotal;
    }

    public Integer getF012() {
        return f012;
    }

    public void setF012(Integer f012) {
        this.f012 = f012;
    }

    public Integer getF1324() {
        return f1324;
    }

    public void setF1324(Integer f1324) {
        this.f1324 = f1324;
    }

    public Integer getF2536() {
        return f2536;
    }

    public void setF2536(Integer f2536) {
        this.f2536 = f2536;
    }

    public Integer getF36() {
        return f36;
    }

    public void setF36(Integer f36) {
        this.f36 = f36;
    }

    public Integer getfTotal() {
        return fTotal;
    }

    public void setfTotal(Integer fTotal) {
        this.fTotal = fTotal;
    }
    
    
    
    //

    public Integer getIdSaida() {
        return idSaida;
    }

    public void setIdSaida(Integer idSaida) {
        this.idSaida = idSaida;
    }

    public String getDataSaida() {
        return DataSaida;
    }

    public void setDataSaida(String DataSaida) {
        this.DataSaida = DataSaida;
    }

    public String getMotivo() {
        return Motivo;
    }

    public void setMotivo(String Motivo) {
        this.Motivo = Motivo;
    }

    public Integer getnEscala() {
        return nEscala;
    }

    public void setnEscala(Integer nEscala) {
        this.nEscala = nEscala;
    }

    public String getnNF() {
        return nNF;
    }

    public void setnNF(String nNF) {
        this.nNF = nNF;
    }

    public Double getValorNF() {
        return ValorNF;
    }

    public void setValorNF(Double ValorNF) {
        this.ValorNF = ValorNF;
    }

    public Integer getQtNF() {
        return qtNF;
    }

    public void setQtNF(Integer qtNF) {
        this.qtNF = qtNF;
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

    public String getnMin() {
        return nMin;
    }

    public void setnMin(String nMin) {
        this.nMin = nMin;
    }

    public String getOrigem() {
        return Origem;
    }

    public void setOrigem(String Origem) {
        this.Origem = Origem;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String Placa) {
        this.Placa = Placa;
    }

    public Integer getQtMin() {
        return qtMin;
    }

    public void setQtMin(Integer qtMin) {
        this.qtMin = qtMin;
    }



    public Double getTara() {
        return Tara;
    }

    public void setTara(Double Tara) {
        this.Tara = Tara;
    }

    public Double getPesoB() {
        return PesoB;
    }

    public void setPesoB(Double PesoB) {
        this.PesoB = PesoB;
    }

    public Double getPesoL() {
        return PesoL;
    }

    public void setPesoL(Double PesoL) {
        this.PesoL = PesoL;
    }

    public Double getPesoM() {
        return PesoM;
    }

    public void setPesoM(Double PesoM) {
        this.PesoM = PesoM;
    }
    
    
            
    
    
    
    
}
