package TableModel;


import Beans.InventarioBeans;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableModelInventarioFinanceiro extends AbstractTableModel {

    private final List<InventarioBeans> ListPrincipal = new ArrayList<>();

    private final String[] colunas = {"ID","Frota", "Fazenda", "Categoria", "Marca", "Modelo"};

    public final int ID_INVENTARIO = 0;
    public final int FROTA = 1;
    public final int FAZENDA = 2;
    public final int CATEGORIA = 3;
    public final int MARCA = 4;
    public final int MODELO = 5;

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case ID_INVENTARIO:
               return InventarioBeans.class;
            case CATEGORIA:
                return String.class;
            case FAZENDA:
                return String.class;
            case MARCA:
                return String.class;
            case MODELO:
                return String.class;
            case FROTA:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("column out of bounds getColumnClass");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InventarioBeans beans = ListPrincipal.get(rowIndex);
        switch (columnIndex) {
            case ID_INVENTARIO:
                return beans.getID();
            case FAZENDA:
                if(beans.getId_fazenda() == null)
                return beans.getFazenda();    
            case CATEGORIA:
                if(beans.getId_categoria() == null)
                return beans.getCategoria();    
            case MARCA:
                if(beans.getId_marca() == null)
                return beans.getMarca();
            case MODELO:
                if(beans.getId_modelo() == null)
                return beans.getModelo();
            case FROTA:
                return beans.getnFrota();
            default:
                throw new IndexOutOfBoundsException("column out of bounds getValueAt");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        InventarioBeans beans = ListPrincipal.get(rowIndex);
        switch (columnIndex) {
            //case ID_INVENTARIO:
            //    beans.setID(Integer.valueOf((String) aValue));
            //    break;
            case CATEGORIA:
                beans.setCategoria(((String) aValue));
                break;
            case FAZENDA:
                beans.setFazenda(((String) aValue));
                break;    
            case MARCA:
                beans.setMarca(((String) aValue));
                break;
            case MODELO:
                beans.setModelo(((String) aValue));
                break;
            case FROTA:
                beans.setnFrota(((String) aValue));
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

    public void addRow(InventarioBeans I) {
        ListPrincipal.add(I);
        this.fireTableDataChanged();
    }

    public void addLista(List<InventarioBeans> beans) {
        int indice = getRowCount();
        ListPrincipal.addAll(beans);
        fireTableRowsInserted(indice, indice + beans.size());
    }

    public void removeRow(int linha) {
        ListPrincipal.remove(linha);
        this.fireTableRowsDeleted(linha, linha);

    }

    public InventarioBeans getItem(int index) {
        return ListPrincipal.get(index);

    }

    public void limpar() {
        ListPrincipal.clear();
        this.fireTableDataChanged();
    }
}
