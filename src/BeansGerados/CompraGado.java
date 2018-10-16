/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeansGerados;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "compra_gado")
public class CompraGado implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "compra_gado_id")
    private Integer compraGadoId;
    
    @Basic(optional = false)
    @Column(name = "compra_gado_dtLan")
    @Temporal(TemporalType.DATE)
    private Date compragadodtLan;
    
    @Basic(optional = false)
    @Column(name = "compra_gado_user")
    private String compraGadoUser;
    
    @Basic(optional = false)
    @Column(name = "compra_gado_data")
    @Temporal(TemporalType.DATE)
    private Date compraGadoData;
    
    @Basic(optional = false)
    @Column(name = "compra_gado_destino")
    private String compraGadoDestino;
    
    @Basic(optional = false)
    @Column(name = "compra_gado_comprador")
    private String compraGadoComprador;
    
    @Basic(optional = false)
    @Column(name = "compra_gado_desc")
    private String compraGadoDesc;
    
    @Basic(optional = false)
    @Column(name = "compra_gado_negociacao")
    private String compraGadoNegociacao;
    
    @Basic(optional = false)
    @Column(name = "compra_gado_quant")
    private short compraGadoQuant;
    
// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "compra_gado_precoUnit")
    private BigDecimal compragadoprecoUnit;
    
    @Column(name = "compra_gado_pesoOrigem")
    private BigDecimal compragadopesoOrigem;
    @Column(name = "compra_gado_ReaisArr")
    private BigDecimal compragadoReaisArr;
    @Basic(optional = false)
    @Column(name = "compra_gado_fornecedor")
    private String compraGadoFornecedor;
    @Basic(optional = false)
    @Column(name = "compra_gado_cpf")
    private String compraGadoCpf;
    @Basic(optional = false)
    @Column(name = "compra_gado_banco")
    private String compraGadoBanco;
    @Basic(optional = false)
    @Column(name = "compra_gado_agencia")
    private String compraGadoAgencia;
    @Basic(optional = false)
    @Column(name = "compra_gado_conta")
    private String compraGadoConta;
    @Column(name = "compra_gado_pesoUnit")
    private BigDecimal compragadopesoUnit;
    @Basic(optional = false)
    @Column(name = "compra_gado_valorT")
    private BigDecimal compragadovalorT;
    @Basic(optional = false)
    @Column(name = "compra_gado_stat")
    private String compraGadoStat;

    public CompraGado() {
    }

    public CompraGado(Integer compraGadoId) {
        this.compraGadoId = compraGadoId;
    }

    public CompraGado(Integer compraGadoId, Date compragadodtLan, String compraGadoUser, Date compraGadoData, String compraGadoDestino, String compraGadoComprador, String compraGadoDesc, String compraGadoNegociacao, short compraGadoQuant, BigDecimal compragadoprecoUnit, String compraGadoFornecedor, String compraGadoCpf, String compraGadoBanco, String compraGadoAgencia, String compraGadoConta, BigDecimal compragadovalorT, String compraGadoStat) {
        this.compraGadoId = compraGadoId;
        this.compragadodtLan = compragadodtLan;
        this.compraGadoUser = compraGadoUser;
        this.compraGadoData = compraGadoData;
        this.compraGadoDestino = compraGadoDestino;
        this.compraGadoComprador = compraGadoComprador;
        this.compraGadoDesc = compraGadoDesc;
        this.compraGadoNegociacao = compraGadoNegociacao;
        this.compraGadoQuant = compraGadoQuant;
        this.compragadoprecoUnit = compragadoprecoUnit;
        this.compraGadoFornecedor = compraGadoFornecedor;
        this.compraGadoCpf = compraGadoCpf;
        this.compraGadoBanco = compraGadoBanco;
        this.compraGadoAgencia = compraGadoAgencia;
        this.compraGadoConta = compraGadoConta;
        this.compragadovalorT = compragadovalorT;
        this.compraGadoStat = compraGadoStat;
    }

    public Integer getCompraGadoId() {
        return compraGadoId;
    }

    public void setCompraGadoId(Integer compraGadoId) {
        this.compraGadoId = compraGadoId;
    }

    public Date getCompragadodtLan() {
        return compragadodtLan;
    }

    public void setCompragadodtLan(Date compragadodtLan) {
        this.compragadodtLan = compragadodtLan;
    }

    public String getCompraGadoUser() {
        return compraGadoUser;
    }

    public void setCompraGadoUser(String compraGadoUser) {
        this.compraGadoUser = compraGadoUser;
    }

    public Date getCompraGadoData() {
        return compraGadoData;
    }

    public void setCompraGadoData(Date compraGadoData) {
        this.compraGadoData = compraGadoData;
    }

    public String getCompraGadoDestino() {
        return compraGadoDestino;
    }

    public void setCompraGadoDestino(String compraGadoDestino) {
        this.compraGadoDestino = compraGadoDestino;
    }

    public String getCompraGadoComprador() {
        return compraGadoComprador;
    }

    public void setCompraGadoComprador(String compraGadoComprador) {
        this.compraGadoComprador = compraGadoComprador;
    }

    public String getCompraGadoDesc() {
        return compraGadoDesc;
    }

    public void setCompraGadoDesc(String compraGadoDesc) {
        this.compraGadoDesc = compraGadoDesc;
    }

    public String getCompraGadoNegociacao() {
        return compraGadoNegociacao;
    }

    public void setCompraGadoNegociacao(String compraGadoNegociacao) {
        this.compraGadoNegociacao = compraGadoNegociacao;
    }

    public short getCompraGadoQuant() {
        return compraGadoQuant;
    }

    public void setCompraGadoQuant(short compraGadoQuant) {
        this.compraGadoQuant = compraGadoQuant;
    }

    public BigDecimal getCompragadoprecoUnit() {
        return compragadoprecoUnit;
    }

    public void setCompragadoprecoUnit(BigDecimal compragadoprecoUnit) {
        this.compragadoprecoUnit = compragadoprecoUnit;
    }

    public BigDecimal getCompragadopesoOrigem() {
        return compragadopesoOrigem;
    }

    public void setCompragadopesoOrigem(BigDecimal compragadopesoOrigem) {
        this.compragadopesoOrigem = compragadopesoOrigem;
    }

    public BigDecimal getCompragadoReaisArr() {
        return compragadoReaisArr;
    }

    public void setCompragadoReaisArr(BigDecimal compragadoReaisArr) {
        this.compragadoReaisArr = compragadoReaisArr;
    }

    public String getCompraGadoFornecedor() {
        return compraGadoFornecedor;
    }

    public void setCompraGadoFornecedor(String compraGadoFornecedor) {
        this.compraGadoFornecedor = compraGadoFornecedor;
    }

    public String getCompraGadoCpf() {
        return compraGadoCpf;
    }

    public void setCompraGadoCpf(String compraGadoCpf) {
        this.compraGadoCpf = compraGadoCpf;
    }

    public String getCompraGadoBanco() {
        return compraGadoBanco;
    }

    public void setCompraGadoBanco(String compraGadoBanco) {
        this.compraGadoBanco = compraGadoBanco;
    }

    public String getCompraGadoAgencia() {
        return compraGadoAgencia;
    }

    public void setCompraGadoAgencia(String compraGadoAgencia) {
        this.compraGadoAgencia = compraGadoAgencia;
    }

    public String getCompraGadoConta() {
        return compraGadoConta;
    }

    public void setCompraGadoConta(String compraGadoConta) {
        this.compraGadoConta = compraGadoConta;
    }

    public BigDecimal getCompragadopesoUnit() {
        return compragadopesoUnit;
    }

    public void setCompragadopesoUnit(BigDecimal compragadopesoUnit) {
        this.compragadopesoUnit = compragadopesoUnit;
    }

    public BigDecimal getCompragadovalorT() {
        return compragadovalorT;
    }

    public void setCompragadovalorT(BigDecimal compragadovalorT) {
        this.compragadovalorT = compragadovalorT;
    }

    public String getCompraGadoStat() {
        return compraGadoStat;
    }

    public void setCompraGadoStat(String compraGadoStat) {
        this.compraGadoStat = compraGadoStat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (compraGadoId != null ? compraGadoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompraGado)) {
            return false;
        }
        CompraGado other = (CompraGado) object;
        if ((this.compraGadoId == null && other.compraGadoId != null) || (this.compraGadoId != null && !this.compraGadoId.equals(other.compraGadoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BeansGerados.CompraGado[ compraGadoId=" + compraGadoId + " ]";
    }
    
}
