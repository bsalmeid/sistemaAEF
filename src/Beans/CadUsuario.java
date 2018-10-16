/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author agroa
 */

@Entity
@Table(name = "cad_usuario_n")
public class CadUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "login")
    private String login;
    
    @Column(name = "senha")
    private String senha;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "telefone")
    private String telefone;
    
    @Column(name = "status", columnDefinition = "BIT", length = 1 ,nullable = false)
    private boolean status;

    @Transient
    private List<CadUsuarioPermissoes> listPermissao;
   
    @Transient
    private List<CadUsuarioFazendas> listFazendas;
    
    
    public CadUsuario() {
    
    }
    
    public List<CadUsuarioFazendas> getListFazendas() {
        return listFazendas;
    }

    public void setListFazendas(List<CadUsuarioFazendas> listFazendas) {
        this.listFazendas = listFazendas;
    } 
    
    public List<CadUsuarioPermissoes> getListPermissao() {
        return listPermissao;
    }

    public void setListPermissao(List<CadUsuarioPermissoes> listPermissao) {
        this.listPermissao = listPermissao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    
    
}
