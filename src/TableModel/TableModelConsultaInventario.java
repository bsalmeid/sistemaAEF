package TableModel;



import Beans.InventarioBeans;
import Utilitarios.Corretores;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableModelConsultaInventario extends AbstractTableModel {

    private final List<InventarioBeans> ListPrincipal = new ArrayList<>();

    private final String[] colunas = {"Id","Fazenda", "Categoria", "Marca", "Modelo", "Frota", "Aquisição", "M", "Largura", "Ano", "Série", "Descrição", "S"};

    public final int ID_INVENTARIO = 0;
    public final int FAZENDA = 1;
    public final int CATEGORIA = 2;
    public final int MARCA = 3;
    public final int MODELO = 4;
    public final int FROTA = 5;
    public final int AQUISICAO = 6;
    public final int MOTORIZADO = 7;
    public final int LARGURA = 8;
    public final int ANO = 9;
    public final int SERIE = 10;
    public final int DESCRICAO = 11;
    public final int STATUS = 12;

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case ID_INVENTARIO:
                return Integer.class;
            case FAZENDA:
                return String.class;    
            case CATEGORIA:
                return String.class;
            case MARCA:
                return String.class;
            case MODELO:
                return String.class;
            case FROTA:
                return String.class;
            case AQUISICAO:
                return Date.class;
            case MOTORIZADO:
                return Boolean.class;
            case LARGURA:
                return Double.class;
            case ANO:
                return String.class;
            case SERIE:
                return String.class;
            case DESCRICAO:
                return String.class;
            case STATUS:
                return Boolean.class;
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
               // if(beans.getId_fazenda() == null)
                return beans.getFazenda();    
            case CATEGORIA:
               // if(beans.getId_categoria() == null)
                return beans.getCategoria();    
            case MARCA:
               // if(beans.getId_marca() == null)
                return beans.getMarca();
            case MODELO:
                //if(beans.getId_modelo() == null)
                return beans.getModelo();
            case FROTA:
                return beans.getnFrota();
            case AQUISICAO:
                return Corretores.ConverterDateStringDDMMAAA(beans.getDataAquisicao());
            case MOTORIZADO:
                return beans.getMotorizado();
            case LARGURA:
                return beans.getLarguraTrabalho();
            case ANO:
                return beans.getAno();
            case SERIE:
                return beans.getSerie();
            case DESCRICAO:
                return beans.getDescricao();
            case STATUS:
                return beans.getStatus();
            default:
                throw new IndexOutOfBoundsException("column out of bounds getValueAt");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        InventarioBeans beans = ListPrincipal.get(rowIndex);
        switch (columnIndex) {
            case ID_INVENTARIO:
                beans.setID(((Integer) aValue));
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
