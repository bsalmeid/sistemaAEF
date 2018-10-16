
package TableModel;

import Beans.TalhaoBeans;
import Beans.PropriedadesBeans;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableModelCadTalhao extends AbstractTableModel {
    
    private String[] colunas = new String[]{"Id Talhão", "Fazenda", "Nº Talhão",  "Cultivo", "Pluviometro" ,"Área", "Mecânizavel" ,"Status" };
    private List<TalhaoBeans> linhas;
    public final int ID_TALHAO = 0;
    public final int FAZENDA = 1;
    public final int NOME_TALHAO = 2;
    public final int CULTIVO = 3;
    public final int PLUVIOMETRO = 4;
    public final int AREA = 5;
    public final int MECANIZAVEL = 6;
    public final int STATUS = 7;
 
    
    public TableModelCadTalhao() {
        linhas = new ArrayList<>();
    }

    public TableModelCadTalhao(List<TalhaoBeans> listaDeFretes) {
        linhas = new ArrayList<>(listaDeFretes);
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
            case ID_TALHAO:
                return Integer.class;
            case NOME_TALHAO:
                return String.class;
            case FAZENDA:
                return String.class;
            case CULTIVO:
                return String.class;
            case PLUVIOMETRO:
                return String.class;
            case AREA:
                return Double.class;
            case MECANIZAVEL:
                return Boolean.class;         
            case STATUS:
                return Boolean.class;                
            default:
                throw new IndexOutOfBoundsException("column out of bounds");
        }
    }

    @Override
    public boolean isCellEditable(int row, int columnIndex) {
       
        return columnIndex == STATUS || columnIndex == MECANIZAVEL || columnIndex == AREA;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TalhaoBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID_TALHAO:
                return beans.getID();
            case NOME_TALHAO:
                return beans.getTalhao();
            case FAZENDA:
                if (beans.getFazenda() == null){ return "";
                } else { return beans.getFazenda().getNome();}
            case CULTIVO:
                return beans.getCultivo();
            case PLUVIOMETRO:
                return beans.getPluviometro();
            case AREA:
                return beans.getArea();
            case MECANIZAVEL:
                return beans.isMecanizavel();       
            case STATUS:
                return beans.isStatus(); 
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    
        TalhaoBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID_TALHAO:
                beans.setID((Integer) aValue);
                break;
            case NOME_TALHAO:
                beans.setTalhao((String) aValue);
                break;
            case FAZENDA:
                beans.setFazenda((PropriedadesBeans) aValue);
                break;
            case CULTIVO:
                beans.setCultivo((String) aValue);
                break;
            case PLUVIOMETRO:
                beans.setPluviometro((String) aValue);
                break;
            case AREA:
                beans.setArea((Double) aValue);
                break;
            case MECANIZAVEL:
                beans.setMecanizavel((Boolean) aValue);
                break;    
            case STATUS:
                beans.setStatus((Boolean) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        fireTableCellUpdated(rowIndex, columnIndex);//notifica a atualização da celula
    }

    public TalhaoBeans getBeans(int indiceLinha) {
      
        return linhas.get(indiceLinha);
    }

    public void editarBean(TalhaoBeans beans, int rowIndex){
        linhas.set(rowIndex, beans);
        this.fireTableRowsUpdated(rowIndex, rowIndex);
    }
    
    public void addBeans(TalhaoBeans beans) {
        linhas.add(beans);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<TalhaoBeans> beans) {
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

    public List<TalhaoBeans> getLista(){
        return linhas;
    }
    
}
