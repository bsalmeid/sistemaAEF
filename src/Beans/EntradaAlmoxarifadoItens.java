
package Beans;

import Almoxarifado.LocalizadorAlmoxarifadoBeans;
import Almoxarifado.CadItensAlmoxarifadoBeans;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "entrada_almoxarifado_itens")
public class EntradaAlmoxarifadoItens implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_entrada", referencedColumnName = "id", nullable = true)
    private EntradaAlmoxarifadoBeans EntradaItem;
    
    @Column(name = "id_entrada", nullable = true, insertable = false, updatable = false)
    private Integer idEntradaItem;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cadastro", referencedColumnName = "id", nullable = true)
    private CadItensAlmoxarifadoBeans CadastroItem;

    @Column(name = "id_cadastro", nullable = true, insertable = false, updatable = false)
    private Integer idCadastroItem;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fornecedor", referencedColumnName = "cad_fornecedor_id", nullable = true)
    private FornecedoresBeans FornecedorItem;

    @Column(name = "id_fornecedor", nullable = true, insertable = false, updatable = false)
    private Integer idFornecedorItem;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_item_pedido", nullable = true, referencedColumnName = "id")
    private PedidoAlmoxarifadoItens PedidoItem;

    @Column(name = "id_item_pedido", nullable = true, insertable = false, updatable = false)
    private Long idPedidoItem;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_localizador", referencedColumnName = "id", nullable = true)
    private LocalizadorAlmoxarifadoBeans LocalizadorItem;

    @Column(name = "id_localizador", nullable = true, insertable = false, updatable = false)
    private Integer idLocalizadorItem;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidade", referencedColumnName = "id", nullable = true)
    private CadUnidadesBeans UnidadeItem;

    @Column(name = "unidade", nullable = true, insertable = false, updatable = false)
    private Integer idUnidadeItem;
    
    @Column(name = "n_doc_entrada", nullable = false)
    private Integer docEntrada;
    
    @Column(name = "codigoProduto", nullable = false, length = 150)
    private String codigoProduto;
    
    @Column(name = "descricao", nullable = false, length = 150)
    private String descricao;
    
    @Column(name = "quantidade", nullable = false, precision = 9 , scale = 3)
    private Double quantidade;
    
    @Column(name = "valorUnit", nullable = false, precision = 9 , scale = 2)
    
    private Double valorUnit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public EntradaAlmoxarifadoBeans getEntradaItem() {
        return EntradaItem;
    }

    public void setEntradaItem(EntradaAlmoxarifadoBeans EntradaItem) {
        this.EntradaItem = EntradaItem;
    }

    public Integer getIdEntradaItem() {
        return idEntradaItem;
    }

    public void setIdEntradaItem(Integer idEntradaItem) {
        this.idEntradaItem = idEntradaItem;
    }

    public CadItensAlmoxarifadoBeans getCadastroItem() {
        return CadastroItem;
    }

    public void setCadastroItem(CadItensAlmoxarifadoBeans CadastroItem) {
        this.CadastroItem = CadastroItem;
    }

    public Integer getIdCadastroItem() {
        return idCadastroItem;
    }

    public void setIdCadastroItem(Integer idCadastroItem) {
        this.idCadastroItem = idCadastroItem;
    }

    public FornecedoresBeans getFornecedorItem() {
        return FornecedorItem;
    }

    public void setFornecedorItem(FornecedoresBeans FornecedorItem) {
        this.FornecedorItem = FornecedorItem;
    }

    public Integer getIdFornecedorItem() {
        return idFornecedorItem;
    }

    public void setIdFornecedorItem(Integer idFornecedorItem) {
        this.idFornecedorItem = idFornecedorItem;
    }

    public PedidoAlmoxarifadoItens getPedidoItem() {
        return PedidoItem;
    }

    public void setPedidoItem(PedidoAlmoxarifadoItens PedidoItem) {
        this.PedidoItem = PedidoItem;
    }

    public Long getIdPedidoItem() {
        return idPedidoItem;
    }

    public void setIdPedidoItem(Long idPedidoItem) {
        this.idPedidoItem = idPedidoItem;
    }

    public LocalizadorAlmoxarifadoBeans getLocalizadorItem() {
        return LocalizadorItem;
    }

    public void setLocalizadorItem(LocalizadorAlmoxarifadoBeans LocalizadorItem) {
        this.LocalizadorItem = LocalizadorItem;
    }

    public Integer getIdLocalizadorItem() {
        return idLocalizadorItem;
    }

    public void setIdLocalizadorItem(Integer idLocalizadorItem) {
        this.idLocalizadorItem = idLocalizadorItem;
    }

    public CadUnidadesBeans getUnidadeItem() {
        return UnidadeItem;
    }

    public void setUnidadeItem(CadUnidadesBeans UnidadeItem) {
        this.UnidadeItem = UnidadeItem;
    }

    public Integer getIdUnidadeItem() {
        return idUnidadeItem;
    }

    public void setIdUnidadeItem(Integer idUnidadeItem) {
        this.idUnidadeItem = idUnidadeItem;
    }

    public Integer getDocEntrada() {
        return docEntrada;
    }

    public void setDocEntrada(Integer docEntrada) {
        this.docEntrada = docEntrada;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
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

    public Double getValorUnit() {
        return valorUnit;
    }

    public void setValorUnit(Double valorUnit) {
        this.valorUnit = valorUnit;
    }
    

}
