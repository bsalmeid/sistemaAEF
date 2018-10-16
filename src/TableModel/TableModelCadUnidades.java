package TableModel;

import Beans.CadUnidadesBeans;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableModelCadUnidades extends AbstractTableModel {

    private List<CadUnidadesBeans> linhas = new ArrayList<>();

    private final String[] colunas = {"ID", "Descrição", "Conversão(Kg/L)", "Status"};

    public final int ID_UNIDADES = 0;
    public final int DESCRICAO = 1;
    public final int CONVERSAO = 2;
    public final int STATUS = 3;

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case ID_UNIDADES:
                return Integer.class;
            case DESCRICAO:
                return String.class;
            case CONVERSAO:
                return Double.class;
            case STATUS:
                return Boolean.class;
            default:
                throw new IndexOutOfBoundsException("column out of bounds getColumnClass");
        }
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
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CadUnidadesBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID_UNIDADES:
                return beans.getID();
            case DESCRICAO:
                return beans.getDescricao();
            case CONVERSAO:
                return beans.getConversaoKg_L();
            case STATUS:
                return beans.getStatus();
            default:
                throw new IndexOutOfBoundsException("column out of bounds getValueAt");
        }
    }

    public void addRow(CadUnidadesBeans U) {
        linhas.add(U);
        this.fireTableDataChanged();
    }

    public void addLista(List<CadUnidadesBeans> lista) {
        linhas = lista;
        this.fireTableDataChanged();
    }

    public void removeRow(int linha) {
        linhas.remove(linha);
        this.fireTableRowsDeleted(linha, linha);

    }

    public CadUnidadesBeans getItem(int index) {
        return linhas.get(index);

    }

    public void limpar() {
        linhas.clear();
        this.fireTableDataChanged();
    }
}
