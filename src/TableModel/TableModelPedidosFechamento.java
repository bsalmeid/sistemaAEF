/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Beans.PedidoAlmoxarifadoItens;
import Beans.PedidosAlmoxarifadoCotacao;
import Beans.PedidosAlmoxarifadoFechamentoItens;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class TableModelPedidosFechamento extends AbstractTableModel {

    private List<PedidosAlmoxarifadoFechamentoItens> linhas;

    public TableModelPedidosFechamento() {
        linhas = new ArrayList<PedidosAlmoxarifadoFechamentoItens>();
    }

    public TableModelPedidosFechamento(List<PedidosAlmoxarifadoFechamentoItens> listaDeFretes) {
        linhas = new ArrayList<PedidosAlmoxarifadoFechamentoItens>(listaDeFretes);
    }

    private String[] colunas = new String[]{"ID", "Nº Fechamento","ID Fornecedor", "Fornecedor", "Pedido Item", "Id Cotacao","Fazenda" ,"Nº Pedido", "Nº Item", "ID Cadastro",
        "Descrição", "Código", "Marca", "Quantidade", "Unidade", "Valor Unitário", "Valor Total", "Status"};

    public final int ID = 0;
    public final int ID_FECHAMENTO = 1;
    public final int ID_FORNECEDOR = 2;
    public final int FORNECEDOR = 3;
    public final int PEDIDO_ALMOXARIFADO_ITEM = 4;
    public final int ID_COTACAO = 5;
    public final int FAZENDA = 6;
    public final int ID_PEDIDO = 7;
    public final int N_ITEM_PEDIDO = 8;
    public final int ID_CADASTRO = 9;
    public final int DESCRICAO = 10;
    public final int CODIGO = 11;
    public final int MARCA = 12;
    public final int QUANTIDADE = 13;
    public final int UNIDADE = 14;
    public final int VALORUNIT = 15;
    public final int VALOR_TOTAL = 16;
    public final int STATUS_FECHAMENTO = 17;

    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    public void excluirLinha(int rowIndex) {
        linhas.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case ID:
                return Long.class;
            case ID_FECHAMENTO:
                return Long.class;
            case ID_FORNECEDOR:
                return Integer.class;
            case FORNECEDOR:
                return String.class;
            case PEDIDO_ALMOXARIFADO_ITEM:
                return PedidoAlmoxarifadoItens.class;
            case ID_COTACAO:
                return PedidosAlmoxarifadoCotacao.class;
            case FAZENDA:
                return String.class;
            case ID_PEDIDO:
                return Long.class;
            case N_ITEM_PEDIDO:
                return Integer.class;
            case ID_CADASTRO:
                return Integer.class;
            case DESCRICAO:
                return String.class;
            case CODIGO:
                return String.class;
            case MARCA:
                return String.class;
            case QUANTIDADE:
                return Double.class;
            case UNIDADE:
                return String.class;
            case VALORUNIT:
                return Double.class;
            case VALOR_TOTAL:
                return Double.class;
            case STATUS_FECHAMENTO:
                return Boolean.class;
            default:
                throw new IndexOutOfBoundsException("column out of bounds");
        }
    }

    @Override
    public boolean isCellEditable(int row, int columnIndex) {
        if (columnIndex == QUANTIDADE || columnIndex == VALORUNIT) {
            return true;
        }
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PedidosAlmoxarifadoFechamentoItens beans = linhas.get(rowIndex);
        return getSwitchCase(columnIndex, beans);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        PedidosAlmoxarifadoFechamentoItens beans = linhas.get(rowIndex);
        if (beans.getID() == 0L) {
            //System.out.println(" ID == 0L");
            switch (columnIndex) {
                case QUANTIDADE:
                    beans.setQuantidade((Double) aValue);
                    beans.getId_item().setQuantidade((Double) aValue);
                    break;
                case VALORUNIT:
                    beans.setValor_unit((Double) aValue);
                    beans.getIdCotacao().setValorUnit((Double) aValue);
                    beans.setValor_total(beans.getId_item().getQuantidade() * beans.getIdCotacao().getValorUnit());
                    break;
                case VALOR_TOTAL:
                    beans.setValor_total(beans.getId_item().getQuantidade() * beans.getIdCotacao().getValorUnit());
                    break;    
                default:
                    throw new IndexOutOfBoundsException("columnIndex out of bounds");
            }
        } else {
            //  System.out.println(" ID != 0L");
            switch (columnIndex) {
                case QUANTIDADE:
                    beans.setQuantidade((Double) aValue);
                    beans.setValor_total(beans.getQuantidade() * beans.getValor_unit());
                    break;
                case VALORUNIT:
                    beans.setValor_unit((Double) aValue);
                    beans.setValor_total(beans.getQuantidade() * beans.getValor_unit());
                    break;
                case VALOR_TOTAL:
                    beans.setValor_total(beans.getQuantidade() * beans.getValor_unit());
                    break;    
                default:
                    throw new IndexOutOfBoundsException("columnIndex out of bounds");
            }
        }
        
        fireTableCellUpdated(rowIndex, columnIndex);//notifica a atualização da celula
    }

    public PedidosAlmoxarifadoFechamentoItens getBeans(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addBeans(PedidosAlmoxarifadoFechamentoItens beans) {
        linhas.add(beans);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<PedidosAlmoxarifadoFechamentoItens> beans) {
        int indice = getRowCount();
        linhas.addAll(beans);
        fireTableRowsInserted(indice, indice + beans.size());
    }

    public void deletarUltimaLinha() {
        int ultimoIndice = getRowCount() - 1;
        linhas.remove(ultimoIndice);
        fireTableRowsDeleted(ultimoIndice, ultimoIndice);
    }

    public void limpar() {
        linhas.clear();
        fireTableDataChanged();
    }

    public List<PedidosAlmoxarifadoFechamentoItens> getLista() {
        return linhas;
    }

    public Object getSwitchCase(int columnIndex, PedidosAlmoxarifadoFechamentoItens beans) {
        if (beans.getID() == 0L) {
             //System.out.println("Teste  Table Model == 0L");
             // Caso a quantidade da cotação seja = 0 utilizar a quantidade original pedida no item, caso contrario utilizar a conquantidade da cotação
             Double quantidade = (beans.getIdCotacao().getQuant_fechamento() > 0 ? beans.getIdCotacao().getQuant_fechamento() : beans.getId_item().getQuantidade());
            switch (columnIndex) {
                case ID:
                    return beans.getID();
                case ID_FECHAMENTO:
                    return 0L;   
                case ID_FORNECEDOR:
                    return beans.getIdCotacao().getIdFornecedor();
                case FORNECEDOR:
                    return beans.getIdCotacao().getFornecedor();
                case PEDIDO_ALMOXARIFADO_ITEM:
                    return beans.getId_item().getId();
                case ID_COTACAO:
                    return beans.getIdCotacao().getId();
                case FAZENDA:
                    return beans.getFazenda();     
                case ID_PEDIDO:
                    return beans.getId_item().getIdPedido().getId();
                case N_ITEM_PEDIDO:
                    return beans.getId_item().getnItem();
                case ID_CADASTRO:
                    return beans.getId_item().getIdItem();
                case DESCRICAO:
                    return beans.getId_item().getDescricao();
                case CODIGO:
                    return beans.getId_item().getCodigo();
                case MARCA:
                    return beans.getIdCotacao().getMarcaPeca();
                case QUANTIDADE:
                    return quantidade;
                case UNIDADE:
                    return beans.getId_item().getUnidade();
                case VALORUNIT:
                    return beans.getIdCotacao().getValorUnit();
                case VALOR_TOTAL:
                    return quantidade * beans.getIdCotacao().getValorUnit();
                case STATUS_FECHAMENTO:
                    return beans.isStatus_fechamento();
                default:
                    throw new IndexOutOfBoundsException("columnIndex out of bounds");
            }
        } else {
            //System.out.println("Teste  Table Model != 0");
            switch (columnIndex) {
                case ID:
                    return beans.getID();
                case ID_FECHAMENTO:
                    return beans.getFechamento().getId();       
                case ID_FORNECEDOR:
                    return beans.getID_Fornecedor();
                case FORNECEDOR:
                    return beans.getFornecedor();
                case PEDIDO_ALMOXARIFADO_ITEM:
                    return beans.getId_item().getId();
                case ID_COTACAO:
                    return beans.getIdCotacao().getId();
                case FAZENDA:
                    return beans.getFazenda();
                case ID_PEDIDO:
                    return beans.getId_pedido();
                case N_ITEM_PEDIDO:
                    return beans.getN_item_pedido();
                case ID_CADASTRO:
                    return beans.getIdCadastro();
                case DESCRICAO:
                    return beans.getDescricao();
                case CODIGO:
                    return beans.getCodigo();
                case MARCA:
                    return beans.getMarca();
                case QUANTIDADE:
                    return beans.getQuantidade();
                case UNIDADE:
                    return beans.getUnidade();
                case VALORUNIT:
                    return beans.getValor_unit();
                case VALOR_TOTAL:
                    return (beans.getValor_unit() * beans.getQuantidade());
                case STATUS_FECHAMENTO:
                    return beans.isStatus_fechamento();
                default:
                    throw new IndexOutOfBoundsException("columnIndex out of bounds");
            }
        }
    }

}
