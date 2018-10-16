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


@Entity
@Table(name = "cad_usuario_permissao")
public class CadUsuarioPermissoes implements Serializable {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false)
    private CadUsuario idUsuario;
    
    @Column(name = "nomeClasse", nullable = false)
    private String nomeClasse;
    
    @Column(name = "formulario", nullable = false)
    private String formulario;
    
    @Column(name = "visualizar", nullable = false, columnDefinition = "BIT", length = 1)
    private Boolean view;
    
    @Column(name = "inserir", nullable = false, columnDefinition = "BIT", length = 1)
    private Boolean insert;
    
    @Column(name = "atualizar", nullable = false, columnDefinition = "BIT", length = 1)
    private Boolean update;
    
    @Column(name = "excluir", nullable = false, columnDefinition = "BIT", length = 1)
    private Boolean delete;

    @Column(name = "relatorios", nullable = false, columnDefinition = "BIT", length = 1)
    private Boolean select;
    
    public CadUsuarioPermissoes() {
   
    }

    public CadUsuarioPermissoes(String nomeClasse, String formulario) {
        // Construtor Utilizado para popular Tabela de Permissões
        this.nomeClasse = nomeClasse;
        this.formulario = formulario;
        this.view = false;
        this.insert = false;
        this.update = false;
        this.select = false;
        this.delete = false;
    }

    public Boolean getSelect() {
        return select;
    }

    public void setSelect(Boolean select) {
        this.select = select;
    }
    
    public String getNomeClasse() {
        return nomeClasse;
    }

    public void setNomeClasse(String nomeClasse) {
        this.nomeClasse = nomeClasse;
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

    public String getFormulario() {
        return formulario;
    }

    public void setFormulario(String formulario) {
        this.formulario = formulario;
    }

    public Boolean getView() {
        return view;
    }

    public void setView(Boolean view) {
        this.view = view;
    }

    public Boolean getInsert() {
        return insert;
    }

    public void setInsert(Boolean insert) {
        this.insert = insert;
    }

    public Boolean getUpdate() {
        return update;
    }

    public void setUpdate(Boolean update) {
        this.update = update;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }
    
    
    
}
