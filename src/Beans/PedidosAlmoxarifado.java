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

/**
 *
 * @author agroa
 */
@Entity
@Table(name = "pedidos_almoxarifado")
public class PedidosAlmoxarifado implements Serializable  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
        
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_fazenda", nullable = false, referencedColumnName = "cad_fazendas_id")
    private PropriedadesBeans idFazenda;
    
    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;
    
    @Column(name = "data", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data;
    
    @Column(name = "status_emissao", columnDefinition = "BIT", length = 1, nullable = false)
    private Boolean statusEmissao;
    
    @Transient
    private List<PedidoAlmoxarifadoItens> listaItens;

    public PedidosAlmoxarifado(Long id, PropriedadesBeans idFazenda, Integer idUsuario, Date data, Boolean statusEmissao) {
        this.id = id;
        this.idFazenda = idFazenda;
        this.idUsuario = idUsuario;
        this.data = data;
        this.statusEmissao = statusEmissao;
    }

    public PedidosAlmoxarifado() {
    }

    @Override
    public String toString() {
        return this.id.toString();
    }
    
    public List<PedidoAlmoxarifadoItens> getListaItens() {
        return listaItens;
    }

    public void setListaItens(List<PedidoAlmoxarifadoItens> listaItens) {
        this.listaItens = listaItens;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PropriedadesBeans getIdFazenda() {
        return idFazenda;
    }

    public void setIdFazenda(PropriedadesBeans idFazenda) {
        this.idFazenda = idFazenda;
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

    
    
}
