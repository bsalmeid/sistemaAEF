/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeansGerados;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author agroa
 */
@Entity
@Table(name = "cad_funcionarios_filhos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CadFuncionariosFilhos.findAll", query = "SELECT c FROM CadFuncionariosFilhos c")
    , @NamedQuery(name = "CadFuncionariosFilhos.findById", query = "SELECT c FROM CadFuncionariosFilhos c WHERE c.id = :id")
    , @NamedQuery(name = "CadFuncionariosFilhos.findByIdFuncionario", query = "SELECT c FROM CadFuncionariosFilhos c WHERE c.idFuncionario = :idFuncionario")
    , @NamedQuery(name = "CadFuncionariosFilhos.findByNome", query = "SELECT c FROM CadFuncionariosFilhos c WHERE c.nome = :nome")
    , @NamedQuery(name = "CadFuncionariosFilhos.findByCpf", query = "SELECT c FROM CadFuncionariosFilhos c WHERE c.cpf = :cpf")
    , @NamedQuery(name = "CadFuncionariosFilhos.findByLivro", query = "SELECT c FROM CadFuncionariosFilhos c WHERE c.livro = :livro")
    , @NamedQuery(name = "CadFuncionariosFilhos.findByFolha", query = "SELECT c FROM CadFuncionariosFilhos c WHERE c.folha = :folha")
    , @NamedQuery(name = "CadFuncionariosFilhos.findByNumeroFolha", query = "SELECT c FROM CadFuncionariosFilhos c WHERE c.numeroFolha = :numeroFolha")
    , @NamedQuery(name = "CadFuncionariosFilhos.findByDataNascimento", query = "SELECT c FROM CadFuncionariosFilhos c WHERE c.dataNascimento = :dataNascimento")
    , @NamedQuery(name = "CadFuncionariosFilhos.findByCidade", query = "SELECT c FROM CadFuncionariosFilhos c WHERE c.cidade = :cidade")
    , @NamedQuery(name = "CadFuncionariosFilhos.findByEstado", query = "SELECT c FROM CadFuncionariosFilhos c WHERE c.estado = :estado")})
public class CadFuncionariosFilhos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "id_funcionario")
    private Integer idFuncionario;
    @Column(name = "nome")
    private String nome;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "livro")
    private String livro;
    @Column(name = "folha")
    private String folha;
    @Column(name = "numero_folha")
    private String numeroFolha;
    @Column(name = "data_nascimento")
    private String dataNascimento;
    @Column(name = "cidade")
    private String cidade;
    @Column(name = "estado")
    private String estado;

    public CadFuncionariosFilhos() {
    }

    public CadFuncionariosFilhos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getLivro() {
        return livro;
    }

    public void setLivro(String livro) {
        this.livro = livro;
    }

    public String getFolha() {
        return folha;
    }

    public void setFolha(String folha) {
        this.folha = folha;
    }

    public String getNumeroFolha() {
        return numeroFolha;
    }

    public void setNumeroFolha(String numeroFolha) {
        this.numeroFolha = numeroFolha;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CadFuncionariosFilhos)) {
            return false;
        }
        CadFuncionariosFilhos other = (CadFuncionariosFilhos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BeansGerados.CadFuncionariosFilhos[ id=" + id + " ]";
    }
    
}
