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
public class TableModelTalhoesAplic extends AbstractTableModel {

    private List<TbTalhaoAplicBeans> linhas;

    public TableModelTalhoesAplic() {
        linhas = new ArrayList<TbTalhaoAplicBeans>();
    }

    public TableModelTalhoesAplic(List<TbTalhaoAplicBeans> listaDeFretes) {
        linhas = new ArrayList<TbTalhaoAplicBeans>(listaDeFretes);
    }

    private String[] colunas = new String[]{"Id Talhão", "Nº Talhão", "Id Fazenda", "Data Inicio", "Data Término",
        "% Aplicada", "Área Talhão", "Area Planejada", "IdDB", "Hora Máq", "Area Aplic T", "Area Trablhada"};

    private static final int IDTALHAO = 0;
    private static final int NOMETALHAO = 1;
    private static final int IDFAZENDA = 2;
    private static final int DATAINICIO = 3;
    private static final int DATATERMINO = 4;
    private static final int PORCENTAGEM = 5;
    private static final int AREATALHAO = 6;
    private static final int AREAPLANEJADA = 7;
    private static final int IDDB = 8;
    private static final int HMTRABALHADA = 9;
    private static final int AREAAPLICADATOTAL = 10;
    private static final int AREAAPLICADA = 11;
    

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
            case IDTALHAO:
                return Integer.class;
            case NOMETALHAO:
                return String.class;
            case IDFAZENDA:
                return Integer.class;
            case DATAINICIO:
                return String.class;
            case DATATERMINO:
                return String.class;
            case PORCENTAGEM:
                return String.class;
            case AREATALHAO:
                return Double.class;
            case AREAPLANEJADA:
                return String.class;
            case IDDB:
                return Integer.class;
            case HMTRABALHADA:
                return String.class;
            case AREAAPLICADATOTAL:
                return String.class;
            case AREAAPLICADA:
                return String.class;
                
            default:
                throw new IndexOutOfBoundsException("column out of bounds");
        }
    }

    @Override
    public boolean isCellEditable(int row, int columnIndex) {
        //Apenas o campo ATIVO será editavel
        if (columnIndex == PORCENTAGEM || columnIndex == AREAPLANEJADA || columnIndex == DATAINICIO 
                || columnIndex == DATATERMINO || columnIndex == HMTRABALHADA || columnIndex == AREAAPLICADA ) {
            return true;
        }
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //pega o Socio referente a linha especificada;
        TbTalhaoAplicBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case IDTALHAO:
                return beans.getIdTalha();
            case NOMETALHAO:
                return beans.getnTalhao();
            case IDFAZENDA:
                return beans.getIdFazenda();
            case DATAINICIO:
                return beans.getDataInicio();
            case DATATERMINO:
                return beans.getDataFinal();
            case PORCENTAGEM:
                return beans.getPorcentAplic();
            case AREATALHAO:
                return beans.getAreaTalhao();
            case AREAPLANEJADA:
                return beans.getAreaPlanejada();
            case IDDB:
                return beans.getIdDB();
            case HMTRABALHADA:
                return beans.getHMTrabalhada();
            case AREAAPLICADATOTAL:
                return beans.getAreaAplicTotal();    
            case AREAAPLICADA:
                return beans.getAreaAplic();    
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //Pega o sócio referente  a linha especificada
        TbTalhaoAplicBeans beans = linhas.get(rowIndex);

        switch (columnIndex) {
            case IDTALHAO:
                beans.setIdTalha((Integer) aValue);
                break;
            case NOMETALHAO:
                beans.setnTalhao((String) aValue);
                break;
            case IDFAZENDA:
                beans.setIdFazenda((Integer) aValue);
                break;
            case DATAINICIO:
                beans.setDataInicio((String) aValue);
                break;
            case DATATERMINO:
                beans.setDataFinal((String) aValue);
                break;
            case PORCENTAGEM:
                beans.setPorcentAplic(Double.parseDouble(aValue.toString().replace(",", ".")));
                break;
            case AREATALHAO:
                beans.setAreaTalhao((Double) aValue);
                break;
            case AREAPLANEJADA:
                beans.setAreaPlanejada(Double.parseDouble(aValue.toString().replace(",", ".")));
                break;
            case HMTRABALHADA:
                beans.setHMTrabalhada(Double.parseDouble(aValue.toString().replace(",", ".")));
                break;
            case AREAAPLICADATOTAL:
                beans.setAreaAplicTotal(Double.parseDouble(aValue.toString().replace(",", ".")));
                break;
            case AREAAPLICADA:
                beans.setAreaAplic(Double.parseDouble(aValue.toString().replace(",", ".")));
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        fireTableCellUpdated(rowIndex, columnIndex);//notifica a atualização da celula
    }

    public TbTalhaoAplicBeans getBeans(int indiceLinha) {
        // retorna o socio referente a linha especidicada
        return linhas.get(indiceLinha);
    }

    public void addBeans(TbTalhaoAplicBeans beans) {
        //Adiciona registro
        linhas.add(beans);
        // Pega a quantidade de registros e subtrai 1 para
        // achar o último índice. A subtração é necessária
        // porque os índices começam em zero.
        int ultimoIndice = getRowCount() - 1;

//notifica a mudança
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<TbTalhaoAplicBeans> beans) {
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
