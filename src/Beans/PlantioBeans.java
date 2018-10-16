/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author agroa
 */
public class PlantioBeans {

    private Integer ID;
    private Integer idOrdemServico;
    private String DataPlantio;
    private Integer idSafra;
    private Integer idCultivo;
    private Integer idOperacao;
    private Integer idCultura;
    private Integer idCultivar;
    private Double  StandIncial;
    private Integer idFazenda;
    private Integer idTalhao;
    private String nomeTalhao;
    private Double areaTalhao;
    private Double porcentagemPlantada;
    private Double areaPlantada;
    private String DataColheitaPrevista;
    private Integer idEquipePlantio;
    private String EquipePlantio;
    private String Observacao;

    public String getNomeTalhao() {
        return nomeTalhao;
    }

    public void setNomeTalhao(String nomeTalhao) {
        this.nomeTalhao = nomeTalhao;
    }

    public Double getStandIncial() {
        return StandIncial;
    }

    public void setStandIncial(Double StandIncial) {
        this.StandIncial = StandIncial;
    }
    
    public String getObservacao() {
        return Observacao;
    }

    public void setObservacao(String Observacao) {
        this.Observacao = Observacao;
    }
    
    public String getEquipePlantio() {
        return EquipePlantio;
    }

    public void setEquipePlantio(String EquipePlantio) {
        this.EquipePlantio = EquipePlantio;
    }
    
    public Integer getID() {
        return ID;
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

    public Integer getIdOperacao() {
        return idOperacao;
    }

    public void setIdOperacao(Integer idOperacao) {
        this.idOperacao = idOperacao;
    }

    public Integer getIdCultura() {
        return idCultura;
    }

    public void setIdCultura(Integer idCultura) {
        this.idCultura = idCultura;
    }

    public Integer getIdCultivar() {
        return idCultivar;
    }

    public void setIdCultivar(Integer idCultivar) {
        this.idCultivar = idCultivar;
    }

    public Integer getIdFazenda() {
        return idFazenda;
    }

    public void setIdFazenda(Integer idFazenda) {
        this.idFazenda = idFazenda;
    }

    public Integer getIdTalhao() {
        return idTalhao;
    }

    public void setIdTalhao(Integer idTalhao) {
        this.idTalhao = idTalhao;
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

    public Integer getIdEquipePlantio() {
        return idEquipePlantio;
    }

    public void setIdEquipePlantio(Integer idEquipePlantio) {
        this.idEquipePlantio = idEquipePlantio;
    }
    
    
    
    
    
}
