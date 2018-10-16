/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Beans.TransferenciasBancosBeans;
import Utilitarios.Corretores;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class TableModelTransfBancos extends AbstractTableModel {

    private List<TransferenciasBancosBeans> linhas;

    public TableModelTransfBancos() {
        linhas = new ArrayList<TransferenciasBancosBeans>();
    }

    public TableModelTransfBancos(List<TransferenciasBancosBeans> listaPagtos) {
        linhas = new ArrayList<TransferenciasBancosBeans>(listaPagtos);
    }

    private String[] colunas = new String[]{"ID", "Data", "idContaOrigem", "Conta de Origem", "idContaDestino", "Conta de Destino",
        "Descricão", "Plano de Contas", "Descrição Plano", "Valor"
    };

    public final int ID = 0;
    public final int DATA = 1;
    public final int IDCONTAORIGEM = 2;
    public final int CONTAORIGEM = 3;
    public final int IDCONTADESTINO = 4;
    public final int CONTADESTINO = 5;
    public final int DESCRICAO = 6;
    public final int PLANOCONTAS = 7;
    public final int DESCPLANOCONTAS = 8;
    public final int VALOR = 9;

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

    public void addRow(TransferenciasBancosBeans Beans) {
        int indice = getRowCount() - 1;
        linhas.add(Beans);
        fireTableRowsInserted(indice, indice);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case ID:
                return Integer.class;
            case DATA:
                return Date.class;
            case IDCONTAORIGEM:
                return Integer.class;
            case IDCONTADESTINO:
                return Integer.class;
            case CONTAORIGEM:
                return String.class;
            case CONTADESTINO:
                return String.class;
            case DESCRICAO:
                return String.class;
            case PLANOCONTAS:
                return Integer.class;
            case DESCPLANOCONTAS:
                return String.class;
            case VALOR:
                return Double.class;
            default:
                throw new IndexOutOfBoundsException("column out of bounds");
        }
    }

    @Override
    public boolean isCellEditable(int row, int columnIndex) {

        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        TransferenciasBancosBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                return beans.getID();
            case DATA:
                return Corretores.ConverterDateStringDDMMAAA(beans.getDtMovimentos());
            case IDCONTAORIGEM:
                return beans.getIdContaOrigem();
            case IDCONTADESTINO:
                return beans.getIdContaDestino();
            case CONTAORIGEM:
                return beans.getIdContaOrigem().getDescricao();
            case CONTADESTINO:
                return beans.getIdContaDestino().getDescricao();
            case DESCRICAO:
                return beans.getDescricao();
            case PLANOCONTAS:
                return beans.getPlanoContas();
            case DESCPLANOCONTAS:
                return beans.getDescPlanoContas();
            case VALOR:
                return beans.getValor();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        TransferenciasBancosBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                beans.setID((Integer) aValue);
                break;
            /*    case IDPAGTO:
                beans.setIDPagto((Integer) aValue);
                break;
            case DTEMISSAO:
                beans.setDtEmissao((String) aValue);
                break;
            case TIPOCAD:
                beans.setTipoCad((String) aValue);
                break;
            case NCAD:
                beans.setnCad((String) aValue);
                break;
            case TIPODOC:
                beans.setTipoDoc((String) aValue);
                break;
            case NDOC:
                beans.setNDOC((Integer) aValue);
                break;
            case FAZENDA:
                beans.setFazenda((String) aValue);
                break;    
            case IDEMISSOR:
                beans.setIdFornecedor((Integer) aValue);
                break;
            case EMISSOR:
                beans.setFornecedor((String) aValue);
                break;
            case VALOR:
                beans.setValor((BigDecimal) aValue);
                break;   */
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        fireTableCellUpdated(rowIndex, columnIndex);

    }
 
    public TransferenciasBancosBeans getBeans(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addBeans(TransferenciasBancosBeans beans) {
        linhas.add(beans);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<TransferenciasBancosBeans> beans) {
        int indice = getRowCount();
        linhas.addAll(beans);
        fireTableRowsInserted(indice, indice + beans.size() + 1);
    }

    public void limpar() {
        linhas.clear();
        fireTableDataChanged();
    }

}
