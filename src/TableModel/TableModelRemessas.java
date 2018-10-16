/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Beans.RemessaMercadoriaItens;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class TableModelRemessas extends AbstractTableModel {

    private List<RemessaMercadoriaItens> linhas;

    public TableModelRemessas() {
        linhas = new ArrayList<RemessaMercadoriaItens>();
    }

    public TableModelRemessas(List<RemessaMercadoriaItens> listaDeFretes) {
        linhas = new ArrayList<RemessaMercadoriaItens>(listaDeFretes);
    }

    private String[] colunas = new String[]{"ID", "Item Pedido", "Nº Pedido", "Nº Item", "Código", "Descrição", "Quantidade",
        "Unidade", "Recebedor", "Aplicação"};

    public final int ID = 0;
    public final int ITEM_PEDIDO = 1;
    public final int N_PEDIDO = 2;
    public final int N_ITEM = 3;
    public final int CODIGO = 4;
    public final int DESCRICAO = 5;
    public final int QUANTIDADE = 6;
    public final int UNIDADE = 7;
    public final int RECEBEDOR = 8;
    public final int INVENTARIO = 9;

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
                return Integer.class;
            case ITEM_PEDIDO:
                return Long.class;
            case N_PEDIDO:
                return String.class;
            case N_ITEM:
                return String.class;
            case CODIGO:
                return String.class;
            case DESCRICAO:
                return String.class;
            case QUANTIDADE:
                return Double.class;
            case UNIDADE:
                return String.class;
            case RECEBEDOR:
                return String.class;
            case INVENTARIO:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("column out of bounds");
        }
    }

    @Override
    public boolean isCellEditable(int row, int columnIndex) {
        //Apenas o campo ATIVO será editavel
        // if (columnIndex == DESCCONTA || columnIndex == ID || columnIndex == IDPAGTO ){
        //      return false;
        //}
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RemessaMercadoriaItens beans = linhas.get(rowIndex);
        if (beans.getItemPedido() != null) {
                switch (columnIndex) {
                    case ID:
                        return beans.getID();
                    case ITEM_PEDIDO:
                        return beans.getItemPedido().getId();
                    case N_PEDIDO:
                        return beans.getItemPedido().getIdPedido().getId();
                    case N_ITEM:
                        return beans.getItemPedido().getnItem();
                    case CODIGO:
                        return beans.getItemPedido().getCodigo();
                    case DESCRICAO:
                        return beans.getItemPedido().getDescricao();
                    case QUANTIDADE:
                        return beans.getItemPedido().getQuantidade();
                    case UNIDADE:
                        return beans.getItemPedido().getUnidade();
                    case RECEBEDOR:
                        return beans.getItemPedido().getSolicitante();
                    case INVENTARIO:
                        return beans.getItemPedido().getInventario();
                    default:
                        throw new IndexOutOfBoundsException("columnIndex out of bounds");
                }
        } else { // Caso o item seja adicionado manualmente
            switch (columnIndex) {
                case ID:
                    return beans.getID();
                case ITEM_PEDIDO:
                    return "-";
                case N_PEDIDO:
                    return beans.getnPedido();
                case N_ITEM:
                    return beans.getnItem();
                case CODIGO:
                    return beans.getCodigo();
                case DESCRICAO:
                    return beans.getDescricao();
                case QUANTIDADE:
                    return beans.getQuantidade();
                case UNIDADE:
                    return beans.getUnidade();
                case RECEBEDOR:
                    return beans.getRecebedor();
                case INVENTARIO:
                    return beans.getInventario();
                default:
                    throw new IndexOutOfBoundsException("columnIndex out of bounds");
            }
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        RemessaMercadoriaItens beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                beans.setID((Long) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public RemessaMercadoriaItens getBeans(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addBeans(RemessaMercadoriaItens beans) {
        linhas.add(beans);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<RemessaMercadoriaItens> beans) {
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

    public List<RemessaMercadoriaItens> getLista() {
        return linhas;
    }

    public void editarItem(RemessaMercadoriaItens item, int rowIndex){
        linhas.set(rowIndex, item);
        this.fireTableRowsUpdated(rowIndex, rowIndex);
    }
    
}
