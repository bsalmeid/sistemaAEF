package TableModel;


import Beans.InventarioBeans;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableModelCadastroInventario extends AbstractTableModel {

    private List<InventarioBeans> ListPrincipal = new ArrayList<>();

    private final String[] colunas = {"Id","Frota","Aquisição","Motorizado","Descrição","Série","Largura","Ano", "Observação", "Status"};

    public final int ID_INVENTARIO = 0;
    public final int FROTA = 1;
    public final int AQUISICAO = 2;
    public final int MOTORIZADO = 3;
    public final int DESCRICAO = 4;
    public final int SERIE = 5;
    public final int LARGURA = 6;
    public final int ANO = 7;
    public final int OBSERVACAO = 8;
    public final int STATUS = 9;

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case ID_INVENTARIO:
                return Integer.class;
            case FROTA:
                return String.class;
            case AQUISICAO:
                return Date.class;
            case MOTORIZADO:
                return Boolean.class;
            case DESCRICAO:
                return String.class;
            case SERIE:
                return String.class;
            case LARGURA:
                return Double.class;
            case ANO:
                return String.class;
            case OBSERVACAO:
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
            case FROTA:
                return beans.getnFrota();
            case AQUISICAO:
                return beans.getDataAquisicao();
            case MOTORIZADO:
                return beans.getMotorizado();
            case DESCRICAO:
                return beans.getDescricao(); 
            case SERIE:
                return beans.getSerie();
            case LARGURA:
                return beans.getLarguraTrabalho();
            case ANO:
                return beans.getAno();
            case OBSERVACAO:
                return beans.getObservacao();    
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
                beans.setID(Integer.valueOf((String) aValue));
                break;
            case FROTA:
                beans.setnFrota(((String) aValue));
                break;
            case AQUISICAO:
                beans.setDataAquisicao(((Date) aValue));
                break;
            case MOTORIZADO:
                beans.setMotorizado(Boolean.valueOf((String) aValue));
                break;
            case DESCRICAO:
                beans.setDescricao(((String) aValue));
                break;
            case SERIE:
                beans.setSerie(((String) aValue));
                break;
            case LARGURA:
                beans.setLarguraTrabalho(Double.valueOf((String) aValue));
                break;
            case ANO:
                beans.setAno(((String) aValue));
                break;
            case OBSERVACAO:
                beans.setObservacao(((String) aValue));
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

    public void addRow(InventarioBeans I) {
        ListPrincipal.add(I);
        this.fireTableDataChanged();
    }

    public void addLista(List<InventarioBeans> lista) {
        ListPrincipal = lista;
        this.fireTableDataChanged();
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
