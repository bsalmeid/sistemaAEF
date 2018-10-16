/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "pedidos_almoxarifado_cotacao")
@NamedQueries({
        @NamedQuery(name = "PedidosAlmoxarifadoCotacao.CotacoesItensNaoComprados", query = "SELECT c FROM PedidosAlmoxarifadoCotacao c, PedidoAlmoxarifadoItens item WHERE item.id = c.idItemPedido.id and c.idSolicitacao.id = :codigo")})
        //   " and item.idStatusItem < 3"

public class PedidosAlmoxarifadoCotacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_item_pedido", referencedColumnName = "id", nullable = false)
    //@Cascade(CascadeType.SAVE_UPDATE) não ativar....realiza atualização ou inserção
    private PedidoAlmoxarifadoItens idItemPedido;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_solicitacao", referencedColumnName = "id", nullable = false)
    private PedidosAlmoxarifadoSolicitacao idSolicitacao;
    
    @Column(name = "id_fornecedor", nullable = false)
    private Integer idFornecedor;
    
    @Column(name = "fornecedor", nullable = false, length = 180)
    private String fornecedor;
    
    @Column(name = "item_original",  columnDefinition = "BIT", length = 1 ,nullable = false)
    private Boolean item_original;
    
    @Column(name = "marca_peca", nullable = false, length = 80)
    private String marcaPeca;
    
    @Column(name = "quant_cotacao", nullable = false, precision = 7, scale = 2, columnDefinition = "Double Not Null Default 0.00")
    private Double quant_cotacao;
    
    @Column(name = "quant_fechamento", nullable = false, precision = 7, scale = 2, columnDefinition = "Double Not Null Default 0.00")
    private Double quant_fechamento;
    
    @Column(name = "valor_unit", nullable = false, precision = 7, scale = 2)
    private Double valorUnit;
    
    @Column(name = "item_selecionado" , columnDefinition = "BIT", length = 1 ,nullable = false)
    private boolean item_selecionado;
    
    @Column(name = "item_comprado" , columnDefinition = "BIT", length = 1 ,nullable = false)
    private boolean item_comprado;

    @Override
    public String toString() {
        return "PedidosAlmoxarifadoCotacao{" + "id=" + id + ", idItemPedido=" + idItemPedido + ", idSolicitacao=" + idSolicitacao + ", idFornecedor=" + idFornecedor + ", fornecedor=" + fornecedor + ", item_original=" + item_original + ", marcaPeca=" + marcaPeca + ", quant_cotacao=" + quant_cotacao + ", quant_fechamento=" + quant_fechamento + ", valorUnit=" + valorUnit + ", item_selecionado=" + item_selecionado + ", item_comprado=" + item_comprado + '}';
    }

    public PedidosAlmoxarifadoCotacao() {
    
    }
    
    public PedidosAlmoxarifadoCotacao(Long id, PedidoAlmoxarifadoItens idItemPedido, Integer idFornecedor, String fornecedor, String marcaPeca, Double valorUnit) {
        this.id = id;
        this.idItemPedido = idItemPedido;
        this.idFornecedor = idFornecedor;
        this.fornecedor = fornecedor;
        this.marcaPeca = marcaPeca;
        this.valorUnit = valorUnit;
    }
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PedidoAlmoxarifadoItens getIdItemPedido() {
        return idItemPedido;
    }

    public void setIdItemPedido(PedidoAlmoxarifadoItens idItemPedido) {
        this.idItemPedido = idItemPedido;
    }

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getMarcaPeca() {
        return marcaPeca;
    }

    public void setMarcaPeca(String marcaPeca) {
        this.marcaPeca = marcaPeca;
    }

    public Double getValorUnit() {
        return valorUnit;
    }

    public void setValorUnit(Double valorUnit) {
        this.valorUnit = valorUnit;
    }

    public PedidosAlmoxarifadoSolicitacao getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(PedidosAlmoxarifadoSolicitacao idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public Boolean getItem_original() {
        return item_original;
    }

    public void setItem_original(Boolean item_original) {
        this.item_original = item_original;
    }

    public boolean isItem_selecionado() {
        return item_selecionado;
    }

    public void setItem_selecionado(boolean item_selecionado) {
        this.item_selecionado = item_selecionado;
    }

    public Double getQuant_cotacao() {
        return quant_cotacao;
    }

    public void setQuant_cotacao(Double quant_cotacao) {
        this.quant_cotacao = quant_cotacao;
    }

    public Double getQuant_fechamento() {
        return quant_fechamento;
    }

    public void setQuant_fechamento(Double quant_fechamento) {
        this.quant_fechamento = quant_fechamento;
    }

    public boolean isItem_comprado() {
        return item_comprado;
    }

    public void setItem_comprado(boolean item_comprado) {
        this.item_comprado = item_comprado;
    }



  

}
