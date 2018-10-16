/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Beans.PedidosAlmoxarifadoCotacao;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class TableModelPedidoCotacaoConsulta extends AbstractTableModel {

    private List<PedidosAlmoxarifadoCotacao> linhas;

    public TableModelPedidoCotacaoConsulta() {
        linhas = new ArrayList<PedidosAlmoxarifadoCotacao>();
    }

    public TableModelPedidoCotacaoConsulta(List<PedidosAlmoxarifadoCotacao> listaDeFretes) {
        linhas = new ArrayList<PedidosAlmoxarifadoCotacao>(listaDeFretes);
    }

    private String[] colunas = new String[]{"Id Solic.", "Item Pedido", "Nº Pedido", "Nº Item", "Id Cadastro", "Quant. Pedido",
             "Quant. Comprado", "Unidade", "Descricao", "Código", "Fornecedor", "Item Original", "Valor", "Marca", "Prazo Entrega", "Item Comprado"};

    public final int ID_SOLICITACAO = 0;
    public final int ID_ITEM_PEDIDO = 1;
    public final int ID_PEDIDO = 2;
    public final int N_ITEM_PEDIDO = 3;
    public final int CADASTRO = 4;
    public final int QUANT_PEDIDO = 5;
    public final int QUANT_COMPRADA = 6;
    public final int UNIDADE = 7;
    public final int DESCRICAO = 8;
    public final int CODIGO = 9;
    public final int FORNECEDOR = 10;
    public final int ITEM_ORIGINAL = 11;
    public final int VALOR = 12;
    public final int MARCA = 13;
    public final int PRAZO_ENTREGA = 14;
    public final int ITEM_COMPRADO = 15;

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case ID_SOLICITACAO:
                return Long.class;
            case ID_ITEM_PEDIDO:
                return Long.class;
            case ID_PEDIDO:
                return Long.class;
            case N_ITEM_PEDIDO:
                return Integer.class;
            case CADASTRO:
                return Integer.class;
            case QUANT_PEDIDO:
                return Double.class;
            case QUANT_COMPRADA:
                return Double.class;
            case UNIDADE:
                return String.class;
            case DESCRICAO:
                return String.class;
            case CODIGO:
                return String.class;
            case FORNECEDOR:
                return String.class;
            case ITEM_ORIGINAL:
                return Boolean.class;
            case VALOR:
                return Double.class;
            case MARCA:
                return String.class;
            case PRAZO_ENTREGA:
                return String.class;
            case ITEM_COMPRADO:
                return Boolean.class;
            default:
                throw new IndexOutOfBoundsException("column out of bounds " + columnIndex);
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PedidosAlmoxarifadoCotacao beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID_SOLICITACAO:
                return beans.getIdSolicitacao().getId();
            case ID_ITEM_PEDIDO:
                return beans.getIdItemPedido().getId();
            case ID_PEDIDO:
                return beans.getIdItemPedido().getIdPedido();
            case N_ITEM_PEDIDO:
                return beans.getIdItemPedido().getnItem();
            case CADASTRO:
                return beans.getIdItemPedido().getIdItem();
            case QUANT_PEDIDO:
                return beans.getIdItemPedido().getQuantidade();
            case QUANT_COMPRADA:
                return beans.getQuant_fechamento();
            case UNIDADE:
                return beans.getIdItemPedido().getUnidade();
            case DESCRICAO:
                return beans.getIdItemPedido().getDescricao();
            case CODIGO:
                return beans.getIdItemPedido().getCodigo();
            case FORNECEDOR:
                return beans.getFornecedor();
            case ITEM_ORIGINAL:
                return beans.getItem_original();    
            case VALOR:
                return beans.getValorUnit();
            case MARCA:
                return beans.getMarcaPeca();
            case PRAZO_ENTREGA:
                return "";
            case ITEM_COMPRADO:
                return beans.isItem_comprado();    
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        PedidosAlmoxarifadoCotacao beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case VALOR:
                beans.setValorUnit((Double) aValue);
                break;
/*            case CADASTRO:
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
                if (aValue instanceof Double) {
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
                break;*/
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        fireTableCellUpdated(rowIndex, columnIndex);

    }

    @Override
    public boolean isCellEditable(int row, int columnIndex) {
        return columnIndex == VALOR;
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

    public PedidosAlmoxarifadoCotacao getBeans(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addBeans(PedidosAlmoxarifadoCotacao beans) {
        linhas.add(beans);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<PedidosAlmoxarifadoCotacao> beans) {
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

    public List<PedidosAlmoxarifadoCotacao> getLista() {
        return linhas;
    }

}
