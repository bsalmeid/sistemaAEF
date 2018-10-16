/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Beans.CategoriaEquipamentosBeans;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Usuario
 */
public class TableModelCadastroCategoriaInv extends AbstractTableModel {

    private List<CategoriaEquipamentosBeans> ListPrincipal = new ArrayList<>();

    private final String[] colunas = {"Id", "Categoria", "Status"};

    public final int ID_CATEGORIA = 0;
    public final int CATEGORIA = 1;
    public final int STATUS = 2;

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case ID_CATEGORIA:
                return Integer.class;
            case CATEGORIA:
                return String.class;
            case STATUS:
                return Boolean.class;
            default:
                throw new IndexOutOfBoundsException("column out of bounds getColumnClass");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CategoriaEquipamentosBeans beans = ListPrincipal.get(rowIndex);
        switch (columnIndex) {
            case ID_CATEGORIA:
                return beans.getID();
            case CATEGORIA:
                return beans.getCategoria();
            case STATUS:
                return beans.getStatus();
            default:
                throw new IndexOutOfBoundsException("column out of bounds getValueAt");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        CategoriaEquipamentosBeans beans = ListPrincipal.get(rowIndex);
        switch (columnIndex) {
            case ID_CATEGORIA:
                beans.setID(Integer.valueOf((String) aValue));
                break;
            case CATEGORIA:
                beans.setCategoria(((String) aValue));
                break;
            case STATUS:
                beans.setStatus(Boolean.valueOf((String) aValue));
                break;
            default:
                throw new IndexOutOfBoundsException("column out of bounds getValueAt");
        }
        this.fireTableRowsUpdated(rowIndex, rowIndex);
    }

    @Override
    public int getRowCount() {
        return ListPrincipal.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;

    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public boolean isCellEditable(int row, int columnIndex) {
        //Apenas o campo ATIVO será editavel
        // if (columnIndex == DESCCONTA || columnIndex == ID || columnIndex == IDPAGTO ){
        //      return false;
        //}
        return false;
    }

    public void addRow(CategoriaEquipamentosBeans C) {
        ListPrincipal.add(C);
        this.fireTableDataChanged();
    }

    public void addLista(List<CategoriaEquipamentosBeans> lista) {
        ListPrincipal = lista;
        this.fireTableDataChanged();
    }

    public void removeRow(int linha) {
        ListPrincipal.remove(linha);
        this.fireTableRowsDeleted(linha, linha);

    }

    public CategoriaEquipamentosBeans getItem(int index) {
        return ListPrincipal.get(index);

    }

    public void limpar() {
        ListPrincipal.clear();
        this.fireTableDataChanged();
    }
}
