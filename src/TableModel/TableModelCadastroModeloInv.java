/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Beans.ModeloEquipamentosBeans;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Usuario
 */
public class TableModelCadastroModeloInv extends AbstractTableModel {

    private List<ModeloEquipamentosBeans> ListPrincipal = new ArrayList<>();

    private final String[] colunas = {"Id", "Modelo", "Largura", "Status"};

    public final int ID_MODELO = 0;
    //public final int CATEGORIA = 1;
    //public final int MARCA = 2;
    public final int MODELO = 1;
    public final int LARGURA = 2;
    public final int STATUS = 3;

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case ID_MODELO:
                return Integer.class;
            //case CATEGORIA:
              //  return CategoriaEquipamentosBeans.class;
            //case MARCA:
              //  return MarcaEquipamentosBeans.class;
            case MODELO:
                return String.class;
            case LARGURA:
                return Double.class;
            case STATUS:
                return Boolean.class;
            default:
                throw new IndexOutOfBoundsException("column out of bounds getColumnClass");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ModeloEquipamentosBeans beans = ListPrincipal.get(rowIndex);
        switch (columnIndex) {
            case ID_MODELO:
                return beans.getID();
            //case CATEGORIA:
              //  return beans.getCategoria();
            //case MARCA:
              //  return beans.getMarca();
            case MODELO:
                return beans.getDescricao();
            case LARGURA:
                return beans.getLargura();
            case STATUS:
                return beans.getStatus();
            default:
                throw new IndexOutOfBoundsException("column out of bounds");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        ModeloEquipamentosBeans beans = ListPrincipal.get(rowIndex);
        switch (columnIndex) {
            case ID_MODELO:
                beans.setID(Integer.valueOf((String) aValue));
                break;
            //case CATEGORIA:
            //beans.setCategoria(((CategoriaEquipamentosBeans) aValue));
            //break;
            //case MARCA:
            //beans.setMarca(((MarcaEquipamentosBeans) aValue));
            //break;
            case MODELO:
                beans.setDescricao(((String) aValue));
                break;
            case LARGURA:
                beans.setLargura(Double.valueOf((String) aValue));
                break;
            case STATUS:
                beans.setStatus(Boolean.valueOf((String) aValue));
                break;
            default:
                throw new IndexOutOfBoundsException("column out of bounds setValueAt");
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

    public void addRow(ModeloEquipamentosBeans M) {
        ListPrincipal.add(M);
        this.fireTableDataChanged();
    }

    public void addLista(List<ModeloEquipamentosBeans> lista) {
        ListPrincipal = lista;
        this.fireTableDataChanged();
    }
    public void addLista2(List<ModeloEquipamentosBeans> lista) {
        ListPrincipal = lista;
        this.fireTableDataChanged();
    }

    public void removeRow(int linha) {
        ListPrincipal.remove(linha);
        this.fireTableRowsDeleted(linha, linha);

    }

    public ModeloEquipamentosBeans getItem(int index) {
        return ListPrincipal.get(index);

    }

    public void limpar() {
        ListPrincipal.clear();
        this.fireTableDataChanged();
    }
}
