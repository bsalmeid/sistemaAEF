package TreeModel;


import Beans.PedidoAlmoxarifadoItens;
import Beans.PedidosAlmoxarifadoCotacao;
import Utilitarios.Corretores;
import java.text.DecimalFormat;
import java.util.List;
import org.jdesktop.swingx.treetable.AbstractTreeTableModel;

public class TreeTableAnaliseMercadoria extends AbstractTreeTableModel {

    private final static String[] COLUMN_NAMES = {"ID", "Nº Pedido", "Data", "Nº Item", "Id Solicitante", "Solicitante", "Id_Item", "Codigo",
        "Descrição", "Quantidade", "Unid", "S", "Setor", "Id Invent.", "Nº Frota", "Id Urgencia", "Urgencia", "Id Status Item", "Status", "Id Solicitacao", "Id Compra"};

    private List<PedidoAlmoxarifadoItens> listaItens;
    private List<PedidosAlmoxarifadoCotacao> listaCotacoes;

    public final int ID = 0;
    public final int ID_PEDIDO = 1;
    public final int DATA = 2;
    public final int N_ITEM = 3;
    public final int ID_SOLICITANTE = 4;
    public final int SOLICITANTE = 5;
    public final int ID_ITEM = 6;
    public final int CODIGO = 7;
    public final int DESCRICAO = 8;
    public final int QUANTIDADE = 9;
    public final int UNIDADE = 10;
    public final int ID_SETOR = 11;
    public final int SETOR = 12;
    public final int ID_INVENTARIO = 13;
    public final int INVENTARIO = 14;
    public final int ID_URGENCIA = 15;
    public final int URGENCIA = 16;
    public final int ID_STATUS_ITEM = 17;
    public final int STATUS_ITEM = 18;
    public final int ID_SOLICITACAO = 19;
    public final int ID_COMPRA = 20;

    public TreeTableAnaliseMercadoria(List<PedidoAlmoxarifadoItens> listaItens) {
        super(new Object());
        this.listaItens = listaItens;
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public boolean isCellEditable(Object node, int column) {
        if (node instanceof PedidosAlmoxarifadoCotacao) {
            if (column == 11) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isLeaf(Object node) {
        return node instanceof PedidosAlmoxarifadoCotacao;
    }

    @Override
    public int getChildCount(Object parent) {
        if (parent instanceof PedidoAlmoxarifadoItens) {
            PedidoAlmoxarifadoItens item = (PedidoAlmoxarifadoItens) parent;
            return item.getListaCotacoes().size();
        }
        return listaItens.size();
    }

    @Override
    public Object getChild(Object parent, int index) {
        if (parent instanceof PedidoAlmoxarifadoItens) {
            PedidoAlmoxarifadoItens item = (PedidoAlmoxarifadoItens) parent;
            return item.getListaCotacoes().get(index);
        }
        return listaItens.get(index);
        
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        PedidoAlmoxarifadoItens item = (PedidoAlmoxarifadoItens) parent;
        PedidosAlmoxarifadoCotacao cotacao = (PedidosAlmoxarifadoCotacao) child;
        return item.getListaCotacoes().indexOf(cotacao);
    }

    @Override
    public Object getValueAt(Object node, int column) {
        if (node instanceof PedidoAlmoxarifadoItens) {
            PedidoAlmoxarifadoItens beans = (PedidoAlmoxarifadoItens) node;
            switch (column) {
                case ID:
                    return beans.getId();
                case ID_PEDIDO:
                    return beans.getIdPedido().getId();
                case DATA:
                    return Corretores.ConverterDateStringDDMMAAA(beans.getData());
                case N_ITEM:
                    return beans.getnItem();
                case ID_SOLICITANTE:
                    return beans.getIdSolicitante();
                case SOLICITANTE:
                    return beans.getSolicitante();
                case ID_ITEM:
                    return beans.getIdItem();
                case CODIGO:
                    return beans.getCodigo();
                case DESCRICAO:
                    return beans.getDescricao();
                case QUANTIDADE:
                    return beans.getQuantidade();
                case UNIDADE:
                    return beans.getUnidade();
                case ID_SETOR:
                    return beans.isSelected();
                case SETOR:
                    return beans.getSetor();
                case ID_INVENTARIO:
                    return beans.getIdInventario();
                case INVENTARIO:
                    return beans.getInventario();
                case ID_URGENCIA:
                    return beans.getIdUrgencia();
                case URGENCIA:
                    return beans.getUrgencia();
                case ID_STATUS_ITEM:
                    return beans.getIdStatusItem();
                case STATUS_ITEM:
                    return beans.getStatusItem();
                case ID_SOLICITACAO:
                    return beans.getIdSolicitacao();
                case ID_COMPRA:
                    return beans.getIdCompra();
                default:
                    throw new IndexOutOfBoundsException("columnIndex out of bounds");
            }
        } else if (node instanceof PedidosAlmoxarifadoCotacao) {
            PedidosAlmoxarifadoCotacao cotacao = (PedidosAlmoxarifadoCotacao) node;
            switch (column) {
                case ID_INVENTARIO:
                    return cotacao.getIdFornecedor(); // Fornecedor
                case ID_SOLICITANTE:
                    return cotacao.getId();
                case DESCRICAO:
                    return cotacao.getFornecedor();
                case QUANTIDADE:
                    return cotacao.getMarcaPeca();
                case UNIDADE:
                    return new DecimalFormat("R$ #,##0.00").format(cotacao.getValorUnit());
                case ID_SETOR:
                    return cotacao.isItem_selecionado();
                default:
            }
        }

        return null;
    }

    public void setValueCotacao(Long IdItem, Long IdCotacao, boolean b) {

        for (int i = 0; i < listaItens.size(); i++) {
            if (listaItens.get(i).getId() == IdItem) {
                List<PedidosAlmoxarifadoCotacao> cotacao = listaItens.get(i).getListaCotacoes();
                for (int y = 0; y < cotacao.size(); y++) {
                    Long ID = cotacao.get(y).getId();
                    if (ID == IdCotacao) {
                        cotacao.get(y).setItem_selecionado(b);
                    } else {
                        cotacao.get(y).setItem_selecionado(!b);
                    }
                }
            }
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case ID:
                return Long.class;
            case DATA:
                return String.class;
            case ID_SOLICITANTE:
                return Long.class;
            case ID_ITEM:
                return Integer.class;
            case CODIGO:
                return String.class;
            case DESCRICAO:
                return String.class;
            case QUANTIDADE:
                return Double.class;
          //  case ID_SETOR:
          //      return Boolean.class;
            case SETOR:
                return String.class;
            default:
                return String.class;
        }
    }

    public void addLista(List<PedidoAlmoxarifadoItens> beans) {
        int indice = getRowCount();
        listaItens.addAll(beans);

    }

    private int getRowCount() {
        return listaItens.size();
    }

    public List<PedidoAlmoxarifadoItens> getListPrincipal(){
        return this.listaItens;
    }
 
    
}
