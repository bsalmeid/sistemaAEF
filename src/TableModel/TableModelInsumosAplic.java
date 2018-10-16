/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Utilitarios.Corretores;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class TableModelInsumosAplic extends AbstractTableModel {

    private List<TbInsumosAplicBeans> linhas;

    public TableModelInsumosAplic() {
        linhas = new ArrayList<TbInsumosAplicBeans>();
    }

    public TableModelInsumosAplic(List<TbInsumosAplicBeans> listaDeFretes) {
        linhas = new ArrayList<TbInsumosAplicBeans>(listaDeFretes);
    }

    private String[] colunas = new String[]{"Id Insumo", "Insumo", "Id Categoria", "Categoria", "Dose Control",
        "Dose Min", "Dose Max", "Dose Aplic.", "Q. Consumida", "Q. Retirada", "Id DB"};

    private static final int IDINSUMO = 0;
    private static final int INSUMO = 1;
    private static final int IDCATEGORIA = 2;
    private static final int CATEGORIA = 3;
    private static final int DOSECONTROL = 4;
    private static final int DOSEMIN = 5;
    private static final int DOSEMAX = 6;
    private static final int DOSEAPLIC = 7;
    private static final int QCONSUMIDA = 8;
    private static final int QRETIRADA = 9;
    private static final int IDDB = 10;

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
            case IDINSUMO:
                return Integer.class;
            case INSUMO:
                return String.class;
            case IDCATEGORIA:
                return Integer.class;
            case CATEGORIA:
                return String.class;
            case DOSECONTROL:
                return Boolean.class;
            case DOSEMIN:
                return Double.class;
            case DOSEMAX:
                return Double.class;
            case DOSEAPLIC:
                return String.class;
            case QCONSUMIDA:
                return String.class;
            case QRETIRADA:
                return String.class;
            case IDDB:
                return Integer.class;
            default:
                throw new IndexOutOfBoundsException("column out of bounds");
        }
    }

    @Override
    public boolean isCellEditable(int row, int columnIndex) {
        //Apenas o campo ATIVO será editavel
        if (columnIndex == DOSEAPLIC || columnIndex == QRETIRADA){
              return true;
        }
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //pega o Socio referente a linha especificada;
        TbInsumosAplicBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case IDINSUMO:
                return beans.getIdInsumo();
            case INSUMO:
                return beans.getInsumo();
            case IDCATEGORIA:
                return beans.getIdCategoria();
            case CATEGORIA:
                return beans.getCategoria();
            case DOSECONTROL:
                return beans.isDoseControl();
            case DOSEMIN:
                return beans.getDoseMin();
            case DOSEMAX:
                return beans.getDoseMax();
            case DOSEAPLIC:
                return beans.getDoseAplic();
            case QCONSUMIDA:
                return beans.getQuantConsumida();
            case QRETIRADA:
                return beans.getQuantRetirada();
            case IDDB:
                return beans.getIdDB();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //Pega o sócio referente  a linha especificada
        TbInsumosAplicBeans beans = linhas.get(rowIndex);

        switch (columnIndex) {
            case IDINSUMO:
                beans.setIdInsumo((Integer) aValue);
                break;
            case INSUMO:
                beans.setInsumo((String) aValue);
                break;
            case IDCATEGORIA:
                beans.setIdCategoria((Integer) aValue);
                break;
            case CATEGORIA:
                beans.setCategoria((String) aValue);
                break;
            case DOSECONTROL:
                beans.setDoseControl((Boolean) aValue);
                break;
            case DOSEMIN:
                beans.setDoseMin((Double) aValue);
                break;
            case DOSEMAX:
                beans.setDoseMax((Double) aValue);
                break;
            case DOSEAPLIC:
                beans.setDoseAplic(Double.parseDouble(aValue.toString().replace(",", ".")));
                break;
            case QCONSUMIDA:
                beans.setQuantConsumida((Double) aValue);
                break;
            case QRETIRADA:
                beans.setQuantRetirada(Double.parseDouble(aValue.toString().replace(",", ".")));
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        fireTableCellUpdated(rowIndex, columnIndex);//notifica a atualização da celula

    }

    public TbInsumosAplicBeans getBeans(int indiceLinha) {
        // retorna o socio referente a linha especidicada
        return linhas.get(indiceLinha);
    }

    public void addBeans(TbInsumosAplicBeans beans) {
        //Adiciona registro
        linhas.add(beans);
        // Pega a quantidade de registros e subtrai 1 para
        // achar o último índice. A subtração é necessária
        // porque os índices começam em zero.
        int ultimoIndice = getRowCount() - 1;

//notifica a mudança
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<TbInsumosAplicBeans> beans) {
        //pega o tamanho antigo da tabela, que servirá como indice para o primeiro dos novos registros
        int indice = getRowCount();
        //adiciona os registros
        linhas.addAll(beans);
        //notifica a mudunça
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
        //notifica a mudança;
        fireTableDataChanged();
    }

}
