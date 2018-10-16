/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Beans.CadUsuario;
import Beans.CadUsuarioFazendas;
import Beans.PropriedadesBeans;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class TableModelUsuarioFazenda extends AbstractTableModel {

    private List<CadUsuarioFazendas> linhas;

    public TableModelUsuarioFazenda() {
        linhas = new ArrayList<CadUsuarioFazendas>();
    }

    public TableModelUsuarioFazenda(List<CadUsuarioFazendas> listaDeFretes) {
        linhas = new ArrayList<CadUsuarioFazendas>(listaDeFretes);
    }

    private String[] colunas = new String[]{"ID"," ID_USER" ,"ID Fazenda", "Fazenda", "Acesso"};

    public final int ID = 0;
    public final int ID_USER = 1;
    public final int ID_FAZENDA = 2;
    public final int FAZENDA = 3;
    public final int ACESSO = 4;

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
            case ID_USER:
                return Integer.class;
            case ID_FAZENDA:
                return Integer.class;
            case FAZENDA:
                return String.class;
            case ACESSO:
                return Boolean.class;  
            default:
                throw new IndexOutOfBoundsException("column out of bounds");
        }
    }

    @Override
    public boolean isCellEditable(int row, int columnIndex) {
        
        return columnIndex == ACESSO;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CadUsuarioFazendas beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                return beans.getId();
            case ID_USER:
                return beans.getIdUsuario().getId();
            case ID_FAZENDA:
                return beans.getIdFazenda().getCodigo();
            case FAZENDA:
                return beans.getIdFazenda().getNome();
            case ACESSO:
                return beans.getAcesso();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        CadUsuarioFazendas beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                beans.setId((Integer) aValue);
                break;
            case ID_USER:
                beans.setIdUsuario((CadUsuario) aValue);
                break;
            case ID_FAZENDA:
                beans.setIdFazenda((PropriedadesBeans) aValue);
                break;
            case ACESSO:
                beans.setAcesso((Boolean) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        fireTableCellUpdated(rowIndex, columnIndex);//notifica a atualização da celula

    }
     
    public CadUsuarioFazendas getBeans(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addBeans(CadUsuarioFazendas beans) {
        linhas.add(beans);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<CadUsuarioFazendas> beans) {
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

    public List<CadUsuarioFazendas> getLista() {
        return linhas;
    }

}
