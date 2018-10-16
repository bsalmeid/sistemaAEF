/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author agroa
 */
@Entity
@Table(name = "cad_modelo_equip")
public class ModeloEquipamentosBeans implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer ID;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    private CategoriaEquipamentosBeans id_categoria;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_marca")
    private MarcaEquipamentosBeans id_marca;

    @Transient
    private Integer IDCategoria;

    @Transient
    private Integer IDMarca;

    @Transient
    private String Categoria;

    @Transient
    private String Marca;

    @Transient
    private String Modelo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "largura_trabalho", precision =  5, scale =  3)
    private Double largura;

    @Column(name = "status", columnDefinition = "BIT", length = 1, nullable = false)
    private Boolean Status;

    public ModeloEquipamentosBeans() {

    }

    public ModeloEquipamentosBeans(Integer ID, String descricao) {
        this.ID = ID;
        this.descricao = descricao;
    }

    public CategoriaEquipamentosBeans getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(CategoriaEquipamentosBeans id_categoria) {
        this.id_categoria = id_categoria;
    }

    public MarcaEquipamentosBeans getId_marca() {
        return id_marca;
    }

    public void setId_marca(MarcaEquipamentosBeans id_marca) {
        this.id_marca = id_marca;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean Status) {
        this.Status = Status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getLargura() {
        return largura;
    }

    public void setLargura(Double largura) {
        this.largura = largura;
    }

    public Integer getIDCategoria() {
        return IDCategoria;
    }

    public void setIDCategoria(Integer IDCategoria) {
        this.IDCategoria = IDCategoria;
    }

    public Integer getIDMarca() {
        return IDMarca;
    }

    public void setIDMarca(Integer IDMarca) {
        this.IDMarca = IDMarca;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }

    @Override
    public String toString() {
        return getDescricao();
    }

}
