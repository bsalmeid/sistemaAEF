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
@Table(name = "cad_fornecedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CadFornecedor.findAll", query = "SELECT c FROM CadFornecedor c")
    , @NamedQuery(name = "CadFornecedor.findByCadFornecedorId", query = "SELECT c FROM CadFornecedor c WHERE c.cadFornecedorId = :cadFornecedorId")
    , @NamedQuery(name = "CadFornecedor.findByCadFornecedorIdseg", query = "SELECT c FROM CadFornecedor c WHERE c.cadFornecedorIdseg = :cadFornecedorIdseg")
    , @NamedQuery(name = "CadFornecedor.findByCadFornecedorNome", query = "SELECT c FROM CadFornecedor c WHERE c.cadFornecedorNome = :cadFornecedorNome")
    , @NamedQuery(name = "CadFornecedor.findByCadFornecedorRazao", query = "SELECT c FROM CadFornecedor c WHERE c.cadFornecedorRazao = :cadFornecedorRazao")
    , @NamedQuery(name = "CadFornecedor.findByCadFornecedorTelefone", query = "SELECT c FROM CadFornecedor c WHERE c.cadFornecedorTelefone = :cadFornecedorTelefone")
    , @NamedQuery(name = "CadFornecedor.findByCadFornecedorEmail", query = "SELECT c FROM CadFornecedor c WHERE c.cadFornecedorEmail = :cadFornecedorEmail")
    , @NamedQuery(name = "CadFornecedor.findByCadfornecedortipoPessoa", query = "SELECT c FROM CadFornecedor c WHERE c.cadfornecedortipoPessoa = :cadfornecedortipoPessoa")
    , @NamedQuery(name = "CadFornecedor.findByCadFornecedorCnpj", query = "SELECT c FROM CadFornecedor c WHERE c.cadFornecedorCnpj = :cadFornecedorCnpj")
    , @NamedQuery(name = "CadFornecedor.findByCadFornecedorBanco", query = "SELECT c FROM CadFornecedor c WHERE c.cadFornecedorBanco = :cadFornecedorBanco")
    , @NamedQuery(name = "CadFornecedor.findByCadFornecedorAgencia", query = "SELECT c FROM CadFornecedor c WHERE c.cadFornecedorAgencia = :cadFornecedorAgencia")
    , @NamedQuery(name = "CadFornecedor.findByCadFornecedorConta", query = "SELECT c FROM CadFornecedor c WHERE c.cadFornecedorConta = :cadFornecedorConta")
    , @NamedQuery(name = "CadFornecedor.findByCadFornecedorIdentificador", query = "SELECT c FROM CadFornecedor c WHERE c.cadFornecedorIdentificador = :cadFornecedorIdentificador")})
public class CadFornecedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cad_fornecedor_id")
    private Integer cadFornecedorId;
    @Basic(optional = false)
    @Column(name = "cad_fornecedor_idseg")
    private int cadFornecedorIdseg;
    @Column(name = "cad_fornecedor_nome")
    private String cadFornecedorNome;
    @Column(name = "cad_fornecedor_razao")
    private String cadFornecedorRazao;
    @Column(name = "cad_fornecedor_telefone")
    private String cadFornecedorTelefone;
    @Column(name = "cad_fornecedor_email")
    private String cadFornecedorEmail;
    @Basic(optional = false)
    @Column(name = "cad_fornecedor_tipoPessoa")
    private String cadfornecedortipoPessoa;
    @Basic(optional = false)
    @Column(name = "cad_fornecedor_cnpj")
    private String cadFornecedorCnpj;
    @Column(name = "cad_fornecedor_banco")
    private String cadFornecedorBanco;
    @Column(name = "cad_fornecedor_agencia")
    private String cadFornecedorAgencia;
    @Column(name = "cad_fornecedor_conta")
    private String cadFornecedorConta;
    @Column(name = "cad_fornecedor_identificador")
    private String cadFornecedorIdentificador;

    public CadFornecedor() {
    }

    public CadFornecedor(Integer cadFornecedorId) {
        this.cadFornecedorId = cadFornecedorId;
    }

    public CadFornecedor(Integer cadFornecedorId, int cadFornecedorIdseg, String cadfornecedortipoPessoa, String cadFornecedorCnpj) {
        this.cadFornecedorId = cadFornecedorId;
        this.cadFornecedorIdseg = cadFornecedorIdseg;
        this.cadfornecedortipoPessoa = cadfornecedortipoPessoa;
        this.cadFornecedorCnpj = cadFornecedorCnpj;
    }

    public Integer getCadFornecedorId() {
        return cadFornecedorId;
    }

    public void setCadFornecedorId(Integer cadFornecedorId) {
        this.cadFornecedorId = cadFornecedorId;
    }

    public int getCadFornecedorIdseg() {
        return cadFornecedorIdseg;
    }

    public void setCadFornecedorIdseg(int cadFornecedorIdseg) {
        this.cadFornecedorIdseg = cadFornecedorIdseg;
    }

    public String getCadFornecedorNome() {
        return cadFornecedorNome;
    }

    public void setCadFornecedorNome(String cadFornecedorNome) {
        this.cadFornecedorNome = cadFornecedorNome;
    }

    public String getCadFornecedorRazao() {
        return cadFornecedorRazao;
    }

    public void setCadFornecedorRazao(String cadFornecedorRazao) {
        this.cadFornecedorRazao = cadFornecedorRazao;
    }

    public String getCadFornecedorTelefone() {
        return cadFornecedorTelefone;
    }

    public void setCadFornecedorTelefone(String cadFornecedorTelefone) {
        this.cadFornecedorTelefone = cadFornecedorTelefone;
    }

    public String getCadFornecedorEmail() {
        return cadFornecedorEmail;
    }

    public void setCadFornecedorEmail(String cadFornecedorEmail) {
        this.cadFornecedorEmail = cadFornecedorEmail;
    }

    public String getCadfornecedortipoPessoa() {
        return cadfornecedortipoPessoa;
    }

    public void setCadfornecedortipoPessoa(String cadfornecedortipoPessoa) {
        this.cadfornecedortipoPessoa = cadfornecedortipoPessoa;
    }

    public String getCadFornecedorCnpj() {
        return cadFornecedorCnpj;
    }

    public void setCadFornecedorCnpj(String cadFornecedorCnpj) {
        this.cadFornecedorCnpj = cadFornecedorCnpj;
    }

    public String getCadFornecedorBanco() {
        return cadFornecedorBanco;
    }

    public void setCadFornecedorBanco(String cadFornecedorBanco) {
        this.cadFornecedorBanco = cadFornecedorBanco;
    }

    public String getCadFornecedorAgencia() {
        return cadFornecedorAgencia;
    }

    public void setCadFornecedorAgencia(String cadFornecedorAgencia) {
        this.cadFornecedorAgencia = cadFornecedorAgencia;
    }

    public String getCadFornecedorConta() {
        return cadFornecedorConta;
    }

    public void setCadFornecedorConta(String cadFornecedorConta) {
        this.cadFornecedorConta = cadFornecedorConta;
    }

    public String getCadFornecedorIdentificador() {
        return cadFornecedorIdentificador;
    }

    public void setCadFornecedorIdentificador(String cadFornecedorIdentificador) {
        this.cadFornecedorIdentificador = cadFornecedorIdentificador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cadFornecedorId != null ? cadFornecedorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CadFornecedor)) {
            return false;
        }
        CadFornecedor other = (CadFornecedor) object;
        if ((this.cadFornecedorId == null && other.cadFornecedorId != null) || (this.cadFornecedorId != null && !this.cadFornecedorId.equals(other.cadFornecedorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BeansGerados.CadFornecedor[ cadFornecedorId=" + cadFornecedorId + " ]";
    }
    
}
