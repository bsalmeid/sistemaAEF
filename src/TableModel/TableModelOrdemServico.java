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
public class TableModelOrdemServico extends AbstractTableModel {

    private List<TbOrdemServicoBeans> linhas;

    public TableModelOrdemServico() {
        linhas = new ArrayList<TbOrdemServicoBeans>();
    }

    public TableModelOrdemServico(List<TbOrdemServicoBeans> listaDeFretes) {
        linhas = new ArrayList<TbOrdemServicoBeans>(listaDeFretes);
    }

    private String[] colunas = new String[]{"ID", "Status", "Data Ordem", "Safra", "Cultivo", "Cultura", "Operação", "Aplicação",
        "Fazenda", "Talhão", "Area Talhão", "Area Planejada", "Area Aplicada"};

    public final int ID = 0;
    public final int STATUS = 1;
    public final int DATAORDEM = 2;
    public final int SAFRA = 3;
    public final int CULTIVO = 4;
    public final int CULTURA = 5;
    public final int OPERACAO = 6;
    public final int APLICACAO = 7;
    public final int FAZENDA = 8;
    public final int TALHAO = 9;
    public final int AREATALHAO = 10;
    public final int AREAPLANEJADA = 11;
    public final int AREAAPLICADA = 12;

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
            case STATUS:
                return Boolean.class;
            case DATAORDEM:
                return String.class;
            case SAFRA:
                return String.class;
            case CULTIVO:
                return String.class;
            case CULTURA:
                return String.class;
            case OPERACAO:
                return String.class;
            case APLICACAO:
                return String.class;
            case FAZENDA:
                return String.class;
            case TALHAO:
                return String.class;
            case AREATALHAO:
                return Double.class;
            case AREAPLANEJADA:
                return Double.class;
            case AREAAPLICADA:
                return Double.class;
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
        TbOrdemServicoBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                return beans.getID();
            case STATUS:
                return beans.isStatus();
            case DATAORDEM:
                return beans.getDataOrdem();
            case SAFRA:
                return beans.getSafra();
            case CULTIVO:
                return beans.getCultivo();
            case CULTURA:
                return beans.getCultura();
            case OPERACAO:
                return beans.getOperacao();
            case APLICACAO:
                return beans.getAplicacao();
            case FAZENDA:
                return beans.getFazenda();
            case TALHAO:
                return beans.getTalhao();
            case AREATALHAO:
                return beans.getAreaTalhao();
            case AREAPLANEJADA:
                return beans.getAreaPlanejada();
            case AREAAPLICADA:
                return beans.getAreaAplic();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //Pega o sócio referente  a linha especificada
        TbOrdemServicoBeans beans = linhas.get(rowIndex);

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
    public TbOrdemServicoBeans getBeans(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addBeans(TbOrdemServicoBeans beans) {
        //Adiciona registro
        linhas.add(beans);
        // Pega a quantidade de registros e subtrai 1 para
        // achar o último índice. A subtração é necessária
        // porque os índices começam em zero.
        int ultimoIndice = getRowCount() - 1;

//notifica a mudança
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<TbOrdemServicoBeans> beans) {
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
