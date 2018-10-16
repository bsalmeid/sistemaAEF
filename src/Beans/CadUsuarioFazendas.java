/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "cad_usuario_fazendas")
public class CadUsuarioFazendas implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false)
    private CadUsuario idUsuario;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_fazenda", referencedColumnName = "cad_fazendas_id", nullable = false)
    private PropriedadesBeans idFazenda;
 
    @Column(name = "acesso", nullable = false, columnDefinition = "BIT", length = 1)
    private Boolean acesso;
    
    @Transient
    private String fazenda;
    

    public CadUsuarioFazendas() {
    
    }

    public CadUsuarioFazendas(PropriedadesBeans idFazenda) {
        this.idFazenda = idFazenda;
    }

    public String getFazenda() {
        return fazenda;
    }

    public void setFazenda(String fazenda) {
        this.fazenda = fazenda;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CadUsuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(CadUsuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public PropriedadesBeans getIdFazenda() {
        return idFazenda;
    }

    public void setIdFazenda(PropriedadesBeans idFazenda) {
        this.idFazenda = idFazenda;
    }

    public Boolean getAcesso() {
        return acesso;
    }

    public void setAcesso(Boolean acesso) {
        this.acesso = acesso;
    }
    
    
    
    
    
    
}
