/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Beans.PropriedadesBeans;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class TableModelCadFazenda extends AbstractTableModel {

    private List<PropriedadesBeans> linhas;

    public TableModelCadFazenda() {
        linhas = new ArrayList<>();
    }

    public TableModelCadFazenda(List<PropriedadesBeans> listaDeFretes) {
        linhas = new ArrayList<>(listaDeFretes);
    }

    private String[] colunas = new String[]{"ID", "Nome", "Estado",  "Cidade", "Tipo" ,"Área", "Inscrição" ,"Status" };

    public final int CODIGO = 0;
    public final int NOME = 1;
    public final int ESTADO = 2;
    public final int CIDADE = 3;
    public final int TIPO = 4;
    public final int AREATOTAL = 5;
    public final int INSCRICAO = 6;
    public final int STATUS = 7;
 
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
            case CODIGO:
                return Integer.class;
            case NOME:
                return String.class;
            case ESTADO:
                return String.class;
            case CIDADE:
                return String.class;
            case TIPO:
                return String.class;
            case AREATOTAL:
                return Double.class;
            case INSCRICAO:
                return String.class;         
            case STATUS:
                return String.class;                
            default:
                throw new IndexOutOfBoundsException("column out of bounds");
        }
    }

    @Override
    public boolean isCellEditable(int row, int columnIndex) {
        //Apenas o campo ATIVO será editavel
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PropriedadesBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case CODIGO:
                return beans.getCodigo();
            case NOME:
                return beans.getNome();
            case ESTADO:
                return beans.getEstado();
            case CIDADE:
                return beans.getCidade();
            case TIPO:
                return beans.getTipo();
            case AREATOTAL:
                return beans.getArea();
            case INSCRICAO:
                return beans.getInscricao();       
            case STATUS:
                return beans.getStatus(); 
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    
        PropriedadesBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case CODIGO:
                beans.setCodigo((Integer) aValue);
                break;
            case NOME:
                beans.setNome((String) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        fireTableCellUpdated(rowIndex, columnIndex);//notifica a atualização da celula
    }

    public PropriedadesBeans getBeans(int indiceLinha) {
        // retorna o socio referente a linha especidicada
        return linhas.get(indiceLinha);
    }

    public void addBeans(PropriedadesBeans beans) {
        linhas.add(beans);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<PropriedadesBeans> beans) {
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
        //remove todos os elementos da lista sócios;
        linhas.clear();
        fireTableDataChanged();
    }

    public List<PropriedadesBeans> getLista(){
        return linhas;
    }
    
}
