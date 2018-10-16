
package TableModel;


import Utilitarios.Corretores;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableModelConsultaColaborador extends AbstractTableModel {

    private final List<TbResumoColaborador> ListPrincipal = new ArrayList<>();

    private final String[] colunas = {"ID","Status", "Nome", "CEI", "Fazenda", "Setor","Função","Salário","Dt Admissão"};

    public final int ID_COLABORADOR = 0;
    public final int STATUS = 1;
    public final int NOME = 2;
    public final int CEI = 3;
    public final int FAZENDA = 4;
    public final int SETOR = 5;
    public final int FUNCAO = 6;
    public final int SALARIO = 7;
    public final int DT_ADMISSAO = 8;

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case ID_COLABORADOR:
               return TbResumoColaborador.class;
            case STATUS:
                return String.class;
            case NOME:
                return String.class;
            case CEI:
                return String.class;
            case FAZENDA:
                return String.class;
            case SETOR:
                return String.class;
            case FUNCAO:
                return String.class;
            case SALARIO:
                return Double.class;
            case DT_ADMISSAO:
                return Date.class;    
            default:
                throw new IndexOutOfBoundsException("column out of bounds getColumnClass");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TbResumoColaborador beans = ListPrincipal.get(rowIndex);
        switch (columnIndex) {
            case ID_COLABORADOR:
                return beans.getID_COLABORADOR();
            case STATUS:
                return beans.getSTATUS();
            case NOME:
                return beans.getNOME();
            case CEI:
                return beans.getCEI().toString();
            case FAZENDA:
                return beans.getFAZENDA();
            case SETOR:
                return beans.getSETOR();
            case FUNCAO:
                return beans.getFUNCAO();
            case SALARIO:
                return beans.getSALARIO();
            case DT_ADMISSAO:
                return Corretores.ConverterDateStringDDMMAAA(beans.getDT_ADMISSAO());
            default:
                throw new IndexOutOfBoundsException("column out of bounds getValueAt");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        TbResumoColaborador beans = ListPrincipal.get(rowIndex);
        switch (columnIndex) {
            case ID_COLABORADOR:
                beans.setID_COLABORADOR(((Integer) aValue));
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

    public void addRow(TbResumoColaborador I) {
        ListPrincipal.add(I);
        this.fireTableDataChanged();
    }

    public void addLista(List<TbResumoColaborador> beans) {
        int indice = getRowCount();
        ListPrincipal.addAll(beans);
        fireTableRowsInserted(indice, indice + beans.size());
    }

    public void removeRow(int linha) {
        ListPrincipal.remove(linha);
        this.fireTableRowsDeleted(linha, linha);

    }

    public TbResumoColaborador getItem(int index) {
        return ListPrincipal.get(index);

    }

    public void limpar() {
        ListPrincipal.clear();
        this.fireTableDataChanged();
    }
}
