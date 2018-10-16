/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cad_estados")
public class EstadosBeans implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer IdEtado;

    @Column(name = "estado", nullable = false)
    private String Estado;

    @Column(name = "abreviacao", nullable = false)
    private String Abreviacao;

    @Column(name = "cod_ibge", nullable = false)
    private Integer codIBGE;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "Id_UF")
    private List<CidadesBeans> listaCidades;

    public EstadosBeans() {
    
    }

    public EstadosBeans(Integer IdEtado, String Estado) {
        this.IdEtado = IdEtado;
        this.Estado = Estado;
    }
    
    public EstadosBeans(Integer IdEtado, String Estado, String Abreviacao) {
        this.IdEtado = IdEtado;
        this.Estado = Estado;
        this.Abreviacao = Abreviacao;
    }

    @Override
    public String toString() {
        return getEstado();
    }

    public List<CidadesBeans> getListaCidades() {
        return listaCidades;
    }

    public void setListaCidades(List<CidadesBeans> listaCidades) {
        this.listaCidades = listaCidades;
    }

    public Integer getIdEtado() {
        return IdEtado;
    }

    public void setIdEtado(Integer IdEtado) {
        this.IdEtado = IdEtado;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getAbreviacao() {
        return Abreviacao;
    }

    public void setAbreviacao(String Abreviacao) {
        this.Abreviacao = Abreviacao;
    }

    public Integer getCodIBGE() {
        return codIBGE;
    }

    public void setCodIBGE(Integer codIBGE) {
        this.codIBGE = codIBGE;
    }

}
