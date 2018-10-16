/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Beans.PedidoAlmoxarifadoItens;
import Utilitarios.Corretores;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class TableModelPedidoMercadoria extends AbstractTableModel {

    private List<PedidoAlmoxarifadoItens> linhas;

    public TableModelPedidoMercadoria() {
        linhas = new ArrayList<PedidoAlmoxarifadoItens>();
    }

    public TableModelPedidoMercadoria(List<PedidoAlmoxarifadoItens> listaDeFretes) {
        linhas = new ArrayList<PedidoAlmoxarifadoItens>(listaDeFretes);
    }

    private String[] colunas = new String[]{"ID", "Fazenda", "Nº Pedido","Data", "Nº Item", "Id Solicitante", "Solicitante","Id_Item", "Codigo",
        "Descrição", "Quantidade", "Unid", "Id Setor", "Setor","Id Invent.", "Nº Frota", "Id Urgencia", "Urgencia", "Id Status Item", "Status", "Nº Solicitacao", "Nº Compra"};

    public final int ID = 0;
    public final int FAZENDA = 1;
    public final int ID_PEDIDO = 2;
    public final int DATA = 3;
    public final int N_ITEM = 4;
    public final int ID_SOLICITANTE = 5;
    public final int SOLICITANTE = 6;
    public final int ID_ITEM = 7;
    public final int CODIGO = 8;
    public final int DESCRICAO = 9;
    public final int QUANTIDADE = 10;
    public final int UNIDADE = 11;
    public final int ID_SETOR = 12;
    public final int SETOR = 13;
    public final int ID_INVENTARIO = 14;
    public final int INVENTARIO = 15;
    public final int ID_URGENCIA = 16;
    public final int URGENCIA = 17;
    public final int ID_STATUS_ITEM = 18;
    public final int STATUS_ITEM = 19;
    public final int ID_SOLICITACAO = 20;
    public final int ID_COMPRA = 21;

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case ID:
                return Integer.class;
            case FAZENDA:
                return String.class;    
            case ID_PEDIDO:
                return Integer.class;
            case DATA:
                return String.class;    
            case N_ITEM:
                return Integer.class;
            case ID_SOLICITANTE:
                return Integer.class;
            case SOLICITANTE:
                return String.class;    
            case ID_ITEM:
                return Integer.class;
            case CODIGO:
                return String.class;
            case DESCRICAO:
                return String.class;
            case QUANTIDADE:
                return Double.class;
            case UNIDADE:
                return String.class;
            case ID_SETOR:
                return Integer.class;
            case SETOR:
                return String.class;    
            case ID_INVENTARIO:
                return Integer.class;
            case INVENTARIO:
                return String.class;
            case ID_URGENCIA:
                return Integer.class;
            case URGENCIA:
                return String.class;
            case ID_STATUS_ITEM:
                return Integer.class;
            case STATUS_ITEM:
                return String.class;
            case ID_SOLICITACAO:
                return Integer.class;
            case ID_COMPRA:
                return Integer.class;
            default:
                throw new IndexOutOfBoundsException("column out of bounds " + columnIndex);
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PedidoAlmoxarifadoItens beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                return beans.getId();
            case FAZENDA:
                return beans.getIdPedido().getIdFazenda();
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
                return beans.getIdSetor();
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
                if (beans.getIdSolicitacao() == null){
                    return 0L;
                }
                return beans.getIdSolicitacao().getId();
            case ID_COMPRA:
                return beans.getIdCompra();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        PedidoAlmoxarifadoItens beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                beans.setId((Long) aValue);
                break;
            case QUANTIDADE:
                beans.setQuantidade((Double) aValue);
                break;
            case N_ITEM:
                beans.setnItem((Integer) aValue);
                break;
           case STATUS_ITEM:
                beans.setStatusItem();
                break;
           case ID_STATUS_ITEM:
                beans.setIdStatusItem((Integer) aValue);
                break;     
           /* case ID_MARCA_APLIC:
                beans.setIdMarcaAplic((Integer) aValue);
                break;
            case ID_MODELO_APLIC:
                beans.setIdModeloAplica((Integer) aValue);
                break;
            case MARCA:
                beans.setMarca((String) aValue);
                break;
            case MODELO:
                beans.setModelo((String) aValue);
                break;*/
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        fireTableCellUpdated(rowIndex, columnIndex);

    }

    @Override
    public boolean isCellEditable(int row, int columnIndex) {
        if (columnIndex == QUANTIDADE || columnIndex == STATUS_ITEM){
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

    public PedidoAlmoxarifadoItens getBeans(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addBeans(PedidoAlmoxarifadoItens beans) {
        linhas.add(beans);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<PedidoAlmoxarifadoItens> beans) {
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

    public List<PedidoAlmoxarifadoItens> getLista() {
        return linhas;
    }

}
