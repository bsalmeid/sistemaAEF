/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "cad_doc_financeiro")
public class TipoDocPagto implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer IdDoc;
    
    @Column(name = "Tipo", nullable = false, length = 80)
    private String Nome;
    
    @Column(name = "uso_fiscal", nullable = false, columnDefinition = "BIT" ,length = 1)
    private Boolean UsoFiscalLivroCaixa;
    
    @Column(name = "status", nullable = false, columnDefinition = "BIT" ,length = 1)
    private Boolean status;

    @Override
    public String toString(){
        return getNome();
    }

    public TipoDocPagto() {
    
    }
    
    public TipoDocPagto(Integer IdDoc, String Nome) {
        this.IdDoc = IdDoc;
        this.Nome = Nome;
    } 
    
    public Integer getIdDoc() {
        return IdDoc;
    }

    public void setIdDoc(Integer IdDoc) {
        this.IdDoc = IdDoc;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public Boolean getUsoFiscalLivroCaixa() {
        return UsoFiscalLivroCaixa;
    }

    public void setUsoFiscalLivroCaixa(Boolean UsoFiscalLivroCaixa) {
        this.UsoFiscalLivroCaixa = UsoFiscalLivroCaixa;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
   
}
