/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import TableModel.TbDadosClimaticosBeans;
import TableModel.TbTalhaoAplicBeans;
import java.util.List;

/**
 *
 * @author agroa
 */
public class OperacoesBeans {

    private Integer ID;
    private String DataOperacao;
    private Integer IdOS;
    private Integer IdSafra;
    private Integer IdCultivo;
    private Integer IdCultura;
    private Integer IdOperacao;
    private Integer IdAplicacao;
    private Integer IdTrator;
    private Integer IdImplemento;
    private Integer IdCei;
    private Integer CodigoOperador;
    private Integer IdOperador;
    private Double LarguraT;
    private Double VelocidadeT;
    private Double HmInicial;
    private Double HmFinal;
    private Double HmTrablhada;
    private List<TbTalhaoAplicBeans> ListTalhoesAplic;
    private List<TbDadosClimaticosBeans> ListDadosClima;

    public List<TbTalhaoAplicBeans> getListTalhoesAplic() {
        return ListTalhoesAplic;
    }

    public void setListTalhoesAplic(List<TbTalhaoAplicBeans> ListTalhoesAplic) {
        this.ListTalhoesAplic = ListTalhoesAplic;
    }

    public List<TbDadosClimaticosBeans> getListDadosClima() {
        return ListDadosClima;
    }

    public void setListDadosClima(List<TbDadosClimaticosBeans> ListDadosClima) {
        this.ListDadosClima = ListDadosClima;
    }

    
    public Integer getIdCei() {
        return IdCei;
    }

    public void setIdCei(Integer IdCei) {
        this.IdCei = IdCei;
    }

    public Integer getCodigoOperador() {
        return CodigoOperador;
    }

    public Integer getIdOperador() {
        return IdOperador;
    }

    public void setIdOperador(Integer IdOperador) {
        this.IdOperador = IdOperador;
    }

    public Double getLarguraT() {
        return LarguraT;
    }

    public void setLarguraT(Double LarguraT) {
        this.LarguraT = LarguraT;
    }

    public Double getVelocidadeT() {
        return VelocidadeT;
    }

    public void setVelocidadeT(Double VelocidadeT) {
        this.VelocidadeT = VelocidadeT;
    }
    
    public void setCodigoOperador(Integer CodigoOperador) {
        this.CodigoOperador = CodigoOperador;
    }
    
    public Double getHmTrablhada() {
        return HmTrablhada;
    }

    public void setHmTrablhada(Double HmTrablhada) {
        this.HmTrablhada = HmTrablhada;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getDataOperacao() {
        return DataOperacao;
    }

    public void setDataOperacao(String DataOperacao) {
        this.DataOperacao = DataOperacao;
    }

    public Integer getIdOS() {
        return IdOS;
    }

    public void setIdOS(Integer IdOS) {
        this.IdOS = IdOS;
    }

    public Integer getIdSafra() {
        return IdSafra;
    }

    public void setIdSafra(Integer IdSafra) {
        this.IdSafra = IdSafra;
    }

    public Integer getIdCultivo() {
        return IdCultivo;
    }

    public void setIdCultivo(Integer IdCultivo) {
        this.IdCultivo = IdCultivo;
    }

    public Integer getIdCultura() {
        return IdCultura;
    }

    public void setIdCultura(Integer IdCultura) {
        this.IdCultura = IdCultura;
    }

    public Integer getIdOperacao() {
        return IdOperacao;
    }

    public void setIdOperacao(Integer IdOperacao) {
        this.IdOperacao = IdOperacao;
    }

    public Integer getIdAplicacao() {
        return IdAplicacao;
    }

    public void setIdAplicacao(Integer IdAplicacao) {
        this.IdAplicacao = IdAplicacao;
    }

    public Integer getIdTrator() {
        return IdTrator;
    }

    public void setIdTrator(Integer IdTrator) {
        this.IdTrator = IdTrator;
    }

    public Integer getIdImplemento() {
        return IdImplemento;
    }

    public void setIdImplemento(Integer IdImplemento) {
        this.IdImplemento = IdImplemento;
    }

    public Double getHmInicial() {
        return HmInicial;
    }

    public void setHmInicial(Double HmInicial) {
        this.HmInicial = HmInicial;
    }

    public Double getHmFinal() {
        return HmFinal;
    }

    public void setHmFinal(Double HmFinal) {
        this.HmFinal = HmFinal;
    }
            
    
    
    
    
}
