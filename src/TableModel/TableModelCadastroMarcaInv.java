/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Beans.MarcaEquipamentosBeans;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Usuario
 */
public class TableModelCadastroMarcaInv extends AbstractTableModel {

    private List<MarcaEquipamentosBeans> ListPrincipal = new ArrayList<>();

    private final String[] colunas = {"Id", "Marca", "Status"};

    public final int ID_MARCA = 0;
    public final int MARCA = 1;
    public final int STATUS = 2;

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case ID_MARCA:
                return Integer.class;
            case MARCA:
                return String.class;
            case STATUS:
                return Boolean.class;
            default:
                throw new IndexOutOfBoundsException("column out of bounds getColumnClass");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MarcaEquipamentosBeans beans = ListPrincipal.get(rowIndex);
        switch (columnIndex) {
            case ID_MARCA:
                return beans.getID();
            case MARCA:
                return beans.getMarca();
            case STATUS:
                return beans.getStatus();
            default:
                throw new IndexOutOfBoundsException("column out of bounds getValueAt");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        MarcaEquipamentosBeans beans = ListPrincipal.get(rowIndex);
        switch (columnIndex) {
            case ID_MARCA:
                beans.setID(Integer.valueOf((String) aValue));
                break;
            case MARCA:
                beans.setMarca(((String) aValue));
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

    public void addRow(MarcaEquipamentosBeans m) {
        ListPrincipal.add(m);
        this.fireTableDataChanged();
    }

    public void addLista(List<MarcaEquipamentosBeans> lista) {
        ListPrincipal = lista;
        this.fireTableDataChanged();
    }

    public void removeRow(int linha) {
        ListPrincipal.remove(linha);
        this.fireTableRowsDeleted(linha, linha);

    }

    public MarcaEquipamentosBeans getItem(int index) {
        return ListPrincipal.get(index);

    }

    public void limpar() {
        ListPrincipal.clear();
        this.fireTableDataChanged();
    }
}
