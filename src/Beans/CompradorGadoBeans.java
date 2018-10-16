/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cad_comprador_gado")
public class CompradorGadoBeans implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comprador_id")
    private Integer idComprador;

    @Column(name = "comprador_nome", unique = true, nullable = false, length = 80)
    private String Comprador;

    @Column(name = "comprador_endereco", nullable = true, length = 255)
    private String endereco;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "comprador_cidade", nullable = true, referencedColumnName = "Id")
    private CidadesBeans cidade;

    @Column(name = "comprador_telefone", nullable = true, length = 80)
    private String telefone;

    @Column(name = "comprador_email", nullable = true, length = 80)
    private String email;

    @Column(name = "comprador_comissao", nullable = false, scale = 4, precision = 2)
    private Double comissao;

    @Column(name = "comprador_status", columnDefinition = "BIT", length = 1, nullable = false)
    private Boolean status;

    public CompradorGadoBeans() {
    }

    public CompradorGadoBeans(Integer idComprador, String Comprador) {
        this.idComprador = idComprador;
        this.Comprador = Comprador;
    }
   
    @Override
    public String toString() {
        return getComprador();
    }

    public Integer getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(Integer idComprador) {
        this.idComprador = idComprador;
    }

    public String getComprador() {
        return Comprador;
    }

    public void setComprador(String Comprador) {
        this.Comprador = Comprador;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public CidadesBeans getCidade() {
        return cidade;
    }

    public void setCidade(CidadesBeans cidade) {
        this.cidade = cidade;
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

    public Double getComissao() {
        return comissao;
    }

    public void setComissao(Double comissao) {
        this.comissao = comissao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
