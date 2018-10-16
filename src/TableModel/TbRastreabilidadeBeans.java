/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import java.util.Objects;

/**
 *
 * @author agroa
 */
public class TbRastreabilidadeBeans {

    private Integer ID;
    private Integer IdStatus;
    private String StatusAnimal;
    private String Data;
    private String Motivo;
    private Integer nCompra;
    private Integer IdFazenda;
    private String Fazenda;
    private String IDF;
    private String IDE;
    private Double Peso;
    private String Sexo;
    private Integer IdCategoria;
    private String categoria;
    private String Biotipo;
    private Integer IdRaca;
    private String Raca;
    private String IDFMatriz;
    private String IDEMatriz;
    private Integer Protocolo;
    private Integer IdComprador;
    private String Comprador;
    private String FormaNeg;
    private Double ValorUnit;
    private Double ValorAr;
    private String Classificacao;
    private boolean campoEditado;

    public String getClassificacao() {
        return Classificacao;
    }

    public void setClassificacao(String Classificacao) {
        this.Classificacao = Classificacao;
    }

    public Integer getIdStatus() {
        return IdStatus;
    }

    public void setIdStatus(Integer IdStatus) {
        this.IdStatus = IdStatus;
    }

    public String getStatusAnimal() {
        return StatusAnimal;
    }

    public void setStatusAnimal(String StatusAnimal) {
        this.StatusAnimal = StatusAnimal;
    }

    public boolean isCampoEditado() {
        return campoEditado;
    }

    public void setCampoEditado(boolean campoEditado) {
        this.campoEditado = campoEditado;
    }

    public Integer getIdComprador() {
        return IdComprador;
    }

    public void setIdComprador(Integer IdComprador) {
        this.IdComprador = IdComprador;
    }

    public String getComprador() {
        return Comprador;
    }

    public void setComprador(String Comprador) {
        this.Comprador = Comprador;
    }

    public String getFormaNeg() {
        return FormaNeg;
    }

    public void setFormaNeg(String FormaNeg) {
        this.FormaNeg = FormaNeg;
    }

    public Double getValorUnit() {
        return ValorUnit;
    }

    public void setValorUnit(Double ValorUnit) {
        this.ValorUnit = ValorUnit;
    }

    public Double getValorAr() {
        return ValorAr;
    }

    public void setValorAr(Double ValorAr) {
        this.ValorAr = ValorAr;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getIdFazenda() {
        return IdFazenda;
    }

    public void setIdFazenda(Integer IdFazenda) {
        this.IdFazenda = IdFazenda;
    }

    public String getFazenda() {
        return Fazenda;
    }

    public void setFazenda(String Fazenda) {
        this.Fazenda = Fazenda;
    }

    public String getRaca() {
        return Raca;
    }

    public void setRaca(String Raca) {
        this.Raca = Raca;
    }

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    public String getMotivo() {
        return Motivo;
    }

    public void setMotivo(String Motivo) {
        this.Motivo = Motivo;
    }

    public Integer getnCompra() {
        return nCompra;
    }

    public void setnCompra(Integer nCompra) {
        this.nCompra = nCompra;
    }

    public String getIDF() {
        return IDF;
    }

    public void setIDF(String IDF) {
        this.IDF = IDF;
    }

    public String getIDE() {
        return IDE;
    }

    public void setIDE(String IDE) {
        this.IDE = IDE;
    }

    public Double getPeso() {
        return Peso;
    }

    public void setPeso(Double Peso) {
        this.Peso = Peso;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public Integer getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(Integer IdCategoria) {
        this.IdCategoria = IdCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getBiotipo() {
        return Biotipo;
    }

    public void setBiotipo(String Biotipo) {
        this.Biotipo = Biotipo;
    }

    public Integer getIdRaca() {
        return IdRaca;
    }

    public void setIdRaca(Integer IdRaca) {
        this.IdRaca = IdRaca;
    }

    public String getIDFMatriz() {
        return IDFMatriz;
    }

    public void setIDFMatriz(String IDFMatriz) {
        this.IDFMatriz = IDFMatriz;
    }

    public String getIDEMatriz() {
        return IDEMatriz;
    }

    public void setIDEMatriz(String IDEMatriz) {
        this.IDEMatriz = IDEMatriz;
    }

    public Integer getProtocolo() {
        return Protocolo;
    }

    public void setProtocolo(Integer Protocolo) {
        this.Protocolo = Protocolo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.IDF);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TbRastreabilidadeBeans other = (TbRastreabilidadeBeans) obj;
        if (!Objects.equals(this.IDF, other.IDF)) {
            return false;
        }
        return true;
    }

}
