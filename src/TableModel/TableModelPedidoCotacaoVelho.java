/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Utilitarios.Corretores;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class TableModelPedidoCotacaoVelho extends AbstractTableModel {

    private List<TbPedidosCotacao> linhas;

    public TableModelPedidoCotacaoVelho() {
        linhas = new ArrayList<TbPedidosCotacao>();
    }
    
    public TableModelPedidoCotacaoVelho(List<TbPedidosCotacao> listaDeFretes) {
        linhas = new ArrayList<TbPedidosCotacao>(listaDeFretes);
    }

    private String[] colunas = new String[]{"ID", "Cadastro", "Nº Pedido", "Nº Item", "Quantidade", "Unidade","Descricao", "Código",
      "Modelo","Nº Série", "Valor - Orig.", "Valor - M1", "Marca-1", "Valor - M2","Marca-2", "Prazo Entrega"};

    public final int ID_ITEM_PEDIDO = 0;
    public final int CADASTRO = 1;
    public final int ID_PEDIDO = 2;
    public final int N_ITEM_PEDIDO = 3;
    public final int QUANTIDADE = 4;
    public final int UNIDADE = 5;
    public final int DESCRICAO = 6;
    public final int CODIGO = 7;
    public final int MODELO = 8;
    public final int NSERIE = 9;
    public final int VALOR_ORIGINAL = 10;
    public final int VALOR_M1 = 11;
    public final int MARCA_1 = 12;
    public final int VALOR_M2 = 13;
    public final int MARCA_2 = 14;
    public final int PRAZO_ENTREGA = 15;

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case ID_ITEM_PEDIDO:
                return Long.class;
            case CADASTRO:
                return Integer.class;
            case ID_PEDIDO:
                return Long.class;    
            case N_ITEM_PEDIDO:
                return Integer.class;
            case QUANTIDADE:
                return Double.class;
            case UNIDADE:
                return String.class;    
            case DESCRICAO:
                return String.class;
            case CODIGO:
                return String.class;
            case MODELO:
                return String.class;
            case NSERIE:
                return String.class;
            case VALOR_ORIGINAL:
                return Double.class;
            case VALOR_M1:
                return Double.class;
            case MARCA_1:
                return String.class;    
            case VALOR_M2:
                return Double.class;
            case MARCA_2:
                return String.class;
            case PRAZO_ENTREGA:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("column out of bounds " + columnIndex);
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TbPedidosCotacao beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID_ITEM_PEDIDO:
                return beans.getIdItemPedido();
            case CADASTRO:
                return beans.getId_cadastro();
            case ID_PEDIDO:
                return beans.getId_Pedido();    
            case N_ITEM_PEDIDO:
                return beans.getnItemPedido();
            case QUANTIDADE:
                return beans.getQuantidade();
            case UNIDADE:
                return beans.getUnidade();    
            case DESCRICAO:
                return beans.getDescricao();
            case CODIGO:
                return beans.getCodigo();
            case MODELO:
                return beans.getModelo();
            case NSERIE:
                return beans.getnSerie();    
            case VALOR_ORIGINAL:
                return beans.getValorOriginal();
            case VALOR_M1:
                return beans.getValorMarca1();
            case MARCA_1:
                return beans.getMarca1();
            case VALOR_M2:
                return beans.getValorMarca2();
            case MARCA_2:
                return beans.getMarca2();
            case PRAZO_ENTREGA:
                return beans.getPrazoEntrega();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        TbPedidosCotacao beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID_ITEM_PEDIDO:
                beans.setIdItemPedido((Long) aValue);
                break;
            case CADASTRO:
                beans.setId_cadastro((Integer) aValue);
                break;
            case ID_PEDIDO:
                beans.setId_Pedido((Long) aValue);
                break;
           case N_ITEM_PEDIDO:
                beans.setnItemPedido((Integer) aValue);
                break;
           case QUANTIDADE:
                beans.setQuantidade((Double) aValue);
                break;     
            case UNIDADE:
                beans.setUnidade((String) aValue);
                break;
            case DESCRICAO:
                beans.setDescricao((String) aValue);
                break;
            case CODIGO:
                beans.setCodigo((String) aValue);
                break;
            case MODELO:
                beans.setModelo((String) aValue);
                break;
            case NSERIE:
                beans.setnSerie((String) aValue);
                break;    
            case VALOR_ORIGINAL:
                if (aValue instanceof Double){
                    beans.setValorOriginal((Double) aValue);
                } else {
                    beans.setValorOriginal(0.00);
                }    
                break;    
            case VALOR_M1:
                beans.setValorMarca1((Double) aValue);
                break;    
            case MARCA_1:
                beans.setMarca1((String) aValue);
                break;    
            case VALOR_M2:
                beans.setValorMarca2((Double) aValue);
                break;    
            case MARCA_2:
                beans.setMarca2((String) aValue);
                break;
            case PRAZO_ENTREGA:
                beans.setPrazoEntrega((String) aValue);
                break;    
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        fireTableCellUpdated(rowIndex, columnIndex);

    }

    @Override
    public boolean isCellEditable(int row, int columnIndex) {
        if (columnIndex == QUANTIDADE || columnIndex == VALOR_M1 || columnIndex == VALOR_M2
                || columnIndex == MARCA_1 || columnIndex == MARCA_2 || columnIndex == PRAZO_ENTREGA || columnIndex == VALOR_ORIGINAL){
              return true;
        }
        return false;
    }

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

    public TbPedidosCotacao getBeans(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addBeans(TbPedidosCotacao beans) {
        linhas.add(beans);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<TbPedidosCotacao> beans) {
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

    public List<TbPedidosCotacao> getLista() {
        return linhas;
    }

    
}
