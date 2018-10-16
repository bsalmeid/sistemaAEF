/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "pedidos_almoxarifado_fechamento_itens")
public class PedidosAlmoxarifadoFechamentoItens implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long ID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_fechamento", referencedColumnName = "id", nullable = false)
    private PedidosAlmoxarifadoFechamento fechamento;
    
    @Column(name = "id_fornecedor", nullable = false)
    private Integer ID_Fornecedor;
    
    @Column(name = "fornecedor", nullable = false, length = 255)
    private String fornecedor;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_item", referencedColumnName = "id", nullable = false)
    private PedidoAlmoxarifadoItens id_item;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cotacao", referencedColumnName = "id", nullable = false)
    private PedidosAlmoxarifadoCotacao idCotacao;
    
    @Column(name = "fazenda", nullable = false, length = 120)
    private String fazenda;
    
    @Column(name = "id_pedido", nullable = false)
    private Long id_pedido;
    
    @Column(name = "n_item_pedido", nullable = false)
    private Integer n_item_pedido;
    
    @Column(name = "id_cadastro", nullable = false)
    private Integer idCadastro;
    
    @Column(name = "descricao", nullable = false, length = 120)
    private String descricao;
    
    @Column(name = "codigo", nullable = false, length = 120)
    private String codigo;
    
    @Column(name = "marca", nullable = false, length = 80)
    private String marca;
    
    @Column(name = "quantidade", nullable = false, precision = 7, scale = 2)
    private Double quantidade;
    
    @Column(name = "unidade", nullable = false)
    private String unidade;
    
    @Column(name = "valor_unit", nullable = false, precision = 7, scale = 2)
    private Double valor_unit;
    
    @Column(name = "valor_total", nullable = false, precision = 7, scale = 2)
    private Double valor_total;
    
    @Column(name = "status_fechamento", columnDefinition = "BIT", length = 1 ,nullable = false)
    private boolean status_fechamento;

    public PedidosAlmoxarifadoFechamentoItens(Long ID, PedidoAlmoxarifadoItens item, PedidosAlmoxarifadoCotacao cot) {
        this.ID = ID;
        this.ID_Fornecedor = cot.getIdFornecedor();
        this.fornecedor = cot.getFornecedor();
        this.id_item = item;
        this.idCotacao = cot;
        this.fazenda = item.getIdPedido().getIdFazenda().getNome();
        this.id_pedido = item.getIdPedido().getId();
        this.n_item_pedido = item.getnItem();
        this.idCadastro = item.getIdItem();
        this.descricao = item.getDescricao();
        this.codigo = item.getCodigo();
        this.marca = cot.getMarcaPeca();
        this.quantidade = (cot.getQuant_fechamento() > 0 ? cot.getQuant_fechamento() : item.getQuantidade());
        this.unidade = item.getUnidade();
        this.valor_unit = cot.getValorUnit();
        this.valor_total = cot.getValorUnit() * item.getQuantidade();
        // Verifica se o item já foi comprado e se a quantidade compradada é maior ou = a quantidade do 
        // pedido e retorna true para inabilitar que seja comprado Novamente
        if (item.getIdStatusItem() >= 3 && (cot.isItem_comprado())){
           this.status_fechamento = true;
        } else {
           this.status_fechamento = false; 
        }
    }

    public PedidosAlmoxarifadoFechamentoItens() {
    
    }
    
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Integer getID_Fornecedor() {
        return ID_Fornecedor;
    }

    public void setID_Fornecedor(Integer ID_Fornecedor) {
        this.ID_Fornecedor = ID_Fornecedor;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public PedidoAlmoxarifadoItens getId_item() {
        return id_item;
    }

    public void setId_item(PedidoAlmoxarifadoItens id_item) {
        this.id_item = id_item;
    }

    public Long getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Long id_pedido) {
        this.id_pedido = id_pedido;
    }

    public Integer getN_item_pedido() {
        return n_item_pedido;
    }

    public void setN_item_pedido(Integer n_item_pedido) {
        this.n_item_pedido = n_item_pedido;
    }

    public Integer getIdCadastro() {
        return idCadastro;
    }

    public void setIdCadastro(Integer idCadastro) {
        this.idCadastro = idCadastro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor_unit() {
        return valor_unit;
    }

    public void setValor_unit(Double valor_unit) {
        this.valor_unit = valor_unit;
    }

    public Double getValor_total() {
        return valor_total;
    }

    public void setValor_total(Double valor_total) {
        this.valor_total = valor_total;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public boolean isStatus_fechamento() {
        return status_fechamento;
    }

    public void setStatus_fechamento(boolean status_fechamento) {
        this.status_fechamento = status_fechamento;
    }

    public PedidosAlmoxarifadoCotacao getIdCotacao() {
        return idCotacao;
    }

    public void setIdCotacao(PedidosAlmoxarifadoCotacao idCotacao) {
        this.idCotacao = idCotacao;
    }

    public String getFazenda() {
        return fazenda;
    }

    public void setFazenda(String fazenda) {
        this.fazenda = fazenda;
    }

    public PedidosAlmoxarifadoFechamento getFechamento() {
        return fechamento;
    }

    public void setFechamento(PedidosAlmoxarifadoFechamento fechamento) {
        this.fechamento = fechamento;
    }
    
    @Override
    public String toString() {
        return "PedidosAlmoxarifadoFechamento{" + "ID=" + ID + ", ID_Fornecedor=" + ID_Fornecedor + ", fornecedor=" + fornecedor + ", id_item=" + id_item + ", idCotacao=" + idCotacao + ", id_pedido=" + id_pedido + ", n_item_pedido=" + n_item_pedido + ", idCadastro=" + idCadastro + ", descricao=" + descricao + ", codigo=" + codigo + ", marca=" + marca + ", quantidade=" + quantidade + ", unidade=" + unidade + ", valor_unit=" + valor_unit + ", valor_total=" + valor_total + ", status_fechamento=" + status_fechamento + '}';
    }
        
    
    
    
}
