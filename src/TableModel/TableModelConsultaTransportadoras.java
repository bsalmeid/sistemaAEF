/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Beans.CadPlacaBeans;
import Beans.TransportadorasBeans;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Usuario
 */
public class TableModelConsultaTransportadoras extends AbstractTableModel {

    private List<CadPlacaBeans> ListPrincipal = new ArrayList<>();

    private String[] colunas = {"ID", "Transportadora", "Placa", "Tipo Veículo", "Carroceria",
        "Próprio", "Marca", "Modelo", "Ano", "Capacidade", "Status"};

    public final int ID_PLACA = 0;
    public final int TRANSPORTADORA = 1;
    public final int PLACA = 2;
    public final int VEICULO = 3;
    public final int CARROCERIA = 4;
    public final int CAM_PROPRIO = 5;
    public final int MARCA = 6;
    public final int MODELO = 7;
    public final int ANO = 8;
    public final int CAPACIDADE = 9;
    public final int STATUS = 10;

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case ID_PLACA:
                return Integer.class;
            case TRANSPORTADORA:
                return TransportadorasBeans.class;
            case PLACA:
                return String.class;
            case VEICULO:
                return String.class;
            case CARROCERIA:
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
            default:
                throw new IndexOutOfBoundsException("column out of bounds");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CadPlacaBeans beans = ListPrincipal.get(rowIndex);
        switch (columnIndex) {
            case ID_PLACA:
                return beans.getTransportadora().getID();
            case TRANSPORTADORA:
                return beans.getTransportadora();
            case PLACA:
                return beans.getPlaca();
            case VEICULO:
                return beans.getVeiculo();
            case CARROCERIA:
                return beans.getCarroceria();
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
            default:
                throw new IndexOutOfBoundsException("column out of bounds");
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
            case TRANSPORTADORA:
                beans.setTransportadora(((TransportadorasBeans) aValue));
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

    public void addRow(CadPlacaBeans t) {
        ListPrincipal.add(t);
        this.fireTableDataChanged();
    }

    public void addLista(List<CadPlacaBeans> beans) {
        int indice = getRowCount();
        ListPrincipal.addAll(beans);
        fireTableRowsInserted(indice, indice + beans.size());
    }

    public void removeRow(int linha) {
        ListPrincipal.remove(linha);
        this.fireTableRowsDeleted(linha, linha);

    }

    public CadPlacaBeans getItem(int index) {
        return ListPrincipal.get(index);

    }

    public void limpar() {
        this.ListPrincipal.clear();
        this.fireTableDataChanged();
    }

}
