/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "cad_fornecedor")
public class FornecedoresBeans implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cad_fornecedor_id", nullable = false)
    private Integer ID;
    
    @Column(name = "cad_fornecedor_idseg", nullable = true)
    private Integer idSegmento;
    
    @Column(name = "cad_fornecedor_nome", nullable = true, length = 200)
    private String NomeFantasia;
    
    @Column(name = "cad_fornecedor_razao", nullable = true, length = 200)
    private String RazaoSocial;
    
    @Column(name = "cad_fornecedor_telefone", nullable = true, length = 80)
    private String telefone;
    
    @Column(name = "cad_fornecedor_email", nullable = true, length = 80)
    private String email;
    
    @Column(name = "cad_fornecedor_tipoPessoa", nullable = false, length = 80)
    private String TipoPessoa;
    
    @Column(name = "cad_fornecedor_cnpj", nullable = false, length = 80)
    private String cnpj;
    
    @Column(name = "cad_fornecedor_banco", nullable = true, length = 80)
    private String Banco;
    
    @Column(name = "cad_fornecedor_agencia", nullable = true, length = 80)
    private String agencia;
    
    @Column(name = "cad_fornecedor_conta", nullable = true, length = 80)
    private String conta;
    
    @Transient
    private Double ValorTotalFechamento;
    
    @Transient
    private List<PedidosAlmoxarifadoCotacao> listaCotacao;
   
    @Transient
    private List<PedidosAlmoxarifadoFechamentoItens> listaFechamento;

    public FornecedoresBeans() {
    
    }

    public FornecedoresBeans(Integer ID) {
        this.ID = ID;
    }
    
    public FornecedoresBeans(int ID, String NomeFantasia) {
        this.ID = ID;
        this.NomeFantasia = NomeFantasia;
    }
    
    public int getIdSegmento() {
        return idSegmento;
    }

    public void setIdSegmento(int idSegmento) {
        this.idSegmento = idSegmento;
    }   
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoPessoa() {
        return TipoPessoa;
    }

    public void setTipoPessoa(String TipoPessoa) {
        this.TipoPessoa = TipoPessoa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getBanco() {
        return Banco;
    }

    public void setBanco(String Banco) {
        this.Banco = Banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public List<PedidosAlmoxarifadoCotacao> getListaCotacao() {
        return listaCotacao;
    }

    public void setListaCotacao(List<PedidosAlmoxarifadoCotacao> listaCotacao) {
        this.listaCotacao = listaCotacao;
    }

    public List<PedidosAlmoxarifadoFechamentoItens> getListaFechamento() {
        return listaFechamento;
    }

    public void setListaFechamento(List<PedidosAlmoxarifadoFechamentoItens> listaFechamento) {
        this.listaFechamento = listaFechamento;
    }

    public Double getValorTotalFechamento() {
        return ValorTotalFechamento;
    }

    public void setValorTotalFechamento(Double ValorTotalFechamento) {
        this.ValorTotalFechamento = ValorTotalFechamento;
    }
    
    @Override
    public String toString() {
        return "FornecedoresBeans{" + "NomeFantasia=" + NomeFantasia + ", RazaoSocial=" + RazaoSocial + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.ID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FornecedoresBeans other = (FornecedoresBeans) obj;
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
}
