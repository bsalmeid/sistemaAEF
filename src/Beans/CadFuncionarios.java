/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author agroa
 */
@Entity
@Table(name = "cad_funcionarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CadFuncionarios.findAll", query = "SELECT c FROM CadFuncionarios c")
    , @NamedQuery(name = "CadFuncionarios.findById", query = "SELECT c FROM CadFuncionarios c WHERE c.id = :id")
    , @NamedQuery(name = "CadFuncionarios.findByIdCei", query = "SELECT c FROM CadFuncionarios c WHERE c.idCei = :idCei")
    , @NamedQuery(name = "CadFuncionarios.findByCodigo", query = "SELECT c FROM CadFuncionarios c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "CadFuncionarios.findByIdFazenda", query = "SELECT c FROM CadFuncionarios c WHERE c.idFazenda = :idFazenda")
    , @NamedQuery(name = "CadFuncionarios.findByIdSetor", query = "SELECT c FROM CadFuncionarios c WHERE c.idSetor = :idSetor")
    , @NamedQuery(name = "CadFuncionarios.findByIdFuncao", query = "SELECT c FROM CadFuncionarios c WHERE c.idFuncao = :idFuncao")
    , @NamedQuery(name = "CadFuncionarios.findByNome", query = "SELECT c FROM CadFuncionarios c WHERE c.nome = :nome")
    , @NamedQuery(name = "CadFuncionarios.findBySalarioBase", query = "SELECT c FROM CadFuncionarios c WHERE c.salarioBase = :salarioBase")
    , @NamedQuery(name = "CadFuncionarios.findByDtAdimissao", query = "SELECT c FROM CadFuncionarios c WHERE c.dtAdimissao = :dtAdimissao")
    , @NamedQuery(name = "CadFuncionarios.findByDtDemissao", query = "SELECT c FROM CadFuncionarios c WHERE c.dtDemissao = :dtDemissao")
    , @NamedQuery(name = "CadFuncionarios.findByDiaDescanco", query = "SELECT c FROM CadFuncionarios c WHERE c.diaDescanco = :diaDescanco")
    , @NamedQuery(name = "CadFuncionarios.findByHEntr1", query = "SELECT c FROM CadFuncionarios c WHERE c.hEntr1 = :hEntr1")
    , @NamedQuery(name = "CadFuncionarios.findByHSaida1", query = "SELECT c FROM CadFuncionarios c WHERE c.hSaida1 = :hSaida1")
    , @NamedQuery(name = "CadFuncionarios.findByHEnt2", query = "SELECT c FROM CadFuncionarios c WHERE c.hEnt2 = :hEnt2")
    , @NamedQuery(name = "CadFuncionarios.findByHSaida2", query = "SELECT c FROM CadFuncionarios c WHERE c.hSaida2 = :hSaida2")})
public class CadFuncionarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "id_cei")
    private int idCei;
    
    @Basic(optional = false)
    @Column(name = "codigo")
    private int codigo;
    
    @Basic(optional = false)
    @Column(name = "id_fazenda")
    private int idFazenda;
    
    @Basic(optional = false)
    @Column(name = "id_setor")
    private int idSetor;
    
    @Basic(optional = false)
    @Column(name = "id_funcao")
    private int idFuncao;
    
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "salario_base")
    private BigDecimal salarioBase;
    
    @Basic(optional = false)
    @Column(name = "dt_adimissao")
    private String dtAdimissao;
    
    @Basic(optional = false)
    @Column(name = "dt_demissao")
    private String dtDemissao;
    
    @Basic(optional = false)
    @Column(name = "dia_descanco")
    private String diaDescanco;
    @Basic(optional = false)
    @Column(name = "h_entr1")
    @Temporal(TemporalType.TIME)
    private Date hEntr1;
    @Basic(optional = false)
    @Column(name = "h_saida1")
    @Temporal(TemporalType.TIME)
    private Date hSaida1;
    
    @Column(name = "h_ent2")
    @Temporal(TemporalType.TIME)
    private Date hEnt2;
    
    @Basic(optional = false)
    @Column(name = "h_saida2")
    @Temporal(TemporalType.TIME)
    private Date hSaida2;

    public CadFuncionarios() {
    }

    public CadFuncionarios(Integer id) {
        this.id = id;
    }

    public CadFuncionarios(Integer id, int idCei, int codigo, int idFazenda, int idSetor, int idFuncao, String nome, BigDecimal salarioBase, String dtAdimissao, String dtDemissao, String diaDescanco, Date hEntr1, Date hSaida1, Date hSaida2) {
        this.id = id;
        this.idCei = idCei;
        this.codigo = codigo;
        this.idFazenda = idFazenda;
        this.idSetor = idSetor;
        this.idFuncao = idFuncao;
        this.nome = nome;
        this.salarioBase = salarioBase;
        this.dtAdimissao = dtAdimissao;
        this.dtDemissao = dtDemissao;
        this.diaDescanco = diaDescanco;
        this.hEntr1 = hEntr1;
        this.hSaida1 = hSaida1;
        this.hSaida2 = hSaida2;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdCei() {
        return idCei;
    }

    public void setIdCei(int idCei) {
        this.idCei = idCei;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getIdFazenda() {
        return idFazenda;
    }

    public void setIdFazenda(int idFazenda) {
        this.idFazenda = idFazenda;
    }

    public int getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(int idSetor) {
        this.idSetor = idSetor;
    }

    public int getIdFuncao() {
        return idFuncao;
    }

    public void setIdFuncao(int idFuncao) {
        this.idFuncao = idFuncao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(BigDecimal salarioBase) {
        this.salarioBase = salarioBase;
    }

    public String getDtAdimissao() {
        return dtAdimissao;
    }

    public void setDtAdimissao(String dtAdimissao) {
        this.dtAdimissao = dtAdimissao;
    }

    public String getDtDemissao() {
        return dtDemissao;
    }

    public void setDtDemissao(String dtDemissao) {
        this.dtDemissao = dtDemissao;
    }

    public String getDiaDescanco() {
        return diaDescanco;
    }

    public void setDiaDescanco(String diaDescanco) {
        this.diaDescanco = diaDescanco;
    }

    public Date getHEntr1() {
        return hEntr1;
    }

    public void setHEntr1(Date hEntr1) {
        this.hEntr1 = hEntr1;
    }

    public Date getHSaida1() {
        return hSaida1;
    }

    public void setHSaida1(Date hSaida1) {
        this.hSaida1 = hSaida1;
    }

    public Date getHEnt2() {
        return hEnt2;
    }

    public void setHEnt2(Date hEnt2) {
        this.hEnt2 = hEnt2;
    }

    public Date getHSaida2() {
        return hSaida2;
    }

    public void setHSaida2(Date hSaida2) {
        this.hSaida2 = hSaida2;
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
        if (!(object instanceof CadFuncionarios)) {
            return false;
        }
        CadFuncionarios other = (CadFuncionarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Beans.CadFuncionarios[ id=" + id + " ]";
    }
    
}
