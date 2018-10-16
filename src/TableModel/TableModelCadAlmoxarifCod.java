/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Almoxarifado.CadItensAlmoxCodigos;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class TableModelCadAlmoxarifCod extends AbstractTableModel {

    private List<CadItensAlmoxCodigos> linhas;

    public TableModelCadAlmoxarifCod() {
        linhas = new ArrayList<CadItensAlmoxCodigos>();
    }

    public TableModelCadAlmoxarifCod(List<CadItensAlmoxCodigos> listaDeFretes) {
        linhas = new ArrayList<CadItensAlmoxCodigos>(listaDeFretes);
    }

    private String[] colunas = new String[]{"ID", "ID_ITEM", "Código", "Original", "ID_Fornecedor",
        "CNPJ_Fornecedor", "Fornecedor"};

    public final int ID = 0;
    public final int ID_ITEM = 1;
    public final int CODIGO = 2;
    public final int CODIGO_CATALOGO = 3;
    public final int ID_FORNECEDOR = 4;
    public final int CNPJ_FORNECEDOR = 5;
    public final int FORNECEDOR = 6;

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
            case ID_ITEM:
                return Integer.class;
            case CODIGO:
                return String.class;
            case CODIGO_CATALOGO:
                return Boolean.class;
            case ID_FORNECEDOR:
                return Integer.class;
            case CNPJ_FORNECEDOR:
                return String.class;
            case FORNECEDOR:
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
        CadItensAlmoxCodigos beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                return beans.getID();
            case ID_ITEM:
                return beans.getId_item().getID();
            case CODIGO:
                return beans.getCodigo();
            case CODIGO_CATALOGO:
                return beans.getCodigoCatalogo();
            case ID_FORNECEDOR:
                if (beans.getId_fornecedor() == null) {
                    return null;
                }
                return beans.getId_fornecedor().getID();
            case CNPJ_FORNECEDOR:
                if (beans.getId_fornecedor() == null) {
                    return "-";
                }
                return beans.getId_fornecedor().getCnpj();
            case FORNECEDOR:
                if (beans.getFornecedor() != null && !beans.getFornecedor().equals("")) {
                    return beans.getFornecedor();
                }
                return "-";
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        CadItensAlmoxCodigos beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                beans.setID((Integer) aValue);
                break;
            case ID_ITEM:
                beans.setID_ITEM((Integer) aValue);
                break;
            case CODIGO:
                beans.setCodigo((String) aValue);
                break;
            case CODIGO_CATALOGO:
                beans.setCodigoCatalogo((Boolean) aValue);
                break;
            case ID_FORNECEDOR:
                beans.setID_Fornecedor((Integer) aValue);
                break;
            case CNPJ_FORNECEDOR:
                beans.setCNPJ_Fornecedor((String) aValue);
                break;
            case FORNECEDOR:
                beans.setFornecedor((String) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        fireTableCellUpdated(rowIndex, columnIndex);//notifica a atualização da celula

    }

    public CadItensAlmoxCodigos getBeans(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addBeans(CadItensAlmoxCodigos beans) {
        linhas.add(beans);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<CadItensAlmoxCodigos> beans) {
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

    public List<CadItensAlmoxCodigos> getLista() {
        return linhas;
    }

}
