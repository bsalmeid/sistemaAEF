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
@Table(name = "cad_contabancaria")
public class ContaBancariaBeans implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cad_conta_id", nullable = false)
    private Integer IdConta;

    @Column(name = "cad_conta_descricao", nullable = false, length = 120)
    private String Descricao;

    @Column(name = "cad_conta_banco", nullable = false, length = 120)
    private String Banco;

    @Column(name = "cad_conta_ag", nullable = false, length = 120)
    private String Agencia;

    @Column(name = "cad_conta_nconta", nullable = false, length = 120)
    private String Conta;

    @Column(name = "cad_conta_status", columnDefinition = "BIT", length = 1, nullable = false)
    private boolean status;

    public ContaBancariaBeans() {
    }

    public ContaBancariaBeans(Integer IdConta) {
        this.IdConta = IdConta;
    }
    
    public ContaBancariaBeans(Integer IdConta, String Descricao) {
        this.IdConta = IdConta;
        this.Descricao = Descricao;
    }

    @Override
    public String toString() {
        return getDescricao();
    }

    public Integer getIdConta() {
        return IdConta;
    }

    public void setIdConta(Integer IdConta) {
        this.IdConta = IdConta;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
