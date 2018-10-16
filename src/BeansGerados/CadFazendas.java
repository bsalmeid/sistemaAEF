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
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author agroa
 */
@Entity
@Table(name = "cad_fazendas")
public class CadFazendas implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cad_fazendas_id")
    private Integer Id;
    
    @Basic(optional = false)
    @Column(name = "cad_fazendas_id_produtor")
    private int IdProdutor;
    
    @Basic(optional = false)
    @Column(name = "cad_fazendas_id_cei")
    private int IdCei;
    
    @Basic(optional = false)
    @Column(name = "cad_fazendas_nome")
    private String Nome;
    
    @Basic(optional = false)
    @Column(name = "cad_fazendas_tipo")
    private String Tipo;
    
    @Basic(optional = false)
    @Column(name = "cad_fazendas_area")
    private String Area;
    
    @Basic(optional = false)
    @Column(name = "cad_fazendas_ins_est")
    private String InsEst;
    
    @Basic(optional = false)
    @Column(name = "cad_fazendas_end")
    private String Endereco;
    
    @Basic(optional = false)
    @Column(name = "cad_fazendas_cidade")
    private String Cidade;
    
    @Basic(optional = false)
    @Column(name = "cad_fazendas_uf")
    private String Estado;
    
    @Basic(optional = false)
    @Column(name = "cad_fazendas_stat")
    private String Status;
    
    @Lob
    @Column(name = "cad_fazendas_obs")
    private String cadFazendasObs;

    public CadFazendas() {
    }

    public CadFazendas(Integer Id) {
        this.Id = Id;
    }

    public CadFazendas(Integer Id, int IdProdutor, int IdCei, String Nome, String Tipo, String Area, String InsEst, String Endereco, String Cidade, String Estado, String Status, String cadFazendasObs) {
        this.Id = Id;
        this.IdProdutor = IdProdutor;
        this.IdCei = IdCei;
        this.Nome = Nome;
        this.Tipo = Tipo;
        this.Area = Area;
        this.InsEst = InsEst;
        this.Endereco = Endereco;
        this.Cidade = Cidade;
        this.Estado = Estado;
        this.Status = Status;
        this.cadFazendasObs = cadFazendasObs;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public int getIdProdutor() {
        return IdProdutor;
    }

    public void setIdProdutor(int IdProdutor) {
        this.IdProdutor = IdProdutor;
    }

    public int getIdCei() {
        return IdCei;
    }

    public void setIdCei(int IdCei) {
        this.IdCei = IdCei;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String Area) {
        this.Area = Area;
    }

    public String getInsEst() {
        return InsEst;
    }

    public void setInsEst(String InsEst) {
        this.InsEst = InsEst;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String Endereco) {
        this.Endereco = Endereco;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String Cidade) {
        this.Cidade = Cidade;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getCadFazendasObs() {
        return cadFazendasObs;
    }

    public void setCadFazendasObs(String cadFazendasObs) {
        this.cadFazendasObs = cadFazendasObs;
    }

    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (Id != null ? Id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CadFazendas)) {
            return false;
        }
        CadFazendas other = (CadFazendas) object;
        if ((this.Id == null && other.Id != null) || (this.Id != null && !this.Id.equals(other.Id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BeansGerados.CadFazendas[ cadFazendasId=" + Id + " ]";
    }
    
}
