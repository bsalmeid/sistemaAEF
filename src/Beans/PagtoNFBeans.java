/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;


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

@Entity
@Table(name = "escrituracao_fiscal")
public class PagtoNFBeans implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fiscal_id", nullable = false)
    private Long ID;

    @ManyToOne
    @JoinColumn(name = "fiscal_idpagto", referencedColumnName = "pagto_id", nullable = false)
    private PagamentosBeans Pagto;

    @ManyToOne
    @JoinColumn(name = "fiscal_idFazenda", referencedColumnName = "cad_fazendas_id", nullable = false)
    private PropriedadesBeans FazendaB;

    @ManyToOne
    @JoinColumn(name = "fornecedor", referencedColumnName = "cad_fornecedor_id", nullable = true)
    private FornecedoresBeans FornecedorB;

    @Column(name = "fiscal_dtEmissao", nullable = false, length = 80)
    @Temporal(TemporalType.DATE)
    private Date DtEmissao;

    @Column(name = "fiscal_tipoEmissor", nullable = false, length = 80)
    private String TipoCad;

    @Column(name = "fiscal_cnpj", nullable = false, length = 80)
    private String nCad;

    @Column(name = "fiscal_tipodoc", nullable = false, length = 80)
    private String TipoDoc;

    @Column(name = "fiscal_ndoc", nullable = false)
    private Integer NDOC;

    @Column(name = "fiscal_fazendaDestino", nullable = false, length = 120)
    private String Fazenda;

    @Column(name = "fiscal_idEmissor", nullable = false)
    private Integer IdFornecedor;

    @Column(name = "fiscal_emissor", nullable = false, length = 80)
    private String Fornecedor;

    @Column(name = "fiscal_valordoc", nullable = false)
    private Double Valor;

    @Column(name = "id_tipo_doc", nullable = false)
    private Integer IdTipoDoc;

    public PagtoNFBeans() {
    
    }

    @Override
    public String toString() {
        return TipoDoc + " Nº " + NDOC + " Emissor: " + Fornecedor;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public PagamentosBeans getPagto() {
        return Pagto;
    }

    public void setPagto(PagamentosBeans Pagto) {
        this.Pagto = Pagto;
    }

    public PropriedadesBeans getFazendaB() {
        return FazendaB;
    }

    public void setFazendaB(PropriedadesBeans FazendaB) {
        this.FazendaB = FazendaB;
    }

    public FornecedoresBeans getFornecedorB() {
        return FornecedorB;
    }

    public void setFornecedorB(FornecedoresBeans FornecedorB) {
        this.FornecedorB = FornecedorB;
    }

    public Date getDtEmissao() {
        return DtEmissao;
    }

    public void setDtEmissao(Date DtEmissao) {
        this.DtEmissao = DtEmissao;
    }

    public String getTipoCad() {
        return TipoCad;
    }

    public void setTipoCad(String TipoCad) {
        this.TipoCad = TipoCad;
    }

    public String getnCad() {
        return nCad;
    }

    public void setnCad(String nCad) {
        this.nCad = nCad;
    }

    public String getTipoDoc() {
        return TipoDoc;
    }

    public void setTipoDoc(String TipoDoc) {
        this.TipoDoc = TipoDoc;
    }

    public Integer getNDOC() {
        return NDOC;
    }

    public void setNDOC(Integer NDOC) {
        this.NDOC = NDOC;
    }

    public String getFazenda() {
        return Fazenda;
    }

    public void setFazenda(String Fazenda) {
        this.Fazenda = Fazenda;
    }

    public Integer getIdFornecedor() {
        return IdFornecedor;
    }

    public void setIdFornecedor(Integer IdFornecedor) {
        this.IdFornecedor = IdFornecedor;
    }

    public String getFornecedor() {
        return Fornecedor;
    }

    public void setFornecedor(String Fornecedor) {
        this.Fornecedor = Fornecedor;
    }

    public Double getValor() {
        return Valor;
    }

    public void setValor(Double Valor) {
        this.Valor = Valor;
    }

    public Integer getIdTipoDoc() {
        return IdTipoDoc;
    }

    public void setIdTipoDoc(Integer IdTipoDoc) {
        this.IdTipoDoc = IdTipoDoc;
    }

    
    
    
}
