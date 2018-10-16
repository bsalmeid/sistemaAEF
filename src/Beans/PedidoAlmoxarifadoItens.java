
package Beans;

import static GUI.Principal.listStatusItemPedido;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name = "pedidos_almoxarifado_itens")
public class PedidoAlmoxarifadoItens implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pedidos_almoxarifado_id", referencedColumnName = "id", nullable = false)
    private PedidosAlmoxarifado idPedido;

    @Column(name = "data_pedido", insertable = false)
    @Temporal(TemporalType.DATE)
    private Date data;
    
    @Column(name = "n_item", nullable = false)
    private int nItem;

    @Column(name = "id_solicitante", nullable = false)
    private int idSolicitante;

    @Column(name = "solicitante", nullable = false, length = 80)
    private String Solicitante;

    @Column(name = "id_item", nullable = false)
    private int idItem;

    @Column(name = "codigo", nullable = false, length = 120)
    private String codigo;

    @Column(name = "descricao", nullable = false, length = 250)
    private String descricao;

    @Column(name = "quantidade", nullable = false, precision = 7, scale = 2)
    private Double quantidade;

    @Column(name = "unidade", nullable = false, length = 45)
    private String unidade;

    @Column(name = "id_setor", nullable = false)
    private int idSetor;

    @Column(name = "Setor", insertable = false)
    private String Setor;

    @Column(name = "id_inventario", nullable = false)
    private int idInventario;

    @Column(name = "Inventario", insertable = false)
    private String Inventario;

    @Column(name = "id_urgencia", nullable = false)
    private Integer idUrgencia;

    @Column(name = "urgencia", nullable = false, length = 45)
    private String urgencia;

    @Column(name = "id_status_item", nullable = false)
    private int idStatusItem;

    @Transient
    private String StatusItem;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_solicitacao", nullable = true, referencedColumnName = "id")
    private PedidosAlmoxarifadoSolicitacao idSolicitacao;

    @Column(name = "id_compra", nullable = false)
    private long idCompra;

    @Column(name = "status_pedido", columnDefinition = "BIT", length = 1 ,nullable = false)
    private Boolean status_pedido;
    
    @Transient
    private List<PedidosAlmoxarifadoCotacao> listaCotacoes;
    
    @Transient
    private boolean Selected;
    
    
    @Override
    public String toString() {
        return "PedidoAlmoxarifadoItens{" + "id=" + id + ", idPedido=" + idPedido + ", nItem=" + nItem + ", idSolicitante=" + idSolicitante + ", Solicitante=" + Solicitante + ", idItem=" + idItem + ", codigo=" + codigo + ", descricao=" + descricao + ", quantidade=" + quantidade + ", unidade=" + unidade + ", idSetor=" + idSetor + ", Setor=" + Setor + ", idInventario=" + idInventario + ", Inventario=" + Inventario + ", idUrgencia=" + idUrgencia + ", urgencia=" + urgencia + '}';
    }

    public PedidoAlmoxarifadoItens(int nItem, String codigo, int idStatusItem) {
        this.nItem = nItem;
        this.codigo = codigo;
        this.idStatusItem = idStatusItem;
    }

    public PedidoAlmoxarifadoItens(Long id) {
        this.id = id;
    }
    
    public PedidoAlmoxarifadoItens() {

    }

    public String getSolicitante() {
        return Solicitante;
    }

    public String getSetor() {
        return Setor;
    }

    public void setSetor(String Setor) {
        this.Setor = Setor;
    }

    public void setSolicitante(String Solicitante) {
        this.Solicitante = Solicitante;
    }

    public Boolean getStatus_pedido() {
        return status_pedido;
    }

    public void setStatus_pedido(Boolean status_pedido) {
        this.status_pedido = status_pedido;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PedidosAlmoxarifado getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(PedidosAlmoxarifado idPedido) {
        this.idPedido = idPedido;
    }

    public int getnItem() {
        return nItem;
    }

    public void setnItem(int nItem) {
        this.nItem = nItem;
    }

    public int getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(int idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public int getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(int idSetor) {
        this.idSetor = idSetor;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public String getInventario() {
        return Inventario;
    }

    public void setInventario(String Inventario) {
        this.Inventario = Inventario;
    }

    public Integer getIdUrgencia() {
        return idUrgencia;
    }

    public void setIdUrgencia(Integer idUrgencia) {
        this.idUrgencia = idUrgencia;
    }

    public String getUrgencia() {
        return urgencia;
    }

    public void setUrgencia(String urgencia) {
        this.urgencia = urgencia;
    }

    public int getIdStatusItem() {
        return idStatusItem;
    }

    public void setIdStatusItem(int idStatusItem) {
        this.idStatusItem = idStatusItem;
    }

    public String getStatusItem() {
        return listStatusItemPedido.get(getIdStatusItem()).toString();
    }

    public void setStatusItem() {
        this.StatusItem = listStatusItemPedido.get(getIdStatusItem()).toString();
    }

    public PedidosAlmoxarifadoSolicitacao getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(PedidosAlmoxarifadoSolicitacao idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public long getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(long idCompra) {
        this.idCompra = idCompra;
    }

    public Date getData() {
        return this.data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<PedidosAlmoxarifadoCotacao> getListaCotacoes() {
        return listaCotacoes;
    }

    public void setListaCotacoes(List<PedidosAlmoxarifadoCotacao> listaCotacoes) {
        this.listaCotacoes = listaCotacoes;
    }

    public boolean isSelected() {
        return Selected;
    }

    public void setSelected(boolean Selected) {
        this.Selected = Selected;
    }

    
    
}
