
package Beans;

import Beans.CategoriaAnimalBeans;
import Beans.CompradorGadoBeans;
import Beans.PropriedadesBeans;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@Entity
@Table(name = "compra_gado")
public class CompraGadoBeans implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "compra_gado_id")
    private Integer ID;
    
    @Column(name = "compra_gado_user")
    private String User;
    
    @Column(name = "compra_gado_dtLan")
    @Temporal(TemporalType.DATE)
    private Date dtLan;
    
    @Column(name = "compra_gado_data")
    @Temporal(TemporalType.DATE)
    private Date Data;
    
    @ManyToOne 
    @JoinColumn(name = "id_fazenda", nullable = false, referencedColumnName = "cad_fazendas_id")
    private PropriedadesBeans FazendaDestino;
    
    @Column(name = "compra_gado_destino")
    private String Destino;
    
    @ManyToOne
    @JoinColumn(name = "id_comprador", referencedColumnName = "comprador_id", nullable = true)
    private CompradorGadoBeans CompradorB;
    
    @Column(name = "compra_gado_comprador")
    private String Comprador;
    
    @ManyToOne
    @JoinColumn (name = "id_categoria", referencedColumnName = "id", nullable = true)
    private CategoriaAnimalBeans CategoriaAnimal;
    
    @Column(name = "compra_gado_desc")
    private String Descricao;
    
    @Column(name = "compra_gado_negociacao")
    private String Negociacao;
    
    @Column(name = "compra_gado_quant")
    private Integer Quant;
    
    @Column(name = "compra_gado_precoUnit")
    private Double valorUnit;
    
    @Column(name = "compra_gado_pesoOrigem")
    private Double pesoOrigem;
   
    @Column(name = "compra_gado_ReaisArr")
    private Double ReaisArr;
   
    @Column(name = "compra_gado_fornecedor")
    private String Fornecedor;
    
    @Column(name = "compra_gado_cpf")
    private String Cpf;

    @Column(name = "compra_gado_banco")
    private String Banco;

    @Column(name = "compra_gado_agencia")
    private String Agencia;

    @Column(name = "compra_gado_conta")
    private String Conta;

    @Column(name = "compra_gado_pesoUnit")
    private Double pesoUnit;

    @Column(name = "compra_gado_valorT")
    private Double valorT;

    @Column(name = "compra_gado_stat")
    private String status;
    
    @Column(name = "status", columnDefinition = "BIT", nullable = false, length = 1)
    private Boolean statusB;
    
    @Transient
    private Double valorPago;

    @Transient
    private Integer QNF;
    
    @Transient
    private Integer QGTA;
    
    @Transient
    private Integer QMIN;
    
    public CompraGadoBeans() {
        
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }

    public Date getDtLan() {
        return dtLan;
    }

    public void setDtLan(Date dtLan) {
        this.dtLan = dtLan;
    }

    public Date getData() {
        return Data;
    }

    public void setData(Date Data) {
        this.Data = Data;
    }

    public PropriedadesBeans getFazendaDestino() {
        return FazendaDestino;
    }

    public void setFazendaDestino(PropriedadesBeans FazendaDestino) {
        this.FazendaDestino = FazendaDestino;
    }

    public String getDestino() {
        return Destino;
    }

    public void setDestino(String Destino) {
        this.Destino = Destino;
    }

    public String getComprador() {
        return Comprador;
    }

    public void setComprador(String Comprador) {
        this.Comprador = Comprador;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public String getNegociacao() {
        return Negociacao;
    }

    public void setNegociacao(String Negociacao) {
        this.Negociacao = Negociacao;
    }

    public Integer getQuant() {
        return Quant;
    }

    public void setQuant(Integer Quant) {
        this.Quant = Quant;
    }

    public Double getValorUnit() {
        return valorUnit;
    }

    public void setValorUnit(Double valorUnit) {
        this.valorUnit = valorUnit;
    }

    public Double getPesoOrigem() {
        return pesoOrigem;
    }

    public void setPesoOrigem(Double pesoOrigem) {
        this.pesoOrigem = pesoOrigem;
    }

    public Double getReaisArr() {
        return ReaisArr;
    }

    public void setReaisArr(Double ReaisArr) {
        this.ReaisArr = ReaisArr;
    }

    public String getFornecedor() {
        return Fornecedor;
    }

    public void setFornecedor(String Fornecedor) {
        this.Fornecedor = Fornecedor;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String Cpf) {
        this.Cpf = Cpf;
    }

    public String getBanco() {
        return Banco;
    }

    public void setBanco(String Banco) {
        this.Banco = Banco;
    }

    public String getAgencia() {
        return Agencia;
    }

    public void setAgencia(String Agencia) {
        this.Agencia = Agencia;
    }

    public String getConta() {
        return Conta;
    }

    public void setConta(String Conta) {
        this.Conta = Conta;
    }

    public Double getPesoUnit() {
        return pesoUnit;
    }

    public void setPesoUnit(Double pesoUnit) {
        this.pesoUnit = pesoUnit;
    }

    public Double getValorT() {
        return valorT;
    }

    public void setValorT(Double valorT) {
        this.valorT = valorT;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CompradorGadoBeans getCompradorB() {
        return CompradorB;
    }

    public void setCompradorB(CompradorGadoBeans CompradorB) {
        this.CompradorB = CompradorB;
    }

    public CategoriaAnimalBeans getCategoriaAniaml() {
        return CategoriaAnimal;
    }

    public void setCategoriaAniaml(CategoriaAnimalBeans CategoriaAniaml) {
        this.CategoriaAnimal = CategoriaAniaml;
    }

    public Boolean getStatusB() {
        return statusB;
    }

    public void setStatusB(Boolean statusB) {
        this.statusB = statusB;
    }

    public Integer getQNF() {
        return QNF;
    }

    public void setQNF(Integer QNF) {
        this.QNF = QNF;
    }

    public Integer getQGTA() {
        return QGTA;
    }

    public void setQGTA(Integer QGTA) {
        this.QGTA = QGTA;
    }

    public Integer getQMIN() {
        return QMIN;
    }

    public void setQMIN(Integer QMIN) {
        this.QMIN = QMIN;
    }
    

}
