/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class TableModelPlantio extends AbstractTableModel {

    private List<TbPlantioBeans> linhas;

    public TableModelPlantio() {
        linhas = new ArrayList<TbPlantioBeans>();
    }

    public TableModelPlantio(List<TbPlantioBeans> listaDeFretes) {
        linhas = new ArrayList<TbPlantioBeans>(listaDeFretes);
    }

    private String[] colunas = new String[]{"ID", "Ordem Serviço", "Data Plantio", "Safra", "Cultivo", "Operação", "Cultura",  "Cultivar",
       "Stand" ,"Fazenda", "Talhão", "Area Talhão", "% Plantada", "Area Plantada", "Data Colheita", "Equipe Plantio" };

    private static final int ID = 0;
    private static final int IDORDEM = 1;
    private static final int DATAPLANTIO = 2;
    private static final int SAFRA = 3;
    private static final int CULTIVO = 4;
    private static final int OPERACAO = 5;
    private static final int CULTURA = 6;
    private static final int CULTIVAR = 7;
    private static final int STANDINICIAL =8;
    private static final int FAZENDA = 9;
    private static final int TALHAO = 10;
    private static final int AREATALHAO = 11;
    private static final int PORCENTPLANTADA = 12;
    private static final int AREAPLANTADA = 13;
    private static final int DATACOLHEITA = 14;
    private static final int EQUIPEPLANTIO = 15;

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
            case ID:
                return Integer.class;
            case IDORDEM:
                return Integer.class;
            case DATAPLANTIO:
                return String.class;
            case SAFRA:
                return String.class;
            case CULTIVO:
                return String.class;
            case OPERACAO:
                return String.class;
            case CULTURA:
                return String.class;
            case CULTIVAR:
                return String.class;
            case STANDINICIAL:
                return Double.class;    
            case FAZENDA:
                return String.class;
            case TALHAO:
                return String.class;
            case AREATALHAO:
                return Double.class;
            case PORCENTPLANTADA:
                return Double.class;
            case AREAPLANTADA:
                return Double.class;
            case DATACOLHEITA:
                return String.class;
            case EQUIPEPLANTIO:
                return String.class;    
                
            default:
                throw new IndexOutOfBoundsException("column out of bounds");
        }
    }

    @Override
    public boolean isCellEditable(int row, int columnIndex) {
        //Apenas o campo ATIVO será editavel
        // if (columnIndex == DESCCONTA || columnIndex == ID || columnIndex == IDPAGTO ){
        //      return false;
        //}
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //pega o Socio referente a linha especificada;
        TbPlantioBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                return beans.getID();
            case IDORDEM:
                return beans.getIdOrdemServico();
            case DATAPLANTIO:
                return beans.getDataPlantio();
            case SAFRA:
                return beans.getSafra();
            case CULTIVO:
                return beans.getCultivo();
            case OPERACAO:
                return beans.getOperacao();
            case CULTURA:
                return beans.getCultura();
            case CULTIVAR:
                return beans.getCultivar();
            case STANDINICIAL:
                return beans.getStandInicial();    
            case FAZENDA:
                return beans.getFazenda();
            case TALHAO:
                return beans.getTalhao();
            case AREATALHAO:
                return beans.getAreaTalhao();
            case PORCENTPLANTADA:
                return beans.getPorcentagemPlantada();
            case AREAPLANTADA:
                return beans.getAreaPlantada();
            case DATACOLHEITA:
                return beans.getDataColheitaPrevista();
            case EQUIPEPLANTIO:
                return beans.getEquipePlantio();    
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //Pega o sócio referente  a linha especificada
        TbPlantioBeans beans = linhas.get(rowIndex);

        switch (columnIndex) {
            case ID:
                beans.setID((Integer) aValue);
                break;

            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        fireTableCellUpdated(rowIndex, columnIndex);//notifica a atualização da celula

    }

// retorna o socio referente a linha especidicada      
    public TbPlantioBeans getBeans(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addBeans(TbPlantioBeans beans) {
        //Adiciona registro
        linhas.add(beans);
        // Pega a quantidade de registros e subtrai 1 para
        // achar o último índice. A subtração é necessária
        // porque os índices começam em zero.
        int ultimoIndice = getRowCount() - 1;

//notifica a mudança
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<TbPlantioBeans> beans) {
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
