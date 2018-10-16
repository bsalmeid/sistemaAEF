package TableModel;

import Beans.CadPlacaBeans;
import Beans.TransportadorasBeans;
import DAO.TransportadorasDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Usuario
 */
public class TableModelCadastroTransportadoras extends AbstractTableModel {

    private List<CadPlacaBeans> ListPrincipal = new ArrayList<>();
    
    private String[] colunas = {"ID", "Placa", "Tipo Veículo", "Carroceria", "Motorista", "CPF", "Telefone",
        "Próprio", "Marca", "Modelo", "Ano", "Capacidade", "Status"};

    public final int ID_PLACA = 0;
    public final int PLACA = 1;
    public final int VEICULO = 2;
    public final int CARROCERIA = 3;
    public final int MOTORISTA = 4;
    public final int CPF = 5;
    public final int TELEFONE = 6;
    public final int CAM_PROPRIO = 7;
    public final int MARCA = 8;
    public final int MODELO = 9;
    public final int ANO = 10;
    public final int CAPACIDADE = 11;
    public final int STATUS = 12;
   // public final int TRANSPORTADORAS = 13;
    

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case ID_PLACA:
                return Long.class;
            case PLACA:
                return String.class;
            case VEICULO:
                return String.class;
            case CARROCERIA:
                return String.class;
            case MOTORISTA:
                return String.class;
            case CPF:
                return String.class;
            case TELEFONE:
                return String.class;
            case CAM_PROPRIO:
                return Boolean.class;
            case MARCA:
                return String.class;
            case MODELO:
                return String.class;
            case ANO:
                return String.class;
            case CAPACIDADE:
                return Double.class;
            case STATUS:
                return Boolean.class;
            //case TRANSPORTADORAS:
            //    return Integer.class;    
            default:
                throw new IndexOutOfBoundsException("column out of bounds getColumnClass");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CadPlacaBeans beans = ListPrincipal.get(rowIndex);
        switch (columnIndex) {
            case ID_PLACA:
                return beans.getId();
            case PLACA:
                return beans.getPlaca();
            case VEICULO:
                return beans.getVeiculo();
            case CARROCERIA:
                return beans.getCarroceria();
            case MOTORISTA:
                return beans.getMotorista();
            case CPF:
                return beans.getCpf();
            case TELEFONE:
                return beans.getTelefone();
            case CAM_PROPRIO:
                return beans.getCamProprio();
            case MARCA:
                return beans.getMarca();
            case MODELO:
                return beans.getModelo();
            case ANO:
                return beans.getAno();
            case CAPACIDADE:
                return beans.getCapacidadeCarga();
            case STATUS:
                return beans.getStatus();
            //case TRANSPORTADORAS:
            //    return beans.getTransportadora();
            default:
                throw new IndexOutOfBoundsException("column out of bounds getValueAt");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        CadPlacaBeans beans = ListPrincipal.get(rowIndex);
        switch (columnIndex) {
            case ID_PLACA:
                beans.setId(Long.valueOf((String) aValue));
                break;
            case PLACA:
                beans.setPlaca(((String) aValue));
                break;
            case VEICULO:
                beans.setVeiculo(((String) aValue));
                break;
            case CARROCERIA:
                beans.setCarroceria(((String) aValue));
                break;
            case MOTORISTA:
                beans.setMotorista(((String) aValue));
                break;
            case CPF:
                beans.setCpf(((String) aValue));
                break;
            case TELEFONE:
               beans.setTelefone(((String) aValue));
                break;
            case CAM_PROPRIO:
               beans.setCamProprio(Boolean.valueOf((String) aValue));
                break;
            case MARCA:
                beans.setMarca(((String) aValue));
                break;
            case MODELO:
                beans.setModelo(((String) aValue));
                break;
            case ANO:
                beans.setAno(((String) aValue));
                break;
            case CAPACIDADE:
                beans.setCapacidadeCarga(Double.valueOf((String) aValue));
                break;
            case STATUS:
                beans.setStatus(Boolean.valueOf((String) aValue));
                break;
            //case TRANSPORTADORAS:
            //    beans.setTransportadora(((ListTransportadoras) aValue));
            //    break;    
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

    public void addRow(CadPlacaBeans t) {
        ListPrincipal.add(t);
        this.fireTableDataChanged();
    }

    public void addLista(List<CadPlacaBeans> lista){
        ListPrincipal = lista;
        this.fireTableDataChanged();
    }
    
    public void removeRow(int linha) {
        ListPrincipal.remove(linha);
        this.fireTableRowsDeleted(linha, linha);

    }

    public CadPlacaBeans getItem(int index) {
        return ListPrincipal.get(index);

    }

    public void limpar() {
        ListPrincipal.clear();
        this.fireTableDataChanged();
    }
    
    public List<CadPlacaBeans> getLista(){
        return ListPrincipal;
    }
    
    public void addBeans(CadPlacaBeans beans) {
        ListPrincipal.add(beans);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

}
