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
@Table(name = "pedidos_almoxarifado_fechamento")
public class PedidosAlmoxarifadoFechamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @Column(name = "data", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data;
    
    @Column(name = "valor_pedido", nullable = false, precision = 7, scale = 2)
    private Double valor_pedido;

    @Column(name = "status_emissao", columnDefinition = "BIT", length = 1, nullable = false)
    private Boolean statusEmissao;

    @Transient
    private List<PedidosAlmoxarifadoFechamentoItens> listItens;  
     
    public PedidosAlmoxarifadoFechamento() {
    
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

    public Boolean getStatusEmissao() {
        return statusEmissao;
    }

    public void setStatusEmissao(Boolean statusEmissao) {
        this.statusEmissao = statusEmissao;
    }

    public Double getValor_pedido() {
        return valor_pedido;
    }

    public void setValor_pedido(Double valor_pedido) {
        this.valor_pedido = valor_pedido;
    }

    public List<PedidosAlmoxarifadoFechamentoItens> getListItens() {
        return listItens;
    }

    public void setListItens(List<PedidosAlmoxarifadoFechamentoItens> listItens) {
        this.listItens = listItens;
    }

    
}
