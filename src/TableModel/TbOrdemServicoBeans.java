/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

public class TbOrdemServicoBeans {
    
   private Integer ID;
   private boolean Status;
   private String DataOrdem; 
   private String Safra;
   private String Cultivo;
   private String Cultura;
   private String Operacao;
   private String Aplicacao;
   private String Fazenda;
   private String Talhao;
   private Double AreaTalhao;
   private Double AreaPlanejada;
   private Double AreaAplic;

    public Double getAreaPlanejada() {
        return AreaPlanejada;
    }

    public void setAreaPlanejada(Double AreaPlanejada) {
        this.AreaPlanejada = AreaPlanejada;
    }
   
    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public String getDataOrdem() {
        return DataOrdem;
    }

    public void setDataOrdem(String DataOrdem) {
        this.DataOrdem = DataOrdem;
    }

    public String getSafra() {
        return Safra;
    }

    public void setSafra(String Safra) {
        this.Safra = Safra;
    }

    public String getCultivo() {
        return Cultivo;
    }

    public void setCultivo(String Cultivo) {
        this.Cultivo = Cultivo;
    }

    public String getCultura() {
        return Cultura;
    }

    public void setCultura(String Cultura) {
        this.Cultura = Cultura;
    }

    public String getOperacao() {
        return Operacao;
    }

    public void setOperacao(String Operacao) {
        this.Operacao = Operacao;
    }

    public String getAplicacao() {
        return Aplicacao;
    }

    public void setAplicacao(String Aplicacao) {
        this.Aplicacao = Aplicacao;
    }

    public String getFazenda() {
        return Fazenda;
    }

    public void setFazenda(String Fazenda) {
        this.Fazenda = Fazenda;
    }

    public String getTalhao() {
        return Talhao;
    }

    public void setTalhao(String Talhao) {
        this.Talhao = Talhao;
    }

    public Double getAreaTalhao() {
        return AreaTalhao;
    }

    public void setAreaTalhao(Double AreaTalhao) {
        this.AreaTalhao = AreaTalhao;
    }

    public Double getAreaAplic() {
        return AreaAplic;
    }

    public void setAreaAplic(Double AreaAplic) {
        this.AreaAplic = AreaAplic;
    }
   
    
    
}
