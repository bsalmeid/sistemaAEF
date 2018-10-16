/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import NFe.NFeProdutosBeans;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "entrada_almoxarifado")
public class EntradaAlmoxarifadoBeans implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer ID;

    @Column(name = "usuario", length = 120, nullable = false)
    private String Usuario;

    @Column(name = "data", length = 120, nullable = false)
    private String Data;

    @Column(name = "id_almoxarifado", nullable = false)
    private Integer ID_Almoxarifado;

    @Column(name = "id_operacao", nullable = false)
    private Integer ID_Operacao;

    @Column(name = "id_fazenda", nullable = false)
    private Integer ID_Fazenda;

    @Column(name = "id_tipoPessoa", nullable = false)
    private Integer ID_TipoPessoa;

    @Column(name = "n_doc", nullable = false)
    private Integer nDoc;

    @Column(name = "cnpj", length = 120, nullable = false)
    private String CNPJ;

    @Column(name = "id_fornecedor", nullable = false)
    private Integer ID_Fornecedor;

    @Column(name = "nome_fantasia", length = 255, nullable = false)
    private String NomeFantasia;

    @Column(name = "razao_social", length = 255, nullable = false)
    private String RazaoSocial;

    @Column(name = "observacao", length = 255, nullable = false)
    private String Observacao;

    
    
    @Transient
    private List<NFeProdutosBeans> Produtos;

    
    public EntradaAlmoxarifadoBeans() {
    
    }
    
    public Integer getnDoc() {
        return nDoc;
    }

    public void setnDoc(Integer nDoc) {
        this.nDoc = nDoc;
    }

    public List<NFeProdutosBeans> getProdutos() {
        return Produtos;
    }

    public void setProdutos(List<NFeProdutosBeans> Produtos) {
        this.Produtos = Produtos;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    public Integer getID_Almoxarifado() {
        return ID_Almoxarifado;
    }

    public void setID_Almoxarifado(Integer ID_Almoxarifado) {
        this.ID_Almoxarifado = ID_Almoxarifado;
    }

    public Integer getID_Operacao() {
        return ID_Operacao;
    }

    public void setID_Operacao(Integer ID_Operacao) {
        this.ID_Operacao = ID_Operacao;
    }

    public Integer getID_Fazenda() {
        return ID_Fazenda;
    }

    public void setID_Fazenda(Integer ID_Fazenda) {
        this.ID_Fazenda = ID_Fazenda;
    }

    public Integer getID_TipoPessoa() {
        return ID_TipoPessoa;
    }

    public void setID_TipoPessoa(Integer ID_TipoPessoa) {
        this.ID_TipoPessoa = ID_TipoPessoa;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public Integer getID_Fornecedor() {
        return ID_Fornecedor;
    }

    public void setID_Fornecedor(Integer ID_Fornecedor) {
        this.ID_Fornecedor = ID_Fornecedor;
    }

    public String getNomeFantasia() {
        return NomeFantasia;
    }

    public void setNomeFantasia(String NomeFantasia) {
        this.NomeFantasia = NomeFantasia;
    }

    public String getRazaoSocial() {
        return RazaoSocial;
    }

    public void setRazaoSocial(String RazaoSocial) {
        this.RazaoSocial = RazaoSocial;
    }

    public String getObservacao() {
        return Observacao;
    }

    public void setObservacao(String Observacao) {
        this.Observacao = Observacao;
    }

}
