/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "cad_cidades")
public class CidadesBeans implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", nullable = false)
    private Integer Id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "Id_UF", nullable = false, referencedColumnName = "id")
    private EstadosBeans Id_UF;

    @Column(name = "Nome", nullable = false)
    private String Nome;

    @Column(name = "UF", nullable = false)
    private String UF;

    @Column(name = "Codigo_IBGE", nullable = false)
    private Integer Codigo_IBGE;

    @Column(name = "Capital", columnDefinition = "BIT", length = 1 ,nullable = false)
    private Boolean Capital;

    public CidadesBeans() {
    
    }

    public CidadesBeans(Integer Id, String Nome) {
        this.Id = Id;
        this.Nome = Nome;
    }
    
    public CidadesBeans(Integer Id, EstadosBeans Id_UF, String Nome) {
        this.Id = Id;
        this.Id_UF = Id_UF;
        this.Nome = Nome;
    }

    @Override
    public String toString() {
        return Nome;
    }    
    
    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public EstadosBeans getId_UF() {
        return Id_UF;
    }

    public void setId_UF(EstadosBeans Id_UF) {
        this.Id_UF = Id_UF;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public Integer getCodigo_IBGE() {
        return Codigo_IBGE;
    }

    public void setCodigo_IBGE(Integer Codigo_IBGE) {
        this.Codigo_IBGE = Codigo_IBGE;
    }

    public Boolean getCapital() {
        return Capital;
    }

    public void setCapital(Boolean Capital) {
        this.Capital = Capital;
    }




}
