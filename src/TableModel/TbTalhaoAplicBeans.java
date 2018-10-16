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
public class TbTalhaoAplicBeans {
    
    private int IdDB;
    private int IdTalha;
    private String nTalhao;
    private int IdFazenda;
    private String DataInicio;
    private String DataFinal;
    private Double AreaTalhao;
    private Double PorcentAplic;
    private Double AreaPlanejada;
    private Double AreaAplicTotal;
    private Double AreaAplic;
    private Double HMTrabalhada;

    public Double getAreaAplicTotal() {
        return AreaAplicTotal;
    }

    public void setAreaAplicTotal(Double AreaAplicTotal) {
        this.AreaAplicTotal = AreaAplicTotal;
    }    
    
    public Double getAreaPlanejada() {
        return AreaPlanejada;
    }

    public void setAreaPlanejada(Double AreaPlanejada) {
        this.AreaPlanejada = AreaPlanejada;
    }
    
    public Double getHMTrabalhada() {
        return HMTrabalhada;
    }

    public void setHMTrabalhada(Double HMTrabalhada) {
        this.HMTrabalhada = HMTrabalhada;
    }

    public int getIdDB() {
        return IdDB;
    }

    public void setIdDB(int IdDB) {
        this.IdDB = IdDB;
    }

    public int getIdTalha() {
        return IdTalha;
    }

    public void setIdTalha(int IdTalha) {
        this.IdTalha = IdTalha;
    }

    public String getnTalhao() {
        return nTalhao;
    }

    public void setnTalhao(String nTalhao) {
        this.nTalhao = nTalhao;
    }

    public int getIdFazenda() {
        return IdFazenda;
    }

    public void setIdFazenda(int IdFazenda) {
        this.IdFazenda = IdFazenda;
    }

    public String getDataInicio() {
        return DataInicio;
    }

    public void setDataInicio(String DataInicio) {
        this.DataInicio = DataInicio;
    }

    public String getDataFinal() {
        return DataFinal;
    }

    public void setDataFinal(String DataFinal) {
        this.DataFinal = DataFinal;
    }

    public Double getAreaTalhao() {
        return AreaTalhao;
    }

    public void setAreaTalhao(Double AreaTalhao) {
        this.AreaTalhao = AreaTalhao;
    }

    public Double getPorcentAplic() {
        return PorcentAplic;
    }

    public void setPorcentAplic(Double PorcentAplic) {
        this.PorcentAplic = PorcentAplic;
    }

    public Double getAreaAplic() {
        return AreaAplic;
    }

    public void setAreaAplic(Double AreaAplic) {
        this.AreaAplic = AreaAplic;
    }
    
    
}
