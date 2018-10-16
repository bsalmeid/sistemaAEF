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
public class TbPlantioBeans {

    private Integer ID;
    private Integer idOrdemServico;
    private String DataPlantio;
    private String Safra;
    private String Cultivo;
    private String Operacao;
    private String Cultura;
    private String Cultivar;
    private Double StandInicial;
    private String Fazenda;
    private String Talhao;
    private Double areaTalhao;
    private Double porcentagemPlantada;
    private Double areaPlantada;
    private String DataColheitaPrevista;
    private String EquipePlantio;

    public Integer getID() {
        return ID;
    }

    public Double getStandInicial() {
        return StandInicial;
    }

    public void setStandInicial(Double StandInicial) {
        this.StandInicial = StandInicial;
    }

    
    
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getIdOrdemServico() {
        return idOrdemServico;
    }

    public void setIdOrdemServico(Integer idOrdemServico) {
        this.idOrdemServico = idOrdemServico;
    }

    public String getDataPlantio() {
        return DataPlantio;
    }

    public void setDataPlantio(String DataPlantio) {
        this.DataPlantio = DataPlantio;
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

    public String getOperacao() {
        return Operacao;
    }

    public void setOperacao(String Operacao) {
        this.Operacao = Operacao;
    }

    public String getCultura() {
        return Cultura;
    }

    public void setCultura(String Cultura) {
        this.Cultura = Cultura;
    }

    public String getCultivar() {
        return Cultivar;
    }

    public void setCultivar(String Cultivar) {
        this.Cultivar = Cultivar;
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
        return areaTalhao;
    }

    public void setAreaTalhao(Double areaTalhao) {
        this.areaTalhao = areaTalhao;
    }

    public Double getPorcentagemPlantada() {
        return porcentagemPlantada;
    }

    public void setPorcentagemPlantada(Double porcentagemPlantada) {
        this.porcentagemPlantada = porcentagemPlantada;
    }

    public Double getAreaPlantada() {
        return areaPlantada;
    }

    public void setAreaPlantada(Double areaPlantada) {
        this.areaPlantada = areaPlantada;
    }

    public String getDataColheitaPrevista() {
        return DataColheitaPrevista;
    }

    public void setDataColheitaPrevista(String DataColheitaPrevista) {
        this.DataColheitaPrevista = DataColheitaPrevista;
    }

    public String getEquipePlantio() {
        return EquipePlantio;
    }

    public void setEquipePlantio(String EquipePlantio) {
        this.EquipePlantio = EquipePlantio;
    }

    
    
    
    
}
