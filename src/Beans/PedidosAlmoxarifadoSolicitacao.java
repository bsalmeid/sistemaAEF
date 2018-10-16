/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "pedidos_almoxarifado_solicitacao")
public class PedidosAlmoxarifadoSolicitacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @Column(name = "data", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data;

    @Column(name = "descricao", length = 255 ,nullable = true)
    private String descricao;
    
    @Column(name = "status", columnDefinition = "BIT", length = 1, nullable = false)
    private Boolean status;

    @OneToMany(mappedBy = "idSolicitacao", cascade = CascadeType.ALL,orphanRemoval = true ,fetch = FetchType.LAZY, targetEntity = PedidoAlmoxarifadoItens.class)
    private List<PedidoAlmoxarifadoItens> listaSolicitacao;

    public PedidosAlmoxarifadoSolicitacao(Long id) {
        this.id = id;
    }

    public PedidosAlmoxarifadoSolicitacao() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<PedidoAlmoxarifadoItens> getListaSolicitacao() {
        return listaSolicitacao;
    }

    public void setListaSolicitacao(List<PedidoAlmoxarifadoItens> listaSolicitacao) {
        this.listaSolicitacao = listaSolicitacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
