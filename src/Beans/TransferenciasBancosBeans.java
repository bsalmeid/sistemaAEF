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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "transf_bancarias")
public class TransferenciasBancosBeans implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer ID;

    @Column(name = "dataMovimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dtMovimentos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idContaOrigem", nullable = true, referencedColumnName = "cad_conta_id")
    private ContaBancariaBeans idContaOrigem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idContaDestino", nullable = true, referencedColumnName = "cad_conta_id")
    private ContaBancariaBeans idContaDestino;

    @ManyToOne
    @JoinColumn(name = "planoConta", nullable = false, referencedColumnName = "pc_id")
    private PlanoContaBeans planoContas;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "desc_planoConta", nullable = false)
    private String descPlanoContas;

    @Column(name = "valor", nullable = false)
    private Double Valor;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Date getDtMovimentos() {
        return dtMovimentos;
    }

    public void setDtMovimentos(Date dtMovimentos) {
        this.dtMovimentos = dtMovimentos;
    }

    public ContaBancariaBeans getIdContaOrigem() {
        return idContaOrigem;
    }

    public void setIdContaOrigem(ContaBancariaBeans idContaOrigem) {
        this.idContaOrigem = idContaOrigem;
    }

    public ContaBancariaBeans getIdContaDestino() {
        return idContaDestino;
    }

    public void setIdContaDestino(ContaBancariaBeans idContaDestino) {
        this.idContaDestino = idContaDestino;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public PlanoContaBeans getPlanoContas() {
        return planoContas;
    }

    public void setPlanoContas(PlanoContaBeans planoContas) {
        this.planoContas = planoContas;
    }

    public String getDescPlanoContas() {
        return descPlanoContas;
    }

    public void setDescPlanoContas(String descPlanoContas) {
        this.descPlanoContas = descPlanoContas;
    }

    public Double getValor() {
        return Valor;
    }

    public void setValor(Double Valor) {
        this.Valor = Valor;
    }

}
