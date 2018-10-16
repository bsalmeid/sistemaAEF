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
@Table(name = "remessas_itens")
public class RemessaMercadoriaItens implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long ID;

    @ManyToOne
    @JoinColumn(name = "id_remessa", nullable = false, referencedColumnName = "id")
    private RemessaMercadoriaBeans remessa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_item_pedido", nullable = true, referencedColumnName = "id")
    private PedidoAlmoxarifadoItens itemPedido;

    @Column(name = "id_item_pedido", nullable = true, insertable = false, updatable = false)
    private Long idItemPedido;
    
    @Column(name = "inventario", nullable = true, length = 120)
    private String Inventario;

    @Column(name = "n_pedido", length = 120, nullable = true)
    private String nPedido;

    @Column(name = "n_item", length = 120, nullable = true)
    private String nItem;

    @Column(name = "quantidade", precision = 2, scale = 9, nullable = false)
    private Double quantidade;

    @Column(name = "unid", nullable = true, length = 120)
    private String unidade;

    @Column(name = "codigo", nullable = false, length = 120)
    private String Codigo;

    @Column(name = "descricao", nullable = false, length = 255)
    private String Descricao;

    @Column(name = "recebedor", nullable = false, length = 255)
    private String recebedor;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public RemessaMercadoriaBeans getRemessa() {
        return remessa;
    }

    public void setRemessa(RemessaMercadoriaBeans remessa) {
        this.remessa = remessa;
    }

    public PedidoAlmoxarifadoItens getItemPedido() {
        return itemPedido;
    }

    public void setItemPedido(PedidoAlmoxarifadoItens itemPedido) {
        this.itemPedido = itemPedido;
    }

    public String getnPedido() {
        return nPedido;
    }

    public void setnPedido(String nPedido) {
        this.nPedido = nPedido;
    }

    public String getnItem() {
        return nItem;
    }

    public void setnItem(String nItem) {
        this.nItem = nItem;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public String getRecebedor() {
        return recebedor;
    }

    public void setRecebedor(String recebedor) {
        this.recebedor = recebedor;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getInventario() {
        return Inventario;
    }

    public void setInventario(String Inventario) {
        this.Inventario = Inventario;
    }

    public Long getIdItemPedido() {
        return idItemPedido;
    }

    public void setIdItemPedido(Long idItemPedido) {
        this.idItemPedido = idItemPedido;
    }



    
    
}
