package TreeModel;

import Beans.FornecedoresBeans;
import Beans.PedidosAlmoxarifadoFechamentoItens;
import java.util.List;
import javax.swing.tree.TreeModel;
import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.treetable.AbstractTreeTableModel;

public class TreeTableFechamento extends AbstractTreeTableModel implements TreeModel {

    private final static String[] COLUMN_NAMES = {"ID Fornecedor", "Fornecedor", "Id Item", "Nº Pedido",
        "Nº Item", "Id Cadastro", "Descricao", "Codigo", "Quantidade", "Unid", "Valor Unit", "Valor Total"};

    private List<FornecedoresBeans> listaFornecedor;
    private List<PedidosAlmoxarifadoFechamentoItens> listaFechamento;
    
    JXTreeTable table;

    public final int ID_FORNECEDOR = 0;
    public final int FORNECEDOR = 1;
    public final int ID_ITEM = 2;
    public final int ID_PEDIDO = 3;
    public final int N_ITEM_PEDIDO = 4;
    public final int ID_CADASTRO = 5;
    public final int DESCRICAO = 6;
    public final int CODIGO = 7;
    public final int QUANTIDADE = 8;
    public final int UNIDADE = 9;
    public final int VALOR_UNIT = 10;
    public final int VALOR_TOTAL = 11;

    public TreeTableFechamento(List<FornecedoresBeans> listaFornecedor, JXTreeTable table) {
        super(new Object());
        this.listaFornecedor = listaFornecedor;
        this.table = table;
    }

    public TreeTableFechamento() {

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
        if (node instanceof PedidosAlmoxarifadoFechamentoItens) {
            if (column == 8) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isLeaf(Object node) {
        return node instanceof PedidosAlmoxarifadoFechamentoItens;
    }

    @Override
    public int getChildCount(Object parent) {
        if (parent instanceof FornecedoresBeans) {
            FornecedoresBeans fornecedor = (FornecedoresBeans) parent;
            return fornecedor.getListaFechamento().size();
        }
        return listaFornecedor.size();
    }

    @Override
    public Object getChild(Object parent, int index) {
        if (parent instanceof FornecedoresBeans) {
            FornecedoresBeans fornecedor = (FornecedoresBeans) parent;
            return fornecedor.getListaFechamento().get(index);
        }
        return listaFornecedor.get(index);
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        FornecedoresBeans item = (FornecedoresBeans) parent;
        PedidosAlmoxarifadoFechamentoItens cotacao = (PedidosAlmoxarifadoFechamentoItens) child;
        return item.getListaFechamento().indexOf(cotacao);
    }

    @Override
    public Object getValueAt(Object node, int column) {
        if (node instanceof FornecedoresBeans) {
            FornecedoresBeans beans = (FornecedoresBeans) node;
            switch (column) {
                case ID_FORNECEDOR:
                    return beans.getID();
                case FORNECEDOR:
                    return beans.getNomeFantasia();
                case VALOR_TOTAL:
                    return beans.getValorTotalFechamento();
                default:
                //throw new IndexOutOfBoundsException("columnIndex out of bounds");
            }
        } else if (node instanceof PedidosAlmoxarifadoFechamentoItens) {
            PedidosAlmoxarifadoFechamentoItens f = (PedidosAlmoxarifadoFechamentoItens) node;
            switch (column) {
                case FORNECEDOR:
                    return f.getId_item().getId();
                case ID_ITEM:
                    return f.getId_item().getId(); // Fornecedor
                case ID_PEDIDO:
                    return f.getId_item().getIdPedido().getId();
                case N_ITEM_PEDIDO:
                    return f.getN_item_pedido();
                case ID_CADASTRO:
                    return f.getIdCadastro();
                case DESCRICAO:
                    return f.getDescricao();
                case CODIGO:
                    return f.getCodigo();
                case QUANTIDADE:
                    return f.getQuantidade();
                case UNIDADE:
                    return f.getUnidade();
                case VALOR_UNIT:
                    return f.getValor_unit();
                case VALOR_TOTAL:
                    return f.getValor_total();
                default:
                //throw new IndexOutOfBoundsException("columnIndex out of bounds");
            }
        }

        return null;
    }

    public void setListaFornecedor(List<FornecedoresBeans> listaFornecedor) {
        this.listaFornecedor = listaFornecedor;
    }

    public List<FornecedoresBeans> getListaFornecedor() {
        return listaFornecedor;
    }

    @Override
    public void setValueAt(Object aValue, Object node, int column) {
        if (node instanceof PedidosAlmoxarifadoFechamentoItens) {
            PedidosAlmoxarifadoFechamentoItens f = (PedidosAlmoxarifadoFechamentoItens) node;
            switch (column) {
                case QUANTIDADE:
                    f.getIdCotacao().setQuant_fechamento(Double.valueOf(aValue.toString().replace(",", ".")));
                    f.setQuantidade(Double.valueOf(aValue.toString().replace(",", ".")));
                    break;
                case VALOR_TOTAL:
                    f.setValor_total((Double)aValue);
                    break;
                    
                default:
            }
        }
    }

}
