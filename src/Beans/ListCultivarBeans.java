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
public class ListCultivarBeans {
    
    private Integer IdCultivar;
    private Integer IdCultura;
    private String  Cultivar;
    private String Evento;
    private Boolean Transgenico;
    private Integer Ciclo;
    private Double GrupoMaturacao;

    
    @Override
    public String toString(){
        return getCultivar();
    }
    
    public Integer getIdCultivar() {
        return IdCultivar;
    }

    public void setIdCultivar(Integer IdCultivar) {
        this.IdCultivar = IdCultivar;
    }

    public Integer getIdCultura() {
        return IdCultura;
    }

    public void setIdCultura(Integer IdCultura) {
        this.IdCultura = IdCultura;
    }

    public String getCultivar() {
        return Cultivar;
    }

    public void setCultivar(String Cultivar) {
        this.Cultivar = Cultivar;
    }

    public String getEvento() {
        return Evento;
    }

    public void setEvento(String Evento) {
        this.Evento = Evento;
    }

    public Boolean getTransgenico() {
        return Transgenico;
    }

    public void setTransgenico(Boolean Transgenico) {
        this.Transgenico = Transgenico;
    }

    public Integer getCiclo() {
        return Ciclo;
    }

    public void setCiclo(Integer Ciclo) {
        this.Ciclo = Ciclo;
    }

    public Double getGrupoMaturacao() {
        return GrupoMaturacao;
    }

    public void setGrupoMaturacao(Double GrupoMaturacao) {
        this.GrupoMaturacao = GrupoMaturacao;
    }
    
    
    
    
    
    
}
